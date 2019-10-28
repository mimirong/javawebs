package cn.com.hugedata.spark.commons.service.preview;

public class ImagePreviewInfo {

    private String originalFileId;
    
    private String originalFileName;

    private String previewFileId;
    
    private String previewFileName;

    public String getOriginalFileId() {
        return originalFileId;
    }

    public void setOriginalFileId(String originalFileId) {
        this.originalFileId = originalFileId;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getPreviewFileId() {
        return previewFileId;
    }

    public void setPreviewFileId(String previewFileId) {
        this.previewFileId = previewFileId;
    }

    public String getPreviewFileName() {
        return previewFileName;
    }

    public void setPreviewFileName(String previewFileName) {
        this.previewFileName = previewFileName;
    }

    
}
