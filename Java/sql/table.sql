/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.10-log : Database - vacancies
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE = '' */;

/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS */`vacancies` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `vacancies`;

/*Table structure for table `candidates` */

DROP TABLE IF EXISTS `candidates`;

CREATE TABLE `candidates` (
  `id`         INT(11)     NOT NULL AUTO_INCREMENT,
  `firstname`  VARCHAR(45) NOT NULL,
  `lastname`   VARCHAR(45) NOT NULL,
  `patronymic` VARCHAR(45)          DEFAULT NULL,
  `email`      VARCHAR(45) NOT NULL,
  `login`      VARCHAR(45) NOT NULL,
  `password`   VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  UNIQUE KEY `id_UNIQUE` (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8;

/*Data for the table `candidates` */

INSERT INTO `candidates` (`id`, `firstname`, `lastname`, `patronymic`, `email`, `login`, `password`)
VALUES (1, 'Константин', 'Каюкин', 'Александрович', 'kayukin@gmail.com', 'kayukin', 'qwerty'),
  (2, 'Петр', 'Петров', 'Петрович', 'petr@mail.ru', 'petrov', '123123');

/*Table structure for table `employers` */

DROP TABLE IF EXISTS `employers`;

CREATE TABLE `employers` (
  `id`        INT(11)     NOT NULL AUTO_INCREMENT,
  `company`   VARCHAR(45) NOT NULL,
  `address`   VARCHAR(45) NOT NULL,
  `firstname` VARCHAR(45) NOT NULL,
  `lastname`  VARCHAR(45) NOT NULL,
  `email`     VARCHAR(45) NOT NULL,
  `login`     VARCHAR(45) NOT NULL,
  `password`  VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  UNIQUE KEY `company_UNIQUE` (`company`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8;

/*Data for the table `employers` */

INSERT INTO `employers` (`id`, `company`, `address`, `firstname`, `lastname`, `email`, `login`, `password`)
VALUES (1, 'Тамтэк', 'Жукова', 'Иван', 'Иванов', 'job@thumbtack.net', 'thumbtack', 'qwerty'),
  (2, 'Газпром', '10 лет октября', 'Александр', 'Иванов', 'work@gazprom.ru', 'gazprom', '123123');

/*Table structure for table `offers` */

DROP TABLE IF EXISTS `offers`;

CREATE TABLE `offers` (
  `id`          INT(11)     NOT NULL AUTO_INCREMENT,
  `name`        VARCHAR(45) NOT NULL,
  `salary`      INT(11)              DEFAULT NULL,
  `id_employer` INT(11)     NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_employer` (`id_employer`),
  CONSTRAINT `offers_ibfk_1` FOREIGN KEY (`id_employer`) REFERENCES `employers` (`id`)
    ON DELETE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8;

/*Data for the table `offers` */

INSERT INTO `offers` (`id`, `name`, `salary`, `id_employer`) VALUES (1, 'Java-разработчик', 30000, 1);

/*Table structure for table `requirements` */

DROP TABLE IF EXISTS `requirements`;

CREATE TABLE `requirements` (
  `id`          INT(11)                       NOT NULL AUTO_INCREMENT,
  `skill_id`    INT(11)                       NOT NULL,
  `level`       ENUM('1', '2', '3', '4', '5') NOT NULL,
  `isnecessary` TINYINT(1)                    NOT NULL DEFAULT '1',
  `id_offer`    INT(11)                       NOT NULL,
  PRIMARY KEY (`id`),
  KEY `skill_id` (`skill_id`),
  KEY `id_offer` (`id_offer`),
  CONSTRAINT `requirements_ibfk_2` FOREIGN KEY (`skill_id`) REFERENCES `skill_list` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `requirements_ibfk_3` FOREIGN KEY (`id_offer`) REFERENCES `offers` (`id`)
    ON DELETE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8;

/*Data for the table `requirements` */

INSERT INTO `requirements` (`id`, `skill_id`, `level`, `isnecessary`, `id_offer`) VALUES (3, 1, '4', 1, 1);

/*Table structure for table `skill_list` */

DROP TABLE IF EXISTS `skill_list`;

CREATE TABLE `skill_list` (
  `id`   INT(11)     NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8;

/*Data for the table `skill_list` */

INSERT INTO `skill_list` (`id`, `name`) VALUES (2, 'C#'), (1, 'Java'), (3, 'JavaScript'), (4, 'SQL');

/*Table structure for table `skills` */

DROP TABLE IF EXISTS `skills`;

CREATE TABLE `skills` (
  `id`           INT(11)                       NOT NULL AUTO_INCREMENT,
  `id_skill`     INT(11)                       NOT NULL,
  `level`        ENUM('1', '2', '3', '4', '5') NOT NULL,
  `id_candidate` INT(11)                       NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_candidate` (`id_candidate`),
  KEY `id_skill` (`id_skill`),
  CONSTRAINT `skills_ibfk_1` FOREIGN KEY (`id_candidate`) REFERENCES `candidates` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `skills_ibfk_2` FOREIGN KEY (`id_skill`) REFERENCES `skill_list` (`id`)
    ON DELETE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8;

/*Data for the table `skills` */

INSERT INTO `skills` (`id`, `id_skill`, `level`, `id_candidate`) VALUES (1, 1, '4', 1), (2, 2, '3', 1), (3, 3, '5', 1);

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;
