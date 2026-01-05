-- MySQL dump 10.13  Distrib 9.0.1, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: onlinecarhailing
-- ------------------------------------------------------
-- Server version	9.0.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `passenger_id` int DEFAULT NULL COMMENT '是否是乘客',
  `driver_id` int DEFAULT NULL COMMENT '是否是司机，司机的表的id',
  `account` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '帐号',
  `password` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '密码',
  `manager_id` int DEFAULT NULL COMMENT '管理员信息的id',
  `phone` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '帐号的手机号，用来在忘记密码时使用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='帐号表，';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
INSERT INTO `account` (`id`, `passenger_id`, `driver_id`, `account`, `password`, `manager_id`, `phone`) VALUES (38,12,29,'11','11',NULL,NULL),(41,NULL,NULL,'1','1',1,NULL),(42,34,24,'22','22',NULL,NULL),(44,16,NULL,'33','44',NULL,'33'),(45,35,NULL,'44','44',NULL,'44'),(46,NULL,30,'66','66',NULL,'88888888'),(47,NULL,31,'99','99',NULL,'99'),(48,NULL,32,'999','999',NULL,'999');
UNLOCK TABLES;

--
-- Table structure for table `appraise`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appraise` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `driver_id` int DEFAULT NULL COMMENT '对应的司机的id',
  `comment` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '评价的内容',
  `order_id` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '对就的订单的id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='评价表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appraise`
--

LOCK TABLES `appraise` WRITE;
INSERT INTO `appraise` (`id`, `driver_id`, `comment`, `order_id`) VALUES (2,-1,'1','2024040519555318351'),(3,-1,'sdfa','2024040917354356774'),(4,-1,'saf','2024041321094757693');
UNLOCK TABLES;

--
-- Table structure for table `car`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `car` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `no` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '车牌号',
  `owner_id` int DEFAULT NULL COMMENT '所有者的id',
  `color` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '车辆颜色',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='车辆表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car`
--

LOCK TABLES `car` WRITE;
INSERT INTO `car` (`id`, `no`, `owner_id`, `color`) VALUES (4,'11222',11,'#A32E2E');
UNLOCK TABLES;

--
-- Table structure for table `chatroom`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chatroom` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '聊天室的id',
  `name` varchar(15) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '聊天室的名字（感觉应该在前端定义）',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `passenger_id` int DEFAULT NULL COMMENT '聊天室的乘客的id',
  `driver_id` int DEFAULT NULL COMMENT '司机的id',
  `manager_id` int DEFAULT NULL COMMENT '管理员的id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='聊天室';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chatroom`
--

LOCK TABLES `chatroom` WRITE;
INSERT INTO `chatroom` (`id`, `name`, `create_date`, `passenger_id`, `driver_id`, `manager_id`) VALUES (1,NULL,'2024-03-19 00:00:00',1,2,NULL),(2,NULL,'2024-03-19 00:00:00',1,3,NULL),(3,NULL,'2024-03-19 22:34:17',1,4,NULL),(4,NULL,'2024-04-09 18:39:33',12,24,NULL),(5,NULL,'2024-04-13 17:51:16',12,NULL,1),(6,NULL,'2024-04-13 20:50:33',34,24,NULL),(7,NULL,'2024-04-13 21:08:41',34,29,NULL),(8,NULL,'2024-04-13 21:09:32',34,NULL,1),(9,NULL,'2024-04-14 19:40:11',35,NULL,1),(10,NULL,'2024-04-19 14:28:26',12,31,NULL);
UNLOCK TABLES;

--
-- Table structure for table `driver`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `driver` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `driver_birthday` date DEFAULT NULL COMMENT '出生日',
  `driver_nation` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '民族',
  `driver_address` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '通信地址',
  `driver_license_id` int DEFAULT NULL COMMENT '驾驶证的id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `phone` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '手机号',
  `gender` varchar(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '性别 ',
  `state` int DEFAULT NULL COMMENT '状态',
  `name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '称谓',
  `account_id` int DEFAULT NULL COMMENT '帐号id',
  `account` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '帐号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='司机表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `driver`
--

LOCK TABLES `driver` WRITE;
INSERT INTO `driver` (`id`, `driver_birthday`, `driver_nation`, `driver_address`, `driver_license_id`, `update_time`, `phone`, `gender`, `state`, `name`, `account_id`, `account`) VALUES (24,'2024-04-24','汉','11',111,'2024-04-19 15:04:17','111','男',NULL,'张六',42,'22'),(29,'2024-04-09','汉','某村',1111111,'2024-04-13 20:42:49','100111111','男',NULL,'李四',38,'11'),(30,NULL,NULL,NULL,NULL,'2024-04-17 19:54:10',NULL,NULL,NULL,NULL,46,'66'),(31,'2024-04-25','汉','花木城杠杆啊的撒范德萨范德萨',1,'2024-04-19 14:28:12','99','男',NULL,'五王',47,'99'),(32,NULL,NULL,NULL,NULL,'2024-04-25 22:13:12',NULL,NULL,NULL,NULL,48,'999');
UNLOCK TABLES;

--
-- Table structure for table `driverlicense`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `driverlicense` (
  `id` int NOT NULL COMMENT '表的id',
  `license_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '驾驶证编号 ',
  `get_driver_license_date` date DEFAULT NULL COMMENT '获得驾驶证的日期',
  `driver_license_on` date DEFAULT NULL COMMENT '驾驶证有效期',
  `driver_license_off` date DEFAULT NULL COMMENT '驾驶证有效期止',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='驾驶证表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `driverlicense`
--

LOCK TABLES `driverlicense` WRITE;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `owner_id` int NOT NULL COMMENT '发出者的id',
  `id` int NOT NULL AUTO_INCREMENT COMMENT '信息的id',
  `data` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '信息的内容',
  `chat_room_id` int DEFAULT NULL COMMENT '这条信息对应的聊天室id',
  `owner_type` int DEFAULT NULL COMMENT '所有者的类型',
  `owner_name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '发出者的名字',
  `send_time` datetime DEFAULT NULL COMMENT '发送的时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='消息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
INSERT INTO `message` (`owner_id`, `id`, `data`, `chat_room_id`, `owner_type`, `owner_name`, `send_time`) VALUES (24,24,'sfa',4,2,'张三','2024-04-12 19:24:06'),(12,25,'saf',4,1,'李四','2024-04-12 19:39:58'),(24,26,'sfa',4,2,'张三','2024-04-12 19:44:19'),(24,27,'ff',4,2,'张三','2024-04-12 19:44:20'),(24,28,'ss',4,2,'张三','2024-04-12 19:44:21'),(24,29,'ff',4,2,'张三','2024-04-12 19:44:22'),(24,30,'ff',4,2,'张三','2024-04-12 19:44:23'),(24,31,'ff',4,2,'张三','2024-04-12 19:44:24'),(24,32,'ff',4,2,'张三','2024-04-12 19:44:26'),(24,33,'sdf\n',4,2,'张三','2024-04-12 19:51:16'),(24,34,'dfs\n',4,2,'张三','2024-04-12 19:51:19'),(24,35,'sdaf',4,2,'张三','2024-04-12 19:57:21'),(12,36,'什么？\n\n',4,1,'李四','2024-04-12 20:38:49'),(12,37,'2024040917354356774',5,1,'李四','2024-04-13 17:51:16'),(12,38,'2024041216415186448',5,1,'李四','2024-04-13 18:08:52'),(12,39,'q r ',5,1,'李四','2024-04-13 18:09:04'),(1,40,'请问？\n',5,3,'管理员1','2024-04-13 19:51:05'),(34,41,'saf',6,1,'张三','2024-04-13 20:50:40'),(29,42,'saf',7,2,'李四','2024-04-13 21:08:45'),(34,43,'11',7,1,'张三','2024-04-13 21:09:07'),(34,44,'2024041321080540132',1,1,'张三','2024-04-13 21:09:32'),(34,45,'fdsafsdfsdf',8,1,'张三','2024-04-13 21:09:38'),(34,46,'2024041321094757693',8,1,'张三','2024-04-13 21:09:55'),(34,47,'2024041321094757693',8,1,'张三','2024-04-13 21:10:42'),(35,48,'2024041419400796620',1,1,NULL,'2024-04-14 19:40:11'),(35,49,'saf',9,1,NULL,'2024-04-14 19:40:16'),(12,50,'safd',5,1,'李四','2024-04-18 21:57:22'),(12,51,'asfdsaf',4,1,'李四','2024-04-18 21:57:28'),(12,52,'2024-04-18 22:00:10',5,1,'李四','2024-04-18 22:00:13'),(12,53,'2024041821570788579',5,1,'李四','2024-04-18 22:00:18'),(12,54,'2024041914225678330',5,1,'李四','2024-04-19 14:25:49'),(31,55,'撒范德萨',10,2,'五王','2024-04-19 14:28:34'),(12,56,'saf',4,1,'李四','2024-04-19 15:03:17'),(1,57,'',5,3,'管理员1','2024-04-25 22:05:07'),(1,58,'',5,3,'管理员1','2024-04-25 22:05:08'),(1,59,'',5,3,'管理员1','2024-04-25 22:05:08'),(1,60,'',5,3,'管理员1','2024-04-25 22:05:08'),(1,61,'',5,3,'管理员1','2024-04-25 22:05:08'),(1,62,'',5,3,'管理员1','2024-04-25 22:05:08'),(1,63,'',5,3,'管理员1','2024-04-25 22:05:09'),(1,64,'甘桂花城地11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111',8,3,'管理员1','2024-04-25 22:05:50'),(12,65,'1',4,1,'李四','2024-06-20 23:10:51'),(24,66,'1',4,2,'张六','2024-06-20 23:11:05'),(24,67,'2',4,2,'张六','2024-06-20 23:12:01'),(12,68,'3',4,1,'李四','2024-06-20 23:12:17'),(24,69,'4',4,2,'张六','2024-06-20 23:16:06'),(12,70,'6',4,1,'李四','2024-06-20 23:16:16'),(24,71,'3',4,2,'张六','2024-06-20 23:16:27'),(12,72,'4\n',4,1,'李四','2024-06-20 23:16:49'),(24,73,'2',4,2,'张六','2024-06-20 23:17:26'),(12,74,'1',4,1,'李四','2024-06-20 23:19:13'),(12,75,'2',4,1,'李四','2024-06-20 23:19:31'),(24,76,'2',4,2,'张六','2024-06-20 23:19:40'),(24,77,'1',4,2,'张六','2024-06-20 23:23:20'),(12,78,'1',4,1,'李四','2024-06-20 23:25:05'),(24,79,'1',4,2,'张六','2024-06-20 23:26:51'),(12,80,'1',4,1,'李四','2024-06-20 23:28:31'),(24,81,'1',4,2,'张六','2024-06-20 23:28:38'),(24,82,'2',4,2,'张六','2024-06-20 23:28:43'),(24,83,'3',4,2,'张六','2024-06-20 23:28:48'),(24,84,'2',4,2,'张六','2024-06-20 23:28:54'),(24,85,'2',4,2,'张六','2024-06-20 23:29:00'),(24,86,'2',4,2,'张六','2024-06-20 23:29:15'),(24,87,'2',4,2,'张六','2024-06-20 23:31:49'),(24,88,'3',4,2,'张六','2024-06-20 23:32:01'),(24,89,'3',4,2,'张六','2024-06-20 23:33:06'),(24,90,'3',4,2,'张六','2024-06-20 23:34:19'),(24,91,'3',4,2,'张六','2024-06-20 23:35:11'),(24,92,'3',4,2,'张六','2024-06-20 23:36:22'),(24,93,'3',4,2,'张六','2024-06-20 23:37:16'),(24,94,'3',4,2,'张六','2024-06-20 23:39:08'),(24,95,'3',4,2,'张六','2024-06-20 23:39:37'),(24,96,'3',4,2,'张六','2024-06-20 23:40:25'),(24,97,'3',4,2,'张六','2024-06-20 23:40:49'),(24,98,'3',4,2,'张六','2024-06-20 23:41:59'),(24,99,'3',4,2,'张六','2024-06-20 23:42:21'),(24,100,'4',4,2,'张六','2024-06-20 23:42:26'),(24,101,'3',4,2,'张六','2024-06-20 23:42:58'),(24,102,'1',4,2,'张六','2024-06-20 23:43:21'),(24,103,'1',4,2,'张六','2024-06-20 23:43:24'),(24,104,'你在吗？',4,2,'张六','2024-06-20 23:43:31'),(12,105,'我在',4,1,'李四','2024-06-20 23:43:36');
UNLOCK TABLES;

--
-- Table structure for table `order`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `order_id` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '订单编号',
  `create_time` datetime DEFAULT NULL COMMENT '订单的发起时间',
  `finish_time` datetime DEFAULT NULL COMMENT '订单的完成时间',
  `driver_id` int DEFAULT NULL COMMENT '司机',
  `man_count` int DEFAULT NULL COMMENT '同行人数，这用来让乘客控制是否可以接单其它人拼单，和司机发出的接单的最大乘客数。',
  `passenger_id` int DEFAULT NULL COMMENT '乘客的id',
  `tips` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '备注，下单时的备注',
  `price` int DEFAULT NULL COMMENT '价格',
  `type` int DEFAULT NULL COMMENT '类型',
  `owner_id` int DEFAULT NULL COMMENT '订单创建者的id',
  `state` int DEFAULT NULL COMMENT '订单的状态',
  `ticket_id` int DEFAULT NULL COMMENT '对应的ticket的id，',
  `appraise_id` int DEFAULT NULL COMMENT '对应的评价的id',
  `other_id` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '是拼单订单产生的订单，会和另一个订单绑定，这个另一个订单的订单编号 ',
  `create_user_type` int DEFAULT NULL COMMENT '订单创建者的类型',
  `departure` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '上车点',
  `destination` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '下车点',
  `passenger_count` int DEFAULT NULL COMMENT '乘客的人数，拼单时乘客设置他们这一单的人数。',
  `join_order_can_join` int DEFAULT NULL COMMENT '判断拼单订单是否可以拼单的，还能用来判断是否是拼单订单。',
  `phone` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '联系用的手机号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_pk` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=156 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='订单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
UNLOCK TABLES;

--
-- Table structure for table `passenger`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `passenger` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `phone` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '手机号',
  `gender` varchar(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '性别',
  `state` int DEFAULT NULL COMMENT '状态',
  `name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '这个是应该是虚拟名，跟qq名一样。',
  `account` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '帐号',
  `account_id` int DEFAULT NULL COMMENT '对应的帐号表的id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='乘客表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `passenger`
--

LOCK TABLES `passenger` WRITE;
INSERT INTO `passenger` (`id`, `phone`, `gender`, `state`, `name`, `account`, `account_id`, `update_time`) VALUES (12,'100111111','男',NULL,'李四','11',38,'2024-04-12 17:44:53'),(34,'111','男',NULL,'张三','22',42,'2024-04-13 20:33:16'),(35,NULL,NULL,NULL,NULL,'44',45,NULL);
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `max_passenger_count` int DEFAULT NULL COMMENT '最大乘客数量或者说是票的数量',
  `start_time` datetime DEFAULT NULL COMMENT '发车时间',
  `status` int DEFAULT NULL COMMENT '是否可以购买',
  `price` int DEFAULT NULL COMMENT '价格',
  `departure` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '出发点',
  `destination` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '目的地',
  `sold_count` int DEFAULT NULL COMMENT '已经售出的数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='官方提供的票';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
UNLOCK TABLES;

--
-- Dumping routines for database 'onlinecarhailing'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-01-05 11:52:55
