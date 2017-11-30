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
			$("tbody[name='unreadRemindList']").load(host + "remindList/1/");
			$("tbody[name='readRemindList']").load(host + "remindList/2/");
		});
		</script>
	</head>
	<body>
		<div class="panel panel-primary">
			<div class="panel-heading">
				<i class="icon-table"></i>&nbsp;系统消息
			</div>
			<div class="panel-body">
				<!-- tabs left -->
				<div class="tabbable">
					<ul class="nav nav-tabs nav-pills">
						<li class="active">
							<a href="#1" data-toggle="tab">
								未读
							</a>
						</li>
						<li>
							<a href="#2" data-toggle="tab">
								已读
							</a>
						</li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="1">
							<!-- 加载未读消息 -->
							<table class="table table-striped table-bordered" 
								cellspacing="0" width="100%">
								<thead>
									<tr>
										<th>序号</th>
										<th>提醒人</th>
										<th>时间</th>
										<th>内容</th>
										<!-- <th>状态</th> -->
									</tr>
								</thead>
								<tbody name="unreadRemindList">
									<tr>
										<td colspan="4">Loading...</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="tab-pane" id="2">
							<!-- 加载已读消息 -->
							<table class="table table-striped table-bordered" 
								cellspacing="0" width="100%">
								<thead>
									<tr>
										<th>序号</th>
										<th>提醒人</th>
										<th>时间</th>
										<th>内容</th>
										<!-- <th>状态</th> -->
									</tr>
								</thead>
								<tbody name="readRemindList">
									<tr>
										<td colspan="4">Loading...</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>