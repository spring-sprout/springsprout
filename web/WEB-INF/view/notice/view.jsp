<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<page:defaultpage selected_menu="board" analytics_yn="N">
<div id="content">
      <div id="profile" class="standard_form">
          <div>
              <a href="<c:url value="/notice/update/${notice.id}"/>">update</a>
              <a href="<c:url value="/notice/delete/${notice.id}"/>">delete</a>
              <a href="<c:url value="/notice/list"/>">list</a>
          </div>
          <hr>
          <div>
              <h3>공지 상세 정보</h3>
              제목: ${notice.title}<br/>
              내용: ${notice.contents}
          </div>
      </div>
  </div>
</page:defaultpage>
