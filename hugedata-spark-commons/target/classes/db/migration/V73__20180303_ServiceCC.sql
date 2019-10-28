
/*==============================================================*/
/* Table: AP_SERVICE_CC                                         */
/*==============================================================*/
create table AP_SERVICE_CC
(
   ID                   INTEGER not null auto_increment,
   SERVICE_ID           INTEGER not null,
   CC_USER_ID           INTEGER,
   CC_USER_NAME         VARCHAR(100),
   CC_DEPT_ID           INTEGER,
   CC_DEPT_NAME         VARCHAR(100),
   CREATE_TIME          DATETIME,
   primary key (ID)
);

create index IX_SERVCC_SERVID_USERID on AP_SERVICE_CC (SERVICE_ID, CC_USER_ID);
