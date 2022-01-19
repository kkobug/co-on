use `ssafy_web_db`;

DROP TABLE `notice`;
DROP TABLE `homework`;
DROP TABLE `studyroom`;
CREATE TABLE `studyroom` (
  `study_id` INT,
  `study_name` VARCHAR(20),
  `study_descruption` TEXT,
  PRIMARY KEY (`study_id`)
);