package cn.com.hugedata.spark.govaffairs.services.tech;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.PageEntity;
import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.entity.OsTechServiceApply;
import cn.com.hugedata.spark.commons.storage.entity.OsTechServiceConfig;
import cn.com.hugedata.spark.commons.storage.entity.OsTechServiceSpec;
import cn.com.hugedata.spark.commons.storage.entity.UcUserInfo;
import cn.com.hugedata.spark.commons.storage.mapper.OsTechServiceApplyMapper;
import cn.com.hugedata.spark.commons.storage.mapper.OsTechServiceConfigMapper;
import cn.com.hugedata.spark.commons.storage.mapper.OsTechServiceSpecMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.commons.web.login.LoginUtils;

/**
 * 云主机服务.
 */
@Service
public class TechComputingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TechComputingService.class);
    
    @Autowired
    private OsTechServiceApplyMapper osTechServiceApplyMapper;
    
    @Autowired
    private OsTechServiceSpecMapper osTechServiceSpecMapper;
    
    @Autowired
    private OsTechServiceConfigMapper osTechServiceConfigMapper;
    
    /**
     * 分页查询指定用户的云主机申请列表.
     * @param user      用户
     * @param pageStart 分页开始记录
     * @param pageSize  每页记录数
     * @return          分页查询结果
     */
    public PageEntity<OsTechServiceApply> queryApplyList(UcUserInfo user, int pageStart, int pageSize) 
            throws ServiceException {
        Map<String, Object> query = new TreeMap<>();
        query.put(OsTechServiceApply.Fields.APPLIER_ID, user.getUserId());
        query.put(OsTechServiceApply.Fields.APPLY_TYPE, OsTechServiceApply.APPLY_TYPE_COMPUTING);
        query.put(OrderItem.ORDER_KEY, Arrays.asList(new OrderItem(OsTechServiceApply.Fields.CREATE_TIME, OrderItem.DESC)));
        
        int count = osTechServiceApplyMapper.countByMap(query);
        List<OsTechServiceApply> data = osTechServiceApplyMapper.selectByMap(query, new RowBounds(pageStart, pageSize));
        
        return new PageEntity<>(data, pageStart, pageSize, count);
    }
    
    /**
     * 提交云主机申请.
     * @param user              申请用户
     * @param specId            套餐ID
     * @param serviceDuration   申请时长（单位为月）
     * @param amount            申请数量
     * @param price             总价格
     * @param specsJson         套餐信息
     * @throws ServiceException 保存申请信息失败
     */
    public void submitApply(
            UcUserInfo user,
            int serviceDuration,
            int amount,
            String specsJson) throws ServiceException {
        
        try {
            JSONObject spec = JSON.parseObject(specsJson);
            
            int totalPrice = spec.getIntValue("price") * amount * serviceDuration;
            
            OsTechServiceApply apply = new OsTechServiceApply();
            apply.setApplyType(OsTechServiceApply.APPLY_TYPE_COMPUTING);
            apply.setSpecId(spec.getString("id"));
            apply.setSpecName(spec.getString("name"));
            apply.setRemark(null);
            apply.setServiceDuration(serviceDuration);
            apply.setServiceStart(null);
            apply.setServiceEnd(null);
            apply.setAmount(amount);
            apply.setPrice(totalPrice);
            apply.setStatus(OsTechServiceApply.STATUS_CREATED);
            apply.setCompanyName(LoginUtils.getCurrentLogin().getCompanyName());
            apply.setContactName(LoginUtils.getCurrentLogin().getName());
            apply.setContactMobile(LoginUtils.getCurrentLogin().getMobile());
            apply.setApplierId(user.getUserId());
            apply.setApplierName(user.getLoginName());
            apply.setExtraData(specsJson);
            apply.setCreateTime(new Date());
            apply.setUpdateTime(new Date());;
            osTechServiceApplyMapper.insertSelective(apply);
            
        } catch (JSONException e) {
            LOGGER.error("Failed to parse spec json.", e);
            throw new ServiceException(this.getClass().getSimpleName() + "-InvalidSpecJson", "套餐信息不正确");
        }
    }
    
    /**
     * 删除申请.
     * @param applyId 申请ID
     */
    public void deleteApply(int applyId) throws ServiceException {
        OsTechServiceApply apply = osTechServiceApplyMapper.selectById(applyId);
        if (apply == null) {
            LOGGER.error("Tech service apply not found: {}", applyId);
            throw new ServiceException(getClass().getSimpleName() + "-ApplyIdNotFound", "申请信息不存在");
        }
        if (!OsTechServiceApply.APPLY_TYPE_COMPUTING.equals(apply.getApplyType())) {
            LOGGER.error("Invalid tech service apply type: {}", applyId);
            throw new ServiceException(getClass().getSimpleName() + "-InvalidApplyType", "申请信息不存在");
        }
        
        if (!OsTechServiceApply.STATUS_CREATED.equals(apply.getStatus())) {
            LOGGER.error("Invalid tech service apply status: {}", applyId);
            throw new ServiceException(getClass().getSimpleName() + "-InvalidStatusToDelete", "只能删除未审批的申请");
        }
        
        osTechServiceApplyMapper.deleteById(applyId);
    }
    
    /**
     * 查询云主机套餐信息.
     * @return
     */
    public List<OsTechServiceSpec> querySpecs() {
        return osTechServiceSpecMapper.selectByMap(MyBatisUtils.params(
                OsTechServiceSpec.Fields.SPEC_TYPE, OsTechServiceSpec.TYPE_COMPUTING,
                new OrderItem(OsTechServiceSpec.Fields.SPEC_ID, OrderItem.ASC)
        ));
    }

    
    /**
     * 查询云主机申请配置.
     * @return 云主机申请配置
     */
    public Map<String, Object> queryConfig() {
        List<OsTechServiceConfig> configs = osTechServiceConfigMapper.selectByMap(MyBatisUtils.params(
                OsTechServiceConfig.Fields.CONFIG_TYPE, OsTechServiceConfig.TYPE_COMPUTING
        ));

        Map<String, Object> map = new TreeMap<>();
        for (OsTechServiceConfig cfg : configs) {
            String name = cfg.getConfigName();
            name = name.substring(0, 1).toLowerCase() + name.substring(1);
            
            try {
                map.put(name, JSON.parse(cfg.getConfigValue()));
            } catch (JSONException e) {
                map.put(name, cfg.getConfigValue());
            }
        }
        return map;
    }
    
}
