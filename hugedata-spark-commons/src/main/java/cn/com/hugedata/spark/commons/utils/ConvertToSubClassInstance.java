package cn.com.hugedata.spark.commons.utils;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;

/**
 * 将父类实体，转换为子类实体.
 * 父类是xxxMapper select出来的各个实体类，子类存在的目的是想要对父类做一下扩展
 */
public abstract class ConvertToSubClassInstance<
        ENTITY extends BaseEntity>{

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

    /**
     * 获取当前ENTITY的实体类类型.
     * @return 实体类类型
     */
    @SuppressWarnings("unchecked")
    protected Class<? extends BaseEntity> getEntityType() {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        return (Class<? extends BaseEntity>) type.getActualTypeArguments()[0];
    }
}
