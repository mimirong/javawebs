alter table AP_SERVICE_INFO drop column BUSINESS_TITLE;
alter table AP_SERVICE_INFO modify  DEPTCODE INTEGER;
alter table AP_SERVICE_INFO modify  GUIDECODE INTEGER;

alter table UC_USER_INFO add WINDOW_NAME VARCHAR(50);
alter table UC_USER_INFO add DEPT_NAME VARCHAR(50);