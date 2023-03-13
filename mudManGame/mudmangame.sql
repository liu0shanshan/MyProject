/*
Navicat MySQL Data Transfer

Source Server         : localhost_3307
Source Server Version : 50562
Source Host           : localhost:3307
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50562
File Encoding         : 65001

Date: 2022-06-04 13:04:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for mudmangame
-- ----------------------------
DROP TABLE IF EXISTS `mudmangame`;
CREATE TABLE `mudmangame` (
  `number` int(11) NOT NULL AUTO_INCREMENT,
  `level` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`number`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mudmangame
-- ----------------------------
INSERT INTO `mudmangame` VALUES ('1', 'C');
INSERT INTO `mudmangame` VALUES ('2', 'C');
INSERT INTO `mudmangame` VALUES ('3', 'C');
