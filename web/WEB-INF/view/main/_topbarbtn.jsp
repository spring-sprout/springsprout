<%--
  Created by IntelliJ IDEA.
  User: helols
  Date: 2009. 10. 22
  Time: 오전 2:39:58
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:if test="${ajaxlogin_yn == 'Y'}">
<script type="text/javascript">
	$(document).ready(function(){
         $("#loginbtn").toggle(function(){
                $('#menu_bar').fadeOut(200);
                $('#loginbox').animate({ top:"21px" });
                $('.j_username').focus();
            },function(){
                $('#loginbox').animate({ top:"-47px" });
                $('#menu_bar').fadeIn(200);
        });
	});
</script>
</c:if>
<c:if test="${ajaxlogin_yn != 'Y'}">
<c:url value="/login" var="loginUrl">
    <c:param name="returnURL" value="${returnURL}"/>
</c:url>
<script type="text/javascript">
	$(document).ready(function(){
		$("#loginbtn").click(function(){
            $(document).attr("location", '${loginUrl}');
        });
	});
</script>
</c:if>

<div id="buttonmenu">
    <sec:authorize ifNotGranted="ROLE_MEMBER, ROLE_ADMIN">
        <img id="loginbtn" class="action" src="<c:url value="/images/loginbutton.png"/>" width="65" height="18" alt="login" />
        <a href="<c:url value="/signup"/>">
            <img src="<c:url value="/images/signupbutton.png"/>" width="65" height="18" alt="signup" />
        </a>
    </sec:authorize>
    <sec:authorize ifAnyGranted="ROLE_ADMIN">
        <a href="<c:url value="/admin/index"/>">
            <img src="<c:url value="/images/adminbutton.png"/>" width="65" height="18" alt="admin" />
        </a>
    </sec:authorize>
    <sec:authorize ifAnyGranted="ROLE_MEMBER, ROLE_ADMIN">
        <a href="<c:url value="/mypage/index" />">
            <img src="<c:url value="/images/mypagebutton.png"/>" width="65" height="18" alt="mypage" />
        </a>
        <a href="<c:url value="/j_spring_security_logout"/>">
            <img src="<c:url value="/images/logoutbutton.png"/>" width="65" height="18" alt="logout" />
        </a>
    </sec:authorize>
</div>