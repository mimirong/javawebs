/*==============================================================*/
/* Table: SP_SERVICE_PROJECT                                    */
/*==============================================================*/
alter TABLE SP_SERVICE_PROJECT add  PUBLISHER_USER_ID    int not null comment '发布者ID';
alter TABLE SP_SERVICE_PROJECT add  PUBLISHER_NAME       varchar(40) not null comment '发布者名称';
alter TABLE SP_SERVICE_PROJECT add  COMPANY_ID           int not null comment '企业ID';
alter TABLE SP_SERVICE_PROJECT add  COMPANY_NAME         varchar(200) not null comment '企业名称';
alter TABLE SP_SERVICE_PROJECT add  ADDRESS_PROVINCE     varchar(10) not null comment '联系地址省份';
alter TABLE SP_SERVICE_PROJECT change ADDRESS ADDRESS_DETAIL varchar(255) not null comment '详细联系地址，限字50个';
