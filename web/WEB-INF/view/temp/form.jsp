<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<page:defaultpage banner_name="main-banner" selected_menu="studies">
<div id="content">
    <h1>권한설정</h1>
    <p>
        <label>회원</label>
        <input id="member" type="text" style="display: none;" name="memberId" />
        <input type="text" id="presenterAC" class="text" />
    </p>
    <p>
        <label>권한</label>
        <select id="role" name="roleId">
            <c:forEach items="${roles}" var="role">
                <option value="${role.id}">${role.name}</option>
            </c:forEach>
        </select>
    </p>
    <button class="action">권한추가</button>
</div>
<script type="text/javascript">
var $presenterAC = $('#presenterAC');
$presenterAC.autocomplete( '<c:url value="/ac/members"/>', {
    model: 'memberList',
    matchContains: true,
    scroll: true,
    scrollHeight: 300,
    matchCase: true,
    formatItem: function( member, i, total) {
        return member[0] + ' ' + member[1] + ' ' + i;
    },
    formatResult: function( member) {
        return member[1];
    }
});

$presenterAC.result( function( event, member, formatted) {
    if( member) $('#member').attr('value', member[0]);
});

$("button").click( function() {
    var url = '<c:url value="/temp/role/"/>' + $("#role").attr("value") + "/to/" + $("#member").attr("value");
    $(document).attr("location", url);
});
</script>
</page:defaultpage>