package cn.com.hugedata.spark.commons.web.tags.management;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import cn.com.hugedata.spark.commons.web.tags.utils.TagBuilder;

public class LeftTag extends TagSupport {

    private static final long serialVersionUID = 1L;

    /** (JSP) 按钮的ID. */
    private String id;
    
    /** (JSP) 显示的文字. */
    private String width;

    /** (JSP) 垂直布局方式. */
    private String valign = "top";
    
    /** (JSP) 两列之间间隔. */
    private String columnSpacing;

    @Override
    public int doStartTag() throws JspException {
        try {
            TagBuilder tb = new TagBuilder(pageContext.getOut());
            tb.startTagBegin("table");
            tb.attr("cellspacing", "0");
            tb.attr("cellpadding", "0");
            tb.attr("border", "0");
            tb.attr("style", "width:100%");
            tb.startTagEnd();

            tb.startTag("tr");

            tb.startTagBegin("td");
            tb.attrIfNotEmpty("id", id);
            tb.attrIfNotEmpty("valign", valign);
            
            tb.startStyleAttr();
            tb.styleIfNotEmpty("width", width);
            tb.styleIfNotEmpty("padding-right", columnSpacing);
            tb.endStyleAttr();
            
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
        columnSpacing = null;
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

    public String getColumnSpacing() {
        return columnSpacing;
    }

    public void setColumnSpacing(String columnSpacing) {
        this.columnSpacing = columnSpacing;
    }
}
