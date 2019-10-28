/*==============================================================*/
/* Table: OS_COMPANY_CONTRACT                                   */
/*==============================================================*/
create table OS_COMPANY_CONTRACT
(
   CONTRACT_ID          INTEGER not null auto_increment,
   COMPANY_NAME         VARCHAR(100) not null,
   CONTRACT_NO          VARCHAR(100),
   CONTRACT_DATE        DATETIME,
   REMARK               TEXT,
   CREATE_TIME          DATETIME not null,
   UPDATE_TIME          DATETIME,
   primary key (CONTRACT_ID)
);

/*==============================================================*/
/* Table: OS_COMPANY_CREDIT                                     */
/*==============================================================*/
create table OS_COMPANY_CREDIT
(
   COMPANY_CREDIT_ID    INTEGER not null auto_increment,
   COMPANY_NAME         VARCHAR(100),
   LEGAL_PERSON         VARCHAR(100),
   REGISTRATION_ID      VARCHAR(100),
   REGISTERED_CAPITAL   VARCHAR(100),
   REGISTERED_DATE      VARCHAR(100),
   TELEPHONE            VARCHAR(40),
   ADDRESS              VARCHAR(100),
   STATUS               VARCHAR(20),
   CREATE_TIME          DATETIME not null,
   UPDATE_TIME          DATETIME,
   primary key (COMPANY_CREDIT_ID)
);

/*==============================================================*/
/* Table: OS_COMPANY_FEEDBACK                                   */
/*==============================================================*/
create table OS_COMPANY_FEEDBACK
(
   FEEDBACK_ID          INTEGER not null auto_increment,
   COMPANY_NAME         VARCHAR(100) not null,
   FEEDBACK_TIME        DATETIME,
   CONTENT              TEXT,
   CONTACT              VARCHAR(40),
   CONTACT_MOBILE       VARCHAR(40),
   STATUS               VARCHAR(20),
   UPDATE_TIME          DATETIME,
   primary key (FEEDBACK_ID)
);

/*==============================================================*/
/* Table: OS_COMPANY_INFO                                       */
/*==============================================================*/
create table OS_COMPANY_INFO
(
   COMPANY_ID           INTEGER not null auto_increment,
   COMPANY_NAME         VARCHAR(100) not null,
   COMPANY_TYPE         VARCHAR(40),
   INDUSTRY_TYPE        VARCHAR(40),
   COMPANY_SIZE         VARCHAR(40),
   REGISTERED_YEAR      VARCHAR(20),
   REGISTERED_CAPITAL   VARCHAR(20),
   BUSINESS_SCOPE       VARCHAR(1000),
   ADDRESS              VARCHAR(100),
   TELEPHONE            VARCHAR(40),
   DESCRIPTION          LONGTEXT,
   PRODUCTS_INFO        TEXT,
   PROJECTS_INFO        TEXT,
   DEMO_FILE_ID         VARCHAR(100),
   CREATE_TIME          DATETIME not null,
   UPDATE_TIME          DATETIME,
   primary key (COMPANY_ID)
);

/*==============================================================*/
/* Table: OS_COMPANY_PROJECT                                    */
/*==============================================================*/
create table OS_COMPANY_PROJECT
(
   PROJECT_ID           INTEGER not null auto_increment,
   COMPANY_NAME         VARCHAR(100) not null,
   PROJECT_NAME         VARCHAR(100),
   REMARK               TEXT,
   STATUS               VARCHAR(20),
   CREATE_TIME          DATETIME not null,
   UPDATE_TIME          DATETIME,
   primary key (PROJECT_ID)
);

/*==============================================================*/
/* Table: OS_COMPANY_SERVICE                                    */
/*==============================================================*/
create table OS_COMPANY_SERVICE
(
   SERVICE_ID           INTEGER not null auto_increment,
   COMPANY_NAME         VARCHAR(100) not null,
   DISCUSS_TIME         DATETIME,
   SERVICE_TYPE         VARCHAR(20),
   REMARK               TEXT,
   STATUS               VARCHAR(20),
   CREATE_TIME          DATETIME not null,
   UPDATE_TIME          DATETIME,
   primary key (SERVICE_ID)
);

/*==============================================================*/
/* Table: OS_INTELLECTUAL_PROPERTY_APPLY                        */
/*==============================================================*/
create table OS_INTELLECTUAL_PROPERTY_APPLY
(
   APPLY_ID             INTEGER not null auto_increment,
   COMPANY_NAME         VARCHAR(100),
   SERVICE_ID           INTEGER,
   SERVICE_TYPE         VARCHAR(100),
   REMARK               TEXT,
   CONTACT_NAME         VARCHAR(100),
   CONTACT_TELEPHONE    VARCHAR(40),
   ATTACHMENT_FILE_NAME VARCHAR(100),
   ATTACHMENT_FILE_ID   VARCHAR(100),
   APPLIER_ID           INTEGER,
   APPLIER_NAME         VARCHAR(100),
   CREATE_TIME          DATETIME not null,
   UPDATE_TIME          DATETIME,
   primary key (APPLY_ID)
);

/*==============================================================*/
/* Table: OS_INTELLECTUAL_PROPERTY_INFO                         */
/*==============================================================*/
create table OS_INTELLECTUAL_PROPERTY_INFO
(
   INFO_ID              INTEGER not null auto_increment,
   SERVICE_TYPE         VARCHAR(100) not null,
   PUBLISHER_DEPT_ID    VARCHAR(100),
   PUBLISHER_DEPT       INTEGER,
   PUBLISHER            VARCHAR(100),
   PUBLISH_TIME         DATETIME,
   CONTENT              LONGTEXT,
   ATTACHMENT_FILE_NAME VARCHAR(100),
   ATTACHMENT_FILE_ID   VARCHAR(100),
   CREATE_TIME          DATETIME not null,
   UPDATE_TIME          DATETIME,
   primary key (INFO_ID)
);

/*==============================================================*/
/* Table: OS_INTENTION_COMPANY                                  */
/*==============================================================*/
create table OS_INTENTION_COMPANY
(
   COMPANY_ID           INTEGER not null auto_increment,
   COMPANY_NAME         VARCHAR(100) not null,
   COMPANY_TYPE         VARCHAR(40),
   DISCUSS_TIME         DATETIME,
   DISCUSS_CONTENT      TEXT,
   STATUS               VARCHAR(20),
   CREATE_TIME          DATETIME not null,
   UPDATE_TIME          DATETIME,
   primary key (COMPANY_ID)
);

/*==============================================================*/
/* Table: OS_JOB_OFFER                                          */
/*==============================================================*/
create table OS_JOB_OFFER
(
   JOB_OFFER_ID         INTEGER not null auto_increment,
   COMPANY_NAME         VARCHAR(100) not null,
   JOB_NAME             VARCHAR(100) not null,
   JOB_BRIEF            VARCHAR(500),
   EMOLUMENT            VARCHAR(100),
   BENEFIT              VARCHAR(200),
   NUM_PERSONS          VARCHAR(100),
   IS_FULL_TIME         TINYINT(1),
   EDUCATION_REQ        VARCHAR(50),
   EXPERIENCE_REQ       VARCHAR(50),
   RESPONSIBILITY       LONGTEXT,
   REQUIREMENT          LONGTEXT,
   WORK_ADDRESS         VARCHAR(200),
   TELEPHONE            VARCHAR(80),
   EMAIL                VARCHAR(200),
   ZIP_CODE             VARCHAR(40),
   CREATE_TIME          DATETIME not null,
   UPDATE_TIME          DATETIME,
   primary key (JOB_OFFER_ID)
);

/*==============================================================*/
/* Table: OS_PERSONAL_CREDIT                                    */
/*==============================================================*/
create table OS_PERSONAL_CREDIT
(
   PERSONAL_CREDIT_ID   INTEGER not null auto_increment,
   NAME                 VARCHAR(100),
   ID_CARD              VARCHAR(100),
   STATUS               VARCHAR(20),
   CREATE_TIME          DATETIME not null,
   UPDATE_TIME          DATETIME,
   primary key (PERSONAL_CREDIT_ID)
);

/*==============================================================*/
/* Table: OS_PERSONAL_CREDIT_ITEM                               */
/*==============================================================*/
create table OS_PERSONAL_CREDIT_ITEM
(
   ITEM_ID              INTEGER not null auto_increment,
   PERSONAL_CREDIT_ID   INTEGER not null,
   COMPANY_NAME         VARCHAR(100),
   START_DATE           DATE,
   END_DATE             DATE,
   STATUS               VARCHAR(20),
   primary key (ITEM_ID)
);

/*==============================================================*/
/* Table: OS_PROJECT_DECLARE_APPLY                              */
/*==============================================================*/
create table OS_PROJECT_DECLARE_APPLY
(
   APPLY_ID             INTEGER not null auto_increment,
   COMPANY_NAME         VARCHAR(100),
   SERVICE_ID           INTEGER,
   SERVICE_TYPE         VARCHAR(100),
   REMARK               TEXT,
   CONTACT_NAME         VARCHAR(100),
   CONTACT_TELEPHONE    VARCHAR(40),
   ATTACHMENT_FILE_NAME VARCHAR(100),
   ATTACHMENT_FILE_ID   VARCHAR(100),
   APPLIER_ID           INTEGER,
   APPLIER_NAME         VARCHAR(100),
   CREATE_TIME          DATETIME not null,
   UPDATE_TIME          DATETIME,
   primary key (APPLY_ID)
);

/*==============================================================*/
/* Table: OS_PROJECT_DECLARE_TEMPLATE                           */
/*==============================================================*/
create table OS_PROJECT_DECLARE_TEMPLATE
(
   TEMPLATE_ID          INTEGER not null auto_increment,
   SERVICE_TYPE         VARCHAR(100) not null,
   PUBLISHER_DEPT_ID    VARCHAR(100),
   PUBLISHER_DEPT       INTEGER,
   PUBLISHER            VARCHAR(100),
   PUBLISH_TIME         DATETIME,
   CONTENT              LONGTEXT,
   ATTACHMENT_FILE_NAME VARCHAR(100),
   ATTACHMENT_FILE_ID   VARCHAR(100),
   CREATE_TIME          DATETIME not null,
   UPDATE_TIME          DATETIME,
   primary key (TEMPLATE_ID)
);

/*==============================================================*/
/* Table: OS_QUALITY_SERVICE                                    */
/*==============================================================*/
create table OS_QUALITY_SERVICE
(
   SERVICE_ID           INTEGER not null auto_increment,
   SERVICE_TYPE         VARCHAR(100) not null,
   PUBLISHER_DEPT_ID    VARCHAR(100),
   PUBLISHER_DEPT       INTEGER,
   PUBLISHER            VARCHAR(100),
   PUBLISH_TIME         DATETIME,
   CONTENT              LONGTEXT,
   ATTACHMENT_FILE_NAME VARCHAR(100),
   ATTACHMENT_FILE_ID   VARCHAR(100),
   CREATE_TIME          DATETIME not null,
   UPDATE_TIME          DATETIME,
   primary key (SERVICE_ID)
);

/*==============================================================*/
/* Table: OS_QUALITY_SERVICE_APPLY                              */
/*==============================================================*/
create table OS_QUALITY_SERVICE_APPLY
(
   APPLY_ID             INTEGER not null auto_increment,
   COMPANY_NAME         VARCHAR(100),
   SERVICE_ID           INTEGER,
   SERVICE_TYPE         VARCHAR(100),
   REMARK               TEXT,
   CONTACT_NAME         VARCHAR(100),
   CONTACT_TELEPHONE    VARCHAR(40),
   ATTACHMENT_FILE_NAME VARCHAR(100),
   ATTACHMENT_FILE_ID   VARCHAR(100),
   APPLIER_ID           INTEGER,
   APPLIER_NAME         VARCHAR(100),
   CREATE_TIME          DATETIME not null,
   UPDATE_TIME          DATETIME,
   primary key (APPLY_ID)
);

/*==============================================================*/
/* Table: OS_TECH_SERVICE_APPLY                                 */
/*==============================================================*/
create table OS_TECH_SERVICE_APPLY
(
   APPLY_ID             INTEGER not null auto_increment,
   APPLY_TYPE           VARCHAR(40),
   SPEC_ID              VARCHAR(100),
   SPEC_NAME            VARCHAR(500),
   REMARK               TEXT,
   SERVICE_DURATION     INTEGER comment '单位为月',
   SERVICE_START        DATETIME,
   SERVICE_END          DATETIME,
   AMOUNT               INTEGER,
   PRICE                INTEGER,
   STATUS               VARCHAR(20),
   COMPANY_NAME         VARCHAR(100),
   CONTACT_NAME         VARCHAR(100),
   CONTACT_MOBILE       VARCHAR(40),
   APPLIER_ID           INTEGER,
   APPLIER_NAME         VARCHAR(100),
   EXTRA_DATA           TEXT comment 'JSON',
   CREATE_TIME          DATETIME not null,
   UPDATE_TIME          DATETIME,
   primary key (APPLY_ID)
);

/*==============================================================*/
/* Table: OS_TRAINING                                           */
/*==============================================================*/
create table OS_TRAINING
(
   TRAINING_ID          INTEGER not null auto_increment,
   TITLE                VARCHAR(100) not null,
   TRAINING_DATE        DATETIME,
   TRAINING_TIME        VARCHAR(100),
   ADDRESS              VARCHAR(100),
   PUBLISHER_DEPT_ID    VARCHAR(100),
   PUBLISHER_DEPT       INTEGER,
   PUBLISHER            VARCHAR(100),
   PUBLISH_TIME         DATETIME,
   CONTENT              LONGTEXT,
   ATTACHMENT_FILE_NAME VARCHAR(100),
   ATTACHMENT_FILE_ID   VARCHAR(100),
   CREATE_TIME          DATETIME not null,
   UPDATE_TIME          DATETIME,
   primary key (TRAINING_ID)
);
