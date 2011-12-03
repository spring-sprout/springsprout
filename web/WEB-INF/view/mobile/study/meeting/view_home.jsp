<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet"  href="/js/mobile/jquery.mobile-1.0a4.1/jquery.mobile-1.0a4.1.css" />
	<link rel="stylesheet" href="/css/mobile/mobile-front.css" />
	<link rel="stylesheet" href="/mobile/ext/css/jqm-docs.css" />
	<script type="text/javascript" src="/module/ext/js/jquery-1.6.1.min.js"></script>
	<script type="text/javascript" src="/js/mobile/jquery.mobile-1.0a4.1/jquery.mobile-1.0a4.1.min.js"></script>
    <script type="text/javascript" src="/module/ext/js/meeting.js"></script>
</head>
<body>
<div data-role="page" data-theme="b">
    <div data-role="header">
		<h1>${meeting.title}</h1>
         <a href="/m" data-icon="home" data-iconpos="notext" data-direction="reverse" class="ui-btn-right jqm-home">Home</a>
	</div>
	<div data-role="content">
		<h3>
			모임장:${meeting.owner.name}<br/>
			모임시작일시:${meeting.startDateTime}<br/>
			모임종료일시:${meeting.endDateTime}<br/>
			참석인원:${meeting.attendedCount}<br/>
			신청인원:${meeting.attendanceCount}<br/>
			제한인원:${meeting.maximum}
		</h3>
		모임장소:${meeting.location.name}
		<a href="/mobile/map?longitude=${meeting.location.longitude}&latitude=${meeting.location.latitude}" data-role="button" data-inline="true" data-rel="dialog" data-transition="pop" data-icon="grid">지도보기</a>
		<div class="ui-body ui-body-c">
		${meeting.contents}
		</div>
	</div>
    <div data-role="footer" data-position="fixed">
		<div data-role="navbar">
			<ul>
				<li><a href="view_subject.html" data-icon="home" class="ui-btn-active">모임홈</a></li>
				<li><a href="/mobile/study" data-icon="star" >의견</a></li>
				<li><a href="view_resources.html" data-icon="gear">자료</a></li>
				<li><a href="view_talk.html" data-icon="info">발표</a></li>
			</ul>
		</div>
	</div>
</div>
</body>
</html> 