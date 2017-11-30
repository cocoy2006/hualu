<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
	<script type="text/javascript">
	function saveOrUpdate() {
		 $.ajax({
			 url: "<%=basePath%>pack/saveOrUpdate/",
			 data: $("form[name='pack']").serialize(),
			 dataType: "json",
			 type: "POST",
			 success: function(data) {
				 if(data == "1") {
					messager(" 操作成功 ", "success", 2000);
					$(".modal").modal("hide");
					filterPackages();
				} else if(data == "-1") {
					messager(data, "danger", 2000);
				}
			 }
		 });
	}
	</script>
	</head>
	<body>		
		<!-- 批次信息的模态框 -->
		<div class="modal fade" role="dialog" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
						<h4 class="modal-title">批次信息</h4>
					</div>
					<div class="modal-body">
						<form name="pack" class="form-horizontal" role="form" action="#" method="post">
							<input type="hidden" name="did" value="${pack.did}"/>
							<input type="hidden" name="comid" value="${company.id}"/>
							<label class="control-label col-md-3">
								厂商名称
							</label>
							<p class="col-md-9">
								<input type="text" class="form-control" readonly 
									name="name" value="${company.name}">
							</p>
							<label class="control-label col-md-3">
								设备名称
							</label>
							<p class="col-md-9">
								<input type="text" class="form-control" name="dname"
									placeholder="在此填写设备名称" value="${pack.dname}">
							</p>
							<label class="control-label col-md-3">
								设备型号
							</label>
							<p class="col-md-9">
								<input type="text" class="form-control" name="dmodel"
									placeholder="在此填写设备型号" value="${pack.dmodel}">
							</p>
							<label class="control-label col-md-3">
								设备服务器IP
							</label>
							<p class="col-md-9">
								<input type="text" class="form-control" name="dip"
									placeholder="在此填写设备服务器IP" value="${pack.dip}">
							</p>
							<label class="control-label col-md-3">
								设备描述
							</label>
							<p class="col-md-9">
								<textarea class="form-control" name="dinfo" rows="4"
									placeholder="在此填写设备描述">${pack.dinfo}</textarea>
							</p>
							<label class="control-label col-md-3">
								设备图片
							</label>
							<p class="col-md-9">
								<input type="text" class="form-control" name="dpic" readonly 
									placeholder="上传的图片名称将在此显示" value="${pack.dpic}">
							</p>
						</form>
					</div>
					<div class="modal-footer" style="border-top: none;">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-primary" onclick="saveOrUpdate()">确定</button>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
	</body>
</html>