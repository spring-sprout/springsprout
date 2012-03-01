ss.modules.graffiti = (function(ss){
  var suda,
      that = {
    init : function(){
      that.setEvent();
      ss.loadJavascript('/static/js/socket.io.js',that.connectSocketIO);
      return that;
    },

    setEvent : function(){
      var gWriterWrap = $('.writer-text.wrap'),
          gWriterHolder = $('.writer-text.holder'),
          gWriterBox  = $('#g-writer-box');
      gWriterHolder.find('input').focus(function(e){
        e.preventDefault();
        gWriterHolder.hide();
        gWriterWrap.show();
        gWriterBox.click(function(e){
          e.preventDefault();
          e.stopPropagation();
        })
        .focus();
        $(document).click(function(e){
          e.preventDefault();
          gWriterWrap.hide();
          gWriterHolder.show();
          gWriterBox.unbind('click');
          $(this).unbind('click');
        });
      });
      gWriterWrap.find('a.btn').click(that.suda);
    },

    suda : function(e){
      e.stopPropagation();
      e.preventDefault();
      var gBox = $('#g-writer-box'),
          gWriterImg = $('.writer-thumb');
      if($.trim(gBox.val())){
        suda.emit('message',{name:gWriterImg.data('mName'),avatar:gWriterImg.attr('src'),id:gWriterImg.data('mId'),msg:gBox.val()});
        gBox.val('').focus();
      }
    },

    connectSocketIO : function(){
      suda = io.connect('http://'+document.location.hostname+':8888/suda');
      that.printSudaData();
    },

    printSudaData : function(){
      var sItems = $('.stream-items');
      suda.on('message', function (data) {
        if(data){
          var li =
            '<li class="stream-item">' +
              '<div class="writer-thumb-wrap">' +
              '<a href="/member/' + data.id + '">' +
              '<img class="writer-thumb" src="' + data.avatar + '" alt="' + data.name + '">' +
              '</a>' +
              '</div>' +
              '<div class="writer-text">' +
              '<div class="item-header"><strong>' + data.name + '</strong> <small class="item-time" title="2012.03.01 09:21:01">on 03.01 09:21:01</small></div>' +
              '<div class="item-content">' +
                data.msg +
              '</div>' +
              '</div>' +
              '</li>';
          sItems.prepend(li);
        }
      });
    }


  };
  return that;
})(ss);