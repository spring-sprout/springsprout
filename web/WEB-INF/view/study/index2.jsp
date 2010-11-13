<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="s" tagdir="/WEB-INF/tags/study"%>
<style type="text/css">
.dotHr {
	background-color:#FFFFFF;
	border-color:-moz-use-text-color -moz-use-text-color #E0E0E0;
	border-style:none none dotted;
	border-width:medium medium 2px;
	color:#FFFFFF;
	height:1px;
	margin:1.5em 0;
	display: block;
	float: left;
	width: 100%;
}
.vertical-line {
	display: inline;
	background-right-color:#000000;
	border-right-color: #E0E0E0;
	border-right-style:dotted;
	width: 1px;
	height: 200px;
	margin: 30 10 30 10;
}
.horizontal-line {
	background-color: gray;
	border: 0 none;
	height:1px;
	margin: 5px 0;
}

.portletDiv {
	width:30%; float: left;
}

ul {
	list-style-type: none;
}
li { padding-left: 3px;}
h2, h3 { color:#77B250; text-decoration:none; margin: 5px 0;}
</style>
<page:defaultpage selected_menu="studies" banner_name="" ajaxlogin_yn="Y">
	<div id="content">
		<div style="width:100%; float: left;">
			<h1 style="color: #003366;">MOST HOT STUDY!</h1>
			<hr class="dotHr" />
			<br/>
			<h2><a href="<c:url value="/study/view/${study.id}"/>">${study.studyName}</a></h2>
			<div class="logo round">
				<img src="<c:url value="${study.logo != null ? study.logo : '/images/study/logos/default.png'}" />" />
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
		<hr class="dotHr" />
		<div style="padding: 10px;">
		<div class="portletDiv">
			<h3>최근 스터디 목록</h3>
			<hr class="horizontal-line">
			<ul>
				<li>스터디 1</li>
				<li>스터디 2</li>
				<li>스터디 3</li>
				<li>스터디 4</li>
				<li>스터디 5</li>
			</ul>
		</div>
		<div class="vertical-line" style="float: left;"></div>
		<div class="portletDiv">
			<h3>최근 모임 목록</h3>
			<hr class="horizontal-line">
			<ul>
				<li>스터디 1</li>
				<li>스터디 2</li>
				<li>스터디 3</li>
				<li>스터디 4</li>
				<li>스터디 5</li>
			</ul>
		</div>
		<div class="vertical-line" style="float: left;"></div>
		<div class="portletDiv">
			<h3>최근 발표 목록</h3>
			<hr class="horizontal-line">
			<ul>
				<li>스터디 1</li>
				<li>스터디 2</li>
				<li>스터디 3</li>
				<li>스터디 4</li>
				<li>스터디 5</li>
			</ul>
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
});
</script>
</page:defaultpage>