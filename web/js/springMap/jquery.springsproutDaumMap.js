/*
 * jQuery Daum Map Plugin
 * version: 1.00 (08-15-2010)
 * @requires jQuery v1.4.2 or later
 * @Developer June http://starplatina.tistory.com
 *
 * Dual licensed under the MIT and GPL licenses:
 *   http://www.opensource.org/licenses/mit-license.php
 *   http://www.gnu.org/licenses/gpl.html
 *   
 *   다음 지도 API에서 상세 주소를 제공하지 않으므로( 현재 동 까지만 -_-;; 이후에 상세 주소 제공하면 다시 개발 -_-)
 *
 */
;(function($) {
	$.fn.springsproutDaumMap = function( options){

		var defaults = {
			mapContainer : 	$( this).attr('id'),
			apiKey : 		'',
			format : 		'json',
			iconUrl :		'',
			callback : 		''
		};
		var options = $.extend(defaults, options),
			map;

		function init() {
			initMap();
			initEvent();
		}
		
		function initMap() {
			map =  new DMap(options.mapContainer, {point:new DLatLng(37.57274610034383, 126.97792053222656), level:4});
			var zoomControl = new DZoomControl();
			var mapTypeControl = new DMapTypeControl();
			map.addControl(zoomControl);
			map.addControl(mapTypeControl);
			zoomControl.setVAlign('middle');
		}
		
		function initEvent() {
			DEvent.addListener( map, 'click', function( point) { 
				$.getJSON('http://apis.daum.net/maps/coord2addr?callback=?',
						{apikey:options.apiKey, longitude:point.x, latitude:point.y, output:options.format, inputCoordSystem:'WGS84'}, 
						function( json) {
							var address = json.fullName;
							var confirmHtml = '<br/><br/><strong style="padding-left: 85px;"><span class="placeconfirm action">예</span> / <span class="placeCancel action">아니오</span></strong>';
							var iw = new DInfoWindow("주소 : <strong>"+address+"</strong><br/><span>이곳을 모임장소로 선택 하시겠습니까?</span>" + confirmHtml, {width:300, height:100, show:true});
							var mark = new DMark( point, {infowindow:iw, draggable:true, mark:new DIcon(options.iconUrl, new DSize(60, 31))});
							map.setCenter( point, 3);
							map.addOverlay( mark);
						});
			});
			
		}
								
		 // 모임 장소 선택/취소시 사용될 이벤트 핸들러 등록.
	    $('.placeconfirm').live('click', function() {
	    	alert( $(this).text());
	    });

	    $('.placeCancel').live('click', function(){
	    });
	    
	    init();
	};
	
	$.fn.showMeetingLocation = function( options) {
		var defaults = {
				mapContainer : 	$(this).attr('id'),
				latitude : 		'',
				longitude : 	'',
				iconUrl :		'http://www.springsprout.org/images/markerpin.png'
		};
		var options = $.extend(defaults, options), map, point;

		function init() {
			initMap();
		}
		
		function initMap() {
			point = new DLatLng( options.latitude, options.longitude);
			map =  new DMap( options.mapContainer, { point:point, width:600, height:400});
			var zoomControl = new DZoomControl(), mapTypeControl = new DMapTypeControl();
			map.addControl( zoomControl);
			map.addControl( mapTypeControl);
			map.addOverlay( makeMark());
		}
		
		function makeMark() {
			var iw = new DInfoWindow( '<strong>이번 모임 장소는 여기에요!</strong>', {width:160, height:30, show:true, removable:false});
			return new DMark(point, {
                infowindow: iw,
                draggable: false,
                mark: new DIcon(options.iconUrl, new DSize(60, 31),new DPoint(-21,-32))                
            });
		};
		
		init();
	};

})(jQuery);
