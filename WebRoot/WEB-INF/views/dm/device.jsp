<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<style type="text/css">
			.control-label {
				padding-left:0;
			}
		</style>
		<script type="text/javascript">
		$(function() {
			$(":radio[name='companyid']").click(function() {
				// change style
				$(".multiline-label").removeClass(c).addClass(d);
				$(this).parent().removeClass(d).addClass(c);
				// filte devices
				filterDevices(filterDevicesData());
			});
			$(":radio[name='dstatus']").click(function() {
				// change style
				$(this).parent("label").removeClass(d).addClass(c)
					.siblings().removeClass(c).addClass(d);
				// filte devices
				filterDevices(filterDevicesData());
			});
			filterDevices();
		});
		function filterDevices(data) {
			$("#tableContainer").load(host + "devicelist/", data, function() {
				dispDataTable("table");
			});
		}
		function filterDevicesData() {
			var dmodel = $("#dmodel").val();
			var dsn = $("#dsn").val();
			var dname = $("#dname").val();
			var comid = $(".label-danger input[name='companyid']").val();
			var dstatus = $(".label-danger input[name='dstatus']").val();
			var data = "";
			if(dmodel) data += "dmodel=" + dmodel + "&";
			if(dsn) data += "dsn=" + dsn + "&";
			if(dname) data += "dname=" + dname + "&";
			if(comid) data += "comid=" + comid + "&";
			if(dstatus) data += "dstatus=" + dstatus + "&";
			return data;
		}
		function openCheckoutModal(id) {
			var url = host + "checkoutModal/" + id + "/";
			$("#modalContainer").load(url, function() {
				// init datetimepicker
				$("input[name='occupationtime']").datetimepicker({
					language:  'zh-CN',
					endDate: new Date(),
					todayBtn: true,
					todayHighlight: true,
					minuteStep: 3
				});
				// show modal
				$(".modal").modal("show");
			})
		}
		function checkout() {
			var nurseid = $(":checked[name='nurseid']").val();
			if(nurseid) {
				$.ajax({
					url: host + "checkout/",
					data: $("form[name='checkoutForm']").serialize(),
					dataType: "json",
					type: "POST",
					success: function(data) {
						success();
						$(".modal").modal("hide");
						filterDevices();
					}
				});
			} else {
				danger(" 请指定客服 ");
			}
		}
		</script>
	</head>
	<body>
		<div class="panel panel-primary">
			<div class="panel-heading">
				<i class="icon-table"></i>&nbsp;设备维护
			</div>
			<div class="panel-body">
				<div class="form-horizontal" role="form">
					<div>
						<label class="control-label col-md-1">
							设备厂商
						</label>
						<p class="col-md-11">
							<label class="radio-inline label label-danger multiline-label">
								<input type="radio" name="companyid"
									value="0" checked style="display: none;">
								全部										
							</label>
						</p>
						<c:if test="${companys != null}">
						   	<c:forEach var="company" items="${companys}" varStatus="i">
							   	<c:if test="${i.index % 10 == 0}">
							   	<p class="col-md-offset-1 col-md-11">
							   	</c:if>
								   	<label class="radio-inline label label-default col-md-1 multiline-label" title="${company.name}">
										<input type="radio" name="companyid"
											value="${company.id}" style="display: none;">
										${company.name}										
									</label>
								<c:if test="${i.index % 10 == 9 || i.last == true}">
							   	</p>
							   	</c:if>
						   	</c:forEach>
						</c:if>
					</div>
					
					<div>
						<label class="control-label col-md-1">
							设备状态
						</label>
						<p class="col-md-11">
							<label class="radio-inline label label-danger">
								<input type="radio" name="dstatus"
									value="0" checked style="display: none;">
								全部										
							</label>
							<label class="radio-inline label label-default">
								<input type="radio" name="dstatus"
									value="1" style="display: none;">
								未使用
							</label>
							<label class="radio-inline label label-default">
								<input type="radio" name="dstatus"
									value="2" style="display: none;">
								已领用
							</label>
							<label class="radio-inline label label-default">
								<input type="radio" name="dstatus"
									value="3" style="display: none;">
								已绑定
							</label>
						</p>
					</div>

					<div>
						<label class="control-label col-md-1">
							设备型号
						</label>
						<p class="col-md-2">
							<input type="text" id="dmodel" class="form-control" 
								placeholder="在此输入设备型号" />
						</p>
						<label class="control-label col-md-1">
							设备编号
						</label>
						<p class="col-md-2">
							<input type="text" id="dsn" class="form-control" 
								placeholder="在此输入设备编号" />
						</p>
						<label class="control-label col-md-1">
							设备名称
						</label>
						<p class="col-md-2">
							<input type="text" id="dname" class="form-control" 
								placeholder="在此输入设备名称" />
						</p>
						<p class="col-md-3">
							<button class="btn btn-success" onclick="filterDevices(filterDevicesData())">检索设备</button>
						</p>
					</div>
				</div>
				<div id="tableContainer"></div>
			</div>
		</div>
		<div id="modalContainer"></div>
	</body>
</html>