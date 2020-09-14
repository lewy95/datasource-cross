/*
 Navicat Premium Data Transfer

 Source Server         : mysql_5.7
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : dscross

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 14/09/2020 14:48:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_b_country
-- ----------------------------
DROP TABLE IF EXISTS `t_b_country`;
CREATE TABLE "t_b_country" (
  "country_id" smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  "country" varchar(50) NOT NULL,
  "last_update" timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY ("country_id") USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for t_b_datasource
-- ----------------------------
DROP TABLE IF EXISTS `t_b_datasource`;
CREATE TABLE "t_b_datasource" (
  "datasource_id" varchar(40) NOT NULL COMMENT '数据源ID',
  "datasource_name" varchar(100) DEFAULT NULL COMMENT '数据源名称',
  "datasource_scope" int(11) DEFAULT '1' COMMENT '数据源规模（1.常规 2.集群）',
  "datasource_desc" varchar(500) DEFAULT NULL COMMENT '数据源描述',
  "datasource_type" varchar(1) DEFAULT NULL COMMENT '数据源类型（1：业务数据源  2：元数据源）',
  "database_label" varchar(50) DEFAULT NULL COMMENT '数据库名称',
  "database_ip" varchar(20) DEFAULT NULL COMMENT '连接ip',
  "database_port" varchar(5) DEFAULT NULL COMMENT '连接端口',
  "database_url" varchar(500) DEFAULT NULL COMMENT '连接url',
  "database_type" varchar(2) DEFAULT NULL COMMENT '数据库类型',
  "database_username" varchar(50) DEFAULT NULL COMMENT '数据库账户',
  "database_password" varchar(300) DEFAULT NULL COMMENT '数据库密码',
  "presto_name" varchar(100) DEFAULT NULL COMMENT '该数据源在presto上对应的名称',
  "config_meta_flag" varchar(1) DEFAULT NULL COMMENT '是否配置元数据源（1：是 2：否）',
  "meta_ds_id" varchar(40) DEFAULT NULL COMMENT '元数据源ID',
  "status" int(11) DEFAULT NULL COMMENT '数据源状态(-2.异常(断开) -1.停用 0.暂存 1.启用)',
  "create_time" datetime DEFAULT NULL COMMENT '创建时间',
  "update_time" datetime DEFAULT NULL COMMENT '最近更新时间',
  PRIMARY KEY ("datasource_id") USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for t_t_sharding_0
-- ----------------------------
DROP TABLE IF EXISTS `t_t_sharding_0`;
CREATE TABLE "t_t_sharding_0" (
  "id" int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  "phone" varchar(20) NOT NULL COMMENT '手机号',
  "back_one" varchar(50) DEFAULT NULL COMMENT '备用1',
  "back_two" varchar(50) DEFAULT NULL COMMENT '备用2',
  "back_three" varchar(50) DEFAULT NULL COMMENT '备用3',
  PRIMARY KEY ("id"),
  KEY "phoneIndex" ("phone")
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='表1';

-- ----------------------------
-- Table structure for t_t_sharding_1
-- ----------------------------
DROP TABLE IF EXISTS `t_t_sharding_1`;
CREATE TABLE "t_t_sharding_1" (
  "id" int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  "phone" varchar(20) NOT NULL COMMENT '手机号',
  "back_one" varchar(50) DEFAULT NULL COMMENT '备用1',
  "back_two" varchar(50) DEFAULT NULL COMMENT '备用2',
  "back_three" varchar(50) DEFAULT NULL COMMENT '备用3',
  PRIMARY KEY ("id"),
  KEY "phoneIndex" ("phone")
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='表1';

-- ----------------------------
-- Table structure for t_t_sharding_club_0
-- ----------------------------
DROP TABLE IF EXISTS `t_t_sharding_club_0`;
CREATE TABLE "t_t_sharding_club_0" (
  "club_id" varchar(40) NOT NULL COMMENT '主键ID',
  "name" varchar(50) NOT NULL COMMENT '名称',
  "nation" varchar(50) DEFAULT NULL COMMENT '所在国家',
  "city" varchar(50) DEFAULT NULL COMMENT '所在城市',
  "champion_time" int(10) DEFAULT NULL COMMENT '夺冠次数',
  PRIMARY KEY ("club_id") USING BTREE,
  KEY "phoneIndex" ("name") USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='表2';

-- ----------------------------
-- Table structure for t_t_sharding_club_1
-- ----------------------------
DROP TABLE IF EXISTS `t_t_sharding_club_1`;
CREATE TABLE "t_t_sharding_club_1" (
  "club_id" varchar(40) NOT NULL COMMENT '主键ID',
  "name" varchar(50) NOT NULL COMMENT '名称',
  "nation" varchar(50) DEFAULT NULL COMMENT '所在国家',
  "city" varchar(50) DEFAULT NULL COMMENT '所在城市',
  "champion_time" int(10) DEFAULT NULL COMMENT '夺冠次数',
  PRIMARY KEY ("club_id") USING BTREE,
  KEY "phoneIndex" ("name") USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='表2';

-- ----------------------------
-- Table structure for t_t_sharding_player_0
-- ----------------------------
DROP TABLE IF EXISTS `t_t_sharding_player_0`;
CREATE TABLE "t_t_sharding_player_0" (
  "player_id" varchar(40) NOT NULL COMMENT '主键ID',
  "name" varchar(100) DEFAULT NULL COMMENT '姓名',
  "age" int(5) DEFAULT NULL COMMENT '年龄',
  "number" int(5) DEFAULT NULL COMMENT '号码',
  "nation" varchar(50) DEFAULT NULL COMMENT '国籍',
  "position" varchar(20) DEFAULT NULL COMMENT '位置',
  PRIMARY KEY ("player_id") USING BTREE,
  KEY "phoneIndex" ("age") USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='表2';

-- ----------------------------
-- Table structure for t_t_sharding_player_1
-- ----------------------------
DROP TABLE IF EXISTS `t_t_sharding_player_1`;
CREATE TABLE "t_t_sharding_player_1" (
  "player_id" varchar(40) NOT NULL COMMENT '主键ID',
  "name" varchar(100) DEFAULT NULL COMMENT '姓名',
  "age" int(5) DEFAULT NULL COMMENT '年龄',
  "number" int(5) DEFAULT NULL COMMENT '号码',
  "nation" varchar(50) DEFAULT NULL COMMENT '国籍',
  "position" varchar(20) DEFAULT NULL COMMENT '位置',
  PRIMARY KEY ("player_id") USING BTREE,
  KEY "phoneIndex" ("age") USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='表2';

SET FOREIGN_KEY_CHECKS = 1;
