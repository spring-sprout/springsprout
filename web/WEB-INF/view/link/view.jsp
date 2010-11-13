<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="s2c" tagdir="/WEB-INF/tags/s2c"%>
<%@ taglib prefix="s" tagdir="/WEB-INF/tags/study"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<page:defaultpage selected_menu="links" banner_text="즐겨찾기" ajaxlogin_yn="Y">
	<s:defaultpage>
        |
        <a class="confirmRequired" href="<c:url value="/link/${link.id}/form"/>" title="링크 수정">수정</a> |
        <a id="deleteBtn" href="<c:url value="#"/>" title="링크 삭제">삭제</a> |
        <a href="<c:url value="/link"/>" title="링크 목록으로 이동">뒤로</a> |
        <form:form action="/link/${link.id}" method="DELETE">
        </form:form>
        <p>
            <label>제목</label>
            <span>${link.title}</span>
        </p>
        <p>
            <label>링크</label>
            <span>${link.url}</span>
        </p>
        <p>
            <label>등록자</label>
            <span>${link.writer.name}</span>
        </p>
        <p>
            <label>요약</label>
            <span>${link.descr}</span>
        </p>
        <p>
            <label>등록일</label>
            <span>${link.created}</span>
        </p>
        <p>
            <label>갱신일</label>
            <span>${link.updated}</span>
        </p>
	</s:defaultpage>
<script type="text/javascript">
$(document).ready( function(){
    $("#deleteBtn").click(function(){
        $("form").submit();
    });
});
</script>
</page:defaultpage>
