var MAX_ATTACHMENTS = 1;

var modifyingId = null;
var uploadAttachmentData = [];

$("#contentEditorModify").ckeditor();

var modifyingId = $("#articleId").val();
        $.ajax({
            url: "toModify",
            type: "post",
            dataType: "json",
            data: { id:modifyingId },
            success: function(resp) {
                if (resp && resp.result == 0) {
                    $("#title").val(resp.data.title);
                    // $("#brief").val(resp.data.brief);
                    // $("#author").val(resp.data.author);
                    // $("#source").val(resp.data.source);
                    CKEDITOR.instances["contentEditorModify"].setData(resp.data.content);
                    uploadAttachmentData = JSON.parse(resp.data.uploadAttachmentData);
                    if(!uploadAttachmentData){
                    	uploadAttachmentData = [];
                    }
                    loadUploadedAttachments();
                    FileUpload.reset("#uploadAttachmentWrapper");
                    $("#btnDoModify").show();
                } else {
                    MU.msgTips("error", resp.message, 1200);
                }
            },
            error: function() {
                MU.msgTips("error", "获取文章信息失败，请稍后重试", 1200);
            }
        });
        
      //检查表单
        function checkAddModifyForm() {
            // title
            var title = $("#title").val();
            if (title == "") {
                MU.msgTips("warn", "请输入文章标题", 1200);
                $("#title").focus();
                return null;
            }
            //content
            var content = CKEDITOR.instances["contentEditorModify"].getData();
            $("#content").val(content);
            if (content == "") {
                MU.msgTips("warn", "请输入内容", 1200);
                $("#content").focus();
                return null;
            }

            if(!uploadAttachmentData || uploadAttachmentData.length == 0 ){
            	 MU.msgTips("warn", "请上传附件", 1200);
            	 return null;
            }
            return {
                title: title,
                content: content,
                uploadAttachmentData: JSON.stringify(uploadAttachmentData)
            };
        }       
        

      //提交修改
        $("#btnDoModify").on("click", function() {
            var data = checkAddModifyForm();
            if (data == null) {
                return;
            }
            data.articleId = modifyingId;

            //提交
            $.ajax({
                url: "doModify",
                type: "post",
                dataType: "json",
                data: data,
                success: function(resp) {
                    if (resp && resp.result == 0) {
                        MU.msgTips("success", "修改成功", 1200);
                        MU.hide($("#btnDoModify"))
                    } else {
                        MU.msgTips("error", resp.message, 1200);
                    }
                },
                error: function() {
                    MU.msgTips("error", "修改失败，请稍后重试", 1200);
                }
            });
        });
        
        







// 判断是否正在修改
function isModifying() {
    return modifyingId != null;
}

// 弹框大小处理
$(window).on("resize", function() {
    var wh = $(window).height();
    $("ul.form").css("height", wh - 190 + "px");
    console.log(wh - 160);
});
$(window).trigger("resize");


//==============================================================================
//上传附件处理
//==============================================================================

window.onAttachmentUploaded = function(err, resp) {
    if (err) {
        MU.msgTips("error", err);
        return;
    }
    if (resp.fileId && resp.fileName) {
        uploadAttachmentData.push({
            fileId: resp.fileId,
            fileName: resp.fileName
        });
        loadUploadedAttachments();
    }
};

function loadUploadedAttachments() {
    $("#uploadedAttachments").empty();
    $.each(uploadAttachmentData, function(i, item) {
        var wrapper = $("<p>").appendTo($("#uploadedAttachments"));
        $("<span>").html(item.fileName).appendTo(wrapper);
        $("<span>").html("  ").appendTo(wrapper);
        $("<a>").attr("href", "javascript:;")
            .html("删除")
            .css("color", "#107aee")
            .on("click", function() {
                for (var i = 0; i < uploadAttachmentData.length; i++) {
                    if (uploadAttachmentData[i].fileId == item.fileId) {
                        uploadAttachmentData.splice(i, 1);
                    }
                }
                loadUploadedAttachments();
            })
            .appendTo(wrapper);
    });

    if (uploadAttachmentData.length >= MAX_ATTACHMENTS) {
        $("#uploadAttachmentWrapper").hide();
    } else {
        $("#uploadAttachmentWrapper").show();
    }
}


