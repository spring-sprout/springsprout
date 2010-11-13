<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="std" tagdir="/WEB-INF/tags/study"%>
<%@ taglib prefix="s2c" tagdir="/WEB-INF/tags/s2c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
<c:choose>
	<c:when test="${study.isStarted || study.isOpened}">
		<c:choose>
			<c:when test="${isAlreadyJoinMember}" >
				<a class="button" href="<c:url value="/study/out/${study.id}"/>">스터디 탈퇴</a>
			</c:when>
			<c:otherwise>
				<a class="button" href="<c:url value="/study/join/${study.id}"/>">스터디 참여</a>
			</c:otherwise>
		</c:choose>
    </c:when>
    <c:otherwise>
        참가자목록
    </c:otherwise>
</c:choose>
--%>
<div id="member_list">
	<c:choose>
   		<c:when test="${!empty study.members}">
		<c:set var="studyObject" value="${study}"/>
		<ul id="list">
		<c:forEach var="member" items="${study.members}" varStatus="row">
		<li class="round memberItem" arate="${member.studyAttendanceRates[studyObject]}" trate="${member.studyTrustRates[studyObject]}">
			<img src="${member.avatar}&s=50"/>
			<div class="description">
				<strong>${member.name}</strong> <br/>
                <std:arate currentRate="${member.studyAttendanceRates[studyObject]}"
                           totlaRate="${member.totalAttendanceRate}" />|
                <std:trate currentRate="${member.studyTrustRates[studyObject]}"
                           totlaRate="${member.totalTrustRate}" /> <br/>
				가입일: <fmt:formatDate value="${member.joined}" pattern="yyyy년 M월 d일" />
			</div>
			<div class="count">
				${row.count}
			</div>
			<br class="clear"/>
		</li>
		</c:forEach>
		</ul>
   		</c:when>
   		<c:otherwise>
		     참석예정인 회원이 없습니다.
   		</c:otherwise>
   	</c:choose>
</div>
<script type="text/javascript">
    $(function(){
        $(".memberItem").each(function(){
            var arate = $(this).attr("arate");
            if(arate == 100){
                $(this).css("border", "2px solid #8D38C9");
            } else if(arate > 0) {
                $(this).css("border", "2px solid #4CC552");
            }

            var trate = $(this).attr("trate");
            if(trate == 100){
                $(this).css("background", "#F9B7FF");
            } else if(trate > 0) {
                $(this).css("background", "#C3FDB8");
            }
        });
    });
</script>