<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<page:defaultpage banner_name="main-banner" selected_menu="home" ajaxlogin_yn="Y">
<%--style sheet for this page is home.css witch is assigned at css_selected.tag--%>
    <div id="content">
        <div id="index_header">
            <jsp:include page="index_swagger.jsp" />
        </div>
        <div id="index_body">
            <div id="left">
                <div id="notices">
                    <h2>최근 공지</h2>
                    <div class="feeds">
                        <a href="<c:url value="/notices.atom"/>" target="_blank">
                            <img class="action" src="<c:url value="/images/rss_followme.png"/>" width="15" height="15"
                                 alt="atom" title="봄싹 공지 ATOM 피드" style="vertical-align:bottom;"/>
                        </a>
                        <a href="<c:url value="/notices.rss"/>" target="_blank">
                            <img class="action" src="<c:url value="/images/Feed-icon.png"/>" width="15" height="15"
                                 alt="rss" title="봄싹 공지 RSS 피드" style="vertical-align:bottom;"/>
                        </a>
                    </div>
                    <h3 class="btn_more">
                        <a href="#">
                            <span>더보기</span>
                        </a>
                    </h3>
                    <ul id="noticeList"></ul>
                </div>
                <div id="guides">
                    <h2>봄싹 이용 가이드</h2>
                    <ul id="guideList">
                        <li><a href="http://wiki.springsprout.org/display/META/Home" target="_blank">봄싹은?</a></li>
                        <li><a href="http://wiki.springsprout.org/display/META/Home" target="_blank">봄싹의 목적은?</a></li>
                        <li><a href="http://wiki.springsprout.org/display/META/Home" target="_blank">봄싹 스터디에 참여하려면?</a></li>
                    </ul>
                </div>
                <div id="links">
                    <h2>봄싹 관련 사이트</h2>
                    <ul id="linkList">
                        <li><a href="http://twitter.com/springsprout" target="_blank">봄싹 트위터</a></li>
                        <li><a href="http://groups.google.com/group/springsprout" target="_blank">봄싹 구글 그룹스</a></li>
                        <li><a href="http://wiki.springsprout.org" target="_blank">봄싹 위키</a></li>
                        <li><a href="http://springstudyclub.tistory.com/" target="_blank">봄싹 블로그</a></li>
                        <li><a href="http://jira.springsprout.org" target="_blank">봄싹 이슈트래커</a></li>
                        <li><a href="https://github.com/whiteship/springsprout" target="_blank">봄싹 Git(+코드 뷰어)</a></li>
                    </ul>
                </div>
                <div id="thanks">
                    <h2>봄싹에 도움주는 곳</h2>
                    <ul id="thanksList">
                        <li><a href="http://springsource.org">Spring</a></li>
                        <li><a href="http://www.hibernate.org/">Hibernate</a></li>
                    </ul>
                </div>
            </div>
            <div id="center">
                <div id="studies">
                    <h2>최근 스터디</h2>
                    <h3 class="btn_more">
                        <a href="<c:url value="/study/"/>">
                            <span>더보기</span>
                        </a>
                    </h3>
                    <ul id="studyList"></ul>
                    <ul id="realStudyList">
                    <c:choose>
                        <c:when test="${studyList != null}">
                            <c:forEach items="${studyList}" var="study">
                                <li study="${study.id}">
                                    <div class="study_box">
                                        <img src="<c:url value="${study.logo != null ? study.logo : '/images/study/logos/default.png'}"/>" alt="스터디 로고" width="120" height="120"/>
                                        <h3 class="studyName" title="${study.studyName}">${study.studyName}</h3>
                                    </div>
                                </li>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            스터디 정보가 없습니다.
                        </c:otherwise>
                    </c:choose>
                    </ul>
                </div>
                <div id="meetings">
                    <h2>최근 모임</h2>
                    <ul id="meetingList">
                    <c:choose>
                        <c:when test="${meetingList != null}">
                            <c:forEach items="${meetingList}" var="meeting">
                                <li study="${meeting.study.id}" meeting="${meeting.id}">
                                    <input type="hidden" class="openDateVal" value="${meeting.openDate }" />
                                    <div class="openDate">
                                        <span class="date">
                                            <span class="month"></span>
                                            <span class="day"></span>
                                        </span>
                                        <span class="dayOfWeek">
                                            <span class="shortDay"></span>
                                        </span>
                                    </div>
                                    <div class="descr">
                                        <h3 class="nowrap">${meeting.title}</h3>
                                       	<div class="nowrap">${meeting.location}</div>
										<span class="dueTime"><fmt:formatDate value="${meeting.openTime}" pattern="a h시 mm분"/></span> ~
                                        <span class="dueTime"><fmt:formatDate value="${meeting.closeTime}" pattern="a h시 mm분"/></span>
                                    </div>
                                </li>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            모임 정보가 없습니다.
                        </c:otherwise>
                    </c:choose>
                    </ul>
                </div>
            </div>
            <div id="right">
                <div id="graffitis">
                    <h2>낙서장</h2>
                    <div class="graffitiWriter" style="border-top: 1px solid #DCDCDC;">
                        <sec:authorize ifAnyGranted="ROLE_MEMBER, ROLE_ADMIN">
                            <img class="g_avatar" src="${currentUser.avatar}" alt="${currentUser.name}" style="vertical-align: bottom;"/>
                            ${currentUser.name}: 
                            <input type="text" id="txtGraffitiContent" maxlength="50"/>
                            <input type="button" id="btnGraffitiWrite" value="쓰기"/>
                        </sec:authorize>
                    </div>
                    <div class="graffitiWindow">
                        <input type="hidden" id="txtGraffitiLoadDateTime" value=""/>
                        <table id='tbGraffitiList' class="graffitiList">
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <div id="index_footer">
        </div>
    </div>
	<div id="addOn" align="center">
		<div id="addOn1" class="addOnList">
			<a href="http://blog.yes24.com/lib/adon/View.aspx?blogid=1423718&goodsno=4744444&idx=10351&ADON_TYPE=B&regs=b" target="_blank"><img src='http://image.yes24.com/goods/4744444/66x96' border='0' /></a>
			<br/>
			<b class="addOnItem">스프링 레시피</b>
		</div>		    
		<div id="addOn2" class="addOnList">
			<a href="http://blog.yes24.com/lib/adon/View.aspx?blogid=1423718&goodsno=4020006&idx=7386&ADON_TYPE=B&regs=b" target="_blank"><img src="http://image.yes24.com/goods/4020006/66x96" border="0" /></a>
			<br/>
			<b class="addOnItem">토비의 스프링 3</b>
		</div>
		<div id="addOn3" class="addOnList">
			<a href="http://blog.yes24.com/lib/adon/View.aspx?blogid=1423718&goodsno=4425736&idx=8502&ADON_TYPE=B&regs=b" target="_blank"><img src="http://image.yes24.com/goods/4425736/66x96" border="0" /></a>
			<br/>
			<b class="addOnItem">스프링 Security</b>
		</div>
	</div>
    

<script type="text/javascript">
    $(function(){
    	changeStudyName();
    	
        $("#studies li").click( function(){
            //var url = '<!-- c:url value="/study/view/"/ -->' + $(this).attr("study");
            var url = '/study/' + $(this).attr("study");
            $(document).attr("location", url);
        });

        $(window).resize(function() {
        	changeAddOnPosition( 'window');
		});

		function changeAddOnPosition( type) {
			var $bodyWidth = $('body').width(), $mainWidth = $('#main').width(), 
				$bodyHeight = $('body').height(), addOnHeight = 370, gap5 = 5;
			var left = (($bodyWidth - $mainWidth) / 2) + $mainWidth,
				top = $bodyHeight - addOnHeight - gap5;

			if ( type == 'window') top = top + gap5 - $('#footer').height() - $('#spacehr').height();
			
			$('#addOn').css('left', left).css('top', top).show();
		}
		changeAddOnPosition();

        var $meetingItem = $("#meetings li");
        changeMeetingDateFormat( $meetingItem);
		
        $meetingItem.click( function() {
            //var url = '<!-- c:url value="/study/"/ -->' + $(this).attr("study") + "/meeting/" + $(this).attr("meeting") + "";
            var url = '/study/' + $(this).attr("study") + "/meeting/" + $(this).attr("meeting");
            $(document).attr("location", url);
        });

        $.getJSON(
            '<c:url value="/main/noticeList" />',
            function(data, status) {
                if (status === 'success') {
                    if (data.noticeList.length === 0) {
                        $('#noticeList').append('<li class="bullet">공지사항이 없습니다.</li>');
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
//                                rowData += '<span class="right">' + notice.created + '</span>'; //날짜영역.
                            rowData += "</li>"; // li 끝

                            $('#noticeList').append(rowData);
                            $('#noticeList li.' + idx + ' a').nyroModal({minWidth:600,minHeight:400});
                        });
                    }
                }
            }
        );

    });
    
    function changeStudyName() {
		 $('.studyName').each( function(){
	            studyName = $(this).text();
	            if ( studyName.length >= 8) {
	            	$(this).text( studyName.substr(0, 8) + '...');
	            }
	     });
	}
	

</script>

<script type="text/javascript">
    $(function(){
        var _maxTop = 0, _tStartTop = 0, _tEndTop = 0;
        var graffitiList = $('#tbGraffitiList'), graffitiWindow = $('.graffitiWindow'), graffitiLoadDateTime = $('#txtGraffitiLoadDateTime'),
        	graffitiContent = $("#txtGraffitiContent");
        graffitiList
            .draggable({
                         axis:'y'
                        ,contanment:'parent'
                        ,cursor : 'move'
                        ,start:function(ev,ui){
                            _tStartTop = graffitiList.css('top').replace('px','');
                            _maxTop = $('.graffitiWindow').height()- graffitiList.height();
                        }
                        ,stop:function(ev, ui){
                            _tEndTop = graffitiList.css('top').replace('px','');
                            var diffTop = parseInt(_tStartTop) - parseInt(_tEndTop);
                            if(diffTop > 0 && graffitiWindow.height() < graffitiList.height()){ // up drag
                                if(_maxTop > _tEndTop ){
                                    graffitiList.animate({top:_maxTop},{duration: 500, easing: 'backout'});
                                }
                            }else if(diffTop < 0 ||  graffitiWindow.height() > graffitiList.height()){ // down drag
                                if(_tEndTop > 0  ||  graffitiWindow.height() > graffitiList.height() ){
                                    graffitiList.animate({top:'0'},{duration: 500, easing: 'backout'});
                                }
                            }
                        }
                });
        refresh_graffitiList();

        // 낙서장을 지속적으로 갱신시켜주는 재귀함수!
        function refresh_graffitiList(){
            $.getJSON(
                '<c:url value="/main/graffitiList" />',
                {loadDateTime:graffitiLoadDateTime.val()},
                function(returnData, textStatus){
                    if(textStatus === 'success'){
                    	makeGraffitiList(returnData.graffitiList);
                        graffitiLoadDateTime.val(returnData.loadDateTime);
                    }
                    setTimeout(refresh_graffitiList, 10000);
                }
            );
        }

        graffitiContent.live( 'keydown', function (event){
			if (event.keyCode === 13) {
				doWrite();
		    }
		});

       	$('#btnGraffitiWrite').live( 'click', function(){
         	doWrite();
		});

		function doWrite() {
			var graffitiContent = $('#txtGraffitiContent'),
				content = $.trim(graffitiContent.val());
			if(!content){
				$.growlUI('기록할 낙서를 입력하세요.');
				return;
			}
           else if(content.length >= 50){
               $.growlUI('낙서가 너무 깁니다. 조금 줄여주시죠? <br/>허용은 [content.length <= 50] 입니다^^;');
               return;
           }

           $.post(
               '<c:url value="/main/graffitiWrite" />',
               { contents:content, lastGraffitiID: $('#tbGraffitiList tr:first-child').attr('id')} ,
               function(data,status){
                   if(status === "success"){
                       if(data.writeResult){
                           graffitiContent.val('');
                           $.growlUI('OK! 낙서를 <br/> 추가했습니다.');
                           makeGraffitiList(data.graffitiList);
                       }
                       else{
                           $.growlUI('warning', '낙서 작성 중 <br/> 오류가 발생했습니다.');
                       }
                   }
               },
               "json"
			);
		}

		function makeGraffitiList( list) {
			$.each(list, function(idx, graffiti){
            	if(graffiti == null || graffiti.id ==  $('#tbGraffitiList tr#'+graffiti.id+'').attr('id')) return;
               	var rowData = "<tr id='" + graffiti.id +"'>";
    			rowData += "<td class='gt_avatar'><img src='" + graffiti.writerAvatar + "&s=30' class='g_avatar' /></td>";
    			rowData += "<td class='gt_content'>"+ graffiti.contents +" <br/>";
    			rowData += "<strong>" + graffiti.writerName + "</strong>";
    			rowData += "<span class='gt_writedate'> on "+graffiti.formatedDate+"</span></td>";
    			rowData += "</tr>";
    			graffitiList.prepend(rowData);
    		});
        }
    });
</script>

<script type="text/javascript">
    <%--Line Height Adjusting --%>
    $(function(){
        var leftHeight = $("#left").height();
        var centerHeight = $("#center").height();
        var rightHeight = $("#right").height();

        var additionalPadding = 100;

        var highestHeight = leftHeight + additionalPadding;
        if(centerHeight > highestHeight)
        highestHeight = centerHeight + additionalPadding;
        if(rightHeight > highestHeight)
        highestHeight = rightHeight + additionalPadding;

        $("#left").height(highestHeight);
        $("#center").height(highestHeight);
        $("#right").height(highestHeight);
    });
</script>

</page:defaultpage>
