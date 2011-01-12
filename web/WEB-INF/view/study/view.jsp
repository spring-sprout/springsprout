<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="s2c" tagdir="/WEB-INF/tags/s2c"%>
<%@ taglib prefix="s" tagdir="/WEB-INF/tags/study"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<page:defaultpage selected_menu="studies" banner_name="study-banner" ajaxlogin_yn="Y">
    <div id="container-header">
        <img src="${study.logo}" alt="${study.studyName}" class="logo70">
        <h1><a href="/study/${study.id}">${study.studyName}</a></h1>
    </div>
    <div id="main-content">
        <ul class="vertical tabs">
            <li class="active first">
                <a href="<c:url value="${study.id}/summary"/>"><strong>종합</strong></a>
            </li>
            <li>
                <a href="<c:url value="${study.id}/post"/>"><strong>게시판</strong></a>
            </li>
            <li>
                <a href="<c:url value="${study.id}/meetings"/>"><strong>모임</strong></a>
            </li>
            <li>
                <a href="<c:url value="${study.id}/members"/>"><strong>구성원</strong></a>
            </li>
            <li>
                <a href="<c:url value="${study.id}/comments"/>"><strong>의견</strong></a>
            </li>
            <li>
                <a href="<c:url value="${study.id}/calendar"/>"><strong>일정</strong></a>
            </li>
        </ul>
        <div class="active-area">
        </div>
</div>
</page:defaultpage>
<script type="text/javascript">
    $(function(){
        $("ul.tabs li a").click(function(){
            var parent = $(this).parent();
            parent.siblings().removeClass("active");
            parent.addClass("active");
            parent.addClass("loading");

            var url = $(this).attr('href');
            $.get(url, function(data, textStatus){
                $(".active-area").html(data);
                if(textStatus === "success") {
                    parent.removeClass("loading");
                }
            });
            
            return false;
        });

        $("ul.tabs li.active a").click();
    });
</script>
