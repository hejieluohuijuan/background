/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50527
 Source Host           : localhost:3306
 Source Schema         : hzw_bk

 Target Server Type    : MySQL
 Target Server Version : 50527
 File Encoding         : 65001

 Date: 12/09/2019 12:35:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for LOGIN_SWITCH_DATA
-- ----------------------------
DROP TABLE IF EXISTS `LOGIN_HEAD_IMG`;
CREATE TABLE `LOGIN_HEAD_IMG`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `IMG_PATH` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `DELETE_FLAG` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0',
  `CREATE_USER` int(255) NULL DEFAULT NULL,
  `UPDATE_USER` int(11) NULL DEFAULT NULL,
  `CREATE_TIME` timestamp(0) NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  `UPDATE_TIME` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

INSERT INTO `LOGIN_HEAD_IMG`(`ID`,`IMG_PATH`, `DELETE_FLAG`, `CREATE_USER`, `UPDATE_USER`, `CREATE_TIME`, `UPDATE_TIME`) VALUES ( 1,'images/headimg/head1.jpg', '0', 1, NULL, '2019-09-23 19:25:01',NULL);
SET FOREIGN_KEY_CHECKS = 1;

