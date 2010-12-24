<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="s2c" tagdir="/WEB-INF/tags/s2c"%>
<%@ taglib prefix="s" tagdir="/WEB-INF/tags/study"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style type="text/css">
.post-list-actions { float: right; padding: 10px 5px 0px 0px;}
img:HOVER { cursor: pointer; }
.post-image-detail-image {
	max-width: 70%;
	margin: 10px;
	border: solid 1px gray;
}
.post-image-thumbnail img { margin: 5px;}
.post-image-thumbnail-default {
	opacity : 0.67;
	opacity : 1;
	border: solid 1px OliveDrab;
}

.post-image-thumbnail-selected {
	opacity : 1;
	border: solid 2px green;
	width: 50%;
}
.post-image-container .images { overflow: hidden;}

.detail {
	float: left;
	width: 76%;
}
.list {
	float: left;
	width: 24%;
}

.details {
	position:absolute;
	top:15px;
	right:15px;
	font-size:11px;
	color:#fff;
	width:15%;
	text-align: left;
}

.details h3 {
	color:#aba;
	font-size:15px;
}
.detail-writer { text-align: right; }

.comment-form { 
	overflow: hidden;
	padding: 4px;
}
.comment-list { border-top: solid gray 2px; }
input.comment-submit { height: 55px; float: left; width: 9%; margin-left: 0.5em;}
#movePrev:HOVER, #moveNext:HOVER { cursor: pointer; }
.icon-arrow { width: 70px; }
.icon-arrow:HOVER { width: 75px; }
.icon-left { float:left; }
.icon-right { float:right; }

.mod-content { padding: 10px; }
.mod-header { margin: 5px;}

</style>
<div class="post-list-actions">
	<sec:authorize ifAnyGranted="ROLE_MEMBER">
		<button id="writeBtn" class="post-button">글쓰기</button>
	</sec:authorize>
	<button id="moveToListBtn" class="post-button">목록으로</button>
</div>
<s2c:module name="List of Image Post">
	<s2c:portlet target="${posts}">
		<div class="post-image-container">
			<div class="images">
				<div class="detail">
					<c:forEach items="${posts}" var="post" varStatus="status">
					<div class="post-image post-image-detailImage" align="center" id="post-${post.id}">
						<img src="/images/study/monotone_arrow_left.png" class="icon-arrow icon-left" />
						<img class="post-image-detail-image" src="/images/userimage/${post.writer.email}/${post.imageFile.savedFileName}" alt="image${status.count }" rel="#${post.id}"/>
						<img src="/images/study/monotone_arrow_right.png" class="icon-arrow icon-right"/>
						<div class="simple_overlay" id="${post.id}">
							<!-- large image -->
							<div style="width: 80%;">
								<img style="max-width: 100%;" src="/images/userimage/${post.writer.email}/${post.imageFile.savedFileName}" />
							</div>
							<!-- image details -->
							<div class="details">
                                <sec:authorize ifAnyGranted="ROLE_MEMBER">
                                    <sec:authentication property="principal.username" var="currentUserName" scope="request"/>
                                    <c:if test="${currentUserName == post.writer.email}">
                                        <button class="updateBtn" id="${post.id}">수정</button>
                                        <button class="deleteBtn" id="${post.id}">삭제</button>
                                    </c:if>
                                </sec:authorize>
								<h3 class="detail-title">${post.title}</h3>
								<h4 class="detail-writer">${post.writer.name}</h4>
								<p class="detail-content">${post.content}</p>
							</div>
						</div>
					<div class="contents" id="content-${post.id}" align="left">
						<div class="content">
							${post.content}
						</div>
						<div class="comment-list">
							<h3>Your Comment↓</h3>
							<div id="commentFormDiv" class="comment-form">
								<form:form id="commentForm" action="/study/${study.id}/post/imagePost/${post.id}/comment/write" cssClass="commentForm" commandName="comment" method="post">
									<form:textarea path="comment" cssClass="comment" />
									<input type="submit" class="comment-submit" value="보내기"/>
								</form:form>
							</div>
							<c:forEach items="${post.comments}" var="comment">
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
		                                       href="/study/${study.id}/post/imagePost/${post.id}/comment/${comment.id}"/>
		                              </c:if>
								</sec:authorize>
							</div>
							</div>
							</c:forEach>
						</div>
					</div>
					</div>
					</c:forEach>
				</div>
				<div class="list" align="center">
					<span id="movePrev" class="ui-icon ui-icon-triangle-1-n">${page - 1}</span>
					<div class="post-image post-image-thumbnail-list">
						<c:forEach items="${posts}" var="post" varStatus="status">
						<div class="post-image post-image-thumbnail" rel="${post.id}">
							<img class="post-image-thumbnail-default" src="/images/userimage/${post.writer.email}/${post.imageFile.thumbNailName}" alt="${post.title}" title="${post.title}" />
						</div>
						</c:forEach>
					</div>
					<span id="moveNext" class="ui-icon ui-icon-triangle-1-s">${page + 1}</span>
				</div>
			</div>
		</div>
	</s2c:portlet>
</s2c:module>
<script type="text/javascript">
var $postForm = $('#postForm'), $commentForm = $('.commentForm'), $actionArea = $('.active-area'), selectedPostId;
$(function(){ 
	initEvent();
	initCommentForm();
	
	$('img[rel]').overlay();
	$('.post-comment-actions .rt').live( 'click', function() {
		$('textarea.comment').val("@" + $(this).attr('writer') + ' ').focus();
	});
	
	$('button').button().focusout( function() { $(this).removeClass('ui-state-focus'); })
		.children().addClass('post-button-text').removeClass('ui-button-text');
	$('#movePrev').focusin().addClass('ui-stage-hover');
});

function initEvent() {
	$('.post-image-detailImage:not(:first)').hide();
	selectedPostId = $('.post-image-thumbnail').first().attr('rel');
	$('.post-image-thumbnail :first').addClass('post-image-thumbnail-selected');
	$('#writeBtn').click( function(e){ 
		changePageToForm();
	});
	$('#moveToListBtn').click( function(e){
		$actionArea.load('${study.id}/post');
	});
	$('.post-image-thumbnail').click( function(){
		var $this = $(this), postId = '#post-' + $this.attr('rel'), 
			$imgEl = $this.children().first();
		$('.post-image-detailImage:not(:hidden)').hide('drop', 300, function(){
			$(postId).show('drop', 300, function() {
				$('.post-image-thumbnail img').removeClass('post-image-thumbnail-selected');
				$imgEl.addClass('post-image-thumbnail-selected');
				content = $(this).find('.detail-content').text();
				$('.content').text(content);
			});
		});
	}); 
	$('#movePrev, #moveNext').click( function(){
		Study.Post.blockUIAjaxReq( '${study.id}/post/imagePost/list/' + $(this).text(), $actionArea);
	});
	$('.action.comment_delete').live( 'click', function() {
        var $this = $(this);
        if( confirm( '삭제 하시겠습니까?')) {
			$.post( $this.attr('href'), {_method:'DELETE'}, function(data) {
				$this.parent().parent().hide( 'blind', null, 1000).remove();
				$.growlUI('Notification', '댓글을 삭제 하였습니다.');
			}, 'json');
        }
        return false;
    });
	$('.deleteBtn').live( 'click', function(){
		var $this = $(this);
        if( confirm( '삭제 하시겠습니까?')) {
        	$.ajax({
        		url : '${study.id}/post/imagePost/' + $this.attr('id'),
        		data : {_method: 'DELETE'},
        		type : 'POST',
        		beforeSend : function(){
        			s_waitblock();
        		},success : function(html){
        			Study.Post.blockUIAjaxReq( '${study.id}/post/imagePost/list/0', $actionArea);
        		}
        	});
        }
        return false;
	});
	$('.updateBtn').live( 'click', function() {
		$.get( '${study.id}/post/imagePost/' + $(this).attr('id'), function(html){
			$actionArea.html(html);	
		});
		return false;
	});
	$('.icon-left').click( function(){
		var $prevEl = $('.post-image-thumbnail-selected').parent().prev();
		navigateImage( $prevEl);
		return false;
	});
	$('.icon-right').click( function(){
		var $nextEl = $('.post-image-thumbnail-selected').parent().next();
		navigateImage( $nextEl);
		return false;
	});
}

function initCommentForm() {
	var commentOptions = {
		beforeSubmit: function(arr, $form, options) { 
			if ($form.find('textarea').val().trim().length <= 0) {
				alert('내용을 입력하세요.');
				$form.find('textarea').focus();
				return false;
			}
			$form.find('input.comment-submit').attr('disabled', 'disabled');
		}, success: function(comment, statusText, xhr, $form) {
        	var length = $form.parent().parent().find('.comment-area').length;
			if ( length >= 1) $('.comment-area:visible:first').before(comment);
			else $form.parent().parent().append(comment);
			$.growlUI('Notification', '댓글을 추가 하였습니다.');
			$form.find('input.comment-submit').attr('disabled', '');
		},  
        clearForm: true
	};
	$commentForm.submit(function() { 
		$(this).ajaxSubmit(commentOptions); 
		return false;
	});
}

function changePageToForm() {
	Study.Post.blockUIAjaxReq( '${study.id}/post/imagePost', $actionArea);
}

function navigateImage( $targetEl) {
	if ($targetEl.length < 1) {
		alert('이동할 이미지가 없습니다.');
		return;
	}
	postId = '#post-' + $targetEl.attr('rel');
	$('.post-image-detailImage:not(:hidden)').hide('drop', 300, function(){
		$(postId).show('drop', 300, function() {
			$('.post-image-thumbnail img').removeClass('post-image-thumbnail-selected');
			$targetEl.find('img').addClass('post-image-thumbnail-selected');
			content = $(this).find('.detail-content').text();
			$('.content').text(content);
		});
	});
}

var Study = {};
Study.Post = {};
Study.Post.blockUIAjaxReq = function( url, $target) {
	$.ajax({
		url : url,
		beforeSend : function(){
			s_waitblock();
		},success : function(html){
			$target.html(html);
			$.unblockUI();
		}
	});
};

</script>