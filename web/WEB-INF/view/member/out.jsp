<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
        	#outReason {width: 340px;}
        	.help-inline {font-size: 12px;}
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
	            		<legend>탈퇴</legend>
	            	</fieldset>
	            	<div class="control-group">
	                    <label for="outReason" class="control-label">탈퇴사유</label>
	                    <div class="controls">
	                        <form:input path="outReason" cssClass="input-xlarge" id="outReason"/>
	                		<form:errors path="outReason" cssClass="help-inline" />
		                    <p class="help-block">
					    		<span class="help-inline">회원 탈퇴시 개인 정보는 사라집니다. 탈퇴사유를 입력해 주세요.</span>
		                    </p>
	                    </div>
	                </div>
					<div class="form-actions">
	                    <button type="submit" class="btn btn-primary">저장</button>
	                    <button type="reset" class="btn">취소</button>
	                </div>
	    		</form:form>
	        </div>
	    	</div>
		    <footer class="footer-outer">
				<p class="copyright">&copy; SpringSprout rocks! 2012</p>
			</footer>
	    </div>
	    <script src="/static/js/jquery.min.js"></script>
        <script src="/static/bootstrap/js/bootstrap.min.js"></script>
        <script type="text/javascript">
        	$(document).ready(function() {
        		$("button[type=reset]").click(function(e) {
        			e.preventDefault();
        			window.location = "<c:url value="/mypage/index"/>";
        		});
        	});
        </script>
	</body>
</html>