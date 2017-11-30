<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript">
		function update() {
			var oldPasswd = $("#oldPasswd").val();
			var newPasswd = $("#newPasswd").val();
			var newPasswdAgain = $("#newPasswdAgain").val();
			if(oldPasswd && newPasswd && newPasswdAgain) {
				if(oldPasswd != "${sessionScope.user.passwd}") {
					messager(" 原始密码不匹配，请重新输入 ", "danger", 2000);
					return false;
				}
				if(newPasswd != newPasswdAgain) {
					messager(" 两次输入的密码不匹配 ", "danger", 2000);
					return false;
				}
				$.ajax({
					url: "<%=basePath%>operator/update/",
					data: "newPasswd=" + newPasswd,
					dataType: "json",
					type: "POST",
					success: function(data) {
						if(data == "1") {
							messager(" 密码修改成功，需要重新登录 ", "success", 1500);
							setTimeout(function() {
								location = "<%=basePath%>operator/signout/";
							}, 1400);
						} else {
							messager(data, "danger", 2000);
						}
					}
				});
			} else {
				messager(" 密码不能为空 ", "danger", 2000);
			}
		}
		</script>
	</head>
	<body>
		<div class="panel panel-primary">
			<div class="panel-heading">
				<i class="icon-table"></i>&nbsp;密码修改
			</div>
			<div class="panel-body">
				<div class="form-horizontal" role="form">
					<div>
						<label class="control-label col-md-2">
							原始密码
						</label>
						<p class="col-md-10">
							<input type="password" id="oldPasswd" class="form-control" placeholder="请输入原始密码" />
						</p>
						<label class="control-label col-md-2">
							新设密码
						</label>
						<p class="col-md-10">
							<input type="password" id="newPasswd" class="form-control" placeholder="6~10位数字、字母、下划线组成" />
						</p>
						<label class="control-label col-md-2">
							重复密码
						</label>
						<p class="col-md-10">
							<input type="password" id="newPasswdAgain" class="form-control" placeholder="请重复新设密码" />
						</p>
						<p class="col-md-10 col-md-offset-2">
							<button class="btn btn-success btn-block" onclick="update()">提交</button>
						</p>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>