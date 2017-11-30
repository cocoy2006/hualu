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
					<th>上传时间</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${fn:length(rtcs) > 0}">
				   	<c:forEach var="rtc" items="${rtcs}">
				   	<tr>
						<td>${rtc.recordTask.id}</td>
						<td>${rtc.recordTask.endtime}</td>
						<td>
							<c:if test="${rtc.recordTask.status == 1}">初始化</c:if>
							<c:if test="${rtc.recordTask.status == 2}">等待客服</c:if>
							<c:if test="${rtc.recordTask.status == 3}">客服处理</c:if>
							<c:if test="${rtc.recordTask.status == 4}">等待医生</c:if>
							<c:if test="${rtc.recordTask.status == 5}">医生处理</c:if>
							<c:if test="${rtc.recordTask.status == 6}">医生处理</c:if>
							<c:if test="${rtc.recordTask.status == 7}">驳回中</c:if>
							<c:if test="${rtc.recordTask.status == 8}">撤回中</c:if>
							<c:if test="${rtc.recordTask.status == 9}">主管处理</c:if>
							<c:if test="${rtc.recordTask.status == 10}">已完成</c:if>
						</td>
						<td>
							<button class="btn btn-success btn-xs" 
								onclick="openRecordModal(${rtc.recordTask.id})">查看详情</button>
						</td>
					</tr>
				   	</c:forEach>
				</c:if>
			</tbody>
		</table>
	</body>
</html>