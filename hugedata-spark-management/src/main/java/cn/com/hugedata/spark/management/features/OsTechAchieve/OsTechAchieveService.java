package cn.com.hugedata.spark.management.features.OsTechAchieve;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.PageEntity;
import cn.com.hugedata.spark.commons.service.file.FileStoreService;
import cn.com.hugedata.spark.commons.service.preview.ImagePreviewInfo;
import cn.com.hugedata.spark.commons.service.preview.ImagePreviewService;
import cn.com.hugedata.spark.commons.service.preview.ImagePreviewTypes;
import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.entity.OsTechAchieve;
import cn.com.hugedata.spark.commons.storage.mapper.OsTechAchieveMapper;
import cn.com.hugedata.spark.commons.web.features.FeatureServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class OsTechAchieveService
        extends FeatureServiceImpl<OsTechAchieve, Integer, OsTechAchieveModel, OsTechAchieveMapper> {

    private static final Logger LOGGER = LoggerFactory.getLogger(OsTechAchieveService.class);

    @Autowired
    private OsTechAchieveMapper osTechAchieveMapper;

    @Autowired
    private ImagePreviewService imagePreviewService;

    @Autowired
    private FileStoreService fileStoreService;

    @Override
    public PageEntity<OsTechAchieve> list(Map<String, Object> query, int pageStart, int pageSize, List<OrderItem> orders) {
        orders = Arrays.asList(
                new OrderItem(OsTechAchieve.Fields.SORT_INDEX, OrderItem.ASC),
                new OrderItem(OsTechAchieve.Fields.ACHIEVE_ID, OrderItem.DESC));
        return super.list(query, pageStart, pageSize, orders);
    }

    @Override
    public OsTechAchieve add(OsTechAchieveModel model) throws ServiceException {
        String fileId = model.getFileId();
        try (InputStream in = fileStoreService.openStreamForLoad(fileId)) {
            ImagePreviewInfo imagePreviewInfo = 
                    imagePreviewService.createPreview(ImagePreviewTypes.HOME_IMAGE, fileId);

            model.setPreviewFileId(imagePreviewInfo.getPreviewFileId());
            model.setPreviewFileId(imagePreviewInfo.getPreviewFileId());
            model.setCreateTime(new Date());
            model.setIsVisible(false);
            return super.add(model);
            
        } catch (IOException e) {
            LOGGER.error("Failed to upload picture.", e);
            throw new ServiceException("FailedToUploadPicture", "上传图片失败", e);
        }
    }

    @Override
    public void modify(OsTechAchieveModel model) throws ServiceException {
        String fileId = model.getFileId();
        try (InputStream in = fileStoreService.openStreamForLoad(fileId)) {
            ImagePreviewInfo imagePreviewInfo = 
                    imagePreviewService.createPreview(ImagePreviewTypes.HOME_IMAGE, fileId);

            model.setPreviewFileId(imagePreviewInfo.getPreviewFileId());
            model.setPreviewFileId(imagePreviewInfo.getPreviewFileId());
            super.modify(model);
            
        } catch (IOException e) {
            LOGGER.error("Failed to upload picture.", e);
            throw new ServiceException("FailedToUploadPicture", "上传图片失败", e);
        }
    }

    /**
     * 设置专家是否可见.
     * @param achieveId          专家ID
     * @param visible           是否可见
     * @throws ServiceException 设置失败
     */
    public void setachieveVisible(Integer achieveId, Boolean visible) throws ServiceException {
        queryExpert(achieveId);

        OsTechAchieve update = new OsTechAchieve();
        update.setAchieveId(achieveId);
        update.setIsVisible(visible);
        osTechAchieveMapper.updateSelectiveById(update);
    }

    /**
     * 查询专家信息.
     * @param expertId   专家ID
     */
    private OsTechAchieve queryExpert(Integer expertId) throws ServiceException {
        OsTechAchieve image = osTechAchieveMapper.selectById(expertId);
        if (image == null){
            LOGGER.error("Expert id not found.");
            throw new ServiceException("ImageNotFound", "专家信息不存在");
        }
        return image;
    }

    /**
     * 将图片排序信息更新到数据库.
     * @param expertIdList 排序的ID列表
     * @param categoryId  类别ID
     */
    public void updateImagesOrder(List<Integer> expertIdList, String categoryId) {
        for (int i = 0; i < expertIdList.size(); i++) {
            OsTechAchieve update = new OsTechAchieve();
            update.setAchieveId(expertIdList.get(i));
            update.setSortIndex(i * 10);
            osTechAchieveMapper.updateSelectiveById(update);
        }
    }

}
