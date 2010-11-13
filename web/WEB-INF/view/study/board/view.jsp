<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="s2c" tagdir="/WEB-INF/tags/s2c"%>
<%@ taglib prefix="s" tagdir="/WEB-INF/tags/study"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<style type="text/css">
.post-reply-count, .post-comment-count, .post-view-count {
	text-align: center;
	float: left;
	height: 45px;
	width: 38px;
	margin: 1px;
}

.post-reply-count {
	background-color: #90EE90;
}
.post-comment-count{
	background-color: #006400;
	width: 60px;
	color: white;
}

.post-comment-count .post-count {
}

.post-view-count {
	background-color: #E6E6FA;
}

.post-count {
	font-weight: bold;
	font-size: 1.5em;
}

.post-text-list {
	border-bottom: 1px dotted #999999;
	overflow:hidden;
	padding:5px;
}

.post-text-list-header {
	border-bottom: 1px solid black;
	overflow:hidden;
	padding-bottom:5px;
}
.post-button { margin: 2px 2px; }
.post-button-text { display: block; padding: 0.2em 0.5em; color: #008080; }
</style>
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

<div id="board" style="overflow: hidden;">
	<div id="typesDiv" style="width:15%; float: left;">
		<div id="allPost">All Post</div>
		<div id="textPost">Text</div>
		<div id="disPost">토론</div>
		<div id="tesPost">설문</div>
		<div id="imagePost">Image</div>
	</div>
	<div id="listDiv" class="boardList" style="width: 85%; float: left;">
		최신글 목록 표시
		${study.id }
	</div>
</div>
