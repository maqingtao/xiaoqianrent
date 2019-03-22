DROP TABLE IF EXISTS `city_dic`;
create table `city_dic`(
`cityId` char(20),
`cityName` char(20),
`id`  bigint(20) NOT NULL AUTO_INCREMENT,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
AUTO_INCREMENT=1
DEFAULT CHARSET=utf8;
;