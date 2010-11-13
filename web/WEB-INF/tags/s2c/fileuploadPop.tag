<%@ tag pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize ifAnyGranted="ROLE_MEMBER, ROLE_ADMIN">
    <div id="fileUploadDialog" title="FileUpload Dialog">
    	<iframe src='<c:url value="/file/uploadPop"/>' frameborder="0"/></iframe>
    </div>

<script type="text/javascript">
$(document).ready(function() {
	$("#fileUploadDialog").dialog({autoOpen: false});
});

function popFileUpload() {
	$("#fileUploadDialog").dialog("open");
}

</script>

<style type="text/css">
#fileUploadDialog {padding:0; background:#FBFBFB; border: 1px dotted #ccc; overflow: hidden;}
</style>

</sec:authorize>
