create table OS_TECH_SERVICE_SPEC
(
   SPEC_ID              INTEGER not null auto_increment,
   SPEC_NAME            VARCHAR(500),
   SPEC_TYPE            VARCHAR(50) comment 'COMPUTING:云主机',
   REMARK               VARCHAR(500),
   SPEC_DATA            TEXT,
   PRICE                INTEGER,
   CREATE_TIME          DATETIME,
   UPDATE_TIME          DATETIME,
   primary key (SPEC_ID)
);
