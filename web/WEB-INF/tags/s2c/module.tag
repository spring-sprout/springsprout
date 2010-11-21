<%@ tag pageEncoding="utf-8" %>
<%@ attribute name="name" required="true"%>

<div class="module">
    <div class="mod-header">
        <h3>${name}</h3>
    </div>
    <div class="mod-content">
        <jsp:doBody/>
    </div>
</div>