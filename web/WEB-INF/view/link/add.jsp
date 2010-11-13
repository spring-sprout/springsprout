<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" tagdir="/WEB-INF/tags/study"%>

<page:defaultpage selected_menu="links">
	<s:defaultpage>
        <h1>새 링크</h1>
        <form:form commandName="link" action="/link"  method="POST">
            <p>
                <label>링크 제목</label>
                <form:input path="title" size="50"/>
                <form:errors path="title" cssClass="smdis-error-message"/>
            </p>
            <p>
                <label>링크 URL</label>
                <form:input path="url" size="50"/>
                <form:errors path="url" cssClass="smdis-error-message"/>
            </p>
            <p>
                <label>설명</label>
                <form:textarea path="descr" rows="3" cols="50"/>
            </p>
            <input type="submit" value="저장"/> 
        </form:form>
	</s:defaultpage>
<script type="text/javascript">
$(document).ready(function(){
});
</script>
</page:defaultpage>