package cn.com.hugedata.spark.commons.web.tags.management;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import cn.com.hugedata.spark.commons.web.tags.utils.TagI18nUtils;

/**
 * 在对话框中显示一个操作按钮.
 * 
 * @author 高鹏
 */
public class DialogOperationButtonTag extends TagSupport {
    /** serialVersionUID. */
    private static final long serialVersionUID = 5354144796778838214L;
    
    /** (JSP) 按钮的ID. */
    private String id;
    
    /** (JSP) 点击触发的操作.*/
    private String onclick;
    
    /** (JSP) AngularJS点击触发操作. */
    private String ngClick;
    
    /** (JSP) 显示的文本. */
    private String label;

    /**
     * 充值标签的所有属性.
     */
    private void reset() {
        id = null;
        label = null;
        onclick = null;
        ngClick = null;
    }

    @Override
    public int doStartTag() throws JspException {
        label = TagI18nUtils.makeI18nText(pageContext, label);
        return super.doStartTag();
    }

    @Override
    public int doEndTag() throws JspException {
        if (!(getParent() instanceof DialogTag)) {
            throw new JspException("[dialogOperationButton] tag must be placed inside a [dialog] tag.");
        }
        DialogTag t = (DialogTag) getParent();
        t.addButton(DialogTag.BUTTON_TYPE_NORMAL, id, onclick, ngClick, label);
        reset();
        return EVAL_PAGE;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOnclick() {
        return onclick;
    }

    public void setOnclick(String onclick) {
        this.onclick = onclick;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getNgClick() {
        return ngClick;
    }

    public void setNgClick(String ngClick) {
        this.ngClick = ngClick;
    }
}
