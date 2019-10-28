package cn.com.hugedata.spark.govaffairs.services;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.hugedata.spark.commons.service.PageEntity;
import cn.com.hugedata.spark.commons.service.file.FileUrlHelperService;
import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.entity.OsMeetingTraining;
import cn.com.hugedata.spark.commons.storage.mapper.OsMeetingTrainingMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;

@Service
public class OsMeetingTrainingQueryService {

    //private static final Logger LOGGER = LoggerFactory.getLogger(OsMeetingTrainingQueryService.class);

    @Autowired
    private OsMeetingTrainingMapper osMeetingTrainingMapper;

    @Autowired
    private FileUrlHelperService fileUrlHelperService;

    /**
     * 查询技术成果列表.
     * @param start         分页开始记录
     * @param length        每页记录数
     * @return
     */
    public PageEntity<OsMeetingTraining> listMeetingTraining(Integer start,
                                                             Integer length,
                                                             String trainingType,
                                                             String outOfDate,
                                                             String searchWord) {

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
        int count = osMeetingTrainingMapper.countByMap(queryMap);

        
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

        return new PageEntity<>(data, start, length, count);

    }

    /**
     * 查询会议培训详情.
     */
    public OsMeetingTraining queryMeetingTrainingDetail(int trainingId) {
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

        // 根据fileId生成附件下载url
        if(StringUtils.isNotEmpty(item.getBriefFileName())) {
            item.setBriefFileDownloadUrl(fileUrlHelperService.fixDownloadUrl(item.getBriefFileId(), item.getBriefFileName()));
        }
        if(StringUtils.isNotEmpty(item.getAnnoFileId())) {
            item.setAnnouncementsFileDownloadUrl(fileUrlHelperService.fixDownloadUrl(item.getAnnoFileId(), item.getAnnoFileName()));
        }

        return item;
    }
}
