CREATE TABLE `sys_user` (
  `id`  int(11) NOT NULL AUTO_INCREMENT COMMENT '用户编号' ,
  `user_name`  varchar(100) NULL COMMENT '用户名称' ,
  `password`  varchar(100) NULL COMMENT '密码' ,
  `create_time`  timestamp NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间' ,
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

CREATE TABLE `sys_role` (
  `id`  int(11) NOT NULL AUTO_INCREMENT ,
  `name`  varchar(100) NULL COMMENT '名称' ,
  `desc`  varchar(100) NULL COMMENT '描述' ,
  `create_time`  timestamp NULL ON UPDATE CURRENT_TIMESTAMP ,
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

CREATE TABLE `sys_resource` (
  `id`  int(11) NOT NULL ,
  `name`  varchar(100) NULL COMMENT '资源名称' ,
  `value`  varchar(100) NULL COMMENT '资源编码' ,
  `type`  varchar(3) NULL DEFAULT '' COMMENT '0-菜单页面；1-操作权限' ,
  `parent_id`  int(11) NULL COMMENT '父编号' ,
  `create_time`  timestamp NULL ON UPDATE CURRENT_TIMESTAMP ,
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='资源表';

CREATE TABLE `sys_role_resource` (
  `id`  int(11) NOT NULL AUTO_INCREMENT ,
  `role_id`  int(11) NULL COMMENT '角色ID' ,
  `resource_id`  int(11) NULL COMMENT '资源ID' ,
  `create_time`  timestamp NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间' ,
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色资源表';

CREATE TABLE `sys_user_role` (
  `id`  int(11) NOT NULL AUTO_INCREMENT ,
  `user_id`  int(11) NULL COMMENT '用户ID' ,
  `role_id`  int(11) NULL COMMENT '角色ID' ,
  `create_time`  timestamp NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间' ,
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色表';
