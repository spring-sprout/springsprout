<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                            <li class="active"><a href="/index/new"><i class="icon-home icon-white"></i> 홈</a></li>
                            <li><a href="/study"><i class="icon-star icon-white"></i> 스터디</a></li>
                            <li><a href="/chat"><i class="icon-comment icon-white"></i> 채팅</a></li>
                            <li><a href="/statistics/index"><i class="icon-signal icon-white"></i> 통계</a></li>
                            <li><a href="http://wiki.springsprout.org"><i class="icon-book icon-white"></i> 위키</a></li>
                        </ul>
                        <sec:authorize ifAnyGranted="ROLE_MEMBER">
                            <ul class="nav pull-right">
                                <li id="fat-menu" class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
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
                            <%--<p class="navbar-text pull-right">Logged in as <a href="/mypage/index">${currentUser.name}</a></p>--%>
                        </sec:authorize>
                        <sec:authorize ifNotGranted="ROLE_MEMBER">
                            <p class="navbar-text pull-right"><a href="/door">Login</a></p>
                        </sec:authorize>
                    </div><!--/.nav-collapse -->
                </div>
            </div>
        </div>
        <div class="page-wrap">
            <div class="container">
                <header class="slogan">
                    <h1>
                        <span class="kd">var</span> springSprout =
                        <a href="#" rel="popover" data-content="추운 겨울을 지나 꽃피는 봄이오면"
                           data-original-title="spring(...);"><span class="string">'spring'</span></a> +
                        <a href="#" rel="popover" data-content="새싹이 돋아나겠죠."
                           data-original-title="sprout(...);"><span class="string">'sprout'</span></a>;
                    </h1>
                    <p class="sub-slogan">우리는
                        <a href="#" rel="popover" data-content="관심있는 주제의 스터디에 참여도 하시고 멋진 개발자도 많이 만나세요."
                           data-original-title="we.study(...);">학습하고</a>
                        <a href="#" rel="popover" data-content="학습한 기술을 적용해볼 곳이 없다구요? 봄싹에서 여러 개발자와 같이 사용해 보세요."
                           data-original-title="we.develope(...);">개발하고</a>
                        <a href="#" rel="popover" data-content="발표, 번역, 저술 등 여러 방법으로 학습하고 적용해본 경험을 다른 개발자와 나눠보세요."
                           data-original-title="we.share(...);">나누고</a>
                        <a href="#" rel="popover" data-content="이 모든게 즐겁다면 여러분은 이미 봄싹 개발자입니다."
                           data-original-title="we.enjoy(...);">즐깁니다.</a>
                    </p>
                </header>
                <section class="content-sec">
                    <header class="sec-header">
                        <h2>스터디&nbsp;<small>학습하고 개발하고...</small></h2>
                    </header>
                    <ul class="studies">
                        <li class="study">
                            <div class="thumb">
                                <a href="http://www.springsprout.org/study/5485">
                                    <img alt="" src="http://farm6.static.flickr.com/5132/5471555620_b24a5ff6c5_m.jpg">
                                </a>
                            </div>
                            <div class="infos">
                                <h3>
                                    <a href="http://www.springsprout.org/study/5485">나는 프로그래머다나는 프로그래머다</a>
                                </h3>
                                <p class="desc">
                                    흥미로운 주제를 가지고 함께 문제를 풀어보고 자신의 해법을 공유하는 스터디 입니다.
                                    개발을 할 줄 몰라도 상관없습니다.
                                    대부분의 문제가 영어로 되어있어 이를 해석하는 능력도 필요합니다....
                                </p>
                            </div>
                            <div class="meeting">
                                <p><i class="icon-calendar"></i> 07월 09일 (토) 14:00</p>
                                <p><i class="icon-map-marker"></i> <a href="#">탐앤탐스</a></p>
                                <h4 class="meeting-title"><a href="http://www.springsprout.org/study/5485/meeting/5765">볼링 퀴즈</a></h4>
                                <p class="desc">
                                    스터디 재등록 가능 여부와
                                    참여 의사를 파악하기 위해 등록된 모임입니다.
                                </p>
                                <ul class="attenders">
                                    <li class="attender">
                                        <img src="http://www.gravatar.com/avatar/c8bb023d6f1160a126088cd83740a4ed?r=X&s=20">
                                    </li>
                                    <li class="attender">
                                        <img src="http://www.gravatar.com/avatar/c8bb023d6f1160a126088cd83740a4ed?r=X&s=20">
                                    </li>
                                    <li class="attender">
                                        <img src="http://www.gravatar.com/avatar/c8bb023d6f1160a126088cd83740a4ed?r=X&s=20">
                                    </li>
                                    <li class="attender">
                                        <img src="http://www.gravatar.com/avatar/c8bb023d6f1160a126088cd83740a4ed?r=X&s=20">
                                    </li>
                                    <li class="attender">
                                        <img src="http://www.gravatar.com/avatar/c8bb023d6f1160a126088cd83740a4ed?r=X&s=20">
                                    </li>
                                </ul>
                                <span class="attender-count">15명 참석자</span>
                            </div>
                        </li>
                        <li class="study">
                            <div class="thumb">
                                <a href="http://www.springsprout.org/study/5485">
                                    <img alt="" src="http://farm6.static.flickr.com/5132/5471555620_b24a5ff6c5_m.jpg">
                                </a>
                            </div>
                            <div class="infos">
                                <h3>
                                    <a href="http://www.springsprout.org/study/5485">나는 프로그래머다나는 프로그래머다</a>
                                </h3>
                                <p class="desc">
                                    흥미로운 주제를 가지고 함께 문제를 풀어보고 자신의 해법을 공유하는 스터디 입니다.
                                    개발을 할 줄 몰라도 상관없습니다.
                                    대부분의 문제가 영어로 되어있어 이를 해석하는 능력도 필요합니다....
                                </p>
                            </div>
                            <div class="meeting">
                                <p><i class="icon-calendar"></i> 07월 09일 (토) 14:00</p>
                                <p><i class="icon-map-marker"></i> <a href="#">탐앤탐스</a></p>
                                <h4 class="meeting-title"><a href="http://www.springsprout.org/study/5485/meeting/5765">볼링 퀴즈</a></h4>
                                <p class="desc">
                                    스터디 재등록 가능 여부와
                                    참여 의사를 파악하기 위해 등록된 모임입니다.
                                </p>
                                <ul class="attenders">
                                    <li class="attender">
                                        <img src="http://www.gravatar.com/avatar/c8bb023d6f1160a126088cd83740a4ed?r=X&s=20">
                                    </li>
                                    <li class="attender">
                                        <img src="http://www.gravatar.com/avatar/c8bb023d6f1160a126088cd83740a4ed?r=X&s=20">
                                    </li>
                                    <li class="attender">
                                        <img src="http://www.gravatar.com/avatar/c8bb023d6f1160a126088cd83740a4ed?r=X&s=20">
                                    </li>
                                    <li class="attender">
                                        <img src="http://www.gravatar.com/avatar/c8bb023d6f1160a126088cd83740a4ed?r=X&s=20">
                                    </li>
                                    <li class="attender">
                                        <img src="http://www.gravatar.com/avatar/c8bb023d6f1160a126088cd83740a4ed?r=X&s=20">
                                    </li>
                                </ul>
                                <span class="attender-count">15명 참석자</span>
                            </div>
                        </li>
                        <li class="study">
                            <div class="thumb">
                                <a href="http://www.springsprout.org/study/5485">
                                    <img alt="" src="http://farm6.static.flickr.com/5132/5471555620_b24a5ff6c5_m.jpg">
                                </a>
                            </div>
                            <div class="infos">
                                <h3>
                                    <a href="http://www.springsprout.org/study/5485">나는 프로그래머다나는 프로그래머다</a>
                                </h3>
                                <p class="desc">
                                    흥미로운 주제를 가지고 함께 문제를 풀어보고 자신의 해법을 공유하는 스터디 입니다.
                                    개발을 할 줄 몰라도 상관없습니다.
                                    대부분의 문제가 영어로 되어있어 이를 해석하는 능력도 필요합니다....
                                </p>
                            </div>
                            <div class="meeting">
                                <p><i class="icon-calendar"></i> 07월 09일 (토) 14:00</p>
                                <p><i class="icon-map-marker"></i> <a href="#">탐앤탐스</a></p>
                                <h4 class="meeting-title"><a href="http://www.springsprout.org/study/5485/meeting/5765">볼링 퀴즈</a></h4>
                                <p class="desc">
                                    스터디 재등록 가능 여부와
                                    참여 의사를 파악하기 위해 등록된 모임입니다.
                                </p>
                                <ul class="attenders">
                                    <li class="attender">
                                        <img src="http://www.gravatar.com/avatar/c8bb023d6f1160a126088cd83740a4ed?r=X&s=20">
                                    </li>
                                    <li class="attender">
                                        <img src="http://www.gravatar.com/avatar/c8bb023d6f1160a126088cd83740a4ed?r=X&s=20">
                                    </li>
                                    <li class="attender">
                                        <img src="http://www.gravatar.com/avatar/c8bb023d6f1160a126088cd83740a4ed?r=X&s=20">
                                    </li>
                                    <li class="attender">
                                        <img src="http://www.gravatar.com/avatar/c8bb023d6f1160a126088cd83740a4ed?r=X&s=20">
                                    </li>
                                    <li class="attender">
                                        <img src="http://www.gravatar.com/avatar/c8bb023d6f1160a126088cd83740a4ed?r=X&s=20">
                                    </li>
                                </ul>
                                <span class="attender-count">15명 참석자</span>
                            </div>
                        </li>
                    </ul>
                    <p class="more-link"><a href="/study">스터디 더보기 &raquo;</a></p>
                    <header class="sec-header">
                        <h2>책&nbsp;<small>읽고 공부하고...</small></h2>
                    </header>
                    <ul class="books thumbnails">
                        <li class="book">
                            <a class="thumbnail" target="_blank" href="http://blog.yes24.com/lib/adon/View.aspx?blogid=1423718&amp;goodsno=4744444&amp;idx=10351&amp;ADON_TYPE=B&amp;regs=b">
                                <img src="http://image.yes24.com/momo/TopCate108/MidCate01/10704373.jpg">
                                <p class="title">
                                    스프링3 레시피
                                </p>
                            </a>
                        </li>
                        <li class="book">
                            <a class="thumbnail" target="_blank" href="http://blog.yes24.com/lib/adon/View.aspx?blogid=1423718&goodsno=4020006&idx=7386&ADON_TYPE=B&regs=b">
                                <img src="http://image.yes24.com/momo/TopCate87/MidCate10/8691328.jpg">
                                <p class="title">
                                        토비의 스프링 3
                                </p>
                            </a>
                        </li>
                        <li class="book">
                            <a class="thumbnail" target="_blank" href="http://blog.yes24.com/lib/adon/View.aspx?blogid=1423718&goodsno=4425736&idx=8502&ADON_TYPE=B&regs=b">
                                <img src="http://image.yes24.com/momo/TopCate97/MidCate09/9686817.jpg">
                                <p class="title">
                                        스프링 시큐리티 3
                                </p>
                            </a>
                        </li>
                    </ul>
                </section>
                <section class="side-sec">
                    <header class="sec-header">
                        <h2>낙서장&nbsp;<small>나누고 즐깁니다...</small></h2>
                    </header>
                    <article>
                        <div class="graffiti-writer">
                            <div class="writer-thumb-wrap">
                                <img class="writer-thumb" src="http://www.gravatar.com/avatar/d3a3e1e76decd8760aaf9af6ab334264?r=X&s=28" />
                            </div>
                            <div class="writer-text holder">
                                <input type="text" class="span3" placeholder="나누고 즐겨보아요!!">
                            </div>
                            <div class="writer-text wrap hide">
                                <textarea rows="3" id="textarea" class="input-xlarge"></textarea>
                                <a href="/main/graffitiWrite" class="btn btn-primary">수다</a>
                            </div>
                        </div>
                        <ul class="stream-items">
                            <li class="stream-item">
                                <div class="writer-thumb-wrap">
                                    <img class="writer-thumb" src="http://www.gravatar.com/avatar/d3a3e1e76decd8760aaf9af6ab334264?r=X&s=28" />
                                </div>
                                <div class="writer-text">
                                    <div class="item-header"><strong>백기선</strong> <small class="item-time">on 02/18 22:52:56</small></div>
                                    <div class="item-content">
                                        아웃사이더님도 ㅋ. 봄싹분을 거기서 보니 더 반가웠어요 ~
                                    </div>
                                </div>
                            </li>
                            <li class="stream-item">
                                <div class="writer-thumb-wrap">
                                    <img class="writer-thumb" src="http://www.gravatar.com/avatar/d3a3e1e76decd8760aaf9af6ab334264?r=X&s=28" />
                                </div>
                                <div class="writer-text">
                                    <div class="item-header"><strong>백기선</strong> <small class="item-time">on 02/18 22:52:56</small></div>
                                    <div class="item-content">
                                        아웃사이더님도 ㅋ. 봄싹분을 거기서 보니 더 반가웠어요 ~
                                    </div>
                                </div>
                            </li>
                            <li class="stream-item">
                                <div class="writer-thumb-wrap">
                                    <img class="writer-thumb" src="http://www.gravatar.com/avatar/d3a3e1e76decd8760aaf9af6ab334264?r=X&s=28" />
                                </div>
                                <div class="writer-text">
                                    <div class="item-header"><strong>백기선</strong> <small class="item-time">on 02/18 22:52:56</small></div>
                                    <div class="item-content">
                                        아웃사이더님도 ㅋ. 봄싹분을 거기서 보니 더 반가웠어요 ~
                                    </div>
                                </div>
                            </li>
                            <li class="stream-item">
                                <div class="writer-thumb-wrap">
                                    <img class="writer-thumb" src="http://www.gravatar.com/avatar/d3a3e1e76decd8760aaf9af6ab334264?r=X&s=28" />
                                </div>
                                <div class="writer-text">
                                    <div class="item-header"><strong>백기선</strong> <small class="item-time">on 02/18 22:52:56</small></div>
                                    <div class="item-content">
                                        아웃사이더님도 ㅋ. 봄싹분을 거기서 보니 더 반가웠어요 ~
                                    </div>
                                </div>
                            </li>
                            <li class="stream-item">
                                <div class="writer-thumb-wrap">
                                    <img class="writer-thumb" src="http://www.gravatar.com/avatar/d3a3e1e76decd8760aaf9af6ab334264?r=X&s=28" />
                                </div>
                                <div class="writer-text">
                                    <div class="item-header"><strong>백기선</strong> <small class="item-time">on 02/18 22:52:56</small></div>
                                    <div class="item-content">
                                        아웃사이더님도 ㅋ. 봄싹분을 거기서 보니 더 반가웠어요 ~
                                    </div>
                                </div>
                            </li>
                            <li class="stream-item">
                                <div class="writer-thumb-wrap">
                                    <img class="writer-thumb" src="http://www.gravatar.com/avatar/d3a3e1e76decd8760aaf9af6ab334264?r=X&s=28" />
                                </div>
                                <div class="writer-text">
                                    <div class="item-header"><strong>백기선</strong> <small class="item-time">on 02/18 22:52:56</small></div>
                                    <div class="item-content">
                                        아웃사이더님도 ㅋ. 봄싹분을 거기서 보니 더 반가웠어요 ~
                                    </div>
                                </div>
                            </li>
                            <li class="stream-item">
                                <div class="writer-thumb-wrap">
                                    <img class="writer-thumb" src="http://www.gravatar.com/avatar/d3a3e1e76decd8760aaf9af6ab334264?r=X&s=28" />
                                </div>
                                <div class="writer-text">
                                    <div class="item-header"><strong>백기선</strong> <small class="item-time">on 02/18 22:52:56</small></div>
                                    <div class="item-content">
                                        아웃사이더님도 ㅋ. 봄싹분을 거기서 보니 더 반가웠어요 ~
                                    </div>
                                </div>
                            </li>
                            <li class="stream-item">
                                <div class="writer-thumb-wrap">
                                    <img class="writer-thumb" src="http://www.gravatar.com/avatar/d3a3e1e76decd8760aaf9af6ab334264?r=X&s=28" />
                                </div>
                                <div class="writer-text">
                                    <div class="item-header"><strong>백기선</strong> <small class="item-time">on 02/18 22:52:56</small></div>
                                    <div class="item-content">
                                        아웃사이더님도 ㅋ. 봄싹분을 거기서 보니 더 반가웠어요 ~
                                    </div>
                                </div>
                            </li>
                            <li class="stream-item">
                                <div class="writer-thumb-wrap">
                                    <img class="writer-thumb" src="http://www.gravatar.com/avatar/d3a3e1e76decd8760aaf9af6ab334264?r=X&s=28" />
                                </div>
                                <div class="writer-text">
                                    <div class="item-header"><strong>백기선</strong> <small class="item-time">on 02/18 22:52:56</small></div>
                                    <div class="item-content">
                                        아웃사이더님도 ㅋ. 봄싹분을 거기서 보니 더 반가웠어요 ~
                                    </div>
                                </div>
                            </li>
                            <li class="stream-item">
                                <div class="writer-thumb-wrap">
                                    <img class="writer-thumb" src="http://www.gravatar.com/avatar/d3a3e1e76decd8760aaf9af6ab334264?r=X&s=28" />
                                </div>
                                <div class="writer-text">
                                    <div class="item-header"><strong>백기선</strong> <small class="item-time">on 02/18 22:52:56</small></div>
                                    <div class="item-content">
                                        아웃사이더님도 ㅋ. 봄싹분을 거기서 보니 더 반가웠어요 ~
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </article>
                    <article class="notice-box">
                        <div class="notice-header">
                            <h4 class="sec-title">공지사항</h4>
                        </div>
                        <div class="notice-body">
                            <ul class="notices">
                                <li><a href="#">text</a></li>
                                <li class="hide">text2</li>
                            </ul>
                        </div>
                    </article>
                </section>
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
                                <li><a target="_blank" href="https://github.com/miracle0k">김정우</a></li>
                                <li><a target="_blank" href="https://github.com/KimJejun">김제준</a></li>
                                <li><a target="_blank" href="https://github.com/arawn">박용권</a></li>
                                <li><a target="_blank" href="https://github.com/outsideris">변정훈</a></li>
                                <li><a target="_blank" href="https://github.com/keesun">백기선</a></li>
                                <li><a target="_blank" href="https://github.com/seosh81">서승호</a></li>
                                <li><a target="_blank" href="https://github.com/miracle0k">석종일</a></li>
                                <li><a target="_blank" href="https://github.com/isyoon">김성윤</a></li>
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
        <script src="/static/jquery/jquery.min.js"></script>
        <script src="/static/bootstrap/js/bootstrap.min.js"></script>
        <script>
            $(document).ready(function(){
                $("a[rel=popover]")
                    .popover({placement:'bottom'})
                    .click(function(e) {
                        e.preventDefault()
                    });
            });
        </script>
    </body>
</html>