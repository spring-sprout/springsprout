<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="comment-area">
	<div class="post-comment-writer-info">
		<div class="thumb">
			<img class="photo fn logoSmall" height="24" width="24" src="${comment.writer.avatar}" alt="${comment.writer.name}"/>
		</div>
		<div class="post-comment-writer">
			<span class="post-comment-writer-name">${comment.writer.name}</span><br/>
			<span class="post-comment-writer-createdText">${comment.createdText}</span>
		</div>
	</div>
	<div class="post-comment-data">${comment.comment}</div>
	<div class="post-comment-actions">
		<img class="rt" src="<c:url value="/images/icon_reply.gif"/>" alt="RT ${comment.writer.name}" writer="${comment.writer.name}" class="action" title="댓글" />
		<sec:authorize ifAnyGranted="ROLE_MEMBER">
			<sec:authentication property="principal.username" var="currentUserName" scope="request"/>
			<c:if test="${currentUserName == comment.writer.email}">
			    <img id="commentDel" class="action comment_delete" src="<c:url value="/images/study/delete_smallest.png"/>" title="삭제"
			         href="/study/view/${study.id}/board/imagePost/${postId}/comment/${comment.id}"/>
			</c:if>
		</sec:authorize>
	</div>
</div>