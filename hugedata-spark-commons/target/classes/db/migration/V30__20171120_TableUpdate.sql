alter table IT_SURVEY add  IS_PUBLISHED   TINYINT(1) not null;



/*==============================================================*/
/* Table: IT_SURVEY_RESULT                                      */
/*==============================================================*/
create table IT_SURVEY_RESULT
(
   SURVEY_RESULT_ID     INTEGER not null auto_increment,
   SURVEY_ID            INTEGER not null,
   SUBMITTER_USER_ID    INTEGER,
   SUBMITTER_NAME       VARCHAR(80),
   CREATE_TIME          DATETIME not null,
   primary key (SURVEY_RESULT_ID)
);
create index IX_SURVEY_RESULT_SURVEY_ID on IT_SURVEY_RESULT (SURVEY_ID);
create index IX_SURVEY_RESULT_USER_ID on IT_SURVEY_RESULT (SUBMITTER_USER_ID);

/*==============================================================*/
/* Table: IT_SURVEY_OPTION                                      */
/*==============================================================*/
create table IT_SURVEY_OPTION
(
   OPTION_ID            INTEGER not null auto_increment,
   SURVEY_ID            INTEGER not null,
   QUESTION_ID          INTEGER  not null,
   OPTION_CODE          INTEGER,
   OPTION_TEXT          VARCHAR(30),
   primary key (OPTION_ID)
);
