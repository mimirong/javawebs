delete from schema_version where version_rank = 13;

/* OS_COMPANY_INFO */
alter table OS_COMPANY_INFO drop column DEMO_FILE_INFO;
