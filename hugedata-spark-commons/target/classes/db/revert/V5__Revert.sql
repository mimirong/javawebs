delete from schema_version where version_rank = 5;

/* GA_SITE_PROOF */
alter table GA_SITE_PROOF drop column PUBLISHER;
alter table GA_SITE_PROOF drop column PUBLISH_DEPT_ID;
alter table GA_SITE_PROOF drop column PUBLISH_DEPT_NAME;

/* GA_APTITUDE */
alter table GA_APTITUDE drop column PUBLISHER;
alter table GA_APTITUDE drop column PUBLISH_DEPT_ID;
alter table GA_APTITUDE drop column PUBLISH_DEPT_NAME;
