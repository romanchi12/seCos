-- --------------------------------------------------------
-- Сервер:                       localhost
-- Версія сервера:               5.6.38 - MySQL Community Server (GPL)
-- ОС сервера:                   Win32
-- HeidiSQL Версія:              9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for restshop
CREATE DATABASE IF NOT EXISTS `hack` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `hack`;

-- Dumping structure for таблиця restshop.Role
CREATE TABLE IF NOT EXISTS `Role` (
  `RoleId` int(11) NOT NULL AUTO_INCREMENT,
  `RoleName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`RoleId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table restshop.Role: ~2 rows (приблизно)
/*!40000 ALTER TABLE `Role` DISABLE KEYS */;
INSERT INTO `Role` (`RoleId`, `RoleName`) VALUES
	(1, 'ADMIN'),
	(2, 'USER');
/*!40000 ALTER TABLE `Role` ENABLE KEYS */;

-- Dumping structure for таблиця restshop.User
CREATE TABLE IF NOT EXISTS `User` (
  `UserId` bigint(20) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(200) NOT NULL DEFAULT '0',
  `Enabled` int(11) NOT NULL DEFAULT '0',
  `AccountLocked` int(11) NOT NULL DEFAULT '0',
  `CredentialsExpired` int(11) NOT NULL DEFAULT '0',
  `AccountExpired` int(11) NOT NULL DEFAULT '0',
  `UserSurname` varchar(200) NOT NULL DEFAULT '0',
  `UserEmail` varchar(200) NOT NULL DEFAULT '0',
  `UserPassword` varchar(200) NOT NULL DEFAULT '0',
  `UserAddress` varchar(200) NOT NULL DEFAULT '0',
  `UserLanguage` varchar(200) NOT NULL DEFAULT '0',
  PRIMARY KEY (`UserId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- Dumping data for table restshop.User: ~0 rows (приблизно)
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` (`UserId`, `UserName`, `Enabled`, `AccountLocked`, `CredentialsExpired`, `AccountExpired`, `UserSurname`, `UserEmail`, `UserPassword`, `UserAddress`, `UserLanguage`) VALUES
	(4, 'romanchi', 1, 0, 0, 0, 'malyarchuk', 'romanchi40160@gmail.com', '$2a$10$1nkg4qdTRWfKjKE27TPEPOJpYwQOQ8YajpAyywsrdDLZ7PD4mWx5O', 'Boguna30', 'uk_UA'),
	(6, 'mykola', 1, 0, 0, 0, '0', '0', '$2a$10$1nkg4qdTRWfKjKE27TPEPOJpYwQOQ8YajpAyywsrdDLZ7PD4mWx5O', '0', '0');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;

-- Dumping structure for таблиця restshop.UserRoles
CREATE TABLE IF NOT EXISTS `UserRoles` (
  `USERID` bigint(20) NOT NULL,
  `ROLEID` int(11) NOT NULL,
  PRIMARY KEY (`USERID`,`ROLEID`),
  KEY `FK_UserRoles_Role` (`ROLEID`),
  CONSTRAINT `FK_UserRoles_Role` FOREIGN KEY (`ROLEID`) REFERENCES `Role` (`RoleId`),
  CONSTRAINT `FK_UserRoles_User` FOREIGN KEY (`USERID`) REFERENCES `User` (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table restshop.UserRoles: ~2 rows (приблизно)
/*!40000 ALTER TABLE `UserRoles` DISABLE KEYS */;
INSERT INTO `UserRoles` (`USERID`, `ROLEID`) VALUES
	(4, 1),
	(4, 2);
/*!40000 ALTER TABLE `UserRoles` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
