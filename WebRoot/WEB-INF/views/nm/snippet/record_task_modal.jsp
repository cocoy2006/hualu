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
		modal("record");
		if("${rtc.user.id}") {
			$("#userinfo").load("<%=basePath%>userinfo/${rtc.user.id}/");
		}
	});
	function next(nurseRecordTaskId) {
		$.ajax({
			url: host + "recordTask/" + nurseRecordTaskId + "/next/",
			dataType: "json",
			type: "POST",
			success: function(data) {
				$(".modal").modal("hide");
			}
		});
	}
	</script>
	</head>
	<body>		
		<!-- 测量数据确认的模态框 -->
		<div class="modal fade" role="dialog" aria-hidden="true">
			<div class="modal-dialog" style="width: 1010px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
						<h4 class="modal-title">自检报告详情</h4>
					</div>
					<div class="modal-body" style="overflow:auto; zoom:1;">
						<div id="userinfo"><!-- 用户信息 --></div>
						<p>
							<label>测量时间：</label>
							${rtc.recordTask.starttime}   ~   ${rtc.recordTask.endtime}
						</p>
						<p>
							<iframe src="<%=basePath%>ymRecordData/detail/${rtc.recordTask.id}/" frameborder="no" border="0" 
								allowtransparency="yes" style="height: 1020px; overflow-y: scroll; width: 100%;"></iframe>
						</p>
						<p class="col-md-2">
							<label>自检摘要：</label>
						</p>
						<p class="col-md-10">
							<label>【编辑时间:${rtc.nurseRecordTask.endtime}】</label>
						</p>
						<p class="col-md-10 col-md-offset-2">${rtc.nurseRecordTask.advice}</p>
						<p class="col-md-2">
							<label>医生建议：</label>
						</p>
						<p class="col-md-10">
							<label>【编辑时间:${rtc.doctorRecordTask.endtime}】</label>
						</p>
						<p class="col-md-10 col-md-offset-2">${rtc.doctorRecordTask.advice}</p>
						<p class="col-md-2" name="reject">
							<label>驳回理由：</label>
						</p>
						<p class="col-md-10" name="reject">${rtc.doctorRecordTask.rejectadvice}</p>
						<p class="col-md-2" name="recall">
							<label>撤回申请：</label>
						</p>
						<p class="col-md-10" name="recall">${rtc.nurseRecordTask.recalladvice}</p>
						<!-- button area -->
						<p class="col-md-2">
							<button type="button" class="btn btn-default btn-block" data-dismiss="modal">返回</button>
						</p>
						<p class="col-md-10" name="next">
							<button type="button" class="btn btn-success btn-block" data-dismiss="modal"
								onclick="next(${rtc.managerRecordTask.id})">同意并退回原客服处理</button>
						</p>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
	</body>
</html>