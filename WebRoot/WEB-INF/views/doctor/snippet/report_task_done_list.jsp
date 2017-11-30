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
					<th>创建时间</th>
					<th>用户</th>
					<th>类型</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody name="reportTaskDoneList">
				<c:if test="${fn:length(rtcs) > 0}">
			   	<c:forEach var="rtc" items="${rtcs}">
			   	<tr>
					<td>${rtc.reportTask.id}</td>
					<td>${rtc.reportTask.createtime}</td>
					<td>${rtc.user.name}</td>
					<td>
						<c:if test="${rtc.reportTask.type == 1}">月报</c:if>
						<c:if test="${rtc.reportTask.type == 2}">季报</c:if>
						<c:if test="${rtc.reportTask.type == 3}">年报</c:if>
					</td>
					<td>
						<button class="btn btn-success btn-xs" 
							onclick="openReportModal(${rtc.reportTask.id}, 3)">查看</button>
					</td>
				</tr>
			   	</c:forEach>
			</c:if>
			</tbody>
		</table>
	</body>
</html>