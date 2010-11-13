<%@ tag pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="banner_name" required="false"%>
<%@ attribute name="banner_text" required="false"%>

<c:if test="${!empty banner_name}">
	<div id="top_banner">
		<img src="<c:url value="/images/${banner_name}.png"/>" alt="springsprout" />
	</div>
</c:if>
<c:if test="${empty banner_name}">
	<div id="top_banner" style="background:url(<c:url value="/images/default-banner.png"/>) no-repeat;">
	    <div style="color:#FFF;padding:3px 0 0 10px;font-weight: bold;text-transform:uppercase">${banner_text}</div>
	</div>
</c:if>