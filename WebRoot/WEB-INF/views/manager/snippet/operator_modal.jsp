<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String[] roles = {" 库管 ", " 客服 ", " 客服组长 ", " 客服主管 ", " 医生 ", " 管理员 "};
	String[] gendas = {" 男 ", " 女 ", " 未知 "};
	String[] statuss = {" 未服务 ", " 正常服务 ", " 已注销 "};
%>
<!DOCTYPE html>
<html>
	<head>
	<script type="text/javascript">
		$(function() {
			modal("operator");
			var c = "label-danger", d = "label-default";
			$(":radio[name='role'], :radio[name='genda'], :radio[name='status']").click(function() {
				// change style
				$(this).parent("label").removeClass(d).addClass(c)
					.siblings().removeClass(c).addClass(d);
			});
			$("input[name='mobile']").on("focusout", function() {
				if(!isPhone($(this).val())) {
					$(this).next().show();
					$("#submitBtn").attr("disabled", "disabled");
				} else {
					$(this).next().hide();
					$("#submitBtn").removeAttr("disabled");
				}
			});
			initRoleStyle();
			initGendaStyle();
			initStatusStyle();
		});
		function initRoleStyle() {
			initStyle("roleHolder", "${operator.role}", false);
		}
		function initGendaStyle() {
			initStyle("gendaHolder", "${operator.genda}", true);
		}
		function initStatusStyle() {
			initStyle("statusHolder", "${operator.status}", true);
		}
		function initStyle(holder, value, modifiable) {
			if(value == "") {
				$("." + holder + " label").first().removeClass("label-default").addClass("label-danger").end()
					.children().first().attr("checked", "checked");
			} else {
				var that = $("." + holder + " label input[value='" + value + "']");
				that.attr("checked", "checked");
				that = that.parent();
				that.removeClass("label-default").addClass("label-danger");
				if(!modifiable) {
					that.siblings().remove();
				}
			}
		}
		function saveOrUpdate() {
			var loginname = $("input[name='loginname']").val();
			var passwd = $("input[name='passwd']").val();
			var name = $("input[name='name']").val();
			var mobile = $("input[name='mobile']");
			if(!loginname || !passwd|| !name || !mobile.val()) {
				messager(" 必填字段不能为空 ", "danger", 2000);
				return false;
			}
			if(!isPhone(mobile.val())) {
				mobile.next().show();
				$("#submitBtn").attr("disabled", "disabled");
				return false;
			}
			$.ajax({
				url: "<%=basePath%>operator/saveOrUpdate/",
				data: $("form[name='operator']").serialize(),
				dataType: "json",
				type: "POST",
				success: function(data) {
					if(data == "1") {
						success();
						$(".modal").modal("hide");
						filterOperators();
					} else {
						danger(data);
					}
				}
			});
			/**/
		}
	</script>
	</head>
	<body>
		<!-- 用户信息的模态框 -->
		<div id="operatorModal" class="modal fade" role="dialog"
			aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title">
							用户信息
						</h4>
					</div>
					<div class="modal-body">
						<form name="operator" method="post" role="form" action="#" class="form-horizontal">
							<input type="hidden" value="${operator.id}" name="id">
							<input type="hidden" value="${operator.isonline}" name="isonline">
							<c:if test="${operator.createtime != null}">
								<input type="hidden" value="${operator.createtime}" name="createtime">
							</c:if>
							<div class="form-group">
								<label class="col-md-2 control-label">
									<i class="text-danger">*&nbsp;角色类型</i>
								</label>
								<div class="col-md-10 roleHolder">
								<c:forEach var="role" items="<%=roles %>" varStatus="i">
									<label class="radio-inline label label-default">
										<input type="radio" value="${i.index + 1}" name="role" style="display: none;">
										${role}
									</label>
								</c:forEach>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label">
									<i class="text-danger">*&nbsp;性别</i>
								</label>
								<div class="col-md-10 gendaHolder">
								<c:forEach var="genda" items="<%=gendas %>" varStatus="i">
									<label class="radio-inline label label-default">
										<input type="radio" value="${i.index + 1}" name="genda" style="display: none;">
										${genda}
									</label>
								</c:forEach>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label">
									<i class="text-danger">*&nbsp;状态</i>
								</label>
								<div class="col-md-10 statusHolder">
								<c:forEach var="status" items="<%=statuss %>" varStatus="i">
									<label class="radio-inline label label-default">
										<input type="radio" value="${i.index}" name="status" style="display: none;">
										${status}
									</label>
								</c:forEach>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label">
									<i class="text-danger">*&nbsp;用户名</i>
								</label>
								<div class="col-md-10">
									<input type="text" placeholder="登录用户名"
										class="form-control" value="${operator.loginname}" name="loginname">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label">
									<i class="text-danger">*&nbsp;密码</i>
								</label>
								<div class="col-md-10">
									<input type="text" placeholder="登录密码"
										class="form-control" value="${operator.passwd}" name="passwd">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label">
									<i class="text-danger">*&nbsp;真实姓名</i>
								</label>
								<div class="col-md-10">
									<input type="text" placeholder="真实姓名"
										class="form-control" value="${operator.name}" name="name">
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-md-2 control-label">
									<i class="text-danger">*&nbsp;手机号</i>
								</label>
								<div class="col-md-10">
									<input type="text" placeholder="11位有效中国大陆手机号"
										class="form-control" value="${operator.mobile}" name="mobile">
									<i class="text-danger" style="display: none;">手机号格式不正确，请重新输入</i>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label">
									备注
								</label>
								<div class="col-md-10">
									<input type="text" placeholder="备注"
										class="form-control" value="${operator.memo}" name="memo">
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-2">
									<button type="button" class="btn btn-default btn-block" data-dismiss="modal">取消</button>
								</div>
								<div class="col-offset-md-2 col-md-10">
									<button type="button" id="submitBtn" class="btn btn-success btn-block" 
										onclick="saveOrUpdate()">确定</button>
								</div>
							</div>
						</form>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->
	</body>
</html>