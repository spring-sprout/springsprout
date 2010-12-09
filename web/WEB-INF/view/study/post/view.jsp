<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="s2c" tagdir="/WEB-INF/tags/s2c"%>
<%@ taglib prefix="s" tagdir="/WEB-INF/tags/study"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<style type="text/css">
#bottomFull { width: 99%; }
.post-image-thumbnail-default { margin: 2px; border: 1px green solid; }
.post-image-thumbnail-default:HOVER { cursor: pointer; border-width: 2px; }
.post-summary { font-size: 0.9em; list-style-type: none; }
.post-summary-title { float: left; width: 80%;}
.post-summary-title:VISITED { color: black; }
.post-summary-title:HOVER { cursor: pointer; text-decoration: underline; font-weight: bold; color: green; }
.post-summary-writer { float: right; width: 20%; }
</style>

<h2>Post Summary</h2>

<s2c:left-column>
    <s2c:module name="Text" more="더보기" url="${study.id}/post/textPost/list/0">
        <ol class="item-details">
            <s2c:portlet target="${texts}">
            <c:forEach var="text" items="${texts}">
				<li class="post-summary">
					<a href="#" class="post-summary-title" title="${text.title}" rel="${text.id}">${text.title}</a><div class="post-summary-writer">${text.writer.name}</div>
				</li>
			</c:forEach>
			</s2c:portlet>
        </ol>
    </s2c:module>
    
</s2c:left-column>
<s2c:right-column>
    <s2c:module name="Surbey" more="더보기" url="${study.id}/post/surbeyPost/list/0">
        <s2c:portlet target="${discuss}">
		</s2c:portlet>  
    </s2c:module>
    <s2c:module name="토론" more="더보기">
		<s2c:portlet target="${discuss}">
		</s2c:portlet>
    </s2c:module>
</s2c:right-column>
<s2c:bottom-column>
	<s2c:module name="Image" more="더보기" url="${study.id}/post/imagePost/list/0">
		<div id="thumbnailList" align="center" >
		<s2c:portlet target="${images}">
			<c:forEach var="image" items="${images}">
				<div class="thumbnail" style="float: left;" id="${image.id}">
					<img class="post-image-thumbnail-default" src="/images/userimage/${image.writer.email}/${image.imageFile.thumbNailName}" alt="${image.title}" title="${image.title}" />
				</div>
			</c:forEach>
		</s2c:portlet>
		</div>
	</s2c:module>
</s2c:bottom-column>
<script type="text/javascript" src="<c:url value="/resources/js/plugin/jqueryTools/jquery.tools.min.js"/>"></script>
<script type="text/javascript">
$(function(){ 
	var $actionArea = $('.active-area');
	$('.more').click( function(){
		$actionArea.load($(this).next().text());
		return false;
	});
	$('.post-summary-title').click( function(){
		var postId = $(this).attr('rel');
		$actionArea.load('${study.id}/post/textPost/' + postId + '?page=1');
		return false;
	});
	$('.thumbnail').click( function(){
		$actionArea.load('${study.id}/post/imagePost/listBySelectId/' + $(this).attr('id'));
	});
	
	SPROUT.common.util.cutStringUsingDot($('.post-summary').children(), 45);
});
</script>