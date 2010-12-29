var SPROUT = {};
SPROUT.common = {};
SPROUT.common.util = {
	cutStringUsingDot : function($target, length) {
		$target.each( function(){
            var targetText = $(this).text();
            if ( targetText.length >= length) {
            	$(this).text( targetText.substr(0, length) + '...');
            }
		});
	},
	isEnterKey : function($event) {
		if ( $event.keyCode == '13') return true;
		else return false;
	}
};

