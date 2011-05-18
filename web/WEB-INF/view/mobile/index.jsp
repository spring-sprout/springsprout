<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html> 
<html> 
<head> 
	<meta charset="utf-8" /> 
	<title>봄싹-모바일</title> 
	<link rel="stylesheet"  href="/module/ext/css/jquery.mobile-1.0a3.css" />
	<link rel="stylesheet" href="/module/ext/css/jqm-docs.css" />
	<link rel="stylesheet" href="/css/mobile/mobile-front.css" />
	<script type="text/javascript" src="/module/ext/js/jquery-1.5.min.js"></script>
	<script type="text/javascript" src="/module/ext/js/jquery.mobile-1.0a3.min.js"></script>
	<script type="text/javascript" src="/module/ext/js/meeting.js"></script>
</head>
<body onload="">
<div data-role="page" data-theme="b" >
    Site: <a class="redirect_link" href="<c:url value="/index?site_preference=normal"/>">Normal</a> | <a class="redirect_link" href="<c:url value="/m?site_preference=mobile"/>">Mobile</a>

	<div id="jqm-homeheader">
		<h1 id="jqm-logo"><img src="/images/logo_beta.png" alt="jQuery Mobile Framework" width="235" height="61" /></h1>
		<p>2011년 새해 복 많이 받으시고, 모두 행복하세요.</p> 
		<p id="jqm-version">Alpha Release</p> 
	</div>  

	<div data-role="content"> 
		<div data-role="collapsible" data-collapsed="true">
			<h3>공지사항</h3>
			<ul data-role="listview" data-inset="true" > 
			<li><a href="notice/view.html" data-transition="pop">봄싹 스웨거 스터디 개설</a></li> 
			<li><a href="notice/view.html" data-transition="pop">로그인 문제 해결했습니다.</a></li> 
			<li><a href="notice/view.html" data-transition="pop">2010년 하반기 봄싹 스터디 일정</a></li>  
			<li><a href="http://springsprout.org" data-transition="pop">2010 한국 자바 개발자 페스티벌</a></li>  
			</ul> 
		</div>

		<div class="ui-body ui-body-c"> 
			<div data-role="header" data-position="inline" data-theme="c">
			<a style="display:none;"></a> 
			<h1>스터디</h1>
			<a href="study/list.jsp" data-icon="arrow-r" data-iconpos="right" data-theme="d">더보기</a> 
			</div>
			<fieldset class="ui-grid-a" style="text-align:center;">
				<div class="ui-block-a">
					<div class="ui-bar ui-bar-c" style="height:130px"> 
						<img src="images/swagger.jpg" style="width:110px;height:110px;"/>
						<br />
						<span class="study_name">봄싹 스웨거</span>
					</div>
				</div>
				<div class="ui-block-b">
					<div class="ui-bar ui-bar-c" style="height:130px;white-space:nowrap;text-overflow:ellipsis;-o-text-overflow:ellipsis;overflow:hidden;">
						<img src="images/java.png" style="width:110px;" height="110px" />
						<br />
						<span class="study_name">스프링으로 더 나은 개발자되기 </span>
					</div>
				</div>	   
			</fieldset>

			<div data-role="header" data-position="inline" data-theme="c">
				<a style="display:none;"></a> 
				<h1>최근 모임</h1>
				<a href="meeting/list.html" data-icon="arrow-r" data-iconpos="right" data-theme="d">더보기</a> 
			</div>
			<div id="meetingList">
				<ul style="list-style:none;">
					<li>
						<div class="openDate">
						<span class="date">
		                   	<span class="month">2월</span>
		                   	<span class="day">26</span>
	               		</span>
	               		<span class="dayOfWeek">
	                   		<span class="shortDay">토</span>
	               		</span>
	           			</div>
	           			<div class="descr">
	                      	<h3>2회, 이번에도 오셔야죠!</h3>
	                      	<div class="nowrap">토즈강남점</div>
							<span class="dueTime">오전 10시 00분</span> ~ <span class="dueTime">오후 2시 00분</span>
						</div>
					</li> 
				</ul>
			</div>
			<div data-role="fieldcontain"> 
		         <label for="name-c"><b>낙서장:</b></label> 
		         <input type="text" name="name" id="name-c" value=""  />
			</div>
			<ul data-role="listview" data-theme="d" data-inset="true"> 
				<li>
					<img src="images/seosh81.jpeg" class="ui-li-icon ss-ui-li-icon" />
					<span class="ss-ui-li-icon-text">한줄내용입니다</span>
				</li>
				<li>
					<img src="images/keesun.jpeg" alt="France" class="ui-li-icon ss-ui-li-icon" />
					<span class="ss-ui-li-icon-text">한줄내용입니다.낙서장이니 편하게 글을 쓰세요.</span>
				</li>
			</ul>
		</div><!-- /themed container --> 
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