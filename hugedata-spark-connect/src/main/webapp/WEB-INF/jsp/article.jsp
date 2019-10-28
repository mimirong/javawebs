<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<style type="text/css">
        *{ margin: 0; padding: 0; }
        body,button,input,select,textarea{font:14px/1 "黑体",Tahoma,Helvetica,Arial,"\5b8b\4f53",sans-serif;color:#222;}
        a{text-decoration:none;color:#222;blr:expression(this.onFocus=this.blur());outline:none;}
        a:focus{outline:none;color:#157dfb;}
        a:hover{text-decoration:none!important;color:#157dfb;}
        img{width:100%;}
        body{background:#f8f8f8;word-wrap: break-word;  word-break: normal;}
        .articel{background-color: #fff; margin-top:10px; padding: 0 15px 20px;}
        .articel h2.tit{font-size:16px;line-height:22px;padding:10px 0 5px;text-align:center;}
        .articel h3.tit{color:#999;font-size:13px;margin-bottom:15px;text-align:center; font-weight: normal;}
        .articel h3.tit span{margin-left:10px;}
        .articel .con{line-height:20px;}
        .articel .con p{font-size:14px;text-indent: 28px;}
        .articel img{ width: 100%; margin: 0 auto 10px; max-width:800px; border-radius: 4px;}
		
		#content p { line-height:2em; margin-top:1.5em; }
		#content b, #content strong { font-weight:bold; }
		#content table { border-top:solid 1px #888; border-left:solid 1px #888; }
		#content table td { border-bottom:solid 1px #888; border-right:solid 1px #888; padding:4px 8px; }
		#content ul { list-style:disc; padding-left:24px; margin-top:12px; } 
		#content ol { list-style:decimal; padding-left:24px; margin-top:12px; } 
		#content a { color:#0e78ee; } 
		#content em { font-style:italic; }
	</style>
</head>
<body>
	<div class="articel articel-notice">
	    <div class="articel">
	        <h2 class="tit">${article.title}</h2>
	        <h3 class="tit">${article.source}<span><fmt:formatDate value="${article.publishTime}" pattern="yyyy-MM-dd" /></span></h3>
	        <div class="con" id="content">
				${article.content}
	        </div>
	    </div>
	</div>
</body>
</html>
