delete from schema_version where version_rank = 4;

/* GA_FINANCING_GUIDE */
alter table GA_FINANCING_GUIDE drop column PUBLISHER;
alter table GA_FINANCING_GUIDE drop column PUBLISH_DEPT;

/* GA_PARK_QUIT_APPLICATION */
alter table GA_PARK_QUIT_APPLICATION drop column QUIT_TIME; 
alter table GA_PARK_QUIT_APPLICATION drop column APPROVE_COMMENT;

/* GA_PARK_QUIT_GUIDE */
alter table GA_PARK_QUIT_GUIDE drop column CREATOR_DEPT; 

/* GA_PM_BILL */
alter table GA_PM_BILL drop column USAGE_AMOUNT;

/* GA_REPORT_TEMPLATE */
alter table GA_REPORT_TEMPLATE drop column PUBLISH_DEPT_ID;
alter table GA_REPORT_TEMPLATE drop column PUBLISH_DEPT_NAME;
alter table GA_REPORT_TEMPLATE drop column PUBLISHER;

