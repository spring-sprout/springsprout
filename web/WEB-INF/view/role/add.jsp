<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<page:defaultpage selected_menu="admin" analytics_yn="N">
<div id="content">
    <div class="standard_form">
        <form:form commandName="role" method="post">
            <label for="name">그룹<br>
            <form:input path="name" size="30" cssStyle="width: 25em;" cssClass="text" maxlength="30"/> </label>
            <label for="descr">설명<br>
            <form:input path="descr" size="30" cssStyle="width: 25em;" cssClass="text" maxlength="30"/> </label>
            <br><hr><br>
            <a href="<c:url value="/role/list"/>">list</a>
            <input value="Add" id="commit" type="submit">
        </form:form>
    </div>
</div>
</page:defaultpage>