<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<page:popuppage>
	<form:form id="resourceForm" commandName="resource" method="post" action="/study/${studyId}/meeting/${meetingId}/presentation/${presentationId}/resource/url/add">
	<p>
		<label>참고자료 타입</label>
		<form:select id="resourceType" path="resourceType" items="${resourceTypes}" itemLabel="descr" itemValue="value" />
	</p>
	<p id="10">
		<label>링크: </label>
		<form:input id="link" path="url" cssClass="text required url" size="30" />
	</p>
	<p id="20">
		<label>파일: </label>
		<input type="file" name="uploadingFile" />
	</p>
	<p>
		<label>설명: </label>
		<form:input path="title" cssClass="text required" size="30" />
	</p>
	<input id="submit" type="submit" value="추가" />
	</form:form>
</page:popuppage>

<script type="text/javascript">
$(document).ready( function(){
	$("form").validate();
	
	$("#20").hide();

    $("#resourceType").change(function () {
		var str = $("select option:selected").attr("value");
		if(str === '20'){
			$("#10").hide();
			$("#20").show();
			$("form").attr("enctype", "multipart/form-data");
			$("form").attr("action", '<c:url value="/study/${studyId}/meeting/${meetingId}/presentation/${presentationId}/resource/file/add"/>');
			$("#link").removeClass("required");
			$("#file").addClass("required");
		} else {
			$("#10").show();
			$("#20").hide();
			$("form").removeAttr("enctype");
			$("form").attr("action", '<c:url value="/study/${studyId}/meeting/${meetingId}/presentation/${presentationId}/resource/url/add"/>');
			$("#link").addClass("required");
			$("#file").removeClass("required");
		}
    }).change();

});
</script>

<style>
    * { margin: 0; padding: 0;}
    body { height: 100px;}
    p {text-align: left; margin: 10px;}
    select {width: 60px; height: 1.5em;}
    input[id='title'], input[id='link'] {height: 1.5em;}
    label {vertical-align: middle;}
</style>