<%@ tag pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="shortcut icon" href="<c:url value="/images/favicon.ico"/>" type="image/x-icon"/>

    <title>봄싹 @2012</title>

    <!-- Style -->
    <link href="/static/bootstrap/css/bootstrap.css" rel="stylesheet">
    <style type="text/css">
        body {
            padding-top: 60px;
            padding-bottom: 40px;
        }
        .navbar .logo {
            height: 25px;
            padding:7px 20px 8px;
        }
    </style>
    <link href="/static/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">

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
                    <p class="navbar-text pull-right"><a href="/login">Login</a></p>
                </sec:authorize>

            </div><!--/.nav-collapse -->
        </div>
    </div>
</div>

<div class="container">

    <jsp:doBody/>

    <hr>

    <footer>
        <p>&copy; SpringSprout rocks! 2012</p>
    </footer>

</div>


<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
<script src="/static/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>