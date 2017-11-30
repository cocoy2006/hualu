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
		<script type="text/javascript" src="<%=basePath%>resources/js/jquery.js"></script>
		<script type="text/javascript">
		$(function() {});
		function recordtasksoperator() {
			var operatorid = $("#recordtasksoperator input[name='operatorid']").val();
			var page = $("#recordtasksoperator input[name='page']").val();
			if(operatorid && page) {
				ajax("<%=basePath %>api/recordtasks/operator/" + operatorid + "/" + page + "/");
			} else {
				alert("参数不能为空")
			}
		}
		function recordtasksuser() {
			var userid = $("#recordtasksuser input[name='userid']").val();
			var page = $("#recordtasksuser input[name='page']").val();
			if(userid && page) {
				ajax("<%=basePath %>api/recordtasks/user/" + userid + "/" + page + "/");
			} else {
				alert("参数不能为空")
			}
		}
		function recordtask() {
			var id = $("#recordtask input[name='id']").val();
			if(id) {
				ajax("<%=basePath %>api/recordtask/" + id + "/");
			} else {
				alert("参数不能为空")
			}
		}
		function recorddata() {
			var id = $("#recorddata input[name='id']").val();
			var page = $("#recorddata input[name='page']").val();
			if(id && page) {
				ajax("<%=basePath %>api/recorddata/" + id + "/" + page + "/");
			} else {
				alert("参数不能为空")
			}
		}
		function reporttasksoperator() {
			var operatorid = $("#reporttasksoperator input[name='operatorid']").val();
			var page = $("#reporttasksoperator input[name='page']").val();
			if(operatorid && page) {
				ajax("<%=basePath %>api/reporttasks/operator/" + operatorid + "/" + page + "/");
			} else {
				alert("参数不能为空")
			}
		}
		function reporttasksuser() {
			var userid = $("#reporttasksuser input[name='userid']").val();
			var page = $("#reporttasksuser input[name='page']").val();
			if(userid && page) {
				ajax("<%=basePath %>api/reporttasks/user/" + userid + "/" + page + "/");
			} else {
				alert("参数不能为空")
			}
		}
		function reporttask() {
			var id = $("#reporttask input[name='id']").val();
			if(id) {
				ajax("<%=basePath %>api/reporttask/" + id + "/");
			} else {
				alert("参数不能为空")
			}
		}
		function remindsoperator() {
			var operatorid = $("#remindsoperator input[name='operatorid']").val();
			var page = $("#remindsoperator input[name='page']").val();
			if(operatorid && page) {
				ajax("<%=basePath %>api/reminds/operator/" + operatorid + "/" + page + "/");
			} else {
				alert("参数不能为空")
			}
		}
		function remindsuser() {
			var userid = $("#remindsuser input[name='userid']").val();
			var page = $("#remindsuser input[name='page']").val();
			if(userid && page) {
				ajax("<%=basePath %>api/reminds/user/" + userid + "/" + page + "/");
			} else {
				alert("参数不能为空")
			}
		}
		function remind() {
			var id = $("#remind input[name='id']").val();
			if(id) {
				ajax("<%=basePath %>api/remind/" + id + "/");
			} else {
				alert("参数不能为空")
			}
		}
		function read() {
			var id = $("#read input[name='id']").val();
			if(id) {
				ajax("<%=basePath %>api/remind/read/" + id + "/");
			} else {
				alert("参数不能为空")
			}
		}
		function healthtasksoperator() {
			var operatorid = $("#healthtasksoperator input[name='operatorid']").val();
			var page = $("#healthtasksoperator input[name='page']").val();
			if(operatorid && page) {
				ajax("<%=basePath %>api/healthtasks/operator/" + operatorid + "/" + page + "/");
			} else {
				alert("参数不能为空")
			}
		}
		function healthtasksuser() {
			var userid = $("#healthtasksuser input[name='userid']").val();
			var page = $("#healthtasksuser input[name='page']").val();
			if(userid && page) {
				ajax("<%=basePath %>api/healthtasks/user/" + userid + "/" + page + "/");
			} else {
				alert("参数不能为空")
			}
		}
		function healthtask() {
			var id = $("#healthtask input[name='id']").val();
			if(id) {
				ajax("<%=basePath %>api/healthtask/" + id + "/");
			} else {
				alert("参数不能为空")
			}
		}
		function signinoperator() {
			var role = $("#signinoperator input[name='role']").val();
			var loginname = $("#signinoperator input[name='loginname']").val();
			var passwd = $("#signinoperator input[name='passwd']").val();
			if(role && loginname && passwd) {
				ajax("<%=basePath %>api/signin/operator/" + role + "/" + loginname + "/" + passwd + "/");
			} else {
				alert("参数不能为空")
			}
		}
		function signinuser() {
			var loginname = $("#signinuser input[name='loginname']").val();
			var passwd = $("#signinuser input[name='passwd']").val();
			if(loginname && passwd) {
				ajax("<%=basePath %>api/signin/user/" + loginname + "/" + passwd + "/");
			} else {
				alert("参数不能为空")
			}
		}
		function ajax(url) {
			$.ajax({
				url: url,
				dataType: "json",
				success: function(data) {}
			});
		}
		</script>
	</head>
	<body>
		<div>
			<h3>自检报告</h3>
			<div id="recordtasksoperator">
				<h5>报告list查询非会员接口</h5>
				operatorid:<input type="text" name="operatorid" value=""/>
				page:<input type="text" name="page" value=""/>
				<button onclick="recordtasksoperator()">recordtasks/operator</button>
			</div>
			<div id="recordtasksuser">
				<h5>报告list查询会员接口</h5>
				userid:<input type="text" name="userid" value=""/>
				page:<input type="text" name="page" value=""/>
				<button onclick="recordtasksuser()">recordtasks/user</button>
			</div>
			<div id="recordtask">
				<h5>单条记录内容查询接口</h5>
				id:<input type="text" name="id" value=""/>
				<button onclick="recordtask()">recordtask</button>
			</div>
			<div id="recorddata">
				<h5>单页心电图内容查询接口</h5>
				id:<input type="text" name="id" value=""/>
				page:<input type="text" name="page" value=""/>
				<button onclick="recorddata()">recorddata</button>
			</div>
		</div>
		<div>
			<h3>综合报告</h3>
			<div id="reporttasksoperator">
				<h5>报告list查询非会员接口</h5>
				operatorid:<input type="text" name="operatorid" value=""/>
				page:<input type="text" name="page" value=""/>
				<button onclick="reporttasksoperator()">reporttasks/operator</button>
			</div>
			<div id="reporttasksuser">
				<h5>报告list查询会员接口</h5>
				userid:<input type="text" name="userid" value=""/>
				page:<input type="text" name="page" value=""/>
				<button onclick="reporttasksuser()">reporttasks/user</button>
			</div>
			<div id="reporttask">
				<h5>单条记录内容查询接口</h5>
				id:<input type="text" name="id" value=""/>
				<button onclick="reporttask()">reporttask</button>
			</div>
		</div>
		<div>
			<h3>健康消息（即提醒）</h3>
			<div id="remindsoperator">
				<h5>消息list查询非会员接口</h5>
				operatorid:<input type="text" name="operatorid" value=""/>
				page:<input type="text" name="page" value=""/>
				<button onclick="remindsoperator()">reminds/operator</button>
			</div>
			<div id="remindsuser">
				<h5>消息list查询会员接口</h5>
				userid:<input type="text" name="userid" value=""/>
				page:<input type="text" name="page" value=""/>
				<button onclick="remindsuser()">reminds/user</button>
			</div>
			<div id="remind">
				<h5>单条记录内容查询接口</h5>
				id:<input type="text" name="id" value=""/>
				<button onclick="remind()">remind</button>
			</div>
			<div id="read">
				<h5>消息已读反馈</h5>
				id:<input type="text" name="id" value=""/>
				<button onclick="read()">read</button>
			</div>
		</div>
		<div>
			<h3>健康咨询</h3>
			<div id="healthtasksoperator">
				<h5>咨询list查询非会员接口</h5>
				operatorid:<input type="text" name="operatorid" value=""/>
				page:<input type="text" name="page" value=""/>
				<button onclick="healthtasksoperator()">healthtasks/operator</button>
			</div>
			<div id="healthtasksuser">
				<h5>咨询list查询会员接口</h5>
				userid:<input type="text" name="userid" value=""/>
				page:<input type="text" name="page" value=""/>
				<button onclick="healthtasksuser()">healthtasks/user</button>
			</div>
			<div id="healthtask">
				<h5>单条记录内容查询接口</h5>
				id:<input type="text" name="id" value=""/>
				<button onclick="healthtask()">healthtask</button>
			</div>
		</div>
		<div>
			<h3>用户登录</h3>
			<div id="signinoperator">
				<h5>非会员登录</h5>
				role:<input type="text" name="role" value=""/>
				loginname:<input type="text" name="loginname" value=""/>
				passwd:<input type="text" name="passwd" value=""/>
				<button onclick="signinoperator()">signin/operator</button>
			</div>
			<div id="signinuser">
				<h5>会员登录</h5>
				loginname:<input type="text" name="loginname" value=""/>
				passwd:<input type="text" name="passwd" value=""/>
				<button onclick="signinuser()">signin/user</button>
			</div>
		</div>
	</body>
</html>