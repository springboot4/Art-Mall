package com.fxz.mall.promotion.dto;

import com.fxz.mall.promotion.entity.Seckill;
import com.fxz.mall.promotion.entity.SeckillApply;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022/8/12 11:14
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class SeckillDTO extends Seckill implements Serializable {

	private static final long serialVersionUID = -1L;

	private List<SeckillApply> seckillApplyList;

}
