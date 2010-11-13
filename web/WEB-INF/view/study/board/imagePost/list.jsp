<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="s2c" tagdir="/WEB-INF/tags/s2c"%>
<%@ taglib prefix="s" tagdir="/WEB-INF/tags/study"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style type="text/css">
.post-image-detailImage, .post-image-thumbnail {
	
}
img:HOVER {
	cursor: pointer;
}
.post-image-detailImage img {
	max-width: 90%;
	margin: 10px;
	border: solid 1px purple;
}
.post-image-thumbnail img {
	margin: 5px;
}

.post-image-thumbnail-default {
	opacity : 0.67;
	opacity : 1;
	border: solid 1px green;
}

.post-image-thumbnail-selected {
	opacity : 1;
	border: solid 2px green;
}

.post-image-container .images {
	overflow: hidden;
}

.detail, .list, .contents {
	border: solid 1px gray;
}

.detail {
	float: left;
	width: 75%;
}
.list {
	float: left;
	width: 24%;
}

/* the overlayed element */
.simple_overlay {
	
	/* must be initially hidden */
	display:none;
	
	/* place overlay on top of other elements */
	z-index:10000;
	
	/* styling */
	background-color:#333;
	
	width:675px;	
	min-height:200px;
	border:1px solid #666;
	
	/* CSS3 styling for latest browsers */
	-moz-box-shadow:0 0 90px 5px #000;
	-webkit-box-shadow: 0 0 90px #000;	
}

/* close button positioned on upper right corner */
.simple_overlay {
	width: 1000px;
}
.simple_overlay div {
	float:left;
}
.simple_overlay .close {
	background-image:url(/resources/js/plugin/jqueryTools/images/close.png);
	position:absolute;
	right:-15px;
	top:-15px;
	cursor:pointer;
	height:35px;
	width:35px;
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

.comment-form { 
	overflow: hidden;
	padding: 4px;
}
textarea.comment  { width: 85%; float: left;}
input.comment-submit { height: 60px; float: left; width: 10%; margin-left: 0.5em;}

</style>
<div id="mainBar">
	<div class="post-text-list-actions">
		<sec:authorize ifAnyGranted="ROLE_MEMBER">
			<button id="writeBtn">글쓰기</button>
		</sec:authorize>
	</div>
	<div class="post-image-container">
		<div class="images">
			<div class="detail">
				<c:forEach items="${imagePostList}" var="post" varStatus="status">
				<div class="post-image post-image-detailImage" align="center" id="post-${post.id}">
					<img src="/images/userimage/${post.writer.email}/${post.imageFile.savedFileName}" alt="image${status.count }" rel="#${post.id}"/>
					<div class="simple_overlay" id="${post.id}">
						<!-- large image -->
						<div style="width: 80%;">
							<img style="max-width: 100%;" src="/images/userimage/${post.writer.email}/${post.imageFile.savedFileName}" />
						</div>
						<!-- image details -->
						<div class="details">
							<h3 class="detail-title">${post.title }</h3>
							<h4 class="detail-writer">Barcelona, Spain</h4>
							<p class="detail-content">${post.content}</p>
						</div>
					</div>
				</div>
				</c:forEach>
				<div class="contents">
					<div class="content">
						이미지 컨텐츠
					</div>
					<div class="comment-list">
						<div id="commentFormDiv" class="comment-form">
							<form:form id="commentForm" cssClass="commentForm" commandName="comment" method="post">
								<form:textarea path="comment" cssClass="comment" />
								<input type="submit" class="comment-submit" value="보내기" />
							</form:form>
						</div>
					</div>
				</div>
			</div>
			<div class="list" align="center">
				<span id="movePrev" class="ui-icon ui-icon-triangle-1-n">${page - 1}</span>
				<div class="post-image post-image-thumbnail-list">
					<c:forEach items="${imagePostList}" var="post" varStatus="status">
					<div class="post-image post-image-thumbnail" rel="${post.id}" lang="asdfd">
						<img class="post-image-thumbnail-default" src="/images/userimage/${post.writer.email}/${post.imageFile.thumbNailName}" alt="${post.title}" title="${post.title}" />
					</div>
					</c:forEach>
				</div>
				<span id="moveNext" class="ui-icon ui-icon-triangle-1-s">${page + 1}</span>
			</div>
		</div>
	</div>
</div>
<div id="registDiv" title="일반 게시물 생성">
	<form:form id="postForm" commandName="imagePost" method="post" action="/study/view/${study.id}/board/imagePost/write" enctype="multipart/form-data">
		<form:hidden path="rootStudy.id"/>
		<p>
			<form:label path="title">제목 : </form:label>
			<form:input path="title" cssStyle="width: 90%;" />
		</p>
		<p>
			<form:label path="file">파일 : </form:label>
			<input id="file" name="file" type="file" />
		</p>
		<p>
			<form:label path="content">내용</form:label><br/>
			<form:textarea path="content" id="postContent" rows="8" cols="100" />
		</p>
		
	</form:form>
</div>
<script type="text/javascript" src="<c:url value="/resources/js/plugin/jqueryTools/jquery.tools.min.js"/>"></script>
<script type="text/javascript">
var $registDiv = $( '#registDiv'), $postForm = $('#postForm'), $commentForm = $('#commentForm'), selectedPostId;
$(function(){ 
	
	initDialog();
	initEvent();
	initAjaxForm();
	initCommentForm();
	$('img[rel]').overlay();
});

function initDialog() {
	$registDiv.dialog({
		autoOpen: false,
		height: 300,
		width: 600,
		modal: false,
		buttons: {
			'생성': function() {
	  			$postForm.submit();
			},
			'취소': function() {
				$(this).dialog( "close" );
			}
		},
		open: function() {
			 $('textarea#postContent').wysiwyg();
		},
		close: function() {
			$('#listDiv').load('${study.id}/board/imagePost/list/0');
		}
	});
}

function initEvent() {
	$('.post-image-detailImage:not(:first)').hide();
	selectedPostId = $('.post-image-thumbnail').first().attr('rel');
	$('.post-image-thumbnail :first').addClass('post-image-thumbnail-selected');
	$('#writeBtn').click( function(e){ $registDiv.dialog('open'); });
	$('.post-image-thumbnail').click( function(){
		var $this = $(this), postId = '#post-' + $this.attr('rel'), 
			$imgEl = $this.children().first();
		$('.post-image-detailImage:not(:hidden)').hide('drop', 1000, function(){
			$(postId).show('drop', 1000, function() {
				$('.post-image-thumbnail img').removeClass('post-image-thumbnail-selected');
				$imgEl.addClass('post-image-thumbnail-selected');
				content = $(this).find('.detail-content').text();
				$('.content').text(content);
			});
		});
	}); 
	$('#movePrev, #moveNext').click( function(){
		$('#listDiv').load('${study.id}/board/imagePost/list/' + $(this).text());
	});
}
function initAjaxForm() {
	var options = { 
        success: function(data) {
			$registDiv.dialog('close');
		}, 
        clearForm: true
    }; 
 	$postForm.submit(function() { 
		$(this).ajaxSubmit(options); 
		return false; 
	}); 
}
function initCommentForm() {
	var commentOptions = {
		url: '/study/view/${study.id}/board/imagePost/' + selectedPostId + '/comment/write',
        success: function(comment, statusText, xhr, $form) {
			
		},  
        dataType:  'json',
        clearForm: true
	};
	$commentForm.submit(function() { 
		$(this).ajaxSubmit(commentOptions); 
		return false;
	});
}
</script>