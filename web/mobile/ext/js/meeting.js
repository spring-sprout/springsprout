/*
*	javascript for springsprout mobile_meeting
*	v0.1
*/


$(document).ready(function(){
	$(document.createElement('script')).attr('src',"http://maps.google.com/maps/api/js?sensor=true&language=ko&callback=mapsLoaded").appendTo('head');
	$(document.createElement('script')).attr('src',"http://code.google.com/apis/gears/gears_init.js").appendTo('head');
	/*
	 * 모임장소 팝업
	 */
	$('#meeting_map').live('pageshow',function(event){
		if(!group_map) group_map = new spMap("map");

		$("#meeting_map_curpoint").unbind("click");
		$("#meeting_map_curpoint").click(function(){
			group_map.curpoint();
		});
		  //return false;
	});
	
	$('#meeting_list').live('pagebeforecreate',function(event){
		//Ajax 데이타는 이런식으로 뿌리면...
		if(!$("#list_list").html())
		for(var i=0;i<5;i++){
			var txt = '<li><div class="openDate" style="margin-right:5px"><span class="date"><span class="month">3월</span><span class="day">26</span></span></div><h3><a href="view_home.html">3회, 특별 초청 게스트가 온다!!</a></h3><p>토즈강남점(10:00~14:00)</p><a href="view_home.html">Purchase album</a></li>';
			$("#list_list").append(txt);
		}
	});
	
	$('#meeting_member').live('pagebeforecreate',function(event){
		//Ajax 데이타는 이런식으로 뿌리면...
		if(!$("#member_list").html())
		for(var i=0;i<5;i++){
			var txt = '<li><div class="openDate" style="width:60px">&nbsp;<img style="margin:5px;height:60px;float:left" src="http://www.gravatar.com/avatar/ab06ea90f8ab5dc71114fc46001814cf?r=X&amp;s=50"></div><div style="margin-left:60px"><h3>UnlogicalDev</h3><p>참석률: 100%(66%), 신뢰도: 100%(91%)</p></div></li>';
			$("#member_list").append(txt);
		}
	});
	

	$('#meeting_subject').live('pagebeforecreate',function(event){
		//Ajax 데이타는 이런식으로 뿌리면...
		if(!$("#subject_list").html())
		for(var i=0;i<5;i++){
			var txt = '<li><div class="openDate" style="width:60px">&nbsp;<img style="margin:5px;height:60px;float:left" src="http://www.gravatar.com/avatar/ab06ea90f8ab5dc71114fc46001814cf?r=X&amp;s=50"></div><div style="margin-left:60px"><h3>NoSql with MongoDB Part1</h3><p>발표자: 이용혁, 주제: MongoDB, 댓글갯수:2, 자료갯수: 2, 뷰: 75, 상태: 종</p></div></li>';
			$("#subject_list").append(txt);
		}
	});
	

	$('#meeting_resources').live('pagebeforecreate',function(event){
		//Ajax 데이타는 이런식으로 뿌리면...
		if(!$("#resources_list").html())
		for(var i=0;i<5;i++){
			var txt = '<li><h3><a href="#">UnlogicalDev</a></h3><p>자료설명</p></li>';
			$("#resources_list").append(txt);
		}
	});
	

	$('#meeting_talk').live('pagebeforecreate',function(event){
		//Ajax 데이타는 이런식으로 뿌리면...
		if(!$("#talk_list").html())
		for(var i=0;i<5;i++){
			var txt = '<li><div class="openDate" style="width:60px">&nbsp;<img style="margin:5px;height:90%;float:left" src="http://www.gravatar.com/avatar/ab06ea90f8ab5dc71114fc46001814cf?r=X&amp;s=50"></div><div style="margin-left:75px"><h3>UnlogicalDev</h3><p>의견은 여기에 이렇게 남기고..</p><p>'+new Date()+'</p></div></li>';
			$("#talk_list").append(txt);
		}
	});
});



var group_map;

function spMap(id){
	this.id = id;
	this.init();
}

spMap.prototype.init = function(){
	$("#"+this.id).height($(document).height()/100*49);
	this.infowindow = new google.maps.InfoWindow();
	this.browserSupportFlag =  new Boolean();
	
	var myOptions = {
		zoom: 16,
		navigationControl: true,
		mapTypeControl: false,
		scaleControl: false,
		mapTypeId: google.maps.MapTypeId.ROADMAP
	  }
	this.map = new google.maps.Map(document.getElementById(this.id), myOptions);
	this.map.setCenter(new google.maps.LatLng(37.566535,126.977969));
	this.addMarker(new google.maps.LatLng(37.566535,126.977969), "../images/ico_blank_02.png");
	//requestGoogleGears();
	//isMap = true;
}
spMap.prototype.curpoint = function(){
	var self = this;
	var latLng;
	if(navigator.geolocation) {
		  navigator.geolocation.getCurrentPosition(function(position) {
			latLng = new google.maps.LatLng(position.coords.latitude,position.coords.longitude);
			self.addMarker(latLng, "../images/ico_blank_01.png");
			self.map.setCenter(latLng);
		  });
	} else if (google.gears) {
		  var geo = google.gears.factory.create('beta.geolocation');
		  geo.getCurrentPosition(function(position) {
			latLng = new google.maps.LatLng(position.latitude,position.longitude);
			self.addMarker(latLng, "../images/ico_blank_01.png");
			self.map.setCenter(latLng);
		  });
	} else {
		latLng = new google.maps.LatLng(37.566535,126.977969);
		self.addMarker(latLng, "../images/ico_blank_01.png");  
		self.map.setCenter(latLng);
	}
	
}
spMap.prototype.addMarker = function(latLng, imageUrl){
	var self = this;
	var image = new google.maps.MarkerImage(
			(imageUrl ? imageUrl:"../images/ico_blank_02.png"),
	      new google.maps.Size(38, 38),
	      new google.maps.Point(0,0),
	      new google.maps.Point(19,51),
	      new google.maps.Size(38, 38));
	var shadow = new google.maps.MarkerImage(
			  "../images/icon_map_bg.png",
		      new google.maps.Size(46, 55),
		      new google.maps.Point(0,0),
		      new google.maps.Point(23, 55));

    self.map.panTo(latLng);
	var marker = new google.maps.Marker({
		position: latLng,
		map: self.map,
		icon: image,
		shadow: shadow
	  });
}

function mapsLoaded(){
	//createMap("map");
}