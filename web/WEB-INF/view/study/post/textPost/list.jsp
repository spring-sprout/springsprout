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
	margin: 1px;
    padding: 2px 5px;
}

.post-reply-count { background-color: #D8E9D6; }
.post-comment-count{
	background-color: #9DB4A8;
	color: white;
    padding: 2px 5px;
}

.post-view-count { background-color: #E6E6FA; }
.post-count { font-weight: bold; font-size: 1.5em; }

.post-text-list {
	border-bottom: 1px dotted #999999;
	overflow:hidden;
	padding:5px;
}

.post-text-list-header {
	border-bottom: 1px solid black;
	overflow:hidden;
	padding-bottom:5px;
	height: 23px;
	width: 100%;
}
.post-text-title { text-indent: 1em; }
.post-text-title:HOVER {
	cursor: pointer;
	text-decoration: underline;
	font-weight: bold;
	color: green;
}
.post-text-list-header-title { float: left; }
.post-text-list-tabs { float: right; }
.post-text-list-tabs div {
	border: 1px solid black;
	float:right;
	padding:5px;
	border-right: none;
	background: none repeat scroll 0 0 #FFFFFF;
}
.post-list-actions { float: right; padding: 10px 5px 0px 0px;}
.active { border-bottom: 1px solid white; }
.tip {
    color: #fff;
    background:#1d1d1d;
    display:none; /*--Hides by default--*/
    padding:10px;
    position:absolute;    z-index:1000;
    -webkit-border-radius: 3px;
    -moz-border-radius: 3px;
    border-radius: 3px;
    font-size: 11px;
}
.mod-content { padding: 10px; }
.mod-header { margin: 0 5px;}

</style>

<div class="post-list-actions">
	<sec:authorize ifAnyGranted="ROLE_MEMBER">
		<button id="writeBtn" class="post-button">글쓰기</button>
	</sec:authorize>
	<button id="moveToListBtn" class="post-button">목록으로</button>
</div>
<s2c:module name="일반 게시판">
	<s2c:portlet target="${textPostList}">
		<div id="post-list">
		<c:forEach items="${textPostList}" var="post">
			<div class="post-text-list">
				<div id="post-text-summary-${post.id}">
					<div class="post-text-summary-count"></div>
						<div class="post-reply-count">
							<div class="post-count">${post.branchCount}</div>
							<div>답글</div>
						</div>
						<div class="post-comment-count">
							<div class="post-count">${post.commentCount}</div>
							<div>댓글</div>
						</div>
					<div class="post-text-summary">
						<div class="post-text-title" id="${post.id}">
							<h3 class="tip_trigger">${post.title}
							<span class="tip">
								<span>${post.content}</span>
							</span>
							</h3>
						</div>
						<div class="post-text-createdAt" align="right"><s:date value="${post.createdAt}"/> ${post.writer.name }</div>
					</div>
				</div>
			</div>
		</c:forEach>
		</div>
		<div id="post-list-bottom"></div>
		<s:postPaging />
	</s2c:portlet>
</s2c:module>

<script type="text/javascript">
$(function(){ 
	var $postForm = $('#postForm'), $actionArea = $('.active-area');
	$('#writeBtn').click( function(e){
		$actionArea.load('/study/${study.id}/post/textPost');
	});
	$('#moveToListBtn').click( function(e){
		$actionArea.load('/study/${study.id}/post');
	});
	$('.post-text-title').click( function(event){
		var postId = $(this).attr('id');
		$actionArea.load('/study/${study.id}/post/textPost/' + postId + '?page=${pagingInfo.now}');
		return false;
	});	
	$('button').button().focusout( function() { $(this).removeClass('ui-state-focus'); })
		.children().addClass('post-button-text').removeClass('ui-button-text');
	  //Tooltips
    $('.tip_trigger').hover(function(){
        tip = $(this).find('.tip');
        tip.show(); 
    }, function() {
		tip.hide(); 
    });
   

});
</script>
