<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
	<head></head>
	<body>
		<table class="table table-striped table-bordered" 
			cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>序号</th>
					<th>录入时间</th>
					<th>疾病名称</th>
					<th>用户</th>
					<th>医生</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody name="healthTaskRunningList">
				<c:if test="${fn:length(htcs) > 0}">
			   	<c:forEach var="htc" items="${htcs}">
			   	<tr>
					<td>${htc.healthTask.id}</td>
					<td>${htc.healthTask.createtime}</td>
					<td>${htc.healthTask.disname}</td>
					<td>${htc.user.name}</td>
					<td>${htc.doctor.name}</td>
					<td>
						<button class="btn btn-success btn-xs" 
							onclick="openHealthModal(${htc.healthTask.id}, 1)">修改数据</button>
					</td>
				</tr>
			   	</c:forEach>
			</c:if>
			</tbody>
		</table>
	</body>
</html>