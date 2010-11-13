var validationUtil = {
	type: {
		POST	: "post",
		GET		: "get"		 	
	},
	dataType : {
		JSON 	: "json"
	},
	setWarnSummary : function(obj, count) {
		obj.html('입력 화면에 <span class="errorCount">' + count + '</span> 개의 에러가 발생했습니다, 다시 입력 하세요.');
	},
	getWarnMsg : function( count) {
		return '입력 화면에 <span class="errorCount">' + count + '</span> 개의 에러가 발생했습니다, 다시 입력 하세요.';
	},
	getErrorHtml : function(msg) {
		return '<em htmlfor="title" generated="true" class="error">' + msg + '</em>';
	},
	processServerError : function(errorCount, errors, $form) {
		validationUtil.setWarnSummary($form.find('.summary'), errorCount);
		for ( var i in errors) {
			var errorObj = errors[i];
			$form.first().prepend( '<span>' + validationUtil.getErrorHtml(errorObj.defaultMessage) +'</span>');
		}
	},
	isNull : function(obj) {
		if ( obj.length <= 0) return true;
		else return false;
	},
	isNotNull : function(obj) {
		return !validationUtil.isNull(obj);
	}
	
};
