package cn.com.hugedata.spark.commons.web.features;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.PageEntity;
import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.base.BaseEntity;

/**
 * 后台功能服务类.
 * @author 高鹏
 *
 * @param <ENTITY> 实体类类型
 * @param <ID>     实体类ID类型
 * @param <MODEL>  MVC Model类型
 */
public abstract class FeatureService<
        ENTITY extends BaseEntity<ID>, 
        ID     extends Serializable,
        MODEL  extends FeatureModel> {

    /**
     * 查询.
     * @param query     查询条件
     * @param pageStart 分页开始记录
     * @param pageSize  分页记录数
     * @param orders    排序信息
     * @return          查询结果
     */
    public abstract PageEntity<ENTITY> list(Map<String, Object> query, int pageStart, int pageSize, 
            List<OrderItem> orders);
    
    /**
     * 根据ID查询一条记录.
     * @param id ID
     * @return 查询结果
     * @throws ServiceException 查询失败
     */
    public abstract MODEL queryForModify(ID id) throws ServiceException;
    
    /**
     * 新增操作.
     * @param model Model
     * @throws ServiceException 新增失败 
     */
    public abstract ENTITY add(MODEL model) throws ServiceException;
    
    /**
     * 根据ID列表删除多条记录.
     * @param idList 需要删除的记录ID
     * @throws ServiceException 删除失败
     */
    public abstract void delete(List<ID> idList) throws ServiceException;
    
    /**
     * 根据ID修改记录.
     * @param model Model(包含ID字段)
     * @throws ServiceException 修改失败
     */
    public abstract void modify(MODEL model) throws ServiceException;
}
