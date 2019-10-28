package cn.com.hugedata.spark.connect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 表示接口返回数据.
 */
public class InterfaceResponse {
    
    /**
     * 返回代码：成功.
     */
    public static final int RESPONSE_SUCCESS = 0;
    
    /**
     * 返回代码：失败.
     */
    public static final int RESPONSE_ERROR = 1;
    
    /**
     * 返回代码：需要登录.
     */
    public static final int RESPONSE_REQUIRE_LOGIN = 9;

    /**
     * 返回代码，0为成功，其他为失败.
     */
    private int result;
    
    /**
     * 返回消息.
     */
    private String message;

    /**
     * 返回数据.
     * JSONObject或JSONData
     */
    private Object data;
    
    /**
     * 用户ID, 不会返回到客户端.
     */
    private Integer extraUserId;
    
    public static InterfaceResponse createSuccessResponse() {
        InterfaceResponse resp = new InterfaceResponse();
        resp.setResult(RESPONSE_SUCCESS);
        resp.setMessage("Success");
        return resp;
    }
    
    public static InterfaceResponse createSuccessResponse(Object data) {
        InterfaceResponse resp = new InterfaceResponse();
        resp.setResult(RESPONSE_SUCCESS);
        resp.setMessage("Success");
        resp.setData(data);
        return resp;
    }
    
    public static InterfaceResponse createFailResponse(String message) {
        InterfaceResponse resp = new InterfaceResponse();
        resp.setResult(RESPONSE_ERROR);
        resp.setMessage(message);
        return resp;
    }
    
    public static InterfaceResponse createFailResponse(int code, String message) {
        InterfaceResponse resp = new InterfaceResponse();
        resp.setResult(code);
        resp.setMessage(message);
        return resp;
    }
    
    /**
     * 将当前接口返回数据转为JSON字符串.
     * @return JSON字符串
     */
    public String toJSONString() {
        JSONObject obj = (JSONObject) JSON.toJSON(this);
        obj.remove("extraUserId");
        return obj.toJSONString();
    }
    
    public int getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public InterfaceResponse setResult(int result) {
        this.result = result;
        return this;
    }

    public InterfaceResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public InterfaceResponse setData(Object data) {
        this.data = data;
        return this;
    }

    public Integer getExtraUserId() {
        return extraUserId;
    }

    public void setExtraUserId(Integer extraUserId) {
        this.extraUserId = extraUserId;
    }
    
}
