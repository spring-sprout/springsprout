<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<page:defaultpage selected_menu="member">
<div class="site">
    <div id="profile" class="standard_form">
        <h1>회원 추가</h1>
        <form:form commandName="member" method="post">
        <p>
            <label>이메일</label>
            <form:input path="email" />
            <form:errors path="email" />
        </p>
        <p>
            <label>비밀번호</label>
            <form:password path="password" />
            <form:errors path="password" />
        </p>
        <p>
            <label>이름 </label>
            <form:input path="name" />
            <form:errors path="name" />
        </p>
        <br/><hr/><br/>
        <a href="<c:url value="/member/list"/>">취소</a>
        <input type="submit" value="저장" />
        </form:form>
    </div>
</div>
</page:defaultpage>