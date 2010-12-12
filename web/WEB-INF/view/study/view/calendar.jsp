<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="s2c" tagdir="/WEB-INF/tags/s2c"%>
<%@ taglib prefix="s" tagdir="/WEB-INF/tags/study"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<style type="text/css">
    .menu-bar .menu-group a.mgt span span,
    .menu-bar .menu-group.opened a.mgt span span{
        background-image: url("/images/tools_20.png");
    }
</style>
<h2>일정</h2>
<div id="quicklinks">
    <c:if test="${isManagerOrAdmin}">
        <ul class="operations menu-bar">
            <li class="menu-group">
                <a class="menu-title trigger mgt" href="#study-mgt-menu">
                    <span>
                        <span>관리</span>
                    </span>
                </a>
                <div id="study-mgt-menu" class="drop-down most-right-menu assistive">
                    <ul class="first">
                        <li><a class="no-icon" href="<c:url value="/study/${study.id}/calendar/create"/>"><span>캘린더 생성</span></a></li>
                        <li><a class="no-icon" href="<c:url value="/study/${study.id}/calendar/sync"/>"><span>캘린더 동기화</span></a></li>
                    </ul>
                </div>
            </li>
        </ul>
    </c:if>
</div>
<s2c:full-column>
    <c:choose>
        <c:when test="${study.calendarId eq null}">
            구글 캘린더에 등록되지 않았습니다.
        </c:when>
        <c:otherwise>
            <iframe src="http://www.google.com/calendar/embed?src=${study.calendarId}%40group.calendar.google.com&ctz=Asia/Seoul" width="830" height="600" frameborder="0" scrolling="no"></iframe>
        </c:otherwise>
    </c:choose>
</s2c:full-column>
<script type="text/javascript">
   $(function(){
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