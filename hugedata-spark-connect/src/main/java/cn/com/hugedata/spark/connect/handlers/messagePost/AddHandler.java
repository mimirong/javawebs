package cn.com.hugedata.spark.connect.handlers.messagePost;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.entity.GaMessagePost;
import cn.com.hugedata.spark.commons.storage.mapper.ApServiceInfoMapper;
import cn.com.hugedata.spark.commons.storage.mapper.GaMessagePostMapper;
import cn.com.hugedata.spark.commons.storage.mapper.GaServiceGuideAttachmentMapper;
import cn.com.hugedata.spark.commons.storage.mapper.SysFileInfoMapper;
import cn.com.hugedata.spark.connect.BaseHandler;
import cn.com.hugedata.spark.connect.InterfaceResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class AddHandler extends BaseHandler {


    private Long postTime;
    private String postAddr;
    private String eventDesc;
    private String postLevel;
    private Long happenTime;
    private String posterName;
    private String posterMobile;
    private  JSONArray fs;
    
    
    private static final Logger LOGGER = LoggerFactory.getLogger(AddHandler.class);

    @Autowired
    private GaMessagePostMapper gaMessagePostMapper;
    
    @Autowired
    private SysFileInfoMapper sysFileInfoMapper;
    
    @Autowired
    private GaServiceGuideAttachmentMapper gaServiceGuideAttachmentMapper;
    
    @Autowired
    private ApServiceInfoMapper apServiceInfoMapper;
 

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
        
        GaMessagePost m  =  new GaMessagePost();
        m.setPostTime(new Date(postTime));
        m.setPostAddr(postAddr);
        m.setEventDesc(eventDesc);
        m.setHappenTime(new Date(happenTime));
        m.setPosterName(posterName);
        m.setPosterMobile(posterMobile);
        m.setPostLevel(postLevel);
        if(fs != null ){
            for(int i = 0; i < 6 && i < fs.size(); i++){
                String fileId = fs.getString(i);
                if(i == 0){
                    m.setFile1Id(fileId);
                }
                if(i == 1){
                    m.setFile2Id(fileId);
                }
                if(i == 2){
                    m.setFile3Id(fileId);
                }
                if(i == 3){
                    m.setFile4Id(fileId);
                }
                if(i == 4){
                    m.setFile5Id(fileId);
                }
                if(i == 5){
                    m.setFile6Id(fileId);
                }
            }
           
        }
        m.setCreateTime(new Date());
        m.setCreaterName(getLogin().getName());
        m.setCreaterId(getLogin().getUserId());
        gaMessagePostMapper.insertSelective(m);
        JSONObject resp = new JSONObject();
        return InterfaceResponse.createSuccessResponse(resp);
    }

    private void parseParameters(JSONObject req) {
        postTime = req.getLong("postTime");
        postAddr = req.getString("postAddr");
        eventDesc = req.getString("eventDesc");
        happenTime = req.getLong("happenTime");
        posterName = req.getString("posterName");
        posterMobile = req.getString("posterMobile");
        postLevel =  req.getString("postLevel");
        fs = req.getJSONArray("fileIds");
    }

    private void checkParameters() throws ServiceException {
        if (postTime == null ) {
            LOGGER.error("Parameter [postTime] is empty.");
            throw new ServiceException(this.getClass().getSimpleName() + "-postTime", "postTime不能为空");
        }
        
        if (postAddr == null ) {
            LOGGER.error("Parameter [postAddr] is empty.");
            throw new ServiceException(this.getClass().getSimpleName() + "-postAddr", "postAddr不能为空");
        }
        
        if (eventDesc == null ) {
            LOGGER.error("Parameter [eventDesc] is empty.");
            throw new ServiceException(this.getClass().getSimpleName() + "-eventDesc", "eventDesc不能为空");
        }
        
        if (happenTime == null ) {
            LOGGER.error("Parameter [happenTime] is empty.");
            throw new ServiceException(this.getClass().getSimpleName() + "-happenTime", "happenTime不能为空");
        }
        
        if (posterName == null ) {
            LOGGER.error("Parameter [posterName] is empty.");
            throw new ServiceException(this.getClass().getSimpleName() + "-posterName", "posterName不能为空");
        }
        
        if (posterMobile == null ) {
            LOGGER.error("Parameter [posterMobile] is empty.");
            throw new ServiceException(this.getClass().getSimpleName() + "-posterMobile", "posterMobile不能为空");
        }
        
        if (postLevel == null ) {
            LOGGER.error("Parameter [postLevel] is empty.");
            throw new ServiceException(this.getClass().getSimpleName() + "-postLevel", "postLevel不能为空");
        }
        
        
    }

   
    


}
