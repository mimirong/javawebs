package cn.com.hugedata.spark.management.features.OsTechImage;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.PageEntity;
import cn.com.hugedata.spark.commons.service.file.FileStoreService;
import cn.com.hugedata.spark.commons.service.preview.ImagePreviewInfo;
import cn.com.hugedata.spark.commons.service.preview.ImagePreviewService;
import cn.com.hugedata.spark.commons.service.preview.ImagePreviewTypes;
import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.entity.PtTechImage;
import cn.com.hugedata.spark.commons.storage.mapper.PtTechImageMapper;
import cn.com.hugedata.spark.commons.web.features.FeatureServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class OsTechImageService
        extends FeatureServiceImpl<PtTechImage, Integer, OsTechImageModel, PtTechImageMapper> {

    private static final Logger LOGGER = LoggerFactory.getLogger(OsTechImageService.class);

    @Autowired
    private PtTechImageMapper ptTechImageMapper;

    @Autowired
    private ImagePreviewService imagePreviewService;

    @Autowired
    private FileStoreService fileStoreService;

    @Override
    public PageEntity<PtTechImage> list(Map<String, Object> query, int pageStart, int pageSize, List<OrderItem> orders) {
        query.put(PtTechImage.Query.CATEGORY_ID__IN,
                Arrays.asList(PtTechImage.TYPE_HUMAN_RESOURCE,
                        PtTechImage.TYPE_COMPANIES_INFORMATION,
                        PtTechImage.TYPE_PROJECT_OSINVEST,
                        PtTechImage.TYPE_INTELLECTUAL,
                        PtTechImage.TYPE_CREDIT_SYSTEM));

        orders = Arrays.asList(new OrderItem(PtTechImage.Fields.SORT_INDEX, OrderItem.ASC),
                new OrderItem(PtTechImage.Fields.IMAGE_ID, OrderItem.DESC));

        return super.list(query, pageStart, pageSize, orders);
    }

    @Override
    public PtTechImage add(OsTechImageModel model) throws ServiceException {
        model.setCreateTime(new Date());
        model.setIsVisible(false);
        String fileId = model.getFileId();
        InputStream in = fileStoreService.openStreamForLoad(fileId);
        try {
            BufferedImage image = ImageIO.read(in);
            Integer height = image.getHeight();
            Integer width = image.getWidth();
            //TODO ji suan heng zong bi
        } catch (IOException e) {
            LOGGER.error("Failed to upload picture.", e);
            throw new ServiceException("FailedToUploadPicture", "上传图片失败", e);
        } finally {
            close(in, "上传图片失败");
        }
        ImagePreviewInfo imagePreviewInfo = null;
        switch (model.getCategoryId()) {
            case PtTechImage.TYPE_HUMAN_RESOURCE:
                imagePreviewInfo = imagePreviewService.createPreview(ImagePreviewTypes.TECH_HUMAN_RESOURCE, fileId);
                break;
            case PtTechImage.TYPE_COMPANIES_INFORMATION:
                imagePreviewInfo = imagePreviewService.createPreview(ImagePreviewTypes.TECH_COMPANIES_INFORMATION, fileId);
                break;
            case PtTechImage.TYPE_PROJECT_OSINVEST:
                imagePreviewInfo = imagePreviewService.createPreview(ImagePreviewTypes.TECH_PROJECT_OSINVEST, fileId);
                break;
            case PtTechImage.TYPE_INTELLECTUAL:
                imagePreviewInfo = imagePreviewService.createPreview(ImagePreviewTypes.TECH_INTELLECTUAL, fileId);
                break;
            case PtTechImage.TYPE_CREDIT_SYSTEM:
                imagePreviewInfo = imagePreviewService.createPreview(ImagePreviewTypes.TECH_CREDIT_SYSTEM, fileId);
                break;
            default:
                imagePreviewInfo = imagePreviewService.createPreview(ImagePreviewTypes.TECH_HUMAN_RESOURCE, fileId);
                break;
        }

        String previewFileId = imagePreviewInfo.getPreviewFileId();
        String previewFileName = imagePreviewInfo.getPreviewFileName();
        model.setPreviewFileId(previewFileId);
        model.setPreviewFileName(previewFileName);
        return super.add(model);
    }

    @Override
    public void modify(OsTechImageModel model) throws ServiceException {
        String fileId = model.getFileId();
        InputStream in = fileStoreService.openStreamForLoad(fileId);
        try {
            BufferedImage image = ImageIO.read(in);
            Integer width = image.getWidth();
            Integer height = image.getHeight();
            //TODO jisuanhengzongbi
        } catch (IOException e) {
            LOGGER.error("Failed to upload picture.", e);
            throw new ServiceException("FailedToUploadPicture", "上传图片失败", e);
        } finally {
            close(in, "上传图片失败");
        }
        ImagePreviewInfo imagePreviewInfo = null;
        switch (model.getCategoryId()) {
            case PtTechImage.TYPE_HUMAN_RESOURCE:
                imagePreviewInfo = imagePreviewService.createPreview(ImagePreviewTypes.TECH_HUMAN_RESOURCE, fileId);
                break;
            case PtTechImage.TYPE_COMPANIES_INFORMATION:
                imagePreviewInfo = imagePreviewService.createPreview(ImagePreviewTypes.TECH_COMPANIES_INFORMATION, fileId);
                break;
            case PtTechImage.TYPE_PROJECT_OSINVEST:
                imagePreviewInfo = imagePreviewService.createPreview(ImagePreviewTypes.TECH_PROJECT_OSINVEST, fileId);
                break;
            case PtTechImage.TYPE_INTELLECTUAL:
                imagePreviewInfo = imagePreviewService.createPreview(ImagePreviewTypes.TECH_INTELLECTUAL, fileId);
                break;
            case PtTechImage.TYPE_CREDIT_SYSTEM:
                imagePreviewInfo = imagePreviewService.createPreview(ImagePreviewTypes.TECH_CREDIT_SYSTEM, fileId);
                break;
            default:
                imagePreviewInfo = imagePreviewService.createPreview(ImagePreviewTypes.TECH_HUMAN_RESOURCE, fileId);
                break;
        }

        String previewFileId = imagePreviewInfo.getPreviewFileId();
        String previewFileName = imagePreviewInfo.getPreviewFileName();
        model.setPreviewFileId(previewFileId);
        model.setPreviewFileName(previewFileName);
        super.modify(model);
    }

    /**
     * 设置图片是否可见.
     * @param imageId           图片ID
     * @param visible           是否可见
     * @throws ServiceException 设置失败
     */
    public void setTechImageVisible(Integer imageId, Boolean visible) throws ServiceException {
        queryTechImage(imageId);

        PtTechImage update = new PtTechImage();
        update.setImageId(imageId);
        update.setIsVisible(visible);
        ptTechImageMapper.updateSelectiveById(update);
    }

    /**
     * 查询图片信息.
     * @param imageId   图片ID
     */
    private PtTechImage queryTechImage(Integer imageId) throws ServiceException {
        PtTechImage ptTechImage = ptTechImageMapper.selectById(imageId);
        if(ptTechImage == null){
            LOGGER.error("Image id not found.");
            throw new ServiceException("ImageNotFound", "图片信息不存在");
        }
        return ptTechImage;
    }

    /**
     * 将图片排序信息更新到数据库.
     * @param imageIdList 排序的ID列表
     * @param categoryId  类别ID
     */
    public void updateTechImagesOrder(List<Integer> imageIdList, String categoryId) {
        for (int i = 0; i < imageIdList.size(); i++) {
            PtTechImage update = new PtTechImage();
            update.setImageId(imageIdList.get(i));
            switch (categoryId) {
                case PtTechImage.TYPE_HUMAN_RESOURCE:
                    update.setSortIndex(100 + i * 10);
                    break;
                case PtTechImage.TYPE_COMPANIES_INFORMATION:
                    update.setSortIndex(200 + i * 10);
                    break;
                case PtTechImage.TYPE_PROJECT_OSINVEST:
                    update.setSortIndex(300 + i * 10);
                    break;
                case PtTechImage.TYPE_INTELLECTUAL:
                    update.setSortIndex(400 + i * 10);
                    break;
                case PtTechImage.TYPE_CREDIT_SYSTEM:
                    update.setSortIndex(500 + i * 10);
                    break;
                default:
                    update.setSortIndex(i * 10);
                    break;
            }
            ptTechImageMapper.updateSelectiveById(update);
        }
    }
    public void close(InputStream in,String errorMsg) throws ServiceException{
        if(null != in) {
            try {
                in.close();
            } catch (IOException e) {
                LOGGER.error("fail to close inputStream ", e);
                throw new ServiceException("FailedToCloseInputStream ", errorMsg, e);
            }
        }
    }

}
