<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.hualu.main.java.util.Hualu"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript">
		$(function() {
			$("#tableContainer").load(host + "recordTaskList/", function() {
				dispDataTable("table");
				$(".redirectHolder").hide();
			});
		});
		function openRecordModal(id) {
			$("#modalContainer").load(host + "recordTask/" + id + "/");
		}
		</script>
	</head>
	<body>
		<div class="panel panel-primary">
			<div class="panel-heading">
				<i class="icon-table"></i>&nbsp;自检记录
			</div>
			<div class="panel-body">
				<div id="tableContainer"></div>
			</div>
		</div>
		<div id="modalContainer"><!-- .modal --></div>
	</body>
</html>