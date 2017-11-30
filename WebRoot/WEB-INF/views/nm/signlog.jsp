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
			$(":radio[name='role']").click(function() {
				// change style
				$(this).parent("label").removeClass(d).addClass(c)
					.siblings().removeClass(c).addClass(d);
				// filte signlogs
				filterSignlogOperators(filterSignlogsData());
			});
			filterSignlogOperators();
		});
		function filterSignlogs(data) {
			$("#tableContainer").load(host + "signlogList/", data, function() {
				dispDataTable("table");
				$(".redirectHolder").hide();
			});
		}
		function filterSignlogsData() {
			return $("form").serialize();
		}
		function filterSignlogOperators(data) {
			$("#operatorContainer").load(host + "signlogOperatorList/", data, function() {
				filterSignlogs(filterSignlogsData());
			});
		}
		</script>
	</head>
	<body>
		<div class="panel panel-primary">
			<div class="panel-heading">
				<i class="icon-table"></i>&nbsp;日志统计
			</div>
			<div class="panel-body">
				<form class="form-horizontal" role="form">
					<div>
						<label class="control-label col-md-1">
							角色
						</label>
						<p class="col-md-11">
							<label class="radio-inline label label-danger">
								<input type="radio" name="role"
									value="0" checked style="display: none;">
								全部										
							</label>
							<label class="radio-inline label label-default">
								<input type="radio" name="role"
									value="2" style="display: none;">
								客服								
							</label>
							<label class="radio-inline label label-default">
								<input type="radio" name="role"
									value="3" style="display: none;">
								客服组长							
							</label>
						</p>
					</div>
					<div>
						<label class="control-label col-md-1">
							姓名
						</label>
						<div class="col-md-11" id="operatorContainer"></div>
					</div>
				</form>
				<div id="tableContainer"></div>
			</div>
		</div>
		<div id="modalContainer"></div>
	</body>
</html>