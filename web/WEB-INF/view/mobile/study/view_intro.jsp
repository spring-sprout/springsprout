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
			<div data-role="collapsible" data-collapsed="false">
				<h3>소개</h3>
				<pre>2011년 이전과는 전혀 다른 스타일의 스터디를 구상했고, 이제 그걸 진행해 볼까 합니다.
이전까지의 스터디의 문제점은 이전에 블로깅을 한적이 있으니 잠깐 살펴보시는 것도 좋겠습니다.</pre>
			</div>
		</div>
		<!-- /content -->

		<jsp:include page="navigation.tag"></jsp:include>
	</div>
</body>
</html>