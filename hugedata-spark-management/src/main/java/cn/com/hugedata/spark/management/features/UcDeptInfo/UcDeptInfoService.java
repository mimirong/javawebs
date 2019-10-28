package cn.com.hugedata.spark.management.features.UcDeptInfo;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.entity.LogOperation;
import cn.com.hugedata.spark.commons.storage.entity.UcDeptInfo;
import cn.com.hugedata.spark.commons.storage.mapper.UcDeptInfoMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.management.features.base.ManagementFeatureServiceImpl;

@Service
public class UcDeptInfoService extends ManagementFeatureServiceImpl<UcDeptInfo, Integer, UcDeptInfoModel, UcDeptInfoMapper> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UcDeptInfoService.class);
    
    @Autowired
    private UcDeptInfoMapper ucDeptInfoMapper;

    @Override
    protected String getLogTargetName() {
        return LogOperation.TARGET_PARK_DEPTS;
    }

    @Override
    public UcDeptInfo add(UcDeptInfoModel model) throws ServiceException {
        // 查询上级部门
        UcDeptInfo parent = null;
        if (model.getParentDeptId() != null) {
            parent = queryParentDept(model.getParentDeptId());
        }
        
        // 检查部门名称是否已经存在
        checkDeptNameExist(model.getName(), model.getParentDeptId(), null);
        
        // 设置其它属性
        model.setCreateTime(new Date());
        model.setUpdateTime(new Date());
        
        // 保存部门信息
        UcDeptInfo dept = super.add(model);
        
        // 更新fullId字段
        UcDeptInfo update = new UcDeptInfo();
        update.setDeptId(dept.getDeptId());
        if (parent == null) {
            update.setFullId(String.valueOf(dept.getDeptId()));
        } else {
            update.setFullId(buildFullId(parent.getFullId(), dept.getDeptId()));
        }
        ucDeptInfoMapper.updateSelectiveById(update);
        
        return dept;
    }

    @Override
    public void modify(UcDeptInfoModel model) throws ServiceException {
        // 查询当前部门信息
        UcDeptInfo oldDept = ucDeptInfoMapper.selectById(model.getDeptId());
        if (oldDept == null) {
            LOGGER.error("Dept to modify not exist: {}", model.getDeptId());
            throw new ServiceException("UcDeptInfoService-DeptIdToModifyNotExist", "部门不存在");
        }
        
        // 查询上级部门
        UcDeptInfo parent = null;
        if (model.getParentDeptId() != null) {
            parent = queryParentDept(model.getParentDeptId());
        }
        
        // 检查部门名称是否已经存在
        checkDeptNameExist(model.getName(), model.getParentDeptId(), oldDept.getDeptId());
        
        // 更新
        model.setUpdateTime(new Date());
        if (parent == null) {
            model.setFullId(String.valueOf(oldDept.getDeptId()));
        } else {
            model.setFullId(buildFullId(parent.getFullId(), oldDept.getDeptId()));
        }
        super.modify(model);
    }
    
    /**
     * 查询所有部门.
     * @return 所有部门
     */
    public List<UcDeptInfo> queryAllDepartments() {
        return ucDeptInfoMapper.selectByMap(null);
    }

    /**
     * 查询上级部门信息.
     * @param parentDeptId      上级部门ID
     * @return                  上级部门信息
     * @throws ServiceException 查询失败，异常包含错误信息
     */
    private UcDeptInfo queryParentDept(int parentDeptId) throws ServiceException {
        UcDeptInfo parent = ucDeptInfoMapper.selectById(parentDeptId);
        if (parent == null) {
            LOGGER.error("Parent dept id not exist: {}", parentDeptId);
            throw new ServiceException("UcDeptInfoService-ParentDeptIdNotFound", "上级部门不存在");
        }
        return parent;
    }
    
    /**
     * 构造部门信息的完整ID.
     * @param parentFullId 上级部门ID
     * @param deptId       当前部门ID
     * @return             当前部门的完整ID
     */
    private String buildFullId(String parentFullId, int deptId) {
        return parentFullId + "_" + deptId;
    }
    
    /**
     * 查询部门名称是否已经存在.
     * @param name              部门名称
     * @param parentDeptId      上级部门ID
     * @param currentDeptId     当前部门ID，新增时为null
     * @throws ServiceException 部门名称已经存在
     */
    private void checkDeptNameExist(String name, Integer parentDeptId, Integer currentDeptId) throws ServiceException {
        List<UcDeptInfo> list = ucDeptInfoMapper.selectByMap(
                MyBatisUtils.buildParameterMap(
                        UcDeptInfo.Fields.PARENT_DEPT_ID, parentDeptId,
                        UcDeptInfo.Fields.NAME, name
                )
        );
        for (UcDeptInfo dept : list) {
            if (!dept.getDeptId().equals(currentDeptId)) {
                LOGGER.error("Dept name exist: {}, parent:{}, current:{}", name, parentDeptId, currentDeptId);
                throw new ServiceException("UcDeptInfoService-DeptNameExists", "部门名称已经存在");
            }
        }
    }
}
