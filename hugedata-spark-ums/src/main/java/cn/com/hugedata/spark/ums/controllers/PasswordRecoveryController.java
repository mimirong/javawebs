package cn.com.hugedata.spark.ums.controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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

import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.entity.UcUserVerificationSession;
import cn.com.hugedata.spark.commons.utils.CaptchaUtil;
import cn.com.hugedata.spark.commons.web.AjaxResponse;
import cn.com.hugedata.spark.commons.web.login.Login;
import cn.com.hugedata.spark.ums.services.user.PasswordRecoveryService;

/**
 * 找回密码功能.
 */
@Controller
@RequestMapping("/passwordRecovery")
public class PasswordRecoveryController {
    
    /**
     * 在Session中保存登录验证码的key.
     */
    private static final String RECOVERY_CODE_SESSION_KEY = "RECOVERY_CODE";

    @Autowired
    private PasswordRecoveryService passwordRecoveryService;
    
    /**
     * 进入重置密码页面.
     * @return ModelAndView
     */
    @RequestMapping(value = { "/", "/recover" })
    @Login(required = false)
    public ModelAndView recover() {
        ModelAndView mv = new ModelAndView("/password_recovery/recover_password");
        return mv;
    }
    
    /**
     * 发送短信或邮件验证码.
     * @param type   发送类型 email/mobile
     * @param email  发送的邮箱
     * @param mobile 发送的手机号码
     * @return       处理结果
     */
    @RequestMapping("/sendCode")
    @Login(required = false)
    @ResponseBody
    public AjaxResponse sendCode(
            @RequestParam("type") String type,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "mobile", required = false) String mobile) {
        try {
            UcUserVerificationSession session = passwordRecoveryService.sendVerificationCode(type, email, mobile);
            JSONObject data = new JSONObject();
            data.put("sessionId", session.getSessionId());
            data.put("sessionKey", session.getSessionKey());
            return AjaxResponse.createSuccessResponse(data);
        } catch (ServiceException e) {
            return AjaxResponse.createFailResponse(e.getMessage());
        }
    }

    /**
     * 重置密码.
     * @param sessionId  验证SessionID
     * @param sessionKey 验证SessionKey
     * @param password   新密码
     * @return           处理结果
     */
    @RequestMapping("/doResetPassword")
    @Login(required = false)
    @ResponseBody
    public AjaxResponse doResetPassword(
            @RequestParam("imgCode") String imgCode,
            @RequestParam("sessionId") String sessionId,
            @RequestParam("sessionKey") String sessionKey,
            @RequestParam("code") String code,
            @RequestParam("password") String password,
            HttpServletRequest request) {
        try {
            // 检查验证码
            String expectedCode = (String) request.getSession().getAttribute(RECOVERY_CODE_SESSION_KEY);
            if (expectedCode == null || StringUtils.isEmpty(imgCode) || !expectedCode.equals(imgCode.toUpperCase())) {
                return AjaxResponse.createFailResponse("图片验证码不正确", "verifyCode");
            }
            
            passwordRecoveryService.resetPassword(sessionId, sessionKey, code, password);
            return AjaxResponse.createSuccessResponse();
        } catch (ServiceException e) {
            return AjaxResponse.createFailResponse(e.getMessage());
        }
    }
    
    /**
     * 进入重置密码成功页面.
     * @return ModelAndView
     */
    @RequestMapping("success")
    @Login(required = false)
    public ModelAndView success() {
        ModelAndView mv = new ModelAndView("/password_recovery/recover_password_success");
        return mv;
    }

    /**
     * 获取登录 验证码.
     * @return
     * @throws IOException 
     */
    @RequestMapping("/viewCode")
    @ResponseBody
    @Login(required = false)
    public ResponseEntity<byte[]> viewCode(HttpServletRequest request) throws IOException {
        StringBuffer code = new StringBuffer();
        BufferedImage image = new CaptchaUtil().genRandomCodeImage(code);
        request.getSession().setAttribute(RECOVERY_CODE_SESSION_KEY, code.toString());
        
        ByteArrayOutputStream buffer = new ByteArrayOutputStream(); 
        ImageIO.write(image, "JPEG", buffer);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setCacheControl("No-Cache");
        return new ResponseEntity<byte[]>(buffer.toByteArray(), headers, HttpStatus.OK);
    }
}
