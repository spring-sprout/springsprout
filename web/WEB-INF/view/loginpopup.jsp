<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
        <link rel="shortcut icon" href="<c:url value="/images/favicon.ico"/>" type="image/x-icon"/>
        <title>SpringSprout</title>
        <style type="text/css">
            #login{
                /*background: url(http://farm1.static.flickr.com/52/128765190_6d87861c8c_z.jpg);*/
                height: 100%;
                margin-top: 0px;
            }
            #loginForm {
                padding-top: 30px;
                padding-left: 5px;
            }

            #loginForm h1, #loginForm label {
                color: green;
            }
        </style>
    </head>
    <body>
    <c:if test="${param.loginFail == 'true'}">
		<script type="text/javascript">
			$.growlUI('infomation', '로그인 정보가 정확하지 않습니다. 다시입력해주세요.');
		</script>
    </c:if>
    <div id="login" class="standard_form">
        <form id="loginForm" name="f" action="<c:url value='j_spring_security_check'/>" method="POST">
            <h1>봄싹 들어가기 <a href="<c:url value="/signup"/>">(아직 가입하지 않았다면..)</a></h1>
            <p>
                <label>이메일</label>
                <input type='text' name='j_username' value="" id="login_field" style="width: 25em;" tabindex="1" class="text email required"/>
            </p>
            <p>
                <label>패스워드 <a href="<c:url value="/forgetpassword"/>">(패스워드 까묵까묵..)</a></label>
                <input type='password' name='j_password' id="password" style="width: 25em;" tabindex="2" class="text required"/>
            </p>
            <p>
                <label class="label_checkbox">
                <input type="checkbox" name="_spring_security_remember_me" checked="true" /> 날 기억해주오~!</label>
            </p>
            <input name="commit" tabindex="3" value="로그인" type="submit">
            <input type="hidden" name="_to" value="${_to}" >
        </form>
    </div>
    <script type="text/javascript">
        $(document).ready(function(){
            $("#loginForm").validate();
            $('input[name=j_username]').focus();
        });
    </script>
    </body>
</html>