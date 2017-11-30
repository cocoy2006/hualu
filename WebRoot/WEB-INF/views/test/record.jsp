<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<!-- Title and other stuffs -->
		<title>华录医疗</title>
		<meta name="keywords" content="医疗" />
		<meta name="description" content="华录医疗" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<!-- HTML5 Support for IE -->
		<!--[if lt IE 9]>
          	<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          	<script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->
		<!-- Favicon -->
		<link rel="shortcut icon" href="<%=basePath%>resources/images/favicon.ico">
		<!-- CSS -->
		<link rel="stylesheet" href="<%=basePath%>resources/bootstrap-3.0.3/css/bootstrap.css">
		<link rel="stylesheet" href="<%=basePath%>resources/bootstrap-3.0.3/font-awesome/css/font-awesome.css">
		<link rel="stylesheet" href="<%=basePath%>resources/DataTables-1.10.4/examples/resources/bootstrap/3/dataTables.bootstrap.css">
		<!-- JS -->
		<script type="text/javascript" src="<%=basePath%>resources/js/jquery.js"></script>
		<script type="text/javascript" src="<%=basePath%>resources/bootstrap-3.0.3/js/bootstrap.js"></script>
		<script type="text/javascript" src="<%=basePath%>resources/DataTables-1.10.4/media/js/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>resources/DataTables-1.10.4/examples/resources/bootstrap/3/dataTables.bootstrap.js"></script>
		<script type="text/javascript" src="<%=basePath%>resources/js/test.js"></script>
		<script type="text/javascript">
		$(function() {});
		</script>
	</head>
	<body>
		<div class="panel panel-primary">
			<div class="panel-heading">
				<i class="icon-table"></i>&nbsp;自检记录
			</div>
			<div class="panel-body">
				<!-- 加载已分析数据 -->
				<table class="table table-striped table-bordered" 
					cellspacing="0" width="100%">
					<thead>
						<tr>
							<th>序号</th>
							<th>上传时间</th>
							<th>档案号</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${fn:length(records) > 0}">
							<c:forEach var="record" items="${records}" varStatus="i">
							<tr>
								<td>${i.index + 1}</td>
								<td>${record.rdatetime}</td>
								<td>${record.dstr}</td>
								<td>
									<a class="btn btn-success btn-xs" href="<%=basePath%>test/detail/${record.id}/">分析数据</a>
								</td>
							</tr>
							</c:forEach>
						</c:if>
						<c:if test="${fn:length(records) <= 0}">
							<tr>
								<td colspan="4">今日无数据</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
	</body>
</html>