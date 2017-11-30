<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
	<head></head>
	<body>
	<c:if test="${rtc != null}">
		<c:if test="${rtc.user != null}">
			<c:out value="${rtc.user.name}"></c:out>
		</c:if>
		先生（女士），您好！您于
		<fmt:formatDate pattern="MM月dd日HH点mm分" value="${rtc.recordTask.endtime}" />
		上传的数据，结果为：
		<form name="indexForm">
			<!-- 血压 -->
			<p style="width: 266px;">
				<input type="checkbox" name="index" value="bpall"
			<c:if test="${rtc.recordTask.bphigh != null}">
				checked="checked"/>
				血压：<fmt:formatNumber type="number" maxFractionDigits="0" value="${rtc.recordTask.bphigh}" />/
					<fmt:formatNumber type="number" maxFractionDigits="0" value="${rtc.recordTask.bplow}" />，
				平均压：<fmt:formatNumber type="number" maxFractionDigits="0" value="${rtc.recordTask.bpavg}" />，
			</c:if>
			<c:if test="${rtc.recordTask.bphigh == null}">
				/>
				血压：null/null，平均压：null，
			</c:if>
			</p>
			<!-- 血压脉率 -->
			<p>
				<input type="checkbox" name="index" value="bprate"
			<c:if test="${rtc.recordTask.bprate != null}">
				checked="checked"/>
				血压脉率：<fmt:formatNumber type="number" maxFractionDigits="0" value="${rtc.recordTask.bprate}" />，
			</c:if>
			<c:if test="${rtc.recordTask.bprate == null}">
				/>
				血压脉率：null，
			</c:if>
			</p>
			<!-- 脉率 -->
			<p>
				<input type="checkbox" name="index" value="pluserate"
			<c:if test="${rtc.recordTask.pluserate != null}">
				checked="checked"/>
				脉率：<fmt:formatNumber type="number" maxFractionDigits="0" value="${rtc.recordTask.pluserate}" />，
			</c:if>
			<c:if test="${rtc.recordTask.pluserate == null}">
				/>
				脉率：null，
			</c:if>
			</p>
			<!-- 呼吸率 -->
			<p>
				<input type="checkbox" name="index" value="breathrate"
			<c:if test="${rtc.recordTask.breathrate != null}">
				checked="checked"/>
				呼吸率：<fmt:formatNumber type="number" maxFractionDigits="0" value="${rtc.recordTask.breathrate}" />，
			</c:if>
			<c:if test="${rtc.recordTask.breathrate == null}">
				/>
				呼吸率：null，
			</c:if>
			</p>
			<!-- 心率 -->
			<p>
				<input type="checkbox" name="index" value="heartrate"
			<c:if test="${rtc.recordTask.heartrate != null}">
				checked="checked"/>
				心率：<fmt:formatNumber type="number" maxFractionDigits="0" value="${rtc.recordTask.heartrate}" />，
			</c:if>
			<c:if test="${rtc.recordTask.heartrate == null}">
				/>
				心率：null，
			</c:if>
			</p>
			<!-- 体温 -->
			<p>
				<input type="checkbox" name="index" value="temp1"
			<c:if test="${rtc.recordTask.temp1 != null}">
				checked="checked"/>
				体温：<fmt:formatNumber type="number" maxFractionDigits="1" value="${rtc.recordTask.temp1}" />，
			</c:if>
			<c:if test="${rtc.recordTask.temp1 == null}">
				/>
				体温：null，
			</c:if>
			</p>
			<!-- 血氧 -->
			<p>
				<input type="checkbox" name="index" value="spo2value"
			<c:if test="${rtc.recordTask.spo2value != null}">
				checked="checked"/>
				血氧：<fmt:formatNumber type="number" maxFractionDigits="0" value="${rtc.recordTask.spo2value}" />，
			</c:if>
			<c:if test="${rtc.recordTask.spo2value == null}">
				/>
				血氧：null，
			</c:if>
			</p>
			<!-- 心电 -->
			<p>
				<input type="checkbox" name="index" value="ecgwave1ids"
			<c:if test="${rtc.recordTask.ecgwave1ids != null}">
				checked="checked"/>
				您测量的心电图，我们会立刻交给医生分析，在得到结果后第一时间回复您。
			</c:if>
			<c:if test="${rtc.recordTask.ecgwave1ids == null}">
				/>
				（心电数据为空，将不显示）
			</c:if>
			</p>
		</form>
		<p>感谢您的支持！</p>
		<p class="col-md-6">
			<button class="btn btn-default btn-block" onclick="hidePopover()">取消</button>
		</p>
		<p class="col-md-6">
			<button class="btn btn-success btn-block" onclick="sendRecordReceivedSms()">确认发送</button>
		</p>
	</c:if>
	<c:if test="${rtc == null}">
		信息获取失败，请刷新重试.
	</c:if>	
	</body>
</html>