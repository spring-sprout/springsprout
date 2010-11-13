<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="s2c" tagdir="/WEB-INF/tags/s2c"%>
<%@ taglib prefix="s" tagdir="/WEB-INF/tags/study"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<page:defaultpage selected_menu="studies" banner_name="study-banner">
<s:defaultpage>
	<div>
	<h1>발표 목록</h1>
	<ul style="margin: 10px;">
	<c:forEach items="${meeting.presentations}" var="p">
		<li>제목 : ${p.title} | 등록자 : ${p.presenter.name} | 주제 : ${p.topic} | 
			<a href="${flowExecutionUrl}&_eventId=delete&presentationKey=${p.hashCode}">DELETE</a>
		</li>
	</c:forEach>
	</ul>
	</div>
	<hr class="clear"/>
	<form:form id="confirm">
		<div class="buttonGroup" align="center">
		<input type="submit" name="_eventId_submit" value="다음" class="jButton"/>
		<input type="submit" name="_eventId_new" value="새 발표 추가" class="jButton"/>
		<input type="submit" name="_eventId_cancel" value="취소" class="jButton"/>
		</div>
	</form:form>
</s:defaultpage>
</page:defaultpage>
<script type="text/javascript">
    $('.buttonGroup input').button().focusout( function() { $(this).removeClass('ui-state-focus'); });
</script>