package cn.com.hugedata.spark.commons.web.i18n;

import java.util.Locale;
import java.util.Properties;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * 自定义的MessageSource.
 * @author gaopeng
 */
public class HugedataResourceBundleMessageSource extends ReloadableResourceBundleMessageSource {

    /**
     * 根据指定的语言获取所有国际化文本.
     * @param locale 语言
     * @return       所有国际化文本
     */
    public Properties getAllProperties(Locale locale) {
        clearCacheIncludingAncestors();
        PropertiesHolder propertiesHolder = getMergedProperties(locale);
        Properties properties = propertiesHolder.getProperties();
        return properties;
    }
    
}
