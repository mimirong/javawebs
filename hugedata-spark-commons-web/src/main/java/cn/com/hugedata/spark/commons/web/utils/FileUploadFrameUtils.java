package cn.com.hugedata.spark.commons.web.utils;

import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 用于iframe文件上传组件的工具类.
 * @author gaopeng
 */
public class FileUploadFrameUtils {

    /**
     * 创建成功返回.
     * @param callback Javascript回调函数
     * @return         返回结果
     */
    public static ModelAndView buildSuccessResponse(String internalId) {
        ModelAndView mv = new ModelAndView("redirect:/commons-web/file-upload/toFileUploadResult-simple.aspx");
        mv.addObject("err", "");
        mv.addObject("internalId", internalId);
        mv.addObject("response", "{}");
        return mv;
    }

    /**
     * 创建成功返回.
     * @param callback Javascript回调函数
     * @param response 返回JSON
     * @return         返回结果
     */
    public static ModelAndView buildSuccessResponse(String internalId, JSONObject response) {
        if (response == null) {
            response = new JSONObject();
        }
        ModelAndView mv = new ModelAndView("redirect:/commons-web/file-upload/toFileUploadResult-simple");
        mv.addObject("err", "");
        mv.addObject("internalId", internalId);
        mv.addObject("response", JSON.toJSONString(response));
        return mv;
    }

    /**
     * 创建成功返回.
     * @param callback Javascript回调函数
     * @param response 返回JSON
     * @return         返回结果
     */
    public static ModelAndView buildSuccessResponse(String internalId, String json) {
        if (json == null) {
            json = "{}";
        }
        ModelAndView mv = new ModelAndView("redirect:/commons-web/file-upload/toFileUploadResult-simple");
        mv.addObject("err", "");
        mv.addObject("internalId", internalId);
        mv.addObject("response", json);
        return mv;
    }
    
    /**
     * 创建失败返回.
     * @param callback Javascript回调函数
     * @param err      错误消息
     * @param response 返回结果
     * @return         ModelAndView
     */
    public static ModelAndView buildFailureResponse(String internalId, String err, JSONObject response) {
        if (response == null) {
            response = new JSONObject();
        }
        ModelAndView mv = new ModelAndView("redirect:/commons-web/file-upload/toFileUploadResult-simple");
        mv.addObject("err", err);
        mv.addObject("internalId", internalId);
        mv.addObject("response", JSON.toJSONString(response));
        return mv;
    }
}
