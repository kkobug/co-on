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
-- Table structure for table `homework_file`
--

DROP TABLE IF EXISTS `homework_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `homework_file` (
  `file_id` int unsigned NOT NULL AUTO_INCREMENT,
  `file_name` varchar(255) DEFAULT NULL,
  `file_origin_name` varchar(255) DEFAULT NULL,
  `file_path` varchar(255) DEFAULT NULL,
  `hw_id` int unsigned DEFAULT NULL,
  `file_ext` varchar(255) DEFAULT NULL,
  `file_size` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`file_id`),
  KEY `hw_id` (`hw_id`),
  CONSTRAINT `homework_file_ibfk_1` FOREIGN KEY (`hw_id`) REFERENCES `homework` (`hw_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `homework_file`
--

LOCK TABLES `homework_file` WRITE;
/*!40000 ALTER TABLE `homework_file` DISABLE KEYS */;
INSERT INTO `homework_file` VALUES (1,'HW0217060735728반_번호_이름_1학년_A반_소인수분해.pdf','반_번호_이름_1학년_A반_소인수분해.pdf',NULL,2,NULL,NULL),(2,'HW0217061851688반_번호_이름_1학년A반_최대공약수.pdf','반_번호_이름_1학년A반_최대공약수.pdf',NULL,3,NULL,NULL),(3,'HW0217065538900반_번호_이름_1-1독해.txt','반_번호_이름_1-1독해.txt',NULL,8,NULL,NULL),(5,'HW0217152142264영어 프린트.pdf','영어 프린트.pdf',NULL,13,NULL,NULL),(6,'HW0217171727295배경이미지1-1.png','배경이미지1-1.png',NULL,15,NULL,NULL),(7,'HW0217173851041jce-16-3-147 (1).pdf','jce-16-3-147 (1).pdf',NULL,16,NULL,NULL),(8,'HW0217173903397jce-16-3-147 (1).pdf','jce-16-3-147 (1).pdf',NULL,17,NULL,NULL);
/*!40000 ALTER TABLE `homework_file` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-18 10:23:30
