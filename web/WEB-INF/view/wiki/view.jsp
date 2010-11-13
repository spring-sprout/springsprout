<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="sh" tagdir="/WEB-INF/tags/sh"%>
<%@ taglib prefix="wiki" tagdir="/WEB-INF/tags/wiki"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<page:defaultpage selected_menu="wiki">
<script type="text/javascript">
$(function() {
	$('#wiki-child-tree').tree(option_wiki_tree);
});
</script>
	<sh:setting/>
	<div id="content">
		<div class="wikihead">
			<div class="wikipath">
				PATH : 
				<ul class="wikipath">
				<c:forEach items="${wikiDocument.paths}" var="path" varStatus="status">
					<li><wiki:link wikiDocument="${path}" /><c:if test="${not status.last}"> &nbsp;&gt;&gt; </c:if></li>
				</c:forEach>
				</ul>
			</div>
			<h1><wiki:title wikiDocument="${wikiDocument}"/></h1>
			<div class="wikimodifier">
				<ul class="wikimodifier">
					<li><a href="<c:url value="/wiki/${wikiDocument.id}/write"></c:url>">글수정</a></li>
					<li>
						<form style="display:inline;" action="<c:url value="/wiki/${wikiDocument.id}/delete"></c:url>" method="post" onsubmit="return confirm('삭제하시겠습니까?');">
							<button type="submit">글삭제 </button>
						</form>
					</li>
					<li><a href="<c:url value="/wiki/${wikiDocument.id}/move"></c:url>">글이동</a></li>
					<li><a href="<c:url value="/wiki/new/write"><c:param name="parentId" value="${wikiDocument.id}"/></c:url>">하위글작성</a></li>
				</ul>
			</div>
		</div>
		<div class="wikiview">
			${wikiDocument.bodyToHtml}
            <span class="document_updater right">
                "${wikiDocument.creator.name}"(이)가
                <fmt:formatDate value="${wikiDocument.createDate}" pattern="yyyy.MM.dd HH:mm:ss z" />에 최초 등록.
                <c:if test="${not empty wikiDocument.updater}">
                그리고,
                "${wikiDocument.updater.name}"(이)가
                <fmt:formatDate value="${wikiDocument.updateDate}" pattern="yyyy.MM.dd HH:mm:ss z" />에 수정.
                </c:if>
            </span>
		</div>
        <div class="wiki_childs left" style="width:400px;">
            <h2>하위 문서들</h2>
            <div id="wiki-child-tree">
            	<wiki:link wikiDocument="${wikiDocument}"/>
	            <c:if test="${fn:length(wikiDocument.childs) > 0}">
	                <wiki:child wikiDocument="${wikiDocument}"></wiki:child>
	            </c:if>
            </div>
        </div>
        <div class="wiki_histories right" style="width:400px;">
            <c:if test="${fn:length(wikiDocument.histories) > 0}">
            <h2>변경내역</h2>
            <div id="wiki-history-tree">
                <ul>
                    <c:forEach items="${wikiDocument.histories}" var="history">
                        <li class="bullet">
                          <a href="<c:url value='/wiki/history/${history.historyId}.do'/>" rel="gal" title="${history.key}"><strong>#${history.revision}</strong></a>
                          <span style="color:#999999; font-size:9pt;">on
                                <fmt:formatDate value="${history.updateDate}" pattern="yyyy/MM/dd hh:mm:ss" />
                                by ${history.updater.name}
                          </span>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            </c:if>
        </div>
	</div>
    <script type="text/javascript">
        $(function(){
            $("a[rel='gal']").nyroModal({
                width:800,
                height:600
            });
        });
    </script>
</page:defaultpage>