<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="s2c" tagdir="/WEB-INF/tags/s2c"%>
<%@ taglib prefix="s" tagdir="/WEB-INF/tags/study"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style type="text/css">
#postForm p { margin: 2px;}
label, .MultiFile-wrap { display: inline !important;}
.MultiFile-label { background-color: whitesmoke; }
.MultiFile-remove { color: red !important;}
textarea { width: 95%; border: 1px solid; }
.mod-content { padding: 10px; }
.mod-header { margin: 5px;}
.form-button { padding-top: 5px }
</style>
<s2c:module name="Add New Image">
	<form:form id="postForm" commandName="imagePost" method="POST" action="${action}" enctype="multipart/form-data">
		<form:hidden path="rootStudy.id"/>
		<p>
			<form:label path="title">제목 : </form:label>
			<form:input path="title" cssStyle="width: 90%;" />
		</p>
		<p>
			<form:label path="file">파일 : </form:label>
			<input name="file" class="file" type="file" id="file" size="96%" />
		</p>
			<div id="fileArea"></div>
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
	initFileupload();
	
	$('#saveBtn, #cancleBtn').button().focusout( function() { $(this).removeClass('ui-state-focus'); })
		.children().addClass('post-button-text').removeClass('ui-button-text');
	
	var options = { 
		beforeSubmit: function(arr, $form, options) { 
			if ($form.find('#fileArea').children().length <= 0) {
				alert('이미지를 선택하세요.');
				$form.find('file').focus();
				return false;
			}
			if ($form.find('textarea').val().trim().length <= 0) {
				alert('내용을 입력하세요.');
				$form.find('textarea').focus();
				return false;
			}
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
	$('#cancleBtn').click( function(e) {
		refreshListDiv();
	});	
}

function initFileupload() {
	$('#file').MultiFile({
		list: '#fileArea',
		max: 1,
		accept: 'gif|jpg|png'
	});
}

function refreshListDiv() {
	$.ajax({
		url : '${study.id}/post/imagePost/list/0',
		beforeSend : function(){
			s_waitblock();
		},success : function(html){
			$activeArea.html(html);
			$.unblockUI();
		}
	});
}
</script>