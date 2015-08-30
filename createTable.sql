-- MySQL dump 10.13  Distrib 5.6.19, for osx10.7 (i386)
--
-- Host: 127.0.0.1    Database: fjb
-- ------------------------------------------------------
-- Server version	5.6.25

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `examine`
--

DROP TABLE IF EXISTS `examine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `examine` (
  `subject` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `examineDistinct` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `start_time` int(11) NOT NULL,
  `end_time` int(11) NOT NULL,
  `room` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `sign_up` int(11) NOT NULL DEFAULT '0',
  `max_num` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`subject`,`examineDistinct`,`start_time`,`end_time`,`room`),
  KEY `exe_map_room_idx` (`examineDistinct`,`room`),
  CONSTRAINT `exe_map_distinct_room` FOREIGN KEY (`examineDistinct`, `room`) REFERENCES `examine_room` (`examineDistinct`, `name`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `exe_map_subject` FOREIGN KEY (`subject`) REFERENCES `examine_subject` (`subject`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `examine_distinct`
--

DROP TABLE IF EXISTS `examine_distinct`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `examine_distinct` (
  `name` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `address` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `examine_room`
--

DROP TABLE IF EXISTS `examine_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `examine_room` (
  `examineDistinct` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `num` int(11) NOT NULL,
  PRIMARY KEY (`examineDistinct`,`name`),
  CONSTRAINT `room_distinct` FOREIGN KEY (`examineDistinct`) REFERENCES `examine_distinct` (`name`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `examine_subject`
--

DROP TABLE IF EXISTS `examine_subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `examine_subject` (
  `subject` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `charge` int(11) NOT NULL,
  `reg_time` int(11) NOT NULL,
  `result_time` int(11) NOT NULL,
  `note` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`subject`,`reg_time`,`result_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fenhang`
--

DROP TABLE IF EXISTS `fenhang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fenhang` (
  `id` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `zonghangid` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `address` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `contactPeople` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`,`zonghangid`),
  KEY `id_idx` (`zonghangid`),
  CONSTRAINT `fenhangFK` FOREIGN KEY (`zonghangid`) REFERENCES `zonghang` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `order_id` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `content` longtext COLLATE utf8_unicode_ci NOT NULL,
  `charge` int(11) NOT NULL,
  `time` int(11) NOT NULL,
  `payed` varchar(45) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'false',
  `status` varchar(45) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'open',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `practice`
--

DROP TABLE IF EXISTS `practice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `practice` (
  `id` int(11) NOT NULL,
  `question` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `answer` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `type` int(11) NOT NULL,
  `A` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `B` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `C` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `D` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `photo` mediumblob,
  `E` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `hasPhoto` varchar(10) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'false',
  PRIMARY KEY (`id`,`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` varchar(20) CHARACTER SET latin1 NOT NULL,
  `username` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(45) CHARACTER SET latin1 NOT NULL,
  `role` varchar(45) CHARACTER SET latin1 NOT NULL,
  `sessionId` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `photo` mediumblob,
  `sex` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `zonghang` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fenhang` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `zhihang` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `mobile` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `fenlichu` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `mail` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `practice` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `zhihang`
--

DROP TABLE IF EXISTS `zhihang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zhihang` (
  `id` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `fenhangid` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `address` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `contactPeople` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`,`fenhangid`),
  KEY `id_idx` (`fenhangid`),
  CONSTRAINT `zhihangFK` FOREIGN KEY (`fenhangid`) REFERENCES `fenhang` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `zonghang`
--

DROP TABLE IF EXISTS `zonghang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zonghang` (
  `id` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `address` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `contactPeople` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-08-30 21:44:19
