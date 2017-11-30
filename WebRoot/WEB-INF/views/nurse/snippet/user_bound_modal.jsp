<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<style type="text/css">
			.device {
				cursor: pointer;
			}
		</style>
		<script type="text/javascript">
		$(function() {
			// 选择设备
			$("tr.device").click(function() {
				var deviceid = this.title;
				var userid = ${userid};
				$.ajax({
					url: host + "bound/",
					data: "deviceid=" + deviceid + "&userid=" + userid,
					dataType: "json",
					type: "POST",
					success: function(data) {
						if(data == "1") {
							success();
							$(".modal").modal("hide");
							filterUsers();
						} else {
							danger(data);
						}
					}
				});
			});
		});
		</script>
	</head>
	<body>
		<!-- 设备绑定的模态框 -->
		<div class="modal fade" role="dialog" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title">设备信息<small>&nbsp;单击表格行即可</small></h4>
					</div>
					<div class="modal-body">
						<table class="table table-striped table-bordered dataTable" cellspacing="0" width="100%">
							<thead>
								<tr>
									<th>序号</th>
									<th>设备名称</th>
									<th>设备型号</th>
									<th>设备SN码</th>
								</tr>
							</thead>
							<tbody>
							<c:if test="${fn:length(dcs) > 0}">
	   							<c:forEach var="dc" items="${dcs}">
	   							<tr class="device" title="${dc.device.did}">
	   								<td>${dc.device.did}</td>
	   								<td>${dc.devicePackage.dname}</td>
	   								<td>${dc.devicePackage.dmodel}</td>
	   								<td>${dc.device.dsn}</td>
	   							</tr>
	   							</c:forEach>
	   						</c:if>
	   						<c:if test="${fn:length(dcs) == 0}">
	   							<tr>
									<td colspan="5">暂无设备</td>
								</tr>
	   						</c:if>
							</tbody>
						</table>
						<button type="button" class="btn btn-default btn-block" data-dismiss="modal">
							取消
						</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->
	</body>
</html>