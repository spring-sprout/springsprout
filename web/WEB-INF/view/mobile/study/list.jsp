<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>스터디 목록</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/mobile/ext/css/jquery.mobile-1.0a3.css" />
    <link rel="stylesheet" href="/mobile/ext/css/jqm-docs.css" />
    <link rel="stylesheet" href="/mobile/css/mobile-front.css" />
    <script type="text/javascript" src="/mobile/ext/js/jquery-1.5.min.js"></script>
    <script type="text/javascript" src="/mobile/ext/js/jquery.mobile-1.0a3.min.js"></script>
    <script src="/mobile/ext/js/jqm-docs.js" type="text/javascript"></script>
</head>
<body>
<div data-role="page" data-theme="b">
 	<div data-role="header">
		<h1>스터디 목록</h1>
         <a href="/m" data-icon="home" data-iconpos="notext" data-direction="reverse" class="ui-btn-right jqm-home">Home</a>
	</div>

	<div data-role="content">
        <ul data-role="listview">
			<li data-role="list-divider">진행중인 스터디</li>
            <c:forEach items="${activeStudyList}" var="study">
                <li>
                    <a href="/mobile/study/${study.id}">
                        <img src="${study.logo}"/>
                        <h3>${study.studyName}</h3>
                        <p>
                            <fmt:formatDate value="${study.startDay}" pattern="yyyy년 M월 d일" /> 부터 ~
							<fmt:formatDate value="${study.endDay}" pattern="yyyy년 M월 d일" /> 까지
                        </p>
                    </a>
                </li>
            </c:forEach>
            <li data-role="list-divider">종료된 스터디</li>
            <c:forEach items="${pastStudyList}" var="study">
                <li>
                    <a href="/mobile/study/${study.id}">
                        <img src="${study.logo}"/>
                        <h3>${study.studyName}</h3>
                        <p>
                            <fmt:formatDate value="${study.startDay}" pattern="yyyy년 M월 d일" /> 부터 ~
							<fmt:formatDate value="${study.endDay}" pattern="yyyy년 M월 d일" /> 까지
                        </p>
                    </a>
                </li>
            </c:forEach>
		</ul>
	</div>

    <div data-role="footer" data-position="fixed">
		<div data-role="navbar">
			<ul>
				<li><a href="view_subject.html" data-icon="star">낙서장</a></li>
				<li><a href="/mobile/study" data-icon="home" class="ui-btn-active">스터디</a></li>
				<li><a href="view_resources.html" data-icon="gear">모임</a></li>
				<li><a href="view_talk.html" data-icon="info">MyPage</a></li>
			</ul>
		</div>
	</div>
</div>
</body>
</html>