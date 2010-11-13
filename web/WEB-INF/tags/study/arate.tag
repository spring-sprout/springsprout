<%@ tag pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ attribute name="currentRate" required="true" %>
<%@ attribute name="totlaRate" required="true" %>

<span title="실제 참석한 모임수/전체 모임수 * 100">참석율: </span>
<span title="현재 스터디 참석율: ${currentRate}% 전체 스터디 참석율: ${totlaRate}%">
    ${currentRate}%(${totlaRate}%)
</span>