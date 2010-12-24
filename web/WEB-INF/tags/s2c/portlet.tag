<%@ tag pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="target" required="true"%>

<style type="text/css">
.noData { text-align: center; }
</style>
<c:choose>
	<c:when test="${empty target}">
		<div class="noData">데이터가 없습니다.</div>
  	</c:when>

   	<c:otherwise>
		<jsp:doBody/>
   	</c:otherwise>
</c:choose>