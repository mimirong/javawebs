package cn.com.hugedata.spark.commons.web.init;

import java.io.File;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import cn.com.hugedata.spark.commons.web.tags.utils.FreemarkerTagBuilder;

/**
 * common-web初始化. 
 * @author 高鹏
 */
@Service
public class Initializer implements ServletContextAware {
    /** Logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(Initializer.class);

    /** 保存Spring传入的ServletContext. */
    private ServletContext servletContext;

    /** FTL模板文件路径. */
    private String tagTemplatePath = "/WEB-INF/templates/management-tags"; 
    
    /**
     * 初始化Portal.
     */
    @PostConstruct
    public void initialize() {
        LOGGER.info("Initializing hugedata-spark-commons-web");
        if (servletContext == null) {
            LOGGER.error("ServletContext is null.");
            return;
        }
        String path = servletContext.getRealPath(tagTemplatePath);
        if (!new File(path).exists()) {
            LOGGER.warn("Tag template path not found. FreemarkerTagBuilder not initialized.");
            return;
        }
        if (StringUtils.isNotEmpty(tagTemplatePath)) {
            LOGGER.info("Initializing freemarker tag builder.");
            FreemarkerTagBuilder.initialize(path);
        }
    }

    public void setServletContext(ServletContext sc) {
        this.servletContext = sc;
    }

    public String getTagTemplatePath() {
        return tagTemplatePath;
    }

    public void setTagTemplatePath(String tagTemplatePath) {
        this.tagTemplatePath = tagTemplatePath;
    }
}
