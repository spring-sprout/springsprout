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
                                        <li><a href="/mypage/index/new"><i class="icon-user"></i> My Page</a></li>
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
        
    	<section class="container">
			<section id="profile">
				<header class="row">
					<h2 class="span2">개인 정보</h2>
					<section class="span2 offset8">
						<a href="<c:url value="/member/update/${member.id}"/>" class="btn btn-info">정보수정 </a>
						<a href="<c:url value="/member/out/${member.id}"/>" class="btn btn-warning">탈퇴</a>
					</section>
				</header>
				<article>
					<ul class="thumbnails pull-left">
						<li class="span2">
							<a class="thumbnail" href="http://ko.gravatar.com/" target="gravatar">
								<img alt="your avatar" src="${member.avatar}&s=120"/>
							</a>
						</li>
					</ul>
					<section>
						<dl class="row">
							<dt class="span2"><i class="icon-envelope"></i> 이메일</dt>
							<dd class="span8">${member.email}</dd>
							<dt class="span2"><i class="icon-user"></i> 이름</dt>
							<dd class="span8">${member.name}</dd>
							<dt class="span2"><i class="icon-calendar"></i> 가입일</dt>
							<dd class="span8">${member.joined}</dd>
							<dt class="span2"><i class="icon-pencil"></i> 블로그</dt>
							<dd class="span8">${member.blog == null ? '등록해 주세요!' : member.blog}</dd>
							<dt class="span2"><a rel="popover" href="#" data-content="실제참석횟수 / 전체모임횟수" data-title="참석률?"><i class="icon-flag"></i> 참석률</a></dt>
							<dd class="span8">${member.totalAttendanceRate == null ? '0' : member.totalAttendanceRate}%</dd>
							<dt class="span2"><a rel="popover" href="#" data-content="실제참석횟수 / 전체참석신청횟수" data-title="신뢰도?"><i class="icon-ok"></i> 신뢰도</a></dt>
							<dd class="span8">${member.totalTrustRate == null ? '0' : member.totalTrustRate}%</dd>
							<dt class="span2"><i class="icon-inbox"></i> 이메일 수신</dt>
							<dd class="span8">${member.isAllowedEmail == true ? "수신" : "거부"}</dd>
							<dt class="span2"><i class="icon-comment"></i> 구글톡 수신</dt>
							<dd class="span8">${member.isAllowedGoogleTalk == true ? '수신' : '거부'}</dd>
						</dl>
					</section>
				</article>
			</section>
			
			<nav class="tabbable">
				<ul class="nav nav-tabs">
					<li class="active"><a href="#1" data-toggle="tab">스터디정보</a></li>
					<li><a href="#2" data-toggle="tab">위키정보</a></li>
					<li><a href="#3" data-toggle="tab">용어정보</a></li>
					<li><a href="#4" data-toggle="tab">내가 쓴 글</a></li>			
				</ul>
				
				<section class="tab-content">
					<section class="tab-pane active" id="1">
						<header>
							<h2>스터디 정보</h2>
						</header>
						<article>
							<c:choose>
							 	<c:when test="${empty member.studies}">
							    	 참석한 스터디가 없습니다.
							 	</c:when>
							 	<c:otherwise>
									<c:forEach var="study" items="${member.studies}">
										<c:set var="studyObject" value="${study}"/>
										<h3><a href="#">${study.studyName}</a></h3>
										<section>
											<article class="list">
												<span class="study_go" onclick="goStudy(${study.id})">스터디 보러가기</span>	
												<ul> 
													<li><span>담당자명</span><strong>${study.manager.name}</strong> <span>시작일</span><strong><fmt:formatDate pattern="yyyy.MM.dd" value="${study.startDay}" /></strong></li> 
													<li><span>진행현황</span><strong>${study.status.descr}</strong> <span>참석률</span><strong>${member.studyAttendanceRates[studyObject]}%</strong></li> 
													<li><span>신뢰도</span><strong>${member.studyTrustRates[studyObject]}%</strong></li> 
												</ul>
											</article>
										</section>
									</c:forEach>
							 	</c:otherwise>
						 	</c:choose>
						</article>
					</section>
					<section class="tab-pane" id="2">
						<p>준비 중입니다.</p>
					</section>
					<section class="tab-pane" id="3">
						<p>준비 중입니다.</p>
					</section>
					<section class="tab-pane" id="4">
						<p>준비 중입니다.</p>
					</section>
				</section>
			</nav>
		</section>
		<footer>
			<p class="copyright">&copy; SpringSprout rocks! 2012</p>
		</footer>
		<script src="/static/jquery/jquery.min.js"></script>
        <script src="/static/bootstrap/js/bootstrap.min.js"></script>
        <script>
            $(document).ready(function(){
                $("a[rel=popover]")
                    .popover({
                    	placement: 'top'
                    }).click(function(e) {
                        e.preventDefault()
                    });
            });
            
            function goStudy(id) {
				location.href="../study/view/" + id;	
			}
        </script>
    </body>
</html>