SPROUT.study = {};
SPROUT.study.find = function( $form){
	var keyword = $form.find('#keyword');
	if ( keyword.length <= 0) {
		alert("검색어를 입력하세요!");
		return false;
	}
	$form.submit();
};

SPROUT.study.Post = {};
SPROUT.study.Post.blockUIAjaxReq = function( url, $target) {
	$.ajax({
		url : url,
		beforeSend : function(){
			s_waitblock();
		},success : function(html){
			$target.html(html);
			$.unblockUI();
		}
	});
};




