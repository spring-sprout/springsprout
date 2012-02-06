<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="language" content="en" />
        <title>봄싹 @2012</title>
        <link href="/static/bootstrap/css/bootstrap.css" rel="stylesheet">
        <link href="/static/css/ss.css" rel="stylesheet">
    </head>
    <body>
        <div class="navbar navbar-fixed-top">
            <div class="navbar-inner">
                <div class="container-fluid">
                    <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </a>
                    <a class="brand logo" href="/index/new">
                        <img src="/resources/images/logo-2012.png" alt="springsprout" width="155" height="25">
                    </a>
                    <div class="nav-collapse">
                        <ul class="nav">
                            <li class="active"><a href="/index/new">Home</a></li>
                            <li><a href="/study">Study</a></li>
                            <li><a href="/statistics/index">Statistics</a></li>
                            <li><a href="http://wiki.springsprout.org">Wiki</a></li>
                        </ul>
                        <sec:authorize ifAnyGranted="ROLE_MEMBER">
                            <ul class="nav pull-right">
                                <li id="fat-menu" class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">${currentUser.name}<b class="caret"></b></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="/mypage/index">My Page</a></li>
                                        <sec:authorize ifAnyGranted="ROLE_ADMIN">
                                            <li><a href="/admin/index">Admin</a></li>
                                        </sec:authorize>
                                        <li class="divider"></li>
                                        <li><a href="/j_spring_security_logout">Logout</a></li>
                                    </ul>
                                </li>
                            </ul>
                            <%--<p class="navbar-text pull-right">Logged in as <a href="/mypage/index">${currentUser.name}</a></p>--%>
                        </sec:authorize>
                        <sec:authorize ifNotGranted="ROLE_MEMBER">
                            <p class="navbar-text pull-right"><a href="/door">Login</a></p>
                        </sec:authorize>
                    </div><!--/.nav-collapse -->
                </div>
            </div>
        </div>
        <div class="page-wrap">
            <div class="container">
                <header class="home-header">
                    <h1><span class="kd">var</span> springsprout = <span class="string">'spring'</span> + <span class="string">'sprout'</span>;</h1>
                    <p>Let's challenge, evolute, share and have fun!</p>
                </header>
                <div class="row ">
                    <section class="content-section">
                        <article class="studies">
                            <h4 class="sec-title">Studies</h4>
                        </article>
                        <aside class="banners">
                            <h4 class="sec-title">Banners</h4>
                        </aside>
                    </section>
                    <section class="side-section">
                        <article class="notices">
                            <h4>Notices</h4>
                        </article>
                        <article class="chatting">
                            <h4>Let's talk</h4>
                        </article>
                    </section>
                </div>
            </div>
            <div class="footer-outer">
                <footer class="main-footer">
                    <div class="row linkBox">
                        <div class="span3">
                            <h4>How to enjoy SpringSprout</h4>
                            <ul class="unstyled">
                                <li><a target="_blank" href="http://wiki.springsprout.org/Home">봄싹은?</a></li>
                                <li><a target="_blank" href="http://wiki.springsprout.org/Home">봄싹의 목적은?</a></li>
                                <li><a target="_blank" href="http://wiki.springsprout.org/Home">봄싹 스터디에 참여하려면?</a></li>
                            </ul>
                        </div>
                        <div class="span3">
                            <h4>SpringSprout family sites</h4>
                            <ul class="unstyled">
                                <li><a target="_blank" href="http://twitter.com/springsprout">봄싹 트위터</a></li>
                                <li><a target="_blank" href="http://groups.google.com/group/springsprout">봄싹 구글 그룹스</a></li>
                                <li><a target="_blank" href="http://wiki.springsprout.org">봄싹 위키</a></li>
                                <li><a target="_blank" href="http://springstudyclub.tistory.com/">봄싹 블로그</a></li>
                                <li><a target="_blank" href="http://jira.springsprout.org">봄싹 이슈트래커</a></li>
                                <li><a target="_blank" href="https://github.com/whiteship/springsprout">봄싹 Git(+코드 뷰어)</a></li>
                            </ul>
                        </div>
                        <div class="span3">
                            <h4>SpringSprout Aces</h4>
                            <ul class="unstyled">
                                <li><a target="_blank" href="http://whiteship.me">백기선</a></li>
                                <li><a target="_blank" href="http://blog.outsider.ne.kr">Outsider</a></li>
                            </ul>
                        </div>
                        <div class="span3">
                            <h4>Play with????? 임시...</h4>
                            <ul class="unstyled">
                                <li><a target="_blank" href="http://springsource.org">Spring</a></li>
                                <li><a target="_blank" href="http://www.hibernate.org/">Hibernate</a></li>
                            </ul>
                        </div>
                    </div>
                    <p class="copyright">&copy; SpringSprout rocks! 2012</p>
                </footer>
            </div>
        </div>
        <script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
        <script src="/static/bootstrap/js/bootstrap.min.js"></script>
    </body>
</html>