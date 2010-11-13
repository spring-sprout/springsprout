$(function(){
	$.ajaxSetup({
		beforeSend : function(xhr) {
			xhr.setRequestHeader("AJAX", "true");
		},
        error : function(e, xhr, settings, exception){
			if ( e.status === 400) {
				
			} else if(e.status === 401){
                window.location.href = "/index";
            } else if(e.status === 403){
                $.growlUI('warning', '이 기능을 사용할 권한이 없습니다.');
            }
            

            if(callback_ajaxError) {
                callback_ajaxError(e, xhr, settings, exception);
            }
        }
	});

	$.validator.setDefaults({
		submitHandler: function(form){
			s_waitblock();
			form.submit();
		}
	});

	$('.s_waitblock').live( 'click', s_waitblock);
	
	// jquery ui 달력 디폴트 로케일 설정
	$.datepicker.setDefaults($.datepicker.regional['en-GB']);

}); //end doc ready

var callback_ajaxError = function () {};

function s_waitblock(){
	$.blockUI({
	    css: {
	    border: 'none',
	    padding: '15px',
	    backgroundColor: '#000',
	    '-webkit-border-radius': '10px',
	    '-moz-border-radius': '10px',
	    opacity: .5,
	    color: '#fff'
		}
	});
}

function divLoad_get(url,param,targetDiv,callback){
	if(callback === undefined){
		$.get(url, param ,function(data, status){
			$(targetDiv).html(data);
		});
	}
	else{
		$.get(url, param ,function(data, status){
			$(targetDiv).html(data);
		});
		callback.call();
	}
}

function centerGrowlUI(msg){
	$.blockUI.defaults.growlCSS.right = '';
	$.blockUI.defaults.growlCSS.left =  '35%';
	$.blockUI.defaults.growlCSS.top  =  '40%';
	$.growlUI('Notification', msg);
}

function defaultGrowlUI(msg){
	$.blockUI.defaults.growlCSS.right = '10px';
	$.blockUI.defaults.growlCSS.left =  '';
	$.blockUI.defaults.growlCSS.top  =  '10px';
	$.growlUI('Notification', msg);
}

function blockInMessge(msg){
    s_waitblock();
		$('.blockUI.blockMsg.blockPage').remove();
		$('#main').block({message:msg, css:{ width: '800px',
			margin:'68px',backgroundColor:'#FFFFFF', padding:'20px',
			border:'5px solid #D6D6D6',cursor:'auto'
		}});
	$('div.blockMsg').css('left','0px');
	$('div.blockMsg').css('top','0px');
	$('#main .blockUI.blockOverlay').remove();
}

function blockInMessgeWhere(target, msg){
    s_waitblock();
		$('.blockUI.blockMsg.blockPage').remove();
		$(target).block({message:msg, css:{ width: '800px',
			margin:'68px',backgroundColor:'#FFFFFF', padding:'20px',
			border:'5px solid #D6D6D6',cursor:'auto',
            top:  ($(window).height() - 500) /2 + 'px',
            left: ($(window).width() - 500) /2 + 'px'
		}});
	$('div.blockMsg').css('left','0px');
	$('div.blockMsg').css('top','0px');
	$('#main .blockUI.blockOverlay').remove();
}