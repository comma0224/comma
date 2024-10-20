-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        11.3.2-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- comma 데이터베이스 구조 내보내기
DROP DATABASE IF EXISTS `comma`;
CREATE DATABASE IF NOT EXISTS `comma` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin */;
USE `comma`;

-- 테이블 comma.addon 구조 내보내기
DROP TABLE IF EXISTS `addon`;
CREATE TABLE IF NOT EXISTS `addon` (
  `addon_key` bigint(20) NOT NULL AUTO_INCREMENT,
  `shelter_key` bigint(20) NOT NULL,
  `title` varchar(255) NOT NULL,
  `url` varchar(255) NOT NULL,
  `status` varchar(50) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`addon_key`),
  KEY `shelter_key` (`shelter_key`),
  CONSTRAINT `addon_ibfk_1` FOREIGN KEY (`shelter_key`) REFERENCES `shelter` (`shelter_key`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- 테이블 데이터 comma.addon:~3 rows (대략적) 내보내기
INSERT IGNORE INTO `addon` (`addon_key`, `shelter_key`, `title`, `url`, `status`, `created_at`, `updated_at`) VALUES
	(1, 1, '계산기1', 'test1', '0', '2024-10-19 17:20:04', '2024-10-20 01:11:08'),
	(2, 1, '계산기2', 'test2', '0', '2024-10-19 17:20:04', '2024-10-20 01:11:09'),
	(3, 1, '계산기3', 'test3', '0', '2024-10-19 17:20:04', '2024-10-20 01:11:10');

-- 테이블 comma.badge 구조 내보내기
DROP TABLE IF EXISTS `badge`;
CREATE TABLE IF NOT EXISTS `badge` (
  `badge_key` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `price` int(11) NOT NULL,
  `status` int(11) DEFAULT NULL COMMENT '0: 사용\r\n1: 미사용',
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`badge_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- 테이블 데이터 comma.badge:~0 rows (대략적) 내보내기

-- 테이블 comma.category 구조 내보내기
DROP TABLE IF EXISTS `category`;
CREATE TABLE IF NOT EXISTS `category` (
  `category_key` bigint(20) NOT NULL AUTO_INCREMENT,
  `shelter_key` bigint(20) NOT NULL,
  `title_group` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `image` blob DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '0: 사용\r\n1: 미사용',
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`category_key`),
  KEY `shelter_key` (`shelter_key`),
  CONSTRAINT `category_ibfk_1` FOREIGN KEY (`shelter_key`) REFERENCES `shelter` (`shelter_key`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- 테이블 데이터 comma.category:~6 rows (대략적) 내보내기
INSERT IGNORE INTO `category` (`category_key`, `shelter_key`, `title_group`, `title`, `image`, `status`, `created_at`, `updated_at`) VALUES
	(1, 1, '공지사항', '공지1', NULL, 0, '2024-10-19 14:47:21', '2024-10-19 17:22:12'),
	(2, 1, '공지사항', '공지2', NULL, 0, '2024-10-19 14:47:21', '2024-10-19 17:22:15'),
	(3, 1, '공지사항', '공지3', NULL, 0, '2024-10-19 14:47:21', '2024-10-19 17:22:17'),
	(4, 1, '공지사항2', '공지21', NULL, 0, '2024-10-19 14:47:21', '2024-10-19 20:24:21'),
	(5, 1, '공지사항2', '공지22', NULL, 0, '2024-10-19 14:47:21', '2024-10-19 20:24:24'),
	(6, 1, '공지사항2', '공지23', NULL, 0, '2024-10-19 14:47:21', '2024-10-19 20:24:26');

-- 테이블 comma.comment 구조 내보내기
DROP TABLE IF EXISTS `comment`;
CREATE TABLE IF NOT EXISTS `comment` (
  `comment_key` bigint(20) NOT NULL AUTO_INCREMENT,
  `post_key` bigint(20) NOT NULL,
  `user_key` bigint(20) NOT NULL,
  `content` text NOT NULL,
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`comment_key`),
  KEY `post_key` (`post_key`),
  KEY `user_key` (`user_key`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`post_key`) REFERENCES `post` (`post_key`),
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`user_key`) REFERENCES `user` (`user_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- 테이블 데이터 comma.comment:~0 rows (대략적) 내보내기

-- 테이블 comma.comment_like 구조 내보내기
DROP TABLE IF EXISTS `comment_like`;
CREATE TABLE IF NOT EXISTS `comment_like` (
  `comment_like_key` bigint(20) NOT NULL AUTO_INCREMENT,
  `comment_key` bigint(20) NOT NULL,
  `user_key` bigint(20) NOT NULL,
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`comment_like_key`),
  KEY `comment_key` (`comment_key`),
  KEY `user_key` (`user_key`),
  CONSTRAINT `comment_like_ibfk_1` FOREIGN KEY (`comment_key`) REFERENCES `comment` (`comment_key`),
  CONSTRAINT `comment_like_ibfk_2` FOREIGN KEY (`user_key`) REFERENCES `user` (`user_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- 테이블 데이터 comma.comment_like:~0 rows (대략적) 내보내기

-- 테이블 comma.level 구조 내보내기
DROP TABLE IF EXISTS `level`;
CREATE TABLE IF NOT EXISTS `level` (
  `level` int(11) NOT NULL,
  `required_experience` int(11) NOT NULL,
  `stored_experience` int(11) NOT NULL,
  PRIMARY KEY (`level`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- 테이블 데이터 comma.level:~100 rows (대략적) 내보내기
INSERT IGNORE INTO `level` (`level`, `required_experience`, `stored_experience`) VALUES
	(1, 110, 0),
	(2, 120, 110),
	(3, 130, 230),
	(4, 140, 360),
	(5, 150, 500),
	(6, 160, 650),
	(7, 170, 810),
	(8, 180, 980),
	(9, 290, 1160),
	(10, 300, 1450),
	(11, 310, 1750),
	(12, 320, 2060),
	(13, 330, 2380),
	(14, 340, 2710),
	(15, 350, 3050),
	(16, 360, 3400),
	(17, 370, 3760),
	(18, 380, 4130),
	(19, 490, 4510),
	(20, 500, 5000),
	(21, 510, 5500),
	(22, 520, 6010),
	(23, 530, 6530),
	(24, 540, 7060),
	(25, 550, 7600),
	(26, 560, 8150),
	(27, 570, 8710),
	(28, 580, 9280),
	(29, 690, 9860),
	(30, 700, 10550),
	(31, 710, 11250),
	(32, 720, 11960),
	(33, 730, 12680),
	(34, 740, 13410),
	(35, 750, 14150),
	(36, 760, 14900),
	(37, 770, 15660),
	(38, 780, 16430),
	(39, 890, 17210),
	(40, 900, 18100),
	(41, 910, 19000),
	(42, 920, 19910),
	(43, 930, 20830),
	(44, 940, 21760),
	(45, 950, 22700),
	(46, 960, 23650),
	(47, 970, 24610),
	(48, 980, 25580),
	(49, 1090, 26560),
	(50, 1100, 27650),
	(51, 1110, 28750),
	(52, 1120, 29860),
	(53, 1130, 30980),
	(54, 1140, 32110),
	(55, 1150, 33250),
	(56, 1160, 34400),
	(57, 1170, 35560),
	(58, 1180, 36730),
	(59, 1290, 37910),
	(60, 1300, 39200),
	(61, 1310, 40500),
	(62, 1320, 41810),
	(63, 1330, 43130),
	(64, 1340, 44460),
	(65, 1350, 45800),
	(66, 1360, 47150),
	(67, 1370, 48510),
	(68, 1380, 49880),
	(69, 1490, 51260),
	(70, 1500, 52750),
	(71, 1510, 54250),
	(72, 1520, 55760),
	(73, 1530, 57280),
	(74, 1540, 58810),
	(75, 1550, 60350),
	(76, 1560, 61900),
	(77, 1570, 63460),
	(78, 1580, 65030),
	(79, 1690, 66610),
	(80, 1700, 68300),
	(81, 1710, 70000),
	(82, 1720, 71710),
	(83, 1730, 73430),
	(84, 1740, 75160),
	(85, 1750, 76900),
	(86, 1760, 78650),
	(87, 1770, 80410),
	(88, 1780, 82180),
	(89, 1890, 83960),
	(90, 1900, 85850),
	(91, 1910, 87750),
	(92, 1920, 89660),
	(93, 1930, 91580),
	(94, 1940, 93510),
	(95, 1950, 95450),
	(96, 1960, 97400),
	(97, 1970, 99360),
	(98, 1980, 101330),
	(99, 2090, 103310),
	(100, 2200, 105400);

-- 테이블 comma.oauth 구조 내보내기
DROP TABLE IF EXISTS `oauth`;
CREATE TABLE IF NOT EXISTS `oauth` (
  `oauth_key` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_key` bigint(20) NOT NULL,
  `company` varchar(255) NOT NULL,
  `company_key` varchar(255) NOT NULL,
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`oauth_key`),
  KEY `user_key` (`user_key`),
  CONSTRAINT `oauth_ibfk_1` FOREIGN KEY (`user_key`) REFERENCES `user` (`user_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- 테이블 데이터 comma.oauth:~0 rows (대략적) 내보내기

-- 테이블 comma.post 구조 내보내기
DROP TABLE IF EXISTS `post`;
CREATE TABLE IF NOT EXISTS `post` (
  `post_key` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_key` bigint(20) NOT NULL,
  `tag_key` bigint(20) NOT NULL,
  `user_key` bigint(20) NOT NULL,
  `title` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `views` int(11) DEFAULT 0,
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '0: 사용\r\n1: 미사용',
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`post_key`),
  KEY `category_key` (`category_key`),
  KEY `tag_key` (`tag_key`),
  KEY `user_key` (`user_key`),
  CONSTRAINT `post_ibfk_1` FOREIGN KEY (`category_key`) REFERENCES `category` (`category_key`),
  CONSTRAINT `post_ibfk_2` FOREIGN KEY (`tag_key`) REFERENCES `tag` (`tag_key`),
  CONSTRAINT `post_ibfk_3` FOREIGN KEY (`user_key`) REFERENCES `user` (`user_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- 테이블 데이터 comma.post:~0 rows (대략적) 내보내기

-- 테이블 comma.post_like 구조 내보내기
DROP TABLE IF EXISTS `post_like`;
CREATE TABLE IF NOT EXISTS `post_like` (
  `post_like_key` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_key` bigint(20) NOT NULL,
  `post_key` bigint(20) NOT NULL,
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`post_like_key`),
  KEY `user_key` (`user_key`),
  KEY `post_key` (`post_key`),
  CONSTRAINT `post_like_ibfk_1` FOREIGN KEY (`user_key`) REFERENCES `user` (`user_key`),
  CONSTRAINT `post_like_ibfk_2` FOREIGN KEY (`post_key`) REFERENCES `post` (`post_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- 테이블 데이터 comma.post_like:~0 rows (대략적) 내보내기

-- 테이블 comma.shelter 구조 내보내기
DROP TABLE IF EXISTS `shelter`;
CREATE TABLE IF NOT EXISTS `shelter` (
  `shelter_key` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `url` varchar(255) NOT NULL,
  `image` blob DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '0: 사용\r\n1: 미사용',
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`shelter_key`),
  UNIQUE KEY `url` (`url`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- 테이블 데이터 comma.shelter:~1 rows (대략적) 내보내기
INSERT IGNORE INTO `shelter` (`shelter_key`, `name`, `url`, `image`, `status`, `created_at`, `updated_at`) VALUES
	(1, '메이플스토리', 'maple', NULL, 0, '2024-10-19 14:45:22', '2024-10-19 14:45:58');

-- 테이블 comma.shelter_like 구조 내보내기
DROP TABLE IF EXISTS `shelter_like`;
CREATE TABLE IF NOT EXISTS `shelter_like` (
  `shelter_like_key` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_key` bigint(20) NOT NULL,
  `shelter_key` bigint(20) NOT NULL,
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`shelter_like_key`),
  KEY `user_key` (`user_key`),
  KEY `shelter_key` (`shelter_key`),
  CONSTRAINT `shelter_like_ibfk_1` FOREIGN KEY (`user_key`) REFERENCES `user` (`user_key`),
  CONSTRAINT `shelter_like_ibfk_2` FOREIGN KEY (`shelter_key`) REFERENCES `shelter` (`shelter_key`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- 테이블 데이터 comma.shelter_like:~1 rows (대략적) 내보내기

-- 테이블 comma.tag 구조 내보내기
DROP TABLE IF EXISTS `tag`;
CREATE TABLE IF NOT EXISTS `tag` (
  `tag_key` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_key` bigint(20) NOT NULL,
  `title` varchar(255) NOT NULL,
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`tag_key`),
  KEY `category_key` (`category_key`),
  CONSTRAINT `tag_ibfk_1` FOREIGN KEY (`category_key`) REFERENCES `category` (`category_key`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- 테이블 데이터 comma.tag:~12 rows (대략적) 내보내기
INSERT IGNORE INTO `tag` (`tag_key`, `category_key`, `title`, `created_at`) VALUES
	(1, 1, '공지1-1', '2024-10-19 17:22:43'),
	(2, 1, '공지1-2', '2024-10-19 17:22:51'),
	(3, 2, '공지2-1', '2024-10-19 17:22:51'),
	(4, 2, '공지2-2', '2024-10-19 17:22:51'),
	(5, 3, '공지3-1', '2024-10-19 17:22:51'),
	(6, 3, '공지3-2', '2024-10-19 17:22:51'),
	(8, 4, '41', '2024-10-19 17:22:51'),
	(9, 4, '42', '2024-10-19 17:22:51'),
	(10, 5, '51', '2024-10-19 17:22:51'),
	(11, 5, '52', '2024-10-19 17:22:51'),
	(12, 6, '61', '2024-10-19 17:22:51'),
	(13, 6, '62', '2024-10-19 17:22:51');

-- 테이블 comma.user 구조 내보내기
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `user_key` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '0: 정상\r\n1: 탈퇴\r\n2: 정지',
  `point` int(11) NOT NULL DEFAULT 0,
  `cash` int(11) NOT NULL DEFAULT 0,
  `role` int(11) NOT NULL DEFAULT 0 COMMENT '0: user\r\n1: admin',
  `nickname` varchar(255) DEFAULT NULL,
  `level` int(11) DEFAULT 1,
  `exp` int(11) DEFAULT 0,
  `last_login` timestamp NULL DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`user_key`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- 테이블 데이터 comma.user:~0 rows (대략적) 내보내기

-- 테이블 comma.user_badge 구조 내보내기
DROP TABLE IF EXISTS `user_badge`;
CREATE TABLE IF NOT EXISTS `user_badge` (
  `user_badge_key` bigint(20) NOT NULL AUTO_INCREMENT,
  `badge_key` bigint(20) NOT NULL,
  `user_key` bigint(20) NOT NULL,
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`user_badge_key`),
  KEY `badge_key` (`badge_key`),
  KEY `user_key` (`user_key`),
  CONSTRAINT `user_badge_ibfk_1` FOREIGN KEY (`badge_key`) REFERENCES `badge` (`badge_key`),
  CONSTRAINT `user_badge_ibfk_2` FOREIGN KEY (`user_key`) REFERENCES `user` (`user_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- 테이블 데이터 comma.user_badge:~0 rows (대략적) 내보내기

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
