/*==============================================================*/
/* Table: GA_SERVICE_GUIDE                                      */
/*==============================================================*/
create table GA_SERVICE_GUIDE
(
   GUIDE_ID             INTEGER not null auto_increment,
   DEPT_CODE            VARCHAR(40),
   DEPT_NAME            VARCHAR(100),
   GUIDE_NAME           VARCHAR(100),
   GUIDE_TYPE           VARCHAR(100),
   SERVICE_SUBJECT      VARCHAR(100),
   ACCORDING            VARCHAR(100),
   PRECONDITION         VARCHAR(100),
   JOINT_DEPT           VARCHAR(100),
   LEGAL_TIME_LIMIT     VARCHAR(100),
   PROMISED_TIME_LIMIT  VARCHAR(100),
   CONDITIONS           LONGTEXT,
   MATERIAL             LONGTEXT,
   PROCESS              LONGTEXT,
   IS_CHARGE            TINYINT(1),
   CHARGE_ACCORDING     VARCHAR(100),
   CHARGE_STANDARD      VARCHAR(100),
   ADDRESS              VARCHAR(100),
   WORK_TIME            VARCHAR(100),
   TELEPHONE            VARCHAR(100),
   COMPLAINT_TELEPHONE  VARCHAR(100),
   FLOW_IMAGE_FILE_ID   VARCHAR(100),
   FLOW_IMAGE_FILE_NAME VARCHAR(100),
   SORT_INDEX           INTEGER,
   CREATE_TIME          DATETIME not null,
   UPDATE_TIME          DATETIME,
   primary key (GUIDE_ID)
);

create index IX_SERVICEGUIDE_DEPTCODE on GA_SERVICE_GUIDE (DEPT_CODE);

/*==============================================================*/
/* Table: GA_SERVICE_GUIDE_ATTACHMENT                           */
/*==============================================================*/
create table GA_SERVICE_GUIDE_ATTACHMENT
(
   ATTACHMENT_ID        INTEGER not null auto_increment,
   ATTACHMENT_TYPE      VARCHAR(20),
   GUIDE_ID             INTEGER,
   FILE_ID              VARCHAR(100),
   FILE_NAME            VARCHAR(100),
   PREVIEW_FILE_ID      VARCHAR(100),
   PREVIEW_FILE_NAME    VARCHAR(100),
   SORT_INDEX           INTEGER,
   CREATE_TIME          DATETIME not null,
   primary key (ATTACHMENT_ID)
);

create index IX_SERVGUIDEATT_GUIDEID on GA_SERVICE_GUIDE_ATTACHMENT (GUIDE_ID);

