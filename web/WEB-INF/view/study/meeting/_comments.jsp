<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div id="meeting_comments">
	<sec:authorize ifAnyGranted="ROLE_MEMBER">
		<div id="meeting_comment_btn_layer">
		<button id="comment_add_btn">의견추가</button>
		<div id="comment_add_form" style="display: none;">
			<form:form id="commentForm" commandName="comment" method="post" action="${commentObj.id}/comment/add">
			    <form:textarea id="comment_content" tabindex="1" 
                    accesskey="u" path="comment" rows="2" cols="80"
                    cssStyle="margin-top: 10px; border:1px solid #AAAAAA; padding:4px 2px; width:90%; height:60px;" cssClass="text"/>
			   	<form:errors path="comment" />
			   	<img id="comment_add" class="action commentAdd" src="<c:url value="/images/study/addComment.png"/>"/>
			</form:form>
		</div>
       </div>
	</sec:authorize>
	<div id="comment_list" style="margin-top: 10px;">
		<c:choose>
    		<c:when test="${!empty commentObj.comments}">
			 	<ol class="statuses">
			 	<c:forEach var="comment" items="${commentObj.comments}">
			 		<li>
			 			<span class="thumb">
							<img class="photo fn logoSmall" height="48" width="48" src="${comment.writer.avatar}" alt="${comment.writer.name}"/>
						</span>
						<span class="status-body">
							<strong>${comment.writer.name}</strong>
							<br/>
							<span class="entry-content">${comment.comment}</span>
							<span class="meta entry-meta">
								<a rel="bookmark" class="entry-date" href="#">
								<span class="published">${comment.created}</span>
								</a>
								<span>from SpringSprout</span>
							</span>
							<span class="actions">
								<img class="rt" src="<c:url value="/images/icon_reply.gif"/>" alt="RT ${comment.writer.name}" writer="${comment.writer.name}" class="action" />
                                <sec:authentication property="principal.username" var="currentUserName" scope="request"/>
                                <c:if test="${currentUserName == comment.writer.email}">
                                    <img class="action comment_delete" src="<c:url value="/images/study/delete_smallest.png"/>" title="delete"
                                         href="${commentObj.id}/comment/${comment.id}/delete"/>
                                </c:if>
                            </span>
						</span>
			 		</li>
		 		</c:forEach>
			 	</ol>
    		</c:when>
    		<c:otherwise>
			     의견이 없습니다.
    		</c:otherwise>
		</c:choose>
	</div>
</div>
<div id="commentDiv" style="display: none;">
	<input type="hidden" id="presentationId" value="${commentObj.id}" />
	<li>
	<span class="thumb">
		<img class="photo fn" height="48" width="48" src="" alt=""/>
	</span>
	<span class="status-body">
		<strong class="commentWriterName"></strong>
		<br/>
		<span class="entry-content"></span>
		<span class="meta entry-meta">
			<a rel="bookmark" class="entry-date" href="#">
			<span class="published"></span>
			</a>
			<span>from SpringSprout</span>
		</span>
		<span class="actions">
			<img class="rt" src="<c:url value="/images/icon_reply.gif"/>" alt="" writer="" class="action" />
            <img class="action comment_delete" src="<c:url value="/images/study/delete_smallest.png"/>" title="delete" href=""/>
		</span>
	</span>
	</li>
</div>
<script type="text/javascript">
	var $commentContent = $("#comment_content"), $commentAddForm = $("#comment_add_form"),
		$commentCount = $('#commentsCount'), $presentationId = $('#presentationId'),
		$commentDiv = $('#commentDiv'), $commentOl = $('.statuses li:first'),
		$commentList = $('#comment_list'), $commentForm = $('#commentForm'),
		isProcessing = false;
	
	$("#comment_add_btn").click(function () {
		$commentAddForm.slideToggle("slow").children('form').children('textarea').focus();
	});

	$('#comment_add').click(function() {
		if ( isProcessing) return false;
		isProcessing = true;
		addReply($(this));
	});
	
	function addReply(eventObj) {
		var $this = eventObj, src = $this.attr('src');
		$this.attr('src', '/images/study/ajax-loader.gif');
		$.ajax({
			type: 'POST',
			url: '${commentObj.id}/comment/add',
			data: $commentForm.serialize(),
			error: function( xhr, textStatus, errorThrown) {
				var obj = JSON.parse(xhr.responseText), errors = obj.errors;
				switch (obj.errorType) {
				case 'validError':
					validationUtil.processServerError(obj.errorCount, errors, $('#commentForm'));
					break;
				default:
					break;
				}
			},
			success: function( data, textStatus, xhr){
				var commentDto = data.commentDto, $commentOl = $('.statuses li:first');
				$commentDiv.find('.photo.fn').attr( 'src', commentDto.writerAvatar).attr( 'alt', commentDto.writerName).end()
					.find( '.commentWriterName').text( commentDto.writerName).end()
					.find( '.entry-content').text( commentDto.comment).end()
					.find( '.published').text( commentDto.createdStr).end()
					.find( '.entry-content').html( commentDto.comment).end()
					.find( '.rt').attr( 'alt', 'RT ' + commentDto.writerName).attr( 'writer', commentDto.writerName).end()
					.find( '.action.comment_delete').attr( 'href', $presentationId.val() + '/comment/' + commentDto.id + '/delete');
	
				if ( $commentOl.length <= 0) {
					$commentList.html($('<ol class="statuses">').append( $commentDiv.html()));
				} else 
					$commentOl.before( $commentDiv.html()).prev().show('highlight', null, 1000);

				$commentForm.clearForm();
				$commentCount.text( parseInt( $commentCount.text()) + 1);
				$.growlUI('Notification', data.title+' 모임에 의견이 등록되었습니다.');
			},
			complete: function() {
				$this.attr('src', src);
				isProcessing = false;
			},
			dataType: 'json'
		});
	}

	$(".actions .rt").live( 'click', function() {
		if ($commentAddForm.is(":hidden")) {
			$commentAddForm.slideToggle("slow");
		}
		$commentContent.val("@" + $(this).attr("writer") + " ").focus();
	});

    $('.action.comment_delete').live( 'click', function() {
        var $this = $(this);
        if( confirm( "삭제 하시겠습니까?")) {
			$.post( $this.attr("href"), null, function(data) {
				$this.parent().parent().parent().hide( 'blind', null, 1000).remove();
		        $commentCount.text( parseInt( $commentCount.text()) - 1);
		        if ( $commentOl.length <= 0) $commentList.html('의견이 없습니다.');
			}, 'json');
        }
        return false;
    });

    $commentContent.focusout( function(){
		if ( $(this).val().length > 0) $commentForm.find('span').remove();
    }); 
	$('button').button().focusout( function() { $(this).removeClass('ui-state-focus'); });
</script>