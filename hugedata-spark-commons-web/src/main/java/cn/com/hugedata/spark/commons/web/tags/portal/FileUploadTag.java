package cn.com.hugedata.spark.commons.web.tags.portal;

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
    
    /** [JSP] 生成form标签的action. */
    private String action;
    
    /** [JSP] 允许的文件后缀. */
    private String allowedExtensions;
    
    /** [JSP] 宽度. */
    private String width;
    
    /** [JSP] 提示文本. */
    private String prompt;
    
    /** [JSP] 按钮文本. */
    private String buttonText;
    
    /** Context Path. */
    private String contextPath;
    
    /** FileUploadTag内部ID. */
    private String internalId;
    
    @Override
    public int doEndTag() throws JspException {
        try {
            LOGGER.debug("Generating file upload tag: {}, {}", inputIdForFileId, inputIdForFileName);
            
            // 检查参数
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
                action = pageContext.getServletContext().getContextPath() + "/commons-web/portal-file-upload/upload";
            }
            
            if (allowedExtensions == null) {
                allowedExtensions = "";
            } else if (allowedExtensions.equals("image") || allowedExtensions.equals("images")) {
                allowedExtensions = "jpg,jpeg,png,gif,bmp";
            }
            
            if (width == null) {
                width = "300px";
            }
            
            if (prompt == null) {
                prompt = "";
            }
            
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
     * 重置所有属性.
     */
    private void reset() {
        id = null;
        action = null;
        inputIdForFileId = null;
        inputIdForFileName = null;
        inputNameForFileId = null;
        inputNameForFileName = null;
        allowedExtensions = null;
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

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getButtonText() {
        return buttonText;
    }

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
    }
}
