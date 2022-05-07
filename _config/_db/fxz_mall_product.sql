
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for attribute
-- ----------------------------
DROP TABLE IF EXISTS `attribute`;
CREATE TABLE `attribute` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `category_id` bigint(20) NOT NULL COMMENT '分类ID',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '属性名称',
  `type` tinyint(4) NOT NULL COMMENT '类型(1:规格;2:属性;)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_pms_attr_pms_category` (`category_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1522951246206267395 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='商品属性表';

-- ----------------------------
-- Records of attribute
-- ----------------------------
BEGIN;
INSERT INTO `attribute` (`id`, `category_id`, `name`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (34, 5, '颜色', 1, '2021-07-11 17:57:06', '2022-03-05 13:16:30', NULL, NULL);
INSERT INTO `attribute` (`id`, `category_id`, `name`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (35, 5, '规格', 1, '2021-07-11 18:00:06', '2022-03-05 13:16:30', NULL, NULL);
INSERT INTO `attribute` (`id`, `category_id`, `name`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (36, 5, '上市时间', 2, '2021-07-11 18:00:08', '2022-03-05 13:16:31', NULL, NULL);
INSERT INTO `attribute` (`id`, `category_id`, `name`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (47, 8, '123', 1, '2022-03-04 13:00:43', '2022-05-06 23:47:13', NULL, 'fxz');
INSERT INTO `attribute` (`id`, `category_id`, `name`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522227526558879746, 3, '网络', 2, '2022-05-05 22:51:39', '2022-05-05 22:56:41', 'fxz', 'fxz');
INSERT INTO `attribute` (`id`, `category_id`, `name`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522228459514695682, 0, '', 1, '2022-05-05 22:55:21', '2022-05-05 22:55:21', 'fxz', 'fxz');
INSERT INTO `attribute` (`id`, `category_id`, `name`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522228692801884161, 3, '电池', 2, '2022-05-05 22:56:17', '2022-05-05 22:56:41', 'fxz', 'fxz');
INSERT INTO `attribute` (`id`, `category_id`, `name`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522228724506628098, 3, '颜色', 1, '2022-05-05 22:56:24', '2022-05-07 16:00:19', 'fxz', 'fxz');
INSERT INTO `attribute` (`id`, `category_id`, `name`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522228796099203073, 3, '重量', 2, '2022-05-05 22:56:41', '2022-05-05 22:56:41', 'fxz', 'fxz');
INSERT INTO `attribute` (`id`, `category_id`, `name`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522228947647795201, 0, '', 2, '2022-05-05 22:57:17', '2022-05-05 22:57:17', 'fxz', 'fxz');
INSERT INTO `attribute` (`id`, `category_id`, `name`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522848786506637313, 3, '内存', 1, '2022-05-07 16:00:19', '2022-05-07 16:00:19', 'fxz', 'fxz');
INSERT INTO `attribute` (`id`, `category_id`, `name`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522950701194211329, 1522189691277635586, '颜色', 1, '2022-05-07 22:45:17', '2022-05-07 22:45:17', 'fxz', 'fxz');
INSERT INTO `attribute` (`id`, `category_id`, `name`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522950701231960066, 1522189691277635586, '内存', 1, '2022-05-07 22:45:17', '2022-05-07 22:45:17', 'fxz', 'fxz');
INSERT INTO `attribute` (`id`, `category_id`, `name`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522950705698893825, 1522189691277635586, '网络', 2, '2022-05-07 22:45:18', '2022-05-07 22:45:18', 'fxz', 'fxz');
INSERT INTO `attribute` (`id`, `category_id`, `name`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522950705707282433, 1522189691277635586, '重量', 2, '2022-05-07 22:45:18', '2022-05-07 22:45:18', 'fxz', 'fxz');
INSERT INTO `attribute` (`id`, `category_id`, `name`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522951238677491713, 1522191266574311425, '屏幕尺寸', 1, '2022-05-07 22:47:25', '2022-05-07 22:47:25', 'fxz', 'fxz');
INSERT INTO `attribute` (`id`, `category_id`, `name`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522951238694268929, 1522191266574311425, '内存', 1, '2022-05-07 22:47:25', '2022-05-07 22:47:25', 'fxz', 'fxz');
INSERT INTO `attribute` (`id`, `category_id`, `name`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522951238694268930, 1522191266574311425, '颜色', 1, '2022-05-07 22:47:25', '2022-05-07 22:47:25', 'fxz', 'fxz');
INSERT INTO `attribute` (`id`, `category_id`, `name`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522951246197878785, 1522191266574311425, '芯片', 2, '2022-05-07 22:47:27', '2022-05-07 22:47:27', 'fxz', 'fxz');
INSERT INTO `attribute` (`id`, `category_id`, `name`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522951246206267394, 1522191266574311425, '重量', 2, '2022-05-07 22:47:27', '2022-05-07 22:47:27', 'fxz', 'fxz');
COMMIT;

-- ----------------------------
-- Table structure for brand
-- ----------------------------
DROP TABLE IF EXISTS `brand`;
CREATE TABLE `brand` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '品牌名称',
  `logo_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'LOGO图片',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1522951736029671426 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='商品品牌表';

-- ----------------------------
-- Records of brand
-- ----------------------------
BEGIN;
INSERT INTO `brand` (`id`, `name`, `logo_url`, `sort`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1, '华为', '/system/file/fxzcloud/f8c8716d0dd64ec1a569e29014605dc2.png', 1, '2021-07-11 19:56:58', '2022-05-07 13:31:41', NULL, 'fxz');
INSERT INTO `brand` (`id`, `name`, `logo_url`, `sort`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (10, '小米', 'http://a.youlai.tech:9000/default/6c5433c84cc54120996a151c8e6d4cf3.jpg', 1, '2022-03-05 16:12:16', '2022-03-05 16:12:16', NULL, NULL);
INSERT INTO `brand` (`id`, `name`, `logo_url`, `sort`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522951736029671425, '苹果', '/system/file/fxzcloud/c483c098f4124eb4a27e900250f1e1d4.png', 0, '2022-05-07 22:49:24', '2022-05-07 22:51:29', 'fxz', 'fxz');
COMMIT;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品分类名称',
  `parent_id` bigint(20) NOT NULL COMMENT '父级ID',
  `level` int(11) DEFAULT NULL COMMENT '层级',
  `icon_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '图标地址',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `visible` tinyint(1) DEFAULT '1' COMMENT '显示状态:( 0:隐藏 1:显示)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1522191266574311426 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='商品分类表';

-- ----------------------------
-- Records of category
-- ----------------------------
BEGIN;
INSERT INTO `category` (`id`, `name`, `parent_id`, `level`, `icon_url`, `sort`, `visible`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (3, '手机配件', 0, 1, NULL, 1, 1, NULL, NULL, NULL, NULL);
INSERT INTO `category` (`id`, `name`, `parent_id`, `level`, `icon_url`, `sort`, `visible`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (4, '智能手机', 3, 2, NULL, 1, 1, NULL, NULL, NULL, NULL);
INSERT INTO `category` (`id`, `name`, `parent_id`, `level`, `icon_url`, `sort`, `visible`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (6, '电脑', 0, 1, 'http://a.youlai.tech:9000/default/776c21c1a71848069093033f461c5f4a.jpg', 1, 1, '2022-02-25 11:22:44', '2022-02-25 11:22:44', NULL, NULL);
INSERT INTO `category` (`id`, `name`, `parent_id`, `level`, `icon_url`, `sort`, `visible`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (7, '游戏本', 6, 2, 'http://a.youlai.tech:9000/default/f41d764d7ce64054b75fe9be5fb3f700.jpg', 1, 1, '2022-02-25 11:23:06', '2022-02-25 11:23:06', NULL, NULL);
INSERT INTO `category` (`id`, `name`, `parent_id`, `level`, `icon_url`, `sort`, `visible`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (8, '轻薄本', 6, 2, 'http://a.youlai.tech:9000/default/840ddc78c93d422b9929821c97f3dfbe.jpg', 2, 1, '2022-02-25 11:23:24', '2022-02-25 11:23:24', NULL, NULL);
INSERT INTO `category` (`id`, `name`, `parent_id`, `level`, `icon_url`, `sort`, `visible`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522189691277635586, '5G手机', 4, 3, '/system/file/fxzcloud/2917ae8f5c4a446ea83b7ce8c947faf3.png', 0, 1, '2022-05-05 20:21:18', '2022-05-07 22:43:54', 'fxz', 'fxz');
INSERT INTO `category` (`id`, `name`, `parent_id`, `level`, `icon_url`, `sort`, `visible`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522191209464668161, '苹果笔记本', 6, 2, '/system/file/fxzcloud/0c1aacddbff24e48bcde61ae4b0ccc7a.jpeg', 3, 1, '2022-05-05 20:27:20', '2022-05-07 22:45:52', 'fxz', 'fxz');
INSERT INTO `category` (`id`, `name`, `parent_id`, `level`, `icon_url`, `sort`, `visible`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522191266574311425, 'macbook pro', 1522191209464668161, 3, '/system/file/fxzcloud/47657feb046845c297104c77ce421df0.jpeg', 1, 1, '2022-05-05 20:27:34', '2022-05-07 22:46:13', 'fxz', 'fxz');
COMMIT;

-- ----------------------------
-- Table structure for sku
-- ----------------------------
DROP TABLE IF EXISTS `sku`;
CREATE TABLE `sku` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sku_sn` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '商品编码',
  `spu_id` bigint(20) NOT NULL COMMENT 'SPU ID',
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '商品名称',
  `price` bigint(20) DEFAULT NULL COMMENT '商品价格(单位：分)',
  `stock_num` int(11) DEFAULT '0' COMMENT '库存数量',
  `locked_stock_num` int(11) DEFAULT '0' COMMENT '锁定库存数量',
  `pic_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '商品图片地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_pms_sku_pms_spu` (`spu_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1522957970707398659 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='商品库存表';

-- ----------------------------
-- Records of sku
-- ----------------------------
BEGIN;
INSERT INTO `sku` (`id`, `sku_sn`, `spu_id`, `name`, `price`, `stock_num`, `locked_stock_num`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (291, 'sn001', 1, '黑 6+128g', 399900, 999, 0, 'http://a.youlai.tech:9000/default/6759b824e6d04af69f6f3e55190e7e79.png', '2021-08-08 00:43:26', '2022-03-05 15:01:53', NULL, NULL);
INSERT INTO `sku` (`id`, `sku_sn`, `spu_id`, `name`, `price`, `stock_num`, `locked_stock_num`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (292, 'sn002', 1, '黑 8+256g', 499900, 999, 0, 'http://a.youlai.tech:9000/default/6759b824e6d04af69f6f3e55190e7e79.png', '2021-08-08 00:43:26', '2022-03-05 15:01:53', NULL, NULL);
INSERT INTO `sku` (`id`, `sku_sn`, `spu_id`, `name`, `price`, `stock_num`, `locked_stock_num`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (353, 'sn003', 1, '蓝 6+128g', 399900, 999, 0, 'http://a.youlai.tech:9000/default/aed7966ff68640f08d110f4fbcd1cdc2.png', '2022-03-05 09:25:53', '2022-03-05 15:01:53', NULL, NULL);
INSERT INTO `sku` (`id`, `sku_sn`, `spu_id`, `name`, `price`, `stock_num`, `locked_stock_num`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (354, 'sn004', 1, '蓝 8+256g', 499900, 998, 0, 'http://a.youlai.tech:9000/default/aed7966ff68640f08d110f4fbcd1cdc2.png', '2022-03-05 09:25:53', '2022-03-05 15:01:53', NULL, NULL);
INSERT INTO `sku` (`id`, `sku_sn`, `spu_id`, `name`, `price`, `stock_num`, `locked_stock_num`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522934681943437314, 'huawei001', 1522934680165052417, '华为p40 8+128g', 5699, 100, 0, '无', '2022-05-07 21:41:38', '2022-05-07 21:41:38', 'fxz', 'fxz');
INSERT INTO `sku` (`id`, `sku_sn`, `spu_id`, `name`, `price`, `stock_num`, `locked_stock_num`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522935088551849985, 'huawei001', 1522935059942502401, '华为p40 8+128g', 5699, 100, 0, '无', '2022-05-07 21:43:15', '2022-05-07 21:43:15', 'fxz', 'fxz');
INSERT INTO `sku` (`id`, `sku_sn`, `spu_id`, `name`, `price`, `stock_num`, `locked_stock_num`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522935374221701122, 'huawei001', 1522935372858552321, '华为p40 8+128g', 5699, 100, 0, '无', '2022-05-07 21:44:23', '2022-05-07 21:44:23', 'fxz', 'fxz');
INSERT INTO `sku` (`id`, `sku_sn`, `spu_id`, `name`, `price`, `stock_num`, `locked_stock_num`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522935837625180161, 'huawei001', 1522935835783880706, '华为p40 8+128g', 5699, 100, 0, '无', '2022-05-07 21:46:13', '2022-05-07 21:46:13', 'fxz', 'fxz');
INSERT INTO `sku` (`id`, `sku_sn`, `spu_id`, `name`, `price`, `stock_num`, `locked_stock_num`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522942857774432257, 'huaweip40-001', 1522942856205762561, '华为p40 黑色 8+128G', 6699, 100, 0, '/system/file/fxzcloud/73109985f11248e5a5eb55ccddb61fed.png', '2022-05-07 22:14:07', '2022-05-07 22:14:07', 'fxz', 'fxz');
INSERT INTO `sku` (`id`, `sku_sn`, `spu_id`, `name`, `price`, `stock_num`, `locked_stock_num`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522947065386958849, 'xiaomi-002', 1522947064145444865, '小米', 188, 10, 0, '/system/file/fxzcloud/18603592738c4e82800368423caa1eb6.png', '2022-05-07 22:30:50', '2022-05-07 22:30:50', 'fxz', 'fxz');
INSERT INTO `sku` (`id`, `sku_sn`, `spu_id`, `name`, `price`, `stock_num`, `locked_stock_num`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522947066339065858, '11', 1522947064145444865, 'ssssssss', 111, 1, 0, NULL, '2022-05-07 22:30:50', '2022-05-07 22:30:50', 'fxz', 'fxz');
INSERT INTO `sku` (`id`, `sku_sn`, `spu_id`, `name`, `price`, `stock_num`, `locked_stock_num`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522948097793912833, '大大', 1522948097013772290, '阿斯顿撒', 116600, 10, 0, NULL, '2022-05-07 22:34:56', '2022-05-07 22:34:56', 'fxz', 'fxz');
INSERT INTO `sku` (`id`, `sku_sn`, `spu_id`, `name`, `price`, `stock_num`, `locked_stock_num`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522956749477068801, 'macbookpro-001', 1522956747757404162, 'macbookpro 8+128G 灰色', 1299988, 100, 0, '/system/file/fxzcloud/6800f7020c7a4aaf846db2651d609f0c.png', '2022-05-07 23:09:19', '2022-05-07 23:09:19', 'fxz', 'fxz');
INSERT INTO `sku` (`id`, `sku_sn`, `spu_id`, `name`, `price`, `stock_num`, `locked_stock_num`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522957394783322113, 'sss', 1522957393978015746, 'ss', 11199, 100, 0, NULL, '2022-05-07 23:11:53', '2022-05-07 23:11:53', 'fxz', 'fxz');
INSERT INTO `sku` (`id`, `sku_sn`, `spu_id`, `name`, `price`, `stock_num`, `locked_stock_num`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522957970707398658, 'aa', 1522957969516216322, 'aa', 1299988, 10, 0, NULL, '2022-05-07 23:14:10', '2022-05-07 23:14:10', 'fxz', 'fxz');
COMMIT;

-- ----------------------------
-- Table structure for sku_attribute_value
-- ----------------------------
DROP TABLE IF EXISTS `sku_attribute_value`;
CREATE TABLE `sku_attribute_value` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `spu_id` bigint(20) NOT NULL COMMENT 'spu ID',
  `sku_id` bigint(20) NOT NULL COMMENT 'sku ID',
  `attribute_id` bigint(20) DEFAULT NULL COMMENT '属性ID',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '属性名称',
  `value` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '属性值',
  `type` tinyint(4) NOT NULL COMMENT '类型(1:规格;2:属性;)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_pms_spu_attribute_pms_attr` (`name`) USING BTREE,
  KEY `fk_pms_spu_attribute_pms_spu` (`spu_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1522957971172966402 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='商品属性项表';

-- ----------------------------
-- Records of sku_attribute_value
-- ----------------------------
BEGIN;
INSERT INTO `sku_attribute_value` (`id`, `spu_id`, `sku_id`, `attribute_id`, `name`, `value`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1, 1, 0, 34, '颜色', '黑', 1, NULL, '2022-03-05 15:01:53', NULL, NULL);
INSERT INTO `sku_attribute_value` (`id`, `spu_id`, `sku_id`, `attribute_id`, `name`, `value`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (3, 1, 0, 35, '规格', '6+128g', 1, NULL, '2022-03-05 15:01:53', NULL, NULL);
INSERT INTO `sku_attribute_value` (`id`, `spu_id`, `sku_id`, `attribute_id`, `name`, `value`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (4, 1, 0, 35, '规格', '8+256g', 1, NULL, '2022-03-05 15:01:53', NULL, NULL);
INSERT INTO `sku_attribute_value` (`id`, `spu_id`, `sku_id`, `attribute_id`, `name`, `value`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (5, 1, 0, 36, '上市时间', '2021-07-17', 2, NULL, '2022-03-05 15:01:53', NULL, NULL);
INSERT INTO `sku_attribute_value` (`id`, `spu_id`, `sku_id`, `attribute_id`, `name`, `value`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (97, 68, 0, NULL, '颜色', '4123', 1, '2021-08-07 23:38:50', '2021-08-07 23:38:50', NULL, NULL);
INSERT INTO `sku_attribute_value` (`id`, `spu_id`, `sku_id`, `attribute_id`, `name`, `value`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (98, 68, 0, NULL, '规格', '456', 1, '2021-08-07 23:38:50', '2021-08-07 23:38:50', NULL, NULL);
INSERT INTO `sku_attribute_value` (`id`, `spu_id`, `sku_id`, `attribute_id`, `name`, `value`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (99, 68, 0, NULL, '规格', '123', 1, '2021-08-07 23:38:50', '2021-08-07 23:38:50', NULL, NULL);
INSERT INTO `sku_attribute_value` (`id`, `spu_id`, `sku_id`, `attribute_id`, `name`, `value`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (100, 69, 0, NULL, '上市时间', '123', 2, '2021-08-07 23:41:44', '2021-08-07 23:41:44', NULL, NULL);
INSERT INTO `sku_attribute_value` (`id`, `spu_id`, `sku_id`, `attribute_id`, `name`, `value`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (101, 69, 0, NULL, '颜色', '123', 1, '2021-08-07 23:41:44', '2021-08-07 23:41:44', NULL, NULL);
INSERT INTO `sku_attribute_value` (`id`, `spu_id`, `sku_id`, `attribute_id`, `name`, `value`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (102, 69, 0, NULL, '颜色', '4123', 1, '2021-08-07 23:41:44', '2021-08-07 23:41:44', NULL, NULL);
INSERT INTO `sku_attribute_value` (`id`, `spu_id`, `sku_id`, `attribute_id`, `name`, `value`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (103, 69, 0, NULL, '规格', '456', 1, '2021-08-07 23:41:44', '2021-08-07 23:41:44', NULL, NULL);
INSERT INTO `sku_attribute_value` (`id`, `spu_id`, `sku_id`, `attribute_id`, `name`, `value`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (104, 69, 0, NULL, '规格', '123', 1, '2021-08-07 23:41:45', '2021-08-07 23:41:45', NULL, NULL);
INSERT INTO `sku_attribute_value` (`id`, `spu_id`, `sku_id`, `attribute_id`, `name`, `value`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (216, 1, 0, NULL, '颜色', '蓝', 1, '2022-03-05 09:25:53', '2022-03-05 15:01:53', NULL, NULL);
INSERT INTO `sku_attribute_value` (`id`, `spu_id`, `sku_id`, `attribute_id`, `name`, `value`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522935838057193474, 1522935835783880706, 1522935837625180161, 1522228724506628098, '颜色', '黑色', 1, '2022-05-07 21:46:13', '2022-05-07 21:46:13', 'fxz', 'fxz');
INSERT INTO `sku_attribute_value` (`id`, `spu_id`, `sku_id`, `attribute_id`, `name`, `value`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522935838061387778, 1522935835783880706, 1522935837625180161, 1522848786506637313, '内存', '8+128g', 1, '2022-05-07 21:46:13', '2022-05-07 21:46:13', 'fxz', 'fxz');
INSERT INTO `sku_attribute_value` (`id`, `spu_id`, `sku_id`, `attribute_id`, `name`, `value`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522942858198056962, 1522942856205762561, 1522942857774432257, 1522228724506628098, '颜色', '黑色', 1, '2022-05-07 22:14:07', '2022-05-07 22:14:07', 'fxz', 'fxz');
INSERT INTO `sku_attribute_value` (`id`, `spu_id`, `sku_id`, `attribute_id`, `name`, `value`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522942858202251266, 1522942856205762561, 1522942857774432257, 1522848786506637313, '内存', '8+128G', 1, '2022-05-07 22:14:07', '2022-05-07 22:14:07', 'fxz', 'fxz');
INSERT INTO `sku_attribute_value` (`id`, `spu_id`, `sku_id`, `attribute_id`, `name`, `value`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522947065739280386, 1522947064145444865, 1522947065386958849, 1522228724506628098, '颜色', '黑色', 1, '2022-05-07 22:30:50', '2022-05-07 22:30:50', 'fxz', 'fxz');
INSERT INTO `sku_attribute_value` (`id`, `spu_id`, `sku_id`, `attribute_id`, `name`, `value`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522947065756057601, 1522947064145444865, 1522947065386958849, 1522848786506637313, '内存', '8+128G', 1, '2022-05-07 22:30:50', '2022-05-07 22:30:50', 'fxz', 'fxz');
INSERT INTO `sku_attribute_value` (`id`, `spu_id`, `sku_id`, `attribute_id`, `name`, `value`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522947066678804481, 1522947064145444865, 1522947066339065858, 1522228724506628098, '颜色', '黑色', 1, '2022-05-07 22:30:50', '2022-05-07 22:30:50', 'fxz', 'fxz');
INSERT INTO `sku_attribute_value` (`id`, `spu_id`, `sku_id`, `attribute_id`, `name`, `value`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522948098465001474, 1522948097013772290, 1522948097793912833, 1522228724506628098, '颜色', '黑色', 1, '2022-05-07 22:34:56', '2022-05-07 22:34:56', 'fxz', 'fxz');
INSERT INTO `sku_attribute_value` (`id`, `spu_id`, `sku_id`, `attribute_id`, `name`, `value`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522956749867139073, 1522956747757404162, 1522956749477068801, 1522951238677491713, '屏幕尺寸', '13寸', 1, '2022-05-07 23:09:19', '2022-05-07 23:09:19', 'fxz', 'fxz');
INSERT INTO `sku_attribute_value` (`id`, `spu_id`, `sku_id`, `attribute_id`, `name`, `value`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522957395076923394, 1522957393978015746, 1522957394783322113, 1522951238677491713, '屏幕尺寸', '13寸', 1, '2022-05-07 23:11:53', '2022-05-07 23:11:53', 'fxz', 'fxz');
INSERT INTO `sku_attribute_value` (`id`, `spu_id`, `sku_id`, `attribute_id`, `name`, `value`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522957395089506306, 1522957393978015746, 1522957394783322113, 1522951238694268929, '内存', '8+128G', 1, '2022-05-07 23:11:53', '2022-05-07 23:11:53', 'fxz', 'fxz');
INSERT INTO `sku_attribute_value` (`id`, `spu_id`, `sku_id`, `attribute_id`, `name`, `value`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522957971172966401, 1522957969516216322, 1522957970707398658, 1522951238677491713, '屏幕尺寸', '13寸', 1, '2022-05-07 23:14:10', '2022-05-07 23:14:10', 'fxz', 'fxz');
COMMIT;

-- ----------------------------
-- Table structure for spu
-- ----------------------------
DROP TABLE IF EXISTS `spu`;
CREATE TABLE `spu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品名称',
  `category_id` bigint(20) NOT NULL COMMENT '商品类型ID',
  `brand_id` bigint(20) DEFAULT NULL COMMENT '商品品牌ID',
  `origin_price` bigint(20) NOT NULL COMMENT '原价【起】',
  `price` bigint(20) NOT NULL COMMENT '现价【起】',
  `sales` int(11) DEFAULT '0' COMMENT '销量',
  `pic_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '商品主图',
  `album` json DEFAULT NULL COMMENT '商品图册',
  `unit` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '单位',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '商品简介',
  `detail` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '商品详情',
  `status` tinyint(4) DEFAULT NULL COMMENT '商品状态：0-下架 1-上架',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_pms_spu_pms_brand` (`brand_id`) USING BTREE,
  KEY `fk_pms_spu_pms_category` (`category_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1522957969516216323 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='商品表';

-- ----------------------------
-- Records of spu
-- ----------------------------
BEGIN;
INSERT INTO `spu` (`id`, `name`, `category_id`, `brand_id`, `origin_price`, `price`, `sales`, `pic_url`, `album`, `unit`, `description`, `detail`, `status`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1, '小米12 PRO', 5, 1, 599900, 599900, 1, 'http://a.youlai.tech:9000/default/1fb5d61cae2c4831a6122ca9ec747624.png', '[\"https://gitee.com/haoxr/image/raw/master/default/image-20210702002909113.png\", \"https://gitee.com/haoxr/image/raw/master/default/image-20210702002909113.png\"]', '台', '好快,好稳,\n好一次强上加强。\n高通全新一代芯片赋能，速度大幅提升。\n三大专业主摄影像加持，能力全面进化。\n大师级设计理念新诠释，质感简而不凡。\n斩获十五项纪录旗舰屏，感官万般出众。', '<p>123123<img src=\"http://a.youlai.tech:9000/default/1a69357664c24962ac23953905c3c38f.png\" alt=\"\" data-href=\"\" style=\"width: 449.00px;height: 449.00px;\"/></p>', 1, NULL, '2022-03-05 15:01:53', NULL, NULL);
INSERT INTO `spu` (`id`, `name`, `category_id`, `brand_id`, `origin_price`, `price`, `sales`, `pic_url`, `album`, `unit`, `description`, `detail`, `status`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522934680165052417, '华为p40', 3, 1, 5999, 5699, 0, '/system/file/fxzcloud/83c63debabbf4319820bec66a55ccd2d.png', '[\"/system/file/fxzcloud/b4b1916a49c74868934213cc4e4363b7.jpeg\"]', NULL, '华为p40手机，就是nb', '<p>华为就是nb</p>', NULL, '2022-05-07 21:41:37', '2022-05-07 21:41:37', 'fxz', 'fxz');
INSERT INTO `spu` (`id`, `name`, `category_id`, `brand_id`, `origin_price`, `price`, `sales`, `pic_url`, `album`, `unit`, `description`, `detail`, `status`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522935059942502401, '华为p40', 3, 1, 5999, 5699, 0, '/system/file/fxzcloud/83c63debabbf4319820bec66a55ccd2d.png', '[\"/system/file/fxzcloud/b4b1916a49c74868934213cc4e4363b7.jpeg\"]', NULL, '华为p40手机，就是nb', '<p>华为就是nb</p>', NULL, '2022-05-07 21:43:08', '2022-05-07 21:43:08', 'fxz', 'fxz');
INSERT INTO `spu` (`id`, `name`, `category_id`, `brand_id`, `origin_price`, `price`, `sales`, `pic_url`, `album`, `unit`, `description`, `detail`, `status`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522935372858552321, '华为p40', 3, 1, 5999, 5699, 0, '/system/file/fxzcloud/83c63debabbf4319820bec66a55ccd2d.png', '[\"/system/file/fxzcloud/b4b1916a49c74868934213cc4e4363b7.jpeg\"]', NULL, '华为p40手机，就是nb', '<p>华为就是nb</p>', NULL, '2022-05-07 21:44:22', '2022-05-07 21:44:22', 'fxz', 'fxz');
INSERT INTO `spu` (`id`, `name`, `category_id`, `brand_id`, `origin_price`, `price`, `sales`, `pic_url`, `album`, `unit`, `description`, `detail`, `status`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522935835783880706, '华为p40', 3, 1, 5999, 5699, 0, '/system/file/fxzcloud/83c63debabbf4319820bec66a55ccd2d.png', '[\"/system/file/fxzcloud/b4b1916a49c74868934213cc4e4363b7.jpeg\"]', NULL, '华为p40手机，就是nb', '<p>华为就是nb</p>', NULL, '2022-05-07 21:46:13', '2022-05-07 21:46:13', 'fxz', 'fxz');
INSERT INTO `spu` (`id`, `name`, `category_id`, `brand_id`, `origin_price`, `price`, `sales`, `pic_url`, `album`, `unit`, `description`, `detail`, `status`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522942856205762561, '华为p40 ', 3, 1, 6999, 6699, 0, '/system/file/fxzcloud/358f2b3d473648269045e5ceccc13814.png', '[\"/system/file/fxzcloud/ff20ac61d9414e379070d91c164b14fa.jpeg\"]', NULL, '华为p40', '<p>华为p40,快买吧</p>', NULL, '2022-05-07 22:14:06', '2022-05-07 22:14:06', 'fxz', 'fxz');
INSERT INTO `spu` (`id`, `name`, `category_id`, `brand_id`, `origin_price`, `price`, `sales`, `pic_url`, `album`, `unit`, `description`, `detail`, `status`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522947064145444865, '1', 3, 10, 111, 111, 0, '/system/file/fxzcloud/46dbf608c94746db8a172e6196ce5435.png', NULL, NULL, '1', '<p>11</p>', NULL, '2022-05-07 22:30:50', '2022-05-07 22:30:50', 'fxz', 'fxz');
INSERT INTO `spu` (`id`, `name`, `category_id`, `brand_id`, `origin_price`, `price`, `sales`, `pic_url`, `album`, `unit`, `description`, `detail`, `status`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522947693517537281, '1', 3, 1, 9988, 9966, 0, '/system/file/fxzcloud/a982df1f75284b039ffb5945a271a9fe.jpeg', '[\"/system/file/fxzcloud/b5cdf09198b54175971bafa2aa4f5b76.png\"]', NULL, '11', '<p>11</p>', NULL, '2022-05-07 22:33:20', '2022-05-07 22:33:20', 'fxz', 'fxz');
INSERT INTO `spu` (`id`, `name`, `category_id`, `brand_id`, `origin_price`, `price`, `sales`, `pic_url`, `album`, `unit`, `description`, `detail`, `status`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522948097013772290, '1', 3, 1, 998800, 996600, 0, '/system/file/fxzcloud/a982df1f75284b039ffb5945a271a9fe.jpeg', '[\"/system/file/fxzcloud/b5cdf09198b54175971bafa2aa4f5b76.png\"]', NULL, '11', '<p>11</p>', NULL, '2022-05-07 22:34:56', '2022-05-07 22:34:56', 'fxz', 'fxz');
INSERT INTO `spu` (`id`, `name`, `category_id`, `brand_id`, `origin_price`, `price`, `sales`, `pic_url`, `album`, `unit`, `description`, `detail`, `status`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522956747757404162, '1', 1522191266574311425, 1522951736029671425, 1188, 1188, 0, NULL, NULL, NULL, '1', NULL, NULL, '2022-05-07 23:09:19', '2022-05-07 23:09:19', 'fxz', 'fxz');
INSERT INTO `spu` (`id`, `name`, `category_id`, `brand_id`, `origin_price`, `price`, `sales`, `pic_url`, `album`, `unit`, `description`, `detail`, `status`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522957393978015746, 'mac', 1522191266574311425, 1522951736029671425, 188999, 1199, 0, NULL, NULL, NULL, '1', NULL, NULL, '2022-05-07 23:11:53', '2022-05-07 23:11:53', 'fxz', 'fxz');
INSERT INTO `spu` (`id`, `name`, `category_id`, `brand_id`, `origin_price`, `price`, `sales`, `pic_url`, `album`, `unit`, `description`, `detail`, `status`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522957969516216322, 'mac', 1522191266574311425, 1522951736029671425, 1299988, 1299966, 0, NULL, NULL, NULL, NULL, NULL, NULL, '2022-05-07 23:14:10', '2022-05-07 23:14:10', 'fxz', 'fxz');
COMMIT;

-- ----------------------------
-- Table structure for spu_attribute_value
-- ----------------------------
DROP TABLE IF EXISTS `spu_attribute_value`;
CREATE TABLE `spu_attribute_value` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `spu_id` bigint(20) NOT NULL COMMENT '产品ID',
  `attribute_id` bigint(20) DEFAULT NULL COMMENT '属性ID',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '属性名称',
  `value` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '属性值',
  `type` tinyint(4) NOT NULL COMMENT '类型(1:规格;2:属性;)',
  `pic_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '规格图片',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_pms_spu_attribute_pms_attr` (`name`) USING BTREE,
  KEY `fk_pms_spu_attribute_pms_spu` (`spu_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1522957970120196098 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='商品属性项表';

-- ----------------------------
-- Records of spu_attribute_value
-- ----------------------------
BEGIN;
INSERT INTO `spu_attribute_value` (`id`, `spu_id`, `attribute_id`, `name`, `value`, `type`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1, 1, 34, '颜色', '黑', 1, 'http://a.youlai.tech:9000/default/6759b824e6d04af69f6f3e55190e7e79.png', NULL, '2022-03-05 15:01:53', NULL, NULL);
INSERT INTO `spu_attribute_value` (`id`, `spu_id`, `attribute_id`, `name`, `value`, `type`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (3, 1, 35, '规格', '6+128g', 1, NULL, NULL, '2022-03-05 15:01:53', NULL, NULL);
INSERT INTO `spu_attribute_value` (`id`, `spu_id`, `attribute_id`, `name`, `value`, `type`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (4, 1, 35, '规格', '8+256g', 1, NULL, NULL, '2022-03-05 15:01:53', NULL, NULL);
INSERT INTO `spu_attribute_value` (`id`, `spu_id`, `attribute_id`, `name`, `value`, `type`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (5, 1, 36, '上市时间', '2021-07-17', 2, NULL, NULL, '2022-03-05 15:01:53', NULL, NULL);
INSERT INTO `spu_attribute_value` (`id`, `spu_id`, `attribute_id`, `name`, `value`, `type`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (97, 68, NULL, '颜色', '4123', 1, NULL, '2021-08-07 23:38:50', '2021-08-07 23:38:50', NULL, NULL);
INSERT INTO `spu_attribute_value` (`id`, `spu_id`, `attribute_id`, `name`, `value`, `type`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (98, 68, NULL, '规格', '456', 1, NULL, '2021-08-07 23:38:50', '2021-08-07 23:38:50', NULL, NULL);
INSERT INTO `spu_attribute_value` (`id`, `spu_id`, `attribute_id`, `name`, `value`, `type`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (99, 68, NULL, '规格', '123', 1, NULL, '2021-08-07 23:38:50', '2021-08-07 23:38:50', NULL, NULL);
INSERT INTO `spu_attribute_value` (`id`, `spu_id`, `attribute_id`, `name`, `value`, `type`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (100, 69, NULL, '上市时间', '123', 2, NULL, '2021-08-07 23:41:44', '2021-08-07 23:41:44', NULL, NULL);
INSERT INTO `spu_attribute_value` (`id`, `spu_id`, `attribute_id`, `name`, `value`, `type`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (101, 69, NULL, '颜色', '123', 1, NULL, '2021-08-07 23:41:44', '2021-08-07 23:41:44', NULL, NULL);
INSERT INTO `spu_attribute_value` (`id`, `spu_id`, `attribute_id`, `name`, `value`, `type`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (102, 69, NULL, '颜色', '4123', 1, NULL, '2021-08-07 23:41:44', '2021-08-07 23:41:44', NULL, NULL);
INSERT INTO `spu_attribute_value` (`id`, `spu_id`, `attribute_id`, `name`, `value`, `type`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (103, 69, NULL, '规格', '456', 1, NULL, '2021-08-07 23:41:44', '2021-08-07 23:41:44', NULL, NULL);
INSERT INTO `spu_attribute_value` (`id`, `spu_id`, `attribute_id`, `name`, `value`, `type`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (104, 69, NULL, '规格', '123', 1, NULL, '2021-08-07 23:41:45', '2021-08-07 23:41:45', NULL, NULL);
INSERT INTO `spu_attribute_value` (`id`, `spu_id`, `attribute_id`, `name`, `value`, `type`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (216, 1, NULL, '颜色', '蓝', 1, 'http://a.youlai.tech:9000/default/aed7966ff68640f08d110f4fbcd1cdc2.png', '2022-03-05 09:25:53', '2022-03-05 15:01:53', NULL, NULL);
INSERT INTO `spu_attribute_value` (`id`, `spu_id`, `attribute_id`, `name`, `value`, `type`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522934681108770817, 1522934680165052417, 1522227526558879746, '网络', '5G', 2, NULL, '2022-05-07 21:41:37', '2022-05-07 21:41:37', 'fxz', 'fxz');
INSERT INTO `spu_attribute_value` (`id`, `spu_id`, `attribute_id`, `name`, `value`, `type`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522934681121353730, 1522934680165052417, 1522228692801884161, '电池', '100w', 2, NULL, '2022-05-07 21:41:37', '2022-05-07 21:41:37', 'fxz', 'fxz');
INSERT INTO `spu_attribute_value` (`id`, `spu_id`, `attribute_id`, `name`, `value`, `type`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522934681125548034, 1522934680165052417, 1522228796099203073, '重量', '300g', 2, NULL, '2022-05-07 21:41:37', '2022-05-07 21:41:37', 'fxz', 'fxz');
INSERT INTO `spu_attribute_value` (`id`, `spu_id`, `attribute_id`, `name`, `value`, `type`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522935072571551745, 1522935059942502401, 1522227526558879746, '网络', '5G', 2, NULL, '2022-05-07 21:43:11', '2022-05-07 21:43:11', 'fxz', 'fxz');
INSERT INTO `spu_attribute_value` (`id`, `spu_id`, `attribute_id`, `name`, `value`, `type`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522935072655437826, 1522935059942502401, 1522228692801884161, '电池', '100w', 2, NULL, '2022-05-07 21:43:11', '2022-05-07 21:43:11', 'fxz', 'fxz');
INSERT INTO `spu_attribute_value` (`id`, `spu_id`, `attribute_id`, `name`, `value`, `type`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522935072743518210, 1522935059942502401, 1522228796099203073, '重量', '300g', 2, NULL, '2022-05-07 21:43:11', '2022-05-07 21:43:11', 'fxz', 'fxz');
INSERT INTO `spu_attribute_value` (`id`, `spu_id`, `attribute_id`, `name`, `value`, `type`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522935373391228930, 1522935372858552321, 1522227526558879746, '网络', '5G', 2, NULL, '2022-05-07 21:44:22', '2022-05-07 21:44:22', 'fxz', 'fxz');
INSERT INTO `spu_attribute_value` (`id`, `spu_id`, `attribute_id`, `name`, `value`, `type`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522935373408006146, 1522935372858552321, 1522228692801884161, '电池', '100w', 2, NULL, '2022-05-07 21:44:22', '2022-05-07 21:44:22', 'fxz', 'fxz');
INSERT INTO `spu_attribute_value` (`id`, `spu_id`, `attribute_id`, `name`, `value`, `type`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522935373412200450, 1522935372858552321, 1522228796099203073, '重量', '300g', 2, NULL, '2022-05-07 21:44:22', '2022-05-07 21:44:22', 'fxz', 'fxz');
INSERT INTO `spu_attribute_value` (`id`, `spu_id`, `attribute_id`, `name`, `value`, `type`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522935836895371265, 1522935835783880706, 1522227526558879746, '网络', '5G', 2, NULL, '2022-05-07 21:46:13', '2022-05-07 21:46:13', 'fxz', 'fxz');
INSERT INTO `spu_attribute_value` (`id`, `spu_id`, `attribute_id`, `name`, `value`, `type`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522935836903759873, 1522935835783880706, 1522228692801884161, '电池', '100w', 2, NULL, '2022-05-07 21:46:13', '2022-05-07 21:46:13', 'fxz', 'fxz');
INSERT INTO `spu_attribute_value` (`id`, `spu_id`, `attribute_id`, `name`, `value`, `type`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522935836907954178, 1522935835783880706, 1522228796099203073, '重量', '300g', 2, NULL, '2022-05-07 21:46:13', '2022-05-07 21:46:13', 'fxz', 'fxz');
INSERT INTO `spu_attribute_value` (`id`, `spu_id`, `attribute_id`, `name`, `value`, `type`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522942857183035393, 1522942856205762561, 1522227526558879746, '网络', '5G', 2, NULL, '2022-05-07 22:14:07', '2022-05-07 22:14:07', 'fxz', 'fxz');
INSERT INTO `spu_attribute_value` (`id`, `spu_id`, `attribute_id`, `name`, `value`, `type`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522947064745230337, 1522947064145444865, 1522227526558879746, '网络', '4G', 2, NULL, '2022-05-07 22:30:50', '2022-05-07 22:30:50', 'fxz', 'fxz');
INSERT INTO `spu_attribute_value` (`id`, `spu_id`, `attribute_id`, `name`, `value`, `type`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522947064753618945, 1522947064145444865, 1522228692801884161, '电池', '30w', 2, NULL, '2022-05-07 22:30:50', '2022-05-07 22:30:50', 'fxz', 'fxz');
INSERT INTO `spu_attribute_value` (`id`, `spu_id`, `attribute_id`, `name`, `value`, `type`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522947064757813249, 1522947064145444865, 1522228796099203073, '重量', '300g', 2, NULL, '2022-05-07 22:30:50', '2022-05-07 22:30:50', 'fxz', 'fxz');
INSERT INTO `spu_attribute_value` (`id`, `spu_id`, `attribute_id`, `name`, `value`, `type`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522956748747259905, 1522956747757404162, 1522951246197878785, '芯片', 'm1', 2, NULL, '2022-05-07 23:09:19', '2022-05-07 23:09:19', 'fxz', 'fxz');
INSERT INTO `spu_attribute_value` (`id`, `spu_id`, `attribute_id`, `name`, `value`, `type`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522956748785008641, 1522956747757404162, 1522951246206267394, '重量', '300g', 2, NULL, '2022-05-07 23:09:19', '2022-05-07 23:09:19', 'fxz', 'fxz');
INSERT INTO `spu_attribute_value` (`id`, `spu_id`, `attribute_id`, `name`, `value`, `type`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522957394389057537, 1522957393978015746, 1522951246197878785, '芯片', 'm1', 2, NULL, '2022-05-07 23:11:53', '2022-05-07 23:11:53', 'fxz', 'fxz');
INSERT INTO `spu_attribute_value` (`id`, `spu_id`, `attribute_id`, `name`, `value`, `type`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522957970120196097, 1522957969516216322, 1522951246197878785, '芯片', 'm1', 2, NULL, '2022-05-07 23:14:10', '2022-05-07 23:14:10', 'fxz', 'fxz');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
