<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
			var c = "label-danger", d = "label-default";
			$(":radio[name='nurseid']").click(function() {
				// change style
				$(this).parent("label").removeClass(d).addClass(c)
					.siblings().removeClass(c).addClass(d);
			});
		});
		</script>
	</head>
	<body>		
		<!-- 设备出库的模态框 -->
		<div id="checkoutModal" class="modal fade" role="dialog" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
						<h4 class="modal-title">设备出库</h4>
					</div>
					<div class="modal-body" style="height: 250px;">
						<form name="checkoutForm" class="form-horizontal" role="form" style="height: 180px;">
							<input type="hidden" name="deviceid" value="${deviceid}"/>
							<label class="control-label col-md-2">
								领用客服
							</label>
							<p class="col-md-10">
								<c:if test="${fn:length(nurses) > 0}">
								   	<c:forEach var="nurse" items="${nurses}">
								   	<label class="radio-inline label label-default">
										<input type="radio" name="nurseid"
											value="${nurse.id}" style="display: none;">
										${nurse.name}										
									</label>
								   	</c:forEach>
								</c:if>
							</p>
							<label class="control-label col-md-2">
								领用地点
							</label>
							<p class="col-md-10">
								<input type="text" class="form-control" name="address"
									placeholder="在此填写领用地点">
							</p>
							<label class="control-label col-md-2">
								备注
							</label>
							<p class="col-md-10">
								<input type="text" class="form-control" name="memo"
									placeholder="在此填写备注">
							</p>
							<label class="control-label col-md-2">
								领用时间
							</label>
							<div class="col-md-10">
								<input type="text" class="form-control" name="occupationtime" 
									data-date-format="yyyy-mm-dd hh:ii:ss" value="${occupationtime}" />
							</div>
						</form>
						<div class="col-md-2">
							<button type="button" class="btn btn-default btn-block" data-dismiss="modal">返回</button>
						</div>
						<div class="col-md-10">
							<button type="button" class="btn btn-success btn-block" name="checkout"
								onclick="checkout()">出库</button>
						</div>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
	</body>
</html>