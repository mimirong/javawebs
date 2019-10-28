package cn.com.hugedata.spark.management.features.ApprovalStat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.queryentity.ApprovalStatItem;
import cn.com.hugedata.spark.commons.web.AjaxResponse;

@Controller
@RequestMapping("/features/ApprovalStat")
public class ApprovalStatController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApprovalStatController.class);
    
    @Autowired
    private ApprovalStatService approvalStatService;
    
    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("/features/ApprovalStat/ApprovalStat_Index");
        return mv;
    }
    
    @RequestMapping("/queryStatData")
    @ResponseBody
    public AjaxResponse queryStatData(
            @RequestParam("beginDate") String beginTimeStr,
            @RequestParam("endDate") String endTimeStr) {
        try {
            Date beginTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(beginTimeStr + " 00:00:00");
            Date endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTimeStr + " 23:59:59");
            List<ApprovalStatItem> list = approvalStatService.statByUser(beginTime, endTime);
            return AjaxResponse.createSuccessResponse(list);
        } catch (ParseException e) {
            LOGGER.error("Invalid date format.", e);
            return AjaxResponse.createFailResponse("日期格式不正确");
        }
    }
    
    @RequestMapping("/exportDetails")
    public ResponseEntity<byte[]> exportDetails(
            @RequestParam("beginDate") String beginTimeStr,
            @RequestParam("endDate") String endTimeStr,
            @RequestParam("userId") Integer userId) {
        try {
            Date beginTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(beginTimeStr + " 00:00:00");
            Date endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTimeStr + " 23:59:59");
            return approvalStatService.exportDetails(beginTime, endTime, userId);
        } catch (ParseException e) {
            LOGGER.error("Invalid date format.", e);
            throw new RuntimeException("日期格式不正确", e);
        } catch (ServiceException e) {
            LOGGER.error("Failed to export excel.", e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }
    
}
