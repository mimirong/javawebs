package cn.com.hugedata.spark.commons.web.login;

public interface LoginConstants {

    /**
     * 在Cookie中保存登录Token的key.
     */
    String LOGIN_TOKEN_COOKIE_KEY = "SPARK_LOGIN";
 
    /**
     * 通过请求参数进行登录时传递登录Token的参数名.
     */
    String LOGIN_TOKEN_REQUEST_PARAM = "login";
    
    /**
     * 在Request中保存登录信息的key.
     */
    String LOGIN_SESSION_REQUEST_KEY = "SPARK_LOGIN_SESSION";
    
    /**
     * 在Request中保存登录用户信息的key.
     */
    String LOGIN_USER_REQUEST_KEY = "SPARK_LOGIN";
    
}
