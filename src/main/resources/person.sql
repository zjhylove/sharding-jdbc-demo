CREATE TABLE `person` (
  `id` varchar(64) NOT NULL,
  `name` varchar(32) NOT NULL COMMENT '姓名',
  `age` int(3) DEFAULT NULL COMMENT '年龄',
  `gender` int(1) NOT NULL COMMENT '性别 1：男  0：女',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;