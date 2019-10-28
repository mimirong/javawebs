package cn.com.hugedata.spark.management.features.OsMeetingTraining;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.PageEntity;
import cn.com.hugedata.spark.commons.service.preview.ImagePreviewInfo;
import cn.com.hugedata.spark.commons.service.preview.ImagePreviewService;
import cn.com.hugedata.spark.commons.service.preview.ImagePreviewTypes;
import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.entity.OsMeetingTraining;
import cn.com.hugedata.spark.commons.storage.mapper.OsMeetingTrainingMapper;
import cn.com.hugedata.spark.commons.web.features.FeatureServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class OsMeetingTrainingService
        extends FeatureServiceImpl<OsMeetingTraining, Integer,OsMeetingTrainingModel, OsMeetingTrainingMapper> {

    private static final Logger LOGGER = LoggerFactory.getLogger(OsMeetingTrainingService.class);

    @Autowired
    private OsMeetingTrainingMapper osMeetingTrainingMapper;
    
    @Autowired
    private ImagePreviewService imagePreviewService;
    
    @Override
    public PageEntity<OsMeetingTraining> list(Map<String, Object> query, int pageStart, int pageSize, List<OrderItem> orders) {

        orders = Arrays.asList(
                new OrderItem(OsMeetingTraining.Fields.SORT_INDEX, OrderItem.ASC),
                new OrderItem(OsMeetingTraining.Fields.TRAINING_ID, OrderItem.DESC));

        PageEntity<OsMeetingTraining> pageEntity = super.list(query, pageStart, pageSize, orders);

        // 判断培训报名时间是否过期
        for(OsMeetingTraining osMeetingTraining : pageEntity.getData()) {
            if(osMeetingTraining.getRegistrationDeadline().after(new Date()) ) {
                osMeetingTraining.setOutOfDate("否");
            }
            else {
                osMeetingTraining.setOutOfDate("是");
            }
        }

        return pageEntity;
    }

    @Override
    public OsMeetingTraining add(OsMeetingTrainingModel model) throws ServiceException {
        parseDate(model);
        model.setCreateTime(new Date());
        model.setUpdateTime(new Date());
        model.setIsVisible(false);
        OsMeetingTraining meetingTraining = super.add(model);
        
        saveImages(model);
        //saveAttachments(model);
        
        return meetingTraining;
    }

    @Override
    public void modify(OsMeetingTrainingModel model) throws ServiceException {

        parseDate(model);
        model.setUpdateTime(new Date());
        super.modify(model);

        saveImages(model);
        //saveAttachments(model, article.getArticleId(), article.getCategoryId());
    }

    @Override
    public OsMeetingTrainingModel queryForModify(Integer id) throws ServiceException {
        OsMeetingTrainingModel model = super.queryForModify(id);
        //loadImagesForModify(model, id);
        //loadAttachmentsForModify(model, id);
        return model;
    }

    /**
     * 设置是否可见.
     * @param trainingId    会议培训ID
     * @param visible           是否可见
     * @throws ServiceException 设置失败
     */
    public void setTraingVisible(int trainingId, boolean visible) throws ServiceException {
        queryMeetingTrainingDetail(trainingId);
        
        OsMeetingTraining update = new OsMeetingTraining();
        update.setTrainingId(trainingId);
        update.setIsVisible(visible);
        osMeetingTrainingMapper.updateSelectiveById(update);
    }

    /**
     * 处理Model中的日期.
     */
    private void parseDate(OsMeetingTrainingModel model) throws ServiceException {
        try {
            if (StringUtils.isNotEmpty(model.getRegistrationTimeStr())) {
                SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                model.setRegistrationTime(fmt.parse(model.getRegistrationTimeStr() + " 00:00:00"));
            }

            if (StringUtils.isNotEmpty(model.getRegistrationDeadlineStr())) {
                SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                model.setRegistrationDeadline(fmt.parse(model.getRegistrationDeadlineStr() + " 23:59:59"));
            }

            if (StringUtils.isNotEmpty(model.getTrainingStartTimeStr())) {
                SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                model.setTrainingStartTime(fmt.parse(model.getTrainingStartTimeStr()));
            }

            if (StringUtils.isNotEmpty(model.getTrainingEndTimeStr())) {
                SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                model.setTrainingEndTime(fmt.parse(model.getTrainingEndTimeStr()));
            }
        } catch (ParseException e) {
            throw new ServiceException("InvalidDateFormat", "日期不正确");
        }
    }

    /**
     * 保存图片信息.
     */
    private void saveImages(OsMeetingTrainingModel model) throws ServiceException {
        // 生成缩略图
        String fileId = model.getFileId();
        ImagePreviewInfo prev = imagePreviewService.createPreview(ImagePreviewTypes.OS_MEETING_TRAINING, fileId);

        // 更新数据库
        OsMeetingTraining update = new OsMeetingTraining();
        update.setTrainingId(model.getTrainingId());
        update.setPreviewFileId(prev.getPreviewFileId());
        update.setPreviewFileName(prev.getPreviewFileName());
        osMeetingTrainingMapper.updateSelectiveById(update);
    }

    /**
     * 查询文章信息.
     */
    private OsMeetingTraining queryMeetingTrainingDetail(int trainingId) throws ServiceException {
        OsMeetingTraining meetingTraining = osMeetingTrainingMapper.selectById(trainingId);
        if (meetingTraining == null) {
            LOGGER.error("meeting training id not found.");
            throw new ServiceException("MeetingTrainingNotFound", "会议培训信息不存在");
        }
        return meetingTraining;
    }

    /**
     * 将排序信息更新到数据库.
     * @param trainingIdList 排序的ID列表
     */
    public void updateOrder(List<Integer> trainingIdList) {
        for (int i = 0; i < trainingIdList.size(); i++) {
            OsMeetingTraining update = new OsMeetingTraining();
            update.setTrainingId(trainingIdList.get(i));
            update.setSortIndex(i * 10);
            osMeetingTrainingMapper.updateSelectiveById(update);
        }
    }
}
