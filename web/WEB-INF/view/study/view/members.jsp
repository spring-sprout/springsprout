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
<style type="text/css">
    #list li.memberItem {
        width: 80%;
    }
    
    .mod-header .ops {
        background-color: #FFFFFF;
        float: right;
        margin-top: 0.415em;
        padding-left: 0.25em;
    } 
    .ops {
        list-style-type: none;
        margin: 0;
        padding: 0;
    }
    .ops li {
        float: left;
    }
    .ops img {
        cursor: pointer;
    }
</style>
<h2>구성원</h2>
<s2c:left-column>
    <div class="module">
        <div class="mod-header">
            <h3 id="attention-title">스터디 참석 신청자</h3>
            <ul class="ops">
                <li><img src="/images/study/user-group-icon.png" alt="신청자" onclick="showAll()"></li>
                <li><img src="/images/study/user-icon.png" alt="참석자" onclick="showAttended()"></li>
                <li><img src="/images/study/offline-user-icon.png" alt="불참자" onclick="showNotAttended()"></li>
            </ul>
        </div>
        <div class="mod-content">
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
                                        가입일: <s2c:date value="${member.joined}"/>
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
        </div>
    </div>
</s2c:left-column>
<s2c:right-column>
    <s2c:module name="전체 참석율">
        <table cellspacing="0" cellpadding="2">
            <tbody>
            <c:forEach items="${studyMemberStatistics}" var="stat" varStatus="status">
                <tr class="${status.count%2 == 0 ? 'rowAlternateLightGray': ''}">
                    <td class="name">${stat.title}</td>
                    <td class="count">${stat.count}</td>
                    <td class="graph last"><s2c:graph value="${stat.percentage}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </s2c:module>
    <s2c:module name="회원별 모임 참석율">
        <table cellspacing="0" cellpadding="2">
            <tbody>
            <c:forEach items="${memberMeetingStatistics}" var="stat2" varStatus="status">
                <tr class="${status.count%2 == 0 ? 'rowAlternateLightGray': ''}">
                    <td class="name">${stat2.member.name}</td>
                    <td class="count">${stat2.meetingCount}</td>
                    <td class="graph last"><s2c:graph value="${stat2.percentage}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </s2c:module>
</s2c:right-column>
<script type="text/javascript">
    $(function(){
        $(".memberItem:not([arate=0])").css("border", "1px solid #4183C4");
        $(".memberItem:not([trate=0])").css("background", "#E0F8F7 none repeat scroll 0 0");
    });

    function showAttended() {
        $(".memberItem").hide().filter(":not([arate=0][trate=0])").show();
        $("#attention-title").text("참석자");
    }
    function showNotAttended() {
        $(".memberItem").hide().filter("[arate=0][trate=0]").show();
        $("#attention-title").text("불참자");
    }
    function showAll() {
        $(".memberItem").show();
        $("#attention-title").text("스터디참석신청자");
    }

</script>