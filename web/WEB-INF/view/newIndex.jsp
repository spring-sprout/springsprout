<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="description" content="스터디하고 책쓰고 발표하며 성장하는 봄싹 커뮤니티" />
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
                            <li class="active"><a href="/index/new"><i class="icon-home icon-white"></i> 홈</a></li>
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
                    </div><!--/.nav-collapse -->
                </div>
            </div>
        </div>
        <div class="page-wrap">
            <div class="container">
                <header class="home-header">
                    <h1><span class="kd">var</span> springSprout =
                        <a href="#" rel="popover" data-content="추운 겨울을 지나 꽃피는 봄이오면"
                           data-original-title="spring(...);"><span class="string">'spring'</span></a> +
                        <a href="#" rel="popover" data-content="새싹이 돋아나겠죠."
                           data-original-title="sprout(...);"><span class="string">'sprout'</span></a>;
                    </h1>
                    <p>우리는
                        <a href="#" rel="popover" data-content="관심있는 주제의 스터디에 참여도 하시고 멋진 개발자도 많이 만나세요."
                           data-original-title="we.study(...);">학습하고</a>
                        <a href="#" rel="popover" data-content="학습한 기술을 적용해볼 곳이 없다구요? 봄싹에서 여러 개발자와 같이 사용해 보세요."
                           data-original-title="we.develope(...);">개발하고</a>
                        <a href="#" rel="popover" data-content="발표, 번역, 저술 등 여러 방법으로 학습하고 적용해본 경험을 다른 개발자와 나눠보세요."
                           data-original-title="we.share(...);">나누고</a>
                        <a href="#" rel="popover" data-content="이 모든게 즐겁다면 여러분은 이미 봄싹 개발자입니다."
                           data-original-title="we.enjoy(...);">즐깁니다.</a>
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
                            <h4>FAQ</h4>
                            <ul class="unstyled">
                                <li><a target="_blank" href="http://wiki.springsprout.org/Home">봄싹은?</a></li>
                                <li><a target="_blank" href="http://wiki.springsprout.org/Home">봄싹의 목적은?</a></li>
                                <li><a target="_blank" href="http://wiki.springsprout.org/Home">봄싹 스터디에 참여하려면?</a></li>
                            </ul>
                        </div>
                        <div class="span3">
                            <h4>Family Sites</h4>
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
                            <h4>Developers</h4>
                            <ul class="unstyled">
                                <li><a target="_blank" href="https://github.com/helols">김성윤</a></li>
                                <li><a target="_blank" href="https://github.com/miracle0k">김정우</a></li>
                                <li><a target="_blank" href="https://github.com/KimJejun">김제준</a></li>
                                <li><a target="_blank" href="https://github.com/arawn">박용권</a></li>
                                <li><a target="_blank" href="https://github.com/outsideris">변정훈</a></li>
                                <li><a target="_blank" href="https://github.com/keesun">백기선</a></li>
                                <li><a target="_blank" href="https://github.com/seosh81">서승호</a></li>
                                <li><a target="_blank" href="https://github.com/miracle0k">석종일</a></li>
                            </ul>
                        </div>
                        <div class="span3">
                            <h4>Thanks To</h4>
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
        <script src="/static/jquery/jquery.min.js"></script>
        <script src="/static/bootstrap/js/bootstrap.min.js"></script>
        <script>
            $(document).ready(function(){
                $("a[rel=popover]")
                    .popover()
                    .click(function(e) {
                        e.preventDefault()
                    });
            });
        </script>
    </body>
</html>