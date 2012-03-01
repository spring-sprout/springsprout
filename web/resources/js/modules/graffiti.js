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
          //console.log(data);
        }
      });
    }


  };
  return that;
})(ss);