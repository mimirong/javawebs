package cn.com.hugedata.spark.admin.utils.apk;

import java.io.File;

/**
 * 解析出的APK信息.
 * @author gaopeng
 */
public class ApkInfo {
    
    /**
     * APK文件大小.
     */
    private Long apkFileSize;

    /**
     * 应用版本代码. 如20
     */
    private Integer versionCode;
    
    /**
     * 应用版本. 如2.0.0
     */
    private String versionName;
    
    /**
     * minSdkVersion值.
     */
    private Integer minSdkVersion;

    /**
     * targetSdkVersion值.
     */
    private Integer targetSdkVersion;
    
    /**
     * APK的package名称.
     */
    private String packageName;
    
    /**
     * 应用名称.
     */
    private String applicationName;
    
    /**
     * 应用图标临时文件(deleteOnExit,使用后可以直接删除).
     */
    private File applicationIcon;
    
    /**
     * 附件信息，ApkParser不会使用这个字段.
     */
    private Object tag;
    
    public Integer getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(Integer versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public Integer getMinSdkVersion() {
        return minSdkVersion;
    }

    public void setMinSdkVersion(Integer minSdkVersion) {
        this.minSdkVersion = minSdkVersion;
    }

    public Integer getTargetSdkVersion() {
        return targetSdkVersion;
    }

    public void setTargetSdkVersion(Integer targetSdkVersion) {
        this.targetSdkVersion = targetSdkVersion;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public File getApplicationIcon() {
        return applicationIcon;
    }

    public void setApplicationIcon(File applicationIcon) {
        this.applicationIcon = applicationIcon;
    }

    public Long getApkFileSize() {
        return apkFileSize;
    }

    public void setApkFileSize(Long apkFileSize) {
        this.apkFileSize = apkFileSize;
    }

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }
}
