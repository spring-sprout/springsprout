<%@ tag pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ tag import="springsprout.service.security.*" %>

<%@ attribute name="selected_menu" required="false"%>
<div id="menu_bar">
	<ul>
		<li><a href="<c:url value="/study/index"/>"  <c:if test="${selected_menu == 'studies'}">class="menuselected"</c:if>><span>Studies</span></a></li>
		<li><a href="<c:url value="/seminar/index"/>"  <c:if test="${selected_menu == 'seminars'}">class="menuselected"</c:if>><span>Seminars</span></a></li>
		<li><a href="<c:url value="/wiki/dashboard"/>" <c:if test="${selected_menu == 'wiki'}">class="menuselected"</c:if>><span>Wiki</span></a></li>
		<li><a href="http://s2c.pe.kr/"  <c:if test="${selected_menu == 'blog'}">class="menuselected"</c:if>><span>Blog</span></a></li>
		<sec:authorize ifNotGranted="ROLE_MEMBER, ROLE_ADMIN">
        	<li><a href="<c:url value="/login"/>">Login</a></li>
		</sec:authorize>
		<sec:authorize ifAnyGranted="ROLE_ADMIN">
		 	<li><a href="<c:url value="/admin/index"/>">Admin</a></li>
		</sec:authorize>
		<sec:authorize ifAnyGranted="ROLE_MEMBER, ROLE_ADMIN">
			 <li><a href="<c:url value="/mypage/index" />">MyPage</a></li>
			 <li><a href="<c:url value="/j_spring_security_logout"/>">Logout</a></li>
		</sec:authorize>
	</ul>
</div>