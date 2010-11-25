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
        width: 100%;
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
        width: 320px;
    }
    ul.meetings li .meeting-descr h4 {
        font-weight: 200;
        font-size: 1.2em;
        color: #333333;
    }
    ul.meetings li .meeting-descr div {
        margin-top: 2px;
    }

    .menu-bar {
        float: right;
        line-height: 1.5;
        position: relative;
        display: block;
        padding: 0;
        margin:10px 0 0 1em;
    }
    .menu-bar .menu-button, .menu-bar .menu-group {
        float:left;
        line-height: 2;
        list-style: none outside none;
        position: static;
        display: block;
        margin: 0;
        padding: 0;
    }
    .menu-bar .menu-button a, .menu-bar .menu-group .menu-title {
        -moz-border-radius: 3px 3px 3px 3px;
        color: #535353;
        font-size: 1.1em;
        line-height: 20px;
        padding: 0.35em 0.5em;
        text-decoration: none;
        white-space: nowrap;
    }
    .menu-bar .menu-button a:hover, .menu-bar .menu-group .menu-title:hover {
        background-color: #6699CC;
        color: #FFFFFF;
    }
    .menu-bar .menu-group .trigger span {
        background-position: 100% 50%;
        background-repeat: no-repeat;
        background-image: url("/images/menu_indicator.gif");
        position: relative;
    }
    .menu-bar .menu-group.opened .trigger span {
        background-image: url("/images/white_menu_indicator.gif");
    }
    .menu-bar .menu-group .trigger span span {
        padding-right:0.9em;
        padding-left: 25px;
    }
    .menu-bar .menu-group .mgt span span {
        background-image: url("/images/tools_20.png");
        background-position: 0 50%;
        background-repeat: no-repeat;
        padding-top: 1px;
    }

</style>
<h2>Summary</h2>
<div id="quicklinks">
    <ul class="operations menu-bar">
        <li class="menu-button">
            <a class="back" href="<c:url value="/study/index3"/>" title="스터디 목록으로 이동">
                <span>스터디 목록</span>
            </a>
        </li>
        <li class="menu-button">
            <a href="<c:url value="/study/join/${study.id}"/>" title="스터디 참여">
                <span>가입</span>
            </a>
        </li>
        <li class="menu-button">
            <a href="<c:url value="/study/out/${study.id}"/>" title="스터디 탈퇴">
                <span>탈퇴</span>
            </a>
        </li>
        <li class="menu-group">
            <a class="menu-title trigger mgt" href="#meeting-menu">
                <span>
                    <span>관리</span>
                </span>
            </a>
        </li>
        <li>
            <div id="meeting-menu" class="hidden">
                <ul>
                    <li><a class="confirmRequired" id="studyNotify" href="<c:url value="/study/notify/${study.id}"/>">스터디 알림</a></li>
                    <li><a class="confirmRequired" href="<c:url value="/study/update/${study.id}"/>">스터디 수정</a></li>
                    <li><a class="confirmRequired" href="<c:url value="/study/end/${study.id}"/>">스터디 종료</a></li>
                    <li><a class="confirmRequired" href="<c:url value="/study/delete/${study.id}"/>">스터디 삭제</a></li>
                    <li><a class="confirmRequired" href="<c:url value="/study/start/${study.id}"/>">스터디 시작</a></li>
                    <li><hr/></li>
                    <li><a class="confirmRequired" href="<c:url value="/study/meeting?studyId=${study.id}"/>">모임 추가</a></li>
                </ul>
            </div>
        </li>
    </ul>
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
   });
</script>