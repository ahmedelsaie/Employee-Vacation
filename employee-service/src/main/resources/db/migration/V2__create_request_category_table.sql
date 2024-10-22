CREATE TABLE IF NOT EXISTS `request_category` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `request_category_type` enum('ANNUAL_LEAVE','SICK_LEAVE','WORK_REMOTELY') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO `request_category` (id,name,request_category_type) VALUES (1,'12','SICK_LEAVE'),(2,'13','WORK_REMOTELY'),(3,'11','ANNUAL_LEAVE');
