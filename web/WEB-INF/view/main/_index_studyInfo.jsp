<%--
  Created by IntelliJ IDEA.
  User: helols
  Date: 2009. 10. 22
  Time: 오후 4:20:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="studyInfo left">
<style type="text/css">
    .OPEN {
        font-weight: bolder;
    }
    .s_selected{
        background: #E0F8F7;
    }


</style>

    <div class="titlebox">
        <ul class="mbtitle">
            <li>스터디 정보</li>
        </ul>
    </div>
    <div class="cbox">
        <c:choose>
            <c:when test="${studyList != null}">
                <ul style="width: 330px; float:left;">
                    <c:forEach items="${studyList}" var="study">
                        <li class="bullet">
                            <div>
                                <a class="m_study" href="<c:url value="/study/view/${study.id}"/>" studyId="${study.id}">${study.studyName}</a>
                                <span class="right">
                                    <fmt:formatDate value="${study.startDay}" pattern="yyyy/M/d"/> ~
                                    <fmt:formatDate value="${study.endDay}" pattern="yyyy/M/d"/>
                                </span>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
                <ul id="m_meetings" style="width: 340px; float:left; margin-left:20px;"></ul>
            </c:when>
            <c:otherwise>
                스터디 정보가 없습니다.
            </c:otherwise>
        </c:choose>
    </div>
<script type="text/javascript">
    $(function(){
        $(".m_study").hover(function(){
            var studyId = $(this).attr("studyId");
            $.getJSON('<c:url value="/main/meetingList?studyId="/>' + studyId, function(data){
                $("#m_meetings").empty();
                if(data.meetingList.length === 0){
                    $('#m_meetings').append('<li class="bullet">모임 내용이 없습니다.</li>');
                }else{
                    $.each(data.meetingList, function(idx, meeting)
                    {
                        var meetingViewUrl = "<c:url value='/study/" + studyId 
                                + "/meeting/" + meeting[0] + "" + "'/>";
                        $('#m_meetings')
                            .append('<li class="bullet"><a class="m_meeting ' + meeting[3] + '" studyId="' + studyId + '" meetingId="' + meeting[0]
                                + '" href="' + meetingViewUrl + '">' + meeting[1] + '</a>'
                                + '<span class="right">'
                                + meeting[4]
                                + '</span>'
                                + '</li>');
                    });
                }
            });
            $(".s_selected").removeClass("s_selected");
            $(this).parent().addClass("s_selected");
        });
    });
</script>
</div>