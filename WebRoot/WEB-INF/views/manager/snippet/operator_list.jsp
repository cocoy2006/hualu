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
					<th>用户名</th>
					<th>密码</th>
					<th>角色</th>
					<th>真实姓名</th>
					<th>性别</th>
					<th>创建时间</th>
					<th>手机号</th>
					<th>状态</th>
					<th>备注</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${fn:length(operators) > 0}">
				   	<c:forEach var="operator" items="${operators}">
				   	<tr>
						<td>${operator.id}</td>
						<td>${operator.loginname}</td>
						<td>${operator.passwd}</td>
						<td>
							<c:if test="${operator.role == 1}">库管</c:if>
							<c:if test="${operator.role == 2}">客服</c:if>
							<c:if test="${operator.role == 3}">客服组长</c:if>
							<c:if test="${operator.role == 4}">客服主管</c:if>
							<c:if test="${operator.role == 5}">医生</c:if>
							<c:if test="${operator.role == 6}">管理员</c:if>
						</td>
						<td>${operator.name}</td>
						<td>
							<c:if test="${operator.genda == 1}">男</c:if>
							<c:if test="${operator.genda == 2}">女</c:if>
							<c:if test="${operator.genda == 3}">未知</c:if>
						</td>
						<td><fmt:formatDate pattern="yyyy年MM月dd日HH点mm分ss秒" value="${operator.createtime}" /></td>
						<td>${operator.mobile}</td>			
						<td>
							<c:if test="${operator.status == 0}">未服务</c:if>
							<c:if test="${operator.status == 1}">正常服务</c:if>
							<c:if test="${operator.status == 2}">已注销</c:if>
						</td>
						<td>${operator.memo}</td>	
						<td>
							<button class="btn btn-success btn-xs" onclick="openEditOperatorModal(${operator.id})">编辑</button>
							<button class="btn btn-danger btn-xs" onclick="removeOperator(${operator.id})">删除</button>
						</td>
					</tr>
				   	</c:forEach>
				</c:if>
			</tbody>
		</table>
	</body>
</html>