<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
	<head>
	<script type="text/javascript">
		$(function() {
			var c = "label-danger", d = "label-default";
			$(":radio[name='operatorid']").click(function() {
				// change style
				$(this).parent("label").removeClass(d).addClass(c)
					.siblings().removeClass(c).addClass(d);
				// filte operationlogs
				filterOperationlogs(filterOperationlogsData());
			});
		});
		</script>
	</head>
	<body>
		<label class="radio-inline label label-danger">
			<input type="radio" name="operatorid"
				value="0" checked style="display: none;">
			全部										
		</label>
	<c:if test="${operators != null}">
	   	<c:forEach var="operator" items="${operators}">
	   	<label class="radio-inline label label-default">
			<input type="radio" name="operatorid"
				value="${operator.id}" style="display: none;">
			${operator.name}										
		</label>
	   	</c:forEach>
	</c:if>	
	</body>
</html>