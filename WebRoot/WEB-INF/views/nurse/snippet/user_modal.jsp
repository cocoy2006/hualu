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
	<script type="text/javascript">
		$(function() {
			var c = "label-danger", d = "label-default";
			$(":radio[name='userlevel'], :radio[name='usergroup'], :radio[name='gender'], :radio[name='status']").click(function() {
				$(this).parent("label").removeClass(d).addClass(c)
					.siblings().removeClass(c).addClass(d);
			});
			$("input[name='birthday']").datetimepicker({
				language:  'zh-CN',
				endDate: new Date(),
				autoclose: true,
				minView: 2,
				todayBtn: true,
				todayHighlight: true
			});
			if(!"${u.passwd}") {
				$("input[name='passwd']").val("1234");
			}
			$("input[name='phone']").on("focusout", function() {
				if(!isPhone($(this).val())) {
					$(this).next().show();
					$("#submitBtn").attr("disabled", "disabled");
				} else {
					$(this).next().hide();
					$("#submitBtn").removeAttr("disabled");
				}
			});
			initUserlevelStyle();
			initUsergroupStyle();
			initGenderStyle();
			initStatusStyle();
		});
		function initUserlevelStyle() {
			initStyle("userlevelHolder", "${u.userlevel}", true);
		}
		function initUsergroupStyle() {
			initStyle("usergroupHolder", "${u.usergroup}", true);
		}
		function initGenderStyle() {
			initStyle("genderHolder", "${u.gender}", true);
		}
		function initStatusStyle() {
			initStyle("statusHolder", "${u.status}", true);
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
			var phone = $("input[name='phone']");
			if(!loginname || !name || !passwd || !phone.val()) {
				messager(" 必填字段不能为空 ", "danger", 2000);
				return false;
			}
			if(!isPhone(phone.val())) {
				phone.next().show();
				$("#submitBtn").attr("disabled", "disabled");
				return false;
			}
			$.ajax({
				url: "<%=basePath%>user/saveOrUpdate/",
				data: $("form[name='user']").serialize(),
				dataType: "json",
				type: "POST",
				success: function(data) {
					if(data == "1") {
						success();
						$(".modal").modal("hide");
						filterUsers();
					} else if(data == "-2") {
						danger(" 用户名或手机号已存在 ");
					} else {
						danger(data);
					}
				}
			});
		}
	</script>
	</head>
	<body>
		<!-- 用户信息的模态框 -->
		<div class="modal fade" role="dialog" aria-hidden="true">
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
					<div class="modal-body" style="overflow:auto; zoom:1;">
						<form name="user" enctype="multipart/form-data" method="post" role="form"
							action="#" class="form-horizontal">
							<input type="hidden" value="${u.id}" name="id">
							<div class="col-md-6" style="border-right: 1px solid #CCCCCC;">
								<div class="form-group">
									<h3>必填项</h3>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">
										<i class="text-danger">*&nbsp;服务类型</i>
									</label>
									<div class="col-md-9 userlevelHolder">
										<label class="radio-inline label label-default">
											<input type="radio" value="1" name="userlevel" style="display: none;">
											普通
										</label>
										<label class="radio-inline label label-default">
											<input type="radio" value="2" name="userlevel" style="display: none;">
											贵宾
										</label>
									</div>
								</div>
	
								<div class="form-group">
									<label class="col-md-3 control-label">
										<i class="text-danger">*&nbsp;用户组别</i>
									</label>
									<div class="col-md-9 usergroupHolder">
										<label class="radio-inline label label-default">
											<input type="radio" value="1" name="usergroup" style="display: none;">
											副教授
										</label>
										<label class="radio-inline label label-default">
											<input type="radio" value="2" name="usergroup" style="display: none;">
											正高级
										</label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">
										<i class="text-danger">*&nbsp;性别</i>
									</label>
									<div class="col-md-9 genderHolder">
										<label class="radio-inline label label-default">
											<input type="radio" value="1" name="gender" style="display: none;">
											男
										</label>
										<label class="radio-inline label label-default">
											<input type="radio" value="2" name="gender" style="display: none;">
											女
										</label>
										<label class="radio-inline label label-default">
											<input type="radio" value="3" name="gender" style="display: none;">
											未知
										</label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">
										<i class="text-danger">*&nbsp;用户状态</i>
									</label>
									<div class="col-md-9 statusHolder">
										<label class="radio-inline label label-default">
											<input type="radio" value="1" name="status" style="display: none;">
											已开通服务
										</label>
										<label class="radio-inline label label-default">
											<input type="radio" value="0" name="status" style="display: none;">
											未开通服务
										</label>
										<label class="radio-inline label label-default">
											<input type="radio" value="2" name="status" style="display: none;">
											服务冻结
										</label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label" for="loginname">
										<i class="text-danger">*&nbsp;登陆账号</i>
									</label>
									<div class="col-md-9">
										<input type="text" placeholder="可以是手机号"
											class="form-control" value="${u.loginname}" name="loginname">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">
										<i class="text-danger">*&nbsp;登陆密码</i>
									</label>
									<div class="col-md-9">
										<input type="text" placeholder="有效密码" class="form-control" 
											value="${u.passwd}" name="passwd" title="默认1234"/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">
										<i class="text-danger">*&nbsp;姓名</i>
									</label>
									<div class="col-md-9">
										<input type="text" placeholder="用户姓名"
											class="form-control" value="${u.name}" name="name">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">
										<i class="text-danger">*&nbsp;手机号</i>
									</label>
									<div class="col-md-9">
										<input type="text" placeholder="11位有效中国大陆手机号"
											id="phone" class="form-control" value="${u.phone}" name="phone">
										<i class="text-danger" style="display: none;">手机号格式不正确，请重新输入</i>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label class="col-md-3 control-label">
										病历号
									</label>
									<div class="col-md-9">
										<input type="text" placeholder="用户病历号"
											class="form-control" value="${u.dstr}" name="dstr">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">
										出生年月
									</label>
									<div class="col-md-9">
										<input type="text" name="birthday" class="form-control"
											data-date-format="yyyy-mm-dd" value="${u.birthday}">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">
										所在公司
									</label>
									<div class="col-md-9">
										<input type="text" placeholder="公司名称"
											class="form-control" value="${u.companyname}" name="companyname">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">
										公司地址
									</label>
									<div class="col-md-9">
										<input type="text" placeholder="公司住址"
											class="form-control" value="${u.companyaddress}" name="companyaddress">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">
										主治医生
									</label>
									<div class="col-md-9">
										<select name="doctorid" class="form-control">
											<option value="0">
												请选择
											</option>
										<c:if test="${fn:length(doctors) > 0}">
	   										<c:forEach var="doctor" items="${doctors}">
	   										<option value="${doctor.id}" <c:if test="${u.doctorid == doctor.id}">selected="selected"</c:if>>
												${doctor.name}
											</option>
	   										</c:forEach>
	   									</c:if>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">
										家庭住址
									</label>
									<div class="col-md-9">
										<input type="text" placeholder="家庭住址"
											class="form-control" value="${u.homeaddress}" name="homeaddress">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">
										邮箱
									</label>
									<div class="col-md-9">
										<input type="text" placeholder="邮箱"
											class="form-control" value="${u.email}" name="email">
									</div>
								</div>
	
								<div class="form-group">
									<label class="col-md-3 control-label">
										微信号
									</label>
									<div class="col-md-9">
										<input type="text" placeholder="微信号"
											class="form-control" value="${u.wechatid}" name="wechatid">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">
										微信昵称
									</label>
									<div class="col-md-9">
										<input type="text" placeholder="微信昵称"
											class="form-control" value="${u.wechatname}" name="wechatname">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">
										社保编码
									</label>
									<div class="col-md-9">
										<input type="text" placeholder="社保编码"
											class="form-control" value="${u.ssnum}" name="ssnum">
									</div>
								</div>
							</div>
						</form>
						<p class="col-md-2">
							<button type="button" class="btn btn-default btn-block" data-dismiss="modal">返回</button>
						</p>
						<p class="col-md-10">
							<button type="button" id="submitBtn" class="btn btn-success btn-block" 
								onclick="saveOrUpdate()">确定</button>
						</p>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
	</body>
</html>