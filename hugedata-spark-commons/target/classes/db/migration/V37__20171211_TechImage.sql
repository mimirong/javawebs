/*==============================================================*/
/* Table: PT_HOME_BANNER                                        */
/*==============================================================*/
create table PT_TECH_IMAGE
(
   IMAGE_ID            INTEGER not null auto_increment,
   NAME                 VARCHAR(80),
   FILE_ID              VARCHAR(80),
   FILE_NAME            char(10),
   PREVIEW_FILE_ID      VARCHAR(80),
   PREVIEW_FILE_NAME    char(10),
   LINK_URL             VARCHAR(200),
   SORT_INDEX           INTEGER,
   IS_VISIBLE           TINYINT(1),
   CREATE_TIME          DATETIME not null,
   primary key (IMAGE_ID)
);
