<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ tag pageEncoding="utf-8"%>


<div id="footer">
	<div class="footer">
			봄싹 is Rock!! 2009-2010 | Introduction | <a href="<c:url value="/developers"/>">Developers</a> |
	    <a href="http://s2c.pe.kr" target="_blank">Blog</a> |
	    <a href="http://groups.google.co.kr/group/springsprout" target="_blank">Group</a> |
	    <a href="http://dev.springsprout.org/code/browse/SpringSprout" target="_blank">Code Viewer</a> |
	    <a href="http://dev.springsprout.org/jira/browse/SSD" target="_blank">Issue Tracker</a>
	</div>
</div>
<script type="text/javascript">
	$('.fg-button').hover(
    	function(){ $(this).removeClass('ui-state-default').addClass('ui-state-focus'); },
    	function(){ $(this).removeClass('ui-state-focus').addClass('ui-state-default'); }
    );
</script>