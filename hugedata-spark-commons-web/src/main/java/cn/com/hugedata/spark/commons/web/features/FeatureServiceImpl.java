package cn.com.hugedata.spark.commons.web.features;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.PageEntity;
import cn.com.hugedata.spark.commons.service.file.FileStoreService;
import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;

/**
 * 包含基础功能的后台服务类.
 *
 * @param <ENTITY>  实体类类型
 * @param <ID>      实体类ID字段类型
 * @param <MODEL>   MVC Model类型
 * @param <MAPPER>  Mapper类型
 */
public abstract class FeatureServiceImpl<
        ENTITY extends BaseEntity<ID>, 
        ID     extends Serializable, 
        MODEL  extends FeatureModel,
        MAPPER extends ManagementMapper<? super ENTITY, ID>>
    extends FeatureService<ENTITY, ID, MODEL> {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(FeatureServiceImpl.class);
    
    @Autowired
    private MAPPER mapper;

    @Autowired
    private FileStoreService fileStoreService;
    
    /**
     * 功能名称.
     */
    private String featureName = null;
    
    @PostConstruct
    public void init() {
        BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);
    }
    
    /**
     * 通过Controller获取Feature项目的名称.
     * @return Feature名称
     */
    protected String getFeatureName() {
        if (featureName == null) {
            String suffix = "Service";
            String className = this.getClass().getSimpleName();
            if (!className.endsWith(suffix)) {
                throw new RuntimeException("Invalid service name: " + className);
            }
            String name = className.substring(0, className.length() - suffix.length());
            LOGGER.info("Feature service found: {}", name);
            featureName = name;
        }
        return featureName;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public PageEntity<ENTITY> list(Map<String, Object> query, int pageStart, int pageSize, List<OrderItem> orders) {
        // 查询总记录数
        int count = executeCountByMap(query);
        
        // 修正pageStart
        if (pageStart >= count) {
            pageStart = count - pageSize;
        }
        if (pageStart < 0) {
            pageStart = 0;
        }
        
        // 分页
        if (query == null) {
            query = new TreeMap<String, Object>();
        }
        query.put("order", orders);
        
        // 查询
        List<? super ENTITY> data = executeSelectByMap(query, pageStart, pageSize);
        if (data.isEmpty()) {
            return new PageEntity<ENTITY>((Collection<ENTITY>) data, pageStart, pageSize, count);
        }
        
        // 转换类型
        List<ENTITY> convertedData = new ArrayList<ENTITY>();
        for (int i = 0; i < data.size(); i++) {
            convertedData.add(convertEntityTypes(data.get(i)));
        }
        return new PageEntity<ENTITY>(convertedData, pageStart, pageSize, count);
    }

    /**
     * 执行Mapper的countByMap，此方法可以被覆盖.
     * @param query 查询条件
     * @return      查询结果
     */
    protected Integer executeCountByMap(Map<String, Object> query) {
        return mapper.countByMap(query);
    }

    /**
     * 执行Mapper的selectByMap，此方法可以被覆盖.
     * @param query     查询条件
     * @param pageStart 开始记录
     * @param pageSize  查询记录数量
     * @return
     */
    protected List<? super ENTITY> executeSelectByMap(Map<String, Object> query, int pageStart, int pageSize) {
        return mapper.selectByMap(query, new RowBounds(pageStart, pageSize));
    }

    @SuppressWarnings("unchecked")
    protected <T> ENTITY convertEntityTypes(T obj) {
        try {
            if (obj == null) {
                return null;
            }
            
            // 创建返回的类型
            ENTITY e = (ENTITY) getEntityType().newInstance();
            if (!obj.getClass().isInstance(e)) {
                throw new RuntimeException(String.format("Can't convert types from %s to %s", 
                        obj.getClass(), e.getClass()));
            }
            
            // 复制字段
            PropertyDescriptor[] properties = PropertyUtils.getPropertyDescriptors(obj);
            for (PropertyDescriptor property : properties) {
                String fieldName = property.getName();
                Object value = property.getReadMethod().invoke(obj);
                PropertyDescriptor targetProperty = PropertyUtils.getPropertyDescriptor(e, fieldName);
                if (targetProperty != null && value != null) {
                    BeanUtils.setProperty(e, fieldName, value);
                }
            }
            
            return e;
            
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public MODEL queryForModify(ID id) throws ServiceException {
        try {
            Object obj = mapper.selectById(id);
            if (obj == null) {
                return null;
            }

            // 创建返回的类型
            MODEL model = (MODEL) getModelType().newInstance();
            
            // 复制字段
            PropertyDescriptor[] properties = PropertyUtils.getPropertyDescriptors(obj);
            for (PropertyDescriptor property : properties) {
                String fieldName = property.getName();
                Object value = property.getReadMethod().invoke(obj);
                PropertyDescriptor targetProperty = PropertyUtils.getPropertyDescriptor(model, fieldName);
                if (targetProperty != null && value != null) {
                    BeanUtils.setProperty(model, fieldName, value);
                }
            }
            
            return model;
            
        } catch (SecurityException e) {
            throw new RuntimeException(e);
            
        } catch (IllegalAccessException e) {
            LOGGER.error("Failed to parse model", e);
            throw new ServiceException("FeatureServiceImpl-FailedToParseModel", "查询失败", e);
            
        } catch (InvocationTargetException e) {
            LOGGER.error("Failed to parse model", e);
            throw new ServiceException("FeatureServiceImpl-FailedToParseModel", "查询失败", e);
            
        } catch (NoSuchMethodException e) {
            LOGGER.error("Failed to parse model", e);
            throw new ServiceException("FeatureServiceImpl-FailedToParseModel", "查询失败", e);
            
        } catch (InstantiationException e) {
            LOGGER.error("Failed to parse model", e);
            throw new ServiceException("FeatureServiceImpl-FailedToParseModel", "查询失败", e);
        }
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public ENTITY add(MODEL model) throws ServiceException {
        try {
            // 创建等待保存到数据库的实体类
            ENTITY entity = (ENTITY) getEntityType().newInstance();
            
            // 将model转换为entity
            PropertyDescriptor[] properties = PropertyUtils.getPropertyDescriptors(model);
            for (PropertyDescriptor property : properties) {
                if (property.getName().equals("class")) {
                    continue;
                }
                
                // 获取字段信息
                String fieldName = property.getName();
                Object value = property.getReadMethod().invoke(model);
                LOGGER.debug("Parsing model field: {} -> {}", fieldName, value);
                
                // 复制到entity
                PropertyDescriptor targetProperty = PropertyUtils.getPropertyDescriptor(entity, fieldName);
                if (targetProperty != null) {
                    if (value instanceof CommonsMultipartFile) {
                        String fileId = fileStoreService.save((CommonsMultipartFile) value);
                        BeanUtils.setProperty(entity, fieldName, fileId);
                    } else {
                        BeanUtils.setProperty(entity, fieldName, value);
                    }
                }
            }
            
            // 转换完成，插入数据库
            LOGGER.debug("Converted entity to insert: " + entity);
            mapper.insertSelective(entity);

            // id设置回model
            PropertyUtils.setProperty(model, entity.getIdPropertyName(), entity.getIdValue());
            
            return entity;
            
        } catch (InstantiationException e) {
            LOGGER.error("Failed to parse model", e);
            throw new ServiceException("Failed to parse model", "manager.features.failed_parse_entity", e);

        } catch (IllegalAccessException e) {
            LOGGER.error("Failed to parse model", e);
            throw new ServiceException("Failed to parse model", "manager.features.failed_parse_entity", e);
            
        } catch (InvocationTargetException e) {
            LOGGER.error("Failed to parse model", e);
            throw new ServiceException("Failed to parse model", "manager.features.failed_parse_entity", e);
            
        } catch (NoSuchMethodException e) {
            LOGGER.error("Failed to parse model", e);
            throw new ServiceException("Failed to parse model", "manager.features.failed_parse_entity", e);
        }
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public void modify(MODEL model) throws ServiceException {
        try {
            // 创建等待保存到数据库的实体类
            ENTITY entity = (ENTITY) getEntityType().newInstance();
            
            // 将model转换为entity
            PropertyDescriptor[] properties = PropertyUtils.getPropertyDescriptors(model);
            for (PropertyDescriptor property : properties) {
                if (property.getName().equals("class")) {
                    continue;
                }
                
                // 获取字段信息
                String fieldName = property.getName();
                Object value = property.getReadMethod().invoke(model);
                LOGGER.debug("Parsing model field: {} -> {}", fieldName, value);
                
                // 复制到entity
                PropertyDescriptor targetProperty = PropertyUtils.getPropertyDescriptor(entity, fieldName);
                if (targetProperty != null && value != null) {
                    if (value instanceof CommonsMultipartFile) {
                        String fileId = fileStoreService.save((CommonsMultipartFile) value);
                        BeanUtils.setProperty(entity, fieldName, fileId);
                    } else {
                        BeanUtils.setProperty(entity, fieldName, value);
                    }
                }
            }
            
            // 转换完成，插入数据库
            LOGGER.debug("Converted entity to update: " + entity);
            mapper.updateSelectiveById(entity);

        } catch (InstantiationException e) {
            LOGGER.error("Failed to parse model", e);
            throw new ServiceException("Failed to parse model", "manager.features.failed_parse_entity", e);

        } catch (IllegalAccessException e) {
            LOGGER.error("Failed to parse model", e);
            throw new ServiceException("Failed to parse model", "manager.features.failed_parse_entity", e);
            
        } catch (InvocationTargetException e) {
            LOGGER.error("Failed to parse model", e);
            throw new ServiceException("Failed to parse model", "manager.features.failed_parse_entity", e);
            
        } catch (NoSuchMethodException e) {
            LOGGER.error("Failed to parse model", e);
            throw new ServiceException("Failed to parse model", "manager.features.failed_parse_entity", e);
        }
    }

    @Override
    public void delete(List<ID> idList) throws ServiceException {
        if (idList == null || idList.isEmpty()) {
            LOGGER.error("ID list for deletion is empty.");
            throw new ServiceException("ID list for deletion is empty", "manager.features.failed_delete");
        }
        mapper.deleteByIds(idList);
    }
    
    /**
     * 获取当前FeatureServiceImpl的实体类类型.
     * @return 实体类类型
     */
    @SuppressWarnings("unchecked")
    protected Class<? extends BaseEntity<ID>> getEntityType() {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        return (Class<? extends BaseEntity<ID>>) type.getActualTypeArguments()[0];
    }

    /**
     * 获取当前FeatureServiceImpl的实体类ID字段类型.
     * @return 实体类ID字段类型
     */
    @SuppressWarnings("unchecked")
    protected Class<? extends Serializable> getIdType() {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        return (Class<? extends Serializable>) type.getActualTypeArguments()[1];
    }

    /**
     * 获取当前FeatureServiceImpl的所使用的Model类型.
     * @return Model类型
     */
    @SuppressWarnings("unchecked")
    protected Class<? extends FeatureModel> getServiceType() {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        return (Class<? extends FeatureModel>) type.getActualTypeArguments()[0];
    }

    /**
     * 获取当前FeatureServiceImpl的所使用的Mapper类型.
     * @return Mapper类型
     */
    @SuppressWarnings("unchecked")
    protected Class<? extends ManagementMapper<ENTITY, ID>> getMapperType() {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        return (Class<? extends ManagementMapper<ENTITY, ID>>) type.getActualTypeArguments()[0];
    }
    
    /**
     * 获取当前FeatureServiceImpl的Model类型.
     * @return Model类型
     */
    protected Class<?> getModelType() {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        return (Class<?>) type.getActualTypeArguments()[2];
    }
}
