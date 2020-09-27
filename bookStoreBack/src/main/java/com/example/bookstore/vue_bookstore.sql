/*
 Navicat Premium Data Transfer

 Source Server         : book
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : vue_bookstore

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 12/05/2019 19:08:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for all_order
-- ----------------------------
DROP TABLE IF EXISTS `all_order`;
CREATE TABLE `all_order`  (
  `order_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `contact_id` int(11) NOT NULL,
  `order_time` datetime(0) NOT NULL,
  `order_status` int(1) NOT NULL,
  `order_allprice` double(10, 2) NOT NULL,
  `user_leave_mess` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mess_status` int(1) NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of all_order
-- ----------------------------
INSERT INTO `all_order` VALUES ('201903031111', '5801', 10, '2019-04-03 01:25:29', 2, 137.50, '尽快发货！', 1);
INSERT INTO `all_order` VALUES ('201903051111', '5801', 1, '2018-11-04 17:45:58', 4, 72.20, '尽快发货！', 1);
INSERT INTO `all_order` VALUES ('201903071111', '5801', 1, '2019-04-05 17:47:45', 3, 74.20, '尽快发货！', 1);
INSERT INTO `all_order` VALUES ('201903091111', '5801', 1, '2018-07-06 17:52:14', 4, 130.50, '尽快发货！', 1);
INSERT INTO `all_order` VALUES ('580120190502004250', '5801', 1, '2018-08-01 16:42:51', 4, 83.40, '', 1);
INSERT INTO `all_order` VALUES ('580120190502004530', '5801', 1, '2018-04-01 16:45:31', 2, 132.70, '尽快发货！', 1);
INSERT INTO `all_order` VALUES ('580120190502005746', '5801', 1, '2018-05-01 16:57:46', 2, 132.70, '', 1);
INSERT INTO `all_order` VALUES ('580120190502010048', '5801', 1, '2018-09-01 17:00:49', 2, 132.70, '', 1);
INSERT INTO `all_order` VALUES ('580120190502010130', '5801', 1, '2018-12-01 17:01:31', 4, 132.70, '', 1);
INSERT INTO `all_order` VALUES ('580120190502010347', '5801', 1, '2019-04-01 17:03:48', 2, 132.70, '', 1);
INSERT INTO `all_order` VALUES ('580120190502010700', '5801', 2, '2019-02-01 17:07:01', 2, 132.70, '', 1);
INSERT INTO `all_order` VALUES ('580120190502013529', '5801', 16, '2019-03-03 17:35:30', 2, 103.70, '', 1);
INSERT INTO `all_order` VALUES ('580120190502014900', '5801', 16, '2018-06-01 17:49:01', 2, 65.90, '6666', NULL);
INSERT INTO `all_order` VALUES ('580120190502020819', '5801', 1, '2019-05-01 18:08:20', 1, 105.70, '', NULL);
INSERT INTO `all_order` VALUES ('580120190504033911', '5801', 2, '2019-05-03 19:39:12', 1, 150.00, '', NULL);
INSERT INTO `all_order` VALUES ('580120190508091834', '5801', 2, '2019-05-08 01:18:35', 1, 183.90, '', NULL);
INSERT INTO `all_order` VALUES ('580120190508103228', '5801', 2, '2019-05-08 02:32:29', 1, 190.50, '', NULL);
INSERT INTO `all_order` VALUES ('580120190508132237', '5801', 3, '2019-05-08 05:22:37', 1, 302.50, '', NULL);
INSERT INTO `all_order` VALUES ('580120190512162255', '5801', 3, '2019-05-12 08:22:56', 0, 224.20, '', NULL);

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `book_id` int(11) NOT NULL AUTO_INCREMENT,
  `book_author` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `book_press` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `book_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `book_price` double(10, 1) NOT NULL,
  `book_time` date NOT NULL,
  `book_num` int(4) NOT NULL,
  `book_intro` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `book_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `book_sale` int(5) NOT NULL,
  PRIMARY KEY (`book_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci COMMENT = '图书表，包括图书id，作者，出版社，图片地址，价格，出版时间，库存,图书名，简介' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (1, '埃里克·马瑟斯', ' 人民邮电出版社', '/api/Img/python1.png', 62.0, '2016-07-01', 200, 'Python3.5编程入门图书 机器学习', 'Python编程 从入门到实践', 8);
INSERT INTO `book` VALUES (2, 'Bruce Eckel ', '机械工业出版社', '/api/Img/java1.png', 84.9, '2007-06-01', 200, 'Java学习必读经典,殿堂级著作！', 'Java编程思想(第4版)', 5);
INSERT INTO `book` VALUES (3, ' 雷蒙德·钱德勒', '海南出版社', '/api/Img/mcdgb.png', 61.3, '2018-09-01', 200, '全新译本插图生动还原硬汉派文字精髓', '漫长的告别', 12);
INSERT INTO `book` VALUES (4, '帕斯卡尔·艾德兰', ' 未来出版社', '/api/Img/children1.jpg', 137.5, '2012-06-01', 200, '学前儿童生理启蒙必备', '我们的身体', 10);
INSERT INTO `book` VALUES (5, '艾文，梅特林克', '凤凰出版传媒集团', '/api/Img/children2.jpg', 11.6, '2010-05-15', 200, '诺贝尔文学奖获奖作家作品', '青鸟', 10);
INSERT INTO `book` VALUES (6, '比尔·斯莱文', ' 四川少年儿童出版社', '/api/Img/science.png', 78.0, '2017-04-01', 200, '精装大开本少儿科普百科全书 [7-10岁]', '它们是怎么来的', 10);
INSERT INTO `book` VALUES (7, ' 凯文·凯利', ' 电子工业出版社', '/api/Img/science2.png', 190.5, '2018-09-01', 200, ' KK三部曲', '科技想要什么', 9);
INSERT INTO `book` VALUES (8, '余华', ' 作家出版社', '/api/Img/letter1.jpg', 28.0, '2017-10-01', 200, '万物生长，阅享春光', '活着', 10);
INSERT INTO `book` VALUES (9, '汪曾祺', ' 贵州人民出版社', '/api/Img/letter2.jpg', 35.8, '2017-09-01', 200, '文学名家名著', '人间有味，自在从容', 10);
INSERT INTO `book` VALUES (10, '沈复', ' 浙江工商大学出版社', '/api/Img/live1.jpg', 36.3, '2018-07-01', 200, '2018新版！无删减彩色插图珍藏本', '浮生六记', 10);
INSERT INTO `book` VALUES (11, '毛姆 ', ' 开明出版社', '/api/Img/live2.jpg', 31.1, '2018-06-01', 200, '未删减足本，毛姆经典著作', '月亮与六便士', 10);
INSERT INTO `book` VALUES (12, '东野圭吾', ' 南海出版公司', '/api/Img/fiction1.jpg', 41.1, '2017-07-01', 200, '万物生长，阅享春光', '白夜行', 10);
INSERT INTO `book` VALUES (13, '晓晨兽', ' 沈阳出版社', '/api/Img/cartoon1.jpg', 24.8, '2015-11-01', 200, '套装1-2册', '开封奇谈', 10);
INSERT INTO `book` VALUES (14, '肥志 ', ' 黑龙江美术出版社', '/api/Img/cartoon2.jpg', 71.5, '2018-06-01', 200, '套装共2册', '如果历史是一群喵', 10);
INSERT INTO `book` VALUES (15, '孙力科', ' 浙江人民出版社', '/api/Img/biog1.jpg', 28.4, '2017-04-01', 200, '文学名家名著', '任正非传', 10);
INSERT INTO `book` VALUES (16, ' 布赖恩·克罗泽', ' 国际文化出版公司', '/api/Img/biog2.jpg', 20.3, '2010-01-01', 200, 'A Biography of Chiang Kai-Shek', '蒋介石传', 10);
INSERT INTO `book` VALUES (17, '尤瓦尔·赫拉利', ' 中信出版社', '/api/Img/people1.jpg', 40.8, '2017-03-01', 200, '从动物到上帝（新版）', '人类简史', 10);
INSERT INTO `book` VALUES (18, ' 莫里斯·布洛克', ' 商务印书馆', '/api/Img/people2.jpg', 22.1, '2018-05-01', 200, '人类学视野译丛', '人类学与认知挑战', 10);
INSERT INTO `book` VALUES (19, '大卫·班布里基', ' 北京联合出版公司', '/api/Img/people3.jpg', 32.2, '2018-05-01', 200, '万物生长，阅享春光', '中年的意义', 10);
INSERT INTO `book` VALUES (20, ' 理查德·德威特', ' 机械工业出版社', '/api/Img/people4.jpg', 74.2, '2019-01-01', 200, '现代人必须要懂的科学哲学和科学史', '世界观', 10);
INSERT INTO `book` VALUES (21, ' 尤瓦尔·赫拉利', ' 中信出版集团', '/api/Img/people5.jpg', 66.0, '2018-02-01', 200, ' [The Gene:An Intimate History]', '基因传', 10);
INSERT INTO `book` VALUES (22, '米歇尔，奥巴马', ' 天地出版社', '/api/Img/biog3.jpg', 77.8, '2019-01-01', 200, '解读奥巴马，解读八年白宫生活', '成为，米歇尔,奥巴马自传', 22);
INSERT INTO `book` VALUES (23, 'Kid岁 ', '北京时代华文书局有限公司', '/api/Img/cartoon3.jpg', 27.5, '2017-05-01', 200, '文学艺术动漫图书', '怦然心动1', 12);
INSERT INTO `book` VALUES (24, '顾西爵 ', ' 百花洲文艺出版社', '/api/Img/fiction2.jpg', 21.1, '2014-01-01', 200, '完美纪念版 附精美海报、纪念卡', '最美遇见你', 12);
INSERT INTO `book` VALUES (25, '郑渊洁', ' 浙江少年儿童出版社', '/api/Img/children3.jpg', 99.0, '2017-11-01', 200, '郑渊洁经典童话：注音版(全6册)', '郑渊洁经典童话', 15);
INSERT INTO `book` VALUES (26, '蔡崇达', ' 天津人民出版社', '/api/Img/letter3.jpg', 33.7, '2014-12-01', 200, '畅销300万册的国民读本，韩寒监制，刘德华、李敬泽作序。', '皮囊', 18);
INSERT INTO `book` VALUES (27, '蔡康永', '湖南文艺出版社', '/api/Img/live3.jpg', 31.5, '2018-11-01', 200, '为你自己活一次', '蔡康永的情商课', 13);
INSERT INTO `book` VALUES (28, '麻省理工科技评论', ' 人民邮电出版社', '/api/Img/science3.jpg', 77.3, '2017-01-01', 200, '50大全球突破性技术深度剖析', '科技之巅 ', 5);
INSERT INTO `book` VALUES (29, 'Stephen Prata ', ' 人民邮电出版社', '/api/Img/cadd.jpg', 72.2, '2012-06-01', 200, '（第6版 中文版） [C++ Primer Plus]', 'C++ Primer Plus', 20);
INSERT INTO `book` VALUES (31, '埃里克·马瑟斯', '人民邮电出版社', '/api/Img/python1.png', 62.0, '1970-01-01', 200, 'Python3.5编程入门图书 机器学习', 'Python编程 从入门到实践', 0);
INSERT INTO `book` VALUES (32, 'Bruce Eckel', '机械工业出版社', '/api/Img/java1.png', 84.9, '1970-01-01', 200, 'Java学习必读经典,殿堂级著作！', 'Java编程思想(第4版)', 0);

-- ----------------------------
-- Table structure for bookorder
-- ----------------------------
DROP TABLE IF EXISTS `bookorder`;
CREATE TABLE `bookorder`  (
  `order_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `contact_id` int(11) NOT NULL,
  `order_time` datetime(0) NOT NULL,
  `order_status` int(1) NOT NULL,
  `order_allprice` double(10, 2) NOT NULL,
  `user_leave_mess` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`) USING BTREE,
  INDEX `order_id`(`order_id`) USING BTREE,
  INDEX `car_suer_id`(`user_id`) USING BTREE,
  INDEX `car_contact_id`(`contact_id`) USING BTREE,
  CONSTRAINT `car_contact_id` FOREIGN KEY (`contact_id`) REFERENCES `contacts` (`contact_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `car_suer_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci COMMENT = '订单id\r\n用户id\r\n联系人id\r\n订单时间\r\n订单状态' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bookorder
-- ----------------------------
INSERT INTO `bookorder` VALUES ('201903031111', '5801', 10, '2019-04-03 01:25:29', 2, 137.50, '尽快发货！');
INSERT INTO `bookorder` VALUES ('201903051111', '5801', 1, '2019-04-04 17:45:58', 4, 72.20, '尽快发货！');
INSERT INTO `bookorder` VALUES ('201903071111', '5801', 1, '2019-04-05 17:47:45', 3, 74.20, '尽快发货！');
INSERT INTO `bookorder` VALUES ('201903091111', '5801', 1, '2019-04-06 17:52:14', 4, 130.50, '尽快发货！');
INSERT INTO `bookorder` VALUES ('580120190502004250', '5801', 1, '2019-05-01 16:42:51', 4, 83.40, '');
INSERT INTO `bookorder` VALUES ('580120190502004530', '5801', 1, '2019-05-01 16:45:31', 2, 132.70, '尽快发货！');
INSERT INTO `bookorder` VALUES ('580120190502010048', '5801', 1, '2019-05-01 17:00:49', 2, 132.70, '');
INSERT INTO `bookorder` VALUES ('580120190502010130', '5801', 1, '2019-05-01 17:01:31', 4, 132.70, '');
INSERT INTO `bookorder` VALUES ('580120190502010347', '5801', 1, '2019-05-01 17:03:48', 2, 132.70, '');
INSERT INTO `bookorder` VALUES ('580120190502010700', '5801', 2, '2019-05-01 17:07:01', 2, 132.70, '');
INSERT INTO `bookorder` VALUES ('580120190502013529', '5801', 16, '2019-05-01 17:35:30', 2, 103.70, '');
INSERT INTO `bookorder` VALUES ('580120190502014900', '5801', 16, '2019-05-01 17:49:01', 2, 65.90, '6666');
INSERT INTO `bookorder` VALUES ('580120190502020819', '5801', 1, '2019-05-01 18:08:20', 1, 105.70, '');
INSERT INTO `bookorder` VALUES ('580120190504033911', '5801', 2, '2019-05-03 19:39:12', 1, 150.00, '');
INSERT INTO `bookorder` VALUES ('580120190508091834', '5801', 2, '2019-05-08 01:18:35', 1, 183.90, '');
INSERT INTO `bookorder` VALUES ('580120190508103228', '5801', 2, '2019-05-08 02:32:29', 1, 190.50, '');
INSERT INTO `bookorder` VALUES ('580120190508132237', '5801', 3, '2019-05-08 05:22:37', 1, 302.50, '');
INSERT INTO `bookorder` VALUES ('580120190512162255', '5801', 3, '2019-05-12 08:22:56', 0, 224.20, '');

-- ----------------------------
-- Table structure for business
-- ----------------------------
DROP TABLE IF EXISTS `business`;
CREATE TABLE `business`  (
  `business_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `business_pass` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `business_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `business_phone` char(22) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `business_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`business_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci COMMENT = '商家表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of business
-- ----------------------------
INSERT INTO `business` VALUES ('8888', '123456', '最最最牛皮的卖家', '13202258585', '/api/Img/1.jpg');

-- ----------------------------
-- Table structure for chat
-- ----------------------------
DROP TABLE IF EXISTS `chat`;
CREATE TABLE `chat`  (
  `chat_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `business_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `send_time` datetime(0) NOT NULL,
  `context` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `statu` int(1) NOT NULL,
  PRIMARY KEY (`chat_id`) USING BTREE,
  INDEX `book_send_id`(`user_id`) USING BTREE,
  INDEX `book_business_id1`(`business_id`) USING BTREE,
  CONSTRAINT `book_business_id1` FOREIGN KEY (`business_id`) REFERENCES `business` (`business_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `book_send_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 95 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci COMMENT = '聊天表\r\n' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of chat
-- ----------------------------
INSERT INTO `chat` VALUES (56, '5803', '8888', '2019-05-05 17:44:38', '111', 2);
INSERT INTO `chat` VALUES (59, '5800', '8888', '2019-05-05 17:47:52', '555', 2);
INSERT INTO `chat` VALUES (61, '5802', '8888', '2019-05-05 17:47:52', '555', 2);
INSERT INTO `chat` VALUES (63, '5804', '8888', '2019-05-05 17:47:52', '555', 2);
INSERT INTO `chat` VALUES (65, '5801', '8888', '2019-05-05 17:48:19', '888', 1);
INSERT INTO `chat` VALUES (67, '5801', '8888', '2019-05-05 17:54:06', '666', 0);
INSERT INTO `chat` VALUES (68, '5801', '8888', '2019-05-05 17:54:22', '888', 2);
INSERT INTO `chat` VALUES (69, '5801', '8888', '2019-05-05 18:16:04', '#调皮;', 2);
INSERT INTO `chat` VALUES (70, '5801', '8888', '2019-05-05 18:33:07', '2222', 2);
INSERT INTO `chat` VALUES (71, '5801', '8888', '2019-05-05 18:37:54', '2222', 2);
INSERT INTO `chat` VALUES (73, '5801', '8888', '2019-05-05 20:29:58', '666', 0);
INSERT INTO `chat` VALUES (74, '5801', '8888', '2019-05-05 20:31:47', '5555', 0);
INSERT INTO `chat` VALUES (75, '5801', '8888', '2019-05-05 20:43:44', '#冷汗;', 0);
INSERT INTO `chat` VALUES (76, '5801', '8888', '2019-05-05 20:43:56', '555', 0);
INSERT INTO `chat` VALUES (77, '5801', '8888', '2019-05-06 05:48:31', '666', 0);
INSERT INTO `chat` VALUES (78, '5801', '8888', '2019-05-06 06:05:50', '你好', 0);
INSERT INTO `chat` VALUES (79, '5801', '8888', '2019-05-06 06:06:06', '#微笑;', 2);
INSERT INTO `chat` VALUES (80, '5801', '8888', '2019-05-06 09:09:04', '#阴险;', 0);
INSERT INTO `chat` VALUES (81, '5801', '8888', '2019-05-06 09:20:58', '#嘘;', 0);
INSERT INTO `chat` VALUES (82, '5801', '8888', '2019-05-06 09:21:56', '#糗大了;', 0);
INSERT INTO `chat` VALUES (83, '5801', '8888', '2019-05-06 09:22:02', '#猪头;', 1);
INSERT INTO `chat` VALUES (84, '5801', '8888', '2019-05-06 09:22:11', '#快哭了;', 1);
INSERT INTO `chat` VALUES (85, '5801', '8888', '2019-05-06 09:22:19', '#晕;', 1);
INSERT INTO `chat` VALUES (86, '5801', '8888', '2019-05-06 19:09:24', '5555', 0);
INSERT INTO `chat` VALUES (87, '5801', '8888', '2019-05-06 19:09:35', '5555', 0);
INSERT INTO `chat` VALUES (88, '5801', '8888', '2019-05-06 19:11:37', '888', 0);
INSERT INTO `chat` VALUES (89, '5801', '8888', '2019-05-06 19:12:46', '789', 0);
INSERT INTO `chat` VALUES (90, '5801', '8888', '2019-05-06 19:13:42', '888', 0);
INSERT INTO `chat` VALUES (91, '5801', '8888', '2019-05-07 12:52:09', '#晕;', 0);
INSERT INTO `chat` VALUES (92, '5801', '8888', '2019-05-08 01:19:27', '#糗大了;', 0);
INSERT INTO `chat` VALUES (93, '5801', '8888', '2019-05-08 01:20:46', 'dddd', 2);
INSERT INTO `chat` VALUES (94, '5801', '8888', '2019-05-08 05:27:43', '#阴险;', 2);

-- ----------------------------
-- Table structure for classified
-- ----------------------------
DROP TABLE IF EXISTS `classified`;
CREATE TABLE `classified`  (
  `classified_id` int(4) NOT NULL AUTO_INCREMENT,
  `classified_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`classified_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '分类表，总共有多少种图书类别。' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of classified
-- ----------------------------
INSERT INTO `classified` VALUES (1, '少儿');
INSERT INTO `classified` VALUES (2, '科技');
INSERT INTO `classified` VALUES (3, '文学');
INSERT INTO `classified` VALUES (4, '生活');
INSERT INTO `classified` VALUES (5, '小说');
INSERT INTO `classified` VALUES (6, '编程');
INSERT INTO `classified` VALUES (7, '动漫');
INSERT INTO `classified` VALUES (8, '传记');
INSERT INTO `classified` VALUES (9, '人类学');

-- ----------------------------
-- Table structure for collect
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect`  (
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `book_id` int(11) NOT NULL,
  `collect_statu` int(1) UNSIGNED ZEROFILL NOT NULL,
  PRIMARY KEY (`user_id`, `book_id`) USING BTREE,
  INDEX `collect_book_id`(`book_id`) USING BTREE,
  CONSTRAINT `collect_book_id` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `collect_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of collect
-- ----------------------------
INSERT INTO `collect` VALUES ('5801', 2, 0);
INSERT INTO `collect` VALUES ('5801', 3, 1);
INSERT INTO `collect` VALUES ('5801', 4, 1);
INSERT INTO `collect` VALUES ('5801', 5, 0);
INSERT INTO `collect` VALUES ('5801', 6, 1);
INSERT INTO `collect` VALUES ('5801', 7, 1);
INSERT INTO `collect` VALUES ('5801', 9, 0);
INSERT INTO `collect` VALUES ('5801', 15, 1);
INSERT INTO `collect` VALUES ('5801', 18, 1);
INSERT INTO `collect` VALUES ('5801', 20, 1);
INSERT INTO `collect` VALUES ('5801', 22, 1);
INSERT INTO `collect` VALUES ('5801', 23, 1);
INSERT INTO `collect` VALUES ('5801', 25, 0);
INSERT INTO `collect` VALUES ('5801', 26, 1);
INSERT INTO `collect` VALUES ('5801', 27, 1);
INSERT INTO `collect` VALUES ('5801', 29, 1);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `com_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `book_id` int(7) NOT NULL,
  `com_mess` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `com_time` datetime(0) NOT NULL,
  `com_status` int(1) NOT NULL,
  `pic_address` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `com_reply` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `reply_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`com_id`) USING BTREE,
  INDEX `fx_book`(`book_id`) USING BTREE,
  INDEX `fx_user`(`user_id`) USING BTREE,
  CONSTRAINT `fx_book` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fx_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci COMMENT = '评论id\r\n评论的用户id\r\n评论的图书id\r\n评论内容\r\n评论时间' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, '5800', 2, '该书讲的清晰明了，很有受益', '2019-03-16 22:05:17', 0, NULL, NULL, NULL);
INSERT INTO `comment` VALUES (2, '5800', 3, '是一本很不错的小说，值得推荐！', '2019-03-14 22:06:41', 0, NULL, NULL, NULL);
INSERT INTO `comment` VALUES (3, '5802', 2, '好书，值得购买！', '2019-03-08 00:04:06', 0, NULL, NULL, NULL);
INSERT INTO `comment` VALUES (4, '5803', 2, '编程好书！', '2019-03-07 00:04:46', 0, NULL, NULL, NULL);
INSERT INTO `comment` VALUES (5, '5804', 2, '内容丰富，更能理解！', '2019-03-01 00:05:50', 0, NULL, NULL, NULL);
INSERT INTO `comment` VALUES (6, '5801', 3, '写的挺好的，推荐阅读！', '2019-03-05 00:06:57', 0, NULL, '多来关顾哦亲！', '2019-04-26 19:01:05');
INSERT INTO `comment` VALUES (7, '5804', 3, '故事情节丰富，阅读感觉不错！', '2019-03-11 00:08:10', 0, NULL, NULL, NULL);
INSERT INTO `comment` VALUES (8, '5801', 27, '这本书挺好的！', '2019-04-16 13:55:31', 0, NULL, NULL, NULL);
INSERT INTO `comment` VALUES (9, '5801', 25, '挺好的这本书！', '2019-04-16 13:55:31', 0, NULL, NULL, NULL);
INSERT INTO `comment` VALUES (10, '5801', 3, '好书，值得推荐！', '2019-05-01 16:44:46', 0, NULL, NULL, NULL);
INSERT INTO `comment` VALUES (11, '5801', 18, '好书，值得推荐！', '2019-05-01 16:44:46', 0, NULL, NULL, NULL);
INSERT INTO `comment` VALUES (12, '5801', 25, '好书，多读点！', '2019-05-01 17:21:45', 0, NULL, NULL, NULL);
INSERT INTO `comment` VALUES (13, '5801', 26, '好书多读点！', '2019-05-01 17:21:45', 0, NULL, NULL, NULL);
INSERT INTO `comment` VALUES (14, '5801', 29, '555', '2019-05-08 05:23:15', 0, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for contacts
-- ----------------------------
DROP TABLE IF EXISTS `contacts`;
CREATE TABLE `contacts`  (
  `contact_id` int(11) NOT NULL AUTO_INCREMENT,
  `contact_phone` char(22) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `contact_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `contact_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `statu` int(1) UNSIGNED ZEROFILL NOT NULL,
  PRIMARY KEY (`contact_id`) USING BTREE,
  INDEX `contacts_user_id`(`user_id`) USING BTREE,
  CONSTRAINT `contacts_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci COMMENT = '联系人id\r\n联系人电话\r\n联系人地址\r\n联系人姓名\r\n用户id(哪个用户里面的联系人)' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of contacts
-- ----------------------------
INSERT INTO `contacts` VALUES (1, '13602289756', '广东广州白云钟落潭仲恺', '飘雪国', '5801', 1);
INSERT INTO `contacts` VALUES (2, '13602890589', '广东广州白云男生宿舍16栋', 'Mary', '5801', 0);
INSERT INTO `contacts` VALUES (3, '13602285857', '广东汕头', '小酷', '5801', 0);
INSERT INTO `contacts` VALUES (10, '13602289788', '广东广州白云钟落潭仲恺', '飘雪', '5801', 0);
INSERT INTO `contacts` VALUES (16, '13602289788', '广东广州白云钟落潭仲恺', '凯文陈国', '5801', 0);

-- ----------------------------
-- Table structure for coupon
-- ----------------------------
DROP TABLE IF EXISTS `coupon`;
CREATE TABLE `coupon`  (
  `coupon_id` int(11) NOT NULL AUTO_INCREMENT,
  `coupon_img` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `coupon_use` int(11) NOT NULL,
  `coupon_money` int(11) NOT NULL,
  `coupon_vaild` datetime(0) NOT NULL,
  `coupon_time` datetime(0) NOT NULL,
  `coupon_num` int(11) NOT NULL,
  `business_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `coupon_getnum` int(10) NOT NULL,
  PRIMARY KEY (`coupon_id`) USING BTREE,
  INDEX `coupon_bussiness_id`(`business_id`) USING BTREE,
  CONSTRAINT `coupon_bussiness_id` FOREIGN KEY (`business_id`) REFERENCES `business` (`business_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of coupon
-- ----------------------------
INSERT INTO `coupon` VALUES (1, '/api/Img/category.png', 200, 10, '2019-06-07 20:21:29', '2019-04-07 20:21:22', 1000, '8888', 100);
INSERT INTO `coupon` VALUES (2, '/api/Img/category.png', 105, 5, '2019-06-01 20:25:56', '2019-04-07 20:25:53', 1000, '8888', 100);
INSERT INTO `coupon` VALUES (3, '/api/Img/category.png', 500, 20, '2019-06-01 20:26:34', '2019-04-07 20:26:40', 1000, '8888', 50);
INSERT INTO `coupon` VALUES (4, '/api/Img/category.png', 105, 5, '2019-04-06 15:19:15', '2019-03-06 15:19:21', 1000, '8888', 888);
INSERT INTO `coupon` VALUES (5, '/api/Img/category.png', 200, 12, '2019-06-05 16:00:00', '2019-05-08 00:27:47', 200, '8888', 0);

-- ----------------------------
-- Table structure for get_classified
-- ----------------------------
DROP TABLE IF EXISTS `get_classified`;
CREATE TABLE `get_classified`  (
  `get_classified_id` int(11) NOT NULL AUTO_INCREMENT,
  `book_id` int(11) NOT NULL,
  `classified_id` int(4) NOT NULL,
  PRIMARY KEY (`get_classified_id`) USING BTREE,
  INDEX `fk_bookid`(`book_id`) USING BTREE,
  INDEX `fk_classifyid`(`classified_id`) USING BTREE,
  CONSTRAINT `fk_bookid` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_classifyid` FOREIGN KEY (`classified_id`) REFERENCES `classified` (`classified_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '获得图书的分类，一本图书有多种类别！' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of get_classified
-- ----------------------------
INSERT INTO `get_classified` VALUES (1, 1, 6);
INSERT INTO `get_classified` VALUES (2, 2, 6);
INSERT INTO `get_classified` VALUES (3, 3, 5);
INSERT INTO `get_classified` VALUES (4, 4, 1);
INSERT INTO `get_classified` VALUES (5, 5, 1);
INSERT INTO `get_classified` VALUES (6, 6, 2);
INSERT INTO `get_classified` VALUES (7, 7, 2);
INSERT INTO `get_classified` VALUES (8, 8, 3);
INSERT INTO `get_classified` VALUES (9, 9, 3);
INSERT INTO `get_classified` VALUES (10, 10, 4);
INSERT INTO `get_classified` VALUES (11, 11, 4);
INSERT INTO `get_classified` VALUES (12, 12, 5);
INSERT INTO `get_classified` VALUES (13, 13, 7);
INSERT INTO `get_classified` VALUES (14, 14, 7);
INSERT INTO `get_classified` VALUES (15, 15, 8);
INSERT INTO `get_classified` VALUES (16, 16, 8);
INSERT INTO `get_classified` VALUES (17, 17, 9);
INSERT INTO `get_classified` VALUES (18, 18, 9);
INSERT INTO `get_classified` VALUES (19, 19, 9);
INSERT INTO `get_classified` VALUES (20, 20, 9);
INSERT INTO `get_classified` VALUES (21, 21, 9);
INSERT INTO `get_classified` VALUES (22, 22, 8);
INSERT INTO `get_classified` VALUES (23, 23, 7);
INSERT INTO `get_classified` VALUES (24, 24, 5);
INSERT INTO `get_classified` VALUES (25, 25, 1);
INSERT INTO `get_classified` VALUES (26, 26, 3);
INSERT INTO `get_classified` VALUES (27, 27, 4);
INSERT INTO `get_classified` VALUES (28, 28, 2);
INSERT INTO `get_classified` VALUES (29, 29, 6);

-- ----------------------------
-- Table structure for get_coupon
-- ----------------------------
DROP TABLE IF EXISTS `get_coupon`;
CREATE TABLE `get_coupon`  (
  `get_coupon_id` int(11) NOT NULL AUTO_INCREMENT,
  `coupon_id` int(11) NOT NULL,
  `coupon_num` int(11) NOT NULL,
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`get_coupon_id`) USING BTREE,
  INDEX `get_coupon_id`(`coupon_id`) USING BTREE,
  INDEX `get_user_id`(`user_id`) USING BTREE,
  CONSTRAINT `get_coupon_id` FOREIGN KEY (`coupon_id`) REFERENCES `coupon` (`coupon_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `get_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of get_coupon
-- ----------------------------
INSERT INTO `get_coupon` VALUES (1, 1, 1, '5801');
INSERT INTO `get_coupon` VALUES (2, 3, 1, '5801');
INSERT INTO `get_coupon` VALUES (3, 4, 1, '5801');
INSERT INTO `get_coupon` VALUES (4, 2, 0, '5801');
INSERT INTO `get_coupon` VALUES (5, 5, 0, '5801');

-- ----------------------------
-- Table structure for item
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item`  (
  `item_id` int(8) NOT NULL AUTO_INCREMENT,
  `book_id` int(8) NOT NULL,
  `shop_num` int(4) NOT NULL,
  `order_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`item_id`) USING BTREE,
  INDEX `book_order_id`(`order_id`) USING BTREE,
  INDEX `book_book_id`(`book_id`) USING BTREE,
  INDEX `book_user_id`(`user_id`) USING BTREE,
  CONSTRAINT `book_book_id` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `book_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci COMMENT = '条目id\r\n图书id\r\n该条目购买书的数量\r\n订单id\r\n用户id\r\n\r\n一个订单对应多种图书，每个条目代表一种图书' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of item
-- ----------------------------
INSERT INTO `item` VALUES (1, 4, 1, '201903031111', '5801');
INSERT INTO `item` VALUES (2, 29, 1, '201903051111', '5801');
INSERT INTO `item` VALUES (3, 22, 1, '201903071111', '5801');
INSERT INTO `item` VALUES (4, 27, 1, '201903091111', '5801');
INSERT INTO `item` VALUES (5, 25, 1, '201903091111', '5801');
INSERT INTO `item` VALUES (6, 3, 1, '580120190416160354', '5801');
INSERT INTO `item` VALUES (7, 22, 1, '580120190416160354', '5801');
INSERT INTO `item` VALUES (8, 4, 1, '580120190418133329', '5801');
INSERT INTO `item` VALUES (9, 29, 1, '580120190418133329', '5801');
INSERT INTO `item` VALUES (10, 3, 1, '580120190502004250', '5801');
INSERT INTO `item` VALUES (11, 18, 1, '580120190502004250', '5801');
INSERT INTO `item` VALUES (12, 25, 1, '580120190502004530', '5801');
INSERT INTO `item` VALUES (13, 26, 1, '580120190502004530', '5801');
INSERT INTO `item` VALUES (14, 25, 1, '580120190502005746', '5801');
INSERT INTO `item` VALUES (15, 26, 1, '580120190502005746', '5801');
INSERT INTO `item` VALUES (16, 25, 1, '580120190502010048', '5801');
INSERT INTO `item` VALUES (17, 26, 1, '580120190502010048', '5801');
INSERT INTO `item` VALUES (18, 25, 1, '580120190502010130', '5801');
INSERT INTO `item` VALUES (19, 26, 1, '580120190502010130', '5801');
INSERT INTO `item` VALUES (20, 25, 1, '580120190502010347', '5801');
INSERT INTO `item` VALUES (21, 26, 1, '580120190502010347', '5801');
INSERT INTO `item` VALUES (22, 25, 1, '580120190502010700', '5801');
INSERT INTO `item` VALUES (23, 26, 1, '580120190502010700', '5801');
INSERT INTO `item` VALUES (24, 27, 1, '580120190502013529', '5801');
INSERT INTO `item` VALUES (25, 29, 1, '580120190502013529', '5801');
INSERT INTO `item` VALUES (26, 27, 1, '580120190502013857', '5801');
INSERT INTO `item` VALUES (27, 29, 1, '580120190502013857', '5801');
INSERT INTO `item` VALUES (28, 15, 1, '580120190502014900', '5801');
INSERT INTO `item` VALUES (29, 23, 1, '580120190502014900', '5801');
INSERT INTO `item` VALUES (30, 20, 1, '580120190502020819', '5801');
INSERT INTO `item` VALUES (31, 27, 1, '580120190502020819', '5801');
INSERT INTO `item` VALUES (32, 22, 1, '580120190504033911', '5801');
INSERT INTO `item` VALUES (33, 29, 1, '580120190504033911', '5801');
INSERT INTO `item` VALUES (34, 6, 1, '580120190508091834', '5801');
INSERT INTO `item` VALUES (35, 26, 1, '580120190508091834', '5801');
INSERT INTO `item` VALUES (36, 29, 1, '580120190508091834', '5801');
INSERT INTO `item` VALUES (37, 7, 1, '580120190508103228', '5801');
INSERT INTO `item` VALUES (38, 4, 1, '580120190508132237', '5801');
INSERT INTO `item` VALUES (39, 6, 1, '580120190508132237', '5801');
INSERT INTO `item` VALUES (40, 25, 1, '580120190508132237', '5801');
INSERT INTO `item` VALUES (41, 7, 1, '580120190512162255', '5801');
INSERT INTO `item` VALUES (42, 26, 1, '580120190512162255', '5801');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `paper_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `paper_state` int(1) NOT NULL,
  `send_time` datetime(0) NOT NULL,
  `send_mess` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`paper_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES (1, '5801', 0, '2019-03-15 00:00:00', '书是人类进步的阶梯！');
INSERT INTO `message` VALUES (2, '5801', 0, '2019-03-15 00:00:00', '书中自有颜如玉！');
INSERT INTO `message` VALUES (3, '5801', 0, '2019-03-14 00:00:00', '书中自有黄金屋！');
INSERT INTO `message` VALUES (4, '5801', 1, '2019-03-13 00:00:00', '读书破万卷,下笔如有神');
INSERT INTO `message` VALUES (5, '5801', 1, '2019-03-12 00:00:00', '书山有路勤为径！');
INSERT INTO `message` VALUES (9, '5800', 0, '2019-04-25 18:12:21', '读书是一种享受！');
INSERT INTO `message` VALUES (10, '5801', 1, '2019-04-25 18:12:21', '读书是一种享受！');
INSERT INTO `message` VALUES (11, '5802', 0, '2019-04-25 18:12:21', '读书是一种享受！');
INSERT INTO `message` VALUES (12, '5803', 0, '2019-04-25 18:12:21', '读书是一种享受！');
INSERT INTO `message` VALUES (13, '5804', 0, '2019-04-25 18:12:21', '读书是一种享受！');
INSERT INTO `message` VALUES (14, '5805', 0, '2019-04-25 18:12:21', '读书是一种享受！');
INSERT INTO `message` VALUES (15, '5800', 0, '2019-04-25 18:15:34', '读书是一种乐趣！');
INSERT INTO `message` VALUES (16, '5801', 1, '2019-04-25 18:15:34', '读书是一种乐趣！');
INSERT INTO `message` VALUES (17, '5802', 0, '2019-04-25 18:15:34', '读书是一种乐趣！');
INSERT INTO `message` VALUES (18, '5803', 0, '2019-04-25 18:15:34', '读书是一种乐趣！');
INSERT INTO `message` VALUES (19, '5804', 0, '2019-04-25 18:15:34', '读书是一种乐趣！');
INSERT INTO `message` VALUES (20, '5805', 0, '2019-04-25 18:15:34', '读书是一种乐趣！');
INSERT INTO `message` VALUES (21, '5800', 0, '2019-04-25 18:20:07', '读书是一种乐趣！');
INSERT INTO `message` VALUES (22, '5801', 1, '2019-04-25 18:20:07', '读书是一种乐趣！');
INSERT INTO `message` VALUES (23, '5802', 0, '2019-04-25 18:20:07', '读书是一种乐趣！');
INSERT INTO `message` VALUES (24, '5803', 0, '2019-04-25 18:20:07', '读书是一种乐趣！');
INSERT INTO `message` VALUES (25, '5804', 0, '2019-04-25 18:20:07', '读书是一种乐趣！');
INSERT INTO `message` VALUES (26, '5805', 0, '2019-04-25 18:20:07', '读书是一种乐趣！');

-- ----------------------------
-- Table structure for morning_paper
-- ----------------------------
DROP TABLE IF EXISTS `morning_paper`;
CREATE TABLE `morning_paper`  (
  `paper_id` int(11) NOT NULL AUTO_INCREMENT,
  `paper_time` datetime(0) NOT NULL,
  `paper_mess` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `business_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`paper_id`) USING BTREE,
  INDEX `paper_business_id`(`business_id`) USING BTREE,
  CONSTRAINT `paper_business_id` FOREIGN KEY (`business_id`) REFERENCES `business` (`business_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of morning_paper
-- ----------------------------
INSERT INTO `morning_paper` VALUES (1, '2019-03-15 10:00:00', '书是人类进步的阶梯！', '8888');
INSERT INTO `morning_paper` VALUES (2, '2019-03-15 11:00:00', '书中自有颜如玉！', '8888');
INSERT INTO `morning_paper` VALUES (3, '2019-03-14 15:00:00', '书中自有黄金屋！', '8888');
INSERT INTO `morning_paper` VALUES (4, '2019-03-13 14:00:00', '读书破万卷,下笔如有神！', '8888');
INSERT INTO `morning_paper` VALUES (5, '2019-03-12 13:00:00', '书山有路勤为径！', '8888');
INSERT INTO `morning_paper` VALUES (6, '2019-04-25 12:00:00', '读书是一种享受！', '8888');
INSERT INTO `morning_paper` VALUES (8, '2019-04-25 18:20:07', '读书是一种乐趣！', '8888');

-- ----------------------------
-- Table structure for shop_car
-- ----------------------------
DROP TABLE IF EXISTS `shop_car`;
CREATE TABLE `shop_car`  (
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `book_id` int(11) NOT NULL,
  `shop_num` int(4) NOT NULL,
  PRIMARY KEY (`user_id`, `book_id`) USING BTREE,
  INDEX `book_id`(`book_id`) USING BTREE,
  CONSTRAINT `fx_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `shop_car_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci COMMENT = '购物车\r\n\r\n用户id\r\n图书id\r\n加购数量\r\n\r\n用户id和图书id合成一个主键' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shop_car
-- ----------------------------
INSERT INTO `shop_car` VALUES ('5801', 20, 1);
INSERT INTO `shop_car` VALUES ('5801', 29, 1);

-- ----------------------------
-- Table structure for topic
-- ----------------------------
DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic`  (
  `topic_id` int(11) NOT NULL AUTO_INCREMENT,
  `topic_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `topic_time` datetime(0) NOT NULL,
  `topic_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`topic_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of topic
-- ----------------------------
INSERT INTO `topic` VALUES (1, '/api/Img/children.jpg', '2019-04-29 00:38:50', '少儿');
INSERT INTO `topic` VALUES (2, '/api/Img/science.jpg', '2019-04-19 00:43:45', '科技');
INSERT INTO `topic` VALUES (3, '/api/Img/renwen.jpg', '2019-04-20 00:44:13', '人文');
INSERT INTO `topic` VALUES (4, '/api/Img/live.jpg', '2019-04-23 00:44:33', '生活');
INSERT INTO `topic` VALUES (5, '/api/Img/fiction.jpg', '2019-04-18 00:44:58', '小说');
INSERT INTO `topic` VALUES (6, '/api/Img/program.jpg', '2019-04-10 00:45:24', '编程');
INSERT INTO `topic` VALUES (7, '/api/Img/cartoon.jpg', '2019-04-09 00:46:00', '动漫');
INSERT INTO `topic` VALUES (8, '/api/Img/biog.jpg', '2019-04-04 00:46:24', '传记');
INSERT INTO `topic` VALUES (9, '/api/Img/people.jpg', '2019-04-09 00:46:47', '人类学');

-- ----------------------------
-- Table structure for topic_book
-- ----------------------------
DROP TABLE IF EXISTS `topic_book`;
CREATE TABLE `topic_book`  (
  `topic_item_id` int(11) NOT NULL AUTO_INCREMENT,
  `topic_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  PRIMARY KEY (`topic_item_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of topic_book
-- ----------------------------
INSERT INTO `topic_book` VALUES (1, 1, 4);
INSERT INTO `topic_book` VALUES (2, 1, 5);
INSERT INTO `topic_book` VALUES (3, 1, 25);
INSERT INTO `topic_book` VALUES (4, 2, 6);
INSERT INTO `topic_book` VALUES (5, 2, 7);
INSERT INTO `topic_book` VALUES (6, 2, 28);
INSERT INTO `topic_book` VALUES (7, 3, 8);
INSERT INTO `topic_book` VALUES (8, 3, 9);
INSERT INTO `topic_book` VALUES (9, 3, 26);
INSERT INTO `topic_book` VALUES (10, 4, 10);
INSERT INTO `topic_book` VALUES (11, 4, 11);
INSERT INTO `topic_book` VALUES (12, 4, 27);
INSERT INTO `topic_book` VALUES (13, 5, 3);
INSERT INTO `topic_book` VALUES (14, 5, 12);
INSERT INTO `topic_book` VALUES (15, 5, 24);
INSERT INTO `topic_book` VALUES (16, 6, 1);
INSERT INTO `topic_book` VALUES (17, 6, 2);
INSERT INTO `topic_book` VALUES (18, 6, 29);
INSERT INTO `topic_book` VALUES (19, 7, 13);
INSERT INTO `topic_book` VALUES (20, 7, 14);
INSERT INTO `topic_book` VALUES (21, 7, 23);
INSERT INTO `topic_book` VALUES (22, 8, 15);
INSERT INTO `topic_book` VALUES (23, 8, 16);
INSERT INTO `topic_book` VALUES (24, 8, 22);
INSERT INTO `topic_book` VALUES (25, 9, 17);
INSERT INTO `topic_book` VALUES (26, 9, 18);
INSERT INTO `topic_book` VALUES (27, 9, 19);
INSERT INTO `topic_book` VALUES (28, 9, 20);
INSERT INTO `topic_book` VALUES (29, 9, 21);
INSERT INTO `topic_book` VALUES (30, 1, 13);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_sex` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_age` int(3) NULL DEFAULT NULL,
  `user_pass` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_phone` char(22) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci COMMENT = '用户id\r\n用户名\r\n用户性别\r\n用户年龄\r\n用户密码' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('5800', '拓荒者', '男', 23, '123456', '13632275847', '/api/Img/2.jpg');
INSERT INTO `user` VALUES ('5801', '飘雪国', '男', 26, '123456', '13602289758', '/api/Img/20190504034222.jpg');
INSERT INTO `user` VALUES ('5802', '刘德华', '男', 45, '123456', '13636666666', '/api/Img/3.jpg');
INSERT INTO `user` VALUES ('5803', '黎明', '男', 47, '123456', '13226111111', '/api/Img/5.jpg');
INSERT INTO `user` VALUES ('5804', '郭富城', '男', 50, '123456', '16555555555', '/api/Img/6.jpg');
INSERT INTO `user` VALUES ('5805', '女侠', '女', 28, '123456', '13632285858', '/api/Img/4.jpg');

SET FOREIGN_KEY_CHECKS = 1;
