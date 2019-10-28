create table MP_BANNER
(
   BANNER_ID            INTEGER not null,
   NAME                 VARCHAR(80),
   FILE_ID              VARCHAR(80),
   PREVIEW_FILE_ID      VARCHAR(80),
   LINK_URL             VARCHAR(200),
   SORT_INDEX           INTEGER,
   IS_VISIBLE           TINYINT(1),
   CREATE_TIME          DATETIME not null,
   primary key (BANNER_ID)
);

create table MP_DEPT_CONTACT
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
