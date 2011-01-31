<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="s2c" tagdir="/WEB-INF/tags/s2c"%>
<%@ taglib prefix="s" tagdir="/WEB-INF/tags/study"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<page:defaultpage selected_menu="studies" banner_name="study-banner">
<s:defaultpage>
	<s:titlebar>
		스터디 이름 : <a class="s_waitblock" href="<c:url value="/study/${study.id}"/>">${presentation.meeting.study.studyName}</a>
        > 
		모임 이름 : <a class="s_waitblock" href="<c:url value="/study/${study.id}/meeting/${meeting.id}"/>">${presentation.meeting.title}</a>
        <td class="title" align="left">
            > 발표 이름 : 
            <a href="<c:url value="/study/${study.id}/meeting/${meeting.id}/presentation/${presentation.id}"/>">${presentation.title}</a>
        </td>
        <td align="right" class="buttons">
			<a href="<c:url value="/study/${study.id}/meeting/${meeting.id}"/>" class="fg-button ui-widget ui-state-default ui-corner-all s_waitblock" style="float:right;">
				뒤로
			</a>
			<a href="#presentation-menu" class="fg-button fg-button-icon-right ui-widget ui-state-default ui-corner-all" id="flat" style="float:right;">
				<span class="ui-icon ui-icon-triangle-1-s"></span>관리
			</a>
			<div id="presentation-menu" class="hidden">
				<ul>
					<li><a href="<c:url value="/study/${study.id}/meeting/${meeting.id}/presentation/update/${presentation.id}"/>">발표 수정</a></li>
					<li><a href="<c:url value="/study/${study.id}/meeting/${meeting.id}/presentation/delete/${presentation.id}"/>">발표 삭제</a></li>
					<c:if test="${presentation.status.descr == '준비'}">
					    <li><a href="<c:url value="/study/${study.id}/meeting/${meeting.id}/presentation/start/${presentation.id}"/>">발표 시작</a></li>
					</c:if>
					<c:if test="${presentation.status.descr == '시작'}">
					    <li><a href="<c:url value="/study/${study.id}/meeting/${meeting.id}/presentation/end/${presentation.id}"/>">발표 종료</a></li>
					</c:if>
					<c:if test="${presentation.status.descr == '종료'}">
					    <li><a href="<c:url value="/study/${study.id}/meeting/${meeting.id}/presentation/start/${presentation.id}"/>">발표 시작</a></li>
					</c:if>
				</ul>
			</div>
        </td>
	</s:titlebar>
    <div class="logo round">
        <img src="<c:url value="${presentation.presenter.avatar}" />" />
    </div>
	<s:content cssClass="presentation">
        <s:textrow title="주제" value="${presentation.topic}" />
        <s:textrow title="발표자" value="${presentation.presenter.name}" />
		<s:textrow title="발표시간" value="${presentation.hour}" />
		<s:textrow title="조회수" value="${presentation.viewCount}" />
	</s:content>
	<s:descrrow value="${presentation.summary}"/>
	<hr class="clear"/>
    <div id="presentationTabs">
		<ul>
			<li><a href="/study/${study.id}/meeting/${meeting.id}/presentation/${presentation.id}/viewResources">자료 ( <span id="resourcesCount">${presentation.resourceCount}</span> 개)</a></li>
			<li><a href="${presentation.id}/viewComments">의견 ( <span id="commentsCount">${presentation.commentCount }</span> 개)</a></li>
		</ul>
	</div>
</s:defaultpage>
<script type="text/javascript">
$(document).ready( function(){
	$(".confirmRequired").click( function(e) {
		var msg = $(this).text();
		if(msg != null)
			return confirm(msg + " 하시겠습니까?");
	});

    $('#flat').menu({ 
		content: $('#flat').next().html(), // grab content from this page
		showSpeed: 400 
	});
});
	$('#presentationTabs').tabs();
	$('.buttons :eq(1)').css( 'margin-right', '5px');
	$('#presentationTabs ul li a').click( function(e){
		$.getJSON('${presentation.id}/updateTabDataCounts', null, function(countInfo) {
			$('#resourceCount').text(countInfo.resourceCount);
			$('#commentsCount').text(countInfo.commentsCount);
		});
	});
</script>
<style type="text/css">
</style>
</page:defaultpage>
