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
    	<div id="content">
			<div id="profile">
				<div class="title">
					<table width="100%">
						<tr>
							<td><h3>개인 정보</h3></td>
							<td style="float:right"><a href="<c:url value="/member/update/${member.id}"/>">정보수정 </a> |
								<a href="<c:url value="/member/out/${member.id}"/>">탈퇴</a>
							</td>
						</tr>
					</table>
				</div>
				<div>
					<table class="profiletable">
						<tr>
							<td rowspan="10" class="avatar"><img src="${member.avatar}&s=120"/></td>
						</tr>
						<tr>
							<td><b>이메일</b></td>
							<td>${member.email}</td>
						</tr>
						<tr>
							<td><b>이름</b></td>
						 	<td>${member.name}</td>
						 </tr>
						 <tr>
						 	<td><b>가입일</b></td>
						 	<td>${member.joined}</td>
						 </tr>
						 <tr>
						 	<td><b>블로그</b></td>
						 	<td>${member.blog}</td>
						 </tr>
						 <tr>
						 	<td><b>참석률(실제참석횟수/전체모임횟수)</b></td>
						 	<td>${member.totalAttendanceRate}%</td>
						 </tr>
						 <tr>
						 	<td><b>신뢰도(실제참석횟수/전체참석신청횟수)</b></td>
						 	<td>${member.totalTrustRate}%</td>
						 </tr>
						 <tr>
						 	<td><b>이메일 수신 여부</b></td>
						 	<td>${member.isAllowedEmail}</td>
						 </tr>
						 <tr>
						 	<td><b>구글 토크 메시지 수신 여부</b></td>
						 	<td>${member.isAllowedGoogleTalk}</td>
						 </tr>
					</table>
				</div>
			</div>
			<div class="margin20"></div>
			<div class="tap_myPage">			
				<ul> 
					<li class="taptype1_on"><a href="#">스터디정보</a></li>
					<li class="taptype2"><a href="#">위키정보</a></li>
					<li class="taptype3"><a href="#">용어정보</a></li>
					<li class="taptype4"><a href="#">내가 쓴 글</a></li>			
				</ul> 
			</div>
			<div class="margin20"></div>
			<div id="studylist">
				<div class="title"><h3>스터디 정보</h3></div>
				<div class="margin20"></div>
				<div id="studyinfo">
					<c:choose>
					 	<c:when test="${empty member.studies}">
					    	 참석한 스터디가 없습니다.
					 	</c:when>
					 	<c:otherwise>
							<c:forEach var="study" items="${member.studies}">
								<c:set var="studyObject" value="${study}"/>
								<h3><a href="#">${study.studyName}</a></h3>
								<div>
									<div class="list">
									<span class="study_go" onclick="goStudy(${study.id})">스터디 보러가기</span>	
									<ul> 
										<li><span>담당자명</span><strong>${study.manager.name}</strong> <span>시작일</span><strong><fmt:formatDate pattern="yyyy.MM.dd" value="${study.startDay}" /></strong></li> 
										<li><span>진행현황</span><strong>${study.status.descr}</strong> <span>참석률</span><strong>${member.studyAttendanceRates[studyObject]}%</strong></li> 
										<li><span>신뢰도</span><strong>${member.studyTrustRates[studyObject]}%</strong></li> 
									</ul>
								</div>
								</div>
							</c:forEach>
					 	</c:otherwise>
				 	</c:choose>
				</div>
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
            
            function goStudy(id) {
				location.href="../study/view/" + id;	
			}
        </script>
    </body>
</html>