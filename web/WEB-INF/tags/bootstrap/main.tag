<%@ tag pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="bootstrap" tagdir="/WEB-INF/tags/bootstrap"%>
<bootstrap:topNavi/>
<div class="page-wrap">
    <div class="container">
        <jsp:doBody/>
    </div>
    <bootstrap:footer/>
</div>