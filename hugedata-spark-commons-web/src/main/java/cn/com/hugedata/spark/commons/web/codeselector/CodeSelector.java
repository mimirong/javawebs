package cn.com.hugedata.spark.commons.web.codeselector;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

public class CodeSelector {

    private static final Logger LOGGER = LoggerFactory.getLogger(CodeSelector.class);
    
    /**
     * 保存所有注册的CodeSelectorDataProvider
     */
    private static Map<String, CodeSelectorDataProvider> providers = new TreeMap<>();
    
    /**
     * 记录Provider是否已经初始化.
     */
    private static Set<String> providerInitialized = new HashSet<>();
    
    /**
     * 注册一个Provider.
     * @param codeGroup 代码组名称
     * @param provider  Provider实例
     */
    public static void register(String codeGroup, CodeSelectorDataProvider provider) {
        LOGGER.info("Register code selector data provider: {}, {}", codeGroup, provider.getClass().getSimpleName());
        providers.put(codeGroup, provider);
    }
    
    /**
     * 根据代码组名称获取一个Provider实例.
     * @param codeGroup 代码组名称
     * @return          Provider实例
     */
    public static CodeSelectorDataProvider getProvider(String codeGroup, ApplicationContext ac) {
        CodeSelectorDataProvider provider = providers.get(codeGroup);
        if (provider == null) {
            return null;
        }
        
        if (!providerInitialized.contains(codeGroup)) {
            provider.init(ac);
            providerInitialized.add(codeGroup);
        }
            
        return provider;
    }
}
