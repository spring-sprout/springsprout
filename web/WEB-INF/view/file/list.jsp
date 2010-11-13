<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<page:defaultpage selected_menu="file">
	<div class="site">
		<a href="<c:url value="/file/upload"/>">파일 업로드</a>
		<br />
		<div style="clear:both;"></div>
	    <table  class="tablesorter" cellspacing="1">
		 <thead>
			<tr>
				<th width="5%">ID</th>
				<th width="20%">파일명</th>
				<th width="30%">download URL</th>
				<th width="8%">욜량</th>
				<th width="20%">등록자</th>
				<th width="7%">등록일자</th>
				<th width="7%">최종다운</th>
				<th width="3%"></th>
			</tr>
		</thead>
		<tbody>
		<c:choose>
			<c:when test="${!empty fileList}">
				<c:forEach items="${fileList}" var="uploadFile" varStatus="vs">
				<tr>
					<td>${uploadFile.id}</td>
					<td>${uploadFile.fileName}</td>
					<td>/file/download?id=${uploadFile.id}</td>
					<td>${uploadFile.fileSize}</td>
					<td>${uploadFile.uploader.email}(${uploadFile.uploader.name})</td>
					<td>${uploadFile.uploadDate}</td>
					<td>${uploadFile.lastDownload}</td>
					<td><a href="javascript:deleteFile(${uploadFile.id})"><img src="<c:url value="/images/remove-16x16.png"/>"/></a></td>
				</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
			  <tr><td colspan="99" align="center">현재 등록된 파일이 없습니다.</td></tr>
			</c:otherwise>
		</c:choose>
		</tbody>
		</table>
	</div>
<script type="text/javascript">
$(document).ready(function(){
	 $("table.tablesorter").tablesorter({
	        //0 index column asceing
	    	//sortList: [[0,0]],
	    	// widget type is zebra
	    	widgets: ['zebra'] 
	  }); 
});

function deleteFile(id) {
	if(confirm("정말로 삭제하시겠습니까?")) {
		location.href = "delete.do?id=" + id;
	}
}

</script>
</page:defaultpage>