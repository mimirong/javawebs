delete from schema_version where version_rank = 8;

alter table OS_QUALITY_SERVICE_APPLY drop column STATUS;

alter table OS_PROJECT_DECLARE_APPLY drop column STATUS;

alter table OS_INTELLECTUAL_PROPERTY_APPLY drop column STATUS;

alter table GA_FINANCING_APPLICATION drop column BUSINESS_LICENCE_FILENAME;
alter table GA_FINANCING_APPLICATION drop column APPLICATION_FORM_FILENAME;
alter table GA_FINANCING_APPLICATION drop column REMARK;
