<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="selected_menu" required="false"%>

<link href="<c:url value="/css/base.css"/>" media="screen" rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/jquery-ui-1.8.4.custom.css"/>" media="screen" rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/nyroModal.full.css"/>" media="screen" rel="stylesheet" type="text/css" />
 
<link href="<c:url value="/resources/js/markitup/skins/markitup/style.css"/>" rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/js/markitup/sets/default/style.css"/>" rel="stylesheet" type="text/css" />
 
<c:choose>
	<c:when test="${selected_menu == 'home'}" >
		<link href="<c:url value="/css/home.css"/>" media="screen" rel="stylesheet" type="text/css" />
        <link href="<c:url value="/resources/js/jScrollPane/jScrollPane.css"/>" rel="stylesheet" type="text/css" media="all" />
    </c:when>
	<c:when test="${selected_menu == 'studies'}" >
		<link href="<c:url value="/css/studies.css"/>" media="screen" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/css/study/index3.css"/>" media="screen" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/css/study/post.css"/>" media="screen" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/css/study/layout.css"/>" media="screen" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/resources/css/pikachoose/pikachoose.css"/>" media="screen" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/resources/css/jquery.autocomplete.css"/>" media="screen" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/resources/css/thickbox.css"/>" media="screen" rel="stylesheet" type="text/css" />
        <link href="<c:url value="/resources/css/crystal-stars.css"/>" media="screen" rel="stylesheet" type="text/css" />
        <link href="<c:url value="/resources/js/fg-menu/fg.menu.css"/>" media="screen" rel="stylesheet" type="text/css" />
        <link href="<c:url value="/resources/js/plugin/jwysiwyg/jwysiwyg/jquery.wysiwyg.css"/>" rel="stylesheet" type="text/css" />
        <link href="<c:url value="/resources/js/plugin/jqgrid/css/ui.jqgrid.css"/>" rel="stylesheet" type="text/css" />
        <link href="<c:url value="/resources/js/plugin/jhtmlarea/style/jHtmlArea.css"/>" rel="stylesheet" type="text/css" />
	</c:when>
	<c:when test="${selected_menu == 'seminars'}" >
		<link href="<c:url value="/css/seminars.css"/>" media="screen" rel="stylesheet" type="text/css" />
	</c:when>
	<c:when test="${selected_menu == 'wiki'}" >
		<link href="<c:url value="/css/wiki.css"/>" media="screen" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/js/jsTree/tree_component.css"/>" media="screen" rel="stylesheet" type="text/css" />
	</c:when>
    <c:when test="${selected_menu == 'New Wiki'}" >
		<link href="<c:url value="/css/newWiki.css"/>" media="screen" rel="stylesheet" type="text/css" />
	</c:when>
	<c:when test="${selected_menu == 'notice'}" >
		<link href="<c:url value="/css/noticesorter.css"/>" media="screen" rel="stylesheet" type="text/css" />
	</c:when>
	<c:when test="${selected_menu == 'mypage'}" >
		<link href="<c:url value="/css/mypage.css"/>" media="screen" rel="stylesheet" type="text/css" />
	</c:when>
	<c:when test="${selected_menu == 'blog'}" >

	</c:when>
    <c:when test="${selected_menu == 'Programming Terms'}" >
        <link href="<c:url value="/css/terms.css"/>" media="screen" rel="stylesheet" type="text/css" />
	</c:when>
	<c:when test="${selected_menu == 'admin'}" >
		<link href="<c:url value="/css/admin.css"/>" media="screen" rel="stylesheet" type="text/css" />
        <link href="<c:url value="/ext/resources/css/ext-all.css"/>" media="screen" rel="stylesheet" type="text/css"  />
        <link href="<c:url value="/ext/ux/css/RowEditor.css"/>" media="screen" rel="stylesheet" type="text/css"  />
	</c:when>
</c:choose>
	<c:if test="${selected_menu == 'studies' || selected_menu == 'seminars' }" >
		<link href="<c:url value="/resources/css/ui.timepickr.css"/>" media="screen" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/resources/css/tablesorter.css"/>" media="screen" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/resources/module/superfish-1.4.8/css/superfish.css"/>" media="screen" rel="stylesheet" type="text/css" />
	</c:if>
