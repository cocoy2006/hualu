<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.hualu.main.java.util.Hualu"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript">
		$(function() {
			var c = "label-danger", d = "label-default";
			$("input[name=isonline]").click(function() {
				$(this).parent("label").removeClass(d).addClass(c)
					.siblings().removeClass(c).addClass(d);
			});
		});
		function openSelectUserModal() {
			var url = host + "selectUser/";
			$("#modalContainer").load(url, function() {
				$(".modal").modal("show");
				$("tr.user").on("click", function() {
					$("input[name='userid']").val(this.title);
					$("#selectedUserInfo").html("已选择用户:" + $(this).children("td[title='name']").html());
					$(".modal").modal("hide");
				});
			});
		}
		function openSelectDoctorModal() {
			var url = host + "healthSelectDoctor/";
			$("#modalContainer").load(url, function() {
				$(".modal").modal("show");
				$("tr.doctor").on("click", function() {
					$("input[name='doctorid']").val(this.title);
					$("#selectedDoctorInfo").html("已选择医生:" + $(this).children("td[title='name']").html());
					$(".modal").modal("hide");
				});
			});
		}
		function saveOrUpdate() {
			var userid = $("input[name='userid']").val();
			var doctorid = $("input[name='doctorid']").val();
			if(!doctorid || !userid) {
				messager(" 用户和医生都不能为空 ", "danger", 2000);
				return;
			}
			var taskid = $("input[name='taskid']").val();
			var url = host;
			if(taskid) { // update
				url += "healthTask/update/" + taskid + "/";
			} else {
				url += "healthTask/save/";
			}
			$.ajax({
				url: url,
				data: $("form[name='healthForm']").serialize(),
				dataType: "json",
				type: "POST",
				success: function(data) {
					loadSpecificPage("health");
					messager(" 操作成功 ", "success", 2000);
				}, error: function(data) {
					messager(data, "danger", 2000);
				}
			});
		}
		</script>
	</head>
	<body>
		<div class="panel panel-primary">
			<div class="panel-heading">
				<i class="icon-table"></i>&nbsp;健康咨询
			</div>
			<div class="panel-body">
				<p class="col-md-10 col-md-offset-2">
					<button class="btn btn-info col-md-2" name="userid" 
						onclick="openSelectUserModal()">选择用户</button>
					<span id="selectedUserInfo" style="line-height: 34px; padding-left: 20px;">
						<c:if test="${htc.user.name != null}">已选择用户:${htc.user.name}</c:if>
					</span>
				</p>
				<form name="healthForm" class="form-horizontal" role="form">
					<input type="hidden" name="taskid" value="${htc.healthTask.id}"/>
					<input type="hidden" name="userid" value="${htc.healthTask.userid}"/>
					<label class="control-label col-md-2">
						疾病名称
					</label>
					<p class="col-md-10">
						<input type="text" class="form-control" name="disname" value="${htc.healthTask.disname}"
							placeholder="在此输入疾病名称">
					</p>
					<label class="control-label col-md-2">
						病情描述
					</label>
					<p class="col-md-10">
						<textarea class="form-control" name="disscript" rows="3" 
							placeholder="在此输入病情描述">${htc.healthTask.disscript}</textarea>
					</p>
					<label class="control-label col-md-2">
						已采取措施
					</label>
					<p class="col-md-10">
						<input type="text" class="form-control" name="donetreat" value="${htc.healthTask.donetreat}" 
							placeholder="在此输入已采取措施">
					</p>
					<label class="control-label col-md-2">
						希望得到的帮助
					</label>
					<p class="col-md-10">
						<input type="text" class="form-control" name="desireaid" value="${htc.healthTask.desireaid}" 
							placeholder="在此输入希望得到的帮助">
					</p>
					<label class="control-label col-md-2">
						医生建议
					</label>
					<p class="col-md-10">
						<input type="text" class="form-control" name="advice" value="${htc.doctorHealthTask.advice}" 
							placeholder="由医生填写" disabled>
					</p>
					<input type="hidden" name="doctorid" value="${htc.healthTask.doctorid}"/>
				</form>
				<p class="col-md-10 col-md-offset-2">
					<button class="btn btn-info col-md-2" name="doctorid" 
						onclick="openSelectDoctorModal()">选择医生</button>
					<span id="selectedDoctorInfo" style="line-height: 34px; padding-left: 20px;">
						<c:if test="${htc.doctor.name != null}">已选择医生:${htc.doctor.name}</c:if>
					</span>
				</p>
				<div class="col-md-2">
					<button class="btn btn-default btn-block" onclick="loadSpecificPage('health');">返回</button>
				</div>
				<div class="col-md-10">
					<button class="btn btn-success btn-block" name="submit" onclick="saveOrUpdate()">提交</button>
				</div>
			</div>
		</div>
		<div id="modalContainer"><!-- .modal --></div>
	</body>
</html>