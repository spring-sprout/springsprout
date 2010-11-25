<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="s2c" tagdir="/WEB-INF/tags/s2c"%>
<%@ taglib prefix="s" tagdir="/WEB-INF/tags/study"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style type="text/css">
#postForm p { margin: 2px;}
label { display: inline !important;}
textarea { width: 95%; border: 1px solid; }
.mod-content { padding: 10px; }
.mod-header { margin: 5px;}
.form-button { padding-top: 5px }
</style>
<s2c:module name="${title} Text Post">
	<fieldset>
		<legend>원본 글</legend>
		${ parent.title }
		${ parent.content }
	</fieldset>
	<form:form id="postForm" commandName="textPost" method="${method}" action="${action}" enctype="multipart/form-data">
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
		<p align="center" class="form-button">
			<input type="submit" id="saveBtn" value="저장" />
			<input type="button" id="cancleBtn" value="취소" />
		</p>
	</form:form>
</s2c:module>
<script type="text/javascript">
$postForm = $('#postForm'), $activeArea = $('.active-area');
$(function(){
	initEvent();
	
	$('#saveBtn, #cancleBtn').button().focusout( function() { $(this).removeClass('ui-state-focus'); })
		.children().addClass('post-button-text').removeClass('ui-button-text');
	
	var options = { 
		beforeSubmit: function(arr, $form, options) { 
			
			s_waitblock();
		}, success: function(html) {
			refreshListDiv();
		}, 
        clearForm: true
    }; 
 	$postForm.submit(function() { 
		$(this).ajaxSubmit(options); 
		return false; 
	}); 
});

function initEvent(){
	$('#cancleBtn').click( function(e) { refreshListDiv(); });	
}

function refreshListDiv() {
	$.ajax({
		url : '${cancelUrl}',
		beforeSend : function(){
			s_waitblock();
		},success : function(html){
			$activeArea.html(html);
			$.unblockUI();
		}
	});
}
</script>