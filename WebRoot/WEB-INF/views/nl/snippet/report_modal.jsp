<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
	<head>
		<script type="text/javascript" src="<%=basePath%>resources/bootstrap-3.0.3/filestyle/bootstrap-filestyle.min.js"></script>
		<script type="text/javascript">
		$(function() {
			modal("user");
			$("input[name='boundtime']").datetimepicker({
				language:  'zh-CN',
				endDate: new Date(),
				minView: 2,
				todayBtn: true,
				todayHighlight: true,
				autoclose: true,
				initialDate: new Date()
			});
		});
		function upload() {
			var boundtime = $("input[name='boundtime']").val();
			if(!boundtime) {
				messager(" 体检时间不能为空 ", "danger", 2000);
				return false;
			}
			var file = $("#file").val();
			if(!file) {
				messager(" 体检报告不能为空 ", "danger", 2000);
				return false;
			}
			$("#uploadForm").attr("action", host + "reportUpload/").submit();
			$(":file").filestyle('disabled', true);
			setTimeout(updateProgressbar, 1600);
			$(".progress").show();
		}
		var attempts = 0;
		var updateProgressbarFrequency = 1000; // 进度条的更新频率，单位ms
		function updateProgressbar() {
			var bar = $(".progress-bar");
			$.ajax( {
				url : host + "percentDone",
				async : false,
				dataType : "json",
				success : function(data) {
					if (data == null || data == "" || data == -1) {
						if (++attempts < 3) {
							setTimeout(updateProgressbar, updateProgressbarFrequency * attempts);
						} else {
							messager(" 网络不稳定，请稍后重试 ", "danger", 2000);
						}
					} else {
						bar.attr("style", "width:" + data + "%;").html(data + "%");
						if (data < 99) {
							setTimeout(updateProgressbar, updateProgressbarFrequency);
						} else {
							$(".modal").modal("hide");
							messager(" 上传成功 ", "success", 2000);
						}
					}
				}
			});
		}
		</script>
	</head>
	<body>
		<div class="modal fade" role="dialog" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title">上传体检报告</h4>
					</div>
					<div class="modal-body">
						<div id="userinfo"><!-- 用户信息 --></div>
						<form id="uploadForm" name="uploadForm" enctype="multipart/form-data" method="post" role="form"
							class="form-horizontal" target="hiddenUploadFrame">
							<input type="hidden" name="userid" value="${userid}"/>
							<div class="form-group">
								<label class="col-md-2 control-label">
									体检时间
								</label>
								<div class="col-md-10">
									<input type="text" name="boundtime" class="form-control"
										data-date-format="yyyy-mm-dd" value="">
									<p class="text-muted">相同体检时间的报告将覆盖存储，如有多份报告请合并后上传</p>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label">
									体检报告
								</label>
								<div class="col-md-10">
									<input type="file" id="file" name="file" class="filestyle"
										onchange="upload()" data-input="false"
										data-buttonText="上传体检报告" data-buttonName="btn-success"
										data-size="lg" data-iconName="glyphicon glyphicon-folder-open">
								</div>
							</div>
						</form>
						<div class="progress" style="display: none; margin: 20px 0;">
							<div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="2"
								aria-valuemin="0" aria-valuemax="100" style="width: 2%;">
							</div>
						</div>
						<iframe name="hiddenUploadFrame" id="hiddenUploadFrame" style="display: none;"></iframe>
						<button type="button" class="btn btn-default btn-block" data-dismiss="modal">
							取消
						</button>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
	</body>
</html>