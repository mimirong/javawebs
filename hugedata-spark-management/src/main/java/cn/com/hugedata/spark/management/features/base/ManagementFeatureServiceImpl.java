package cn.com.hugedata.spark.management.features.base;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.LogOperation;
import cn.com.hugedata.spark.commons.web.features.FeatureModel;
import cn.com.hugedata.spark.commons.web.features.FeatureServiceImpl;
import cn.com.hugedata.spark.management.utils.ManagerOperationLogUtils;

/**
 * 后台管理所使用的FeatureServiceImpl，在原有的FeatureServiceImpl中增加了操作日志记录.
 * @author gaopeng
 *
 * @param <ENTITY>  实体类类型
 * @param <ID>      实体类ID字段类型
 * @param <MODEL>   MVC Model类型
 * @param <MAPPER>  Mapper类型
 */
public abstract class ManagementFeatureServiceImpl<
        ENTITY extends BaseEntity<ID>, 
        ID     extends Serializable, 
        MODEL  extends FeatureModel,
        MAPPER extends ManagementMapper<? super ENTITY, ID>>
        extends FeatureServiceImpl<ENTITY, ID, MODEL, MAPPER> {
    
    protected abstract String getLogTargetName();

    @Override
    public ENTITY add(MODEL model) throws ServiceException {
        ENTITY e = super.add(model);
        ManagerOperationLogUtils.save(getLogTargetName(), 
                model.findModelId(), model.findModelName(), LogOperation.OPERATION_ADD);
        return e;
    }

    @Override
    public void modify(MODEL model) throws ServiceException {
        super.modify(model);
        ManagerOperationLogUtils.save(getLogTargetName(), 
                model.findModelId(), model.findModelName(), LogOperation.OPERATION_MODIFY);
    }

    @Override
    public void delete(List<ID> idList) throws ServiceException {
        super.delete(idList);
        ManagerOperationLogUtils.save(getLogTargetName(), 
                StringUtils.join(idList, ","), null, LogOperation.OPERATION_DELETE);
    }

}
