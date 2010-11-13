<%@ taglib prefix="s2c" tagdir="/WEB-INF/tags/s2c"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="selected_menu" required="false"%>

<s2c:page>
    <s2c:head>
        <page:css_selected selected_menu="${selected_menu}"/>
        <page:js_selected  selected_menu="${selected_menu}"/>
    </s2c:head>
    <s2c:body analytics_yn="N">
    	<jsp:doBody/>
    </s2c:body>
</s2c:page>

