/*==============================================================*/
/* Table: SP_SERVICE_PROJECT                                    */
/*==============================================================*/
alter TABLE SP_SERVICE_PROJECT modify SERVICE_FIELD varchar(50) not null comment '服务领域，个数和类别已确认';
alter TABLE SP_SERVICE_PROJECT modify SUPPLY_TYPE varchar(50) not null comment '供应类型，检验监测产品、技术服务';