/*==============================================================*/
/* Table: sp_specification                                    */
/*==============================================================*/
alter TABLE sp_specification change REFER_PRICE   REFER_PRICE          float comment '规格单价';
alter TABLE sp_specification change MEASURE_UNIT  MEASURE_UNIT         varchar(10) comment '计量单位';
alter TABLE sp_specification change PRICE_UNIT    PRICE_UNIT           varchar(5) comment '价格单位，元、万元、百万元';
