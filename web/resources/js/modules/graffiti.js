ss.modules.graffiti = (function(ss){
  var that = {
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
      $('#g-writer-box').val('');
    },

    connectSocketIO : function(){
      var suda = io.connect('http://'+document.location.hostname+':8888/suda');
    }
  };
  return that;
})(ss);