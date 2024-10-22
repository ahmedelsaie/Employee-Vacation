CREATE TABLE IF NOT EXISTS `employee` (
  `salary` float NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `id` binary(16) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
    `password` varchar(255) NOT NULL,
  `position` varchar(255) DEFAULT NULL,
  `employee_role` enum('ADMIN', 'EMPLOYEE') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci


