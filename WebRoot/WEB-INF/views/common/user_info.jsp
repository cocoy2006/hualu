<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<c:if test="${u != null}">
		<div class="col-md-3">
			<img class="img-circle" style="height: 125px;" 
				src="<%=basePath%>resources/images/avatar/20140623140343000000_1_11499_54.jpg"/>
		</div>
		<div class="col-md-9">
			<p class="col-md-6">
				<label>用户姓名：</label>
				${u.name}
			</p>
			<p class="col-md-6">
				<label>性别：</label>
				<c:if test="${u.gender == 1}">男</c:if>
				<c:if test="${u.gender == 2}">女</c:if>
				<c:if test="${u.gender == 3}">未知</c:if>
			</p>
			<p class="col-md-6">
				<label>年龄：</label>
				${u.age}
			</p>
			<p class="col-md-6">
				<label>服务类型：</label>
				<c:if test="${u.userlevel == 1}">普通</c:if>
				<c:if test="${u.userlevel == 2}">贵宾</c:if>
			</p>
			<p class="col-md-12">
				<label>通讯地址：</label>
				${u.homeaddress}
			</p>
			<p class="col-md-12">
				<label>手机：</label>
				${u.loginname}
			</p>
		</div>
		</c:if>		
		<c:if test="${u == null}">
		<div> 用户不存在 </div>
		</c:if>
	</body>
</html>