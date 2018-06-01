CREATE DATABASE  IF NOT EXISTS `nba` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `nba`;
-- MySQL dump 10.13  Distrib 5.7.22, for Linux (x86_64)
--
-- Host: localhost    Database: nba
-- ------------------------------------------------------
-- Server version	5.7.22-0ubuntu0.16.04.1

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
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity` (
  `ID` int(11) NOT NULL,
  `begin_time` date NOT NULL,
  `end_time` date NOT NULL,
  `name` varchar(45) NOT NULL,
  `content` varchar(45) NOT NULL,
  `gold_multiple` double NOT NULL DEFAULT '1',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `belongteam`
--

DROP TABLE IF EXISTS `belongteam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `belongteam` (
  `ID` int(11) NOT NULL,
  `players_num` int(11) NOT NULL,
  `members` int(11) NOT NULL,
  `lineup` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_belongTeam_members1_idx` (`members`),
  KEY `fk_belongTeam_出战阵容1_idx` (`lineup`),
  CONSTRAINT `fk_belongTeam_lineup1` FOREIGN KEY (`lineup`) REFERENCES `lineup` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_belongTeam_members1` FOREIGN KEY (`members`) REFERENCES `teammembers` (`player_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `belongteam`
--

LOCK TABLES `belongteam` WRITE;
/*!40000 ALTER TABLE `belongteam` DISABLE KEYS */;
/*!40000 ALTER TABLE `belongteam` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat`
--

DROP TABLE IF EXISTS `chat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chat` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `send_ID` int(11) NOT NULL,
  `receive_ID` int(11) NOT NULL DEFAULT '-1',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `content` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat`
--

LOCK TABLES `chat` WRITE;
/*!40000 ALTER TABLE `chat` DISABLE KEYS */;
INSERT INTO `chat` VALUES (29,0,-1,'2018-05-22 03:11:25','hello'),(30,1,-1,'2018-05-22 04:11:25','hi'),(31,2,-1,'2018-05-22 05:11:25','nice to meet you'),(32,3,-1,'2018-05-22 06:11:25','+10086'),(33,1,0,'2018-05-22 03:11:25','how\'s it going'),(34,2,0,'2018-05-22 03:11:25','what\'s up,man?'),(35,0,2,'2018-05-24 02:18:38','nothing'),(36,0,-1,'2018-05-24 03:09:00','233333');
/*!40000 ALTER TABLE `chat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consumables`
--

DROP TABLE IF EXISTS `consumables`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `consumables` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `goods_ID` int(11) DEFAULT NULL,
  `user_ID` int(11) DEFAULT NULL,
  `live_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consumables`
--

LOCK TABLES `consumables` WRITE;
/*!40000 ALTER TABLE `consumables` DISABLE KEYS */;
INSERT INTO `consumables` VALUES (61,2,0,'2018-06-27 09:20:48'),(62,3,0,'2018-07-16 09:20:51'),(63,4,0,'2018-06-19 01:41:27'),(64,6,0,'2018-07-31 03:10:02');
/*!40000 ALTER TABLE `consumables` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `d_tactics`
--

DROP TABLE IF EXISTS `d_tactics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `d_tactics` (
  `ID` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `content` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `d_tactics`
--

LOCK TABLES `d_tactics` WRITE;
/*!40000 ALTER TABLE `d_tactics` DISABLE KEYS */;
/*!40000 ALTER TABLE `d_tactics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friends`
--

DROP TABLE IF EXISTS `friends`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `friends` (
  `user_ID1` int(11) NOT NULL,
  `user_ID2` int(11) NOT NULL,
  `last_chat` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friends`
--

LOCK TABLES `friends` WRITE;
/*!40000 ALTER TABLE `friends` DISABLE KEYS */;
INSERT INTO `friends` VALUES (1,2,'2018-04-19 08:16:10'),(2,1,'2018-04-19 08:16:10'),(0,1,'2018-04-20 08:16:20'),(1,0,'2018-04-20 08:16:20'),(0,2,'2018-04-21 08:16:20'),(2,0,'2018-04-21 08:16:20'),(0,3,'2018-04-21 09:16:20'),(3,0,'2018-04-21 09:16:20');
/*!40000 ALTER TABLE `friends` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goods`
--

DROP TABLE IF EXISTS `goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `goods` (
  `ID` int(11) NOT NULL COMMENT '	',
  `goods_name` varchar(45) NOT NULL,
  `goods_intro` varchar(45) NOT NULL,
  `goods_type` varchar(10) DEFAULT NULL,
  `goods_attr` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goods`
--

LOCK TABLES `goods` WRITE;
/*!40000 ALTER TABLE `goods` DISABLE KEYS */;
INSERT INTO `goods` VALUES (0,'YaoMing','a basketballl player','player',-1),(1,'YaoMing','a basketballl player','piece',30),(2,'double gold','get double gold in each match','equipment',3),(3,'YaoMing','a 7-day player','consumable',7),(4,'double exp','a 3-day exp','equipment',7),(5,'Jodrn','the best basketplayer','player',-1),(6,'Jodrn','the best basketplayer,7_day','consumable',7),(7,'Jodrn','the best basketplayer','piece',50);
/*!40000 ALTER TABLE `goods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lineup`
--

DROP TABLE IF EXISTS `lineup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lineup` (
  `ID` int(11) NOT NULL,
  `C` int(11) NOT NULL,
  `PF` int(11) NOT NULL,
  `SF` int(11) NOT NULL,
  `SG` int(11) NOT NULL,
  `PG` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lineup`
--

LOCK TABLES `lineup` WRITE;
/*!40000 ALTER TABLE `lineup` DISABLE KEYS */;
/*!40000 ALTER TABLE `lineup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `o_tactics`
--

DROP TABLE IF EXISTS `o_tactics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `o_tactics` (
  `ID` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `content` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `o_tactics`
--

LOCK TABLES `o_tactics` WRITE;
/*!40000 ALTER TABLE `o_tactics` DISABLE KEYS */;
/*!40000 ALTER TABLE `o_tactics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `package`
--

DROP TABLE IF EXISTS `package`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `package` (
  `package_id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_ID` int(11) NOT NULL,
  `user_ID` int(11) DEFAULT NULL,
  `goods_num` int(11) DEFAULT '1',
  PRIMARY KEY (`package_id`),
  KEY `fk_package_goods1_idx` (`goods_ID`),
  KEY `fk_package_user` (`user_ID`),
  CONSTRAINT `fk_package_goods1` FOREIGN KEY (`goods_ID`) REFERENCES `goods` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=512 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `package`
--

LOCK TABLES `package` WRITE;
/*!40000 ALTER TABLE `package` DISABLE KEYS */;
INSERT INTO `package` VALUES (1,1,0,80),(2,3,0,80),(3,4,0,80),(4,4,0,99),(6,6,0,98),(7,7,0,100),(15,2,0,50),(16,2,0,50),(17,2,0,50),(18,2,0,50),(19,2,0,50),(20,2,0,50),(21,2,0,50),(22,2,0,50),(23,2,0,50),(24,2,0,50),(25,2,0,50),(26,2,0,50),(27,2,0,50),(28,2,0,50),(29,2,0,50),(30,2,0,50),(31,2,0,50),(32,2,0,50),(33,2,0,50),(34,2,0,50),(35,2,0,50),(36,2,0,50),(37,2,0,50),(38,2,0,50),(39,2,0,50),(40,2,0,50),(41,2,0,50),(42,2,0,50),(43,2,0,50),(44,2,0,50),(45,2,0,50),(46,2,0,50),(47,2,0,50),(48,2,0,50),(49,2,0,50),(50,2,0,50),(51,2,0,50),(52,2,0,50),(53,2,0,50),(54,2,0,50),(55,2,0,50),(56,2,0,50),(57,2,0,50),(58,2,0,50),(59,2,0,50),(60,2,0,50),(61,2,0,50),(62,2,0,50),(63,2,0,50),(64,2,0,50),(65,2,0,50),(66,2,0,50),(67,2,0,50),(68,2,0,50),(69,2,0,50),(70,2,0,50),(71,2,0,50),(72,2,0,50),(73,2,0,50),(74,2,0,50),(75,2,0,50),(76,2,0,50),(77,2,0,50),(78,2,0,50),(79,2,0,50),(80,2,0,50),(81,2,0,50),(82,2,0,50),(83,2,0,50),(84,2,0,50),(85,2,0,50),(86,2,0,50),(87,2,0,50),(88,2,0,50),(89,2,0,50),(90,2,0,50),(91,2,0,50),(92,2,0,50),(93,2,0,50),(94,2,0,50),(95,2,0,50),(96,2,0,50),(97,2,0,50),(98,2,0,50),(99,2,0,50),(100,2,0,50),(101,2,0,50),(102,2,0,50),(103,2,0,50),(104,2,0,50),(105,2,0,50),(106,2,0,50),(107,2,0,50),(108,2,0,50),(109,2,0,50),(110,2,0,50),(111,2,0,50),(112,2,0,50),(113,2,0,50),(114,2,0,50),(115,2,0,50),(116,2,0,50),(117,2,0,50),(118,2,0,50),(119,2,0,50),(120,2,0,50),(121,2,0,50),(122,2,0,50),(123,2,0,50),(124,2,0,50),(125,2,0,50),(126,2,0,50),(127,2,0,50),(128,2,0,50),(129,2,0,50),(130,2,0,50),(131,2,0,50),(132,2,0,50),(133,2,0,50),(134,2,0,50),(135,2,0,50),(136,2,0,50),(137,2,0,50),(138,2,0,50),(139,2,0,50),(140,2,0,50),(141,2,0,50),(142,2,0,50),(143,2,0,50),(144,2,0,50),(145,2,0,50),(146,2,0,50),(147,2,0,50),(148,2,0,50),(149,2,0,50),(150,2,0,50),(151,2,0,50),(152,2,0,50),(153,2,0,50),(154,2,0,50),(155,2,0,50),(156,2,0,50),(157,2,0,50),(158,2,0,50),(159,2,0,50),(160,2,0,50),(161,2,0,50),(162,2,0,50),(163,2,0,50),(164,2,0,50),(165,2,0,50),(166,2,0,50),(167,2,0,50),(168,2,0,50),(169,2,0,50),(170,2,0,50),(171,2,0,50),(172,2,0,50),(173,2,0,50),(174,2,0,50),(175,2,0,50),(176,2,0,50),(177,2,0,50),(178,2,0,50),(179,2,0,50),(180,2,0,50),(181,2,0,50),(182,2,0,50),(183,2,0,50),(184,2,0,50),(185,2,0,50),(186,2,0,50),(187,2,0,50),(188,2,0,50),(189,2,0,50),(190,2,0,50),(191,2,0,50),(192,2,0,50),(193,2,0,50),(194,2,0,50),(195,2,0,50),(196,2,0,50),(197,2,0,50),(198,2,0,50),(199,2,0,50),(200,2,0,50),(201,2,0,50),(202,2,0,50),(203,2,0,50),(204,2,0,50),(205,2,0,50),(206,2,0,50),(207,2,0,50),(208,2,0,50),(209,2,0,50),(210,2,0,50),(211,2,0,50),(212,2,0,50),(213,2,0,50),(214,2,0,50),(215,2,0,50),(216,2,0,50),(217,2,0,50),(218,2,0,50),(219,2,0,50),(220,2,0,50),(221,2,0,50),(222,2,0,50),(223,2,0,50),(224,2,0,50),(225,2,0,50),(226,2,0,50),(227,2,0,50),(228,2,0,50),(229,2,0,50),(230,2,0,50),(231,2,0,50),(232,2,0,50),(233,2,0,50),(234,2,0,50),(235,2,0,50),(236,2,0,50),(237,2,0,50),(238,2,0,50),(239,2,0,50),(240,2,0,50),(241,2,0,50),(242,2,0,50),(243,2,0,50),(244,2,0,50),(245,2,0,50),(246,2,0,50),(247,2,0,50),(248,2,0,50),(249,2,0,50),(250,2,0,50),(251,2,0,50),(252,2,0,50),(253,2,0,50),(254,2,0,50),(255,2,0,50),(256,2,0,50),(257,2,0,50),(258,2,0,50),(259,2,0,50),(260,2,0,50),(261,2,0,50),(262,2,0,50),(263,2,0,50),(264,2,0,50),(265,2,0,50),(266,2,0,50),(267,2,0,50),(268,2,0,50),(269,2,0,50),(270,2,0,50),(271,2,0,50),(272,2,0,50),(273,2,0,50),(274,2,0,50),(275,2,0,50),(276,2,0,50),(277,2,0,50),(278,2,0,50),(279,2,0,50),(280,2,0,50),(281,2,0,50),(282,2,0,50),(283,2,0,50),(284,2,0,50),(285,2,0,50),(286,2,0,50),(287,2,0,50),(288,2,0,50),(289,2,0,50),(290,2,0,50),(291,2,0,50),(292,2,0,50),(293,2,0,50),(294,2,0,50),(295,2,0,50),(296,2,0,50),(297,2,0,50),(298,2,0,50),(299,2,0,50),(300,2,0,50),(301,2,0,50),(302,2,0,50),(303,2,0,50),(304,2,0,50),(305,2,0,50),(306,2,0,50),(307,2,0,50),(308,2,0,50),(309,2,0,50),(310,2,0,50),(311,2,0,50),(312,2,0,50),(313,2,0,50),(314,2,0,50),(315,2,0,50),(316,2,0,50),(317,2,0,50),(318,2,0,50),(319,2,0,50),(320,2,0,50),(321,2,0,50),(322,2,0,50),(323,2,0,50),(324,2,0,50),(325,2,0,50),(326,2,0,50),(327,2,0,50),(328,2,0,50),(329,2,0,50),(330,2,0,50),(331,2,0,50),(332,2,0,50),(333,2,0,50),(334,2,0,50),(335,2,0,50),(336,2,0,50),(337,2,0,50),(338,2,0,50),(339,2,0,50),(340,2,0,50),(341,2,0,50),(342,2,0,50),(343,2,0,50),(344,2,0,50),(345,2,0,50),(346,2,0,50),(347,2,0,50),(348,2,0,50),(349,2,0,50),(350,2,0,50),(351,2,0,50),(352,2,0,50),(353,2,0,50),(354,2,0,50),(355,2,0,50),(356,2,0,50),(357,2,0,50),(358,2,0,50),(359,2,0,50),(360,2,0,50),(361,2,0,50),(362,2,0,50),(363,2,0,50),(364,2,0,50),(365,2,0,50),(366,2,0,50),(367,2,0,50),(368,2,0,50),(369,2,0,50),(370,2,0,50),(371,2,0,50),(372,2,0,50),(373,2,0,50),(374,2,0,50),(375,2,0,50),(376,2,0,50),(377,2,0,50),(378,2,0,50),(379,2,0,50),(380,2,0,50),(381,2,0,50),(382,2,0,50),(383,2,0,50),(384,2,0,50),(385,2,0,50),(386,2,0,50),(387,2,0,50),(388,2,0,50),(389,2,0,50),(390,2,0,50),(391,2,0,50),(392,2,0,50),(393,2,0,50),(394,2,0,50),(395,2,0,50),(396,2,0,50),(397,2,0,50),(398,2,0,50),(399,2,0,50),(400,2,0,50),(401,2,0,50),(402,2,0,50),(403,2,0,50),(404,2,0,50),(405,2,0,50),(406,2,0,50),(407,2,0,50),(408,2,0,50),(409,2,0,50),(410,2,0,50),(411,2,0,50),(412,2,0,50),(413,2,0,50),(414,2,0,50),(415,2,0,50),(416,2,0,50),(417,2,0,50),(418,2,0,50),(419,2,0,50),(420,2,0,50),(421,2,0,50),(422,2,0,50),(423,2,0,50),(424,2,0,50),(425,2,0,50),(426,2,0,50),(427,2,0,50),(428,2,0,50),(429,2,0,50),(430,2,0,50),(431,2,0,50),(432,2,0,50),(433,2,0,50),(434,2,0,50),(435,2,0,50),(436,2,0,50),(437,2,0,50),(438,2,0,50),(439,2,0,50),(440,2,0,50),(441,2,0,50),(442,2,0,50),(443,2,0,50),(444,2,0,50),(445,2,0,50),(446,2,0,50),(447,2,0,50),(448,2,0,50),(449,2,0,50),(450,2,0,50),(451,2,0,50),(452,2,0,50),(453,2,0,50),(454,2,0,50),(455,2,0,50),(456,2,0,50),(457,2,0,50),(458,2,0,50),(459,2,0,50),(460,2,0,50),(461,2,0,50),(462,2,0,50),(463,2,0,50),(464,2,0,50),(465,2,0,50),(466,2,0,50),(467,2,0,50),(468,2,0,50),(469,2,0,50),(470,2,0,50),(471,2,0,50),(472,2,0,50),(473,2,0,50),(474,2,0,50),(475,2,0,50),(476,2,0,50),(477,2,0,50),(478,2,0,50),(479,2,0,50),(480,2,0,50),(481,2,0,50),(482,2,0,50),(483,2,0,50),(484,2,0,50),(485,2,0,50),(486,2,0,50),(487,2,0,50),(488,2,0,50),(489,2,0,50),(490,2,0,50),(491,2,0,50),(492,2,0,50),(493,2,0,50),(494,2,0,50),(495,2,0,50),(496,2,0,50),(497,2,0,50),(498,2,0,50),(499,2,0,50),(500,2,0,50),(501,2,0,50),(502,2,0,50),(503,2,0,50),(504,2,0,50),(505,2,0,50),(506,2,0,50),(507,2,0,50),(508,2,0,50),(509,2,0,50),(510,2,0,50),(511,2,0,50);
/*!40000 ALTER TABLE `package` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `players`
--

DROP TABLE IF EXISTS `players`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `players` (
  `ID` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `salary` int(11) NOT NULL,
  `info_id` int(11) NOT NULL,
  `stats_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_players_players_info_idx` (`info_id`),
  KEY `fk_players_players_stats_idx` (`stats_ID`),
  CONSTRAINT `fk_players_info_id1` FOREIGN KEY (`info_id`) REFERENCES `players_info` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_players_stats_ID1` FOREIGN KEY (`stats_ID`) REFERENCES `players_stats` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `players`
--

LOCK TABLES `players` WRITE;
/*!40000 ALTER TABLE `players` DISABLE KEYS */;
/*!40000 ALTER TABLE `players` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `players_info`
--

DROP TABLE IF EXISTS `players_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `players_info` (
  `id` int(11) NOT NULL,
  `birth` date NOT NULL,
  `nation` varchar(45) NOT NULL,
  `draft` varchar(45) NOT NULL,
  `height` double NOT NULL,
  `weight` double NOT NULL,
  `arms` double DEFAULT NULL,
  `stand_tall` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `players_info`
--

LOCK TABLES `players_info` WRITE;
/*!40000 ALTER TABLE `players_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `players_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `players_stats`
--

DROP TABLE IF EXISTS `players_stats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `players_stats` (
  `ID` int(11) NOT NULL,
  `time` int(11) NOT NULL,
  `point` double NOT NULL,
  `rebound` double NOT NULL,
  `assist` double NOT NULL,
  `steal` double NOT NULL COMMENT '	',
  `block` double NOT NULL COMMENT '				',
  `to` double NOT NULL,
  `shot` double NOT NULL,
  `shotRate` double NOT NULL,
  `threeShot` double NOT NULL,
  `threeRate` double NOT NULL,
  `freeShot` double NOT NULL,
  `freeRate` double NOT NULL,
  `efg` double NOT NULL,
  `TS` double NOT NULL,
  `offRtg` double NOT NULL,
  `defRtg` double NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `players_stats`
--

LOCK TABLES `players_stats` WRITE;
/*!40000 ALTER TABLE `players_stats` DISABLE KEYS */;
/*!40000 ALTER TABLE `players_stats` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `setting`
--

DROP TABLE IF EXISTS `setting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `setting` (
  `ID` int(11) NOT NULL,
  `quality` int(11) NOT NULL,
  `music` tinyint(1) NOT NULL,
  `noyice` tinyint(1) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `setting`
--

LOCK TABLES `setting` WRITE;
/*!40000 ALTER TABLE `setting` DISABLE KEYS */;
/*!40000 ALTER TABLE `setting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teammembers`
--

DROP TABLE IF EXISTS `teammembers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teammembers` (
  `player_id` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `position` varchar(2) NOT NULL,
  PRIMARY KEY (`player_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teammembers`
--

LOCK TABLES `teammembers` WRITE;
/*!40000 ALTER TABLE `teammembers` DISABLE KEYS */;
/*!40000 ALTER TABLE `teammembers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `ID` int(11) NOT NULL,
  `userinfo` int(11) NOT NULL,
  `setting` int(11) NOT NULL,
  `power` int(11) NOT NULL,
  `money` int(11) NOT NULL,
  `friends_num` int(11) NOT NULL,
  `package_num` int(11) NOT NULL,
  `O_tactics` int(11) DEFAULT NULL,
  `D_tactics` int(11) DEFAULT NULL,
  `user_name` char(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_user_userinfo_idx` (`userinfo`),
  KEY `fk_user_setting1_idx` (`setting`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (0,0,0,0,0,0,0,0,0,'本人'),(1,1,1,1,1,1,1,1,1,'山东及时雨'),(2,2,2,2,2,2,2,2,2,'河北镇关西'),(3,3,3,3,3,3,3,3,3,'路人甲');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_info` (
  `user_id` int(11) NOT NULL,
  `belongTeam` int(11) NOT NULL,
  `level` int(11) NOT NULL,
  `VIP` int(11) NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `fk_user_Info_belongTeam1_idx` (`belongTeam`),
  CONSTRAINT `fk_user_Info_belongTeam1` FOREIGN KEY (`belongTeam`) REFERENCES `belongteam` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vip_info`
--

DROP TABLE IF EXISTS `vip_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vip_info` (
  `VIP_level` int(11) NOT NULL,
  `gold_multiple` double NOT NULL,
  `win_multiple` double NOT NULL,
  PRIMARY KEY (`VIP_level`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vip_info`
--

LOCK TABLES `vip_info` WRITE;
/*!40000 ALTER TABLE `vip_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `vip_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-24 11:43:45
