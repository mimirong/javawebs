/*==============================================================*/
/* Table: GA_PM_BILL                                            */
/*==============================================================*/
create table GA_PM_BILL
(
   BILL_ID              INTEGER not null auto_increment,
   BILL_TYPE            VARCHAR(40),
   MONTH                VARCHAR(100) comment 'YYYYMM 或 第x季度',
   AMOUNT               INTEGER comment '单位为分',
   REMARK               VARCHAR(200),
   STATUS               VARCHAR(40) comment 'CREATED, FINISHED',
   USER_ID              INTEGER,
   USER_NAME            VARCHAR(100),
   COMPANY_NAME         VARCHAR(100),
   CREATE_TIME          DATETIME not null,
   EXPIRE_TIME          DATETIME,
   FINISH_TIME          DATETIME,
   primary key (BILL_ID)
);


create index IX_PM_BILL_USER_ID on GA_PM_BILL(USER_ID);

/*==============================================================*/
/* Table: GA_PM_STANDARD                                        */
/*==============================================================*/
create table GA_PM_STANDARD
(
   STANDARD_ID          VARCHAR(100) not null,
   NAME                 VARCHAR(100),
   REMARK               TEXT,
   ICON_FILE_ID         VARCHAR(100),
   CREATE_TIME          DATETIME,
   primary key (STANDARD_ID)
);

/*==============================================================*/
/* Table: GA_PM_REFUND                                          */
/*==============================================================*/
create table GA_PM_REFUND
(
   REFUND_ID            INTEGER not null auto_increment,
   COMPANY_NAME         VARCHAR(100),
   REMARK               VARCHAR(300),
   AMOUNT               INTEGER,
   STATUS               VARCHAR(20) comment 'CREATED, APPROVED',
   USER_ID              INTEGER,
   USER_NAME            VARCHAR(100),
   CREATE_TIME          DATETIME not null,
   ESTIMATED_REFUND_TIME DATETIME,
   REFUND_TIME          DATETIME,
   primary key (REFUND_ID)
);
create index IX_GA_PM_REFUND_USER_ID on GA_PM_REFUND (USER_ID);




alter table GA_PARK_JOIN_APPLICATION add APPROVE_COMMENT VARCHAR(200); 

create unique index IX_ORG_INFO_REF_ORG_ID on UC_ORG_INFO (REF_ORG_ID);
create unique index IX_DEPT_INFO_REF_DEPT_ID on UC_DEPT_INFO (REF_DEPT_ID);
create unique index IX_USER_INFO_REF_USER_ID on UC_USER_INFO (REF_USER_ID);

alter table GA_FINANCING_APPLICATION add STAFF_SIZE VARCHAR(100);

alter table GA_PARK_JOIN_GUIDE add CREATOR_DEPT VARCHAR(100); 



