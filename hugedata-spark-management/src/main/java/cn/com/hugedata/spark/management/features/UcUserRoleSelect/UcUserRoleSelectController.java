package cn.com.hugedata.spark.management.features.UcUserRoleSelect;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.PageEntity;
import cn.com.hugedata.spark.commons.web.AjaxResponse;
import cn.com.hugedata.spark.commons.web.features.FeatureControllerImpl;
import cn.com.hugedata.spark.commons.web.features.NullModel;

@Controller
@RequestMapping("/features/UcUserRoleSelect")
public class UcUserRoleSelectController 
        extends FeatureControllerImpl<UcUserRoleSelect, String, UcUserRoleSelectService, NullModel> {

    @Override
    public PageEntity<UcUserRoleSelect> listData(HttpServletRequest request, HttpServletResponse response, int start,
            int length, String searchParams, Locale locale) {
        start = 0;
        length = 1000;
        return super.listData(request, response, start, length, searchParams, locale);
    }

    /**
     * 保存用户角色信息.
     * @param userId 用户ID
     * @param roles  角色ID列表
     * @return       处理结果
     */
    @RequestMapping("/saveUserRoles")
    @ResponseBody
    public AjaxResponse saveUserRoles(
            @RequestParam("userId") int userId,
            @RequestParam("roles[]") List<String> roles) {
        try {
            this.service.updateUserRoles(userId, roles);
            return AjaxResponse.createSuccessResponse();
        } catch (ServiceException e) {
            return AjaxResponse.createFailResponse(e.getMessage());
        }
    }
}
