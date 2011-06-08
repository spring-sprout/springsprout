<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	</div>

	<div data-role="content">
        <ul data-role="listview" data-inset="true" data-theme="c" data-dividertheme="b">
			<li data-role="list-divider">모임</li>
            <c:forEach items="${meetingList}" var="meeting">
                <li><a href="docs/about/intro.html">${meeting.title}</a></li>
            </c:forEach>
		</ul>
	</div>

    <div data-role="footer" data-position="fixed">
		<div data-role="navbar">
			<ul>
				<li><a href="/m" data-icon="grid">홈</a></li>
				<li><a href="view_subject.html" data-icon="star">낙서장</a></li>
				<li><a href="/mobile/study" data-icon="home">스터디</a></li>
				<li><a href="view_resources.html" data-icon="gear">모임</a></li>
				<li><a href="view_talk.html" data-icon="info">MyPage</a></li>
			</ul>
		</div>
	</div>
</div>



        <div id="jqm-home" data-theme="b" data-role="page">

            <div data-role="content" class="ui-content" role="main">
                <form class="ui-listview-filter ui-bar-c" role="search" action="">
                    <input placeholder="Filter results..." data-type="search"
                        class="ui-input-text ui-body-null">
                </form>
                <ul data-dividertheme="b" data-theme="c" data-inset="true"
                    data-role="listview"
                    class="ui-listview ui-listview-inset ui-corner-all ui-shadow"
                    role="listbox">
                    <li data-role="list-divider" role="heading" tabindex="0"
                        class="ui-li ui-li-divider ui-btn ui-bar-b ui-corner-top ui-btn-up-undefined">
                        진행 중</li>

                    <li role="option" tabindex="-1" data-theme="c"
                        class="ui-btn ui-btn-icon-right ui-li ui-btn-up-c">
                        <div class="ui-btn-inner">
                            <img src="http://farm6.static.flickr.com/5258/5479496931_a78184ee00_m.jpg" alt=""/>
                            <a href="#" class="ui-link-inherit">나들이 갑시다</a>
                        </div></li>

                    <li role="option" tabindex="-1" data-theme="c"
                        class="ui-btn ui-btn-icon-right ui-li ui-btn-up-c">
                        <div class="ui-btn-inner">
                            <img src="http://farm5.static.flickr.com/4105/5065837716_fbe8782778_m.jpg" alt=""/>
                            <a href="#" class="ui-link-inherit">봄싹 모바일</a>
                        </div></li>
                    <li role="option" tabindex="-1" data-theme="c"
                        class="ui-btn ui-btn-icon-right ui-li ui-btn-up-c">
                        <div class="ui-btn-inner">
                            <img src="http://farm6.static.flickr.com/5285/5363342954_87b6585cd7_m.jpg" alt=""/>
                            <a href="detail.jsp" class="ui-link-inherit">봄싹 스웨거</a>
                        </div></li>
                    <li role="option" tabindex="-1" data-theme="c"
                        class="ui-btn ui-btn-icon-right ui-li ui-corner-bottom ui-btn-up-c">
                        <div class="ui-btn-inner">
                            <img src="http://www.springsprout.org/images/study/logos/language/java.png" alt=""/>
                            <a href="#" class="ui-link-inherit">스프링을 통해 조금 더 나은 개발자되기</a>
                        </div></li>
                </ul>
            </div>
        </div>

        <div data-role="footer" data-position="fixed">
            <div data-role="navbar">
                <ul>
                    <li><a href="/m" data-icon="grid">홈</a></li>
                    <li><a href="view_subject.html" data-icon="star">낙서장</a></li>
                    <li><a href="/mobile/study" data-icon="home">스터디</a></li>
                    <li><a href="view_resources.html" data-icon="gear">모임</a></li>
                    <li><a href="view_talk.html" data-icon="info">MyPage</a></li>
                </ul>
            </div>
        </div>
    </body>
</html>