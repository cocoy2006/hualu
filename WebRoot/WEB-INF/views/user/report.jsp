<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.hualu.main.java.util.Hualu"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript">
		$(function() {
			loadReportTaskList();
		});
		function loadReportTaskList() {
			$("#tableContainer").load(host + "reportTaskList/", function() {
				dispDataTable("table");
				$(".redirectHolder").hide();
			});
		}
		function openReportModal(id) {
			$("#modalContainer").load(host + "reportTask/" + id + "/", function() {
				$("textarea[name='advice']").attr("disabled", "disabled");
				$("p[name='next']").hide();
				$("p[name='draft']").hide();
			});
		}
		</script>
	</head>
	<body>
		<div class="panel panel-primary">
			<div class="panel-heading">
				<i class="icon-table"></i>&nbsp;综合报告
			</div>
			<div class="panel-body">
				<div id="tableContainer"></div>
			</div>
		</div>
		<div id="modalContainer"><!-- .modal --></div>
	</body>
</html>