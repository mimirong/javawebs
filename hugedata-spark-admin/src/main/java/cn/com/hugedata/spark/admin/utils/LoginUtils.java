package cn.com.hugedata.spark.admin.utils;

import cn.com.hugedata.spark.admin.login.AdminLoginService;
import cn.com.hugedata.spark.commons.storage.entity.UcAdminUser;
import cn.com.hugedata.spark.commons.utils.SpringUtils;

/**
 * 用于获取登录相关信息的工具类.
 * @author gaopeng
 */
public final class LoginUtils {

    /**
     * 获取当前登录用户的信息.
     * @return 当前登录用户的信息
     */
    public static UcAdminUser getLoginUser() {
        AdminLoginService adminLoginService = SpringUtils.getApplicationContext()
                .getBean(AdminLoginService.class);
        return adminLoginService.getLoginUser();
    }

    /**
     * 不允许创建实例.
     */
    private LoginUtils() {
    }
}
