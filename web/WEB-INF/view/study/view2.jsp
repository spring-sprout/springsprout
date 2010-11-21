<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="s2c" tagdir="/WEB-INF/tags/s2c"%>
<%@ taglib prefix="s" tagdir="/WEB-INF/tags/study"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    ul.item-details {
        display: inline-block;
        list-style-type: none;
        margin: 0;
        padding: 0;
        vertical-align: top;
        width: 100%;
    }
    ul.item-details dl {
        display: inline-block;
        margin-bottom: 0.5em;
        vertical-align: top;
        width: 100%;
    }
    ul.item-details dl dt, ul.item-details dl dd {
        display: block;
        float: left;
    }
    ul.item-details dl dt {
        color: #666666;
        margin-right: 1%;
        position: relative;
        width: 18%;
    }
    ul.item-details dl dd {
        display: block;
        float: left;
        width: 81%;
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
                <a href="#"><strong>Summary</strong></a>
            </li>
            <li>
                <a href="#"><strong>게시판</strong></a>
            </li>
            <li>
                <a href="#"><strong>모임</strong></a>
            </li>
            <li>
                <a href="#"><strong>구성원</strong></a>
            </li>
            <li>
                <a href="#"><strong>의견</strong></a>
            </li>
            <li>
                <a href="#"><strong>일정</strong></a>
            </li>
        </ul>
            <div class="active-area">
        <h2>Summary</h2>
        <div id="quicklinks">
            <ul class="operations">
                <li>가입</li>
                <li>관리</li>
            </ul>
        </div>
        <div id="primary" class="column">
            <div class="content">
                <div class="module">
                    <div class="mod-header">
                        <h3>정보</h3>
                    </div>
                    <div class="mod-content">
                        <ul class="item-details">
                            <li>
                                <dl>
                                    <dt>리더:</dt>
                                    <dd>${study.manager.name}</dd>
                                </dl>
                                <dl>
                                    <dt>모임수:</dt>
                                    <dd>${study.meetingCount}</dd>
                                </dl>
                                <dl>
                                    <dt>참석인원:</dt>
                                    <dd>${study.memberCount}/${study.maximumCount}</dd>
                                </dl>
                                <dl>
                                    <dt>시작일:</dt>
                                    <dd><s:date value="${study.startDay}"/></dd>
                                </dl>
                                <dl>
                                    <dt>종료일:</dt>
                                    <dd><s:date value="${study.endDay}"/></dd>
                                </dl>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="module">
                    <div class="mod-header">
                        <h3>최근 모임</h3>
                    </div>
                    <div class="mod-content">
                        
                    </div>
                </div>
            </div>
        </div>
        <div id="secondary" class="column">
            <div class="content">
                <div class="module">
                    <div class="mod-header">
                        <h3>소개</h3>
                    </div>
                    <div class="mod-content">
                        <s:nl2br value="${study.descr}"/>
                    </div>
                </div>
                <div class="module">
                    <div class="mod-header">
                        <h3>활동 내역</h3>
                    </div>
                    <div class="mod-content">

                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
    
</page:defaultpage>
<script type="text/javascript">
    $(function(){
        $("ul.tabs li").click(function(){
            $(this).addClass("active");
            $(this).siblings().removeClass("active");
        });

//        var activeAreaHeight = $(".active-area").height();
//        $("#main").height(activeAreaHeight + 10);
    });
</script>
