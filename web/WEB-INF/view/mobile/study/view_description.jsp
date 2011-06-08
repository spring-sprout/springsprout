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
				<h3>정보</h3>
				<h4>관리자: 백기선</h4>
				<h4>Latest meeting: Fri Dec 31 00:00:00 KST 2010</h4>
				<ul data-role="listview" data-inset="true">
					<li>리더:</li>
					<li>모임 수:</li>
					<li>참석인원: /</li>
					<li>시작일:</li>
					<li>종료일:</li>
				</ul>
			</div>
		</div>
		<!-- /content -->

		<jsp:include page="navigation.tag"></jsp:include>
	</div>
</body>
</html>