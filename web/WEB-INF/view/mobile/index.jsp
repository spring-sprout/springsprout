<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html> 
<html> 
<head> 
	<meta charset="utf-8" /> 
	<title>봄싹-모바일</title> 
	<link rel="stylesheet"  href="/js/mobile/jquery.mobile-1.0a4.1/jquery.mobile-1.0a4.1.css" />
	<link rel="stylesheet" href="/css/mobile/mobile-front.css" />
	<link rel="stylesheet" href="/mobile/ext/css/jqm-docs.css" />
	<script type="text/javascript" src="/module/ext/js/jquery-1.6.1.min.js"></script>
	<script type="text/javascript" src="/js/mobile/jquery.mobile-1.0a4.1/jquery.mobile-1.0a4.1.min.js"></script>
</head>
<body onload="">
<div data-role="page" data-theme="b">
    Site: <a class="redirect_link" href="<c:url value="/index?site_preference=normal"/>">Normal</a> | <a class="redirect_link" href="<c:url value="/m?site_preference=mobile"/>">Mobile</a>

	<div data-role="content">
        <ul data-role="listview" data-inset="true" data-theme="c" data-dividertheme="b">
			<li data-role="list-divider">공지사항</li>
            <c:forEach items="${noticeList}" var="notice">
                <li><a href="/mobile/notice/${notice.id}">${notice.title}</a></li>
            </c:forEach>
		</ul>

        <ul data-role="listview" data-inset="true" data-theme="c" data-dividertheme="b">
			<li data-role="list-divider">모임</li>
            <c:forEach items="${meetingList}" var="meeting">
                <li><a href="/mobile/meeting/${meeting.id}">${meeting.title}</a></li>
            </c:forEach>
		</ul>

	</div>

    <div data-role="footer" data-position="fixed">
		<div data-role="navbar">
			<ul>
				<li><a href="view_subject.html" data-icon="star">낙서장</a></li>
				<li><a href="/mobile/study" data-icon="home">스터디</a></li>
				<li><a href="view_resources.html" data-icon="gear">모임</a></li>
				<li><a href="view_talk.html" data-icon="info">MyPage</a></li>
			</ul>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
    $(".redirect_link").click(function(){
        window.location = $(this).attr("href");
    });
});
</script>
</body> 
</html>