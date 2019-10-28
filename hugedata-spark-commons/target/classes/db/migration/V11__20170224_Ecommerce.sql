/*==============================================================*/
/* Table: EC_BIDDING_INFO                                       */
/*==============================================================*/
create table EC_BIDDING_INFO
(
   INFO_ID              INTEGER not null auto_increment,
   TITLE                VARCHAR(200),
   CONTENT              LONGTEXT,
   INDUSTRY_TYPE        VARCHAR(100) comment 'EcBiddingInfoConstants',
   PUBLISH_COMPANY      VARCHAR(100),
   PRICE                VARCHAR(100),
   PUBLISHER            VARCHAR(100),
   TELEPHONE            VARCHAR(100),
   STATUS               VARCHAR(20) comment 'EcBiddingInfoConstants',
   PUBLISH_TIME         DATETIME,
   CREATE_TIME          DATETIME not null,
   UPDATE_TIME          DATETIME,
   primary key (INFO_ID)
);

/*==============================================================*/
/* Table: EC_SUCCESS_BIDDING                                    */
/*==============================================================*/
create table EC_SUCCESS_BIDDING
(
   SUBMIT_ID            INTEGER not null auto_increment,
   TITLE                VARCHAR(100),
   INDUSTRY_TYPE        VARCHAR(100),
   COMPANY_NAME         VARCHAR(100),
   CONTACT_NAME         VARCHAR(100),
   CONTACT_TELEPHONE    VARCHAR(100),
   PUBLISH_TIME         DATETIME,
   CREATE_TIME          DATETIME not null,
   UPDATE_TIME          DATETIME,
   primary key (SUBMIT_ID)
);


/*==============================================================*/
/* Table: EC_REQUIREMENT_INFO                                   */
/*==============================================================*/
create table EC_REQUIREMENT_INFO
(
   INFO_ID              INTEGER not null auto_increment,
   PROJECT_TYPE         VARCHAR(100),
   TITLE                VARCHAR(100),
   CONTENT              LONGTEXT,
   ATTACHMENT_FILE_ID   VARCHAR(100),
   ATTACHMENT_FILE_NAME VARCHAR(200),
   STATUS               VARCHAR(20),
   CONTACT_NAME         VARCHAR(100),
   CONTACT_TELEPHONE    VARCHAR(100),
   PRICE                VARCHAR(100),
   PUBLISH_TIME         DATETIME,
   CREATE_TIME          DATETIME,
   UPDATE_TIME          DATETIME,
   primary key (INFO_ID)
);

/*==============================================================*/
/* Table: EC_INVESTMENT_INFO                                    */
/*==============================================================*/
create table EC_INVESTMENT_INFO
(
   INFO_ID              INTEGER not null auto_increment,
   PROJECT_TYPE         VARCHAR(100),
   COMPANY_NAME         VARCHAR(100),
   SERVICE_RANGE        VARCHAR(200),
   LOGO_FILE_ID         VARCHAR(100),
   LOGO_FILE_NAME       VARCHAR(100),
   ESTABLISH_TIME       VARCHAR(20),
   REMARK               TEXT,
   ADDRESS              VARCHAR(200),
   CONTACT_NAME         VARCHAR(100),
   CONTACT_TELEPHONE    VARCHAR(100),
   EMAIL                VARCHAR(200),
   PUBLISH_TIME         DATETIME,
   CREATE_TIME          DATETIME not null,
   UPDATE_TIME          DATETIME,
   primary key (INFO_ID)
);
