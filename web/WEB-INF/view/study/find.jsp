<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="s2c" tagdir="/WEB-INF/tags/s2c"%>
<page:defaultpage selected_menu="studies" banner_name="study-banner" ajaxlogin_yn="Y">
<s2c:bottom-column>
	<div align="right" style="margin-top: 10px;">
		<input type="text" name="keyword" class="study-index-search" id="keyword" style="width: 40%" value="${key}">
		<button id="findStudy">찾기!!</button>
	</div>
	<s2c:module name="${fn:length(list)} 개의 검색 결과">
	<c:choose>
		<c:when test="${!empty list}">
		<ul id="list">
			<c:forEach items="${list}" var="study" varStatus="vs">
			<li class="studyItem round ${study.status}" study="${study.id}">
				<span class="studyId" style="display: none;">${study.id}</span>
				<div style="width: 18%; float: left;">
					<div style="float: left;" align="center">
						<img class="action thumbnail" src="<c:url value="${study.logo != null ? study.logo : '/resources/images/study/logos/default.png'}" />" width="96" height="96" alt="${study.studyName}"/>
						<span class="cclArea" style="display: none;"></span>
					</div>
				</div>
				<div class="studyInfo s_waitblock" style="width: 80%; float: right;">
					<div class="description">
						<strong>${study.studyName}</strong>
					</div>
					<div class="count">
						${vs.count}
					</div>
					<div class="detail round" style="width:90%">
						관리자: ${study.manager.name} | 상태: ${study.status.descr} | 모임수: ${study.meetingCount} | 
						현재인원: ${study.memberCount} | 제한인원: ${study.maximumCount} <br/>
						<fmt:formatDate value="${study.startDay}" pattern="yyyy년 M월 d일" /> 부터 ~
						<fmt:formatDate value="${study.endDay}" pattern="yyyy년 M월 d일" /> 까지
					</div>
				</div>
				<br class="clear"/>
			</li>
			<c:if test="${study.meetingCount > 0}">
			<li class="studySubItem round">
				<c:forEach items="${study.meetings}" var="meeting">
					<img src="<c:url value="/images/dot03.gif"/>" style="margin-top: 5px; margin-right: 5px;"/>  
					<a class="s_waitblock" href="<c:url value="/study/${study.id}/meeting/${meeting.id}" />">${meeting.title}</a>
					<br/>
				</c:forEach>
			</li>
			</c:if>
			</c:forEach>
		</ul>
		</c:when>
		<c:otherwise>
			<h3 style="text-align: center;">검색결과가 없습니다.</h3>
		</c:otherwise>
	</c:choose>
	</s2c:module>
</s2c:bottom-column>
<script type="text/javascript">
$(document).ready(function() {
	$('button').button().focusout( function() { $(this).removeClass('ui-state-focus'); });
	$(".studyInfo").click( function() {
		var url = '<c:url value="/study/view/"/>' + $(this).parent().children().first().text();
		$(document).attr("location", url);
	});
	
    $(".confirmRequired").click( function(e) {
		var msg = $(this).text(), actionUrl = $(this).attr('href');
		dialogOpen( msg, actionUrl);
		return false;
	});

	$.each( $('.studyItem').find( '.action.thumbnail'), function( i, el) {
		var src = $(el).attr('src');
		if ( /^http:\/\/farm/.test(src)) {
			$(el).next().flickrGetPhotoInfo('77f7a3eb07331902b582e1db782aeb57', src).show();
		};
	});
	 $("#findStudy").click( function(e) {
	    	var keyword = $("#keyword").val();
	    	if ( keyword.length <= 0) {
	    		alert("검색어를 입력하세요!");
	    		return false;
	    	}
		    $(document).attr("location", "/study/find/" + $("#keyword").val());
	    });
});
</script>
</page:defaultpage>