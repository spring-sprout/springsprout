<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
        <link href="/static/css/ss-member.css" rel="stylesheet">
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
    	<div class="container">
			<div class="row">
                <div class="span2 pull-left">
                    <a class="thumbnail" href="http://ko.gravatar.com/" target="gravatar">
                        <img class="gravatar" alt="your avatar" src="${member.avatar}&s=120"/>
                    </a>
                </div>
                <div class="span10">
                    <table class="table table-bordered table-striped">
                        <tbody>
                        <tr>
                            <th><i class="icon-user"></i> 이름</th>
                            <td>${member.name}</td>
                        </tr>
                        <tr>
                            <th><i class="icon-calendar"></i> 가입일</th>
                            <td>${member.joined}</td>
                        </tr>
                        <tr>
                            <th><i class="icon-chevron-right"></i> 가입상태</th>
                            <td>${member.status.descr}</td>
                        </tr>
                        <tr>
                            <th><i class="icon-tags"></i> 그룹</th>
                            <td>
                            <c:forEach items="${member.roles}" var="role">
                                <span class="label label-success">${role.descr}</span>
                            </c:forEach>
                            </td>
                        </tr>
                        <tr>
                            <th><i class="icon-pencil"></i> 블로그</th>
                            <td>
                            <c:choose>
                                <c:when test="member.blog == null">등록해 주세요!</c:when>
                                <c:otherwise>
                                    <a href="${member.blog}" title="등록된 블로그" target="blog">${member.blog}</a>
                                </c:otherwise>
                            </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <th><a rel="popover" href="#" title="참석률 계산법" data-content="(모임참석 횟수 / 총 모임 횟수) X 100"><i class="icon-flag"></i> 참석률</a></th>
                            <td>${member.totalAttendanceRate == null ? '0' : member.totalAttendanceRate}%</td>
                        </tr>
                        <tr>
                            <th><a rel="popover" href="#" title="신뢰도 계산법" data-content="(모임참석 횟수 / 총 참석신청 횟수) X 100"><i class="icon-ok"></i> 신뢰도</a></th>
                            <td>${member.totalTrustRate == null ? '0' : member.totalTrustRate}%</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>

			<nav class="tabbable">
				<ul class="nav nav-tabs">
					<li class="active"><a href="#study_section" data-toggle="tab">스터디</a></li>
					<li><a href="#meeting_section" data-toggle="tab">모임</a></li>
                    <li><a href="#written_section_section" data-toggle="tab">글</a></li>
				</ul>
				
				<div class="tab-content">
					<section class="tab-pane active" id="study_section">
                        <ul class="thumbnails">
                            <c:choose>
                                <c:when test="${empty member.studies}">
                                    <li class="span3">참석한 스터디가 없습니다.</li>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach var="study" items="${member.studies}">
                                        <c:set var="studyObject" value="${study}"/>
                                        <c:set var="studyAttendanceRate" value="${member.studyAttendanceRates[studyObject] eq null ? 0 : member.studyAttendanceRates[studyObject]}%"/>
                                        <c:set var="studyTrustRate" value="${member.studyTrustRates[studyObject] eq null ? 0 : member.studyTrustRates[studyObject]}%"/>
                                        <li class="span3">
                                            <div class="thumbnail">
                                                <img src="${study.logo}" />
                                                <div class="caption">
                                                    <h5>${study.studyName}</h5>
                                                    <table class="table table-bordered table-striped">
                                                        <tr>
                                                            <th><i class="icon-calendar"></i>  시작일</th>
                                                            <td><fmt:formatDate pattern="yyyy.MM.dd" value="${study.startDay}" /></td>
                                                        </tr>
                                                        <tr>
                                                            <th><i class="icon-road"></i> 진행상황</th>
                                                            <td>${study.status.descr}</td>
                                                        </tr>
                                                        <tr>
                                                            <th><i class="icon-flag"></i> 참석률</th>
                                                            <td>
                                                                <div class="progress progress-info progress-striped active">
                                                                    <div class="bar" style="width: ${studyAttendanceRate};">${studyAttendanceRate}</div>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <th><i class="icon-ok"></i> 신뢰도</th>
                                                            <td>
                                                                <div class="progress progress-success progress-striped active">
                                                                    <div class="bar" style="width: ${studyTrustRate};">${studyTrustRate}</div>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </div>
                                        </li>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                        </ul>
					</section>
					<section class="tab-pane" id="meeting_section">
						<p>준비 중입니다.</p>
					</section>
					<section class="tab-pane" id="written_section">
						<p>준비 중입니다.</p>
					</section>
				</div>
			</nav>
		</div>
		<footer class="footer-outer">
			<p class="copyright">&copy; SpringSprout rocks! 2012</p>
		</footer>
		<script src="/static/jquery/jquery.min.js"></script>
        <script src="/static/bootstrap/js/bootstrap.min.js"></script>
        <script>
            $(document).ready(function(){
                $("a[rel=popover]").popover();
            });
        </script>
    </body>
</html>