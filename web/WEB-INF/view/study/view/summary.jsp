<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="s2c" tagdir="/WEB-INF/tags/s2c"%>
<%@ taglib prefix="s" tagdir="/WEB-INF/tags/study"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<style type="text/css">
    ul.meetings {
        display: inline-block;
        list-style-type: none;
        margin: 0;
        padding: 0;
        vertical-align: top;
        width: 383px;
    }
    /*Meetings */
    ul.meetings li {
        float: left;
        margin-bottom: 5px;
    }
    ul.meetings li:hover {
        background: #F7F7F7;
    }
    .meeting-date {
        float: left;
        width: 50px;
        text-align: center;
        font-weight: bold;
    }
    .month {
        color:white;
        display:block;
        font-family:arial,sans-serif;
        font-size:1.1em;
        line-height:1em;
        padding:0 0 0.25em;
    }
    .date {
        -moz-border-radius:0 0 8px 0;
        background:none repeat scroll 0 0 green;
        display:block;
        margin:0 auto;
        padding:0.25em 3px 3px;
    }
    .day {
        -moz-border-radius:0 0 3px 0;
        background:none repeat scroll 0 0 #FFFFFF;
        color:green;
        display:block;
        font-family:arial,sans-serif;
        font-size:2.46em;
        line-height:1em;
        padding:0.15em 0;
    }
    .dayOfWeek {
        display:block;
        font-size:0.9em;
        margin-top:0.25em;
    }
    .dueTime {
        font-weight: 500;
    }
    ul.meetings li .meeting-descr {
        float: left;
        padding-left: 10px;
        width: 319px;
    }
    ul.meetings li .meeting-descr h4 {
        font-weight: 200;
        font-size: 1.2em;
        color: #333333;
    }
    ul.meetings li .meeting-descr div {
        margin-top: 2px;
    }


    <%-- ===================== --%>
    <%--    메뉴 아이콘 설정     --%>
    <%-- ===================== --%>
    .menu-bar .menu-group a.mgt span span,
    .menu-bar .menu-group.opened a.mgt span span{
        background-image: url("/images/tools_20.png");
    }

    .menu-bar .menu-button a.join-btn span {
        background-image: url("/images/big-smile-icon.png");
    }

    .menu-bar .menu-button a.out-btn span {
        background-image: url("/images/electric-shock-icon.png");
    }
    /*.menu-bar .drop-down a.add-comment {*/
        /*background-image: url("../../../images/icons/comment_16.png");*/
    /*}*/

    /*.menu-bar .drop-down a.add-attachment {*/
        /*background-image: url("../../../images/icons/attach_16.png");*/
    /*}*/


</style>
<h2>종합</h2>
<div id="quicklinks">
    <c:if test="${isManagerOrAdmin}">
        <ul class="operations menu-bar">
            <li class="menu-button">
                <c:if test="${(study.isStarted || study.isOpened) && (!isAlreadyJoinMember)}">
                    <a class="join-btn" href="<c:url value="/study/${study.id}/join"/>" title="스터디 가입">
                        <span>가입</span>
                    </a>
                </c:if>
                <c:if test="${(study.isStarted || study.isOpened) && (isAlreadyJoinMember)}">
                    <a class="out-btn" href="<c:url value="/study/${study.id}/out"/>" title="스터디 탈퇴">
                        <span>탈퇴</span>
                    </a>
                </c:if>
            </li>
            <li class="menu-button">
                <a class="no-icon" href="<c:url value="/study/${study.id}/notify"/>">
                    <span class="no-icon">스터디 알림</span>
                </a>
            </li>
            <li class="menu-group">
                <a class="menu-title trigger mgt" href="#study-mgt-menu">
                    <span>
                        <span>관리</span>
                    </span>
                </a>
                <div id="study-mgt-menu" class="drop-down most-right-menu assistive">
                    <ul class="first">
                        <li><a class="no-icon" href="<c:url value="/study/${study.id}/form"/>"><span>스터디 수정</span></a></li>
                        <li><a class="no-icon" href="<c:url value="/study/${study.id}/end"/>"><span>스터디 종료</span></a></li>
                        <li><a class="no-icon" href="<c:url value="/study/${study.id}/start"/>"><span>스터디 시작</span></a></li>
                        <li><a class="no-icon" href="<c:url value="/study/${study.id}/delete"/>"><span>스터디 삭제</span></a></li>
                    </ul>
                </div>
            </li>
        </ul>
    </c:if>
</div>
<s2c:left-column>
    <s2c:module name="정보">
        <ul class="item-details">
            <li>
                <dl>
                    <dt>리더:</dt>
                    <dd>${study.manager.name}</dd>
                </dl>
                <dl>
                    <dt>모임수:</dt>
                    <dd>${study.meetingCount}</dd>
                </dl>
                <dl>
                    <dt>참석인원:</dt>
                    <dd>${study.memberCount}/${study.maximumCount}</dd>
                </dl>
                <dl>
                    <dt>시작일:</dt>
                    <dd><s:date value="${study.startDay}"/></dd>
                </dl>
                <dl>
                    <dt>종료일:</dt>
                    <dd><s:date value="${study.endDay}"/></dd>
                </dl>
            </li>
        </ul>
    </s2c:module>
    <s2c:module name="최근 모임">
        <ul class="meetings">
            <c:forEach items="${study.meetings}" var="meeting" begin="0" end="2">
                <li>
                    <input type="hidden" class="openDateVal" value="${meeting.openDate}" />
                    <div class="meeting-date">
                        <span class="date">
                            <span class="month"></span>
                            <span class="day"></span>
                        </span>
                        <span class="dayOfWeek">
                            <span class="shortDay"></span>
                        </span>
                    </div>
                    <div class="meeting-descr">
                        <h4><a href="/study/${study.id}/meeting/${meeting.id}">${meeting.title}</a></h4>
                        <div>${meeting.location}</div>
                        <div>
                            <span class="dueTime"><fmt:formatDate value="${meeting.openTime}" pattern="a h시 mm분"/></span> ~
                            <span class="dueTime"><fmt:formatDate value="${meeting.closeTime}" pattern="a h시 mm분"/></span>
                        </div>
                        <div>
                            <img src="/images/study/user-group-icon.png" alt="신청자 수"> ${meeting.attendanceCount}
                            <img src="/images/study/user-icon.png" alt="참석자 수"> ${meeting.attendedCount}
                            <img src="/images/study/offline-user-icon.png" alt="불참석자 수"> ${meeting.attendanceCount - meeting.attendedCount}
                        </div>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </s2c:module>
    <s2c:module name="최근 게시물">
        <ul class="posts">
            <c:forEach items="${postList}" var="post">
                <li>
                    <div style="clear:both;">
                        <div style="float:left;">
                            <a href="/study/${study.id}/post/textPost/article/${post.id}"><h4>${post.title}</h4></a>
                        </div>
                        <div style="float:right;">
                            <s:date value="${post.createdAt}"/>
                            <img src="${post.writer.avatar}" width="50" height="50"/>
                        </div>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </s2c:module>
</s2c:left-column>
<s2c:right-column>
    <s2c:module name="소개">
        <s:nl2br value="${study.descr}"/>    
    </s2c:module>
    <s2c:module name="활동 내역">

    </s2c:module>
</s2c:right-column>
<script type="text/javascript">
   $(function(){
        var $meetingItem = $(".meetings li");
        changeMeetingDateFormat($meetingItem);

       $(".menu-bar li.menu-group").mouseover(function() {
           $(".menu-bar").addClass("menu-bar-open");
           $(this).addClass("opened");
           $(this).find(".drop-down").removeClass("assistive");
       }).mouseout(function() {
           $(".menu-bar").removeClass("menu-bar-open");
           $(this).removeClass("opened");
           $(this).find(".drop-down").addClass("assistive");
       });

       $(".drop-down li").mouseover(function() {
           $(this).addClass("active");
       }).mouseout(function() {
           $(this).removeClass("active");
       });
   });
</script>