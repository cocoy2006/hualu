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
	<style type="text/css">
		.highlight {
			font-size: 30px;
			color: #35B36A;
		}
	</style>
	<script type="text/javascript">
	$(function() {
		modal("report");
		if("${rtc.user.id}") {
			$("#userinfo").load("<%=basePath%>userinfo/${rtc.user.id}/");
		}
		setHighcharts();
		showBpCharts();
		showBreathCharts();
		showSpo2valueCharts();
		showPluseCharts();
		showHeartCharts();
		showBprateCharts();
		showTemp1Charts();
	});
	function setHighcharts() {
		Highcharts.setOptions({
	        colors: ['#B7E8E1', '#CEE28B', '#ED561B', 
	        	'#F6D749', '#FEB340', '#F2B0AE', '#D2BAD9']
	    });
	}
	function showBpCharts() {
		showBpLine();
		showBpResult1(${rtc.reportTask.bphigh }, ${rtc.reportTask.bplow }, 
			${rtc.reportTask.bpavg }, ${rtc.reportTask.bpresult });
		showBpPie();
		showBpColumn();
		showBpResult2(${fn:length(rtc.rtList)}, 
			${rtc.reportTask.hour }, ${rtc.reportTask.bpresult });
	}
	function showBpLine() {
		var container = document.getElementById("bpLine");
		var title = '血压趋势图';
		var time = [], data = [];
		<c:forEach var="rd" items="${rtc.rdList}">
			<c:if test="${rd.bphigh != null}">
				time.push("${rd.date}");
				data.push(['收缩压:${rd.bphigh} 舒张压:${rd.bplow} ' +
					<c:if test="${rd.bpresult == 1}">"偏低"</c:if>
					<c:if test="${rd.bpresult == 2}">"理想"</c:if>
					<c:if test="${rd.bpresult == 3}">"正常"</c:if>
					<c:if test="${rd.bpresult == 4}">"轻度1级"</c:if>
					<c:if test="${rd.bpresult == 5}">"中度2级"</c:if>
					<c:if test="${rd.bpresult == 6}">"重度3级"</c:if>
					<c:if test="${rd.bpresult == 7}">"极重度4级"</c:if>
				, ${rd.bphigh}]);
			</c:if>
		</c:forEach>
		showLine(container, title, time, data);
	}
	
	function showBpResult1(bphigh, bplow, bpavg, bpresult) {
		var result = "您的血压平均值<b class='highlight'>" + bphigh + "/" + bplow + "</b>mmHg <b class='highlight'>" + bpavg + "</b>bpm, 血压状态属于<b class='highlight'>";
		switch(bpresult) {
			case 1:
				result += "偏低</b>, 建议日常生活、饮食及运动三方面加强养生保健。"
				break;
			case 2:
				result += "理想</b>, 血压正常，请继续保持。"
				break;
			case 3:
				result += "正常</b>, 血压正常，注意清淡饮食，多食水果蔬菜，适量体育锻炼，加强养生保健。"
				break;
			case 4:
				result += "轻度1级</b>, 建议低盐饮食、戒咖啡类饮料；加强锻炼、减重；注意休息、不宜过度睡眠；有助于降低血压，坚持早中晚监测，及时掌握血压变化，必要时就医咨询。"
				break;
			case 5:
				result += "中度2级</b>, 建议就医治疗，遵医嘱用药；坚持早中晚监测，及时掌握血压变化，控制血压。"
				break;
			case 6:
				result += "重度3级</b>, 心血管疾病、心脏急症、肾病、脑卒中风险增加；增加测量频率、及时掌握血压变化。"
				break;
			case 7:
				result += "极重度4级</b>, 易发生脑出血、心力衰竭、肾功能衰竭等病变，随时可能发生生命危险。"
				break;
		}
		$("#bpResult1").html(result);
	}
	function showBpPie() {
		var container = document.getElementById("bpPie");
		var title = '血压分布图';
		var data = [
              	{name: '偏低', y: ${rtc.bpCount[0]}},
              	{name: '理想', y: ${rtc.bpCount[1]}},
              	{name: '正常', y: ${rtc.bpCount[2]}},
              	{name: '轻度1级', y: ${rtc.bpCount[3]}},
              	{name: '中度2级', y: ${rtc.bpCount[4]}},
              	{name: '重度3级', y: ${rtc.bpCount[5]}},
              	{name: '极重度4级', y: ${rtc.bpCount[6]}}
		];
		showPie(container, title, data);
	}
	
	function showBpColumn() {
		var container = document.getElementById("bpColumn");
		var categories = ['偏低', '理想', '正常', '轻度1级', '中度2级', '重度3级', '极重度4级'];
		var data = [
             	{y: ${rtc.bpCount[0]}, color: '#B7E8E1'},
             	{y: ${rtc.bpCount[1]}, color: '#CEE28B'},
             	{y: ${rtc.bpCount[2]}, color: '#ED561B'},
             	{y: ${rtc.bpCount[3]}, color: '#F6D749'},
             	{y: ${rtc.bpCount[4]}, color: '#FEB340'},
             	{y: ${rtc.bpCount[5]}, color: '#F2B0AE'},
             	{y: ${rtc.bpCount[6]}, color: '#D2BAD9'}
             	];
		showColumn(container, categories, data);
	}
	function showBpResult2(count, hour, bpresult) {
		var result = "您总共测量<b class='highlight'>" + count + "</b>次, 测量时间经常是在<b class='highlight'>" + hour + "</b>时左右, 测量结果最多的血压状态是:<b class='highlight'>";
		switch(bpresult) {
			case 1:
				result += "偏低";
				break;
			case 2:
				result += "理想";
				break;
			case 3:
				result += "正常";
				break;
			case 4:
				result += "轻度";
				break;
			case 5:
				result += "中度2级";
				break;
			case 6:
				result += "重度3级";
				break;
			case 7:
				result += "极重度4级";
				break;
		}
		$("#bpResult2").html(result + "</b>");
	}
	function showBreathCharts() {
		showBreathLine();
	}
	function showBreathLine() {
		var container = document.getElementById("breathLine");
		var title = '呼吸率趋势图';
		var time = [], data = [];
		<c:forEach var="rd" items="${rtc.rdList}">
			<c:if test="${rd.breathrate != null}">
				time.push("${rd.date}");
				data.push(['呼吸率:${rd.breathrate}', ${rd.breathrate}]);
			</c:if>
		</c:forEach>
		showLine(container, title, time, data);
	}
	function showSpo2valueCharts() {
		showSpo2valueLine();
	}
	function showSpo2valueLine() {
		var container = document.getElementById("spo2valueLine");
		var title = '血氧趋势图';
		var time = [], data = [];
		<c:forEach var="rd" items="${rtc.rdList}">
			<c:if test="${rd.spo2value != null}">
				time.push("${rd.date}");
				data.push(['血氧:${rd.spo2value}', ${rd.spo2value}]);
			</c:if>
		</c:forEach>
		showLine(container, title, time, data);
	}
	function showPluseCharts() {
		showPluseLine();
	}
	function showPluseLine() {
		var container = document.getElementById("pluseLine");
		var title = '脉率趋势图';
		var time = [], data = [];
		<c:forEach var="rd" items="${rtc.rdList}">
			<c:if test="${rd.pluserate != null}">
				time.push("${rd.date}");
				data.push(['脉率:${rd.pluserate}', ${rd.pluserate}]);
			</c:if>
		</c:forEach>
		showLine(container, title, time, data);
	}
	function showHeartCharts() {
		showHeartLine();
	}
	function showHeartLine() {
		var container = document.getElementById("heartLine");
		var title = '心率趋势图';
		var time = [], data = [];
		<c:forEach var="rd" items="${rtc.rdList}">
			<c:if test="${rd.heartrate != null}">
				time.push("${rd.date}");
				data.push(['心率:${rd.heartrate}', ${rd.heartrate}]);
			</c:if>
		</c:forEach>
		showLine(container, title, time, data);
	}
	function showBprateCharts() {
		showBprateLine();
	}
	function showBprateLine() {
		var container = document.getElementById("bprateLine");
		var title = '血压脉率趋势图';
		var time = [], data = [];
		<c:forEach var="rd" items="${rtc.rdList}">
			<c:if test="${rd.bprate != null}">
				time.push("${rd.date}");
				data.push(['血压脉率:${rd.bprate}', ${rd.bprate}]);
			</c:if>
		</c:forEach>
		showLine(container, title, time, data);
	}
	function showTemp1Charts() {
		showTemp1Line();
	}
	function showTemp1Line() {
		var container = document.getElementById("temp1Line");
		var title = '体温趋势图';
		var time = [], data = [];
		<c:forEach var="rd" items="${rtc.rdList}">
			<c:if test="${rd.temp1 != null}">
				time.push("${rd.date}");
				data.push(['体温:${rd.temp1}', ${rd.temp1}]);
			</c:if>
		</c:forEach>
		showLine(container, title, time, data);
	}
	function showLine(container, title, categories, data) {
		if(data.length == 0) {
			$(container).parents(".panel").first().hide();
			return false;
		}
		new Highcharts.Chart({
			chart: {
				backgroundColor: '#35B36A',
				renderTo: container,
				width: 826,
				type: 'spline'
			},
			title: {text: title},
			xAxis: {
				categories: categories,
				labels: {rotation: -45},
				lineColor: '#E3FFFA'
			},
			yAxis: {
				lineColor: '#E3FFFA',
				title: {text: ''}
			},
			legend: {enabled: false},
			tooltip: {
	            formatter: function () {
	                return '<b>' + this.x + '</b><br/>' + this.point.name;
	            }
	        },
		    credits: {enabled: false},
		    exporting: {enabled: false},
			series: [{
				color: '#E3FFFA',
				data: data
			}]
		});
	}
	function showPie(container, title, data) {
		new Highcharts.Chart({
			chart: {
				renderTo: container,
				width: 826
			},
			title: {text: title},
			legend: {enabled: false},
			tooltip: {
				formatter: function() {
					return '<b>'+ this.point.name +'</b>: '+ this.percentage +' %';
				}
			},
		    credits: {enabled: false},
		    exporting: {enabled: false},
			series: [{
                type: 'pie',
                data: data,
               	size: 200,
                showInLegend: false,
                dataLabels: {
					enabled: true,
					formatter: function() {
						if(this.percentage != 0) {
							return '<b>'+ this.point.name +'</b>: '+ this.percentage +' %';
						}
					}
				}
            }]
		});
	}
	function showColumn(container, categories, data) {
		new Highcharts.Chart({
			chart: {
				renderTo: container,
				width: 826
			},
			title: {text: ''},
			xAxis: {
				categories: categories
			},
			yAxis: {
				title: {text: ''}
			},
			legend: {enabled: false},
			tooltip: {enabled: false},
		    credits: {enabled: false},
		    exporting: {enabled: false},
			series: [{
                type: 'column',
                name: '',
                data: data,
                dataLabels: {
					enabled: true,
					formatter: function() {
						return '<b>'+ this.y +'次</b>';
					}
				}
            }]
		});
	}
	</script>
	</head>
	<body>
		<div id="reportTaskModal" class="modal fade" role="dialog" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
						<h4 class="modal-title">综合报告详情</h4>
					</div>
					<div class="modal-body" style="overflow:auto; zoom:1;">
						<div id="userinfo"><!-- 用户信息 --></div>
						<div>
							<label>报告时间：</label>
							${rtc.reportTask.starttime}~${rtc.reportTask.endtime}
						</div>
						<div class="tabbable">
							<ul class="nav nav-tabs nav-pills">
								<li class="active">
									<a href="#11" data-toggle="tab">
										测量指标
									</a>
								</li>
								<li>
									<a href="#12" data-toggle="tab">
										测量历史记录
									</a>
								</li>
								<li>
									<a href="#13" data-toggle="tab">
										体检报告
									</a>
								</li>
							</ul>
							<div class="tab-content">
								<div class="tab-pane active" id="11">
									<div class="panel-group" id="accordion" role="tablist"
										aria-multiselectable="true">
										<div class="panel panel-default">
											<div class="panel-heading" role="tab" id="headingBp">
												<h4 class="panel-title">
													<a data-toggle="collapse" data-parent="#accordion"
														href="#collapseBp" aria-expanded="true"
														aria-controls="collapseBp">血压</a>
													<small>平均值:${rtc.reportTask.bphigh }/${rtc.reportTask.bplow }mmHg ${rtc.reportTask.bpavg }bpm</small>
												</h4>
											</div>
											<div id="collapseBp" class="panel-collapse collapse in"
												role="tabpanel" aria-labelledby="headingBp">
												<div class="panel-body">
													<div id="bpLine">无数据</div>
													<div>
														<blockquote>
  															<p id="bpResult1"></p>
														</blockquote>
													</div>
													<div id="bpPie"></div>
													<div id="bpColumn"></div>
													<div>
														<blockquote>
  															<p id="bpResult2"></p>
														</blockquote>
													</div>
												</div>
											</div>
										</div>
										<div class="panel panel-default">
											<div class="panel-heading" role="tab" id="headingBreathrate">
												<h4 class="panel-title">
													<a class="collapsed" data-toggle="collapse"
														data-parent="#accordion" href="#collapseBreathrate"
														aria-expanded="false" aria-controls="collapseBreathrate">呼吸率 </a>
													<small>平均值:${rtc.reportTask.breathrate }</small>
												</h4>
											</div>
											<div id="collapseBreathrate" class="panel-collapse collapse"
												role="tabpanel" aria-labelledby="headingBreathrate">
												<div class="panel-body">
													<div id="breathLine">无数据</div>
												</div>
											</div>
										</div>
										<div class="panel panel-default">
											<div class="panel-heading" role="tab" id="headingSpo2value">
												<h4 class="panel-title">
													<a class="collapsed" data-toggle="collapse"
														data-parent="#accordion" href="#collapseSpo2value"
														aria-expanded="false" aria-controls="collapseSpo2value">血氧 </a>
													<small>平均值:${rtc.reportTask.spo2value }</small>
												</h4>
											</div>
											<div id="collapseSpo2value" class="panel-collapse collapse"
												role="tabpanel" aria-labelledby="headingSpo2value">
												<div class="panel-body">
													<div id="spo2valueLine">无数据</div>
												</div>
											</div>
										</div>
										<div class="panel panel-default">
											<div class="panel-heading" role="tab" id="headingPluserate">
												<h4 class="panel-title">
													<a class="collapsed" data-toggle="collapse"
														data-parent="#accordion" href="#collapsePluserate"
														aria-expanded="false" aria-controls="collapsePluserate">脉率 </a>
													<small>平均值:${rtc.reportTask.pluserate }</small>
												</h4>
											</div>
											<div id="collapsePluserate" class="panel-collapse collapse"
												role="tabpanel" aria-labelledby="headingPluserate">
												<div class="panel-body">
													<div id="pluseLine">无数据</div>
												</div>
											</div>
										</div>
										<div class="panel panel-default">
											<div class="panel-heading" role="tab" id="headingHeartrate">
												<h4 class="panel-title">
													<a class="collapsed" data-toggle="collapse"
														data-parent="#accordion" href="#collapseHeartrate"
														aria-expanded="false" aria-controls="collapseHeartrate">心率 </a>
													<small>平均值:${rtc.reportTask.heartrate }</small>
												</h4>
											</div>
											<div id="collapseHeartrate" class="panel-collapse collapse"
												role="tabpanel" aria-labelledby="headingHeartrate">
												<div class="panel-body">
													<div id="heartLine">无数据</div>
												</div>
											</div>
										</div>
										<div class="panel panel-default">
											<div class="panel-heading" role="tab" id="headingBprate">
												<h4 class="panel-title">
													<a class="collapsed" data-toggle="collapse"
														data-parent="#accordion" href="#collapseBprate"
														aria-expanded="false" aria-controls="collapseBprate">血压脉率 </a>
													<small>平均值:${rtc.reportTask.bprate }</small>
												</h4>
											</div>
											<div id="collapseBprate" class="panel-collapse collapse"
												role="tabpanel" aria-labelledby="headingBprate">
												<div class="panel-body">
													<div id="bprateLine">无数据</div>
												</div>
											</div>
										</div>
										<div class="panel panel-default">
											<div class="panel-heading" role="tab" id="headingTemp1">
												<h4 class="panel-title">
													<a class="collapsed" data-toggle="collapse"
														data-parent="#accordion" href="#collapseTemp1"
														aria-expanded="false" aria-controls="collapseTemp1">体温 </a>
													<small>平均值:${rtc.reportTask.temp1 }</small>
												</h4>
											</div>
											<div id="collapseTemp1" class="panel-collapse collapse"
												role="tabpanel" aria-labelledby="headingTemp1">
												<div class="panel-body">
													<div id="temp1Line">无数据</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="tab-pane" id="12">
									<table class="table table-striped table-bordered recordTaskList" cellspacing="0" width="100%">
										<thead>
											<tr>
												<th>序号</th>
												<th>开始时间</th>
												<th>结束时间 </th>
											</tr>
										</thead>
										<tbody>
										<c:if test="${fn:length(rtc.rtList) > 0}">
										   	<c:forEach var="rt" items="${rtc.rtList}" varStatus="s">
										   	<tr>
												<td>${s.count}</td>
												<td>${rt.starttime}</td>
												<td>${rt.endtime}</td>
											</tr>
										   	</c:forEach>
										</c:if>
										</tbody>
									</table>
								</div>
								<div class="tab-pane" id="13">
								<c:if test="${fn:length(rtc.rpList) > 0}">
								   	<c:forEach var="rp" items="${rtc.rpList}" varStatus="s">
								   	<img src="<%=basePath %>upload/report/${rtc.user.id}/${rp.url}" class="img-thumbnail">
								   	</c:forEach>
								</c:if>
								</div>
							</div>
						</div>
						<hr/>
						<p class="col-md-2">
							<label>报告及建议：</label>
						</p>
						<p class="col-md-10">
							<textarea rows="2" name="advice" style="width: 100%;">${rtc.reportTask.advice}</textarea>
						</p>
						<p class="col-md-2">
							<button type="button" class="btn btn-default btn-block" data-dismiss="modal">返回</button>
						</p>
						<p class="col-md-5" name="draft">
							<button type="button" class="btn btn-info btn-block" 
								onclick="draft(${rtc.reportTask.id})">存草稿箱</button>
						</p>
						<p class="col-md-5" name="next">
							<button type="button" class="btn btn-success btn-block" 
								onclick="next(${rtc.reportTask.id})">提交</button>
						</p>
						
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
	</body>
</html>