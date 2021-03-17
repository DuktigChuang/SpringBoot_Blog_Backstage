/*
 Navicat Premium Data Transfer

 Source Server         : LocalDataMysql
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : duktig_springboot

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 17/03/2021 20:19:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_admin_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_admin_user`;
CREATE TABLE `tb_admin_user` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `user_name` varchar(20) NOT NULL DEFAULT '' COMMENT '用户名',
  `password_md5` varchar(50) NOT NULL DEFAULT '' COMMENT '密码',
  `user_token` varchar(50) NOT NULL DEFAULT '' COMMENT 'token值',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否已删除 0未删除 1已删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_admin_user
-- ----------------------------
BEGIN;
INSERT INTO `tb_admin_user` VALUES (1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', '2e6528506bb48d46ea9e18c7cd00b162', 0, '2021-03-15 11:50:13');
INSERT INTO `tb_admin_user` VALUES (2, 'duktig', '25f9e794323b453885f5181f1b624d0b', '', 1, '2021-03-17 17:46:55');
INSERT INTO `tb_admin_user` VALUES (3, 'duktig', '25f9e794323b453885f5181f1b624d0b', '', 1, '2021-03-17 17:55:20');
INSERT INTO `tb_admin_user` VALUES (4, 'duktig', 'e10adc3949ba59abbe56e057f20f883e', '', 0, '2021-03-17 19:16:11');
COMMIT;

-- ----------------------------
-- Table structure for tb_article
-- ----------------------------
DROP TABLE IF EXISTS `tb_article`;
CREATE TABLE `tb_article` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `article_title` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '文章标题',
  `article_content` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '文章内容',
  `add_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '添加人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否已删除 0未删除 1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_article
-- ----------------------------
BEGIN;
INSERT INTO `tb_article` VALUES (1, 'hello', 'first', 'duktig', '2021-03-17 12:58:30', '2021-03-17 12:58:30', 0);
INSERT INTO `tb_article` VALUES (2, 'asfa', 'two', 'chuang', '2021-03-17 12:58:44', '2021-03-17 12:58:44', 1);
INSERT INTO `tb_article` VALUES (3, '新的征程', '<p>\n	Hello\n</p>', 'Acthor', '2021-03-17 16:58:58', '2021-03-17 16:58:58', 0);
INSERT INTO `tb_article` VALUES (4, 'Alitype', 'HAHA', 'Hahah', '2021-03-17 17:47:44', '2021-03-17 17:47:44', 0);
COMMIT;

-- ----------------------------
-- Table structure for tb_picture
-- ----------------------------
DROP TABLE IF EXISTS `tb_picture`;
CREATE TABLE `tb_picture` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `path` varchar(200) NOT NULL DEFAULT '' COMMENT '图片路径',
  `remark` varchar(200) NOT NULL DEFAULT '' COMMENT '备注',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否已删除 0未删除 1已删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_picture
-- ----------------------------
BEGIN;
INSERT INTO `tb_picture` VALUES (1, 'asasda', 'sdada', 1, '2021-03-17 12:59:07');
INSERT INTO `tb_picture` VALUES (2, 'ada', 'ada', 1, '2021-03-17 12:59:12');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
