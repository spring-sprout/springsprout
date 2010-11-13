<%@ tag pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ attribute name="title" required="true" %>
<%@ attribute name="path" required="true" %>
<%@ attribute name="rows" required="true" %>
<%@ attribute name="cols" required="true" %>
<%@ attribute name="id" required="false" %>
<%@ attribute name="style" %>

<p>
    <label>${title}</label>
    <form:textarea path="${path}" rows="${rows}" cols="${cols}" cssClass="text" id="${id}" cssStyle="${style}" />
    <form:errors path="${path}" cssClass="error" />
</p>