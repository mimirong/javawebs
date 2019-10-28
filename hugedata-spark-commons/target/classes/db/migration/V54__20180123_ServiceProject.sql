/*==============================================================*/
/* Table: SP_SERVICE_PROJECT                                    */
/*==============================================================*/
create table SP_SERVICE_PROJECT
(
   PROJECT_ID           integer not null auto_increment comment '项目ID，自增',
   SERVICE_FIELD        varchar(30) not null comment '服务领域，固定个数',
   SUPPLY_TYPE          varchar(20) not null comment '供应类型，检验监测产品、技术服务',
   SUPPLY_THEME         varchar(100) not null comment '供应主题名称，不能超过50个字',
   SUPPLY_BRIEF         varchar(255) not null comment '供应简介，不能超过100个字',
   COVER_FILE_ID        varchar(100) comment '封面图片ID',
   COVER_FILE_NAME      varchar(100) comment '封面图片名称',
   DETAIL_DESC          varchar(255) not null comment '详细说明',
   SEARCH_KEY           varchar(150) not null comment '搜过关键字，用分号隔开，限字60个',
   ADDRESS              varchar(255) not null comment '联系地址，限字55个',
   CONTACT              varchar(15) not null comment '联系人姓名，限字10个',
   CONTACT_MOBILE       varchar(20) not null comment '联系电话',
   EMAIL                varchar(100) not null comment '电子邮箱',
   CREATE_TIME          datetime not null comment '创建时间',
   UPDATE_TIME          datetime comment '更新时间',
   primary key (PROJECT_ID)
);

/*==============================================================*/
/* Table: SP_SPECIFICATION                                      */
/*==============================================================*/
create table SP_SPECIFICATION
(
   SPEC_ID              integer not null auto_increment comment '规格ID，自增',
   PROJECT_ID           varchar(100) not null comment '项目ID',
   SPEC_NAME            varchar(100) not null comment '规格名称',
   REFER_PRICE          float not null comment '规格单价',
   MEASURE_UNIT         varchar(10) not null comment '计量单位',
   IS_NEGOTIABLE_PRICE  tinyint(1) comment '价格面议',
   PRICE_UNIT           varchar(5) not null comment '价格单位，元、万元、百万元',
   SPEC_BRIEF           varchar(255) not null comment '规格简介，限字100个',
   CREATE_TIME          datetime not null comment '创建时间',
   UPDATE_TIME          datetime comment '更新时间',
   primary key (SPEC_ID)
);

/*==============================================================*/
/* Table: SP_PROJECT_IMAGE                                      */
/*==============================================================*/
create table SP_PROJECT_IMAGE
(
   IMAGE_ID             INTEGER not null auto_increment,
   PROJECT_ID           VARCHAR(100),
   FILE_ID              VARCHAR(100),
   FILE_NAME            VARCHAR(100),
   PREVIEW_FILE_ID      VARCHAR(100),
   PREVIEW_FILE_NAME    VARCHAR(100),
   REMARK               VARCHAR(300),
   SORT_INDEX           DECIMAL(5),
   CREATE_TIME          DATETIME not null,
   primary key (IMAGE_ID)
);