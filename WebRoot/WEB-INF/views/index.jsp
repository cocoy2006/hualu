<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
		<!-- JS -->
		<script type="text/javascript" src="<%=basePath%>resources/js/jquery.js"></script>
		<script type="text/javascript" src="<%=basePath%>resources/bootstrap-3.0.3/js/bootstrap.js"></script>
		<script type="text/javascript" src="<%=basePath%>resources/js/messager.js"></script>
		<script type="text/javascript">
		$(function() {});
		$(document).bind("keydown", function(event) {
			var button = $(".defaultButton");
	        if(event.keyCode == 13) {
		        button.click();
		        event.returnValue = false;
	        }
		});
		function signin() {
			var loginname = $("#loginname").val();
			var passwd = $("#passwd").val();
			if(loginname && passwd) {
				$.ajax({
					url: "<%=basePath%>user/signin/",
					data: $("#signinForm").serialize(),
					dataType: "json",
					type: "POST",
					success: function(data) {
						switch(data) {
							case 1:
								location.href = "<%=basePath%>user/main/";
								break;
							default:
								messager(" 用户名或密码错误，请重新输入 ", "danger", 2000);
								break;
						}
					}, error: function(data) {
						messager(data, "danger", 2000);
					}
				});
			} else {
				messager(" 用户名和密码不能为空 ", "danger", 2000);
			}
		}
		</script>
	</head>
	<body style="background: #343434 url('<%=basePath%>resources/images/back.png') repeat;">
		<div class="container" style="margin: 50px auto; max-width: 600px;">
			<div class="row">
				<div><img style="width:438px; margin-left:35px;" src="<%=basePath%>resources/images/hualu_logo.png"></div>
				<div class="col-sm-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<i class="icon-lock">&nbsp;VIP登录</i>
						</div>
						<div class="panel-body">
							<form id="signinForm" class="form-horizontal" role="form">
								<!-- Login name -->
								<div class="form-group">
									<label for="loginname" class="control-label col-sm-3">
										用户名
									</label>
									<div class="col-sm-9">
										<input type="text" id="loginname" name="loginname"
											class="form-control">
										<script type="text/javascript">
											document.getElementById("loginname").focus();
										</script>
									</div>
								</div>
								<!-- Password -->
								<div class="form-group">
									<label for="passwd" class="control-label col-sm-3">
										密码
									</label>
									<div class="col-sm-9">
										<input type="password" id="passwd" name="passwd"
											class="form-control">
									</div>
								</div>
								<!-- Remeber me -->
								<!--
								<div class="form-group">
									<div class="col-sm-9 col-sm-offset-3">
										<div class="checkbox">
											<label>
												<input type="checkbox">
												记住我
											</label>
										</div>
									</div>
								</div>
								-->
							</form>
							<button class="defaultButton btn btn-success btn-block" 
								onclick="signin()">登录</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>