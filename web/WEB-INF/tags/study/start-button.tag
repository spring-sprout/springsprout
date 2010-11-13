<%@ tag pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="url" required="true" %>

<a id="btn_start" href="<c:url value="${url}"/>"><img src="<c:url value="/images/study/start.png"/>"/></a>

<script type="text/javascript">
$(document).ready( function(){
	$("#btn_start").click( function(e) {
		return confirm("진행 하시겠습니까?");
	});
});
</script>