/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.65.129-docker
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : 192.168.65.129:3306
 Source Schema         : jimureport

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 19/01/2022 08:39:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for lims_temp_info
-- ----------------------------
DROP TABLE IF EXISTS `lims_temp_info`;
CREATE TABLE `lims_temp_info`  (
  `id` int NOT NULL COMMENT '主键id',
  `temp_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '模板存放路径',
  `temp_sql` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '模板预填数据脚本',
  `temp_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '模板类型：文本、表格、图标、图片',
  `labor` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '模板标签',
  `task_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务id',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Table structure for lims_temp_data
-- ----------------------------
DROP TABLE IF EXISTS `lims_temp_data`;
CREATE TABLE `lims_temp_data`  (
  `id` int NOT NULL,
  `temp_data` json NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
