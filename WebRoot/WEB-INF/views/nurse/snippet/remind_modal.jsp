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
		$("input[name='starttime']").datetimepicker({
			language:  'zh-CN',
			startDate: new Date(),
			autoclose: true,
			todayBtn: true,
			todayHighlight: true
		});
		var c = "label-danger", d = "label-default";
		$(":radio[name='cycle']").click(function() {
			$(this).parent("label").removeClass(d).addClass(c)
				.siblings().removeClass(c).addClass(d);
		});
		var cycle = "${remind.cycle}";
		if(cycle && cycle != 1) {
			$(":radio[name='cycle']").each(function() {
				$(this).parent("label").removeClass(c).addClass(d).end()
					.removeAttr("checked");
				if($(this).val() == cycle) {
					$(this).parent("label").removeClass(d).addClass(c).end()
						.attr("checked", "checked");
				}
			});
		}
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
		if(starttime.length < 19) {
			$("input[name='starttime']").val(starttime + ":00");
		}
//		messager($("form[name='remind']").serialize(), "success", 10000);
		
		$.ajax({
		 	url: "<%=basePath%>remind/saveOrUpdate/",
		 	data: $("form[name='remind']").serialize(),
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
		<!-- 提醒信息的模态框 -->
		<div class="modal fade" role="dialog" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
						<h4 class="modal-title">提醒信息</h4>
					</div>
					<div class="modal-body">
						<form name="remind" class="form-horizontal" role="form" action="#" method="post">
							<input type="hidden" name="id" value="${remind.id}"/>
							<input type="hidden" name="fromid" value="${remind.fromid}"/>
							<input type="hidden" name="toid" value="${remind.toid}"/>
							<label class="control-label col-md-3">
								提醒时间
							</label>
							<p class="col-md-9">
								<input type="text" class="form-control" 
									name="starttime" value="${remind.starttime}">
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
								<textarea class="form-control" name="content" rows="4">${remind.content}</textarea>
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