<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<page:defaultpage selected_menu="mypage">
<div id="content">
    <div class="standard_form">
            <h1>개인 정보 수정 </h1>
            <hr/>
            <form:form commandName="member" method="post">
            <p>
                <label>현재 비밀번호</label>
                <form:password path="currentPassword" cssClass="text" />
                <form:errors path="currentPassword" />
            </p>      
            <p>
                <label>새로운 비밀번호</label>
                <form:password path="newPassword" cssClass="text"/>
                <form:errors path="newPassword" />
            </p>
            <p>
                <label>새로운 비밀번호 확인</label>
                <form:password path="newPasswordConfirm" cssClass="text"/>
                <form:errors path="newPasswordConfirm" />
            </p>
            <p>
                <label>이름</label>
                <form:input path="name" cssClass="text"/>
                <form:errors path="name" />
            </p>
            <p>
            	<label>블로그</label>
            	<form:input path="blog" cssClass="text"/>
                <form:errors path="blog" />
            </p>
            <p>
	          	<label for="notification">Notification Service</label>
	        	이메일 수신: <form:checkbox title="이메일 수신" path="isAllowedEmail" /> | 
	        	구글토크 메시지 수신: <form:checkbox title="구글토크 메시지 수신" path="isAllowedGoogleTalk"/>
	        	<label>사진은 <a href="http://en.gravatar.com/" target="_blank">Gravatar</a>에서 수정할 수 있습니다.</label>
       		</p>
            <br/><hr/><br/>
            <a href="<c:url value="/mypage/index"/>">취소</a>
            <input type="submit" value="저장" />
            </form:form>
        </div>
    </div>
</page:defaultpage>