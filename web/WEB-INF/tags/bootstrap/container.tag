<%@ tag pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="bootstrap" tagdir="/WEB-INF/tags/bootstrap"%>
<%@ taglib prefix="springsprout" uri="/META-INF/springsprout.tld" %>
<%@ attribute name="isSlimFooter" required="false" rtexprvalue="true" %>
<%@ attribute name="existFooter" required="false" rtexprvalue="true" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="description" content="스터디하고 책쓰고 발표하며 성장하는 봄싹 커뮤니티" />
    <meta name="language" content="en" />
    <title>봄싹 @2012</title>
    <link href="/static/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="/static/css/ss.css" rel="stylesheet">
    <!--[if lte IE 6]><script src="/static/js/ie6-warning.js"></script><script>window.onload=function(){e("/static/images/ie6/");}</script><![endif]-->
    <springsprout:headScriptFileLoad isOutTag="true"/>
    <springsprout:headScriptLoad isOutTag="true"/>
</head>
<body>
<springsprout:beginScriptLoadTag isOutTag="true"/>
<%--top navigation bar--%>
<bootstrap:topNavi/>
<div class="page-wrap">
    <div class="container">
        <jsp:doBody/>
    </div>
    <c:if test="${existFooter}">
        <c:if test="${isSlimFooter}">
            <bootstrap:slimFooter/>
        </c:if>
        <c:if test="${!isSlimFooter}">
            <bootstrap:footer/>
        </c:if>
    </c:if>
</div>
<script src="/static/js/jquery.min.js"></script>
<script src="/static/bootstrap/js/bootstrap.min.js"></script>
<script src="/static/js/springsprout.js"></script>
<springsprout:endScriptFileLoadTag isOutTag="true"/>
<springsprout:endScriptLoad isOutTag="true"/>
<springsprout:readyScriptLoad isOutTag="true"/>
<script>
    if(document.location.host === 'www.springsprout.org'){
        var _gaq = _gaq || [];
        _gaq.push(['_setAccount', 'UA-10470594-1'],
                ['_setAllowLinker', true],
                ['_setAllowHash', true],
                ['_trackPageview']);

        (function() {
            var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
            ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
            var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
        })();
    }
</script>
</body>
</html>