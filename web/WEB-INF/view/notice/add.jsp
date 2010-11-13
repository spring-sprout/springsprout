<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" tagdir="/WEB-INF/tags/study"%>

<page:defaultpage selected_menu="notice">
	<s:defaultpage>
		<h1>공지 추가</h1>
		<form:form commandName="notice" method="post">
			<s:ftext title="제목" path="title" />
			<s:ftextarea title="내용" path="contents" rows="30" cols="100" />
			<hr />
			<s:back-button url="/index" />
			<input type="submit" value="저장" class="s_waitblock" />
		</form:form>
	</s:defaultpage>
	
<script type="text/javascript">
$(document).ready( function(){
	CKEDITOR.replace( 'contents' );
});
</script>
</page:defaultpage>