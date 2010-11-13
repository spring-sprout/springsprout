<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div id="meeting_resources">
	<sec:authorize ifAnyGranted="ROLE_MEMBER">
		<div id="meeting_resource_btn_layer">
			<button id="resuorce_add_btn">자료추가</button>
		    <div id="resourceAdd" title="자료추가">
		    	<form:form id="resourceForm" commandName="resource" method="post" action="${targetObj.id}/resource/url/add">
				<p>
					<label>참고자료 타입 : </label>
					<form:select id="resourceType" path="resourceType" items="${resourceTypes}" itemLabel="descr" itemValue="value" />
				</p>
				<p id="linkP">
					<label>링크: </label>
					<form:input id="link" path="url" cssClass="text required url" size="50" />
				</p>
				<p id="fileP">
					<label>파일: </label>
					<input type="file" name="uploadingFile" />
				</p>
				<p>
					<label>설명: </label>
					<form:input path="title" cssClass="text required" size="50" />
				</p>
				</form:form>
    		</div>
		</div>
	</sec:authorize>
	<div id="resource_list" style="margin-top: 10px;">
		<c:choose>
    		<c:when test="${!empty targetObj.resources}">
				<!-- data list -->
                <ul class="resources">
                <c:forEach var="resource" items="${targetObj.resources}">
                    <li class="${resource.resourceType.descr}">
                        <c:choose>
                            <c:when test="${resource.resourceType.descr == 'URL'}">
                                <a href="${resource.url}" target="_blank">${resource.url}</a>
                            </c:when>
                            <c:otherwise>
                                <a href="<c:url value="${resource.fileDownUrl}"/>" target="_blank">${resource.uploadFile.fileName}</a>
                            </c:otherwise>
                        </c:choose>
                        - ${resource.title} |
                        ${resource.owner.name} |
                        <sec:authorize ifAnyGranted="ROLE_ADMIN">
                            <img class="resourceDelete" src="<c:url value="/images/remove-16x16.png"/>" title="${resource.id}"/>
                        </sec:authorize>
                    </li>
                </c:forEach>
                </ul>
    		</c:when>
    		<c:otherwise>
			     참고자료가 없습니다.
			</c:otherwise>
		</c:choose>
	</div>
</div>
<script type="text/javascript">
$( function() {
	var $resourceForm = $('#resourceForm'), $resourceAdd = $('#resourceAdd'), 
		$resourceCount = $('#resourcesCount'), $resourcesUl = $('.resources'),
		$resourceList = $('#resource_list');
		
	$("#fileP").hide();
	$('button').button().focusout( function() {
		$(this).removeClass('ui-state-focus');
	}); 

	$resourceAdd.dialog({
		autoOpen	: false,
		buttons		: {
			'전송' : function() {
				$resourceForm.submit();  
			}
		},
		close		: function( event, ui) {
			$(document).attr("location", "${targetObj.id}");
		}
	});
	
	$("#resuorce_add_btn").click(function () {
		$resourceAdd.dialog("open");
	});
	
	$( '.resourceDelete').live( 'click', function() {
		var $this = $(this);
        if( confirm( "삭제 하시겠습니까?")) {
			$.post( "${targetObj.id}/resource/" + $this.attr( 'title') + "/delete", null, function(data) {
				$this.parent().hide( 'blind', null, 1000, function() {
					$(this).remove();
					if ( $resourcesUl.has('li').length == 0) $resourceList.html( '참고자료가 없습니다.');
				});
		        $resourceCount.text( parseInt( $resourceCount.text()) - 1);
   				$.growlUI('Notification', '자료를 삭제 하였습니다.');
			}, 'json');
        }
        return false;
	});
	
	$("#resourceType").change(function () {
		var str = $("select option:selected").attr("value");
		if(str === '20'){
			$("#linkP").hide();
			$("#fileP").show();
			$("form").attr("enctype", "multipart/form-data");
			$("form").attr("action", '${targetObj.id}/resource/file/add');
			$("#link").removeClass("required");
			$("#file").addClass("required");
		} else {
			$("#linkP").show();
			$("#fileP").hide();
			$("form").removeAttr("enctype");
			$("form").attr("action", '${targetObj.id}/resource/url/add');
			$("#link").addClass("required");
			$("#file").removeClass("required");
		}
	}).change();
	
    $resourceForm.validate({
    	errorElement: 'em',
		submitHandler: function( form) {
       		$(form).ajaxSubmit({
				success: function( data, textStatus, XMLHttpRequest) {
					$resourceAdd.dialog('close');
      			}
           	});
       		return false;
		}
    });
    $resourcesUl.last().css('border-bottom', '1px solid #c8c8c8');
});
</script>
<style type="text/css">
#resourceAdd {padding:0; background:#FBFBFB; border: 1px dotted #ccc; overflow: hidden;}
#resourceForm { padding: 0px 10px 10px 10px; }
#resourceForm p { padding-top: 10px; }
em.error {
  	background:url("/images/validation/unchecked.gif") no-repeat 0px 0px;
  	padding-left: 16px;
  	margin-left: 5px;
}
em.success {
  	background:url("/images/validation/checked.gif") no-repeat 0px 0px;
  	padding-left: 16px;
  	margin-left: 5px;
}
.resourceDelete:HOVER { cursor: pointer; }
.resources {
	list-style: none;
	margin: 0;
	padding: 0;
}

.resources li {
	background-repeat: no-repeat;
	background-position: 0 50%;
	padding: 3px 0 3px 20px;
	margin: .4em 0;
	border-top: 1px solid #c8c8c8;
}
li.URL { background-image: url(/images/study/link.png); }
li.파일 { background-image: url(/images/study/file.png); }

</style>