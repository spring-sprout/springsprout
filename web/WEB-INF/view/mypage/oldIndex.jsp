<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<page:defaultpage selected_menu="mypage">
<script type="text/javascript" src="/js/framework/jquery.accordion.js"></script> 

<script type="text/javascript">
	$( function(){
		$('#studyinfo').accordion();
	});
	
	function goStudy(id) {
		location.href="../study/view/" + id;	
	}
</script>
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
</page:defaultpage>
