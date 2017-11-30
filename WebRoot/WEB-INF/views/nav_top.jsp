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
					<!--  
					<li>
						<a href="javascript:void(0);" onclick="showMsg()">
							系统消息
						</a>
					</li>
					-->
					<li role="presentation" class="dropdown">
					    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
					    	<c:if test="${user.isonline == 1}">
				    		<span class="label label-success">在线</span>
					    	</c:if>
					    	<c:if test="${user.isonline == 3}">
				    		<span class="label label-default">休息</span>
					    	</c:if>
					      	&nbsp;<span class="caret"></span>
					    </a>
					    <ul class="dropdown-menu" role="menu">
					      	<li>
								<button class="btn btn-block" onclick="updateIsonline()">
									<c:if test="${user.isonline == 1}">
							    		休息
							    	</c:if>
							    	<c:if test="${user.isonline == 3}">
							    		在线
							    	</c:if>
								</button>
							</li>
					    </ul>
				  	</li>
					<li class="active"><a href="<%=basePath%>operator/signout/"><i class="icon-off"></i>&nbsp;退出</a></li>
				</ul>
			</div>
		</nav>
		<script type="text/javascript">
			function updateIsonline() {
				$.ajax({
					url: "<%=basePath%>operator/updateIsonline/",
					dataType: "json",
					type: "POST",
					success: function(data) {
						location.reload();
					}
				});
			}
			function showRemind() {
				$('.nav a[href="#info"]').tab('show')
			}
		</script>
	</body>
</html>