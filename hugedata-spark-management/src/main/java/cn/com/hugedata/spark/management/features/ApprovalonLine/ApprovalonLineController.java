package cn.com.hugedata.spark.management.features.ApprovalonLine;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.entity.ApServiceInfo;
import cn.com.hugedata.spark.commons.storage.entity.UcUserInfo;
import cn.com.hugedata.spark.commons.web.AjaxResponse;
import cn.com.hugedata.spark.commons.web.features.FeatureControllerImpl;
import cn.com.hugedata.spark.commons.web.login.LoginUtils;
import cn.com.hugedata.spark.management.services.approval.ApprovalService;


@Controller
@RequestMapping("/features/ApprovalonLine")
public class ApprovalonLineController
        extends FeatureControllerImpl<ApServiceInfo, Integer, ApprovalonLineService, ApprovalonLineModel> {
	
    @Autowired
    private ApprovalService approvalService;
    
    /**
     * 查询一个部门下的用户.
     */
    @RequestMapping("/queryDeptUsers")
    @ResponseBody
    public AjaxResponse queryDeptUsers(
            @RequestParam("deptCode") String deptCode,
            @RequestParam("serviceRole") String serviceRole) {
        List<UcUserInfo> users = service.queryDeptUsers(deptCode, serviceRole);
        List<JSONObject> objlist = new ArrayList<>();
        for (UcUserInfo u : users) {
            JSONObject obj = new JSONObject();
            obj.put("userId", u.getUserId());
            obj.put("name", u.getName());
            objlist.add(obj);
        }
        return AjaxResponse.createSuccessResponse(objlist);
    }
    
    /**
     * 下一步(deliver)操作.
     */
    @RequestMapping("/deliver")
    @ResponseBody
    public AjaxResponse deliver(
            @RequestParam("serviceId") Integer serviceId,
            @RequestParam("remark") String remark,
            @RequestParam("nextUserId") Integer nextUserId,
            @RequestParam("nextDeptId") Integer nextDeptId,
            @RequestParam("attachmentStatusJson") String attachmentStatusJson) {
        try {
            approvalService.deliver(LoginUtils.getCurrentLogin(), serviceId, remark, nextUserId, nextDeptId, attachmentStatusJson);
            return AjaxResponse.createSuccessResponse();
        } catch (ServiceException e) {
            return AjaxResponse.createFailResponse(e.getMessage());
        }
    }
    
    /**
     * 审批不通过(reject)操作.
     */
    @RequestMapping("/reject")
    @ResponseBody
    public AjaxResponse reject(
            @RequestParam("serviceId") Integer serviceId,
            @RequestParam("remark") String remark,
            @RequestParam("attachmentStatusJson") String attachmentStatusJson) {
        try {
            approvalService.reject(LoginUtils.getCurrentLogin(), serviceId, remark, attachmentStatusJson);
            return AjaxResponse.createSuccessResponse();
        } catch (ServiceException e) {
            return AjaxResponse.createFailResponse(e.getMessage());
        }
    }
    
    /**
     * 审批通过(approve)操作.
     */
    @RequestMapping("/approve")
    @ResponseBody
    public AjaxResponse approve(
            @RequestParam("serviceId") Integer serviceId,
            @RequestParam("remark") String remark,
            @RequestParam("attachmentStatusJson") String attachmentStatusJson) {
        try {
            approvalService.approve(LoginUtils.getCurrentLogin(), serviceId, remark, attachmentStatusJson);
            return AjaxResponse.createSuccessResponse();
        } catch (ServiceException e) {
            return AjaxResponse.createFailResponse(e.getMessage());
        }
    }
    
    /**
     * 确认办结/废弃(finish)操作.
     */
    @RequestMapping("/finish")
    @ResponseBody
    public AjaxResponse finish(
            @RequestParam("serviceId") Integer serviceId,
            @RequestParam("isApproved") Boolean isApproved,
            @RequestParam("remark") String remark,
            @RequestParam(value = "ccUserId", required = false) Integer ccUserId,
            @RequestParam(value = "ccDeptId", required = false) Integer ccDeptId,
            @RequestParam(value = "finishDocFileId", required = false) String finishDocFileId,
            @RequestParam(value = "finishDocFileName", required = false) String finishDocFileName,
            @RequestParam("attachmentStatusJson") String attachmentStatusJson) {
        try {
            approvalService.finish(LoginUtils.getCurrentLogin(), serviceId, isApproved, remark,
                    finishDocFileId, finishDocFileName, ccDeptId, ccUserId, attachmentStatusJson);
            return AjaxResponse.createSuccessResponse();
        } catch (ServiceException e) {
            return AjaxResponse.createFailResponse(e.getMessage());
        }
    }
    
    
    //----------------
    // delete below
	
	@RequestMapping("/openModify")
	@ResponseBody
	public AjaxResponse openModify(
			HttpServletRequest request, 
			HttpServletResponse response,
			Locale locale) {
		UcUserInfo user = LoginUtils.getCurrentLogin();
		Map<String, Object> returnMap = new HashMap<String, Object>();
		String acceptUserName = user.getName();
        String acceptWindowName = user.getWindowName();
        Date acceptTime = new Date();
        String acceptCellphone = user.getMobile();
        returnMap.put(ApServiceInfo.Fields.ACCEPT_USER_NAME, acceptUserName);
        returnMap.put(ApServiceInfo.Fields.ACCEPT_WINDOW_NAME, acceptWindowName);
        returnMap.put(ApServiceInfo.Fields.ACCEPT_TIME, acceptTime);
        returnMap.put(ApServiceInfo.Fields.ACCEPT_CELLPHONE, acceptCellphone);
		return AjaxResponse.createSuccessResponse(returnMap);
	}
	
	@RequestMapping("/detailData")
	@ResponseBody
	public ApServiceInfo detailData(
			HttpServletRequest request, 
			HttpServletResponse response,
			@RequestParam("serviceId") int serviceId,
			Locale locale) {
		return service.selectById(serviceId);
	}
}
