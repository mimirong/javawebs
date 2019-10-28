create table DD_BUSINESS_REQUIREMENT
(
   REQUIREMENT_ID       INTEGER not null auto_increment,
   REQUIREMENT_TYPE     VARCHAR(30) comment 'DdBusinessRequirement',
   TITLE                VARCHAR(100),
   PRICE                INTEGER,
   CONTACT_NAME         VARCHAR(100),
   CONTACT_TELEPHONE    VARCHAR(100),
   STATUS               VARCHAR(20) comment 'CREATED, FINISHED',
   CONTENT              LONGTEXT,
   PUBLISH_TIME         DATETIME,
   CREATE_TIME          DATETIME not null,
   UPDATE_TIME          DATETIME,
   primary key (REQUIREMENT_ID)
);


create table DD_TRAINING_BASE_INFO
(
   INFO_ID              INTEGER not null auto_increment,
   NAME                 VARCHAR(100),
   ADDRESS              VARCHAR(100),
   IMAGE_FILE_ID        VARCHAR(100),
   IMAGE_FILE_NAME      VARCHAR(200),
   CONTACT_NAME         VARCHAR(100),
   CONTACT_TELEPHONE    VARCHAR(100),
   PRICE_INFO           VARCHAR(100),
   CONTENT              LONGTEXT,
   PUBLISH_TIME         DATETIME,
   CREATE_TIME          DATETIME not null,
   UPDATE_TIME          DATETIME,
   primary key (INFO_ID)
);


create table DD_TRAINING_INFO
(
   INFO_ID              INTEGER not null auto_increment,
   TITLE                VARCHAR(100) not null,
   TRAINING_DATE        DATETIME,
   TRAINING_TIME        VARCHAR(100),
   TRAINING_START_TIME  VARCHAR(100),
   TRAINING_END_TIME    VARCHAR(100),
   ADDRESS              VARCHAR(100),
   CONTACT_NAME         VARCHAR(100),
   CONTACT_TELEPHONE    VARCHAR(100),
   PRICE_INFO           VARCHAR(100),
   CONTENT              LONGTEXT,
   PUBLISH_TIME         DATETIME,
   CREATE_TIME          DATETIME not null,
   UPDATE_TIME          DATETIME,
   primary key (INFO_ID)
);


create table DD_DETECTION_COMPANY
(
   COMPANY_ID           INTEGER not null auto_increment,
   COMPANY_NAME         VARCHAR(100),
   ESTABLISH_TIME       VARCHAR(20),
   ADDRESS              VARCHAR(100),
   TELEPHONE            VARCHAR(100),
   CONTENT              LONGTEXT,
   HONOR_FILE_ID        VARCHAR(100),
   HONOR_FILE_NAME      VARCHAR(200),
   PUBLISH_TIME         DATETIME,
   CREATE_TIME          DATETIME not null,
   UPDATE_TIME          DATETIME,
   primary key (COMPANY_ID)
);
