<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
	<head></head>
	<body>
	<c:if test="${fn:length(pcs) > 0}">
	   	<c:forEach var="pc" items="${pcs}">
	   	<tr class="package" title="${pc.devicePackage.did }" >
	   		<td>${pc.company.name}</td>
	   		<td>${pc.devicePackage.dname}</td>
	   		<td>${pc.devicePackage.dmodel}</td>
	   		<td>${pc.devicePackage.dip}</td>
	   		<td>${pc.devicePackage.dinfo}</td>
	   		<td>${pc.devicePackage.dpic}</td>
		</tr>
	   	</c:forEach>
	</c:if>
	<c:if test="${fn:length(pcs) == 0}">
		<tr>
			<td colspan="6">暂无批次</td>
		</tr>
	</c:if>
	</body>
</html>