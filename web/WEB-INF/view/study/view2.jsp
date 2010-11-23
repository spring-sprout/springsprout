<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="s2c" tagdir="/WEB-INF/tags/s2c"%>
<%@ taglib prefix="s" tagdir="/WEB-INF/tags/study"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<style type="text/css">
    #main {
        background-color: #F7F7F7;
        color: #333333;
        font: 12px/1.4 arial, FreeSans, Helvetica, sans-serif;
    }
    #container-header {
        min-height: 5em;
        padding: 1em 1em 0 6em;
        position: relative;
    }
    #container-header img {
        display: block;
        font-size: 1em;
        left: 1em;
        overflow: hidden;
        position: absolute;
        top: 1em;
    }
    #container-header h1 {
        color: #3C78B5;
    }
    #main-content {
        min-width: 966px;
        padding: 0 1em;
    }
    ul.vertical {
        float: left;
        margin-right: -0.083em;
        position: relative;
        width: 11em;
    }
    ul.vertical li {
        margin:-0.083em 0 0;
    }
    ul.tabs {
        list-style-type: none;
        margin: 0;
        padding: 0;
        z-index: 5;
    }
    ul.tabs li {
        border: 0.083em solid #BBBBBB;
        display: block;
        margin: -0.083em -0.083em 0 0;
        padding: 0;
    }
    ul.vertical li.active {
        background-color: #FFFFFF;
        border-right: medium none;
        margin-left: -0.5em;
    }
    ul.vertical li.first {
        margin-top:1em;
    }
    ul.tabs li a {
        background-color: #F0F0F0;
        color: #535353;
        display: block;
        font-weight: 700;
        text-decoration: none;
    }
    ul.tabs li.active a {
        background: none repeat scroll 0 0 #FFFFFF;
        color: #000000;
    }
    ul.tabs li.active.loading a {
        background: url("/images/indicator.gif") no-repeat scroll 95% center #FFFFFF;
    }
    ul.tabs li strong {
        display: block;
        overflow: hidden;
        padding: 0.4em 0.5em 0.2em;
        white-space: nowrap;
    }
    ul.tabs li a:hover {
        background: none repeat scroll 0 0 #FFFFFF;
        color: #535353;
    }
    .active-area {
        -moz-border-radius: 0.4em 0.4em 0.4em 0.4em;
        background-color: #FFFFFF;
        border: 1px solid #BBBBBB;
        min-height: 600px;
        position: relative;
        overflow-x:auto;
        margin-right: 10px;
        margin-bottom: 10px;
    }
    .active-area h2 {
        font-size: 1.6em;
        margin: 0.5em 0.5em 0;
    }
    #quicklinks {
        position: absolute;
        right: 8px;
        top: 10px;
        z-index: 1000;
    }
    ul.operations {
        list-style-type: none;
        margin: 0;
    }
    ul.operations li {
        float: left;
        margin: 0 0 0 4px;
        position: relative;
    }
    #primary, #secondary {
        width: 49.9%;
    }
    #main-content .column {
        float: left;
        margin: 0;
    }
    .content {
        margin:0 0.832em 0.832em;
    }
    .module {
        background-color: #FFFFFF;
        clear: both;
        margin-bottom: 0.5em;
        word-wrap: break-word;
    }
    .mod-header, .mod-content, .mod-footer {
        clear: both;
        display: block;
    }
    .mod-header {
        background: url("/images/study/mod_header_bg.png") repeat-x scroll 0 18px #FFFFFF;
        padding: 0.5em 0;
    }
    .mod-header h3 {
        background-color: #FFFFFF;
        color: #808080;
        display: inline;
        font-size: 1.5em;
        font-weight: 400;
        margin: 0;
        padding-right: 0.25em;
    }
    .mod-content {
        font-size: 1.167em;
        line-height: 1.429;
    }
</style>
<page:defaultpage selected_menu="studies" banner_name="study-banner" ajaxlogin_yn="Y">
    <div id="container-header">
        <img src="${study.logo}" alt="${study.studyName}" class="logo50">
        <h1>${study.studyName}</h1>
    </div>
    <div id="main-content">
        <ul class="vertical tabs">
            <li class="active first">
                <a href="<c:url value="${study.id}/summary"/>"><strong>Summary</strong></a>
            </li>
            <li>
                <a href="<c:url value="${study.id}/summary"/>"><strong>게시판</strong></a>
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
                <a href="<c:url value="${study.id}/summary"/>"><strong>일정</strong></a>
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
