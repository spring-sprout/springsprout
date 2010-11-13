<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<page:defaultpage selected_menu="file">
    <div class="site">
        <h1>File Upload</h1>
        <form:form action="fileupload" method="post" enctype="multipart/form-data">
        <p>
  			<label>첨부파일</label>
			<input type="file" name="uploadfile" class="text"/>
        </p>
        <br/><hr/><br/>
        <input type="submit" value="저장" />
        </form:form>
	    <br/><br/>
		<sec:authorize ifAnyGranted="ROLE_ADMIN">
	    <a href="<c:url value="/file/list"/>">파일관리</a>
		</sec:authorize>
		
		<br/><br/>
        <a href="javascript:popFileUpload()">(파일업로드 팝업)</a>
		
    </div>
    
</page:defaultpage>
