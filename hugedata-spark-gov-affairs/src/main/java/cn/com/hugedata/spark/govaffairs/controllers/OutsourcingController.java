package cn.com.hugedata.spark.govaffairs.controllers;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

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

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.PageEntity;
import cn.com.hugedata.spark.commons.service.file.FileStoreService;
import cn.com.hugedata.spark.commons.service.file.FileUrlHelperService;
import cn.com.hugedata.spark.commons.storage.entity.OsExpertsList;
import cn.com.hugedata.spark.commons.storage.entity.OsMeetingTraining;
import cn.com.hugedata.spark.commons.storage.entity.OsRequireAtt;
import cn.com.hugedata.spark.commons.storage.entity.OsRequireInfo;
import cn.com.hugedata.spark.commons.storage.entity.OsTechAchieve;
import cn.com.hugedata.spark.commons.storage.entity.PtTechImage;
import cn.com.hugedata.spark.commons.storage.entity.SpProjectImage;
import cn.com.hugedata.spark.commons.storage.entity.SpServiceProject;
import cn.com.hugedata.spark.commons.storage.entity.SysFileInfo;
import cn.com.hugedata.spark.commons.storage.entity.UcCompanyInfo;
import cn.com.hugedata.spark.commons.storage.entity.UcUserInfo;
import cn.com.hugedata.spark.commons.storage.mapper.OsRequireAttMapper;
import cn.com.hugedata.spark.commons.storage.mapper.UcCompanyInfoMapper;
import cn.com.hugedata.spark.commons.web.AjaxResponse;
import cn.com.hugedata.spark.commons.web.login.Login;
import cn.com.hugedata.spark.commons.web.login.LoginUtils;
import cn.com.hugedata.spark.govaffairs.services.DetectionApplyService;
import cn.com.hugedata.spark.govaffairs.services.OsExpertsQueryService;
import cn.com.hugedata.spark.govaffairs.services.OsMeetingTrainingQueryService;
import cn.com.hugedata.spark.govaffairs.services.OsRequireService;
import cn.com.hugedata.spark.govaffairs.services.OsTechAchieveQueryService;
import cn.com.hugedata.spark.govaffairs.services.PtTechImageQueryService;
import cn.com.hugedata.spark.govaffairs.services.SpServiceProjectService;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/outsourcing")
public class OutsourcingController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OutsourcingController.class);

    private static final long MAX_FILE_SIZE = 1024 * 1024 * 1;

    @Autowired
    private DetectionApplyService detectionApplyService;

    @Autowired
    private PtTechImageQueryService ptTechImageQueryService;
    
    @Autowired
    private FileUrlHelperService fileUrlHelperService;

    @Autowired
    private FileStoreService fileStoreService;

    @Autowired
    private SpServiceProjectService spServiceProjectService;

    @Autowired
    private OsExpertsQueryService osExpertsQueryService;

    @Autowired
    private OsTechAchieveQueryService osTechAchieveQueryService;

    @Autowired
    private OsMeetingTrainingQueryService osMeetingTrainingQueryService;
    
    @Autowired
    private OsRequireService osRequireService;
    
    @Autowired
    private UcCompanyInfoMapper ucCompanyInfoMapper; 
    
    @Autowired
    private OsRequireAttMapper osRequireAttMapper; 

    @RequestMapping(value = { "", "/", "/index" })
    @Login(required = false)
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("/spark/outsourcing/outsourcing_index2");
        mv.addObject("menuId", "100");
        mv.addObject("searchMenuId", "001");
        List<Map<String,String>> msgList = new ArrayList<Map<String,String>>();
        //
        List<SpServiceProject> s1s = spServiceProjectService.listSupply1();
        if(s1s != null && s1s.size() > 0){
            Map<String,String> s1 = new HashMap<String,String>();
            s1.put("name", s1s.get(0).getSupplyTheme());
            s1.put("url", "outsourcing/serviceProjectDetail?projectId="+s1s.get(0).getProjectId());
            msgList.add(s1);
        }
        List<SpServiceProject> s2s = spServiceProjectService.listSupply2();
        if(s2s != null && s2s.size() > 0){
            Map<String,String> s2 = new HashMap<String,String>();
            s2.put("name", s2s.get(0).getSupplyTheme());
            s2.put("url", "outsourcing/serviceProjectDetail?projectId="+s2s.get(0).getProjectId());
            msgList.add(s2);
            
        }
        
        List<OsRequireInfo> rs = osRequireService.listRequire();
        if(rs != null && rs.size() > 0){
            Map<String,String> r = new HashMap<String,String>();
            r.put("name", rs.get(0).getRequireTitle());
            r.put("url", "outsourcing/requireDetail?requireId="+rs.get(0).getRequireId());
            msgList.add(r);
            
        }
        
        PageEntity<OsMeetingTraining> ms = osMeetingTrainingQueryService.listMeetingTraining(0, 1, null, null, null);
        if(ms != null && ms.getData() != null && ms.getData().size() > 0){
            List<OsMeetingTraining> s = (List<OsMeetingTraining>) ms.getData();
            Map<String,String> r = new HashMap<String,String>();
            r.put("name", s.get(0).getName());
            r.put("url", "outsourcing/meetingTrainingDetail?trainingId="+s.get(0).getTrainingId());
            msgList.add(r);
            
        }
        
        mv.addObject("msgList",msgList);
        return mv;
    }

    @RequestMapping("/detectIndex")
    @Login(required = false)
    public ModelAndView detecIndex(){
        ModelAndView mv = new ModelAndView("/spark/outsourcing/outsourcing_detectIndex");
        mv.addObject("menuId", "201");
        return mv;
    }
    
    @RequestMapping("/requireList")
    @Login(required = false)
    public ModelAndView requireList(@RequestParam(value = "searchWord", required = false) String searchWord){
        ModelAndView mv = new ModelAndView("/spark/outsourcing/requireList");
        mv.addObject("menuId", "300");
        mv.addObject("searchMenuId", "002");
        mv.addObject("searchWord", searchWord);
        return mv;
    }

    @RequestMapping("/requireAdd")
    @Login(required = true)
    public ModelAndView requireAdd(){
        ModelAndView mv = new ModelAndView("/spark/outsourcing/requireAdd");
        mv.addObject("menuId", "300");
        mv.addObject("searchMenuId", "002");
        return mv;
    }
    
    @RequestMapping("/requireModify")
    @Login(required = true)
    public ModelAndView requireModify( @RequestParam("requireId") Integer requireId){
        ModelAndView mv = new ModelAndView("/spark/outsourcing/requireAdd");
        mv.addObject("menuId", "300");
        mv.addObject("searchMenuId", "002");
        mv.addObject("requireId",requireId);
        return mv;
    }
    
    
    
    @RequestMapping("/requireMy")
    @Login(required = true)
    public ModelAndView requireMy(){
        ModelAndView mv = new ModelAndView("/spark/outsourcing/requireMy");
        mv.addObject("menuId", "300");
        mv.addObject("searchMenuId", "002");
        return mv;
    }
    
    @RequestMapping("/requireDetail")
    @Login(required = false)
    public ModelAndView requireDetail( @RequestParam("requireId") Integer requireId){
        ModelAndView mv = new ModelAndView("/spark/outsourcing/requireDetail");
        mv.addObject("menuId", "300");
        mv.addObject("searchMenuId", "002");
       OsRequireInfo r = osRequireService.getRequireInfoDetail(requireId);
        mv.addObject("require",r);
        long restTime = r.getDeadDate().getTime() - Calendar.getInstance().getTimeInMillis();
        long restDay = restTime/(24*60*60*1000);
        long restHour = restTime%(24*60*60*1000)/(60*60*1000);
        long restMin = restTime%(24*60*60*1000)%(60*60*1000)/(60*1000);
        long restSec = restTime%(24*60*60*1000)%(60*60*1000)%(60*1000)/1000;
        mv.addObject("restDay",restDay);
        mv.addObject("restHour",restHour);
        mv.addObject("restMin",restMin);
        mv.addObject("restSec",restSec);
        //浏览量加1
        r.setReadNum(r.getReadNum() + 1);
        osRequireService.updateRequire(r);
        List<OsRequireAtt> atts = osRequireService.getRequireAtts(requireId);
        mv.addObject("atts",atts);
        for(OsRequireAtt a: atts){
            a.setFileId(fileUrlHelperService.fixDownloadUrl(a.getFileId(), a.getFileName()));
        }
        return mv;
    }
    
    @RequestMapping("/ecommIndex")
    @Login(required = false)
    public ModelAndView ecommIndex() {
        ModelAndView mv = new ModelAndView("/spark/outsourcing/outsourcing_ecommIndex");
        mv.addObject("menuId", "203");
        return mv;
    }

    @RequestMapping("/techIndex")
    @Login(required = false)
    public ModelAndView techIndex() {
        ModelAndView mv = new ModelAndView("/spark/outsourcing/outsourcing_techIndex");
        mv.addObject("menuId", "202");
        return mv;
    }

    @RequestMapping("/gaIndex")
    @Login(required = false)
    public ModelAndView gaIndex() {
        ModelAndView mv = new ModelAndView("/spark/outsourcing/outsourcing_gaIndex");
        mv.addObject("menuId", "204");
        return mv;
    }

    @RequestMapping("/serviceProject")
    @Login(required = false)
    public ModelAndView serviceProject(@RequestParam(value = "supplyType", required = false) String supplyType) {
        ModelAndView mv = new ModelAndView("/spark/outsourcing/outsourcing_serviceProject");
        mv.addObject("menuId", "200");
        mv.addObject("searchMenuId", "001");
        mv.addObject("supplyType", supplyType);
        return mv;
    }

    @RequestMapping("/serviceProjectSearch")
    @Login(required = false)
    public ModelAndView serviceProjectSearch(
            @RequestParam("searchWord") String searchWord) {
        ModelAndView mv = new ModelAndView("/spark/outsourcing/outsourcing_serviceProjectSearch");
        mv.addObject("menuId", "200");
        mv.addObject("searchMenuId", "001");
        mv.addObject("searchWord", searchWord);
        return mv;
    }

    @RequestMapping("/listServiceProject")
    @ResponseBody
    @Login(required = false)
    public PageEntity<SpServiceProject> listServiceProject(
            @RequestParam("supplyType") String supplyType,
            @RequestParam("serviceField") String serviceField,
            @RequestParam("sortType") String sortType,
            @RequestParam("start") Integer start,
            @RequestParam("length") Integer length,
            @RequestParam(value = "searchWord", required = false) String searchWord) {
        return spServiceProjectService.listData(supplyType, serviceField, sortType, searchWord, start, length);
    }

    @RequestMapping("/publishSupply")
    @Login(required = false)
    public ModelAndView publishSupply(
            @RequestParam(value = "isModify", defaultValue = "false") boolean isModify,
            @RequestParam(value = "projectId", defaultValue = "-100") Integer projectId) {
        ModelAndView mv = new ModelAndView("/spark/outsourcing/outsourcing_publishSupply");
        mv.addObject("menuId", "200");
        mv.addObject("searchMenuId", "001");
        if (isModify && projectId < 0) {
            isModify = false;
        }
        mv.addObject("isModify", isModify);
        mv.addObject("projectId", projectId);
        return mv;
    }

    @RequestMapping("/submitServiceProject")
    @ResponseBody
    @Login(required = true)
    public AjaxResponse submitServiceProject(@RequestParam("data") String dataJson) {
        try {
            spServiceProjectService.submitServiceProject(dataJson);
            return AjaxResponse.createSuccessResponse();
        } catch (ServiceException e) {
            return AjaxResponse.createFailResponse(e.getMessage());
        }
    }

    @RequestMapping("/modifyServiceProject")
    @ResponseBody
    @Login(required = true)
    public AjaxResponse modifyServiceProject(@RequestParam("data") String dataJson) {
        try {
            spServiceProjectService.modifyServiceProject(dataJson);
            return AjaxResponse.createSuccessResponse();
        } catch (ServiceException e) {
            return AjaxResponse.createFailResponse(e.getMessage());
        }
    }

    @RequestMapping("/myRelease")
    @Login(required = true)
    public ModelAndView myRelease() {
        ModelAndView mv = new ModelAndView("/spark/outsourcing/outsourcing_myRelease");
        mv.addObject("menuId", "200");
        mv.addObject("searchMenuId", "001");
        return mv;
    }

    @RequestMapping("/listMyRelease")
    @ResponseBody
    @Login(required = true)
    public PageEntity<SpServiceProject> listMyRelease(
            @RequestParam("start") Integer start,
            @RequestParam("length") Integer length) {
        return spServiceProjectService.listMyRelease(start, length);
    }
        
    
    @RequestMapping("/listSupply1")
    @ResponseBody
    @Login(required = false)
    public AjaxResponse listSupply1(
          ) {
        return AjaxResponse.createSuccessResponse(spServiceProjectService.listSupply1()); 
    }
    
    @RequestMapping("/listSupply2")
    @ResponseBody
    @Login(required = false)
    public AjaxResponse listSupply2(
          ) {
        return AjaxResponse.createSuccessResponse(spServiceProjectService.listSupply2()); 
    }
    
    @RequestMapping("/listRequire")
    @ResponseBody
    @Login(required = false)
    public AjaxResponse listRequires(){
        return AjaxResponse.createSuccessResponse(osRequireService.listRequire()); 
    }
    
    @RequestMapping("/deleteMyRelease")
    @ResponseBody
    @Login(required = true)
    public AjaxResponse deleteMyRelease(@RequestParam("projectId") Integer projectId) {
        try {
            spServiceProjectService.deleteMyRelease(projectId);
            return AjaxResponse.createSuccessResponse();
        } catch (ServiceException e) {
            return AjaxResponse.createFailResponse(e.getMessage());
        }
    }

    @RequestMapping("/serviceProjectDetail")
    @Login(required = false)
    public ModelAndView serviceProjectDetail(
            @RequestParam("projectId") Integer projectId) {
        ModelAndView mv = new ModelAndView("/spark/outsourcing/outsourcing_serviceProjectDetail");
        mv.addObject("menuId", "200");
        mv.addObject("searchMenuId", "001");
        mv.addObject("projectId", projectId);
        spServiceProjectService.viewServiceProjectDetail(projectId);
        List<SpProjectImage> images = spServiceProjectService.getServiceProjectImages(projectId);
        for(SpProjectImage image : images){
            image.setFileId(fileUrlHelperService.fixDownloadUrl(image.getFileId()));
        }
        mv.addObject("images", images);
        return mv;
    }

    @RequestMapping("/queryServiceProjectDetail")
    @ResponseBody
    @Login(required = false)
    public AjaxResponse queryServiceProjectDetail(
            @RequestParam("projectId") Integer projectId) {
        try {
            JSONObject serviceProjectData = spServiceProjectService.queryServiceProjectDetail(projectId);
            return AjaxResponse.createSuccessResponse(serviceProjectData);
        } catch (ServiceException e) {
            return AjaxResponse.createFailResponse(e.getMessage());
        }
    }

    @RequestMapping("/articleList")
    @Login(required = false)
    public ModelAndView articleList(
            @RequestParam(value = "category", defaultValue = "HUMAN_RESOURCE_1") String category) {
        ModelAndView mv = new ModelAndView("/spark/outsourcing/outsourcing_articleList");
        mv.addObject("menuId", findMenuIdFromCategoryId(category));
        mv.addObject("category", category);
        return mv;
    }
 
    @RequestMapping("/details")
    @Login(required = false)
    public ModelAndView articleDetails(
            @RequestParam("articleId") Integer articleId,
            @RequestParam("categoryId") String categoryId) {
        ModelAndView mv = new ModelAndView("/spark/outsourcing/outsourcing_articleDetails");
        mv.addObject("menuId", findMenuIdFromCategoryId(categoryId));
        mv.addObject("articleId", articleId);
        mv.addObject("categoryId", categoryId);
        return mv;
    }

    /**
     * 服务外包->成果转化->专家名录->列表
     */
    @RequestMapping("/expertsIndex")
    @Login(required = false)
    public ModelAndView expertsIndex() {
        ModelAndView mv = new ModelAndView("/spark/outsourcing/outsourcing_expertsIndex");
        mv.addObject("searchMenuId", "003");
        mv.addObject("menuId", "400");
        return mv;
    }

    /**
     * 服务外包->成果转化->专家名录->专家详情
     */
    @RequestMapping("/expertDetail")
    @Login(required = false)
    public ModelAndView expertDetail(
            @RequestParam("expertId") Integer expertId
    ) {
        ModelAndView mv = new ModelAndView("/spark/outsourcing/outsourcing_expertDetail");
        mv.addObject("searchMenuId", "003");
        mv.addObject("menuId", "400");
        mv.addObject("expertId", expertId);
        return mv;
    }

    /**
     * 服务外包->成果转化->专家名录->搜索结果
     */
    @RequestMapping("/expertsSearch")
    @Login(required = false)
    public ModelAndView expertsSearch(
            @RequestParam("searchWord") String searchWord
    ) {
        ModelAndView mv = new ModelAndView("/spark/outsourcing/outsourcing_expertsSearch");
        mv.addObject("searchMenuId", "003");
        mv.addObject("menuId", "400");
        mv.addObject("searchWord", searchWord);
        return mv;
    }

    /**
     * 服务外包->成果转化->技术成果->列表
     */
    @RequestMapping("/techAchieveIndex")
    @Login(required = false)
    public ModelAndView achieveIndex() {
        ModelAndView mv = new ModelAndView("/spark/outsourcing/outsourcing_techAchieveIndex");
        mv.addObject("searchMenuId", "004");
        mv.addObject("menuId", "400");
        return mv;
    }

    /**
     * 服务外包->成果转化->技术成果->详情
     */
    @RequestMapping("/techAchieveDetail")
    @Login(required = false)
    public ModelAndView techAchieveDetail(
            @RequestParam("achieveId") Integer achieveId
    ) {
        ModelAndView mv = new ModelAndView("/spark/outsourcing/outsourcing_techAchieveDetail");
        mv.addObject("searchMenuId", "004");
        mv.addObject("menuId", "400");
        mv.addObject("achieveId", achieveId);
        return mv;
    }

    /**
     * 服务外包->成果转化->技术成果->搜索结果
     */
    @RequestMapping("/techAchieveSearch")
    @Login(required = false)
    public ModelAndView techAchieveSearch(
            @RequestParam("searchWord") String searchWord
    ) {
        ModelAndView mv = new ModelAndView("/spark/outsourcing/outsourcing_techAchieveSearch");
        mv.addObject("searchMenuId", "004");
        mv.addObject("menuId", "400");
        mv.addObject("searchWord", searchWord);
        return mv;
    }

    /**
     * 服务外包->会议培训->列表
     */
    @RequestMapping("/meetingTrainingIndex")
    @Login(required = false)
    public ModelAndView meetingTrainingIndex(
            @RequestParam(value="searchWord", required=false) String searchWord){
        ModelAndView mv = new ModelAndView("/spark/outsourcing/outsourcing_meetingTrainingIndex");
        mv.addObject("searchMenuId", "005");
        mv.addObject("menuId", "500");
        mv.addObject("searchWord", searchWord);// 会议培训的搜索页和列表页是同一个页面
        return mv;
    }

    /**
     * 服务外包->会议培训->详情
     */
    @RequestMapping("/meetingTrainingDetail")
    @Login(required = false)
    public ModelAndView meetingTrainingDetail(
            @RequestParam("trainingId") Integer trainingId
    ) {
        ModelAndView mv = new ModelAndView("/spark/outsourcing/outsourcing_meetingTrainingDetail");
        mv.addObject("searchMenuId", "005");
        mv.addObject("menuId", "500");
        mv.addObject("trainingId", trainingId);
        return mv;
    }

    /**
     * 申请前检查是否已处于登录状态
     * @return
     */
    @RequestMapping("/applyCheck")
    @ResponseBody
    @Login(required = false)
    public AjaxResponse applyCheck(){
        try {
            UcUserInfo ucUserInfo = LoginUtils.getCurrentLogin();
            if (ucUserInfo != null) {
                return AjaxResponse.createSuccessResponse();
            } else {
                return AjaxResponse.createFailResponse("请重新登录");
            }
        } catch (Exception e) {
            return AjaxResponse.createFailResponse(e.getMessage());
        }
    }
    
    
    @RequestMapping("/doRequireModify")
    @ResponseBody
    public AjaxResponse doRequireModify(
            @RequestParam("requireId") Integer requireId,
            @RequestParam("requireArea") String requireArea,
            @RequestParam("requireTitle") String requireTitle,
            @RequestParam("deadDate") String deadDate,
            @RequestParam("offerDate") String offerDate,
            @RequestParam("hopePrice") Double hopePrice,
            @RequestParam("isChat") String isChat,
            @RequestParam("priceUnit") String priceUnit,
            @RequestParam("isQuick") String isQuick,
            @RequestParam("requireDesc") String requireDesc,
            @RequestParam("keyWord") String keyWord,
            @RequestParam("paymentMethod") String paymentMethod,
            @RequestParam("invoiceType") String invoiceType,
            @RequestParam("freightPayer") String freightPayer,
            @RequestParam("isCod") String isCod,
            @RequestParam("requireNum") Integer requireNum,
            @RequestParam("numUnit") String numUnit,
            @RequestParam("contactArea") String contactArea,
            @RequestParam("contactAddr") String contactAddr,
            @RequestParam("contacter") String contacter,
            @RequestParam("contactPhone") String contactPhone,
            @RequestParam("uploadRequireAttData") String uploadRequireAttData,
            @RequestParam("email") String email) {
        try {
           
            OsRequireInfo r =  osRequireService.getRequireInfoDetail(requireId);
            r.setRequireArea(requireArea);
            r.setRequireTitle(requireTitle);
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            r.setDeadDate(f.parse(deadDate));
            r.setOfferDate(f.parse(offerDate));
            r.setIsChat(isChat);
            r.setHopePrice(hopePrice);
            r.setPriceUnit(priceUnit);
            r.setIsQuick(isQuick);
            r.setRequireDesc(requireDesc);
            r.setKeyWord(keyWord);
            r.setPaymentMethod(paymentMethod);
            r.setInvoiceType(invoiceType);
            r.setFreightPayer(freightPayer);
            r.setIsCod(isCod);
            r.setRequireNum(requireNum);
            r.setNumUnit(numUnit);
            r.setContactArea(contactArea);
            r.setContactAddr(contactAddr);
            r.setContacter(contacter);
            r.setContactPhone(contactPhone);
            r.setEmail(email);
            r.setPublishTime(Calendar.getInstance().getTime());
            r.setUserId(LoginUtils.getCurrentLogin().getUserId());
            if(LoginUtils.getCurrentLogin().getCompanyId() != null){
              UcCompanyInfo c =  ucCompanyInfoMapper.selectById(LoginUtils.getCurrentLogin().getCompanyId());
              r.setCompanyId(c.getCompanyId());
              r.setCompanyName(c.getCompanyName());
              
            }
            osRequireService.updateRequire(r);
            List<OsRequireAtt> oldAtts = osRequireService.getRequireAtts(requireId);
            JSONArray as = JSON.parseArray(uploadRequireAttData);
           
            // 删除老的不存在的附件
            List<OsRequireAtt> deleteFile = new ArrayList<OsRequireAtt>();
                for(OsRequireAtt a : oldAtts){
                    boolean oldExist = false;
                    if(as != null && as.size() > 0){
                        for (int i = 0; i < as.size(); i++) {
                            if(a.getFileId().equals(as.getJSONObject(i).getString("fileId"))){
                                oldExist = true;
                                break;
                            }
                        }
                    }
                    if(!oldExist){
                        deleteFile.add(a);
                    }
                }
                
                for(OsRequireAtt a:deleteFile){
                    fileStoreService.deleteFile(a.getFileId());
                    osRequireAttMapper.deleteById(a.getAttId());
                }
                oldAtts.removeAll(deleteFile);  
           //插入不存在的附件
                if(as != null && as.size() > 0){
                    for (int i = 0; i < as.size(); i++) {
                        String fileId = as.getJSONObject(i).getString("fileId");
                        boolean exist = false;
                        for(OsRequireAtt a : oldAtts){
                            if(a.getFileId().equals(fileId)){
                                exist = true;
                                break;
                            }
                        }
                        if(!exist){
                            SysFileInfo fileInfo = fileStoreService.loadFileInfo(fileId);
                            OsRequireAtt ot = new OsRequireAtt();
                            ot.setFileId(fileId);
                            ot.setFileName(fileInfo.getFileName());
                            ot.setCreateTime(Calendar.getInstance().getTime());
                            ot.setRequireId(r.getRequireId());
                            osRequireAttMapper.insertSelective(ot);
                        }
                       
                    }
            }
            return AjaxResponse.createSuccessResponse();
        } 
        catch (ParseException e)
        {
            return AjaxResponse.createFailResponse(e.getMessage());
        }
    }
    
    @RequestMapping("/doRequireAdd")
    @ResponseBody
    public AjaxResponse doRequireAdd(@RequestParam("requireArea") String requireArea,
            @RequestParam("requireTitle") String requireTitle,
            @RequestParam("deadDate") String deadDate,
            @RequestParam("offerDate") String offerDate,
            @RequestParam("hopePrice") Double hopePrice,
            @RequestParam("isChat") String isChat,
            @RequestParam("priceUnit") String priceUnit,
            @RequestParam("isQuick") String isQuick,
            @RequestParam("requireDesc") String requireDesc,
            @RequestParam("keyWord") String keyWord,
            @RequestParam("paymentMethod") String paymentMethod,
            @RequestParam("invoiceType") String invoiceType,
            @RequestParam("freightPayer") String freightPayer,
            @RequestParam("isCod") String isCod,
            @RequestParam("requireNum") Integer requireNum,
            @RequestParam("numUnit") String numUnit,
            @RequestParam("contactArea") String contactArea,
            @RequestParam("contactAddr") String contactAddr,
            @RequestParam("contacter") String contacter,
            @RequestParam("uploadRequireAttData") String uploadRequireAttData,
            @RequestParam("contactPhone") String contactPhone,
            @RequestParam("email") String email) {
        try {
            OsRequireInfo r = new OsRequireInfo();
            r.setRequireArea(requireArea);
            r.setRequireTitle(requireTitle);
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            r.setDeadDate(f.parse(deadDate));
            r.setOfferDate(f.parse(offerDate));
            r.setIsChat(isChat);
            r.setHopePrice(hopePrice);
            r.setPriceUnit(priceUnit);
            r.setIsQuick(isQuick);
            r.setRequireDesc(requireDesc);
            r.setKeyWord(keyWord);
            r.setPaymentMethod(paymentMethod);
            r.setInvoiceType(invoiceType);
            r.setFreightPayer(freightPayer);
            r.setIsCod(isCod);
            r.setRequireNum(requireNum);
            r.setNumUnit(numUnit);
            r.setContactArea(contactArea);
            r.setContactAddr(contactAddr);
            r.setContacter(contacter);
            r.setContactPhone(contactPhone);
            r.setEmail(email);
            r.setPublishTime(Calendar.getInstance().getTime());
            r.setUserId(LoginUtils.getCurrentLogin().getUserId());
            if(LoginUtils.getCurrentLogin().getCompanyId() != null){
              UcCompanyInfo c =  ucCompanyInfoMapper.selectById(LoginUtils.getCurrentLogin().getCompanyId());
              r.setCompanyId(c.getCompanyId());
              r.setCompanyName(c.getCompanyName());
            }
            
            osRequireService.addRequire(r);
            JSONArray as = JSON.parseArray(uploadRequireAttData);
            if(as != null && as.size() > 0){
                for (int i = 0; i < as.size(); i++) {
                    String fileId = as.getJSONObject(i).getString("fileId");
                    SysFileInfo fileInfo = fileStoreService.loadFileInfo(fileId);
                    OsRequireAtt ot = new OsRequireAtt();
                    ot.setFileId(fileId);
                    ot.setFileName(fileInfo.getFileName());
                    ot.setCreateTime(Calendar.getInstance().getTime());
                    ot.setRequireId(r.getRequireId());
                    osRequireAttMapper.insertSelective(ot);
                }
            }
            return AjaxResponse.createSuccessResponse();
        } 
        catch (ParseException e)
        {
            return AjaxResponse.createFailResponse(e.getMessage());
        }
    }

    
    
    @RequestMapping("/deleteRequire")
    @ResponseBody
    public AjaxResponse deleteRequire(@RequestParam("requireId") Integer requireId) {
        try {
            osRequireService.deleteRequire(requireId);
            return AjaxResponse.createSuccessResponse();
        } catch (Exception e) {
            return AjaxResponse.createFailResponse(e.getMessage());
        }
    }
    /**
     * 提交申请
     * @param dataJson
     * @return
     */
    @RequestMapping("/submitApply")
    @ResponseBody
    public AjaxResponse submitApply(@RequestParam("data") String dataJson) {
        try {
            detectionApplyService.submitDetectionApply(dataJson);
            return AjaxResponse.createSuccessResponse();
        } catch (ServiceException e) {
            return AjaxResponse.createFailResponse(e.getMessage());
        }
    }

    @RequestMapping("/listTechImages")
    @ResponseBody
    @Login(required = false)
    public PageEntity<PtTechImage> listTechImages(
            @RequestParam("categoryId") String categoryId,
            @RequestParam("start") Integer start,
            @RequestParam("length") Integer length){
        return ptTechImageQueryService.listTechImages(categoryId, start, length);
    }

    @RequestMapping("/techImage")
    @Login(required = false)
    public ModelAndView techImage(@RequestParam("imageId") Integer imageId) {
        PtTechImage image = ptTechImageQueryService.queryImage(imageId);
        ModelAndView mv = new ModelAndView("/spark/outsourcing/outsourcing_techImage");
        mv.addObject("image", image);
        if (image != null) {
            mv.addObject("fileUrl", fileUrlHelperService.fixDownloadUrl(image.getFileId(), image.getFileName()));
        }
        mv.addObject("menuId", "202");
        return mv;
    }

    @RequestMapping("/listRequires")
    @ResponseBody
    @Login(required = false)
    public PageEntity<OsRequireInfo> listRequires(
            @RequestParam("start") Integer start,
            @RequestParam("length") Integer length,
            @RequestParam("orderType") String orderType,
            @RequestParam("requireArea") String requireArea,
            @RequestParam(value="searchWord", required=false) String searchWord){
        return osRequireService.listRequires(start, length, searchWord,requireArea,orderType);
    }
    
    @RequestMapping("/listMyRequires")
    @ResponseBody
    public PageEntity<OsRequireInfo> listMyRequires(
            @RequestParam("start") Integer start,
            @RequestParam("length") Integer length,
            @RequestParam(value="searchWord", required=false) String searchWord){
        return osRequireService.listMyRequires(start, length, searchWord);
    }

    @RequestMapping("/getRequire")
    @ResponseBody
    public AjaxResponse getRequire(@RequestParam("requireId") Integer requireId){
        try {
            Map<String,Object> result = new HashMap<String,Object>();
            result.put("requireInfo", osRequireService.getRequireInfoDetail(requireId));
            result.put("atts", osRequireService.getRequireAtts(requireId));
            return AjaxResponse.createSuccessResponse(result);
        } catch (Exception e) {
            return AjaxResponse.createFailResponse(e.getMessage());
        }
    }

    /**
     * 服务外包->成果转化->专家名录->列表
     */
    @RequestMapping("/listExperts")
    @ResponseBody
    @Login(required = false)
    public PageEntity<OsExpertsList> listExperts(
            @RequestParam("start") Integer start,
            @RequestParam("length") Integer length,
            @RequestParam(value="professionFieldId", required=false) String professionFieldId,
            @RequestParam(value="searchWord", required=false) String searchWord){
        return osExpertsQueryService.listExperts(start, length, professionFieldId, searchWord);
    }

    /**
     * 服务外包->成果转化->专家名录->专家详情
     */
    @RequestMapping("/getExpertDetail")
    @ResponseBody
    @Login(required = false)
    public OsExpertsList getExpertDetail(
            @RequestParam("expertId") Integer expertId){
        return osExpertsQueryService.queryExpertDetail(expertId);
    }
    

    /**
     * 服务外包->成果转化->技术成果->列表
     */
    @RequestMapping("/listTechAchieve")
    @ResponseBody
    @Login(required = false)
    public PageEntity<OsTechAchieve> listAchieve(
            @RequestParam("start") Integer start,
            @RequestParam("length") Integer length,
            @RequestParam(value="professionFieldId", required=false) String professionFieldId,
            @RequestParam(value="achieveTypeId", required=false) String achieveTypeId,
            @RequestParam(value="investmentValumeScope", required=false) String investmentValumeScope,
            @RequestParam(value="searchWord", required=false) String searchWord){
        return osTechAchieveQueryService.listTechAchieve(start, length, professionFieldId, achieveTypeId, investmentValumeScope, searchWord);
    }

    /**
     * 服务外包->成果转化->技术成果->详情
     */
    @RequestMapping("/getTechAchieveDetail")
    @ResponseBody
    @Login(required = false)
    public OsTechAchieve getTechAchieveDetail(
            @RequestParam("achieveId") Integer achieveId){
        return osTechAchieveQueryService.queryTechAchieveDetail(achieveId);
    }

    /**
     * 服务外包->会议培训->列表
     */
    @RequestMapping("/listMeetingTraining")
    @ResponseBody
    @Login(required = false)
    public PageEntity<OsMeetingTraining> listAchieve(
            @RequestParam("start") Integer start,
            @RequestParam("length") Integer length,
            @RequestParam(value="trainingType", required=false) String trainingType,
            @RequestParam(value="outOfDate", required=false) String outOfDate,
            @RequestParam(value="searchWord", required=false) String searchWord){
        return osMeetingTrainingQueryService.listMeetingTraining(start, length, trainingType, outOfDate, searchWord);
    }

    /**
     * 服务外包->会议培训->详情
     */
    @RequestMapping("/getMeetingTrainingDetail")
    @ResponseBody
    @Login(required = false)
    public OsMeetingTraining getMeetingTrainingDetail(
            @RequestParam("trainingId") Integer trainingId){
        return osMeetingTrainingQueryService.queryMeetingTrainingDetail(trainingId);
    }

    /**
     * 根据栏目ID获取当前菜单ID.
     */
    private String findMenuIdFromCategoryId(String categoryId) {
        switch (categoryId) {
            case "OS_DETEC_ACHIEVE_EXHIBIT":
            case "OS_DETEC_RESOURCE_PLATFORM":
            case "OS_DETEC_TECH_TRANSFER":
            case "OS_DETEC_TECH_TRAIN":
                return "201";
            case "HUMAN_RESOURCE_1":
            case "HUMAN_RESOURCE_2":
            case "HUMAN_RESOURCE_3":
            case "INFORMATION_COMPANIES":
            case "INFORMATION_PRODUCTS":
            case "OSINVEST_1":
            case "OSINVEST_2":
            case "INTELLECTUAL_1":
            case "INTELLECTUAL_2":
                return "202";
            case "ECOMM_BIDDINGS":
            case "ECOMM_PROJECTS":
            case "ECOMM_OUTSOURCING":
            case "ECOMM_FINANCING":
                return "203";
            case "GA_SITE":
            case "GA_FEES":
                return "901";
            default: 
                return "200";
        }
    }

    //--------------------------------------------------------------------------
    // 上传文件iframe功能
    //--------------------------------------------------------------------------
    /**
     * 上传办理材料iframe内页.
     */
    @RequestMapping("/showUploadImage")
    public ModelAndView showUploadImage(
            @RequestParam("callback") String callback) {
        ModelAndView mv = new ModelAndView("/spark/outsourcing/imageUpload");
        mv.addObject("callback", callback);
        mv.addObject("isResult", false);
        mv.addObject("response", "null");
        return mv;
    }
    
    
    @RequestMapping("/showUploadRequireAtt")
    public ModelAndView showUploadRequireAtt(
            @RequestParam("callback") String callback) {
        ModelAndView mv = new ModelAndView("/spark/outsourcing/requireAttUpload");
        mv.addObject("callback", callback);
        mv.addObject("isResult", false);
        mv.addObject("response", "null");
        return mv;
    }

    /**
     * 上传办理材料iframe内页提交.
     */
    @RequestMapping("/doRequireAttUpload")
    public ModelAndView doRequireAttUpload(
            @RequestParam("callback") String callback,
            @RequestParam("file") CommonsMultipartFile file) {
        try {
            if (file.getSize() > MAX_FILE_SIZE) {
                LOGGER.error("File too large: {}", file.getSize());
                throw new ServiceException("FileTooLarge", "文件大小超过1Mb限制");
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

            ModelAndView mv = new ModelAndView("/spark/outsourcing/requireAttUpload");
            mv.addObject("callback", callback);
            mv.addObject("isResult", true);
            mv.addObject("isError", false);
            mv.addObject("response", JSON.toJSONString(resp));
            return mv;

        } catch (ServiceException e) {
            ModelAndView mv = new ModelAndView("/spark/outsourcing/requireAttUpload");
            mv.addObject("callback", callback);
            mv.addObject("isResult", true);
            mv.addObject("isError", true);
            mv.addObject("errorMessage", e.getMessage());
            mv.addObject("response", "null");
            return mv;
        }
    }
    
    
    @RequestMapping("/doImageUpload")
    public ModelAndView doImageUpload(
            @RequestParam("callback") String callback,
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
            checkImage(fileId);

            JSONObject resp = new JSONObject();
            resp.put("fileId", fileId);
            resp.put("fileName", fileName);
            resp.put("fileUrl", fileUrlHelperService.fixDownloadUrl(fileId, file.getOriginalFilename()));

            ModelAndView mv = new ModelAndView("/spark/outsourcing/imageUpload");
            mv.addObject("callback", callback);
            mv.addObject("isResult", true);
            mv.addObject("isError", false);
            mv.addObject("response", JSON.toJSONString(resp));
            return mv;

        } catch (ServiceException e) {
            ModelAndView mv = new ModelAndView("/spark/outsourcing/imageUpload");
            mv.addObject("callback", callback);
            mv.addObject("isResult", true);
            mv.addObject("isError", true);
            mv.addObject("errorMessage", e.getMessage());
            mv.addObject("response", "null");
            return mv;
        }
    }

    /**
     * 检查图片是否符合要求
     * @param fileId
     */
    private void checkImage(String fileId) throws ServiceException {
        InputStream in = fileStoreService.openStreamForLoad(fileId);
        try {
            BufferedImage image = ImageIO.read(in);
            Integer height = image.getHeight();
            Integer width = image.getWidth();
            if (width % height != 0) {
                throw new ServiceException("FailedToUploadPicture", "图片要满足宽度与高度的比例为1:1");
            }
        } catch (Exception e) {
            LOGGER.error("Failed to upload picture.", e);
            throw new ServiceException("FailedToUploadPicture", e.getMessage(), e);
        } finally {
            close(in, "上传图片失败");
        }
    }

    public void close(InputStream in, String errorMsg) throws ServiceException {
        if (null != in) {
            try {
                in.close();
            } catch (IOException e) {
                LOGGER.error("fail to close inputStream ", e);
                throw new ServiceException("FailedToCloseInputStream ", errorMsg, e);
            }
        }
    }

}
