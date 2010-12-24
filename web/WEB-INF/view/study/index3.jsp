<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="s" tagdir="/WEB-INF/tags/study"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<style type="text/css">
.dotHr {
	background-color: #FFFFFF;
	border-color: -moz-use-text-color -moz-use-text-color #E0E0E0;
	border-style: none none dotted;
	border-width: medium medium 2px;
	color: #FFFFFF;
	height: 1px;
	margin: 1.5em 0;
	display: block;
	float: left;
	width: 100%;
}

.dotHr-small {
	background-color: #FFFFFF;
	border-color: -moz-use-text-color -moz-use-text-color #E0E0E0;
	border-style: none none dotted;
	border-width: medium medium 1px;
	color: #FFFFFF;
	height: 1px;
	margin: 1.5em 0;
	display: block;
	float: left;
	width: 100%;
}

.dotDiv-top {
	border-color: #DDDDDD;
	border-style: dotted;
	border-width: 0 0 1px;
	margin:0 0 0.75em;
	padding:0 0 0.75em;
	vertical-align:top;
}

.vertical-line {
	display: inline;
	background-right-color: #000000;
	border-right-color: #E0E0E0;
	border-right-style: dotted;
	width: 1px;
	height: 200px;
	margin: 30 10 30 10;
}

.horizontal-line {
	background-color: gray;
	border: 0 none;
	height: 1px;
	margin: 5px 0;
}

.portletDiv {
	width: 30%;
	float: left;
}

ul {
	list-style-type: none;
}

li {
	padding-left: 3px;
}

.logoTitle {
	color: #77B250;
	text-decoration: none;
	margin: 5px 0;
}

a:hover {
	color: #2175B1;
	cursor: pointer;
	text-decoration: none;
}

.D_photoGroup {
	color: #FFFFFF;
	float: left;
	margin-bottom: 1px;
	margin-right: 1px;
	position: relative;
	height: 200;
}

.D_photoGroup .D_photoGroup_bkg {
	background-color: #111111;
	bottom: 5px;
	height: 85px;
	left: 5px;
	opacity: 0.65;
	position: absolute;
	width: 96%;
	z-index: 1;
}

.D_photoGroup span {
	display: block;
}

.D_photoGroup .D_photoGroup_caption .D_photoGroup_caption_body {
	padding: 2px 5px;
}

.D_photoGroup span {
	display: block;
}

.D_photoGroup .D_photoGroup_caption {
	bottom: 5px;
	height: 85px;
	left: 5px;
	overflow: hidden;
	position: absolute;
	width: 96%;
	z-index: 2;
}

.D_photoGroup .D_photoGroup_caption .D_photoGroup_name,.D_photoGroup .D_photoGroup_caption .D_photoGroup_name a
{
	color: #EEEEEE;
	font-weight: bold;
}

.D_photoGroup .D_photoGroup_caption .D_photoGroup_location {
	color: #FFFFFF;
	font-size: 0.9em;
	text-decoration: none !important;
}

.D_photoGroup .D_photoGroup_caption .D_photoGroup_lastMeetup {
	color: #CCCCCC;
	font-size: 0.8em;
	margin-top: 0.15em;
	text-decoration: none !important;
}
a.D_photoGroup_link:hover {
	text-decoration: none;
}
span.D_photoGroup_name:hover {
	text-decoration: underline;
	font-size: 1.1em;
}
.D_photoGroup a.D_photoGroup_link {
	color: #FFFFFF;
	display: block;
	height: 200px;
	width: 248px;
}

ul.main.study {
	width: 60%;
}

.main.mainDescr {
	border: 2px solid #B2CE67;
	float: left;
	margin-bottom: 10px;
	padding: 10px 30px;
	width: 80%;
}

.studyList .studyDescr, .studySearch, .studyDetails {
	padding: 5px; margin: 3px 10px 3px 10px; border-bottom: 1px solid #CCCCCC;
}

.studyBorder {
	background:none repeat scroll 0 0 #FFFFFF;
	border:2px solid #D6D6D6;
	display:inline;
	float:left;
	margin:0 10px 15px 0;
	padding: 10px;
	position:relative;
}
#content { height: auto !important;}
.study-index-search { 
	width: 70%;
	border: 2px solid green;
    height: 30px;
}
.buttons a { float: right; padding: 3 3;margin : 0 20 10 0;}
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
				<h2>봄싹 스터디!</h2>
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
						<s:textrow title="모임장" value="${recentMeeting.owner.name}" />
						<s:datetimerow title="모임시작일시" dateValue="${recentMeeting.openDate}" timeValue="${recentMeeting.openTime}" datePattern="yyyy-MM-dd" timePattern="HH:MM" />
						<s:datetimerow title="모임종료일시" dateValue="${recentMeeting.closeDate}" timeValue="${recentMeeting.closeTime}" />
						<s:textrow title="참석인원" value="${recentMeeting.attendedCount}" valueid="attendedCount"/>
						<s:textrow title="신청인원" value="${recentMeeting.attendanceCount}" />
						<s:textrow title="제한인원" value="${recentMeeting.maximum}" />
						<s:textrow title="상태" value="${recentMeeting.status.descr}" />
						<c:if test="${!empty recentMeeting.location}">
            				<li><span class="title">모임장소:</span>${recentMeeting.location} [<a class="_meetingLocation" title="모임장소는 ${recentMeeting.location} 입니다." href="<c:url value="/study/${recentMeeting.study.id}/meeting/${recentMeeting.id}/meetingLocation"/>">지도보기</a>]</li>
         				</c:if>
					</s:content>
					<s:descrrow value="${recentMeeting.contents}" mainCssClass="main mainDescr round"/>
				</div>
			</div>
			<div style="margin-bottom: 5px;">
				<c:choose>
				<c:when test="${!empty list}">
					<c:forEach items="${list}" var="study" varStatus="vs">
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
		<div class="studyBorder ui-corner-all studySearch" style="width:33%; float: left;">
			<h1>스터디 찾기</h1>
			<hr class="horizontal-line">
			<div align="center">
				<input type="text" name="keyword" class="study-index-search">
				<button>find!</button>
			</div>
		</div>
		<div class="studyBorder ui-corner-all studyDetails" style="width:33%; float: left;">
			<h1>스터디 종합</h1>
			<hr class="horizontal-line">
			<div class="dotDiv-top">
				<h2 class="logoTitle">${fn:length(activeStudies) } 진행중인 스터디</h2>
				<c:forEach items="${activeStudies}" var="study" varStatus="status" begin="0" end="0">
					<span><a href="/study/${study.id }">${study.studyName }</a></span><br/>
				</c:forEach>
				${fn:length(activeStudies) - displayStudyCount } more studies..<br/>
			</div>
			<div class="dotDiv-top">
				<h2 class="logoTitle">${studyIndexInfo.meetingCount} 지금까지 모임 정보</h2>
				블라 블라<br/>
				블라 블라<br/>
				블라 블라<br/>
				${studyIndexInfo.meetingMoreCount} more meetings..<br/>
			</div>
			<div class="dotDiv-top">
				<h2 class="logoTitle">${studyIndexInfo.presentationCount} 지금까지 발표 정보</h2>
				블라 블라<br/>
				블라 블라<br/>
				블라 블라<br/>
				${studyIndexInfo.presentationMoreCount} more presentations..<br/>
			</div>
			<div>
				<h2 class="logoTitle">${studyIndexInfo.closedStudyCount} 종료된 스터디 </h2>
				<c:forEach items="${closedStudies}" var="study" varStatus="vs" begin="0" end="3">
					<span>${study.studyName }</span><br/>
					<c:set var="displayStudyCount" value="${vs.count }"></c:set>
				</c:forEach>
				${studyIndexInfo.closedStudyMoreCount} more closed studies..<br/>
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
    $('button, #btnRegist').button().focusout( function() { $(this).removeClass('ui-state-focus'); });
});
</script>
</page:defaultpage>