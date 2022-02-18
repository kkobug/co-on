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
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teacher` (
  `tchr_id` varchar(20) NOT NULL,
  `tchr_name` varchar(10) NOT NULL,
  `tchr_email` varchar(72) NOT NULL,
  `tchr_password` varchar(255) NOT NULL,
  `tchr_contact` varchar(13) DEFAULT NULL,
  `tchr_signup_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `tchr_birthday` date DEFAULT NULL,
  `tchr_school` varchar(20) DEFAULT NULL,
  `tchr_prof_name` text,
  `tchr_origin_prof_name` text,
  `tchr_prof_path` text,
  PRIMARY KEY (`tchr_id`),
  UNIQUE KEY `tchr_email` (`tchr_email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES ('artKim1234','김미술','artKim1234@gmail.com','$2a$10$N.JHwFgb9pLMHQCLbww9Q.q/27MkZVrMQEVW08r.RHToarKoLgUai','01012345678','2022-02-16 15:22:32','1981-10-28','싸피중학교','Tprof0217073436773none.png','none.png',''),('athleticHa1234','하체육','athleticHa1234@daum.net','$2a$10$4Vku8/rFX7YNX3LQcNP7vezc2QeYiylH.Uu/8HmlF2aInRLB9Jcca','01012345678','2022-02-16 15:04:56','1987-12-31','싸피중학교','','',''),('chineseJo1234','조한문','chineseJo1234@naver.com','$2a$10$XKDDpGnkq9ceOSYNXSm9e.i38mmua6Mgzfug65A9RmdXrntohPLQG','01012345678','2022-02-16 15:28:54','2001-03-22','싸피중학교','','',''),('Co-on양지훈','양지훈','kelvin9149@gmail.com','$2a$10$4GhuaUq3OwvIA/ffx8vBc.jKMZMCC8EFRcJuE7ITxO.2Iogghv9Mu','','2022-02-17 17:20:55','1995-09-24','',NULL,NULL,NULL),('dldldl','오일남','xxx@naver.com','$2a$10$dFM2eKVHw25L74OjoGR/huPPJreeAMNrDklw2i7SficvXi6/xd9Tq','01044442345','2022-02-18 04:32:25','2022-02-10','칼바람학교',NULL,NULL,NULL),('englishHan1234','한영어','englishHan1234@naver.com','$2a$10$09pHpnYm5.oLfHthKLEqieH1iGmoccttX9UFFqXWjEsQTqa6lnacu','01012345678','2022-02-16 14:56:27','1994-09-04','싸피중학교','','',''),('historyJo1234','조역사','historyJo1234@naver.com','$2a$10$atjyF3n2RU0/wqhLrqF27eLpqQ623zoA2XNsUEucnzsf35r59MA7G','01012345678','2022-02-16 15:08:03','1979-10-21','싸피중학교','','',''),('homeKim1234','김가정','homeKim1234@naver.com','$2a$10$CPsB01Jd9qqsJ5ia3Usxf.2kwdsT0he9BhnsQpGTLCznV5wslivia','01012345678','2022-02-16 15:20:17','1992-08-20','싸피중학교','','',''),('japenKang1234','강일본','japenKang1234@naver.com','$2a$10$9jWk73PZhDzNkYIGzL15AOuw0YJZX7KfQizrXL39w4we7ftOa6hT.','01012345678','2022-02-16 15:25:26','1989-09-25','싸피중학교','','',''),('literKim1234','김문학','literKim1234@daum.net','$2a$10$NpdJwIptIdHzyR18KQTbsuEHzDxaWVmbQlQTBLfMUjI.v5xsqNRLy','01012345678','2022-02-16 14:47:30','1982-02-17','싸피중학교','','',''),('mathPark1234','박수학','mathPark1234@naver.com','$2a$10$A1Ys3QNy0cb2oc3f5h0ERudIjuSX6TiduGX7ZEzouT34jdcrilpw2','01012345678','2022-02-16 14:48:57','1976-07-29','싸피중학교','','',''),('moralO1234','오도덕','moralO1234@naver.com','$2a$10$I/BB5oWhoaI4GFhA7RzI.eCjqud8B9FSagV.hXofxO0JqG./3seTW','01012345678','2022-02-16 15:02:38','2000-02-15','싸피중학교','','',''),('musicUw1234','우음악','musicUw1234@gmail.net','$2a$10$OvLT84M8gMbWPj1Qoq/O4.j4NJlOcJcEnPzfVaZ66CJvJurmvNFIO','01012345678','2022-02-16 15:11:11','1995-11-15','싸피중학교','','',''),('robin1202','조철수','robin@naver.com','$2a$10$ya/moSJDFztanbjyij1KIOVnJMO0lYH8RMbvvKyPEJcsbyQrBXHhm','01011223344','2022-02-18 00:00:21','1989-08-18','싸피 중학교',NULL,NULL,NULL),('scienceMoon1234','문과학','scienceMoon1234@naver.com','$2a$10$BydK2tnCh0w8jvSeoELeDefuClTV8i7XaR.MU4eKauMcp0gDwU34y','01012345678','2022-02-16 15:00:12','1992-09-23','싸피중학교','','',''),('socialLee1234','이사회','socialLee1234@gmail.com','$2a$10$FfHZZx.VF2RYeOH2msRzNeUxmSS.2RzNq005OLhiSZtsQnHUXc2mW','01012345678','2022-02-16 14:53:16','1979-01-17','싸피중학교','','',''),('teacher1','teacher1','teacher1@sc.com','$2a$10$u0b4ukg5g6OpSlw/dBNABu8uS0Ymq5aF/IsDsrLCjij3XRwJWqX02','','2022-02-17 13:27:37','2022-02-08','teacher1@sc.com',NULL,NULL,NULL),('YJH6207','양지훈','kelvin9149@gmail.co.kr','$2a$10$ILM.62kOETdBQ85OW1YnquFyASBHphyiFDLVvoirxjxfmgGvrqFke','','2022-02-17 17:29:23','2010-06-08','',NULL,NULL,NULL),('yjhtchr','양지훈','kelvin9149@gmaill.com','$2a$10$oos/RM1zVQiLfY22YjbQEu93jNVNyvM3dCkcCK9dv0wa3Tx7KBlW2','','2022-02-17 11:41:11','2020-01-01','','Tprof0217024200595제목 없음.jpg','제목 없음.jpg',NULL);
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
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
