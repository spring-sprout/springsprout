<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="s2c" tagdir="/WEB-INF/tags/s2c"%>
<%@ taglib prefix="s" tagdir="/WEB-INF/tags/study"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<page:defaultpage selected_menu="studies" banner_name="study-banner" ajaxlogin_yn="Y">
	<s:defaultpage>
		<s:titlebar>
            <td class="title" align="left">
				스터디 이름  : <a href="<c:url value="/study/view/${study.id}"/>">${study.studyName}</a>
            </td>
            <td align="right" class="buttons">
            	<a href="<c:url value="/study/index"/>" title="스터디 목록으로 이동" class="fg-button ui-widget ui-state-default ui-corner-all s_waitblock" style="float:right;">
					뒤로
				</a>
				<c:if test="${(study.isStarted || study.isOpened) && (!isAlreadyJoinMember)}">
					<a href="<c:url value="/study/join/${study.id}"/>" title="스터디 참여" class="fg-button ui-widget ui-state-default ui-corner-all confirmRequired" style="float:right; margin-right: 5px;"">참여</a>
				</c:if>
                <c:if test="${(study.isStarted || study.isOpened) && (isAlreadyJoinMember)}">
                	<a href="<c:url value="/study/out/${study.id}"/>" title="스터디 탈퇴" class="fg-button ui-widget ui-state-default ui-corner-all confirmRequired" style="float:right; margin-right: 5px;"">탈퇴</a>
				</c:if>
				<c:if test="${isManagerOrAdmin}">
					<a href="#meeting-menu" id="flat" class="fg-button fg-button-icon-right ui-widget ui-state-default ui-corner-all" style="float:right; margin-right: 5px;">
						<span class="ui-icon ui-icon-triangle-1-s"></span>관리
					</a>
					<div id="meeting-menu" class="hidden">
					<ul>
						<li><a class="confirmRequired" id="studyNotify" href="<c:url value="/study/notify/${study.id}"/>">스터디 알림</a></li>
                        <li><a class="confirmRequired" href="<c:url value="/study/update/${study.id}"/>">스터디 수정</a></li>
                        <li><a class="confirmRequired" href="<c:url value="/study/end/${study.id}"/>">스터디 종료</a></li>
                        <li><a class="confirmRequired" href="<c:url value="/study/delete/${study.id}"/>">스터디 삭제</a></li>
                        <c:if test="${study.isEnded}">
                            <li><a class="confirmRequired" href="<c:url value="/study/start/${study.id}"/>">스터디 시작</a></li>
                        </c:if>
                        <li><hr/></li>
                        <li><a class="confirmRequired" href="<c:url value="/study/meeting?studyId=${study.id}"/>">모임 추가</a></li>
					</ul>
				</div>
				</c:if>
            </td>
        </s:titlebar>
        <div class="collapsible_line"></div>
        <div id="collapsible_target">
			<div class="logo round" align="center">
				<img id="logo" src="<c:url value="${study.logo != null ? study.logo : '/images/study/logos/default.png'}" />" style="width: 128px; height: 128px"/>
				<br/>
				<span id="cclArea" class="cclArea" style="display: none;"></span>
			</div>
			<s:content cssClass="study">
				<s:textrow title="관리자" value="${study.manager.name}" />
				<s:textrow title="모임수" value="${study.meetingCount}" />
				<s:textrow title="현재인원" value="${study.memberCount}" />
				<s:textrow title="제한인원" value="${study.maximumCount}" />
				<s:daterow title="시작일" value="${study.startDay}" />
				<s:daterow title="종료일" value="${study.endDay}" />
				<s:textrow title="현재상태" value="${study.status.descr}" />
			</s:content>
			<s:descrrow value="${study.descr}"/>
		</div>
		<div id="collapsible_bottom_line" class="collapsible_line" style="clear: both;"></div>
		<div id="studyTabs" style="background: white;">
			<ul>
				<li><a href="${study.id}/meetings">모임 목록</a></li>
				<li><a href="${study.id}/meetingMembers">참석자 목록( ${memberCount}명)</a></li>
				<li><a href="${study.id}/comments">의견</a></li>
				<li><a href="${study.id}/board/view">게시판</a></li>
				<li><a href="#studyTabs-5">캘린더</a></li>
			</ul>
			<div id="studyTabs-5">
				<c:choose>
					<c:when test="${study.calendarId eq null}">
						<h1>구글 캘린더에 등록되지 않았습니다.</h1>
					</c:when>
					<c:otherwise>
						<iframe src="http://www.google.com/calendar/embed?src=${study.calendarId}%40group.calendar.google.com&ctz=Asia/Seoul" style="border: 2px solid #4183C4" width="895" height="600" frameborder="0" scrolling="no"></iframe>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</s:defaultpage>
<script type="text/javascript">
$( function(){
	$('#flat').menu({ 
		content: $('#flat').next().html(),
		showSpeed: 400 
	});
	$(".confirmRequired").click( function(e) {
		var msg = $(this).text(), actionUrl = $(this).attr('href');
		dialogOpen( msg, actionUrl);
		return false;
	});

	$('#studyTabs').tabs();
	var src = $('#logo').attr('src');
	if ( /^http:\/\/farm/.test(src)) {
		$('#cclArea').flickrGetPhotoInfo('77f7a3eb07331902b582e1db782aeb57', src).show();
	};

	$('.collapsible_line').click( function(e) {
		$('#collapsible_target').toggle();
		$('#collapsible_bottom_line').toggle();
	});
});
</script>
<style type="text/css">
div.collapsible_line {
	border: 2px solid #E5ECF9;
	cursor: pointer;
	margin-top: 10px;
	margin-bottom: 10px;
}
div.collapsible_line:HOVER {
	border: 4px solid #E5ECF9;	
}
</style>
</page:defaultpage>
