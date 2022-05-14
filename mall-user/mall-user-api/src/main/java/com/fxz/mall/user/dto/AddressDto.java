package com.fxz.mall.user.dto;

import com.fxz.common.core.annotation.CheckCityValid;
import com.fxz.common.core.enums.CityTypeEnum;
import com.fxz.common.mp.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author fxz
 * @date 2022-05-14
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class AddressDto extends BaseEntity {

	private static final long serialVersionUID = -1L;

	private Long id;

	private Long memberId;

	private String consigneeName;

	private String consigneeMobile;

	@CheckCityValid(value = CityTypeEnum.PROVINCE)
	private String province;

	@CheckCityValid(value = CityTypeEnum.CITY)
	private String city;

	@CheckCityValid(value = CityTypeEnum.AREA)
	private String area;

	private String detailAddress;

	private String zipCode;

	private Integer defaulted;

}