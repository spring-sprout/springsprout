<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" tagdir="/WEB-INF/tags/study"%>

<page:defaultpage banner_name="term-banner" selected_menu="Programming Terms">
	<s:defaultpage>
		<div class="previous"><a href="<c:url value="/term/${term.id}?nocount=true"/>"><img src="<c:url value="/images/btn/previous.png"/>" alt="이전페이지" /></a></div>
		<div class="devTerm">
            <form:form commandName="term" method="post">
                <s:ftext title="용어" path="phrase" />
                <s:ftextarea title="설명" path="details" cols="64" rows="5" />
                <s:ftext title="태그" path="tags" size="64" />
                <div class="noti">
                <form:checkbox path="notifiable" cssClass="ch01 "/>&nbsp; 이 용어에 대한 한글 용어 알림 메시지를 받으시겠습니까?
                </div>
                <div class="saveBtn"><input type="image" src="<c:url value="/images/term/save.png"/>" alt="저장" id="btnSaveTerm"/></div>
            </form:form>
		</div>
	</s:defaultpage>
<script type="text/javascript">
	$(document).ready(function(){
        $(".saveBtn").click( function(){
            $("form").submit();
        });
	});

    callback_ajaxError = function(){
        defaultGrowlUI('로그인 해주세요~');
    };
</script>
</page:defaultpage>