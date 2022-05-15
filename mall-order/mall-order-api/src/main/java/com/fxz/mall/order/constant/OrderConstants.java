package com.fxz.mall.order.constant;

/**
 * 订单服务常量
 *
 * @author fxz
 */
public interface OrderConstants {

	String CART_PREFIX = "cart:";

	String ORDER_TOKEN_PREFIX = "order:token:";

	String ORDER_SN_PREFIX = "order:sn:";

	/**
	 * 释放锁lua脚本
	 */
	String RELEASE_LOCK_LUA_SCRIPT = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

}
