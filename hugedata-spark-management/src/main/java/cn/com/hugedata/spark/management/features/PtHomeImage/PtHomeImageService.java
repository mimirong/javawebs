package cn.com.hugedata.spark.management.features.PtHomeImage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.PageEntity;
import cn.com.hugedata.spark.commons.service.file.FileStoreService;
import cn.com.hugedata.spark.commons.service.preview.ImagePreviewInfo;
import cn.com.hugedata.spark.commons.service.preview.ImagePreviewService;
import cn.com.hugedata.spark.commons.service.preview.ImagePreviewTypes;
import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.entity.PtHomeImage;
import cn.com.hugedata.spark.commons.storage.mapper.PtHomeImageMapper;
import cn.com.hugedata.spark.commons.web.features.FeatureServiceImpl;

@Service
public class PtHomeImageService
        extends FeatureServiceImpl<PtHomeImage, Integer, PtHomeImageModel, PtHomeImageMapper> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PtHomeImageService.class);

    @Autowired
    private PtHomeImageMapper ptHomeImageMapper;

    @Autowired
    private ImagePreviewService imagePreviewService;

    @Autowired
    private FileStoreService fileStoreService;

    @Override
    public PageEntity<PtHomeImage> list(Map<String, Object> query, int pageStart, int pageSize, List<OrderItem> orders) {
        orders = Arrays.asList(
                new OrderItem(PtHomeImage.Fields.SORT_INDEX, OrderItem.ASC),
                new OrderItem(PtHomeImage.Fields.IMAGE_ID, OrderItem.DESC));
        return super.list(query, pageStart, pageSize, orders);
    }

    @Override
    public PtHomeImage add(PtHomeImageModel model) throws ServiceException {
        String fileId = model.getFileId();
        try (InputStream in = fileStoreService.openStreamForLoad(fileId)) {
            ImagePreviewInfo imagePreviewInfo = 
                    imagePreviewService.createPreview(ImagePreviewTypes.HOME_IMAGE, fileId);

            model.setPreviewFileId(imagePreviewInfo.getPreviewFileId());
            model.setPreviewFileName(imagePreviewInfo.getPreviewFileName());
            model.setCreateTime(new Date());
            model.setIsVisible(false);
            model.setCategoryId(PtHomeImage.CATEGORY_DEFAULT);
            return super.add(model);
            
        } catch (IOException e) {
            LOGGER.error("Failed to upload picture.", e);
            throw new ServiceException("FailedToUploadPicture", "上传图片失败", e);
        }
    }

    @Override
    public void modify(PtHomeImageModel model) throws ServiceException {
        String fileId = model.getFileId();
        try (InputStream in = fileStoreService.openStreamForLoad(fileId)) {
            ImagePreviewInfo imagePreviewInfo = 
                    imagePreviewService.createPreview(ImagePreviewTypes.HOME_IMAGE, fileId);

            model.setPreviewFileId(imagePreviewInfo.getPreviewFileId());
            model.setPreviewFileName(imagePreviewInfo.getPreviewFileName());
            model.setCategoryId(PtHomeImage.CATEGORY_DEFAULT);
            super.modify(model);
            
        } catch (IOException e) {
            LOGGER.error("Failed to upload picture.", e);
            throw new ServiceException("FailedToUploadPicture", "上传图片失败", e);
        }
    }

    /**
     * 设置图片是否可见.
     * @param imageId           图片ID
     * @param visible           是否可见
     * @throws ServiceException 设置失败
     */
    public void setImageVisible(Integer imageId, Boolean visible) throws ServiceException {
        queryImage(imageId);

        PtHomeImage update = new PtHomeImage();
        update.setImageId(imageId);
        update.setIsVisible(visible);
        ptHomeImageMapper.updateSelectiveById(update);
    }

    /**
     * 查询图片信息.
     * @param imageId   图片ID
     */
    private PtHomeImage queryImage(Integer imageId) throws ServiceException {
        PtHomeImage image = ptHomeImageMapper.selectById(imageId);
        if (image == null){
            LOGGER.error("Image id not found.");
            throw new ServiceException("ImageNotFound", "图片信息不存在");
        }
        return image;
    }

    /**
     * 将图片排序信息更新到数据库.
     * @param imageIdList 排序的ID列表
     * @param categoryId  类别ID
     */
    public void updateImagesOrder(List<Integer> imageIdList, String categoryId) {
        for (int i = 0; i < imageIdList.size(); i++) {
            PtHomeImage update = new PtHomeImage();
            update.setImageId(imageIdList.get(i));
            update.setSortIndex(i * 10);
            ptHomeImageMapper.updateSelectiveById(update);
        }
    }

}
