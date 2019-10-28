/*
懒人建站 http://www.51xuediannao.com/ 
懒人建站为您提供-基于jquery特效，jquery弹出层效果，js特效代码大全，JS广告代码，导航菜单代码，下拉菜单代码和jquery焦点图片代码。

jQ向上滚动带上下翻页按钮
*/
(function($){
$.fn.extend({
        Scroll:function(opt,callback){
                //参数初始化
                if(!opt) var opt={};
                var timerID;
                var _this=this.eq(0).find("div:first");
															
                var     lineH=_this.find("ul:first").height(), //获取行高									
                        line=opt.line?parseInt(opt.line,10):parseInt(this.height()/lineH,10), //每次滚动的行数，默认为一屏，即父容器高度
                        speed=opt.speed?parseInt(opt.speed,10):2000; //卷动速度，数值越大，速度越慢（毫秒）
                        timer=opt.timer //?parseInt(opt.timer,10):3000; //滚动的时间间隔（毫秒）
                if(line==0) line=1;
                lineH = 40;
                var upHeight=0-line*lineH;
                //滚动函数
                var scrollUp=function(){
                        _this.animate({
                                marginTop:upHeight
                        },speed,function(){
                                for(i=1;i<=line;i++){
                                        _this.find("ul:first").appendTo(_this);
                                }
                                _this.css({marginTop:0});
                        });

                }
                //Shawphy:向下翻页函数
                var scrollDown=function(){
                        for(i=1;i<=line;i++){
                                _this.find("ul:last").show().prependTo(_this);
                        }
                        _this.css({marginTop:upHeight});
                        _this.animate({
                                marginTop:0
                        });
                }
               //Shawphy:自动播放
                var autoPlay = function(){
                        if(timer)timerID = window.setInterval(scrollUp,timer);
                };
                var autoStop = function(){
                        if(timer)window.clearInterval(timerID);
                };
                 //鼠标事件绑定
                _this.hover(autoStop,autoPlay).mouseout();
        }       
})
})(jQuery);
