<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<page:defaultpage banner_name="main-banner" selected_menu="home">
<script type="text/javascript">
$(document).ready(function(){
	$("#loginForm").validate();
	$('input[name=j_username]').focus();
});
</script>    
<div id="content">
    <c:if test="${param.loginFail == 'true'}">
		<script type="text/javascript">
			$.growlUI('infomation', 'Your login attempt was not successful, try again.');
		</script>
    </c:if>            
    <div id="login" class="standard_form">
        <form id="loginForm" name="f" action="<c:url value='j_spring_security_check'/>" method="POST">
            <h1>Login <a href="<c:url value="/signup"/>">(Signup)</a></h1>
            <p>
                <label>Email</label>
                <input type='text' name='j_username' value="" id="login_field" style="width: 25em;" tabindex="1" class="text email required"/>
            </p>
            <p>
                <label>Password <a href="<c:url value="/forgetpassword"/>">(forgot password)</a></label>
                <input type='password' name='j_password' id="password" style="width: 25em;" tabindex="2" class="text required"/>
            </p>
            <p>
                <label class="label_checkbox">
                <input type="checkbox" name="_spring_security_remember_me" checked="true" /> Remember Me</label>
            </p>
            <input name="commit" tabindex="3" value="Log in" type="submit">

            <c:if test="${ajaxlogin_yn == 'N'}">
                <input type="hidden" value="${param.returnURL}" name = "spring-security-redirect" />
            </c:if>
        </form>
    </div>
</div>

</page:defaultpage>