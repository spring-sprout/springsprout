<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="s2c" tagdir="/WEB-INF/tags/s2c"%>
<%@ taglib prefix="s" tagdir="/WEB-INF/tags/study"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<style type="text/css">
#reply-header {
	border-bottom: 1px solid black;
	overflow:hidden;
	padding-bottom:5px;
}
.reply-comment-list {
	padding-left: 2%;
}
.comment-list {
	padding-left: 5%;
}
.comment-header { 
	border-bottom: 1px solid black;
	overflow:hidden;
}
.comment-area {
	border-bottom: 1px dotted black;
	padding: 5px;
	overflow: hidden;	
}

.comment {
	width: 89%;
	height: 50px;
	border: 2px solid green;
}

.reply-comment-form { 
	overflow: hidden;
	display: none;
	padding: 4px;
}
.post-root { 
	border-bottom: 2px solid #006400;
	padding: 10px 0;
}
.post-comment-writer-info { float:left; width: 15%; }
.post-comment-writer {
	float: left;
	font-size: 0.8em;
	padding-left: 5px;
}
.post-comment-data { float:left; padding-left: 3px;}
.post-comment-actions { float:right; visibility: hidden;}
.comment-area:HOVER { background-color:#E0F8F7; }
.comment-area:HOVER .post-comment-actions { visibility:visible;}
.post-comment-writer-name { font-weight:bold;}
.rt:HOVER { cursor: pointer; }
span#at { color:#2276BB; }
.post-text-root, .post-text-reply { overflow: hidden; }
.post-text-root-writer-info, .post-text-reply-writer-info { float: right; border: solid 1px #1E90FF; width: 200px; }
.post-text-reply-writer-info { font-size: 0.9em; }
.post-reply-list { padding-top: 10px;}
.replyPost{ padding: 10px; margin: 5px; border: dashed 1px #6B8E23; }
.replyPost:HOVER{ border: solid 1px #6B8E23;}
.reply-content, .text-post-content {
	border:1px solid gray;
	margin:2px 0;
	text-indent:0.8em;
}
.reply-title { font-weight: bold;}
.post-commentSubmitBtn { height: 60px; float: right; width: 50px; }
</style>
<div id="textPost-detail">
	<div class="post-root">
	<div id="post-list-header" class="post-text-list-header">
		<h2 style="float: left;">${textPost.title }</h2>
		<div style="float: right;">
			<sec:authorize ifAnyGranted="ROLE_MEMBER">
				<button class="writeBtn">답글</button>
				<sec:authentication property="principal.username" var="currentUserName" scope="request"/>
				<c:if test="${currentUserName == textPost.writer.email}">
				<button class="updateBtn" id="${textPost.id}">수정</button>
				</c:if>
			</sec:authorize>
			<button id="moveListBtn" class="listBtn">목록으로</button>
		</div>
	</div>
	<div id="rootPost" class="post-text-root">
		<div class="text-post-content wysiwyg">
			${textPost.content }
		</div>
		<div>
			<div class="post-text-root-writer-info">
				<img class="photo fn logoSmall" height="48" width="48" src="${textPost.writer.avatar}" alt="${textPost.writer.name}"/>
				<strong>${textPost.writer.name}</strong><br/>
				${textPost.createdAt}
			</div>
		</div>
	</div>
	<div id="reply-comment-list">
		<div id="comment-header" class="comment-header">
			<h4 style="float: left;"><span id="rootTextPost-CommentCount">${textPost.commentCount}</span> Comments</h4>
			<div style="float: right;">
				<sec:authorize ifAnyGranted="ROLE_MEMBER">
				<button class="writeReplyBtn post-button">댓글 작성</button>
				</sec:authorize>
			</div>
		</div>
		<div class="comment-list">
			<div id="commentFormDiv" class="reply-comment-form">
				<form:form id="commentForm" cssClass="commentForm" commandName="comment" action="/study/view/${study.id}/board/textPost/${textPost.id}/comment/write" method="post">
					<form:textarea path="comment" cssClass="comment root" cssStyle="float: left;"/>
					<input type="submit" class="post-commentSubmitBtn" value="보내기" />
				</form:form>
			</div>
			<c:forEach items="${textPost.comments}" var="comment">
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
                                       href="/study/view/${study.id}/board/textPost/${textPost.id}/comment/${comment.id}/remove"/>
                              </c:if>
						</sec:authorize>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<!-- 답글 목록 시작 -->
	</div>
	<div id="replyList" class="post-reply-list">
		<div id="reply-header">
			<h3>${textPost.branchCount} Replys</h3>
		</div>
		<c:forEach items="${textPost.branchPosts}" var="reply">
		<div class="replyPost">
			<div class="reply-data post-text-reply">
				<div class="reply-title">${reply.title}</div>
				<div class="reply-content">${reply.content}</div>
				<div>
					<div class="post-text-reply-writer-info">
						<img class="photo fn logoSmall" height="36" width="36" src="${reply.writer.avatar}" alt="${reply.writer.name}"/>
						<strong>${reply.writer.name}</strong><br/>
						${reply.createdAt}
					</div>
				</div>
			</div>
			<div class="reply-comment-list">
			<div id="comment-header" class="comment-header">
				<h5 style="float: left;"><span class="comment-count">${reply.commentCount}</span> Comments</h5>
				<div style="float: right;">
					<sec:authorize ifAnyGranted="ROLE_MEMBER">
						<sec:authentication property="principal.username" var="currentUserName" scope="request"/>
						<c:if test="${currentUserName == reply.writer.email}">
							<button class="updateReplyBtn post-button" id="${reply.id}">수정</button>
						</c:if>
						<button class="writeReplyBtn post-button">댓글 작성</button>
					</sec:authorize>
				</div>
			</div>
			<div class="comment-list">
				<div id="commentFormDiv" class="reply-comment-form">
					<form:form cssClass="commentForm" commandName="comment" action="/study/view/${study.id}/board/textPost/${reply.id}/comment/write" method="post">
						<form:textarea path="comment" cssClass="comment root" cssStyle="float: left;"/>
						<input type="submit" class="post-commentSubmitBtn" value="보내기" />
					</form:form>
				</div>
				<c:forEach items="${reply.comments}" var="comment">
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
                                	href="/study/view/${study.id}/board/textPost/${reply.id}/comment/${comment.id}/remove"/>
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
</div>
<!-- 작성 및 수정에 필요 한 HTML 조각 들  -->
<div id="replyDiv" title="답글 - 일반 게시물 생성">
	<form:form id="replyForm" modelAttribute="branchPost" method="post" action="/study/view/${study.id}/board/textPost/write">
		<form:hidden path="rootPost.id"/>
		<form:hidden path="rootStudy.id"/>
		<p>
			<form:label path="title">제목 : </form:label>
			<form:input path="title" cssStyle="width: 90%;" />
		</p>
		<p>
			<form:label path="content">내용</form:label><br/>
			<form:textarea path="content" id="postContent" rows="8" cols="100" />
		</p>
	</form:form>
</div>
<div id="updateDiv" title="수정">
	<form:form id="updateForm" modelAttribute="updatePost" method="PUT" action="/study/view/${study.id}/board/textPost/">
		<form:hidden path="rootPost.id"/>
		<form:hidden path="rootStudy.id"/>
		<p>
			<form:label path="title">제목 : </form:label>
			<form:input path="title" cssStyle="width: 90%;" />
		</p>
		<p>
			<form:label path="content">내용</form:label><br/>
			<form:textarea path="content" id="postContent" rows="8" cols="100" cssStyle="width: 100%;"/>
		</p>
	</form:form>
</div>
<div id="replyAddSpot" style="display:none;">
	<div class="replyPost">
		<div class="reply-data post-text-reply">
			<div class="reply-title"></div>
			<div class="reply-content"></div>
			<div>
				<div class="post-comment-writer-info">
					<div class="thumb">
						<img class="photo fn logoSmall" height="24" width="24" src="" alt=""/>
					</div>
					<div class="post-comment-writer">
						<span class="post-comment-writer-name"></span><br/>
						<span class="post-comment-writer-createdText"></span>
					</div>
				</div>
			</div>
		</div>
		<div class="reply-comment-list">
			<div class="comment-header">
				<h5 style="float: left;"><span class="comment-count"></span> Comments</h5>
				<div style="float: right;">
					<button class="updateReplyBtn post-button" id="${reply.id}">수정</button>
					<button class="writeReplyBtn">댓글 작성</button>
				</div>
			</div>
			<div class="comment-list">
				<div class="reply-comment-form">
					<form:form cssClass="commentForm" commandName="comment" action="" method="post">
						<form:textarea path="comment" cssClass="comment root" cssStyle="float: left;"/>
						<input type="submit" style="float: right;" />
					</form:form>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="commentAddSpot" style="display: none;">
	<div class="comment-area">
		<div class="post-comment-writer-info">
			<div class="thumb">
				<img class="photo fn logoSmall" height="24" width="24" src="" alt=""/>
			</div>
			<div class="post-comment-writer">
				<span class="post-comment-writer-name"></span><br/>
				<span class="post-comment-writer-createdText"></span>
			</div>
		</div>
		<div class="post-comment-data"></div>
		<div class="post-comment-actions">
			<img class="rt" src="<c:url value="/images/icon_reply.gif"/>" alt="" writer="" class="action" title="댓글" />
            <img class="action comment_delete" src="<c:url value="/images/study/delete_smallest.png"/>" title="삭제"
                     href=""/>
		</div>
	</div>
</div>
<script type="text/javascript">
$(function(){ 
	var $updateDiv = $('#updateDiv'), $replyDiv = $('#replyDiv'),
		$updateForm = $('#updateForm'), $replyForm = $('#replyForm'),
		$actionArea = $('.active-area');
	
	$replyDiv.dialog({
		autoOpen: false,
		height: 300,
		width: 600,
		modal: true,
		buttons: {
			'저장': function() {
				$replyForm.submit();
			},
			'닫기': function() {
				$( this ).dialog( "close" );
			}
		},
		open: function() {
			$('textarea').wysiwyg();
		},
		close: function() {
		}
	});
	$updateDiv.dialog({
		autoOpen: false,
		height: 300,
		width: 600,
		modal: true,
		buttons: {
			'수정': function() {
				$updateForm.submit();
			}, '닫기': function() {
				$(this).dialog( "close" );
			}
		},
		open: function() {
			$('textarea').wysiwyg();
		},
		close: function() {
		}
	});
	
	$('.writeBtn').click( function(e){ $replyDiv.dialog('open'); });
	$('.updateReplyBtn, .updateBtn').click( function(e){ 
		$.getJSON('/study/view/${study.id}/board/textPost/'+$(this).attr('id'), null, function(post){
			$updateForm.find('input[name="title"]').val(post.title).end()
				.find('textarea[name="content"]').val(post.content).end()
				.attr('action', '/study/view/${study.id}/board/textPost/' + post.id);
			$updateDiv.dialog('open'); 
		});
	});


	var options = { 
	        success:       function(reply, statusText, xhr, $form) {
				$('#replyAddSpot').find('.reply-content').text( reply.content).end()
					.find('.reply-title').text( reply.title).end()
					.find('.comment-count').text( reply.commentCount).end()
					.find('.commentForm').attr('action', '/study/${study.id}/active-area/textPost/' + reply.id + '/comment/write');
				$('#reply-header').after( $('#replyAddSpot').html());
				$replyDiv.dialog('close');
				$('active-area').load('${study.id}/active-area/textPost/view/' + '${textPost.id}/page/' + '${page}');
			},  
	        dataType:  'json',
	        clearForm: true
	}; 
	
	var commentOptions = {
        success:       function(comment, statusText, xhr, $form) {
			$form.parent().slideToggle("slow");
			$('#commentAddSpot').find('.photo.fn').attr( 'src', comment.writer.avatar).attr( 'alt',comment.writer.name).end()
				.find( '.post-comment-writer-name').text( comment.writer.name).end()
				.find( '.post-comment-writer-createdText').text( comment.createdText).end()
				.find( '.post-comment-data').text( comment.comment).end()
				.find( '.rt').attr( 'alt', 'RT ' + comment.writer.name).attr( 'writer', comment.writer.name).end()
				.find( '.action.comment_delete').attr( '/study/view/${study.id}/board/textPost/${textPost.id}/comment/' + comment.id + '/remove');
			$form.parent().after($('#commentAddSpot').html()).next().show('highlight', null, 1000);
			var $commentCount = $form.parent().parent().prev().children().first().children().first();
			$commentCount.text( parseInt( $commentCount.text()) + 1);
		},  
        dataType:  'json',
        clearForm: true
	};
	var updateOptions = {
	        success:       function(comment, statusText, xhr, $form) {
	        	$('.active-area').load('${study.id}/board/textPost/view/' + '${textPost.id}/page/' + '${page}');
	        	$updateDiv.dialog('close');
			},  
	        dataType:  'json',
	        clearForm: true
		};
	$replyForm.submit(function() { 
		$(this).ajaxSubmit(options); 
		return false;
	});
	
	$('.commentForm').live( 'submit', function() { 
		$(this).ajaxSubmit(commentOptions); 
		return false;
	});
	$updateForm.submit( function() { 
		$(this).ajaxSubmit(updateOptions); 
		return false;
	});
	
	$('.post-comment-actions .rt').live( 'click', function() {
		var $form = $(this).parent().parent().parent().children().first();
		if ($form.is(':hidden')) {
			$form.slideToggle('slow');
		}
		$form.find('.comment.root').val("@" + $(this).attr('writer') + ' ').focus();
	});
	
	$('.action.comment_delete').live( 'click', function() {
        var $this = $(this);
		var $commentCount = $this.parent().parent().parent().prev().children().first().children().first();
        if( confirm( "삭제 하시겠습니까?")) {
			$.post( $this.attr("href"), null, function(data) {
			$this.parent().parent().hide( 'blind', null, 1000).remove();
			$commentCount.text( parseInt( $commentCount.text()) - 1);
			}, 'json');
        }
        return false;
    });
	
	$('.writeReplyBtn').live( 'click', function() {
		$this = $(this);
		$this.parent().parent().next().find('.reply-comment-form').slideToggle('slow');
		return false;
	});
	$('#moveListBtn').click( function(){
		$actionArea.load('${study.id}/post/textPost/list/' + '${page}');
	});
	// 버튼 모양으로 변경
	$('button').button().focusout( function() { $(this).removeClass('ui-state-focus'); })
		.children().addClass('post-button-text').removeClass('ui-button-text');
	
});
</script>