<html>
<head>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>I'm tracking you!</title>
<script type="text/javascript"
	src="http://maps.google.com/maps/api/js?sensor=true"></script>
<script type="text/javascript">
	function $(id){
		return document.getElementById(id);
	}
	var trackerId = 0;
	var geocoder;
	var theUser = {};
	var map = {};
	function initialize() {
		geocoder = new google.maps.Geocoder();
		if (navigator.geolocation){
			var gps = navigator.geolocation;
			gps.getCurrentPosition(function(pos){
				var latLng = new google.maps.LatLng(pos.coords.latitude,pos.coords.longitude);
				var opts = {zoom:12, center:latLng, mapTypeId: google.maps.MapTypeId.ROADMAP};
				map = new google.maps.Map($("map_canvas"), opts);
				theUser = new google.maps.Marker({
					position: latLng,
					map: map,
					title: "You!"
				});
				showLocation(pos);
			});
			trackerId = gps.watchPosition(function(pos){
				var latLng = new google.maps.LatLng(pos.coords.latitude,pos.coords.longitude);
				map.setCenter(latLng);
				theUser.setPosition(latLng);
				showLocation(pos);
			});
		}
  }
	function showLocation(pos){
		var latLng = new google.maps.LatLng(pos.coords.latitude,pos.coords.longitude);
	    if (geocoder) {
	        geocoder.geocode({'latLng': latLng}, function(results, status) {
	          if (status == google.maps.GeocoderStatus.OK) {
	            if (results[1]) {
		            $("location").innerHTML = results[1].formatted_address;
	            } 
	          } 
	        });
	      }		
	}
	function stopTracking(){
		if (trackerId){
			navigator.geolocation.clearWatch(trackerId);
		}
	}
</script>
</head>
<body style="margin: 0px; padding: 0px;" onload="initialize()">
<div id="superbar"><span class="msg">Current location: <span
	id="location"></span> </span> <input type="button" value="Stop tracking me!"
	onclick="stopTracking()" /></div>
<div id="map_canvas"
	style="width: 100%; height: 90%; float: left; border: 1px solid black;">
</div>
</body>
</html>
