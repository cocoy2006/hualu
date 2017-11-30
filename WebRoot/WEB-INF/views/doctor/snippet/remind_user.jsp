<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
	<head></head>
	<body>
		<h3 class="text-center">会员账号信息</h3>
	<c:if test="${u != null}">
		<input type="hidden" name="uid" value="${u.id }"/>
	   	<p class="col-md-12">
			<label class="col-md-2">登录账号</label>
			<span class="col-md-2">${u.loginname }</span>
			<label class="col-md-2">姓名</label>
			<span class="col-md-2">${u.name }</span>
			<label class="col-md-2">手机</label>
			<span class="col-md-2">${u.loginname }</span>
		</p>
		<p class="col-md-12">
			<label class="col-md-2">邮箱</label>
			<span class="col-md-2">${u.email }</span>
			<label class="col-md-2">会员号</label>
			<span class="col-md-2">${u.id }</span>
			<label class="col-md-2">服务有效期</label>
			<span class="col-md-2">终身有效</span>
		</p>
		<table class="table table-striped table-bordered" 
				cellspacing="0" width="100%">
			<thead>
				<tr>
					<td>编号</td>
					<td>提醒时间</td>
					<td>提醒周期</td>
					<td>提醒内容</td>
					<td>状态</td>
					<td>操作</td>
				</tr>
			</thead>
			<tbody>
			<c:if test="${fn:length(reminds) > 0}">
				<c:forEach var="remind" items="${reminds}">
			   	<tr>
			   		<td>${remind.id}</td>
					<td>${remind.starttime}</td>
					<td>
						<c:if test="${remind.cycle == 1}">每天</c:if>
						<c:if test="${remind.cycle == 2}">每2天</c:if>
						<c:if test="${remind.cycle == 3}">每3天</c:if>
						<c:if test="${remind.cycle == 7}">每周</c:if>
					</td>
					<td><textarea rows="1" class="form-control" disabled>${remind.content}</textarea></td>
					<td>
						<c:if test="${remind.status == 0}">已失效</c:if>
						<c:if test="${remind.status != 0}">正常</c:if>
					</td>
					<td>
					<c:if test="${remind.status != 0}">
						<button class="btn btn-success btn-xs" onclick="openEditRemindModal(${remind.id})">编辑</button>
						<button class="btn btn-danger btn-xs" onclick="removeRemind(${remind.id})">删除</button>
					</c:if>
					</td>
				</tr>
			   	</c:forEach>
			</c:if>
			<tr>
				<td colspan="6">
					<button class="btn btn-success btn-block" onclick="openSaveRemindModal()">新建</button>
				</td>
			</tr>
			</tbody>
		</table>
	</c:if>
	<c:if test="${u == null}">
		<p class="col-md-12">选择用户后显示</p>
	</c:if>
	</body>
</html>