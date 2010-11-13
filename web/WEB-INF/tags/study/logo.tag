<%@ tag pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<p>
	<label>로고</label>
	<div class="pikachoose">
		<ul id="logos">
			<li><img src="<c:url value="/images/study/logos/iphone/cal-128x128.png" />"/></li>
			<li><img src="<c:url value="/images/study/logos/quty/Aiyou-Address-Book-128x128.png" />"/></li>
			<li><img src="<c:url value="/images/study/logos/quty/Dashi-Dashboard-128x128.png" />"/></li>
			<li><img src="<c:url value="/images/study/logos/quty/Fuji-Finder-128x128.png" />"/></li>
			<li><img src="<c:url value="/images/study/logos/quty/Mosu-Burn-128x128.png" />"/></li>
			<li><img src="<c:url value="/images/study/logos/quty/Sayonara-Spinning-Ball-128x128.png" />"/></li>
			<li><img src="<c:url value="/images/study/logos/quty/Sousa-Safari-128x128.png" />"/></li>
			<li><img src="<c:url value="/images/study/logos/star/Corellian-128x128.png" />"/></li>
			<li><img src="<c:url value="/images/study/logos/star/DridStarFighter-128x128.png" />"/></li>
			<li><img src="<c:url value="/images/study/logos/star/JediStarFighter-128x128.png" />"/></li>
			<li><img src="<c:url value="/images/study/logos/star/MilleniumFalcon-128x128.png" />"/></li>
			<li><img src="<c:url value="/images/study/logos/star/NabooStarFighter-128x128.png" />"/></li>
			<li><img src="<c:url value="/images/study/logos/star/RepublicCruiser-128x128.png" />"/></li>
			<li><img src="<c:url value="/images/study/logos/star/Tibirium-128x128.png" />"/></li>
			<li><img src="<c:url value="/images/study/logos/star/TieFighter-128x128.png" />"/></li>
			<li><img src="<c:url value="/images/study/logos/star/YWing-128x128.png" />"/></li>
		</ul>
	</div>
	<form:input path="logo" cssClass="text" size="75" cssStyle="display: none;" />
</p>

<script type="text/javascript">
$(document).ready(function() {
	$("#logos").PikaChoose({
		show_captions:false, 
		slide_enabled:false,
		show_prev_next:false,
		delay_caption:false
	});
	$("#logos img").click(function () {
		var imgToken = "/images";
		var src = $(this).attr("src");
		src = imgToken + src.split(imgToken)[1];
		$("#logo").attr("value", src);
	});
});
</script>
