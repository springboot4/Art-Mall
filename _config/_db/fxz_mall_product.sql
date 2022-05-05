
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
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='商品属性表';

-- ----------------------------
-- Records of attribute
-- ----------------------------
BEGIN;
INSERT INTO `attribute` (`id`, `category_id`, `name`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (34, 5, '颜色', 1, '2021-07-11 17:57:06', '2022-03-05 13:16:30', NULL, NULL);
INSERT INTO `attribute` (`id`, `category_id`, `name`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (35, 5, '规格', 1, '2021-07-11 18:00:06', '2022-03-05 13:16:30', NULL, NULL);
INSERT INTO `attribute` (`id`, `category_id`, `name`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (36, 5, '上市时间', 2, '2021-07-11 18:00:08', '2022-03-05 13:16:31', NULL, NULL);
INSERT INTO `attribute` (`id`, `category_id`, `name`, `type`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (47, 8, '阿斯顿', 1, '2022-03-04 13:00:43', '2022-03-04 13:00:43', NULL, NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='商品品牌表';

-- ----------------------------
-- Records of brand
-- ----------------------------
BEGIN;
INSERT INTO `brand` (`id`, `name`, `logo_url`, `sort`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1, '有来', 'http://a.youlai.tech:9000/default/5409e3deb5a14b8fa8cb4275dee0e25d.png', 1, '2021-07-11 19:56:58', '2021-07-11 20:02:54', NULL, NULL);
INSERT INTO `brand` (`id`, `name`, `logo_url`, `sort`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (10, '小米', 'http://a.youlai.tech:9000/default/6c5433c84cc54120996a151c8e6d4cf3.jpg', 1, '2022-03-05 16:12:16', '2022-03-05 16:12:16', NULL, NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='商品分类表';

-- ----------------------------
-- Records of category
-- ----------------------------
BEGIN;
INSERT INTO `category` (`id`, `name`, `parent_id`, `level`, `icon_url`, `sort`, `visible`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (3, '手机配件', 0, 1, NULL, 1, 1, NULL, NULL, NULL, NULL);
INSERT INTO `category` (`id`, `name`, `parent_id`, `level`, `icon_url`, `sort`, `visible`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (4, '智能手机', 3, 2, NULL, 1, 1, NULL, NULL, NULL, NULL);
INSERT INTO `category` (`id`, `name`, `parent_id`, `level`, `icon_url`, `sort`, `visible`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (5, '5g手机', 4, 3, 'http://a.youlai.tech:9000/default/f4a27e240c184758942670aad9ce5639.jpg', 1, 1, NULL, '2022-03-05 16:16:16', NULL, NULL);
INSERT INTO `category` (`id`, `name`, `parent_id`, `level`, `icon_url`, `sort`, `visible`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (6, '电脑', 0, 1, 'http://a.youlai.tech:9000/default/776c21c1a71848069093033f461c5f4a.jpg', 1, 1, '2022-02-25 11:22:44', '2022-02-25 11:22:44', NULL, NULL);
INSERT INTO `category` (`id`, `name`, `parent_id`, `level`, `icon_url`, `sort`, `visible`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (7, '游戏本', 6, 2, 'http://a.youlai.tech:9000/default/f41d764d7ce64054b75fe9be5fb3f700.jpg', 1, 1, '2022-02-25 11:23:06', '2022-02-25 11:23:06', NULL, NULL);
INSERT INTO `category` (`id`, `name`, `parent_id`, `level`, `icon_url`, `sort`, `visible`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (8, '轻薄本', 6, 2, 'http://a.youlai.tech:9000/default/840ddc78c93d422b9929821c97f3dfbe.jpg', 2, 1, '2022-02-25 11:23:24', '2022-02-25 11:23:24', NULL, NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=355 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='商品库存表';

-- ----------------------------
-- Records of sku
-- ----------------------------
BEGIN;
INSERT INTO `sku` (`id`, `sku_sn`, `spu_id`, `name`, `price`, `stock_num`, `locked_stock_num`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (291, 'sn001', 1, '黑 6+128g', 399900, 999, 0, 'http://a.youlai.tech:9000/default/6759b824e6d04af69f6f3e55190e7e79.png', '2021-08-08 00:43:26', '2022-03-05 15:01:53', NULL, NULL);
INSERT INTO `sku` (`id`, `sku_sn`, `spu_id`, `name`, `price`, `stock_num`, `locked_stock_num`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (292, 'sn002', 1, '黑 8+256g', 499900, 999, 0, 'http://a.youlai.tech:9000/default/6759b824e6d04af69f6f3e55190e7e79.png', '2021-08-08 00:43:26', '2022-03-05 15:01:53', NULL, NULL);
INSERT INTO `sku` (`id`, `sku_sn`, `spu_id`, `name`, `price`, `stock_num`, `locked_stock_num`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (353, 'sn003', 1, '蓝 6+128g', 399900, 999, 0, 'http://a.youlai.tech:9000/default/aed7966ff68640f08d110f4fbcd1cdc2.png', '2022-03-05 09:25:53', '2022-03-05 15:01:53', NULL, NULL);
INSERT INTO `sku` (`id`, `sku_sn`, `spu_id`, `name`, `price`, `stock_num`, `locked_stock_num`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (354, 'sn004', 1, '蓝 8+256g', 499900, 998, 0, 'http://a.youlai.tech:9000/default/aed7966ff68640f08d110f4fbcd1cdc2.png', '2022-03-05 09:25:53', '2022-03-05 15:01:53', NULL, NULL);
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
  `pic_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '规格图片',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_pms_spu_attribute_pms_attr` (`name`) USING BTREE,
  KEY `fk_pms_spu_attribute_pms_spu` (`spu_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=217 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='商品属性项表';

-- ----------------------------
-- Records of sku_attribute_value
-- ----------------------------
BEGIN;
INSERT INTO `sku_attribute_value` (`id`, `spu_id`, `sku_id`, `attribute_id`, `name`, `value`, `type`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1, 1, 0, 34, '颜色', '黑', 1, 'http://a.youlai.tech:9000/default/6759b824e6d04af69f6f3e55190e7e79.png', NULL, '2022-03-05 15:01:53', NULL, NULL);
INSERT INTO `sku_attribute_value` (`id`, `spu_id`, `sku_id`, `attribute_id`, `name`, `value`, `type`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (3, 1, 0, 35, '规格', '6+128g', 1, NULL, NULL, '2022-03-05 15:01:53', NULL, NULL);
INSERT INTO `sku_attribute_value` (`id`, `spu_id`, `sku_id`, `attribute_id`, `name`, `value`, `type`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (4, 1, 0, 35, '规格', '8+256g', 1, NULL, NULL, '2022-03-05 15:01:53', NULL, NULL);
INSERT INTO `sku_attribute_value` (`id`, `spu_id`, `sku_id`, `attribute_id`, `name`, `value`, `type`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (5, 1, 0, 36, '上市时间', '2021-07-17', 2, NULL, NULL, '2022-03-05 15:01:53', NULL, NULL);
INSERT INTO `sku_attribute_value` (`id`, `spu_id`, `sku_id`, `attribute_id`, `name`, `value`, `type`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (97, 68, 0, NULL, '颜色', '4123', 1, NULL, '2021-08-07 23:38:50', '2021-08-07 23:38:50', NULL, NULL);
INSERT INTO `sku_attribute_value` (`id`, `spu_id`, `sku_id`, `attribute_id`, `name`, `value`, `type`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (98, 68, 0, NULL, '规格', '456', 1, NULL, '2021-08-07 23:38:50', '2021-08-07 23:38:50', NULL, NULL);
INSERT INTO `sku_attribute_value` (`id`, `spu_id`, `sku_id`, `attribute_id`, `name`, `value`, `type`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (99, 68, 0, NULL, '规格', '123', 1, NULL, '2021-08-07 23:38:50', '2021-08-07 23:38:50', NULL, NULL);
INSERT INTO `sku_attribute_value` (`id`, `spu_id`, `sku_id`, `attribute_id`, `name`, `value`, `type`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (100, 69, 0, NULL, '上市时间', '123', 2, NULL, '2021-08-07 23:41:44', '2021-08-07 23:41:44', NULL, NULL);
INSERT INTO `sku_attribute_value` (`id`, `spu_id`, `sku_id`, `attribute_id`, `name`, `value`, `type`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (101, 69, 0, NULL, '颜色', '123', 1, NULL, '2021-08-07 23:41:44', '2021-08-07 23:41:44', NULL, NULL);
INSERT INTO `sku_attribute_value` (`id`, `spu_id`, `sku_id`, `attribute_id`, `name`, `value`, `type`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (102, 69, 0, NULL, '颜色', '4123', 1, NULL, '2021-08-07 23:41:44', '2021-08-07 23:41:44', NULL, NULL);
INSERT INTO `sku_attribute_value` (`id`, `spu_id`, `sku_id`, `attribute_id`, `name`, `value`, `type`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (103, 69, 0, NULL, '规格', '456', 1, NULL, '2021-08-07 23:41:44', '2021-08-07 23:41:44', NULL, NULL);
INSERT INTO `sku_attribute_value` (`id`, `spu_id`, `sku_id`, `attribute_id`, `name`, `value`, `type`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (104, 69, 0, NULL, '规格', '123', 1, NULL, '2021-08-07 23:41:45', '2021-08-07 23:41:45', NULL, NULL);
INSERT INTO `sku_attribute_value` (`id`, `spu_id`, `sku_id`, `attribute_id`, `name`, `value`, `type`, `pic_url`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (216, 1, 0, NULL, '颜色', '蓝', 1, 'http://a.youlai.tech:9000/default/aed7966ff68640f08d110f4fbcd1cdc2.png', '2022-03-05 09:25:53', '2022-03-05 15:01:53', NULL, NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='商品表';

-- ----------------------------
-- Records of spu
-- ----------------------------
BEGIN;
INSERT INTO `spu` (`id`, `name`, `category_id`, `brand_id`, `origin_price`, `price`, `sales`, `pic_url`, `album`, `unit`, `description`, `detail`, `status`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1, '小米12 PRO', 5, 1, 599900, 599900, 1, 'http://a.youlai.tech:9000/default/1fb5d61cae2c4831a6122ca9ec747624.png', '[\"https://gitee.com/haoxr/image/raw/master/default/image-20210702002909113.png\", \"https://gitee.com/haoxr/image/raw/master/default/image-20210702002909113.png\"]', '台', '好快,好稳,\n好一次强上加强。\n高通全新一代芯片赋能，速度大幅提升。\n三大专业主摄影像加持，能力全面进化。\n大师级设计理念新诠释，质感简而不凡。\n斩获十五项纪录旗舰屏，感官万般出众。', '<p>123123<img src=\"http://a.youlai.tech:9000/default/1a69357664c24962ac23953905c3c38f.png\" alt=\"\" data-href=\"\" style=\"width: 449.00px;height: 449.00px;\"/></p>', 1, NULL, '2022-03-05 15:01:53', NULL, NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=217 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='商品属性项表';

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
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
