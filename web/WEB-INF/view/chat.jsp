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
    <script src="/static/js/jquery.min.js"></script>
    <script src="/static/bootstrap/js/bootstrap.min.js"></script>
    <script src="/static/js/socket.io.js"></script>
</head>
<body>
<bootstrap:topNavi/>
<div class="page-wrap">
    <div class="container">
    <div id="chatWindow" class="row">
        <div class="span10">
            <ul id="chats">
            </ul>
        </div>
        <div class="span2">
            <ul id="users">
            </ul>
        </div>
    </div>
    <div id="chatConsole" class="row">
        <div class="span10">
            <form class="form-horizontal" id="msgForm">
                <fieldset>
                    <div class="control-group">
                        <label class="control-label" for="chatMessage">백기선@봄싹 /chat <i class="icon-chevron-right icon-white"></i> </label>
                        <div class="controls">
                            <input class="input-xlarge focused" id="chatMessage" type="text" value="">
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>
        <div class="span2">
            <i class="icon-question-sign icon-white"></i>
        </div>
    </div>

    </div>
</div>
<script type="text/javascript">
    $(document).ready(function(){
        $("#chatMessage").focus();

        var chat = io.connect('http://springsprout.org:8888/chat');
        console.log(chat);

        chat.on('connect', function () {
            chat.emit('getIn', {who: '${user.name}', email: '${user.email}', msg: '들어왔어요.'});
        });

        chat.on('message', function (data) {
            $("#chats").append("<li>" + data.who + "> " + data.msg + "</li>");
            console.log(data);
        });

        chat.on('refresh', function() {
            $("#users").empty();

            $.get("/chat/sessions", function(data){
                $.each(data, function(idx, item){
                    $("#users").append("<li>" + item.member.name + "</li>")
                });
            });
        });

        $("#msgForm").submit(function(e){
            var msg = $("#chatMessage").val();
            chat.emit('chat', {who: '${user.name}' , msg: msg});
            $("#chatMessage").val("");
            e.preventDefault();
        });
    });
</script>
</body>
</html>