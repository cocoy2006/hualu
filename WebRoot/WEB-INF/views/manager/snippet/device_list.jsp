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
					<th>型号</th>
					<th>编号</th>
					<th>名称</th>
					<th>厂商</th>
					<th>入库时间</th>
					<th>领用的客服</th>
					<th>绑定的用户</th>
				</tr>
			</thead>
			<tbody name="devicelist">
				<c:if test="${fn:length(dcs) > 0}">
				   	<c:forEach var="dc" items="${dcs}">
					   	<c:if test="${dc.device.dstatus != 1}">
				   		<tr>
							<td>${dc.device.did}</td>
							<td>${dc.devicePackage.dmodel}</td>
							<td>${dc.device.dsn}</td>
							<td>${dc.devicePackage.dname}</td>
							<td>${dc.company.name}</td>
							<td>${dc.device.ddatetime}</td>
							<td>
							<c:if test="${dc.nurse != null}">
								<a href="javascript:void(0);" title=" 点击更换客服 "
									onclick="openSelectNurseModal(${dc.device.did})">${dc.nurse.name}</a>
							</c:if>
							<c:if test="${dc.nurse == null}">
								<i class="text-muted">未领用</i>
							</c:if>
							</td>
							<td>
							<c:if test="${dc.user != null}">
								<!-- <a href="javascript:void(0);" title=" 点击更换用户 "
									onclick="openSelectUserModal(${dc.device.did})">${dc.user.name}</a> -->
								${dc.user.name}
							</c:if>
							<c:if test="${dc.user == null}">
								<i class="text-muted">未绑定</i>
							</c:if>
							</td>
						</tr>
					   	</c:if>
				   	</c:forEach>
				</c:if>
			</tbody>
		</table>
	</body>
</html>