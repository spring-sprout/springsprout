<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, minimum-scale=1, maximum-scale=1">
<title>스터디 목록</title>
<link
	href="http://code.jquery.com/mobile/1.0a3/jquery.mobile-1.0a3.min.css"
	rel="stylesheet">
<link href="docs/_assets/css/jqm-docs.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-1.5.min.js"
	type="text/javascript">
	
</script>
<script
	src="http://code.jquery.com/mobile/1.0a3/jquery.mobile-1.0a3.min.js"
	type="text/javascript">
	
</script>
<script src="experiments/themeswitcher/jquery.mobile.themeswitcher.js"
	type="text/javascript">
	
</script>
<script src="docs/_assets/js/jqm-docs.js" type="text/javascript">
	
</script>
</head>
<body class="ui-mobile-viewport">
	<div id="jqm-home" data-theme="b" data-role="page">
		<div data-theme="b" data-role="header" class="ui-bar-b ui-header"
			role="banner">
			<a data-icon="arrow-l" data-rel="back"
				class="ui-btn-left ui-btn ui-btn-up-b ui-btn-icon-left ui-btn-corner-all ui-shadow"
				href="#" data-theme="b"><span
				class="ui-btn-inner ui-btn-corner-all"><span
					class="ui-btn-text">Back</span><span
					class="ui-icon ui-icon-arrow-l ui-icon-shadow"></span>
			</span>
			</a>
			<h1 class="ui-title" tabindex="0" role="heading" aria-level="1">봄싹
				스터디!</h1>
			<a
				class="ui-btn-right jqm-home ui-btn ui-btn-icon-notext ui-btn-corner-all ui-shadow ui-btn-up-b"
				data-direction="reverse" data-iconpos="notext" data-icon="home"
				href="/" title="Home" data-theme="b"><span
				class="ui-btn-inner ui-btn-corner-all"><span
					class="ui-btn-text">Home</span><span
					class="ui-icon ui-icon-home ui-icon-shadow"></span>
			</span>
			</a>
		</div>
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
	<div class="ui-loader ui-body-a ui-corner-all" style="top: 325.5px;">
		<span class="ui-icon ui-icon-loading spin"></span>
		<h1>loading</h1>
	</div>
</body>
</html>