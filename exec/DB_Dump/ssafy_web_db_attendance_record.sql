-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: i6E207.p.ssafy.io    Database: ssafy_web_db
-- ------------------------------------------------------
-- Server version	8.0.28-0ubuntu0.20.04.3

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
-- Table structure for table `attendance_record`
--

DROP TABLE IF EXISTS `attendance_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attendance_record` (
  `rec_id` int unsigned NOT NULL AUTO_INCREMENT,
  `att_id` int unsigned NOT NULL,
  `conf_id` int unsigned NOT NULL,
  `st_id` varchar(20) NOT NULL,
  `rec_in` datetime DEFAULT NULL,
  `rec_out` datetime DEFAULT NULL,
  PRIMARY KEY (`rec_id`),
  KEY `att_id` (`att_id`),
  KEY `conf_id` (`conf_id`),
  KEY `st_id` (`st_id`),
  CONSTRAINT `attendance_record_ibfk_1` FOREIGN KEY (`att_id`) REFERENCES `attendance` (`att_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `attendance_record_ibfk_2` FOREIGN KEY (`conf_id`) REFERENCES `attendance` (`conf_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `attendance_record_ibfk_3` FOREIGN KEY (`st_id`) REFERENCES `attendance` (`st_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendance_record`
--

LOCK TABLES `attendance_record` WRITE;
/*!40000 ALTER TABLE `attendance_record` DISABLE KEYS */;
INSERT INTO `attendance_record` VALUES (13,57,6,'chasang1234','2022-02-17 16:25:41','2022-02-17 16:26:05'),(14,56,6,'lsy1234','2022-02-17 16:26:23','2022-02-17 16:26:51'),(15,55,6,'lsy5656','2022-02-17 16:27:13','2022-02-17 16:27:43'),(16,54,6,'zihoon1234','2022-02-17 16:28:01','2022-02-17 16:28:31'),(17,57,6,'chasang1234','2022-02-17 16:35:27','2022-02-17 16:36:13'),(18,57,6,'chasang1234','2022-02-17 16:36:17','2022-02-17 16:37:11'),(31,219,21,'lsh6207','2022-02-17 17:49:16','2022-02-17 17:49:52'),(32,223,21,'KYJ6207','2022-02-17 17:49:18','2022-02-17 17:50:24'),(33,221,21,'JIS6207','2022-02-17 17:49:32','2022-02-17 17:51:06'),(34,228,22,'JIS6207','2022-02-17 17:53:29','2022-02-17 17:54:28'),(35,443,39,'lsy5656','2022-02-18 09:27:59','2022-02-18 10:06:16'),(36,474,41,'lsy1234','2022-02-18 10:06:04','2022-02-18 10:06:08');
/*!40000 ALTER TABLE `attendance_record` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-18 10:23:35
