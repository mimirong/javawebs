/*==============================================================*/
/* Table: PT_CATEGORY                                           */
/*==============================================================*/
create table PT_CATEGORY
(
   CATEGORY_ID          VARCHAR(100) not null,
   CATEGORY_NAME        VARCHAR(100),
   IS_INTERNAL          TINYINT(1),
   IS_EDITABLE          TINYINT(1),
   CREATE_TIME          DATETIME not null,
   primary key (CATEGORY_ID)
);

/*==============================================================*/
/* Table: PT_ARTICLE                                            */
/*==============================================================*/
create table PT_ARTICLE
(
   ARTICLE_ID           INTEGER not null auto_increment,
   CATEGORY_ID          VARCHAR(100),
   TITLE                VARCHAR(200) not null,
   BRIEF                VARCHAR(200),
   CONTENT              LONGTEXT,
   AUTHOR               VARCHAR(40),
   SOURCE               VARCHAR(200),
   PUBLISHER_USER_ID    INTEGER,
   PUBLISHER_NAME       VARCHAR(40),
   COVER_FILE_ID        VARCHAR(100),
   COVER_FILE_NAME      VARCHAR(100),
   IS_VISIBLE           TINYINT(1),
   PUBLISH_TIME         DATETIME,
   CREATE_TIME          DATETIME not null,
   UPDATE_TIME          DATETIME,
   EXTRA_DATA           TEXT,
   primary key (ARTICLE_ID)
);

create index IX_PT_ARTICLE_CATID_CRTIME on PT_ARTICLE (CATEGORY_ID, CREATE_TIME);
create index IX_PT_ARTICLE_CATID on PT_ARTICLE (CATEGORY_ID);

/*==============================================================*/
/* Table: PT_ARTICLE_IMAGE                                      */
/*==============================================================*/
create table PT_ARTICLE_IMAGE
(
   IMAGE_ID             INTEGER not null auto_increment,
   ARTICLE_ID           VARCHAR(100),
   CATEGORY_ID          VARCHAR(100),
   FILE_ID              VARCHAR(100),
   FILE_NAME            VARCHAR(100),
   PREVIEW_FILE_ID      VARCHAR(100),
   PREVIEW_FILE_NAME    VARCHAR(100),
   REMARK               VARCHAR(300),
   SORT_INDEX           DECIMAL(5),
   CREATE_TIME          DATETIME not null,
   primary key (IMAGE_ID)
);

/*==============================================================*/
/* Table: PT_ARTICLE_ATTACHMENT                                 */
/*==============================================================*/
create table PT_ARTICLE_ATTACHMENT
(
   ATTACHMENT_ID        INTEGER not null auto_increment,
   ARTICLE_ID           VARCHAR(100),
   CATEGORY_ID          VARCHAR(100),
   FILE_ID              VARCHAR(100),
   FILE_NAME            VARCHAR(100),
   REMARK               VARCHAR(300),
   SORT_INDEX           DECIMAL(5),
   CREATE_TIME          DATETIME not null,
   primary key (ATTACHMENT_ID)
);

