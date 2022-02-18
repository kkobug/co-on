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
-- Table structure for table `studyroom`
--

DROP TABLE IF EXISTS `studyroom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `studyroom` (
  `study_id` int unsigned NOT NULL AUTO_INCREMENT,
  `tchr_id` varchar(20) NOT NULL,
  `study_name` varchar(15) NOT NULL,
  `study_desc` text,
  PRIMARY KEY (`study_id`),
  KEY `tchr_id` (`tchr_id`),
  CONSTRAINT `studyroom_ibfk_1` FOREIGN KEY (`tchr_id`) REFERENCES `teacher` (`tchr_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studyroom`
--

LOCK TABLES `studyroom` WRITE;
/*!40000 ALTER TABLE `studyroom` DISABLE KEYS */;
INSERT INTO `studyroom` VALUES (4,'japenKang1234','일본어1-1','일본어 수업'),(5,'japenKang1234','일본어1-2','일본어 수업'),(6,'artKim1234','미술1-1','미술 수업'),(7,'artKim1234','미술1-2','미술 수업'),(8,'athleticHa1234','체육1-2','체육 수업'),(9,'athleticHa1234','체육1-1','체육 수업'),(10,'chineseJo1234','한문1-1','한문 수업'),(11,'chineseJo1234','한문1-2','한문 수업'),(12,'englishHan1234','영어1-1','영어 수업'),(13,'englishHan1234','영어1-2','영어 수업'),(14,'historyJo1234','역사1-1','역사 수업'),(15,'historyJo1234','역사1-2','역사 수업'),(16,'homeKim1234','가정1-1','가정 수업'),(17,'homeKim1234','가정1-2','가정 수업'),(18,'literKim1234','문학1-1','문학 수업'),(19,'literKim1234','문학1-2','문학 수업'),(20,'mathPark1234','수학1-1','수학 수업'),(21,'mathPark1234','수학1-2','수학 수업'),(22,'moralO1234','도덕1-1','도덕 수업'),(23,'moralO1234','도덕1-2','도덕 수업'),(24,'musicUw1234','음악1-1','음악 수업'),(25,'musicUw1234','음악1-2','음악 수업'),(26,'scienceMoon1234','과학1-1','과학 수업'),(27,'scienceMoon1234','과학1-2','과학 수업'),(28,'socialLee1234','사회1-1','사회 수업'),(29,'socialLee1234','사회1-2','사회 수업'),(39,'teacher1','1학년 A반','1학년 A반'),(40,'teacher1','1학년 C반','1학년 C반'),(41,'teacher1','2학년 A반','2학년 A반'),(42,'teacher1','3학년 A반','3학년 A반'),(43,'Co-on양지훈','공통PJT','공통PJT'),(44,'YJH6207','공통PJT','공통PJT'),(45,'teacher1','3학년 B반 ','컴퓨터 공부'),(46,'robin1202','영어 1-1','영어 수업입니다.'),(47,'robin1202','영어 1-2','영어 수업입니다.'),(48,'robin1202','영어 1-3','영어 수업입니다.');
/*!40000 ALTER TABLE `studyroom` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-18 10:23:36
