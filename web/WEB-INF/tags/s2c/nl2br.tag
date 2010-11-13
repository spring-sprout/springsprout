<%@ tag body-content="scriptless" import="springsprout.common.util.StringUtils" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ attribute name="value" required="true" %>
<div class="descr">
<%= StringUtils.nl2br(value) %>
</div>