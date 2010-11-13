<%@ tag pageEncoding="utf-8"%>
<%@ tag body-content="scriptless" import="springsprout.common.util.StringUtils" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="value" required="true" %>
<%@ attribute name="mainCssClass" required="false" %>
<%@ attribute name="subCssClass" required="false" %>

<c:set var="mainCssClass" value="${mainCssClass != null ? mainCssClass : 'mainDescr round collapse'}"/>
<c:set var="subCssClass" value="${subCssClass != null ? subCssClass : 'mainDetails'}"/>


<div class="${mainCssClass}">
    <div class="${subCssClass}"><%= StringUtils.nl2br(value) %></div>
    <jsp:doBody/>
</div>