<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<page:defaultpage selected_menu="member">
    <div class="site">
        <div id="profile" class="standard_form">
            <div>
            <a id="listButton" href="<c:url value="/member/list.do?${c.allParam}"/>">목록으로</a> |
            <a id="updateButton" href="<c:url value="/member/update/${member.id}.do?${c.allParam}"/>">수정</a> |
            <a id="deleteButton" href="<c:url value="/member/delete/${member.id}.do?${c.allParam}"/>">삭제</a>
            </div>

            <div>
            이름: ${member.name}<br/>
            이메일: ${member.email}<br/>
            상태: ${member.status.descr}<br/>
            그룹: <br/>
            <ul>
            <c:forEach items="${member.roles}" var="role">
                <li>${role.name} | ${role.descr}</li>
            </c:forEach>
            </ul>
            </div>
        </div>
    </div>
</page:defaultpage>
