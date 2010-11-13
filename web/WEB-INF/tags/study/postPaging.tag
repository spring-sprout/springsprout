<%@ tag pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<style type="text/css">
.moveFirst:HOVER, .movePrev:HOVER, .moveNext:HOVER, .moveLast:HOVER, .movePageTo:HOVER {
	cursor: pointer;
	font-weight: bold;  
}
.pageNavigator { margin-top: 5px; }
</style>
<div class="pageNavigator" style="text-align: center; float: none;">
	<input type="hidden" id="currentPage" value="${ pagingInfo.now }" />
	<input type="hidden" id="limit" value="${ pagingInfo.limit }" />
	<input type="hidden" id="totalPage" value="${ pagingInfo.totalPage }" />
	<c:choose>
    	<c:when test="${ pagingInfo.now == 1 }">
			<span class="moveFirst" style="color: gray;">처음</span>|
			<span class="movePrev" style="color: gray;">이전</span>|
      	</c:when>
      	<c:otherwise>
			<span class="moveFirst" style="color: black;">처음</span>|
			<span class="movePrev" style="color: black;">이전</span>|
    	</c:otherwise>
	</c:choose>
	<c:forEach items="${pagingInfo.paging}" var="paging" varStatus="status" >
		<c:choose>
	    	<c:when test="${ paging == pagingInfo.now }">
	      		<span class="movePageTo" style="color: #1E90FF;"> ${ paging } </span>|
	      	</c:when>
	      	<c:when test="${ status.last}">
	      		<span class="movePageTo"> ${ paging } </span>
	      	</c:when>
	      	<c:otherwise>
				<span class="movePageTo"> ${ paging } </span>|
	    	</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:choose>
    	<c:when test="${ pagingInfo.now == pagingInfo.totalPage || pagingInfo.totalCount == 0}">
			<span class="moveNext" style="color: gray;">다음</span>|
			<span class="moveLast" style="color: gray;">마지막</span>
      	</c:when>
      	<c:otherwise>
      		<span class="moveNext" style="color: black;">다음</span>|
			<span class="moveLast" style="color: black;">마지막</span>
    	</c:otherwise>
	</c:choose>
	<br/>
	<span style="float: right;">
	<c:choose>
		<c:when test="${ pagingInfo.totalCount == 0 }">
			페이지 [<strong>0</strong>/0] / 총[0건]
      	</c:when>
      	<c:otherwise>
			페이지 [<strong>${ pagingInfo.now }</strong>/${ pagingInfo.totalPage }] / 총[${ pagingInfo.totalCount}건]
    	</c:otherwise>
	</c:choose>
	</span>
</div>
<script type="text/javascript">
$( function(){
	var page = 0, currentPage = parseInt($( '#currentPage').val()), totalPage = parseInt($( '#totalPage').val());;
	$( '.movePageTo').click( function() {
		page = parseInt($( this).text()) - 1;
		movePageTo( page);
	});
	
	$( '.moveNext').click( function() {
		page = currentPage;
		movePageTo( page);
	});
	
	$( '.movePrev').click( function() {
		page = currentPage - 2;
		movePageTo( page);
	});
	
	$( '.moveFirst').click( function() {
		page = 0;
		movePageTo( page);
	});
	
	$( '.moveLast').click( function() {
		page = totalPage - 1;
		movePageTo( page);
	});
	
	function movePageTo( page) {
		$('#listDiv').load('${study.id}/board/textPost/list/' + page);
	}
	
});
</script>