/**
 * Created by IntelliJ IDEA.
 * User: ImYoon
 * Date: 2/27/12
 * Time: 11:41 PM
 * To change this template use File | Settings | File Templates.
 */
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
};
window.ss = new SpringSprout();
ss.modules = ss.prototype = ss.prototype;
jQuery(function($){
  ss.init();
});
