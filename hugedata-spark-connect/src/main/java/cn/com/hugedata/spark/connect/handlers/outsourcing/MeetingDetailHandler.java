package cn.com.hugedata.spark.connect.handlers.outsourcing;

import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.entity.OsMeetingTraining;
import cn.com.hugedata.spark.commons.storage.mapper.OsMeetingTrainingMapper;
import cn.com.hugedata.spark.connect.BaseHandler;
import cn.com.hugedata.spark.connect.InterfaceResponse;

import com.alibaba.fastjson.JSONObject;

public class MeetingDetailHandler extends BaseHandler {


    private Integer trainingId;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MeetingDetailHandler.class);

    @Autowired
    private OsMeetingTrainingMapper osMeetingTrainingMapper;
    
     
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
        OsMeetingTraining item = osMeetingTrainingMapper.selectById(trainingId);

        ////// 报名时间 、培训开始时间、结束时间格式转换
        SimpleDateFormat fmt_ymd = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat fmt_ymd_hms = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat fmt_hms = new SimpleDateFormat("HH:mm");

        // 报名时间
        String registrationTimeStr = fmt_ymd.format(item.getRegistrationTime());
        String registrationDeadlineStr = fmt_ymd.format(item.getRegistrationDeadline());
        item.setRegistrationTimeStr(registrationTimeStr);
        item.setRegistrationDeadlineStr(registrationDeadlineStr);

        // 培训时间
        String trainingStartTimeStr;
        String trainingEndTimeStr;
        if (fmt_ymd.format(item.getTrainingStartTime()).equals(fmt_ymd.format(item.getTrainingEndTime()))) {
            trainingStartTimeStr = fmt_ymd_hms.format(item.getTrainingStartTime());
            trainingEndTimeStr = fmt_hms.format(item.getTrainingEndTime());
            item.setTrainingStartTimeStr(trainingStartTimeStr);
            item.setTrainingEndTimeStr(trainingEndTimeStr);
        } else {
            trainingStartTimeStr = fmt_ymd_hms.format(item.getTrainingStartTime()).toString();
            trainingEndTimeStr = fmt_ymd_hms.format(item.getTrainingEndTime()).toString();
        }
        item.setTrainingStartTimeStr(trainingStartTimeStr);
        item.setTrainingEndTimeStr(trainingEndTimeStr);

//        // 根据fileId生成附件下载url
//        if(StringUtils.isNotEmpty(item.getBriefFileName())) {
//            item.setBriefFileDownloadUrl(fileUrlHelperService.fixDownloadUrl(item.getBriefFileId(), item.getBriefFileName()));
//        }
//        if(StringUtils.isNotEmpty(item.getAnnoFileId())) {
//            item.setAnnouncementsFileDownloadUrl(fileUrlHelperService.fixDownloadUrl(item.getAnnoFileId(), item.getAnnoFileName()));
//        }
        
        resp.put("info",item);
        return InterfaceResponse.createSuccessResponse(resp);
    }

    private void parseParameters(JSONObject req) {
        trainingId = req.getInteger("trainingId");
    }

    private void checkParameters() throws ServiceException {        
        if (trainingId == null ) {
            LOGGER.error("Parameter [trainingId] is empty.");
            throw new ServiceException(this.getClass().getSimpleName() + "-trainingId", "trainingId不能为空");
        }
    }

   
     

}
