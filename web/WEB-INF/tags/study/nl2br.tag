<%@ tag pageEncoding="utf-8"%>
<%@ tag body-content="scriptless" import="springsprout.common.util.StringUtils" %>
<%@ attribute name="value" required="true" %>

<%= StringUtils.nl2br(value) %>