package cn.com.hugedata.spark.management.features.OsTechServiceSpec;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.PageEntity;
import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.entity.OsTechServiceSpec;
import cn.com.hugedata.spark.commons.storage.mapper.OsTechServiceSpecMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.commons.web.features.FeatureServiceImpl;

@Service
public class OsTechServiceSpecService
        extends FeatureServiceImpl<OsTechServiceSpec, Integer, OsTechServiceSpecModel, OsTechServiceSpecMapper> {

    @Autowired
    private OsTechServiceSpecMapper osTechServiceSpecMapper;
    
    @Override
    public PageEntity<OsTechServiceSpec> list(Map<String, Object> query, int pageStart, int pageSize,
            List<OrderItem> orders) {
        query.put(OsTechServiceSpec.Fields.SPEC_TYPE, OsTechServiceSpec.TYPE_COMPUTING);
        orders = Arrays.asList(new OrderItem(OsTechServiceSpec.Fields.SPEC_ID, OrderItem.ASC));
        return super.list(query, pageStart, pageSize, orders);
    }

    @Override
    public OsTechServiceSpec add(OsTechServiceSpecModel model) throws ServiceException {
        // 最多只允许添加3个
        int count = osTechServiceSpecMapper.countByMap(MyBatisUtils.params(
                OsTechServiceSpec.Fields.SPEC_TYPE, OsTechServiceSpec.TYPE_COMPUTING
        ));
        if (count >= 3) {
            throw new ServiceException("MaxSpecs", "最多只允许添加3个云主机套餐");
        }
        
        model.setSpecType(OsTechServiceSpec.TYPE_COMPUTING);
        model.setCreateTime(new Date());
        model.setUpdateTime(new Date());
        return super.add(model);
    }

    @Override
    public void modify(OsTechServiceSpecModel model) throws ServiceException {
        model.setUpdateTime(new Date());
        super.modify(model);
    }

}
