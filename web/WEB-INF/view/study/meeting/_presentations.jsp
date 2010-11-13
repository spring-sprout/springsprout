<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="s2c" tagdir="/WEB-INF/tags/s2c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div id="presentation_list">
	<c:choose>
   		<c:when test="${!empty presentations}">
   		<ul id="list">
			<c:forEach var="presentation" items="${presentations}" varStatus="row">
			<li class="presentationItem round action ${presentation.status}" study="${meeting.study.id}" meeting="${meeting.id}" presentation="${presentation.id}">
				<img alt="발표자" src="${presentation.presenter.avatar}&s=65" />
				<div class="description">
					<strong>${presentation.title}</strong><br/>
					발표자: ${presentation.presenter.name} |
					주제: ${presentation.topic} <br/>
                  	댓글갯수: ${presentation.commentCount} |
					자료갯수: ${presentation.resourceCount} |
					뷰: ${presentation.viewCount} <br/>
					상태: ${presentation.status.descr} <br/>
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
		     발표 정보가 없습니다.
   		</c:otherwise>
   	</c:choose>
</div>
<script type="text/javascript">
$(document).ready( function(){
	$(".presentationItem").click( function() {
		s_waitblock();
		var url = '<c:url value="/study/"/>' + $(this).attr("study") + "/meeting/" + $(this).attr("meeting") + "/presentation/" + $(this).attr("presentation") + "";
		$(document).attr("location", url);
	});
});
</script>
