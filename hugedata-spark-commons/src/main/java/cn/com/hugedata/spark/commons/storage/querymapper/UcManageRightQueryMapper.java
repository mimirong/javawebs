package cn.com.hugedata.spark.commons.storage.querymapper;

import java.util.List;
import java.util.Map;

import cn.com.hugedata.spark.commons.storage.entity.UcManageRight;

public interface UcManageRightQueryMapper {

    /**
     * 查询普通用户的后台管理权限.
     * @param params 多个参数: 
     *                   userId      用户ID
     *                   systemType  系统类型
     *                   order       排序信息
     * @return 权限列表
     */
    List<UcManageRight> queryRightsForUser(Map<String, Object> params);

    /**
     * 查询系统管理员的后台管理权限.
     * @param params 多个参数: 
     *                   systemType  系统类型
     *                   order       排序信息
     * @return 权限列表
     */
    List<UcManageRight> queryRightsForAdmin(Map<String, Object> params);

    /**
     * 查询指定角色的权限列表.
     * @param params 多个参数: 
     *                   roleId  角色ID
     *                   order   排序信息
     * @return 权限列表
     */
    List<UcManageRight> queryRightsForRole(Map<String, Object> params);
}
