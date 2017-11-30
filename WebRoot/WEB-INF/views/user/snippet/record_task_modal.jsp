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
	</script>
	</head>
	<body>		
		<!-- 测量数据确认的模态框 -->
		<div class="modal fade" role="dialog" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
						<h4 class="modal-title">自检报告详情</h4>
					</div>
					<div class="modal-body" style="height: 1220px;">
						<div id="userinfo"><!-- 用户信息 --></div>
						<p>
							<label>测量时间：</label>
							${rtc.recordTask.starttime}~${rtc.recordTask.endtime}
						</p>
						<p>
							<iframe src="<%=basePath%>ymRecordData/detail/${rtc.recordTask.id}/" frameborder="no" border="0" 
								allowtransparency="yes" style="height: 1020px; overflow-y: scroll; width: 100%;"></iframe>
						</p>
						<p class="col-md-2">
							<label>自检摘要：</label>
						</p>
						<p class="col-md-10">
							<label>【客服:${rtc.nurse.name}】【编辑时间:${rtc.nurseRecordTask.endtime}】</label>
						</p>
						<p class="col-md-10 col-md-offset-2">
							${rtc.nurseRecordTask.advice}
						</p>
						<p class="col-md-2">
							<label>报告及建议：</label>
						</p>
						<p class="col-md-10">
							<label>【医生:${rtc.doctor.name}】【编辑时间:${rtc.doctorRecordTask.endtime}】</label>
						</p>
						<p class="col-md-10 col-md-offset-2">
							${rtc.doctorRecordTask.advice}
						</p>
					</div>
					<div class="modal-footer">
						<p class="col-md-12">
							<button type="button" class="btn btn-default btn-block" data-dismiss="modal">返回</button>
						</p>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
	</body>
</html>