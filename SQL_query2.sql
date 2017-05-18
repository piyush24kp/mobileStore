-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: mobilestore
-- ------------------------------------------------------
-- Server version	5.7.9-log

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
-- Table structure for table `brands`
--

DROP TABLE IF EXISTS `brands`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `brands` (
  `brandId` int(10) NOT NULL AUTO_INCREMENT,
  `brandName` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`brandId`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brands`
--

LOCK TABLES `brands` WRITE;
/*!40000 ALTER TABLE `brands` DISABLE KEYS */;
INSERT INTO `brands` VALUES (1,'Apple'),(2,'MI'),(3,'Samsung'),(4,'Sony'),(5,'Oppo'),(6,'Vivo'),(7,'Lenovo'),(8,'Intex'),(9,'Moto'),(10,'LYF'),(12,'Test');
/*!40000 ALTER TABLE `brands` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `categoryId` int(11) NOT NULL AUTO_INCREMENT,
  `categoryName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`categoryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `models`
--

DROP TABLE IF EXISTS `models`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `models` (
  `modelId` int(10) NOT NULL AUTO_INCREMENT,
  `modelName` varchar(20) DEFAULT NULL,
  `brandId` int(10) DEFAULT NULL,
  `storage` varchar(45) DEFAULT NULL,
  `price` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`modelId`),
  KEY `FK_models` (`brandId`),
  CONSTRAINT `models_ibfk_1` FOREIGN KEY (`brandId`) REFERENCES `brands` (`brandId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `models`
--

LOCK TABLES `models` WRITE;
/*!40000 ALTER TABLE `models` DISABLE KEYS */;
INSERT INTO `models` VALUES (1,'Iphone 6',1,' 32Gb','20000'),(2,'Iphone 6s',1,' 32Gb','30000'),(3,'S8',3,'64GB','57000'),(4,'Iphone 5s',1,'16Gb','18000'),(5,'Iphone 4s',1,'16Gb','18000'),(7,'S6',3,'32GB','3200');
/*!40000 ALTER TABLE `models` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderdetails`
--

DROP TABLE IF EXISTS `orderdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderdetails` (
  `uId` int(20) NOT NULL AUTO_INCREMENT,
  `orderId` int(20) NOT NULL,
  `orderName` varchar(40) DEFAULT NULL,
  `amount` int(5) DEFAULT NULL,
  `quantity` int(3) DEFAULT '0',
  `category` varchar(10) DEFAULT NULL,
  `purchasePrice` int(11) DEFAULT NULL,
  `sellPrice` int(11) DEFAULT NULL,
  `sellBy` varchar(10) DEFAULT NULL,
  `brand` varchar(10) DEFAULT NULL,
  `suppliedBy` int(10) DEFAULT NULL,
  `orderDate` datetime DEFAULT NULL,
  `model` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`uId`),
  KEY `suppliedBy` (`suppliedBy`),
  CONSTRAINT `orderdetails_ibfk_1` FOREIGN KEY (`suppliedBy`) REFERENCES `supplier` (`supplierId`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderdetails`
--

LOCK TABLES `orderdetails` WRITE;
/*!40000 ALTER TABLE `orderdetails` DISABLE KEYS */;
INSERT INTO `orderdetails` VALUES (1,10000,'Iphone 6s',1000,5,'M',380000,36000,'APEX','1',1,'2017-05-16 00:00:00','1'),(17,1494916812,'Testing24',1000,1,'32Gb',24000,3000,NULL,'3',1,'2017-05-16 00:00:00','3'),(24,1494919807,'Testing24',1000,2,'32Gb',24000,3000,NULL,'1',1,'2017-05-16 00:00:00','2');
/*!40000 ALTER TABLE `orderdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `selldetail`
--

DROP TABLE IF EXISTS `selldetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `selldetail` (
  `orderId` bigint(10) NOT NULL AUTO_INCREMENT,
  `invoiceNo` varchar(45) DEFAULT NULL,
  `customerName` varchar(45) DEFAULT NULL,
  `imeiNo` bigint(10) NOT NULL,
  `brand` varchar(10) DEFAULT NULL,
  `model` varchar(10) DEFAULT NULL,
  `saleType` varchar(10) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `sellDate` date DEFAULT NULL,
  `amount` bigint(10) DEFAULT NULL,
  `contantNo` bigint(10) DEFAULT NULL,
  `vendor` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`orderId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `selldetail`
--

LOCK TABLES `selldetail` WRITE;
/*!40000 ALTER TABLE `selldetail` DISABLE KEYS */;
INSERT INTO `selldetail` VALUES (1,'100','Piyush',123456789123456,'1','2','offline','141 indore','2017-05-17',32000,7415786624,NULL),(2,'101','DJ',1111111111,'1','1','ONLINE','141 indore','2017-05-17',32000,123345,'Amazon'),(4,'124','Piyush',12345678912,'1','2','offline','141 indore','2017-05-16',32000,1111111111,NULL),(5,'111','test 123',12345678912,'1','2','offline','141 indore','2017-05-16',32000,1111,NULL),(7,'STR-1495094054','test 123',12345678912,'1','1','offline','141 indore','2017-05-16',32000,NULL,NULL),(8,'Ama-1495094080','test 123',12345678912,'1','1','online','141 indore','2017-05-16',32000,NULL,NULL);
/*!40000 ALTER TABLE `selldetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supplier` (
  `supplierId` int(10) NOT NULL AUTO_INCREMENT,
  `supplierName` varchar(20) DEFAULT NULL,
  `contactNo` int(10) DEFAULT NULL,
  `address` varchar(30) DEFAULT NULL,
  `amountDue` varchar(10) DEFAULT NULL,
  `amountPaid` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`supplierId`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (1,'test',123456789,'1233456','1000','4000'),(2,'dj',123456789,'123123','500','2500'),(3,'AS',23,'123','123123','0'),(13,'test',123456,'indore','1000','4000');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userdetails`
--

DROP TABLE IF EXISTS `userdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userdetails` (
  `uId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `userName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`uId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userdetails`
--

LOCK TABLES `userdetails` WRITE;
/*!40000 ALTER TABLE `userdetails` DISABLE KEYS */;
INSERT INTO `userdetails` VALUES (1,'root','12345','Piyush Patil');
/*!40000 ALTER TABLE `userdetails` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-18 19:51:27
