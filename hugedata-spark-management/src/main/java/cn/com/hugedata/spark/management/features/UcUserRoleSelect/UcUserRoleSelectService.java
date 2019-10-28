package cn.com.hugedata.spark.management.features.UcUserRoleSelect;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.PageEntity;
import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.entity.LogOperation;
import cn.com.hugedata.spark.commons.storage.entity.UcManageUserRole;
import cn.com.hugedata.spark.commons.storage.entity.UcUserInfo;
import cn.com.hugedata.spark.commons.storage.mapper.UcManageRoleMapper;
import cn.com.hugedata.spark.commons.storage.mapper.UcManageUserRoleMapper;
import cn.com.hugedata.spark.commons.storage.mapper.UcUserInfoMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.commons.web.features.FeatureServiceImpl;
import cn.com.hugedata.spark.commons.web.features.NullModel;
import cn.com.hugedata.spark.management.utils.ManagerOperationLogUtils;

/**
 * 选择角色服务.
 */
@Service
public class UcUserRoleSelectService extends FeatureServiceImpl<UcUserRoleSelect, String, NullModel, UcManageRoleMapper> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UcUserRoleSelectService.class);
    
    @Autowired
    private UcUserInfoMapper ucUserInfoMapper;
    
    @Autowired
    private UcManageUserRoleMapper ucManageUserRoleMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PageEntity<UcUserRoleSelect> list(Map<String, Object> query, int pageStart, int pageSize,
            List<OrderItem> orders) {
        // 查询已选择的角色信息
        int userId = (int) query.get("userId");
        List<UcManageUserRole> userRoleList = ucManageUserRoleMapper.selectByMap(
                MyBatisUtils.buildParameterMap(
                        UcManageUserRole.Fields.USER_ID, userId
                )
        );
        Set<String> selectedRoleSet = new TreeSet<>();
        for (UcManageUserRole ur : userRoleList) {
            selectedRoleSet.add(ur.getRoleId());
        }
        
        // 查询
        PageEntity<UcUserRoleSelect> page = super.list(query, pageStart, pageSize, orders);
        
        // 处理是否选中字段
        for (UcUserRoleSelect urs : page.getData()) {
            urs.setSelected(selectedRoleSet.contains(urs.getRoleId()));
        }
        
        return page;
    }
    
    /**
     * 保存用户角色配置信息.
     * @param userId            用户ID
     * @param roleIdList        配置的权限列表
     * @throws ServiceException 保存失败
     */
    public void updateUserRoles(int userId, List<String> roleIdList) throws ServiceException {
        // 查询用户信息
        UcUserInfo user = ucUserInfoMapper.selectById(userId);
        if (user == null) {
            LOGGER.error("User id not found: {}", userId);
            throw new ServiceException("UcUserRoleSelectService-UserNotFound", "用户不存在");
        }
        
        // 删除原有角色关联信息
        ucManageUserRoleMapper.deleteByMap(MyBatisUtils.buildParameterMap(
                UcManageUserRole.Fields.USER_ID, userId
        ));
        
        // 保存用户角色关联信息
        for (String roleId : roleIdList) {
            UcManageUserRole ur = new UcManageUserRole();
            ur.setRoleId(roleId);
            ur.setUserId(userId);
            ucManageUserRoleMapper.insertSelective(ur);
        }
        
        // 保存操作日志
        ManagerOperationLogUtils.save(LogOperation.TARGET_PARK_USERS, userId, user.getName(), 
                LogOperation.OPERATION_USER_UPDATE_ROLES);
    }
}
