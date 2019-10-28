package cn.com.hugedata.spark.commons.service.preview;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.file.FileStoreService;
import cn.com.hugedata.spark.commons.storage.entity.SysFileInfo;
import cn.com.hugedata.spark.commons.storage.entity.SysImagePreview;
import cn.com.hugedata.spark.commons.storage.mapper.SysImagePreviewMapper;
import cn.com.hugedata.spark.commons.utils.ImageResizeUtils;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;

/**
 * 用于处理图片缩略图的服务.
 */
@Service
public class ImagePreviewService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ImagePreviewService.class);

    @Autowired
    private FileStoreService fileStoreService;
    
    @Autowired
    private SysImagePreviewMapper sysImagePreviewMapper;
    
    /**
     * 生成缩略图，如果缩略图已经生成过，返回生成的缩略图信息.
     * @param type                缩略图类型
     * @param originalFileId      原图文件ID
     * @return                    缩略图文件ID和文件名
     * @throws ServiceException   生成缩略图失败
     */
    public ImagePreviewInfo createPreview(ImagePreviewTypes type, String originalFileId) throws ServiceException {
        // 获取文件信息
        SysFileInfo fi = fileStoreService.loadFileInfo(originalFileId);
        if (fi == null) {
            LOGGER.error("Image file id invalid: {}", originalFileId);
            throw new ServiceException("FileIdNotFound", "文件ID不存在");
        }
        
        // 查询已经生成的缩略图
        List<SysImagePreview> oldList = sysImagePreviewMapper.selectByMap(MyBatisUtils.buildParameterMap(
                SysImagePreview.Fields.ORIGINAL_FILE_ID, originalFileId,
                SysImagePreview.Fields.PREVIEW_TYPE, type.getName()
        ));
        if (!oldList.isEmpty()) {
            SysImagePreview preview = oldList.get(0);
            SysFileInfo previewFileInfo = fileStoreService.loadFileInfo(preview.getPreviewFileId());
            if (previewFileInfo != null) {
                ImagePreviewInfo ret = new ImagePreviewInfo();
                ret.setOriginalFileId(originalFileId);
                ret.setOriginalFileName(fi.getFileName());
                ret.setPreviewFileId(previewFileInfo.getFileId());
                ret.setPreviewFileName(previewFileInfo.getFileName());
                return ret;
            }
        }
        
        // 生成缩略图
        String previewFileId = fileStoreService.makeId();
        String previewFileName = FilenameUtils.getBaseName(fi.getFileName()) + "-preview." + type.getExtension();
        
        try (InputStream in = fileStoreService.openStreamForLoad(originalFileId);
                OutputStream out = fileStoreService.openStreamForSave(previewFileId, fi.getFileName())) {
            BufferedImage image = ImageIO.read(in);
            if (image == null) {
                LOGGER.error("Failed to read image.");
                throw new ServiceException("InvalidImage", "无法解析图片");
            }
            
            BufferedImage resized;
            if (type.getResizeMode().equals(ResizeMode.FIT_TARGET_SIZE)) {
                resized = ImageResizeUtils.resizeImage(image, type.getWidth(), type.getHeight());
            } else if (type.getResizeMode().equals(ResizeMode.KEEP_ORIGINAL_RATIO)) {
                resized = ImageResizeUtils.resizeImageWithMaxSize(image, type.getWidth(), type.getHeight());
            } else {
                LOGGER.error("Unsupported resize mode: {}", type.getResizeMode());
                throw new ServiceException("UnsupportedResizeMode", "不支持的缩放模式");
            }
            ImageIO.write(resized, type.getFormat(), out);
            
            // 保存缩略图信息到数据库
            SysImagePreview preview = new SysImagePreview();
            preview.setOriginalFileId(originalFileId);
            preview.setPreviewFileId(previewFileId);
            preview.setWidth(type.getWidth());
            preview.setHeight(type.getHeight());
            preview.setPreviewType(type.getName());
            preview.setCreateTime(new Date());
            sysImagePreviewMapper.insertSelective(preview);
            
            // 返回
            ImagePreviewInfo ret = new ImagePreviewInfo();
            ret.setOriginalFileId(originalFileId);
            ret.setOriginalFileName(fi.getFileName());
            ret.setPreviewFileId(previewFileId);
            ret.setPreviewFileName(previewFileName);
            return ret;
            
        } catch (IOException e) {
            LOGGER.error("Failed to generate preview image.", e);
            throw new ServiceException("FailedToGeneratePreview", "生成缩略图失败", e);
        }
    }
    
}
