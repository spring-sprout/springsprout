<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" tagdir="/WEB-INF/tags/study"%>

<page:defaultpage banner_name="term-banner" selected_menu="Programming Terms" ajaxlogin_yn="Y">
<style type="text/css" media="screen">
    .hls { background: #D3E18A; }
</style>
	<s:defaultpage>
        <s:titlebar>
            <td class="title" align="left">
                <a href="<c:url value="/term/${term.id}"/>">${term.phrase}</a>
            </td>
            <td align="right" class="buttons">
                <c:if test="${isManagerOrAdmin}">
                    <a class="button confirmRequired" href="<c:url value="/term/update/${term.id}"/>" title="용어 수정">수정</a>
                </c:if>
                <a class="button" id="korTermAdd_btn" termId="${term.id}" href="<c:url value="/term/${term.id}/kor/add"/>" title="한글 용어 달기">새 한글 용어</a>
                <a class="button" href="<c:url value="/term/index"/>" title="용어 목록으로 이동">뒤로</a>
            </td>
        </s:titlebar>
        <img class="logo round" src="${term.member.avatar}" alt="${term.member.name}" />
        <s:content cssClass="term_main">
            <s:textrow title="등록자" value="${term.member.name}" />
            <s:daterow title="등록일" value="${term.created}" />
            <s:textrow title="한글용어갯수" value="${term.korTermCount}" />
            <s:textrow title="조회수" value="${term.viewCount}" />
        </s:content>
        <s:descrrow mainCssClass="mainDescr round" subCssClass="mainDetails" value="${term.details}">
            <c:if test="${term.tags != null}">
                <ul class="tagList" style="margin-top: 20px;">
                    <c:forEach items="${term.tags}" var="tag">
                        <li class="termTag action"><a href="javascript:tagClick('${tag.tag}');">${tag.tag}</a></li>
                    </c:forEach>
                </ul>
            </c:if>
        </s:descrrow>
        <div id="term_daum_diction_api">
            <ul class="c">
                <li class="daumSearchLabel"><img src="<c:url value="/images/term/daum.png" />" alt="Daum"/>영어 사전</li>
            </ul>
        </div>
        <div style="clear: both;">
            <div id="termCommentList">
            <c:if test="${term.comments != null}">
                <ul class="commentList">
                    <c:forEach items="${term.comments}" var="comment">
                    <li>
                        <div class="commentContents">${comment.comment}</div> 
                        <span class="commentWriter">${comment.writer.name}</span>
                        <span class="commentCreated">${comment.createdText}</span>
                    </li>
                    </c:forEach>
                </ul>
            </c:if>
            </div>
            <a id="termCommentAdd_btn" termId="${term.id}" href="<c:url value="/term/${term.id}/comment/add" />" class="right" style="clear: both;">댓글달기</a>
        </div>
        <div id="korTermTitlebar" style="clear:both;">
            한글용어(${term.korTermCount})
        </div>
        <div id="subcontents">
        <c:if test="${term.korTerms != null}">
            <ul id="korTermList">
            <c:forEach items="${term.korTerms}" var="korTerm">
                <c:set var="korTermObject" value="${korTerm}"/>
                <li class="korTermItem round" id="kor${korTerm.id}">
                    <div class="voteCreated">
                        <div class="korTerm_vote round" korTermIdVote="${korTerm.id}" title="up: ${korTerm.voteUp} down: ${korTerm.voteDown}">${korTerm.vote}</div>
                        <div class="korTerm_created">${korTerm.createdText}</div>
                    </div>
                    <div class="upDownMember ">
                        <div class="korTerm_vote_buttons">
                            <div class="korTerm_vote_up round">
                                <a href="javascript:voteUp('${term.id}', '${korTerm.id}');">Up
                                    <span class="vote${korTermVoteMap[korTermObject]}"></span>
                                </a>
                            </div>
                            <div class="korTerm_vote_down round">
                                <a href="javascript:voteDown('${term.id}', '${korTerm.id}');">Down
                                    <span class="vote${korTermVoteMap[korTermObject]}"></span>
                                </a>
                            </div>
                        </div>
                        <img class="round" src="${korTerm.member.avatar}" alt="${korTerm.member.name}" />
                    </div>
                    <div class="korTerm_phrase"><a name="${korTerm.smallPhrase}">${korTerm.korPhrase}</a></div>
                    <div class="kor_daum_diction_api">
                        <ul class="c">
                            <li class="daumSearchLabel"><img src="<c:url value="/images/term/daum.png" />" alt="Daum"/>국어 사전</li>
                        </ul>
                    </div>
                    <s:descrrow mainCssClass="korTerm_details round" subCssClass="descr" value="${korTerm.details}"></s:descrrow>
                    <%--<div class="korTerm_details round">${korTerm.details}</div>--%>
                    <div style="clear: both;">
                        <c:if test="${korTerm.comments != null}">
                            <ul class="commentList" style="clear: both;">
                                <c:forEach items="${korTerm.comments}" var="comment">
                                <li>
                                    <div class="commentContents">${comment.comment}</div>
                                    <span class="commentWriter">${comment.writer.name}</span>
                                    <span class="commentCreated">${comment.createdText}</span>
                                </li>
                                </c:forEach>
                            </ul>
                        </c:if>
                        <a class="right round" href="javascript:getKorCommentAddView('${term.id}', '${korTerm.id}');" style="clear: both;">댓글달기</a>
                    </div>
                    <div style="clear: both;">&nbsp;</div>
                </li>
            </c:forEach>
            </ul>
        </c:if>
        </div>
        <div id="t_korAdd" style="display: none;">
	    </div>
        <div id="t_commentAdd" style="display: none;">
	    </div>
	</s:defaultpage>
<script type="text/javascript">
    $.fn.extend({
        highlight: function(search, insensitive, hls_class) {
            var regex = new RegExp("(<[^>]*>)|(\\b" + search.replace(/([-.*+?^\$\{\}()|[\]\/\\])/g, "\\$1") + ")", insensitive ? "ig" : "g");
            return this.html(this.html().replace(regex, function(a, b, c) {
                return (a.charAt(0) === "<") ? a : "<strong class=\"" + hls_class + "\">" + c + "</strong>";
            }));
        }
    });

    $(document).ready(function() {
        $(".mainDetails").highlight('${term.phrase}', 1, "hls");

        $(".confirmRequired").click(function(e) {
            var msg = $(this).attr("title");
            if (msg != null)
                return confirm(msg + " 하시겠습니까?");
        });

        $("#korTermAdd_btn").click(function(e) {
            var memberIsHere = '${hasLoggedInUser}';
            if(memberIsHere === 'false')
                callback_ajaxError();
            else {
                getKorAddView($(this).attr("termId"));
            }
            return false;
        });

        $("#termCommentAdd_btn").click(function(e) {
            var memberIsHere = '${hasLoggedInUser}';
            if(memberIsHere === 'false')
                callback_ajaxError();
            else {
                getCommentAddView($(this).attr("termId"));
            }
            return false;
        });

        var daumEngWordUrl = 'http://apis.daum.net/dic/endic?apikey=385d07e5206c110185bf9867f2f351612c3f8006&q='
                + encodeURIComponent('${term.phrase}') + '&kind=WORD&callback=?&output=json';
        $.getJSON(daumEngWordUrl, function(data) {
            var list = $("#term_daum_diction_api .c");
            if (!data.channel.result) {
                list.append("검색 결과 없음!");
            } else {
                $.each(data.channel.item, function(count, state) {
                    var htmlText = "<li><a href='" + state.link + "'> -" + escapeHtml(state.description) + "</a><li>";
                    list.append(htmlText);
                });
            }
        });

        loadKorTermDaumSearch();
    });

    function tagClick(tag){
        var url = getUrl("index?s_keyword=" + tag);
        $(document).attr("location", url);
    }

    function loadKorTermDaumSearch(){
        $.each($(".korTerm_phrase"), function(count, state) {
            var self = $(this);
            var searchKeyword = self.text();
            var daumKorWordUrl = 'http://apis.daum.net/dic/krdic?apikey=385d07e5206c110185bf9867f2f351612c3f8006&q='
                    + encodeURIComponent(searchKeyword) + '&kind=WORD&callback=?&output=json&result=2';

            var listBox = $(self).siblings(".kor_daum_diction_api").children('.c');

            $.getJSON(daumKorWordUrl, function(data) {
                if (!data.channel.result) {
                    listBox.append("검색 결과 없음!");
                } else {
                    $.each(data.channel.item, function(count, state) {
                        var htmlText = "<li><a href='" + state.link + "'> -" + escapeHtml(state.description) + "</a><li>";
                        listBox.append(htmlText);
                    });
                }
            });
        });
    }

    callback_ajaxError = function(e, xhr, settings, exception) {
        console.log(e);
        console.log(exception);
        defaultGrowlUI('로그인 해주세요~');
    };

    function escapeHtml(str)
    {
        str = str.replace(/&amp;/g, "&");
        str = str.replace(/&lt;/g, "<");
        str = str.replace(/&gt;/g, " >");
        return str;
    }

    function getUrl(action) {
        return '<c:url value="/term/"/>' + action;
    }

    function getKorAddView(termId) {
        divLoad_get(getUrl(termId + '/kor/add'), '', '#t_korAdd', function() {
            blockInMessge($('#t_korAdd'));
        });
    }

    function getCommentAddView(termId) {
        divLoad_get(getUrl(termId + '/comment/add'), '', '#t_commentAdd', function() {
            blockInMessge($('#t_commentAdd'));
        });
    }

    function getKorCommentAddView(termId, korTermId) {
        divLoad_get(getUrl(termId + '/kor/' + korTermId + '/comment/add'), '', '#t_commentAdd', function() {
            blockInMessgeWhere('#kor' + korTermId, $('#t_commentAdd'));
        });
    }

    function voteUp(termId, korTermId) {
        var url = getUrl(termId + '/kor/' + korTermId + '/voteup');
        var memberIsHere = '${hasLoggedInUser}';
        if(memberIsHere === 'false')
            callback_ajaxError();
        else {
            sendAjax(url, termId);
        }
    }

    function voteDown(termId, korTermId) {
        var url = getUrl(termId + '/kor/' + korTermId + '/votedown');
        var memberIsHere = '${hasLoggedInUser}';
        if(memberIsHere === 'false')
            callback_ajaxError();
        else {
            sendAjax(url, termId);
        }
    }

    function sendAjax(url, termId) {
        $.getJSON(url, function(data, status) {
            if (status === 'success') {
                $('#subcontents').load(getUrl(termId) + ' ' + '#subcontents', function(){
                    loadKorTermDaumSearch();    
                });
                defaultGrowlUI(data.msg);
            } else {
                defaultGrowlUI("Ajax 요청이 실패했습니다.");
            }
        });
    }
</script>
</page:defaultpage>