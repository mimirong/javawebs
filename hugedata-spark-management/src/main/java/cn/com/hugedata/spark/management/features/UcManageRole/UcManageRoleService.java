package cn.com.hugedata.spark.management.features.UcManageRole;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.entity.LogOperation;
import cn.com.hugedata.spark.commons.storage.entity.UcManageRight;
import cn.com.hugedata.spark.commons.storage.entity.UcManageRole;
import cn.com.hugedata.spark.commons.storage.entity.UcManageRoleRight;
import cn.com.hugedata.spark.commons.storage.mapper.UcManageRightMapper;
import cn.com.hugedata.spark.commons.storage.mapper.UcManageRoleMapper;
import cn.com.hugedata.spark.commons.storage.mapper.UcManageRoleRightMapper;
import cn.com.hugedata.spark.commons.storage.querymapper.UcManageRightQueryMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.management.features.base.ManagementFeatureServiceImpl;
import cn.com.hugedata.spark.management.utils.ManagerOperationLogUtils;

@Service
public class UcManageRoleService 
        extends ManagementFeatureServiceImpl<UcManageRole, String, UcManageRoleModel, UcManageRoleMapper> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UcManageRoleService.class);
    
    @Autowired
    private UcManageRightMapper ucManageRightMapper;
    
    @Autowired
    private UcManageRightQueryMapper ucManageRightQueryMapper;
    
    @Autowired
    private UcManageRoleRightMapper ucManageRoleRightMapper;
    
    @Autowired
    private UcManageRoleMapper ucManageRoleMapper;

    @Override
    protected String getLogTargetName() {
        return LogOperation.TARGET_ROLES;
    }

    @Override
    public UcManageRole add(UcManageRoleModel model) throws ServiceException {
        checkRoleIdExist(model.getRoleId());
        checkRoleNameExist(model.getName(), null);
        return super.add(model);
    }

    @Override
    public void modify(UcManageRoleModel model) throws ServiceException {
        checkRoleNameExist(model.getName(), model.getRoleId());
        super.modify(model);
    }
    
    /**
     * 查询所有权限.
     * @return 所有权限
     */
    public List<UcManageRight> queryAllRights() {
        List<UcManageRight> list = ucManageRightMapper.selectByMap(MyBatisUtils.buildParameterMap(
                "order", Arrays.asList(new OrderItem(UcManageRight.Fields.SORT_INDEX, "asc"))
        ));
        return list;
    }
    
    /**
     * 查询指定角色已配置的权限信息，并返回JSONArray.
     * @param roleId 角色ID
     * @return       权限列表
     */
    public List<UcManageRight> querySelectedRights(String roleId) {
        List<UcManageRight> list = ucManageRightQueryMapper.queryRightsForRole(MyBatisUtils.buildParameterMap(
                "roleId", roleId,
                "order", Arrays.asList(new OrderItem(UcManageRight.Fields.SORT_INDEX, "asc"))
        ));
        return list;
    }
    
    /**
     * 为指定角色设置权限信息.
     * @param roleId 角色ID
     * @param rights 权限ID
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveRightsForRole(String roleId, List<String> rights) throws ServiceException {
        UcManageRole role = queryRole(roleId);
        
        // 删除角色原有的权限信息
        ucManageRoleRightMapper.deleteByMap(MyBatisUtils.buildParameterMap(
                UcManageRoleRight.Fields.ROLE_ID, roleId
        ));
        
        // 保存角色的权限信息
        for (String rightId : rights) {
            UcManageRoleRight rr = new UcManageRoleRight();
            rr.setRightId(rightId);
            rr.setRoleId(roleId);
            ucManageRoleRightMapper.insertSelective(rr);
        }
        
        // 保存操作日志
        ManagerOperationLogUtils.save(LogOperation.TARGET_ROLES, roleId, role.getName(), 
                LogOperation.OPERATION_ROLE_UPDATE_RIGHTS);
    }
    
    
    /**
     * 检查角色ID是否已经存在.
     * @param roleId            需要检查的角色ID
     * @throws ServiceException 检查失败
     */
    private void checkRoleIdExist(String roleId) throws ServiceException {
        int count = ucManageRoleMapper.countByMap(
                MyBatisUtils.buildParameterMap(
                        UcManageRole.Fields.ROLE_ID, roleId
                )
        );
        if (count > 0) {
            throw new ServiceException("UcManageRoleService-RoleIExists", "角色ID已经存在");
        }
    }
    
    /**
     * 检查角色名称是否已经存在.
     * @param roleName           需要检查的角色名称
     * @param currentRoleId      当前正在修改的角色ID，(新增角色时)可以为null
     * @throws ServiceException  检查失败
     */
    private void checkRoleNameExist(String roleName, String currentRoleId) throws ServiceException {
        List<UcManageRole> list = ucManageRoleMapper.selectByMap(
                MyBatisUtils.buildParameterMap(
                        UcManageRole.Fields.NAME, roleName
                )
        );
        for (UcManageRole role : list) {
            if (!role.getRoleId().equals(currentRoleId)) {
                throw new ServiceException("UcManageRoleService-RoleNameExists", "角色名称已经存在");
            }
        }
    }

    /**
     * 根据角色ID查询角色信息，如果不存在，抛出异常.
     * @param roleId             角色ID
     * @return                   角色信息
     * @throws ServiceException  角色不存在
     */
    public UcManageRole queryRole(String roleId) throws ServiceException {
        UcManageRole role = ucManageRoleMapper.selectById(roleId);
        if (role == null) {
            LOGGER.error("Role id not exist: {}", roleId);
            throw new ServiceException("UcManageRoleService-RoleIdNotFound", "角色信息不存在");
        }
        return role;
    }
}
