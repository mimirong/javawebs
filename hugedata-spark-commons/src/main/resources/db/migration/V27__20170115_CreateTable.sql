/*==============================================================*/
/* Table: IT_MESSAGE                                            */
/*==============================================================*/
create table IT_MESSAGE
(
   MESSAGE_ID           INTEGER not null auto_increment,
   MESSAGE_TYPE         VARCHAR(20) not null comment 'MAIL:领导信箱 CONSULT:咨询 COMPLAINT:投诉',
   USER_ID              INTEGER,
   NAME                 VARCHAR(40),
   USER_TYPE            VARCHAR(20),
   ID_CARD              VARCHAR(40),
   MOBILE               VARCHAR(80),
   EMAIL                VARCHAR(300),
   ADDRESS              VARCHAR(300),
   TITLE                VARCHAR(300),
   CONTENT              TEXT,
   PASSWORD             VARCHAR(40),
   STATUS               VARCHAR(20) comment 'SUBMITTED:已提交 REPLIED:已回复',
   IS_DELETED           TINYINT(1) not null,
   SUBMIT_TIME          DATETIME not null,
   REPLY_TIME           DATETIME,
   primary key (MESSAGE_ID)
);

/*==============================================================*/
/* Table: IT_SURVEY                                             */
/*==============================================================*/
create table IT_SURVEY
(
   SURVEY_ID            INTEGER not null auto_increment,
   TITLE                VARCHAR(200) not null,
   BRIEF                VARCHAR(1000),
   SOURCE               VARCHAR(100),
   START_TIME           DATETIME not null,
   END_TIME             DATETIME not null,
   SUBMIT_COUNT         INTEGER,
   CREATOR_USER_ID      INTEGER,
   CREATOR_NAME         VARCHAR(80),
   CREATE_TIME          DATETIME not null,
   UPDATE_TIME          DATETIME,
   primary key (SURVEY_ID)
);

/*==============================================================*/
/* Table: IT_SURVEY_QUESTION                                    */
/*==============================================================*/
create table IT_SURVEY_QUESTION
(
   QUESTION_ID          INTEGER not null auto_increment,
   SURVEY_ID            INTEGER not null,
   SORT_INDEX           INTEGER,
   QUESTION_TEXT        VARCHAR(200),
   IS_REQUIRED          TINYINT(1),
   QUESTION_TYPE        VARCHAR(20) comment 'RADIO:单选 CHECK:多选 TEXT:文本',
   CONFIG_DATA          TEXT comment 'JSON，如选项列表信息',
   SUMMARY_DATA         TEXT,
   primary key (QUESTION_ID)
);

/*==============================================================*/
/* Table: IT_SURVEY_QUESTION_RESULT                             */
/*==============================================================*/
create table IT_SURVEY_QUESTION_RESULT
(
   RESULT_ID            INTEGER not null auto_increment,
   QUESTION_ID          INTEGER not null,
   SURVEY_ID            INTEGER not null,
   RESULT               VARCHAR(200),
   RESULT_TEXT          TEXT,
   RESULT_DATA          TEXT,
   primary key (RESULT_ID)
);

/*==============================================================*/
/* Index: IX_ID_QUES_RESULT_SURVEY_ID                           */
/*==============================================================*/
create index IX_ID_QUES_RESULT_SURVEY_ID on IT_SURVEY_QUESTION_RESULT
(
   SURVEY_ID
);

/*==============================================================*/
/* Index: IX_ID_QUES_RESULT_QUESTION_ID                         */
/*==============================================================*/
create index IX_ID_QUES_RESULT_QUESTION_ID on IT_SURVEY_QUESTION_RESULT
(
   QUESTION_ID
);