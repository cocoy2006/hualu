<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head></head>
	<body>
		<!-- 置顶导航栏 -->
		<nav class="navbar navbar-default navbar-fixed-top">
			<div class="navbar-header">
				<img style="width: 168px; margin-left: 15px;" 
					src="<%=basePath%>resources/images/hualu_logo.png">
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav pull-right">
					<li>
						<a href="javascript:void(0);">
							<i class="icon-user"></i>&nbsp;${user.name },&nbsp;您好
						</a>
					</li>
					<li class="dropdown">
						<a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown">
							<i class="icon-envelope"></i>&nbsp;系统消息
							<span id="unreadMsgNumber" class="label label-danger"></span>
						</a>
						<ul class="dropdown-menu" role="menu">
				            <li>
				            	<a href="javascript:void(0);" onclick="showMsg('1')">
				            		<i class="icon-envelope"></i>&nbsp;健康干预消息
				            		<span id="unreadRemindNumber" class="label label-danger"></span>
				            	</a>
				            </li>
				            <li class="divider"></li>
				            <li>
				            	<a href="javascript:void(0);" onclick="showMsg('2')">
				            		<i class="icon-envelope"></i>&nbsp;健康计划消息
				            		<span id="unreadPlanNumber" class="label label-danger"></span>
				            	</a>
				            </li>
			          	</ul>
					</li>
					<li class="active"><a href="<%=basePath%>user/signout/"><i class="icon-off"></i>&nbsp;退出</a></li>
				</ul>
			</div>
		</nav>
		<script type="text/javascript">
			function showMsg(sub) {
				$(".nav a[href='#msg']").tab("show");
				$("a[href='#" + sub + "']").tab("show");
			}
		</script>
	</body>
</html>