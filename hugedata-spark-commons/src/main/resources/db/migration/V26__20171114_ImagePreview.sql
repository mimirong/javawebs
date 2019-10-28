/*==============================================================*/
/* Table: SYS_IMAGE_PREVIEW                                     */
/*==============================================================*/
create table SYS_IMAGE_PREVIEW
(
   PREVIEW_ID           INTEGER not null auto_increment,
   ORIGINAL_FILE_ID     VARCHAR(100),
   PREVIEW_FILE_ID      VARCHAR(100),
   WIDTH                INTEGER,
   HEIGHT               INTEGER,
   PREVIEW_TYPE         VARCHAR(20) not null,
   CREATE_TIME          DATETIME not null,
   primary key (PREVIEW_ID)
);
