<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
	<head></head>
	<body>
		<table class="table table-striped table-bordered" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>序号</th>
					<th>用户名</th>
					<th>密码</th>
					<th>真实姓名</th>
					<th>服务类型</th>
					<th>用户组别</th>
					<th>档案编号</th>
					<th>手机号</th>
					<th>看过的医生</th>
					<th>设备状态</th>
					<th>设备编号</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody name="userlist">
				<c:if test="${fn:length(ucs) > 0}">
				   	<c:forEach var="uc" items="${ucs}">
				   	<tr>
						<td>${uc.user.id}</td>
						<td>${uc.user.loginname}</td>
						<td>${uc.user.passwd}</td>
						<td>${uc.user.name}</td>
						<td>
							<c:if test="${uc.user.userlevel == 1}">普通</c:if>
							<c:if test="${uc.user.userlevel == 2}">贵宾</c:if>
						</td>
						<td>
							<c:if test="${uc.user.usergroup == 1}">副教授</c:if>
							<c:if test="${uc.user.usergroup == 2}">正高级</c:if>
						</td>
						<td>${uc.user.dstr }</td>
						<td>${uc.user.phone }</td>
						<td>
						<c:if test="${fn:length(uc.doctors) > 0}">
							<c:forEach var="doctor" items="${uc.doctors}">
								${doctor.name },
							</c:forEach>
						</c:if>
						<c:if test="${fn:length(uc.doctors) == 0}">
							-
						</c:if>
						</td>
						<td>
						<c:if test="${fn:length(uc.devices) > 0}">
							<c:if test="${uc.devices[0].dstatus == 3}">已绑定</c:if>
							<c:if test="${uc.devices[0].dstatus != 3}">未绑定</c:if>
						</c:if>
						<c:if test="${fn:length(uc.devices) == 0}">
							-
						</c:if>
						</td>
						<td>
						<c:if test="${fn:length(uc.devices) > 0}">
							${uc.devices[0].dsn}
						</c:if>
						<c:if test="${fn:length(uc.devices) == 0}">
							-
						</c:if>
						</td>
						
						<td>
							<button class="btn btn-success btn-xs" onclick="openEditUserModal(${uc.user.id})">编辑</button>
							<c:if test="${fn:length(uc.devices) == 0}">
								<button class="btn btn-warning btn-xs" onclick="openBoundModal(${uc.user.id})">绑定设备</button>
							</c:if>
							<c:if test="${fn:length(uc.devices) > 0}">
								<button class="btn btn-info btn-xs" onclick="unbound(${uc.user.id})">解除绑定</button>
							</c:if>
							<button class="btn btn-primary btn-xs" onclick="openUploadReportModal(${uc.user.id})">上传体检报告</button>
							<button class="btn btn-danger btn-xs" onclick="removeUser(${uc.user.id})">删除</button>
						</td>
					</tr>
				   	</c:forEach>
				</c:if>						
			</tbody>
		</table>
	</body>
</html>