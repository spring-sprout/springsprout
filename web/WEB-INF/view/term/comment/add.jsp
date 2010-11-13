<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" tagdir="/WEB-INF/tags/study"%>
<div class="small_close"> 
	<a href="javascript:$('#main').unblock();$.unblockUI();"><img src="<c:url value="/images/term/close_small.png"/>" id="cancle"></a>
</div>
<div class="pop_input">
	<div class="title">댓글</div>
	<div class="content" style="height:80px;">
		<p>
			<label></label>
			<textarea id="term_comment" name="comment" rows="3" cols="42" style="float:left;width:400px;"></textarea>
			<a href="javascript:addComment();" style="float:right;"><img src="<c:url value="/images/term/save.png"/>" id="saven"></a>
		</p>
	</div>
</div>
<script type="text/javascript">
function getUrl(action){
	return '<c:url value="/term/"/>'+action;
}

function addComment(){
	var param = {
					comment:$('#term_comment').val()
				};

	$.post(getUrl(${termId} + '/comment/addsubmit'), param,
        function(data, status){
            if(status === "success" && data.result === 'success'){
                $('#termCommentList').load(getUrl(${termId}) + ' ' + '#termCommentList', function(){
                    loadKorTermDaumSearch();
                });
                $('#main').unblock();$.unblockUI();
                defaultGrowlUI('저장을 완료했습니다.');
            }else{
                centerGrowlUI(data.errMessge);
            }
        }, "json");
}

callback_ajaxError = function(){
    defaultGrowlUI('로그인 해주세요~');
};
</script>