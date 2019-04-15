DROP TABLE IF EXISTS `province_dic`;
create table `province_dic`(
`provinceId` char(20),
`provinceName` char(20),
`id`  bigint(20) NOT NULL AUTO_INCREMENT,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
AUTO_INCREMENT=1
DEFAULT CHARSET=utf8;
;



DROP TABLE IF EXISTS `mycollect`;
create table `mycollect`(
`userID` char(20),
`mainID` char(20),
`id`  bigint(20) NOT NULL AUTO_INCREMENT,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
AUTO_INCREMENT=1
DEFAULT CHARSET=utf8;
;