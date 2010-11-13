<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <a id="add" href="<c:url value="/role/add"/>">Add</a>
 <br><hr>
 <c:if test="${empty roleList}">
     현재 등록된 그룹이 없습니다.
 </c:if>
 <c:if test="${!empty roleList}">
     <h3>그룹 목록</h3>
     <table>
         <tr>
             <th>id</th>
             <th>이름</th>
             <th>설명</th>
         </tr>
     <c:forEach var="role" items="${roleList}">
         <tr>
             <td>${role.id}</td>
             <td><a href="<c:url value="/role/${role.id}"/>">${role.name}</a></td>
             <td>${role.descr}</td>
         </tr>
     </c:forEach>
     </table>
 </c:if>
