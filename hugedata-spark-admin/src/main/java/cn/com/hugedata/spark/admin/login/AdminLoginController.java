package cn.com.hugedata.spark.admin.login;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.web.AjaxResponse;

/**
 * 系统管理登录处理.
 */
@Controller
@RequestMapping("/user")
public class AdminLoginController {
    
    @Autowired
    private AdminLoginService adminLoginService;
    
    /**
     * 进入登录界面.
     * @return ModelAndView
     */
    @RequestMapping("/login")
    @Login(required = false)
    public ModelAndView login() {
        return new ModelAndView("/user/login");
    }
    
    /**
     * 登录.
     * @param username 用户名
     * @param password 密码
     * @param locale   语言
     * @return         Ajax响应
     */
    @RequestMapping("/doLogin")
    @Login(required = false)
    @ResponseBody
    public AjaxResponse doLogin(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Locale locale) {
        try {
            adminLoginService.login(username, password);
            return AjaxResponse.createSuccessResponse();
        } catch (ServiceException e) {
            return AjaxResponse.createFailResponse(e.getMessage());
        }
    }
    
    /**
     * 退出登录.
     * @return ModelAndView
     */
    @RequestMapping("/logout")
    public ModelAndView logout() {
        adminLoginService.logout();
        return new ModelAndView("redirect:/user/login.aspx");
    }
}
