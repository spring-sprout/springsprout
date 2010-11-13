<%@ tag pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="value" required="true" %>
<%@ attribute name="title" required="true" %>
<%@ attribute name="valueid" %>

<li><span class="title">${title}:</span><span id="${valueid}">${value}</span></li>