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
        cursor: pointer;
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
        color: #333333;
        padding-bottom: 5px;
    }
    ul.meetings li .meeting-descr div {
        margin-top: 1px;
    }
</style>
<h2>Summary</h2>
<div id="quicklinks">
    <ul class="operations">
        <li>가입</li>
        <li>관리</li>
    </ul>
</div>
<div id="primary" class="column">
    <div class="content">
        <div class="module">
            <div class="mod-header">
                <h3>정보</h3>
            </div>
            <div class="mod-content">
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
            </div>
        </div>
        <div class="module">
            <div class="mod-header">
                <h3>최근 모임</h3>
            </div>
            <div class="mod-content">
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
                                <h4>${meeting.title}</h4>
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
            </div>
        </div>
    </div>
</div>
<div id="secondary" class="column">
    <div class="content">
        <div class="module">
            <div class="mod-header">
                <h3>소개</h3>
            </div>
            <div class="mod-content">
                <s:nl2br value="${study.descr}"/>
            </div>
        </div>
        <div class="module">
            <div class="mod-header">
                <h3>활동 내역</h3>
            </div>
            <div class="mod-content">

            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
   $(function(){
        var $meetingItem = $(".meetings li");
        changeMeetingDateFormat($meetingItem);
   });
</script>