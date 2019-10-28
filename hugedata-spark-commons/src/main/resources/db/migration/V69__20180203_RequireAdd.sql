CREATE TABLE `os_require_info` (
  `require_id` int(11) NOT NULL AUTO_INCREMENT,
  `require_area` varchar(40) DEFAULT NULL,
  `require_title` varchar(120) DEFAULT NULL,
  `dead_date` date DEFAULT NULL,
  `offer_date` date DEFAULT NULL,
  `hope_price` double DEFAULT NULL,
  `price_unit` varchar(10) DEFAULT NULL,
  `is_chat` char(1) DEFAULT NULL,
  `is_quick` char(1) DEFAULT NULL,
  `require_desc` longtext,
  `key_word` varchar(200) DEFAULT NULL,
  `payment_method` varchar(20) DEFAULT NULL,
  `invoice_type` varchar(20) DEFAULT NULL,
  `freight_payer` varchar(20) DEFAULT NULL,
  `is_cod` char(1) DEFAULT NULL,
  `require_num` int(11) DEFAULT NULL,
  `num_unit` varchar(20) DEFAULT NULL,
  `contact_area` varchar(20) DEFAULT NULL,
  `contact_addr` varchar(200) DEFAULT NULL,
  `contacter` varchar(20) DEFAULT NULL,
  `contact_phone` varchar(20) DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL,
  `publish_time` datetime DEFAULT NULL,
  `deleted` char(1) DEFAULT '0',
  `user_id` int(11) DEFAULT NULL,
  `company_id` int(11) DEFAULT NULL,
  `company_name` varchar(100) DEFAULT NULL,
  `read_num` int(11) DEFAULT '0',
  PRIMARY KEY (`require_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;


CREATE TABLE `os_require_att` (
  `att_id` int(11) NOT NULL AUTO_INCREMENT,
  `require_id` int(11) DEFAULT NULL,
  `file_id` varchar(100) DEFAULT NULL,
  `file_name` varchar(100) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`att_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;