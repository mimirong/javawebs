/*==============================================================*/
/* Table: GA_APTITUDE                                           */
/*==============================================================*/
create table GA_APTITUDE
(
   APTITUDE_ID          INTEGER not null auto_increment,
   TITLE                VARCHAR(100) not null,
   CONTENT              LONGTEXT,
   ATTACHMENT_FILE_NAME VARCHAR(100),
   ATTACHMENT_FILE_ID   VARCHAR(100),
   CREATOR_USER_ID      INTEGER,
   CREATOR_NAME         VARCHAR(100),
   IS_VISIBLE           TINYINT(1),
   PUBLISH_TIME         DATETIME,
   CREATE_TIME          DATETIME not null,
   UPDATE_TIME          DATETIME,
   primary key (APTITUDE_ID)
);

/*==============================================================*/
/* Table: GA_FINANCING_APPLICATION                              */
/*==============================================================*/
create table GA_FINANCING_APPLICATION
(
   APPLICATION_ID       INTEGER not null auto_increment,
   COMPANY_NAME         VARCHAR(100) not null,
   CONTACT              VARCHAR(100),
   CONTACT_MOBILE       VARCHAR(100),
   BUSINESS_INFO        VARCHAR(500),
   BUSINESS_LICENCE     VARCHAR(100),
   APPLICATION_FORM     VARCHAR(100),
   CONTENT              TEXT comment 'JSON',
   STATUS               VARCHAR(40) not null comment 'CREATED, APPROVED, REJECTED',
   APPLIER_USER_ID      INTEGER,
   APPLIER_NAME         VARCHAR(100),
   APPROVER_USER_ID     INTEGER,
   APPROVER_NAME        VARCHAR(100),
   CREATE_TIME          DATETIME not null,
   APPROVE_TIME         DATETIME,
   primary key (APPLICATION_ID)
);

/*==============================================================*/
/* Table: GA_FINANCING_GUIDE                                    */
/*==============================================================*/
create table GA_FINANCING_GUIDE
(
   GUIDE_ID             INTEGER not null auto_increment,
   TITLE                VARCHAR(100) not null,
   BRIEF                VARCHAR(200),
   CONTENT              LONGTEXT,
   ATTACHMENT_FILE_NAME VARCHAR(100),
   ATTACHMENT_FILE_ID   VARCHAR(100),
   CREATOR_USER_ID      INTEGER,
   CREATOR_NAME         VARCHAR(100),
   IS_VISIBLE           TINYINT(1),
   PUBLISH_TIME         DATETIME,
   CREATE_TIME          DATETIME not null,
   UPDATE_TIME          DATETIME,
   primary key (GUIDE_ID)
);

/*==============================================================*/
/* Table: GA_PARK_JOIN_APPLICATION                              */
/*==============================================================*/
create table GA_PARK_JOIN_APPLICATION
(
   APPLICATION_ID       INTEGER not null auto_increment,
   COMPANY_NAME         VARCHAR(100) not null,
   CONTACT              VARCHAR(100),
   CONTACT_MOBILE       VARCHAR(100),
   APPLICATION_FORM     VARCHAR(80),
   CONTENT              TEXT comment 'JSON',
   STATUS               VARCHAR(40) not null comment 'CREATED, APPROVED, REJECTED',
   APPLIER_USER_ID      INTEGER,
   APPLIER_NAME         VARCHAR(100),
   APPROVER_USER_ID     INTEGER,
   APPROVER_NAME        VARCHAR(100),
   CREATE_TIME          DATETIME not null,
   APPROVE_TIME         DATETIME,
   primary key (APPLICATION_ID)
);

/*==============================================================*/
/* Table: GA_PARK_JOIN_GUIDE                                    */
/*==============================================================*/
create table GA_PARK_JOIN_GUIDE
(
   GUIDE_ID             INTEGER not null auto_increment,
   TITLE                VARCHAR(100) not null,
   BRIEF                VARCHAR(200),
   CONTENT              LONGTEXT,
   ATTACHMENT_FILE_NAME VARCHAR(100),
   ATTACHMENT_FILE_ID   VARCHAR(100),
   CREATOR_USER_ID      INTEGER,
   CREATOR_NAME         VARCHAR(100),
   IS_VISIBLE           TINYINT(1),
   PUBLISH_TIME         DATETIME,
   CREATE_TIME          DATETIME not null,
   UPDATE_TIME          DATETIME,
   primary key (GUIDE_ID)
);

/*==============================================================*/
/* Table: GA_PARK_QUIT_APPLICATION                              */
/*==============================================================*/
create table GA_PARK_QUIT_APPLICATION
(
   APPLICATION_ID       INTEGER not null auto_increment,
   COMPANY_NAME         VARCHAR(100) not null,
   CONTACT              VARCHAR(100),
   CONTACT_MOBILE       VARCHAR(100),
   APPLICATION_FORM     VARCHAR(80),
   CONTENT              TEXT comment 'JSON',
   STATUS               VARCHAR(40) not null comment 'CREATED, APPROVED, REJECTED',
   APPLIER_USER_ID      INTEGER,
   APPLIER_NAME         VARCHAR(100),
   APPROVER_USER_ID     INTEGER,
   APPROVER_NAME        VARCHAR(100),
   CREATE_TIME          DATETIME not null,
   APPROVE_TIME         DATETIME,
   primary key (APPLICATION_ID)
);

/*==============================================================*/
/* Table: GA_PARK_QUIT_GUIDE                                    */
/*==============================================================*/
create table GA_PARK_QUIT_GUIDE
(
   GUIDE_ID             INTEGER not null auto_increment,
   TITLE                VARCHAR(100) not null,
   BRIEF                VARCHAR(200),
   CONTENT              LONGTEXT,
   ATTACHMENT_FILE_NAME VARCHAR(100),
   ATTACHMENT_FILE_ID   VARCHAR(100),
   CREATOR_USER_ID      INTEGER,
   CREATOR_NAME         VARCHAR(100),
   IS_VISIBLE           TINYINT(1),
   PUBLISH_TIME         DATETIME,
   CREATE_TIME          DATETIME not null,
   UPDATE_TIME          DATETIME,
   primary key (GUIDE_ID)
);

/*==============================================================*/
/* Table: GA_QUIT_RENT_INFO                                     */
/*==============================================================*/
create table GA_QUIT_RENT_INFO
(
   INFO_ID              INTEGER not null auto_increment,
   COMPANY_NAME         VARCHAR(100),
   RETURN_AMOUNT        INTEGER comment '单位为分',
   APPLY_TIME           DATETIME,
   QUIT_TIME            DATETIME,
   RETURN_TIME          DATETIME,
   STATUS               VARCHAR(40),
   primary key (INFO_ID)
);

/*==============================================================*/
/* Table: GA_RENT_INFO                                          */
/*==============================================================*/
create table GA_RENT_INFO
(
   RENT_INFO_ID         INTEGER not null auto_increment,
   BUILDING_NO          VARCHAR(100),
   FLOOR                VARCHAR(100),
   ROOM_NO              VARCHAR(100),
   AREA                 VARCHAR(100),
   IS_RENT              TINYINT(1),
   COMPANY_NAME         VARCHAR(100),
   CREATE_TIME          DATETIME not null,
   UPDATE_TIME          DATETIME,
   primary key (RENT_INFO_ID)
);

/*==============================================================*/
/* Table: GA_REPORT_SUBMIT                                      */
/*==============================================================*/
create table GA_REPORT_SUBMIT
(
   SUBMIT_ID            INTEGER not null auto_increment,
   TEMPLATE_ID          INTEGER,
   TEMPLATE_NAME        VARCHAR(100),
   COMPANY_NAME         VARCHAR(100) not null,
   CONTACT              VARCHAR(100),
   CONTACT_MOBILE       VARCHAR(100),
   REPORT_FILE_ID       VARCHAR(80),
   CONTENT              TEXT comment 'JSON',
   SUBMITTER_USER_ID    INTEGER,
   SUBMITTER_NAME       VARCHAR(100),
   CREATE_TIME          DATETIME not null,
   APPROVE_TIME         DATETIME,
   primary key (SUBMIT_ID)
);

/*==============================================================*/
/* Table: GA_REPORT_TEMPLATE                                    */
/*==============================================================*/
create table GA_REPORT_TEMPLATE
(
   TEMPLATE_ID          INTEGER not null auto_increment,
   TEMPLATE_NAME        VARCHAR(100) not null,
   CONTENT              LONGTEXT,
   ATTACHMENT_FILE_NAME VARCHAR(100),
   ATTACHMENT_FILE_ID   VARCHAR(100),
   CREATOR_USER_ID      INTEGER,
   CREATOR_NAME         VARCHAR(100),
   IS_VISIBLE           TINYINT(1),
   PUBLISH_TIME         DATETIME,
   CREATE_TIME          DATETIME not null,
   UPDATE_TIME          DATETIME,
   primary key (TEMPLATE_ID)
);

/*==============================================================*/
/* Table: GA_SITE_PROOF                                         */
/*==============================================================*/
create table GA_SITE_PROOF
(
   SITE_PROOF_ID        INTEGER not null auto_increment,
   TITLE                VARCHAR(100) not null,
   CONTENT              LONGTEXT,
   ATTACHMENT_FILE_NAME VARCHAR(100),
   ATTACHMENT_FILE_ID   VARCHAR(100),
   CREATOR_USER_ID      INTEGER,
   CREATOR_NAME         VARCHAR(100),
   IS_VISIBLE           TINYINT(1),
   PUBLISH_TIME         DATETIME,
   CREATE_TIME          DATETIME not null,
   UPDATE_TIME          DATETIME,
   primary key (SITE_PROOF_ID)
);

/*==============================================================*/
/* Table: LOG_OPERATION                                         */
/*==============================================================*/
create table LOG_OPERATION
(
   LOG_ID               INTEGER not null auto_increment,
   USER_ID              INTEGER,
   USERNAME             VARCHAR(80),
   OPERATION_TYPE       VARCHAR(40) comment '后台:MANAGEMENT',
   TARGET               VARCHAR(40),
   TARGET_INFO          VARCHAR(40),
   OPERATION            VARCHAR(40),
   CREATE_TIME          TIMESTAMP not null,
   DATA                 TEXT,
   primary key (LOG_ID)
);

/*==============================================================*/
/* Index: IX_OPERATION_LOG_CREATE_TIME                          */
/*==============================================================*/
create index IX_OPERATION_LOG_CREATE_TIME on LOG_OPERATION
(
   CREATE_TIME
);

/*==============================================================*/
/* Table: SYS_FILE_INFO                                         */
/*==============================================================*/
create table SYS_FILE_INFO
(
   FILE_ID              VARCHAR(100) not null,
   FILE_NAME            VARCHAR(100) not null,
   FILE_SIZE            BIGINT,
   CREATE_TIME          DATETIME not null,
   MIME_TYPE            VARCHAR(40),
   EXTENSION            VARCHAR(40),
   OWNER_ID             INTEGER,
   primary key (FILE_ID)
);

/*==============================================================*/
/* Table: UC_ADMIN_USER                                         */
/*==============================================================*/
create table UC_ADMIN_USER
(
   USER_ID              VARCHAR(40) not null,
   USERNAME             VARCHAR(150) not null,
   PASSWORD             VARCHAR(40),
   MOBILE               VARCHAR(40),
   EMAIL                VARCHAR(250),
   IS_ACCEPT_MESSAGE    VARCHAR(5),
   CREATE_TIME          DATETIME,
   LAST_LOGIN_TIME      DATETIME,
   primary key (USER_ID)
);

/*==============================================================*/
/* Table: UC_DEPT_INFO                                          */
/*==============================================================*/
create table UC_DEPT_INFO
(
   DEPT_ID              INTEGER not null auto_increment,
   REF_DEPT_ID          VARCHAR(100),
   NAME                 VARCHAR(80) not null,
   ORG_ID               INTEGER,
   PARENT_DEPT_ID       INTEGER,
   FULL_ID              VARCHAR(200),
   CREATE_TIME          DATETIME not null,
   UPDATE_TIME          DATETIME,
   primary key (DEPT_ID)
);

/*==============================================================*/
/* Table: UC_MANAGE_RIGHT                                       */
/*==============================================================*/
create table UC_MANAGE_RIGHT
(
   RIGHT_ID             VARCHAR(128) not null,
   PARENT_ID            VARCHAR(128),
   NAME                 VARCHAR(64) not null,
   SYSTEM_TYPE          VARCHAR(16) comment 'BASIC:基本配置管理系统 PORTAL:门户内容管理系统',
   URL                  VARCHAR(2000),
   DISPLAY              TINYINT(1) not null,
   SORT_INDEX           INTEGER not null,
   ICON_NAME            VARCHAR(128),
   primary key (RIGHT_ID)
);

/*==============================================================*/
/* Table: UC_MANAGE_ROLE                                        */
/*==============================================================*/
create table UC_MANAGE_ROLE
(
   ROLE_ID              VARCHAR(32) not null,
   NAME                 VARCHAR(32) not null,
   DESCRIPTION          VARCHAR(256),
   CUSTOM_MENU          TEXT,
   primary key (ROLE_ID)
);

/*==============================================================*/
/* Table: UC_MANAGE_ROLE_RIGHT                                  */
/*==============================================================*/
create table UC_MANAGE_ROLE_RIGHT
(
   ID                   INTEGER not null auto_increment,
   ROLE_ID              VARCHAR(32) not null,
   RIGHT_ID             VARCHAR(128) not null,
   primary key (ID)
);

/*==============================================================*/
/* Index: IX_MANAGE_ROLE_RIGHT_RIGHT_ID                         */
/*==============================================================*/
create index IX_MANAGE_ROLE_RIGHT_RIGHT_ID on UC_MANAGE_ROLE_RIGHT
(
   RIGHT_ID
);

/*==============================================================*/
/* Index: IX_MANAGE_ROLE_RIGHT_ROLE_ID                          */
/*==============================================================*/
create index IX_MANAGE_ROLE_RIGHT_ROLE_ID on UC_MANAGE_ROLE_RIGHT
(
   ROLE_ID
);

/*==============================================================*/
/* Table: UC_MANAGE_USER_ROLE                                   */
/*==============================================================*/
create table UC_MANAGE_USER_ROLE
(
   ID                   INTEGER not null auto_increment,
   USER_ID              INTEGER not null,
   ROLE_ID              VARCHAR(32) not null,
   primary key (ID)
);

/*==============================================================*/
/* Index: IX_MANAGE_USER_ROLE_USER_ID                           */
/*==============================================================*/
create index IX_MANAGE_USER_ROLE_USER_ID on UC_MANAGE_USER_ROLE
(
   USER_ID
);

/*==============================================================*/
/* Index: IX_MANAGE_USER_ROLE_ROLE_ID                           */
/*==============================================================*/
create index IX_MANAGE_USER_ROLE_ROLE_ID on UC_MANAGE_USER_ROLE
(
   ROLE_ID
);

/*==============================================================*/
/* Table: UC_MESSAGE                                            */
/*==============================================================*/
create table UC_MESSAGE
(
   MESSAGE_ID           INTEGER not null auto_increment,
   MESSAGE_TYPE         VARCHAR(40) comment 'USER:用户消息 ',
   TITLE                VARCHAR(200),
   CONTENT              TEXT,
   SENDER_ID            INTEGER,
   SENDER_NAME          VARCHAR(100),
   RECEIVER_ID          INTEGER,
   RECEIVER_NAME        VARCHAR(100),
   IS_READ              TINYINT(1),
   SEND_TIME            DATETIME not null,
   READ_TIME            DATETIME,
   primary key (MESSAGE_ID)
);

/*==============================================================*/
/* Table: UC_ORG_INFO                                           */
/*==============================================================*/
create table UC_ORG_INFO
(
   ORG_ID               INTEGER not null auto_increment,
   REF_ORG_ID           VARCHAR(100),
   NAME                 VARCHAR(80) not null,
   PARENT_ORG_ID        INTEGER,
   CREATE_TIME          DATETIME not null,
   UPDATE_TIME          DATETIME,
   primary key (ORG_ID)
);

/*==============================================================*/
/* Table: UC_USER_INFO                                          */
/*==============================================================*/
create table UC_USER_INFO
(
   USER_ID              INTEGER not null auto_increment,
   REF_USER_ID          VARCHAR(100),
   USER_TYPE            VARCHAR(20) not null comment 'COMPANY:企业用户, PARK:园区管委会用户, SYSTEM:系统用户, PORTAL:门户用户',
   LOGIN_NAME           VARCHAR(80) not null,
   LOGIN_NAME_UPPER     VARCHAR(80) not null comment '用于不区分大小写查询',
   PASSWORD_HASH        VARCHAR(80),
   PASSWORD             VARCHAR(80),
   PASSWORD_KEY         VARCHAR(80),
   DEPT_ID              INTEGER,
   NAME                 VARCHAR(40) not null,
   ID_CARD              VARCHAR(40),
   MOBILE               VARCHAR(80),
   TELEPHONE            VARCHAR(80),
   EMAIL                VARCHAR(300),
   EMAIL_UPPER          VARCHAR(300) comment '用于不区分大小写查询',
   FULL_ADDRESS         VARCHAR(300),
   HEAD_IMAGE_PREVIEW_ID VARCHAR(80),
   HEAD_IMAGE_ID        VARCHAR(80),
   SEX                  CHAR(1) comment '1男0女',
   BIRTHDAY             VARCHAR(40),
   COMPANY_NAME         VARCHAR(200),
   APPROVE_STATUS       VARCHAR(40) comment 'CREATED:未申请 APPLIED:已申请 APPROVED:已审核 REJECTED:已拒绝',
   USABLE               TINYINT(1),
   CREATE_TIME          DATETIME not null,
   UPDATE_TIME          DATETIME,
   primary key (USER_ID)
);

/*==============================================================*/
/* Index: IX_USER_INFO_LOGIN_NAME_UPPER                         */
/*==============================================================*/
create index IX_USER_INFO_LOGIN_NAME_UPPER on UC_USER_INFO
(
   LOGIN_NAME_UPPER
);

/*==============================================================*/
/* Index: IX_USER_INFO_EMAIL_UPPER                              */
/*==============================================================*/
create index IX_USER_INFO_EMAIL_UPPER on UC_USER_INFO
(
   EMAIL_UPPER
);

/*==============================================================*/
/* Table: UC_USER_VERIFICATION_SESSION                          */
/*==============================================================*/
create table UC_USER_VERIFICATION_SESSION
(
   SESSION_ID           CHAR(32) not null,
   SESSION_KEY          CHAR(32) not null,
   CODE                 VARCHAR(20) comment '手机邮箱验证码',
   VERIFICATION_TYPE    VARCHAR(20) comment 'M_REGISTER, M_FINDPWD',
   STATUS               VARCHAR(20) not null comment 'create,verified,finished,timeout',
   EMAIL                VARCHAR(255),
   MOBILE               VARCHAR(32),
   CREATE_TIME          DATETIME not null,
   VALIDITY_TIME        DATETIME not null,
   USER_ID              VARCHAR(40),
   EXTRA_DATA           VARCHAR(1),
   FINISH_TIME          DATETIME,
   primary key (SESSION_ID)
);

/*==============================================================*/
/* Index: IX_USER_VERIFY_SESSION_MOBILE                         */
/*==============================================================*/
create index IX_USER_VERIFY_SESSION_MOBILE on UC_USER_VERIFICATION_SESSION
(
   MOBILE
);

