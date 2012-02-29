<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="description" content="스터디하고 책쓰고 발표하며 성장하는 봄싹 커뮤니티" />
        <meta name="language" content="en" />
        <title>봄싹 @2012</title>
        <link rel="shortcut icon" href="<c:url value="/images/favicon.ico"/>" type="image/x-icon"/>
        <link href="/static/bootstrap/css/bootstrap.css" rel="stylesheet">
        <link href="/static/css/ss.css" rel="stylesheet">
        <style type="text/css">
        	.page-wrap {background-color: #FFFFFF; margin-top: -18px; min-width: 960px;}
        </style>
    </head>
    <body>
    	<nav class="navbar navbar-fixed-top">
            <section class="navbar-inner">
                <section class="container-fluid">
                    <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </a>
                    <a class="brand logo" href="/index/new">
                        <img src="/resources/images/logo-2012.png" alt="springsprout" width="155" height="25">
                    </a>
                    <section class="nav-collapse">
                        <ul class="nav">
                            <li><a href="/index/new"><i class="icon-home icon-white"></i> 홈</a></li>
                            <li><a href="/study"><i class="icon-star icon-white"></i> 스터디</a></li>
                            <li><a href="/chat"><i class="icon-comment icon-white"></i> 채팅</a></li>
                            <li><a href="/statistics/index"><i class="icon-signal icon-white"></i> 통계</a></li>
                            <li><a href="http://wiki.springsprout.org"><i class="icon-book icon-white"></i> 위키</a></li>
                        </ul>
                        <sec:authorize ifAnyGranted="ROLE_MEMBER">
                            <ul class="nav pull-right">
                                <li id="fat-menu" class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                        <sec:authentication property="principal.username"/><b class="caret"></b>
                                    </a>
                                    <ul class="dropdown-menu">
                                        <li><a href="/mypage/index"><i class="icon-user"></i> My Page</a></li>
                                        <sec:authorize ifAnyGranted="ROLE_ADMIN">
                                            <li><a href="/admin/index"><i class="icon-cog"></i> Admin</a></li>
                                        </sec:authorize>
                                        <li class="divider"></li>
                                        <li><a href="/j_spring_security_logout"><i class="icon-off"></i> Logout</a></li>
                                    </ul>
                                </li>
                            </ul>
                            <%--<p class="navbar-text pull-right">Logged in as <a href="/mypage/index">${currentUser.name}</a></p>--%>
                        </sec:authorize>
                        <sec:authorize ifNotGranted="ROLE_MEMBER">
                            <p class="navbar-text pull-right"><a href="/door">Login</a></p>
                        </sec:authorize>
                    </section><!--/.nav-collapse -->
                </section>
            </section>
        </nav>
	    <div class="page-wrap">
	    	<div class="container">
	            <form:form commandName="member" method="post" cssClass="form-horizontal">
	            	<fieldset>
	            		<legend>개인 정보 수정</legend>
	            	</fieldset>
	            	<div class="control-group">
	                    <label for="currentPassword" class="control-label">현재 비밀번호</label>
	                    <div class="controls">
	                        <form:password path="currentPassword" cssClass="input-xlarge" id="currentPassword"/>
	                		<form:errors path="currentPassword" cssClass="help-inline" />
	                    </div>
	                </div>
	            	<div class="control-group">
	                    <label for="newPassword" class="control-label">새로운 비밀번호</label>
	                    <div class="controls">
	                        <form:password path="newPassword" cssClass="input-xlarge" id="newPassword"/>
	                		<form:errors path="newPassword" cssClass="help-inline"/>
	                    </div>
	                </div>
	            	<div class="control-group">
	                    <label for="newPasswordConfirm" class="control-label">새로운 비밀번호 확인</label>
	                    <div class="controls">
	                        <form:password path="newPasswordConfirm" cssClass="input-xlarge" id="newPasswordConfirm"/>
	                		<form:errors path="newPasswordConfirm" cssClass="help-inline"/>
	                    </div>
	                </div>
	            	<div class="control-group">
	                    <label for="name" class="control-label">이름</label>
	                    <div class="controls">
	                        <form:input path="name" cssClass="input-xlarge" id="name"/>
	                		<form:errors path="name" cssClass="help-inline"/>
	                    </div>
	                </div>
	            	<div class="control-group">
	                    <label for="blog" class="control-label">블로그</label>
	                    <div class="controls">
	                        <form:input path="blog" cssClass="input-xlarge" id="blog"/>
	                		<form:errors path="blog" cssClass="help-inline"/>
	                    </div>
	                </div>
	                <div class="control-group">
	                    <label for="notification" class="control-label">Notification Service</label>
	                    <div class="controls">
	                        <label class="checkbox">
	                            <form:checkbox title="이메일 수신" path="isAllowedEmail"  value="true" />
	                            이메일 수신
	                        </label>
	                        <label class="checkbox">
	                            <form:checkbox title="구글토크 메시지 수신" path="isAllowedGoogleTalk" value="true"/>
	                            구글토크 메시지 수신
	                        </label>
	                        <p class="help-block">
	                        	<strong>Note:</strong> 사진은 <a href="http://en.gravatar.com/" target="_blank">Gravatar</a>에서 수정할 수 있습니다.
	                        </p>
	                    </div>
	                </div>
	                <div class="form-actions">
	                    <button type="submit" class="btn btn-large btn-primary">저장</button>
	                    <a href="<c:url value="/mypage/index"/>" class="btn btn-large"/>취소</a>
	                </div>
	            </form:form>
	        </div>
	    </div>
	    <script src="/static/js/jquery.min.js"></script>
        <script src="/static/bootstrap/js/bootstrap.min.js"></script>
        <script type="text/javascript">
        	$(document).ready(function() {
        		$(".control-group").has("span").addClass("warning");
        	});
        </script>
	</body>
</html>