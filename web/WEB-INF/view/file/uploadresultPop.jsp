<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<page:popuppage>
	<br/>
	<p>아래의 링크 주소를 입력창에 붙여 넣으세요.</p>
	<br/>
	<p>&lt;a href=&quot;/file/download?id=${uploadFile.id}&quot;&gt;${uploadFile.fileName}&lt;/a&gt;</p>
	<br/>
	<a href="<c:url value="/file/uploadPop"/>">업로드 화면</a>
	&nbsp;&nbsp;&nbsp;
	<sec:authorize ifAnyGranted="ROLE_ADMIN">
    <a href="<c:url value="/file/list"/>" target="_blank">파일관리</a>
	</sec:authorize>
</page:popuppage>
	