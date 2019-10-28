package cn.com.hugedata.spark.connect.handlers.serviceGuide;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.file.FileStoreService;
import cn.com.hugedata.spark.commons.service.file.FileUrlHelperService;
import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.entity.GaServiceGuide;
import cn.com.hugedata.spark.commons.storage.entity.GaServiceGuideAttachment;
import cn.com.hugedata.spark.commons.storage.entity.SysFileInfo;
import cn.com.hugedata.spark.commons.storage.mapper.GaServiceGuideAttachmentMapper;
import cn.com.hugedata.spark.commons.storage.mapper.GaServiceGuideMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.connect.BaseHandler;
import cn.com.hugedata.spark.connect.InterfaceResponse;

import com.alibaba.fastjson.JSONObject;

public class DetailHandler extends BaseHandler {


    private Integer guideId;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DetailHandler.class);

    @Autowired
    private GaServiceGuideMapper gaServiceGuideMapper;
    
    @Autowired
    private GaServiceGuideAttachmentMapper gaServiceGuideAttachmentMapper;
    
    @Autowired
    private FileStoreService fileStoreService;
    
    
    @Autowired
    private FileUrlHelperService fileUrlHelperService;

    @Override
    protected boolean isRequireLogin() {
        return false;
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

        List<GaServiceGuideAttachment> attachmentList = gaServiceGuideAttachmentMapper.selectByMap(
                MyBatisUtils.buildParameterMap(
                        GaServiceGuideAttachment.Fields.GUIDE_ID, guideId,
                        OrderItem.ORDER_KEY, Arrays.asList(new OrderItem(GaServiceGuideAttachment.Fields.SORT_INDEX, OrderItem.ASC))
                )
        );
        
        ServiceGuideData data = new ServiceGuideData();
        data.guide = guide;
        if(attachmentList != null){
            for(GaServiceGuideAttachment a : attachmentList){
                SysFileInfo file = fileStoreService.loadFileInfo(a.getFileId());
                a.setFileId(fileUrlHelperService.fixDownloadUrl(file.getFileId(),file.getFileName()));
            }
        }
        data.attachments = attachmentList;
        
        JSONObject resp = new JSONObject();
        resp.put("guide", data);
        
        return InterfaceResponse.createSuccessResponse(resp);
        
        
        
        
    }

    private void parseParameters(JSONObject req) {
        guideId = req.getInteger("guideId");
    }

    private void checkParameters() throws ServiceException {        
        if (guideId == null ) {
            LOGGER.error("Parameter [guideId] is empty.");
            throw new ServiceException(this.getClass().getSimpleName() + "-guideId", "guideId不能为空");
        }
    }

   
    public static class ServiceGuideData {

        private GaServiceGuide guide;
        
        private List<GaServiceGuideAttachment> attachments;

        public GaServiceGuide getGuide() {
            return guide;
        }

        public void setGuide(GaServiceGuide guide) {
            this.guide = guide;
        }

        public List<GaServiceGuideAttachment> getAttachments() {
            return attachments;
        }

        public void setAttachments(List<GaServiceGuideAttachment> attachments) {
            this.attachments = attachments;
        }
        
    }


}
