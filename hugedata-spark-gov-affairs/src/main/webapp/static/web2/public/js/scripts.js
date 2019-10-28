$(function() {

   /* $(".index-bottom").mouseenter(function(){  //首页底部显示效果
            $(this).css("bottom","0px");
    }).mouseleave(function(){
        $(this).css("bottom","-110px");
    });*/
    $(".tab-btn a").click(function(){
        $(this).addClass("on").siblings().removeClass("on");
        $(this).parents().next(".tab-detail").find(".tab-details").eq($(this).index()).css("display","block").siblings().css("display","none");
    });


    $(".close-btn").click(function(){
        $(this).parent().remove();
    });

    $(".op-cloud,.op-save").on('click',function(){ //  云主机-申请记录弹出层    云储存-申请记录弹出层
        var url = $(this).attr('href');
        var title = $(this).attr('title');
        $.post(url,{rand:Math.random()},function(msg){
            MU.alert(msg,560,title);
        },'html');
        return false;
    });

    $(".op-enclosure").on('click',function(){ //  附件数弹出层
        var url = $(this).attr('href');
        var title = $(this).attr('title');
        $.post(url,{rand:Math.random()},function(msg){
            MU.alert(msg,600,title);
        },'html');
        return false;
    });

    $(".cloud-btn").on('click',function(){ //是否立即申请按钮
        var url = $(this).attr('href');
        var title = $(this).attr('title');
        $.post(url,{rand:Math.random()},function(msg){
            MU.alert(msg,360,title);
        },'html');
        return false;
    });

    $(".op-add").on('click',function(){ //  项目管理-添加弹出层
        var url = $(this).attr('href');
        var title = $(this).attr('title');
        $.post(url,{rand:Math.random()},function(msg){
            MU.alert(msg,560,title);
            $(".select").each(function(){  // 下拉列表
                $(this).children('select').selectList();
            });
        },'html');
        return false;
    });

    $(".op-del").on('click',function(){ //删除按钮
        var id = $(this).parent().parent().attr('data-id');
        MU.conFirm('删除','您确定要删除吗？<input id="itemId" type="hidden" value="'+id+'" />',delInfo);

    });
    function delInfo(){
        var id = $("#itemId").val();
        $("#item-"+id).remove();
    }

    /*$(".tab-content-left dd a").on('click',function(){  //项目管理-项目月报左侧菜单栏高亮选中
        $(this).addClass('on').parent().siblings().find('a').removeClass('on');
    });*/

    /*图片轮播*/
    var slide = function(){
        $(".slideList").stop(true,false).animate({'margin-left':-98},500,function(){
            $(this).removeAttr('style');
            $(this).find("li:first").appendTo($(this))
        });
    };
    var autoPlay = setInterval(slide,1000);
    $(".prize").on({
        mouseenter:function(){
            clearInterval(autoPlay);
           $(this).find(".item.item1").hover(function(){
               $(this).find("img").css("width",$(this).width()+10).css("height",$(this).height()+10);
               $(this).find(".slide-tit").css("display","none");
               return false;
           }).mouseleave(function(){
                $(this).find("img").css("width",$(this).width()-10).css("height",$(this).height()-20);
                $(this).find(".slide-tit").css("display","block");
               return false;
            });
        },
        mouseleave:function(){
            autoPlay = setInterval(slide,3000);
        }
    });

       $(".column_tab_table td a.del-tr").click(function(){
           $(this).parent().parent().remove();
       });

    $("#submitPassword").on('click',function(){//找回密码

        var email = $.trim($("#email").val());
        var code = $.trim($("#code").val());
        var password = $.trim($("#password").val());
        var passwords = $.trim($("#passwords").val());
        var imgCode = $.trim($("#imgCode").val());

        if(email == ''){
            var hdw1 = 'Email地址0~40个字符，请输入合法Email地址';
            $("#email").focus().next().html(hdw1);
            return false;
        }else if(email!="" && !/.+@.+\.[a-zA-Z]{2,4}$/.test(email)){
            var hdw1 = '邮箱的格式不正确';
            $("#email").focus().next().html(hdw1);
            return false;
        }else{
            $("#email").next().empty();
        }

        if(code == ''){
            var hdw1 = '验证码不得为空';
            $("#code").focus().parent().find("em").html(hdw1);
            return false;
        }else{
            $("#code").parent().find('em').empty();
        }

        if (password ==""){
            var hdw1 = '密码不得为空';
            $("#password").focus().next().html(hdw1);
            return false;
        }else if(password.length < 6){
            var hdw1 = '密码不得小于6位';
            $("#password").focus().next().html(hdw1);
            return false;
        }else{
            $("#password").next().empty();
        }

        if (passwords =="" || passwords != password){
            var hdw1 = '两次密码不一致';
            $("#passwords").focus().next().html(hdw1);
            return false;
        }else{
            $("#passwords").next().empty();
        }

        if(imgCode == ''){
            var hdw1 = '请输入右侧验证码';
            $("#imgCode").focus().parent().find("em").html(hdw1);
            return false;
        }else{
            $("#imgCode").parent().find('em').empty();
        }

    });

    $(".search-block h2 a,.se-title-tab .fl span,.se-filter .conbox a,.se-sort a").click(function(){  // 服务外包Tab切换
        $(this).addClass("on").siblings().removeClass("on");
    });

    $(".se-filter .show_more").click(function(){  //服务外包-行业类别点击更多
        var num = $(this).attr("data-id");
        if( num == 1){
            $(this).attr('data-id','0').removeClass('up');
            $(".se-filter .conbox").css("height","24px");
        }else{
            $(this).attr('data-id','1').addClass('up');
            $(".se-filter .conbox").css("height","auto");
        }
    });

    /*$(".se-del").on('click',function(){ //删除按钮
        var id = $(this).closest('li').attr('data-id');
        MU.conFirm('删除','您确定要删除吗？<input id="itemId" type="hidden" value="'+id+'" />',delMyRelease);

    });
    function delMyRelease(){
        var id = $("#itemId").val();
        $("#item-"+id).remove();
    }*/

    /*$(".add-standard").on('click',function(){  //  服务外包-发布供应-添加规格
        var html = '<li><label class="tit">添加规格：</label><input type="text" class="text w140" placeholder="规格名称"/><input type="text" class="text w140 m10" placeholder="单项价格"/><a class="del"></a></li>';
        $(this).parent().before(html);
    });

    $(".release-info").on('click','a.del',function(){  //  服务外包-删除规格
        $(this).parent().remove();
    });*/

    $(".collect-btn").click(function(){// 服务外包-详情-关注收藏
        if($(this).hasClass("on")){
            $(this).html('关注收藏');
            $(this).removeClass("on");
        }else{
            $(this).html('取消关注');
            $(this).addClass("on");
        }
    });

    $(".fast-btn").click(function(){// 服务外包-详情-加急
        if($(this).hasClass("on")){
            $(this).html('加急');
            $(this).removeClass("on");
        }else{
            $(this).html('取消加急');
            $(this).addClass("on");
        }
    });

    $(".se-article-block .more").click(function(){  //服务外包-详情页点击更多
        var num = $(this).attr("data-id");
        if( num == 1){
            $(this).attr('data-id','0');
            $(this).parent().next().removeClass('show');
        }else{
            $(this).attr('data-id','1');
            $(this).parent().next().addClass('show');
        }
    });



    $(".location-div a").click(function(){  // 服务外包-培训详情-右侧导航高亮
        var data = $(this).attr('data-id');
        $(this).addClass('on').siblings().removeClass('on');
        $(window).unbind('scroll');
        $("html,body").animate({scrollTop:$("#"+data).offset().top-20},800,function(){
            _scroll();
        });
    });
    var _scroll = function(){
        $(window).unbind('scroll').bind('scroll',function(){    // 服务外包-培训详情-右侧导航高亮
            $(".se-block").each(function(index, element) {
                var data = $(this).attr('id');
                var scrollerTop = $(document).scrollTop();
                var top = $(this).offset().top - 10;
                var bottom = $(this).offset().top + 43;
                var nextId=$(".se-block").eq(index+1).attr('id');
                var preId="kcjj";
                if(index>0){
                    preId=$(".se-block").eq(index-1).attr('id');
                }
                if(scrollerTop > bottom ){
                    $(".location-div a").removeClass('on');
                    $('.location-div a[data-id='+nextId+']').addClass('on');
                }
                if(scrollerTop< 250){
                    $(".location-div a").removeClass('on');
                    $('.location-div a[data-id="kcjj"]').addClass('on');
                }
            });

            var sc=$(window).scrollTop();
            if(sc>0){
                $("#goTopBtn").css("display","block");
            }else{
                $("#goTopBtn").css("display","none");
            }

        });
    }
    _scroll();

    $("#goTopBtn").click(function(){  //  回到顶部
        var sc=$(window).scrollTop();
        $('body,html').animate({scrollTop:0},500);
    });


});

