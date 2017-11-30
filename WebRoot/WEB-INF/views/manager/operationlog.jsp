<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript">
		$(function() {
			// bind datetimepicker
			initDatetimepicker();
			var c = "label-danger", d = "label-default";
			$(":radio[name='operatorrole'], :radio[name='operationtype'], :radio[name='targettype']").click(function() {
				// change style
				$(this).parent("label").removeClass(d).addClass(c)
					.siblings().removeClass(c).addClass(d);
				// filte operationloglogs
				if(this.name == "operatorrole") {
					filterOperationlogOperators(filterOperationlogsData());
				} else {
					filterOperationlogs(filterOperationlogsData());
				}
			});
			filterOperationlogOperators();
		});
		function initDatetimepicker() {
			$("input.date").datetimepicker({
				language:  'zh-CN',
				endDate: new Date(),
				autoclose: true,
//				minuteStep: 1,
				todayBtn: true,
				todayHighlight: true
			});
		}
		function filterOperationlogs(data) {
			$("#tableContainer").load(host + "operationloglist/", data, function() {
				dispDataTable("table");
				$(".redirectHolder").hide();
			});
		}
		function filterOperationlogsData() {
			return $("form").serialize();
		}
		function filterOperationlogOperators(data) {
			$("p[name='operationlogOperatorlist']").load(host + "operationlogOperatorlist/", data, function() {
				filterOperationlogs(filterOperationlogsData());
			});
		}
		function reset() {
			$("input.date").val("");
		}
		</script>
	</head>
	<body>
		<div class="panel panel-primary">
			<div class="panel-heading">
				<i class="icon-table"></i>&nbsp;操作记录
			</div>
			<div class="panel-body">
				<form class="form-horizontal" role="form">
					<div>
						<label class="control-label col-md-1">
							角色
						</label>
						<p class="col-md-11">
							<label class="radio-inline label label-danger">
								<input type="radio" name="operatorrole"
									value="0" checked style="display: none;">
								全部										
							</label>
							<label class="radio-inline label label-default">
								<input type="radio" name="operatorrole"
									value="1" style="display: none;">
								库管								
							</label>
							<label class="radio-inline label label-default">
								<input type="radio" name="operatorrole"
									value="2" style="display: none;">
								客服								
							</label>
							<label class="radio-inline label label-default">
								<input type="radio" name="operatorrole"
									value="3" style="display: none;">
								客服组长							
							</label>
							<label class="radio-inline label label-default">
								<input type="radio" name="operatorrole"
									value="4" style="display: none;">
								客服主管								
							</label>
							<label class="radio-inline label label-default">
								<input type="radio" name="operatorrole"
									value="5" style="display: none;">
								医生								
							</label>
						</p>
					</div>
					<div>
						<label class="control-label col-md-1">
							姓名
						</label>
						<p class="col-md-11" name="operationlogOperatorlist"></p>
					</div>
					<div>
						<label class="control-label col-md-1">
							模块
						</label>
						<p class="col-md-11">
							<label class="radio-inline label label-danger">
								<input type="radio" name="targettype"
									value="0" checked style="display: none;">
								全部										
							</label>
							<label class="radio-inline label label-default">
								<input type="radio" name="targettype"
									value="1" style="display: none;">
								自检记录						
							</label>
							<label class="radio-inline label label-default">
								<input type="radio" name="targettype"
									value="2" style="display: none;">
								综合报告							
							</label>
							<label class="radio-inline label label-default">
								<input type="radio" name="targettype"
									value="3" style="display: none;">
								健康咨询
							</label>
							<label class="radio-inline label label-default">
								<input type="radio" name="targettype"
									value="4" style="display: none;">
								健康干预			
							</label>
							<label class="radio-inline label label-default">
								<input type="radio" name="targettype"
									value="5" style="display: none;">
								用户管理
							</label>
							<label class="radio-inline label label-default">
								<input type="radio" name="targettype"
									value="6" style="display: none;">
								设备管理
							</label>
						</p>
					</div>
					<div>
						<label class="control-label col-md-1">
							类型
						</label>
						<p class="col-md-11">
							<label class="radio-inline label label-danger">
								<input type="radio" name="operationtype"
									value="0" checked style="display: none;">
								全部										
							</label>
							<label class="radio-inline label label-default">
								<input type="radio" name="operationtype"
									value="1" style="display: none;">
								新建								
							</label>
							<label class="radio-inline label label-default">
								<input type="radio" name="operationtype"
									value="2" style="display: none;">
								删除								
							</label>
							<label class="radio-inline label label-default">
								<input type="radio" name="operationtype"
									value="3" style="display: none;">
								修改/处理							
							</label>
							<label class="radio-inline label label-default">
								<input type="radio" name="operationtype"
									value="4" style="display: none;">
								查询							
							</label>
							<label class="radio-inline label label-default">
								<input type="radio" name="operationtype"
									value="5" style="display: none;">
								绑定(限设备管理)							
							</label>
							<label class="radio-inline label label-default">
								<input type="radio" name="operationtype"
									value="6" style="display: none;">
								解绑定(限设备管理)						
							</label>
							<label class="radio-inline label label-default">
								<input type="radio" name="operationtype"
									value="7" style="display: none;">
								驳回	(限医生的自检记录)					
							</label>
							<label class="radio-inline label label-default">
								<input type="radio" name="operationtype"
									value="8" style="display: none;">
								撤回(限客服类的自检记录)				
							</label>
						</p>
					</div>
					<div>
						<label class="control-label col-md-1">
							日期
						</label>
						<p class="col-md-3">
							<input type="text" name="start" class="date form-control" 
								data-date-format="yyyy-mm-dd hh:ii:ss" readonly>
							
						</p>
						<label class="control-label col-md-1">
							至
						</label>
						<p class="col-md-3">
							<input type="text" name="finish" class="date form-control" 
								data-date-format="yyyy-mm-dd hh:ii:ss" readonly>
						</p>
					</div>
				</form>
				<div>
					<button class="btn btn-warning" onclick="reset()">清空日期</button>
					<button class="btn btn-success" onclick="filterOperationlogs(filterOperationlogsData())">检索</button>
				</div>
				<div id="tableContainer"></div>
			</div>
		</div>
		<div id="modalContainer"></div>
	</body>
</html>