package cn.com.hugedata.spark.management.features.ApprovalBywindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.approval.ContentUtil;
import cn.com.hugedata.spark.commons.service.file.FileStoreService;
import cn.com.hugedata.spark.commons.service.file.FileUrlHelperService;
import cn.com.hugedata.spark.commons.storage.entity.ApServiceInfo;
import cn.com.hugedata.spark.commons.storage.entity.GaServiceGuide;
import cn.com.hugedata.spark.commons.storage.entity.UcUserInfo;
import cn.com.hugedata.spark.commons.web.AjaxResponse;
import cn.com.hugedata.spark.commons.web.features.FeatureControllerImpl;
import cn.com.hugedata.spark.commons.web.login.LoginUtils;
import cn.com.hugedata.spark.management.features.ApprovalBywindow.ApprovalBywindowService.ServiceGuideData;
import cn.com.hugedata.spark.management.services.approval.ApprovalService;

@Controller
@RequestMapping("/features/ApprovalBywindow")
public class ApprovalBywindowController
        extends FeatureControllerImpl<ApServiceInfo, Integer, ApprovalBywindowService, ApprovalBywindowModel> {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ApprovalBywindowController.class);

    private static final long MAX_FILE_SIZE = 1024 * 1024 * 20;
    
    @Autowired
    private FileStoreService fileStoreService;
    
    @Autowired
    private FileUrlHelperService fileUrlHelperService;

    @Autowired
    private ApprovalService approvalService;
    
    //==========================================================================
    // 新增
    //==========================================================================
    
    /**
     * 新增前处理；查询一个部门下的所有办事事项信息.
     */
    @RequestMapping("/prepareCreate")
    @ResponseBody
    public AjaxResponse prepareCreate(@RequestParam("deptId") String deptId) {
         List<GaServiceGuide> guideList = new ArrayList<GaServiceGuide> ();
         guideList = service.queryGuideList(Integer.parseInt(deptId));
         
         Map<String, Object> resp = new HashMap<String, Object>();
         resp.put("guideList", guideList);
         resp.put("businessNo", ContentUtil.produceRandom());
         return AjaxResponse.createSuccessResponse(resp);
    }

    /**
     * 查询一个办事事项的详细信息，包括办理材料配置.
     */
    @RequestMapping("/queryGuideDetails")
    @ResponseBody
    public AjaxResponse queryGuideDetails(@RequestParam("guideId") Integer guideId) {
        ServiceGuideData data = service.queryGuideDetails(guideId);
        return AjaxResponse.createSuccessResponse(data);
    }
    
    /**
     * 上传办理材料iframe内页.
     */
    @RequestMapping("/showUploadAtt")
    public ModelAndView showUploadAtt(
            @RequestParam("callback") String callback,
            @RequestParam("attConfigId") String attConfigId) {
        ModelAndView mv = new ModelAndView("/features/ApprovalBywindow/ApprovalBywindow_AttUpload");
        mv.addObject("callback", callback);
        mv.addObject("attConfigId", attConfigId);
        mv.addObject("isResult", false);
        mv.addObject("response", "null");
        return mv;
    }

    /**
     * 上传办理材料iframe内页提交.
     */
    @RequestMapping("/doAttUpload")
    public ModelAndView doAttUpload(
            @RequestParam("callback") String callback,
            @RequestParam("attConfigId") String attConfigId,
            @RequestParam("file") CommonsMultipartFile file) {
        try {
            if (file.getSize() > MAX_FILE_SIZE) {
                LOGGER.error("File too large: {}", file.getSize());
                throw new ServiceException("FileTooLarge", "文件大小超过限制");
            }
            
            String fileName = file.getOriginalFilename();
            if (fileName.length() > 100) {
                String ext = FilenameUtils.getExtension(fileName);
                if (ext.equals("")) {
                    fileName = fileName.substring(0, 100);
                } else {
                    fileName = fileName.substring(0, 100 - ext.length() - 1) + "." + ext;
                }
            }
            
            String fileId = fileStoreService.makeId(); 
            fileStoreService.save(fileId, file, fileName);
            
            JSONObject resp = new JSONObject();
            resp.put("fileId", fileId);
            resp.put("fileName", fileName);
            resp.put("fileUrl", fileUrlHelperService.fixDownloadUrl(fileId, file.getOriginalFilename()));

            ModelAndView mv = new ModelAndView("/features/ApprovalBywindow/ApprovalBywindow_AttUpload");
            mv.addObject("callback", callback);
            mv.addObject("attConfigId", attConfigId);
            mv.addObject("isResult", true);
            mv.addObject("isError", false);
            mv.addObject("response", JSON.toJSONString(resp));
            return mv;
            
        } catch (ServiceException e) {
            ModelAndView mv = new ModelAndView("/features/ApprovalBywindow/ApprovalBywindow_AttUpload");
            mv.addObject("callback", callback);
            mv.addObject("attConfigId", attConfigId);
            mv.addObject("isResult", true);
            mv.addObject("isError", true);
            mv.addObject("errorMessage", e.getMessage());
            mv.addObject("response", "null");
            return mv;
        }
    }

    
    //==========================================================================
    // 办理流程
    //==========================================================================

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
            @RequestParam(value = "finishDocFileId", required = false) String finishDocFileId,
            @RequestParam(value = "finishDocFileName", required = false) String finishDocFileName,
            @RequestParam(value = "ccUserId", required = false) Integer ccUserId,
            @RequestParam(value = "ccDeptId", required = false) Integer ccDeptId,
            @RequestParam("attachmentStatusJson") String attachmentStatusJson) {
        try {
            approvalService.finish(LoginUtils.getCurrentLogin(), serviceId, isApproved, remark,
                    finishDocFileId, finishDocFileName, ccDeptId, ccUserId, attachmentStatusJson);
            return AjaxResponse.createSuccessResponse();
        } catch (ServiceException e) {
            return AjaxResponse.createFailResponse(e.getMessage());
        }
    }

    
    
    //==========================================================================
    // MISC
    //==========================================================================

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
     * 打印受理通知书. 
     */
    @RequestMapping("/printAcceptNotice")
    public ModelAndView printAcceptNotice(@RequestParam("serviceId") Integer serviceId) {
        try {
            ModelAndView mv = new ModelAndView("/features/ApprovalBywindow/ApprovalBywindow_AcceptNotice");
            mv.addObject("data", service.queryForModify(serviceId));
            return mv;
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
}

