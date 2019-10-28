<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<style type="text/css">
		article,aside,details,figcaption,figure,footer,header,hgroup,menu,nav,section{display:block;}
		body,button,input,select,textarea{font:16px/1 "黑体",Tahoma,Helvetica,Arial,"\5b8b\4f53",sans-serif;color:#333;}
		a{text-decoration:none;color:#333;blr:expression(this.onFocus=this.blur());outline:none;}
		a:focus{outline:none;color:#0f88eb;}
		a:hover{text-decoration:none!important;color:#0f88eb;}
		img{width:100%;}
		body{background:#fff;position:relative;}
		.header-top{height:44px;background-color:#0f88eb;position:fixed;width:100%;top:0;line-height:44px;}
		.header-top .reback img{width:21px;}
		.header-top h3.title{text-align:center;font-size:18px;color:#fff;}
		.header-top .reback{position:absolute;left:10px;top:12px;}
		.articel{margin:0px 15px 20px;}
		.articel h2.tit{font-size:16px;line-height:24px;padding:15px 0;text-align:center;}
		.articel h3.tit{border-left:2px solid #0f88eb;padding-left:15px;color:#999;font-size:12px;margin-bottom:12px;}
		.articel h3.tit span{color:#0f88eb;margin-right:10px;}
		.articel .con{line-height:24px;}
		.articel .con p{margin-bottom:20px;font-size:14px;color:#666;}
		.articel .download{font-size:12px;color:#333;margin-top:-10px;}
		.articel .download img{width:14px;vertical-align:top;margin-left:8px;}
		.articel img{ width: 100%; margin: 0 auto; max-width:800px;}
	</style>
</head>

<body>
<div class="articel articel-notice">
	<h2 class="tit">${guide.title}</h2>
	<h3 class="tit"><span></span>
		<fmt:formatDate value="${guide.publishTime}" type="date"/>
	</h3>
	<div class="con">
		 ${guide.content}
	</div>
</div>
</body>
</html>
