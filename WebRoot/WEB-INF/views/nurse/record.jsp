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
			loadRecordTaskList();
			window.recordTaskTimer = setInterval(function() {
				if(!window.isModalPop) {
					location.reload();
				}
			}, period);
		});
		function loadRecordTaskList() {
			loadNurseProcessingList();
			loadDoneList();
			loadOtherList();
		}
		function loadNurseProcessingList() {
			var columns = [
		            { "data": "id" },
		            { "data": "time" },
		            { "data": "username" },
		            { "data": "id" }
		        ];
			dispDatatableWithPagination(".table3", host + "recordTaskList/3/", columns, "#span3", add3Buttons);
		}
		function loadDoneList() {
			var columns = [
		            { "data": "id" },
		            { "data": "time" },
		            { "data": "username" },
		            { "data": "id" }
		        ];
			dispDatatableWithPagination(".table10", host + "recordTaskList/10/", columns, "#span10", add10Buttons);
		}
		function loadOtherList() {
			var columns = [
		            { "data": "id" },
		            { "data": "time" },
		            { "data": "username" },
		            { "data": "status" },
		            { "data": "id" }
		        ];
			dispDatatableWithPagination(".table99", host + "recordTaskList/99/", columns, "#span99", add99Buttons);
		}
		function add3Buttons() {
			$(".table3 tbody tr").each(function() {
				var td = $(this).children("td").last();
				var id = td.html();
				td.html(buttonOfOpenModal(id, 3, " 处理 "));
			});
		}
		function add10Buttons() {
			$(".table10 tbody tr").each(function() {
				var td = $(this).children("td").last();
				var id = td.html();
				td.html(buttonOfOpenModal(id, 10, " 查看&nbsp;/&nbsp;撤回 "));
			});
		}
		function add99Buttons() {
			$(".table99 tbody tr").each(function() {
				var td = $(this).children("td").last();
				var id = td.html();
				td.html(buttonOfOpenModal(id, 99, " 查看 "));
			});
		}
		function buttonOfOpenModal(id, status, desc) {
			return '<button class="btn btn-success btn-xs" ' +
				'onclick="openRecordModal(' + id + ', ' + status + ')">' + desc + '</button>';
		}
		function openRecordModal(taskid, mode) {
			$("#modalContainer").load(host + "recordTask/" + taskid + "/", function() {
				if(mode == RecordTask.NURSE_PROCESSING) {
					$("p[name='recall']").hide();
				} else if(mode == RecordTask.DONE) {
					$("textarea[name='advice']").attr("disabled", "disabled");
					$("p[name='next']").hide();
					$("p[name='recall']").show();
				} else {
					$("textarea[name='advice']").attr("disabled", "disabled");
					$("p[name='next']").hide();
					$("p[name='recall']").hide();
				}
			});
		}
		</script>
	</head>
	<body>
		<div class="panel panel-primary">
			<div class="panel-heading">
				<i class="icon-table"></i>&nbsp;自检记录
				<!-- <small>[下次刷新时间<span id="nextRefreshDatetime" class="badge"></span>]</small> -->
				<small>[自动刷新周期:3分钟]</small>
				<button class="btn btn-warning btn-xs" onclick="location.reload();">立即刷新</button>
			</div>
			<div class="panel-body">
				<!-- tabs left -->
				<div class="tabbable">
					<ul class="nav nav-tabs nav-pills">
						<li class="active">
							<a href="#3" data-toggle="tab">
								待处理<span id="span3" class="badge"></span>
							</a>
						</li>
						<li>
							<a href="#10" data-toggle="tab">
								已完成<span id="span10" class="badge"></span>
							</a>
						</li>
						<li>
							<a href="#99" data-toggle="tab">
								可查看<span id="span99" class="badge"></span>
							</a>
						</li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="3">
							<table class="table table-striped table-bordered table3" 
								cellspacing="0" width="100%">
								<thead>
									<tr>
										<th>序号</th>
										<th>上传时间</th>
										<th>用户名</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody></tbody>
							</table>
						</div>
						<div class="tab-pane" id="10">
							<table class="table table-striped table-bordered table10" 
								cellspacing="0" width="100%">
								<thead>
									<tr>
										<th>序号</th>
										<th>创建时间</th>
										<th>用户名</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody></tbody>
							</table>
						</div>
						<div class="tab-pane" id="99">
							<table class="table table-striped table-bordered table99" 
								cellspacing="0" width="100%">
								<thead>
									<tr>
										<th>序号</th>
										<th>创建时间</th>
										<th>用户名</th>
										<th>状态</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody></tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="modalContainer"><!-- .modal --></div>
	</body>
</html>