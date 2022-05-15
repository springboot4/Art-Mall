package com.fxz.mall.product.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxz.mall.product.constant.ProductConstant;
import com.fxz.mall.product.dto.CheckPriceDTO;
import com.fxz.mall.product.dto.LockStockDTO;
import com.fxz.mall.product.dto.SkuDto;
import com.fxz.mall.product.dto.SkuInfoDTO;
import com.fxz.mall.product.entity.Sku;
import com.fxz.mall.product.mapper.SkuMapper;
import com.fxz.mall.product.service.SkuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 商品库存表
 *
 * @author fxz
 * @date 2022-05-06
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku> implements SkuService {

	private final SkuMapper skuMapper;

	private final RedisTemplate redisTemplate;

	private final RedissonClient redissonClient;

	/**
	 * 添加
	 */
	@Override
	public Boolean addSku(SkuDto skuDto) {
		Sku sku = new Sku();
		BeanUtils.copyProperties(skuDto, sku);
		skuMapper.insert(sku);
		return Boolean.TRUE;
	}

	/**
	 * 修改
	 */
	@Override
	public Boolean updateSku(SkuDto skuDto) {
		Sku sku = new Sku();
		BeanUtils.copyProperties(skuDto, sku);
		skuMapper.updateById(sku);
		return Boolean.TRUE;
	}

	/**
	 * 分页
	 */
	@Override
	public IPage<Sku> pageSku(Page<Sku> pageParam, Sku sku) {
		return skuMapper.selectPage(pageParam, Wrappers.emptyWrapper());
	}

	/**
	 * 获取单条
	 */
	@Override
	public Sku findById(Long id) {
		return skuMapper.selectById(id);
	}

	/**
	 * 获取全部
	 */
	@Override
	public List<Sku> findAll() {
		return skuMapper.selectList(Wrappers.emptyWrapper());
	}

	/**
	 * 删除
	 */
	@Override
	public Boolean deleteSku(Long id) {
		skuMapper.deleteById(id);
		return Boolean.TRUE;
	}

	/**
	 * 获取商品库存信息
	 */
	@Override
	public SkuInfoDTO getSkuInfo(Long skuId) {
		return skuMapper.getSkuInfo(skuId);
	}

	/**
	 * 商品验价
	 * @param checkPriceDTO 校验价格dto
	 * @return 价格是否相同
	 */
	@Override
	public Boolean checkPrice(CheckPriceDTO checkPriceDTO) {
		// 订单总金额
		Long orderTotalAmount = checkPriceDTO.getOrderTotalAmount();

		// 订单项
		List<CheckPriceDTO.CheckSku> checkOrderItems = checkPriceDTO.getCheckSkus();

		if (CollectionUtil.isNotEmpty(checkOrderItems)) {
			List<Long> skuIds = checkOrderItems.stream().map(CheckPriceDTO.CheckSku::getSkuId)
					.collect(Collectors.toList());
			List<Sku> skuList = this
					.list(new LambdaQueryWrapper<Sku>().in(Sku::getId, skuIds).select(Sku::getId, Sku::getPrice));

			// 商品总金额
			Long skuTotalAmount = checkOrderItems.stream().map(checkOrderItem -> {
				Long skuId = checkOrderItem.getSkuId();
				Sku sku = skuList.stream().filter(item -> item.getId().equals(skuId)).findFirst().orElse(null);

				Long price = Optional.ofNullable(sku).orElse(new Sku().setPrice(0L)).getPrice();
				return checkOrderItem.getCount() * price;
			}).reduce(0L, Long::sum);

			// 比较价格是否相同
			return Objects.equals(orderTotalAmount.compareTo(skuTotalAmount), 0);
		}

		return Boolean.FALSE;
	}

	/**
	 * 锁定库存
	 * @return 锁定是否成功
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public Boolean lockStock(LockStockDTO lockStockDTO) {
		log.info("锁定商品库存:{}", JSONUtil.toJsonStr(lockStockDTO));

		List<LockStockDTO.LockedSku> lockedSkuList = lockStockDTO.getLockedSkuList();
		Assert.isTrue(CollectionUtil.isNotEmpty(lockedSkuList), "锁定的商品为空");

		// 循环遍历锁定商品
		lockedSkuList.forEach(lockedSku -> {
			RLock lock = redissonClient.getLock(ProductConstant.LOCK_SKU_PREFIX + lockedSku.getSkuId()); // 获取分布式锁
			// 加锁
			lock.lock();
			try {
				boolean lockResult = this.update(new LambdaUpdateWrapper<Sku>()
						.setSql("locked_stock_num = locked_stock_num + " + lockedSku.getCount())
						.eq(Sku::getId, lockedSku.getSkuId())
						.apply("stock_num - locked_stock_num >= {0}", lockedSku.getCount()));
				Assert.isTrue(lockResult, "锁定商品 {} 失败", lockedSku.getSkuId());
			}
			finally {
				// 释放锁
				lock.unlock();
			}
		});

		// 将锁定的商品ID和对应购买数量持久化至Redis，后续使用场景: 1.订单取消归还库存;2.订单支付成功扣减库存。
		String orderToken = lockStockDTO.getOrderToken();
		redisTemplate.opsForValue().set(ProductConstant.LOCKED_STOCK_PREFIX + orderToken,
				JSONUtil.toJsonStr(lockedSkuList));

		// 无异常直接返回true
		return Boolean.TRUE;
	}

}