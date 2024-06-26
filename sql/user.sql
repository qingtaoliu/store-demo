DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `uid` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `password` char(32) NOT NULL COMMENT '密码',
  `salt` char(36) DEFAULT NULL COMMENT '盐值',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话号码',
  `email` varchar(30) DEFAULT NULL COMMENT '电子邮箱',
  `gender` int DEFAULT NULL COMMENT '性别:0-女，1-男',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像',
  `is_delete` int DEFAULT NULL COMMENT '是否删除：0-未删除，1-已删除',
  `created_user` varchar(20) DEFAULT NULL COMMENT '日志-创建人',
  `created_time` datetime DEFAULT NULL COMMENT '日志-创建时间',
  `modified_user` varchar(20) DEFAULT NULL COMMENT '日志-最后修改执行人',
  `modified_time` datetime DEFAULT NULL COMMENT '日志-最后修改时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb3;
