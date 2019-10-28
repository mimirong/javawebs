package cn.com.hugedata.spark.commons.service.preview;

public enum ResizeMode {

    /** 直接缩放图片，最终图片宽高比与原图相同. */
    KEEP_ORIGINAL_RATIO,
    
    /** 最终生成的图片大小为目标大小，如果比例不一致，上下或左右会有白边. */
    FIT_TARGET_SIZE,
    
    /** 不支持!! 裁剪上下或左右，生成图片最终大小为目标大小. */
    CROP_TO_TARGET_SIZE
    
}
