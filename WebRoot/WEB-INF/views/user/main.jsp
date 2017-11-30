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
		</style>
		<!-- JS -->
		<script type="text/javascript" src="<%=basePath%>resources/js/jquery.js"></script>
		<script type="text/javascript" src="<%=basePath%>resources/bootstrap-3.0.3/js/bootstrap.js"></script>
		<script type="text/javascript" src="<%=basePath%>resources/bootstrap-3.0.3/datetimepicker/js/bootstrap-datetimepicker.js"></script>
		<script type="text/javascript" src="<%=basePath%>resources/bootstrap-3.0.3/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
		<script type="text/javascript" src="<%=basePath%>resources/DataTables-1.10.4/media/js/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>resources/DataTables-1.10.4/examples/resources/bootstrap/3/dataTables.bootstrap.js"></script>
		<script type="text/javascript" src="<%=basePath%>resources/Highcharts-4.0.1/js/highcharts.js"></script>
		<script type="text/javascript" src="<%=basePath%>resources/js/messager.js"></script>
		<script type="text/javascript" src="<%=basePath%>resources/js/util.js"></script>
		<script type="text/javascript">
		var host = "<%=basePath%>user/";
		$(function() {
			loadSpecificPage("record");
			loadUnreadMsgNumber();
			setInterval(function() {
				// load record page
				loadSpecificPage("record");
				// load unreadRemindNumber
				loadUnreadMsgNumber();
			}, period); // 10 minutes
			// bind switching action of nav-tabs
			$("a[data-toggle='tab']").on("show.bs.tab", function (e) {
				$("#" + e.relatedTarget.title).html("");
				loadSpecificPage(this.title);
			});
		});
		function loadSpecificPage(page) {
			$("#" + page).load(host + page + "/", function() {
				if(page == "msg") {
					loadUnreadMsgNumber();
//					updateMsgStatus();
				}
			});
		}
		function loadUnreadMsgNumber() {
			var total = 0;
			$.ajax({
				url: host + "remindNumber/1/",
				async: false,
				dataType: "json",
				success: function(data) {
					total += data;
					$("#unreadRemindNumber").html(data);
				}
			});
			$.ajax({
				url: host + "planNumber/1/",
				async: false,
				dataType: "json",
				success: function(data) {
					total += data;
					$("#unreadPlanNumber").html(data);
				}
			});
			$("#unreadMsgNumber").html(total);
		}
		</script>
	</head>
	<body style="background: #343434 url('<%=basePath%>resources/images/back.png') repeat;">
		<%@ include file="../nav_top_user.jsp" %>
		<div style="height: 100%; margin: 80px 0 80px 20px;">
			<!-- tabs left -->
			<div class="tabbable tabs-left">
				<ul class="nav nav-tabs nav-pills col-md-2" style="padding-right: 0; margin-right: 0;">
					<li class="active">
						<a href="#record" title="record" data-toggle="tab">
							自检记录<span class="pull-right"><i class="icon-chevron-right"></i></span>
						</a>
					</li>
					<li>
						<a href="#health" title="health" data-toggle="tab">
							健康咨询<span class="pull-right"><i class="icon-chevron-right"></i></span>
						</a>
					</li>
					<li>
						<a href="#report" title="report" data-toggle="tab">
							综合报告<span class="pull-right"><i class="icon-chevron-right"></i></span>
						</a>
					</li>
					<!--  
					<li>
						<a href="#device" title="device" data-toggle="tab">
							名下设备<span class="pull-right"><i class="icon-chevron-right"></i></span>
						</a>
					</li>
					-->
					<li>
						<a href="#msg" title="msg" data-toggle="tab">
							系统消息<span class="pull-right"><i class="icon-chevron-right"></i></span>
						</a>
					</li>
					<li>
						<a href="#info" title="info" data-toggle="tab">
							密码修改<span class="pull-right"><i class="icon-chevron-right"></i></span>
						</a>
					</li>
				</ul>
				<div class="tab-content col-md-10">
					<div class="tab-pane active" id="record">
						<!-- 加载自检记录模块 -->						
					</div>
					<div class="tab-pane" id="device">
						<!-- 加载名下设备模块 -->
					</div>
					<div class="tab-pane" id="health">
						<!-- 加载健康咨询模块 -->
					</div>
					<div class="tab-pane" id="report">
						<!-- 加载综合报告模块 -->
					</div>
					<div class="tab-pane" id="msg">
						<!-- 加载系统消息模块 -->
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