/*==============================================================*/
/* Table: AP_SERVICE_ATTACHMENT                                 */
/*==============================================================*/
create table AP_SERVICE_ATTACHMENT
(
   ATTACHMENT_ID        INTEGER not null auto_increment,
   SERVICE_ID           INTEGER,
   ATT_CONFIG_ID        INTEGER,
   ATT_CONFIG_NAME      VARCHAR(100),
   FILE_ID              VARCHAR(100),
   FILE_NAME            VARCHAR(100),
   REMARK               VARCHAR(200),
   APPROVE_STATUS       VARCHAR(30) comment 'CREATED,APPROVED,REJECTED',
   SORT_INDEX           INTEGER,
   CREATE_TIME          DATETIME,
   primary key (ATTACHMENT_ID)
);

create index IX_SERVATT_SERVICEID on AP_SERVICE_ATTACHMENT (SERVICE_ID);

/*==============================================================*/
/* Table: GA_SERVICE_GUIDE_ATT_CONFIG                           */
/*==============================================================*/
create table GA_SERVICE_GUIDE_ATT_CONFIG
(
   ATT_CONFIG_ID        INTEGER not null auto_increment,
   ATT_CONFIG_NAME      VARCHAR(100) not null,
   GUIDE_ID             INTEGER not null,
   IS_REQUIRED          TINYINT(1) not null default 0 comment '保留字段',
   CREATE_TIME          DATETIME not null,
   primary key (ATT_CONFIG_ID)
);

create index IX_SERVGAC_GUIDEID on GA_SERVICE_GUIDE_ATT_CONFIG (GUIDE_ID);
