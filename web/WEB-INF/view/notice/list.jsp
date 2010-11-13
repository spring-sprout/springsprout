<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

 <a id="add" href="<c:url value="/notice/add"/>">Add</a>
 <br><hr>
 <c:if test="${empty list}">
     현재 등록된 공지가 없습니다.
 </c:if>
 <c:if test="${!empty list}">
     <h3>공지 목록</h3>
     <table>
         <tr>
             <th>id</th>
             <th>제목</th>
         </tr>
     <c:forEach var="notice" items="${list}">
         <tr>
             <td>${notice.id}</td>
             <td><a href="<c:url value="/notice/${notice.id}"/>">${notice.title}</a></td>
         </tr>
     </c:forEach>
     </table>
 </c:if>
