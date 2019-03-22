
DROP TABLE IF EXISTS `room`;
create table `room`(
`roomDimID` char(20),
`roomDimType` char(20),
`spaceDimID` char(20),
`spaceDimType` char(20),
`roomArea` char(20),
`roomStructure` char(20),
`roomPrice` char(20),
`mainID` char(120)
`id`  bigint(20) NOT NULL AUTO_INCREMENT,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
AUTO_INCREMENT=1
DEFAULT CHARSET=utf8;
;