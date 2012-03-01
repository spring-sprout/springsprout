ss.modules.graffiti = (function(ss){
  var suda,
      itemTemplate,
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
          gVal = $.trim(gBox.val());
      if(gVal){
        gVal = gVal.replace(/\n/ig,' ');
        suda.emit('message',{name:gWriterImg.data('mName'),avatar:gWriterImg.attr('src'),id:gWriterImg.data('mId'),msg:gVal});
        gBox.val('').focus();
      }
    },

    connectSocketIO : function(){
      suda = io.connect('http://'+document.location.hostname+':8888/suda');
      that.printSudaData();
    },

    makeItem : function(data){
      if(!itemTemplate){
        itemTemplate =   '<li class="stream-item">' +
                         '  <div class="writer-thumb-wrap">' +
                         '    <a href="/member/${id}">' +
                         '      <img class="writer-thumb" src="${avatar}" alt="${name}">' +
                         '    </a>' +
                         '  </div>' +
                         '  <div class="writer-text">' +
                         '    <div class="item-header"><strong>${name}</strong> <small class="item-time" title="${time}">on ${time}</small></div>' +
                         '    <div class="item-content">${msg}' +
                         '    </div>' +
                         '  </div>' +
                         '</li>';
      }
      var html = itemTemplate;
      if(data.writtenDate){
        data.time = that.covertDateString(data.writtenDate);
      }
      $.each(data, function(key, value){
        html = html.replace(new RegExp("\\$\\{" + key + "\\}", "gi"), value);
      });
      return html;

    },

    printSudaData : function(){
      var sItems = $('.stream-items');
      suda.on('message', function (data) {
        if(data){
          sItems.prepend(that.makeItem(data));
        }
      });
    },

    covertDateString : function(date){
      var date = new Date(date||Date.now()).toString().split(" "),
          month = ['month','Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'].indexOf(date[1]);
      if(month < 10){
        month = '0'+month;
      }
      return month+'.'+date[2]+' '+date[4];
    }
  };
  return that;
})(ss);