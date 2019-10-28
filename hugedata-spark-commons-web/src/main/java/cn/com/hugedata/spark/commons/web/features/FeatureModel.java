package cn.com.hugedata.spark.commons.web.features;

/**
 * MVC Model基类.
 * @author 高鹏
 */
public interface FeatureModel {
    
    /**
     * 获取当前操作对象的ID，在记录系统日志时会使用，可以返回null.
     * @return 当前操作对象的ID
     */
    public abstract String findModelId();

    /**
     * 获取当前操作对象的名称，在记录系统日志时会使用，可以返回null.
     * @return 当前操作对象的名称
     */
    public abstract String findModelName();
    
}
