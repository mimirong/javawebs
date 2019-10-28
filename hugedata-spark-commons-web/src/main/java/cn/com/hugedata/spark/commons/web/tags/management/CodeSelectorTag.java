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
 * 代码选择/显示标签.
 */
public class CodeSelectorTag extends BodyTagSupport {

    private static final long serialVersionUID = 1L;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CodeSelectorTag.class);
    
    /** Freemarker模板文件名. */
    private static final String TEMPLATE = "code_selector.ftl";
    
    /** JSP: 唯一ID. */
    private String id;

    /** JSP: 选项组. */
    private String codeGroup;
    
    /** JSP: 当前值(code). */
    private String value;

    /** JSP: 生成的保存code的hidden input标签的id. */
    private String inputIdForCode;

    /** JSP: 生成的保存code的hidden input标签的name. */
    private String inputIdForName;

    /** JSP: 生成的保存name的hidden input标签的id. */
    private String inputNameForCode;

    /** JSP: 生成的保存name的hidden input标签的name. */
    private String inputNameForName;

    /** JSP: 生成的保存完整多级id的hidden input标签的id. */
    private String inputIdForFullName;
    
    /** JSP: 生成的保存完整多级name的hidden input标签的id. */
    private String inputIdForFullCode;
    
    /** JSP: 外层DIV样式. */
    private String wrapperStyle;
    
    /** JSP: 每一级选项下拉列表的宽度. */
    private String itemWidth;
    
    /** JSP: 是否允许选择非叶子节点. */
    private boolean allowSelectingNonLeaf = false;
    
    /** JSP: 提示文本，如"请选择". */
    private String promptText;
    
    /** JSP: 选项值更新时处罚的JS方法，必须在window下. */
    private String onchange;
    
    /** JSP: 显示的最大层级数量. */
    private int maxLevel = 0;
    
    /** JSP: 是否为只读. */
    private boolean readonly = false;
    

    /** ContextPath. */
    private String contextPath;
    
    /**
     * 重置标签数据，用于标签重用.
     */
    private void reset() {
        codeGroup = null;
        value = null;
        inputIdForCode = null;
        inputIdForName = null;
        inputNameForCode = null;
        inputNameForName = null;
        inputIdForFullName = null;
        inputIdForFullCode = null;
        wrapperStyle = null;
        allowSelectingNonLeaf = false;
        id = null;
        promptText = null;
        onchange = null;
        maxLevel = 0;
        readonly = false;
    }
    
    @Override
    public int doEndTag() throws JspException {
        try {
            // 检查数据
            if (StringUtils.isEmpty(codeGroup)) {
                LOGGER.error("Attribute [codeGroup] can't be empty.");
                throw new JspException("Attribute [codeGroup] can't be empty.");
            }

            if (StringUtils.isEmpty(inputIdForCode)) {
                throw new JspException("Attribute [inputIdForCode] must not be empty");
            }
            if (StringUtils.isEmpty(inputNameForCode)) {
                inputNameForCode = inputIdForCode;
            }
            
            if (StringUtils.isEmpty(inputIdForName)) {
                throw new JspException("Attribute [inputIdForName] must not be empty");
            }
            if (StringUtils.isEmpty(inputNameForName)) {
                inputNameForName = inputIdForName;
            }
            
            if (StringUtils.isEmpty(wrapperStyle)) {
                wrapperStyle = "float:left;";
            }
            
            if (StringUtils.isEmpty(itemWidth)) {
                itemWidth = "230px";
            }
            
            if (StringUtils.isEmpty(promptText)) {
                promptText = "请选择";
            }
            
            // 获取contextPath
            contextPath = pageContext.getServletContext().getContextPath();
            
            // 生成唯一ID
            if (StringUtils.isEmpty(id)) {
                id = RandomStringUtils.randomAlphabetic(1).toUpperCase()
                        + RandomStringUtils.randomAlphanumeric(11).toUpperCase();
            }
            
            // 生成HTML
            String html = FreemarkerTagBuilder.create().generate(TEMPLATE, this);
            pageContext.getOut().write(html);
            reset();
            return EVAL_PAGE;
            
        } catch (IOException e) {
            throw new JspException(e);
        }
    }

    public String getCodeGroup() {
        return codeGroup;
    }

    public void setCodeGroup(String codeGroup) {
        this.codeGroup = codeGroup;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getInputIdForCode() {
        return inputIdForCode;
    }

    public void setInputIdForCode(String inputIdForCode) {
        this.inputIdForCode = inputIdForCode;
    }

    public String getInputIdForName() {
        return inputIdForName;
    }

    public void setInputIdForName(String inputIdForName) {
        this.inputIdForName = inputIdForName;
    }

    public String getInputNameForCode() {
        return inputNameForCode;
    }

    public void setInputNameForCode(String inputNameForCode) {
        this.inputNameForCode = inputNameForCode;
    }

    public String getInputNameForName() {
        return inputNameForName;
    }

    public void setInputNameForName(String inputNameForName) {
        this.inputNameForName = inputNameForName;
    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWrapperStyle() {
        return wrapperStyle;
    }

    public void setWrapperStyle(String wrapperStyle) {
        this.wrapperStyle = wrapperStyle;
    }

    public String getItemWidth() {
        return itemWidth;
    }

    public void setItemWidth(String itemWidth) {
        this.itemWidth = itemWidth;
    }

    public boolean isAllowSelectingNonLeaf() {
        return allowSelectingNonLeaf;
    }

    public void setAllowSelectingNonLeaf(boolean allowSelectingNonLeaf) {
        this.allowSelectingNonLeaf = allowSelectingNonLeaf;
    }

    public String getPromptText() {
        return promptText;
    }

    public void setPromptText(String promptText) {
        this.promptText = promptText;
    }

    public String getOnchange() {
        return onchange;
    }

    public void setOnchange(String onchange) {
        this.onchange = onchange;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    public String getInputIdForFullName() {
        return inputIdForFullName;
    }

    public void setInputIdForFullName(String inputIdForFullName) {
        this.inputIdForFullName = inputIdForFullName;
    }

    public String getInputIdForFullCode() {
        return inputIdForFullCode;
    }

    public void setInputIdForFullCode(String inputIdForFullCode) {
        this.inputIdForFullCode = inputIdForFullCode;
    }

    public boolean isReadonly() {
        return readonly;
    }

    public void setReadonly(boolean readonly) {
        this.readonly = readonly;
    }
}
