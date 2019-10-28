package cn.com.hugedata.spark.commons.service.http;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 用于设置和获取全局的代理信息.
 * (Spring Service)
 * 
 * @author gaopeng
 */
@Service
public class HttpProxyManager {
    
    /**
     * 是否启用代理.
     */
    @Value("${httpProxy.enabled}")
    private boolean enabled;

    /**
     * 代理服务器地址.
     */
    @Value("${httpProxy.host}")
    private String httpProxyHost;
    
    /**
     * 代理服务器端口.
     */
    @Value("${httpProxy.port}")
    private int httpProxyPort;
    
    /**
     * 代理服务器用户名.
     */
    @Value("${httpProxy.username}")
    private String httpProxyUsername;
    
    /**
     * 代理服务器密码.
     */
    @Value("${httpProxy.password}")
    private String httpProxyPassword;

    /**
     * 初始化.
     */
    @PostConstruct
    public void init() {
//        System.setProperty("http.proxyHost", httpProxyHost);
//        System.setProperty("http.proxyPort", "" + httpProxyPort);
    }

    public String getHttpProxyHost() {
        return httpProxyHost;
    }

    public void setHttpProxyHost(String httpProxyHost) {
        this.httpProxyHost = httpProxyHost;
    }

    public int getHttpProxyPort() {
        return httpProxyPort;
    }

    public void setHttpProxyPort(int httpProxyPort) {
        this.httpProxyPort = httpProxyPort;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getHttpProxyUsername() {
        return httpProxyUsername;
    }

    public void setHttpProxyUsername(String httpProxyUsername) {
        this.httpProxyUsername = httpProxyUsername;
    }

    public String getHttpProxyPassword() {
        return httpProxyPassword;
    }

    public void setHttpProxyPassword(String httpProxyPassword) {
        this.httpProxyPassword = httpProxyPassword;
    }
    
}
