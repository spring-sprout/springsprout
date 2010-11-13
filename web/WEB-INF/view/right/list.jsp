<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<a id="add" href="<c:url value="/right/add"/>">Add</a>
<br><hr>
<c:if test="${empty rightList}">
    현재 등록된 권한이 없습니다.
</c:if>
<c:if test="${!empty rightList}">
    <h3>권한 목록</h3>
    <table>
        <tr>
            <th>id</th>
            <th>이름</th>
            <th>설명</th>
        </tr>
    <c:forEach var="right" items="${rightList}">
        <tr>
            <td>${right.id}</td>
            <td><a href="<c:url value="/right/${right.id}"/>">${right.name}</a></td>
            <td>${right.descr}</td>
        </tr>
    </c:forEach>
    </table>
</c:if>
