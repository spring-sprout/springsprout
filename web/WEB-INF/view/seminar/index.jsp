<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="utils" tagdir="/WEB-INF/tags/utils"%>
<page:defaultpage selected_menu="seminars" banner_name="main-banner">
<script type="text/javascript">

//Add버튼에 폼서밋 이벤트 추가하기
$("#btn_new").click(function() {
	//$("form").attr("action","seminar/" +$("#id").val()+ "/update");
	$("form").attr("action","/seminar/add");
	$("form").submit();
});

</script>
<div id="content">
		<c:if test="${canSeminarManage}">

		</c:if>
		<div class="buttonGroup">
		<table class="titlebar">
			<tr>
	           <td align="right" class="buttons">
                <a class="button round confirmRequired" href="<c:url value="/seminar/add"/>" title="등록 ">NEW</a>    
            </td>
			</tr>
		</table>
		</div>
		
		<br />
		
		<ul id="minitabs">
			<li><a href="<c:url value="/seminar/index"/>" class="${minitab_active}">진행중인 세미나</a></li>
			<li><a href="<c:url value="/seminar/past"/>" class="${minitab_past}">지난 세미나 </a></li>
		</ul>
		<div style="clear:both;"></div>
		

	
		<c:choose>
			<c:when test="${!empty list}">
				<c:forEach items="${list}" var="seminar" varStatus="vs">
				
				<li class="round ${seminar.status} ">
					<div class="count">
						${vs.count}
					</div>
					<div >
						<strong><a class="s_waitblock" href="<c:url value="/seminar/${seminar.id}/view" />">${seminar.title}</a></strong>
					</div>
					<div class="description">
						<strong>${seminar.location}</strong>
					</div>

					<div class="detail round">
						관리자: ${seminar.maximum} | 상태: ${seminar.status} | 참가비 : ${seminar.entryFee}원
						제한인원:${seminar.maximum} |  현재인원: ${seminar.totalActiveComer} <br/>
						<fmt:formatDate value="${seminar.seminarOpenDate}" pattern="yyyy년 M월 d일" /> 부터 ~
						<fmt:formatDate value="${seminar.seminarCloseDate}" pattern="yyyy년 M월 d일" /> 까지
					</div>
					<br class="clear"/>
				</li>
				</c:forEach>
			</c:when>
			<c:otherwise>
			  <tr><td colspan="99">  현재 등록된 세미나가 없습니다.</td></tr>
			 </c:otherwise>
		</c:choose>
	</div>

</page:defaultpage>