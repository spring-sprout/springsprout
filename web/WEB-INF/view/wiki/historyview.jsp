<%--
  Created by IntelliJ IDEA.
  User: Whiteship
  Date: 2009. 11. 27
  Time: 오전 11:40:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="w_notice" style="background: #FFFFCE; margin-bottom: 20px;">
    <strong>This page is an old version(${history.revision})</strong>
    View Differences | Restore this version <br/>
    ${history.updater.name} modified this on ${history.updateDate}
</div>
${preview}