	var vm = new Vue({
		  el: '#out',
		  data: {
		    guides: [],
		    getGuidesUrl: contextPath +'/parkApply/getApplyOutGuides',
		    contextPath:contextPath
		  },
		  mounted: function () {
			  this.$nextTick(function () {
				  this.getGuides()
			  })
			},
		  methods: {
			applyOut: function () {
                $(".applyPanelTitle").html("退园申请");
                MU.mask();
                MU.center("#applyPanel");
                $("#applyPanel").show();
		    },
		    applyDetail: function () {
                $(".applyDetailTitle").html("退园申请详情");
                MU.mask();
                MU.center("#applyDetail");
                $("#applyDetail").show();
		    },
              deleteApply: function (applyId) {
                  var vm = this;
                  MU.conFirm("删除", "确认删除申请?", function () {
                      vm.$http({
                          url: "deleteApplyOut",
                          method: "post",
                          params: {
                              applyId:applyId
                          },
                      }).then(function(response) {
                              if (response.data.result == 0) {
                                  //$scope.reload();
                                  location.reload();
                                  MU.msgTips("success", "删除成功", 1200);
                              } else {
                                  MU.msgTips("error", response.data.message, 1200);
                              }
                          },
                          function(err){
                              MU.msgTips("error", "删除失败", 1200);
                          }
                      );
                  });
              },
		    getGuides:function(){
		    	var vm = this;
		    	this.$http.get(this.getGuidesUrl)
                .then(function(response) {
                	if(response.ok && response.data.data.length > 0){
                		var guides = response.data.data;
                		for(var i=0;i< guides.length;i++){
                			var guide =  guides[i];
                			guide.publishTime = DateFormat.format.date(new Date(guide.publishTime), 'yyyy-MM-dd');
                		}
                		
                		vm.guides = guides;
                	}
                    
                },
                function(response) {
                    console.log(response)
                });
		    }
		  }
		});
	
		  //tab切换
	    $('.con_tab .con_tab_ul li').click(function(){
	        var index = $(this).index();
	        $('.con_tab .con_tab_box').eq(index).show().siblings('.con_tab .con_tab_box').hide();
	        $(this).addClass('active').siblings().removeClass('active');
	    });
	
		$("input[type='file']").live('change',function(){
			 
			 $(this).addClass("addFiled");
			 
		});


		$('li.submitApply a.submit-btn').live('click',function(){
			var companyName = $(this).closest("form").find("input[name='companyName']").val();
			var quitTime = $(this).closest("form").find("input[name='quitTime']").val();
			var applyFile =$(this).closest("form").find("input[name='applyFile']").hasClass('addFiled');
			var contacter = $(this).closest("form").find("input[name='contacter']").val();
			var mobile = $(this).closest("form").find("input[name='mobile']").val();
            if (!CheckService.telephone(mobile)) {
                MU.msgTips("warn", "请输入正确的联系电话", 1200);
                $("#contactTelephone").focus();
                return null;
            }

			if(!companyName || !quitTime || !contacter || !mobile || !applyFile){
				MU.msgTips("error", '请将内容填写完整!');
				return false;
			}
			
			$(this).closest('form').submit();
			return false;
		});
	

