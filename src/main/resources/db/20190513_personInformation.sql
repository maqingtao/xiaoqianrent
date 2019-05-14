DROP TABLE IF EXISTS `person_information`;
create table `person_information`(
`userID` char(20),
`userName` char(20),
`nickName` char(20),
`phoneNumber` char(20),
`eMail` char(20),
`iconPath` char(20),
`id`  bigint(20) NOT NULL AUTO_INCREMENT,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
AUTO_INCREMENT=1
DEFAULT CHARSET=utf8;
;