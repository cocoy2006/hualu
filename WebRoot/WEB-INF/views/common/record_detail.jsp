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
		<!-- Title and other stuffs -->
		<title>华录医疗</title>
		<meta name="keywords" content="医疗" />
		<meta name="description" content="华录医疗" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<!-- HTML5 Support for IE -->
		<!--[if lt IE 9]>
          	<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          	<script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->
		<!-- Favicon -->
		<link rel="shortcut icon" href="<%=basePath%>resources/images/favicon.ico">
		<!-- CSS -->
		<link rel="stylesheet" href="<%=basePath%>resources/bootstrap-3.0.3/css/bootstrap.css">
		<link rel="stylesheet" href="<%=basePath%>resources/bootstrap-3.0.3/font-awesome/css/font-awesome.css">
		<link rel="stylesheet" href="<%=basePath%>resources/DataTables-1.10.4/examples/resources/bootstrap/3/dataTables.bootstrap.css">
		<!-- JS -->
		<script type="text/javascript" src="<%=basePath%>resources/js/jquery.js"></script>
		<script type="text/javascript" src="<%=basePath%>resources/js/excanvas.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>resources/js/jquery.flot.js"></script>
		<script type="text/javascript" src="<%=basePath%>resources/js/jquery.twbsPagination.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>resources/bootstrap-3.0.3/js/bootstrap.js"></script>
		<script type="text/javascript" src="<%=basePath%>resources/DataTables-1.10.4/media/js/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>resources/DataTables-1.10.4/examples/resources/bootstrap/3/dataTables.bootstrap.js"></script>
		<script type="text/javascript" src="<%=basePath%>resources/Highcharts-4.0.1/js/highcharts.js"></script>
		<script type="text/javascript">
		$(function() {
			initPager(<c:out value="${page}">1</c:out>);
			bindRedirect();
			loadHeartrateChart();
			loadBreathrateChart();
			loadSpo2valueChart();
			loadPluserateChart();
			loadBpChart();
			loadBprateChart();
			loadTemp1Chart();
		});
//		var pagination;
		function initPager(startPage) {
			$(".pagination").twbsPagination({
		        totalPages: <c:out value="${totalPages}">1</c:out>,
		        startPage: startPage,
		        visiblePages: 10,
		        first: ' 首页 ',
		        prev: ' < ',
		        next: ' > ',
		        last: ' 末页 ',
		        onPageClick: function (event, page) {
 					changeEcgwaveChart(page);
		        }
		    });
		}
		function bindRedirect() {
			$("#ecgRedirect").keyup(function(e) {
				var page = parseInt($(this).val());
				var totalPages = <c:out value="${totalPages}">1</c:out>;
				if (isNaN(page) || page < 1) {
					page = 1;
					$(this).val("");
				} else if (page > totalPages) {
					page = totalPages;
					$(this).val(page);
				}
				if(page != top.window.ecgwavePage) {
					changePage(page);
					changeEcgwaveChart(page);
				}
			});
		}
		function changeEcgwaveChart(page) {
			var url = "<%=basePath%>ymRecordData/ecgwaveChart/${task.id}/" + page + "/";
			$("#ecgwaveChartIframeContainer").attr("src", url);
			top.window.ecgwavePage = page;
		}
		function changePage(page) {
			$('#paginationContainer').html('');
		    $('#paginationContainer').html('<ul class="pagination" style="float: right; margin: 2px;"></ul>');
			initPager(page);
		}
		function loadHeartrateChart() {
			var data = [];
			<c:forEach var="h" items="${heartrateData}">
				data.push({name: '${h.startTime}<br>~${h.endTime}', y: ${h.value}});
			</c:forEach>
			if(data.length == 0) {
				$('.heartrateChartContainer').hide();
			} else {
				chart('.heartrateChart', '心率图', '心率', seriesWidth(data.length), data, '#7cb5ec');
			}
		}
		
		function loadBreathrateChart() {
			var data = [];
			<c:forEach var="h" items="${breathrateData}">
				data.push({name: '${h.startTime}<br>~${h.endTime}', y: ${h.value}});
			</c:forEach>
			if(data.length == 0) {
				$('.breathrateChartContainer').hide();
			} else {
				chart('.breathrateChart', '呼吸率图', '呼吸率', seriesWidth(data.length), data, '#4eee94');
			}
		}
		
		function loadSpo2valueChart() {
			var data = [];
			<c:forEach var="h" items="${spo2valueData}">
				data.push({name: '${h.startTime}<br>~${h.endTime}', y: ${h.value}});
			</c:forEach>
			if(data.length == 0) {
				$('.spo2valueChartContainer').hide();
			} else {
				chart('.spo2valueChart', '血氧图', '血氧', seriesWidth(data.length), data, '#e2a21a');
			}
		}
		function loadPluserateChart() {
			var data = [];
			<c:forEach var="h" items="${pluserateData}">
				data.push({name: '${h.startTime}<br>~${h.endTime}', y: ${h.value}});
			</c:forEach>
			if(data.length == 0) {
				$('.pluserateChartContainer').hide();
			} else {
				chart('.pluserateChart', '脉率图', '脉率', seriesWidth(data.length), data, '#df5353');
			}
		}
		
		function loadBpChart() {
			var timeArr = [
			<c:forEach var="b" items="${bpData}">
				'${b.startTime}<br>~${b.endTime}',
			</c:forEach>
			],
			bphighData = [
			<c:forEach var="b" items="${bpData}">
				${b.bphigh},
			</c:forEach>
			],
			bplowData = [
			<c:forEach var="b" items="${bpData}">
				${b.bplow},
			</c:forEach>
			];
			if(timeArr.length == 0) {
				$('.bpChartContainer').hide();
			} else {
				$('.bpChart').highcharts({
					title: {
		                text: '血压图' 
		            },
			        xAxis: {
			            categories: timeArr,
						labels: {
		                    rotation: -15,
		                    align: 'right'
						}
			        },
					yAxis: {
		                min: 0,
		                title: {
		                    text: ''
		                }
		            },
			        labels: {
			            items: [{
			                html: '',
			                style: {
			                    left: '50px',
			                    top: '18px',
			                    color: (Highcharts.theme && Highcharts.theme.textColor) || 'black'
			                }
			            }]
			        },
					credits: {
		                enabled: false
		            },	
					exporting: {
						enabled: false
					},
			        series: [{
			            type: 'column',
			            name: '收缩压',
			            data: bphighData,
						dataLabels: {
				            enabled: true,
				            style: {
				                fontSize: '24px'
				            }
				        },
				        color: '#7798bf'
			        }, {
			            type: 'column',
			            name: '舒张压',
			            data: bplowData,
			            color: '#910000',
						dataLabels: {
				            enabled: true,
				            style: {
				                fontSize: '24px'
				            }
				        },
				        color: '#dddf0d'
			        }]
			    });
			}
		}
		
		function loadBprateChart() {
			var data = [];
			<c:forEach var="h" items="${bprateData}">
				data.push({name: '${h.startTime}<br>~${h.endTime}', y: ${h.value}});
			</c:forEach>
			if(data.length == 0) {
				$('.bprateChartContainer').hide();
			} else {
				chart('.bprateChart', '血压脉率图', '血压脉率', seriesWidth(data.length), data, '#55bf3b');
			}
		}
		
		function loadTemp1Chart() {
			var data = [];
			<c:forEach var="h" items="${temp1Data}">
				data.push({name: '${h.startTime}<br>~${h.endTime}', y: ${h.value}});
			</c:forEach>
			if(data.length == 0) {
				$('.temp1ChartContainer').hide();
			} else {
				chart('.temp1Chart', '体温图', '体温', seriesWidth(data.length), data, '#e2a21a');
			}
		}
		
		function chart(that, titleText, yAxisText, seriesWidth, seriesData, seriesColor) {
			$(that).highcharts({
			    chart: {type: 'column'},
			    title: {text: titleText},
			    subtitle: {text: ''},
			    xAxis: {
			        type: 'category',
			        labels: {
//			            rotation: -45
						enabled: false
			        },
			        title: {
			            text: '测量时间'
			        }
			    },
			    yAxis: {
			        title: {
			            text: yAxisText
			        },
			        min: 0
//			        type: 'logarithmic'
			    },
			    plotOptions: {
		            series: {pointWidth: seriesWidth}
		        },
			    legend: {enabled: false},
			    tooltip: {enabled: true},
			    credits: {enabled: false},
			    exporting: {enabled: false},
			    series: [{
			        data: seriesData,
			        name: yAxisText,
			        dataLabels: {
			            enabled: true,
			            formatter: function() {
			    			if(this.y == 0) return "0";
			            }
			        },
			        color: seriesColor
			    }]
			});
		}
		function seriesWidth(dataLength) {
			var width = 900 / dataLength / 2;
			return width > 100 ? 100 : width;
		}
		</script>
	</head>
	<body>
		<div>
			<label>心电图：</label>
		</div>
		<div>
			<iframe id="ecgwaveChartIframeContainer" src="<%=basePath%>ymRecordData/ecgwaveChart/${task.id}/1/" frameborder="no" border="0" 
				scrolling="no" allowtransparency="yes" style="width: 920px; height: 520px;"></iframe>
		</div>
		<div class="col-xs-3">
			<label style="margin-left:20px;">
				总共&nbsp;<c:out value="${totalPages}">1</c:out>&nbsp;页
			</label>
		</div>
		<div class="col-xs-9">
			<div style="float: right; margin-top: 2px;">
				<input type="text" id="ecgRedirect" class="redirect">/<c:out value="${totalPages}">1</c:out>页
			</div>
			<div id="paginationContainer">
				<ul class="pagination" style="float: right; margin: 2px;"></ul>
			</div>
		</div>
		<div class="heartrateChartContainer">
			<div>
				<label>心率：</label>
			</div>
			<div class="heartrateChart">
				Loading...
			</div>
		</div>
		<div class="breathrateChartContainer">
			<div>
				<label>呼吸率：</label>
			</div>
			<div class="breathrateChart">
				Loading...
			</div>
		</div>
		<div class="spo2valueChartContainer">
			<div>
				<label>血氧：</label>
			</div>
			<div class="spo2valueChart">
				Loading...
			</div>
		</div>
		<div class="pluserateChartContainer">
			<div>
				<label>脉率：</label>
			</div>
			<div class="pluserateChart">
				Loading...
			</div>
		</div>
		<div class="bpChartContainer">
			<div>
				<label>血压：</label>
			</div>
			<div class="bpChart">
				Loading...
			</div>
		</div>
		<div class="bprateChartContainer">
			<div>
				<label>血压脉率：</label>
			</div>
			<div class="bprateChart">
				Loading...
			</div>
		</div>
		<div class="temp1ChartContainer">
			<div>
				<label>体温：</label>
			</div>
			<div class="temp1Chart">
				Loading...
			</div>
		</div>
	</body>
</html>