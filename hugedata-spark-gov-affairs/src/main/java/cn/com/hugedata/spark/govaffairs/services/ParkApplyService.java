/**  
* @Title:  ParkApplyService.java
* @Package cn.com.hugedata.spark.govaffairs.services
* @Description: TODO(用一句话描述该文件做什么)
* @author QianQingzhao
* @date  2017年1月13日 下午1:33:30
* @version V1.0  
* Update Logs:
* ****************************************************
* Name:
* Date:
* Description:
******************************************************
*/
package cn.com.hugedata.spark.govaffairs.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.file.FileStoreService;
import cn.com.hugedata.spark.commons.service.file.FileUrlHelperService;
import cn.com.hugedata.spark.commons.storage.constant.GaParkJoinApplicationConstants;
import cn.com.hugedata.spark.commons.storage.constant.GaParkQuitApplicationConstants;
import cn.com.hugedata.spark.commons.storage.entity.GaParkJoinApplication;
import cn.com.hugedata.spark.commons.storage.entity.GaParkJoinGuide;
import cn.com.hugedata.spark.commons.storage.entity.GaParkQuitApplication;
import cn.com.hugedata.spark.commons.storage.entity.GaParkQuitGuide;
import cn.com.hugedata.spark.commons.storage.entity.SysFileInfo;
import cn.com.hugedata.spark.commons.storage.entity.UcUserInfo;
import cn.com.hugedata.spark.commons.storage.mapper.GaParkJoinApplicationMapper;
import cn.com.hugedata.spark.commons.storage.mapper.GaParkJoinGuideMapper;
import cn.com.hugedata.spark.commons.storage.mapper.GaParkQuitApplicationMapper;
import cn.com.hugedata.spark.commons.storage.mapper.GaParkQuitGuideMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.commons.web.login.LoginUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * @ClassName: ParkApplyService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author QianQingzhao
 * @date 2017年1月13日 下午1:33:30
 *
 */
@Service
public class ParkApplyService
{
    @Autowired
    private GaParkJoinApplicationMapper gaParkJoinApplicationMapper;
    
    @Autowired
    private GaParkQuitApplicationMapper gaParkQuitApplicationMapper;
    
    @Autowired
    private GaParkJoinGuideMapper gaParkJoinGuideMapper;
    
    @Autowired
    private GaParkQuitGuideMapper gaParkQuitGuideMapper;
    
    @Autowired
    private FileStoreService fileStoreService;
    
    @Autowired
    private FileUrlHelperService fileUrlHelperService;
    
    public void addApplyIn(String companyName,
            CommonsMultipartFile bp,
            CommonsMultipartFile op,
            CommonsMultipartFile dtp,
            CommonsMultipartFile ctp,
            CommonsMultipartFile applyFile,
            String contacter,
            String mobile) throws ServiceException {
        
       String bpId = fileStoreService.save(bp);
       String opId = fileStoreService.save(op);
       String dtpId = fileStoreService.save(dtp);
       String ctpId = fileStoreService.save(ctp);
       String applyFileId = fileStoreService.save(applyFile);
       JSONObject jo = new JSONObject();
       jo.put("bpId", bpId);
       jo.put("opId", opId);
       jo.put("dtpId", dtpId);
       jo.put("ctpId", ctpId);
       jo.put("applyFileId", applyFileId);
        GaParkJoinApplication obj = new GaParkJoinApplication();
        obj.setCompanyName(companyName);
        obj.setContact(contacter);
        obj.setContactMobile(mobile);
        obj.setApplicationForm(applyFileId);
        obj.setContent(jo.toJSONString());
        obj.setStatus(GaParkJoinApplicationConstants.STATUS_CREATED);
        UcUserInfo uc = LoginUtils.getCurrentLogin();
        obj.setApplierUserId(uc.getUserId());
        obj.setApplierName(uc.getLoginName());
        obj.setCreateTime(Calendar.getInstance().getTime());
        gaParkJoinApplicationMapper.insertSelective(obj);
        
    }

    /**
     * 删除入园申请.
     * @param applyId applyId
     */
    public void deleteApplyIn(int applyId) throws ServiceException {
        GaParkJoinApplication apply = gaParkJoinApplicationMapper.selectById(applyId);
        if (apply == null) {
            throw new ServiceException(getClass().getSimpleName() + "-ApplyIdNotFound", "申请信息不存在");
        }

        if (!GaParkJoinApplication.STATUS_CREATED.equals(apply.getStatus())) {
            throw new ServiceException(getClass().getSimpleName() + "-InvalidStatusToDelete", "只能删除未审批的申请");
        }

        gaParkJoinApplicationMapper.deleteById(applyId);
    }
    
    public void addApplyOut(String companyName,
            String quitTime,
            CommonsMultipartFile applyFile,
            String contacter,
            String mobile) throws ServiceException {
        
       String applyFileId = fileStoreService.save(applyFile);
        GaParkQuitApplication obj = new GaParkQuitApplication();
        obj.setCompanyName(companyName);
        obj.setContact(contacter);
        obj.setContactMobile(mobile);
        obj.setApplicationForm(applyFileId);
        obj.setStatus(GaParkQuitApplicationConstants.STATUS_CREATED);
        UcUserInfo uc = LoginUtils.getCurrentLogin();
        obj.setApplierUserId(uc.getUserId());
        obj.setApplierName(uc.getLoginName());
        obj.setCreateTime(Calendar.getInstance().getTime());
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            Date d = s.parse(quitTime);
            obj.setQuitTime(d);
        }
        catch (ParseException e)
        {
            throw new ServiceException(".ParkApplyService.addApplyOut",
                    "退园时间日期格式解析有误");
        }
        gaParkQuitApplicationMapper.insertSelective(obj);
        
        
    }

    /**
     * 删除退园申请.
     * @param applyId applyId
     */
    public void deleteApplyOut(int applyId) throws ServiceException {
        GaParkQuitApplication apply = gaParkQuitApplicationMapper.selectById(applyId);
        if (apply == null) {
            throw new ServiceException(getClass().getSimpleName() + "-ApplyIdNotFound", "申请信息不存在");
        }

        if (!GaParkQuitApplication.STATUS_CREATED.equals(apply.getStatus())) {
            throw new ServiceException(getClass().getSimpleName() + "-InvalidStatusToDelete", "只能删除未审批的申请");
        }

        gaParkQuitApplicationMapper.deleteById(applyId);
    }

    public GaParkQuitApplication qryMyApplyOut(){
        Map<String, Object> query = MyBatisUtils.buildParameterMap();
        query.put(GaParkQuitApplication.Fields.APPLIER_USER_ID, LoginUtils.getCurrentLogin().getUserId());
        List<GaParkQuitApplication> gs =gaParkQuitApplicationMapper.selectByMap(query);
        
        if(gs != null && gs.size() > 0){
            return gs.get(0);
        }else{
            return null;
        }
    }
    
    public GaParkJoinApplication qryMyApplyIn(){
        Map<String, Object> query = MyBatisUtils.buildParameterMap();
        query.put(GaParkJoinApplication.Fields.APPLIER_USER_ID, LoginUtils.getCurrentLogin().getUserId());
        List<GaParkJoinApplication> gs =gaParkJoinApplicationMapper.selectByMap(query);
        if(gs != null && gs.size() > 0){
            return gs.get(0);
        }else{
            return null;
        }
    }
    
    public Map<String,String> getMyApplyInUploadFileInfo(String contentString){
        JSONObject jo = JSONObject.parseObject(contentString);
        Map<String,String> fileInfo = new HashMap<String,String>();
        SysFileInfo bpInfo = fileStoreService.loadFileInfo(jo.getString("bpId"));
        fileInfo.put("bpId", fileUrlHelperService.fixDownloadUrl(jo.getString("bpId")));
        fileInfo.put("bpName", bpInfo.getFileName());
        
        SysFileInfo opInfo = fileStoreService.loadFileInfo(jo.getString("opId"));
        fileInfo.put("opId", fileUrlHelperService.fixDownloadUrl(jo.getString("opId")));
        fileInfo.put("opName", opInfo.getFileName());
        
        SysFileInfo dtpInfo = fileStoreService.loadFileInfo(jo.getString("dtpId"));
        fileInfo.put("dtpId", fileUrlHelperService.fixDownloadUrl(jo.getString("dtpId")));
        fileInfo.put("dtpName", dtpInfo.getFileName());
        
        SysFileInfo ctpInfo = fileStoreService.loadFileInfo(jo.getString("ctpId"));
        fileInfo.put("ctpId", fileUrlHelperService.fixDownloadUrl(jo.getString("ctpId")));
        fileInfo.put("ctpName", ctpInfo.getFileName());
        
        SysFileInfo applyFileInfo = fileStoreService.loadFileInfo(jo.getString("applyFileId"));
        fileInfo.put("applyFileId", fileUrlHelperService.fixDownloadUrl(jo.getString("applyFileId")));
        fileInfo.put("applyFileName", applyFileInfo.getFileName());
        
        
        return fileInfo;
    }
    
    public Map<String,String> getMyApplyOutUploadFileInfo(String fileId){
        
        Map<String,String> fileInfo = new HashMap<String,String>();
        SysFileInfo fileOut = fileStoreService.loadFileInfo(fileId);
        fileInfo.put("url", fileUrlHelperService.fixDownloadUrl(fileId));
        fileInfo.put("name", fileOut.getFileName());
        return fileInfo;
    }

    /**
    * @Title: getApplyInGuides
    * @Description: 
    * @author QianQingzhao
    * @return
    * @throws
    */
    public List<GaParkJoinGuide> getApplyInGuides () throws ServiceException
    {
        List<GaParkJoinGuide> gs = gaParkJoinGuideMapper.selectByMap(null);
        for(GaParkJoinGuide g:gs){
            g.setBrief(fileUrlHelperService.fixDownloadUrl(g.getAttachmentFileId(),  g.getAttachmentFileName()));
            g.setAttachmentFileName(fileStoreService.loadFileInfo(g.getAttachmentFileId()).getFileName());
        }
        return gs;
    }
    
    public List<GaParkQuitGuide> getApplyOutGuides () throws ServiceException
    {
        List<GaParkQuitGuide> gs = gaParkQuitGuideMapper.selectByMap(null);
        for(GaParkQuitGuide g:gs){
            g.setBrief(fileUrlHelperService.fixDownloadUrl(g.getAttachmentFileId(), g.getAttachmentFileName()));
            g.setAttachmentFileName(fileStoreService.loadFileInfo(g.getAttachmentFileId()).getFileName());
        }
        return gs;
    }

    /**
    * @Title: getApplyInGuide
    * @Description: 
    * @author QianQingzhao
    * @param guideId
    * @return
    * @throws
    */
    public GaParkJoinGuide getApplyInGuide(Integer guideId)  throws ServiceException
    {
        
        return gaParkJoinGuideMapper.selectById(guideId);
    }
    
    public GaParkQuitGuide getApplyOutGuide(Integer guideId)  throws ServiceException
    {
        
        return gaParkQuitGuideMapper.selectById(guideId);
    }
    
}
