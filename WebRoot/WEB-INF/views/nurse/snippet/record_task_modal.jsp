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
	<style type="text/css">
		.popover {
			max-width: 298px;
		}
	</style>
	<script type="text/javascript">
	$(function() {
		top.window.ecgwaveToggle = true, window.ecgwaveLength = 600;
		modal("record");
		if("${rtc.user.id}") {
			$("#userinfo").load("<%=basePath%>userinfo/${rtc.user.id}/");
		}
		$("#smsButton").popover({
			title: ' 请确认短信内容 ',
			html: 'true',
			placement: 'top',
			content: function() {
				var html;
				$.ajax({
					async: false,
					url: "<%=basePath %>sms/dataReceived/open/${rtc.recordTask.id}/",
					dataType: "html",
					success: function(data) {
						html = data;
					}
				});
				return html;
			}
		});
	});
	function sendRecordReceivedSms() {
		$.ajax({
			url: "<%=basePath %>sms/dataReceived/${rtc.recordTask.id}/",
			data: $("form[name='indexForm']").serialize(),
			dataType: "json",
			success: function(data) {
				if(data == "0") {
					messager(" 已成功发送 ", "success", 2000);
					hidePopover();
				} else {
					messager(" 发送失败，请刷新后重试 ", "danger", 2000);
				}
			}
		});
	}
	function hidePopover() {
		$("#smsButton").popover("hide");
	}
	function next(nrtId) {
		var advice = $("textarea[name='advice']").val();
		var piecestarttime = $("#piecestarttime").val(), pieceendtime = $("#pieceendtime").val()
			startIndex = $("#startIndex").val(), endIndex = $("#endIndex").val()
			startPage = $("#startPage").val(), endPage = $("#endPage").val();
		if(advice) {
			if((piecestarttime != "" && pieceendtime != "" && parseDate(piecestarttime) <= parseDate(pieceendtime))
					|| (piecestarttime == "" && pieceendtime == "")) {
				$.ajax({
					url: host + "recordTask/next/" + nrtId + "/",
					data: "advice=" + advice + "&piecestarttime=" + piecestarttime + "&pieceendtime=" + pieceendtime
						+ "&pieceStartIndex=" + startIndex + "&pieceEndIndex=" + endIndex
						+ "&pieceStartPage=" + startPage + "&pieceEndPage=" + endPage + "&ecgwaveLength=" + window.ecgwaveLength,
					dataType: "json",
					type: "POST",
					success: function(data) {
						$(".modal").modal("hide");
					}
				});
			} else {
				messager(" 截取的开始时间不能晚于结束时间 ", "danger", 5000);
			}
		}
	}
	function recall(nrtId) {
		var recalladvice = prompt("请填写撤回理由");
		if(recalladvice) {
			$.ajax({
				url: host + "recordTask/recall/" + nrtId + "/",
				data: "recalladvice=" + recalladvice,
				dataType: "json",
				type: "POST",
				success: function(data) {
					$(".modal").modal("hide");
				}
			});
		}
	}
	function reset() {
		$("#piecestarttime, #pieceendtime, #startIndex, #startPage, #endIndex, #endPage").val("");
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
							${rtc.recordTask.starttime}~${rtc.recordTask.endtime}
						</p>
						<p>
							<label>截取时间：</label>
							<input type="text" id="piecestarttime" name="piecestarttime" readonly value="${rtc.recordTask.piecestarttime}"/>~
							<input type="text" id="pieceendtime" name="pieceendtime" readonly value="${rtc.recordTask.pieceendtime}"/>
							<input type="hidden" id="startIndex" name="startIndex" />
							<input type="hidden" id="startPage" name="startPage" />
							<input type="hidden" id="endIndex" name="endIndex" />
							<input type="hidden" id="endPage" name="endPage" />
							<button class="btn btn-default btn-xs" onclick="reset()">清空</button>
							<i class="icon-question-sign" title=" 鼠标点击心电图曲线，可循环获取时间值 "></i>
						</p>
						<p>
							<iframe src="<%=basePath%>ymRecordData/detail/${rtc.recordTask.id}/" frameborder="no" border="0" 
								allowtransparency="yes" style="height: 1020px; overflow-y: scroll; width: 100%;"></iframe>
						</p>
						<p class="col-md-3">
							<label>自检摘要：</label>
						</p>
						<p class="col-md-9">
							<label>【编辑时间:${rtc.nurseRecordTask.endtime}】</label>
						</p>
						<p class="col-md-9 col-md-offset-3">
							<textarea rows="2" name="advice" style="width: 100%;">${rtc.nurseRecordTask.advice}</textarea>
						</p>
						<p class="col-md-3">
							<button type="button" class="btn btn-default btn-block" data-dismiss="modal">返回</button>
						</p>
						<p class="col-md-3">
							<button type="button" class="btn btn-info btn-block" id="smsButton">发送短信</button>
						</p>
						<p class="col-md-3" name="next">
							<button type="button" class="btn btn-success btn-block" 
								onclick="next(${rtc.nurseRecordTask.id})">提交</button>
						</p>
						<p class="col-md-3" name="recall">
							<button type="button" class="btn btn-warning btn-block" 
								onclick="recall(${rtc.nurseRecordTask.id})">申请撤回</button>
						</p>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
	</body>
</html>