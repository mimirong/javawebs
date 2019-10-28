package cn.com.hugedata.spark.commons.service.file;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.hugedata.spark.commons.service.UrlManager;

/**
 * 用于处理下载URL的工具类.
 * 
 * @author gaopeng
 */
@Service
public final class FileUrlHelperService {

    /**
     * "http://"前缀.
     */
    private static final String HTTP_PREFIX = "http://";
    
    /**
     * "https://"前缀.
     */
    private static final String HTTPS_PREFIX = "https://";

    @Autowired
    private UrlManager urlManager;
    
    public String getDownloadServiceUrl() {
        String url = urlManager.getDownloadServiceUrl();
        if (!url.endsWith("/")) {
            url += "/";
        }
        return url;
    }
    
    /**
     * 返回去除下载链接中http://host:port部分.
     * @param urlOrFileId HTTP下载链接
     * @return            去除host和port部分的fileId
     */
    public String findFileIdFromUrl(String urlOrFileId) {
        if (urlOrFileId.startsWith(HTTP_PREFIX)) {
            urlOrFileId = urlOrFileId.substring(HTTP_PREFIX.length());
        } else if (urlOrFileId.startsWith(HTTPS_PREFIX)) {
            urlOrFileId = urlOrFileId.substring(HTTPS_PREFIX.length());
        } else {
            return urlOrFileId;
        }
        
        int pos = urlOrFileId.indexOf('/');
        if (pos < 0) {
            throw new IllegalArgumentException("Invalid url.");
        }
        return urlOrFileId.substring(pos + 1);
    }
    
    /**
     * 删除下载链接中attname参数.
     * @param url 下载链接
     * @return    去除attname部分的链接
     */
    public String removeAttnameFromUrl(String url) {
        int pos = url.lastIndexOf('?');
        if (pos < 0) {
            return url;
        }
        return url.substring(0, pos);
    }
    
    /**
     * 修正下载链接地址为系统配置的FastDFS下载URL.
     * @param urlOrFileId 原HTTP下载URL
     * @return    修正后的URL
     */
    public String fixDownloadUrl(String urlOrFileId) {
        if (StringUtils.isEmpty(urlOrFileId)) {
            return null;
        }
        String fileId = findFileIdFromUrl(urlOrFileId);
        return getDownloadServiceUrl() + findFolderName(fileId) + fileId;
    }

    /**
     * 修正下载链接地址为系统配置的FastDFS下载URL并重置下载文件名.
     * @param urlOrFileId     原FastDFS HTTP下载URL
     * @param attname 下载文件名
     * @return        修正后的URL
     */
    public String fixDownloadUrl(String urlOrFileId, String attname) {
        try {
            if (StringUtils.isEmpty(urlOrFileId)) {
                return null;
            }
            String newUrl = removeAttnameFromUrl(fixDownloadUrl(urlOrFileId));
            return newUrl + "?attname=" + URLEncoder.encode(attname, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Failed to encode url", e);
        }
    }
    
    public String findFolderName(String fileId) {
        return fileId.substring(0, 7)
            + "_"
            + fileId.substring(fileId.length() - 1)
            + "/";
    }
}
