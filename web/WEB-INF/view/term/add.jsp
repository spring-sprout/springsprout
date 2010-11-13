<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" tagdir="/WEB-INF/tags/study"%>

<page:defaultpage banner_name="term-banner" selected_menu="Programming Terms">
	<s:defaultpage>
		<div class="previous"><a href="<c:url value="/term/index"/>"><img src="<c:url value="/images/btn/previous.png"/>" alt="이전페이지" /></a></div>
		<div class="devTerm">
            <form:form commandName="term" method="post" action="/term/addsubmt">
                <s:ftext title="용어" path="devTerm.phrase" size="64" />
                <s:ftextarea title="설명" path="devTerm.details" cols="64" rows="5" />
                <s:ftext title="태그" path="devTerm.tags" size="64" />
                <div class="noti">
                <form:checkbox path="devTerm.notifiable" cssClass="ch01 "/>&nbsp; 이 용어에 대한 한글 용어 알림 메시지를 받으시겠습니까?
                </div>
                <div class="termDown"><a href="" onclick="korTermToggle('block'); return false;"><img src="<c:url value="/images/term/term_down.png"/>" alt="down"/> 한글 용어도 같이 등록 하기</a></div>
                <div class="termUp disOff"><a href="" onclick="korTermToggle('none'); return false;"><img src="<c:url value="/images/term/term_up.png"/>" alt="down"/> 한글 용어도 같이 등록 하기</a></div>
                <div id="korTermAdd" class="korTerm disOff">
                    <s:ftext title="한글로" path="korTerm.korPhrase" size="64" />
                    <s:ftextarea title="&nbsp;&nbsp;&nbsp;설명" path="korTerm.details" cols="80" rows="5" />
                </div>
                <div class="saveBtn s_waitblock"><input type="image" src="<c:url value="/images/term/save.png"/>" alt="저장" id="btnSaveTerm" /></div>
            </form:form>
		</div>
	</s:defaultpage>
<script type="text/javascript">
	var _initTag, _initDetails = false;
	$(document).ready(function(){
		initValueSet();
		$("input[name=devTerm.tags]").mousedown( function() {
			if(!_initTag) {
				$("input[name=devTerm.tags]").val("");
				_initTag = true;
			}
	    });
		$("input[name=devTerm.tags]").keyup( function(e) {
			if(!_initTag) {
				if(e.which === 9) {
					$("input[name=devTerm.tags]").val("");
					$("input[name=devTerm.tags]").focus();
					_initTag = true;
				}
			}
	    });
		$("textarea[name=devTerm.details]").mousedown( function() {
			if(!_initDetails) {
				$("textarea[name=devTerm.details]").val("");
				_initDetails = true;
			}
	    });
		$("textarea[name=devTerm.details]").keyup( function(e) {
			if(!_initDetails) {
				if(e.which === 9) {
					$("textarea[name=devTerm.details]").val("");
					$("textarea[name=devTerm.details]").focus();
					_initDetails = true;
				}
			}
	    });
	    $("form").submit( function() {
		     $("form").attr("action", "<c:url value='/term/addsubmt'/>");
	    	 if(!_initTag) {
	    		 $("input[name=devTerm.tags]").val("");
	    	 }
	    	 if(!_initDetails) {
				$("textarea[name=devTerm.details]").val("");
	    	 }
	    });
	});
	function korTermToggle(val){
		//$("#korTermAdd").slideToggle("slow");
		$("#korTermAdd").toggle();
	 	$(".termDown").toggle();	
	 	$(".termUp").toggle();
	}

    function initValueSet() {
        $("input[name=devTerm.tags]").val("태그는 공백을 사용하여 구분합니다. 예) spring spring-web-flow hibernate");
        $("textarea[name=devTerm.details]").val("용어가 사용된 문장 또는 단락을 입력해 주시면 문맥 정보를 이해하기 좋습니다.\n이해하기 좋을수록 적절한 한글 용어를 구상하는데 더 많은 도움이 됩니다.");
    }

    callback_ajaxError = function(){
        defaultGrowlUI('로그인 해주세요~');
    };
</script>
</page:defaultpage>