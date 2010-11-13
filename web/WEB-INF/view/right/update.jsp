<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<page:defaultpage selected_menu="admin" analytics_yn="N">
<div id="content">
    <div class="standard_form">
        <form:form commandName="right" method="post">
        <p>
            <label>권한</label>
            <form:input path="name" size="30" cssStyle="width: 25em;" cssClass="text" maxlength="30"/>
            <form:errors path="name" />
           </p>
        <p>
            <label>설명</label>
            <form:input path="descr" size="30" cssStyle="width: 25em;" cssClass="text" maxlength="30"/>
            <form:errors path="descr" />
           </p>
        <br><hr><br>
        <a href="<c:url value="/right/${right.id}"/>">cancle</a>
        <input value="update" id="commit" type="submit">
        </form:form>
        </div>
    </div>
</page:defaultpage>
