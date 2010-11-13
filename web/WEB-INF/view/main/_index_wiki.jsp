<%--
  Created by IntelliJ IDEA.
  User: helols
  Date: 2009. 10. 22
  Time: 오후 4:20:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="wiki left">
    <div class="titlebox">
        <ul class="mbtitle">
            <li>WIKI 최신업데이트 글</li>
        </ul>
    </div>
    <div class="cbox">
        <c:choose>
            <c:when test="${wikiList != null}">
                <ul>
                    <c:forEach items="${wikiList}" var="wiki">
                        <li class="bullet">
                            <a class="m_wiki" href="<c:url value="/wiki/${wiki.id}"/>">${wiki.smallKey}</a>
                            <div class="right">
                                <strong>${wiki.updater.name}</strong>
                                <span style="color:#999999; font-size:8pt;">
                                on <fmt:formatDate value="${wiki.updateDate}" pattern="MM/dd hh:mm:ss" />
                                </span>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </c:when>
            <c:otherwise>
                Wiki 정보가 없습니다.
            </c:otherwise>
        </c:choose>
    </div>
</div>