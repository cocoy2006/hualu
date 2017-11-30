<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
			var c = "label-danger", d = "label-default";
			$(":radio[name='role']").click(function() {
				// change style
				$(this).parent("label").removeClass(d).addClass(c)
					.siblings().removeClass(c).addClass(d);
				// filte operators
				filterOperators(filterOperatorsData());
			});
			filterOperators();
		});
		function filterOperators(data) {
			$("#tableContainer").load(host + "operatorlist/", data, function() {
				dispDataTable("table");
			});
		}
		function filterOperatorsData() {
			return $("form").serialize();
		}
		function openSaveOperatorModal() {
			var url = host + "operatorModal/1/";
			$("#modalContainer").load(url, function() {
				$(".modal").modal("show");
			});
		}
		function openEditOperatorModal(id) {
			var url = host + "operatorModal/2/";
			$("#modalContainer").load(url, {id: id}, function() {
				$(".modal").modal("show");
			});
		}
		function removeOperator(id) {
			if(confirm(" 确定删除该操作员？ ")) {
				$.ajax({
					url: "<%=basePath %>operator/remove/" + id + "/",
					dataType: "json",
					success: function(data) {
						if(data == "1") {
							messager(" 操作成功 ", "success", 2000);
							filterOperators(filterOperatorsData());
						} else if(data == "-1"){
							messager(" 名下有未完成工单，无法删除 ", "danger", 2000);
						} else {
							messager(data, "danger", 2000);
						}
					}
				});
			}
		}
		</script>
	</head>
	<body>
		<div class="panel panel-primary">
			<div class="panel-heading">
				<i class="icon-table"></i>&nbsp;用户管理
				<button class="btn btn-warning btn-xs" onclick="openSaveOperatorModal()">新建用户</button>
			</div>
			<div class="panel-body">
				<form class="form-horizontal" role="form">
					<!-- 角色类型筛选项 -->
					<div>
						<label class="control-label col-md-1">
							角色
						</label>
						<p class="col-md-11">
							<label class="radio-inline label label-danger">
								<input type="radio" name="role"
									value="0" checked style="display: none;">
								全部										
							</label>
							<label class="radio-inline label label-default">
								<input type="radio" name="role"
									value="2" style="display: none;">
								客服									
							</label>
							<label class="radio-inline label label-default">
								<input type="radio" name="role"
									value="3" style="display: none;">
								客服组长									
							</label>
							<label class="radio-inline label label-default">
								<input type="radio" name="role"
									value="4" style="display: none;">
								客服主管									
							</label>
							<label class="radio-inline label label-default">
								<input type="radio" name="role"
									value="5" style="display: none;">
								医生								
							</label>
							<label class="radio-inline label label-default">
								<input type="radio" name="role"
									value="1" style="display: none;">
								库管							
							</label>
						</p>
					</div>					
				</form>
				<div id="tableContainer"></div>
			</div>
		</div>
		<div id="modalContainer"></div>
	</body>
</html>