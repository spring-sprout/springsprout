<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" tagdir="/WEB-INF/tags/study"%>

<page:defaultpage selected_menu="studies">
	<s:defaultpage>
		<h1>[${meetingTitle}] 모임 발표 ${action}</h1>
		<hr class="clear" />
		<form:form commandName="presentation" method="post">
			<h2 class="summary"></h2>
			<s:ftext title="제목" path="title" size="100"/>
			<s:ftext title="주제" path="topic" size="100"/>
			<s:ftext title="시간" path="hour" />
			<p>
                <label>발표자</label>
                <form:input path="presenter" cssClass="text" cssStyle="display: none;" />
                <input type="text" id="presenterAC" class="text" value="${presentation.presenter.name}"/>
                <form:errors path="presenter" />
            </p>
			<s:ftextarea title="요약" path="summary" id="summary" rows="4" cols="100" />
			<hr class="clear" />
 		<div class="buttonGroup" style="margin-top: 20px;" align="center">
			<input type="submit" name="_eventId_proceed" value="저장" class="s_waitblock" />
			<a href="${backUrl }" >
				<input type="button" value="뒤로" class="s_waitblock" />
			</a>
		</div>
		</form:form>
	</s:defaultpage>
<script type="text/javascript">
	var $presenterAC = $('#presenterAC');
	$presenterAC.autocomplete( '<c:url value="/ac/members"/>', {
		model: 'memberList',
        matchContains: true,
		scroll: true,
		scrollHeight: 300,
		matchCase: true,
		formatItem: function( member, i, total) {
			return member[0] + ' ' + member[1] + ' ' + i;
		},
		formatResult: function( member) {
			return member[1];
        }
    });

    $presenterAC.result( function( event, member, formatted) {
        if( member) $('#presenter').attr('value', member[0]);
    });

    var validator = $('form').bind('invalid-form.validate', function() {
		validationUtil.setWarnSummary($(this).find('.summary'), validator.numberOfInvalids());
	}).validate({
		errorElement: 'em',
		errorContainer: $('.summary'),
		success: function(label) {
			label.addClass('success');
		},
		rules: {
			title: 'required',
			topic: 'required',
			presenterAC: 'required',
			summary: 'required'
		}
	});
	
    $('#summary').wysiwyg();
    $('.buttonGroup input').button().focusout( function() { $(this).removeClass('ui-state-focus'); });

</script>
</page:defaultpage>