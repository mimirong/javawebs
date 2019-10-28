package cn.com.hugedata.spark.commons.web.codeselector.dataproviders;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.web.codeselector.CodeInfo;
import cn.com.hugedata.spark.commons.web.codeselector.CodeSelectorDataProvider;

public class TestDataProvider implements CodeSelectorDataProvider {

    @Override
    public void init(ApplicationContext ac) {
    }

    @Override
    public List<CodeInfo> listAll() throws ServiceException {
        List<CodeInfo> list = new ArrayList<>();
        
        list.add(new CodeInfo("1", "AAA"));
        list.add(new CodeInfo("2", "BBB"));
        list.add(new CodeInfo("3", "CCC"));

        list.add(new CodeInfo("11", "AAA111", "1"));
        list.add(new CodeInfo("12", "AAA222", "1"));
        list.add(new CodeInfo("13", "BBB333", "2"));
        list.add(new CodeInfo("14", "BBB444", "2"));
        list.add(new CodeInfo("15", "BBB555", "3"));
        
        return list;
    }

}
