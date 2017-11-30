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
			loadRejectList();
			loadRecallList();
			loadOtherList();
		}
		function loadRejectList() {
			var columns = [
		            { "data": "id" },
		            { "data": "time" },
		            { "data": "username" },
		            { "data": "id" }
		        ];
			dispDatatableWithPagination(".table7", host + "recordTaskList/7/", columns, "#span7", add7Buttons);
		}
		function loadRecallList() {
			var columns = [
		            { "data": "id" },
		            { "data": "time" },
		            { "data": "username" },
		            { "data": "id" }
		        ];
			dispDatatableWithPagination(".table8", host + "recordTaskList/8/", columns, "#span8", add8Buttons);
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
		function add7Buttons() {
			$(".table7 tbody tr").each(function() {
				var td = $(this).children("td").last();
				var id = td.html();
				td.html(buttonOfOpenModal(id, 7, " 处理 "));
			});
		}
		function add8Buttons() {
			$(".table8 tbody tr").each(function() {
				var td = $(this).children("td").last();
				var id = td.html();
				td.html(buttonOfOpenModal(id, 8, " 处理 "));
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
				if(mode == RecordTask.REJECT) {
					$("p[name='recall']").hide();
				} else if(mode == RecordTask.RECALL) {
					$("p[name='reject']").hide();
				} else {
					$("p[name='reject']").hide();
					$("p[name='recall']").hide();
					$("p[name='next']").hide();
				}
			});
		}
		</script>
	</head>
	<body>
		<div class="panel panel-primary">
			<div class="panel-heading">
				<i class="icon-table"></i>&nbsp;自检记录
				<small>[自动刷新周期:3分钟]</small>
				<button class="btn btn-warning btn-xs" onclick="location.reload();">立即刷新</button>
			</div>
			<div class="panel-body">
				<!-- tabs left -->
				<div class="tabbable">
					<ul class="nav nav-tabs nav-pills">
						<li class="active">
							<a href="#7" data-toggle="tab">
								驳回中<span id="span7" class="badge"></span>
							</a>
						</li>
						<li>
							<a href="#8" data-toggle="tab">
								撤回中<span id="span8" class="badge"></span>
							</a>
						</li>
						<li>
							<a href="#99" data-toggle="tab">
								可查看<span id="span99" class="badge"></span>
							</a>
						</li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="7">
							<table class="table table-striped table-bordered table7" 
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
						<div class="tab-pane" id="8">
							<table class="table table-striped table-bordered table8" 
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