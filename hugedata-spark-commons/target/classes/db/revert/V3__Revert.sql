delete from schema_version where version_rank = 3;

drop table GA_PM_BILL;
drop table GA_PM_STANDARD;
drop table GA_PM_REFUND;

alter table GA_PARK_JOIN_APPLICATION drop column APPROVE_COMMENT; 

drop index IX_ORG_INFO_REF_ORG_ID on UC_ORG_INFO;
drop index IX_DEPT_INFO_REF_DEPT_ID on UC_DEPT_INFO;
drop index IX_USER_INFO_REF_USER_ID on UC_USER_INFO;

alter table GA_FINANCING_APPLICATION drop column STAFF_SIZE;

alter table GA_PARK_JOIN_GUIDE drop column CREATOR_DEPT; 