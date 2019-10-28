/* OS_COMPANY_INFO */
alter table OS_COMPANY_INFO add (
   DEMO_FILENAME        VARCHAR(100)
);

/* OS_JOB_OFFER */
alter table OS_JOB_OFFER add (
   COMPANY_ID           INTEGER
);
