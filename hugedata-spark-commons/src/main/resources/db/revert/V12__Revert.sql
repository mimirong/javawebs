delete from schema_version where version_rank = 12;

/* OS_COMPANY_INFO */
alter table OS_COMPANY_INFO drop column DEMO_FILENAME;

/* OS_JOB_OFFER */
alter table OS_JOB_OFFER drop column COMPANY_ID;
