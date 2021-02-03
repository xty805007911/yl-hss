/*
SQLyog Professional v12.08 (32 bit)
MySQL - 5.5.49 : Database - yl_hss_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`yl_hss_db` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `yl_hss_db`;

/*Table structure for table `tb_avatar` */

DROP TABLE IF EXISTS `tb_avatar`;

CREATE TABLE `tb_avatar` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(256) DEFAULT NULL,
  `name` varchar(16) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `enabled` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Data for the table `tb_avatar` */

insert  into `tb_avatar`(`id`,`url`,`name`,`create_time`,`enabled`) values (1,'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif','动态小人','2020-12-31 15:01:12',1),(15,'https://chart7appyyrzbzpa.ingress.ctbiyi.com/gcrs/user-1.jpg','用户头像-女','2020-12-31 14:15:07',1),(16,'https://chart7appyyrzbzpa.ingress.ctbiyi.com/gcrs/user2.jpg','用户头像男','2020-12-31 14:19:31',1),(17,'https://chart7appyyrzbzpa.ingress.ctbiyi.com/gcrs/user3.jpg','头像男-简笔','2020-12-31 14:21:11',1);

/*Table structure for table `tb_role` */

DROP TABLE IF EXISTS `tb_role`;

CREATE TABLE `tb_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(16) DEFAULT NULL,
  `code` varchar(16) DEFAULT NULL,
  `enabled` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `tb_role` */

insert  into `tb_role`(`id`,`name`,`code`,`enabled`) values (1,'普通用户','roleuser','1'),(2,'护工','rolesend','1'),(3,'管理员','roleadmin','1');

/*Table structure for table `tb_user` */

DROP TABLE IF EXISTS `tb_user`;

CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(60) DEFAULT NULL,
  `realname` varchar(48) DEFAULT NULL,
  `password` varchar(60) DEFAULT NULL,
  `mobile` varchar(60) DEFAULT NULL,
  `address` varchar(90) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `enabled` int(11) DEFAULT NULL,
  `avatar` varchar(300) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

/*Data for the table `tb_user` */

insert  into `tb_user`(`id`,`username`,`realname`,`password`,`mobile`,`address`,`create_time`,`enabled`,`avatar`,`role_id`) values (1,'admin','管理员','1','admin','111','2020-12-26 14:09:11',1,'https://chart7appyyrzbzpa.ingress.ctbiyi.com/gcrs/user3.jpg',1),(3,'','王ML','1','17303652621','','2020-12-31 14:22:03',1,'https://chart7appyyrzbzpa.ingress.ctbiyi.com/gcrs/user-1.jpg',1),(4,'','ct-xty','1','18910313705','西城区','2021-01-01 01:12:52',1,'https://chart7appyyrzbzpa.ingress.ctbiyi.com/gcrs/user3.jpg',1),(23,'','bb','1','13184064308','4','2021-01-30 11:55:58',1,'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',1),(24,NULL,'xty-liantong','1','13141309261','bjfu','2021-01-30 11:57:38',1,'https://chart7appyyrzbzpa.ingress.ctbiyi.com/gcrs/user3.jpg',2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
