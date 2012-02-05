<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="bootstrap" tagdir="/WEB-INF/tags/bootstrap"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<bootstrap:container>
<div class="row">
    <div class="span4">
        <form class="form-horizontal" action="<c:url value='j_spring_security_check'/>" method="POST">
            <fieldset>
                <legend>봄싹 들어가기 <a href="<c:url value="/signup"/>">(Signup)</a></legend>
                <div class="control-group">
                    <label class="control-label" for="login_field">Email</label>
                    <div class="controls">
                        <input id="login_field" class="input-xlarge" type='text' name='j_username' value="" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="password">Password</label>
                    <div class="controls">
                        <input id="password" class="input-xlarge" type='password' name='j_password' />
                        <p class="help-block"><a href="<c:url value="/forgetpassword"/>">(forgot password)</a></p>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="remember">Remember Me</label>
                    <div class="controls">
                        <label class="checkbox">
                            <input id="remember" type="checkbox" name="_spring_security_remember_me" checked="true" />
                        </label>
                    </div>
                </div>
                <c:if test="${ajaxlogin_yn == 'N'}">
                    <input type="hidden" value="${param.returnURL}" name = "spring-security-redirect" />
                </c:if>

                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">들어가기</button>
                    <button type="reset" class="btn">다시입력</button>
                </div>
            </fieldset>
        </form>
    </div>

    <div class="span6 pull-right">
        <form:form cssClass="form-horizontal" id="signupform" commandName="member" method="post" action="/signup">
            <fieldset>
                <legend>봄싹 가입하기</legend>
                <div class="control-group">
                    <label for="email" class="control-label">Email</label>
                    <div class="controls">
                        <form:input path="email" cssClass="input-xlarge" />
                        <form:errors path="email" />
                    </div>
                </div>
                <div class="control-group">
                    <label for="password" class="control-label">Password</label>
                    <div class="controls">
                        <form:password path="password" cssClass="input-xlarge" />
                        <form:errors path="password" />
                    </div>
                </div>
                <div class="control-group">
                    <label for="passwordConfirm" class="control-label">Confirm Password</label>
                    <div class="controls">
                        <input name="passwordConfirm" class="input-xlarge" equalTo="#password" type="password" />
                    </div>
                </div>
                <div class="control-group">
                    <label for="name" class="control-label">Name</label>
                    <div class="controls">
                        <form:input path="name" cssClass="input-xlarge" />
                        <form:errors path="name" />
                    </div>
                </div>
                <div class="control-group">
                    <label for="notification" class="control-label">Notification Service</label>
                    <div class="controls">
                        <label class="checkbox">
                            <form:checkbox title="이메일 수신" path="isAllowedEmail"  value="true" />
                            이매일 수신
                        </label>
                        <label class="checkbox">
                            <form:checkbox title="구글토크 메시지 수신" path="isAllowedGoogleTalk" value="true"/>
                            구글토크 메시지 수신
                        </label>
                    </div>
                </div>
                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">가입하기</button>
                    <button type="reset" class="btn">다시입력</button>
                </div>
            </fieldset>
        </form:form>
    </div>
</div>
</bootstrap:container>