<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="bootstrap" tagdir="/WEB-INF/tags/bootstrap"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="description" content="스터디하고 책쓰고 발표하며 성장하는 봄싹 커뮤니티" />
    <meta name="language" content="en" />
    <title>봄싹 @2012</title>
    <link href="/static/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="/static/css/ss.css" rel="stylesheet">
    <link href="/static/css/ss-chat.css" rel="stylesheet">
    <script src="/static/jquery/jquery.min.js"></script>
    <script src="/static/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<bootstrap:main>
    <div id="chatWindow" class="row">
        <div class="span10">
            <ul>
                <li>백기선> Hi?</li>
                <li>백기선> Hi?</li>
                <li>백기선> Hi?</li>
                <li>김성윤> How RU?</li>
                <li>김성윤> How RU?</li>
                <li>김성윤> 잘 살고 있는가???</li>
                <li>백기선> 그렇다네...</li>
            </ul>
        </div>
        <div class="span2">
            <ul>
                <li>백기선</li>
                <li>김성윤</li>
            </ul>
        </div>
    </div>
    <div id="chatConsole" class="row">
        <form class="form-horizontal">
            <fieldset>
                <div class="control-group">
                    <label class="control-label" for="focusedInput">백기선@봄싹 /chat > </label>
                    <div class="controls">
                        <input class="input-xlarge focused" id="focusedInput" type="text" value="">
                    </div>
                </div>
            </fieldset>
        </form>
    </div>
</bootstrap:main>
<script type="text/javascript">

</script>
</body>
</html>