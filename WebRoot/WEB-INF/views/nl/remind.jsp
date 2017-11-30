<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript">
		function openSelectUserModal() {
			var url = host + "selectUser/";
			$("#modalContainer").load(url, function() {
				$(".modal").modal("show");
				$("tr.user").on("click", function() {
					$("input[name='userid']").val(this.title);
					loadUserInfo(this.title);
					$(".modal").modal("hide");
				});
			});
		}
		function loadUserInfo(userid) {
			$("#userInfo").load(host + "remindUser/" + userid + "/");
		}
		function openSaveRemindModal() {
			var url = host + "remindModal/1/";
			$("#modalContainer").load(url, function() {
				$(".modal").modal("show");
			});
		}
		function openEditRemindModal(id) {
			var url = host + "remindModal/2/";
			$("#modalContainer").load(url, {id: id}, function() {
				$(".modal").modal("show");
			});
		}
		function removeRemind(id) {
			if(confirm(" 确定删除吗? ")) {
				$.ajax({
					url: "<%=basePath%>remind/remove/" + id + "/",
					dataType: "json",
					success: function(data) {
					 	if(data == "1") {
							messager(" 操作成功 ", "success", 2000);
							loadUserInfo(id);
						} else {
							messager(data, "danger", 2000);
						}
				 	}
				});
			}
			return false;
		}
		</script>
	</head>
	<body>
		<div class="panel panel-primary">
			<div class="panel-heading">
				<i class="icon-table"></i>&nbsp;健康干预
			</div>
			<div class="panel-body">
				<p class="col-md-12">
					<button class="btn btn-info" onclick="openSelectUserModal()">选择用户</button>
					<span id="selectedUserInfo" style="line-height: 34px; padding-left: 20px;">
						<c:if test="${htc.user.name != null}">已选择用户:${htc.user.name}</c:if>
					</span>
				</p>
				<div id="userInfo"><!-- 加载选中用户的信息 --></div>
			</div>
		</div>
		<div id="modalContainer"><!-- .modal --></div>
	</body>
</html>