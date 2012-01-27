<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ tag pageEncoding="utf-8"%>


<div id="footer">
	<div class="footer">
			Powered by 봄싹! 2009-2012 |
        <a href="<c:url value="/developers"/>">Developers</a> |
	    <a href="http://s2c.pe.kr" target="_blank">Blog</a> |
	    <a href="http://groups.google.co.kr/group/springsprout" target="_blank">Group</a>
	</div>
</div>
<script type="text/javascript">
	$('.fg-button').hover(
    	function(){ $(this).removeClass('ui-state-default').addClass('ui-state-focus'); },
    	function(){ $(this).removeClass('ui-state-focus').addClass('ui-state-default'); }
    );
</script>