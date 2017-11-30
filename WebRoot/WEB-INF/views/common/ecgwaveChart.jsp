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
		<!-- HTML5 Support for IE -->
		<!--[if lt IE 9]>
          	<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          	<script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->
		<!-- Favicon -->
		<!-- CSS -->
		<!-- JS -->
		<script type="text/javascript" src="<%=basePath%>resources/js/jquery.js"></script>
		<script type="text/javascript" src="<%=basePath%>resources/js/excanvas.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>resources/js/jquery.flot.js"></script>
		<script type="text/javascript">
		$(function() {
			loadEcgwaveChart();
		});
		function loadEcgwaveChart() {
			// 获取体检数据
			<c:forEach var="data" items="${ecgwaveData}" varStatus="i">
				var data${i.index + 1} = [
				<c:forEach var="d" items="${data}" varStatus="j">[${j.index + 1}, ${d}],</c:forEach>
				];
			</c:forEach>
			var timeArr = [
			<c:forEach var="e" items="${ecgwaveTime}">"${e}",</c:forEach>
			];
			if("${ecgwaveLength}") {
				top.window.ecgwaveLength = <c:out value="${ecgwaveLength}">0</c:out>;
				//定义表格属性
				var plot = $.plot($("#ecgwaveChart"), 
					[{data: data1}, {data: data2}, 
					{data: data3}, {data: data4}, 
					{data: data5}, {data: data6}, 
					{data: data7}], {
					series: {
						lines: {
							show: true,
							lineWidth: 1
						},
						points: {
							show: false
						},
						shadowSize: 0,
					},
					colors: ["red", "blue", "black", "green", "red", "blue", "black"],
					grid: {
						hoverable: true,
						clickable: true
					},
					yaxis: {
						show: false,
						min: 0,
						max: 1200
					},
					xaxis: {
						show: false,
						min: 1,
						max: top.window.ecgwaveLength == 500 ? 4450 : 2670,
						tickDecimals: 0,
						tickFormatter: function() {
							return "";
						}
					},
					// 拖动
					pan: {
						interactive: true
					}
				});
				var dotNo = "";
				function showTooltip(x, y, contents) {
					var tooltip = $('<div id="tooltip' + dotNo + '">' + contents + '</div>');
					tooltip.css({
						position: 'absolute',
						display: 'none',
						top: y,
						left: x + 10,
						border: '1px solid #fdd',
						padding: '2px',
						'background-color': '#fee',
						opacity: 0.80
					});
					if(x > 500) {
						tooltip.css("left", x - 250);
					}
					if(y > 250) {
						tooltip.css("top", y - 50);
					}
					tooltip.appendTo(".mainbar").fadeIn(200);
				}
				var previousPoint = null;
				$("#ecgwaveChart").bind("plothover", function(event, pos, item) {
					if (item) {
						if (previousPoint != item.dataIndex) {
							previousPoint = item.dataIndex;
							$("#tooltip" + dotNo).remove();
							var x = timeArr[item.dataIndex];
							var y = parseInt(item.datapoint[1].toFixed(2));
		
							showTooltip(item.pageX, item.pageY, " 测量时间：" + x + "<br />测量值：" + y);
						}
					} else {
						$("#tooltip" + dotNo).remove();
						previousPoint = null;
					}
				}).bind("plotclick", function(event, pos, item) {
					if (item) {
						if (top.window.ecgwaveToggle) {
							$(top.window.document.getElementById("piecestarttime")).val("" + timeArr[item.dataIndex] + "");
							$(top.window.document.getElementById("startIndex")).val("" + item.dataIndex + "");
							$(top.window.document.getElementById("startPage")).val("" + top.window.ecgwavePage + "");
							top.window.ecgwaveToggle = false;
						} else {
							$(top.window.document.getElementById("pieceendtime")).val("" + timeArr[item.dataIndex] + "");
							$(top.window.document.getElementById("endIndex")).val("" + item.dataIndex + "");
							$(top.window.document.getElementById("endPage")).val("" + top.window.ecgwavePage + "");
							top.window.ecgwaveToggle = true;
						}
					}
				});
				// 微调
				$("#ecgwaveChart").css('padding', '1px');
			} else {
				// hide ecgwave1 module
			}
		}
		</script>
	</head>
	<body>
		<div class="mainbar">
			<div id="ecgwaveChart" 
				style="position: relative; padding: 1px; background: url('<%=basePath%>resources/images/heartback.png') no-repeat scroll center center transparent; margin: 10px; height: 500px; width: 900px;"
				Loading...
			</div>
		</div>
	</body>
</html>