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
	function update(doctorHealthTaskId, mode) {
		var advice = $("textarea[name='advice']").val();
		if(advice) {
			$.ajax({
				url: host + "healthTask/update/" + doctorHealthTaskId + "/" + mode + "/",
				data: "advice=" + advice,
				dataType: "json",
				type: "POST",
				success: function(data) {
					$(".modal").modal("hide");
//					loadSpecificPage("health");
				}
			});
		}
	}
	</script>
	</head>
	<body>		
		<!-- 健康咨询的模态框 -->
		<div class="modal fade" role="dialog" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
						<h4 class="modal-title">健康咨询</h4>
					</div>
					<div class="modal-body">
						<form name="healthForm" class="form-horizontal" role="form"
							style="height: 300px; padding-top: 10px;">
							<label class="control-label col-md-2">
								疾病名称
							</label>
							<p class="col-md-10">
								<input type="text" class="form-control" value="${htc.healthTask.disname}" disabled>
							</p>
							<label class="control-label col-md-2">
								病情描述
							</label>
							<p class="col-md-10">
								<textarea class="form-control" rows="3" disabled>${htc.healthTask.disscript}</textarea>
							</p>
							<label class="control-label col-md-2">
								已采取措施
							</label>
							<p class="col-md-10">
								<input type="text" class="form-control" value="${htc.healthTask.donetreat}" disabled>
							</p>
							<label class="control-label col-md-2">
								希望得到的帮助
							</label>
							<p class="col-md-10">
								<input type="text" class="form-control" value="${htc.healthTask.desireaid}" disabled>
							</p>
							<label class="control-label col-md-2">
								医生建议
							</label>
							<p class="col-md-10">
								<textarea class="form-control" name="advice" rows="3" 
									placeholder="在此输入您的建议">${htc.doctorHealthTask.advice}</textarea>
							</p>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" name="update"
							onclick="update(${htc.doctorHealthTask.id}, 3)">存草稿箱</button>
						<button type="button" class="btn btn-success" name="update"
							onclick="update(${htc.doctorHealthTask.id}, 2)">提交</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
	</body>
</html>