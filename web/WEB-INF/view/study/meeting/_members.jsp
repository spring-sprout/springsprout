<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="std" tagdir="/WEB-INF/tags/study"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="meeting_members">
	<div id="member_list">
	<c:choose>
   		<c:when test="${!empty attendances}">
   			<c:set var="studyObject" value="${meeting.study}"/>
			<ul id="list">
			<c:forEach var="attendance" items="${attendances}">
			<li class="memberItem round ${attendance.attended}">
				<img class="logoSmall" src="${attendance.member.avatar}&s=50"/>
				<div class="description">
					<strong>${attendance.member.name}</strong> <br/>
                       <std:arate currentRate="${attendance.member.studyAttendanceRates[studyObject]}"
                              totlaRate="${attendance.member.totalAttendanceRate}"/>
                       <std:trate currentRate="${attendance.member.studyTrustRates[studyObject]}"
                              totlaRate="${attendance.member.totalTrustRate}"/> <br/>
					가입일: <fmt:formatDate value="${attendance.member.joined}" pattern="yyyy년 M월 d일" />
				</div>
				<c:if test="${isManagerOrAdmin && meeting.isOpened}">
				<div class="count">
				<c:choose>
					<c:when test="${attendance.attended}">
						<img class="btn_attend_member action" src="<c:url value="/images/accept-16x16.png"/>" meeting="${meeting.id}" attendance="${attendance.id}" />
					</c:when>
					<c:otherwise>
						<img class="btn_absend_member action" src="<c:url value="/images/remove-16x16.png"/>" meeting="${meeting.id}" attendance="${attendance.id}" />
					</c:otherwise>
				</c:choose>
				</div>
				</c:if>
				<br class="clear"/>
			</li>
			</c:forEach>
			</ul>
   		</c:when>
   		<c:otherwise>
		     현재 참석예정인 회원이 없습니다.
   		</c:otherwise>
 	  </c:choose>
	</div>
</div>
<script type="text/javascript">
$( function(){
	var removeImg = "<c:url value='/images/remove-16x16.png'/>",
		acceptImg = "<c:url value='/images/accept-16x16.png'/>",
		loaderImg = "<c:url value='/images/ajax-loader.gif'/>",
		$attendedCount = $('#attendedCount');
		
	$(".btn_attend_member").live( 'mouseover', function(){
		$(this).attr("src", removeImg);
	}).mouseout(function(){
		$(this).attr("src", acceptImg);
	});

	$(".btn_absend_member").live( 'mouseover', function(){
		$(this).attr("src", acceptImg);
	}).mouseout(function(){
		$(this).attr("src", removeImg);
	});
	
	//참석/불참석
	$('.btn_attend_member, .btn_absend_member').click(function(e){
		var $this = $(this), action, imgSrc, chgClass, isAttended;

		//loading image
		$this.attr('src', loaderImg);

		if( $this.hasClass('btn_attend_member')) {
			action = '/reject/';
			imgSrc = removeImg;
			chgClass = 'btn_absend_member';
			isAttended = 'false';
		} else {
			action = '/confirm/';
			imgSrc = acceptImg;
			chgClass = 'btn_attend_member';
			isAttended = 'true';
		}

		var url = $this.attr('meeting') + action + $this.attr('attendance');

		//AJAX 요청 보내기 만들어야함..
		$.getJSON(url, function(data, status) {
			//성공시 실행될 로직
			if(status === 'success') {
				$this.attr('src', imgSrc)
					.removeClass('btn_absend_member')
					.removeClass('btn_attend_member')
					.addClass(chgClass);
				$this.parent().parent('li')
					.removeClass('true')
					.removeClass('false')
					.addClass(isAttended);
				
				changeAttendedCount( isAttended);
				$.growlUI('Notification', data.notifyMsg);
			} else {
				$.growlUI('Notification', "출석체크에 실패했습니다. 다시 시도해주세요.");
			}
		});
	});
	function changeAttendedCount( isAttended) {
		var now = parseInt( $attendedCount.text());
		if ( isAttended === 'true') $attendedCount.text( now + 1);
		else $attendedCount.text( now - 1);
		
	}
});
</script>