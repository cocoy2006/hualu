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
	$(function() {
		$("input[name='date']").datetimepicker({
			language:  'zh-CN',
			endDate: new Date(),
			minView: 2,
			todayBtn: true,
			todayHighlight: true
		});
	});
	function saveOrUpdate() {
		 $.ajax({
			 url: "<%=basePath%>company/saveOrUpdate/",
			 data: $("form[name='company']").serialize(),
			 dataType: "json",
			 type: "POST",
			 success: function(data) {
				 if(data == "1") {
					messager(" 操作成功 ", "success", 2000);
					$(".modal").modal("hide");
					filterCompanys();
				} else if(data == "-1") {
					messager(data, "danger", 2000);
				}
			 }
		 });
	}
	</script>
	</head>
	<body>		
		<!-- 厂商信息的模态框 -->
		<div class="modal fade" role="dialog" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
						<h4 class="modal-title">厂商信息</h4>
					</div>
					<div class="modal-body">
						<form name="company" class="form-horizontal" role="form" action="#" method="post">
							<input type="hidden" name="id" value="${company.id}"/>
							<label class="control-label col-md-3">
								厂商名称
							</label>
							<p class="col-md-9">
								<input type="text" class="form-control" 
									name="name" value="${company.name}">
							</p>
							<label class="control-label col-md-3">
								厂商代码
							</label>
							<p class="col-md-9">
								<input type="text" class="form-control" 
									name="code" value="${company.code}">
							</p>
							<label class="control-label col-md-3">
								厂商地址
							</label>
							<p class="col-md-9">
								<input type="text" class="form-control" 
									name="address" value="${company.address}">
							</p>
							<label class="control-label col-md-3">
								简介（选填）
							</label>
							<p class="col-md-9">
								<textarea class="form-control" name="spec" rows="4">${company.spec}</textarea>
							</p>
							<label class="control-label col-md-3">
								入库时间
							</label>
							<p class="col-md-9">
								<input type="text" name="date" class="form-control" 
									data-date-format="yyyy-mm-dd" readonly value="${company.date}">
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