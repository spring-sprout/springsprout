<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% request.setAttribute("cacheTime", System.currentTimeMillis());%>
<page:defaultpage selected_menu="admin" analytics_yn="N">
    <div id="content" style="padding: 5px 5px; width: 966px;"></div>

    <!-- Ext JS -->
    <script type="text/javascript" src="<c:url value="/ext/adapter/jquery/ext-jquery-adapter.js" />"></script>
    <script type="text/javascript" src="<c:url value="/ext/ext-all.js" />"></script>
    <script type="text/javascript" src="<c:url value="/ext/ux/ux-all.js" />"></script>

    <!-- 콤포넌트 -->
    <script type="text/javascript" src="<c:url value="/js/admin/member.js"><c:param value="${cacheTime}" name="_c"/></c:url>"></script>
    <script type="text/javascript" src="<c:url value="/js/admin/notice.js"><c:param value="${cacheTime}" name="_c"/></c:url>"></script>
    <script type="text/javascript" src="<c:url value="/js/admin/security.js"><c:param value="${cacheTime}" name="_c"/></c:url>"></script>
    <script type="text/javascript" src="<c:url value="/js/admin/seminar.js"><c:param value="${cacheTime}" name="_c"/></c:url>"></script>
    <script type="text/javascript" src="<c:url value="/js/admin/study.js"><c:param value="${cacheTime}" name="_c"/></c:url>"></script>
    <script type="text/javascript" src="<c:url value="/js/admin/layout.js"><c:param value="${cacheTime}" name="_c"/></c:url>"></script>

</page:defaultpage>