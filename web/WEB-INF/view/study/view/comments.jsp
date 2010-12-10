<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="s2c" tagdir="/WEB-INF/tags/s2c"%>
<%@ taglib prefix="s" tagdir="/WEB-INF/tags/study"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<style type="text/css">
    #meeting_comments {
        margin-top: 20px;
    }
    .comment_box {
        position: relative;
        height: 70px;
        margin-bottom: 10px;
    }

    .comment_box img {
        position: absolute;
    }

    .comment_box .writer_name {
        position:absolute;
        background:none repeat scroll 0 0 #797979;
        color:#FFFFFF;
        font-size:8pt;
        height:16px;
        bottom: -1px;
        letter-spacing:0.1em;
        line-height:19px;
        opacity:0.8;
        overflow:hidden;
        padding:1px;
        text-align:center;
        text-indent:0;
        width:70px;
    }

    .comment_box .comment {
        position: absolute;
        margin-left: 90px;
    }

    .comment_box .info_box {
        position: absolute;
        margin-left: 90px;
        bottom: 0;
        color: #a9a9a9;
    }

    .comment_box .info_box created {
        font-size: 80%;
    }

    .comment_box .info_box a {
        color:#4183C4;
    }

    hr {
        color: #FFFFFF;
        border-top: 1px dotted #d3d3d3;
    }

    #main-content .active-area .full {
        width:99.8%;
    }
</style>
<h2>의견</h2>
<s2c:full-column>
    <s2c:module name="의견 모아보기">
        <div id="meeting_comments">
            <c:forEach items="${study.meetings}" var="meeting">
                <c:forEach items="${meeting.comments}" var="comment">
                    <div class="comment_box">
                        <img src="${comment.writer.avatar}" alt="${comment.writer.name}" width="72" height="72"/>
                        <span class="writer_name">${comment.writer.name}</span>
                        <div class="comment">
                                ${comment.comment}
                        </div>
                    </div>
                    <div class="info_box">
                            <span class="created"><s:date value="${comment.created}" pattern="yyyy년 M월 d일 a h시 mm분"/></span>
                            <span><a href="/study/${study.id}/meeting/${meeting.id}">${meeting.title}</a></span>
                    </div>
                    <hr/>
                </c:forEach>
            </c:forEach>
        </div>
        </s2c:module>
</s2c:full-column>
