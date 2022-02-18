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
-- Table structure for table `student_homework`
--

DROP TABLE IF EXISTS `student_homework`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_homework` (
  `st_hwid` int unsigned NOT NULL AUTO_INCREMENT,
  `hw_id` int unsigned NOT NULL,
  `study_id` int unsigned NOT NULL,
  `tchr_id` varchar(20) NOT NULL,
  `st_id` varchar(20) NOT NULL,
  `st_hwcontent` text,
  `st_hwposted` datetime DEFAULT NULL,
  `st_hwscore` int DEFAULT NULL,
  PRIMARY KEY (`st_hwid`),
  KEY `hw_id` (`hw_id`),
  KEY `study_id` (`study_id`),
  KEY `tchr_id` (`tchr_id`),
  KEY `st_id` (`st_id`),
  CONSTRAINT `student_homework_ibfk_1` FOREIGN KEY (`hw_id`) REFERENCES `homework` (`hw_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `student_homework_ibfk_2` FOREIGN KEY (`study_id`) REFERENCES `studyroom` (`study_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `student_homework_ibfk_3` FOREIGN KEY (`tchr_id`) REFERENCES `teacher` (`tchr_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `student_homework_ibfk_4` FOREIGN KEY (`st_id`) REFERENCES `student` (`st_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=150 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_homework`
--

LOCK TABLES `student_homework` WRITE;
/*!40000 ALTER TABLE `student_homework` DISABLE KEYS */;
INSERT INTO `student_homework` VALUES (16,2,39,'teacher1','zihoon1234','이상엽 과제 제출','2022-02-18 01:43:52',NULL),(17,2,39,'teacher1','lsy5656','정인수 과제 제출',NULL,NULL),(18,2,39,'teacher1','lsy1234','이승훈 과제 제출',NULL,NULL),(19,2,39,'teacher1','chasang1234','차상훈 과제 제출',NULL,NULL),(20,2,39,'teacher1','yunzi1234','김윤지 과제 제출',NULL,NULL),(23,3,39,'teacher1','zihoon1234','과제입니다!','2022-02-18 02:02:35',NULL),(24,3,39,'teacher1','lsy5656',NULL,NULL,NULL),(25,3,39,'teacher1','lsy1234',NULL,NULL,NULL),(26,3,39,'teacher1','chasang1234',NULL,NULL,NULL),(27,3,39,'teacher1','yunzi1234',NULL,NULL,NULL),(30,6,7,'artKim1234','bdu1234',NULL,NULL,NULL),(31,6,7,'artKim1234','bys1234',NULL,NULL,NULL),(32,6,7,'artKim1234','gsm1234',NULL,NULL,NULL),(33,6,7,'artKim1234','hjh1234',NULL,NULL,NULL),(34,6,7,'artKim1234','jjh1234',NULL,NULL,NULL),(35,6,7,'artKim1234','jty1234',NULL,NULL,NULL),(36,6,7,'artKim1234','lsy1234',NULL,NULL,NULL),(37,6,7,'artKim1234','mhy1234',NULL,NULL,NULL),(38,6,7,'artKim1234','ngyh1234',NULL,NULL,NULL),(39,6,7,'artKim1234','phu1234',NULL,NULL,NULL),(40,6,7,'artKim1234','pms1234',NULL,NULL,NULL),(41,6,7,'artKim1234','sjy1234',NULL,NULL,NULL),(43,6,7,'artKim1234','asdf1234',NULL,NULL,NULL),(61,8,12,'englishHan1234','boy1234',NULL,NULL,NULL),(62,8,12,'englishHan1234','chasang1234',NULL,NULL,NULL),(63,8,12,'englishHan1234','hbu1234',NULL,NULL,NULL),(64,8,12,'englishHan1234','jjc1234',NULL,NULL,NULL),(65,8,12,'englishHan1234','jsu1234',NULL,NULL,NULL),(66,8,12,'englishHan1234','jyp1234',NULL,NULL,NULL),(67,8,12,'englishHan1234','lsy5656',NULL,NULL,NULL),(68,8,12,'englishHan1234','myy1234',NULL,NULL,NULL),(69,8,12,'englishHan1234','nys1234',NULL,NULL,NULL),(70,8,12,'englishHan1234','pja1234',NULL,NULL,NULL),(71,8,12,'englishHan1234','psy1234',NULL,NULL,NULL),(72,8,12,'englishHan1234','syo1234',NULL,NULL,NULL),(75,9,6,'artKim1234','boy1234',NULL,NULL,NULL),(76,9,6,'artKim1234','chasang1234',NULL,NULL,NULL),(77,9,6,'artKim1234','hbu1234',NULL,NULL,NULL),(78,9,6,'artKim1234','jjc1234',NULL,NULL,NULL),(79,9,6,'artKim1234','jsu1234',NULL,NULL,NULL),(80,9,6,'artKim1234','jyp1234',NULL,NULL,NULL),(81,9,6,'artKim1234','lsy5656',NULL,NULL,NULL),(82,9,6,'artKim1234','myy1234',NULL,NULL,NULL),(83,9,6,'artKim1234','nys1234',NULL,NULL,NULL),(84,9,6,'artKim1234','pja1234',NULL,NULL,NULL),(85,9,6,'artKim1234','psy1234',NULL,NULL,NULL),(86,9,6,'artKim1234','syo1234',NULL,NULL,NULL),(90,14,39,'teacher1','zihoon1234','행렬행렬','2022-02-18 02:06:08',NULL),(91,14,39,'teacher1','lsy5656',NULL,NULL,NULL),(92,14,39,'teacher1','lsy1234','fdsf','2022-02-18 02:43:58',NULL),(93,14,39,'teacher1','chasang1234',NULL,NULL,NULL),(94,14,39,'teacher1','yunzi1234',NULL,NULL,NULL),(95,14,39,'teacher1','ygc8257',NULL,NULL,NULL),(96,14,39,'teacher1','bdu1234',NULL,NULL,NULL),(97,14,39,'teacher1','pja1234',NULL,NULL,NULL),(98,14,39,'teacher1','hbu1234',NULL,NULL,NULL),(99,14,39,'teacher1','nys1234',NULL,NULL,NULL),(100,14,39,'teacher1','jjc1234',NULL,NULL,NULL),(101,14,39,'teacher1','psy1234',NULL,NULL,NULL),(105,15,39,'teacher1','zihoon1234','','2022-02-18 02:18:08',NULL),(106,15,39,'teacher1','lsy5656',NULL,NULL,NULL),(107,15,39,'teacher1','lsy1234',NULL,NULL,NULL),(108,15,39,'teacher1','chasang1234',NULL,NULL,NULL),(109,15,39,'teacher1','yunzi1234',NULL,NULL,NULL),(110,15,39,'teacher1','ygc8257',NULL,NULL,NULL),(111,15,39,'teacher1','bdu1234',NULL,NULL,NULL),(112,15,39,'teacher1','pja1234',NULL,NULL,NULL),(113,15,39,'teacher1','hbu1234',NULL,NULL,NULL),(114,15,39,'teacher1','nys1234',NULL,NULL,NULL),(115,15,39,'teacher1','jjc1234',NULL,NULL,NULL),(116,15,39,'teacher1','psy1234',NULL,NULL,NULL),(120,16,6,'artKim1234','boy1234',NULL,NULL,15),(121,16,6,'artKim1234','chasang1234',NULL,NULL,NULL),(122,16,6,'artKim1234','hbu1234',NULL,NULL,NULL),(123,16,6,'artKim1234','jjc1234',NULL,NULL,NULL),(124,16,6,'artKim1234','jsu1234',NULL,NULL,NULL),(125,16,6,'artKim1234','jyp1234',NULL,NULL,NULL),(126,16,6,'artKim1234','lsy5656',NULL,NULL,NULL),(127,16,6,'artKim1234','myy1234',NULL,NULL,NULL),(128,16,6,'artKim1234','nys1234',NULL,NULL,NULL),(129,16,6,'artKim1234','pja1234',NULL,NULL,NULL),(130,16,6,'artKim1234','psy1234',NULL,NULL,NULL),(131,16,6,'artKim1234','syo1234',NULL,NULL,NULL),(132,16,6,'artKim1234','bdu1234',NULL,NULL,NULL),(133,16,6,'artKim1234','ygc8257',NULL,NULL,NULL),(135,17,6,'artKim1234','boy1234',NULL,NULL,15),(136,17,6,'artKim1234','chasang1234',NULL,NULL,NULL),(137,17,6,'artKim1234','hbu1234',NULL,NULL,NULL),(138,17,6,'artKim1234','jjc1234',NULL,NULL,NULL),(139,17,6,'artKim1234','jsu1234',NULL,NULL,NULL),(140,17,6,'artKim1234','jyp1234',NULL,NULL,NULL),(141,17,6,'artKim1234','lsy5656',NULL,NULL,NULL),(142,17,6,'artKim1234','myy1234',NULL,NULL,NULL),(143,17,6,'artKim1234','nys1234',NULL,NULL,NULL),(144,17,6,'artKim1234','pja1234',NULL,NULL,NULL),(145,17,6,'artKim1234','psy1234',NULL,NULL,NULL),(146,17,6,'artKim1234','syo1234',NULL,NULL,NULL),(147,17,6,'artKim1234','bdu1234',NULL,NULL,NULL),(148,17,6,'artKim1234','ygc8257',NULL,NULL,NULL);
/*!40000 ALTER TABLE `student_homework` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-18 10:23:31
