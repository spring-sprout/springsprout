<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="s" tagdir="/WEB-INF/tags/study"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<style type="text/css">

</style>

<page:defaultpage selected_menu="studies" banner_name="" ajaxlogin_yn="Y">
	<div id="content">
		<sec:authorize ifAnyGranted="ROLE_MANAGER, ROLE_ADMIN">
		<div class="buttons">
            <a id="btnRegist" class="confirmRequired" href="<c:url value="/study/form"/>">새 스터디 등록</a>
		</div>
		</sec:authorize>
		<div class="studyBorder ui-corner-all studyList" style="width:60%; float: left; padding: 5px;">
			<div class="studyDescr">
				<h1>봄싹 스터디!</h1>
				<h3 class="logoTitle">
					• 도전하자 • 학습하자 • 나누자 • 즐기자
				</h3>	
			</div>
			<div class="studyDescr" style="height: 300px;">
				<h1>다음 모임</h1>
				<div style="padding: 2px;" >
					<div class="studyBorder ui-corner-all">
						<img src="<c:url value="${study.logo != null ? study.logo : '/images/study/logos/default.png'}" />" width="96" height="96"/>
					</div>
					<s:content cssClass="main study" >
						<h3>
							<a href="/study/${recentMeeting.study.id}/meeting/${recentMeeting.id}">${recentMeeting.title}</a>
						</h3>
						<s:textrow title="모임장" value="${recentMeeting.owner.name}" />
						<s:datetimerow title="모임시작일시" dateValue="${recentMeeting.openDate}" timeValue="${recentMeeting.openTime}" datePattern="yyyy-MM-dd" timePattern="HH:MM" />
						<s:datetimerow title="모임종료일시" dateValue="${recentMeeting.closeDate}" timeValue="${recentMeeting.closeTime}" />
						<%--<s:textrow title="참석인원" value="${recentMeeting.attendedCount}" valueid="attendedCount"/>--%>
						<%--<s:textrow title="신청인원" value="${recentMeeting.attendanceCount}" />--%>
						<%--<s:textrow title="제한인원" value="${recentMeeting.maximum}" />--%>
						<%--<s:textrow title="상태" value="${recentMeeting.status.descr}" />--%>
						<c:if test="${!empty recentMeeting.location}">
            				<li><span class="title">모임장소:</span>${recentMeeting.location}</li>
         				</c:if>
					</s:content>
					<s:descrrow id="meetingContents" value="${recentMeeting.contents}" mainCssClass="main mainDescr round"/>
				</div>
			</div>
			<div style="margin-bottom: 5px;">
				<c:choose>
				<c:when test="${!empty activeStudies}">
					<c:forEach items="${activeStudies}" var="study" varStatus="vs">
					<div class="D_photoGroup" style="margin-left: 1.5em; background: url('<c:url value="${study.logo != null ? study.logo : '/resources/images/study/logos/default.png'}" />') no-repeat scroll center center #DCDCDC; width:45%; float: left;" >
						<a class="D_photoGroup_link" href="/study/${study.id }">
							<span class="D_photoGroup_bkg"></span>
							<span class="D_photoGroup_caption">
							<span class="D_photoGroup_caption_body">
								<span class="D_photoGroup_name">
								${study.studyName}
								</span>
								<span class="D_photoGroup_location">
								관리자 : ${study.manager.name}
								</span>
								<span class="D_photoGroup_lastMeetup">Latest meeting: ${study.endDay}</span>
							</span>
							</span>
						</a>
					</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					진행중인 스터디가 없습니다.
				</c:otherwise>
				</c:choose>
			</div>
		</div>
		<!--
        <div class="studyBorder ui-corner-all studySearch" style="width:33%; float: left;">
			<h1>스터디 찾기</h1>
			<hr class="horizontal-line">
			<div align="center">
				<input type="text" name="keyword" class="study-index-search" id="keyword">
				<button id="findStudy">찾기!!</button>
			</div>
		</div>
		-->
		<div class="studyBorder ui-corner-all studyDetails" style="width:33%; float: left;">
			<h1>스터디 현황</h1>
			<hr class="horizontal-line">
			<div class="dotDiv-top">
				<h2 class="logoTitle"><span class="num_cnt">${fn:length(activeStudies) }</span>개 스터디 진행중</h2>
				<c:set var="displayStudyCount" value="0"></c:set>
				<c:forEach items="${activeStudies}" var="study" varStatus="status" begin="0" end="3">
					<span><a class="totalTitle" href="/study/${study.id }">${study.studyName }</a></span><br/>
					<c:set var="displayStudyCount" value="${status.count }"></c:set>
				</c:forEach>
				${fn:length(activeStudies) - displayStudyCount } more studies..<br/>
			</div>
			<div class="dotDiv-top">
				<h2 class="logoTitle"><span class="num_cnt">${fn:length(studyIndexInfo.meetings)}</span>번 모임</h2>
				<c:set var="displayStudyCount" value="0"></c:set>
				<c:forEach items="${studyIndexInfo.meetings}" var="meeting" varStatus="status" begin="0" end="3">
					<span><a class="totalTitle" href="/study/${meeting.study.id}/meeting/${meeting.id}">${meeting.title }</a></span><br/>
					<c:set var="displayStudyCount" value="${status.count}"></c:set>
				</c:forEach>
				${fn:length(studyIndexInfo.meetings) - displayStudyCount } more meetings..<br/>
			</div>
			<div class="dotDiv-top">
				<h2 class="logoTitle"><span class="num_cnt">${fn:length(studyIndexInfo.presentations)}</span>번 발표</h2>
				<c:set var="displayStudyCount" value="0"></c:set>
				<c:forEach items="${studyIndexInfo.presentations}" var="presentation" varStatus="status" begin="0" end="3">
					<span><a class="totalTitle" href="/study/${presentation.meeting.study.id}">${presentation.title }</a></span><br/>
					<c:set var="displayStudyCount" value="${status.count}"></c:set>
				</c:forEach>
				${fn:length(studyIndexInfo.presentations) - displayStudyCount } more presentations..<br/>
			</div>
			<div>
				<h2 class="logoTitle"><span class="num_cnt">${fn:length(studyIndexInfo.pastStudies)}</span>개 스터디 종료</h2>
				<c:set var="displayStudyCount" value="0"></c:set>
				<c:forEach items="${closedStudies}" var="study" varStatus="vs" begin="0" end="3">
					<span>${study.studyName }</span><br/>
					<c:set var="displayStudyCount" value="${vs.count }"></c:set>
				</c:forEach>
				${fn:length(studyIndexInfo.pastStudies) - displayStudyCount } more closed studies..<br/>
			</div>
		</div>
	</div>
	
<script type="text/javascript">
$(document).ready(function() {
	$(".studyItem").click( function() {
		var url = '<c:url value="/study/view/"/>' + $(this).attr("study") + "";
		$(document).attr("location", url);
	});
    $(".confirmRequired").click( function(e) {
		var msg = $(this).text(), actionUrl = $(this).attr('href');
		dialogOpen( msg, actionUrl);
		return false;
	});
    $("#findStudy").click( function(e) {
    	var keyword = $("#keyword").val();
    	if ( keyword.length <= 0) {
    		alert("검색어를 입력하세요!");
    		return false;
    	}
	    $(document).attr("location", "find/" + $("#keyword").val());
    });
    $('button, #btnRegist').button().focusout( function() { $(this).removeClass('ui-state-focus'); });
    SPROUT.common.util.cutStringUsingDot($('#meetingContents'), 100);
    SPROUT.common.util.cutStringUsingDot($('.totalTitle'), 20);
});
</script>
</page:defaultpage>