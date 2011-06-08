<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="http://code.jquery.com/mobile/1.0a3/jquery.mobile-1.0a3.min.css" />
<link rel="stylesheet" href="../ext/css/jqm-docs.css" />
<link rel="stylesheet" href="../css/mobile-front.css" />
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.5.min.js"></script>
<script type="text/javascript"
	src="http://code.jquery.com/mobile/1.0a3/jquery.mobile-1.0a3.min.js"></script>
<title>한 줄 메모장</title>
</head>
<body>
	<div data-role="page" id="meeting_talk">
		<div data-role="header" data-theme="b">
			<h1>한 줄 메모장</h1>
			<a href="/index.html" data-icon="home" data-iconpos="notext"
				data-direction="reverse" class="ui-btn-right jqm-home">Home</a>
		</div>
		<!-- /header -->

		<div data-role="content">
			<div data-role="fieldcontain">
				<label for="name-c"> <b>낙서장:</b> </label> <input type="text"
					name="name" id="name-c" value="" />
			</div>
			<ul data-role="listview" data-theme="d" data-inset="true">
				<li><img src="../images/seosh81.jpeg" alt=""
					class="ui-li-icon ss-ui-li-icon" /><span
					class="ss-ui-li-icon-text">한줄내용입니다</span></li>
				<li><img src="../images/keesun.jpeg" alt="France"
					class="ui-li-icon ss-ui-li-icon" /><span
					class="ss-ui-li-icon-text">한줄내용입니다.낙서장이니 편하게 글을 쓰세요.</span>
				</li>
			</ul>
		</div>
		<!-- /content -->

		<jsp:include page="navigation.tag"></jsp:include>
	</div>
</body>
</html>