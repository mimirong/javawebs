package cn.com.hugedata.spark.commons.service;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 用户获取系统配置的URL.
 */
@Service
public class UrlManager {

    /** 下载服务URL. */
    @Value("${url.downloadServerUrl}")
    private String downloadServiceUrl;
    
    /** 用户管理URL. */
    @Value("${url.umsUrl}")
    private String umsUrl;
    
    /** 政务服务门户URL. */
    @Value("${url.govAffairsPortalUrl}")
    private String govAffairsPortalUrl;
    
    /** 后台管理系统URL. */
    @Value("${url.managementUrl}")
    private String managementUrl;
    
    /** OA地址. */
    @Value("${weaver.webUrl}")
    private String oaWebUrl;

    /** 接口URL. */
    @Value("${url.connectUrl}")
    private String connectUrl;

    /** DataApp URL. */
    @Value("${url.dataAppUrl}")
    private String dataAppUrl;


    /** 接口内网URL. */
    @Value("${url.internal.connectUrl}")
    private String internalConnectUrl;

    /**
     * 获取用户管理URL.
     * @return
     */
    public String getUmsUrl() {
        return fixUrl(umsUrl);
    }

    /**
     * 获取后台管理系统URL.
     * @return
     */
    public String getManagementUrl() {
        return fixUrl(managementUrl);
    }

    /**
     * 获取政务服务门户URL.
     * @return
     */
    public String getGovAffairsPortalUrl() {
        return fixUrl(govAffairsPortalUrl);
    }

    /**
     * 获取下载服务的URL.
     * @return
     */
    public String getDownloadServiceUrl() {
        return fixUrl(downloadServiceUrl);
    }

    /**
     * 获取接口URL.
     * @return
     */
    public String getConnectUrl() {
        return fixUrl(connectUrl);
    }

    /**
     * DataApp URL.
     * @return
     */
    public String getDataAppUrl() {
        return fixUrl(dataAppUrl);
    }

    /**
     * 接口内网URL.
     * @return
     */
    public String getInternalConnectUrl() {
        return internalConnectUrl;
    }

    /**
     * 根据当前请求地址，修正URL.
     */
    private String fixUrl(String url) {
        // 获取请求信息
        HttpServletRequest req = findHttpRequest();
        if (req == null) {
            return url;
        }
        
        String reqUrl = req.getHeader("Wpark-Real-Host");
        if (StringUtils.isEmpty(reqUrl)) {
            reqUrl = req.getRequestURL().toString();
        }
        
        // 修正后的URL
        StringBuilder newUrl = new StringBuilder();
        
        // reqUrl: 处理 "http://" 或者 "https://" 的部分
        if (reqUrl.startsWith("http://")) {
            newUrl.append("http://");
            reqUrl = reqUrl.substring("http://".length());
        } else if (reqUrl.startsWith("https://")) {
            newUrl.append("https://");
            reqUrl = reqUrl.substring("https://".length());
        } else {
            return url;
        }
        
        // server:port部分
        int pos = reqUrl.indexOf('/');
        if (pos < 0) {
            return url;
        }
        String part = reqUrl.substring(0, pos);
        newUrl.append(part);
        
        // 从url获取contextPath部分
        if (url.startsWith("http://")) {
            url = url.substring("http://".length());
        } else if (reqUrl.startsWith("https://")) {
            url = url.substring("https://".length());
        } else {
            return url;
        }
        
        pos = url.indexOf('/');
        if (pos < 0) {
            return url;
        }
        url = url.substring(pos);

        newUrl.append(url);
        return newUrl.toString();
    }
    
    /**
     * 获取当前会话的HttpServletRequest.
     */
    private HttpServletRequest findHttpRequest() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attrs == null) {
            return null;
        }
        HttpServletRequest request = attrs.getRequest();
        return request;
    }

    public String getOaWebUrl() {
        return oaWebUrl;
    }

    public void setOaWebUrl(String oaWebUrl) {
        this.oaWebUrl = oaWebUrl;
    }
}
