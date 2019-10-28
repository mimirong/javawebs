package cn.com.hugedata.spark.commons.web.tags.management;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.hugedata.spark.commons.web.tags.utils.FreemarkerTagBuilder;

/**
 * iframe文件上传.
 */
public class FileUploadTag extends BodyTagSupport {

    private static final long serialVersionUID = 8429883361076636296L;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadTag.class);
    
    /** Freemarker模板文件名. */
    private static final String TEMPLATE = "file_upload.ftl";
    
    /** [JSP] 生成的hidden标签的id. */
    private String inputIdForFileId;

    /** [JSP] 生成的hidden标签的name */
    private String inputNameForFileId;
    
    /** [JSP] 生成的hidden标签的id. */
    private String inputIdForFileName;

    /** [JSP] 生成的hidden标签的name */
    private String inputNameForFileName;
    
    /** [JSP] 允许的文件后缀. */
    private String allowedExtensions;
    
    /** [JSP] 提示文本. */
    private String prompt;
    
    /** [JSP] 最大文件大小，可以为5M, 5K等包含单位的格式. */
    private String maxFileSize;
    
    /** [JSP] 上传结果回调. */
    private String callback;
    
    /** [JSP] 成功后自动重置. */
    private boolean autoReset = false;
    
    /** [JSP] iframe宽度. */
    private String width = "";
    
    /** 生成form标签的action. */
    private String action;
    
    /** Context Path. */
    private String contextPath;
    
    /** FileUploadTag内部ID. */
    private String internalId;

    @Override
    public int doEndTag() throws JspException {
        try {
            LOGGER.debug("Generating file upload tag: {}, {}", inputIdForFileId, inputIdForFileName);
            
            // 检查参数
            if (StringUtils.isEmpty(id)) {
                id = "file";
            }
            if (StringUtils.isEmpty(inputIdForFileId)) {
                throw new JspException("Attribute [inputIdForFileId] must not be empty");
            }
            if (StringUtils.isEmpty(inputNameForFileId)) {
                inputNameForFileId = inputIdForFileId;
            }
            
            if (StringUtils.isEmpty(inputIdForFileName)) {
                throw new JspException("Attribute [inputIdForFileName] must not be empty");
            }
            if (StringUtils.isEmpty(inputNameForFileName)) {
                inputNameForFileName = inputIdForFileName;
            }
            
            if (StringUtils.isEmpty(action)) {
                action = pageContext.getServletContext().getContextPath() + "/commons-web/file-upload/upload";
            }
            
            if (allowedExtensions == null) {
                allowedExtensions = "";
            } else if (allowedExtensions.equals("image") || allowedExtensions.equals("images")) {
                allowedExtensions = "jpg,jpeg,png,gif,bmp";
            }
            
            if (prompt == null) {
                prompt = "";
            }
            
            if (callback == null) {
                callback = "";
            }
            
            if (StringUtils.isEmpty(width)) {
                width = "600px";
            }
            
            parseMaxFileSize();
            
            // 生成内部ID
            internalId = RandomStringUtils.randomAlphabetic(16);
            
            // 获取contextPath
            contextPath = pageContext.getServletContext().getContextPath();
            
            // 生成HTML
            String html = FreemarkerTagBuilder.create().generate(TEMPLATE, this);
            pageContext.getOut().write(html);
            reset();
            return EVAL_PAGE;
        } catch (IOException e) {
            throw new JspException("Failed to generate file upload tag.", e);
        }
    }
    
    /**
     * 转换最大文件大小字段.
     * @throws JspException 最大文件大小格式错误
     */
    private void parseMaxFileSize() throws JspException {
        try {
            if (StringUtils.isEmpty(maxFileSize)) {
                maxFileSize = "0";
            }
            maxFileSize = maxFileSize.toUpperCase();
            
            if (maxFileSize.endsWith("K")) {
                int val = Integer.parseInt(maxFileSize.substring(0, maxFileSize.length() - 1)) * 1024;
                maxFileSize = String.valueOf(val);
            } else if (maxFileSize.endsWith("M")) {
                int val = Integer.parseInt(maxFileSize.substring(0, maxFileSize.length() - 1)) * 1024 * 1024;
                maxFileSize = String.valueOf(val);
            } else if (maxFileSize.endsWith("G")) {
                int val = Integer.parseInt(maxFileSize.substring(0, maxFileSize.length() - 1)) * 1024 * 1024 * 1024;
                maxFileSize = String.valueOf(val);
            } else {
                maxFileSize = String.valueOf(Integer.parseInt(maxFileSize));
            }
        } catch (NumberFormatException e) {
            throw new JspException("Invalid maxFileSize attribute.");
        }
    }

    /**
     * 重置所有属性.
     */
    private void reset() {
        id = null;
        inputIdForFileId = null;
        inputIdForFileName = null;
        inputNameForFileId = null;
        inputNameForFileName = null;
        action = null;
        allowedExtensions = null;
        prompt = null;
        maxFileSize = null;
        callback = null;
        autoReset = false;
        width = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
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

    public String getInputIdForFileId() {
        return inputIdForFileId;
    }

    public void setInputIdForFileId(String inputIdForFileId) {
        this.inputIdForFileId = inputIdForFileId;
    }

    public String getInputNameForFileId() {
        return inputNameForFileId;
    }

    public void setInputNameForFileId(String inputNameForFileId) {
        this.inputNameForFileId = inputNameForFileId;
    }

    public String getInputIdForFileName() {
        return inputIdForFileName;
    }

    public void setInputIdForFileName(String inputIdForFileName) {
        this.inputIdForFileName = inputIdForFileName;
    }

    public String getInputNameForFileName() {
        return inputNameForFileName;
    }

    public void setInputNameForFileName(String inputNameForFileName) {
        this.inputNameForFileName = inputNameForFileName;
    }

    public String getAllowedExtensions() {
        return allowedExtensions;
    }

    public void setAllowedExtensions(String allowedExtensions) {
        this.allowedExtensions = allowedExtensions;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getMaxFileSize() {
        return maxFileSize;
    }

    public void setMaxFileSize(String maxFileSize) {
        this.maxFileSize = maxFileSize;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public boolean isAutoReset() {
        return autoReset;
    }

    public void setAutoReset(boolean autoReset) {
        this.autoReset = autoReset;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }
}
