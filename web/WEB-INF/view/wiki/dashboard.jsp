<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="wiki" tagdir="/WEB-INF/tags/wiki" %>
<page:defaultpage selected_menu="wiki">
    <script type="text/javascript">
        $(function() {
            $('#wiki-total-menu').tree(option_wiki_tree);
        });
    </script>
    <div id="content">
        <div>
            <a href="<c:url value="/wiki/new/write"/>">새글</a>
        </div>

        <table style="width: 100%; text-align: left;">
            <colgroup>
                <col width="50%"/>
                <col width="50%"/>
            </colgroup>
            <tr>
                <td valign="top">
                    <h2 class="root-menu">전체메뉴</h2>

                    <div id="wiki-total-menu">
                        <ul class="wikichild">
                            <c:forEach items="${rootMenus}" var="eachDoc" varStatus="status">
                                <li ${fn:length(eachDoc.childs) > 0 ? '' : 'class="leaf"'}>
                                    <wiki:link wikiDocument="${eachDoc}"/>
                                    <wiki:child wikiDocument="${eachDoc}"></wiki:child>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </td>
                <td valign="top">
                    <h2 class="recent-list">최근업데이트된글</h2>
                    <ul>
                        <c:forEach items="${updatedList}" var="eachDoc">
                            <li><a href="<c:url value="${eachDoc.id}"></c:url>"> <c:out
                                    value="${eachDoc.key}"/></a> <fmt:formatDate
                                    pattern="yyyy.MM.dd HH:mm:ss z" value="${eachDoc.updateDate}"/></li>
                        </c:forEach>
                    </ul>
                </td>
            </tr>
        </table>
    </div>
</page:defaultpage>