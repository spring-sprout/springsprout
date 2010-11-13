<%@ tag pageEncoding="utf-8" body-content="empty"%>
<%@ attribute name="wikiDocument" required="true"
	type="springsprout.domain.WikiDocument"%>
<%@ attribute name="currentDocument" required="true"
	type="springsprout.domain.WikiDocument"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<input type="radio" name="newParentId" value="<c:out value="${wikiDocument.id}" />" <%
	out.println(wikiDocument.isContain(currentDocument) ? "disabled" : ""); %> /> 
<a href="<c:url value="${wikiDocument.idUrl}"/>"><c:out value="${wikiDocument.key}" /></a>