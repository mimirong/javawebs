package cn.com.hugedata.spark.management.features.UcDeptInfo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.hugedata.spark.commons.storage.entity.UcDeptInfo;
import cn.com.hugedata.spark.commons.web.AjaxResponse;
import cn.com.hugedata.spark.commons.web.features.FeatureControllerImpl;

@Controller
@RequestMapping("/features/UcDeptInfo")
public class UcDeptInfoController extends FeatureControllerImpl<UcDeptInfo, Integer, UcDeptInfoService, UcDeptInfoModel> {

    @RequestMapping("/queryAllDepartments")
    @ResponseBody
    public AjaxResponse queryAllDepartments() {
        return AjaxResponse.createSuccessResponse(service.queryAllDepartments());
    }
    
}
