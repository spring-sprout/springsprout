<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<page:defaultpage selected_menu="admin" analytics_yn="N">
<div id="content">
      <div id="profile" class="standard_form">
          <div>
              <a href="<c:url value="/role/update/${role.id}"/>">update</a>
              <a href="<c:url value="/role/delete/${role.id}"/>">delete</a>
              <a href="<c:url value="/role/list"/>">list</a>
          </div>
          <hr>
          <div>
              <h3>그룹 상세 정보</h3>
              이름: ${role.name}<br/>
              설명 : ${role.descr}<br/>
              권한: <br/>
              <ul>
              <c:forEach items="${role.rights}" var="right">
                  <li>${right.name} | ${right.descr}</li>
              </c:forEach>
              </ul>
          </div>
      </div>
  </div>
</page:defaultpage>
