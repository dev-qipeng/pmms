/*
Navicat MySQL Data Transfer
Source Host     : localhost:3306
Source Database : pmms
Target Host     : localhost:3306
Target Database : pmms
Date: 2017-04-14 21:23:00
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for date
-- ----------------------------
DROP TABLE IF EXISTS `date`;
CREATE TABLE `date` (
  `userName` varchar(30) NOT NULL,
  `date` varchar(30) NOT NULL,
  `thing` varchar(255) NOT NULL,
  PRIMARY KEY (`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of date
-- ----------------------------
INSERT INTO `date` VALUES ('123', '2016-10-23', '早上第');
INSERT INTO `date` VALUES ('123', '2016-10-25', '早点睡觉早点睡觉啊');
INSERT INTO `date` VALUES ('123', '2016-10-26', '早点起来吃饭');
INSERT INTO `date` VALUES ('123', '2017-3-27', '下午一点四十去上课');

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
  `userName` varchar(30) NOT NULL,
  `title` varchar(30) NOT NULL,
  `name` varchar(30) NOT NULL,
  `contentType` varchar(30) NOT NULL,
  `size` varchar(30) NOT NULL,
  `filePath` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of file
-- ----------------------------
INSERT INTO `file` VALUES ('123', '自行车', 'ic.jpg', 'image/jpeg', '7685', 'C:UsersDELL001Desktopworkspace.metadata.pluginsorg.eclipse.wst.server.core	mp0wtpwebappsPMMSfileuploadic.jpg');

-- ----------------------------
-- Table structure for friends
-- ----------------------------
DROP TABLE IF EXISTS `friends`;
CREATE TABLE `friends` (
  `userName` varchar(30) NOT NULL,
  `name` varchar(30) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `email` varchar(30) NOT NULL,
  `workplace` varchar(30) NOT NULL,
  `place` varchar(30) NOT NULL,
  `QQ` varchar(10) NOT NULL,
  PRIMARY KEY (`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of friends
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userName` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `name` varchar(30) NOT NULL,
  `sex` varchar(2) NOT NULL,
  `birth` varchar(10) NOT NULL,
  `nation` varchar(10) NOT NULL,
  `edu` varchar(10) NOT NULL,
  `work` varchar(30) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `place` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  PRIMARY KEY (`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('123', '123', '123', '男', '2014-11-11', '123', '硕士', '教师', '123', '123', '123');
