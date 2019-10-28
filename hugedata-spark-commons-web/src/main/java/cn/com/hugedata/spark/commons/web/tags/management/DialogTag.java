package cn.com.hugedata.spark.commons.web.tags.management;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.hugedata.spark.commons.web.tags.utils.FreemarkerTagBuilder;
import cn.com.hugedata.spark.commons.web.tags.utils.TagI18nUtils;

/**
 * 对话框标签.
 * @author 高鹏
 */
public class DialogTag extends BodyTagSupport {
    /** serialVersionUID. */
    private static final long serialVersionUID = -4689481993312452051L;
    
    /** Logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(DialogTag.class);
    
    /** Freemarker模板文件名. */
    private static final String TEMPLATE = "dialog.ftl";
    
    /** 表示一个按钮为普通按扭. */
    public static final String BUTTON_TYPE_NORMAL = "normal";
    
    /** 表示一个按钮为关闭对话框按钮. */
    public static final String BUTTON_TYPE_CLOSE = "close";
    
    /** (JSP) 对话框ID. */
    private String id;

    /** (JSP) 对话框标题. */
    private String title;
    
    /** (JSP) 类型. 保留字段未使用. */
    private String type;
    
    /** (JSP) 宽度. */
    private String width;
    
    /** (JSP) 高度. */
    private String height;
    
    /** 标签体生成的HTML. */
    private String contentHtml;
    
    /** 对话框按钮. */
    private List<ButtonInfo> buttons;
    
    /**
     * 重置所有属性.
     */
    private void reset() {
        id = null;
        title = null;
        type = null;
        contentHtml = null;
        buttons = null;
        width = null;
        height = null;
    }

    @Override
    public int doStartTag() throws JspException {
        title = TagI18nUtils.makeI18nText(pageContext, title);
        return EVAL_BODY_BUFFERED;
    }
    
    @Override
    public int doEndTag() throws JspException {
        try {
            LOGGER.debug("Generating dialog: {}, {}, {}, {}, {}", id, title, title, width, height);
            contentHtml = bodyContent.getString();
            String html = FreemarkerTagBuilder.create().generate(TEMPLATE, this);
            pageContext.getOut().write(html);
            reset();
            return EVAL_PAGE;
        } catch (IOException e) {
            throw new JspException(e);
        }
    }
    
    /**
     * 增加一个按钮.
     * @param inType    按钮类型
     * @param inId      ID
     * @param inOnclick onclick属性值
     * @param inNgclick ng-click属性值
     * @param inLabel   按钮标题
     */
    public void addButton(String inType, String inId, String inOnclick, String inNgclick, String inLabel) {
        if (buttons == null) {
            buttons = new ArrayList<ButtonInfo>();
        }
        ButtonInfo bi = new ButtonInfo();
        bi.type = inType;
        bi.id = inId;
        bi.onclick = inOnclick;
        bi.ngClick = inNgclick;
        bi.label = inLabel;
        buttons.add(bi);
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContentHtml() {
        return contentHtml;
    }

    public void setContentHtml(String contentHtml) {
        this.contentHtml = contentHtml;
    }

    public List<ButtonInfo> getButtons() {
        return buttons;
    }

    public void setButtons(List<ButtonInfo> buttons) {
        this.buttons = buttons;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
    
    //------------------------------------------------------------------------------------------------------
    
    /**
     * 保存对话框的操作.
     */
    public static class ButtonInfo {
    	/**
    	 * 操作类型. 
    	 */
        private String type;
        
        /**
         * HTML ID.
         */
        private String id;
        
        /**
         * onclick.
         */
        private String onclick;
        
        /**
         * 文本.
         */
        private String label;
        
        /**
         * ng-click.
         */
        private String ngClick;
        
        public String getType() {
            return type;
        }
        
        public void setType(String type) {
            this.type = type;
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
}
