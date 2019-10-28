alter table PT_TECH_IMAGE modify column FILE_NAME varchar(100);
alter table PT_TECH_IMAGE modify column PREVIEW_FILE_NAME varchar(100);
alter table PT_TECH_IMAGE add title varchar(100);
alter table PT_TECH_IMAGE add brief varchar(200);