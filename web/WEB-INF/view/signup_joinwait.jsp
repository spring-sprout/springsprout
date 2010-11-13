<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<page:defaultpage banner_name="main-banner" selected_menu="home">
<div id="content">
    <div id="profile" class="standard_form">
        <form:form method="get">
            <h1>가입 승인 대기 중 입니다.</h1>

            <p>
			가입 인증 메일을 발송하였습니다.<br/>
			메일의 링크를 클릭하여 로그인 하시기 바랍니다.<br/>
			<br/>
			<a href="login">로그인</a> |
			<a href="confimmail.do?email=${email}" class="s_waitblock">인증메일 재발송</a> |
			<a href="checkmail.do?email=${email}">메일 확인</a>
			</p>
        </form:form>
    </div>
</div>
</page:defaultpage>