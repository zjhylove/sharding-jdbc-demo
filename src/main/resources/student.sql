CREATE TABLE `student` (
  `id` varchar(64) NOT NULL,
  `name` varchar(32) NOT NULL COMMENT '姓名',
  `age` int(3) NOT NULL COMMENT '年龄',
  `gender` int(1) NOT NULL COMMENT '性别 1：男  0：女',
  `school_name` varchar(32) NOT NULL COMMENT '学校名称',
  `class_name` varchar(32) NOT NULL COMMENT '班级名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;