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
			<span class="col-md-2">${u.phone }</span>
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
					<td>开始时间</td>
					<td>计划周期</td>
					<td>计划类型</td>
					<td>状态</td>
					<td>操作</td>
				</tr>
			</thead>
			<tbody>
			<c:if test="${fn:length(plans) > 0}">
				<c:forEach var="plan" items="${plans}">
			   	<tr>
			   		<td>${plan.id}</td>
			   		<td>${plan.starttime}</td>
			   		<td>
			   			<c:if test="${plan.period == 1}">一周</c:if>
						<c:if test="${plan.period == 2}">两周</c:if>
						<c:if test="${plan.period == 3}">三周</c:if>
						<c:if test="${plan.period == 4}">一个月</c:if>
						<c:if test="${plan.period == 5}">两个月</c:if>
						<c:if test="${plan.period == 6}">三个月</c:if>
			   		</td>
			   		<td>
			   			<c:if test="${plan.type == 1}">测量计划</c:if>
			   			<c:if test="${plan.type == 2}">运动计划</c:if>
			   		</td>
					<td>
						<c:if test="${plan.status == 0}">已失效</c:if>
						<c:if test="${plan.status != 0}">正常</c:if>
					</td>
					<td>
					<c:if test="${plan.status != 0}">
						<button class="btn btn-success btn-xs" onclick="openEditPlanModal(${plan.id})">编辑</button>
						<button class="btn btn-danger btn-xs" onclick="removePlan(${plan.id})">删除</button>
					</c:if>
					</td>
				</tr>
			   	</c:forEach>
			</c:if>
			<tr>
				<td colspan="6">
					<button class="btn btn-success btn-block" onclick="openSavePlanModal()">新建</button>
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