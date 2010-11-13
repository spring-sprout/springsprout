<%@ tag pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ attribute name="title" required="true" %>
<%@ attribute name="path" required="true" %>
<%@ attribute name="size" %>
<%@ attribute name="pstyle" %>

<p id="${path}row" style="${pstyle}">
    <label>${title}</label>
    <form:input path="${path}" cssClass="text" size="${size}" />
    <form:errors path="${path}" cssClass="error"/>
</p>