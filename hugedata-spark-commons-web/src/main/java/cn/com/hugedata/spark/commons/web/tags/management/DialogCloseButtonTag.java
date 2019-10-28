package cn.com.hugedata.spark.commons.web.tags.management;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import cn.com.hugedata.spark.commons.web.tags.utils.TagI18nUtils;

/**
 * 在对话框中显示关闭按钮.
 * 
 * @author 高鹏
 */
public class DialogCloseButtonTag extends TagSupport {
    /** serialVersionUID. */
    private static final long serialVersionUID = -2535431813866199258L;
    
    /** (JSP) 按钮的ID. */
    private String id;
    
    /** (JSP) 显示的文字. */
    private String label;

    /**
     * 重置标签字段.
     */
    private void reset() {
        id = null;
        label = null;
    }

    @Override
    public int doStartTag() throws JspException {
        label = TagI18nUtils.makeI18nText(pageContext, label);
        return super.doStartTag();
    }

    @Override
    public int doEndTag() throws JspException {
        if (!(getParent() instanceof DialogTag)) {
            throw new JspException("[dialogCloseButton] tag must be placed inside a [dialog] tag.");
        }
        DialogTag t = (DialogTag) getParent();
        t.addButton(DialogTag.BUTTON_TYPE_CLOSE, id, null, null, label);
        reset();
        return EVAL_PAGE;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
