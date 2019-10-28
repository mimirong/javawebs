/*==============================================================*/
/* Table: OS_EXPERTS_LIST                                       */
/*==============================================================*/
alter TABLE OS_EXPERTS_LIST modify PROFESSION_FIELD_ID  VARCHAR(50) comment '专业领域';
alter TABLE OS_EXPERTS_LIST modify FILE_ID              VARCHAR(100) comment '图片文件ID';
alter TABLE OS_EXPERTS_LIST add FILE_NAME            VARCHAR(100) comment '图片文件名';
alter TABLE OS_EXPERTS_LIST modify PREVIEW_FILE_ID      VARCHAR(100) comment '缩略图文件ID';
alter TABLE OS_EXPERTS_LIST add PREVIEW_FILE_NAME    VARCHAR(100) comment '缩略图文件名';

/*==============================================================*/
/* Table: OS_TECH_ACHIEVE                                       */
/*==============================================================*/
create table OS_TECH_ACHIEVE
(
   ACHIEVE_ID           INTEGER not null auto_increment comment 'ACHIEVE_ID',
   NAME                 VARCHAR(40) not null comment '项目名称',
   PROFESSION_FIELD_ID  VARCHAR(50) comment '专业领域',
   ACHIEVE_TYPE         varchar(50) comment '成果形式',
   INVESTMENT_VOLUME    INTEGER comment '投资额',
   MONETARY_UNIT        varchar(50) comment '货币单位',
   MATURITY_STAGE       varchar(50) comment '投资成熟度',
   DESIRED_EFFECT       VARCHAR(300) comment '预期效果',
   ADAPT_OBJECT         VARCHAR(100) comment '适应对象',
   COOPERATION_WAYS     VARCHAR(100) comment '合作方式',
   CONTACT              VARCHAR(100) comment '联系人',
   PHONE                VARCHAR(80) comment '联系电话',
   BRIEF                VARCHAR(500) comment '方案描述',
   FILE_ID              VARCHAR(100) comment '图片文件ID',
   FILE_NAME            VARCHAR(100) comment '图片文件名',
   PREVIEW_FILE_ID      VARCHAR(100) comment '缩略图文件ID',
   PREVIEW_FILE_NAME    VARCHAR(100) comment '缩略图文件名',
   SORT_INDEX           INTEGER comment '排序',
   IS_VISIBLE           TINYINT(1) comment '是否显示',
   CREATE_TIME          DATETIME not null comment '创建时间',
   UPDATE_TIME          DATETIME comment '更新时间',
   primary key (ACHIEVE_ID)
);
