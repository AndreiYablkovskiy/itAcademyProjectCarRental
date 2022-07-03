-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: car_rental
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `car`
--

DROP TABLE IF EXISTS `car`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `car` (
  `id` int NOT NULL AUTO_INCREMENT,
  `mark` varchar(255) NOT NULL,
  `model` varchar(255) NOT NULL,
  `car_status_id` int NOT NULL,
  `cost_for_one_hour` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `car_fk0` (`car_status_id`),
  CONSTRAINT `car_fk0` FOREIGN KEY (`car_status_id`) REFERENCES `car_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car`
--

LOCK TABLES `car` WRITE;
/*!40000 ALTER TABLE `car` DISABLE KEYS */;
INSERT INTO `car` VALUES (1,'Jeep','Compass',1,10),(2,'Audi','A4',1,10),(3,'Mazda','CX-9',2,15),(4,'Peugeot ','Expert',1,10),(5,'Geely ','Coolray',2,12),(6,'BMW','X6',1,17),(7,'BMW','Z4',1,17),(8,'Dodge','Magnum',2,12),(9,'Skoda','Kodiaq',1,10),(10,'MINI','Cooper',1,10),(18,'Dodge','Caliber',1,11),(19,'Dodge','Charger',1,12),(20,'MINI','Countryman',1,14),(21,'MINI','Paceman',1,13),(22,'Audi','SQ5',1,20),(23,'Audi','A6 Allroad',1,17),(24,'Audi','TT',1,15),(25,'Audi','Coupe',1,15),(26,'Peugeot ','Partner',1,13),(27,'Peugeot ','408',1,11),(28,'Geely ','Atlas',1,14),(29,'Geely ','Geometry A',1,20),(30,'BMW','X5M',1,35);
/*!40000 ALTER TABLE `car` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `car_status`
--

DROP TABLE IF EXISTS `car_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `car_status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car_status`
--

LOCK TABLES `car_status` WRITE;
/*!40000 ALTER TABLE `car_status` DISABLE KEYS */;
INSERT INTO `car_status` VALUES (1,'free'),(2,'busy'),(3,'repair');
/*!40000 ALTER TABLE `car_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_status`
--

DROP TABLE IF EXISTS `order_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_status`
--

LOCK TABLES `order_status` WRITE;
/*!40000 ALTER TABLE `order_status` DISABLE KEYS */;
INSERT INTO `order_status` VALUES (1,'created'),(2,'approved'),(3,'active'),(4,'completed'),(5,'incident'),(6,'canceled'),(7,'paid'),(8,'reject');
/*!40000 ALTER TABLE `order_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rental_order`
--

DROP TABLE IF EXISTS `rental_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rental_order` (
  `id` int NOT NULL AUTO_INCREMENT,
  `order_date` datetime NOT NULL,
  `order_status_id` int NOT NULL,
  `user_id` int NOT NULL,
  `rental_start` datetime NOT NULL,
  `rental_end` datetime NOT NULL,
  `car_id` int NOT NULL,
  `payment_value` double NOT NULL,
  `order_info` varchar(1000) DEFAULT NULL,
  `employee_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_fk0` (`order_status_id`),
  KEY `order_fk2_idx` (`user_id`),
  KEY `order_fk4` (`car_id`),
  CONSTRAINT `order_fk0` FOREIGN KEY (`order_status_id`) REFERENCES `order_status` (`id`),
  CONSTRAINT `order_fk2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `order_fk4` FOREIGN KEY (`car_id`) REFERENCES `car` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rental_order`
--

LOCK TABLES `rental_order` WRITE;
/*!40000 ALTER TABLE `rental_order` DISABLE KEYS */;
INSERT INTO `rental_order` VALUES (1,'2022-06-06 00:06:44',1,25,'2022-06-07 00:06:00','2022-06-08 00:06:00',9,240,'null\n@Admin\n test\n@admin admin: \ngj',NULL),(2,'2022-06-06 00:25:14',1,25,'2022-06-06 00:25:00','2022-06-07 00:25:00',1,240,'null\n@admin admin: \nadadad',NULL),(3,'2022-06-06 01:24:07',1,25,'2022-06-10 01:24:00','2022-06-11 01:24:00',8,288,NULL,NULL),(4,'2022-06-06 01:28:21',1,25,'2022-06-10 01:28:00','2022-06-12 01:28:00',5,576,NULL,NULL),(5,'2022-06-06 01:29:42',1,25,'2022-06-07 01:29:00','2022-06-09 01:29:00',4,480,NULL,NULL),(6,'2022-06-06 01:30:36',1,25,'2022-06-07 01:30:00','2022-06-08 01:30:00',4,240,NULL,NULL),(7,'2022-06-06 01:31:47',1,27,'2022-06-09 01:31:00','2022-06-11 01:31:00',9,480,NULL,NULL),(8,'2022-06-06 01:33:05',1,25,'2022-06-11 01:33:00','2022-06-13 01:33:00',4,480,NULL,NULL),(9,'2022-06-06 01:35:23',1,25,'2022-06-11 01:35:00','2022-06-12 01:35:00',4,240,NULL,NULL),(10,'2022-06-06 01:40:10',1,25,'2022-06-08 01:40:00','2022-06-09 01:40:00',5,288,'',NULL),(11,'2022-06-06 01:43:37',1,25,'2022-06-11 01:43:00','2022-06-19 01:43:00',2,1920,NULL,NULL),(12,'2022-06-06 11:30:56',1,25,'2022-06-07 11:30:00','2022-06-08 11:30:00',1,240,NULL,NULL),(13,'2022-06-06 11:50:36',1,25,'2022-06-07 11:50:00','2022-06-08 11:50:00',1,240,NULL,NULL),(14,'2022-06-06 11:51:09',6,26,'2022-06-08 11:51:00','2022-06-09 11:51:00',5,288,NULL,NULL),(15,'2022-06-06 12:46:09',1,25,'2022-06-07 12:46:00','2022-06-08 12:46:00',1,240,NULL,NULL),(16,'2022-06-06 12:47:25',1,26,'2022-06-10 14:00:00','2022-06-12 12:00:00',9,460,NULL,NULL),(17,'2022-06-06 12:48:49',1,25,'2022-06-10 14:00:00','2022-06-12 14:00:00',8,576,NULL,NULL),(18,'2022-06-06 12:50:35',1,25,'2022-06-10 12:50:00','2022-06-12 12:50:00',8,576,NULL,NULL),(19,'2022-06-06 14:18:49',1,25,'2022-06-07 14:18:00','2022-06-08 14:18:00',9,240,NULL,NULL),(20,'2022-06-06 14:34:27',1,25,'2022-06-10 14:34:00','2022-06-12 14:34:00',1,480,NULL,NULL),(21,'2022-06-06 14:37:14',1,25,'2022-06-09 14:37:00','2022-06-11 14:37:00',3,720,NULL,NULL),(22,'2022-06-06 14:47:00',1,25,'2022-06-07 14:46:00','2022-06-08 14:46:00',8,288,'null\n@admin admin: \n213',NULL),(23,'2022-06-06 14:54:37',6,26,'2022-06-07 14:54:00','2022-06-09 14:54:00',1,480,NULL,NULL),(24,'2022-06-06 14:58:40',1,27,'2022-06-07 14:58:00','2022-06-11 14:58:00',6,1632,NULL,NULL),(25,'2022-06-06 15:21:30',1,25,'2022-06-07 15:21:00','2022-06-10 15:21:00',1,720,NULL,NULL),(26,'2022-06-06 16:02:00',1,27,'2022-06-07 16:01:00','2022-06-08 16:01:00',9,240,NULL,NULL),(27,'2022-06-06 16:12:24',4,27,'2022-06-10 16:12:00','2022-06-12 16:12:00',9,480,NULL,'Admin'),(28,'2022-06-07 13:03:27',8,25,'2022-06-08 13:00:00','2022-06-10 13:00:00',2,480,'Bad user very bad',NULL),(29,'2022-06-07 13:34:23',5,25,'2022-06-11 13:34:00','2022-06-12 13:34:00',9,240,' bad user good user','Admin'),(30,'2022-06-08 18:11:49',6,30,'2022-06-10 18:11:00','2022-06-12 18:11:00',1,480,NULL,NULL),(31,'2022-06-08 18:13:42',5,30,'2022-06-09 18:13:00','2022-06-10 18:13:00',5,288,'null @admin admin baaad\n @admin admin: 123','Admin'),(32,'2022-06-08 21:32:49',4,26,'2022-06-09 21:32:00','2022-06-10 21:32:00',2,240,'Good userand test test2','Admin'),(33,'2022-06-09 18:36:51',8,30,'2022-06-10 18:36:00','2022-06-12 18:36:00',1,480,'null @Admin rre','Admin'),(34,'2022-06-09 18:50:30',6,30,'2022-06-15 18:50:00','2022-06-19 18:50:00',9,960,NULL,NULL),(35,'2022-06-09 19:07:20',1,30,'2022-06-16 19:07:00','2022-06-17 19:07:00',6,408,NULL,NULL),(36,'2022-06-09 19:08:08',1,30,'2022-06-17 19:07:00','2022-06-18 19:07:00',6,408,NULL,NULL),(37,'2022-06-09 19:09:31',1,30,'2022-06-10 19:09:00','2022-06-11 19:09:00',4,240,NULL,NULL),(38,'2022-06-09 19:11:15',6,30,'2022-06-10 19:11:00','2022-06-11 19:11:00',9,240,NULL,NULL),(39,'2022-06-09 19:15:48',8,30,'2022-06-10 19:15:00','2022-06-12 19:15:00',1,480,'null hi test','Admin'),(40,'2022-06-10 14:25:58',5,30,'2022-06-12 14:25:00','2022-06-14 14:25:00',1,480,' \n@Admin\n goood user\n@Admin\n test\n@Admin\n @Admin\r\ngoood user\r\n@Admin2\r\nadgagda\n@Admin\n fgsdgadafdasdgasdfadafdgadga\n@Admin\n dawdkjhdajkcfhjkh\\\r\nadl;kfa;lkfla\\\r\nlk;jklkjadg\r\nklsdjgl;akd;ald\r\nl;kjdlg;ja;lkd\n@Admin\n \n@Admin\n \n@Admin\n \n@Admin\n \n@Admin\n \n@Admin\n ','Admin'),(41,'2022-06-10 14:54:24',5,30,'2022-06-11 14:54:00','2022-06-12 14:54:00',7,408,'  @Admin test @admin admin: 555','Admin'),(42,'2022-06-10 18:11:01',6,25,'2022-06-11 18:10:00','2022-06-12 18:10:00',1,240,' ',NULL),(43,'2022-06-10 18:24:40',8,30,'2022-06-12 18:24:00','2022-06-13 18:24:00',4,240,'  @admin admin: test12','Admin'),(44,'2022-06-10 18:32:48',4,30,'2022-06-11 18:32:00','2022-06-12 18:32:00',9,240,' ','Admin'),(45,'2022-06-10 18:45:46',4,24,'2022-06-12 18:45:00','2022-06-15 18:45:00',7,1224,' ','Admin'),(46,'2022-06-10 18:46:24',6,24,'2022-06-12 18:46:00','2022-06-14 18:46:00',6,816,' ',NULL),(47,'2022-06-10 18:48:02',6,24,'2022-06-11 18:47:00','2022-06-12 18:48:00',6,408,' ',NULL),(48,'2022-06-10 18:48:28',8,24,'2022-06-11 18:48:00','2022-06-12 18:48:00',6,408,' ','Admin'),(49,'2022-06-10 18:49:27',6,24,'2022-06-12 18:49:00','2022-06-16 18:49:00',6,1632,' ','Admin'),(50,'2022-06-10 18:50:40',4,24,'2022-06-12 18:50:00','2022-06-15 18:50:00',6,1224,' \n@admin admin: \n11','Admin'),(51,'2022-06-10 18:51:51',4,24,'2022-06-11 18:51:00','2022-06-16 18:51:00',6,2040,' ','Admin'),(52,'2022-06-10 23:18:35',6,30,'2022-06-11 23:18:00','2022-06-14 23:18:00',7,1224,' ',NULL),(53,'2022-06-10 23:22:13',4,30,'2022-06-11 23:22:00','2022-06-13 23:22:00',7,816,'  @admin admin: test','Admin'),(54,'2022-06-15 12:43:34',4,24,'2022-06-16 12:43:00','2022-06-17 12:43:00',2,240,'  @admin admin: good user ','Admin'),(55,'2022-06-17 10:45:39',4,30,'2022-06-18 10:45:00','2022-06-19 10:45:00',10,240,'  @admin admin: good @admin admin: dtp','Admin'),(56,'2022-06-17 11:47:25',1,30,'2022-06-18 11:47:00','2022-06-19 11:47:00',4,240,' ',NULL),(57,'2022-06-17 11:51:29',1,30,'2022-06-18 11:51:00','2022-06-19 11:51:00',10,240,' \n@admin admin: \n123',NULL),(58,'2022-06-17 13:29:11',1,30,'2022-06-19 13:29:00','2022-06-18 13:29:00',2,-240,' ',NULL),(59,'2022-06-17 14:29:15',6,30,'2022-06-19 14:29:00','2022-06-20 14:29:00',4,240,' ',NULL),(60,'2022-06-18 09:09:50',4,30,'2022-06-19 09:00:00','2022-06-20 09:00:00',2,240,'  @admin admin: good user @admin admin: bad user @admin admin: lkawjdflakjfda;ljfd;lajfdlajfoiawjdlkajflkadfakljgghrfth','Admin'),(61,'2022-06-20 15:15:49',6,30,'2022-06-21 15:00:00','2022-06-23 15:00:00',10,480,' ',NULL),(62,'2022-06-20 15:48:37',8,30,'2022-06-21 15:00:00','2022-06-22 15:00:00',10,240,' ','Admin'),(63,'2022-06-20 15:57:30',4,30,'2022-06-21 15:57:00','2022-06-26 15:57:00',7,2040,' ','Admin'),(64,'2022-06-20 17:54:47',6,30,'2022-06-21 17:54:00','2022-06-22 17:54:00',10,240,' ',NULL),(65,'2022-06-20 17:55:34',4,30,'2022-06-22 17:55:00','2022-06-24 17:55:00',10,480,' ','Admin'),(66,'2022-06-20 17:57:47',4,30,'2022-06-21 17:57:00','2022-06-23 17:57:00',10,480,' ','Admin'),(67,'2022-06-20 18:35:09',6,30,'2022-06-21 18:35:00','2022-06-23 18:35:00',2,480,' ',NULL),(68,'2022-06-20 18:35:51',4,30,'2022-06-21 18:35:00','2022-06-23 18:35:00',2,480,'  @admin admin: dafd','Admin'),(69,'2022-06-20 18:52:18',6,31,'2022-06-21 18:52:00','2022-06-26 18:52:00',2,1200,' \n@admin admin: \n321',NULL),(70,'2022-06-21 16:47:44',4,31,'2022-06-22 16:47:00','2022-06-23 16:47:00',2,240,' ','Admin'),(71,'2022-06-21 17:34:23',4,30,'2022-06-25 17:33:00','2022-06-28 17:33:00',9,720,' \n@admin admin: \ndafafdad\n@admin admin: \nadgadfads\n@admin admin: \ndgasdadfa','Admin'),(72,'2022-06-21 17:55:46',1,31,'2022-06-22 17:55:00','2022-06-23 17:55:00',10,240,' \n@admin admin: \nsgyahrf\n@admin admin: \ngssartgtuhut\n@admin admin: \nadfwfdadf\n@admin admin: \ndafdgtaarae\n@admin admin: \ntest',NULL),(73,'2022-06-21 17:59:46',8,31,'2022-06-22 17:59:00','2022-06-24 17:59:00',4,480,' \n@admin admin: \ngsdafagrsf\n@admin admin: \ndgawdfaa','Admin'),(74,'2022-06-23 20:18:32',4,31,'2022-06-24 20:18:00','2022-06-25 20:18:00',4,240,' \n@admin admin: \n123','Admin'),(75,'2022-06-24 20:08:42',8,31,'2022-06-26 20:08:00','2022-06-27 20:08:00',6,408,' \n@admin admin: \nadafdad','Admin'),(76,'2022-06-27 13:58:14',1,31,'2022-06-28 13:58:00','2022-06-29 13:58:00',6,408,' \n@admin admin: \n445\n@admin admin: \n123',NULL),(77,'2022-06-28 16:08:51',4,31,'2022-06-29 16:08:00','2022-06-30 16:08:00',4,240,' \n@admin admin: \n123','Admin'),(78,'2022-06-28 19:59:25',8,31,'2022-06-29 19:59:00','2022-06-30 19:59:00',10,240,' ','Admin'),(79,'2022-06-28 20:04:50',6,31,'2022-06-30 20:04:00','2022-07-01 20:04:00',9,240,' ',NULL),(80,'2022-06-28 20:05:33',4,31,'2022-06-29 20:05:00','2022-06-30 20:05:00',9,240,' \n@admin admin: \nnice client give him coockie next time\n@admin admin: \ngave it','Admin'),(81,'2022-06-28 23:43:25',4,31,'2022-06-30 23:43:00','2022-07-01 23:43:00',6,408,' ','Admin'),(82,'2022-06-29 09:29:21',4,31,'2022-06-30 09:00:00','2022-07-01 09:00:00',7,408,' ','Admin'),(83,'2022-06-29 13:03:52',4,31,'2022-06-30 13:00:00','2022-07-01 13:00:00',6,408,' \n@admin admin: \ngood client','Admin'),(84,'2022-06-30 11:19:28',1,31,'2025-06-30 21:00:00','2025-06-30 22:00:00',1,10,' ',NULL),(85,'2022-06-30 11:20:51',1,31,'2025-06-30 21:00:00','2025-06-30 22:00:00',1,10,' ',NULL),(86,'2022-06-30 11:23:40',1,31,'2025-06-30 21:00:00','2025-06-30 22:00:00',1,10,' ',NULL),(87,'2022-06-30 11:25:41',1,31,'2022-06-30 14:25:41','2022-07-01 00:25:41',1,100,' ',NULL),(88,'2022-06-30 11:49:06',1,31,'2022-06-30 21:49:06','2022-07-01 07:49:06',1,100,' ',NULL),(89,'2022-06-30 13:20:13',4,35,'2022-07-01 13:20:00','2022-07-02 13:20:00',2,240,' \n@admin admin: \nIts BOND!\n@admin admin: \nYEA!!','Admin'),(90,'2022-06-30 15:23:36',6,35,'2022-06-30 16:00:00','2022-06-30 21:00:00',25,75,' ',NULL),(91,'2022-06-30 15:24:30',4,35,'2022-06-30 16:25:00','2022-06-30 21:25:00',2,50,' \n@admin admin: \ntest','Admin'),(94,'2022-06-30 15:57:16',1,36,'2022-07-01 01:57:17','2022-07-01 11:57:17',1,100,' ',NULL),(95,'2022-06-30 16:08:20',4,35,'2022-07-01 16:08:00','2022-07-03 16:08:00',6,816,' \n@admin admin: \ngood client','Admin'),(96,'2022-06-30 16:08:42',6,35,'2022-07-01 16:08:00','2022-07-03 16:08:00',28,672,' ',NULL),(97,'2022-06-30 19:19:14',4,35,'2022-06-30 20:19:00','2022-07-01 19:19:00',2,230,' \nChuck Norris: \ntest\nChuck Norris: \ntest2','Admin'),(98,'2022-06-30 21:25:40',4,37,'2022-06-30 22:00:00','2022-07-01 22:00:00',7,408,' \nAdmin Admin: \nbad client\nAdmin Admin: \nyea\r\n','Admin');
/*!40000 ALTER TABLE `rental_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `repair_payment`
--

DROP TABLE IF EXISTS `repair_payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `repair_payment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `value` double NOT NULL,
  `description` varchar(1000) NOT NULL,
  `order_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `repair_payment_fk0` (`order_id`),
  CONSTRAINT `repair_payment_fk0` FOREIGN KEY (`order_id`) REFERENCES `rental_order` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `repair_payment`
--

LOCK TABLES `repair_payment` WRITE;
/*!40000 ALTER TABLE `repair_payment` DISABLE KEYS */;
INSERT INTO `repair_payment` VALUES (1,100,'test description',1),(2,100,'repair half car',27),(3,155,'all car ',40),(4,111,'test',41),(5,12,'test',41),(6,44,'ttttt',41),(7,1111,'test1111',51),(8,2222,'test2222',51),(9,1000,'repair half car',53),(10,500,'afdasdaffdaf',53),(11,255,'123',54),(12,122,'ttty',54),(13,1000,'repair half car',55),(14,50,'fara',55),(15,10000,'half car',60),(16,100,'fara',60),(17,255,'kj;',65),(18,22,'adg',68),(19,22222,'112',68),(20,555,'test test test.\r\ntest2 test2 test2.\r\nsssssss\r\naaaaaaa\r\n',70),(21,255,'33333',77),(22,1000,'need to repair windshield',80),(23,50,'for moral damage',80),(24,444,'lalalalala',82),(25,333,'lala',82),(26,8000,'half car',83),(27,50,'some work',83),(28,800,'some work',89),(29,10,'and some little more',89),(30,100,'last test',91),(31,1000,'some work',95),(32,100,'test',97),(33,10000,'need to repair half car',98),(34,50,'some work',98);
/*!40000 ALTER TABLE `repair_payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_USER'),(2,'ROLE_ADMIN');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `firstname` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `passport_number` varchar(13) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (24,'Admin','Admin','Admin','1234567890123','$2a$10$hJbNrW9AN0faUdZCxivNlOEV/29XeKGL9pM7GG.8xh5BABTTi2Jay',''),(25,'user0','user0','user0','1234567891','$2a$10$EZlfu14lxNBlgy0.JQ8J..5bV.GCS/3uJrDxRS18UFaS5rWUn8hha',''),(26,'user111','user111','user111','1234567890','$2a$10$vT3TOFbDusSvE3eRYR8nse6ZlDxUGujKlGeKelWiFnm/L9.o9F6ei',''),(27,'user2','user2','user2','12345908','$2a$10$D7nJ3JlUArkoh5EEdHU./.6e9f.FrCJIbYQast8U7AAxXnVHtIKsS',''),(28,'user3','user3','user3','1234567890','$2a$10$5gvIUXDX1bfl1TvyXiMTs.jhvp/ONwR9WI6l0ihM1gmm1/ypMJVBa',''),(30,'user00','user000','user000','1234567890','$2a$10$m8.GkOpadVXV216kWybpQuQTdTP038qteqBqfmKE8IH.1Gcp2hpiW','tensaii@mail.ru'),(31,'user12','Cat','Kompot','1234567890','$2a$10$k49kYOg7goSuSA.jZTxwv.GqDBvmzZ3Ab8tGR1J3DwHwBP5hHlrxC','tensaii@mail.ru'),(32,'user11','user11','user11','1234567890','$2a$10$h/NF/IHv/AeN9coYpBTfLOe8yv7YfucKo5HhpL1q1YVfsybvXYMj.','some@mail.ru'),(33,'user22','Cat','Kompot','1234567890','$2a$10$iX69vDiBZqbmMtPPlMSx4eNYwetlq6v9ROC3FMHkfyPMEg90WIhR2','some@mail.ru'),(34,'userr','Cat','Kompot','1234567890','$2a$10$4QKJLJuoybOk2qFAPcmV0OF3M6lRDUyyJjgZKfSmMwfT2UtL4f7si','some@mail.ru'),(35,'user008','James','Bond','MI6secret!','$2a$10$kP4EPqm/HV/JbTiBPxsq8.0LLXPrEA8CzPtORbqZpxTxe62Xnl3vO','tensaii@mail.ru'),(36,'test','test','test','1234567890','$2a$10$cmj3iTV7jsVtL.mYmPABuOE38xT1Ei0F/FUbtnm4P3lH5fLp2ItEe','tensaii@mail.ru'),(37,'user777','user777','user777','1234567890','$2a$10$zD1c/dvFBYRM6z8LOEWJ1uAe8MgLCs0C5a25LdCyV0AEUoRGHXFsC','tensaii@mail.ru');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_role_fk1` (`role_id`),
  KEY `user_role_fk0` (`user_id`),
  CONSTRAINT `user_role_fk0` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `user_role_fk1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (19,24,2),(22,27,1),(23,28,1),(26,26,1),(30,25,1),(31,30,1),(33,32,1),(34,33,1),(36,34,1),(39,35,1),(47,31,1),(48,36,1),(49,37,1);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-07-03 21:15:01
