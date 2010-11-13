<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="wiki" tagdir="/WEB-INF/tags/wiki" %>

<page:defaultpage selected_menu="New Wiki">
    <style type="text/css">
        #spacelist {
            list-style: none outside none;
            margin: 0 auto;
            overflow: hidden;
            position: relative;
            width: 45%;
            float: left;
        }

        #wikiList {
            list-style: none outside none;
            margin: 0 auto;
            overflow: hidden;
            position: relative;
            float: left;
            width: 53%;
        }

        #spacelist li, #wikiList li {
            border-top: 2px solid #4183C4;
            display: inline;
            float: left;
            margin: 8px 5px 0;
            padding: 2px 7px;
            position: relative;
            width: 92%;
        }

        #spacelist li img, #wikiList li img {
            width: 20px;
            height: 20px;
            float: left;
            margin-top: 2px;
        }

        #spacelist .name, #wikiList .title {
            float: left;
            font-size: 1.1em;
            margin-left: 5px;
            width: 70%;
        }

        #wikiList .date {
            font-size: 0.8em;
            float: right;
        }

        #spacelist .description, #wikiList .description {
            float: left;
            width: 90%;
            background: none repeat scroll 0 0 transparent;
            border: 1px solid #4183C4;
            height: 20px;
            margin: 5px 0px 0px 5px;
            padding: 5px 10px;
        }

        #wikiList .description {
            overflow:auto;
            height: 219px;
            width: 92%;
        }

        #wikiList .description * {
            margin: 0px;
            padding: 0px;
            font: 10px;
        }

        #wikiList li .description * li{
            border: none;
            display: list-item;
        }

        #wikiList .author{
            font-size: 0.8em;
            float: right;
            margin-left: 2px;
            margin-right: 20px;
        }

        .ulHead {
            background-color:#C3D9FF;
            border:1px #CCCCCC solid;
            color:#003366;
            font-size: 1.2em;
            font-weight: bold;
            text-align: center;
        }

        #spacelist li:hover, #wikiList li:hover {
            background-color: #BEE1F6;
        }

        #spacelist .name:hover, #wikiList .title:hover {
            font-weight: bold;
            color:#4378B6;
        }
    </style>
    <div id="content">
        <ul id="minitabs">
            <li><a href="<c:url value="/wiki/index" />" class="${minitab_index}">대쉬보드</a></li>
                <%--<li><a href="<c:url value="/wiki/favorite.do" />" class="${minitab_fav}">관심위키</a></li>--%>
        </ul>
        <div style="clear:both;"></div>
        <c:choose>
            <c:when test="${!empty spaceList}">
                <ul id="spacelist">
                    <li class="ulHead">모든 위키 목록</li>
                    <c:forEach items="${spaceList}" var="space" varStatus="vs">
                        <li class="round">
                            <img class="viewImg action" src="<c:url value="/images/wiki/globe3.jpg" />" title="설명보기"/>
                            <div class="name action" onclick="javascript:goSpace('${space.key}');" title="위키로 이동">${space.name}</div>
                            <div class="description round" style="display: none;">${space.descr}</div>
                        </li>
                    </c:forEach>
                </ul>
            </c:when>
            <c:otherwise>
                현재 등록된 위키가 없습니다.
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${!empty wikiList}">
                <ul id="wikiList">
                    <li class="ulHead">최근 작성한 페이지 목록</li>
                    <c:forEach items="${wikiList}" var="wiki" varStatus="vs">
                        <li class="round">
                            <a href="${wiki.link}"><img class="viewImg action" src="<c:url value="/images/wiki/Clipping-Text-icon.png" />" title="펼쳐보기"/></a>
                            <span class="title action" onclick="javascript:goPage('${wiki.link}');" title="페이지로 이동">${wiki.title}</span>
                            <span class="author">${wiki.author}</span>
                            <span class="date" title="<fmt:formatDate pattern="yyyy년 M월 d일 HH:mm:ss z" value="${wiki.published}"/>">
                                <fmt:formatDate pattern="M월 d일" value="${wiki.published}"/>
                            </span>
                        </li>
                    </c:forEach>
                </ul>
            </c:when>
            <c:otherwise>
                현재 등록된 위키가 없습니다.
            </c:otherwise>
        </c:choose>
    </div>
    <script type="text/javascript">
        var goPage = function(link) {
            $(document).attr("location", link);
        }

        var goSpace = function(key) {
            $(document).attr("location", "http://wiki.springsprout.org/display/" + key);
        }

        $(function() {
            $("#spacelist .viewImg").click(function () {
                $(this).siblings("div.description").slideToggle("fast");
            });

            $('#wikiList a').nyroModal({width: 700, height: 600});

            //JQuery
        });
    </script>
</page:defaultpage>