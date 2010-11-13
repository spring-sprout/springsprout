<%@ tag pageEncoding="utf-8"%>
<%@ taglib prefix="wiki" tagdir="/WEB-INF/tags/wiki"%>
<%@ attribute name="wikiDocument" required="true"
	type="springsprout.domain.WikiDocument"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<ul class="wikichild">
<c:forEach items="${wikiDocument.childs}" var="child" varStatus="status">
	<li ${fn:length(child.childs) > 0 ? '' : 'class="leaf"'}>
		<wiki:link wikiDocument="${child}"/>
		<c:if test="${fn:length(child.childs) > 0}">
			<wiki:child wikiDocument="${child}"></wiki:child>
		</c:if>
	</li>
</c:forEach>
</ul>