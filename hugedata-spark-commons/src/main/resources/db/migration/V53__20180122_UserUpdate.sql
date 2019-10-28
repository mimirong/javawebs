/*==============================================================*/
/* UC_USER_INFO                                                 */
/*==============================================================*/
alter table UC_USER_INFO add SERVICE_ROLE VARCHAR(50);
alter table UC_USER_INFO modify DEPT_NAME VARCHAR(200);

/*==============================================================*/
/* Table: UC_USER_DEPT                                          */
/*==============================================================*/
create table UC_USER_DEPT
(
   RECORD_ID            INTEGER not null auto_increment,
   USER_ID              INTEGER not null,
   DEPT_ID              INTEGER not null,
   primary key (RECORD_ID)
);

create index IX_USERDEPT_USERID on UC_USER_DEPT (USER_ID);
create index IX_USERDEPT_DEPTID on UC_USER_DEPT (DEPT_ID);

/*==============================================================*/
/* Table: UC_PARK_DEPT                                          */
/*==============================================================*/
create table UC_PARK_DEPT
(
   DEPT_ID              INTEGER not null,
   DEPT_NAME            VARCHAR(100) not null,
   primary key (DEPT_ID)
);

