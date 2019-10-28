package cn.com.hugedata.spark.admin.login;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.entity.UcAdminUser;
import cn.com.hugedata.spark.commons.storage.mapper.UcAdminUserMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.commons.utils.PasswordHash3;

/**
 * 系统管理登录.
 */
@Service
public class AdminLoginService {

    /** Logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminLoginService.class);

    @Autowired
    private UcAdminUserMapper ucAdminUserMapper;
    
    /**
     * 登录处理.
     * @param username            用户名
     * @param password            密码
     * @throws ServiceException 登录失败
     */
    public void login(String username, String password) throws ServiceException {
        // 查询用户信息
        List<UcAdminUser> userList = ucAdminUserMapper.selectByMap(MyBatisUtils.buildParameterMap(
                "username", username
        ));
        if (userList == null || userList.isEmpty()) {
            LOGGER.error("Admin user not found: {}", username);
            throw new ServiceException("AdminLoginService-UserNotFound", "用户名或密码错误");
        }
        UcAdminUser user = userList.get(0);
        
        // 计算密码
        String hash = PasswordHash3.calcPasswordHash(username, password);
        
        // 判断密码是否正确
        if (!user.getPassword().equals(hash)) {
            LOGGER.error("Wrong password for admin user: {}", username);
            throw new ServiceException("AdminLoginService-WrongPassword", "用户名或密码错误");
        }
        
        // 登录成功
        HttpSession session = findHttpRequest().getSession();
        session.setAttribute(AdminLoginConstants.KEY_LOGIN_USER, user);
    }

    /**
     * 加载当前登录用户的信息.
     * @return 是否已经登录
     */
    public boolean loadLoginInfo() {
        HttpServletRequest request = findHttpRequest();
        HttpSession session = request.getSession();
        UcAdminUser user = (UcAdminUser) session.getAttribute(AdminLoginConstants.KEY_LOGIN_USER);
        if (user == null) {
            return false;
        }
        request.setAttribute(AdminLoginConstants.KEY_LOGIN_USER, user);
        return true;
    }
    
    /**
     * 获取当前登录用户的信息.
     * @return 当前登录用户的信息
     */
    public UcAdminUser getLoginUser() {
        HttpServletRequest request = findHttpRequest();
        return (UcAdminUser) request.getAttribute(AdminLoginConstants.KEY_LOGIN_USER);
    }

    /**
     * 退出登录.
     */
    public void logout() {
        HttpServletRequest request = findHttpRequest();
        HttpSession session = request.getSession();
        request.removeAttribute(AdminLoginConstants.KEY_LOGIN_USER);
        session.removeAttribute(AdminLoginConstants.KEY_LOGIN_USER);
    }

    /**
     * 获得HttpServletRequest对象.
     * @return HttpServletRequest对象
     */
    private HttpServletRequest findHttpRequest() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attrs.getRequest();
        return request;
    }
}
