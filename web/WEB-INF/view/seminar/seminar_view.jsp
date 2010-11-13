<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<page:defaultpage selected_menu="seminars">
<script src="http://maps.google.co.kr/maps?file=api&amp;v=2&amp;sensor=false&amp;key=ABQIAAAArT4vsI1EJr2kLc-f3wA9oBS9JFbsM2zZm8hfm-FX12j4bCsYFhTI_TcdktR_dgNl00f8bf0GseJeeA" type="text/javascript"></script>
<script type="text/javascript" src="<c:url value="/js/springMap/map.js" />"></script>
<script type="text/javascript">
$(document).ready(function(){

	 if (GBrowserIsCompatible()) { 
			 springMAP = new SpringMAP({
	            renderTo : "map_canvas"
	        });

	        springMAP.setCenterMarker("${seminar.location.name}", "${seminar.location.latitude}", "${seminar.location.longitude}");
	  }

	$("#btn_add").click(function() {
		//$("form").attr("action","seminar/" +$("#id").val()+ "/update");
		$("form").submit();
	});
	$("#btn_seminar_update").click( function() {
		if(confirm('Are you sure you want to update?')){
			$(document).attr("location", "../${seminar.id }/update");
		}
	});
	$("#btn_seminar_remove").click( function() {
		if(confirm('Are you sure you want to remove?')){
			$(document).attr("location", "../${seminar.id }/delete");
		}
	});
	$("#btn_schedules").click( function() {
			$(document).attr("location", "../${seminar.id}/schedule/add");
	});
	$("#btn_enrollment_join").click(function() { alert('개발중'); return;
		$(document).attr("location", "../${seminar.id}/enrollment/join");
	});
	$("#btn_enrollment_cancle").click(function() {alert('개발중'); return;
		$(document).attr("location", "../${seminar.id}/schedule/cancle");
	});		
});
</script>
<div id="content">

	<div class="buttonGroup">
	<table class="titlebar">
		<tr>
           <td align="right" class="buttons">
				<a class="button round" href="#" title="삭제 " id="btn_seminar_remove">Delete</a>
				<a class="button round" href="#" title="수정 " id="btn_seminar_update">Edit</a>
				<a class="button round" href="<c:url value="/seminar/index"/>" title="목록">List</a>
           </td>
		</tr>
	</table>
	</div>
	

	<div id="title">
        <h2>${seminar.title}</h2>
		<!--  presentation  -->
		<jsp:include page="/WEB-INF/view/seminar/seminar_tabs.jsp" />
    </div>
    
    
<div id="DivSchedule" >    
 
    <div id="seminar_content">
        <div id="base">
            <div id="base_info">
                <p>
                    <span>행사기간</span><br />
                    <fmt:formatDate value="${seminar.seminarOpenDate}" pattern="yyyy년 MM월 dd일"/>
                    <fmt:formatDate value="${seminar.seminarOpenTime}" pattern="a h:mm"/> ~ 
                    <fmt:formatDate value="${seminar.seminarCloseDate}" pattern="yyyy년 MM월 dd일"/>
                    <fmt:formatDate value="${seminar.seminarCloseTime}" pattern="a h:mm"/>
                </p>
               <br />
               <p>
                   <span>신청기간</span><br />
                    <fmt:formatDate value="${seminar.entryStartDate}" pattern="yyyy년 MM월 dd일"/>
                    <fmt:formatDate value="${seminar.entryEndDate}" pattern="yyyy년 MM월 dd일"/>
               </p>
               <br />
                <c:if test="${seminar.entryFee > 0}">
               <p>
                   <span>참가비</span><br />
                    ${seminar.entryFee}
               </p>
                <br />
                </c:if>
               <p>
                   <span>문의</span><br />
                    ${seminar.creator.name}<br />
                    ${seminar.creator.email}
               </p>
           </div>
            <div id="base_detail">
                ${seminar.description}
            </div>
        </div>
        <div class="line_width"></div>
		<div class="buttonGroup">
			<table class="titlebar">
				<tr>
		           <td align="right" class="buttons">
						<a class="button round" href="#" title="스케줄 " id="btn_schedules">schedules</a>
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
        <div class="line_width"></div>
        
        <div id="location">
            <div class="fLeft"><span>장소</span><br />
                ${seminar.location.name } <br />  ${seminar.location.descr }
            </div>
            <div id="map_canvas" style="width:500px;height:400px;">지도</div>
            
        </div>
    </div>
    <div id="button">
        <div class="boxButton fLeft"><a class="boxButton" href="#" title="등록 " id="btn_enrollment_join">등록</a></div>
        <div class="boxButton fRight"><a class="boxButton" href="#" title="취소 " id="btn_enrollment_cancle">취소</a></div>
    </div>
</div>   

<div id="DivEnrollment" style="display:none;">

참가자 목록

</div>

<div id="DivComment" style="display:none;">

덧글 목록 

</div>	

    
</div>

</page:defaultpage>