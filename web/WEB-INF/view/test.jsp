<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Flickr Image Searcher</title>
<style type="text/css">
html, body {
	font: normal 11px Tahoma;
	color: #333;
}

a {
	outline: none;
}

.flickrImg {
	opacity : 0.67;
	padding: 1px 1px;
	border: 1px solid #CCC;
}

.flickrImg:HOVER, .flickrImgSelected {
	opacity : 1;
	border: 1px solid black;
}

.flickerBtnNext:HOVER, .flickerBtnPrev:HOVER, .flickerMoveFirst:HOVER, .flickerMoveLast:HOVER, .movePageTo:HOVER {
	cursor: pointer;
	font-weight: bold;
}

.flickrThumbnail {
	width: 400px; height: auto; float: left;
}

.flickrIndicator {
	background: transparent url('<c:url value="/resources/js/plugin/flickr/ajax-loader.gif"/>') no-repeat center;
}

</style>
<script type="text/javascript" src="<c:url value="/resources/js/framework/jquery-1.4.2.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/plugin/flickr/jquery.flickr.js"/>"></script>
<script type="text/javascript">
$(function() {
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
			text :		$( '#textSearch').val()
		});
	}
});
</script>
</head>
<body>
<input type="text" id="textSearch"></input>
<button id="btnSearch">검색</button><br/>
<div id="searchResult" style="width: 815px; height: 375px; float:left" >
</div>
<form action="test" method="post">
    <input type="text" name="date" value="${date}" />
</form>
</body>
</html>