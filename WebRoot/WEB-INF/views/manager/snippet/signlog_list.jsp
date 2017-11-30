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
					<th>姓名（工号）</th>
					<th>登录时间</th>
					<th>登出时间 </th>
					<th>在线时间</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${fn:length(scs) > 0}">
				   	<c:forEach var="sc" items="${scs}">
				   	<tr>
						<td>${sc.signlog.id}</td>
						<td>
							<c:if test="${sc.operator.role == 1}">库管</c:if>
							<c:if test="${sc.operator.role == 2}">客服</c:if>
							<c:if test="${sc.operator.role == 3}">客服组长</c:if>
							<c:if test="${sc.operator.role == 4}">客服主管</c:if>
							<c:if test="${sc.operator.role == 5}">医生</c:if>
						</td>
						<td>${sc.operator.name}</td>
						<td><fmt:formatDate pattern="yyyy年MM月dd日HH点mm分ss秒" value="${sc.signlog.signintime}" /></td>
						<td><fmt:formatDate pattern="yyyy年MM月dd日HH点mm分ss秒" value="${sc.signlog.signouttime}" /></td>
						<td>
							<c:if test="${sc.signlog.onlinetime > 3600000}">
							<fmt:formatNumber type="number" value="${(sc.signlog.onlinetime - sc.signlog.onlinetime % 3600000) / 3600000 }" maxFractionDigits="0"/>小时
							<fmt:formatNumber type="number" value="${(sc.signlog.onlinetime / 1000 % 3600 - sc.signlog.onlinetime / 1000 % 3600 % 60) / 60 }" maxFractionDigits="0"/>分
							</c:if>
							<c:if test="${sc.signlog.onlinetime <= 3600000 && sc.signlog.onlinetime > 60000}">
							<fmt:formatNumber type="number" value="${(sc.signlog.onlinetime - sc.signlog.onlinetime % 60000) / 60000 }" maxFractionDigits="0"/>分
							<fmt:formatNumber type="number" value="${sc.signlog.onlinetime / 1000 % 60 }" maxFractionDigits="0"/>秒
							</c:if>
							
							<c:if test="${sc.signlog.onlinetime < 60000}">
							<fmt:formatNumber type="number" value="${(sc.signlog.onlinetime - sc.signlog.onlinetime % 1000) / 1000 }" maxFractionDigits="0"/>秒
							</c:if>
						</td>
					</tr>
				   	</c:forEach>
				</c:if>
			</tbody>
		</table>
	</body>
</html>