<%@ tag pageEncoding="utf-8" %>
<%@ attribute name="name" required="true"%>
<%@ attribute name="more" required="false"%>
<%@ attribute name="url" required="false"%>
<style type="text/css">
.url { visibility: hidden; }
.more { float: right; }
</style>


<div class="module">
    <div class="mod-header">
        <h3>${name}</h3>
        <span class="more">${more}</span>
        <span class="url">${url}</span>
    </div>
    <div class="mod-content">
        <jsp:doBody/>
    </div>
</div>