<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="s2c" tagdir="/WEB-INF/tags/s2c"%>
<%@ taglib prefix="s" tagdir="/WEB-INF/tags/study"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<h2>일정</h2>
<s2c:full-column>
    <c:choose>
        <c:when test="${study.calendarId eq null}">
            <h1>구글 캘린더에 등록되지 않았습니다.</h1>
        </c:when>
        <c:otherwise>
            <iframe src="http://www.google.com/calendar/embed?src=${study.calendarId}%40group.calendar.google.com&ctz=Asia/Seoul" width="830" height="600" frameborder="0" scrolling="no"></iframe>
        </c:otherwise>
    </c:choose>
</s2c:full-column>