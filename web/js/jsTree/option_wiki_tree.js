var option_wiki_tree = {
		ui:{theme_name  : "apple"}
		, rules : {
			renameable  : "none",    // which node types can the user select | default - all
	        deletable   : "none",    // which node types can the user delete | default - all
	        creatable   : "none"
		}
		, callback : {
			 onselect    : function(NODE,TREE_OBJ) {
			 	document.location.href = $('a', NODE)[0].attr("href");
			 }
		}
};