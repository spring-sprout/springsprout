<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<page:popuppage>
	<br/>
	<form:form action="fileuploadPop" method="post" enctype="multipart/form-data">
		<input id="fileInput" type="file" name="uploadfile" class="text"/>
	</form:form>
	<br/>
	<sec:authorize ifAnyGranted="ROLE_ADMIN">
    <a href="<c:url value="/file/list"/>" target="_blank">파일관리</a>
	</sec:authorize>
<script type="text/javascript">
$(document).ready(function() {
	$("#fileInput").change(function() {
		$("form")[0].submit();
	});
});

</script>
</page:popuppage>

