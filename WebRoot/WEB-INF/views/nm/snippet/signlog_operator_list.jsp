<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
	<head>
	<script type="text/javascript">
		$(function() {
			$(":radio[name='operatorid']").click(function() {
				// change style
				$(".multiline-label").removeClass(c).addClass(d);
				$(this).parent().removeClass(d).addClass(c);
				// filte devices
				filterSignlogs(filterSignlogsData());
			});
		});
		</script>
	</head>
	<body>
		<p>
			<label class="radio-inline label label-danger multiline-label">
				<input type="radio" name="operatorid"
					value="0" checked style="display: none;">
				全部										
			</label>
		</p>
		<c:if test="${operators != null}">
		   	<c:forEach var="operator" items="${operators}" varStatus="i">
			   	<c:if test="${i.index % 10 == 0}">
			   	<p>
			   	</c:if>
				   	<label class="radio-inline label label-default col-md-1 multiline-label" title="${operator.name}">
						<input type="radio" name="operatorid"
							value="${operator.id}" style="display: none;">
						${operator.name}										
					</label>
				<c:if test="${i.index % 10 == 9 || i.last == true}">
			   	</p>
			   	</c:if>
		   	</c:forEach>
		</c:if>
	</body>
</html>