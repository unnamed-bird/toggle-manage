
DROP TABLE IF EXISTS `sw_switch_rules`;
CREATE TABLE `sw_switch_rules` (
    `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `app_id` int(10) unsigned NOT NULL COMMENT '应用主键',
    `switch_id` int(10) unsigned NOT NULL  COMMENT '开关主键',
    `rule` varchar(128) NOT NULL COMMENT '开关规则',
    `sort` int(10) NOT NULL DEFAULT 0 COMMENT '开关排序' ,
    `status` tinyint(4) NOT NULL DEFAULT  '1' COMMENT '-1: deleted, 1: normal',
    `createtime` timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updatetime` timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`Id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='开关规则表';

INSERT INTO `sw_switch_rules` (`id`,`app_id`,`switch_id`,`rule`,`sort`,`status`,`createtime`,`updatetime`) VALUES (1,2,2,'TRUE',0,1,'2020-04-19 14:55:11','2020-04-20 14:55:11');
INSERT INTO `sw_switch_rules` (`id`,`app_id`,`switch_id`,`rule`,`sort`,`status`,`createtime`,`updatetime`) VALUES (3,1,1,'TRUE',0,1,'2020-01-19 18:45:48','2020-01-01 18:45:48');
INSERT INTO `sw_switch_rules` (`id`,`app_id`,`switch_id`,`rule`,`sort`,`status`,`createtime`,`updatetime`) VALUES (5,3,3,'a/b/b',11,1,'2020-04-25 17:47:45','2020-04-25 17:47:45');
INSERT INTO `sw_switch_rules` (`id`,`app_id`,`switch_id`,`rule`,`sort`,`status`,`createtime`,`updatetime`) VALUES (7,2,4,'aaa',0,1,'2020-04-25 17:59:16','2020-04-25 17:59:16');
INSERT INTO `sw_switch_rules` (`id`,`app_id`,`switch_id`,`rule`,`sort`,`status`,`createtime`,`updatetime`) VALUES (8,2,4,'bbb',0,1,'2020-04-25 17:59:16','2020-04-25 17:59:16');
INSERT INTO `sw_switch_rules` (`id`,`app_id`,`switch_id`,`rule`,`sort`,`status`,`createtime`,`updatetime`) VALUES (9,3,3,'a/b/c',8,1,'2020-01-01 00:00:00','2020-01-01 00:00:00');
INSERT INTO `sw_switch_rules` (`id`,`app_id`,`switch_id`,`rule`,`sort`,`status`,`createtime`,`updatetime`) VALUES (29,4,3,'2wd',4,1,'2020-03-03 00:00:00','2020-03-03 00:00:00');