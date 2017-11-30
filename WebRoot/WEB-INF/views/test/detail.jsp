<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
			initPager();
//			loadEcgwaveChart();
			loadHeartrateChart();
			loadBreathrateChart();
			loadSpo2valueChart();
			loadPluserateChart();
			loadBphighAndBplowchart();
			loadBprateChart();
			loadTemp1Chart();
		});
		
		function initPager() {
			$(".pagination").twbsPagination({
		        totalPages: ${totalPages},
		        startPage: ${page},
		        visiblePages: 10,
		        first: '第一页',
		        prev: '上一页',
		        next: '下一页',
		        last: '最后一页',
		        onPageClick: function (event, page) {
 					var url = "<%=basePath%>test/ecgwaveChart/${summaryId}/" + page + "/";
					$("#ecgwaveChartIframeContainer").attr("src", url);
		        }
		    });
		}

		function loadHeartrateChart() {
			var heartrateData = [];
			<c:forEach var="h" items="${heartrateData}">
				heartrateData.push({name: '${h.startTime}<br>~${h.endTime}', y: ${h.value}});
			</c:forEach>
			if(heartrateData.length == 1 && heartrateData[0].y == 0) {
				$('.heartrateChartContainer').hide();
			} else {
				$('.heartrateChart').highcharts({
				    chart: {
				        type: 'column'
				    },
				    title: {
				        text: '心率图'
				    },
				    subtitle: {
				        text: ''
				    },
				    xAxis: {
				        type: 'category',
				        labels: {
				            rotation: -45
				        },
				        title: {
				            text: '测量时间'
				        }
				    },
				    yAxis: {
				        title: {
				            text: '心率'
				        },
				        min: 1,
				        type: 'logarithmic'
				    },
				    legend: {
				        enabled: false
				    },
				    tooltip: {
				        enabled: false
				    },
				    credits: {
				        enabled: false
				    },
				    exporting: {
				        enabled: false
				    },
				    series: [{
				        data: heartrateData,
				        dataLabels: {
				            enabled: true,
				            style: {
				                fontSize: '24px'
				            }
				        }
				    }]
				});
			}
		}
		
		function loadBreathrateChart() {
			var breathrateData = [];
			<c:forEach var="h" items="${breathrateData}">
				breathrateData.push({name: '${h.startTime}<br>~${h.endTime}', y: ${h.value}});
			</c:forEach>
			if(breathrateData.length == 1 && breathrateData[0].y == 0) {
				$('.breathrateChartContainer').hide();
			} else {
				$('.breathrateChart').highcharts({
				    chart: {
				        type: 'column'
				    },
				    title: {
				        text: '呼吸率图'
				    },
				    subtitle: {
				        text: ''
				    },
				    xAxis: {
				        type: 'category',
				        labels: {
				            rotation: -45
				        },
				        title: {
				            text: '测量时间'
				        }
				    },
				    yAxis: {
				        title: {
				            text: '呼吸率'
				        },
				        min: 1,
				        type: 'logarithmic'
				    },
				    legend: {
				        enabled: false
				    },
				    tooltip: {
				        enabled: false
				    },
				    credits: {
				        enabled: false
				    },
				    exporting: {
				        enabled: false
				    },
				    series: [{
				        data: breathrateData,
				        dataLabels: {
				            enabled: true,
				            style: {
				                fontSize: '24px'
				            }
				        },
				        color: '#4eee94'
				    }]
				});
			}
		}
		
		function loadSpo2valueChart() {
			var spo2valueData = [];
			<c:forEach var="h" items="${spo2valueData}">
				spo2valueData.push({name: '${h.startTime}<br>~${h.endTime}', y: ${h.value}});
			</c:forEach>
			if(spo2valueData.length == 1 && spo2valueData[0].y == 0) {
				$('.spo2valueChartContainer').hide();
			} else {
				$('.spo2valueChart').highcharts({
				    chart: {
				        type: 'column'
				    },
				    title: {
				        text: '血氧图'
				    },
				    subtitle: {
				        text: ''
				    },
				    xAxis: {
				        type: 'category',
				        labels: {
				            rotation: -45
				        },
				        title: {
				            text: '测量时间'
				        }
				    },
				    yAxis: {
				        title: {
				            text: '血氧'
				        },
				        min: 1,
				        type: 'logarithmic'
				    },
				    legend: {
				        enabled: false
				    },
				    tooltip: {
				        enabled: false
				    },
				    credits: {
				        enabled: false
				    },
				    exporting: {
				        enabled: false
				    },
				    series: [{
				        data: spo2valueData,
				        dataLabels: {
				            enabled: true,
				            style: {
				                fontSize: '24px'
				            }
				        },
				        color: '#e2a21a'
				    }]
				});
			}
		}
		function loadPluserateChart() {
			var pluserateData = [];
			<c:forEach var="h" items="${pluserateData}">
				pluserateData.push({name: '${h.startTime}<br>~${h.endTime}', y: ${h.value}});
			</c:forEach>
			if(pluserateData.length == 1 && pluserateData[0].y == 0) {
				$('.pluserateChartContainer').hide();
			} else {
				$('.pluserateChart').highcharts({
				    chart: {
				        type: 'column'
				    },
				    title: {
				        text: '脉率图'
				    },
				    subtitle: {
				        text: ''
				    },
				    xAxis: {
				        type: 'category',
				        labels: {
				            rotation: -45
				        },
				        title: {
				            text: '测量时间'
				        }
				    },
				    yAxis: {
				        title: {
				            text: '脉率'
				        },
				        min: 1,
				        type: 'logarithmic'
				    },
				    legend: {
				        enabled: false
				    },
				    tooltip: {
				        enabled: false
				    },
				    credits: {
				        enabled: false
				    },
				    exporting: {
				        enabled: false
				    },
				    series: [{
				        data: pluserateData,
				        dataLabels: {
				            enabled: true,
				            style: {
				                fontSize: '24px'
				            }
				        },
				        color: '#df5353'
				    }]
				});
			}
		}
		
		function loadBphighAndBplowchart() {
			var timeArr = [
			<c:forEach var="b" items="${bphighAndBplowData}">
				'${b.startTime}<br>~${b.endTime}',
			</c:forEach>
			],
			bphighData = [
			<c:forEach var="b" items="${bphighAndBplowData}">
				${b.bphigh},
			</c:forEach>
			],
			bplowData = [
			<c:forEach var="b" items="${bphighAndBplowData}">
				${b.bplow},
			</c:forEach>
			];
			if(timeArr.length == 1 && bphighData[0] == 0 && bplowData[0] == 0) {
				$('.bphighAndBplowChartContainer').hide();
			} else {
				$('.bphighAndBplowChart').highcharts({
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
			var bprateData = [];
			<c:forEach var="h" items="${bprateData}">
				bprateData.push({name: '${h.startTime}<br>~${h.endTime}', y: ${h.value}});
			</c:forEach>
			if(bprateData.length == 1 && bprateData[0].y == 0) {
				$('.bprateChartContainer').hide();
			} else {
				$('.bprateChart').highcharts({
				    chart: {
				        type: 'column'
				    },
				    title: {
				        text: '血压脉率图'
				    },
				    subtitle: {
				        text: ''
				    },
				    xAxis: {
				        type: 'category',
				        labels: {
				            rotation: -45
				        },
				        title: {
				            text: '测量时间'
				        }
				    },
				    yAxis: {
				        title: {
				            text: '血压脉率'
				        },
				        min: 1,
				        type: 'logarithmic'
				    },
				    legend: {
				        enabled: false
				    },
				    tooltip: {
				        enabled: false
				    },
				    credits: {
				        enabled: false
				    },
				    exporting: {
				        enabled: false
				    },
				    series: [{
				        data: bprateData,
				        dataLabels: {
				            enabled: true,
				            style: {
				                fontSize: '24px'
				            }
				        },
				        color: '#55bf3b'
				    }]
				});
			}
		}
		
		function loadTemp1Chart() {
			var temp1Data = [];
			<c:forEach var="h" items="${temp1Data}">
				temp1Data.push({name: '${h.startTime}<br>~${h.endTime}', y: ${h.value}});
			</c:forEach>
			if(temp1Data.length == 1 && temp1Data[0].y == 0) {
				$('.temp1ChartContainer').hide();
			} else {
				$('.temp1Chart').highcharts({
				    chart: {
				        type: 'column'
				    },
				    title: {
				        text: '体温图'
				    },
				    subtitle: {
				        text: ''
				    },
				    xAxis: {
				        type: 'category',
				        labels: {
				            rotation: -45
				        },
				        title: {
				            text: '测量时间'
				        }
				    },
				    yAxis: {
				        title: {
				            text: '体温'
				        },
				        min: 1,
				        type: 'logarithmic'
				    },
				    legend: {
				        enabled: false
				    },
				    tooltip: {
				        enabled: false
				    },
				    credits: {
				        enabled: false
				    },
				    exporting: {
				        enabled: false
				    },
				    series: [{
				        data: temp1Data,
				        dataLabels: {
				            enabled: true,
				            style: {
				                fontSize: '24px'
				            }
				        },
				        color: '#8d4653'
				    }]
				});
			}
		}
		</script>
	</head>
	<body>
		<div class="panel panel-primary">
			<div class="panel-heading">
				<a href="<%=basePath%>test" style="color: white; margin-right: 38%;">
					<i class="glyphicon glyphicon-circle-arrow-left"></i>&nbsp;返回
				</a>
				分析自检报告数据
			</div>
			<div class="panel-body">
				<p class="col-md-2">
					<label>档案号：</label>
				</p>
				<p class="col-md-10">${dstr}</p>
				<p class="col-md-12">
					<label>心电图：</label>
				</p>
				<!--  
				<div class="col-md-12">
					<div class="mainbar">
						<div id="ecgwaveChart" 
							style="position: relative; padding: 1px; background: url('<%=basePath%>resources/images/heartback.png') no-repeat scroll center center transparent; margin: 10px; height: 500px; width: 900px;"
							Loading...
						</div>
					</div>
				</div>
				-->
				<div class="col-md-12">
					<iframe id="ecgwaveChartIframeContainer" src="<%=basePath%>test/ecgwaveChart/${summaryId}/1/" frameborder="no" border="0" 
						scrolling="no" allowtransparency="yes" style="width: 920px; height: 520px;"></iframe>
				</div>
				<div class="col-md-12" style="margin-left: 20px;">
					<ul class="pagination pull-left"></ul>
					<label style="margin-top:27px;margin-left:10px;" class="pull-left">
						总共&nbsp;${totalPages}&nbsp;页
					</label>
				</div>
				<div class="heartrateChartContainer">
					<p class="col-md-12">
						<label>心率：</label>
					</p>
					<p class="col-md-12 heartrateChart">
						Loading...
					</p>
				</div>
				<div class="breathrateChartContainer">
					<p class="col-md-12">
						<label>呼吸率：</label>
					</p>
					<p class="col-md-12 breathrateChart">
						Loading...
					</p>
				</div>
				<div class="spo2valueChartContainer">
					<p class="col-md-12">
						<label>血氧：</label>
					</p>
					<p class="col-md-12 spo2valueChart">
						Loading...
					</p>
				</div>
				<div class="pluserateChartContainer">
					<p class="col-md-12">
						<label>脉率：</label>
					</p>
					<p class="col-md-12 pluserateChart">
						Loading...
					</p>
				</div>
				<div class="bphighAndBplowChartContainer">
					<p class="col-md-12">
						<label>血压：</label>
					</p>
					<p class="col-md-12 bphighAndBplowChart">
						Loading...
					</p>
				</div>
				<div class="bprateChartContainer">
					<p class="col-md-12">
						<label>血压脉率：</label>
					</p>
					<p class="col-md-12 bprateChart">
						Loading...
					</p>
				</div>
				<div class="temp1ChartContainer">
					<p class="col-md-12">
						<label>体温：</label>
					</p>
					<p class="col-md-12 temp1Chart">
						Loading...
					</p>
				</div>
			</div>
		</div>
	</body>
</html>