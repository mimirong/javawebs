ALTER TABLE pm_doc_type ADD COLUMN DOC_TYPE CHAR(1);
UPDATE pm_doc_type SET doc_type = '1';
INSERT  INTO pm_doc_type (TYPE_ID,TYPE_NAME,DOC_TYPE) VALUES (201,'项目简介','2'),(202,'合同信息','2'),(203,'年度工程计划进度','2'),(204,'质量资料','2'),(205,'安全管理','2'),(206,'工程效果图','2'),(207,'工程录像','2'),(208,'竣工资料','2');
