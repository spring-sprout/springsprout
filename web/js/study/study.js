SPROUT.study = {};
SPROUT.study.find = function( key){
	if ( key.length <= 0) {
		alert("검색어를 입력하세요!");
		return false;
	}
    $(document).attr("location", "/study/find/" + key);
};


