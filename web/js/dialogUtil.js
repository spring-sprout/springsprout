var confirmDialog, actionUrl, msg;

$(function(){
	$('#main').append( '<div id="dialog-confirm" title="등록 확인"><p></p></div>');
	confirmDialog =  $("#dialog-confirm");
	confirmDialog.dialog( {
		autoOpen : false,
		resizable : false,
		modal : true,
		open: function() {
			$(this).parents('.ui-dialog-buttonpane button:eq(0)').blur();
			$(this).parents('.ui-dialog-buttonpane button:eq(1)').focus(); 
	    },
		buttons : {
			'취소' : function() {
	    		$.unblockUI;
				$(this).dialog('close');
			},
			'확인' : function() {
				$this = $(this);
				s_waitblock();
				processAjaxAction(msg, $this);
			}
		}
	});
});

function dialogOpen( alertMsg, targetUrl) {
	msg = alertMsg, actionUrl = targetUrl;
	confirmDialog.dialog('open').find("p")
	.html( '<span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>' + msg + ' 하시겠습니까?');
}

function processAjaxAction(msg, $dialog) {
	if ( msg === '스터디 알림') {
		$dialog.dialog('close');
		$.getJSON(actionUrl, null, function(data) {
			$.unblockUI();
			$.growlUI('Notification', data.studyName+'를 알림 하였습니다.');
		}); 
	} else {
		$(location).attr('href', actionUrl);
		$dialog.dialog('close');
	}
}
