DROP TABLE IF EXISTS `imagepath`;
create table `imagepath`(
`mainID` char(20),
`imagePath` char(20),
`id`  bigint(20) NOT NULL AUTO_INCREMENT,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
AUTO_INCREMENT=1
DEFAULT CHARSET=utf8;
;