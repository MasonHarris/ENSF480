


USE PROPERTY_RENTAL;
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
SET NAMES utf8mb4;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE='NO_AUTO_VALUE_ON_ZERO', SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table ACCOUNT
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ACCOUNT`;

CREATE TABLE `ACCOUNT` (
  `Username` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `Password` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `ID` int unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `ACCOUNT` WRITE;
/*!40000 ALTER TABLE `ACCOUNT` DISABLE KEYS */;

INSERT INTO `ACCOUNT` (`Username`, `Password`, `ID`)
VALUES
	('gary','hello1234',1),
	('jack23','goodbye432',2),
	('cats34','sfmk3ml$312',3),
	('doctor','wdsd341212',4),
	('sandy','n2nk23km2',5),
	('jake','world123',6),
	('ai', 'hayasaka',7);
/*!40000 ALTER TABLE `ACCOUNT` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table LANDLORD
# ------------------------------------------------------------

DROP TABLE IF EXISTS `LANDLORD`;

CREATE TABLE `LANDLORD` (
  `Username` varchar(256) NOT NULL,
  `name` varchar(256) DEFAULT NULL,
  `emailAddress` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `LANDLORD` WRITE;
/*!40000 ALTER TABLE `LANDLORD` DISABLE KEYS */;

INSERT INTO `LANDLORD` (`Username`, `name`, `emailAddress`)
VALUES
	('doctor','Howl','howl@me.com'),
	('sandy','Sandy','sandy1234@gmail.com'),
    ('ai','hayasaka','email@email.com');

/*!40000 ALTER TABLE `LANDLORD` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table MANAGER
# ------------------------------------------------------------

DROP TABLE IF EXISTS `MANAGER`;

CREATE TABLE `MANAGER` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `Username` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `MANAGER` WRITE;
/*!40000 ALTER TABLE `MANAGER` DISABLE KEYS */;

INSERT INTO `MANAGER` (`id`, `Username`)
VALUES
	(1,'jake');

/*!40000 ALTER TABLE `MANAGER` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table PROPERTY
# ------------------------------------------------------------

DROP TABLE IF EXISTS `PROPERTY`;

CREATE TABLE `PROPERTY` (
  `propertyType` varchar(256) DEFAULT NULL,
  `propertyID` int unsigned NOT NULL AUTO_INCREMENT,
  `isListed` tinyint(1) DEFAULT NULL,
  `noOfBedrooms` int DEFAULT NULL,
  `noOfBathrooms` int DEFAULT NULL,
  `Furnished` tinyint(1) DEFAULT NULL,
  `cityQuadrant` char(2) DEFAULT NULL,
  `listingPeriod` int DEFAULT NULL,
  `landlordUsername` varchar(256) DEFAULT NULL,
  `listingState` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`propertyID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `PROPERTY` WRITE;
/*!40000 ALTER TABLE `PROPERTY` DISABLE KEYS */;

INSERT INTO `PROPERTY` (`propertyType`, `propertyID`, `isListed`, `noOfBedrooms`, `noOfBathrooms`, `Furnished`, `cityQuadrant`, `listingPeriod`, `landlordUsername`, `listingState`)
VALUES
	('Apartment',1,1,1,1,1,'NW',40,'doctor','Active'),
	('Townhouse',2,1,3,3,1,'SE',20,'sandy','Rented');

/*!40000 ALTER TABLE `PROPERTY` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
