

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for coupon
-- ----------------------------
DROP TABLE IF EXISTS `coupon`;
CREATE TABLE `coupon` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `promotion_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '活动名称',
  `start_time` datetime(6) DEFAULT NULL COMMENT '活动开始时间',
  `end_time` datetime(6) DEFAULT NULL COMMENT '活动结束时间',
  `coupon_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '优惠券名称',
  `get_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '优惠券类型',
  `consume_threshold` double(20,2) DEFAULT NULL COMMENT '消费门槛',
  `coupon_discount` double(20,2) DEFAULT NULL COMMENT '折扣',
  `coupon_limit_num` int(11) DEFAULT NULL COMMENT '领取限制',
  `coupon_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '活动类型',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '活动描述',
  `price` double(20,2) DEFAULT NULL COMMENT '面额',
  `publish_num` int(11) DEFAULT NULL COMMENT '发行数量',
  `received_num` int(11) DEFAULT NULL COMMENT '已被领取的数量',
  `scope_id` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '范围关联的ID',
  `scope_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT 'PORTION_GOODS' COMMENT '关联范围类型',
  `used_num` int(11) DEFAULT NULL COMMENT '已被使用的数量',
  `range_day_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '时间范围类型(固定时间、动态时间)',
  `effective_days` int(11) DEFAULT NULL COMMENT '有效期',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(6) DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(6) DEFAULT NULL COMMENT '更新时间',
  `delete_flag` tinyint(4) DEFAULT '0' COMMENT '删除标志 ',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='优惠券\n';

-- ----------------------------
-- Records of coupon
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for coupon_activity
-- ----------------------------
DROP TABLE IF EXISTS `coupon_activity`;
CREATE TABLE `coupon_activity` (
  `id` bigint(20) NOT NULL COMMENT 'id主键',
  `promotion_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '活动名称',
  `start_time` datetime(6) DEFAULT NULL COMMENT '活动开始时间',
  `end_time` datetime(6) DEFAULT NULL COMMENT '活动结束时间',
  `scope_id` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '范围关联的ID',
  `scope_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT 'PORTION_GOODS' COMMENT '关联范围类型',
  `activity_scope` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '活动范围',
  `activity_scope_info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '活动范围详情',
  `coupon_activity_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '优惠券活动类型',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(6) DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(6) DEFAULT NULL COMMENT '更新时间',
  `delete_flag` tinyint(4) DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='优惠券活动';

-- ----------------------------
-- Records of coupon_activity
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for coupon_activity_item
-- ----------------------------
DROP TABLE IF EXISTS `coupon_activity_item`;
CREATE TABLE `coupon_activity_item` (
  `id` bigint(20) NOT NULL COMMENT 'id主键',
  `activity_id` bigint(20) DEFAULT NULL COMMENT '优惠券活动id',
  `coupon_id` bigint(20) DEFAULT NULL COMMENT '优惠券id',
  `num` int(11) DEFAULT NULL COMMENT '优惠券数量',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(6) DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(6) DEFAULT NULL COMMENT '更新时间',
  `delete_flag` tinyint(4) DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='优惠券活动项';

-- ----------------------------
-- Records of coupon_activity_item
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for member_coupon
-- ----------------------------
DROP TABLE IF EXISTS `member_coupon`;
CREATE TABLE `member_coupon` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `consume_threshold` double(20,2) DEFAULT NULL COMMENT '消费门槛',
  `consumption_time` datetime(6) DEFAULT NULL COMMENT '核销时间',
  `coupon_id` bigint(20) DEFAULT NULL COMMENT '优惠券ID',
  `coupon_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '活动类型',
  `discount` double DEFAULT NULL COMMENT '折扣',
  `end_time` datetime(6) DEFAULT NULL COMMENT '使用截止时间',
  `get_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '优惠券类型',
  `member_coupon_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '会员优惠券状态',
  `member_id` bigint(20) DEFAULT NULL COMMENT '会员ID',
  `member_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '会员名称',
  `price` double(20,2) DEFAULT NULL COMMENT '面额',
  `scope_id` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '范围关联的ID',
  `scope_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT 'PORTION_GOODS' COMMENT '关联范围类型',
  `start_time` datetime(6) DEFAULT NULL COMMENT '使用起始时间',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(6) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(6) DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新者',
  `delete_flag` tinyint(4) DEFAULT '0' COMMENT '删除标志 ',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `member-id` (`member_id`) USING BTREE COMMENT 'member_id检索优惠券索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='会员优惠券';

-- ----------------------------
-- Records of member_coupon
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for promotion_goods
-- ----------------------------
DROP TABLE IF EXISTS `promotion_goods`;
CREATE TABLE `promotion_goods` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `goods_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '货品名称',
  `limit_num` int(11) DEFAULT NULL COMMENT '限购数量',
  `num` int(11) DEFAULT NULL COMMENT '卖出的商品数量',
  `original_price` bigint(20) DEFAULT NULL COMMENT '原价',
  `price` bigint(20) DEFAULT NULL COMMENT '促销价格',
  `promotion_id` bigint(20) DEFAULT NULL COMMENT '活动ID',
  `quantity` int(11) DEFAULT NULL COMMENT '促销库存',
  `promotion_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '促销工具类型',
  `goods_id` bigint(20) DEFAULT NULL COMMENT '商品ID',
  `sku_id` bigint(20) DEFAULT NULL COMMENT '货品SkuID',
  `start_time` datetime DEFAULT NULL COMMENT '活动开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '活动结束时间',
  `thumbnail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '缩略图',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '活动标题',
  `category_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '分类路径',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `delete_flag` tinyint(1) DEFAULT '0' COMMENT '删除标志 ',
  `scope_id` mediumtext COLLATE utf8mb4_bin COMMENT '关联的范围id',
  `scope_type` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '关联范围类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of promotion_goods
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for seckill
-- ----------------------------
DROP TABLE IF EXISTS `seckill`;
CREATE TABLE `seckill` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `promotion_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '活动名称',
  `start_time` datetime DEFAULT NULL COMMENT '活动开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '活动结束时间',
  `apply_end_time` datetime NOT NULL COMMENT '报名截至时间',
  `hours` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '开启几点场',
  `seckill_rule` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '申请规则',
  `goods_num` int(11) DEFAULT NULL COMMENT '参与的商品数量',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `delete_flag` tinyint(1) DEFAULT '0' COMMENT '删除标志 ',
  `scope_id` mediumtext COLLATE utf8mb4_bin COMMENT '范围关联的ID',
  `scope_type` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '关联范围类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='秒杀';

-- ----------------------------
-- Records of seckill
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for seckill_apply
-- ----------------------------
DROP TABLE IF EXISTS `seckill_apply`;
CREATE TABLE `seckill_apply` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `goods_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '商品名称',
  `original_price` bigint(20) DEFAULT NULL COMMENT '商品原始价格',
  `price` bigint(20) NOT NULL COMMENT '价格',
  `promotion_apply_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '促销活动申请状态',
  `quantity` int(11) NOT NULL COMMENT '促销数量',
  `sales_num` int(11) DEFAULT NULL COMMENT '已售数量',
  `seckill_id` bigint(20) NOT NULL COMMENT '活动ID',
  `sku_id` bigint(20) NOT NULL COMMENT '货品ID',
  `time_line` int(11) NOT NULL COMMENT '时刻',
  `fail_reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '驳回原因',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `delete_flag` tinyint(1) DEFAULT '0' COMMENT '删除标志 ',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of seckill_apply
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for setting
-- ----------------------------
DROP TABLE IF EXISTS `setting`;
CREATE TABLE `setting` (
  `id` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `setting_value` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '配置值value',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `delete_flag` tinyint(4) DEFAULT NULL COMMENT '删除标志',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of setting
-- ----------------------------
BEGIN;
INSERT INTO `setting` (`id`, `setting_value`, `create_by`, `create_time`, `update_by`, `update_time`, `delete_flag`) VALUES ('seckill_setting', '{\"seckillRule\":\"秒杀规则fxz\",\"hours\":\"0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23\"}', NULL, NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log` (
  `branch_id` bigint(20) NOT NULL COMMENT 'branch transaction id',
  `xid` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'global transaction id',
  `context` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'undo_log context,such as serialization',
  `rollback_info` longblob NOT NULL COMMENT 'rollback info',
  `log_status` int(11) NOT NULL COMMENT '0:normal status,1:defense status',
  `log_created` datetime(6) NOT NULL COMMENT 'create datetime',
  `log_modified` datetime(6) NOT NULL COMMENT 'modify datetime',
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='AT transaction mode undo table';

-- ----------------------------
-- Records of undo_log
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
