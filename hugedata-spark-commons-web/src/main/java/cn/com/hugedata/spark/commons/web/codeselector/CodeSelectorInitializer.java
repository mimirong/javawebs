package cn.com.hugedata.spark.commons.web.codeselector;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import cn.com.hugedata.spark.commons.web.codeselector.dataproviders.TestDataProvider;

@Service
public class CodeSelectorInitializer {

    @PostConstruct
    public void init() {
        // Test
        CodeSelector.register("test", new TestDataProvider());
    }
    
}
