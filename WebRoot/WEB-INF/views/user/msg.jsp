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
			$("tbody[name='remindList']").load(host + "remindList/", function() {
				dispDataTable("remindDataTable");
				$(".redirectHolder").hide();
			});
			$("tbody[name='planList']").load(host + "planList/", function() {
				dispDataTable("planDataTable");
				$(".redirectHolder").hide();
			});
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
								健康干预消息
							</a>
						</li>
						<li>
							<a href="#2" data-toggle="tab">
								健康计划消息
							</a>
						</li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="1">
							<!-- 加载健康干预消息 -->
							<table class="table table-striped table-bordered remindDataTable" 
								cellspacing="0" width="100%">
								<thead>
									<tr>
										<td></td>
									</tr>
								</thead>
								<tbody name="remindList">
									<tr>
										<td>Loading...</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="tab-pane" id="2">
							<!-- 加载健康计划消息 -->
							<table class="table table-striped table-bordered planDataTable" 
								cellspacing="0" width="100%">
								<thead>
									<tr>
										<td></td>
									</tr>
								</thead>
								<tbody name="planList">
									<tr>
										<td>Loading...</td>
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