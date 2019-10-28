package cn.com.hugedata.spark.management.controllers;
import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.file.FileStoreService;
import cn.com.hugedata.spark.commons.service.file.FileUrlHelperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


/**
 * 用于接受并保存CKEditor上传的图片.
 */
@Controller
@RequestMapping("/cke")
public class CKEditorFileUploadController {

    @Autowired
    private FileStoreService fileStoreService;
    
    @Autowired
    private FileUrlHelperService fileUrlHelperService;
    
    @RequestMapping(value = "/imageUpload", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String upload(
            @RequestParam("upload") CommonsMultipartFile file,
            @RequestParam("CKEditorFuncNum") String funcNum) {
        try {
            // 检查文件为图片
            String filename = file.getOriginalFilename().toLowerCase();
            if (!filename.endsWith("jpg") && !filename.endsWith("jpeg") && !filename.endsWith("png")
                    && !filename.endsWith("gif") && !filename.endsWith("bmp")) {

                return String.format("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction(%s, '%s', '%s');</script>",
                        funcNum, "", "请上传图片文件");
            }
            
            String fileId = fileStoreService.save(file);
            String fileUrl = fileUrlHelperService.fixDownloadUrl(fileId);

            return String.format("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction(%s, '%s', '%s');</script>",
                    funcNum, fileUrl, "");
            
        } catch (ServiceException e) {
            return String.format("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction(%s, '%s', '%s');</script>",
                    funcNum, "", e.getMessage());
        }
    }
    
}
