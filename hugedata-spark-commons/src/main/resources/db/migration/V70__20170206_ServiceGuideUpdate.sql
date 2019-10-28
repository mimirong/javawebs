alter table GA_SERVICE_GUIDE add IS_INTERNAL TINYINT(1) not null default 0;

insert into ga_service_guide (dept_code, dept_name, guide_name, is_internal, create_time)
values ('1', '办公室', 			'其它事项', 1, sysdate()),
       ('2', '国土规划局', 		'其它事项', 1, sysdate()),
       ('3', '工程建设局', 		'其它事项', 1, sysdate()),
       ('4', '社会事务局', 		'其它事项', 1, sysdate()),
       ('5', '招商合作局', 		'其它事项', 1, sysdate()),
       ('6', '财政分局', 		'其它事项', 1, sysdate()),
       ('7', '办公室', 			'其它事项', 1, sysdate()),
       ('8', '党群纪检绩效办', 	'其它事项', 1, sysdate())

