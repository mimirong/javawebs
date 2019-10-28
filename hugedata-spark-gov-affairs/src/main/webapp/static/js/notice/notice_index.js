angular.module("noticeIndexModule", [ ])

//Controller
.controller("noticeIndexController", ["$scope", "$timeout", function ($scope, $timeout) {
    $scope.isLoading = false;					            //是否显示正在加载
    $scope.pitcureNewsPage = { start: 0, length: 5 };		//图片新闻分页信息
    $scope.noticePage = { start: 0, length: 10 };		    //公告通知分页信息
    $scope.pictureNewsCategoryId = "NOTICE_PICTURE_NEWS";   //通知公告-图片新闻类别ID
    $scope.pictureNewsData = [];                            //图片新闻初始化数据
    $scope.pictureNewsIndex = [];                           //图片新闻序号
    $scope.noticeCategoryId = "NOTICE_NOTICE";              //通知公告-通知公告类别ID
    $scope.noticeData = [];                                 //公告通知初始化数据
    $scope.bannerImages = [];

    // load data
    $scope.loadData = function () {
        $scope.loadPictureNewsData();
        $scope.loadNoticeData();
    }

    // 顶部Banner
    $scope.loadAndInitTopBanner = function() {
    	$.ajax({
    		url: "queryBannerImages",
    		type: "post",
    		dataType: "json",
    		data: {},
    		success: function(resp) {
    			if (resp && resp.result == 0) {
    				$scope.bannerImages = resp.data;
    				$.each($scope.bannerImages, function(i, img) {
    					if (!img.linkUrl || img.linkUrl == "") {
    						img.linkUrl = "javascript:;";
    					}
    					img.previewUrl = DownloadService.buildUrl(img.previewFileId, img.previewFileName);
    				});
    				$scope.$apply();
    				
    				$timeout(function() {
        				$scope.initTopBanner();
    				});
    			} else {
    				MU.msgTips("error", resp.result);
    			}
    		},
    		error: function() {
    			MU.msgTips("error", "加载图片信息失败，请稍后重试");
    		}
    	});
    }
    
    // 初始化顶部Banner滚动
    $scope.initTopBanner = function () {
        var t = n = count = 0;
        count = $("#play_list a").size();  // 通知公告头部banner轮播
        
        $("#play_list a:not(:first-child)").hide();
        $("#play_text li:first-child").css("background", "#ff6501");
        $("#play_text li").click(function () {
            var i = $(this).text() - 1;
            n = i;
            if (i >= count) return;
            $("#play_list a").filter(":visible").fadeOut().parent().children().eq(i).fadeIn();
            $(this).css("background-color", "#ff6501").siblings().css("background-color", "#fff");
        });
        
        t = setInterval(function () {
            n = (n + 1) % count;
            $("#play_text li").eq(n).trigger('click');
        }, 3000);
        
        $("#play").hover(
    		function () {
    			clearInterval(t)
    		}, 
    		function () {
	            t = setInterval(function () {
	                n = (n + 1) % count;
	                $("#play_text li").eq(n).trigger('click');
	            }, 3000);
    		}
		);
    }

    // 初始化图片新闻滚动
    $scope.initPictureNewsBanner = function () {
        var t2 = n2 = count2 = 0;
        count2 = $("#recommend_list a").size();   // 通知公告推荐文章轮播
        $("#recommend_list a:not(:first-child)").hide();
        $("#recommend_info").html($("#recommend_list a:first-child").find("img").attr('alt'));
        $("#recommend_text li:first-child").css("background", "#f7f7f7");
        $("#recommend_info").click(function () {
            window.open($("#recommend_list a:first-child").attr('href'), "_self")
        });
        $("#recommend_text li").click(function () {
            var i2 = $(this).text() - 1;
            n2 = i2;
            if (i2 >= count2) return;
            $("#recommend_info").html($("#recommend_list a").eq(i2).find("img").attr('alt'));
            $("#recommend_info").unbind().click(function () {
                window.open($("#recommend_list a").eq(i2).attr('href'), "_self")
            })
            $("#recommend_list a").filter(":visible").fadeOut().parent().children().eq(i2).fadeIn();
            $(this).css("background-color", "#f7f7f7").siblings().css("background-color", "rgba(247,247,247,.5)");
        });
        t2 = setInterval(function () {
            n2 = n2 >= (count2 - 1) ? 0 : n2 + 1;
            $("#recommend_text li").eq(n2).trigger('click');
        }, 3000);
        $("#recommend").hover(function () {
            clearInterval(t2)
        }, function () {
            t2 = setInterval(function () {
                n2 = n2 >= (count2 - 1) ? 0 : n2 + 1;
                $("#recommend_text li").eq(n2).trigger('click');
            }, 3000);
        });

    }

    // load picture news data
    $scope.loadPictureNewsData = function () {
        $scope.isLoading = true;
        $.ajax({
            url: contextPath + "/article/listArticles",
            type: "post",
            dataType: "json",
            data: {
                start: $scope.pitcureNewsPage.start,
                length: $scope.pitcureNewsPage.length,
                categoryId: $scope.pictureNewsCategoryId
            },
            success: function(resp) {
                $scope.isLoading = false;
                // 拼接预览图路径
                    for (var i = 0, size = resp.data.length; i < size; i++) {
                    $scope.pictureNewsIndex.push({"index": i + 1});
                    var coverFileId = resp.data[i].coverFileId;
                    var coverFileName = resp.data[i].coverFileName;
                    if (coverFileId != null && coverFileName != null) {
                        var imgSrc = DownloadService.buildUrl(coverFileId, coverFileName);
                        resp.data[i].imgSrc = imgSrc;
                    }
                }
                $scope.pictureNewsData = resp.data;
                $scope.$apply();
                $scope.initPictureNewsBanner();
            },
            error: function() {
                alert("加载数据失败");
                $scope.isLoading = false;
                $scope.$apply();
            }
        });
    }

    // load notice data
    $scope.loadNoticeData = function () {
        $scope.isLoading = true;
        $.ajax({
            url: contextPath + "/article/listArticles",
            type: "post",
            dataType: "json",
            data: {
                start: $scope.noticePage.start,
                length: $scope.noticePage.length,
                categoryId: $scope.noticeCategoryId
            },
            success: function(resp) {
                $scope.isLoading = false;
                // 拼接预览图路径
                for (var i = 0, size = resp.data.length; i < size; i++) {
                    var coverFileId = resp.data[i].coverFileId;
                    var coverFileName = resp.data[i].coverFileName;
                    if (coverFileId != null && coverFileName != null) {
                        var imgSrc = DownloadService.buildUrl(coverFileId, coverFileName);
                        resp.data[i].imgSrc = imgSrc;
                    } else {
                        resp.data[i].imgSrc = "";
                    }
                }
                $scope.noticeData = resp.data;
                $scope.$apply();
            },
            error: function() {
                alert("加载数据失败");
                $scope.isLoading = false;
                $scope.$apply();

            }
        });
    }

    // init
    $scope.loadData();
    $scope.loadAndInitTopBanner();

}]);