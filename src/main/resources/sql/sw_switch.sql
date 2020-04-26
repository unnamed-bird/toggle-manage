
DROP TABLE IF EXISTS `sw_switch`;
CREATE TABLE `sw_switch` (
    `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `app_id` int(10) unsigned NOT NULL COMMENT '应用主键',
    `name` varchar(500) NOT NULL  COMMENT '开关名',
    `name_key` varchar(128) NOT NULL COMMENT '开关唯一键，在应用的namespace 唯一',
    `name_desc` varchar(500) NOT NULL DEFAULT ''  COMMENT '开关描述',
    `owner_email` varchar(500) NOT NULL DEFAULT '' COMMENT '负责人邮箱地址',
    `status` tinyint(4) NOT NULL DEFAULT  '1' COMMENT '-1: deleted, 1: normal',
    `type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1:普通开关（true、false） ，2: 正则匹配 ，3：灰度类型，4：百分比类型',
    `createtime` timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updatetime` timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`Id`),
    KEY `IX_Name` (`name_key`(64))
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='开关表';

/*INSERT INTO `sw_switch` VALUES(1,2,'switch1', 'key_1' , '开关一', '1044121990@qq.com',-1,1, '2020-4-11','2020-4-11');
INSERT INTO `sw_switch` VALUES(2,2,'switch2', 'key_2' , '开关二', '321'           , 1,4, '2020-4-11','2020-4-11');
*/

INSERT INTO `sw_switch` (`id`,`app_id`,`name`,`name_key`,`name_desc`,`owner_email`,`status`,`type`,`createtime`,`updatetime`) VALUES (1,2,'switch1','key_1','开关一','1044121991@qq.com',-1,1,'2020-04-11 00:00:00','2020-04-11 00:00:00');
INSERT INTO `sw_switch` (`id`,`app_id`,`name`,`name_key`,`name_desc`,`owner_email`,`status`,`type`,`createtime`,`updatetime`) VALUES (2,2,'switch2','key_2','开关二','321456',1,1,'2020-04-11 00:00:00','2020-04-11 00:00:00');
INSERT INTO `sw_switch` (`id`,`app_id`,`name`,`name_key`,`name_desc`,`owner_email`,`status`,`type`,`createtime`,`updatetime`) VALUES (3,4,'aaa','321','232dedw','1',1,2,'2020-01-01 00:00:00','2020-01-01 00:00:00');
INSERT INTO `sw_switch` (`id`,`app_id`,`name`,`name_key`,`name_desc`,`owner_email`,`status`,`type`,`createtime`,`updatetime`) VALUES (4,2,'bbb','321','1111','111',1,3,'2020-04-25 17:57:43','2020-04-25 17:57:43');