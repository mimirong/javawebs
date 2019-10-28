create table PT_HOME_IMAGE
(
   IMAGE_ID             INTEGER not null auto_increment,
   NAME                 VARCHAR(100),
   TITLE                VARCHAR(100),
   BRIEF                VARCHAR(200),
   CATEGORY_ID          VARCHAR(50),
   FILE_ID              VARCHAR(100),
   FILE_NAME            VARCHAR(100),
   PREVIEW_FILE_ID      VARCHAR(100),
   PREVIEW_FILE_NAME    VARCHAR(100),
   LINK_URL             VARCHAR(200),
   SORT_INDEX           INTEGER,
   IS_VISIBLE           TINYINT(1),
   CREATE_TIME          DATETIME not null,
   primary key (IMAGE_ID)
);
