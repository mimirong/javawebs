package cn.com.hugedata.spark.commons.web.codeselector;

import java.util.List;

import org.springframework.context.ApplicationContext;

import cn.com.hugedata.spark.commons.exception.ServiceException;

public interface CodeSelectorDataProvider {
    
    void init(ApplicationContext ac);

    List<CodeInfo> listAll() throws ServiceException;
}
