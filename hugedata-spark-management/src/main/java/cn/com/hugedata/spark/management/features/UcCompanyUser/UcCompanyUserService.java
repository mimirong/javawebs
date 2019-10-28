package cn.com.hugedata.spark.management.features.UcCompanyUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.PageEntity;
import cn.com.hugedata.spark.commons.service.UrlManager;
import cn.com.hugedata.spark.commons.service.email.SendEmailService;
import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.entity.LogOperation;
import cn.com.hugedata.spark.commons.storage.entity.UcCompanyInfo;
import cn.com.hugedata.spark.commons.storage.entity.UcUserInfo;
import cn.com.hugedata.spark.commons.storage.mapper.UcCompanyInfoMapper;
import cn.com.hugedata.spark.commons.storage.mapper.UcUserInfoMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.management.features.base.ManagementFeatureServiceImpl;

/**
 * 用户帐号管理.
 */
@Service
public class UcCompanyUserService
        extends ManagementFeatureServiceImpl<UcUserInfo, Integer, UcCompanyUserModel, UcUserInfoMapper> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UcCompanyUserService.class);
    
    @Autowired
    private UcUserInfoMapper ucUserInfoMapper;
    
    @Autowired
    private UcCompanyInfoMapper ucCompanyInfoMapper;
    
    @Autowired
    private SendEmailService sendEmailService;
    
    @Autowired
    private UrlManager urlManager;
    
    @Override
    protected String getLogTargetName() {
        return LogOperation.TARGET_COMPANY_USERS;
    }

    @Override
    public PageEntity<UcUserInfo> list(Map<String, Object> query, int pageStart, int pageSize, List<OrderItem> orders) {
        if (orders == null) {
            orders = new ArrayList<>();
        }
        orders.add(new OrderItem(UcUserInfo.Fields.USER_ID, OrderItem.DESC));
        
        if (query == null) {
            query = new TreeMap<>();
        }
        
        
        if(query.containsKey(UcUserInfo.Query.COMPANY_NAME__LIKE)){
            //
            List<UcCompanyInfo> cs = ucCompanyInfoMapper.selectByMap(MyBatisUtils.buildParameterMap(UcCompanyInfo.Query.COMPANY_NAME__LIKE,query.get(UcUserInfo.Query.COMPANY_NAME__LIKE)));
            
            if(cs != null && cs.size() > 0){
                List<Integer> companyIds =  new ArrayList<Integer>();
                for(UcCompanyInfo c : cs){
                    companyIds.add(c.getCompanyId());
                }
                query.remove(UcUserInfo.Query.COMPANY_NAME__LIKE);
                query.put(UcUserInfo.Query.COMPANY_ID__IN, companyIds);
            }else{
                return new  PageEntity<UcUserInfo>(new ArrayList<UcUserInfo>(), pageStart, pageSize, 0);
            }
            
        }
        query.put(UcUserInfo.Fields.USER_TYPE, UcUserInfo.USER_TYPE_COMPANY);
        query.put(UcUserInfo.Query.COMPANY_ID__NE,"null");
        PageEntity<UcUserInfo> us = super.list(query, pageStart, pageSize, orders);
        if(us != null && us.getData() != null){
            for(UcUserInfo u : us.getData()){
               UcCompanyInfo c = ucCompanyInfoMapper.selectById(u.getCompanyId());
               u.setCompanyName(c.getCompanyName());
            }
        }
        return us;
    } 

    /**
     * 设置用户是否可用.
     * @param userId            用户ID
     * @param isEnabled         是否可用
     * @throws ServiceException 设置失败
     */
    public void setUserEnabled(int userId, boolean isEnabled) throws ServiceException {
        queryUser(userId);
        
        UcUserInfo update = new UcUserInfo();
        update.setUserId(userId);
        update.setUsable(isEnabled);
        update.setApproveStatus(UcUserInfo.APPROVE_STATUS_APPROVED);
        ucUserInfoMapper.updateSelectiveById(update);
    }
    
    /**
     * 发送开通邮件.
     * @param userId            用户ID
     * @throws ServiceException 开通失败
     */
    public void sendApproveMail(int userId) throws ServiceException {
        final UcUserInfo user = queryUser(userId);
        final String umsUrl = urlManager.getUmsUrl();
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    LOGGER.info("Send user approve email: {}", user.getUserId());
                    
                    Map<String, Object> data = new HashMap<String, Object>();
                    data.put("user", user);
                    data.put("now", new Date());
                    data.put("umsUrl", umsUrl);
                    
                    if (user.getUsable()) {
                        sendEmailService.sendEmail(user.getEmail(), "user_approved_message_email.ftl", data);
                    } else {
                        sendEmailService.sendEmail(user.getEmail(), "user_disabled_message_email.ftl", data);
                    }
                    LOGGER.info("Successfully sent user approve message email to {}", user.getEmail());
                } catch (ServiceException e) {
                    LOGGER.error("Failed to send password recovery email.", e);
                }
            }
        }).start();
    }
    
    /**
     * 根据用户ID查询用户
     * @param userId
     * @return
     * @throws ServiceException
     */
    private UcUserInfo queryUser(int userId) throws ServiceException {
        UcUserInfo user = ucUserInfoMapper.selectById(userId);
        if (user == null) {
            LOGGER.error("User id not found: {}", userId);
            throw new ServiceException("UcCompanyUserService-UserNotFound", "用户不存在");
        }
        return user;
    }

    @Override
    public UcCompanyUserModel queryForModify(Integer id) throws ServiceException {
        UcCompanyUserModel model = super.queryForModify(id);
        
        UcCompanyInfo company = ucCompanyInfoMapper.selectById(model.getCompanyId());
        String compnayInfoJson = JSON.toJSONString(company);
        
        model.setCompanyInfoJson(compnayInfoJson);
        return model;
    }
}
