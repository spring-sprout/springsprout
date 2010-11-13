<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="s2c" tagdir="/WEB-INF/tags/s2c"%>
<page:defaultpage banner_name="term-banner" selected_menu="Programming Terms" ajaxlogin_yn="N">
    <div id="content">
        <div class="search">
            <input type="text" name="s_keyword" />
            <input id="termSearchBtn" type="button" value="검색" class="button"/>
        </div>
        <div class="buttons">
            <a class="button confirmRequired" href="<c:url value="/term/add"/>">새 용어 등록</a>
            <a style="float: right;" href="http://twitter.com/devterms" target="_blank">
                <img class="action" src="<c:url value="/images/twitter_followme.png"/>" width="30" height="30" alt="개발 용어 한글화 프로젝트 트위터"/>
            </a>
        </div>
        <br />
        <ul id="minitabs">
			<li><a href="<c:url value="/term/recent" />" class="${term_new}">신규 용어</a></li>
			<li><a href="<c:url value="/term/lonely" />" class="${term_lonely}">외로운 용어</a></li>
            <li><a href="<c:url value="/term/hot" />" class="${term_hot}">뜨거운 용어</a></li>
            <li><a href="<c:url value="/term/abc" />" class="${term_a2z}">A->Z</a></li>
            <li><a href="<c:url value="/term/zyx" />" class="${term_z2a}">Z->A</a></li>
            <li><a href="<c:url value="/term/my" />" class="${term_my}">내 용어</a></li>
            <li><a href="<c:url value="/term/mykor" />" class="${term_mine}">내 한글 용어</a></li>
            <li><a href="<c:url value="/term/favorite" />" class="${term_fav}">관심 용어</a></li>
        </ul>
		<div style="clear:both;"></div>
        <div id="termPage">
            <c:choose>
                <c:when test="${terms != null}">
                    <s2c:paging />
                    <ul id="termList">
                    <c:forEach items="${terms}" var="term">
                        <c:set var="termObject" value="${term}"/>
                        <li class="termItem round" viewCount="${term.viewCount}" korCount="${term.korTermCount}">
                            <div class="countBox">
                                <div class="korCount" title="한글 용어 ${term.korTermCount}개"><span class="countNum">${term.korTermCount}</span> <span class="countText">K</span></div>
                                <div class="viewCount" title="조회수 ${term.viewCount}"><span class="countNum">${term.viewCount}</span> <span class="countText">V</span></div>
                            </div>
                            <div class="termPhrase action" termId="${term.id}" onclick="javascript:viewTerm(${term.id});">${term.phrase}</div>
                            <div class="termFav action" termId="${term.id}" isFav="${member.favoriteTerms[termObject]}">
                            </div>
                            <div class="termDetails">${term.smallDetails}</div>
                            <div class="termCreated">${term.createdText}</div>
                            <div class="tagList">
                            <c:if test="${term.tags != null}">
                                <ul>
                                    <c:forEach items="${term.tags}" var="tag">
                                        <li class="termTag action"><a href="javascript:tagClick('${tag.tag}');">${tag.tag}</a></li>
                                    </c:forEach>
                                </ul>
                            </c:if>
                            </div>
                        </li>
                        <c:if test="${term.korTermCount > 0}">
                            <c:forEach items="${term.korTerms}" var="korTerm" begin="0" end="2" varStatus="var">
                                <li class="korTermItem round row${var.count} action" termId="${term.id}" phrase="${korTerm.smallPhrase}">
                                    <span class="kor_vote_count">${korTerm.vote}</span>
                                    <span class="kor_vote_text">p</span>
                                    <span class="kor_phrase" onclick="javascript:viewKorTerm('${term.id}', '${korTerm.smallPhrase}');">${korTerm.smallPhrase}</span>
                                    <img src="${korTerm.member.avatar}&s=20"/>
                                    <span class="kor_created">${korTerm.createdText}</span>
                                </li>
                            </c:forEach>
                        </c:if>
                    </c:forEach>
                    </ul>
                </c:when>
                <c:otherwise>
                    no data
                </c:otherwise>
            </c:choose>
        </div>
	</div>

<script type="text/javascript">
    $(document).ready(function(){
        initFav();

        $('#termSearchBtn').click(function(){
            initTermList();
        });

        $('input[name=s_keyword]').keydown(function(e){
            if(e.which === 13) {
            	initTermList();
            }
        });

        $(".termPhrase").click( function() {
            var url = getUrl($(this).attr("termId") + "");
            $(document).attr("location", url);
	    });

        $(".korTermItem").click(function(){
            var url = getUrl($(this).attr("termId") + "#" + $(this).attr("phrase"));
            $(document).attr("location", url);
        });
        
        updateTotalRowsCount();
    });

//    callback_ajaxError = function(){
//        defaultGrowlUI('로그인 해주세요~');
//    };

    function tagClick(tag){
        $("input[name='s_keyword']").attr('value', tag);
        $("#termSearchBtn").click();
    }

    function initFav(){
        $.each($(".termFav"), function(count, state){
            updateStarImg(state);

            $(state).click( function(){
                favTerm(state);
            });
        });
    }

    function updateTotalRowsCount(){
        $("#minitabs a[class='active']")
            .append(' (${context.paging.totalRowsCount})');
    }

    function initTermList(){
        var param = {s_keyword:$("input[name=s_keyword]").val(), p_size:$("input[name=p_size]").val()};
        loadTermList(getUrl('index'), '#termPage', param, '#termPage', function(){
            initFav();
        });
    }

    function getUrl(action){
        return '<c:url value="/term/"/>' + action;
    }

    function goPaging(page){
        var url = getUrl('index');
        if($('#allParam').val()){
            url += '?' + $('#allParam').val();
        }
        var param = {p_page:page, p_size:$('#p_size').val(), o_field:'${context.orderParam.field}', o_direction:'${context.orderParam.direction}'};
        loadTermList(url, '#termPage', param, '#termPage', function(){
            initFav();
        });
    }

    //common
    function favTerm(termFav){
        var isFav = $(termFav).attr("isFav");
        var termId = $(termFav).attr("termId");
        //ajax with isFav and URL
        $.getJSON(getUrl(termId + "/fav"), function(data){
            if(data.favadded === true){
                defaultGrowlUI('관심 용어로 등록 했습니다.');
            } else {
                defaultGrowlUI('관심 용어에서 제거 했습니다.');
            }

            //update isFav
            $(termFav).attr("isFav", data.favadded);

            //updateStarImg
            updateStarImg($(termFav));
        });
    }

    function updateStarImg(termFav){
        var isFav = $(termFav).attr("isFav");

        if(isFav === "true"){
            $(termFav).addClass("goldFavImg");
            $(termFav).removeClass("whiteFavImg");

            $(termFav).mouseover(function(){
                $(termFav).addClass("whiteFavImg");
                $(termFav).removeClass("goldFavImg");
            }).mouseout(function(){
                $(termFav).addClass("goldFavImg");
                $(termFav).removeClass("whiteFavImg");
            });
        } else {
            $(termFav).addClass("whiteFavImg");
            $(termFav).removeClass("goldFavImg");

            $(termFav).mouseover(function(){
                $(termFav).addClass("goldFavImg");
                $(termFav).removeClass("whiteFavImg");
            }).mouseout(function(){
                $(termFav).addClass("whiteFavImg");
                $(termFav).removeClass("goldFavImg");
            });
        }
    }

    function viewTerm(id){
        var url = '<c:url value="/term/"/>' + id + "";
        $(document).attr("location", url);
    }

    function viewKorTerm(termId, phrase){
        var url = '<c:url value="/term/"/>' + termId + "#" + phrase;
        $(document).attr("location", url);
    }

    function loadTermList(url, src, param, target, callback){
        if(!callback)
            $(target).load(url + ' ' + src, param);
        else
            $(target).load(url + ' ' + src, param, callback);
    }
</script>
</page:defaultpage>