package cn.com.hugedata.spark.commons.service.session;

import java.io.Serializable;

import cn.com.hugedata.spark.commons.storage.entity.UcUserInfo;

/**
 * 用于保存登录信息
 * @author gaopeng
 */
public class LoginSessionInfo implements Serializable {

    private static final long serialVersionUID = 5086483169144866278L;

    /** Token. */
    private String token;
    
    /** 用户信息. */
    private UcUserInfo userInfo;
    
    /** Session创建时间. */
    private Long createTime;
    
    /** 设备类型: ios/android/web. */
    private String platform;
    
    /** 设备型号. */
    private String deviceModel;
    
    /** 设备ID.  IMEI或IDFV */
    private String deviceId;

    /** APP版本. */
    private String version;
    
    /** APP版本. */
    private Integer versionCode;
    
    /** 系统版本. */
    private String systemVersion;
    

    public UcUserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UcUserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSystemVersion() {
        return systemVersion;
    }

    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
    }

    public Integer getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(Integer versionCode) {
        this.versionCode = versionCode;
    }
    
}
