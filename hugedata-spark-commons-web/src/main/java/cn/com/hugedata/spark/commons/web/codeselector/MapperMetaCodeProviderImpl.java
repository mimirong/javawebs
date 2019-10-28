package cn.com.hugedata.spark.commons.web.codeselector;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;

public abstract class MapperMetaCodeProviderImpl<
        ENTITY extends BaseEntity<String>,
        MAPPER extends ManagementMapper<ENTITY, String>> implements CodeSelectorDataProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapperMetaCodeProviderImpl.class);
    
    @Autowired
    private MAPPER mapper;

    @Override
    public void init(ApplicationContext ac) {
        ac.getAutowireCapableBeanFactory().autowireBean(this);
    }

    @Override
    public List<CodeInfo> listAll() throws ServiceException {
        List<ENTITY> list = mapper.selectByMap(
                MyBatisUtils.buildParameterMap(
                        OrderItem.ORDER_KEY, Arrays.asList(new OrderItem("sortIndex", OrderItem.ASC))
                ), 
                RowBounds.DEFAULT
        );
        return convertToCodeInfo(list);
    }
    
    private CodeInfo convertToCodeInfo(ENTITY e) throws ServiceException {
        try {
            CodeInfo ci = new CodeInfo();
      
            ci.setCode((String) PropertyUtils.getProperty(e, "code"));
            ci.setParentCode((String) PropertyUtils.getProperty(e, "parentCode"));
            ci.setName((String) PropertyUtils.getProperty(e, "name"));
            
            return ci;
            
        } catch (IllegalAccessException ex) {
            LOGGER.error("Failed to convert to CodeInfo.", e);
            throw new ServiceException(this.getClass().getSimpleName() + "-FailedToConvertCodeInfo", "查询数据失败");
            
        } catch (InvocationTargetException ex) {
            LOGGER.error("Failed to convert to CodeInfo.", e);
            throw new ServiceException(this.getClass().getSimpleName() + "-FailedToConvertCodeInfo", "查询数据失败");
            
        } catch (NoSuchMethodException ex) {
            LOGGER.error("Failed to convert to CodeInfo.", e);
            throw new ServiceException(this.getClass().getSimpleName() + "-FailedToConvertCodeInfo", "查询数据失败");
        }
    }
    
    private List<CodeInfo> convertToCodeInfo(List<ENTITY> list) throws ServiceException {
        List<CodeInfo> retList = new ArrayList<>();
        for (ENTITY e : list) {
            retList.add(convertToCodeInfo(e));
        }
        return retList;
    }
}
