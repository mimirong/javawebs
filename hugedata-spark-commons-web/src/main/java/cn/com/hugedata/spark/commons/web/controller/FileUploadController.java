package cn.com.hugedata.spark.commons.web.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.file.FileStoreService;
import cn.com.hugedata.spark.commons.web.tags.utils.FreemarkerTagBuilder;

/**
 * 用于FileUploadTag的文件上传处理.
 * @author gaopeng
 */
@Controller(value = "commonsWebFileUploadController")
@RequestMapping("/commons-web/file-upload")
public class FileUploadController implements ServletContextAware {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);
    
    /** Freemarker模板文件名. */
    private static final String TEMPLATE_UPLOAD = "file_upload_frame.ftl";
    
    /** Freemarker模板文件名. */
    private static final String TEMPLATE_UPLOAD_RESULT = "file_upload_result_frame.ftl";

    @Autowired
    private FileStoreService fileStoreService;
    
    private ServletContext servletContext;

    /**
     * 显示文件上传iframe页面.
     * @param model 上传页面Model
     * @return      上传页面HTML
     */
    @RequestMapping("toFileUpload-simple")
    @ResponseBody
    public String toFileUpload(
            @ModelAttribute FileUploadPageModel model,
            HttpServletRequest request) {
        try {
            request.setAttribute("contextPath", servletContext.getContextPath());
            model.setContextPath(servletContext.getContextPath());
            String html = FreemarkerTagBuilder.create().generate(TEMPLATE_UPLOAD, model);
            return html;
        } catch (JspException e) {
            return "无法创建上传组件";
        }
    }

    /**
     * 显示文件上传失败的iframe页面.
     * @param model 上传页面Model
     * @return      上传页面HTML
     */
    @RequestMapping("toFileUploadResult-simple")
    @ResponseBody
    public String toFileUploadResult(
            @ModelAttribute FileUploadFailurePageModel model,
            HttpServletRequest request) {
        try {
            request.setAttribute("contextPath", servletContext.getContextPath());
            model.setContextPath(servletContext.getContextPath());
            String html = FreemarkerTagBuilder.create().generate(TEMPLATE_UPLOAD_RESULT, model);
            return html;
        } catch (JspException e) {
            LOGGER.error("Failed to generate file upload failure page.", e);;
            return "无法创建上传组件";
        }
    }

    /**
     * 上传处理.
     * @param internalId
     * @param file
     * @return
     */
    @RequestMapping("/upload")
    public ModelAndView upload(
            @RequestParam("internalId") String internalId,
            @RequestParam(value = "maxFileSize", defaultValue = "0") Integer maxFileSize,
            @RequestParam("file") CommonsMultipartFile file) {
        try {
            if (!maxFileSize.equals(0) && file.getSize() > maxFileSize) {
                ModelAndView mv = new ModelAndView("redirect:/commons-web/file-upload/toFileUploadResult-simple");
                mv.addObject("err", "文件大小超过限制");
                mv.addObject("internalId", internalId);
                mv.addObject("response", "{}");
                return mv;
            }
            
            String fileId = fileStoreService.save(file);
            
            JSONObject resp = new JSONObject();
            resp.put("fileId", fileId);
            resp.put("fileName", file.getOriginalFilename());

            ModelAndView mv = new ModelAndView("redirect:/commons-web/file-upload/toFileUploadResult-simple");
            mv.addObject("err", "");
            mv.addObject("internalId", internalId);
            mv.addObject("response", JSON.toJSONString(resp));
            return mv;
            
        } catch (ServiceException e) {
            ModelAndView mv = new ModelAndView("redirect:/commons-web/file-upload/toFileUploadResult-simple");
            mv.addObject("err", e.getMessage());
            mv.addObject("internalId", internalId);
            mv.addObject("response", "{}");
            return mv;
        }
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    //==================================================================================================================
    
    /**
     * 用于生成文件上传iframe页面的model.
     */
    public static class FileUploadPageModel {
        /** 生成的input标签的id. */
        private String id;

        /** 生成的input标签的name */
        private String name;

        /** 生成的input标签的placeholder. */
        private String placeholder;
        
        /** 生成form标签的action. */
        private String action;

        /** 标签ID. */
        private String internalId;
        
        /** contextPath. */
        private String contextPath;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public String getInternalId() {
            return internalId;
        }

        public void setInternalId(String internalId) {
            this.internalId = internalId;
        }

        public String getContextPath() {
            return contextPath;
        }

        public void setContextPath(String contextPath) {
            this.contextPath = contextPath;
        }
    }

    //==================================================================================================================
    
    /**
     * 用于生成上传文件失败页面的Model.
     */
    public static class FileUploadFailurePageModel {
        
        /** 错误消息. */
        private String err;
        
        /** 返回结果JSON. */
        private String response;

        /** 标签ID. */
        private String internalId;
        
        /** contextPath. */
        private String contextPath;

        public String getResponse() {
            return response;
        }

        public void setResponse(String response) {
            this.response = response;
        }

        public String getErr() {
            return err;
        }

        public void setErr(String err) {
            this.err = err;
        }

        public String getInternalId() {
            return internalId;
        }

        public void setInternalId(String internalId) {
            this.internalId = internalId;
        }

        public String getContextPath() {
            return contextPath;
        }

        public void setContextPath(String contextPath) {
            this.contextPath = contextPath;
        }
    }
}
