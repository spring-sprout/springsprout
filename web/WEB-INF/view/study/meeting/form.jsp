<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<page:defaultpage selected_menu="studies">
<!-- Load Google map API version 2-->
<script src="http://maps.google.co.kr/maps?file=api&amp;v=2&amp;sensor=false&amp;key=ABQIAAAArT4vsI1EJr2kLc-f3wA9oBS9JFbsM2zZm8hfm-FX12j4bCsYFhTI_TcdktR_dgNl00f8bf0GseJeeA" type="text/javascript"></script>
<script type="text/javascript" src="<c:url value="/js/springMap/map.js" />"></script>
<style type="text/css">
em.error, span.error { display: inline; }
</style>
<div id="content">
    <div class="standard_form standard_margin">
       	스터디 이름 : <a class="s_waitblock" href="<c:url value="/study/view/${meeting.study.id}"/>">${studyName}</a>
        <c:if test="${ action == '수정' }">
			<h1> > [${meeting.title}] 모임 수정</h1>
		</c:if>
       	<c:if test="${ action == '추가' }">
       		<h1> > 스터디 모임 추가</h1>
       	</c:if>
       	<hr class="clear" />
        <form:form commandName="meeting" method="post" id="meetingForm">
        	<h2 class="summary"></h2>
	        <p>
	            <label>모임주제</label>
	            <form:input path="title" cssClass="text" cssStyle="width: 400px;"/>
	            <form:errors path="title" />
	        </p>
	        <p>
	            <label>주소</label>
	            <form:input id="location_name" path="location.name" cssClass="text" cssStyle="width: 400px;"/>
	            <form:errors path="location.name" cssClass="error"/>
	        </p>
	        <div id="map_canvas" style="width: 700px; height: 400px;border: 1px solid gray;"></div>
	        <p>
	            <label>모임시작일시</label>
	            <form:input path="openDate" cssClass="text"/>
	            <form:errors path="openDate" cssClass="error"/>
	            <form:input path="openTime" cssClass="text"/>
	            <form:errors path="openTime" cssClass="error"/>
	        </p>
	        <p>
	            <label>모임종료일시</label>
	            <form:input path="closeDate" cssClass="text"/>
	            <form:errors path="closeDate" cssClass="error"/>
	            <form:input path="closeTime" cssClass="text"/>
	            <form:errors path="closeTime" cssClass="error"/>
	        </p>
	        <p>
	            <label>제한인원</label>
	            <form:input path="maximum" cssClass="text"/>
	            <form:errors path="maximum" cssClass="error"/>
	        </p>
	        <p>
	            <label>모임내용</label>
	            <form:textarea path="contents" rows="4" cols="100" cssClass="text"/>
	            <form:errors path="contents" cssClass="error"/>
	        </p>
	        <p>
	            <label style="display: inline;">수정 내역을 통지할까요?</label>
	            <input type="checkbox" name="isGoingToBeNotified" />
	        </p>
	        <br/><hr/>
	        <div class="buttonGroup" style="margin-top: 20px;" align="center">
	        	<c:if test="${ action == '수정' }">
		            <input type="submit" value="저장" />
		            <a href="<c:url value="/study/${study.id}/meeting/${meetingId}"/>">
		            	<input type="button" value="취소" />
		            </a>
				</c:if>
	        	<c:if test="${ action == '추가' }">
	        		<input type="submit" name="_eventId_proceed" value="다음" class="jButton"/>
					<input type="submit" name="_eventId_submit" value="저장" class="jButton"/>
					<input type="submit" name="_eventId_cancel" value="취소" class="jButton"/>
	        	</c:if>
	        <form:hidden id="location_latitude" path="location.latitude"/>
	        <form:hidden id="location_longitude" path="location.longitude"/>
	        </div>
        </form:form>
    </div>
</div>
<script type="text/javascript">
$( function(){
    $("#openDate").datepicker({ dateFormat: 'yy/mm/dd' });
    $("#closeDate").datepicker({ dateFormat: 'yy/mm/dd' });
    $("#openTime").timepickr({updateLive:false, handle: 'openTime', trigger:'focus'});
    $("#closeTime").timepickr({updateLive:false, handle: 'closeTime', trigger:'focus'});

    if (GBrowserIsCompatible()) {
        springMAP = new SpringMAP({
            renderTo : "map_canvas",
            seletedLocationCallback : function(latlng, meetingPacleName) {
                $('#location_latitude').val(latlng.lat());
                $('#location_longitude').val(latlng.lng());
                $('#location_name').val(meetingPacleName.replace(/<("[^"]*"|'[^']*'|[^'">])*>/gi, ""));
            }
        });
        // 기존에 선택된 장소에 마커를 표시한다.
        <c:if test="${ action == '수정' }">
        springMAP.setCenterMarker("${meeting.location.name}", ${meeting.location.latitude}, ${meeting.location.longitude});
        </c:if>
    }
});

	var validator = $('#meetingForm').bind('invalid-form.validate', function() {
		validationUtil.setWarnSummary($(this).find('.summary'), validator.numberOfInvalids());
	}).validate({
		errorElement: 'em',
		errorContainer: $('.summary'),
		success: function(label) {
			label.addClass('success');
		},
		rules: {
			title: 'required',
			openDate: 'required',
			openTime: 'required',
			closeDate: 'required',
			closeTime: 'required',
			username: {
				required: true,
				minlength: 2,
				maxlength: 10
			}
		}
	});
	$('#contents').wysiwyg();
	$('.buttonGroup input').button().focusout( function() { $(this).removeClass('ui-state-focus'); });
</script>
</page:defaultpage>
