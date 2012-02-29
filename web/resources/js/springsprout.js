var SpringSprout = function(){
  var that = this;
  this.init  = function(){
    that.initPopover();
  };

  this.initPopover = function(){
    $("a[rel=popover]").click(function(e){
      if (e.stopPropagation) {
        e.stopPropagation();
        e.preventDefault();
      }
    }).popover({delay: { show: 300, hide: 100 }});
  };

  this.require = function(modules,args){
    if($.isArray(modules)){
      $.each(modules,function(idx,module){
        if(ss.modules && ss.modules[module] && ss.modules[module].hasOwnProperty('init')){
          ss.modules[module]['init'](args);
        }else{
          that.loadJavascript('/static/js/modules/'+module+'.js',function(){
            if(ss.modules[module] && ss.modules[module].hasOwnProperty('init')){
              ss.modules[module]['init'](args);
            }
          });
        }
      });
    }
  };

  this.loadJavascript = function(src,callback) {
    var script = document.createElement("script");
    script.type = "text/javascript";
    script.src = src;
    if(callback){
      script.onload = callback;
    }
    document.body.appendChild(script);
  };
};
window.ss = new SpringSprout();
ss.modules = ss.prototype = SpringSprout.prototype;
jQuery(function($){
  ss.init();
});
