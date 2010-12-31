<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="s2c" tagdir="/WEB-INF/tags/s2c"%>
<%@ taglib prefix="s" tagdir="/WEB-INF/tags/study"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<page:defaultpage selected_menu="studies" banner_name="study-banner">
<script type="text/javascript" src="http://apis.daum.net/maps/maps2.js?apikey=27a3f692f446be591ac627f5c6e499eddf255ad4"></script>
<script type="text/javascript" src="<c:url value="/resources/js/springMap/jquery.springsproutDaumMap.js"/>"></script>
<s:defaultpage>
	<s:titlebar>
    	스터디 이름 : <a class="s_waitblock" href="<c:url value="/study/${meeting.study.id}"/>">${meeting.study.studyName}</a>
        <td class="title" align="left">
            > 모임 이름 : 
            <a href="<c:url value="/study/${meeting.study.id}/meeting/${meeting.id}"/>">${meeting.title}</a>
        </td>
        <td align="right" class="buttons">
        	<a href="<c:url value="/study/view/${meeting.study.id}"/>" class="fg-button ui-widget ui-state-default ui-corner-all s_waitblock" style="float:right;">
				뒤로
			</a>
			<c:if test="${meeting.status != 'ENDED' }">
				<c:choose>
					<c:when test="${isAlreadyJoinMember}">
						<a href="<c:url value="/study/${meeting.study.id}/meeting/out/${meeting.id}"/>" class="fg-button ui-widget ui-state-default ui-corner-all confirmRequired" style="float:right; margin-right: 5px;">참석 취소</a>
					</c:when>
					<c:otherwise>
						<a href="<c:url value="/study/${meeting.study.id}/meeting/join/${meeting.id}"/>" class="fg-button ui-widget ui-state-default ui-corner-all confirmRequired" style="float:right; margin-right: 5px;">참석 신청</a>
					</c:otherwise>
				</c:choose>
			</c:if>
			<c:if test="${isManagerOrAdmin && meeting.isOpened}">
			<a href="#meeting-menu" id="flat" class="fg-button fg-button-icon-right ui-widget ui-state-default ui-corner-all" style="float:right; margin-right: 5px;">
				<span class="ui-icon ui-icon-triangle-1-s"></span>관리
			</a>
			<div id="meeting-menu" class="hidden">
				<ul>
                   <li><a class="confirmRequired" href="<c:url value="/study/${meeting.study.id}/meeting/notify/${meeting.id}"/>">모임 알림</a></li>
                   <li><a class="confirmRequired" href="<c:url value="/study/${meeting.study.id}/meeting/update/${meeting.id}"/>">모임 수정</a></li>
                   <li><a class="confirmRequired" href="<c:url value="/study/${meeting.study.id}/meeting/end/${meeting.id}"/>">모임 종료</a></li>
                   <li><a class="confirmRequired" href="<c:url value="/study/${meeting.study.id}/meeting/delete/${meeting.id}"/>">모임 삭제</a></li>
                   <c:if test="${meeting.status.descr == '종료'}">
                       <li><a class="confirmRequired" href="<c:url value="/study/${meeting.study.id}/meeting/open/${meeting.id}"/>">모임 시작</a></li>
                   </c:if>
                   <li><hr/></li>
                   <li><a class="confirmRequired" href="<c:url value="/study/${meeting.study.id}/meeting/${meeting.id}/presentation/add"/>">발표 추가</a></li>
				</ul>
			</div>
			</c:if>
        </td>
	</s:titlebar>
	<s:content cssClass="meeting">
		<s:textrow title="모임장" value="${meeting.owner.name}" />
		<s:datetimerow title="모임시작일시" dateValue="${meeting.openDate}" timeValue="${meeting.openTime}" datePattern="yyyy-MM-dd" timePattern="HH:MM" />
		<s:datetimerow title="모임종료일시" dateValue="${meeting.closeDate}" timeValue="${meeting.closeTime}" />
		<s:textrow title="참석인원" value="${meeting.attendedCount}" valueid="attendedCount"/>
		<s:textrow title="신청인원" value="${meeting.attendanceCount}" />
		<s:textrow title="제한인원" value="${meeting.maximum}" />
		<s:textrow title="상태" value="${meeting.status.descr}" />
        <c:if test="${!empty meeting.location}">
            <li><span class="title">모임장소:</span>${meeting.location} [<a class="_meetingLocation" title="모임장소는 ${meeting.location} 입니다." href="<c:url value="/study/${meeting.study.id}/meeting/${meeting.id}/meetingLocation"/>">지도보기</a>]</li>
         </c:if>
	</s:content>
	<s:descrrow value="${meeting.contents}"/>
	<hr class="clear" />
	<div id="meetingTabs">
		<ul>
			<li><a href="${meeting.id}/viewMembers">참석자 목록( <span id="membersCount">${meeting.attendanceCount}</span> 명)</a></li>
			<li><a href="${meeting.id}/viewPresentations">발표( <span id="presentationCount">${meeting.presentationCount}</span> 섹션)</a></li>
			<li><a href="${meeting.id}/viewResources">자료( <span id="resourcesCount">${meeting.resourcesCount}</span> 개)</a></li>
			<li><a href="${meeting.id}/viewComments">의견( <span id="commentsCount">${meeting.commentsCount}</span> 개)</a></li>
		</ul>
	</div>
</s:defaultpage>
<script type="text/javascript">
$(document).ready( function(){
	$('.confirmRequired').click( function(e) {
		var msg = $(this).text(), actionUrl = $(this).attr('href');
		dialogOpen( msg, actionUrl);
		return false;
	});
    $('._meetingLocation').nyroModal({minWidth:600,minHeight:400,width:600,height:400});
    $('#flat').menu({ 
		content: $('#flat').next().html(), // grab content from this page
		showSpeed: 400 
	});
	
});
    $('#meetingTabs').tabs();
	$('#meetingTabs ul li a').click( function(e){
		$.getJSON('${meeting.id}/updateTabDataCounts', null, function(countInfo) {
			$('#membersCount').text(countInfo.membersCount);
			$('#presentationCount').text(countInfo.presentationCount);
			$('#resourcesCount').text(countInfo.resourcesCount);
			$('#commentsCount').text(countInfo.commentsCount);
		});
	});
</script>
<style type="text/css">

</style>
</page:defaultpage>
