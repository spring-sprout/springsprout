<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<page:defaultpage selected_menu="admin" analytics_yn="N">
    <div id="content">
    <div class="standard_form">
        <form:form commandName="role" method="post">
        <p>
            <label for="name">그룹</label>
            <form:input path="name" size="30" cssStyle="width: 25em;"
                cssClass="text" maxlength="30" />
        </p>
        <p>
            <label for="descr">설명</label>
            <form:input path="descr" size="30" cssStyle="width: 25em;"
                cssClass="text" maxlength="30" />
        </p>
        <p>
            <label for="rights">권한</label>
            <form:checkboxes items="${allRights}" path="rights" delimiter="<br/>"
                itemLabel="descr" itemValue="id" />
        </p>
        <br />
        <hr>
        <br />
        <a href="<c:url value="/role/${role.id}"/>">cancle</a>
        <input value="update" id="commit" type="submit">
    </form:form></div>
    </div>
</page:defaultpage>
