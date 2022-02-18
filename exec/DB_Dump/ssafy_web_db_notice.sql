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
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notice` (
  `notice_id` int unsigned NOT NULL AUTO_INCREMENT,
  `study_id` int unsigned NOT NULL,
  `tchr_id` varchar(20) NOT NULL,
  `notice_title` varchar(50) NOT NULL,
  `notice_content` text NOT NULL,
  `notice_posted` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`notice_id`),
  KEY `study_id` (`study_id`),
  KEY `tchr_id` (`tchr_id`),
  CONSTRAINT `notice_ibfk_1` FOREIGN KEY (`study_id`) REFERENCES `studyroom` (`study_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `notice_ibfk_2` FOREIGN KEY (`tchr_id`) REFERENCES `teacher` (`tchr_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice`
--

LOCK TABLES `notice` WRITE;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
INSERT INTO `notice` VALUES (8,7,'artKim1234','미술 시험관련 공지','시험 범위는 160p~ 235p 까지 입니다.\r\n프린트도 잘 확인해 주세요','2022-02-17 14:42:41'),(10,39,'teacher1','원격 수업 안내','2.19(금)은 원격수업으로 진행합니다. 시간에 맞춰 입장해주시길 바랍니다.','2022-02-18 09:58:14'),(16,39,'teacher1','출석 확인 안내','출석 인증 관련 공지입니다. 첨부된 파일을 확인하여 수업에 참석해주시길 바랍니다.','2022-02-01 15:01:31'),(18,6,'artKim1234','안녕하세요!','hi','2022-02-17 20:10:25'),(19,45,'teacher1','원격수업 안내','2022.02.17(목)은 원격수업으로 학습을 진행합니다. ','2022-02-16 15:01:31'),(20,45,'teacher1','원격 수업 안내','2.18(금)은 원격수업으로 진행합니다. 시간에 맞춰 입장해주시길 바랍니다.','2022-02-17 15:08:58'),(21,45,'teacher1','과제 제출기한 엄수','제출 기한이 지난 과제는 감점이니 제출 기한 내에 제출해주시길 바랍니다.','2022-01-31 15:01:31'),(22,45,'teacher1','출석 확인 안내','출석 인증 관련 공지입니다. 첨부된 파일을 확인하여 수업에 참석해주시길 바랍니다.','2022-02-01 15:01:31'),(23,46,'robin1202','시험 관련 공지입니다,','시험범위는 교과서 150p~230p 까지 입니다.','2022-02-18 00:09:35'),(24,46,'robin1202','영어 과제 재구입 관련 공지','출판사의 문제로 시간이 더 필요 할 것 같습니다.','2022-02-18 00:11:21'),(25,46,'robin1202','단어장 프린트입니다.','다운로드 해주세요','2022-02-18 00:23:28'),(26,6,'artKim1234','반갑습니다.','hihi','2022-02-18 02:38:27');
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;
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
