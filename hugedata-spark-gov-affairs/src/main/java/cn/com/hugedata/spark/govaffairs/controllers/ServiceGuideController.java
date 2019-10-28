package cn.com.hugedata.spark.govaffairs.controllers;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
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
import cn.com.hugedata.spark.commons.service.PageEntity;
import cn.com.hugedata.spark.commons.service.file.FileStoreService;
import cn.com.hugedata.spark.commons.service.file.FileUrlHelperService;
import cn.com.hugedata.spark.commons.storage.entity.ApServiceInfo;
import cn.com.hugedata.spark.commons.storage.entity.GaServiceGuide;
import cn.com.hugedata.spark.commons.web.AjaxResponse;
import cn.com.hugedata.spark.commons.web.login.Login;
import cn.com.hugedata.spark.commons.web.login.LoginUtils;
import cn.com.hugedata.spark.govaffairs.services.ServiceGuideQueryService;
import cn.com.hugedata.spark.govaffairs.services.ServiceGuideQueryService.ServiceGuideData;
import cn.com.hugedata.spark.govaffairs.services.approval.ApprovalService;
import cn.com.hugedata.spark.govaffairs.services.approval.ApprovalService.ApServiceDetails;
import cn.com.hugedata.spark.govaffairs.services.approval.BusinessApprovalService;

/**
 * 办事指南.
 */
@Controller
@RequestMapping("/serviceGuide1")
public class ServiceGuideController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceGuideController.class);

    private static final long MAX_FILE_SIZE = 1024 * 1024 * 20;
    
	@Autowired
	private ServiceGuideQueryService serviceGuideQueryService;

	@Autowired
	private ApprovalService approvalService;
	
	@Autowired
	private BusinessApprovalService businessApprovalService;

	@Autowired
	private FileStoreService fileStoreService;
	
	@Autowired
	private FileUrlHelperService fileUrlHelperService; 

    /**
     * 办事指南列表页.
     */
	@RequestMapping("/list")
	@Login(required = false)
	public ModelAndView list(@RequestParam(value = "dept", defaultValue = "7") String deptCode) {
		ModelAndView mv = new ModelAndView("/spark/serviceGuide/list");
        mv.addObject("menuId", "2");
		mv.addObject("deptCode", deptCode);
		return mv;
	}

	/**
	 * 办事指南列表页查询数据.
	 */
	@RequestMapping("/listData")
	@ResponseBody
	@Login(required = false)
	public PageEntity<GaServiceGuide> listData(@RequestParam(value = "dept", defaultValue = "1") String deptCode,
			@RequestParam("start") Integer start, @RequestParam("length") Integer length) {
		return serviceGuideQueryService.list(deptCode, start, length);
	}

    /**
     * 详情页-办事指南页面.
     */
	@RequestMapping(value = { "/details", "/details1" })
	@Login(required = false)
	public ModelAndView details1(@RequestParam(value = "guideId") Integer guideId) {
		ServiceGuideData data = serviceGuideQueryService.queryServiceGuideData(guideId);
		ModelAndView mv = new ModelAndView("/spark/serviceGuide/details1");
        mv.addObject("menuId", "2");
		mv.addObject("guideId", guideId);
		mv.addObject("data", JSON.toJSONString(data));
		return mv;
	}

    /**
     * 详情页-办事流程页面.
     */
	@RequestMapping("/details2")
	@Login(required = false)
	public ModelAndView details2(@RequestParam(value = "guideId") Integer guideId) {
		ServiceGuideData data = serviceGuideQueryService.queryServiceGuideData(guideId);
		ModelAndView mv = new ModelAndView("/spark/serviceGuide/details2");
        mv.addObject("menuId", "2");
		mv.addObject("guideId", guideId);
		mv.addObject("data", JSON.toJSONString(data));
		return mv;
	}

	/**
	 * 详情页-表格下载页面.
	 */
	@RequestMapping("/details3")
	@Login(required = false)
	public ModelAndView details3(@RequestParam(value = "guideId") Integer guideId) {
		ServiceGuideData data = serviceGuideQueryService.queryServiceGuideData(guideId);
		ModelAndView mv = new ModelAndView("/spark/serviceGuide/details3");
        mv.addObject("menuId", "2");
		mv.addObject("guideId", guideId);
		mv.addObject("data", JSON.toJSONString(data));
		return mv;
	}

	/**
	 * 在线办理页面.
	 */
	@RequestMapping("/onlineTransact")
	public ModelAndView onlineTransact(@RequestParam(value = "guideId") Integer guideId) {
		ServiceGuideData data = serviceGuideQueryService.queryServiceGuideData(guideId);
		
		ModelAndView mv = new ModelAndView("/spark/serviceGuide/onlineTransact");
		mv.addObject("menuId", "2");
		mv.addObject("guideId", guideId);
		mv.addObject("guide", data.getGuide());
		mv.addObject("attConfigs", data.getAttConfigs());
		mv.addObject("data", JSON.toJSONString(data));
		return mv;
	}
    
	/**
	 * 在线办理提交.
	 */
    @RequestMapping("/submitOnLineBusiness")
    @ResponseBody
    public AjaxResponse submitOnLineBusiness(
            @RequestParam("guideId") int guideId, 
            @RequestParam("businessDeptPerson") String businessDeptPerson,
            @RequestParam("cellphone") String cellphone,
            @RequestParam("attachmentsJson") String attachmentsJson) {
        try {
            businessApprovalService.createService(guideId, businessDeptPerson, cellphone, attachmentsJson);
            return AjaxResponse.createSuccessResponse();
        } catch (ServiceException e) {
            return AjaxResponse.createFailResponse(e.getMessage());
        }
    }

	@RequestMapping("/serviceShow")
	@Login(required = false)
	public ModelAndView serviceShow() {
		ModelAndView mv = new ModelAndView("/spark/serviceGuide/serviceShow");
		mv.addObject("menuId", "1");
		return mv;
	}

	@RequestMapping("/serviceShowlistData")
	@ResponseBody
	@Login(required = false)
	public PageEntity<ApServiceInfo> serviceShowlistData(
			@RequestParam(value = "dept", defaultValue = "1") String deptCode, @RequestParam("start") Integer start,
			@RequestParam("length") Integer length,
			@RequestParam(value = "businessNo", defaultValue = "") String businessNo,
			@RequestParam(value = "guidename", defaultValue = "") String guidename,
			@RequestParam(value = "businessDetpOrPerson", defaultValue = "") String businessDetpOrPerson) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(businessNo)) {
			queryMap.put("like_businessNo", businessNo);
		}
		if (StringUtils.isNotEmpty(guidename)) {
			queryMap.put("like_guidename", guidename);
		}
		if (StringUtils.isNotEmpty(businessDetpOrPerson)) {
			queryMap.put("like_businessDeptPerson", businessDetpOrPerson);
		}
		return approvalService.list(queryMap, start, length);
	}

	/**
	 * 我的办件.
	 */
    @RequestMapping("/serviceListMy")
    public ModelAndView serviceListMy() {
        ModelAndView mv = new ModelAndView("/spark/serviceGuide/serviceListMy");
        mv.addObject("menuId", "1");
        return mv;
    }

    /**
     * 我的办件数据.
     */
    @RequestMapping("/serviceListMyData")
    @ResponseBody
    @Login(required = false)
    public PageEntity<ApServiceInfo> serviceListMyData(
            @RequestParam(value = "dept", defaultValue = "1") String deptCode,
            @RequestParam("start") Integer start,
            @RequestParam("length") Integer length,
            @RequestParam(value = "businessNo", defaultValue = "") String businessNo,
            @RequestParam(value = "guidename", defaultValue = "") String guidename,
            @RequestParam(value = "businessDetpOrPerson", defaultValue = "") String businessDetpOrPerson) {
        Map<String, Object> queryMap = new HashMap<String, Object>();
        if (StringUtils.isNotEmpty(businessNo)) {
            queryMap.put("like_businessNo", businessNo);
        }
        if (StringUtils.isNotEmpty(guidename)) {
            queryMap.put("like_guidename", guidename);
        }
        if (StringUtils.isNotEmpty(businessDetpOrPerson)) {
            queryMap.put("like_businessDeptPerson", businessDetpOrPerson);
        }
        queryMap.put(ApServiceInfo.Fields.CREATE_USER_ID, "" + LoginUtils.getCurrentLogin().getUserId());
        return approvalService.list(queryMap, start, length);
    }

	/**
	 * 办件详情页.
	 */
	@RequestMapping("/serviceDetail")
	@Login(required = false)
	public ModelAndView serviceDetail(@RequestParam(value = "serviceId") Integer serviceId) {
		ModelAndView mv = new ModelAndView("/spark/serviceGuide/serviceDetail");
		mv.addObject("serviceId", serviceId);
		mv.addObject("menuId", "1");
		return mv;
	}
	
	/**
	 * 办件详情页查询数据
	 * @param serviceId
	 * @return
	 */
	@RequestMapping("/serviceShowDeatil")
	@ResponseBody
	@Login(required = false)
	public AjaxResponse serviceShowlistData(
			@RequestParam(value = "serviceId") Integer serviceId){
	    try {
    	    ApServiceDetails details = approvalService.queryServiceDetails(serviceId);
    	    return AjaxResponse.createSuccessResponse(details);
	    } catch (ServiceException e) {
	        return AjaxResponse.createFailResponse(e.getMessage());
	    }
	}

    /**
     * 撤销办事.
     */
	@RequestMapping("/cancelService")
	@ResponseBody
	public AjaxResponse cancelService(
            @RequestParam(value = "serviceId") Integer serviceId) {
	    try {
	        businessApprovalService.cancelService(LoginUtils.getCurrentLogin(), serviceId);
	        return AjaxResponse.createSuccessResponse();
	    } catch (ServiceException e) {
	        return AjaxResponse.createFailResponse(e.getMessage());
	    }
	}
	
	//--------------------------------------------------------------------------
	// 上传文件iframe功能
    //--------------------------------------------------------------------------
	
	/**
	 * 上传办理材料iframe内页.
	 */
	@RequestMapping("/showUploadAtt")
	public ModelAndView showUploadAtt(
	        @RequestParam("callback") String callback,
	        @RequestParam("attConfigId") String attConfigId) {
        ModelAndView mv = new ModelAndView("/spark/serviceGuide/attUpload");
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

            ModelAndView mv = new ModelAndView("/spark/serviceGuide/attUpload");
            mv.addObject("callback", callback);
            mv.addObject("attConfigId", attConfigId);
            mv.addObject("isResult", true);
            mv.addObject("isError", false);
            mv.addObject("response", JSON.toJSONString(resp));
            return mv;
            
        } catch (ServiceException e) {
            ModelAndView mv = new ModelAndView("/spark/serviceGuide/attUpload");
            mv.addObject("callback", callback);
            mv.addObject("attConfigId", attConfigId);
            mv.addObject("isResult", true);
            mv.addObject("isError", true);
            mv.addObject("errorMessage", e.getMessage());
            mv.addObject("response", "null");
            return mv;
        }
	}
}
