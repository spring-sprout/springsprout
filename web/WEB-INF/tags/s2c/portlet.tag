<%@ tag pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="target" required="true"%>

<style type="text/css">
.noData { text-align: center; }
</style>
<c:choose>
	<c:when test="${empty target}">
		<h3 class="noData">No Data Avaliable.</h3>
  	</c:when>

   	<c:otherwise>
		<jsp:doBody/>
   	</c:otherwise>
</c:choose>