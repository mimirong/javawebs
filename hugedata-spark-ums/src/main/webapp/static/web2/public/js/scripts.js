$(function() {

   /* $(".index-bottom").mouseenter(function(){  //首页底部显示效果
            $(this).css("bottom","0px");
    }).mouseleave(function(){
        $(this).css("bottom","-110px");
    });*/

    $("body").on('click','label.checkbox',function(){   //单复选按钮
        if($(this).hasClass('disabled')){return false;}
        if($(this).hasClass('radio')){
            $(this).addClass('checked').siblings().removeClass('checked');
            var $name = $(this).attr('name');
            $(".radio[name="+$name+"]").removeClass('checked');
            $(this).addClass('checked');
        }else{
            $(this)[$(this).hasClass('checked')?'removeClass':'addClass']('checked');
            $(this).next().find('input').attr('disabled',!$(this).hasClass('checked'));
        }
    });

    $(".close-btn").click(function(){
        $(this).parent().remove();
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
    var autoPlay = setInterval(slide,3000);
    $(".prize").on({
        mouseenter:function(){
            clearInterval(autoPlay);
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

});

