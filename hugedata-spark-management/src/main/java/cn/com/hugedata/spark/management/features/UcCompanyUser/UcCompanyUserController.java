package cn.com.hugedata.spark.management.features.UcCompanyUser;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.entity.UcUserInfo;
import cn.com.hugedata.spark.commons.web.AjaxResponse;
import cn.com.hugedata.spark.commons.web.features.FeatureControllerImpl;

/**
 * 用户帐号管理.
 */
@Controller
@RequestMapping("/features/UcCompanyUser")
public class UcCompanyUserController
        extends FeatureControllerImpl<UcUserInfo, Integer, UcCompanyUserService, UcCompanyUserModel> {

    /**
     * 设置用户是否可用.
     * @param userId     用户ID
     * @param isEnabled  是否可用
     * @return           处理结果
     */
    @RequestMapping("/setEnable")
    @ResponseBody
    public AjaxResponse setEnable(
            @RequestParam("userId") Integer userId,
            @RequestParam("enable") Boolean isEnabled) {
        try {
            service.setUserEnabled(userId, isEnabled);
            return AjaxResponse.createSuccessResponse();
        } catch (ServiceException e) {
            return AjaxResponse.createFailResponse(e.getMessage());
        }
    }

    /**
     * 发送开通邮件
     * @param userId 用户ID
     * @return       处理结果
     */
    @RequestMapping("/sendApproveMail")
    @ResponseBody
    public AjaxResponse sendApproveMail(
            @RequestParam("userId") Integer userId) {
        try {
            service.sendApproveMail(userId);
            return AjaxResponse.createSuccessResponse();
        } catch (ServiceException e) {
            return AjaxResponse.createFailResponse(e.getMessage());
        }
    }
}
