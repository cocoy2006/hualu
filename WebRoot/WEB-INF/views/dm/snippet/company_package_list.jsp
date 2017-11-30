<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
	<head></head>
	<body>
	<c:if test="${fn:length(packages) > 0}">
	   	<c:forEach var="pack" items="${packages}">
	   	<tr title="${pack.did }" >
	   		<td>${pack.did}</td>
	   		<td>${company.name}</td>
	   		<td>${pack.dname}</td>
	   		<td>${pack.dmodel}</td>
	   		<td>${pack.dip}</td>
	   		<td>${pack.dinfo}</td>
	   		<td>${pack.dpic}</td>
	   		<td>
				<button class="btn btn-success" onclick="openEditPackModal(${company.id}, ${pack.did})">编辑</button>
			</td>
		</tr>
	   	</c:forEach>
	</c:if>
	<c:if test="${fn:length(packages) == 0}">
		<tr>
			<td colspan="8">暂无批次</td>
		</tr>
	</c:if>
	</body>
</html>