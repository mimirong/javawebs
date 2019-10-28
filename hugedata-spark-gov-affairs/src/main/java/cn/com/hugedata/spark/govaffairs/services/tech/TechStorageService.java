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
import cn.com.hugedata.spark.commons.storage.entity.UcUserInfo;
import cn.com.hugedata.spark.commons.storage.mapper.OsTechServiceApplyMapper;
import cn.com.hugedata.spark.commons.storage.mapper.OsTechServiceConfigMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.commons.web.login.LoginUtils;

/**
 * 前台云存储服务.
 */
@Service
public class TechStorageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TechStorageService.class);
    
    @Autowired
    private OsTechServiceApplyMapper osTechServiceApplyMapper;
    
    @Autowired
    private OsTechServiceConfigMapper osTechServiceConfigMapper;
    
    /**
     * 分页查询指定用户的云存储申请列表.
     * @param user      用户
     * @param pageStart 分页开始记录
     * @param pageSize  每页记录数
     * @return          分页查询结果
     */
    public PageEntity<OsTechServiceApply> queryApplyList(UcUserInfo user, int pageStart, int pageSize) 
            throws ServiceException {
        Map<String, Object> query = new TreeMap<>();
        query.put(OsTechServiceApply.Fields.APPLIER_ID, user.getUserId());
        query.put(OsTechServiceApply.Fields.APPLY_TYPE, OsTechServiceApply.APPLY_TYPE_STORAGE);
        query.put(OrderItem.ORDER_KEY, Arrays.asList(new OrderItem(OsTechServiceApply.Fields.CREATE_TIME, OrderItem.DESC)));
        
        int count = osTechServiceApplyMapper.countByMap(query);
        List<OsTechServiceApply> data = osTechServiceApplyMapper.selectByMap(query, new RowBounds(pageStart, pageSize));
        
        return new PageEntity<>(data, pageStart, pageSize, count);
    }
    
    /**
     * 提交云存储申请.
     * @param user              申请用户
     * @throws ServiceException 保存申请信息失败
     */
    public void submitApply(
            UcUserInfo user,
            String size,
            int serviceDuration,
            int amount,
            int totalPrice) throws ServiceException {
        JSONObject specsJson = new JSONObject();
        specsJson.put("size", size);
        
        OsTechServiceApply apply = new OsTechServiceApply();
        apply.setApplyType(OsTechServiceApply.APPLY_TYPE_STORAGE);
        apply.setSpecId(size);
        apply.setSpecName(size);
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
        apply.setExtraData(specsJson.toJSONString());
        apply.setCreateTime(new Date());
        apply.setUpdateTime(new Date());;
        osTechServiceApplyMapper.insertSelective(apply);
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
        if (!OsTechServiceApply.APPLY_TYPE_STORAGE.equals(apply.getApplyType())) {
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
     * 查询云存储申请配置.
     * @return 云存储申请配置
     */
    public Map<String, Object> queryConfig() {
        List<OsTechServiceConfig> configs = osTechServiceConfigMapper.selectByMap(MyBatisUtils.params(
                OsTechServiceConfig.Fields.CONFIG_TYPE, OsTechServiceConfig.TYPE_STORAGE
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
