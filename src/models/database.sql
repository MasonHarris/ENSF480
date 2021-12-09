DROP PROPERTY_RENTAL IF EXISTS PROPERTY_RENTAL;
CREATE DATABASE PROPERTY_RENTAL; 
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
	('ice','belowfreezing',7),
	('mike','celesius111',8),
	('yori122','renter111',12);

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
	('mike','Mike','miku@me.com'),
	('sandy','Sandy','sandy1234@gmail.com');

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


# Dump of table NOTIFICATION
# ------------------------------------------------------------

DROP TABLE IF EXISTS `NOTIFICATION`;

CREATE TABLE `NOTIFICATION` (
  `Username` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `noOfBathrooms` int DEFAULT NULL,
  `noOfBedrooms` int DEFAULT NULL,
  `Furnished` tinyint(1) DEFAULT NULL,
  `cityQuadrant` char(2) DEFAULT NULL,
  `propertyType` varchar(256) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `NOTIFICATION` WRITE;
/*!40000 ALTER TABLE `NOTIFICATION` DISABLE KEYS */;

INSERT INTO `NOTIFICATION` (`Username`, `noOfBathrooms`, `noOfBedrooms`, `Furnished`, `cityQuadrant`, `propertyType`)
VALUES
	('jack23',4,3,0,'NE','Townhouse'),
	('gary',1,1,1,'NW','Apartment');

/*!40000 ALTER TABLE `NOTIFICATION` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table NOTIFICATION_LANDLORD
# ------------------------------------------------------------

DROP TABLE IF EXISTS `NOTIFICATION_LANDLORD`;

CREATE TABLE `NOTIFICATION_LANDLORD` (
  `Username` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `renter_email` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `property_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `NOTIFICATION_LANDLORD` WRITE;
/*!40000 ALTER TABLE `NOTIFICATION_LANDLORD` DISABLE KEYS */;

INSERT INTO `NOTIFICATION_LANDLORD` (`Username`, `renter_email`, `property_id`)
VALUES
	('doctor','gary@123.com',1),
	('doctor','hi@me.com',1);

/*!40000 ALTER TABLE `NOTIFICATION_LANDLORD` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table NOTIFICATION_RENTER
# ------------------------------------------------------------

DROP TABLE IF EXISTS `NOTIFICATION_RENTER`;

CREATE TABLE `NOTIFICATION_RENTER` (
  `Username` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `property_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `NOTIFICATION_RENTER` WRITE;
/*!40000 ALTER TABLE `NOTIFICATION_RENTER` DISABLE KEYS */;

INSERT INTO `NOTIFICATION_RENTER` (`Username`, `property_id`)
VALUES
	('gary',1);

/*!40000 ALTER TABLE `NOTIFICATION_RENTER` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table PERIOD_FEE
# ------------------------------------------------------------

DROP TABLE IF EXISTS `PERIOD_FEE`;

CREATE TABLE `PERIOD_FEE` (
  `startListingPeriod` int DEFAULT NULL,
  `fee` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `PERIOD_FEE` WRITE;
/*!40000 ALTER TABLE `PERIOD_FEE` DISABLE KEYS */;

INSERT INTO `PERIOD_FEE` (`startListingPeriod`, `fee`)
VALUES
	(40,30.03);

/*!40000 ALTER TABLE `PERIOD_FEE` ENABLE KEYS */;
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
  `amountofFee` double DEFAULT NULL,
  `address` varchar(256) DEFAULT NULL,
  `isPaid` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`propertyID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `PROPERTY` WRITE;
/*!40000 ALTER TABLE `PROPERTY` DISABLE KEYS */;

INSERT INTO `PROPERTY` (`propertyType`, `propertyID`, `isListed`, `noOfBedrooms`, `noOfBathrooms`, `Furnished`, `cityQuadrant`, `listingPeriod`, `landlordUsername`, `listingState`, `amountofFee`, `address`, `isPaid`)
VALUES
	('Apartment',1,1,1,1,1,'NW',40,'doctor','Active',30.33,'12 Ave',1),
	('Townhouse',2,1,3,3,1,'SE',20,'sandy','Rented',30.33,'20 World Way',1),
	('Apartment',3,1,2,2,0,'NW',10,'sandy','Active',30.33,'44 User Hwy',1),
	('Attached House',4,0,5,3,0,'NE',30,'doctor','Registered',30.33,'81 Easter Ave',0),
	('Detached House',5,0,2,1,0,'SE',15,'mike','Suspended',30.33,'45 Road Street',0),
	('House',6,1,3,3,1,'NE',10,'mike','Active',30.33,'23 Hunter Hwy',1);

/*!40000 ALTER TABLE `PROPERTY` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table RENTER
# ------------------------------------------------------------

DROP TABLE IF EXISTS `RENTER`;

CREATE TABLE `RENTER` (
  `Username` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `email` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `RENTER` WRITE;
/*!40000 ALTER TABLE `RENTER` DISABLE KEYS */;

INSERT INTO `RENTER` (`Username`, `email`)
VALUES
	('cats34','cat@5.com'),
	('gary','gary@123.com'),
	('ice','cold@water.com'),
	('jack23','jack222@g.com'),
	('yori122','yori@4.com');

/*!40000 ALTER TABLE `RENTER` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
