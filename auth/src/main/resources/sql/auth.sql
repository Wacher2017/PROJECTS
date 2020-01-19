CREATE TABLE IF NOT EXISTS `sys_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nameZh` varchar(11) NOT NULL DEFAULT '' COMMENT '角色名称：管理员；用户',
  `name` varchar(11) DEFAULT NULL COMMENT '角色：ROLE_ADMIN;ROLE_USER',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` (`id`, `nameZh`, `name`) VALUES
  (1, '用户', 'ROLE_USER'),
  (2, '管理员', 'ROLE_ADMIN');

CREATE TABLE IF NOT EXISTS `sys_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(11) NOT NULL DEFAULT '',
  `nickname` varchar(11) NOT NULL DEFAULT '',
  `password` varchar(64) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `sys_user_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;