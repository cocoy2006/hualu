<%@ page language="java" pageEncoding="UTF-8"%>
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
			filterCompanys();
		});
		function filterCompanys() {
			$("tbody[name='companylist']").load(host + "companylist/");
		}
		function openSaveCompanyModal() {
			var url = host + "companymodal/1/";
			$("#modalContainer").load(url, function() {
				$(".modal").modal("show");
			});
		}
		function openEditCompanyModal(id) {
			var url = host + "companymodal/2/";
			$("#modalContainer").load(url, {id:id}, function() {
				$(".modal").modal("show");
			});
		}
		</script>
	</head>
	<body>
		<div class="panel panel-primary">
			<div class="panel-heading">
				<i class="icon-table"></i>&nbsp;厂商管理
			</div>
			<div class="panel-body">
				<div>
					<button class="btn btn-warning" onclick="openSaveCompanyModal()">+&nbsp;添加厂商</button>
				</div>
				<table class="table table-striped table-bordered" 
					cellspacing="0" width="100%">
					<thead>
						<tr>
							<th>序号</th>
							<th>厂商名称</th>
							<th>厂商代码</th>
							<th>厂商地址</th>
							<th>厂商简介</th>
							<th>入库时间</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody name="companylist">
						<tr>
							<td colspan="7">Loading...</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div id="modalContainer"></div>
	</body>
</html>