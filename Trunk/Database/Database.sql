CREATE DATABASE  IF NOT EXISTS `meetschema` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `meetschema`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: meetschema
-- ------------------------------------------------------
-- Server version	5.6.13

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
-- Table structure for table `blacklist`
--

DROP TABLE IF EXISTS `blacklist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `blacklist` (
  `username` varchar(100) NOT NULL,
  `usernameblackuser` varchar(100) NOT NULL,
  PRIMARY KEY (`username`,`usernameblackuser`),
  KEY `fk_blackuser_idx` (`usernameblackuser`),
  CONSTRAINT `fk_black_user` FOREIGN KEY (`usernameblackuser`) REFERENCES `users` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='The black list of users for one user';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blacklist`
--

LOCK TABLES `blacklist` WRITE;
/*!40000 ALTER TABLE `blacklist` DISABLE KEYS */;
/*!40000 ALTER TABLE `blacklist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friends_requests`
--

DROP TABLE IF EXISTS `friends_requests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `friends_requests` (
  `username` varchar(100) NOT NULL,
  `usernamefriend` varchar(100) NOT NULL,
  `startdate` char(14) NOT NULL,
  `message` varchar(250) DEFAULT NULL,
  `daterequest` datetime NOT NULL,
  `accepted` tinyint(1) DEFAULT NULL,
  `enddate` char(14) NOT NULL,
  PRIMARY KEY (`username`,`usernamefriend`,`startdate`),
  KEY `fk_friend_requst_idx` (`usernamefriend`),
  CONSTRAINT `fk_frienduser` FOREIGN KEY (`usernamefriend`) REFERENCES `users` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_2` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friends_requests`
--

LOCK TABLES `friends_requests` WRITE;
/*!40000 ALTER TABLE `friends_requests` DISABLE KEYS */;
INSERT INTO `friends_requests` VALUES ('antonio','luirzy','20130921000000','Ciao sono Antonio, come stati???','2013-09-21 12:19:34',NULL,'20130921154002');
/*!40000 ALTER TABLE `friends_requests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messages` (
  `username` varchar(100) NOT NULL,
  `usernamereceiver` varchar(100) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `state` varchar(10) NOT NULL,
  `message` varchar(500) NOT NULL,
  PRIMARY KEY (`username`,`usernamereceiver`,`date`),
  KEY `fk_receiver_idx` (`usernamereceiver`),
  CONSTRAINT `fk_receiver` FOREIGN KEY (`usernamereceiver`) REFERENCES `users` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_3` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='The chat messages sent between users';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES ('aaaa','luirzy','2013-10-13 18:35:36','SENT','ciao come stai?'),('aaaa','pippo','2013-09-29 17:47:01','SENT','hello'),('luirzy','antonio','2013-10-13 18:38:40','SENT','ciao'),('luirzy','pippo','2013-09-29 17:47:01','SENT','ciao come stai?');
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notificaton_offline`
--

DROP TABLE IF EXISTS `notificaton_offline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notificaton_offline` (
  `username` varchar(100) NOT NULL,
  `registrationid` varchar(200) NOT NULL,
  `lastmessage` varchar(1000) DEFAULT NULL,
  `datelastnotificaton` timestamp NULL DEFAULT NULL,
  `state` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`username`),
  CONSTRAINT `fk_notofflineusers` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='The table contains the notifications offline to send to user.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notificaton_offline`
--

LOCK TABLES `notificaton_offline` WRITE;
/*!40000 ALTER TABLE `notificaton_offline` DISABLE KEYS */;
/*!40000 ALTER TABLE `notificaton_offline` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `surname` varchar(100) DEFAULT NULL,
  `sex` char(1) DEFAULT NULL,
  `email` varchar(150) DEFAULT NULL,
  `enddate` char(14) NOT NULL,
  `telephonenumber` varchar(20) DEFAULT NULL,
  `photo` tinyblob,
  `dateofbirth` date DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('aaaa','aaaa',NULL,NULL,NULL,NULL,'99991231000000',NULL,NULL,NULL),('antonio','antonio','Antonio',NULL,'M','aaa@sfsd.it','99991231000000','+390812211234',NULL,NULL),('antonio1','Albano','Antonio',NULL,'M','aaa@sfsd.it','99991231000000','+390812211234',NULL,'2013-10-17'),('antonio2','Albano','Antonio',NULL,'M','aaa@sfsd.it','99991231000000','+390812211234',NULL,'2013-10-17'),('luirzy','luirzy',NULL,NULL,NULL,NULL,'99991231000000',NULL,NULL,NULL),('pippo','pippo',NULL,NULL,NULL,NULL,'99991231000000',NULL,NULL,NULL),('Prova Username','Prova password',NULL,NULL,'M','tom78@kk.com','99991231000000',NULL,NULL,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_friends`
--

DROP TABLE IF EXISTS `users_friends`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_friends` (
  `username` varchar(100) NOT NULL,
  `usernamefriend` varchar(100) NOT NULL,
  PRIMARY KEY (`username`,`usernamefriend`),
  KEY `fk_userfiend_idx` (`usernamefriend`),
  CONSTRAINT `fk_userfiend` FOREIGN KEY (`usernamefriend`) REFERENCES `users` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_users` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_friends`
--

LOCK TABLES `users_friends` WRITE;
/*!40000 ALTER TABLE `users_friends` DISABLE KEYS */;
/*!40000 ALTER TABLE `users_friends` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-10-17 21:15:35

--Modifica 19/10/2013

ALTER TABLE `meetschema`.`notificaton_offline` 
CHANGE COLUMN `datelastnotificaton` `datelastnotification` TIMESTAMP NULL DEFAULT NULL , RENAME TO  `meetschema`.`notification_offline` ;

ALTER TABLE `meetschema`.`notification_offline` 
CHANGE COLUMN `state` `status` VARCHAR(10) NULL DEFAULT NULL ,
ADD COLUMN `devicetype` VARCHAR(20) NOT NULL AFTER `status`;

