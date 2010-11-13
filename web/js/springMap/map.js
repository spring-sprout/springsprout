var SpringMAP = function(options) {
    var self = this;
    /*ABQIAAAArT4vsI1EJr2kLc-f3wA9oBS9JFbsM2zZm8hfm-FX12j4bCsYFhTI_TcdktR_dgNl00f8bf0GseJeeA*/

    /* 서울 좌표 */
	var SEOUL_LAT = "37.57274610034383";
    var SEOUL_LNG = "126.97792053222656";
    /* 기본 줌 레벨 */
    var DEFAULT_ZOOM = 13;
    /* 마커가 표시될때 줌 레벨 */
    var MARKER_ZOOM = 16;

    var savedZoomLevle = DEFAULT_ZOOM;

    var mapObj;
	var geocoder;
    var baseIcon;

    var searched_marker;
    var generate_marker;
    var hidden_marker;

    var selectedLatlng;

    var tmp_meeting_place_name;
    var meeting_place;

    // 마커가 선택된후에 호출될 콜백
    var seletedLocationCallback = options.seletedLocationCallback || function() {};

    var confirmHtml = '<br/><br/><strong style="padding-left: 85px;"><span class="placeconfirm action">예</span> / <span class="placeCancel action">아니오</span></strong>';

    this.getMeetingPlace = function(){
        return tmp_meeting_place_name;
    };

    this.getLatLng = function(){
        return selectedLatlng;
    };

    /**
     * 지도에 마커를 표시한다. 외부에서 호출할 때는 name, latiude, longitude를 명시해야한다.
     * @param name {String} 장소명
     * @param latiude {Number} 위도
     * @param longitude {Number} 경도
     */
    this.setMarker = function(name, latitude, longitude) {
        if(name !== undefined && latitude !== undefined && longitude !== undefined) {
            tmp_meeting_place_name = name;
            selectedLatlng = new GLatLng(latitude, longitude);
            mapObj.panTo(selectedLatlng);
        }
        
        meeting_place = '** 이번 모임장소는 ** <br/><strong>'+tmp_meeting_place_name+'</strong> 입니다.';
        mapObj.closeInfoWindow(); // 기존에 열린 인포 윈도가 있으면 닫음.
        markerHide(); // 검색으로 장소를 찾았을시 검색된 마커 숨기기..
        createMarker(); // 마커를 만듬.
        mapObj.setZoom(MARKER_ZOOM);
        seletedLocationCallback(selectedLatlng, tmp_meeting_place_name);
    };
    
    /**
     * 지도에 마커를 표시한다. 외부에서 호출할 때는 name, latiude, longitude를 명시해야한다.
     * @param name {String} 장소명
     * @param latiude {Number} 위도
     * @param longitude {Number} 경도
     */
    this.setCenterMarker = function(name, latitude, longitude) {
        if(name !== undefined && latitude !== undefined && longitude !== undefined) {
            tmp_meeting_place_name = name;
            selectedLatlng = new GLatLng(latitude, longitude);
            mapObj.panTo(selectedLatlng);
        }
        var x = latitude;
        var y = longitude;
        meeting_place = '** 이번 세미나는 ** <br/><strong>'+tmp_meeting_place_name+'</strong> 입니다.';
        mapObj.closeInfoWindow(); // 기존에 열린 인포 윈도가 있으면 닫음.
        markerHide(); // 검색으로 장소를 찾았을시 검색된 마커 숨기기..
        createMarker(); // 마커를 만듬.
        mapObj.setZoom(MARKER_ZOOM);
        mapObj.setCenter(new GLatLng(x, y), MARKER_ZOOM);
        mapObj.openInfoWindow(mapObj.getCenter(), meeting_place);
       
    };
    /**
     * 마커 없애기..
     */
    var markerHide = function(){
        if(hidden_marker !== undefined){
            hidden_marker.show();
        }
        if(searched_marker !== undefined){
            searched_marker.hide();
        }
        if(generate_marker !== undefined){
            mapObj.removeOverlay(generate_marker);
        }
    };
   
    /**
     * Google Map에 Marker 추가하는 함수.
     */
    var createMarker = function(){
        generate_marker = new GMarker(selectedLatlng,{icon:baseIcon,clickable:true});

	    GEvent.addListener(generate_marker, "click", function() {
            simpleOpenInfoWindow(meeting_place);
        });

        mapObj.addOverlay(generate_marker);
	};

    /**
     * 검색후.. marker 만들때 callback.
     */
    var doGenerateMarkerHtmlCallback = function(marker,html,result) {
        selectedLatlng = marker.getLatLng();
        if(generate_marker !== undefined){ // 검색해서 이미 선택했으나 또다시 시도 했을때 ... 중복 방지용..ㅋ
            if(generate_marker.getLatLng() === marker.getLatLng()) {
                html.innerHTML=meeting_place;
                return html;
            }
        }
        searched_marker = marker;
        /**
         * marker hidden시.. undo를 위해서... toggle 기능을 함.
         */
        GEvent.addListener(searched_marker, "visibilitychanged", function(isVisible) {
            if(isVisible){
                hidden_marker = undefined;
            }else{
                hidden_marker = searched_marker;
            }
        });
        tmp_meeting_place_name =  result.title;
        html.innerHTML='<strong>'+ result.title  +'</strong> 를(을) 모임장소로 선택 하시겠습니까?'+confirmHtml;
        return html;
    };

    var simpleOpenInfoWindow = function(html){
        mapObj.openInfoWindow(generate_marker.getLatLng(),html);
    };

    /**
     * 로케이션 정보 가져오면서 정보창 만들어주기.
     * lat lng 정보와 함께 후속 callback(html crateed function need!! - agr: response)
     * @param latlng
     * @param callback
     */
    var getLocationWithOpenInfoWindow = function(latlng,callback){
        geocoder.getLocations(latlng, function(response){
            mapObj.openInfoWindow(latlng, callback(response));
        });
    };

   /********************************************************************************************************************
    * 초기화 시작
    *******************************************************************************************************************/
   //지도 객체 준비
   mapObj = new GMap2(document.getElementById(options.renderTo || "map_canvas"),{
        googleBarOptions : {
            onGenerateMarkerHtmlCallback : doGenerateMarkerHtmlCallback
        }
    });

    geocoder = new GClientGeocoder();

    // 서울을 시작 좌표 설정.
    mapObj.setCenter(new GLatLng(options.lat || SEOUL_LAT, options.lng || SEOUL_LNG), DEFAULT_ZOOM);

    // 지도 기본 UI 설정
    var ui = mapObj.getDefaultUI();
    ui.zoom.doubleclick = false;
    mapObj.setUI(ui);
    mapObj.enableGoogleBar();

    // 마커 아이콘 생성
    baseIcon = new GIcon(G_DEFAULT_ICON);
    baseIcon.image = 'http://www.springsprout.org/images/markerpin.png';
    baseIcon.iconSize = new GSize(60, 31);
    baseIcon.shadowSize = new GSize(0,0);
    baseIcon.iconAnchor = new GPoint(21, 32);  // new GPoint(x,y);   x는 <- 이방향. Y는 ↑ 방향.

    // 모임 장소 선택/취소시 사용될 이벤트 핸들러 등록.
    $('.placeconfirm').live('click', function() {

        if(self.getLatLng() !== undefined) {
            mapObj.savePosition();
            self.setMarker();
        }
    });

    $('.placeCancel').live('click', function(){
        mapObj.closeInfoWindow();
        mapObj.returnToSavedPosition();
    });

    // 사용자 정의 이벤트 등록
    GEvent.addListener(mapObj, "click", function(overlay, latlng) {
        searched_marker = undefined;
        if (latlng) {
            selectedLatlng = latlng;
            getLocationWithOpenInfoWindow(latlng, function(response){
                var placeInfo = response.Placemark[0];
                tmp_meeting_place_name = placeInfo.address;
                return $(document.createElement("div"))
                            .append('주소 : <strong>'+placeInfo.address+'</strong><br/>')
                            .append('<span>이곳을 모임장소로 선택 하시겠습니까?</span>')
                            .html()+confirmHtml;
            }); // geocoder.getLocations end
        }
    });

    GEvent.addListener(mapObj, "infowindowopen", function() {
        mapObj.panTo(selectedLatlng);
    });
};