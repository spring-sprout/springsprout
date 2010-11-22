<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="s2c" tagdir="/WEB-INF/tags/s2c"%>
<%@ taglib prefix="s" tagdir="/WEB-INF/tags/study"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<style type="text/css">
    .openDate {
        float: left;
        width: 40px;
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
        color:#333333;
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
    .meetingTitle {
        font-weight: bold;
        font-size: 1.1em;
    }
    .dueTime {
        font-weight: bold;
    }
    #meeting_list li.meetingItem {
        width: 90%;
    }
</style>
<h2>모임</h2>
<s2c:left-column>
    <s2c:module name="최근모임">
        <c:forEach items="${study.meetings}" begin="0" end="0" var="lastMeeting">
            <ul class="item-details">
                <li>
                    <dl>
                        <dt>모임명: </dt>
                        <dd>
                            ${lastMeeting.title}
                        </dd>
                        <dt>모임장: </dt>
                        <dd>
                            ${lastMeeting.owner.name}
                        </dd>
                        <dt>모임일: </dt>
                        <dd>
                            <s:date value="${lastMeeting.openDate}"/>
                        </dd>
                        <dt>모임시간: </dt>
                        <dd>
                            <s2c:time value="${lastMeeting.openTime}"/> ~
                            <s2c:time value="${lastMeeting.closeTime}"/>
                        </dd>
                        <dt>장소: </dt>
                        <dd>
                            ${lastMeeting.location}
                        </dd>
                    </dl>
                </li>
            </ul>
            <s:nl2br value="${lastMeeting.contents}"/>
        </c:forEach>
    </s2c:module>
    <s2c:module name="요일별 모임횟수">
        <table cellspacing="0" cellpadding="2">
            <tbody>
            <c:forEach items="${meetingWeekStatistics}" var="stat" varStatus="status">
                <tr class="${status.count%2 == 0 ? 'rowAlternateLightGray': ''}">
                    <td class="name">${stat.dayOfWeek.title}</td>
                    <td class="count">${stat.meetingCount}</td>
                    <td class="graph last"><s2c:graph value="${stat.percentage}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </s2c:module>
    <s2c:module name="모임별 출석률">
        <table cellspacing="0" cellpadding="2">
            <tbody>
            <c:forEach items="${meetingMemberStatistics}" var="stat" varStatus="status">
                <tr class="${status.count%2 == 0 ? 'rowAlternateLightGray': ''}">
                    <td class="name">${stat.meeting.shortTitle}</td>
                    <td class="count">${stat.realCount}/${stat.attendedCount}</td>
                    <td class="graph last"><s2c:graph value="${stat.percentage}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </s2c:module>
</s2c:left-column>
<s2c:right-column>
    <s2c:module name="모임목록">
        <div id="meeting_list">
            <c:choose>
                <c:when test="${!empty study.meetings}">
                    <ul id="list">
                        <c:forEach var="meeting" items="${study.meetings}" varStatus="status">
                            <li class="meetingItem s_waitblock round action ${meeting.status}" study="${study.id}" meeting="${meeting.id}">
                                <input type="hidden" class="openDateVal" value="${meeting.openDate }" />
                                <div class="openDate">
                                    <span class="date">
                                        <span class="month"></span>
                                        <span class="day"></span>
                                    </span>
                                    <span class="dayOfWeek">
                                        <span class="shortDay"></span>
                                    </span>
                                </div>
                                <div style="float: left; width: 280px;">
                                    <div class="description">
                                        <span class="meetingTitle">${meeting.title}</span><br/>
                                        모임장: ${meeting.owner.name} |
                                        상태: ${meeting.status.descr} |
                                        인원수: ${meeting.attendedCount}/${meeting.attendanceCount}/${meeting.maximum} <br/>
                                        장소: ${meeting.location}<br/>
                                        시간: <span class="dueTime">${meeting.openTime}</span> ~ <span class="dueTime">${meeting.closeTime}</span>
                                    </div>
                                    <div class="count">
                                            ${meeting.cnt}
                                    </div>
                                </div>
                                <br class="clear"/>
                            </li>
                        </c:forEach>
                    </ul>
                </c:when>
                <c:otherwise>
                    모임 정보가 없습니다.
                </c:otherwise>
            </c:choose>
        </div>
    </s2c:module>
</s2c:right-column>

<script type="text/javascript">
$(document).ready( function(){
	$meetingItem = $(".meetingItem");
	$meetingItem.click( function() {
		var url = '<c:url value="/study/"/>' + $(this).attr("study") + "/meeting/" + $(this).attr("meeting") + "";
		$(document).attr("location", url);
	});

	$meetingItem.each( function() {
		var $this = $(this), openDate = $this.find('.openDateVal').val(), myDate = new Date();
		myDate.setFullYear(openDate.slice(0, 4),openDate.slice(5, 7)-1,openDate.slice(8, 10));
		$this.find('.month').text($.datepicker.regional['ko'].monthNamesShort[myDate.getMonth()]);
		$this.find('.day').text(myDate.getDate());
		$this.find('.shortDay').text($.datepicker.regional['ko'].dayNamesShort[myDate.getDay()]);
	});
});
</script>
