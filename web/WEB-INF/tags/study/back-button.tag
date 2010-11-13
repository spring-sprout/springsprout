<%@ tag pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="url" required="true" %>

<a href="<c:url value="${url}"/>" class="s_waitblock"><input type="button" value="취소" class="jButton" /></a>
