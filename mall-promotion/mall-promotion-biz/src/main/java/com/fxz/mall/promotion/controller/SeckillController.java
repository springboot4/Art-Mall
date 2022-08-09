package com.fxz.mall.promotion.controller;

import com.fxz.mall.promotion.service.SeckillService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fxz
 * @date 2022-08-09
 */
@RestController
@RequestMapping("/seckill")
@RequiredArgsConstructor
public class SeckillController {

	private final SeckillService seckillService;

}