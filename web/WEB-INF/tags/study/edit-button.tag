<%@ tag pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="url" required="true" %>

<a href="<c:url value="${url}"/>"><img src="<c:url value="/images/study/edit.png"/>"/></a>