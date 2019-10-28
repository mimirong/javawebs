package cn.com.hugedata.spark.management.features.UcManageRole;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.entity.UcManageRole;
import cn.com.hugedata.spark.commons.web.AjaxResponse;
import cn.com.hugedata.spark.commons.web.ErrorPageBuilder;
import cn.com.hugedata.spark.commons.web.features.FeatureControllerImpl;

@Controller
@RequestMapping("/features/UcManageRole")
public class UcManageRoleController extends FeatureControllerImpl<UcManageRole, String, UcManageRoleService, UcManageRoleModel> {

    @RequestMapping("/toSelectRights")
    public ModelAndView toSelectRights(@RequestParam("roleId") String roleId) {
        try {
            UcManageRole role = service.queryRole(roleId);
            
            ModelAndView mv = new ModelAndView("/features/" + getFeatureName() + "/" + getFeatureName() + "_SelectRights");
            mv.addObject("roleId", roleId);
            mv.addObject("role", role);
            mv.addObject("roleJson", JSON.toJSONString(role));
            mv.addObject("allRightsJson", JSON.toJSONString(this.service.queryAllRights()));
            mv.addObject("selectedRightsJson", JSON.toJSONString(this.service.querySelectedRights(roleId)));
            return mv;
        } catch (ServiceException e) {
            return new ErrorPageBuilder().setErrorMessageText("角色不存在").create();
        }
    }

    /**
     * 保存权限信息.
     * @param roleId
     * @param rightId
     * @return
     */
    @RequestMapping("/doSelectRights")
    @ResponseBody
    public AjaxResponse doSelectRights(
            @RequestParam("roleId") String roleId,
            @RequestParam("selectedRights[]") List<String> rights) {
        try {
            this.service.saveRightsForRole(roleId, rights);
            return AjaxResponse.createSuccessResponse();
        } catch (ServiceException e) {
            return AjaxResponse.createFailResponse(e.getMessage());
        }
    }
}
