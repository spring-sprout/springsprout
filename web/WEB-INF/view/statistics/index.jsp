<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<page:defaultpage selected_menu="statistics" ajaxlogin_yn="Y">
    <div id="content">
        <ul id="minitabs">
			<li><a href="<c:url value="/statistics/index" />" class="${study_tab}">Study</a></li>
			<li><a href="<c:url value="/statistics/member" />" class="${member_tab}">Member</a></li>
        </ul>
        <div style="clear:both;"></div>
        <div id="visualization"></div>
    </div>
    <script type="text/javascript">
        google.load('visualization', '1', {'packages':['motionchart']});

        function drawVisualization() {
            var data = new google.visualization.DataTable(${chartData}, 0.5);
            var motionchart = new google.visualization.MotionChart(document.getElementById('visualization'));

            //options: http://code.google.com/intl/ko-KR/apis/visualization/documentation/gallery/motionchart.html
            var options = {};
            if("${study_tab}" === "active") {
                options['state'] = '{"duration":{"timeUnit":"D","multiplier":1},"nonSelectedAlpha":0.4,"yZoomedDataMin":1,"yZoomedDataMax":17,"iconKeySettings":[],"yZoomedIn":false,"xZoomedDataMin":1252108800000,"xLambda":1,"time":"2009-09-05","orderedByX":false,"xZoomedIn":false,"uniColorForNonSelected":false,"sizeOption":"4","iconType":"BUBBLE","playDuration":15000,"dimensions":{"iconDimensions":["dim0"]},"xZoomedDataMax":1268438400000,"yLambda":1,"yAxisOption":"2","colorOption":"_UNIQUE_COLOR","showTrails":true,"xAxisOption":"_TIME","orderedByY":false}';
            }
            if("${member_tab}" === "active") {
                options['state'] = '{"duration":{"timeUnit":"D","multiplier":1},"nonSelectedAlpha":0.4,"yZoomedDataMin":1,"yZoomedDataMax":242,"iconKeySettings":[],"yZoomedIn":false,"xZoomedDataMin":1251244800000,"xLambda":1,"time":"2009-08-26","orderedByX":false,"xZoomedIn":false,"uniColorForNonSelected":false,"sizeOption":"_UNISIZE","iconType":"BUBBLE","playDuration":15000,"dimensions":{"iconDimensions":["dim0"]},"xZoomedDataMax":1268524800000,"yLambda":1,"yAxisOption":"2","colorOption":"_UNIQUE_COLOR","showTrails":true,"xAxisOption":"_TIME","orderedByY":false}';
            }
            options['width'] = 800;
            options['height'] = 400;

            motionchart.draw(data, options);
        }


        $(document).ready(function() {
            drawVisualization();
        });
    </script>
</page:defaultpage>