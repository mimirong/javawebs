package cn.com.hugedata.spark.commons.web.tags.utils;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;

import org.apache.commons.lang3.StringUtils;

/**
 * 用于生成标签的工具类.
 * 
 * @author 高鹏
 */
public class TagBuilder {
	
	/**
	 * 保存创建时传入的JspWriter.
	 */
    private JspWriter w;

    /**
     * 创建标签生成工具.
     * @param w JspWriter
     */
    public TagBuilder(JspWriter w) {
        this.w = w;
    }

    /**
     * 输出开始标签.
     * @param name         标签名
     * @throws IOException 输出到JspWriter时发生异常
     */
    public void startTag(String name) throws IOException {
        w.print("<");
        w.print(name);
        w.print(">");
        w.newLine();
    }

    /**
     * 输出开始标签开始.
     * @param name         标签名
     * @throws IOException 输出到JspWriter时发生异常
     */
    public void startTagBegin(String name) throws IOException {
        w.print("<");
        w.print(name);
    }

    /**
     * 输出开始标签结束(&gt;).
     * @throws IOException 输出到JspWriter时发生异常
     */
    public void startTagEnd() throws IOException {
        w.print(">");
        w.newLine();
    }

    /**
     * 输出开始标签结束和结束标签(/&gt;).
     * @throws IOException 输出到JspWriter时发生异常
     */
    public void endTagWithoutBody() throws IOException {
        w.print(" />");
        w.newLine();
    }

    /**
     * 输出标签属性.
     * @param name         标签属性名
     * @param value        标签属性值
     * @throws IOException 输出到JspWriter时发生异常
     */
    public void attr(String name, Object value) throws IOException {
        w.print(" ");
        w.print(name);
        w.print("=\"");
        w.print(value);
        w.print("\"");
    }

    /**
     * 如果属性值不为空输出标签属性.
     * @param name         标签属性名
     * @param value        标签属性值
     * @throws IOException 输出到JspWriter时发生异常
     */
    public void attrIfNotEmpty(String name, String value) throws IOException {
        if (!StringUtils.isEmpty(value)) {
            attr(name, value);
        }
    }

    /**
     * 输出结束标签.
     * @param name         标签名
     * @throws IOException 输出到JspWriter时发生异常
     */
    public void endTag(String name) throws IOException {
        w.print("</");
        w.print(name);
        w.print(">");
        w.newLine();
    }

    /**
     * 输出结束标签并附加注释.
     * @param name         结束标签名
     * @param comment      注释内容
     * @throws IOException 输出到JspWriter时发生异常
     */
    public void endTag(String name, String comment) throws IOException {
        w.print("</");
        w.print(name);
        w.print(">");
        w.print("<!-- ");
        w.print(comment);
        w.print(" -->");
        w.newLine();
    }
    
    public void startStyleAttr() throws IOException {
        w.print(" style=\"");
    }
    
    public void endStyleAttr() throws IOException {
        w.print("\"");
    }
    
    public void style(String key, String value) throws IOException {
        w.print(key + ":" + value + "; ");
    }
    
    public void styleIfNotEmpty(String key, String value) throws IOException {
        if (StringUtils.isNotEmpty(value)) {
            style(key, value);
        }
    }

    /**
     * 输出文本.
     * @param text         输出的文本内容
     * @throws IOException 输出到JspWriter时发生异常
     */
    public void print(String text) throws IOException {
        w.print(text);
    }
}
