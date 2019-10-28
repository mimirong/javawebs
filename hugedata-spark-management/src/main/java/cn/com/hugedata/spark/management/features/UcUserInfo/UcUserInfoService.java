package cn.com.hugedata.spark.management.features.UcUserInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.PageEntity;
import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.constant.UcUserInfoConstants;
import cn.com.hugedata.spark.commons.storage.entity.LogOperation;
import cn.com.hugedata.spark.commons.storage.entity.UcUserDept;
import cn.com.hugedata.spark.commons.storage.entity.UcUserInfo;
import cn.com.hugedata.spark.commons.storage.mapper.UcUserDeptMapper;
import cn.com.hugedata.spark.commons.storage.mapper.UcUserInfoMapper;
import cn.com.hugedata.spark.commons.storage.querymapper.UcUserInfoQueryMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.commons.utils.PasswordEncryption;
import cn.com.hugedata.spark.management.features.base.ManagementFeatureServiceImpl;

@Service
public class UcUserInfoService extends ManagementFeatureServiceImpl<UcUserInfo, Integer, UcUserInfoModel, UcUserInfoMapper> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UcUserInfoService.class);
    
    @Autowired
    private UcUserInfoMapper ucUserInfoMapper;
    
    @Autowired
    private UcUserInfoQueryMapper ucUserInfoQueryMapper;
    
    @Autowired
    private UcUserDeptMapper ucUserDeptMapper;
    
    @Override
    protected String getLogTargetName() {
        return LogOperation.TARGET_PARK_USERS;
    }
    
    @Override
    public PageEntity<UcUserInfo> list(Map<String, Object> query, int pageStart, int pageSize, List<OrderItem> orders) {
        query.put(UcUserInfo.Fields.USER_TYPE, UcUserInfo.USER_TYPE_PARK);
        orders = Arrays.asList(new OrderItem(UcUserInfo.Fields.USER_ID, OrderItem.DESC));
        return super.list(query, pageStart, pageSize, orders);
    }

    @Override
    protected Integer executeCountByMap(Map<String, Object> query) {
        if (query.containsKey("deptCode")) {
            return ucUserInfoQueryMapper.countByMap(query);    
        } else {
            return ucUserInfoMapper.countByMap(query);
        }
    }

    @Override
    protected List<? super UcUserInfo> executeSelectByMap(Map<String, Object> query, int pageStart, int pageSize) {
        if (query.containsKey("deptCode")) {
            return ucUserInfoQueryMapper.selectByMap(query, new RowBounds(pageStart, pageSize));
        } else {
            return ucUserInfoMapper.selectByMap(query, new RowBounds(pageStart, pageSize));
        }
    }

    @Override
    public UcUserInfo add(UcUserInfoModel model) throws ServiceException {
        model.setUserType(UcUserInfoConstants.USER_TYPE_PARK);
        model.setLoginNameUpper(model.getLoginName().toUpperCase());
        model.setPasswordKey(PasswordEncryption.randomKey());
        model.setPassword(PasswordEncryption.encrypt(model.getPassword(), model.getPasswordKey()));
        if (!StringUtils.isEmpty(model.getEmail())) {
            model.setEmail(model.getEmail());
            model.setEmailUpper(model.getEmail().toUpperCase());
        }
        model.setUsable(true);
        model.setCreateTime(new Date());
        model.setUpdateTime(new Date());
        
        checkLoginNameExists(model.getLoginName(), null);
        
        UcUserInfo added = super.add(model);
        
        saveUserDepts(added.getUserId(), model);
        
        return added;
    }

    @Override
    public void modify(UcUserInfoModel model) throws ServiceException {
        model.setUserType(UcUserInfoConstants.USER_TYPE_PARK);
        model.setLoginNameUpper(model.getLoginName().toUpperCase());
        if (StringUtils.isNotEmpty(model.getPassword())) {
            model.setPasswordKey(PasswordEncryption.randomKey());
            model.setPassword(PasswordEncryption.encrypt(model.getPassword(), model.getPasswordKey()));
        } else {
            model.setPassword(null);
        }
        if (!StringUtils.isEmpty(model.getEmail())) {
            model.setEmail(model.getEmail());
            model.setEmailUpper(model.getEmail().toUpperCase());
        }
        model.setUpdateTime(new Date());
        
        checkLoginNameExists(model.getLoginName(), model.getUserId());
        
        super.modify(model);
        saveUserDepts(model.getUserId(), model);
    }

    @Override
    public UcUserInfoModel queryForModify(Integer id) throws ServiceException {
        UcUserInfoModel user =  super.queryForModify(id);
        user.setPassword(null);
        user.setPasswordKey(null);
        user.setPasswordHash(null);
        
        // 查询部门信息
        List<String> deptIdList = new ArrayList<>();
        List<UcUserDept> udList = ucUserDeptMapper.selectByMap(MyBatisUtils.params(UcUserDept.Fields.USER_ID, id));
        for (UcUserDept ud : udList) {
            deptIdList.add("" + ud.getDeptId());
        }
        user.setDeptJson(JSON.toJSONString(deptIdList));
        
        return user;
    }

    private String deptIdToName(String deptId) {
        switch (deptId) {
            case "1":  return "经济发展局";
            case "2":  return "国土规划局";
            case "3":  return "工程建设局";
            case "4":  return "社会事务局";
            case "5":  return "招商合作局";
            case "6":  return "财政分局";
            case "7":  return "办公室";
            case "8":  return "党群纪检绩效办";
            case "99": return "其它";
            default:   return "";
        }
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
    
    /**
     * 判断用户名是否已经存在.
     * @throws ServiceException
     */
    private void checkLoginNameExists(String loginName, Integer userId) throws ServiceException {
        int count = ucUserInfoMapper.countByMap(MyBatisUtils.buildParameterMap(
                UcUserInfo.Query.USER_ID__NE, userId,
                UcUserInfo.Fields.LOGIN_NAME_UPPER, loginName.toUpperCase()
        ));
        if (count > 0) {
            LOGGER.error("Login name exists: {}", loginName);
            throw new ServiceException("LoginNameExists", "用户名已经存在");
        }
    }
    
    /**
     * 保存用户部门信息.
     */
    private void saveUserDepts(int userId, UcUserInfoModel model) throws ServiceException {
        try {
            JSONArray arr = JSON.parseArray(model.getDeptJson());
            
            ucUserDeptMapper.deleteByMap(MyBatisUtils.params(
                    UcUserDept.Fields.USER_ID, userId
            ));

            ArrayList<String> deptNames = new ArrayList<>();
            for (int i = 0; i < arr.size(); i++) {
                String deptId = arr.getString(i);
                deptNames.add(deptIdToName(deptId));
                
                UcUserDept ud = new UcUserDept();
                ud.setUserId(userId);
                ud.setDeptId(Integer.parseInt(deptId));
                ucUserDeptMapper.insertSelective(ud);
            }
            
            UcUserInfo userUpdate = new UcUserInfo();
            userUpdate.setUserId(userId);
            userUpdate.setDeptName(StringUtils.join(deptNames, ", "));
            ucUserInfoMapper.updateSelectiveById(userUpdate);
            
        } catch (JSONException e) {
            LOGGER.error("Failed to parse dept id array", e);
            throw new ServiceException("FailedParseDeptArray", "无法解析部门信息");
        }
    }
}
