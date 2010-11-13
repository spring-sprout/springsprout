<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<page:defaultpage selected_menu="admin" analytics_yn="N">
<div id="content">
    <div class="standard_form">
            <div>
                <a href="<c:url value="/right/update/${right.id}"/>">update</a>
                <a href="<c:url value="/right/delete/${right.id}"/>">delete</a>
                <a href="<c:url value="/right/list"/>">list</a>
            </div>
            <hr>
            <div>
                <h3>권한 상세 정보</h3>
                이름: ${right.name}<br/>
                설명 : ${right.descr}<br/>
            </div>
        </div>
    </div>
</page:defaultpage>
