<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<style type="text/css">
			tr.doctor {
				cursor: pointer;
			}
		</style>
		<script type="text/javascript">
		$(function() {
			dispDataTable("dataTable");
			$(".redirectHolder").hide();
		});
		</script>
	</head>
	<body>		
		<!-- 选择医生的模态框 -->
		<div class="modal fade" role="dialog" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
						<h4 class="modal-title">选择医生</h4>
					</div>
					<div class="modal-body">
						<table class="table table-striped table-bordered dataTable" 
								cellspacing="0" width="100%">
							<thead>
								<tr>
									<td>医生编号</td>
									<td>医生姓名</td>
									<td>状态</td>
									<td>待办工作</td>
								</tr>
							</thead>
							<tbody>
								<c:if test="${fn:length(dcs) > 0}">
									<c:forEach var="dc" items="${dcs}">
								   	<tr class="doctor" title="${dc.doctor.id}">
										<td>${dc.doctor.id}</td>
										<td title="name">${dc.doctor.name}</td>
										<td>
										<c:if test="${dc.doctor.isonline == 1}">
											在线
										</c:if>
										<c:if test="${dc.doctor.isonline == 2}">
											离线
										</c:if>
										<c:if test="${dc.doctor.isonline == 3}">
											休息中
										</c:if>
										</td>
										<td>${dc.undoneHealthTaskNumber}</td>
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