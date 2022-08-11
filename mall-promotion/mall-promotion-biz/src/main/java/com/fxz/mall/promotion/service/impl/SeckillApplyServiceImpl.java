package com.fxz.mall.promotion.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxz.common.core.exception.FxzException;
import com.fxz.mall.product.dto.SkuInfoDTO;
import com.fxz.mall.product.feign.RemoteSkuService;
import com.fxz.mall.promotion.entity.PromotionGoods;
import com.fxz.mall.promotion.entity.Seckill;
import com.fxz.mall.promotion.entity.SeckillApply;
import com.fxz.mall.promotion.enums.PromotionTypeEnum;
import com.fxz.mall.promotion.enums.PromotionsApplyStatusEnum;
import com.fxz.mall.promotion.mapper.SeckillApplyMapper;
import com.fxz.mall.promotion.service.PromotionGoodsService;
import com.fxz.mall.promotion.service.SeckillApplyService;
import com.fxz.mall.promotion.service.SeckillService;
import com.fxz.mall.promotion.vo.SeckillApplyVO;
import com.fxz.mall.promotion.vo.SeckillGoodsVO;
import com.fxz.mall.promotion.vo.SeckillTimelineVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author fxz
 * @date 2022-08-09
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SeckillApplyServiceImpl extends ServiceImpl<SeckillApplyMapper, SeckillApply>
		implements SeckillApplyService {

	@Autowired
	private SeckillService seckillService;

	private final RemoteSkuService remoteSkuService;

	private final PromotionGoodsService promotionGoodsService;

	/**
	 * 秒杀活动信息更新，更新相关的秒杀请求以及促销商品信息
	 * @param seckill 秒杀活动信息
	 * @return true or false
	 */
	@Override
	public boolean updateSeckillApplyTime(Seckill seckill) {
		// 查出当前秒杀活动下的商家请求
		List<SeckillApply> seckillApplies = this
				.list(Wrappers.<SeckillApply>lambdaQuery().eq(SeckillApply::getSeckillId, seckill.getId()));
		// 筛选出查询当前秒杀活动修改后在时间段内的商家请求
		List<SeckillApply> applies = seckillApplies.stream()
				.filter(seckillApply -> Objects.nonNull(seckillApply.getTimeLine())
						&& seckill.getHours().contains(seckillApply.getTimeLine().toString()))
				.collect(Collectors.toList());

		// 为每个商家请求构建促销商品
		List<PromotionGoods> promotionGoodsList = applies.stream().map(applie -> {
			// 获取参与活动的sku信息
			SkuInfoDTO skuInfo = remoteSkuService.getSkuInfo(applie.getSkuId()).getData();
			// 构建促销商品
			return this.createSeckillGoods(skuInfo, applie, seckill);
		}).collect(Collectors.toList());

		// 删掉之前的所有促销活动
		promotionGoodsService
				.remove(Wrappers.<PromotionGoods>lambdaQuery().eq(PromotionGoods::getPromotionId, seckill.getId())
						.eq(PromotionGoods::getPromotionType, PromotionTypeEnum.SECKILL.getValue()));
		// 保存本次构建的促销活动
		promotionGoodsService.saveBatch(promotionGoodsList);

		List<Long> collect = promotionGoodsList.stream().map(PromotionGoods::getSkuId).collect(Collectors.toList());
		// 删掉修改后不在时间段下的商家请求
		return this.remove(Wrappers.<SeckillApply>lambdaQuery().eq(SeckillApply::getSeckillId, seckill.getId())
				.notIn(CollectionUtils.isNotEmpty(collect), SeckillApply::getSkuId, collect));
	}

	/**
	 * 删除秒杀活动申请
	 */
	@Override
	public void removeSeckillApply(Long seckillId, Long id) {
		SeckillApply apply = this.getById(id);
		if (Objects.isNull(apply)) {
			throw new FxzException("当前参与的秒杀活动不存在");
		}
		Seckill seckill = seckillService.getById(seckillId);
		if (Objects.isNull(seckill)) {
			throw new FxzException("当前秒杀活动不存在");
		}

		// 清除秒杀活动中的商品
		this.remove(Wrappers.<SeckillApply>lambdaQuery().eq(SeckillApply::getSeckillId, seckillId)
				.eq(SeckillApply::getId, id));

		// 删除促销商品
		promotionGoodsService.remove(Wrappers.<PromotionGoods>lambdaQuery()
				.eq(PromotionGoods::getPromotionId, seckillId).eq(PromotionGoods::getSkuId, apply.getSkuId()));

		// 更新当前秒杀活动商品数量
		seckillService.countSeckillGoodsNum(seckillId);
	}

	/**
	 * 添加秒杀活动申请
	 * @param seckillId 秒杀活动id
	 * @param applyVos 商家秒杀请求视图对象
	 */
	@Override
	public void addSeckillApply(Long seckillId, List<SeckillApplyVO> applyVos) {
		// 获取当前秒杀活动
		Seckill seckill = seckillService.getById(seckillId);
		if (Objects.isNull(seckill)) {
			throw new FxzException("秒杀活动不存在");
		}

		// 活动请求是否为空
		if (CollectionUtils.isEmpty(applyVos)) {
			return;
		}

		// 合法性检查（检查促销价格是否大于原价、检查当前时间是否在秒杀活动时间内、检查一个商品是否参与了此秒杀的多个时间段）
		checkSeckillApplyList(seckill.getHours(), applyVos);

		List<SeckillApply> applyList = new ArrayList<>();
		List<PromotionGoods> promotionGoodsList = applyVos.stream().map(applyVo -> {
			// 获取sku信息
			SkuInfoDTO skuInfoDTO = remoteSkuService.getSkuInfo(applyVo.getSkuId()).getData();
			if (Objects.isNull(skuInfoDTO)) {
				return null;
			}

			// 检测是否可以发布促销商品
			checkSeckillGoodsSku(seckill, applyVo, skuInfoDTO);

			// 为秒杀请求设置默认值
			applyVo.setSeckillId(seckillId);
			applyVo.setOriginalPrice(skuInfoDTO.getPrice());
			applyVo.setSalesNum(0);
			applyVo.setPromotionApplyStatus(PromotionsApplyStatusEnum.PASS.getValue());
			applyList.add(applyVo);

			// 构建促销商品（根据秒杀活动、添加秒杀商品请求、商品sku信息）
			return createSeckillGoods(skuInfoDTO, applyVo, seckill);
		}).collect(Collectors.toList());

		log.info("集合：{}", promotionGoodsList);
		// 为了保证一个sku只能参加此秒杀的的一个时间段，删掉当前sku在此秒杀下的请求
		this.remove(Wrappers.<SeckillApply>lambdaQuery().eq(SeckillApply::getSeckillId, seckillId).in(
				SeckillApply::getSkuId, applyVos.stream().map(SeckillApply::getSkuId).collect(Collectors.toList())));

		// 保存秒杀活动请求的信息
		this.saveBatch(applyList);

		// 保存促销商品信息 (删掉商品在此秒杀下的促销商品)
		if (CollectionUtils.isNotEmpty(promotionGoodsList)) {
			promotionGoodsService.remove(Wrappers.<PromotionGoods>lambdaQuery()
					.eq(PromotionGoods::getPromotionId, seckill.getId())
					.eq(PromotionGoods::getPromotionType, PromotionTypeEnum.SECKILL.getValue())
					.in(PromotionGoods::getGoodsId,
							promotionGoodsList.stream().map(PromotionGoods::getSkuId).collect(Collectors.toList())));
			promotionGoodsService.saveBatch(promotionGoodsList);
		}

		// 更新当前秒杀活动的参与商品数量
		seckillService.countSeckillGoodsNum(seckillId);
	}

	/**
	 * 获取当天秒杀活动信息(时刻及对应时刻下的商品)
	 */
	@Override
	public List<SeckillTimelineVO> listSeckillTime() {
		LocalDateTime now = LocalDateTime.now();
		List<SeckillTimelineVO> timelineList = new ArrayList<>();

		// 1.查询出当天的所有秒杀活动。
		LambdaQueryWrapper<Seckill> lambdaQuery = Wrappers.lambdaQuery();
		lambdaQuery.between(Seckill::getStartTime, LocalDateTime.of(now.toLocalDate(), LocalTime.MIN),
				LocalDateTime.of(now.toLocalDate(), LocalTime.MAX));
		lambdaQuery.le(Seckill::getEndTime, LocalDateTime.of(now.toLocalDate(), LocalTime.MAX));
		List<Seckill> seckillList = seckillService.list(lambdaQuery);

		// 2.遍历每一个秒杀活动，查询该秒杀活动的每一个时间点的秒杀商品信息。
		seckillList.forEach(seckill -> {
			// 获取当前秒杀活动的每个时刻
			int[] split = Arrays.stream(seckill.getHours().split(StringPool.COMMA)).mapToInt(Integer::parseInt)
					.toArray();
			Arrays.sort(split);

			// 遍历此秒杀活动的每个时刻
			for (int s : split) {
				// 每个未开始的秒杀活动
				if (s >= now.getHour()) {
					// 封装秒杀时刻视图对象
					SeckillTimelineVO timelineVO = new SeckillTimelineVO();
					timelineVO.setStartTime(
							LocalDateTime.of(now.toLocalDate(), LocalTime.of(s, 0)).toEpochSecond(ZoneOffset.of("+8")));
					timelineVO.setTimeLine(s);
					timelineVO.setDistanceStartTime(
							LocalDateTime.of(now.toLocalDate(), LocalTime.of(s, 0)).toEpochSecond(ZoneOffset.of("+8"))
									- DateUtil.currentSeconds());
					timelineVO.setSeckillGoodsList(wrapperSeckillGoods(s, seckill.getId()));
					timelineList.add(timelineVO);
				}
			}
		});

		return timelineList;
	}

	/**
	 * 组装当时间秒杀活动的商品数据
	 * @param startTimeline 秒杀活动开始时刻
	 * @param seckillId 秒杀活动id
	 * @return 当时间秒杀活动的商品数据
	 */
	private List<SeckillGoodsVO> wrapperSeckillGoods(int startTimeline, Long seckillId) {
		List<SeckillGoodsVO> res = new ArrayList<>();
		List<SeckillApply> seckillApplyList = this
				.list(Wrappers.<SeckillApply>lambdaQuery().eq(SeckillApply::getSeckillId, seckillId));
		if (CollectionUtils.isNotEmpty(seckillApplyList)) {
			List<SeckillApply> seckillApplies = seckillApplyList.stream()
					.filter(seckillApply -> seckillApply.getTimeLine().equals(startTimeline)
							&& seckillApply.getPromotionApplyStatus().equals(PromotionsApplyStatusEnum.PASS.getValue()))
					.collect(Collectors.toList());
			for (SeckillApply apply : seckillApplies) {
				SkuInfoDTO skuInfoDTO = remoteSkuService.getSkuInfo(apply.getSkuId()).getData();
				if (!Objects.isNull(skuInfoDTO)) {
					SeckillGoodsVO seckillGoodsVO = new SeckillGoodsVO(seckillId, startTimeline, skuInfoDTO.getSpuId(),
							skuInfoDTO.getSkuId(), skuInfoDTO.getSkuName(), skuInfoDTO.getPicUrl(), apply.getPrice(),
							apply.getQuantity(), apply.getSalesNum(), apply.getOriginalPrice());
					res.add(seckillGoodsVO);
				}
			}
		}
		return res;
	}

	/**
	 * 检测是否可以发布促销商品
	 * @param seckill 秒杀活动信息
	 * @param applyVo 秒杀请求
	 * @param skuInfoDTO sku信息
	 */
	private void checkSeckillGoodsSku(Seckill seckill, SeckillApplyVO applyVo, SkuInfoDTO skuInfoDTO) {
		// 检查库存属否充足
		if (skuInfoDTO.getStockNum() < applyVo.getQuantity()) {
			throw new FxzException(skuInfoDTO.getSkuName() + "库存不足");
		}

		// 秒杀商品开始时间
		LocalDateTime startTime = LocalDateTime.of(seckill.getStartTime().toLocalDate(),
				LocalTime.of(applyVo.getTimeLine(), 0));

		// 查询是否在同一时间段参与了秒杀活动活动
		if (promotionGoodsService.findInnerOverlapPromotionGoods(PromotionTypeEnum.SECKILL.name(),
				skuInfoDTO.getSkuId(), startTime, seckill.getEndTime(), seckill.getId()) > 0) {
			throw new FxzException("商品[" + skuInfoDTO.getSkuName() + "]已经在重叠的时间段参加了秒杀活动，不能参加秒杀活动活动");
		}
	}

	/**
	 * 合法性检查（检查促销价格是否大于原价、检查当前时间是否在秒杀活动时间内、检查一个商品是否参与了此秒杀的多个时间段）
	 * @param hours 秒杀活动开启的时间段
	 * @param applyVos 秒杀请求
	 */
	private void checkSeckillApplyList(String hours, List<SeckillApplyVO> applyVos) {
		List<Long> exist = new ArrayList<>();
		for (SeckillApplyVO applyVo : applyVos) {
			// 检查促销价格是否大于原价
			if (applyVo.getPrice() > applyVo.getOriginalPrice()) {
				throw new FxzException("活动价格不能大于原始价格");
			}

			// 检查当前时间是否在秒杀活动时间内
			Integer timeLine = applyVo.getTimeLine();
			String[] split = hours.split(StringPool.COMMA);
			boolean match = Arrays.stream(split).anyMatch(time -> time.equals(timeLine.toString()));
			if (!match) {
				throw new FxzException("当前活动请求不在活动时间范围内");
			}

			// 检查一个商品是否参与了此秒杀的多个时间段
			if (exist.contains(applyVo.getSkuId())) {
				throw new FxzException("当前商品重复参加秒杀活动");
			}
			else {
				exist.add(applyVo.getSkuId());
			}
		}
	}

	/**
	 * 构建促销商品
	 * @param skuInfo sku信息
	 * @param applie 商家请求
	 * @param seckill 秒杀信息
	 * @return 促销商品信息
	 */
	private PromotionGoods createSeckillGoods(SkuInfoDTO skuInfo, SeckillApply applie, Seckill seckill) {
		// 促销商品默认信息
		PromotionGoods promotionGoods = new PromotionGoods(skuInfo);
		promotionGoods.setPromotionId(seckill.getId());
		promotionGoods.setLimitNum(applie.getQuantity());
		promotionGoods.setNum(0);
		promotionGoods.setTitle(seckill.getPromotionName());
		promotionGoods.setPrice(applie.getPrice());
		promotionGoods.setQuantity(applie.getQuantity());
		promotionGoods.setPromotionType(PromotionTypeEnum.SECKILL.getValue());
		promotionGoods.setTitle(seckill.getPromotionName());
		promotionGoods.setStartTime(seckill.getStartTime().withHour(applie.getTimeLine()));
		promotionGoods.setEndTime(seckill.getEndTime());
		return promotionGoods;
	}

}