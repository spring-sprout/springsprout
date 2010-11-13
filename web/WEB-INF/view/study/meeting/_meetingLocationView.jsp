<%--
  Created by IntelliJ IDEA.
  User: helols
  Date: 2009. 11. 28
  Time: 오후 1:29:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
	<div id="map" style="width:600px;height:400px;"></div>
	<script type="text/javascript">
		$('#map').showMeetingLocation({latitude:${meeting.location.latitude}, longitude:${meeting.location.longitude}})
	</script>

