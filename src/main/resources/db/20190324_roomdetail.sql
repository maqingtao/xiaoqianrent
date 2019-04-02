DROP TABLE IF EXISTS `roomdetail`;
create table `roomdetail`(
`roomDimID` char(20),
`roomDimType` char(20),
`spaceDimID` char(20),
`spaceDimType` char(20),
`roomArea` char(20),
`roomStructure` char(20),
`roomPrice` char(20),
`mainID` char(120),
`userID` char(20),
`roomOriented` char(20),
`roomFloor` char(20),
`elevator` char(20),
`check_in_date` char(20),
`signing_time` char(20),
`house_status` char(20),
`facility` char(20),
`describes` char(20),
`id`  bigint(20) NOT NULL AUTO_INCREMENT,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
AUTO_INCREMENT=1
DEFAULT CHARSET=utf8;
;


DROP TABLE IF EXISTS `facility_dic`;
create table `facility_dic`(
`f_id` char(20),
`f_name` char(20),
`id`  bigint(20) NOT NULL AUTO_INCREMENT,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
AUTO_INCREMENT=1
DEFAULT CHARSET=utf8;
;
