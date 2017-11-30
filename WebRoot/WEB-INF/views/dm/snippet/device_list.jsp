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
					<!-- <th><input type="checkbox" name="did"/></th> -->
					<th>序号</th>
					<th>型号</th>
					<th>编号</th>
					<th>名称</th>
					<th>厂商</th>
					<th>入库时间</th>
					<th>IP</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody name="devicelist">
				<c:if test="${fn:length(dcs) > 0}">
			   	<c:forEach var="dc" items="${dcs}">
			   	<tr>
					<!-- <td><input type="checkbox" name="did"/></td> -->
					<td>${dc.device.did}</td>
					<td>${dc.devicePackage.dmodel}</td>
					<td>${dc.device.dsn}</td>
					<td>${dc.devicePackage.dname}</td>
					<td>${dc.company.name}</td>
					<td>${dc.device.ddatetime}</td>
					<td>${dc.devicePackage.dip}</td>
					<td>
					<c:if test="${dc.device.dstatus == 1}">
						未使用
					</c:if>
					<c:if test="${dc.device.dstatus == 2}">
						已领用
					</c:if>
					<c:if test="${dc.device.dstatus == 3}">
						已绑定
					</c:if>
					</td>
					<td>
					<c:if test="${dc.device.dstatus == 1}">
						<button class="btn btn-xs btn-success" onclick="openCheckoutModal(${dc.device.did})">出库</button>
					</c:if>
					</td>
				</tr>
			   	</c:forEach>
			</c:if>
			</tbody>
		</table>
	</body>
</html>