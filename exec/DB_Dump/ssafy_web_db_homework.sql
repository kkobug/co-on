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
-- Table structure for table `homework`
--

DROP TABLE IF EXISTS `homework`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `homework` (
  `hw_id` int unsigned NOT NULL AUTO_INCREMENT,
  `study_id` int unsigned NOT NULL,
  `tchr_id` varchar(20) NOT NULL,
  `hw_title` varchar(50) NOT NULL,
  `hw_content` text NOT NULL,
  `hw_deadline` datetime DEFAULT NULL,
  `hw_posted` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`hw_id`),
  KEY `study_id` (`study_id`),
  KEY `tchr_id` (`tchr_id`),
  CONSTRAINT `homework_ibfk_1` FOREIGN KEY (`study_id`) REFERENCES `studyroom` (`study_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `homework_ibfk_2` FOREIGN KEY (`tchr_id`) REFERENCES `teacher` (`tchr_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `homework`
--

LOCK TABLES `homework` WRITE;
/*!40000 ALTER TABLE `homework` DISABLE KEYS */;
INSERT INTO `homework` VALUES (2,39,'teacher1','1학년 A반 소인수분해','강의를 듣고 과제를 풀어봅시다.','2022-02-21 09:00:00','2022-02-17 15:07:36'),(3,39,'teacher1','1학년 A반 최대공약수','최대공약수 문제를 풀고 제출해주세요.','2022-02-21 09:00:00','2022-02-17 15:18:52'),(6,7,'artKim1234','중간시험','초상화그리기','2022-02-23 09:00:00','2022-02-17 15:26:37'),(8,12,'englishHan1234','1-1 독해','첨부된 파일을 읽고 해석한 내용을 제출하세요','2022-02-18 08:00:00','2022-02-17 15:55:39'),(9,6,'artKim1234','과제 내주세요!','초상화그리기','2022-02-23 09:00:00','2022-02-17 20:11:28'),(10,45,'teacher1','1학년 A반 소인수분해','강의를 듣고 과제를 풀어봅시다.','2022-02-21 09:00:00','2022-02-17 15:07:36'),(11,45,'teacher1','1학년 A반 최대공약수','최대공약수 문제를 풀고 제출해주세요.','2022-02-21 09:00:00','2022-02-17 15:18:52'),(12,46,'robin1202','영어 단어 외우기','영어 단어 외우기','2022-02-20 09:00:00','2022-02-18 00:12:02'),(13,46,'robin1202','영어 시험','프린트도 확인 바랍니다.','2022-02-23 09:00:00','2022-02-18 00:21:42'),(14,39,'teacher1','1학년 A반 행렬','1학년 A반 행렬','2022-02-24 09:00:00','2022-02-18 02:04:46'),(15,39,'teacher1','3학년 B반 정렬','정렬을 배워봅시다','2022-02-25 09:00:00','2022-02-18 02:17:27'),(16,6,'artKim1234','안녕하세요','과제입니다.','2022-02-23 09:00:00','2022-02-18 02:38:51'),(17,6,'artKim1234','sds','sss','2022-02-08 09:00:00','2022-02-18 02:39:03');
/*!40000 ALTER TABLE `homework` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-18 10:23:33
