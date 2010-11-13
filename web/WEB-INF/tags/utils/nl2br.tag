<%@ tag body-content="scriptless" import="springsprout.common.util.StringUtils" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:doBody var="value" />
<% String value = (String) jspContext.getAttribute("value"); %>
<%= StringUtils.nl2br(value) %>
