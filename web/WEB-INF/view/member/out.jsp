<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<page:defaultpage selected_menu="member">
    <div class="site">
        <div id="profile" class="standard_form">
            <h1>탈퇴</h1>

            <p>
			회원 탈퇴시 개인 정보는 사라집니다.<br/>
			탈퇴사유를 입력해 주세요.<br/>
			</p>
			
            <form:form commandName="member" method="post">
            <p>
            	<label>탈퇴사유</label>
            	<form:input path="outReason" cssClass="text"/>
                <form:errors path="outReason" />
            </p>
            <br/><hr/><br/>
            <a href="<c:url value="/mypage/index"/>">취소</a>
            <input type="submit" value="저장" />
            </form:form>
        </div>
    </div>
</page:defaultpage>