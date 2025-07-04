/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80022
Source Host           : 127.0.0.1:3306
Source Database       : beyond_platform_db

Target Server Type    : MYSQL
Target Server Version : 80022
File Encoding         : 65001

Date: 2024-12-23 18:56:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for app_category
-- ----------------------------
DROP TABLE IF EXISTS `app_category`;
CREATE TABLE `app_category` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `categoryCode` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '分类编码',
  `categoryName` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '分类名称',
  `parentId` bigint DEFAULT NULL COMMENT '父级节点id',
  `createdBy` bigint DEFAULT NULL COMMENT '创建者（来源于backend_user用户表的用户id）',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyBy` bigint DEFAULT NULL COMMENT '更新者（来源于backend_user用户表的用户id）',
  `modifyDate` datetime DEFAULT NULL COMMENT '最新更新时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=124 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='手游类别';

-- ----------------------------
-- Records of app_category
-- ----------------------------
INSERT INTO `app_category` VALUES ('1', 'ALL_GAME', '全部游戏', null, '1', '2016-08-12 18:11:47', null, null);
INSERT INTO `app_category` VALUES ('2', 'GAME_001', '休闲游戏', '1', '1', '2016-08-12 18:11:47', null, null);
INSERT INTO `app_category` VALUES ('3', 'GAME_002', '益智游戏', '1', '1', '2016-08-12 18:11:47', null, null);
INSERT INTO `app_category` VALUES ('4', 'GAME_003', '体育游戏', '1', '1', '2016-08-12 18:11:47', null, null);
INSERT INTO `app_category` VALUES ('5', 'GAME_004', '角色扮演', '1', '1', '2016-08-12 18:11:47', null, null);
INSERT INTO `app_category` VALUES ('115', 'GAME_004_01_02', '局域网', '28', '1', '2016-08-12 18:11:47', null, null);
INSERT INTO `app_category` VALUES ('48', 'GAME_001_02_02', '三国类', '20', '1', '2016-08-12 18:11:47', null, null);
INSERT INTO `app_category` VALUES ('52', 'GAME_002_01_02', '麻将', '22', '1', '2016-08-12 18:11:47', null, null);
INSERT INTO `app_category` VALUES ('53', 'GAME_002_01_03', '围棋', '22', '1', '2016-08-12 18:11:47', null, null);
INSERT INTO `app_category` VALUES ('54', 'GAME_002_02_01', '九宫格', '23', '1', '2016-08-12 18:11:47', null, null);
INSERT INTO `app_category` VALUES ('55', 'GAME_002_02_02', '杀手数独', '23', '1', '2016-08-12 18:11:47', null, null);
INSERT INTO `app_category` VALUES ('56', 'GAME_002_02_03', '梅花桩数独', '23', '1', '2016-08-12 18:11:47', null, null);
INSERT INTO `app_category` VALUES ('57', 'GAME_002_03_01', '车内逃脱', '24', '1', '2016-08-12 18:11:47', null, null);
INSERT INTO `app_category` VALUES ('19', 'GAME_001_01', '养成类', '2', '1', '2016-08-12 18:11:47', null, null);
INSERT INTO `app_category` VALUES ('20', 'GAME_001_02', '塔防类', '2', '1', '2016-08-12 18:11:47', null, null);
INSERT INTO `app_category` VALUES ('21', 'GAME_001_03', '消除类', '2', '1', '2016-08-12 18:11:47', null, null);
INSERT INTO `app_category` VALUES ('22', 'GAME_002_01', '棋牌类', '3', '1', '2016-08-12 18:11:47', null, null);
INSERT INTO `app_category` VALUES ('23', 'GAME_002_02', '数独类', '3', '1', '2016-08-12 18:11:47', null, null);
INSERT INTO `app_category` VALUES ('24', 'GAME_002_03', '密室逃脱类', '3', '1', '2016-08-12 18:11:47', null, null);
INSERT INTO `app_category` VALUES ('25', 'GAME_003_01', '球类', '4', '1', '2016-08-12 18:11:47', null, null);
INSERT INTO `app_category` VALUES ('26', 'GAME_003_02', '赛车类', '4', '1', '2016-08-12 18:11:47', null, null);
INSERT INTO `app_category` VALUES ('28', 'GAME_004_01', '射击类', '5', '1', '2016-08-12 18:11:47', null, null);
INSERT INTO `app_category` VALUES ('120', 'GAME_004_03_02', '多阵营', '42', '1', '2016-08-12 18:11:47', null, null);
INSERT INTO `app_category` VALUES ('119', 'GAME_004_03_01', '双阵营', '42', '1', '2016-08-12 18:11:47', null, null);
INSERT INTO `app_category` VALUES ('118', 'GAME_004_02_02', '3D', '41', '1', '2016-08-12 18:11:47', null, null);
INSERT INTO `app_category` VALUES ('117', 'GAME_004_02_01', '拳皇', '41', '1', '2016-08-12 18:11:47', null, null);
INSERT INTO `app_category` VALUES ('116', 'GAME_004_01_03', '广域网', '28', '1', '2016-08-12 18:11:47', null, null);
INSERT INTO `app_category` VALUES ('112', 'GAME_003_02_01', '汽车', '26', '1', '2016-08-12 18:11:47', null, null);
INSERT INTO `app_category` VALUES ('111', 'GAME_003_01_03', '排球', '25', '1', '2016-08-12 18:11:47', null, null);
INSERT INTO `app_category` VALUES ('110', 'GAME_003_01_02', '篮球', '25', '1', '2016-08-12 18:11:47', null, null);
INSERT INTO `app_category` VALUES ('109', 'GAME_003_01_01', '足球', '25', '1', '2016-08-12 18:11:47', null, null);
INSERT INTO `app_category` VALUES ('58', 'GAME_002_03_02', '房内逃脱', '24', '1', '2016-08-12 18:11:47', null, null);
INSERT INTO `app_category` VALUES ('41', 'GAME_004_02', '格斗类', '5', '1', '2016-08-12 18:11:47', null, null);
INSERT INTO `app_category` VALUES ('42', 'GAME_004_03', '策略对战', '5', '1', '2016-08-12 18:11:47', null, null);
INSERT INTO `app_category` VALUES ('43', 'GAME_004_04', '冒险升级', '5', '1', '2016-08-12 18:11:47', null, null);
INSERT INTO `app_category` VALUES ('44', 'GAME_001_01_01', '庄园类', '19', '1', '2016-08-12 18:11:47', null, null);
INSERT INTO `app_category` VALUES ('45', 'GAME_001_01_02', '荒岛类', '19', '1', '2016-08-12 18:11:47', null, null);
INSERT INTO `app_category` VALUES ('46', 'GAME_001_01_03', '经营类', '19', '1', '2016-08-12 18:11:47', null, null);
INSERT INTO `app_category` VALUES ('47', 'GAME_001_02_01', '丧尸类', '20', '1', '2016-08-12 18:11:47', null, null);
INSERT INTO `app_category` VALUES ('49', 'GAME_001_03_01', '同色消除', '21', '1', '2016-08-12 18:11:47', null, null);
INSERT INTO `app_category` VALUES ('50', 'GAME_001_03_02', '连线消除', '21', '1', '2016-08-12 18:11:47', null, null);
INSERT INTO `app_category` VALUES ('51', 'GAME_002_01_01', '象棋', '22', '1', '2016-08-12 18:11:47', null, null);
INSERT INTO `app_category` VALUES ('114', 'GAME_004_01_01', '单机', '28', '1', '2016-08-12 18:11:47', null, null);
INSERT INTO `app_category` VALUES ('113', 'GAME_003_02_02', '摩托车', '26', '1', '2016-08-12 18:11:47', null, null);
INSERT INTO `app_category` VALUES ('121', 'GAME_004_03_03', '5V5', '42', '1', '2016-08-12 18:11:47', null, null);
INSERT INTO `app_category` VALUES ('122', 'GAME_004_04_01', '单机', '43', '1', '2016-08-12 18:11:47', null, null);
INSERT INTO `app_category` VALUES ('123', 'GAME_004_04_01', '广域网', '43', '11', '2016-08-12 18:11:47', null, null);
INSERT INTO `app_category` VALUES ('59', 'GAME_002_03_03', '其他场景', '24', '1', '2016-08-12 18:11:47', null, null);

-- ----------------------------
-- Table structure for app_info
-- ----------------------------
DROP TABLE IF EXISTS `app_info`;
CREATE TABLE `app_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `softwareName` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '软件名称',
  `apkName` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'APK名称（唯一）',
  `supportROM` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '支持ROM',
  `interfaceLanguage` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '界面语言',
  `softwareSize` decimal(20,2) DEFAULT NULL COMMENT '软件大小（单位：M）',
  `updateDate` date DEFAULT NULL COMMENT '更新日期',
  `devId` bigint DEFAULT NULL COMMENT '开发者id（来源于：dev_user表的开发者id）',
  `appInfo` varchar(5000) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '应用简介',
  `status` bigint DEFAULT NULL COMMENT '状态（来源于：data_dictionary，1 待审核 2 审核通过 3 审核不通过 4 已上架 5 已下架）',
  `onSaleDate` datetime DEFAULT NULL COMMENT '上架时间',
  `offSaleDate` datetime DEFAULT NULL COMMENT '下架时间',
  `flatformId` bigint DEFAULT NULL COMMENT '所属平台（来源于：data_dictionary，1 手机 2 平板 3 通用）',
  `categoryLevel3` bigint DEFAULT NULL COMMENT '所属三级分类（来源于：data_dictionary）',
  `downloads` bigint DEFAULT NULL COMMENT '下载量（单位：次）',
  `createdBy` bigint DEFAULT NULL COMMENT '创建者（来源于dev_user开发者信息表的用户id）',
  `creationDate` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyBy` bigint DEFAULT NULL COMMENT '更新者（来源于dev_user开发者信息表的用户id）',
  `modifyDate` datetime DEFAULT NULL COMMENT '最新更新时间',
  `categoryLevel1` bigint DEFAULT NULL COMMENT '所属一级分类（来源于：data_dictionary）',
  `categoryLevel2` bigint DEFAULT NULL COMMENT '所属二级分类（来源于：data_dictionary）',
  `logoPicPath` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'LOGO图片url路径',
  `logoLocPath` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'LOGO图片的服务器存储路径',
  `versionId` bigint DEFAULT NULL COMMENT '最新的版本id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of app_info
-- ----------------------------
INSERT INTO `app_info` VALUES ('48', '劲乐团U:O2Jam U', 'com.momocorp.o2jamu', '2.3及更高版本', '英文软件', '56.00', null, '1', '劲乐团U O2Jam U是一款音乐节拍游戏，跟着音乐的节奏点击屏幕即可，就是跳舞机，需要网络支持。\r\n注意：部分机型会卡死在启动界面或fc，结束进程后重新开启游戏就正常了。', '1', null, null, '3', '38', '1000', '1', '2016-08-22 11:43:02', null, null, '2', '19', '/BeyondPlatform/uploadfiles/com.momocorp.o2jamu.jpg', 'D:\\soft\\apache-tomcat-9.1.4\\webapps\\BeyondPlatform\\statics\\uploadfiles\\com.momocorp.o2jamu.jpg', null);
INSERT INTO `app_info` VALUES ('49', '塔防名将传', 'com.lbe.security', '2.2及更高版本', '简体中文', '9.00', null, '1', '三国塔防名将传是一款手机上的角色扮演类手机游戏，畅爽游戏新激情带来独特的刺激新玩法你在游戏当中也会变得愈加强大，拥有更为强悍的战技多样的冒险等待玩家在游戏里不断的去探索爽快经典优秀的游戏新内容缔造极致', '1', null, null, '1', '48', '2000', '1', '2016-08-22 11:47:11', null, null, '1', '3', '/BeyondPlatform/statics/uploadfiles/com.lbe.security2.jpg', 'D:\\soft\\apache-tomcat-9.1.4\\webapps\\BeyondPlatform\\statics\\uploadfiles\\com.lbe.security2.jpg', null);
INSERT INTO `app_info` VALUES ('50', '和平精英', 'com.sp.protector.free', '2.3及更高版本', '简体中文', '3.00', null, '2', '《和平精英》采用虚幻4引擎研发，致力于从画面、地图、射击手感等多个层面，为玩家全方位打造出极具真实感的军事竞赛体验。 ', '1', null, null, '1', '116', '5000', '1', '2016-08-22 11:49:12', null, null, '1', '3', '/BeyondPlatform/statics/uploadfiles/com.sp.protector.free2.jpg', 'D:\\soft\\apache-tomcat-9.1.4\\webapps\\BeyondPlatform\\statics\\uploadfiles\\com.sp.protector.free2.jpg', null);
INSERT INTO `app_info` VALUES ('51', '开心消消乐', 'com.google.android.inputmethod.pinyin', '4.2及更高版本', '简体中文', '16.00', null, '1', '《开心消消乐》是一款三消游戏，游戏画面精美、上手简单。玩家只需滑动手指让三个及以上的同色小动物横竖相连即可消除，完成每关的指定消除目标就可以过关。小动物的滑动还会触发很多神奇的效果，比如在四连直线和横线特效相邻时，两个互相拖动，两者同时触发，构成十字架；四连直线可以产生爆炸特效，使横排或竖排四个目标同时清空。\r\n四连直线+爆炸特效，可构成竖着四排，同时清空！威力很强。\r\n\r\n四连横线+爆炸特效，横向四排同时清空，同样威力很强。\r\n\r\n爆炸特效+爆炸特效，爆炸特效的扩大，同样威力很强。\r\n\r\n但横于竖是随即的，全部化为爆炸特效。这个是很实用的特效，大家一定要牢记。\r\n\r\n五连+五连，极难出现。两者相邻相互拖动，直接清屏，这最后一个纯属机缘巧合，玩家也不要强求。', '1', null, null, '3', '49', '8000', '1', '2016-08-22 11:53:23', null, null, '1', '3', '/BeyondPlatform/statics/uploadfiles/com.google.android.inputmethod.pinying.jpg', 'D:\\soft\\apache-tomcat-9.1.4\\webapps\\BeyondPlatform\\statics\\uploadfiles\\com.google.android.inputmethod.pinying.jpg', '37');
INSERT INTO `app_info` VALUES ('52', 'QQ飞车手游', 'com.speedsoftware.rootexplorer', '2.3及更高版本', '简体中文', '3.00', null, '1', 'qq飞车手游赛车排行-完美兼容手游,高清大屏,完美画质,给你真实享受.超越手机高端运行,PC深度适配,游戏不卡顿,操控无压力!', '1', null, null, '1', '112', '2340', '1', '2016-08-22 11:55:14', null, null, '1', '3', '/BeyondPlatform/statics/uploadfiles/com.speedsoftware.rootexplorer2.jpg', 'D:\\soft\\apache-tomcat-9.1.4\\webapps\\BeyondPlatform\\statics\\uploadfiles\\com.speedsoftware.rootexplorer2.jpg', '38');
INSERT INTO `app_info` VALUES ('53', 'FIFA 15:终极队伍', 'com.katecca.screenofflockdonate', '2.2及更高版本', '简体中文', '1.00', null, '1', '《 FIFA 15：终极队伍 FIFA 15 Ultimate Team》是一款由巨头体育游戏公司EA推出的最新款足球体育游戏。游戏中由着真实球员，真实球队，真实联赛，让玩家不用出门也可以在足球场上一展雄风。欢迎 进入安卓智能手机和平板电脑上最真实的足球游戏。以全新的触摸操作感受每次传球和临门一脚的激动时刻。', '1', null, null, '1', '109', '20', '1', '2016-08-22 13:07:11', null, null, '1', '4', '/BeyondPlatform/statics/uploadfiles/com.katecca.screenofflockdonate3.jpg', 'D:\\soft\\apache-tomcat-9.1.4\\webapps\\BeyondPlatform\\statics\\uploadfiles\\com.katecca.screenofflockdonate3.jpg', null);
INSERT INTO `app_info` VALUES ('54', '复古传奇', 'com.plexnor.gravityscreenoffpro', '2.2及更高版本', '英文软件', '1.00', null, '1', '1', '1', null, null, '1', '58', '23', '1', '2016-08-22 13:08:24', '1', '2024-11-21 20:35:06', '3', '24', '090351d0e9ef4171ba1859224f6be203.jpg', 'D:\\soft\\apache-tomcat-9.1.4\\webapps\\BeyondPlatform\\statics\\uploadfiles\\com.plexnor.gravityscreenoffpro2.jpg', null);
INSERT INTO `app_info` VALUES ('55', '机械迷城:Machinarium', 'air.net.machinarium.Machinarium.GP', '2.3及更高版本', '英文软件', '11.00', null, '1', '机械迷城Machinarium是一款解密冒险游戏，玩家可以在城市里随意的走动，跟场景或是其他同样是机器人互动，了解他们的需求以及帮忙他们，一步一步往事件的核心探索。 ', '3', null, null, '1', '48', '277', '1', '2016-08-22 13:09:44', '1', '2024-11-21 20:34:37', '2', '20', '95da1998af7c43aa8ce3340497862ec4.jpg', 'D:\\soft\\apache-tomcat-9.1.4\\webapps\\BeyondPlatform\\statics\\uploadfiles\\air.net.machinarium.Machinarium.GP.jpg', '36');
INSERT INTO `app_info` VALUES ('56', 'Dont Starve饥荒', 'com.kleientertainment.doNotStarvePocket', '4.0.3及更高版本', '英文软件', '4.00', null, '1', 'Don`t Starve: Pocket Edition移动版将深受超过4百万玩家喜爱的热门PC游戏移植到了安卓平台。现在你可以随时随地进入充满科学和魔法元素的世界，体验原汁原味的荒野生存游戏！\r\n你扮演威尔逊，一位被困住并传送到神秘荒野世界的勇敢无畏的绅士科学家。如果威尔逊希望逃出生天并找到回家的路，他必须充分利用所处的环境及这里的各种生物。\r\n进入一个光怪陆离而未经探索的世界，这里充满了奇怪的生物以及形形色色的危险和意外。收集资源以制作符合你的生存风格的物品和建筑。一路解开这块奇怪大陆上的各种谜团。', '5', null, '2016-08-22 13:28:16', '2', '45', '390', '1', '2016-08-22 13:11:51', '1', '2024-12-11 15:55:13', '2', '19', '6038888a6c404de6af951c8510c572b6.jpg', 'D:\\soft\\apache-tomcat-9.1.4\\webapps\\BeyondPlatform\\statics\\uploadfiles\\com.kleientertainment.doNotStarvePocket.jpg', '42');
INSERT INTO `app_info` VALUES ('57', '机械世界:Apparatus', 'com.bithack.apparatus', '1.6及更高版本', '英文软件', '11.00', null, '1', '在机械世界Apparatus游戏中你需要利用木板、钉子、绳索、圆轮等等各种道具搭建从简单到复杂的机械结构，使得小球可以最终滚落到蓝色方框中。可以利用的可不止重力，包括杠杆原理、滑轮等等，尤其是后面的关卡，你需要足够灵活的头脑才能解决问题，可玩性还是非常高的。', '3', null, null, '1', '52', '255', '1', '2016-08-22 13:13:27', '1', '2024-12-03 19:48:16', '3', '22', 'c6d0898ecb364a3c933f5a5a9234b623.png', 'D:\\soft\\apache-tomcat-9.1.4\\webapps\\BeyondPlatform\\statics\\uploadfiles\\com.bithack.apparatus.jpg', '41');
INSERT INTO `app_info` VALUES ('58', '沙盘玩具:The Powder Toy', 'com.doodleapps.powdertoy', '2.2及更高版本', '英文软件', '1.00', null, '1', '沙盘玩具The Powder Toy是一款模拟类游戏。想过自己建造核电站吗？或者自己做一个CPU？你甚至可以创建一个虚拟网络。沙盘玩具可以模拟空气的压力，速度，热，重力和无数的不同物质之间的相互作用，游戏提供各种形态的建材，用以构建复杂的机器或是电子元件，你可以模拟超酷的爆炸或是布线出复杂的机器人。欢迎提交您的作品。', '5', null, '2024-11-21 20:31:33', '3', '45', '2000', '1', '2016-08-22 13:14:56', '1', '2024-12-11 15:55:07', '2', '20', 'f7f6839bf5c84cafb3e774698ec6f7c6.jpg', 'D:\\soft\\apache-tomcat-9.1.4\\webapps\\BeyondPlatform\\statics\\uploadfiles\\com.doodleapps.powdertoy.jpg', '40');
INSERT INTO `app_info` VALUES ('59', '王者荣耀', 'com.timi-wangzherongyao', '2.2及更高版本', '中文软件', '2666.00', null, '1', '一款5V5的公平竞技手游', '5', '2024-11-20 14:43:51', '2024-11-20 14:43:54', '3', '44', '1000', '1', '2020-03-11 18:05:50', '1', '2024-12-11 15:54:53', '2', '19', 'a687747124944ae38804e97a4a6ffd58.png', 'D:\\soft\\apache-tomcat-9.1.4\\webapps\\BeyondPlatform\\statics\\uploadfiles\\com.timi-wangzherongyao.png', '43');
INSERT INTO `app_info` VALUES ('86', 'yyyy', 'com.lbe.security123', null, null, null, null, null, null, null, null, null, '1', '48', '10', null, null, null, '2024-12-12 21:26:35', '2', '20', '4030983230794beaa3d1661073d3988f.png', null, null);
INSERT INTO `app_info` VALUES ('88', 'zzzz', 'dfesfe', null, null, null, '2024-12-17', null, null, '1', null, null, '2', '46', null, null, '2024-12-16 16:56:16', null, '2024-12-17 15:06:14', '2', '19', 'a4786d956df047a6996658d52fae5a80.png', null, null);
INSERT INTO `app_info` VALUES ('89', 'zhangsan2', '2432423', null, null, null, '2024-12-18', null, null, '1', null, null, '2', '51', null, null, '2024-12-16 16:56:41', null, '2024-12-18 14:58:20', '3', '22', '9f2c8a7201d44c3a8d4e9e8ea4b6cd8d.jpg', null, null);

-- ----------------------------
-- Table structure for app_version
-- ----------------------------
DROP TABLE IF EXISTS `app_version`;
CREATE TABLE `app_version` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `appId` bigint DEFAULT NULL COMMENT 'appId（来源于：app_info表的主键id）',
  `versionNo` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '版本号',
  `versionInfo` varchar(2000) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '版本介绍',
  `publishStatus` bigint DEFAULT NULL COMMENT '发布状态（来源于：data_dictionary，1 不发布 2 已发布 3 预发布）',
  `downloadLink` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '下载链接',
  `versionSize` decimal(20,2) DEFAULT NULL COMMENT '版本大小（单位：M）',
  `createdBy` bigint DEFAULT NULL COMMENT '创建者（来源于dev_user开发者信息表的用户id）',
  `creationDate` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyBy` bigint DEFAULT NULL COMMENT '更新者（来源于dev_user开发者信息表的用户id）',
  `modifyDate` datetime DEFAULT NULL COMMENT '最新更新时间',
  `apkLocPath` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'apk文件的服务器存储路径',
  `apkFileName` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '上传的apk文件名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of app_version
-- ----------------------------
INSERT INTO `app_version` VALUES ('33', '58', 'V1.1.1', 'V1.1.1版本简介', '3', '/BeyondPlatform/statics/uploadfiles/com.doodleapps.powdertoy-V1.1.31.apk', '1.00', '1', '2016-08-22 13:17:47', null, null, 'D:\\soft\\apache-tomcat-7.0.41\\webapps\\BeyondPlatform\\statics\\uploadfiles\\com.doodleapps.powdertoy-V1.1.31.apk', 'com.doodleapps.powdertoy-V1.1.31.apk');
INSERT INTO `app_version` VALUES ('34', '57', 'V1.1.1', '              V1.1.1版本简介', '3', '/BeyondPlatform/statics/uploadfiles/com.bithack.apparatus-V1.1.1.apk', '11.00', '1', '2016-08-22 13:19:42', '1', '2016-08-22 13:19:54', 'D:\\soft\\apache-tomcat-7.0.41\\webapps\\BeyondPlatform\\statics\\uploadfiles\\com.bithack.apparatus-V1.1.1.apk', 'com.bithack.apparatus-V1.1.1.apk');
INSERT INTO `app_version` VALUES ('35', '56', 'V1.1.1', '              V1.1.1简介', '2', '/BeyondPlatform/statics/uploadfiles/com.kleientertainment.doNotStarvePocket-V1.1.1.apk', '4.00', '1', '2016-08-22 13:21:12', '1', '2016-08-22 13:28:07', 'D:\\soft\\apache-tomcat-7.0.41\\webapps\\BeyondPlatform\\statics\\uploadfiles\\com.kleientertainment.doNotStarvePocket-V1.1.1.apk', 'com.kleientertainment.doNotStarvePocket-V1.1.1.apk');
INSERT INTO `app_version` VALUES ('36', '55', 'V1.1.1', '              V1.1.1简介', '3', '/BeyondPlatform/statics/uploadfiles/air.net.machinarium.Machinarium.GP-V1.1.1.apk', '11.00', '1', '2016-08-22 13:21:40', '1', '2016-08-22 13:21:57', 'D:\\soft\\apache-tomcat-7.0.41\\webapps\\BeyondPlatform\\statics\\uploadfiles\\air.net.machinarium.Machinarium.GP-V1.1.1.apk', 'air.net.machinarium.Machinarium.GP-V1.1.1.apk');
INSERT INTO `app_version` VALUES ('37', '51', 'V1.1.1', 'V1.1.1简介', '3', '/BeyondPlatform/statics/uploadfiles/com.google.android.inputmethod.pinyin-V1.1.1.apk', '16.00', '1', '2016-08-22 13:24:07', null, null, 'D:\\soft\\apache-tomcat-7.0.41\\webapps\\BeyondPlatform\\statics\\uploadfiles\\com.google.android.inputmethod.pinyin-V1.1.1.apk', 'com.google.android.inputmethod.pinyin-V1.1.1.apk');
INSERT INTO `app_version` VALUES ('38', '52', 'V1.1.1', 'V1.1.1简介', '3', '/BeyondPlatform/statics/uploadfiles/com.speedsoftware.rootexplorer-V1.1.1.apk', '3.00', '1', '2016-08-22 13:24:35', null, null, 'D:\\soft\\apache-tomcat-7.0.41\\webapps\\BeyondPlatform\\statics\\uploadfiles\\com.speedsoftware.rootexplorer-V1.1.1.apk', 'com.speedsoftware.rootexplorer-V1.1.1.apk');
INSERT INTO `app_version` VALUES ('39', '58', 'V1.1.2', 'V1.1.2', '3', '/BeyondPlatform/statics/uploadfiles/com.doodleapps.powdertoy-V1.1.2.apk', '2.00', '1', '2016-08-22 13:26:15', null, null, 'D:\\soft\\apache-tomcat-7.0.41\\webapps\\BeyondPlatform\\statics\\uploadfiles\\com.doodleapps.powdertoy-V1.1.2.apk', 'com.doodleapps.powdertoy-V1.1.2.apk');
INSERT INTO `app_version` VALUES ('40', '58', 'V1.1.3', 'V1.1.3简介', '2', '/BeyondPlatform/statics/uploadfiles/com.doodleapps.powdertoy-V1.1.3.apk', '2.00', '1', '2016-08-22 13:26:47', '1', '2016-08-22 13:27:42', 'D:\\soft\\apache-tomcat-7.0.41\\webapps\\BeyondPlatform\\statics\\uploadfiles\\com.doodleapps.powdertoy-V1.1.3.apk', 'com.doodleapps.powdertoy-V1.1.3.apk');
INSERT INTO `app_version` VALUES ('41', '57', 'V1.1.2', '              V1.1.2简介', '3', '/BeyondPlatform/statics/uploadfiles/com.bithack.apparatus-V1.1.2.apk', '11.00', '1', '2016-08-22 13:27:32', '1', '2016-08-22 15:08:04', 'D:\\soft\\apache-tomcat-7.0.41\\webapps\\BeyondPlatform\\statics\\uploadfiles\\com.bithack.apparatus-V1.1.2.apk', 'com.bithack.apparatus-V1.1.2.apk');
INSERT INTO `app_version` VALUES ('42', '56', 'V1.1.2', 'V1.1.2简介', '3', '/BeyondPlatform/statics/uploadfiles/com.kleientertainment.doNotStarvePocket-V1.1.2.apk', '4.00', '1', '2016-08-22 15:00:32', null, null, 'D:\\soft\\apache-tomcat-7.0.41\\webapps\\BeyondPlatform\\statics\\uploadfiles\\com.kleientertainment.doNotStarvePocket-V1.1.2.apk', 'com.kleientertainment.doNotStarvePocket-V1.1.2.apk');
INSERT INTO `app_version` VALUES ('43', '59', 'v_3.4.11', '一款5V5的公平竞技手游', '3', '/BeyondPlatform/statics/uploadfiles/com.timi-wangzherongyao-v_3.4.11.apk', '2999.00', '1', '2020-03-11 18:09:51', '1', '2020-03-11 18:38:44', 'D:\\java\\tomcat\\apache-tomcat-9.0.14\\webapps\\BeyondPlatform\\statics\\uploadfiles\\com.timi-wangzherongyao-v_3.4.11.apk', 'com.timi-wangzherongyao-v_3.4.11.apk');
INSERT INTO `app_version` VALUES ('45', '59', 'wdw', 'dwq', null, '67380835fd4a4b52af54b7e8cd71f08a.png', '12.00', '1', '2024-11-18 10:35:39', null, null, null, null);
INSERT INTO `app_version` VALUES ('46', '59', 'sxewfce', 'dewcew', '3', null, '12.00', '1', '2024-11-18 14:46:01', null, null, null, null);
INSERT INTO `app_version` VALUES ('47', '59', '232', '23', '3', 'c09917e0e975491b8e83e9d794b6a915.apk', '321.00', '1', '2024-11-18 14:50:20', null, null, 'D:/upload/appinfo/apk/c09917e0e975491b8e83e9d794b6a915.apk', null);
INSERT INTO `app_version` VALUES ('48', '74', 'srteres', 'e32r', null, 'd3c39560b0c14271984710f6974f0987.apk', '12.00', '1', '2024-11-20 10:28:02', null, null, null, null);
INSERT INTO `app_version` VALUES ('51', '83', 'erwerw', 'r3rw', '3', '8dc28e65c6c24b478c8bf282545f72b0.apk', '12.00', '1', '2024-11-21 21:36:45', null, null, 'D:/upload/appinfo/apk/8dc28e65c6c24b478c8bf282545f72b0.apk', null);
INSERT INTO `app_version` VALUES ('52', '84', '3e2e43', '323', '3', '24026e213e8c4eb29cbb90e2110bc927.apk', '12.00', '1', '2024-11-21 22:00:30', null, null, 'D:/upload/appinfo/apk/24026e213e8c4eb29cbb90e2110bc927.apk', null);
INSERT INTO `app_version` VALUES ('54', '59', 'erw', '12', '3', 'd743b8117e0f47758b5bdb90f4d0eac5.apk', '12.00', null, '2024-12-04 23:45:07', null, null, null, null);
INSERT INTO `app_version` VALUES ('55', '59', 'erw', '12', '3', '64203bbab4d84e0a9952cb476c068314.apk', '12.00', null, '2024-12-04 23:46:38', null, null, null, null);
INSERT INTO `app_version` VALUES ('56', '59', '55555', '12', '3', '6e2dc110d2a642af8cdd6cd9796cca28.apk', '12.00', null, '2024-12-04 23:49:57', null, null, null, null);
INSERT INTO `app_version` VALUES ('57', '57', '23123', '12', '3', '23b5391cf4a74edfae99b558fd9db8d9.apk', '12.00', null, '2024-12-04 23:51:05', null, null, null, null);
INSERT INTO `app_version` VALUES ('58', '57', '5645645', '12', '3', '3d290df72f15445b920bb8175b32da27.apk', '12.00', null, '2024-12-04 23:51:24', null, null, null, null);
INSERT INTO `app_version` VALUES ('60', '59', 'erwerw11', '121', '3', null, '12.00', null, '2024-12-10 14:33:43', null, null, null, '424e28b934454984ba45f8a41ed3b9bb.apk');
INSERT INTO `app_version` VALUES ('61', '59', 'erwerw1112313', '121111', '3', null, '12.00', null, '2024-12-10 14:34:58', null, null, null, '971da0a062dc47d98ce82bbd208631dc.apk');
INSERT INTO `app_version` VALUES ('64', '86', 'xxx', '23', '3', 'e7c1f8215d5c426db8c0f63712b07c2e.apk', '12.00', null, '2024-12-11 15:33:13', null, null, null, null);
INSERT INTO `app_version` VALUES ('65', '86', '12', '132', '3', '6a4aa0a84c11441f863b19135e5c4621.apk', '121.00', null, '2024-12-12 21:14:01', null, null, null, null);
INSERT INTO `app_version` VALUES ('66', '86', '312', '12321', '3', '2a499935587a4a3ab7bb55e27adfb7b5.apk', '121.00', null, '2024-12-12 21:14:45', null, null, null, null);

-- ----------------------------
-- Table structure for backend_user
-- ----------------------------
DROP TABLE IF EXISTS `backend_user`;
CREATE TABLE `backend_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `userCode` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户编码',
  `userName` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户名称',
  `userType` bigint DEFAULT NULL COMMENT '用户角色类型（来源于数据字典表，分为：超管、财务、市场、运营、销售）',
  `createdBy` bigint DEFAULT NULL COMMENT '创建者（来源于backend_user用户表的用户id）',
  `creationDate` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyBy` bigint DEFAULT NULL COMMENT '更新者（来源于backend_user用户表的用户id）',
  `modifyDate` datetime DEFAULT NULL COMMENT '最新更新时间',
  `userPassword` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of backend_user
-- ----------------------------
INSERT INTO `backend_user` VALUES ('1', 'admin', '系统管理员', '1', '1', '2016-08-20 00:13:41', null, null, '123456');

-- ----------------------------
-- Table structure for data_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `data_dictionary`;
CREATE TABLE `data_dictionary` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `typeCode` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '类型编码',
  `typeName` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '类型名称',
  `valueId` bigint DEFAULT NULL COMMENT '类型值ID',
  `valueName` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '类型值Name',
  `createdBy` bigint DEFAULT NULL COMMENT '创建者（来源于backend_user用户表的用户id）',
  `creationDate` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyBy` bigint DEFAULT NULL COMMENT '更新者（来源于backend_user用户表的用户id）',
  `modifyDate` datetime DEFAULT NULL COMMENT '最新更新时间',
  `typeFlag` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=110 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of data_dictionary
-- ----------------------------
INSERT INTO `data_dictionary` VALUES ('1', 'USER_TYPE', '用户类型', '1', '超级管理员', '1', '2016-08-12 18:11:47', null, null, 'USER_TYPE_ADMIN');
INSERT INTO `data_dictionary` VALUES ('2', 'USER_TYPE', '用户类型', '2', '财务', '1', '2016-08-12 18:11:47', null, null, '');
INSERT INTO `data_dictionary` VALUES ('3', 'USER_TYPE', '用户类型', '3', '市场', '1', '2016-08-12 18:11:47', null, null, null);
INSERT INTO `data_dictionary` VALUES ('4', 'USER_TYPE', '用户类型', '4', '运营', '1', '2016-08-12 18:11:47', null, null, null);
INSERT INTO `data_dictionary` VALUES ('5', 'USER_TYPE', '用户类型', '5', '销售', '1', '2016-08-12 18:11:47', null, null, null);
INSERT INTO `data_dictionary` VALUES ('6', 'APP_STATUS', 'APP状态', '1', '待审核', '1', '2016-08-12 18:11:47', null, null, 'APP_STATUS_WAIT');
INSERT INTO `data_dictionary` VALUES ('7', 'APP_STATUS', 'APP状态', '2', '审核通过', '1', '2016-08-12 18:11:47', null, null, 'APP_STATUS_YES');
INSERT INTO `data_dictionary` VALUES ('8', 'APP_STATUS', 'APP状态', '3', '审核未通过', '1', '2016-08-12 18:11:47', null, null, 'APP_STATUS_NO');
INSERT INTO `data_dictionary` VALUES ('9', 'APP_STATUS', 'APP状态', '4', '已上架', '1', '2016-08-12 18:11:47', null, null, 'APP_STATUS_ON');
INSERT INTO `data_dictionary` VALUES ('10', 'APP_STATUS', 'APP状态', '5', '已下架', '1', '2016-08-12 18:11:47', null, null, 'APP_STATUS_OFF');
INSERT INTO `data_dictionary` VALUES ('11', 'APP_FLATFORM', '所属平台', '1', '手机', '1', '2016-08-12 18:11:47', null, null, null);
INSERT INTO `data_dictionary` VALUES ('12', 'APP_FLATFORM', '所属平台', '2', '平板', '1', '2016-08-12 18:11:47', null, null, null);
INSERT INTO `data_dictionary` VALUES ('14', 'PUBLISH_STATUS', '发布状态', '1', '不发布', '1', '2016-08-12 18:11:47', null, null, null);
INSERT INTO `data_dictionary` VALUES ('15', 'PUBLISH_STATUS', '发布状态', '2', '已发布', '1', '2016-08-12 18:11:47', null, null, null);
INSERT INTO `data_dictionary` VALUES ('16', 'PUBLISH_STATUS', '发布状态', '3', '预发布', '1', '2016-08-12 18:11:47', null, null, null);
INSERT INTO `data_dictionary` VALUES ('13', 'APP_FLATFORM', '所属平台', '3', '通用', '1', '2016-08-12 18:11:47', null, null, null);

-- ----------------------------
-- Table structure for dev_user
-- ----------------------------
DROP TABLE IF EXISTS `dev_user`;
CREATE TABLE `dev_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `devCode` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '开发者帐号',
  `devName` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '开发者名称',
  `devPassword` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '开发者密码',
  `devEmail` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '开发者电子邮箱',
  `devInfo` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '开发者简介',
  `createdBy` bigint DEFAULT NULL COMMENT '创建者（来源于backend_user用户表的用户id）',
  `creationDate` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyBy` bigint DEFAULT NULL COMMENT '更新者（来源于backend_user用户表的用户id）',
  `modifyDate` datetime DEFAULT NULL COMMENT '最新更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of dev_user
-- ----------------------------
INSERT INTO `dev_user` VALUES ('1', 'test001', '测试账户001', '123456', null, null, '1', '2024-08-18 00:13:41', null, null);
INSERT INTO `dev_user` VALUES ('2', 'test2', '测试用户2', '123456', null, null, '1', '2024-11-22 20:49:49', null, null);

-- ----------------------------
-- Table structure for normal_user
-- ----------------------------
DROP TABLE IF EXISTS `normal_user`;
CREATE TABLE `normal_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `userCode` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '普通用户帐号',
  `userName` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '普通用户名称',
  `userPassword` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '普通用户密码',
  `userEmail` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '普通用户电子邮箱',
  `userInfo` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '普通用户简介',
  `createdBy` bigint DEFAULT NULL COMMENT '创建者（来源于backend_user用户表的用户id）',
  `creationDate` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyBy` bigint DEFAULT NULL COMMENT '更新者（来源于backend_user用户表的用户id）',
  `modifyDate` datetime DEFAULT NULL COMMENT '最新更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for app_collection
-- ----------------------------
DROP TABLE IF EXISTS `app_collection`;
CREATE TABLE `app_collection` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint NOT NULL COMMENT '普通用户id',
  `app_id` bigint NOT NULL COMMENT 'app_info表id',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '收藏时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;