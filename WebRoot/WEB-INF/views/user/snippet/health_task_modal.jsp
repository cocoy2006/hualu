<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
	<head>
	<script type="text/javascript">
	$(function() {
		modal("health");
	});
	</script>
	</head>
	<body>		
		<!-- 健康咨询的模态框 -->
		<div class="modal fade" role="dialog" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
						<h4 class="modal-title">健康咨询</h4>
					</div>
					<div class="modal-body">
						<form name="healthForm" class="form-horizontal" role="form"
							style="height: 150px;">
							<label class="control-label col-md-3">
								疾病名称
							</label>
							<p class="col-md-9">${htc.healthTask.disname}</p>
							<label class="control-label col-md-3">
								病情描述
							</label>
							<p class="col-md-9">${htc.healthTask.disscript}</p>
							<label class="control-label col-md-3">
								已采取措施
							</label>
							<p class="col-md-9">${htc.healthTask.donetreat}</p>
							<label class="control-label col-md-3">
								希望得到的帮助
							</label>
							<p class="col-md-9">${htc.healthTask.desireaid}</p>
							<label class="control-label col-md-3">
								医生建议
							</label>
							<p class="col-md-9">${htc.doctorHealthTask.advice}</p>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default btn-block" data-dismiss="modal">返回</button>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
	</body>
</html>