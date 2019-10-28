package cn.com.hugedata.spark.connect.handlers.serviceGuide;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.approval.ContentUtil;
import cn.com.hugedata.spark.commons.service.file.FileStoreService;
import cn.com.hugedata.spark.commons.service.file.FileUrlHelperService;
import cn.com.hugedata.spark.commons.storage.entity.ApServiceInfo;
import cn.com.hugedata.spark.commons.storage.entity.GaServiceGuide;
import cn.com.hugedata.spark.commons.storage.entity.SysFileInfo;
import cn.com.hugedata.spark.commons.storage.mapper.ApServiceInfoMapper;
import cn.com.hugedata.spark.commons.storage.mapper.GaServiceGuideAttachmentMapper;
import cn.com.hugedata.spark.commons.storage.mapper.GaServiceGuideMapper;
import cn.com.hugedata.spark.commons.storage.mapper.SysFileInfoMapper;
import cn.com.hugedata.spark.connect.BaseHandler;
import cn.com.hugedata.spark.connect.InterfaceResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class ApplyHandler extends BaseHandler {


    private Integer guideId;
    private String businessDeptPerson;
    private String cellphone;
    private  JSONArray fs;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplyHandler.class);

    @Autowired
    private GaServiceGuideMapper gaServiceGuideMapper;
    
    @Autowired
    private SysFileInfoMapper sysFileInfoMapper;
    
    @Autowired
    private GaServiceGuideAttachmentMapper gaServiceGuideAttachmentMapper;
    
    @Autowired
    private ApServiceInfoMapper apServiceInfoMapper;
    
    @Autowired
    private FileStoreService fileStoreService;
    
    
    @Autowired
    private FileUrlHelperService fileUrlHelperService;
 

    @Override
    protected boolean isRequireLogin() {
        return true;
    }

    @Override
    public InterfaceResponse execute(JSONObject requestData)
            throws ServiceException
    {
        parseParameters(requestData);
        checkParameters();
        GaServiceGuide guide = gaServiceGuideMapper.selectById(guideId);
        if (guide == null) {
            return InterfaceResponse.createFailResponse("办事指南不存在");
        }
        ApServiceInfo apServiceInfo  =  new ApServiceInfo();
        apServiceInfo.setDeptcode(Integer.valueOf(guide.getDeptCode()));
        apServiceInfo.setDeptname(guide.getDeptName());
        apServiceInfo.setGuidecode(String.valueOf(guideId));
        apServiceInfo.setBusinessType("onLine");
        apServiceInfo.setGuidename(guide.getGuideName());
        apServiceInfo.setBusinessDeptPerson(businessDeptPerson);
        apServiceInfo.setCellphone(cellphone);
        String businessNo = "ZX" + ContentUtil.produceRandom();
        // 如果附件有多个文件 则应该进行压缩
        if(fs.size() > 1){
            try
            {
                String fileId = fileStoreService.makeId();
                OutputStream os = fileStoreService.openStreamForSave(fileId,businessNo+".zip");
                ZipArchiveOutputStream zip = new ZipArchiveOutputStream(os);
               // zip.setEncoding("UTF-8");
                zip.setUseZip64(Zip64Mode.AsNeeded);
                for (int i = 0; i < fs.size() ;i ++) {
                    String fId = fs.getString(i);
                    SysFileInfo fInfo = fileStoreService.loadFileInfo(fs.getString(i));
                    try (InputStream in = fileStoreService.openStreamForLoad(fId)) {
                        ZipArchiveEntry entry = new ZipArchiveEntry(businessNo + File.separator + fInfo.getFileName());
                        zip.putArchiveEntry(entry);
                        InputStream is = null;
                        try {
                            is = new BufferedInputStream(in);
                            byte[] buffer = new byte[1024 * 5]; 
                            int len = -1;
                            while((len = is.read(buffer)) != -1) {
                                //把缓冲区的字节写入到ZipArchiveEntry
                                zip.write(buffer, 0, len);
                            }
                            zip.closeArchiveEntry();
                        }catch(Exception e) {
                            throw new RuntimeException(e);
                        }finally {
                            if(is != null)
                                is.close();
                            if(in != null)
                            in.close();
                        }
                        
                    }
                }
                zip.finish();
                if(os != null){
                    os.flush();
                    os.close();
                }
                
                apServiceInfo.setFileId(fileId);
                SysFileInfo f = sysFileInfoMapper.selectById(fileId);
                if (f == null) {
                   return InterfaceResponse.createFailResponse("附件上传压缩失败,请重试!");
                }
                apServiceInfo.setFileName(businessNo+".zip");
            }
            catch (IOException e)
            {
                return InterfaceResponse.createFailResponse("附件上传压缩失败,请重试!");
            }
        }else if(fs.size() == 1){
                apServiceInfo.setFileId(fs.getString(0));
                SysFileInfo f = sysFileInfoMapper.selectById(fs.getString(0));
                if (f == null) {
                   return InterfaceResponse.createFailResponse("附件上传失败,请重试!");
                }
                apServiceInfo.setFileName(f.getFileName());
        }
            
        
       
       
        apServiceInfo.setStatus("0");
        apServiceInfo.setTimeLimit(guide.getPromisedTimeLimit() == null || guide.getPromisedTimeLimit().equals("") ? guide.getLegalTimeLimit():guide.getPromisedTimeLimit());
        apServiceInfo.setBusinessNo(businessNo);
        apServiceInfo.setCreateTime(new Date());
        apServiceInfo.setCreateCellphone(getLogin().getMobile());
        apServiceInfo.setCreateUserId(String.valueOf(getLogin().getUserId()));
        apServiceInfoMapper.insertSelective(apServiceInfo);
        JSONObject resp = new JSONObject();
        return InterfaceResponse.createSuccessResponse(resp);
    }

    private void parseParameters(JSONObject req) {
        guideId = req.getInteger("guideId");
        businessDeptPerson = req.getString("businessDeptPerson");
        cellphone = req.getString("cellphone");
        fs = req.getJSONArray("fileIds");
    }

    private void checkParameters() throws ServiceException {        
        if (guideId == null ) {
            LOGGER.error("Parameter [guideId] is empty.");
            throw new ServiceException(this.getClass().getSimpleName() + "-guideId", "guideId不能为空");
        }
        
        if (businessDeptPerson == null ) {
            LOGGER.error("Parameter [businessDeptPerson] is empty.");
            throw new ServiceException(this.getClass().getSimpleName() + "-businessDeptPerson", "businessDeptPerson不能为空");
        }
        
        if (cellphone == null ) {
            LOGGER.error("Parameter [cellphone] is empty.");
            throw new ServiceException(this.getClass().getSimpleName() + "-cellphone", "cellphone不能为空");
        }
        
        if (fs == null ) {
            LOGGER.error("Parameter [fileIds] is empty.");
            throw new ServiceException(this.getClass().getSimpleName() + "-fileIds", "fileIds不能为空");
        }
    }

   
    


}
