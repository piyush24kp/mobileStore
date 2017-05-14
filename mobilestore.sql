/*
SQLyog - Free MySQL GUI v5.17
Host - 5.0.24-community-nt : Database - mobilestore
*********************************************************************
Server version : 5.0.24-community-nt
*/

SET NAMES utf8;

SET SQL_MODE='';

create database if not exists `mobilestore`;

USE `mobilestore`;

SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO';

/*Table structure for table `brands` */

DROP TABLE IF EXISTS `brands`;

CREATE TABLE `brands` (
  `brandId` int(10) NOT NULL auto_increment,
  `brandName` varchar(20) default NULL,
  PRIMARY KEY  (`brandId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `brands` */

insert into `brands` (`brandId`,`brandName`) values (1,'Apple'),(2,'MI'),(3,'Samsung'),(4,'Sony'),(5,'Oppo'),(6,'Vivo'),(7,'Lenovo'),(8,'Intex'),(9,'Moto'),(10,'LYF');

/*Table structure for table `models` */

DROP TABLE IF EXISTS `models`;

CREATE TABLE `models` (
  `modelId` int(10) NOT NULL auto_increment,
  `modelName` varchar(20) default NULL,
  `brandId` int(10) default NULL,
  PRIMARY KEY  (`modelId`),
  KEY `FK_models` (`brandId`),
  CONSTRAINT `models_ibfk_1` FOREIGN KEY (`brandId`) REFERENCES `brands` (`brandId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `models` */

insert into `models` (`modelId`,`modelName`,`brandId`) values (1,'Iphone 6 32Gb',1),(2,'Iphone 6s 32Gb',1);

/*Table structure for table `orderdetails` */

DROP TABLE IF EXISTS `orderdetails`;

CREATE TABLE `orderdetails` (
  `uId` int(20) NOT NULL auto_increment,
  `orderId` int(20) NOT NULL,
  `orderName` varchar(40) default NULL,
  `amount` int(5) default NULL,
  `quantity` int(3) default NULL,
  `category` varchar(10) default NULL,
  `purchasePrice` int(11) default NULL,
  `sellPrice` int(11) default NULL,
  `sellBy` varchar(10) default NULL,
  `brand` varchar(10) default NULL,
  `suppliedBy` int(10) default NULL,
  PRIMARY KEY  (`uId`),
  KEY `suppliedBy` (`suppliedBy`),
  CONSTRAINT `orderdetails_ibfk_1` FOREIGN KEY (`suppliedBy`) REFERENCES `supplier` (`supplierId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `orderdetails` */

insert into `orderdetails` (`uId`,`orderId`,`orderName`,`amount`,`quantity`,`category`,`purchasePrice`,`sellPrice`,`sellBy`,`brand`,`suppliedBy`) values (1,10000,'Iphone 6s',NULL,20,'M',380000,36000,'APEX','Apple',2),(2,10001,'Iphone 6',NULL,20,'M',30000,26000,'E','Apple',1),(6,10002,'Iphone 6s',NULL,40,'M',400000,36000,'E','Apple',1),(8,1494776760,'test',NULL,12,'test',120,123,NULL,'123',2);

/*Table structure for table `supplier` */

DROP TABLE IF EXISTS `supplier`;

CREATE TABLE `supplier` (
  `supplierId` int(10) NOT NULL auto_increment,
  `supplierName` varchar(20) default NULL,
  `contactNo` int(10) default NULL,
  `address` varchar(30) default NULL,
  `amountDue` varchar(10) default NULL,
  `amountPaid` varchar(10) default NULL,
  PRIMARY KEY  (`supplierId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `supplier` */

insert into `supplier` (`supplierId`,`supplierName`,`contactNo`,`address`,`amountDue`,`amountPaid`) values (1,'test',123456789,'1233456','1000','4000'),(2,'dj',123456789,'123123','500','2500'),(3,'AS',23,'123','123123',NULL);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
