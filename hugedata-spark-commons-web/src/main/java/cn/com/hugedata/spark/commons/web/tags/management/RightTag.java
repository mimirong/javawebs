package cn.com.hugedata.spark.commons.web.tags.management;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang3.StringUtils;

import cn.com.hugedata.spark.commons.web.tags.utils.TagBuilder;

public class RightTag extends TagSupport {

    private static final long serialVersionUID = 1L;

    /** (JSP) 按钮的ID. */
    private String id;
    
    /** (JSP) 显示的文字. */
    private String width;

    /** (JSP) 垂直布局方式. */
    private String valign = "top";

    @Override
    public int doStartTag() throws JspException {
        try {
            TagBuilder tb = new TagBuilder(pageContext.getOut());

            tb.startTagBegin("td");
            tb.attrIfNotEmpty("id", id);
            tb.attrIfNotEmpty("valign", valign);
            if (StringUtils.isNotEmpty(width)) {
                tb.attr("style", "width:" + width + ";");
            }
            tb.startTagEnd();

            return EVAL_BODY_INCLUDE;
            
        } catch (IOException e) {
            throw new JspException(e);
        }
    }

    @Override
    public int doEndTag() throws JspException {
        try {
            TagBuilder tb = new TagBuilder(pageContext.getOut());
            tb.endTag("td");
            tb.endTag("tr");
            tb.endTag("table");
            
            reset();
            return EVAL_PAGE;
            
        } catch (IOException e) {
            throw new JspException(e);
        }
    }

    private void reset() {
        id = null;
        width = null;
        valign = "top";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getValign() {
        return valign;
    }

    public void setValign(String valign) {
        this.valign = valign;
    }
}
