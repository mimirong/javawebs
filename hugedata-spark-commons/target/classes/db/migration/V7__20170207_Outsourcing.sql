alter table OS_QUALITY_SERVICE_APPLY add (
   APPROVE_REMARK       VARCHAR(500)
);

alter table OS_PROJECT_DECLARE_APPLY add (
   APPROVE_REMARK       VARCHAR(500)
);

alter table OS_INTELLECTUAL_PROPERTY_APPLY add (
   APPROVE_REMARK       VARCHAR(500)
);

alter table OS_TECH_SERVICE_APPLY add (
   APPROVE_REMARK       VARCHAR(500)
);

alter table OS_COMPANY_FEEDBACK add (
   CREATE_TIME          DATETIME
);

alter table OS_COMPANY_INFO add (
   LOGO_FILE_ID         VARCHAR(80),
   LOGO_FILENAME        VARCHAR(100)
);

alter table OS_TRAINING drop column PUBLISHER_DEPT_ID;
alter table OS_TRAINING drop column PUBLISHER_DEPT;
alter table OS_TRAINING add (
   PUBLISHER_DEPT_ID    INTEGER,
   PUBLISHER_DEPT       VARCHAR(100),
   TRAINING_START_TIME  VARCHAR(100),
   TRAINING_END_TIME    VARCHAR(100)
);

alter table GA_REPORT_SUBMIT add (
   REPORT_FILE_NAME     VARCHAR(200)
);

