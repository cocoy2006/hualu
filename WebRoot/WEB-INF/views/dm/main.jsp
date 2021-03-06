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
		<link rel="stylesheet" href="<%=basePath%>resources/bootstrap-3.0.3/css/bootstrap-tab.css">
		<link rel="stylesheet" href="<%=basePath%>resources/bootstrap-3.0.3/font-awesome/css/font-awesome.css">
		<link rel="stylesheet" href="<%=basePath%>resources/bootstrap-3.0.3/datetimepicker/css/bootstrap-datetimepicker.css">
		<link rel="stylesheet" href="<%=basePath%>resources/DataTables-1.10.4/examples/resources/bootstrap/3/dataTables.bootstrap.css">
		<style type="text/css">
			html, body { height:100%; }
			.multiline-label { text-overflow: ellipsis; overflow: hidden; }
		</style>
		<!-- JS -->
		<script type="text/javascript" src="<%=basePath%>resources/js/jquery.js"></script>
		<script type="text/javascript" src="<%=basePath%>resources/bootstrap-3.0.3/js/bootstrap.js"></script>
		<script type="text/javascript" src="<%=basePath%>resources/bootstrap-3.0.3/datetimepicker/js/bootstrap-datetimepicker.js"></script>
		<script type="text/javascript" src="<%=basePath%>resources/bootstrap-3.0.3/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
		<script type="text/javascript" src="<%=basePath%>resources/DataTables-1.10.4/media/js/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>resources/DataTables-1.10.4/examples/resources/bootstrap/3/dataTables.bootstrap.js"></script>
		<script type="text/javascript" src="<%=basePath%>resources/js/messager.js"></script>
		<script type="text/javascript" src="<%=basePath%>resources/js/util.js"></script>
		<script type="text/javascript">
		var host = "<%=basePath%>dm/";
		$(function() {
			loadSpecificPage("device");
			// bind switching action of nav-tabs
			$("a[data-toggle='tab']").on("show.bs.tab", function (e) {
				$("#" + e.relatedTarget.title).html("");
				loadSpecificPage(this.title);
			})
		});
		function loadSpecificPage(page) {
			$("#" + page).load(host + page + "/");
		}
		function loadCompanyPage() {
			loadSpecificPage("company");
		}
		function loadPackagePage(comid) {
			$("#company").load(host + "pack/" + comid + "/");
		}
		</script>
	</head>
	<body style="background: #343434 url('<%=basePath%>resources/images/back.png') repeat;">
		<%@ include file="../nav_top.jsp" %>
		<div style="height: 100%; margin: 80px 0 80px 20px;">
			<!-- tabs left -->
			<div class="tabbable tabs-left">
				<ul class="nav nav-tabs nav-pills col-md-2" style="padding-right: 0; margin-right: 0;">
					<li class="active">
						<a href="#device" title="device" data-toggle="tab">
							设备维护<span class="pull-right"><i class="icon-chevron-right"></i></span>
						</a>
					</li>
					<li>
						<a href="#add" title="add" data-toggle="tab">
							添加设备<span class="pull-right"><i class="icon-chevron-right"></i></span>
						</a>
					</li>
					<li>
						<a href="#company" title="company" data-toggle="tab">
							厂商/批次<span class="pull-right"><i class="icon-chevron-right"></i></span>
						</a>
					</li>
					<li>
						<a href="#info" title="info" data-toggle="tab">
							密码修改<span class="pull-right"><i class="icon-chevron-right"></i></span>
						</a>
					</li>
				</ul>
				<div class="tab-content col-md-10">
					<div class="tab-pane active" id="device">
						<!-- 加载设备管理模块 -->						
					</div>
					<div class="tab-pane" id="add">
						<!-- 加载添加设备模块 -->
					</div>
					<div class="tab-pane" id="company">
						<!-- 加载厂商/批次管理模块 -->
					</div>
					<div class="tab-pane" id="info">
						<!-- 加载个人信息模块，主要是修改密码 -->
					</div>
				</div>
			</div>
		</div>
		<%@ include file="../nav_bottom.jsp" %>
	</body>
</html>