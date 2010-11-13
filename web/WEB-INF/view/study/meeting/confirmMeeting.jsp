<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="s2c" tagdir="/WEB-INF/tags/s2c"%>
<%@ taglib prefix="s" tagdir="/WEB-INF/tags/study"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<page:defaultpage selected_menu="studies" banner_name="study-banner">
<style type="text/css">
.presentations{
    overflow: hidden;
    position: relative;
    float: left;
    width: 40%;
}

</style>
<s:defaultpage>
	<s:content cssClass="meeting">
        <h1>모임 상세</h1>
        <s:textrow title="모임주제" value="${meeting.title}" />
		<s:datetimerow title="모임시작일시" dateValue="${meeting.openDate}" timeValue="${meeting.openTime}" datePattern="yyyy-MM-dd" timePattern="HH:MM" />
		<s:datetimerow title="모임종료일시" dateValue="${meeting.closeDate}" timeValue="${meeting.closeTime}" />
		<s:textrow title="참석인원" value="${meeting.attendedCount}" />
		<s:textrow title="신청인원" value="${meeting.attendanceCount}" />
		<s:textrow title="제한인원" value="${meeting.maximum}" />
		<s:textrow title="모임장소" value="${meeting.location}" />
	</s:content>
	<h1>발표 목록</h1>
	<ul class="presentations">
        <c:forEach items="${meeting.presentations}" var="p" varStatus="status">
            <li>${status.index + 1} | ${p.title} | ${p.presenter.name} | ${p.topic}</li>
        </c:forEach>
	</ul>
	<br/>
	<hr class="clear"/>
	<div class="buttonGroup clear" align="center">
	<form:form id="confirm">
		<input type="submit" name="_eventId_submit" value="완료" class="jButton"/>
		<input type="submit" name="_eventId_cancel" value="취소" class="jButton"/>
	</form:form>
	</div>
</s:defaultpage>
</page:defaultpage>
<script type="text/javascript">
	$('.buttonGroup input').button().focusout( function() { $(this).removeClass('ui-state-focus'); });
</script>
