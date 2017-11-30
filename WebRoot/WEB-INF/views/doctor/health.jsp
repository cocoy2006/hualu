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
			$("#healthTaskRunningTableContainer").load(host + "healthTaskList/1/", function() {
				dispDataTable("table");
			});
			$("#healthTaskDoneTableContainer").load(host + "healthTaskList/2/", function() {
				dispDataTable("table");
			});
			$("#healthTaskDraftTableContainer").load(host + "healthTaskList/3/", function() {
				dispDataTable("table");
			});
		});
		function openHealthModal(doctorHealthTaskId, mode) {
			$("#modalContainer").load(host + "healthTask/" + doctorHealthTaskId + "/", function() {
				if(mode == 2) {
					$("textarea[name='advice']").attr("readonly", "readonly");
					$("button[name='update']").remove();
				}
				modal("health");
			});
		}
		</script>
	</head>
	<body>
		<div class="panel panel-primary">
			<div class="panel-heading">
				<i class="icon-table"></i>&nbsp;健康咨询
			</div>
			<div class="panel-body">
				<!-- tabs left -->
				<div class="tabbable">
					<ul class="nav nav-tabs nav-pills">
						<li class="active">
							<a href="#1" data-toggle="tab">
								待处理
							</a>
						</li>
						<li>
							<a href="#3" data-toggle="tab">
								草稿
							</a>
						</li>
						<li>
							<a href="#2" data-toggle="tab">
								已完成
							</a>
						</li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="1">
							<div id="healthTaskRunningTableContainer"></div>
						</div>
						<div class="tab-pane" id="3">
							<div id="healthTaskDraftTableContainer"></div>
						</div>
						<div class="tab-pane" id="2">
							<div id="healthTaskDoneTableContainer"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="modalContainer"><!-- .modal --></div>
	</body>
</html>