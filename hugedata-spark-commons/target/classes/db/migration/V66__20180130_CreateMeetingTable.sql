/*==============================================================*/
/* Table: OS_EXPERTS_LIST                                       */
/*==============================================================*/
alter TABLE OS_EXPERTS_LIST add ORGANIZATION VARCHAR(300) comment '所属单位';


/*==============================================================*/
/* Table: OS_MEETING_TRAINING                                      */
/*==============================================================*/
create table OS_MEETING_TRAINING
(
  TRAINING_ID          INTEGER not null auto_increment comment 'ACHIEVE_ID',
  NAME                 VARCHAR(100) not null comment '培训名称',
  TRAINING_TYPE        VARCHAR(50) comment '课程类型',
  ADAPT_OBJECT         VARCHAR(100) comment '适用对象',
  REGISTRATION_TIME    DATETIME comment '报名开始时间',
  REGISTRATION_DEADLINE DATETIME comment '报名截止时间',
  TRAINING_START_TIME  DATETIME comment '培训开始时间',
  TRAINING_END_TIME    DATETIME comment '培训结束时间',
  ADDRESS              VARCHAR(100) comment '培训地点',
  REGISTRATION_WAY     VARCHAR(20) comment '报名方式',
  MAX_PLAYERS          INTEGER comment '限制人数',
  TRAINING_WAY         VARCHAR(50) comment '课程形式',
  PRICE                INTEGER comment '课程价格',
  FILE_ID              VARCHAR(100) comment '图片文件ID',
  FILE_NAME            VARCHAR(100) comment '图片文件名',
  PREVIEW_FILE_ID      VARCHAR(100) comment '缩略图文件ID',
  PREVIEW_FILE_NAME    VARCHAR(100) comment '缩略图文件名',
  BRIEF                VARCHAR(1000) comment '课程内容',
  BRIEF_FILE_ID        VARCHAR(100) comment '课程内容附件文件ID',
  BRIEF_FILE_NAME      VARCHAR(100) comment '课程内容附件文件名',
  ANNOUNCEMENTS        VARCHAR(1000) comment '注意事项',
  ANNO_FILE_ID         VARCHAR(100) comment '注意事项附件文件ID',
  ANNO_FILE_NAME       VARCHAR(100) comment '注意事项附件文件名',
  CONTACT              VARCHAR(100) comment '联系人',
  PHONE                VARCHAR(80) comment '联系电话',
  MOBILE               VARCHAR(80) comment '联系人手机',
  EMAIL                VARCHAR(80) comment '联系人邮箱',
  SORT_INDEX           INTEGER comment '排序',
  IS_VISIBLE           TINYINT(1) comment '是否显示',
  CREATE_TIME          DATETIME not null comment '创建时间',
  UPDATE_TIME          DATETIME comment '更新时间',
  primary key (TRAINING_ID)
);