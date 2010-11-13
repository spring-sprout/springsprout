<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<br />
<script type="text/javascript">
$(document).ready(function(){


});


function showSeminar()
 {
  $("#DivSchedule").css("display", "block");
  $("#DivEnrollment").css("display", "none");
  $("#DivComment").css("display", "none");

  $("#TabSeminar").addClass("tab_active");
  $("#TabEnrollment").removeClass("tab_active");
  $("#TabComment").removeClass("tab_active");
 }
function showEnrollment()
 {
  $("#DivSchedule").css("display", "none");
  $("#DivEnrollment").css("display", "block");
  $("#DivComment").css("display", "none");

  $("#TabSeminar").removeClass("tab_active");
  $("#TabEnrollment").addClass("tab_active");
  $("#TabComment").removeClass("tab_active");
 }
function showComment()
 {
  $("#DivSchedule").css("display", "none");
  $("#DivEnrollment").css("display", "none");
  $("#DivComment").css("display", "block");

  $("#TabSeminar").removeClass("tab_active");
  $("#TabEnrollment").removeClass("tab_active");
  $("#TabComment").addClass("tab_active");
  
 }
</script>

<div id="tab">
    <div id='TabSeminar' 	class="tab_active" >	<a href="#none" onclick="showSeminar(); 	return false;" 	>		개요</a></div>
    <div id='TabEnrollment' class="">				<a href="#none" onclick="showEnrollment(); 	return false;" 	>	참석자 목록</a></div>
    <div id='TabComment' 	class="">				<a href="#none" onclick="showComment(); 	return false;" 	>		댓글</a></div>
</div>
