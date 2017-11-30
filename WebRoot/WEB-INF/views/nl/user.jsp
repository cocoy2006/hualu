<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript">
		$(function() {
			$(":radio[name='doctorid']").click(function() {
				// change style
				$(".multiline-label").removeClass(c).addClass(d);
				$(this).parent().removeClass(d).addClass(c);
				// filte devices
				filterUsers(filterUsersData());
			});
			$(":radio[name='userlevel'], :radio[name='usergroup']").click(function() {
				// change style
				$(this).parent("label").removeClass(d).addClass(c)
					.siblings().removeClass(c).addClass(d);
				// filte users
				filterUsers(filterUsersData());
			});
			filterUsers();
		});
		function filterUsers(data) {
			$("#tableContainer").load(host + "userlist/", data, function() {
				dispDataTable("table");
				$(".redirectHolder").hide();
			});
		}
		function filterUsersData() {
			var name = $("#name").val();
			var loginname = $("#loginname").val();
			var doctorid = $(".label-danger input[name='doctorid']").val();
			var userlevel = $(".label-danger input[name='userlevel']").val();
			var usergroup = $(".label-danger input[name='usergroup']").val();
			var data = "";
			if(name) data += "name=" + name + "&";
			if(loginname) data += "loginname=" + loginname + "&";
			if(doctorid) data += "doctorid=" + doctorid + "&";
			if(userlevel) data += "userlevel=" + userlevel + "&";
			if(usergroup) data += "usergroup=" + usergroup + "&";
			return data;
		}
		function openSaveUserModal() {
			var url = host + "userModal/1/";
			$("#modalContainer").load(url, function() {
				$(".modal").modal("show");
			});
		}
		function openEditUserModal(id) {
			var url = host + "userModal/2/";
			$("#modalContainer").load(url, {id: id}, function() {
				$(".modal").modal("show");
			});
		}
		function openBoundModal(userid) {
			var url = host + "boundModal/" + userid + "/";
			$("#modalContainer").load(url, function() {
				$(".modal").modal("show");
			});
		}
		function unbound(userid) {
			if(confirm(" 您确定要解除绑定吗? ")) {
				$.ajax({
					url: host + "unbound/",
					data: "userid=" + userid,
					dataType: "json",
					type: "POST",
					success: function(data) {
						if(data == "1") {
							success();
							filterUsers();
						} else {
							danger(data);
						}
					}
				});
			}
		}
		function openUploadReportModal(userid) {
			var url = host + "reportModal/" + userid + "/";
			$("#modalContainer").load(url, function() {
				$(".modal").modal("show");
			});
		}
		function removeUser(id) {
			if(confirm(" 确定删除该用户？ ")) {
				$.ajax({
					url: "<%=basePath %>user/remove/" + id + "/",
					dataType: "json",
					success: function(data) {
						if(data == "1") {
							success();
							filterUsers(filterUsersData());
						} else if(data == "-1"){
							danger();
						} else {
							danger(data);
						}
					}
				});
			}
		}
		</script>
	</head>
	<body>
		<div class="panel panel-primary">
			<div class="panel-heading">
				<i class="icon-table"></i>&nbsp;用户管理
				<button class="btn btn-warning btn-xs" style="margin-left:20px;" 
					onclick="openSaveUserModal()">新建客户</button>
				<button class="btn btn-info btn-xs js_downloadModel" style="margin-left:20px;" disabled>下载导入模板</button>
				<button class="btn btn-info btn-xs js_loadModel" style="margin-left:20px;" disabled>批量导入</button>
			</div>
			<div class="panel-body">
				<div class="form-horizontal" role="form">
					<!-- 主治医生筛选项 -->
					<div>
						<label class="control-label col-md-1">
							医生
						</label>
						<p class="col-md-11">
							<label class="radio-inline label label-danger multiline-label">
								<input type="radio" name="doctorid"
									value="0" checked style="display: none;">
								全部										
							</label>
						</p>
						<c:if test="${doctors != null}">
						   	<c:forEach var="doctor" items="${doctors}" varStatus="i">
							   	<c:if test="${i.index % 10 == 0}">
							   	<p class="col-md-offset-1 col-md-11">
							   	</c:if>
								   	<label class="radio-inline label label-default col-md-1 multiline-label" title="${doctor.name}">
										<input type="radio" name="doctorid"
											value="${doctor.id}" style="display: none;">
										${doctor.name}										
									</label>
								<c:if test="${i.index % 10 == 9 || i.last == true}">
							   	</p>
							   	</c:if>
						   	</c:forEach>
						</c:if>
					</div>
					<!-- 服务类型筛选项 -->
					<div>
						<label class="control-label col-md-1">
							类型
						</label>
						<p class="col-md-11">
							<label class="radio-inline label label-danger">
								<input type="radio" name="userlevel"
									value="0" checked style="display: none;">
								全部										
							</label>
							<label class="radio-inline label label-default">
								<input type="radio" name="userlevel"
									value="1" style="display: none;">
								普通									
							</label>
							<label class="radio-inline label label-default">
								<input type="radio" name="userlevel"
									value="2" style="display: none;">
								贵宾									
							</label>
						</p>
					</div>
					<!-- 用户组别筛选项 -->
					<div>
						<label class="control-label col-md-1">
							组别
						</label>
						<p class="col-md-11">
							<label class="radio-inline label label-danger">
								<input type="radio" name="usergroup"
									value="0" checked style="display: none;">
								全部										
							</label>
							<label class="radio-inline label label-default">
								<input type="radio" name="usergroup"
									value="1" style="display: none;">
								副教授											
							</label>
							<label class="radio-inline label label-default">
								<input type="radio" name="usergroup"
									value="2" style="display: none;">
								正高级								
							</label>
						</p>
					</div>
					<div>
						<label class="control-label col-md-1">
							姓名
						</label>
						<p class="col-md-3">
							<input type="text" id="name" class="form-control" 
								placeholder="在此输入用户姓名" />
						</p>
						<label class="control-label col-md-1">
							编号
						</label>
						<p class="col-md-3">
							<input type="text" id="loginname" class="form-control" 
								placeholder="在此输入用户编号" />
						</p>
						<p class="col-md-4">
							<button class="btn btn-success" onclick="filterUsers(filterUsersData())">检索用户</button>
						</p>
					</div>
				</div>
				<form style="display:none;" class="js_hiddenForm" action="#" method="post" enctype="multipart/form-data" target="_self">
				  <input type="file" id="upfile" name="group_load" />
				</form>
				<div id="tableContainer"></div>
			</div>
		</div>
		<div id="modalContainer"></div>
	</body>
</html>