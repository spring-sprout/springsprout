<%@ tag pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ attribute name="ajaxlogin_yn" required="false"%>

<div id="spacehr">
    <div class="left">
    Site: <a class="redirect_link" href="<c:url value="/index?site_preference=normal"/>">Normal</a> | <a class="redirect_link" href="<c:url value="/m?site_preference=mobile"/>">Mobile</a>
    </div>
    <div id="buttonmenu">
    <sec:authorize ifNotGranted="ROLE_MEMBER, ROLE_ADMIN">
        <a id="loginbtn" href="<c:url value="/loginpopup"  />">
            <img class="action" src="<c:url value="/images/loginbutton.png"/>" width="65" height="18" alt="login" />
        </a>
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
</div>
<script type="text/javascript">
    $(function(){
        $("#loginbtn").nyroModal({width:600,height:400});
    });
</script>