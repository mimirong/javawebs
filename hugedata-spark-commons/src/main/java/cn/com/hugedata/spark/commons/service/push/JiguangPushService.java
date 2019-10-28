package cn.com.hugedata.spark.commons.service.push;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.http.HttpProxyManager;
import cn.com.hugedata.spark.commons.storage.entity.UcPushDevice;
import cn.com.hugedata.spark.commons.storage.mapper.UcPushDeviceMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;

/**
 * 极光推送服务.
 */
@Service
public class JiguangPushService implements PushService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(JiguangPushService.class);
    
    /** 推送REST接口地址. */
    private static final String PUSH_URL = "https://api.jpush.cn/v3/push";
    
    @Autowired
    private UcPushDeviceMapper ucPushDeviceMapper;
    
    @Autowired
    private HttpProxyManager httpProxyManager;
    
    /** 极光推送的appKey. */
    @Value("${push.jiguang.appKey}")
    private String appKey;

    /** 极光推送的masterSecret. */
    @Value("${push.jiguang.masterSecret}")
    private String masterSecret;

    /** 是否使用生产环境配置. */
    @Value("${push.jiguang.isProduction}")
    private Boolean isProduction;

    /**
     * 根据tag推送.
     * @param tags                 需要推送的tag
     * @param alert                推送通知的文本
     * @param title                推送通知的标题
     * @param extras               附加数据
     * @throws ServiceException    推送失败，异常包含错误信息
     */
    public void pushTags(List<String> tags, String alert, String title, JSONObject extras) throws ServiceException {
        JSONObject wrapper = new JSONObject();
        wrapper.put("platform", "all");
        wrapper.put("audience", createAudienceObjectByTags(tags));
        wrapper.put("notification", createNotificationObject(alert, title, extras));
        
        String content = wrapper.toJSONString();
        LOGGER.info("Push by tags: {}, {}", StringUtils.join(tags, ","), content);
        
        executePushRequest(content);
    }
    
    /**
     * 根据uid推送.
     * @param userId                用户id
     * @param alert                推送通知的文本
     * @param title                推送通知的标题
     * @param extras               附加数据
     * @throws ServiceException    推送失败，异常包含错误信息
     */
    public void pushUser(Integer userId, String alert, String title, JSONObject extras) throws ServiceException {
        List<String> registrationIds = getPushIdByUserId(userId);
        LOGGER.info("Registration ids to push: {}", StringUtils.join(registrationIds, ","));
        
        if (registrationIds.isEmpty()) {
            LOGGER.warn("Push failed, no registration id for user: {}", userId);
            return;
        }
        
        JSONObject wrapper = new JSONObject();
        wrapper.put("platform", "all");
        wrapper.put("audience", createAudienceObjectByRegistrationId(registrationIds));
        wrapper.put("notification", createNotificationObject(alert, title, extras));
        wrapper.put("options", createOptionsObject());
        
        String content = wrapper.toJSONString();
        LOGGER.info("Push by registration id: {}, {}", StringUtils.join(registrationIds, ","), content);
        
        executePushRequest(content);
    }

    /**
     * 根据用户id获取极光推送的registration_id.
     * @param userId 用户id
     * @return    registration_id列表
     */
    private List<String> getPushIdByUserId(Integer userId) {
        List<String> list = new ArrayList<String>();
        List<UcPushDevice> devices = ucPushDeviceMapper.selectByMap(MyBatisUtils.buildParameterMap(
                UcPushDevice.Fields.USER_ID, userId
        ));
        for (UcPushDevice pd : devices) {
            list.add(pd.getRegistrationId());
        }
        return list;
    }

    /**
     * 调用推送接口.
     * @param content              POST数据
     * @throws ServiceException    调用接口失败
     */
    private void executePushRequest(String content) throws ServiceException {
        // 设置HTTP代理验证
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        if (httpProxyManager.isEnabled() && StringUtils.isNotEmpty(httpProxyManager.getHttpProxyUsername())) {
            credsProvider.setCredentials(
                    new AuthScope(httpProxyManager.getHttpProxyHost(), httpProxyManager.getHttpProxyPort()),
                    new UsernamePasswordCredentials(httpProxyManager.getHttpProxyUsername(), httpProxyManager.getHttpProxyPassword()));
        }
        
        try (CloseableHttpClient httpClient 
                = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build()) {
            RequestConfig.Builder configBuilder = RequestConfig.custom();
            if (httpProxyManager.isEnabled()) {
                configBuilder.setProxy(new HttpHost(httpProxyManager.getHttpProxyHost(), httpProxyManager.getHttpProxyPort()));
            }

            // POST请求
            HttpPost post = new HttpPost(PUSH_URL);
            post.setConfig(configBuilder.build());
            post.setHeader("Content-Type", "application/json");
            post.setHeader("Authorization", buildAuthorizationHeader(appKey, masterSecret));
            post.setEntity(new StringEntity(content, "UTF-8"));
            try (CloseableHttpResponse resp = httpClient.execute(post)) {
                // 解析响应
                if (resp.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                    String respContent = readResponse(resp.getEntity());
                    LOGGER.error("Failed to send push request: {}, {}", resp.getStatusLine().getStatusCode(), respContent);
                    throw new ServiceException("PushFailed", "推送失败");
                }

                String respContent = readResponse(resp.getEntity());
                System.out.println(respContent);
            }
            
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("Failed to convert string to bytes.", e);
            throw new RuntimeException(e);
            
        } catch (ClientProtocolException e) {
            LOGGER.error("Push failed.", e);
            throw new ServiceException("PushFailed", "推送失败", e);
            
        } catch (IOException e) {
            LOGGER.error("Push failed.", e);
            throw new ServiceException("PushFailed", "推送失败", e);
        }
    }
    
    /**
     * 创建notification段.
     * @param alert
     * @param title
     * @param extras
     * @return
     */
    private JSONObject createNotificationObject(String alert, String title, JSONObject extras) {
        JSONObject android = new JSONObject();
        android.put("alert", alert);
        android.put("title", title);
        android.put("extras", extras);
        
        JSONObject ios = new JSONObject();
        ios.put("alert", alert);
        ios.put("extras", JSON.parseObject(extras.toJSONString()));
        
        JSONObject notification = new JSONObject();
        notification.put("android", android);
        notification.put("ios", ios);
        return notification;
    }
    
    /**
     * 根据一个或多个tag创建audience段.
     * @param tags
     * @return
     */
    private JSONObject createAudienceObjectByTags(List<String> tags) {
        JSONArray tagsArray = new JSONArray();
        tagsArray.addAll(tags);
        
        JSONObject audience = new JSONObject();
        audience.put("tag", tagsArray);
        return audience;
    }
    
    /**
     * 根据registration_id创建audience段.
     * @param registrationIds
     * @return
     */
    private JSONObject createAudienceObjectByRegistrationId(List<String> registrationIds) {
        JSONArray tagsArray = new JSONArray();
        tagsArray.addAll(registrationIds);
        
        JSONObject audience = new JSONObject();
        audience.put("registration_id", tagsArray);
        return audience;
    }
    
    /**
     * 创建options段
     * @return options
     */
    private JSONObject createOptionsObject() {
        JSONObject options = new JSONObject();
        options.put("apns_production", isProduction);
        return options;
    }

    /**
     * 创建HTTP Authorization头的数据.
     * @param username                      用户名
     * @param password                      密码
     * @return                              Authorization头的值，如"BASIC xxxxxx="
     * @throws UnsupportedEncodingException
     */
    private String buildAuthorizationHeader(String username, String password) throws UnsupportedEncodingException {
        String auth = Base64.encodeBase64String((username + ":" + password).getBytes("UTF-8"));
        return "BASIC " + auth;
    }
    
    /**
     * 读取极光服务器返回的数据内容.
     * @param entity                          HTTP返回消息体
     * @return                                文本消息
     * @throws UnsupportedOperationException  读取或解析失败
     * @throws IOException                    读取或解析失败
     */
    private String readResponse(HttpEntity entity) throws UnsupportedOperationException, IOException {
        try (InputStream in = entity.getContent()) {
            StringWriter w = new StringWriter();
            IOUtils.copy(in, w, "UTF-8");
            return w.toString();
        }
    }
    
    //------------------------------------------------------------------------------------------------------------------
    
    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getMasterSecret() {
        return masterSecret;
    }

    public void setMasterSecret(String masterSecret) {
        this.masterSecret = masterSecret;
    }
    
}
