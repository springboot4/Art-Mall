

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) DEFAULT NULL COMMENT '会员ID',
  `consignee_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '收货人姓名',
  `consignee_mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '收货人联系方式',
  `province` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '省',
  `city` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '市',
  `area` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '区',
  `detail_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '详细地址',
  `zip_code` char(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '邮编',
  `defaulted` tinyint(4) DEFAULT '0' COMMENT '是否默认地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1557378106906165250 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of address
-- ----------------------------
BEGIN;
INSERT INTO `address` (`id`, `member_id`, `consignee_name`, `consignee_mobile`, `province`, `city`, `area`, `detail_address`, `zip_code`, `defaulted`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (3, 4, '花韦', '13151572876', '上海', '南京市', '雨花台区', '1号', NULL, 1, NULL, NULL, NULL, NULL);
INSERT INTO `address` (`id`, `member_id`, `consignee_name`, `consignee_mobile`, `province`, `city`, `area`, `detail_address`, `zip_code`, `defaulted`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (4, 4, '花韦', '13151572876', '上海', '南京市', '雨花台区', '1号', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `address` (`id`, `member_id`, `consignee_name`, `consignee_mobile`, `province`, `city`, `area`, `detail_address`, `zip_code`, `defaulted`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (5, 4, 'niuniu', '13151572870', '上海', '上海市', '浦东新区', '1号路1栋', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `address` (`id`, `member_id`, `consignee_name`, `consignee_mobile`, `province`, `city`, `area`, `detail_address`, `zip_code`, `defaulted`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (6, 4, '123', '13150572890', '上海', '上海市', '浦东新区', '123', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `address` (`id`, `member_id`, `consignee_name`, `consignee_mobile`, `province`, `city`, `area`, `detail_address`, `zip_code`, `defaulted`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (7, 4, '123', '13157282800', '上海', '上海市', '浦东新区', '13157282800', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `address` (`id`, `member_id`, `consignee_name`, `consignee_mobile`, `province`, `city`, `area`, `detail_address`, `zip_code`, `defaulted`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (12, 21, '郝先瑞', '17621250365', '上海', '上海市', '浦东新区', '111111', NULL, NULL, '2021-03-22 21:56:58', '2021-03-22 21:56:58', NULL, NULL);
INSERT INTO `address` (`id`, `member_id`, `consignee_name`, `consignee_mobile`, `province`, `city`, `area`, `detail_address`, `zip_code`, `defaulted`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (13, 21, '测试地址', '17621498765', '上海', '上海市', '浦东新区', '111', NULL, NULL, '2021-03-22 21:59:25', '2021-03-22 21:59:25', NULL, NULL);
INSERT INTO `address` (`id`, `member_id`, `consignee_name`, `consignee_mobile`, `province`, `city`, `area`, `detail_address`, `zip_code`, `defaulted`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (14, 22, 'F', '15297828702', '上海', '上海市', '浦东新区', 'qqq', NULL, 1, '2021-03-22 22:12:15', '2021-03-22 22:12:15', NULL, NULL);
INSERT INTO `address` (`id`, `member_id`, `consignee_name`, `consignee_mobile`, `province`, `city`, `area`, `detail_address`, `zip_code`, `defaulted`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (15, 25, '完全', '13415418395', '上海', '上海市', '浦东新区', '钉钉', NULL, NULL, '2021-03-31 14:22:16', '2021-03-31 14:22:16', NULL, NULL);
INSERT INTO `address` (`id`, `member_id`, `consignee_name`, `consignee_mobile`, `province`, `city`, `area`, `detail_address`, `zip_code`, `defaulted`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (16, 31, '郝先瑞', '17612590365', '上海', '上海市', '浦东新区', '111', NULL, 1, '2021-04-14 23:37:46', '2021-04-14 23:37:46', NULL, NULL);
INSERT INTO `address` (`id`, `member_id`, `consignee_name`, `consignee_mobile`, `province`, `city`, `area`, `detail_address`, `zip_code`, `defaulted`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (17, 32, '张三', '13721071025', '上海', '上海市', '浦东新区', '春秋北路', NULL, 1, '2021-04-14 23:46:39', '2021-04-14 23:46:39', NULL, NULL);
INSERT INTO `address` (`id`, `member_id`, `consignee_name`, `consignee_mobile`, `province`, `city`, `area`, `detail_address`, `zip_code`, `defaulted`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (18, 34, 'zs', '13721071025', '上海', '上海市', '浦东新区', '江山大街', NULL, 1, '2021-04-20 01:00:49', '2021-06-04 00:57:59', NULL, NULL);
INSERT INTO `address` (`id`, `member_id`, `consignee_name`, `consignee_mobile`, `province`, `city`, `area`, `detail_address`, `zip_code`, `defaulted`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (19, 35, '秦忠旺', '15589569011', '上海', '烟台市', '莱山区', '天合城40号楼402', NULL, 1, '2021-04-21 10:05:09', '2021-04-21 10:05:09', NULL, NULL);
INSERT INTO `address` (`id`, `member_id`, `consignee_name`, `consignee_mobile`, `province`, `city`, `area`, `detail_address`, `zip_code`, `defaulted`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (20, 39, '郝先瑞', '17621590365', '上海', '上海市', '浦东新区', '111', NULL, 1, '2021-06-10 23:10:30', '2021-06-10 23:10:30', NULL, NULL);
INSERT INTO `address` (`id`, `member_id`, `consignee_name`, `consignee_mobile`, `province`, `city`, `area`, `detail_address`, `zip_code`, `defaulted`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1525386584467980289, 1, 'fxz', '15954561234', '山东省', '潍坊市', '奎文区', '山东省潍坊市奎文区潍坊学院', NULL, 0, '2022-05-14 16:04:37', '2022-05-14 16:34:16', 'fxz', 'fxz');
INSERT INTO `address` (`id`, `member_id`, `consignee_name`, `consignee_mobile`, `province`, `city`, `area`, `detail_address`, `zip_code`, `defaulted`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1525387143572897794, 1, 'fxzcloud', '15954561111', '山东省', '聊城市', '茌平县', '山东省聊城市茌平县', NULL, NULL, '2022-05-14 16:06:50', '2022-05-14 16:06:50', 'fxz', 'fxz');
INSERT INTO `address` (`id`, `member_id`, `consignee_name`, `consignee_mobile`, `province`, `city`, `area`, `detail_address`, `zip_code`, `defaulted`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1525387631097823234, 1, 'fxz', '17361271234', '山东省', '济南市', '市中区', '山东省济南市', NULL, NULL, '2022-05-14 16:08:46', '2022-05-14 16:08:46', 'fxz', 'fxz');
INSERT INTO `address` (`id`, `member_id`, `consignee_name`, `consignee_mobile`, `province`, `city`, `area`, `detail_address`, `zip_code`, `defaulted`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1525387632125427714, 1, 'fxz', '17361271234', '山东省', '济南市', '市中区', '山东省济南市', NULL, NULL, '2022-05-14 16:08:46', '2022-05-14 16:08:46', 'fxz', 'fxz');
INSERT INTO `address` (`id`, `member_id`, `consignee_name`, `consignee_mobile`, `province`, `city`, `area`, `detail_address`, `zip_code`, `defaulted`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1525387987609468930, 1, 'fxzz', '17361278901', '上海', '上海市', '浦东新区', '上海', NULL, 0, '2022-05-14 16:10:11', '2022-05-14 16:10:11', 'fxz', 'fxz');
INSERT INTO `address` (`id`, `member_id`, `consignee_name`, `consignee_mobile`, `province`, `city`, `area`, `detail_address`, `zip_code`, `defaulted`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1525389780309618689, 1, 'fxzz', '15954561221', '上海', '上海市', '浦东新区', '上海市', NULL, 1, '2022-05-14 16:17:19', '2022-05-14 16:34:22', 'fxz', 'fxz');
INSERT INTO `address` (`id`, `member_id`, `consignee_name`, `consignee_mobile`, `province`, `city`, `area`, `detail_address`, `zip_code`, `defaulted`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1557378106906165249, 1557373423881924609, '付绪壮', '17000000000', '北京', '北京市', '东城区', '北京', NULL, 1, '2022-08-10 22:47:30', '2022-08-10 22:47:30', 'fxz', 'fxz');
COMMIT;

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gender` tinyint(1) DEFAULT NULL,
  `nick_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '密码',
  `mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `openid` char(28) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `session_key` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `status` tinyint(1) DEFAULT '1',
  `point` int(11) DEFAULT '0' COMMENT '会员积分',
  `deleted` tinyint(1) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `balance` bigint(20) DEFAULT '1000000000',
  `city` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `country` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `language` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `province` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1557373423881924610 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of member
-- ----------------------------
BEGIN;
INSERT INTO `member` (`id`, `gender`, `nick_name`, `password`, `mobile`, `birthday`, `avatar_url`, `openid`, `session_key`, `status`, `point`, `deleted`, `create_time`, `update_time`, `balance`, `city`, `country`, `language`, `province`, `create_by`, `update_by`) VALUES (1557373423881924609, 0, 'fxz', NULL, '19806082431', NULL, 'https://thirdwx.qlogo.cn/mmopen/vi_32/Zn6eCC569kt1NicQchoR23fZiaF2eKccf4tliaxiaY3AgPthl8Ggeu5KriceaOJnpt2YJDickDlA6QjPD3PiazHSib6Viaw/132', 'oy6204tZJ7BK53Ue7InPr5Qq1V7A', NULL, 1, 0, 0, '2022-08-10 22:28:54', '2022-08-10 22:28:54', NULL, '', '', 'zh_CN', '', 'anonymousUser', 'anonymousUser');
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
