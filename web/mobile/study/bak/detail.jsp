<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>봄싹-모바일</title>
<link rel="stylesheet" href="/mobile/ext/css/jquery.mobile-1.0a3.css" />
<link rel="stylesheet" href="/mobile/ext/css/jqm-docs.css" />
<link rel="stylesheet" href="/mobile/css/mobile-front.css" />
<script type="text/javascript" src="/mobile/ext/js/jquery-1.5.min.js"></script>
<script type="text/javascript" src="/mobile/ext/js/jquery.mobile-1.0a3.min.js"></script>
</head>
<body>
	<div data-role="page" data-theme="b">
		<div data-theme="b" data-role="header" class="ui-bar-b ui-header"
			role="banner">
			<a data-icon="arrow-l" data-rel="back"
				class="ui-btn-left ui-btn ui-btn-up-b ui-btn-icon-left ui-btn-corner-all ui-shadow"
				href="#" data-theme="b"><span
				class="ui-btn-inner ui-btn-corner-all"><span
					class="ui-btn-text">Back</span><span
					class="ui-icon ui-icon-arrow-l ui-icon-shadow"></span> </span> </a>
			<h1 class="ui-title" tabindex="0" role="heading" aria-level="1">봄싹
				스웨거</h1>
			<a
				class="ui-btn-right jqm-home ui-btn ui-btn-icon-notext ui-btn-corner-all ui-shadow ui-btn-up-b"
				data-direction="reverse" data-iconpos="notext" data-icon="home"
				href="/" title="Home" data-theme="b"><span
				class="ui-btn-inner ui-btn-corner-all"><span
					class="ui-btn-text">Home</span><span
					class="ui-icon ui-icon-home ui-icon-shadow"></span> </span> </a>
		</div>
		<div data-role="content">
			<div class="ui-body ui-body-c">
				<div data-role="header" data-position="inline" data-theme="c">
					<a style="display: none;"></a>
					<h1>최근 모임</h1>
					<a href="index.html" data-icon="arrow-r" data-iconpos="right"
						data-theme="d">더보기</a>
				</div>
				<div data-role="collapsible" data-collapsed="true">
					<h3>3회, 특별 초청 게스트가 온다!!</h3>
					<ul data-role="listview" data-inset="true">
						<li>3월 26일 (토)</li>
						<li>토즈 강남2호점</li>
						<li>오전 10시 00분 ~ 오후 2시 00분</li>
						<li>18 / 0 / 18</li>
					</ul>
				</div>
				<div data-role="collapsible" data-collapsed="true">
					<h3>2회, 이번에도 오셔야죠!</h3>
				</div>
				<div data-role="collapsible" data-collapsed="true">
					<h3>1회, 일단 오는거다!</h3>
				</div>
			</div>
		</div>
		<jsp:include page="navigation.tag"></jsp:include>
	</div>
</body>
</html>