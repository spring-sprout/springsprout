<%@ tag pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
	<div id="paging">
	<input type = "hidden"  id = "allParam" value = "${context.orderParam}&${context.searchParam}" />
		<c:if test="${context.paging.totalRowsCount >= context.paging.size}">
		    <c:if test="${context.paging.currentPage != 1}">
		       	<a id="prevPage" href="javascript:;" onclick="javascript:goPaging('${context.paging.currentPage-1}');"><span id="prePage" >이전</span></a>
		    </c:if>
		    <c:if test="${context.paging.currentPage == 1}">
		       	<span id="noprePage" >이전</span>
		    </c:if>
			<span class="numbox">		
			<c:if test="${context.paging.beginPage > 1}">
		    	<a href="javascript:;" onclick="javascript:goPaging('1');"> <span class="num">1</span> </a>
		    	<span class="interword">...</span>
			</c:if>
		    <c:forEach begin="${context.paging.beginPage}" end="${context.paging.endPage}" var="current" >
		        <c:choose>
		            <c:when test="${current == context.paging.currentPage}">
		        		<span class="selected">${current}</span></a>
		            </c:when>
		            <c:otherwise>
		        		<a href="javascript:;" onclick="javascript:goPaging('${current}');"><span class="num">${current}</span></a>
		            </c:otherwise>
		        </c:choose>
		    </c:forEach>
		   
		    <c:if test="${context.paging.endPage != context.paging.totalPage}">
		    	<span class="interword">...</span>
		        <a href="javascript:;" onclick="javascript:goPaging('${context.paging.totalPage}');"><span class="num">${context.paging.totalPage}</span></a>
			</c:if>
				</span>
			<c:if test="${context.paging.currentPage != context.paging.totalPage}">
		        <a href="javascript:;" onclick="javascript:goPaging('${context.paging.currentPage +1}');"><span id="nextPage" >다음</span> </a>
			</c:if>
			<c:if test="${context.paging.currentPage == context.paging.totalPage}">
		       <span id="nonextPage" >다음</span>
			</c:if>
		</c:if>
		<c:if test="${context.paging.totalRowsCount < context.paging.size}">
			 <span id="noprePage" >이전</span>
			 <span class="numbox"><span class="selected">1</span></span>
			 <span id="nonextPage" >다음</span>
		</c:if>
		<select name="p_size" id="p_size" onChange="javascript:goPaging('1');">
			<c:forEach items="${context.paging.pageSizes}" var="currentSize">
				<c:choose>
				<c:when test="${currentSize == context.paging.size}">
					<option value="${currentSize}" selected="selected">${currentSize}</option>
				</c:when>
				<c:otherwise>
					<option value="${currentSize}">${currentSize}</option>
				</c:otherwise>
				</c:choose>
			</c:forEach>
		</select>
	</div>
