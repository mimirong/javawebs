package cn.com.hugedata.spark.connect.handlers.outsourcing;

import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.file.FileStoreService;
import cn.com.hugedata.spark.commons.service.file.FileUrlHelperService;
import cn.com.hugedata.spark.commons.storage.entity.OsRequireAtt;
import cn.com.hugedata.spark.commons.storage.entity.OsRequireInfo;
import cn.com.hugedata.spark.commons.storage.entity.SpProjectImage;
import cn.com.hugedata.spark.commons.storage.entity.SpServiceProject;
import cn.com.hugedata.spark.commons.storage.mapper.OsRequireAttMapper;
import cn.com.hugedata.spark.commons.storage.mapper.OsRequireInfoMapper;
import cn.com.hugedata.spark.commons.storage.mapper.SpSpecificationMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.connect.BaseHandler;
import cn.com.hugedata.spark.connect.InterfaceResponse;

import com.alibaba.fastjson.JSONObject;

public class RequireDetailHandler extends BaseHandler {


    private Integer requireId;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(RequireDetailHandler.class);

    @Autowired
    private OsRequireInfoMapper osRequireMapper;
    
    @Autowired
    private OsRequireAttMapper osRequireAttMapper;

    
    @Autowired
    private SpSpecificationMapper spSpecificationMapper;
    
    
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
        JSONObject resp = new JSONObject();
        OsRequireInfo info = osRequireMapper.selectById(requireId);
        resp.put("info",info);
        long restTime = info.getDeadDate().getTime() - Calendar.getInstance().getTimeInMillis();
        long restDay = restTime/(24*60*60*1000);
        long restHour = restTime%(24*60*60*1000)/(60*60*1000);
        long restMin = restTime%(24*60*60*1000)%(60*60*1000)/(60*1000);
        long restSec = restTime%(24*60*60*1000)%(60*60*1000)%(60*1000)/1000;
        resp.put("restDay",restDay);
        resp.put("restHour",restHour);
        resp.put("restMin",restMin);
        resp.put("restSec",restSec);
        //浏览量加1
        info.setReadNum(info.getReadNum() + 1);
        osRequireMapper.updateSelectiveById(info);
        List<OsRequireAtt> atts =osRequireAttMapper.selectByMap(MyBatisUtils.buildParameterMap(OsRequireAtt.Fields.REQUIRE_ID,requireId));
        resp.put("atts",atts);
        return InterfaceResponse.createSuccessResponse(resp);
    }

    private void parseParameters(JSONObject req) {
        requireId = req.getInteger("requireId");
    }

    private void checkParameters() throws ServiceException {        
        if (requireId == null ) {
            LOGGER.error("Parameter [requireId] is empty.");
            throw new ServiceException(this.getClass().getSimpleName() + "-requireId", "requireId不能为空");
        }
    }

   
    public static class DetailData {

        private SpServiceProject info;
        
        private List<SpProjectImage> attList;

        public SpServiceProject getInfo()
        {
            return info;
        }

        public void setInfo(SpServiceProject info)
        {
            this.info = info;
        }

        public List<SpProjectImage> getAttList()
        {
            return attList;
        }

        public void setAttList(List<SpProjectImage> attList)
        {
            this.attList = attList;
        }
        
        

    }


}
