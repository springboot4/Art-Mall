

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
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_pms_attr_pms_category` (`category_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1564437364663427074 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商品属性表';

-- ----------------------------
-- Records of attribute
-- ----------------------------
BEGIN;
INSERT INTO `attribute` (`id`, `category_id`, `name`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (34, 5, '颜色', 1, '2021-07-11 17:57:06', '2022-03-05 13:16:30', NULL, NULL);
INSERT INTO `attribute` (`id`, `category_id`, `name`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (35, 5, '规格', 1, '2021-07-11 18:00:06', '2022-03-05 13:16:30', NULL, NULL);
INSERT INTO `attribute` (`id`, `category_id`, `name`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (36, 5, '上市时间', 2, '2021-07-11 18:00:08', '2022-03-05 13:16:31', NULL, NULL);
INSERT INTO `attribute` (`id`, `category_id`, `name`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (47, 8, '123', 1, '2022-03-04 13:00:43', '2022-05-06 23:47:13', NULL, 'fxz');
INSERT INTO `attribute` (`id`, `category_id`, `name`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522228459514695682, 0, '', 1, '2022-05-05 22:55:21', '2022-05-05 22:55:21', 'fxz', 'fxz');
INSERT INTO `attribute` (`id`, `category_id`, `name`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522228947647795201, 0, '', 2, '2022-05-05 22:57:17', '2022-05-05 22:57:17', 'fxz', 'fxz');
INSERT INTO `attribute` (`id`, `category_id`, `name`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522950701194211329, 1522189691277635586, '颜色', 1, '2022-05-07 22:45:17', '2022-05-07 22:45:17', 'fxz', 'fxz');
INSERT INTO `attribute` (`id`, `category_id`, `name`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522950701231960066, 1522189691277635586, '内存', 1, '2022-05-07 22:45:17', '2022-05-07 22:45:17', 'fxz', 'fxz');
INSERT INTO `attribute` (`id`, `category_id`, `name`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522950705698893825, 1522189691277635586, '网络', 2, '2022-05-07 22:45:18', '2022-05-07 22:45:18', 'fxz', 'fxz');
INSERT INTO `attribute` (`id`, `category_id`, `name`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522950705707282433, 1522189691277635586, '重量', 2, '2022-05-07 22:45:18', '2022-05-07 22:45:18', 'fxz', 'fxz');
INSERT INTO `attribute` (`id`, `category_id`, `name`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522951238677491713, 1522191266574311425, '屏幕尺寸', 1, '2022-05-07 22:47:25', '2022-05-07 22:47:25', 'fxz', 'fxz');
INSERT INTO `attribute` (`id`, `category_id`, `name`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522951238694268929, 1522191266574311425, '内存', 1, '2022-05-07 22:47:25', '2022-05-07 22:47:25', 'fxz', 'fxz');
INSERT INTO `attribute` (`id`, `category_id`, `name`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522951238694268930, 1522191266574311425, '颜色', 1, '2022-05-07 22:47:25', '2022-05-07 22:47:25', 'fxz', 'fxz');
INSERT INTO `attribute` (`id`, `category_id`, `name`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522951246197878785, 1522191266574311425, '芯片', 2, '2022-05-07 22:47:27', '2022-05-07 22:47:27', 'fxz', 'fxz');
INSERT INTO `attribute` (`id`, `category_id`, `name`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522951246206267394, 1522191266574311425, '重量', 2, '2022-05-07 22:47:27', '2022-05-07 22:47:27', 'fxz', 'fxz');
INSERT INTO `attribute` (`id`, `category_id`, `name`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1524927022354968577, 3, '', 1, '2022-05-13 09:38:29', '2022-05-13 09:38:29', 'fxz', 'fxz');
INSERT INTO `attribute` (`id`, `category_id`, `name`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1524927027245527041, 3, '', 2, '2022-05-13 09:38:30', '2022-05-13 09:38:30', 'fxz', 'fxz');
INSERT INTO `attribute` (`id`, `category_id`, `name`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1564435985689518081, 1524737447959506946, '颜色', 1, '2022-08-30 10:12:59', '2022-08-30 10:12:59', 'fxz', 'fxz');
INSERT INTO `attribute` (`id`, `category_id`, `name`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1564435985727266817, 1524737447959506946, '内存', 1, '2022-08-30 10:12:59', '2022-08-30 10:12:59', 'fxz', 'fxz');
INSERT INTO `attribute` (`id`, `category_id`, `name`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1564435991829979138, 1524737447959506946, '显卡', 2, '2022-08-30 10:13:01', '2022-08-30 10:13:01', 'fxz', 'fxz');
INSERT INTO `attribute` (`id`, `category_id`, `name`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1564436042136461313, 1524737517643673602, '内存', 1, '2022-08-30 10:13:13', '2022-08-30 10:13:13', 'fxz', 'fxz');
INSERT INTO `attribute` (`id`, `category_id`, `name`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1564436046330765313, 1524737517643673602, '显卡', 2, '2022-08-30 10:13:14', '2022-08-30 10:13:14', 'fxz', 'fxz');
INSERT INTO `attribute` (`id`, `category_id`, `name`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1564437281486184449, 1524738411214974977, '重量', 1, '2022-08-30 10:18:08', '2022-08-30 10:18:08', 'fxz', 'fxz');
INSERT INTO `attribute` (`id`, `category_id`, `name`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1564437284686438401, 1524738411214974977, '品质', 2, '2022-08-30 10:18:09', '2022-08-30 10:18:09', 'fxz', 'fxz');
INSERT INTO `attribute` (`id`, `category_id`, `name`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1564437362499166209, 1524738470811840514, '重量', 1, '2022-08-30 10:18:28', '2022-08-30 10:18:28', 'fxz', 'fxz');
INSERT INTO `attribute` (`id`, `category_id`, `name`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1564437364663427073, 1524738470811840514, '品种', 2, '2022-08-30 10:18:28', '2022-08-30 10:18:28', 'fxz', 'fxz');
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
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1564437495265665027 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商品品牌表';

-- ----------------------------
-- Records of brand
-- ----------------------------
BEGIN;
INSERT INTO `brand` (`id`, `name`, `logo_url`, `sort`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1, '华为', '/system/file/fxzcloud/fe713dafd220473491d1cd3d6ebddf3f.webp', 1, '2021-07-11 19:56:58', '2022-08-11 10:58:51', NULL, 'fxz');
INSERT INTO `brand` (`id`, `name`, `logo_url`, `sort`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (10, '小米', '/system/file/fxzcloud/6d1953ea1f114fad86386965e7e1ff7e.png', 1, '2022-03-05 16:12:16', '2022-08-11 10:58:59', NULL, 'fxz');
INSERT INTO `brand` (`id`, `name`, `logo_url`, `sort`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522951736029671425, '苹果', '/system/file/fxzcloud/4664773d67d64a2db83956a44ed57e2f.png', 0, '2022-05-07 22:49:24', '2022-08-11 10:58:43', 'fxz', 'fxz');
INSERT INTO `brand` (`id`, `name`, `logo_url`, `sort`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1564437495265665026, '红富士', '/system/file/fxzcloud/102ff1c9828a4d99ba365eaf866577e1.jpg', 4, '2022-08-30 10:18:59', '2022-08-30 10:18:59', 'fxz', 'fxz');
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
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1524738470811840515 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商品分类表';

-- ----------------------------
-- Records of category
-- ----------------------------
BEGIN;
INSERT INTO `category` (`id`, `name`, `parent_id`, `level`, `icon_url`, `sort`, `visible`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (3, '手机配件', 0, 1, NULL, 1, 1, NULL, NULL, NULL, NULL);
INSERT INTO `category` (`id`, `name`, `parent_id`, `level`, `icon_url`, `sort`, `visible`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (4, '智能手机', 3, 2, NULL, 1, 1, NULL, NULL, NULL, NULL);
INSERT INTO `category` (`id`, `name`, `parent_id`, `level`, `icon_url`, `sort`, `visible`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (6, '电脑', 0, 1, 'http://a.youlai.tech:9000/default/776c21c1a71848069093033f461c5f4a.jpg', 1, 1, '2022-02-25 11:22:44', '2022-02-25 11:22:44', NULL, NULL);
INSERT INTO `category` (`id`, `name`, `parent_id`, `level`, `icon_url`, `sort`, `visible`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (7, '游戏本', 6, 2, 'http://a.youlai.tech:9000/default/f41d764d7ce64054b75fe9be5fb3f700.jpg', 1, 1, '2022-02-25 11:23:06', '2022-02-25 11:23:06', NULL, NULL);
INSERT INTO `category` (`id`, `name`, `parent_id`, `level`, `icon_url`, `sort`, `visible`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (8, '轻薄本', 6, 2, 'http://a.youlai.tech:9000/default/840ddc78c93d422b9929821c97f3dfbe.jpg', 2, 1, '2022-02-25 11:23:24', '2022-02-25 11:23:24', NULL, NULL);
INSERT INTO `category` (`id`, `name`, `parent_id`, `level`, `icon_url`, `sort`, `visible`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522189691277635586, '5G手机', 4, 3, '/system/file/fxzcloud/10ecdc1273c24e37a213616b604de77c.png', 0, 1, '2022-05-05 20:21:18', '2022-08-28 18:58:38', 'fxz', 'fxz');
INSERT INTO `category` (`id`, `name`, `parent_id`, `level`, `icon_url`, `sort`, `visible`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522191209464668161, '苹果笔记本', 6, 2, '/system/file/fxzcloud/0c1aacddbff24e48bcde61ae4b0ccc7a.jpeg', 3, 1, '2022-05-05 20:27:20', '2022-05-07 22:45:52', 'fxz', 'fxz');
INSERT INTO `category` (`id`, `name`, `parent_id`, `level`, `icon_url`, `sort`, `visible`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1522191266574311425, 'macbook pro', 1522191209464668161, 3, '/system/file/fxzcloud/f0d5d8885a8b4eb29227c5e993b8667b.webp', 1, 1, '2022-05-05 20:27:34', '2022-08-11 09:26:41', 'fxz', 'fxz');
INSERT INTO `category` (`id`, `name`, `parent_id`, `level`, `icon_url`, `sort`, `visible`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1524737447959506946, '游戏笔记本', 7, 3, '/system/file/fxzcloud/961d0681ba594253bb23d505a6c63aa7.png', 0, 1, '2022-05-12 21:05:11', '2022-08-11 09:26:19', 'fxz', 'fxz');
INSERT INTO `category` (`id`, `name`, `parent_id`, `level`, `icon_url`, `sort`, `visible`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1524737517643673602, '高端游戏本', 7, 3, '/system/file/fxzcloud/084f74db8c3d4d1a9edbd36047694c57.png', 2, 1, '2022-05-12 21:05:27', '2022-08-11 09:26:28', 'fxz', 'fxz');
INSERT INTO `category` (`id`, `name`, `parent_id`, `level`, `icon_url`, `sort`, `visible`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1524738318097231873, '生鲜', 0, 1, '/system/file/fxzcloud/6a91521f391d4c9e959cd2f744be9d2b.png', 3, 1, '2022-05-12 21:08:38', '2022-05-12 21:08:38', 'fxz', 'fxz');
INSERT INTO `category` (`id`, `name`, `parent_id`, `level`, `icon_url`, `sort`, `visible`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1524738363114696705, '水果', 1524738318097231873, 2, '/system/file/fxzcloud/68fad79706804511bf273ded3a0d9120.jpeg', 1, 1, '2022-05-12 21:08:49', '2022-05-12 21:08:49', 'fxz', 'fxz');
INSERT INTO `category` (`id`, `name`, `parent_id`, `level`, `icon_url`, `sort`, `visible`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1524738411214974977, '苹果', 1524738363114696705, 3, '/system/file/fxzcloud/4c371284b26b4449b3fceeb4e0cb8468.png', 1, 1, '2022-05-12 21:09:00', '2022-08-11 09:26:53', 'fxz', 'fxz');
INSERT INTO `category` (`id`, `name`, `parent_id`, `level`, `icon_url`, `sort`, `visible`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1524738470811840514, '葡萄', 1524738363114696705, 3, '/system/file/fxzcloud/b3bb2f37b17546a9a602e72bd8a523bd.jpeg', 2, 1, '2022-05-12 21:09:14', '2022-08-11 09:27:03', 'fxz', 'fxz');
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
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_pms_sku_pms_spu` (`spu_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1564437966378278915 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商品库存表';

-- ----------------------------
-- Records of sku
-- ----------------------------
BEGIN;
INSERT INTO `sku` (`id`, `sku_sn`, `spu_id`, `name`, `price`, `stock_num`, `locked_stock_num`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1557683375603257347, '0001', 1557683374491766788, '黑色华为p50', 899900, 1000, 0, '/system/file/fxzcloud/b9120a762ef444a782ed79c047662c83.png', '2022-08-11 19:00:32', '2022-08-11 19:00:32', 'fxz', 'fxz');
INSERT INTO `sku` (`id`, `sku_sn`, `spu_id`, `name`, `price`, `stock_num`, `locked_stock_num`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1557683376983183364, '0002', 1557683374491766788, '白色华为p50', 899900, 1000, 0, '/system/file/fxzcloud/d7c0b00c532d4fc6a406e65fd2797fd9.png', '2022-08-11 19:00:32', '2022-08-11 19:00:32', 'fxz', 'fxz');
INSERT INTO `sku` (`id`, `sku_sn`, `spu_id`, `name`, `price`, `stock_num`, `locked_stock_num`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1564437958274883586, 'hfs0001', 1564437956710408194, '红富士', 899, 1000, 1, NULL, '2022-08-30 10:20:50', '2022-08-30 10:20:50', 'fxz', 'fxz');
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
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_pms_spu_attribute_pms_attr` (`name`) USING BTREE,
  KEY `fk_pms_spu_attribute_pms_spu` (`spu_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1564437967082921986 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商品属性项表';

-- ----------------------------
-- Records of sku_attribute_value
-- ----------------------------
BEGIN;
INSERT INTO `sku_attribute_value` (`id`, `spu_id`, `sku_id`, `attribute_id`, `name`, `value`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1557683376127545346, 1557683374491766786, 1557683375603257345, 1522950701231960066, '内存', '8+128G', 1, '2022-08-11 19:00:32', '2022-08-11 19:00:32', 'fxz', 'fxz');
INSERT INTO `sku_attribute_value` (`id`, `spu_id`, `sku_id`, `attribute_id`, `name`, `value`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1557683376135933953, 1557683374491766786, 1557683375603257345, 1522950701194211329, '颜色', '黑色', 1, '2022-08-11 19:00:32', '2022-08-11 19:00:32', 'fxz', 'fxz');
INSERT INTO `sku_attribute_value` (`id`, `spu_id`, `sku_id`, `attribute_id`, `name`, `value`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1557683377750740993, 1557683374491766786, 1557683376983183362, 1522950701231960066, '内存', '8+128G', 1, '2022-08-11 19:00:32', '2022-08-11 19:00:32', 'fxz', 'fxz');
INSERT INTO `sku_attribute_value` (`id`, `spu_id`, `sku_id`, `attribute_id`, `name`, `value`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1557683377754935297, 1557683374491766786, 1557683376983183362, 1522950701194211329, '颜色', '白色', 1, '2022-08-11 19:00:32', '2022-08-11 19:00:32', 'fxz', 'fxz');
INSERT INTO `sku_attribute_value` (`id`, `spu_id`, `sku_id`, `attribute_id`, `name`, `value`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1564437958954360834, 1564437956710408194, 1564437958274883586, 1564437281486184449, '重量', '1g', 1, '2022-08-30 10:20:50', '2022-08-30 10:20:50', 'fxz', 'fxz');
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
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_pms_spu_pms_brand` (`brand_id`) USING BTREE,
  KEY `fk_pms_spu_pms_category` (`category_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1564437964717334530 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商品表';

-- ----------------------------
-- Records of spu
-- ----------------------------
BEGIN;
INSERT INTO `spu` (`id`, `name`, `category_id`, `brand_id`, `origin_price`, `price`, `sales`, `pic_url`, `album`, `unit`, `description`, `detail`, `status`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1564437956710408194, '红富士大苹果', 1524738411214974977, 1564437495265665026, 999, 899, 0, '/system/file/fxzcloud/becfd62040b2413ca9126c8b21afe808.jpg', NULL, NULL, '快来买', '<p>不好吃</p>', NULL, '2022-08-30 10:20:49', '2022-08-30 10:20:49', 'fxz', 'fxz');
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
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_pms_spu_attribute_pms_attr` (`name`) USING BTREE,
  KEY `fk_pms_spu_attribute_pms_spu` (`spu_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1564437965388423171 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商品属性项表';

-- ----------------------------
-- Records of spu_attribute_value
-- ----------------------------
BEGIN;
INSERT INTO `spu_attribute_value` (`id`, `spu_id`, `attribute_id`, `name`, `value`, `type`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1557683374990888961, 1557683374491766786, 1522950705698893825, '网络', '5G', 2, NULL, '2022-08-11 19:00:32', '2022-08-11 19:00:32', 'fxz', 'fxz');
INSERT INTO `spu_attribute_value` (`id`, `spu_id`, `attribute_id`, `name`, `value`, `type`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1557683375007666178, 1557683374491766786, 1522950705707282433, '重量', '200g', 2, NULL, '2022-08-11 19:00:32', '2022-08-11 19:00:32', 'fxz', 'fxz');
INSERT INTO `spu_attribute_value` (`id`, `spu_id`, `attribute_id`, `name`, `value`, `type`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1564437957528297474, 1564437956710408194, 1564437284686438401, '品质', '杠杠滴', 2, NULL, '2022-08-30 10:20:50', '2022-08-30 10:20:50', 'fxz', 'fxz');
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
