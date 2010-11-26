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

    <%-- 메뉴 만들기 --%>
    .menu-bar {
        float: right;
        line-height: 1.5;
        position: relative;
    }
    .menu-bar, .menu-bar .menu-button, .menu-bar .menu-button a, .menu-bar .menu-button span, .menu-bar .menu-group .menu-title, .menu-bar .menu-group .menu-title span, .menu-bar .menu-group .menu-title span span, .menu-bar .drop-down a, .menu-bar .drop-down a span, .menu-bar .drop-down a span span {
        display: block;
        margin: 0;
        padding: 0;
    }
    .menu-bar .menu-button span, .menu-bar .menu-group .trigger span span {
        padding-right: 1em;
    }
    .menu-bar .menu-group, .menu-bar .menu-button {
        float: left;
        list-style: none outside none;
        position: relative;
    }
    .menu-bar .menu-group a, .menu-bar .menu-button a {
        line-height: 1.5;
        text-decoration: none;
        white-space: nowrap;
    }
    .menu-bar .menu-button {
        float: left;
        line-height: 2;
        list-style: none outside none;
        position: static;
    }
    .menu-bar .menu-group .trigger span {
        background-image: url("/images/menu_indicator.gif");
        position: relative;
    }
    .menu-bar .menu-group.opened .trigger span {
        background-image: url("/images/white_menu_indicator.gif");
    }
    .menu-bar .menu-group.opened .trigger span span, .menu-bar .menu-group .trigger span span {
        background-image: none;
    }
    .menu-bar .menu-button a:hover, .menu-bar .menu-group.opened .trigger span {
        color: #FFFFFF;
    }
    .menu-bar .menu-group .trigger span {
        background-position: 100% 50%;
        background-repeat: no-repeat;
    }
    .menu-bar .menu-button span, .menu-bar .menu-group .trigger span span {
        background-position: 0 50%;
        background-repeat: no-repeat;
    }
    .menu-bar .menu-group .drop-down a {
        background: none no-repeat scroll 4px 50% #FFFFFF;
        border: medium none;
        display: block;
        line-height: 1.6;
        margin: 0;
        padding: 0 0.5em 0 24px;
        text-decoration: none;
    }
    .menu-bar .drop-down {
        -moz-border-radius-bottomleft: 3px;
        -moz-border-radius-bottomright: 3px;
        background: none repeat scroll 0 0 #FFFFFF;
        border: 1px solid #C1C1C1;
        font-weight: normal;
        left: 0;
        /*min-width: 11em;*/
        padding: 0;
        position: absolute;
        white-space: nowrap;
        z-index: 1000;
    }
    #header-menu-bar .drop-down {
        min-width: 15em;
    }
    .menu-bar .drop-down.above {
        -moz-border-radius: 3px 3px 0 0;
        bottom: 2em;
        margin-top: -2px;
    }
    .menu-bar .drop-down ul, .menu-bar ul.drop-down {
        border-top: 1px solid #E1E1E1;
        list-style: none outside none;
        margin: 0;
        padding: 5px 0;
        position: relative;
    }
    .menu-bar .drop-down ul.first, .menu-bar ul.drop-down.first {
        border-top: medium none;
    }
    .menu-bar .drop-down li {
        margin: 0;
        padding: 0;
    }
    .menu-bar .menu-group div.drop-down a {
        background-position: 0.5em 50%;
        background-repeat: no-repeat;
        border: medium none;
        display: block;
        line-height: 2;
        margin: 0;
        padding: 0 1em 0 28px;
        position: relative;
        text-decoration: none;
        white-space: nowrap;
    }
    .menu-bar .drop-down a img {
        float: left;
        height: 16px;
        margin: 0.3em 0 0 -20px;
        width: 16px;
    }

    .menu-bar .menu-group .section-secondary li a, .menu-bar .menu-group .section-modify li a {
        font-size: 0.9em;
        padding-left: 8px;
    }
    .menu-bar .menu-group .drop-down li.hidden {
        display: none;
    }
    .menu-bar {
        margin: 10px 0 0 1em;
    }
    .menu-bar .menu-button span, .menu-bar .menu-group .menu-title span span {
        padding-left: 25px;
    }
    .menu-bar li.menu-group .menu-title, .menu-bar li.menu-button a {
        -moz-border-radius: 3px 3px 3px 3px;
        color: #535353;
        font-size: 1.1em;
        line-height: 20px;
        padding: 0.35em 0.5em;
    }
    .menu-bar li.menu-group .menu-title {
        -moz-border-radius-bottomleft: 0;
        -moz-border-radius-bottomright: 0;
    }
    .menu-bar .menu-group .most-right-menu {
        left: auto;
        margin: 0;
        right: 0;
    }
    .menu-bar li.menu-group .drop-down a {
        color: #535353;
    }
    .menu-bar .menu-button a:hover,
    .menu-bar .menu-group li.active a,
    .menu-bar .menu-group.opened .drop-down li.active a,
    .menu-bar li.menu-button a.active,
    .menu-bar li.menu-group.opened .drop-down li.active a,
    .menu-bar li.menu-group a.trigger:hover{
        background-color: #6699CC;
        color: #FFFFFF;
    }
    .menu-bar .menu-group.opened a.trigger {
        background-color: #6699CC;
    }
    .assistive {
        height: 0;
        left: -20000px;
        overflow: hidden;
        position: absolute;
        top: 0;
        width: 4000px;
    }
    .menu-bar .menu-group .assistive {
        display: block;
        left: -20000px;
        position: absolute;
        visibility: visible;
        width: 4000px;
    }
    .menu-bar .menu-group .most-right-menu.assistive {
        left: auto;
        right: 20000px;
    }
    .menu-bar .menu-group a.mgt span span,
    .menu-bar .menu-group.opened a.mgt span span{
        background-image: url("/images/tools_20.png");
    }
    /*.menu-bar .drop-down a.add-comment {*/
        /*background-image: url("../../../images/icons/comment_16.png");*/
    /*}*/

    /*.menu-bar .drop-down a.add-attachment {*/
        /*background-image: url("../../../images/icons/attach_16.png");*/
    /*}*/


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
            <a class="join" href="<c:url value="/study/join/${study.id}"/>" title="스터디 참여">
                <span>가입</span>
            </a>
        </li>
        <li class="menu-button">
            <a class="out" href="<c:url value="/study/out/${study.id}"/>" title="스터디 탈퇴">
                <span>탈퇴</span>
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
                    <li><a href="<c:url value="/study/notify/${study.id}"/>"><span>스터디 알림</span></a></li>
                    <li><a href="<c:url value="/study/update/${study.id}"/>"><span>스터디 수정</span></a></li>
                    <li><a href="<c:url value="/study/end/${study.id}"/>"><span>스터디 종료</span></a></li>
                    <li><a href="<c:url value="/study/delete/${study.id}"/>"><span>스터디 삭제</span></a></li>
                    <li><a href="<c:url value="/study/start/${study.id}"/>"><span>스터디 시작</span></a></li>
                </ul>
                <ul>
                    <li><a href="<c:url value="/study/meeting?studyId=${study.id}"/>"><span>모임 추가</span></a></li>
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