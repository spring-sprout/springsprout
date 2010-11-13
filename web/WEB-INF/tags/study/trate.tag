<%@ tag pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ attribute name="currentRate" required="true" %>
<%@ attribute name="totlaRate" required="true" %>

<span title="실제 참석한 모임수/전체 모임 참가 신청수 * 100">신뢰도: </span>
<span title="현재 스터디 신뢰도: ${currentRate}% 전체 스터디 신뢰도: ${totlaRate}%">
    ${currentRate}%(${totlaRate}%)
</span>