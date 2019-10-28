package cn.com.hugedata.spark.connect.handlers.outsourcing;

import static cn.com.hugedata.spark.commons.storage.constant.OsTechAchieveConstants.MONETARY_UNIT_MILLION_YUAN;
import static cn.com.hugedata.spark.commons.storage.constant.OsTechAchieveConstants.MONETARY_UNIT_TEN_THOUSND_YUAN;
import static cn.com.hugedata.spark.commons.storage.constant.OsTechAchieveConstants.MONETARY_UNIT_YUAN;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.entity.OsMeetingTraining;
import cn.com.hugedata.spark.commons.storage.mapper.OsMeetingTrainingMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.connect.BaseHandler;
import cn.com.hugedata.spark.connect.InterfaceResponse;

import com.alibaba.fastjson.JSONObject;

public class MeetingListHandler extends BaseHandler {
   
    private Integer start;
    private Integer length;
    private String trainingType;
    private String outOfDate;
    private String searchWord;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MeetingListHandler.class);

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
        
        
        
        if(StringUtils.isEmpty(trainingType)) {
            trainingType = null;
        }
        if(StringUtils.isEmpty(outOfDate)) {
            outOfDate = null;
        }

        Map<String, Object> queryMap = MyBatisUtils.buildParameterMap(
                OsMeetingTraining.Fields.TRAINING_TYPE, trainingType,
                OsMeetingTraining.Fields.IS_VISIBLE, true,
                OrderItem.ORDER_KEY, Arrays.asList(new OrderItem(OsMeetingTraining.Fields.SORT_INDEX, OrderItem.ASC),
                        new OrderItem(OsMeetingTraining.Fields.TRAINING_ID, OrderItem.DESC))
        );
        Date now = new Date();
        if(!StringUtils.isEmpty(outOfDate) && outOfDate.equals("1")){
            queryMap.put(OsMeetingTraining.Query.REGISTRATION_TIME__END, now);
            queryMap.put(OsMeetingTraining.Query.REGISTRATION_DEADLINE__BEGIN, now);
        }
        
        if(!StringUtils.isEmpty(outOfDate) && outOfDate.equals("2")){
            //queryMap.put(OsMeetingTraining.Query.TRAINING_START_TIME__END, now);
            queryMap.put(OsMeetingTraining.Query.TRAINING_END_TIME__END, now);
        }
        

        if(StringUtils.isNotEmpty(searchWord)) {
            // 模糊查询
            //query.put(OsMeetingTraining.Query.NAME__LIKE, searchWord);

            //OsMeetingTrainingMapper.xml中添加了的"like_keyWord"查询动作，指定查哪些
            queryMap.put("like_keyWord",searchWord);
        }

        List<OsMeetingTraining> data = osMeetingTrainingMapper.selectByMap(queryMap, new RowBounds(start, length));

        
        for(OsMeetingTraining item: data) {
            if(item.getRegistrationTime().before(now) && item.getRegistrationDeadline().after(now)){
                item.setAnnouncements("1");
            }else if(item.getTrainingEndTime().before(now)){
                item.setAnnouncements("2");
            }else{
                item.setAnnouncements("0");
            }
            ////// 培训开始时间、结束时间格式转换
            SimpleDateFormat fmt_ymd = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat fmt_ymd_hms = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            SimpleDateFormat fmt_hms = new SimpleDateFormat("HH:mm");
            String trainingStartTimeStr;
            String trainingEndTimeStr;
            if (fmt_ymd.format(item.getTrainingStartTime()).equals(fmt_ymd.format(item.getTrainingEndTime()))) {
                trainingStartTimeStr = fmt_ymd_hms.format(item.getTrainingStartTime());
                trainingEndTimeStr = fmt_hms.format(item.getTrainingEndTime());
                item.setTrainingStartTimeStr(trainingStartTimeStr);
                item.setTrainingEndTimeStr(trainingEndTimeStr);
            } else {
                trainingStartTimeStr = fmt_ymd_hms.format(item.getTrainingStartTime());
                trainingEndTimeStr = fmt_ymd_hms.format(item.getTrainingEndTime());
            }
            item.setTrainingStartTimeStr(trainingStartTimeStr);
            item.setTrainingEndTimeStr(trainingEndTimeStr);

            
        }
        JSONObject resp = new JSONObject();
        resp.put("meetings", data);
        resp.put("start", start);
        resp.put("length", length);
        return InterfaceResponse.createSuccessResponse(resp);
    }

    private void parseParameters(JSONObject req) {
        start = req.getInteger("start");
        length = req.getInteger("length");
        trainingType = req.getString("trainingType");
        outOfDate = req.getString("outOfDate");
        searchWord = req.getString("searchWord");
    }

    private void checkParameters() throws ServiceException {
        
        if (start == null ) {
            LOGGER.error("Parameter [start] is empty.");
            throw new ServiceException(this.getClass().getSimpleName() + "-start", "start不能为空");
        }
        
        if (length == null ) {
            LOGGER.error("Parameter [length] is empty.");
            throw new ServiceException(this.getClass().getSimpleName() + "-length", "length不能为空");
        }
        
    }

   
    private int convertMonetaryUnit2Yuan(String monetaryUnit) {
        int unit_yuan = 0;
        switch (monetaryUnit) {
            case MONETARY_UNIT_YUAN:
                unit_yuan = 1;
                break;
            case MONETARY_UNIT_TEN_THOUSND_YUAN:
                unit_yuan = 10000;
                break;
            case MONETARY_UNIT_MILLION_YUAN:
                unit_yuan = 1000000;
                break;
            default:
                unit_yuan = 1;
                break;
        }
        return unit_yuan;
    }

}
