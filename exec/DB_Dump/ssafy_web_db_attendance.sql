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
-- Table structure for table `attendance`
--

DROP TABLE IF EXISTS `attendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attendance` (
  `att_id` int unsigned NOT NULL AUTO_INCREMENT,
  `conf_id` int unsigned NOT NULL,
  `st_id` varchar(20) NOT NULL,
  `att_pass` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`att_id`),
  KEY `conf_id` (`conf_id`),
  KEY `st_id` (`st_id`),
  CONSTRAINT `attendance_ibfk_1` FOREIGN KEY (`conf_id`) REFERENCES `conference` (`conf_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `attendance_ibfk_2` FOREIGN KEY (`st_id`) REFERENCES `studyroom_detail` (`st_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=487 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendance`
--

LOCK TABLES `attendance` WRITE;
/*!40000 ALTER TABLE `attendance` DISABLE KEYS */;
INSERT INTO `attendance` VALUES (3,2,'bys1234',0),(4,2,'gsm1234',0),(5,2,'hjh1234',0),(6,2,'jjh1234',0),(7,2,'jty1234',0),(8,2,'lsy1234',0),(9,2,'mhy1234',0),(10,2,'ngyh1234',0),(11,2,'phu1234',0),(12,2,'pms1234',0),(13,2,'sjy1234',0),(15,2,'asdf1234',0),(18,3,'bys1234',0),(19,3,'gsm1234',0),(20,3,'hjh1234',0),(21,3,'jjh1234',0),(22,3,'jty1234',0),(23,3,'lsy1234',0),(24,3,'mhy1234',0),(25,3,'ngyh1234',0),(26,3,'phu1234',0),(27,3,'pms1234',0),(28,3,'sjy1234',0),(30,3,'asdf1234',0),(33,4,'bys1234',0),(34,4,'gsm1234',0),(35,4,'hjh1234',0),(36,4,'jjh1234',0),(37,4,'jty1234',0),(38,4,'lsy1234',0),(39,4,'mhy1234',0),(40,4,'ngyh1234',0),(41,4,'phu1234',0),(42,4,'pms1234',0),(43,4,'sjy1234',0),(45,4,'asdf1234',0),(54,6,'zihoon1234',1),(55,6,'lsy5656',1),(56,6,'lsy1234',1),(57,6,'chasang1234',1),(58,6,'yunzi1234',0),(62,7,'bys1234',0),(63,7,'gsm1234',0),(64,7,'hjh1234',0),(65,7,'jjh1234',0),(66,7,'jty1234',0),(67,7,'lsy1234',0),(68,7,'mhy1234',0),(69,7,'ngyh1234',0),(70,7,'phu1234',0),(71,7,'pms1234',0),(72,7,'sjy1234',0),(73,7,'asdf1234',0),(77,8,'bys1234',0),(78,8,'gsm1234',0),(79,8,'hjh1234',0),(80,8,'jjh1234',0),(81,8,'jty1234',0),(82,8,'lsy1234',0),(83,8,'mhy1234',0),(84,8,'ngyh1234',0),(85,8,'phu1234',0),(86,8,'pms1234',0),(87,8,'sjy1234',0),(88,8,'asdf1234',0),(92,9,'bys1234',0),(93,9,'gsm1234',0),(94,9,'hjh1234',0),(95,9,'jjh1234',0),(96,9,'jty1234',0),(97,9,'lsy1234',0),(98,9,'mhy1234',0),(99,9,'ngyh1234',0),(100,9,'phu1234',0),(101,9,'pms1234',0),(102,9,'sjy1234',0),(103,9,'asdf1234',0),(107,10,'bys1234',0),(108,10,'gsm1234',0),(109,10,'hjh1234',0),(110,10,'jjh1234',0),(111,10,'jty1234',0),(112,10,'lsy1234',0),(113,10,'mhy1234',0),(114,10,'ngyh1234',0),(115,10,'phu1234',0),(116,10,'pms1234',0),(117,10,'sjy1234',0),(118,10,'asdf1234',0),(122,11,'bys1234',0),(123,11,'gsm1234',0),(124,11,'hjh1234',0),(125,11,'jjh1234',0),(126,11,'jty1234',0),(127,11,'lsy1234',0),(128,11,'mhy1234',0),(129,11,'ngyh1234',0),(130,11,'phu1234',0),(131,11,'pms1234',0),(132,11,'sjy1234',0),(133,11,'asdf1234',0),(136,12,'boy1234',0),(137,12,'chasang1234',0),(138,12,'hbu1234',0),(139,12,'jjc1234',0),(140,12,'jsu1234',0),(141,12,'jyp1234',0),(142,12,'lsy5656',0),(143,12,'myy1234',0),(144,12,'nys1234',0),(145,12,'pja1234',0),(146,12,'psy1234',0),(147,12,'syo1234',0),(151,14,'boy1234',0),(152,14,'chasang1234',0),(153,14,'hbu1234',0),(154,14,'jjc1234',0),(155,14,'jsu1234',0),(156,14,'jyp1234',0),(157,14,'lsy5656',0),(158,14,'myy1234',0),(159,14,'nys1234',0),(160,14,'pja1234',0),(161,14,'psy1234',0),(162,14,'syo1234',0),(166,15,'boy1234',0),(167,15,'chasang1234',0),(168,15,'hbu1234',0),(169,15,'jjc1234',0),(170,15,'jsu1234',0),(171,15,'jyp1234',0),(172,15,'lsy5656',0),(173,15,'myy1234',0),(174,15,'nys1234',0),(175,15,'pja1234',0),(176,15,'psy1234',0),(177,15,'syo1234',0),(182,16,'bys1234',0),(183,16,'gsm1234',0),(184,16,'hjh1234',0),(185,16,'jjh1234',0),(186,16,'jty1234',0),(187,16,'lsy1234',0),(188,16,'mhy1234',0),(189,16,'ngyh1234',0),(190,16,'phu1234',0),(191,16,'pms1234',0),(192,16,'sjy1234',0),(193,16,'asdf1234',0),(197,17,'bys1234',0),(198,17,'gsm1234',0),(199,17,'hjh1234',0),(200,17,'jjh1234',0),(201,17,'jty1234',0),(202,17,'lsy1234',0),(203,17,'mhy1234',0),(204,17,'ngyh1234',0),(205,17,'phu1234',0),(206,17,'pms1234',0),(207,17,'sjy1234',0),(208,17,'asdf1234',0),(211,18,'Co-on?????????',0),(212,18,'Co-on?????????',0),(214,18,'Co-on?????????',0),(215,18,'lsy1234',0),(218,19,'?????????4???_?????????',0),(219,21,'lsh6207',0),(220,21,'CSH6207',0),(221,21,'JIS6207',0),(222,21,'lsy1234',0),(223,21,'KYJ6207',0),(226,22,'lsh6207',0),(227,22,'CSH6207',0),(228,22,'JIS6207',0),(229,22,'lsy1234',0),(230,22,'KYJ6207',0),(233,23,'lsh6207',0),(234,23,'CSH6207',0),(235,23,'JIS6207',0),(236,23,'lsy1234',0),(237,23,'KYJ6207',0),(240,24,'zihoon1234',0),(241,24,'lsy5656',0),(242,24,'lsy1234',0),(243,24,'chasang1234',0),(244,24,'yunzi1234',0),(245,24,'ygc8257',0),(247,25,'boy1234',0),(248,25,'chasang1234',0),(249,25,'hbu1234',0),(250,25,'jjc1234',0),(251,25,'jsu1234',0),(252,25,'jyp1234',0),(253,25,'lsy5656',0),(254,25,'myy1234',0),(255,25,'nys1234',0),(256,25,'pja1234',0),(257,25,'psy1234',0),(258,25,'syo1234',0),(262,26,'boy1234',0),(263,26,'chasang1234',0),(264,26,'hbu1234',0),(265,26,'jjc1234',0),(266,26,'jsu1234',0),(267,26,'jyp1234',0),(268,26,'lsy5656',0),(269,26,'myy1234',0),(270,26,'nys1234',0),(271,26,'pja1234',0),(272,26,'psy1234',0),(273,26,'syo1234',0),(277,27,'boy1234',0),(278,27,'chasang1234',0),(279,27,'hbu1234',0),(280,27,'jjc1234',0),(281,27,'jsu1234',0),(282,27,'jyp1234',0),(283,27,'lsy5656',0),(284,27,'myy1234',0),(285,27,'nys1234',0),(286,27,'pja1234',0),(287,27,'psy1234',0),(288,27,'syo1234',0),(292,28,'boy1234',0),(293,28,'chasang1234',0),(294,28,'hbu1234',0),(295,28,'jjc1234',0),(296,28,'jsu1234',0),(297,28,'jyp1234',0),(298,28,'lsy5656',0),(299,28,'myy1234',0),(300,28,'nys1234',0),(301,28,'pja1234',0),(302,28,'psy1234',0),(303,28,'syo1234',0),(307,29,'boy1234',0),(308,29,'chasang1234',0),(309,29,'hbu1234',0),(310,29,'jjc1234',0),(311,29,'jsu1234',0),(312,29,'jyp1234',0),(313,29,'lsy5656',0),(314,29,'myy1234',0),(315,29,'nys1234',0),(316,29,'pja1234',0),(317,29,'psy1234',0),(318,29,'syo1234',0),(319,29,'ygc8257',0),(320,29,'bdu1234',0),(322,30,'bdu1234',0),(323,30,'bys1234',0),(324,30,'gsm1234',0),(325,30,'hjh1234',0),(326,30,'jjh1234',0),(327,30,'jty1234',0),(328,30,'lsy1234',0),(329,30,'mhy1234',0),(330,30,'ngyh1234',0),(331,30,'phu1234',0),(332,30,'pms1234',0),(333,30,'sjy1234',0),(334,30,'asdf1234',0),(337,31,'bdu1234',0),(338,31,'bys1234',0),(339,31,'gsm1234',0),(340,31,'hjh1234',0),(341,31,'jjh1234',0),(342,31,'jty1234',0),(343,31,'lsy1234',0),(344,31,'mhy1234',0),(345,31,'ngyh1234',0),(346,31,'phu1234',0),(347,31,'pms1234',0),(348,31,'sjy1234',0),(349,31,'asdf1234',0),(352,32,'bdu1234',0),(353,32,'bys1234',0),(354,32,'gsm1234',0),(355,32,'hjh1234',0),(356,32,'jjh1234',0),(357,32,'jty1234',0),(358,32,'lsy1234',0),(359,32,'mhy1234',0),(360,32,'ngyh1234',0),(361,32,'phu1234',0),(362,32,'pms1234',0),(363,32,'sjy1234',0),(364,32,'asdf1234',0),(367,33,'bdu1234',0),(368,33,'bys1234',0),(369,33,'gsm1234',0),(370,33,'hjh1234',0),(371,33,'jjh1234',0),(372,33,'jty1234',0),(373,33,'lsy1234',0),(374,33,'mhy1234',0),(375,33,'ngyh1234',0),(376,33,'phu1234',0),(377,33,'pms1234',0),(378,33,'sjy1234',0),(379,33,'asdf1234',0),(382,34,'zihoon1234',0),(383,34,'lsy5656',0),(384,34,'lsy1234',0),(385,34,'chasang1234',0),(386,34,'yunzi1234',0),(387,34,'ygc8257',0),(388,34,'bdu1234',0),(389,34,'pja1234',0),(390,34,'hbu1234',0),(391,34,'nys1234',0),(392,34,'jjc1234',0),(393,34,'psy1234',0),(397,36,'zihoon1234',0),(398,36,'lsy5656',0),(399,36,'lsy1234',0),(400,36,'chasang1234',0),(401,36,'yunzi1234',0),(402,36,'ygc8257',0),(403,36,'bdu1234',0),(404,36,'pja1234',0),(405,36,'hbu1234',0),(406,36,'nys1234',0),(407,36,'jjc1234',0),(408,36,'psy1234',0),(412,37,'zihoon1234',0),(413,37,'lsy5656',0),(414,37,'lsy1234',0),(415,37,'chasang1234',0),(416,37,'yunzi1234',0),(417,37,'ygc8257',0),(418,37,'bdu1234',0),(419,37,'pja1234',0),(420,37,'hbu1234',0),(421,37,'nys1234',0),(422,37,'jjc1234',0),(423,37,'psy1234',0),(427,38,'zihoon1234',0),(428,38,'lsy5656',0),(429,38,'lsy1234',0),(430,38,'chasang1234',0),(431,38,'yunzi1234',0),(432,38,'ygc8257',0),(433,38,'bdu1234',0),(434,38,'pja1234',0),(435,38,'hbu1234',0),(436,38,'nys1234',0),(437,38,'jjc1234',0),(438,38,'psy1234',0),(442,39,'zihoon1234',0),(443,39,'lsy5656',0),(444,39,'lsy1234',0),(445,39,'chasang1234',0),(446,39,'yunzi1234',0),(447,39,'ygc8257',0),(448,39,'bdu1234',0),(449,39,'pja1234',0),(450,39,'hbu1234',0),(451,39,'nys1234',0),(452,39,'jjc1234',0),(453,39,'psy1234',0),(457,40,'zihoon1234',0),(458,40,'lsy5656',0),(459,40,'lsy1234',0),(460,40,'chasang1234',0),(461,40,'yunzi1234',0),(462,40,'ygc8257',0),(463,40,'bdu1234',0),(464,40,'pja1234',0),(465,40,'hbu1234',0),(466,40,'nys1234',0),(467,40,'jjc1234',0),(468,40,'psy1234',0),(472,41,'zihoon1234',0),(473,41,'lsy5656',0),(474,41,'lsy1234',0),(475,41,'chasang1234',0),(476,41,'yunzi1234',0),(477,41,'ygc8257',0),(478,41,'bdu1234',0),(479,41,'pja1234',0),(480,41,'hbu1234',0),(481,41,'nys1234',0),(482,41,'jjc1234',0),(483,41,'psy1234',0);
/*!40000 ALTER TABLE `attendance` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-18 10:23:32
