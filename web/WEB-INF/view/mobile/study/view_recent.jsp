<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>최근 게시물</title>
<link rel="stylesheet" href="/mobile/ext/css/jquery.mobile-1.0a3.css" />
<link rel="stylesheet" href="/mobile/ext/css/jqm-docs.css" />
<link rel="stylesheet" href="/mobile/css/mobile-front.css" />
<script type="text/javascript" src="/mobile/ext/js/jquery-1.5.min.js"></script>
<script type="text/javascript" src="/mobile/ext/js/jquery.mobile-1.0a3.min.js"></script>
</head>
<body>
	<div data-role="page" id="meeting_talk">
		<div data-role="header" data-theme="b">
			<h1>최근 게시물</h1>
			<a href="/index.html" data-icon="home" data-iconpos="notext"
				data-direction="reverse" class="ui-btn-right jqm-home">Home</a>
		</div>
		<!-- /header -->

		<div data-role="content">
			<div data-role="header" data-position="inline" data-theme="c">
				<a style="display: none;"></a>
				<h1>최근 게시물</h1>
				<a href="index.html" data-icon="arrow-r" data-iconpos="right"
					data-theme="d">더보기</a>
			</div>
			<ul data-role="listview" data-inset="true">
				<li><a href="#" data-transition="pop">2차 스웨거 늦은 간단 후기 1 </a>
				</li>
				<li><a href="#" data-transition="pop">발표 신청 [정규 표현식]</a>
				</li>
				<li><a href="#" data-transition="pop">[NoSQL with MongoDB
						part 2 - index & query ] - 참고자료</a>
				</li>
				<li><a href="#" data-transition="pop">벌써 두번 째 모임이 다음 주
						토요일이네요.</a></li>
				<li><a href="#" data-transition="pop">1차 스웨거 간단 회고2</a>
				</li>
			</ul>
		</div>
		<!-- /content -->

		<jsp:include page="navigation.tag"></jsp:include>
	</div>
</body>
</html>