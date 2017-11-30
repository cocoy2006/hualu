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
	function next(drtId) {
		var advice = $("textarea[name='advice']").val();
		if(advice) {
			$.ajax({
				url: host + "recordTask/next/" + drtId + "/",
				data: "advice=" + advice,
				dataType: "json",
				type: "POST",
				success: function(data) {
					$(".modal").modal("hide");
				}
			});
		}
	}
	function draft(drtId) {
		var advice = $("textarea[name='advice']").val();
		if(advice) {
			$.ajax({
				url: host + "recordTask/draft/" + drtId + "/",
				data: "advice=" + advice,
				dataType: "json",
				type: "POST",
				success: function(data) {
					$(".modal").modal("hide");
				}
			});
		}
	}
	function reject(drtId) {
		var rejectadvice = prompt("请填写驳回理由");
		if(rejectadvice) {
			$.ajax({
				url: host + "recordTask/reject/" + drtId + "/",
				data: "rejectadvice=" + rejectadvice,
				dataType: "json",
				type: "POST",
				success: function(data) {
					$(".modal").modal("hide");
				}
			});
		}
	}
	</script>
	</head>
	<body>		
		<!-- 测量数据确认的模态框 -->
		<div id="recordTaskModal" class="modal fade" role="dialog" aria-hidden="true">
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
							${rtc.recordTask.starttime}~${rtc.recordTask.endtime}
						</p>
						<c:if test="${rtc.recordTask.piecestarttime != null && rtc.recordTask.pieceendtime != null}">
						<p>
							<label>截取时间：</label>
							${rtc.recordTask.piecestarttime}~${rtc.recordTask.pieceendtime}
						</p>
						</c:if>
						<p>
							<iframe src="<%=basePath%>ymRecordData/detail/${rtc.recordTask.id}/" frameborder="no" border="0" 
								allowtransparency="yes" style="height: 1020px; overflow-y: scroll; width: 100%;"></iframe>
						</p>
						<p class="col-md-2">
							<label>自检摘要：</label>
						</p>
						<p class="col-md-10">
							<label>【客服:${rtc.nurse.name }】&nbsp;【编辑时间:${rtc.nurseRecordTask.endtime}】</label>
						</p>
						<p class="col-md-10 col-md-offset-2">
							${rtc.nurseRecordTask.advice}
						</p>
						<p class="col-md-2">
							<label>报告及建议：</label>
						</p>
						<p class="col-md-10">
							<label>【编辑时间:${rtc.doctorRecordTask.endtime}】</label>
						</p>
						<p class="col-md-10 col-md-offset-2">
							<textarea rows="2" name="advice" style="width: 100%;">${rtc.doctorRecordTask.advice}</textarea>
						</p>
						<p class="col-md-2">
							<button type="button" class="btn btn-default btn-block" data-dismiss="modal">返回</button>
						</p>
						<p class="col-md-3" name="next">
							<button type="button" class="btn btn-success btn-block" data-dismiss="modal"
								onclick="next(${rtc.doctorRecordTask.id})">提交</button>
						</p>
						<p class="col-md-3" name="draft">
							<button type="button" class="btn btn-info btn-block" data-dismiss="modal"
								onclick="draft(${rtc.doctorRecordTask.id})">存草稿箱</button>
						</p>
						<p class="col-md-3" name="reject">
							<button type="button" class="btn btn-warning btn-block" data-dismiss="modal"
								onclick="reject(${rtc.doctorRecordTask.id})">驳回</button>
						</p>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
	</body>
</html>