/*
SQLyog Community v13.1.6 (64 bit)
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
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

/*Data for the table `tb_avatar` */

insert  into `tb_avatar`(`id`,`url`,`name`,`create_time`,`enabled`) values 
(1,'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif','动态小人','2020-12-31 15:01:12',1),
(15,'https://chart7appyyrzbzpa.ingress.ctbiyi.com/gcrs/user-1.jpg','用户头像-女','2020-12-31 14:15:07',1),
(16,'https://chart7appyyrzbzpa.ingress.ctbiyi.com/gcrs/user2.jpg','用户头像男','2020-12-31 14:19:31',1),
(17,'https://chart7appyyrzbzpa.ingress.ctbiyi.com/gcrs/user3.jpg','头像男-简笔','2020-12-31 14:21:11',1),
(18,'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png','ele头像-女','2021-01-31 12:10:00',1),
(19,'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg','ele头像-bird','2021-02-02 10:31:42',1);

/*Table structure for table `tb_care` */

DROP TABLE IF EXISTS `tb_care`;

CREATE TABLE `tb_care` (
  `id` int(11) NOT NULL COMMENT 'user_id',
  `temperature` double DEFAULT NULL COMMENT '温度',
  `height` double DEFAULT NULL COMMENT '身高',
  `weight` double DEFAULT NULL COMMENT '体重',
  `healthy` varchar(128) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_care` */

insert  into `tb_care`(`id`,`temperature`,`height`,`weight`,`healthy`,`create_time`) values 
(33,36.1,17.4,55,'良好','2021-01-30 21:51:40'),
(36,36.1,175,66,'12','2021-01-31 07:49:10');

/*Table structure for table `tb_order` */

DROP TABLE IF EXISTS `tb_order`;

CREATE TABLE `tb_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `care_id` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `order_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `user_demo` varchar(100) DEFAULT NULL,
  `care_demo` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*Data for the table `tb_order` */

insert  into `tb_order`(`id`,`user_id`,`care_id`,`status`,`order_time`,`create_time`,`end_time`,`user_demo`,`care_demo`) values 
(11,32,44,-1,'2021-02-01 16:41:15','2021-02-01 16:41:15',NULL,NULL,NULL),
(12,32,33,0,'2021-02-01 17:33:22','2021-02-01 17:33:27','2021-02-01 23:07:46','尽快谢谢',NULL),
(13,30,44,-1,'2021-02-01 18:15:59','2021-02-01 18:16:00',NULL,NULL,NULL),
(14,30,33,-1,'2021-02-01 20:50:54','2021-02-01 20:51:00',NULL,'备注..00',NULL),
(15,30,33,-1,'2021-02-01 21:35:52','2021-02-01 21:35:52',NULL,NULL,NULL),
(16,30,33,0,'2021-02-06 00:00:00','2021-02-02 22:12:19','2021-02-02 00:00:00','周末来，谢谢~~','和用户沟通，现在服务完毕~'),
(17,30,44,0,'2021-02-05 00:00:00','2021-02-03 12:10:24','2021-02-03 12:12:18',NULL,'OK'),
(18,32,40,-2,'2021-02-03 17:17:59','2021-02-03 17:18:00',NULL,NULL,NULL);

/*Table structure for table `tb_order_rating` */

DROP TABLE IF EXISTS `tb_order_rating`;

CREATE TABLE `tb_order_rating` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) DEFAULT NULL,
  `rating` int(11) DEFAULT NULL,
  `content` varchar(1024) DEFAULT NULL,
  `content_reply` varchar(1024) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `tb_order_rating` */

insert  into `tb_order_rating`(`id`,`order_id`,`rating`,`content`,`content_reply`,`create_time`) values 
(9,12,4,'满意，4分，不给满分怕你骄傲~','欢迎光临哦','2021-02-02 22:06:45'),
(10,16,5,'首次预定就非常好！',NULL,'2021-02-02 22:24:41');

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

insert  into `tb_role`(`id`,`name`,`code`,`enabled`) values 
(1,'普通用户','roleuser','1'),
(2,'护工','rolesend','1'),
(3,'管理员','roleadmin','1');

/*Table structure for table `tb_user` */

DROP TABLE IF EXISTS `tb_user`;

CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `username` varchar(60) DEFAULT NULL,
  `realname` varchar(48) DEFAULT NULL,
  `password` varchar(60) DEFAULT NULL,
  `mobile` varchar(60) DEFAULT NULL,
  `address` varchar(90) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `enabled` int(11) DEFAULT NULL,
  `avatar` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

/*Data for the table `tb_user` */

insert  into `tb_user`(`id`,`role_id`,`username`,`realname`,`password`,`mobile`,`address`,`create_time`,`enabled`,`avatar`) values 
(1,3,'admin','管理员','1','admin','北京市海淀区清华东路35号','2020-12-26 14:09:11',1,'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif'),
(30,1,'xty-lt','用户-xty-liantong','1','13141309261','北京市海淀区清华东路35号','2021-01-30 13:14:42',1,'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif'),
(31,1,NULL,'用户-BB','1','13184064308',NULL,'2021-01-30 13:15:31',1,'https://chart7appyyrzbzpa.ingress.ctbiyi.com/gcrs/user2.jpg'),
(32,1,NULL,'用户-wml','1','17303652621',NULL,'2021-01-30 13:17:29',1,'https://chart7appyyrzbzpa.ingress.ctbiyi.com/gcrs/user-1.jpg'),
(33,2,NULL,'护工-177','1','17700000000',NULL,'2021-01-30 13:18:21',1,'https://chart7appyyrzbzpa.ingress.ctbiyi.com/gcrs/user3.jpg'),
(34,1,NULL,'用户-xty-ct1','1','18910313705',NULL,'2021-01-30 13:19:30',1,'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif'),
(35,2,NULL,'护工-188','1','18800000000',NULL,'2021-01-30 13:20:11',1,'https://chart7appyyrzbzpa.ingress.ctbiyi.com/gcrs/user3.jpg'),
(36,2,NULL,'护工-199','1','19900000000','北京市','2021-01-30 13:20:23',1,'https://chart7appyyrzbzpa.ingress.ctbiyi.com/gcrs/user2.jpg'),
(39,1,'mlmm','用户-mlmm','1','15311586216','北京市红联北村','2021-01-31 06:52:33',1,'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif'),
(40,2,NULL,'护工131','1','13100000000',NULL,'2021-01-31 08:44:26',1,'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif'),
(41,2,NULL,'护工132','1','13200000000',NULL,'2021-01-31 08:44:42',1,'https://chart7appyyrzbzpa.ingress.ctbiyi.com/gcrs/user2.jpg'),
(42,2,NULL,'护工134','1','13400000000',NULL,'2021-01-31 08:44:51',1,'https://chart7appyyrzbzpa.ingress.ctbiyi.com/gcrs/user2.jpg'),
(43,2,NULL,'护工135','1','13500000000',NULL,'2021-01-31 08:45:04',1,'https://chart7appyyrzbzpa.ingress.ctbiyi.com/gcrs/user3.jpg'),
(44,2,NULL,'护工136','1','13600000000',NULL,'2021-01-31 08:45:16',1,'https://chart7appyyrzbzpa.ingress.ctbiyi.com/gcrs/user-1.jpg'),
(45,2,NULL,'护工137','1','13700000000',NULL,'2021-01-31 08:45:24',1,'https://chart7appyyrzbzpa.ingress.ctbiyi.com/gcrs/user-1.jpg'),
(46,2,NULL,'护工138','1','13800000000',NULL,'2021-01-31 08:45:35',1,'https://chart7appyyrzbzpa.ingress.ctbiyi.com/gcrs/user-1.jpg'),
(47,2,NULL,'护工139','1','13900000000',NULL,'2021-01-31 08:45:42',1,'https://chart7appyyrzbzpa.ingress.ctbiyi.com/gcrs/user-1.jpg'),
(48,2,NULL,'护工151','1','15100000000',NULL,'2021-01-31 08:45:53',1,'https://chart7appyyrzbzpa.ingress.ctbiyi.com/gcrs/user-1.jpg'),
(49,2,NULL,'护工152','1','15200000000',NULL,'2021-01-31 08:46:01',1,'https://chart7appyyrzbzpa.ingress.ctbiyi.com/gcrs/user2.jpg');

/*Table structure for table `tb_work` */

DROP TABLE IF EXISTS `tb_work`;

CREATE TABLE `tb_work` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(32) DEFAULT NULL,
  `content` varchar(256) DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `tb_work` */

insert  into `tb_work`(`id`,`title`,`content`,`order_id`,`user_id`,`create_time`,`update_time`) values 
(1,'今天工作很开森~ 1','今天工作很开森~ o_o~~~~ 1',12,33,'2021-02-03 20:57:40','2021-02-03 21:26:37');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
