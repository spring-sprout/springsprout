<%@ tag pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="cssClass" required="false" %>

<ul class="c ${cssClass}">
	<jsp:doBody/>
</ul>