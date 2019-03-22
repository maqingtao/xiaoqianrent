
DROP TABLE IF EXISTS `user`;
create table `user`(
`username` char(20),
`password` char(20),
`id`  bigint(20) NOT NULL AUTO_INCREMENT,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
AUTO_INCREMENT=1
DEFAULT CHARSET=utf8;
;
