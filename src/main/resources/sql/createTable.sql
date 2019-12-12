-- 建立简单的user表
create table user (
	id int(8) NOT NULL AUTO_INCREMENT COMMENT '自增id',
	age int(3) ,
	password VARCHAR(12),
	sex int(1),
	username VARCHAR(12),
	PRIMARY KEY (`id`)

)