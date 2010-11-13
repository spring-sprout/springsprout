<%@ taglib prefix="s2c" tagdir="/WEB-INF/tags/s2c"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="selected_menu" required="true"%>
<%@ attribute name="banner_name" required="false"%>
<%@ attribute name="banner_text" required="false"%>
<%@ attribute name="analytics_yn" required="false"%>
<%@ attribute name="ajaxlogin_yn" required="false"%>
<%@ attribute name="returnURL" required="false"%>

<c:set scope="page"    value="${empty analytics_yn ? 'Y' : analytics_yn}" var="analytics_yn"/>
<c:set scope="page"    value="${empty ajaxlogin_yn ? 'N' : ajaxlogin_yn}" var="ajaxlogin_yn"/>
<c:set scope="page"    value="${empty banner_text?selected_menu:banner_text}" var="banner_text"/>
<c:set scope="request" value="${!empty returnURL?returnURL:null}" var="returnURL"/>

<s2c:page>
    <s2c:head>
    	<page:css_selected selected_menu="${selected_menu}"/>
        <page:js_selected  selected_menu="${selected_menu}"/>
    </s2c:head>
    <s2c:body analytics_yn="${analytics_yn}">
        <s2c:topbar ajaxlogin_yn="${ajaxlogin_yn}"/>
	    <s2c:header selected_menu="${selected_menu}"ajaxlogin_yn="${ajaxlogin_yn}" />
        <s2c:main>           
          	<s2c:banner banner_name="${banner_name}" banner_text="${banner_text}" />
            <jsp:doBody/>
        </s2c:main>
        <s2c:footer />
    </s2c:body>
</s2c:page>