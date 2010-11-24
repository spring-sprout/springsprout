<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="s2c" tagdir="/WEB-INF/tags/s2c"%>
<%@ taglib prefix="s" tagdir="/WEB-INF/tags/study"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link href="<c:url value="/css/study/post.css"/>" media="screen" rel="stylesheet" type="text/css" />

<style type="text/css">
#bottomFull {
	width: 99%;
}
</style>

<h2>Post Summary</h2>

<s2c:left-column>
    <s2c:module name="Text" more="더보기">
        <ul class="item-details">
            <li>
                <dl>
                    <dt>리더:</dt>
                    <dd>${study.manager.name}</dd>
                </dl>
                <dl>
                    <dt>모임수:</dt>
                    <dd>${study.meetingCount}</dd>
                </dl>
                <dl>
                    <dt>참석인원:</dt>
                    <dd>${study.memberCount}/${study.maximumCount}</dd>
                </dl>
                <dl>
                    <dt>시작일:</dt>
                    <dd><s:date value="${study.startDay}"/></dd>
                </dl>
                <dl>
                    <dt>종료일:</dt>
                    <dd><s:date value="${study.endDay}"/></dd>
                </dl>
            </li>
        </ul>
    </s2c:module>
    
</s2c:left-column>
<s2c:right-column>
    <s2c:module name="Poll" more="더보기">
        <s:nl2br value="${study.descr}"/>    
    </s2c:module>
    <s2c:module name="토론" more="더보기">

    </s2c:module>
</s2c:right-column>
<s2c:bottom-column>
	<s2c:module name="Image" more="더보기">
	
	</s2c:module>
</s2c:bottom-column>
<script type="text/javascript" src="<c:url value="/resources/js/plugin/jqueryTools/jquery.tools.min.js"/>"></script>
<script type="text/javascript">
$(function(){ 
	$('#textPost').click( function( event){
		$('#listDiv').load('${study.id}/board/textPost/list/0');			
	});
	$('#allPost').click( function( event){
		$('#listDiv').load('/study/${study.id}/board/allPost');
	});
	$('#imagePost').click( function( event){
		$('#listDiv').load('${study.id}/board/imagePost/list/0');
	});
});
</script>