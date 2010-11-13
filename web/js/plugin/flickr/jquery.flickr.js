/*
 * jQuery Flickr Plugin
 * version: 1.00 (12-11-2009)
 * @requires jQuery v1.2.6 or later
 * @Developer June http://starplatina.tistory.com
 *
 * Dual licensed under the MIT and GPL licenses:
 *   http://www.opensource.org/licenses/mit-license.php
 *   http://www.gnu.org/licenses/gpl.html
 *
 */
;(function($) {
	$.fn.favoriteFlickr = function( options){

		var defaults={
			method : 	'',
			api_key : 	'',
			format : 	'',
			text :		'',
			perpage : 12,
			imgSize : 'm',
			clickCallback : ''
		};
		var options = $.extend(defaults, options);

		var url = "http://api.flickr.com/services/rest/?method=" +
			options.method+"&format=" + options.format + "&api_key=" + options.api_key + "&text=" + options.text +"&per_page=" + options.perpage + "&license=2",
			target = $( this);

		function setDiv() {
			target.empty();
			target.append( '<div class="flickrThumbnail">'
				+ '<div class="flickrResultImgs"></div>' + '<div class="flickrNavi" align="center"></div></div>');
		};

		function getImgUrl( farm, server, id, secret, type) {
			var url = "http://farm"+farm+".static.flickr.com/"+server+"/"+id+"_"+secret;
			if ( type == 'default')
				return url + ".jpg";
			else
				return url +"_" + options.imgSize + ".jpg";

		};

		function makeNavigation( data) {
			var paging = makePaging( data.page, data.pages),
				flickNavi = $( '.flickrNavi');
			flickNavi.append( "<span class='flickrMoveFirst'>처음</span> | <span class='flickrBtnPrev'>이전</span>");
			$.each(paging, function(){
				if ( this == data.page ) {
					flickNavi.append( "<span class='flickrMovePageTo' style='color: red;'> "+this+" </span>");
				} else
					flickNavi.append( "<span class='flickrMovePageTo'> "+this+" </span>");
			});
			flickNavi.append( "<span class='flickrBtnNext'>다음</span> | <span class='flickrMoveLast'>마지막</span><div align='right'>"+
				data.page + "/ " + data.pages + " total : " + data.total + "</div>");

			$( '.flickrMoveFirst').click( function(){
				movePage( 'first', 1);
			});
			$( '.flickrMoveLast').click( function(){
				movePage( 'last', data.pages);
			});
			$( '.flickrMovePageTo').click( function(){
				movePage( 'default',  $( this).text());
			});
			$( '.flickrBtnNext').click( function(){
				movePage( 'next', data.page);
			});
			$( '.flickrBtnPrev').click( function(){
				movePage( 'prev', data.page);
			});
			flickNavi.children().hover( function() {
				$(this).css('font-weight', 'bold');
			}, function() {
				$(this).css('font-weight', '');
			}).css( 'cursor', 'pointer');
		};

		function makePaging( page, total) {
			var paging = new Array();
			if ( page <= 5) {
				for ( var i = 0, end = total; i < end; i++) {
					if ( i >= 10) break;
						paging.push( i + 1);
				}
			} else {
				for ( var i = page - 5, j = 0; i < page + 6; i++, j++) {
					if ( i >= total) break;
						paging.push( i + 1);
				}
			}
			return paging;
		};

		function movePage( type, page) {
			switch( type) {
				case 'next' :
					getList( ++page);
					break;
				case 'prev' :
					getList( --page);
					break;
				case 'first' :
					getList( 1);
					break;
				case 'last' :
					getList( page);
					break;
				default :
					getList( page);
					break;
			}
		};

		function getList( page) {
			refresh();
			$( '.flickrThumbnail').addClass( 'flickrIndicator');
			$.ajax({
				url : url+"&page="+page,
				type : 'get',
				dataType : 'jsonp',
				jsonp : 'jsoncallback',
				success : function(data) {
		          	$.each(data.photos.photo, function( index, val){
						var imgUrl = getImgUrl(val.farm, val.server, val.id, val.secret, null),
							nextImgUrl = getImgUrl(val.farm, val.server, val.id, val.secret, 'default');
						target.find( '.flickrResultImgs').append( "<a rel='group' title='"+nextImgUrl+"' href='#'>"+
								"<img src='"+imgUrl+"' class='flickrImg' width='128' height='128'></a>");

					});
		          	makeNavigation( data.photos);
		          	$( 'a').click( function(){
		          		$( this).find( 'img').addClass( 'flickrImgSelected');
						imgurl = $( this).attr( 'title');
						img = new Image();
						img.src = imgurl;
					}).mousedown( function(){
		          		$( '.flickrImgSelected').removeClass( 'flickrImgSelected');
					});
				},
				complete : function() {
		          	$( '.flickrThumbnail').removeClass( 'flickrIndicator');
				}
			});
		};
		
		$('.flickrImgSelected').live( 'click', function() {
			var $this = $(this);
			options.clickCallback( $this.attr('src'));
			//$this.flickrGetPhotoInfo( options.api_key, photoId);
		});

		function refresh() {
			$( '.flickrResultImgs').empty();
			$( '.flickrNavi').empty();
		};
		setDiv();
		getList( 1);
	};
	
	$.fn.flickrGetPhotoInfo = function( api_key, src) {
		var photoIdRegx = /\/[0-9]*\_/,
			photoId = src.match( photoIdRegx).toString(),
			linkUrl;
		photoId = (photoId.replace(/[^0-9]/, '')).replace(/[^0-9]/, '');
		var $this = $(this),
			userInfoUrl = "http://api.flickr.com/services/rest/?method=flickr.photos.getInfo" +
				"&format=json&api_key=" + api_key + "&photo_id=" + photoId;
		$.ajax({
			url : userInfoUrl,
			type : 'get',
			dataType : 'jsonp',
			jsonp : 'jsoncallback',
			success : function(data) {
				linkUrl = 'http://www.flickr.com/photos/' + data.photo.owner.nsid;
				$this.html( '<a href="' + linkUrl + '" target="_blank">' +  data.photo.owner.username + '</a> 님 사진');
			}
		});
		return $this;
	};

})(jQuery);
