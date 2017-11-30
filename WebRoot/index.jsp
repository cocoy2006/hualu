<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
	<head>
		<script type="text/javascript">
		window.onload = function() {
			location.href = "<%=basePath%>index/";
		}
		</script>
	</head>
	<body></body>
</html>
