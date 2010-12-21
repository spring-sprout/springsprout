<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="selected_menu" required="false"%>
<%@ attribute name="ajaxlogin_yn" required="false" %>
<%@ tag pageEncoding="utf-8"%>
<c:if test="${ajaxlogin_yn == 'Y'}">
</c:if>
<div id="topheader">
	<div id="mainlogo" class="left">
          <a href="<c:url value="/"/>"><img src="<c:url value="/images/logo_beta.png"/>" alt="springsprout" /></a>
    </div>
	<div id="menu_bar">
		<ul>
			<%--<li><a class="menu_alinker <c:if test="${selected_menu == 'home'}"> menuselected</c:if>" href="<c:url value="/"/>">홈</a></li>--%>
			<li><a class="menu_alinker <c:if test="${selected_menu == 'studies'}"> menuselected</c:if>" href="<c:url value="/study/"/>">스터디</a></li>
			<%--<li><a class="menu_alinker <c:if test="${selected_menu == 'seminars'}"> menuselected</c:if>" href="<c:url value="/seminar/index"/>">세미나</a></li>--%>
			<li><a class="menu_alinker <c:if test="${selected_menu == 'New Wiki'}"> menuselected</c:if>" href="http://wiki.springsprout.org/">위키</a></li>
			<li><a class="menu_alinker <c:if test="${selected_menu == 'Programming Terms'}"> menuselected</c:if>" href="<c:url value="/term/index"/>">용어집</a></li>
			<li><a class="menu_alinker <c:if test="${selected_menu == 'statistics'}"> menuselected</c:if>" href="<c:url value="/statistics/index"/>">통계</a></li>
			<%--<li><a class="menu_alinker <c:if test="${selected_menu == 'links'}"> menuselected</c:if>" href="<c:url value="/link"/>">즐겨찾기</a></li>--%>
			<li class="sliceli"></li>
		</ul>
	</div>
    <%--<c:if test="${ajaxlogin_yn == 'Y'}">--%>
        <%--<div id="loginbox" class="round">--%>
            <%--<div class="ajaxloginform">--%>
                <%--<div class="loginboxclose action">--%>
                    <%--<img src="<c:url value="/images/btn_close.gif"/>" width="12" height="11" alt="닫기"  tabindex="6" />--%>
                <%--</div>--%>
                <%--<div class="logindoor action">--%>
                    <%--<img src="<c:url value="/images/door_in.png"/>" width="32" height="45" alt="로그인" tabindex="5"  />--%>
                <%--</div>--%>
                <%--<div class="p">--%>
                    <%--<div class="emailLabel loginLabel"></div>--%>
                    <%--<input type='text' class="text j_username email_bg" tabindex="1" />--%>
                    <%--<input type="checkbox" name="_spring_security_remember_me" tabindex="3"/> Remember Me</label>--%>

                    <%--<div class="keyLabel loginLabel"></div>--%>
                    <%--<input type='password' class="text j_password password_bg"  tabindex="2" />--%>
                    <%--<a href="<c:url value="/forgetpassword"/>" class="forgotpassword"><span tabindex="4">(forgot password)</span></a>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</c:if>--%>
</div>
<div id="sessionflashmsg" style="display:none;">${SESSION_FLASH_MSG}</div>
<c:if test="${not empty SESSION_FLASH_MSG}">
	<c:set var="flashMsg" value="${SESSION_FLASH_MSG}"/>
	<c:remove var="SESSION_FLASH_MSG"/>
	<script type="text/javascript">
		$.growlUI('Notification', '${flashMsg}');
	</script>
</c:if>
<div id="flashmsg" style="display:none;">${flashMsg}</div>