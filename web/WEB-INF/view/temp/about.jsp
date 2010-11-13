<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<page:defaultpage banner_name="main-banner" selected_menu="home">
<div id="content">
    <h1>${member.name}</h1>
    <p>
        ID: ${member.id}
    </p>
    <p>
        EMail: ${member.email}
    </p>
    <p>
        Joined: ${member.joined}
    </p>
    <p>
        Blog: ${member.blog}
    </p>
    <p>
        Role:
        <c:forEach items="${member.roles}" var="role">
            ${role.name} | 
        </c:forEach>
    </p>
</div>
</page:defaultpage>