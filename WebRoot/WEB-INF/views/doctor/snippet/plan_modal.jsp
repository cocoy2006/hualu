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
	var c = "label-danger", d = "label-default";
	$(function() {
		$("input[name='starttime']").datetimepicker({
			language:  'zh-CN',
			format: 'yyyy-mm-dd',
			startDate: new Date(),
			minView: 2,
			autoclose: true,
			todayBtn: true,
			todayHighlight: true
		});
		$(":radio[name='type'], :radio[name='period'], :radio[name='cycle']").click(function() {
			$(this).parent("label").removeClass(d).addClass(c)
				.siblings().removeClass(c).addClass(d);
		});
		findChecked("type", "${plan.type}");
		findChecked("period", "${plan.period}");
		findChecked("cycle", "${plan.cycle}");
	});
	function saveOrUpdate() {
		var fromid = $("input[name='fromid']").val();
		if(!fromid) {
			$("input[name='fromid']").val(${operator.id});
		}
		var toid = $("input[name='toid']").val();
		if(!toid) {
			$("input[name='toid']").val($("input[name='uid']").val());
		}
		var starttime = $("input[name='starttime']").val();
		if(starttime.length < 11) {
			$("input[name='starttime']").val(starttime + " 00:00:00");
		}
//		messager($("form[name='plan']").serialize(), "success", 5000);
		
		$.ajax({
		 	url: "<%=basePath%>plan/saveOrUpdate/",
		 	data: $("form[name='plan']").serialize(),
		 	dataType: "json",
		 	type: "POST",
		 	success: function(data) {
			 	if(data == "1") {
					messager(" 操作成功 ", "success", 2000);
					$(".modal").modal("hide");
					loadUserInfo();
				} else {
					messager(data, "danger", 2000);
				}
		 	}
		});
		/**/
	}
	</script>
	</head>
	<body>		
		<!-- 计划信息的模态框 -->
		<div class="modal fade" role="dialog" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
						<h4 class="modal-title">计划信息</h4>
					</div>
					<div class="modal-body">
						<form name="plan" class="form-horizontal" role="form" action="#" method="post">
							<input type="hidden" name="id" value="${plan.id}"/>
							<input type="hidden" name="fromid" value="${plan.fromid}"/>
							<input type="hidden" name="toid" value="${plan.toid}"/>
							<label class="control-label col-md-3">
								计划类型
							</label>
							<p class="col-md-9">
								<label class="radio-inline label label-danger">
									<input type="radio" name="type"
										value="1" checked style="display: none;">
									测量计划								
								</label>
								<label class="radio-inline label label-default">
									<input type="radio" name="type"
										value="2" style="display: none;">
									运动计划	
								</label>
							</p>
							<label class="control-label col-md-3">
								开始时间
							</label>
							<p class="col-md-9">
								<input type="text" class="form-control" 
									name="starttime" value="${plan.starttime}">
							</p>
							<label class="control-label col-md-3">
								计划周期
							</label>
							<p class="col-md-9">
								<label class="radio-inline label label-danger">
									<input type="radio" name="period"
										value="1" checked style="display: none;">
									一周								
								</label>
								<label class="radio-inline label label-default">
									<input type="radio" name="period"
										value="2" style="display: none;">
									两周	
								</label>
								<label class="radio-inline label label-default">
									<input type="radio" name="period"
										value="3" style="display: none;">
									三周	
								</label>
								<label class="radio-inline label label-default">
									<input type="radio" name="period"
										value="4" style="display: none;">
									一个月
								</label>
								<label class="radio-inline label label-default">
									<input type="radio" name="period"
										value="5" style="display: none;">
									两个月
								</label>
								<label class="radio-inline label label-default">
									<input type="radio" name="period"
										value="6" style="display: none;">
									三个月
								</label>
							</p>
							<label class="control-label col-md-3">
								提醒周期
							</label>
							<p class="col-md-9">
								<label class="radio-inline label label-danger">
									<input type="radio" name="cycle"
										value="1" checked style="display: none;">
									每天									
								</label>
								<label class="radio-inline label label-default">
									<input type="radio" name="cycle"
										value="2" style="display: none;">
									每2天	
								</label>
								<label class="radio-inline label label-default">
									<input type="radio" name="cycle"
										value="3" style="display: none;">
									每3天	
								</label>
								<label class="radio-inline label label-default">
									<input type="radio" name="cycle"
										value="7" style="display: none;">
									每周
								</label>
							</p>
							<input type="hidden" name="way" value="1"/>
							<label class="control-label col-md-3">
								提醒内容
							</label>
							<p class="col-md-9">
								<textarea class="form-control" name="content" rows="4">${plan.content}</textarea>
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