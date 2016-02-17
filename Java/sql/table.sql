/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.10-log : Database - vacancies
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`vacancies` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `vacancies`;

/*Table structure for table `candidates` */

DROP TABLE IF EXISTS `candidates`;

CREATE TABLE `candidates` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `patronymic` varchar(45) DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `candidates` */

insert  into `candidates`(`id`,`firstname`,`lastname`,`patronymic`,`email`,`login`,`password`) values (1,'РљРѕРЅСЃС‚Р°РЅС‚РёРЅ','РљР°СЋРєРёРЅ','РђР»РµРєСЃР°РЅРґСЂРѕРІРёС‡','kayukin@gmail.com','kayukin','qwerty');

/*Table structure for table `employers` */

DROP TABLE IF EXISTS `employers`;

CREATE TABLE `employers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `company` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  UNIQUE KEY `company_UNIQUE` (`company`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

/*Data for the table `employers` */

insert  into `employers`(`id`,`company`,`address`,`firstname`,`lastname`,`email`,`login`,`password`) values (1,'РўР°РјС‚СЌРє','Р–СѓРєРѕРІР°','РРІР°РЅ','РРІР°РЅРѕРІ','job@thumbtack.net','thumbtack','qwerty'),(20,'TEST','Р–СѓРєРѕРІР°','РРІР°РЅ','РРІР°РЅРѕРІ','job@thumbtack.net','newuser','qwerty');

/*Table structure for table `offers` */

DROP TABLE IF EXISTS `offers`;

CREATE TABLE `offers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `salary` int(11) DEFAULT NULL,
  `id_employer` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_employer` (`id_employer`),
  CONSTRAINT `offers_ibfk_1` FOREIGN KEY (`id_employer`) REFERENCES `employers` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `offers` */

insert  into `offers`(`id`,`name`,`salary`,`id_employer`) values (1,'Java-СЂР°Р·СЂР°Р±РѕС‚С‡РёРє',30000,1);

/*Table structure for table `requirements` */

DROP TABLE IF EXISTS `requirements`;

CREATE TABLE `requirements` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `skill_id` int(11) NOT NULL,
  `level` enum('1','2','3','4','5') NOT NULL,
  `isnecessary` tinyint(1) NOT NULL DEFAULT '1',
  `id_offer` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `skill_id` (`skill_id`),
  KEY `id_offer` (`id_offer`),
  CONSTRAINT `requirements_ibfk_2` FOREIGN KEY (`skill_id`) REFERENCES `skill_list` (`id`) ON DELETE CASCADE,
  CONSTRAINT `requirements_ibfk_3` FOREIGN KEY (`id_offer`) REFERENCES `offers` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `requirements` */

insert  into `requirements`(`id`,`skill_id`,`level`,`isnecessary`,`id_offer`) values (3,1,'4',1,1);

/*Table structure for table `skill_list` */

DROP TABLE IF EXISTS `skill_list`;

CREATE TABLE `skill_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `skill_list` */

insert  into `skill_list`(`id`,`name`) values (2,'C#'),(1,'Java'),(3,'JavaScript'),(4,'SQL');

/*Table structure for table `skills` */

DROP TABLE IF EXISTS `skills`;

CREATE TABLE `skills` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_skill` int(11) NOT NULL,
  `level` enum('1','2','3','4','5') NOT NULL,
  `id_candidate` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_candidate` (`id_candidate`),
  KEY `id_skill` (`id_skill`),
  CONSTRAINT `skills_ibfk_1` FOREIGN KEY (`id_candidate`) REFERENCES `candidates` (`id`) ON DELETE CASCADE,
  CONSTRAINT `skills_ibfk_2` FOREIGN KEY (`id_skill`) REFERENCES `skill_list` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `skills` */

insert  into `skills`(`id`,`id_skill`,`level`,`id_candidate`) values (1,1,'4',1),(2,2,'3',1),(3,3,'5',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
