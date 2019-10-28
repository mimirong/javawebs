(function($){

	$.fn.selectList = function(options){

		var opts = $.extend({}, $.fn.selectList.defults, options);

		var $this = $(this),
			select = "<div class='select-input'></div>",
			selectList = "<ul class='select-list'></ul>",
			selectedVal = $this.children("option[selected ='selected']").length!=0 ? $this.children("option[selected ='selected']") : $this.children("option:first"),
			selectedValIndex = selectedVal.index();

			$this.hide().nextAll().remove().end().after(selectList).after(select);

		var selectUl = $this.siblings('ul'),
			selectInput = $this.siblings('div.select-input');

			$this.children('option').each(function(index){
				var data = $(this).attr('data')==undefined ? $(this).text() : $(this).attr('data');
				var value = $(this).attr('value');
				selectUl.append('<li data-value="' + value + '" title="'+data+'">'+$(this).text()+'</li>');
			});
			
			$this.on("change", function(e) {
				$.each(selectUl.children("li"), function(i, li) {
					if ($(li).attr("data-value") == $(e.target).val()) {
						var thisText = $(li).html();
						selectUl.children('li').removeClass('cur');
						$(li).addClass('cur').parents('ul').siblings('div.select-input').text(thisText).attr('title',thisText);
					}
				});
			});
			
			selectUl.children('li:eq('+selectedValIndex+')').addClass('cur');
			var curItem = selectUl.children('li:eq('+selectedValIndex+')').text();
			var curText = selectUl.children('li:eq('+selectedValIndex+')').attr('title');
			selectInput.text(curItem).attr('title',curText);

		$this.siblings('div').click(function(event){
			$(this).addClass('focus');
			$('.select-list').hide();
			selectUl.show().css({'z-index':'120',position:'absolute','width':$this.parents('.select').width()-2,'top':$this.parents('.select').outerHeight()+2});
			event.stopPropagation();
		});

		selectUl.children('li').bind('click',function(event){
			var index = $(this).index();
			thisText = $(this).html();
			selectUl.children('li').removeClass('cur');
			$(this).addClass('cur').parents('ul').siblings('div').text(thisText).attr('title',thisText);



            var tit = $(".select-input").attr("title");
           $("#txt-d").html(tit);


			selectUl.hide().css({'z-index':'1',position:'absolute'});
			selectInput.removeClass('focus');
			$this.children('option').removeAttr('selected');
			$this.children('option:eq('+index+')').prop('selected',true);
			//event.stopPropagation();
		});

		$(document).click(function(){
			selectUl.hide();
			selectInput.removeClass('focus');
		});

	}

	// $.fn.selectList.defults = {

	// }
	/*在原来的基础上构造下拉多选框*/
	$("div.select-input").on('click',function(event){
		var flag = $(this).hasClass('focus');
		$(this)[flag?'removeClass':'addClass']('focus');
		$(this).next('.select-list')[flag?'hide':'show']().css({'z-index':'9',position:'absolute','width':$(this).parent('.select').width()-2,'top':$(this).parent('.select').outerHeight()+2});
		event.stopPropagation();
	});
	$('.select-list li').on('click',function(event){
		event = window.event||event;
		if(document.all){
			event.cancelBubble = true;
		}else{
			event.stopPropagation();
		}
	})
	$(document).click(function(){
		$('.select-list').hide();
		$("div.select-input").removeClass('focus');
	});

})(jQuery);