<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" tagdir="/WEB-INF/tags/study"%>

<div class="pop_input">
<div class="title">■ 한글용어 등록</div>
	<div class="content">
	<p>
		<label>한글로</label>
		<input id="kor_korPhrase" type="text" name="korPhrase" />
	</p>
	<p>
		<label>설명</label>
		<textarea id="kor_details" name="details" rows="5" cols="42"></textarea>
	</p>
	<input type="hidden" id="termId" name="termId" value="${termId}">
	<div class="btn">
		<a href="javascript:$('#main').unblock();$.unblockUI();"><img src="<c:url value="/images/term/cancel.png"/>" onclick="javascritpt:$('#main').unblock();$.unblockUI();"></a>
		<a href="javascript:addKorTerm();"><img id="saveBtn" src="<c:url value='/images/term/save.png'/>"></a>
	</div>
	</div>
</div>
<script type="text/javascript">
function getUrl(action){
	return '<c:url value="/term/"/>'+action;
}

function addKorTerm(){
    var originImgSrc = $("#saveBtn").attr("src");
    $("#saveBtn").attr("src", "<c:url value='/images/loading.gif'/>");

	var param = {
					korPhrase:$('#kor_korPhrase').val(),
					details:$('#kor_details').val()
				};

	$.post(getUrl(${termId} + '/kor/addsubmit'), param,
        function(data, status){
            if(status === "success" && data.result === 'success'){
                $('#subcontents').load(getUrl(${termId}) + ' ' + '#subcontents', function(){
                    loadKorTermDaumSearch();
                });
                $('#main').unblock();$.unblockUI();
                defaultGrowlUI('저장을 완료했습니다.');
            }else{
                centerGrowlUI(data.errMessge);
                $("#saveBtn").attr("src", originImgSrc);
            }
        }, "json");
}

callback_ajaxError = function(){
    defaultGrowlUI('로그인 해주세요~');
};
</script>