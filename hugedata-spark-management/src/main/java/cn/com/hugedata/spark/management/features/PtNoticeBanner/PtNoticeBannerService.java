package cn.com.hugedata.spark.management.features.PtNoticeBanner;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
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
import cn.com.hugedata.spark.commons.storage.entity.PtNoticeBanner;
import cn.com.hugedata.spark.commons.storage.mapper.PtNoticeBannerMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.commons.web.features.FeatureServiceImpl;

@Service
public class PtNoticeBannerService 
        extends FeatureServiceImpl<PtNoticeBanner, Integer, PtNoticeBannerModel, PtNoticeBannerMapper> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PtNoticeBannerService.class);

    @Autowired
    private PtNoticeBannerMapper ptNoticeBannerMapper;

    @Autowired
    private ImagePreviewService imagePreviewService;

    @Autowired
    private FileStoreService fileStoreService;

    @Override
    public PageEntity<PtNoticeBanner> list(Map<String, Object> query, int pageStart, int pageSize, List<OrderItem> orders) {
        orders = Arrays.asList(
                new OrderItem(PtNoticeBanner.Fields.SORT_INDEX, OrderItem.ASC),
                new OrderItem(PtNoticeBanner.Fields.IMAGE_ID, OrderItem.DESC));
        return super.list(query, pageStart, pageSize, orders);
    }

    @Override
    public PtNoticeBanner add(PtNoticeBannerModel model) throws ServiceException {
        String fileId = model.getFileId();
        try (InputStream in = fileStoreService.openStreamForLoad(fileId)) {
            ImagePreviewInfo imagePreviewInfo = 
                    imagePreviewService.createPreview(ImagePreviewTypes.NOTICE_BANNER, fileId);

            model.setPreviewFileId(imagePreviewInfo.getPreviewFileId());
            model.setPreviewFileName(imagePreviewInfo.getPreviewFileName());
            model.setCreateTime(new Date());
            model.setIsVisible(false);
            model.setCategoryId(PtNoticeBanner.CATEGORY_DEFAULT);
            model.setSortIndex(queryNextSortIndex());
            return super.add(model);
            
        } catch (IOException e) {
            LOGGER.error("Failed to upload picture.", e);
            throw new ServiceException("FailedToUploadPicture", "上传图片失败", e);
        }
    }

    @Override
    public void modify(PtNoticeBannerModel model) throws ServiceException {
        String fileId = model.getFileId();
        try (InputStream in = fileStoreService.openStreamForLoad(fileId)) {
            ImagePreviewInfo imagePreviewInfo = 
                    imagePreviewService.createPreview(ImagePreviewTypes.NOTICE_BANNER, fileId);

            model.setPreviewFileId(imagePreviewInfo.getPreviewFileId());
            model.setPreviewFileName(imagePreviewInfo.getPreviewFileName());
            model.setCategoryId(PtNoticeBanner.CATEGORY_DEFAULT);
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

        PtNoticeBanner update = new PtNoticeBanner();
        update.setImageId(imageId);
        update.setIsVisible(visible);
        ptNoticeBannerMapper.updateSelectiveById(update);
    }

    /**
     * 查询图片信息.
     * @param imageId   图片ID
     */
    private PtNoticeBanner queryImage(Integer imageId) throws ServiceException {
        PtNoticeBanner image = ptNoticeBannerMapper.selectById(imageId);
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
            PtNoticeBanner update = new PtNoticeBanner();
            update.setImageId(imageIdList.get(i));
            update.setSortIndex(i * 10);
            ptNoticeBannerMapper.updateSelectiveById(update);
        }
    }
    
    /**
     * 新增时获取sortIndex.
     */
    private int queryNextSortIndex() {
        List<PtNoticeBanner> list = ptNoticeBannerMapper.selectByMap(MyBatisUtils.params(
                new OrderItem(PtNoticeBanner.Fields.SORT_INDEX, OrderItem.DESC)
        ), new RowBounds(0, 1));
        if (list.isEmpty()) {
            return 10;
        }
        return list.get(0).getSortIndex() + 10;
    }

}
