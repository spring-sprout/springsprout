<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
$(document).ready(function(){


	$("#btn_presentation_update").click( function() {

		alert('개발중.'); return;
		if(confirm('Are you sure you want to update?')){
			$(document).attr("location", "../${seminar.id }/presentation/update");
		}
	});

});
</script>

<div id="content">

	<div class="buttonGroup">
	<table class="titlebar">
		<tr>
           <td align="right" class="buttons">
               <a class="button round confirmRequired" href="#" title="수정 " id="btn_presentation_update" >SAVE</a>    
           </td>
		</tr>
	</table>
	</div>
	
	
     <div id="schedules">
        <table width="800px">
            <tr class="title">
                <th width="92px">발표자</th>
                <th width="253px">발표시간</th>
                <th width="505px">발표주제</th>
                <th width="100px">발표자료</th>
            </tr>
			<c:choose>
				<c:when test="${!empty seminar.detailSchedules}">
					<c:forEach items="${seminar.detailSchedules}" var="detailSchedule" varStatus="vs">
							
			              <tr>
			                  <td>백기선</td>
			                  <td>
			                  	<fmt:formatDate value="${detailSchedule.startTime}" pattern="a h:mm"/> ~ 
			                  	<fmt:formatDate value="${detailSchedule.endTime}" pattern="a h:mm"/>
			                  </td>
			                  <td class="alignLeft">${detailSchedule.topic}</td>
			                  <td>Download</td>
			              </tr>								
							
					</c:forEach>
				</c:when>
				<c:otherwise>
				  <tr><td colspan="99">  현재 등록된 발표자가 없습니다.</td></tr>
				 </c:otherwise>
			</c:choose>
			        
        </table>
    </div>
    
</div>
