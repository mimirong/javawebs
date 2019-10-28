package cn.com.hugedata.spark.commons.web.tags.utils;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;

/**
 * 标签生成器工具类.
 * @author 高鹏
 */
public final class FreemarkerTagBuilder {
    
    /** Logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(FreemarkerTagBuilder.class);
    
    /** 默认使用的Freemarker版本. */
    private static final String FREEMARKER_CONFIGURATION_VERSION = "2.3.21";
    
    //-----------------------------------------------------------------------------------
    // 单例
    
    /**
     * Freemarker标签生成器实例.
     */
    private static FreemarkerTagBuilder instance;
    
    /**
     * 初始化Freemarker标签生成器.
     * @param templatePath 模板文件路径
     */
    public static void initialize(String templatePath) {
        LOGGER.info("Initializing FreemarkerTagBuilder: " + templatePath);
        instance = new FreemarkerTagBuilder(templatePath);
    }
    
    /**
     * 创建Freemarker标签生成器.
     * @return Freemarker标签生成器
     */
    public static FreemarkerTagBuilder create() {
        if (instance == null) {
            throw new RuntimeException("Freemarker tag builder not initialized.");
        }
        return instance;
    }
    
    
    //-----------------------------------------------------------------------------------
    // 实例

    /** 初始化的Freemarker Configuration. */
    private Configuration configuration;
    
    /**
     * 创建Freemarker标签生成器.
     * @param templatePath 模板文件路径
     */
    private FreemarkerTagBuilder(String templatePath) {
        try {
            configuration = new Configuration(new Version(FREEMARKER_CONFIGURATION_VERSION));
            configuration.setDirectoryForTemplateLoading(new File(templatePath));
            configuration.setDefaultEncoding("UTF-8");            
        } catch (IOException e) {
            LOGGER.error("Failed to initialize freemarker tag builder.", e);
            throw new RuntimeException("Failed to initialize freemarker tag builder.", e);
        }
    }

    /**
     * 根据FTL模板，生成HTML.
     * 模板文件路径为{@link #TEMPLATE_PATH}.
     * 
     * @param template      模板文件名
     * @param bean          模板数据
     * @return              生成的HTML
     * @throws JspException 生成HTML时出现异常
     */
    public String generate(String template, Object bean) throws JspException {
        try {
            StringWriter w = new StringWriter();
            Template t = configuration.getTemplate(template);
            t.process(bean, w);
            return w.toString();
        } catch (IOException e) {
            throw new JspException(e);
        } catch (TemplateException e) {
            throw new JspException(e);
        }
    }
}
