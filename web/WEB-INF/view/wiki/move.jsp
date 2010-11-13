<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="sh" tagdir="/WEB-INF/tags/sh"%>
<%@ taglib prefix="wiki" tagdir="/WEB-INF/tags/wiki"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<page:defaultpage selected_menu="wiki">
<script type="text/javascript">
$(function() {
	$('#wiki-total-menu').tree({
		ui:{theme_name  : "apple"}
		, rules : {
			renameable  : "none",    // which node types can the user select | default - all
	        deletable   : "none",    // which node types can the user delete | default - all
	        creatable   : "none"
		}
	});
});
</script>
	<sh:setting/>
	<div class="site">
		<form:form method="POST" id="moveForm">
			모두(펼침, 닫침)
			<div id="wiki-total-menu">
				<input type="radio" name="newParentId" value="" />최상위
				<ul class="wikichild" >
					<c:forEach items="${rootMenus}" var="eachDoc" varStatus="status">
						<li ${fn:length(eachDoc.childs) > 0 ? '' : 'class="leaf"'}>
							<wiki:radiolink wikiDocument="${eachDoc}" currentDocument="${wikiDocument}" />
							<wiki:radiochild wikiDocument="${eachDoc}" currentDocument="${wikiDocument}" /></li>
					</c:forEach>
				</ul>
			</div>
			<div style="clear: both;">
				<input type="submit" value="저장"/>
			</div>
		</form:form>
	</div>
</page:defaultpage>