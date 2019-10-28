alter table ap_service_info
    add CURRENT_USER_ID         INTEGER,
    add CURRENT_USER_NAME       VARCHAR(100),
    add ACCEPT_NOTICE_FILE_ID   VARCHAR(100),
    add ACCEPT_NOTICE_FILE_NAME VARCHAR(100),
    add FINISH_DOC_FILE_ID      VARCHAR(100),
    add FINISH_DOC_FILE_NAME    VARCHAR(100),
    add FINISH_TIME             DATETIME;

/*==============================================================*/
/* Table: AP_SERVICE_PROCESS                                    */
/*==============================================================*/
create table AP_SERVICE_PROCESS
(
   PROCESS_ID           INTEGER not null auto_increment,
   SERVICE_ID           INTEGER,
   SERVICE_STATUS       VARCHAR(40),
   SEQ                  INTEGER,
   USER_ID              INTEGER,
   USER_NAME            VARCHAR(100),
   DEPT_CODE            VARCHAR(100),
   DEPT_NAME            VARCHAR(100),
   STATUS               VARCHAR(40),
   REMARK               TEXT,
   ATTACHMENTS_STATUS   TEXT comment 'JSON',
   CREATE_TIME          DATETIME,
   primary key (PROCESS_ID)
);

create index IX_APSERVPROC_SERVICEID on AP_SERVICE_PROCESS (SERVICE_ID);
