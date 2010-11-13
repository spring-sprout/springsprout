<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<page:defaultpage banner_name="main-banner" selected_menu="home" returnURL="/index">
<script type="text/javascript">
$(function(){
	$("#signupform").validate();

	$("#email").blur(function () {
		if($("label.error[for = 'email']:visible").size() === 0){
			$.getJSON("emailconfirm", { email: $(this).attr("value") },
				function(data){
					if(data["isDuplicated"]){
						alert("이미 등록되어있는 이메일 입니다.");
					}
				}
			);
		}
	});
});
</script>
<div id="content">
    <div id="signup" class="standard_form">
        <form:form id="signupform" commandName="member" method="post">
            <h1>Sign up <a href="login">(log in)</a></h1>

            <label for="email" class="">Email Address </label>
            <form:input path="email" size="30" cssStyle="width: 25em;" cssClass="required email text" maxlength="30"/>
            <form:errors path="email" />

            <label for="password">Password</label>
            <form:password path="password" size="30" cssStyle="width: 15em;" cssClass="required text" maxlength="20"/>
            <form:errors path="password" />

            <label for="passwordConfirm">Confirm Password</label>
            <input name="passwordConfirm" size="30" style="width: 15em;" class="required text" equalTo="#password" type="password" maxlength="20"/>

            <label for="name">Name</label>
            <form:input path="name" size="30" cssStyle="width: 25em;" cssClass="required text" maxlength="30"/>
            <form:errors path="name" />
        
        	<label for="notification">Notification Service</label>
        	이메일 수신: <form:checkbox title="이메일 수신" path="isAllowedEmail"  value="true" /> | 
        	구글토크 메시지 수신: <form:checkbox title="구글토크 메시지 수신" path="isAllowedGoogleTalk" value="true"/>
        	
            <br/><br/>
            <input value="I agree, sign me up!" id="signup_button" type="submit">
        </form:form>
    </div>
</div>
</page:defaultpage>