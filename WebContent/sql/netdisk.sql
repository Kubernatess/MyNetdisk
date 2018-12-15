/*
Navicat MySQL Data Transfer

Source Server         : 172.16.188.86
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : netdisk

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-12-16 00:30:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for list
-- ----------------------------
DROP TABLE IF EXISTS `list`;
CREATE TABLE `list` (
  `date` datetime NOT NULL,
  `size` double NOT NULL,
  `filename` varchar(255) NOT NULL,
  `username` varchar(20) NOT NULL,
  PRIMARY KEY (`filename`,`username`),
  KEY `FK_username` (`username`),
  CONSTRAINT `FK_username` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of list
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  `telephone` char(11) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('docker', '122112117247_lum', null, null);
INSERT INTO `user` VALUES ('hadoop', '302203123456', '', '2337590994@qq.com');
INSERT INTO `user` VALUES ('kubernetes', '1234567890123456', null, null);
INSERT INTO `user` VALUES ('OpenStack', '502717', '', '');
