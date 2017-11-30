<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
	<head></head>
	<body>
	<c:if test="${fn:length(companys) > 0}">
	   	<c:forEach var="company" items="${companys}">
	   	<tr>
			<td>${company.id}</td>
			<td>${company.name}</td>
			<td>${company.code}</td>
			<td>${company.address}</td>
			<td>${company.spec}</td>
			<td>${company.date}</td>
			<td>
				<button class="btn btn-success" onclick="openEditCompanyModal(${company.id})">编辑</button>
				<button class="btn btn-success" onclick="loadPackagePage(${company.id})">查看批次</button>
			</td>
		</tr>
	   	</c:forEach>
	</c:if>
	<c:if test="${fn:length(companys) == 0}">
		<tr>
			<td colspan="7">暂无厂商</td>
		</tr>
	</c:if>
	</body>
</html>