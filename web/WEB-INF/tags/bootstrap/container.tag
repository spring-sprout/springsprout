<%@ tag pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:useBean id="clientScript" scope="request" class="springsprout.common.usebean.ClientScript"/>
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
    <jsp:doBody/>
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
                        <li><a target="_blank" href="https://github.com/miracle0k">김정우</a></li>
                        <li><a target="_blank" href="https://github.com/KimJejun">김제준</a></li>
                        <li><a target="_blank" href="https://github.com/arawn">박용권</a></li>
                        <li><a target="_blank" href="https://github.com/outsideris">변정훈</a></li>
                        <li><a target="_blank" href="https://github.com/keesun">백기선</a></li>
                        <li><a target="_blank" href="https://github.com/seosh81">서승호</a></li>
                        <li><a target="_blank" href="https://github.com/miracle0k">석종일</a></li>
                        <li><a target="_blank" href="https://github.com/isyoon">김성윤</a></li>
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
    if(document.location.host === 'www.springsprout.org'){
        var _gaq = _gaq || [];
        _gaq.push(['_setAccount', 'UA-10470594-1'],
                ['_setAllowLinker', true],
                ['_setAllowHash', true],
                ['_trackPageview']);

        (function() {
            var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
            ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
            var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
        })();
    }
</script>
<jsp:getProperty name="clientScript" property="endScript"/>
<jsp:getProperty name="clientScript" property="readyScript" />
</body>
</html>