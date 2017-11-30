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
			.package {
				cursor: pointer;
			}
		</style>
		<script type="text/javascript">
		var comid = 0, packageId = 0;
		$(function() {
			// 筛选
			$(":radio[name='companyid']").click(function() {
				$(".multiline-label").removeClass(c).addClass(d);
				$(this).parent().removeClass(d).addClass(c);
				comid = this.value;
			});
			// 选择批次
			$("#selectPackageModal").on("shown.bs.modal", function(e) {
				$("tr.package").click(function() {
					// packageId
					packageId = this.title;
					// show selected package
					$(".modal").modal("hide");
					var selectedPackage = $("tr[title='" + this.title + "']")
						.clone().removeAttr("title").removeAttr("class");
					$("#selectedPackage").find("tbody").html(selectedPackage).end().show();
					// show devices according to packageId and companyId
					filterDevicelistForAdd();
				});
			});
		});
		function filterPackagesForAdd(data) {
			$("tbody[name='packagelistForAdd']").load(host + "packagelistForAdd/", data);
		}
		function filterPackagesForAddData() {
			var data = "";
			if(comid) {
				data += "comid=" + comid;
			}
			return data;
		}
		function filterDevicelistForAdd() {
			var data = "";
			if(packageId) {
				data += "packageId=" + packageId;
			}
			$("#devicelistForAdd")
				.find("tbody").load(host + "devicelistForAdd/", data).end()
				.show();
		}
		function openSelectPackageModal() {
			filterPackagesForAdd(filterPackagesForAddData());
			$("#selectPackageModal").modal("show");
		}
		function openManualModal() {
			$("#manualModal").modal("show");
		}
		function saveDeviceSingle() {
			var dsn = $("#dsnForAdd").val();
			if(packageId && dsn) {
				$.ajax({
					url: "<%=basePath%>device/save/",
					data: "packageId=" + packageId + "&dsn=" + dsn,
					dataType: "json",
					type: "POST",
					success: function(data) {
						filterDevicelistForAdd();
					}
				});
			}
		}
		function openBatchModal() {
			$("#batchModal").modal("show");
		}
		</script>
	</head>
	<body>
		<div class="panel panel-primary">
			<div class="panel-heading">
				<i class="icon-table"></i>&nbsp;添加设备
			</div>
			<div class="panel-body">
				<div class="form-horizontal" role="form">
					<!-- 选择设备厂商 -->
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
					<!-- 选择设备批次 -->
					<div>
						<label class="control-label col-md-1">
							设备批次
						</label>
						<p class="col-md-11">
							<button class="btn btn-success" onclick="openSelectPackageModal()">点击选择</button>
						</p>
						<table class="table table-striped table-bordered" 
							id="selectedPackage" cellspacing="0" width="100%" style="display: none;">
							<thead>
								<tr>
									<th>厂商</th>
									<th>名称</th>
									<th>型号</th>
									<th>IP</th>
									<th>描述</th>
									<th>图片</th>
								</tr>
							</thead>
							<tbody>
								<!-- 展示已选择的设备批次 -->
							</tbody>
						</table>
					</div>
					<!-- 输入设备编号 -->
					<div>
						<label class="control-label col-md-2">
							设备编号（即sn码）
						</label>
						<p class="col-md-10">
							<button class="btn btn-info" onclick="openManualModal()">手工输入</button>
							<button class="btn btn-info" onclick="openBatchModal()" disabled>批量导入</button>
						</p>
						<table class="table table-striped table-bordered" 
							id="devicelistForAdd" cellspacing="0" width="100%" style="display: none;">
							<thead>
								<tr>
									<th>序号</th>
									<th>型号</th>
									<th>编号</th>
									<th>名称</th>
									<th>厂商</th>
									<th>入库时间</th>
									<th>IP</th>
									<th>状态</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td colspan="8">Loading...</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<!-- 选择设备批次的模态框 -->
		<div id="selectPackageModal" class="modal fade" role="dialog" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
						<h4 class="modal-title">点击选择批次</h4>
					</div>
					<div class="modal-body">
						<table class="table table-striped table-bordered" 
							cellspacing="0" width="100%">
							<thead>
								<tr>
									<th>厂商</th>
									<th>名称</th>
									<th>型号</th>
									<th>IP</th>
									<th>描述</th>
									<th>图片</th>
								</tr>
							</thead>
							<tbody name="packagelistForAdd">
								<tr>
									<td colspan="6">Loading...</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
		<!-- 手工输入SN码的模态框 -->
		<div id="manualModal" class="modal fade" role="dialog" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
						<h4 class="modal-title">输入设备编号（SN码）</h4>
					</div>
					<div class="modal-body" style="height: 115px;">
						<div class="form-horizontal" role="form">
							<label class="control-label col-md-2">
								SN码
							</label>
							<p class="col-md-10">
								<input type="text" id="dsnForAdd" class="form-control">
							</p>
							<div class="col-md-2">
								<button type="button" class="btn btn-default btn-block" data-dismiss="modal">返回</button>
							</div>
							<div class="col-md-10">
								<button type="button" class="btn btn-success btn-block" data-dismiss="modal" 
									onclick="saveDeviceSingle()">提交</button>
							</div>
						</div>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
		<!-- 批量导入SN码的模态框 -->
		<div id="batchModal" class="modal fade" role="dialog" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
						<h4 class="modal-title">
							导入文本文件
							<small>支持纯文本文件，每行一个设备编号</small>
						</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" role="form" action="#" method="get">
							<div>
								<label class="control-label col-md-2">
									选择文件
								</label>
								<p class="col-md-10">
									<input type="file" class="form-control">
								</p>
							</div>
						</form>
					</div>
					<div class="modal-footer" style="border-top: none;">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
	</body>
</html>