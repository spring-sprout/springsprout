<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<style type="text/css">
    #list {
        margin-top:20px;
        list-style:none outside none;
        overflow:hidden;
        position:relative;
        width:100%;
    }

    #list li {
        float: left;
        width: 100%;
        padding:10px 0px;;
        border-bottom:1px solid #DDDDDD;
    }

    #list li img{
        width: 50px;
        height: 50px;
        float: left;
        margin-right:10px;
    }

    .link-title {
        font-size: 1.1em;
        font-weight: bold;
    }

    #link-writer-name {
        position: absolute;
    }

</style>

<page:defaultpage selected_menu="links" banner_text="즐겨찾기" ajaxlogin_yn="Y">
	<div id="content">
        <a href="<c:url value='/link/form'/>" class="button" style="font-size: 1.5em; font-weight: bolder;">링크 추가</a>
        <ul id="list">
        <c:forEach items="${list}" var="link" >
            <li>
                <img src="${link.writer.avatar}" title="${link.writer.name}"/>
                <a class="link-title" href="<c:url value='/link/${link.id}'/>">${link.title}</a><br/>
                <a href="${link.url}" target="_blank">${link.url}</a> | ${link.descr} <br/>
                <fmt:formatDate value="${link.created}" pattern="yyyy년 MM월 dd일 hh시 mm분"/> |
                <fmt:formatDate value="${link.updated}" pattern="yyyy년 MM월 dd일 hh시 mm분"/>
            </li>
        </c:forEach>
        </ul>
	</div>
<script type="text/javascript">
$(function() {

});
</script>
</page:defaultpage>