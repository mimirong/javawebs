$(function() {

    $(".tab-content-left").css('min-height',$('.tab-content-right').height()+30);  //项目管理-项目月报-左侧菜单的高度

    $(".login-top .login-logo a").click(function(){
        $(this).addClass("on").siblings().removeClass("on");
    });

    $(".close-btn").click(function(){
        $(this).parent().remove();
    });

    $(".op-add,.op-modify,.op-apply,.op-resinfo").on('click',function(){ //  账号管理-添加用户/修改用户弹出层  服务外包管理-检验检测-我要申请按钮
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


    $(".op-check,.op-approval").on('click',function(){ //服务外包管理-检验检测-查看按钮&审批按钮
        var url = $(this).attr('href');
        var title = $(this).attr('title');
        $.post(url,{rand:Math.random()},function(msg){
            MU.alert(msg,360,title);
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

    $(".op-publish,.op-preview,.add-topic,.check-result,.op-condition,.op-export,.op-achievements,.op-conference,.op-next").on('click',function(){ //服务外包管理-成果展示-发布文章按钮  服务外包管理-成果展示-预览按钮  互动交流-调查征集-配置-新增题目  项目管理-年度计划-待审核弹出层   项目管理-按条件查询    添加/修改专家    添加/修改成果   会议培训添加修改培训弹窗
        var url = $(this).attr('href');
        var title = $(this).attr('title');
        $.post(url,{rand:Math.random()},function(msg){
            MU.alert(msg,600,title);
            $(".select").each(function(){  // 下拉列表
                $(this).children('select').selectList();
            });
            $("#beginDate").datepicker();
            $("#beginDate1").datepicker();
            $("#beginDate2").datepicker();
            $("#beginDate3").datepicker();
            $("#beginDate4").datepicker();
            $("#beginDate5").datepicker();
            $("#beginDate6").datepicker();

            $(".item-list").on('click','.add-choose',function(){  //互动交流-调查征集-新增题目-新增选项
                var html = '<li><input type="text" class="text w300" value=""/><a class="blue del-choose ml10">删除</a></li>';
                $(this).closest('ul').append(html);
            });
            $(".item-list").on('click','.del-choose',function(){ // 互动交流-调查征集-新增题目-删除选项
                $(this).parent().remove();
            });
            $(".pop-content1 .tab-div-btn a").click(function(){ //会议培训添加修改培训弹窗tab切换
                $(this).addClass("on").siblings().removeClass("on");
                $(".pop-content1 .tab-form").hide().eq($('.pop-content1 .tab-div-btn a').index(this)).show();
                MU.init();
            });
        },'html');
        return false;
    });

    $(".op-gov-check,.op-add-guide,.op-gov-exit,.op-reply,.add-survey,.op-check1,.op-gov-detail").on('click',function(){ //服务外包管理-综合政务-查看按钮   入园指南-添加/修改按钮   退园审批-查看按钮   互动交流-领导信息-回复按钮  互动交流-调查征集-新增调查按钮 综合政务-入园服务审批按钮
        var url = $(this).attr('href');
        var title = $(this).attr('title');
        $.post(url,{rand:Math.random()},function(msg){
            MU.alert(msg,760,title);
            $("#beginDate1").datepicker();
            $("#beginDate2").datepicker();
            $(".select").each(function(){  // 下拉列表
                $(this).children('select').selectList();
            });
        },'html');
        return false;
    });

    $(".deal-check,.add-check,.op-result").on('click',function(){ //行政审批处理-办件处理，添加办件弹出层    互动交流-调查征集-查看结果弹出层
        var url = $(this).attr('href');
        var title = $(this).attr('title');
        $.post(url,{rand:Math.random()},function(msg){
            MU.alert(msg,850,title);
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

    $(".op-deal").on('click',function(){ //窗口办理-办理按钮
        var id = $(this).parent().parent().attr('data-id');
        MU.conFirm('办理','状态设置为办结？<input id="itemId" type="hidden" value="'+id+'" />',deal);
    });
    function deal(){
        var id = $("#itemId").val();
        $("#item-"+id).find('a.op-deal').remove();
        $("#item-"+id).find('span.blue-font').text('办结').addClass('grey');
    }

    /*$(".tab-content-left dd a").on('click',function(){  //项目管理-项目月报左侧菜单栏高亮选中
        $(this).addClass('on').parent().siblings().find('a').removeClass('on');
    });*/

    $(".selectContainer").hover(function() {  //表格标题下拉列表显示隐藏
        $(this).children("ul").toggleClass("dis");
    });

    $(".img .del").on('click',function(){  //  删除上传图片
        $(this).parent().hide();
        $(this).parent().nextAll().show();
    });

    $(".op-isview").on('click',function(){  //首页banner是否可见
        var title = $(this).html();
        if(title == '隐藏'){
            $(this).html('显示');
            $(this).closest('tr').find('.isview').html('否');
        }else{
            $(this).html('隐藏');
            $(this).closest('tr').find('.isview').html('是');
        }
    });

    $(".op-isview1").on('click',function(){  //账号管理启用停用
        var title = $(this).html();
        if(title == '停用'){
            $(this).html('启用');
            $(this).closest('tr').find('.isview').html('不可用');
        }else{
            $(this).html('停用');
            $(this).closest('tr').find('.isview').html('可用');
        }
    });

    $(".op-release").on('click',function(){  //互动交流-调查征集-是否发布
        var title = $(this).html();
        if(title == '取消发布'){
            $(this).html('发布');
            $(this).closest('tr').find('.isrelease').html('未发布');
        }else{
            $(this).html('取消发布');
            $(this).closest('tr').find('.isrelease').html('已发布');
        }
    });

    //互动交流-调查征集-配置-上移
    var $up = $(".op-up");
    $up.click(function() {
        var $tr = $(this).parents("tr");
        if ($tr.index() != 0) {
            $tr.fadeOut().fadeIn();
            $tr.prev().before($tr);
        }
    });
    //互动交流-调查征集-配置-下移
    var $down = $(".op-down");
    var len = $down.length;
    $down.click(function() {
        var $tr = $(this).parents("tr");
        if ($tr.index() != len) {
            $tr.fadeOut().fadeIn();
            $tr.next().after($tr);
        }
    });

    $("#submitPassword").on('click',function(){//修改密码

        var oldPassword = $.trim($("#oldPassword").val());
        var password = $.trim($("#password").val());
        var passwords = $.trim($("#passwords").val());
        var imgCode = $.trim($("#imgCode").val());

        if (oldPassword ==""){
            var hdw1 = '请输入旧密码';
            $("#oldPassword").focus().next().html(hdw1);
            return false;
        }else{
            $("#oldPassword").next().empty();
        }

        if (password ==""){
            var hdw1 = '新密码不得为空';
            $("#password").focus().next().html(hdw1);
            return false;
        }else if(password.length < 6){
            var hdw1 = '新密码不得小于6位';
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
            var hdw1 = '请输入验证码';
            $("#imgCode").focus().parent().find("em").html(hdw1);
            return false;
        }else{
            $("#imgCode").parent().find('em').empty();
        }

        MU.msgTips('success','密码修改成功!',1200);
        MU.delay(function(){
            window.location.href = 'home.html'
        },1200);

    });

    $(".manual-enter").click(function(){  //行政审批-窗口办理-添加办件-手动输入材料名称功能
        $(".manual-enter-input").removeAttr("disabled");
    })

    $(".receivebox-close").click(function(){ //办件详情转交下一步
        $(this).parent().parent().hide();
    });

});

