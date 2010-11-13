<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<page:defaultpage selected_menu="file">
    <div class="site">
        <h1>Upload Result</h1>
        <p>첨부파일 : <a href="<c:url value="/file/download.do?id=${uploadFile.id}"/>">${uploadFile.fileName}</a> | /file/download?id=${uploadFile.id}</p>
        <br/><hr/><br/>
        
        <div>
	        <a href="<c:url value="/file/upload"/>">업로드 화면</a>
	        &nbsp;&nbsp;&nbsp;
			<sec:authorize ifAnyGranted="ROLE_ADMIN">
		    <a href="<c:url value="/file/list"/>">파일관리</a>
			</sec:authorize>
        </div>
    </div>
</page:defaultpage>
