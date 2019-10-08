DROP TABLE IF EXISTS `HOTEL_FACILITY`;
CREATE TABLE HOTEL_FACILITY (
	`ID` INT NULL COMMENT '主键',
	`HOTEL_ID` VARCHAR(100) NOT NULL COMMENT '酒店编号',
	`HOTEL_FACILITY_MODULE_ID` TEXT NULL COMMENT '酒店服务编号',
	`DELETE_FLAG` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0',
  `CREATE_USER` int(255) NULL DEFAULT NULL COMMENT '创建用户',
  `UPDATE_USER` int(11) NULL DEFAULT NULL COMMENT '更新用户',
  `CREATE_TIME` timestamp(0) NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  `UPDATE_TIME` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci;