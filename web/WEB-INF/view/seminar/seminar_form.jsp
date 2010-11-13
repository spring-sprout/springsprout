<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" tagdir="/WEB-INF/tags/study"%>
<page:defaultpage selected_menu="seminars">
<!-- Load Google map API version 2-->
<script src="http://maps.google.co.kr/maps?file=api&amp;v=2&amp;sensor=false&amp;key=ABQIAAAArT4vsI1EJr2kLc-f3wA9oBS9JFbsM2zZm8hfm-FX12j4bCsYFhTI_TcdktR_dgNl00f8bf0GseJeeA" type="text/javascript"></script>
<script type="text/javascript" src="<c:url value="/js/springMap/map.js" />"></script>
<script type="text/javascript">
  $(document).ready(function(){

	    if (GBrowserIsCompatible()) {
	        
	        springMAP = new SpringMAP({
	            renderTo : "map_canvas",
	            seletedLocationCallback : function(latlng, meetingPacleName) {
	                $('#location_latitude').val(latlng.lat());
	                $('#location_longitude').val(latlng.lng());
	                $('#location_name').val(meetingPacleName.replace(/<("[^"]*"|'[^']*'|[^'">])*>/gi, ""));
	            }
	        });

	        <c:if test="${seminar.id > 0 }">
	        // 기존에 선택된 장소에 마커를 표시한다.
	        	springMAP.setCenterMarker('${seminar.location.name}', '${seminar.location.latitude}', '${seminar.location.longitude}');
	        </c:if>
	    }

	    
    $("#seminarOpenDate").datepicker({ dateFormat: 'yy/mm/dd' });
    $("#seminarCloseDate").datepicker({ dateFormat: 'yy/mm/dd' });
    $("#entryStartDate").datepicker({ dateFormat: 'yy/mm/dd' });
    $("#entryEndDate").datepicker({ dateFormat: 'yy/mm/dd' });
    $("#seminarOpenTime").timepickr({updateLive:false, handle: 'seminarOpenTime', trigger:'focus'});
    $("#seminarCloseTime").timepickr({updateLive:false, handle: 'seminarCloseTime', trigger:'focus'});
	//Add버튼에 폼서밋 이벤트 추가하기
	$("#btn_add").click(function() {
		//$("form").attr("action","seminar/" +$("#id").val()+ "/update");
		$("form").submit();
	});
    
  });

</script>
<div id="content">
   	<div class="standard_form standard_margin">
	    <h1>세미나 정보</h1>
		<form:form commandName="seminar" method="post">
				<form:hidden path="id" />
		        <form:hidden id="location_latitude" path="location.latitude"/>
		        <form:hidden id="location_longitude" path="location.longitude"/>				
				<p>
					<label>세미나 이름</label>
					
					<form:input path="title" cssClass="text" size="50" /><form:errors path="title" />
				</p>
		        <p>
		            <label>세미나 장소 </label>
		            <div id="map_canvas" style="width: 700px; height: 400px;"></div>
		        </p>
				<p>
		            <label>세미나 장소 </label>
		            <form:input id="location_name" path="location.name" cssClass="text" size="50"/>
           		 	<form:errors path="location.name"/>
		        </p>
				<p>
		            <label>세미나 일시 </label>
		            <form:input path="seminarOpenDate" cssClass="text"/>  <form:errors path="seminarOpenDate" />
		            <form:input id="seminarOpenTime" path="seminarOpenTime" cssClass="text"/><form:errors path="seminarOpenTime" />
		             ~
		            <form:input path="seminarCloseDate" cssClass="text"/>  <form:errors path="seminarCloseDate" />
		            <form:input id="seminarCloseTime" path="seminarCloseTime" cssClass="text"/><form:errors path="seminarCloseTime" />
		        </p>
				<p>
		            <label>세미나 신청 기간 </label>
		            <form:input path="entryStartDate" cssClass="text"/>  <form:errors path="entryStartDate" /> ~
		            <form:input path="entryEndDate" cssClass="text"/>  <form:errors path="entryEndDate" />
		        </p>		        

				<p >
		            <label>참가비 </label>
		            <form:input path="entryFee" cssClass="text"/> <form:errors path="entryFee" />원
		        </p>

		        <p>
		            <label>기타사항</label>
		            <form:textarea id="description" tabindex="1" accesskey="u" path="description" rows="2" cols="80" cssStyle="margin-top: 10px; border:1px solid #AAAAAA; padding:4px 2px;" cssClass="text"/>
			   		<form:errors path="description" />
		        </p>
		        
		         <br/><hr/><br/>
	
				<div class="buttonGroup">
				<table class="titlebar">
					<tr>
			           <td align="right" class="buttons">
		                   
		                <a class="button round" href="<c:url value="/seminar/index"/>" title="취소">Back</a>
		                 <a class="button round confirmRequired" href="#" title="저장 " id="btn_add" >SAVE</a>
		            </td>
					</tr>
				</table>
				</div>
		</form:form>
	</div>
</div>
</page:defaultpage>