<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="sh" tagdir="/WEB-INF/tags/sh"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<page:defaultpage selected_menu="wiki">
	<!-- markItUp! -->
	<script type="text/javascript"
		src="<c:url value="/js/markitup/jquery.markitup.pack.js"/>"></script>
	<!-- markItUp! toolbar settings -->
	<script type="text/javascript"
		src="<c:url value="/js/markitup/sets/markdown/set.js"/>"></script>
	<!-- markItUp! skin -->
	<link rel="stylesheet" type="text/css"
		href="<c:url value="/js/markitup/skins/markitup/style.css"/>" />
	<!--  markItUp! toolbar skin -->
	<link rel="stylesheet" type="text/css"
		href="<c:url value="/js/markitup/sets/markdown/style.css"/>" />
	<sh:setting/>
	<script type="text/javascript">
<!--
$(document).ready(function()	{
	// Add markItUp! to your textarea in one line
	// $('textarea').markItUp( { Settings }, { OptionalExtraSettings } );
	$('#markItUp').markItUp(myMarkdownSettings);

	// You can add content from anywhere in your page
	// $.markItUp( { Settings } );
	$('.add').click(function() {
 		$.markItUp( { 	openWith:'<opening tag>',
						closeWith:'<\/closing tag>',
						placeHolder:"New content"
					}
				);
 		return false;
	});

	// And you can add/remove markItUp! whenever you want
	// $(textarea).markItUpRemove();
	$('.toggle').click(function() {
		if ($("#markItUp.markItUpEditor").length === 1) {
 			$("#markItUp").markItUpRemove();
			$("span", this).text("get markItUp! back");
		} else {
			$('#markItUp').markItUp(mySettings);
			$("span", this).text("remove markItUp!");
		}
 		return false;
	});
});
-->
	</script>


	<div id="content">
	<div id="wikiwrite" class="document_form">
		<form:form commandName="wikiEditor" method="POST">
			<table cellpadding="5">
				<colgroup>
					<col width="80%" />
					<col />
				</colgroup>
				<tr>
					<td valign="top" class="wikieditor">
						<p class="info">Click <a href="#" class="add">this link to insert content</a>
						from anywhere in the page or <a href="#" class="toggle">this one
						to <span>remove markItUp!</span></a></p>

						<div class="title">
							<form:input path="key" cssClass="text"
										readonly="${empty wikiEditor.id ? false : true}" />
							<form:errors path="key" />
						</div>
						<div class="body">
							<form:textarea id="markItUp" path="body"
									cssClass="body" cols="80" rows="20" /><form:errors
									path="body" />
						</div>
						<div class="command">
							<input type="submit" value="저장" />
						</div>
					</td>
					<td>
						<link href="<c:url value="/css/wiki_help.css"/>" media="screen"
						rel="stylesheet" type="text/css" />
						<c:import url="wikihelp.html"></c:import></td>
				</tr>
			</table>
		</form:form>
	</div>
	</div>
</page:defaultpage>