<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" tagdir="/WEB-INF/tags/study"%>
<style type="text/css">
    .form-row {
        width: 100%;
        float: left;
    }

</style>
<page:defaultpage selected_menu="studies">
	<s:defaultpage>
		<h1>${title}</h1>
			<hr class="clear"/>
			<form:form commandName="study" method="post" id="studyForm" cssStyle="width: 80%;">
                <h2 class="summary"></h2>
				<form:hidden path="manager"/>
	   			<form:hidden path="id"/>
	   			<div class="form-row">
		   			<div class="logo round" align="center">
						<img id="logoImg" src="<c:url value="${study.logo != null && study.logo != ''  ? study.logo : '/images/study/logos/default.png'}" />" />
						<br/>
						<input id="logo" name="logo" type="hidden" value="<c:url value="${study.logo != null && study.logo != ''  ? study.logo : '/images/study/logos/default.png'}" />"/>
						<span>로고를 선택하세요.</span>
					</div>
                    <s:ftext title="스터디명" path="studyName" size="50" descr="나중에 변경할 수도 있습니다."/>
                    <s:ftext title="최대인원수" path="maximum" />
                </div>
				<div class="form-row">
					<div style="float: left;">시작일 : <form:input path="startDay" cssClass="inputNoneBorder"/><div class="fdate"></div></div>
					<div style="float: left; margin-left: 20px;">종료일 : <form:input path="endDay" cssClass="inputNoneBorder"/><div class="fdate"></div></div>
				</div>
				<div class="form-row">
					<s:ftextarea title="설명" path="descr" rows="4" cols="100" id="descr" style="width: 100%;"/>
				</div>
				<div class="form-row">
					<c:if test="${ isUpdate }">
					<p>
	            		<label style="display: inline;">수정 내역을 통지할까요?</label>
						<input type="checkbox" name="isGoingToBeNotified" checked="checked" />
	           		</p>
					</c:if>
				</div>
				<div style="width: 100%; padding-bottom: 10px;" align="center">
					<input type="submit" value="저장" class="jButton" />
		            <s:back-button url="${backUrl}" />
	            </div>
	        </form:form>
			<div id="logoDialog" style="padding: 0 35px;" title="로고를 선택하세요." align="center">
				<div align="center" style="margin-top: 15px; padding-right: 5px;">
					<input type="radio" name="iconType" value="default" checked="checked" /> 디폴트
					<input type="radio" name="iconType" value="flickr" /> 플리커
					<!-- 나중에 구현 합시다.
					<input type="radio" name="iconType" value="uploadSelf" /> 직접 업로드
					 -->
				</div>
				<ul class="thumb" id="thumb">
					<li><a href="#"><img src="<c:url value="/images/study/logos/language/css.png" />"/></a></li>
					<li><a href="#"><img src="<c:url value="/images/study/logos/language/html.png" />"/></a></li>
					<li><a href="#"><img src="<c:url value="/images/study/logos/language/java.png" />"/></a></li>
					<li><a href="#"><img src="<c:url value="/images/study/logos/language/js.png" />"/></a></li>
					<li><a href="#"><img src="<c:url value="/images/study/logos/iphone/browser-128x128.png" />"/></a></li>
					<li><a href="#"><img src="<c:url value="/images/study/logos/iphone/clock-128x128.png" />"/></a></li>
					<li><a href="#"><img src="<c:url value="/images/study/logos/iphone/graph-128x128.png" />"/></a></li>
					<li><a href="#"><img src="<c:url value="/images/study/logos/iphone/map-128x128.png" />"/></a></li>
					<li><a href="#"><img src="<c:url value="/images/study/logos/iphone/notes-128x128.png" />"/></a></li>
					<li><a href="#"><img src="<c:url value="/images/study/logos/iphone/tools-128x128.png" />"/></a></li>
					<li><a href="#"><img src="<c:url value="/images/study/logos/iphone/SMS-128x128.png" />"/></a></li>
					<li><a href="#"><img src="<c:url value="/images/study/logos/iphone/photo-128x128.png" />"/></a></li>
				</ul>
				<div id="flickrSearch" style="display: none;" align="center">
					<input type="text" id="textSearch"/>
					<button id="btnSearch">검색</button><br/>
					<div id="searchResult"></div>
				</div>
			</div>
	</s:defaultpage>
<script type="text/javascript">
	$logoDialog = $('#logoDialog'), $logoImg = $('#logoImg'), $logo = $('#logo');
	$('.fdate').datepicker({ 
		dateFormat: 'yy/mm/dd',
		onSelect: function(dateText, inst) {
			if ( dateText.length > 0) {
				//$(this).parent().find('span.error').hide().addClass('success');
				//$(this).parent().find('input').removeClass('error');
			}
			$(this).parent().find('input').val(dateText);
		},
		defaultDate : $(this).prev().val()
	});
	$('#descr').wysiwyg();
	
	$('.jButton, #btnSearch').button().focusout( function() { $(this).removeClass('ui-state-focus'); });
	$('input:radio[name="iconType"]').click( function() {
		$('#thumb').toggle();
		$('#flickrSearch').toggle();
	});
	$( '#btnSearch').click( function(){
		getImgFromFlick();
	});
	$( '#textSearch').keyup(function(e) {
		if(e.keyCode == 13) {
			getImgFromFlick();
		}
	});

	function getImgFromFlick() {
		$( '#searchResult').favoriteFlickr( {
			method : 	'flickr.photos.search',
			api_key : 	'77f7a3eb07331902b582e1db782aeb57',
			format : 	'json',
			text :		$( '#textSearch').val(),
			perpage : 	9,
			clickCallback: flickrCallback
		});
	}

	function flickrCallback( flickrImgSrc) {
		$logoImg.attr( 'src', flickrImgSrc);
		$logo.val( flickrImgSrc);
		$logoDialog.dialog( 'close');
	}
	
	$logoDialog.dialog( {autoOpen:false, width: 570});
	$logoImg.click( function() {
		$logoDialog.dialog( 'open');
	});
	$("ul.thumb li").hover(function() {
		$(this).css({'z-index' : '10'}); /*Add a higher z-index value so this image stays on top*/ 
		$(this).find('img').addClass("hover").stop() /* Add class of "hover", then stop animation queue buildup*/
			.animate({
				marginTop: '-110px', /* The next 4 lines will vertically align this image */ 
				marginLeft: '-110px',
				top: '50%',
				left: '50%',
				width: '174px', /* Set new width */
				height: '174px', /* Set new height */
				padding: '20px'
			}, 200); /* this value of "200" is the speed of how fast/slow this hover animates */

		} , function() {
		$(this).css({'z-index' : '0'}); /* Set z-index back to 0 */
		$(this).find('img').removeClass("hover").stop()  /* Remove the "hover" class , then stop animation queue buildup*/
			.animate({
				marginTop: '0', /* Set alignment back to default */
				marginLeft: '0',
				top: '0',
				left: '0',
				width: '100px', /* Set width back to default */
				height: '100px', /* Set height back to default */
				padding: '5px'
			}, 400);
	}).click( function() {
		var logSrc = $(this).find('img').attr('src');
		$logoImg.attr( 'src', logSrc);
		$logo.val( logSrc);
		$logoDialog.dialog( 'close');
	});

	var validator = $('#studyForm').bind('invalid-form.validate', function() {
		validationUtil.setWarnSummary($(this).find('.summary'), validator.numberOfInvalids());
	}).validate({
		onfocusout: false, onkeyup: false, onclick: false,
		errorElement: 'span',
		errorContainer: $('.summary'),
		success: function(label) {
			label.addClass('success');
		},
		submitHandler: function(form) {
			s_waitblock();
			form.submit();
		},
		invalidHandler: function(form, validator) {
			$.unblockUI;
		},
		rules: {
			studyName: 'required',
			startDay: 'required',
			endDay: 'required'
		},
		messages: {
			studyName: '스터디명을 입력 하세요!'
		}
	});
</script>
<style type="text/css">
.pikachoose ul { width : 100%; }
</style>
</page:defaultpage>