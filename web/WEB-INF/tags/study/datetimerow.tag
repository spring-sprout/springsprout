<%@ tag pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="title" required="true" %>
<%@ attribute name="dateValue" required="true" type="java.util.Date" %>
<%@ attribute name="datePattern" required="false" %>
<%@ attribute name="timeValue" required="true" type="java.util.Date" %>
<%@ attribute name="timePattern" required="false" %>

<c:set var="datePatternNS" value="${pattern != null ? pattern : 'yyyy년 MM월 dd일'}"/>
<c:set var="timePatternNS" value="${pattern != null ? pattern : 'a h:mm'}"/>

<li><span class="title">${title}:</span><fmt:formatDate value="${dateValue}" pattern="${datePatternNS}"/> <fmt:formatDate value="${timeValue}" pattern="${timePatternNS}"/></li>

