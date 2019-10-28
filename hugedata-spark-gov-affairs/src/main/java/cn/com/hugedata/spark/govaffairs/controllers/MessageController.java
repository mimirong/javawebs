package cn.com.hugedata.spark.govaffairs.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.PageEntity;
import cn.com.hugedata.spark.commons.storage.entity.UcMessage;
import cn.com.hugedata.spark.commons.web.AjaxResponse;
import cn.com.hugedata.spark.commons.web.login.LoginUtils;
import cn.com.hugedata.spark.govaffairs.services.MessageQueryService;

/**
 * 消息通知.
 */
@Controller
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageQueryService messageQueryService;
    
//    @Autowired
//    private MessageService messageService;
    
    /**
     * 进入消息列表查询页面.
     * @return ModelAndView
     */
    @RequestMapping("/list")
    public ModelAndView list() {

//        try {
//            messageService.createSystemMessage(UcMessage.TYPE_PARK_JOIN_APPLY, "入园申请成功", "Success Success Success ", 3);
//            messageService.createSystemMessage(UcMessage.TYPE_PARK_QUIT_APPLY, "退园成功", "Success Success Success adfasdf", 3);
//            messageService.createSystemMessage(UcMessage.TYPE_FINANCING_APPLY, "融资申请成功", "Success Success Success y4 57ir", 3);
//        } catch (ServiceException e) {
//            e.printStackTrace();
//        }
        
        ModelAndView mv = new ModelAndView("/spark/messages/message_list");
        return mv;
    }
    
    /**
     * 查询用户消息.
     * @param messageType  消息类型
     * @param dateStartStr 查询日期范围开始日期 (yyyy-MM-dd)
     * @param dateEndStr   查询日期范围结束日期 (yyyy-MM-dd)
     * @param pageStart    分页开始记录
     * @param pageSize     分页每页记录数
     * @return             查询结果
     */
    @RequestMapping("/query")
    @ResponseBody
    public AjaxResponse query(
            @RequestParam(value = "messageType", required = false) String messageType,
            @RequestParam(value = "dateStart", required = false) String dateStartStr,
            @RequestParam(value = "dateEnd", required = false) String dateEndStr,
            @RequestParam("pageStart") Integer pageStart,
            @RequestParam("pageSize") Integer pageSize) {
        try {
            // 转换日期格式
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            Date dateStart = null;
            Date dateEnd = null;
            
            if (StringUtils.isNotEmpty(dateStartStr)) {
                dateStart = fmt.parse(dateStartStr);
            }
            if (StringUtils.isNotEmpty(dateEndStr)) {
                dateEnd = fmt.parse(dateEndStr);
            }
            
            // 处理messageType
            if ("".equals(messageType)) {
                messageType = null;
            }
            
            // 查询并返回
            PageEntity<UcMessage> page = messageQueryService.queryMessages(
                    LoginUtils.getCurrentLogin(), messageType, dateStart, dateEnd, pageStart, pageSize);
            return AjaxResponse.createSuccessResponse(page);
            
        } catch (ServiceException e) {
            return AjaxResponse.createFailResponse(e.getMessage());
            
        } catch (ParseException e) {
            return AjaxResponse.createFailResponse("日期格式不正确");
        }
    }
    
    /**
     * 更新消息为已读.
     * @param messageId 消息ID
     * @return          处理结果
     */
    @RequestMapping("/updateReadStatus")
    @ResponseBody
    public AjaxResponse updateReadStatus(@RequestParam("messageId") int messageId) {
        messageQueryService.updateReadStatus(messageId);
        return AjaxResponse.createSuccessResponse();
    }
    
    /**
     * 删除消息.
     * @param messageId 消息ID
     * @return          处理结果
     */
    @RequestMapping("/deleteMessage")
    @ResponseBody
    public AjaxResponse deleteMessage(@RequestParam("messageId") int messageId) {
        messageQueryService.deleteMessage(LoginUtils.getCurrentLogin(), messageId);
        return AjaxResponse.createSuccessResponse();
    }

    /**
     * 查询当前用户的未读消息数.
     * @return
     */
    @RequestMapping("/queryUnreadCount")
    @ResponseBody
    public AjaxResponse queryUnreadCount() {
        int count = messageQueryService.countUnreadMessage();
        return AjaxResponse.createSuccessResponse(count);
    }
}
