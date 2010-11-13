<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<page:defaultpage banner_name="main-banner" selected_menu="home">
<script type="text/javascript">
$(document).ready(function(){
	$("#passwordFind").validate();
	$('input[name=email]').focus();
});
</script>
<div id="content">
	<div id="login" class="standard_form">
		<form id="passwordFind" name="f" action="<c:url value="forgetpassword"/>" method="post" id="forgetpasswordform">
			<h1>비밀번호 찾기 <a href="<c:url value="/signup"/>">(Signup)</a></h1>
			<p>
				<label>가입한 이메일</label>
				<input type="text" id="email" name="email" style="width: 25em;" tabindex="1"  class="text required email"  />
			</p>
			<p>
				<label>가입한 이름</label>
				<input type="text" id="name" name="name" style="width: 15em;" tabindex="2"  class="required text"  />
			</p>
			<p>
				<input type="submit" value="send password" />
			</p>
		</form>
    </div>
</div>
</page:defaultpage>