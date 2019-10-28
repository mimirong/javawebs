create table DD_DETECTION_APPLY
(
   APPLY_ID             INTEGER not null auto_increment,
   APPLY_TYPE           VARCHAR(20),
   STATUS               VARCHAR(20),
   RESOURCE_ID          VARCHAR(100) comment 'Article ID',
   RESOURCE_NAME        VARCHAR(100) comment 'Article Title',
   COMPANY_NAME         VARCHAR(100),
   CONTACT              VARCHAR(100),
   CONTACT_NAME         VARCHAR(100),
   COMMENT              TEXT,
   APPLIER_USER_ID      INTEGER,
   APPLIER_NAME         VARCHAR(100),
   APPROVER_USER_ID     INTEGER,
   APPROVER_NAME        VARCHAR(100),
   APPROVE_COMMENT      VARCHAR(200),
   EXTRA_DATA           TEXT,
   CREATE_TIME          DATETIME,
   UPDATE_TIME          DATETIME,
   APPROVE_TIME         DATETIME,
   primary key (APPLY_ID)
);
