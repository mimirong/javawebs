package cn.com.hugedata.spark.management.features.GaMessagePost;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.hugedata.spark.commons.service.PageEntity;
import cn.com.hugedata.spark.commons.service.file.FileStoreService;
import cn.com.hugedata.spark.commons.service.file.FileUrlHelperService;
import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.entity.GaMessagePost;
import cn.com.hugedata.spark.commons.storage.entity.LogOperation;
import cn.com.hugedata.spark.commons.storage.entity.SysFileInfo;
import cn.com.hugedata.spark.commons.storage.mapper.GaMessagePostMapper;
import cn.com.hugedata.spark.management.features.base.ManagementFeatureServiceImpl;
@Service
public class GaMessagePostService
        extends ManagementFeatureServiceImpl<GaMessagePost, Integer, GaMessagePostModel, GaMessagePostMapper> {
    private static final Logger LOGGER = LoggerFactory.getLogger(GaMessagePostService.class);
    
    @Autowired
    GaMessagePostMapper gaMessagePostMapper;
    
    @Autowired
    private FileStoreService fileStoreService;
    
    
    @Autowired
    private FileUrlHelperService fileUrlHelperService;
    
    @Override
    protected String getLogTargetName()
    {
        return LogOperation.TARGET_MESSAGE_POST;
    }
    
    @Override
    public PageEntity<GaMessagePost> list(Map<String, Object> query, int pageStart, int pageSize,
                                          List<OrderItem> orders) {
        orders = new ArrayList<OrderItem>();
        orders.add(new OrderItem(GaMessagePost.Fields.CREATE_TIME, OrderItem.DESC));
        PageEntity<GaMessagePost> page = super.list(query, pageStart, pageSize, orders);
        return page;
    }
    
    public GaMessagePost selectById(Integer serviceId)  {
        GaMessagePost mp = gaMessagePostMapper.selectById(serviceId);
        if(mp.getFile1Id() != null){
            SysFileInfo file = fileStoreService.loadFileInfo(mp.getFile1Id());
            mp.setFile1Id(fileUrlHelperService.fixDownloadUrl(file.getFileId(),file.getFileName()));
        }
        
        if(mp.getFile2Id() != null){
            SysFileInfo file = fileStoreService.loadFileInfo(mp.getFile2Id());
            mp.setFile2Id(fileUrlHelperService.fixDownloadUrl(file.getFileId(),file.getFileName()));
        }
       
        if(mp.getFile3Id() != null){
            SysFileInfo file = fileStoreService.loadFileInfo(mp.getFile3Id());
            mp.setFile3Id(fileUrlHelperService.fixDownloadUrl(file.getFileId(),file.getFileName()));
        }
        
        if(mp.getFile4Id() != null){
            SysFileInfo file = fileStoreService.loadFileInfo(mp.getFile4Id());
            mp.setFile4Id(fileUrlHelperService.fixDownloadUrl(file.getFileId(),file.getFileName()));
        }
        
        if(mp.getFile5Id() != null){
            SysFileInfo file = fileStoreService.loadFileInfo(mp.getFile5Id());
            mp.setFile5Id(fileUrlHelperService.fixDownloadUrl(file.getFileId(),file.getFileName()));
        }
        
        if(mp.getFile6Id() != null){
            SysFileInfo file = fileStoreService.loadFileInfo(mp.getFile6Id());
            mp.setFile6Id(fileUrlHelperService.fixDownloadUrl(file.getFileId(),file.getFileName()));
        }
        return mp;
    }
    
}
