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
			$("#reportTaskDoctorProcessingTableContainer").load(host + "reportTaskList/" + ReportTask.DOCTOR_PROCESSING + "/", function() {
				dispDataTable("table");
			});
			$("#reportTaskDoctorDraftTableContainer").load(host + "reportTaskList/" + ReportTask.DOCTOR_DRAFT + "/", function() {
				dispDataTable("table");
			});
			$("#reportTaskDoneTableContainer").load(host + "reportTaskList/" + ReportTask.DONE + "/", function() {
				dispDataTable("table");
			});
		}
		function openReportModal(id, mode) {
			$("#modalContainer").load(host + "reportTask/" + id + "/", function() {
				if(mode == ReportTask.DONE) {
					$("textarea[name='advice']").attr("disabled", "disabled");
					$("p[name='next']").hide();
					$("p[name='draft']").hide();
				}
			});
		}
		function next(id) {
			var advice = $("textarea[name='advice']").val();
			if(advice) {
				$.ajax({
					url: host + "reportTask/next/" + id + "/",
					data: "advice=" + advice,
					dataType: "json",
					type: "POST",
					success: function(data) {
						$(".modal").modal("hide");
					}
				});
			}
		}
		function draft(id) {
			var advice = $("textarea[name='advice']").val();
			if(advice) {
				$.ajax({
					url: host + "reportTask/draft/" + id + "/",
					data: "advice=" + advice,
					dataType: "json",
					type: "POST",
					success: function(data) {
						$(".modal").modal("hide");
					}
				});
			}
		}
		</script>
	</head>
	<body>
		<div class="panel panel-primary">
			<div class="panel-heading">
				<i class="icon-table"></i>&nbsp;综合报告
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
							<a href="#2" data-toggle="tab">
								草稿
							</a>
						</li>
						<li>
							<a href="#3" data-toggle="tab">
								已完成
							</a>
						</li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="1">
							<div id="reportTaskDoctorProcessingTableContainer"></div>
						</div>
						<div class="tab-pane" id="2">
							<div id="reportTaskDoctorDraftTableContainer"></div>
						</div>
						<div class="tab-pane" id="3">
							<div id="reportTaskDoneTableContainer"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="modalContainer"><!-- .modal --></div>
	</body>
</html>