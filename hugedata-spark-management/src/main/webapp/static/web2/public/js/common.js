$(function(){
	/**
	 * 左侧菜单栏
	 */
	 $('.left ul li').click(function(){
		$(this).addClass('current').siblings().removeClass('current');
		$(this).find('div').css('display','block');
		$(this).siblings().find('div').css('display','none');
	 });
	 /*用户退出*/
	  $('.dropdown_toggle').click(function(){
        $('.dropdown_menu').toggle();
    });
	  /*二级菜单*/
	  $('.container .menu .cen li').mouseenter(function(){
	    $(this).addClass('on');
	  }).mouseleave(function(){
	    $(this).removeClass('on');
	  });
	  
	 /*下拉选择*/	
	$(".select_input").on('click','.input',function(){
		$(this)[$(this).hasClass('focus')?'removeClass':'addClass']('focus');
		$(this).next()[$(this).hasClass('focus')?'show':'hide']();
		return false;
	});
	$(".select_input").on('click','li',function(){
		var txt = $(this).text();
		$(this).parent().prev().text(txt);
	});

	/*搜索按钮选中效果*/
	$('.container .search_div input').focus(function(){
		$(this).parent().addClass('sear_click');
		$(this).next().addClass('active');
	}).blur(function(){
		$(this).parent().removeClass('sear_click');
		$(this).next().removeClass('active');
	});


	$(document).on('click',function(){
		$(".select_input .input").removeClass('focus');
		$(".select_input .select_list").hide();
	});
	
	
	$("body").on('click','span.checkbox',function(){  //单选&多选按钮
		if($(this).hasClass('disabled')){return false;}
		if($(this).hasClass('radio')){
			$(this).addClass('checked').siblings().removeClass('checked');
		}else{
			$(this)[$(this).hasClass('checked')?'removeClass':'addClass']('checked');
			$(this).next().find('input').attr('disabled',!$(this).hasClass('checked'));
		}
	});

	$("#returnTop").on('click',function(){//返回顶部
		$("html,body").animate({"scrollTop":0},400);
	});

	/*悬浮框*/
	getTop();
    /*搜索框*/
    $('.search_div .input_test').bind({
        focus:function(){
            if (this.value == this.defaultValue){
                this.value="";
            }
        },
        blur:function(){
            if (this.value == ""){
                this.value = this.defaultValue;
            }
        }
    });
});
function getTop(){
    var top = $(document).scrollTop();
	var right = ($(window).width() - 1000)/2 -56;
    if(top>100){
        $(".fixed").css({
            'display':'block',
			'right':right
        });
    } else {
        $(".fixed").css('display','none');
    }
    setTimeout(getTop);
}
