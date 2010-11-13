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

.post-reply-count { background-color: #AFEEEE; }
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
	height: 23px;
	width: 100%;
}
.post-text-title {
	text-indent: 1em;
}
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
.post-text-list-actions { float: right; }
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
</style>
<script type="text/javascript">
$(function(){ 
	$registDiv = $( '#registDiv'), $postForm = $('#postForm');
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
				$( this ).dialog( "close" );
			}
		},
		open: function() {
			 $('div#postContent').wysiwyg();
		},
		close: function() {
			$('#listDiv').load('${study.id}/board/textPost/list/0');
		}
	});

	$('#writeBtn').click( function(e){
		  $registDiv.dialog('open');
	});

 	var options = { 
        success:       function(data) {
			$registDiv.dialog('close');
		}, 
        dataType:  'json',
        clearForm: true
    }; 
 	$postForm.submit(function() { 
		$(this).ajaxSubmit(options); 
		return false; 
	}); 

	$('.post-text-title').click( function(event){
		var postId = $(this).attr('id');
		$('#listDiv').load('${study.id}/board/textPost/view/' + postId + '/page/' + '${pagingInfo.now}');
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
<div id="mainBar">
	<div class="post-text-list-actions">
		<sec:authorize ifAnyGranted="ROLE_MEMBER">
			<button id="writeBtn">글쓰기</button>
		</sec:authorize>
	</div>
	<div id="post-list-header" class="post-text-list-header">
		<div class="post-text-list-header-title"><h2>일반 포스트 목록</h2></div>
	</div>
	<div class="post-text-list-tabs">
		<div class="active">active</div>
		<div>hot</div>
		<div>week</div>
		<div>month</div>
	</div>
	<div id="post-list">
	<c:forEach items="${textPostList}" var="post">
		<div class="post-text-list">
			<div id="post-text-summary-${post.id}">
				<div class="post-text-summary-count"></div>
					<div class="post-reply-count">
						<div class="post-count">${post.branchCount}</div>
						<div>reply</div>
					</div>
					<div class="post-comment-count">
						<div class="post-count">${post.commentCount}</div>
						<div>comment</div>
					</div>
					<div class="post-view-count">
						<div class="post-count">10</div>
						<div>view</div>
					</div>
				<div class="post-text-summary">
					<div class="post-text-title" id="${post.id}">
						<h3 class="tip_trigger">${post.title}
						<span class="tip">
							<img class="photo fn logoSmall" height="48" width="48" src="${post.writer.avatar}" alt="${post.writer.name}"/>
							<span>${post.content}</span>
						</span>
						</h3>
					</div>
					<div class="post-text-createdAt" align="right">${post.createdAt} writed by ${post.writer.name }</div>
				</div>
			</div>
		</div>
	</c:forEach>
	</div>
	<div id="post-list-bottom"></div>
</div>
<s:postPaging />
<div id="registDiv" title="일반 게시물 생성">
	<form:form id="postForm" commandName="textPost" method="post" action="/study/view/${study.id}/board/textPost/write">
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
