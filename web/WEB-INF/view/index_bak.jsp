<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>

<page:defaultpage banner_name="main-banner" selected_menu="home" ajaxlogin_yn="Y">
   <div id="content">
        	<div class="mainContent">
                <div class="aboutss2 left">
                    <div class="tabtitlebox">
                        <ul class="ss2title">
                            <li class="a1 selected titlebox">
                                공지
                                 <a href="<c:url value="/notices.atom"/>" target="_blank">
                                    <img class="action" src="<c:url value="/images/rss_followme.png"/>" width="15" height="15"
                                         alt="atom" title="봄싹 공지 ATOM 피드" style="vertical-align:bottom;"/>
                                </a>
                                <a href="<c:url value="/notices.rss"/>" target="_blank">
                                    <img class="action" src="<c:url value="/images/Feed-icon.png"/>" width="15" height="15"
                                         alt="rss" title="봄싹 공지 RSS 피드" style="vertical-align:bottom;"/>
                                </a>
                            </li>
                            <li class="a2 noneselected action">가이드</li>
                            <li class="a3 noneselected action">관련 사이트</li>
                        </ul>
                    </div>
                    <div class="cbox c1 cboxOn" style="padding-right:10px;">
                        <ul class="noticeList"></ul>
                    </div>
                    <div class="cbox c2 cboxOff">
                        <ul>
                            <li class="bullet">봄싹은?</li>
                            <li class="bullet">봄싹의 목적은?</li>
                            <li class="bullet">봄싹 스터디에 참여하려면?</li>
                        </ul>
                    </div>
                    <div class="cbox c3 cboxOff">
                        <ul>
                            <li class="bullet">봄싹 트위터</li>
                            <li class="bullet">봄싹 구글 그룹스</li>
                            <li class="bullet">봄싹 위키</li>
                            <li class="bullet">봄싹 블로그(@Deprecated)</li>
                            <li class="bullet">봄싹 이슈트래커</li>
                            <li class="bullet">봄싹 코드 뷰어</li>
                        </ul>
                    </div>
                </div>
                <jsp:include page="main/_index_notice.jsp"/>       <!-- notice -->
                <jsp:include page="main/_index_studyInfo.jsp"/>    <!-- studyInfo -->

	        	<!-- 이곳 css 추후 따로 작성할꺼임. -->
	        	<div class="coverFlow left">
					coverFlow 준비중..
	            </div>
            </div>
            <div class="mainSidebar">
            	<%--<div class="iconlist">--%>
                    <%--<a href="http://twitter.com/springsprout" target="_blank">--%>
                        <%--<img class="action" src="<c:url value="/images/twitter_followme.png"/>" width="25" height="25"--%>
                             <%--alt="twitter" title="봄싹 트위터"/>--%>
                    <%--</a>--%>
                    <%--<a href="<c:url value="/notices.atom"/>" target="_blank">--%>
                        <%--<img class="action" src="<c:url value="/images/rss_followme.png"/>" width="25" height="25"--%>
                             <%--alt="atom" title="봄싹 공지 ATOM 피드"/>--%>
                    <%--</a>--%>
                    <%--<a href="<c:url value="/notices.rss"/>" target="_blank">--%>
                        <%--<img class="action" src="<c:url value="/images/Feed-icon.png"/>" width="25" height="25"--%>
                             <%--alt="rss" title="봄싹 공지 RSS 피드"/>--%>
                    <%--</a>--%>
                    <%--<a href="http://groups.google.com/group/springsprout" target="_blank">--%>
                        <%--<img class="action" src="<c:url value="/images/google_followme.png"/>" width="25" height="25"--%>
                             <%--alt="google" title="봄싹 구글 그룹스"/>--%>
                    <%--</a>--%>
                    <%--<a href="javascript:bookmark();">--%>
                        <%--<img class="action" src="<c:url value="/images/Favorite-icon.png"/>" width="25" height="25"--%>
                             <%--alt="favorite" title="봄싹 즐겨찾기 추가"/>--%>
                    <%--</a>--%>
            	<%--</div>--%>

                <jsp:include page="main/_index_graffiti.jsp"/> <!-- graffiti -->

                <div class="sponsor">
	            	<a href="http://www.developerfarm.com/" target="_blank">
					    <img class="action" src="<c:url value="/images/wikibooks.png"/>" width="210" height="70" alt="wikibooks" />
                     </a>
                     <a href="http://www.hanbitedu.co.kr/" target="_blank">
					    <img class="action" src="<c:url value="/images/hanbit.png"/>" width="210" height="70" alt="hanbit" />
                     </a>
	            </div>
	        </div>
        </div> <!-- content div end -->
<script type="text/javascript">
    $(function(){
        $('.ss2title li').click(function(){
            var self = $(this);
            if(self.is('.noneselected')){
                $('.ss2title').find('.selected').each(function(){
                    $(this).removeClass('selected').addClass('action').addClass('noneselected');
                });

                $('.aboutss2').find('.cboxOn').each(function(){
                    $(this).removeClass('cboxOn').addClass('cboxOff');
                });
                self.removeClass('noneselected').removeClass('action').addClass('selected');

                cbox = $('.aboutss2 .c1');
                if(self.is('.a2')){
                    cbox = $('.aboutss2 .c2');
                }else if(self.is('.a3')){
                    cbox = $('.aboutss2 .c3');
                }
                cbox.removeClass('cboxOff').addClass('cboxOn');
            }
        });
    });

    function afterlogin_callback(){
        // callback
        $('.graffitiWriter').html('')
                .html('<sec:authorize ifAnyGranted="ROLE_MEMBER, ROLE_ADMIN">'
                +'<input type="text" id="txtGraffitiContent" maxlength="50" />'
                +'<input type="button" id="btnGraffitiWrite" value="쓰기" />'
                +'</sec:authorize>');
    }

    /*  BOOKMARK THE PAGE  */
    function bookmark()
    {
        if ((navigator.appName === 'Microsoft Internet Explorer') && (parseInt(navigator.appVersion) >= 4)){
            window.external.AddFavorite(window.location,document.title);
        }
        else{
            $.growlUI('북마크에 추가해 주세요 ;) <br/> (CTRL-D)');
        }
    }
</script>
<script type="text/javascript">
    $(function(){
        $.getJSON(
                '<c:url value="/main/noticeList" />',
                function(data, status) {
                    if (status === 'success') {
                        if (data.noticeList.length === 0) {
                            $('.noticeList').append('<li class="bullet">공지사항이 없습니다.</li>');
                        } else {
                            var visable = '';
                            $.each(data.noticeList, function(idx, notice) {
                                visable = idx > 5 ? idx + ' hidden' : idx;
                                var rowData = '<li class="star ' + visable + '">'; //li 시작
                                rowData += '<a href="main/noticeView/' + notice.id + '" rel="gal" title="' + notice.title + '">';  // 부득이 하게 .. c:url 생략!
                                rowData += '<span class="action">';
                                rowData += notice.cuttingTitle;
                                rowData += '</span>';
                                rowData += '</a>';
                                rowData += '<span class="right">' + notice.created + '</span>'; //날짜영역.
                                rowData += "</li>"; // li 끝

                                $('.noticeList').append(rowData);
                                $('.noticeList li.' + idx + ' a').nyroModal({minWidth:600,minHeight:400});
                            });
                        }
                    }
                }
        );
    });
//    callback_ajaxError = function(){
//        $('.noticeList').append('<li class="bullet">공지사항을 불러오지 못했습니다.</li>');
//    };

</script>
</page:defaultpage>
