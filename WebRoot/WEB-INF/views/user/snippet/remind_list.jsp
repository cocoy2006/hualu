<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
	<head></head>
	<body>
	<c:if test="${fn:length(rcs) > 0}">
	   	<c:forEach var="rc" items="${rcs}">
	   	<tr>
			<td>
				<div class="media" style="overflow: visible;">
					<a class="pull-left" href="#"> 
						<img class="img-circle" style="height: 40px;" 
							src="<%=basePath%>resources/images/role/default.png">
					</a>
					<div class="media-body" style="overflow: visible;">
						<h4 class="media-heading">
							${rc.operator.name}
							<small class="pull-right">
								<fmt:formatDate pattern="yyyy年MM月dd日HH点mm分" value="${rc.remind.starttime}" />
							<c:if test="${rc.remind.status == 1}">
								<span class="label label-danger">未读</span>
							</c:if>
							</small>
						</h4>
						${rc.remind.content}
					</div>
				</div>
			</td>
		</tr>
	   	</c:forEach>
	</c:if>
	</body>
</html>