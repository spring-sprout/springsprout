<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="description" content="스터디하고 책쓰고 발표하며 성장하는 봄싹 커뮤니티" />
    <meta name="language" content="en" />
    <title>봄싹 @2012</title>
    <link href="/static/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="/static/css/ss.css" rel="stylesheet">
    <script src="/static/sockjs/sockjs-0.1.min.js"></script>
    <script src="/static/jquery/jquery.min.js"></script>
    <script src="/static/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container-fluid">
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <a class="brand logo" href="/index/new">
                <img src="/resources/images/logo-2012.png" alt="springsprout" width="155" height="25">
            </a>
            <div class="nav-collapse">
                <ul class="nav">
                    <li><a href="/index/new"><i class="icon-home icon-white"></i> 홈</a></li>
                    <li><a href="/study"><i class="icon-star icon-white"></i> 스터디</a></li>
                    <li class="active"><a href="/chat"><i class="icon-comment icon-white"></i> 채팅</a></li>
                    <li><a href="/statistics/index"><i class="icon-signal icon-white"></i> 통계</a></li>
                    <li><a href="http://wiki.springsprout.org"><i class="icon-book icon-white"></i> 위키</a></li>
                </ul>
                <sec:authorize ifAnyGranted="ROLE_MEMBER">
                    <ul class="nav pull-right">
                        <li id="fat-menu" class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" id="userEmail">
                                <sec:authentication property="principal.username"/><b class="caret"></b>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="/mypage/index"><i class="icon-user"></i> My Page</a></li>
                                <sec:authorize ifAnyGranted="ROLE_ADMIN">
                                    <li><a href="/admin/index"><i class="icon-cog"></i> Admin</a></li>
                                </sec:authorize>
                                <li class="divider"></li>
                                <li><a href="/j_spring_security_logout"><i class="icon-off"></i> Logout</a></li>
                            </ul>
                        </li>
                    </ul>
                </sec:authorize>
                <sec:authorize ifNotGranted="ROLE_MEMBER">
                    <p class="navbar-text pull-right"><a href="/door">Login</a></p>
                </sec:authorize>
            </div>
        </div>
    </div>
</div>
<div class="page-wrap">
    <div class="container">
        <div class="row">
            <div class="panel span6">
                <form id="messageForm" class="form-inline">
                    <fieldset>
                        <div class="control-group">
                            <div class="controls row">
                                <div class="span0.5">
                                    <img src="${user.avatar}&amp;s=30"/>
                                </div>
                                <div class="span5">
                                    <input id="message" class="input-xlarge" type="text" name="message" value="Hello, 봄싹!" />
                                </div>
                            </div>
                        </div>
                        <button id="sendBtn" type="submit" class="btn-success">> send</button>
                        <button id="clearBtn" class="btn-danger">> clear</button>
                    </fieldset>
                </form>
                <ul class="attenders" id="chatSessions">
                    <%--// filled by JavaScript--%>
                </ul>
            </div>
            <div class="chatBody span6">
                <div id="messages"></div>
            </div>
        </div>
    </div>
    <div class="footer-outer">
        <footer class="main-footer">
            <div class="row linkBox">
                <div class="span3">
                    <h4>FAQ</h4>
                    <ul class="unstyled">
                        <li><a target="_blank" href="http://wiki.springsprout.org/Home">봄싹은?</a></li>
                        <li><a target="_blank" href="http://wiki.springsprout.org/Home">봄싹의 목적은?</a></li>
                        <li><a target="_blank" href="http://wiki.springsprout.org/Home">봄싹 스터디에 참여하려면?</a></li>
                    </ul>
                </div>
                <div class="span3">
                    <h4>Family Sites</h4>
                    <ul class="unstyled">
                        <li><a target="_blank" href="http://twitter.com/springsprout">봄싹 트위터</a></li>
                        <li><a target="_blank" href="http://groups.google.com/group/springsprout">봄싹 구글 그룹스</a></li>
                        <li><a target="_blank" href="http://wiki.springsprout.org">봄싹 위키</a></li>
                        <li><a target="_blank" href="http://springstudyclub.tistory.com/">봄싹 블로그</a></li>
                        <li><a target="_blank" href="http://jira.springsprout.org">봄싹 이슈트래커</a></li>
                        <li><a target="_blank" href="https://github.com/whiteship/springsprout">봄싹 Git(+코드 뷰어)</a></li>
                    </ul>
                </div>
                <div class="span3">
                    <h4>Developers</h4>
                    <ul class="unstyled">
                        <li><a target="_blank" href="https://github.com/helols">김성윤</a></li>
                        <li><a target="_blank" href="https://github.com/miracle0k">김정우</a></li>
                        <li><a target="_blank" href="https://github.com/KimJejun">김제준</a></li>
                        <li><a target="_blank" href="https://github.com/arawn">박용권</a></li>
                        <li><a target="_blank" href="https://github.com/outsideris">변정훈</a></li>
                        <li><a target="_blank" href="https://github.com/keesun">백기선</a></li>
                        <li><a target="_blank" href="https://github.com/seosh81">서승호</a></li>
                        <li><a target="_blank" href="https://github.com/miracle0k">석종일</a></li>
                    </ul>
                </div>
                <div class="span3">
                    <h4>Thanks To</h4>
                    <ul class="unstyled">
                        <li><a target="_blank" href="http://springsource.org">Spring</a></li>
                        <li><a target="_blank" href="http://www.hibernate.org/">Hibernate</a></li>
                    </ul>
                </div>
            </div>
            <p class="copyright">&copy; SpringSprout rocks! 2012</p>
        </footer>
    </div>
</div>
<script type="text/javascript">
   var sockId;
   $(document).ready(function(){
       var sock;

       function init() {
           sock = new SockJS('http://springsprout.org:8888/chat');

           sock.onopen = function() {
               console.log('open');
           };

           sock.onmessage = function(e) {
               console.log('message', e.data);

               if(e.data.lastIndexOf("sockId:", 0) === 0) {
                   sockId = e.data.substring(7);
                   $.get("/chat/in", {'sock': sockId, 'email': '${user.email}'}, function(data){
                       console.log(data);
                   });

                   //update other chatter's chatSession
                   sock.send("UPDATE SESSIONS");
                   return;
               }

               if(e.data.lastIndexOf("UPDATE SESSIONS", 0) === 0) {
                   fillChatSessions();
                   return;
               }

               $("#messages").append("<p>" + e.data + "</p>")
           };

           sock.onclose = function() {
               console.log('close');
           };
       };

       init(); //SockJS Initialize

       $(window).unload(function(){
           sock.send("CLOSE");
           sock.close();
       });

       $("#messageForm").submit(function(e){
           var msgDiv = $("#message");
           var msg = msgDiv.val();
           if(msg != null && msg.trim().length > 1) {
               var time = new Date();
               var chatMsg = "{name: ${user.name}, avatar: ${user.avatar}, email: ${user.email}, time: " + time + ", msg: " + msg + "}";
               sock.send(chatMsg);
               msgDiv.val("");
               msgDiv.focus();
           }
           e.preventDefault();
       });

       $("#clearBtn").click(function(e){
           $("#messages").empty();
           e.preventDefault();
       });

       // fill the chatSessions
       function fillChatSessions() {
           $.get("/chat/sessions", function(data){
               var chatSessionsDiv = $("#chatSessions");
               chatSessionsDiv.empty();
               $.each(data, function(i, item){
                   var chatSessionLi = "<li class='attender'><img src='" + item.member.avatar + "'/> </li>"
                   console.log(chatSessionLi);
                   chatSessionsDiv.append(chatSessionLi);
               });

               console.log(data.length);
               var chatSessionCntDiv = "<span>참석자 " + data.length + "명</span>";
               chatSessionsDiv.append(chatSessionCntDiv);
           });
       };

       fillChatSessions();

   });
</script>
</body>
</html>