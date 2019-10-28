package cn.com.hugedata.spark.ums.controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.UrlManager;
import cn.com.hugedata.spark.commons.utils.CaptchaUtil;
import cn.com.hugedata.spark.commons.web.AjaxResponse;
import cn.com.hugedata.spark.commons.web.login.Login;
import cn.com.hugedata.spark.commons.web.login.LoginConstants;
import cn.com.hugedata.spark.commons.web.login.LoginUtils;
import cn.com.hugedata.spark.ums.services.login.LoginService;
import cn.com.hugedata.spark.ums.services.login.LoginService.LoginResult;

@Controller
public class LoginController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    
    /**
     * 在Session中保存登录验证码的key.
     */
    private static final String LOGIN_CODE_SESSION_KEY = "LOGIN_CODE";
    
    @Autowired
    private LoginService loginService;
    
    @Autowired
    private UrlManager urlManager;
    
    /**
     * 进入登录页面.
     * @param forceLogin 是否强制重新登录，如果为false并且用户已经登录，将直接跳转到登录成功页面.
     * @param redirect   登录成功后的跳转
     * @return           ModelAndView
     */
    @RequestMapping(value = { "/", "/login", "/login/" })
    @Login(required = false)
    public ModelAndView login(
            @RequestParam(value = "forceLogin", defaultValue = "false") boolean forceLogin,
            @RequestParam(value = "redirect", required = false) String redirect) {
        
        if (LoginUtils.isLoggedIn() && !forceLogin && StringUtils.isNotEmpty(redirect)) {
            LOGGER.info("Already logged in. redirecting to: {}", redirect);
            ModelAndView mv = new ModelAndView("redirect:" + redirect);
            mv.addObject("login", LoginUtils.getCurrentLoginToken());
            return mv;
        }
        
        if (StringUtils.isEmpty(redirect)) {
            redirect = urlManager.getGovAffairsPortalUrl();
        }
        
        ModelAndView mv = new ModelAndView("/login/login");
        mv.addObject("redirect", redirect);
        return mv;
    }
    
    /**
     * 登录操作.
     * @return AJAX响应
     */
    @RequestMapping("/login/doLogin")
    @ResponseBody
    @Login(required = false)
    public AjaxResponse doLogin(
            @RequestParam("username") String loginName,
            @RequestParam("password") String password,
            @RequestParam("imgCode") String verifyCode,
            HttpServletRequest request,
            HttpServletResponse response) {
        // 检查验证码
        String expectedCode = (String) request.getSession().getAttribute(LOGIN_CODE_SESSION_KEY);
        if (expectedCode == null || StringUtils.isEmpty(verifyCode) || !expectedCode.equals(verifyCode.toUpperCase())) {
            return AjaxResponse.createFailResponse("验证码不正确", "verifyCode");
        }
        
        // 登录操作
        try {
            LoginResult result = loginService.login(loginName, password);
            
            Cookie cookie = new Cookie(LoginConstants.LOGIN_TOKEN_COOKIE_KEY, result.getToken());
            cookie.setPath(request.getContextPath());
            response.addCookie(cookie);
            
            return AjaxResponse.createSuccessResponse(result);
        } catch (ServiceException e) {
            return AjaxResponse.createFailResponse(e.getMessage());
        }
    }
    
    /**
     * 获取登录 验证码.
     * @return
     * @throws IOException 
     */
    @RequestMapping("/login/viewLoginCode")
    @ResponseBody
    @Login(required = false)
    public ResponseEntity<byte[]> viewLoginCode(HttpServletRequest request) throws IOException {
        StringBuffer code = new StringBuffer();
        BufferedImage image = new CaptchaUtil().genRandomCodeImage(code);
        request.getSession().setAttribute(LOGIN_CODE_SESSION_KEY, code.toString());
        
        ByteArrayOutputStream buffer = new ByteArrayOutputStream(); 
        ImageIO.write(image, "JPEG", buffer);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setCacheControl("No-Cache");
        return new ResponseEntity<byte[]>(buffer.toByteArray(), headers, HttpStatus.OK);
    }
    
    /**
     * 推出登录，并跳转到指定URL.
     * @return ModelAndView
     */
    @RequestMapping(value = { "/logout", "/login/logout" })
    public ModelAndView logout(@RequestParam(value = "redirect", required = false) String redirect) {
        if (StringUtils.isEmpty(redirect)) {
            redirect = urlManager.getGovAffairsPortalUrl();
        }

        loginService.logout();
        
        ModelAndView mv = new ModelAndView("redirect:" + redirect);
        return mv;
    }
}
