<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
                            <p class="navbar-text pull-right"><a href="/door">Login</a></p>
                        </sec:authorize>

                    </div><!--/.nav-collapse -->
                </div>
            </div>
        </div>

        <div class="container">

            <!-- Main hero unit for a primary marketing message or call to action -->
            <div class="hero-unit">
                <div class="row show-grid">
                    <a href="http://www.yes24.com/24/goods/6271069" target="_blank"><img class="span3" src="/resources/images/nodejs.png"/></a>
                    <div class="span7 pull-right">
                        <p>나는 노드를 무척 좋아한다. 업무로 노드를 사용하지 않음에도 노드는 내 삶에 많은 영향을 줬고 즐거움을 줬다. 그래서 노드를 만든 라이언 달과 커미터들을 포함해 수많은 노드 개발자한테 항상 고마움을 가지고 있다. ...(중략)... 노드의 인기가 앞으로 얼마나 커질지는 현재 장담할 수 없지만, 그런 분위기도 개발자들이 직접 만들어 가는 것이라 생각한다. 이 책을 통해 노드에 관심 있는 사람들이 좀 더 쉽게 노드를 이해하고 내가 그랬던 것처럼 노드의 매력을 느낄 수 있기를 바랄 뿐이다.</p>
                        <p>- <a href="http://blog.outsider.ne.kr/" target="_blank">변정훈</a> (저자 서문 중에서) </p>
                        <p><a class="btn btn-primary btn-large" href="http://www.yes24.com/24/goods/6271069" target="_blank">구매하기 &raquo;</a></p>
                    </div>
                </div>

            </div>

            <!-- Example row of columns -->
            <div class="row">
                <div class="span4">
                    <h2>Heading</h2>
                    <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
                    <p><a class="btn" href="#">View details &raquo;</a></p>
                </div>
                <div class="span4">
                    <h2>Heading</h2>
                    <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
                    <p><a class="btn" href="#">View details &raquo;</a></p>
                </div>
                <div class="span4">
                    <h2>Heading</h2>
                    <p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Vestibulum id ligula porta felis euismod semper. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.</p>
                    <p><a class="btn" href="#">View details &raquo;</a></p>
                </div>
            </div>

            <!-- Example row of columns -->
            <div class="row">
                <div class="span4">
                    <h2>Heading</h2>
                    <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
                    <p><a class="btn" href="#">View details &raquo;</a></p>
                </div>
                <div class="span4">
                    <h2>Heading</h2>
                    <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
                    <p><a class="btn" href="#">View details &raquo;</a></p>
                </div>
                <div class="span4">
                    <h2>Heading</h2>
                    <p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Vestibulum id ligula porta felis euismod semper. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.</p>
                    <p><a class="btn" href="#">View details &raquo;</a></p>
                </div>
            </div>

            <hr>

            <footer>
                <p>&copy; SpringSprout rocks! 2012</p>
            </footer>

        </div>


        <script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
        <script src="/static/bootstrap/js/bootstrap.min.js"></script>
    </body>
</html>