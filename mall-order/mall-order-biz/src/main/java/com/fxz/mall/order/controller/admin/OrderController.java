package com.fxz.mall.order.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxz.common.mp.result.PageResult;
import com.fxz.common.mp.result.Result;
import com.fxz.mall.order.query.OrderPageQuery;
import com.fxz.mall.order.service.impl.OrderServiceImpl;
import com.fxz.mall.order.vo.OrderPageVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022/5/14 17:44
 */
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

	private final OrderServiceImpl orderService;

	/**
	 * 分页查询
	 * @param queryParams 分页参数
	 */
	@GetMapping("/page")
	public Result<PageResult<OrderPageVO>> listOrderPages(Page<OrderPageVO> page, OrderPageQuery queryParams) {
		return Result.success(PageResult.success(orderService.listOrderPages(page, queryParams)));
	}

}
