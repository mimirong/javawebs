<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- 标题 --%>
<c:if test="${param.title != null}">
	<div style="font-size:20px;">${param.title}</div>
</c:if>


<%-- INFO MESSAGE PANEL --%>
<div id="infoMessage" class="alert alert-success" role="alert" style="margin-top:12px; display:none;">
	<button type="button" class="close" data-dismiss="alert">
		<span aria-hidden="true">&times;</span>
		<span class="sr-only">Close</span>
	</button>
	<div>${infoMessage}</div>
</div>

<c:if test="${infoMessage != null}">
	<script type="text/javascript">
		$('#infoMessage').css('display', 'block');	
	</script>
</c:if>


<%-- ERROR MESSAGE PANEL --%>
<div id="errorMessage" class="alert alert-warning" role="alert" style="margin-top:12px; display:none;">
	<button type="button" class="close" data-dismiss="alert">
		<span aria-hidden="true">&times;</span>
		<span class="sr-only">Close</span>
	</button>
	<div>${errorMessage}</div>
</div>

<c:if test="${errorMessage != null}">
	<script type="text/javascript">
		$('#errorMessage').css('display', 'block');	
	</script>
</c:if>

<%-- MARGIN --%>

<div style="margin-bottom:12px;">
</div>
