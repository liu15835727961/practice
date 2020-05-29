--创建测试表
CREATE TABLE tb_user(
	id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
	user_name VARCHAR(20) NOT NULL COMMENT '用户名',
	PASSWORD VARCHAR(20) NOT NULL COMMENT '密码',
	NAME VARCHAR(30) DEFAULT NULL COMMENT '姓名',
	age INT(11) DEFAULT NULL COMMENT '年龄',
	email VARCHAR(50) DEFAULT NULL COMMENT '邮箱',
	PRIMARY KEY (id)
 )ENGINE=INNODB AUTO_INCREMENT =1 DEFAULT CHARSET=utf8

--插入数据测试
INSERT INTO tb_user (user_name,PASSWORD,NAME,age,email) VALUES ('zhangsan','123456','张三',18,'test1@email.cm');
INSERT INTO tb_user (user_name,PASSWORD,NAME,age,email) VALUES ('lisi','123456','李四',20,'test2@email.cm');
INSERT INTO tb_user (user_name,PASSWORD,NAME,age,email) VALUES ('wangwu','123456','王五',28,'test3@email.cm');
INSERT INTO tb_user (user_name,PASSWORD,NAME,age,email) VALUES ('zhaoliu','123456','赵六',21,'test4@email.cm');