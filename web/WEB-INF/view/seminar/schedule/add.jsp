<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" tagdir="/WEB-INF/tags/study"%>
<page:defaultpage selected_menu="seminars">

<script type="text/javascript">
	
	$(document).ready(function(){
	    $("#startTime").timepickr({updateLive:false, handle: 'startTime', trigger:'focus'});
	    $("#endTime").timepickr({updateLive:false, handle: 'endTime', trigger:'focus'});
	    
		$("#btn_presentation_list").click( function() {
			$(document).attr("location", "../presentation/list");
		});
		$("#btn_presentation_view").click( function() {
			$(document).attr("location", "../view");
		});
		$("#btn_presentation_add").click( function() {
			$("form").submit();
		});
	});

</script>

<div id="content">
   	<div class="standard_form standard_margin">

	<div class="buttonGroup">
	<table class="titlebar">
		<tr>
           <td align="right" class="buttons">
           	  <a class="button round confirmRequired" href="#" title="보기 " id="btn_presentation_view" >VIEW</a>
           	  <a class="button round confirmRequired" href="#" title="저장 " id="btn_presentation_add" >SAVE</a>
           </td>
		</tr>
	</table>
	</div>
	
	<form:form commandName="seminarDetailSchedule" method="post">	
		
				<form:hidden path="id" />
		    	
				<p>
					<label>발표자 이름</label>
					 <form:input path="" cssClass="text" size="10"/>
				</p>
				<p>
		            <label>발표 주제 </label>
		            <form:input path="topic" cssClass="text" size="50"/><form:errors path="topic" />
		        </p>
				<p>
		            <label>발표 시간 </label>
		            <form:input id="startTime" path="startTime" cssClass="text"/><form:errors path="startTime" />
		             ~
		            <form:input id="endTime" path="endTime" cssClass="text"/><form:errors path="endTime" />
		        </p>
				<p>
		            <label>첨부자료 </label>
		             <form:input path="" cssClass="text" size="10"/>
		        </p>				
	
		</form:form>
				<!-- end  content  -->	
	</div>				
</div>
</page:defaultpage>