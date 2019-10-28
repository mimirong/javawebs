/*==============================================================*/
/* Table: sp_specification                                    */
/*==============================================================*/
alter TABLE sp_specification modify PRICE_UNIT varchar(50) DEFAULT NULL COMMENT '价格单位，元、万元、百万元';
