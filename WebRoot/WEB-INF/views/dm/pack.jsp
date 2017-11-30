<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
			filterPackages();
		});
		function filterPackages() {
			$("tbody[name='packlist']").load(host + "packlist/${company.id}/");
		}
		function openSavePackModal(comid) {
			var url = host + "packmodal/1/";
			$("#modalContainer").load(url, {comid:comid}, function() {
				$(".modal").modal("show");
			});
		}
		function openEditPackModal(comid, id) {
			var url = host + "packmodal/2/";
			$("#modalContainer").load(url, {comid:comid, id:id}, function() {
				$(".modal").modal("show");
			});
		}
		</script>
	</head>
	<body>
		<div class="panel panel-primary">
			<div class="panel-heading">
				<i class="icon-table"></i>&nbsp;批次管理
			</div>
			<div class="panel-body">
				<div>
					<button class="btn btn-warning" onclick="openSavePackModal(${company.id})">+&nbsp;添加批次</button>
				</div>
				<table class="table table-striped table-bordered" 
					cellspacing="0" width="100%">
					<thead>
						<tr>
							<th>序号</th>
							<th>厂商名称</th>
							<th>设备名称</th>
							<th>设备型号</th>
							<th>设备服务器IP</th>
							<th>设备描述</th>
							<th>设备图片</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody name="packlist">
						<tr>
							<td colspan="8">Loading...</td>
						</tr>
					</tbody>
				</table>
				<div>
					<button class="btn btn-info" onclick="loadCompanyPage()">&lt;&lt;&nbsp;返回厂商列表</button>
				</div>
			</div>
		</div>
		<div id="modalContainer"></div>
	</body>
</html>