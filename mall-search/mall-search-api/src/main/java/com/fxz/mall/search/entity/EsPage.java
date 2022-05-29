package com.fxz.mall.search.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022/5/29 10:31
 */
@Data
public class EsPage<T> implements Serializable {

	private static final Long serialVersionUID = -1L;

	private List<T> records;

	private Long total;

	private Long current;

	private Long size;

}
