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
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `st_id` varchar(20) NOT NULL,
  `st_name` varchar(10) NOT NULL,
  `st_email` varchar(72) NOT NULL,
  `st_password` varchar(255) NOT NULL,
  `st_contact` varchar(13) DEFAULT NULL,
  `st_signup_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `st_birthday` date DEFAULT NULL,
  `st_school` varchar(20) DEFAULT NULL,
  `st_prof_name` text,
  `st_origin_prof_name` text,
  `st_prof_path` text,
  PRIMARY KEY (`st_id`),
  UNIQUE KEY `st_email` (`st_email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('11','11','11@gmail.com','$2a$10$mIUQ.VxZDKih2wlPySyU1.AbvO5a2iSWlkzQhfJOVi3ffiJu67J6.','1','2022-02-18 01:44:07','2022-02-01','1',NULL,NULL,NULL),('1q2w3e4r','정우영','1q2w3e4r@ssafy.com','$2a$10$w6E59SRnf2kcQ78srDN4k.1j5Q36xeZuI1aJIZfguVgtqvYqlnlt.','12345678','2022-02-17 13:26:53','2022-02-07','싸피',NULL,NULL,NULL),('asdf1234','김기본','asdf1234@naver.com','$2a$10$04fj0QX3PV/O3Mh9CFWV0eHTqAS7ZQSyupn5mOOoDMuAHVJvzk36q','','2022-02-17 15:07:48','2022-02-07','','Sprof0217060927734중등 교육용 화상시스템] Untitled.png','중등 교육용 화상시스템] Untitled.png',NULL),('bdu1234','배대웅','bdu1234@gmail.com','$2a$10$C5g6got49i0eZvEUdAnck.w5kgECVohp7PmteGT3xexNykKCmgDWC','','2022-02-16 16:25:52','2022-02-07','d','Sprof0217064215048free-icon-file-submit-80226.png','free-icon-file-submit-80226.png',NULL),('bewhy2043','비와이','bgd02013@naver.com','$2a$10$nFXqu9DM1KJLc2oJPIumiegic5GQzCjQg.7djae7I8tnNiKcCr32m','','2022-02-17 14:03:32','2022-02-14','',NULL,NULL,NULL),('boy1234','배원영','boy1234@naver.com','$2a$10$JpnHgj6gcPlYap8OfPcwHuo2KAhsrAEMIRpV5rSP7fvEOUNdsufPK','','2022-02-16 16:26:16','2022-02-23','','','',''),('bys1234','복윤성','bys1234@naver.com','$2a$10$V40ZGqDosi1CiMN5qHqVYuaPT0Sb0RYfAP7v2WMhNLO8LJOLHFCWi','','2022-02-16 16:15:50','2022-02-22','','','',''),('chasang1234','차상훈','chasang1234@naver.com','$2a$10$4rezknZH1dotVQyYHanQFenXZqSiJhApIpyD5tw/EF/9U6i31DgWa','','2022-02-16 15:40:56','2022-02-15','','','',''),('Co-on김윤지','김윤지','ssafy@ssafy.com','$2a$10$AfD5LJ8ooDfHeaYHvZ5cyerHSNUsmZi/Qf4iukuh/6M8spxwqbSNi','010-1234-1234','2022-02-17 17:19:23','2022-02-01','싸피중학교',NULL,NULL,NULL),('Co-on이승훈','이승훈','coon@naver.com','$2a$10$35t3Q2jvACuIiHbBAOEZAecXIWG4Z0LXrwuD8txcWw.tMheRbhAcO','','2022-02-17 17:16:56','2022-02-01','',NULL,NULL,NULL),('Co-on정인수','정인수','Co-on정인수@naver.com','$2a$10$DF2k4FMXl5tDQn08b0Ixhe3/3cmixG6W.aoXUMprxYbiOV8ceLtzO','','2022-02-17 17:22:07','2022-02-07','',NULL,NULL,NULL),('Co-on차상훈','차상훈','brade6666@naver.com','$2a$10$0rGJIU8/.TfYIr7UdHNeGedKt2CcWpEYUOVISxew5jKRtD20GGdbK','01022223312','2022-02-17 17:19:21','2020-01-08','ssafy',NULL,NULL,NULL),('CSH6207','차상훈','brade7777@nate.com','$2a$10$hm.RM/3Kk12JQfh4MGv6Eu8JvtIuNguGt58y95EU.TULVZ3NjpVRS','01052223333','2022-02-17 17:29:07','1992-06-09','ssafy',NULL,NULL,NULL),('gsm1234','권수미','gsm1234@naver.com','$2a$10$2Ia33vSEw8Mw7WdudE9aIeK1m5mo4Mj15Zob2qg2p12q1JCzfZdj2','','2022-02-16 16:19:13','2022-02-07','','','',''),('hbu1234','하병욱','hbu1234@naver.com','$2a$10$l9Kh61C0qwAB.WzrSWCcEOD2lPnngH0EyuvF.L4Wblf3KWLIiSCzO','','2022-02-16 16:24:15','2022-02-08','','','',''),('hjh1234','한주혁','hjh1234@naver.com','$2a$10$HQ06K7QhzwT5i1V/NbPFqOgSYoD6SFjoWIhQbprqN76GYvNDnI2nO','','2022-02-16 16:14:43','2022-02-15','','','',''),('JIS6207','정인수','JIS6207@naver.com','$2a$10$rBGv9ZWPdUoh9iy3VsXT/uihKHNpwmLZkAA6m.laHNmbe4tjtuTX.','','2022-02-17 17:29:08','2022-02-08','','Sprof0217133648688none.png','none.png',NULL),('jjc1234','정준철','jjc1234@gmail.com','$2a$10$DGVrNfJm3XSsqL7TcanlaepRvxlC/TeU0qlu4XocAXUmoztrRtivG','','2022-02-16 16:19:51','2022-02-07','','','',''),('jjh1234','장지혜','jjh1234@naver.com','$2a$10$1jq0ZQZ.Ykuw8jinB.CD/./3LddVOOBAsEdGiUNLivhcZjaHNa36a','','2022-02-16 16:25:19','2022-02-17','','','',''),('jsu1234','조성우','jsu1234@naver.com','$2a$10$vzunFLmLdfauEbe/7kYdreYy7JIgyvIUFnI74yvjZuOrg3rnh1Iei','','2022-02-16 15:52:39','2022-02-09','','','',''),('jty1234','정태윤','jty1234@naver.com','$2a$10$Hs6uFW/KT/F3ceCK/k4Zl.Lf2amJUcT7ZZu7/60fhP3RNhpIS8CuC','','2022-02-16 16:24:47','2022-02-08','','','',''),('jyp1234','박지용','jyp1234@naver.com','$2a$10$0KtiZP2oG6OI6cCG/WNroOOwSzncrfi2uaLdbRMlAvXWeTjmp1xXe','','2022-02-16 15:44:02','2022-02-13','','','',''),('KYJ6207','김윤지','KYJ6207@ssafy.com','$2a$10$YpY0hx083K.uWIPdcrVDY.53SdgigbkQdNe3ykjJzt25aZjMOYipS','','2022-02-17 17:31:03','2022-02-11','',NULL,NULL,NULL),('lsh6207','이승훈','hao@naver.com','$2a$10$ZyMvANTSMKVfqVz/QlQyKejZLWe8ajH5o86qDQd/DMI43qmUmcesa','','2022-02-17 17:29:42','2022-02-20','',NULL,NULL,NULL),('lsy1234','이상엽','lsy1234@gmail.com','$2a$10$kt3Du920Yoal173TyFVsDexxjb28.jsBqnOVYrv2/PxU.nV.yfpDW','','2022-02-16 15:43:09','2022-02-08','','','',''),('lsy5656','이승훈','lsy5656@gamil.com','$2a$10$RxRzYBQVvEULTHLBIpMd5e2m5YgBuyLHiyZI.Bho7qmHTlBpfXpwC','01012345678','2022-02-16 15:39:08','1995-05-06','싸피중학교','','',''),('mhy1234','문희영','mhy1234@naver.com','$2a$10$tcSSY6lRpqiiVAheVVkcROETxUdRlHJtpzB385XGve.0XPUrRvPEq','','2022-02-16 16:18:18','2022-02-09','','','',''),('myy1234','문유영','myy1234@naver.com','$2a$10$ho967wTToTMDsgYkTzrRS.GHyoGvxLBaUf0D0Top.bt9OrmUyPQ6K','','2022-02-16 16:23:11','2022-02-08','','','',''),('ngyh1234','남궁용하','ngyh1234@gmail.com','$2a$10$neZyCMFCUjBEuHdMbPiGROZPquXEOgTDdGgCheRwkeEXiBBIoIF0C','','2022-02-16 16:26:45','2022-02-07','','','',''),('nys1234','남용수','nys1234@naver.com','$2a$10$F/wOgb214eyRGI4bqtjfJ.yc9E9HAykAxomm2nnOxVFbaR2jbw/b.','','2022-02-16 16:21:02','2022-02-07','','','',''),('phu1234','표현우','phu1234@naver.com','$2a$10$cnKC59AC1gC3UhxRuPWqk.ei1iTDbOhoWF7440qmzWN6hQ6LGj4JK','','2022-02-16 15:59:05','2022-02-06','','','',''),('pja1234','박정아','pja1234@gmail.com','$2a$10$6eTy.WaetyXHkPtI03KpIuzp4Ywe3sW6Mg8hRk6pNN484//87ELoK','','2022-02-16 16:17:34','2022-02-16','','','',''),('pms1234','박민수','pms1234@naver.com','$2a$10$48c9uPGHAQ4NUHoeN8xixO/guM1KPdYZP1uiJIbW6auvr2XecXz.C','','2022-02-16 15:44:49','2022-02-14','','','',''),('psy1234','박수현','psy1234@daum.net','$2a$10$tNrPDlQ7Ai2MLbOO28GfL.OEEJNxwGyOvjWZBy55ooSF5ZatxI1Bi','','2022-02-16 15:51:40','2022-02-07','','','',''),('sjy1234','손준용','sjy1234@naver.com','$2a$10$uh9cbFsvphnu3TdC./aW3O7ov5vV5jrbwHilGuDczHxIYd7B4j1HG','','2022-02-16 15:56:26','2022-02-07','','','',''),('student1','student1','student1@cdj.com','$2a$10$zAywGf5HwAyBCJSgdLguuOnnHYv9umpRRKgnLQlEk.wH9JF3pM5cq','01011114444','2022-02-17 13:28:54','2022-02-01','student1',NULL,NULL,NULL),('syo1234','신해원','syo1234@gmail.com','$2a$10$DYt/TNqFT/qyNjkW/8fyG.UegqJyvva3eVmSGNQeTuqYyKWEUKrO6','','2022-02-16 16:16:21','2022-02-16','','','',''),('ygc8257','정인수','ygc8257@naver.com','$2a$10$QZ6IUkZ/O9AIop7BzVIz9eov0XgnwX5bIp8S63Zg6mHZ4etzxTQVy','','2022-02-17 14:57:32','2022-02-01','',NULL,NULL,NULL),('yhj1234','윤혜주','yhj1234@naver.com','$2a$10$Hc3cPBA7p2QsRQ.iU.741uwNaT6h7/IVReq2u95zCvL.C0aeVF1mq','','2022-02-16 16:21:32','2022-02-15','','','',''),('yjh1234','예종하','yjh1234@gmail.com','$2a$10$NU9zeFzv4I1ZM1dQ66nHVeKXs3frTL0C3U1XagRdbpKF5TXkZogt2','','2022-02-16 16:18:46','2022-02-16','','','',''),('yjhst','양지훈','kelvin9149@gmail.co.kr','$2a$10$nxYL18uPIBJLYc0a33D9benVmmu0EPgImbCuDuZrXxAJ9v006E9gC','','2022-02-17 11:42:26','2022-02-01','','Sprof0217024315312r6.jpg','r6.jpg',NULL),('yunzi1234','김윤지','yunzi1234@naver.com','$2a$10$dYbNwiEMfCUSu4gqyPrxH.z76dJjtqxSwkn75zM6heFxBBQegfbYa','01012345678','2022-02-16 15:36:20','1996-07-18','싸피중학교','','',''),('zihoon1234','양지훈','zihoon1234@naver.com','$2a$10$AGPqRIVAgiX9.6N0ptuhBu.3nmrQY3x3WCifKHH.RpQqtxz8POmNW','','2022-02-16 15:42:28','2022-02-02','','','',''),('구동매','구동매','gdm@naver.com','$2a$10$Hzya21DUfBY1NnAR04ZLQeLr4caMwBRXqdUWSL4rdhJaZSm5SdRFq','','2022-02-17 16:55:35','2022-02-08','',NULL,NULL,NULL),('부울경2반_이승훈','이승훈','fine@naver.com','$2a$10$.omVK0AFbAcvgepbb8csu.fST4P5fwcebb8Us/KbyaSYhC8229Msm','','2022-02-17 16:51:11','2022-02-14','',NULL,NULL,NULL),('부울경2반-이승훈','이승훈','con@naver.com','$2a$10$/A3nJ0U1i3AoP6fegDibBuHDKdSVmv5SQCRE0Y3BfG2zgSdjT39ay','','2022-02-17 17:17:42','2022-02-07','',NULL,NULL,NULL),('부울경4반_차상훈','차상훈','sanghunc62@gmail.com','$2a$10$rkyk5xcoXqpmOxDQRS.b.OZY/S/2frTowgGmlum6UQqZLtGtlrIFy','01052524545','2022-02-17 16:52:53','2020-01-01','ssafy',NULL,NULL,NULL);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-18 10:23:34
