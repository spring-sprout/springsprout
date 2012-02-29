<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="bootstrap" tagdir="/WEB-INF/tags/bootstrap"%>
<%@ taglib prefix="springsprout" uri="/META-INF/springsprout.tld" %>
<springsprout:endScriptLoad>
    console.log('aaa');
</springsprout:endScriptLoad>
<bootstrap:container isSlimFooter="false">
<header class="slogan">
    <h1>
        <span class="kd">var</span> springSprout =
        <a href="#" rel="popover" data-content="추운 겨울 지나 꽃피는 봄에" data-placement="bottom"
           data-original-title="spring(...);"><span class="string">'spring'</span></a> +
        <a href="#" rel="popover" data-content="피어나는 새싹같은 커뮤니티." data-placement="bottom"
           data-original-title="sprout(...);"><span class="string">'sprout'</span></a>;
    </h1>
    <p class="sub-slogan">
        <a href="#" rel="popover" data-content="관심있는 주제의 스터디에 참여도 하시고 멋진 개발자도 많이 만나세요." data-placement="bottom"
           data-original-title="we.study(...);">학습하고</a>
        <a href="#" rel="popover" data-content="학습한 기술을 적용해볼 곳이 없다구요? 봄싹에서 여러 개발자와 같이 사용해 보세요." data-placement="bottom"
           data-original-title="we.develope(...);">개발하고</a>
        <a href="#" rel="popover" data-content="발표, 번역, 저술 등 여러 방법으로 학습하고 적용해본 경험을 다른 개발자와 나눠보세요." data-placement="bottom"
           data-original-title="we.share(...);">나누고</a>
        <a href="#" rel="popover" data-content="이 모든게 즐겁다면 여러분은 이미 봄싹 개발자입니다." data-placement="bottom"
           data-original-title="we.enjoy(...);">즐깁시다.</a>
    </p>
</header>
<section class="content-sec">
    <header class="sec-header">
        <h2>스터디&nbsp;<small>학습하고 개발하고</small></h2>
    </header>
    <ul class="studies">
        <c:choose>
            <c:when test="${studyList != null}">
                <c:forEach items="${studyList}" var="study">
                    <li class="study">
                        <div class="thumb">
                            <a href="<c:url value="/study/${study.id}"/>">
                                <img alt="스터디 로고" src="<c:url value="${study.logo != null ? study.logo : '/images/study/logos/default.png'}"/>">
                            </a>
                        </div>
                        <div class="infos">
                            <h3>
                                <a href="<c:url value="/study/${study.id}"/>">${study.studyName}</a>
                            </h3>
                            <p class="desc">
                                ${study.descrText}
                            </p>
                        </div>
                        <div class="meeting">
                            <c:choose>
                                <c:when test="${empty study.recentMeeting}">
                                    <p>진행중인 모임이 없습니다.</p>
                                </c:when>
                                <c:otherwise>
                                    <c:set var="meeting" value="${study.recentMeeting}"></c:set>
                                    <p><i class="icon-calendar"></i>
                                        <fmt:formatDate value="${meeting.openDate}" pattern="MM월 dd일 (E)"/>&nbsp;
                                        <fmt:formatDate value="${meeting.openTime}" pattern="H:mm"/>
                                    </p>
                                    <p><i class="icon-map-marker"></i> <a href="<c:url value="/study/${study.id}/meeting/${meeting.id}/meetingLocation"/>">${meeting.location}</a></p>
                                    <h4 class="meeting-title"><a href="<c:url value="/study/${study.id}/meeting/${meeting.id}"/>">${meeting.title}</a></h4>
                                    <p class="desc">
                                        ${meeting.contentsText}
                                    </p>
                                    <ul class="attenders unstyled">
                                        <c:forEach items="${meeting.attendances}" var="attendance" end="5">
                                            <li class="attender">
                                                <a href="<c:url value="/member/${attendance.member.id}"/>">
                                                    <img src="${attendance.member.avatar}&s=24" alt="${attendance.member.name}">
                                                </a>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                    <span class="attender-count">총 <fmt:formatNumber value="${meeting.attendanceCount}" pattern="#,###"/>명 참석</span>
                                    <div class="rsvp-btn-wrap"><a href="<c:url value="/study/${study.id}/meeting/${meeting.id}"/>" class="btn btn-small btn-primary">참석 신청</a></div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </li>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <li class="no-study">진행중인 스터디가 없습니다.</li>
            </c:otherwise>
        </c:choose>
    </ul>
    <p class="more-link"><a href="/study">스터디 더보기 &raquo;</a></p>
    <header class="sec-header">
        <h2>책&nbsp;<small>읽고 공부하고</small></h2>
    </header>
    <ul class="books thumbnails">
        <li class="book">
            <a class="thumbnail" target="_blank" href="http://blog.yes24.com/lib/adon/View.aspx?blogid=1423718&amp;goodsno=4744444&amp;idx=10351&amp;ADON_TYPE=B&amp;regs=b">
                <img src="http://image.yes24.com/momo/TopCate108/MidCate01/10704373.jpg" alt="스프링3 레시피" />
                <span class="title">스프링3 레시피</span>
            </a>
        </li>
        <li class="book">
            <a class="thumbnail" target="_blank" href="http://blog.yes24.com/lib/adon/View.aspx?blogid=1423718&amp;goodsno=4020006&amp;idx=7386&amp;ADON_TYPE=B&amp;regs=b">
                <img src="http://image.yes24.com/momo/TopCate87/MidCate10/8691328.jpg" alt="토비의 스프링 3" />
                <span class="title">토비의 스프링 3</span>
            </a>
        </li>
        <li class="book">
            <a class="thumbnail" target="_blank" href="http://blog.yes24.com/lib/adon/View.aspx?blogid=1423718&amp;goodsno=4425736&amp;idx=8502&ADON_TYPE=B&amp;regs=b">
                <img src="http://image.yes24.com/momo/TopCate97/MidCate09/9686817.jpg" alt="스프링 시큐리티 3" /`>
                <span class="title">스프링 시큐리티 3</span>
            </a>
        </li>
        <li class="book">
            <a class="thumbnail" target="_blank" href="http://blog.yes24.com/lib/adon/View.aspx?blogid=1423718&amp;goodsno=3908398&amp;idx=8502&ADON_TYPE=B&amp;regs=b">
                <img src="http://image.yes24.com/momo/TopCate85/MidCate04/8436972.jpg" alt="테스트 주도 개발" />
                <span class="title">테스트 주도 개발</span>
            </a>
        </li>
        <li class="book">
            <a class="thumbnail" target="_blank" href="http://blog.yes24.com/lib/adon/View.aspx?blogid=1423718&amp;goodsno=6271069&amp;idx=8502&ADON_TYPE=B&amp;regs=b">
                <img src="http://image.yes24.com/momo/TopCate168/MidCate04/16738746.jpg" alt="Node.js 프로그래밍" />
                <span class="title">Node.js 프로그래밍</span>
            </a>
        </li>
    </ul>
</section>
<section class="side-sec">
    <header class="sec-header">
        <h2>낙서장&nbsp;<small>나누고 즐깁시다</small></h2>
    </header>
    <article class="graffities">
        <sec:authorize ifAnyGranted="ROLE_MEMBER">
        <div class="graffiti-writer">
            <div class="writer-thumb-wrap">
                <img class="writer-thumb" src="http://www.gravatar.com/avatar/d3a3e1e76decd8760aaf9af6ab334264?r=X&s=28" />
            </div>
            <div class="writer-text holder">
                <input type="text" class="span3" placeholder="나누고 즐겨보아요!!">
            </div>
            <div class="writer-text wrap hide">
                <textarea rows="3" id="textarea" class="input-xlarge"></textarea>
                <a href="/main/graffitiWrite" class="btn btn-primary">수다</a>
            </div>
        </div>
        </sec:authorize>
        <ul class="stream-items">
            <c:forEach items="${graffitiList}" var="graffiti">
                <li class="stream-item">
                    <div class="writer-thumb-wrap">
                        <a href="<c:url value="/member/${graffiti.writerId}"/>">
                            <img class="writer-thumb" src="${graffiti.writerAvatar}" alt="${graffiti.writerName}"/>
                        </a>
                    </div>
                    <div class="writer-text">
                        <div class="item-header"><strong>${graffiti.writerName}</strong> <small class="item-time" title="${graffiti.formattedDate}">on ${graffiti.writtenDateString}</small></div>
                        <div class="item-content">
                            <spring:htmlEscape defaultHtmlEscape="true">
                                ${graffiti.contents}
                            </spring:htmlEscape>
                        </div>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </article>
</section>
</article>
</bootstrap:container>