CREATE TABLE `pm_doc_type` (
  `TYPE_ID` int(3) NOT NULL AUTO_INCREMENT,
  `TYPE_NAME` varchar(59) NOT NULL,
  PRIMARY KEY (`TYPE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=utf8;

insert  into `pm_doc_type`(`TYPE_ID`,`TYPE_NAME`) values (101,'立项批复文件'),(102,'可研批复文件'),(103,'初设批复文件'),(104,'国土批复文件'),(105,'规划批复文件'),(106,'环评批复文件'),(107,'交评批复文件'),(108,'中标通知书2'),(109,'施工合同签订'),(110,'监理合同签订'),(111,'施工许可'),(112,'质安监入场');