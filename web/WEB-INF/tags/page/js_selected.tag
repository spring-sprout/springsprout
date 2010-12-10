<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="selected_menu" required="false"%>
<script type="text/javascript" src="<c:url value="/resources/js/framework/jquery-1.4.2.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/framework/jquery-ui-1.8.4.custom.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/framework/jquery-ui-1.8.2/ui/i18n/jquery-ui-i18n.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/main.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/sprout.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/study/study.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/study/post.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/framework/jquery.validate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/framework/jquery.blockUI.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/framework/css.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/nyroModal/jquery.nyroModal-1.5.2.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/framework/jquery.easing.compatibility.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/form/jquery.form.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/fg-menu/fg.menu.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/json2.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/validationUtil.js"/>"></script>
<!-- ui.timepicker js -->
<script type="text/javascript" src="<c:url value="/resources/js/framework/jquery.utils.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/framework/jquery.strings.js"/>"></script>

<script type="text/javascript" src="<c:url value="/resources/js/markitup/jquery.markitup.pack.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/markitup/sets/default/set.js"/>"></script>
<c:choose>
    <c:when test="${selected_menu == 'home'}" >
        <script type="text/javascript" src="<c:url value="/resources/js/jScrollPane/jquery.mousewheel.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/jScrollPane/jquery.em.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/jScrollPane/jScrollPane.js"/>"></script>
    </c:when>
    <c:when test="${selected_menu == 'wiki'}" >
		<script type="text/javascript" src="<c:url value="/js/jsTree/tree_component.min.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/js/jsTree/option_wiki_tree.js"/>"></script>
	</c:when>
    <c:when test="${selected_menu == 'New Wiki'}" >
	</c:when>
	<c:when test="${selected_menu == 'notice'}" >
		<script type="text/javascript" src="<c:url value="/module/ckeditor/ckeditor.js"/>"></script>
	</c:when>
	<c:when test="${selected_menu == 'seminars'}" >
		<script type="text/javascript" src="<c:url value="/js/framework/jquery.tablesorter.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/js/framework/ui.timepickr.min.js"/>"></script>
	</c:when>
	<c:when test="${selected_menu == 'studies'}" >
		<script type="text/javascript" src="<c:url value="/resources/js/framework/jquery.tablesorter.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/framework/ui.timepickr.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/framework/pikachoose-min.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/autocomplete/jquery.ajaxQueue.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/autocomplete/jquery.bgiframe.min.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/autocomplete/thickbox-compressed.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/autocomplete/jquery.autocomplete.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/framework/ui.stars.min.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/resources/module/superfish-1.4.8/js/superfish.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/resources/module/superfish-1.4.8/js/hoverIntent.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/dialogUtil.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/plugin/jwysiwyg/jwysiwyg/jquery.wysiwyg.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/plugin/flickr/jquery.flickr.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/plugin/jqgrid/js/i18n/grid.locale-en.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/plugin/jqgrid/js/jquery.jqGrid.min.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/plugin/jhtmlarea/scripts/jHtmlArea-0.7.0.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/plugin/multipleFileUpload/jquery.MultiFile.pack.js"/>"></script>
	</c:when>
    <c:when test="${selected_menu == 'statistics'}" >
		<script type="text/javascript" src="http://www.google.com/jsapi"></script>
	</c:when>
</c:choose>
