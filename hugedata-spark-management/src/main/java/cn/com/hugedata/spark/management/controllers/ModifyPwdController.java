package cn.com.hugedata.spark.management.controllers;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.entity.UcUserInfo;
import cn.com.hugedata.spark.commons.utils.CaptchaUtil;
import cn.com.hugedata.spark.commons.web.AjaxResponse;
import cn.com.hugedata.spark.commons.web.login.Login;
import cn.com.hugedata.spark.commons.web.login.LoginUtils;
import cn.com.hugedata.spark.management.services.ModifyPwdService;
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

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 修改当前登录的用户的密码.
 */

@Controller
@RequestMapping("/modifyPwd")
public class ModifyPwdController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ModifyPwdController.class);

    @Autowired
    private ModifyPwdService modifyPwdService;

    /**
     * 在Session中保存修改密码验证码的key.
     */
    private static final String MODIFY_PASSWORD_CODE_SESSION_KEY = "LOGIN_CODE";

    @RequestMapping(value = {"/index", "/index/", ""})
    @Login(required = true)
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("/user/modify_password");
        return mv;
    }

    /**
     * 获取修改密码 验证码.
     * @return
     * @throws IOException
     */
    @RequestMapping("/viewLoginCode")
    @ResponseBody
    @Login(required = false)
    public ResponseEntity<byte[]> viewLoginCode(HttpServletRequest request) throws IOException {
        StringBuffer code = new StringBuffer();
        BufferedImage image = new CaptchaUtil().genRandomCodeImage(code);
        request.getSession().setAttribute(MODIFY_PASSWORD_CODE_SESSION_KEY, code.toString());

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        ImageIO.write(image, "JPEG", buffer);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setCacheControl("No-Cache");
        return new ResponseEntity<byte[]>(buffer.toByteArray(), headers, HttpStatus.OK);
    }

    /**
     * 修改密码操作.
     * @return  AJAX响应
     */
    @RequestMapping("/doModify")
    @ResponseBody
    @Login(required = true)
    public AjaxResponse doModify(
            @RequestParam("oldPassword") String oldPassword,
            @RequestParam("password") String newPassword,
            @RequestParam("imgCode") String verifyCode,
            HttpServletRequest request,
            HttpServletResponse response) {
        // 检查验证码
        String expectedCode = (String) request.getSession().getAttribute(MODIFY_PASSWORD_CODE_SESSION_KEY);
        if (expectedCode == null || StringUtils.isEmpty(verifyCode) || !expectedCode.equals(verifyCode.toUpperCase())) {
            return AjaxResponse.createFailResponse("验证码不正确", "verifyCode");
        }

        // 检查当前用户的密码是否正确
        try {
            modifyPwdService.checkPassword(oldPassword);
            UcUserInfo ucUserInfo = LoginUtils.getCurrentLogin();
            modifyPwdService.updateUserPassword(ucUserInfo, newPassword);

            return AjaxResponse.createSuccessResponse();
        } catch (ServiceException e) {
            return AjaxResponse.createFailResponse(e.getMessage());
        }
    }


}
