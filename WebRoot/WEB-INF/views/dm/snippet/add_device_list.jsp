<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
	<head></head>
	<body>
	<c:if test="${fn:length(dcs) > 0}">
	   	<c:forEach var="dc" items="${dcs}">
	   	<tr>
			<td>${dc.device.did}</td>
			<td>${dc.devicePackage.dmodel}</td>
			<td>${dc.device.dsn}</td>
			<td>${dc.devicePackage.dname}</td>
			<td>${dc.company.name}</td>
			<td>${dc.device.ddatetime}</td>
			<td>${dc.devicePackage.dip}</td>
			<td>
			<c:if test="${dc.device.dstatus == 1}">
				未使用
			</c:if>
			<c:if test="${dc.device.dstatus == 2}">
				已领用
			</c:if>
			<c:if test="${dc.device.dstatus == 3}">
				已绑定
			</c:if>
			</td>
		</tr>
	   	</c:forEach>
	</c:if>
	<c:if test="${fn:length(dcs) == 0}">
		<tr>
			<td colspan="10">暂无设备</td>
		</tr>
	</c:if>
	</body>
</html>