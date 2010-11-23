<%@ tag pageEncoding="utf-8" %>
<%@ attribute name="name" required="true"%>
<%@ attribute name="more" required="false"%>

<div class="module">
    <div class="mod-header">
        <h3>${name}</h3>
        <span style="float: right;">${more}</span>
    </div>
    <div class="mod-content">
        <jsp:doBody/>
    </div>
</div>