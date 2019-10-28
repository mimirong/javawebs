/*==============================================================*/
/* Table: LOG_INTERFACE_CALL                                    */
/*==============================================================*/
create table LOG_INTERFACE_CALL
(
   ID                   INTEGER not null auto_increment,
   USER_ID              INTEGER,
   METHOD               VARCHAR(60) not null,
   CLIENT_TYPE          VARCHAR(20),
   CLIENT_VERSION       VARCHAR(20),
   CLIENT_VERSION_CODE  INTEGER,
   DEVICE_MODEL         VARCHAR(50),
   DEVICE_ID            VARCHAR(50),
   SYSTEM_VERSION       VARCHAR(50),
   REQUEST_TIME         DATETIME,
   PROCESS_DURATION     INTEGER,
   IS_SUCCESS           TINYINT(1),
   ERROR_MESSAGE        VARCHAR(100),
   REQUEST_LENGTH       INTEGER,
   RESPONSE_LENGTH      INTEGER,
   primary key (ID)
);

/*==============================================================*/
/* Table: UC_PUSH_DEVICE                                        */
/*==============================================================*/
create table UC_PUSH_DEVICE
(
   REGISTRATION_ID      VARCHAR(80) not null comment '极光推送的registration_id',
   USER_ID              INTEGER,
   PLATFORM_CODE        VARCHAR(20) comment 'IOS, ANDROID',
   LOGIN_TOKEN          VARCHAR(80),
   UPDATE_TIME          TIMESTAMP,
   primary key (REGISTRATION_ID)
);

/*==============================================================*/
/* Index: IX_PUSH_DEVICE_USER_ID                                */
/*==============================================================*/
create index IX_PUSH_DEVICE_USER_ID on UC_PUSH_DEVICE
(
   USER_ID
);

