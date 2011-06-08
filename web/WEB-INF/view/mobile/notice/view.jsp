<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<body> 
<div data-role="page" data-theme="b">
 	<div data-role="header">
		<h1 id="notice_title">${notice.title}</h1>
        <a href="/m" data-icon="home" data-iconpos="notext" data-direction="reverse" class="ui-btn-right jqm-home">Home</a>
	</div>

	<div data-role="content">	
		<p>${notice.contents}</p>
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
</body>
</html> 