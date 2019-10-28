
/*==============================================================*/
/* Table: OS_EXPERTS_LIST                                       */
/*==============================================================*/
create table OS_EXPERTS_LIST
(
   EXPERT_ID            INTEGER not null auto_increment comment '专家ID',
   NAME                 VARCHAR(40) not null comment '姓名',
   POSITION             VARCHAR(300) comment '职位',
   JOB_TITLE            VARCHAR(300) comment '职称',
   DEPT                 VARCHAR(300) comment '部门',
   PROFESSION_FIELD_ID  INTEGER comment '专业领域',
   EXPERT_FIELD         VARCHAR(300) comment '擅长领域',
   EXPERT_TITLE         VARCHAR(80) comment '专家称号',
   BRIEF                VARCHAR(500) comment '个人简介',
   SEX                  TINYINT(1) comment '性别',
   ETHNIC               VARCHAR(30) comment '民族',
   NATIVE_PLACE         VARCHAR(30) comment '籍贯',
   EDUCATION            VARCHAR(30) comment '学历',
   FILE_ID              VARCHAR(80) comment '文件ID',
   PREVIEW_FILE_ID      VARCHAR(80) comment '缩略图文件ID',
   SORT_INDEX           INTEGER comment '排序',
   IS_VISIBLE           TINYINT(1) comment '是否显示',
   CREATE_TIME          DATETIME not null comment '创建时间',
   UPDATE_TIME          DATETIME comment '更新时间',
   primary key (EXPERT_ID)
);