<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head></head>
	<body>
		<table class="table table-striped table-bordered" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>序号</th>
					<th>角色</th>
					<th>操作人</th>
					<th>操作类型</th>
					<th>操作模块</th>
					<th>操作时间 </th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${fn:length(ocs) > 0}">
				   	<c:forEach var="oc" items="${ocs}">
				   	<tr>
						<td>${oc.operationlog.id}</td>
						<td>
							<c:if test="${oc.operationlog.operatorrole == 1}">库管</c:if>
							<c:if test="${oc.operationlog.operatorrole == 2}">客服</c:if>
							<c:if test="${oc.operationlog.operatorrole == 3}">客服组长</c:if>
							<c:if test="${oc.operationlog.operatorrole == 4}">库管主管</c:if>
							<c:if test="${oc.operationlog.operatorrole == 5}">医生</c:if>
							<c:if test="${oc.operationlog.operatorrole == 6}">管理员</c:if>
						</td>
						<td>${oc.operator.name}</td>
						<td>
							<c:if test="${oc.operationlog.operationtype == 1}">新建</c:if>
							<c:if test="${oc.operationlog.operationtype == 2}">删除</c:if>
							<c:if test="${oc.operationlog.operationtype == 3}">修改/处理</c:if>
							<c:if test="${oc.operationlog.operationtype == 4}">查询</c:if>
							<c:if test="${oc.operationlog.operationtype == 5}">绑定</c:if>
							<c:if test="${oc.operationlog.operationtype == 6}">解绑定</c:if>
						</td>
						<td>
							<c:if test="${oc.operationlog.targettype == 1}">自检记录</c:if>
							<c:if test="${oc.operationlog.targettype == 2}">综合报告</c:if>
							<c:if test="${oc.operationlog.targettype == 3}">健康咨询</c:if>
							<c:if test="${oc.operationlog.targettype == 4}">健康干预</c:if>
							<c:if test="${oc.operationlog.targettype == 5}">用户管理</c:if>
							<c:if test="${oc.operationlog.targettype == 6}">设备管理</c:if>
							<c:if test="${oc.operationlog.targettype == 7}">厂家管理</c:if>
							<c:if test="${oc.operationlog.targettype == 8}">批次管理</c:if>
						</td>
						<td><fmt:formatDate pattern="yyyy年MM月dd日HH点mm分ss秒" value="${oc.operationlog.time}" /></td>
					</tr>
				   	</c:forEach>
				</c:if>
			</tbody>
		</table>
	</body>
</html>