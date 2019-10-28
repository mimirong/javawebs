/* 将“征信体系服务”改为“诚信体系服务” */

UPDATE mp_service_introduce
set TITLE = '诚信体系服务',BRIEF ='诚信体系服务'
where GUIDE_ID = 16;
