use ssafy_web_db;
CREATE TABLE `student` (
  `st_id` varchar(20) NOT NULL,
  `st_name` varchar(10) NOT NULL,
  `st_email` varchar(72) NOT NULL,
  `st_password` varchar(20) NOT NULL,
  `st_contact` varchar(13) DEFAULT NULL,
  `st_signup_date` DATETIME DEFAULT NOW(),
  `st_school` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`st_id`),
  unique key (`st_email`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `teacher` (
  `tchr_id` varchar(20) NOT NULL,
  `tchr_name` varchar(10) NOT NULL,
  `tchr_email` varchar(72) NOT NULL,
  `tchr_password` varchar(20) NOT NULL,
  `tchr_contact` varchar(13) DEFAULT NULL,
  `tchr_signup_date` DATETIME DEFAULT NOW(),
  `tchr_school` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`tchr_id`),
  unique key (`tchr_email`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;
