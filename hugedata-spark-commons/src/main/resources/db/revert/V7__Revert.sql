delete from schema_version where version_rank = 7;

alter table OS_QUALITY_SERVICE_APPLY drop column APPROVE_REMARK;

alter table OS_PROJECT_DECLARE_APPLY drop column APPROVE_REMARK;

alter table OS_INTELLECTUAL_PROPERTY_APPLY drop column APPROVE_REMARK;

alter table OS_TECH_SERVICE_APPLY drop column APPROVE_REMARK;

alter table OS_COMPANY_FEEDBACK drop column CREATE_TIME;

alter table OS_COMPANY_INFO drop column LOGO_FILE_ID;
alter table OS_COMPANY_INFO drop column LOGO_FILENAME;

alter table OS_TRAINING drop column TRAINING_START_TIME;
alter table OS_TRAINING drop column TRAINING_END_TIME;

alter table GA_REPORT_SUBMIT drop column REPORT_FILE_NAME;
