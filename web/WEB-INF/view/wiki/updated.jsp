<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<page:defaultpage selected_menu="wiki">
	<div class="site">
	<ul>
		<c:forEach items="${updatedList}" var="eachDoc">
			<li><a href="<c:url value="${eachDoc.id}"></c:url>"> <c:out
				value="${eachDoc.key}" /></a> <fmt:formatDate
				pattern="yyyy.MM.dd HH:mm:ss z" value="${eachDoc.updateDate}" /></li>
		</c:forEach>
	</ul>
	</div>
</page:defaultpage>