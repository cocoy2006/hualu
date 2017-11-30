<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
			tr.user {
				cursor: pointer;
			}
		</style>
		<script type="text/javascript">
		$(function() {
			dispDataTable("dataTable2");
			$(".redirectHolder").hide();
		});
		</script>
	</head>
	<body>
		<div class="modal fade" role="dialog" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
						<h4 class="modal-title">选择新的用户<small>&nbsp;单击表格行即可</small></h4>
					</div>
					<div class="modal-body">
						<table class="table table-striped table-bordered dataTable2" 
								cellspacing="0" width="100%">
							<thead>
								<tr>
									<td>用户编号</td>
									<td>用户姓名</td>
									<td>用户电话</td>
								</tr>
							</thead>
							<tbody>
							<c:if test="${fn:length(users) > 0}">
								<c:forEach var="user" items="${users}">
							   	<tr class="user" title="${user.id}">
									<td>${user.id}</td>
									<td title="name">${user.name}</td>
									<td>${user.phone}</td>
								</tr>
							   	</c:forEach>
							</c:if>
							</tbody>
						</table>
						<button type="button" class="btn btn-default btn-block" data-dismiss="modal">返回</button>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
	</body>
</html>