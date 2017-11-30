<%@ page language="java" pageEncoding="UTF-8"%>
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
			loadProcessingList();
			loadDraftList();
			loadOtherList();
		}
		function loadProcessingList() {
			var columns = [
		            { "data": "id" },
		            { "data": "nursename" },
		            { "data": "username" },
		            { "data": "time" },
		            { "data": "id" }
		        ];
			dispDatatableWithPagination(".table5", host + "recordTaskList/5/", columns, "#span5", add5Buttons);
		}
		function loadDraftList() {
			var columns = [
		            { "data": "id" },
		            { "data": "nursename" },
		            { "data": "username" },
		            { "data": "time" },
		            { "data": "id" }
		        ];
			dispDatatableWithPagination(".table6", host + "recordTaskList/6/", columns, "#span6", add6Buttons);
		}
		function loadOtherList() {
			var columns = [
		            { "data": "id" },
		            { "data": "nursename" },
		            { "data": "username" },
		            { "data": "time" },
		            { "data": "status" },
		            { "data": "id" }
		        ];
			dispDatatableWithPagination(".table99", host + "recordTaskList/99/", columns, "#span99", add99Buttons);
		}
		function add5Buttons() {
			$(".table5 tbody tr").each(function() {
				var td = $(this).children("td").last();
				var id = td.html();
				td.html(buttonOfOpenModal(id, 5, " 处理 "));
			});
		}
		function add6Buttons() {
			$(".table6 tbody tr").each(function() {
				var td = $(this).children("td").last();
				var id = td.html();
				td.html(buttonOfOpenModal(id, 6, " 处理 "));
			});
		}
		function add99Buttons() {
			$(".table99 tbody tr").each(function() {
				var td = $(this).children("td").last();
				var id = td.html();
				td.html(buttonOfOpenModal(id, 99, " 查看 ") 
					+ "&nbsp;" + buttonOfSendProblemSms(id, " 发送问题短信 ") 
					+ "&nbsp;" + buttonOfSendNormalSms(id, " 发送正常短信 "));
			});
		}
		function buttonOfOpenModal(id, status, desc) {
			return '<button class="btn btn-success btn-xs" ' +
				'onclick="openRecordModal(' + id + ', ' + status + ')">' + desc + '</button>';
		}
		function buttonOfSendProblemSms(id, desc) {
			return '<button class="btn btn-info btn-xs" ' +
				'onclick="sendRecordProblemSms(' + id + ')">' + desc + '</button>';
		}
		function buttonOfSendNormalSms(id, desc) {
			return '<button class="btn btn-info btn-xs" ' +
				'onclick="sendRecordNormalSms(' + id + ')">' + desc + '</button>';
		}
		function openRecordModal(taskid, mode) {
			$("#modalContainer").load(host + "recordTask/" + taskid + "/", function() {
				if(mode == RecordTask.DOCTOR_PROCESSING || mode == RecordTask.DOCTOR_DRAFT) {
					
				} else {
					$("textarea[name='advice']").attr("disabled", "disabled");
					$("p[name='next']").hide();
					$("p[name='draft']").hide();
					$("p[name='reject']").hide();
				}
			});
		}
		function sendRecordProblemSms(rtId) {
			sendSms("<%=basePath%>sms/recordProblem/" + rtId + "/");
		}
		function sendRecordNormalSms(rtId) {
			sendSms("<%=basePath%>sms/recordNormal/" + rtId + "/");
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
							<a href="#5" data-toggle="tab">
								待处理<span id="span5" class="badge"></span>
							</a>
						</li>
						<li>
							<a href="#6" data-toggle="tab">
								草稿<span id="span6" class="badge"></span>
							</a>
						</li>
						<li>
							<a href="#99" data-toggle="tab">
								可查看<span id="span99" class="badge"></span>
							</a>
						</li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="5">
							<table class="table table-striped table-bordered table5" 
								cellspacing="0" width="100%">
								<thead>
									<tr>
										<th>任务序号</th>
										<th>客服</th>
										<th>用户</th>
										<th>创建时间</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody></tbody>
							</table>
						</div>
						<div class="tab-pane" id="6">
							<table class="table table-striped table-bordered table6" 
								cellspacing="0" width="100%">
								<thead>
									<tr>
										<th>任务序号</th>
										<th>客服</th>
										<th>用户</th>
										<th>创建时间</th>
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
										<th>任务序号</th>
										<th>客服</th>
										<th>用户</th>
										<th>创建时间</th>
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