CREATE TABLE IF NOT EXISTS `time_off_request` (
  `end_date` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `request_category_id` bigint DEFAULT NULL,
  `start_date` datetime(6) DEFAULT NULL,
  `employee_id` binary(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1j0nkcyyhyqy6qdo37hf0fan3` (`employee_id`),
  KEY `FK5luswkvb7sg38djgjc4xrg06o` (`request_category_id`),
  CONSTRAINT `FK1j0nkcyyhyqy6qdo37hf0fan3` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `FK5luswkvb7sg38djgjc4xrg06o` FOREIGN KEY (`request_category_id`) REFERENCES `request_category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci