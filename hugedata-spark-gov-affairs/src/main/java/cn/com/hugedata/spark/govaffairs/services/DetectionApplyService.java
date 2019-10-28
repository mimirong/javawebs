package cn.com.hugedata.spark.govaffairs.services;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.constant.DdDetectionApplyConstants;
import cn.com.hugedata.spark.commons.storage.entity.DdDetectionApply;
import cn.com.hugedata.spark.commons.storage.mapper.DdDetectionApplyMapper;
import cn.com.hugedata.spark.commons.web.login.LoginUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 检验检测Service
 */
@Service
public class DetectionApplyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DetectionApplyService.class);

    @Autowired
    private DdDetectionApplyMapper ddDetectionApplyMapper;

    /**
     * 提交申请
     */
    public void submitDetectionApply(String dataJson) throws ServiceException{
        try {
            JSONObject data = JSON.parseObject(dataJson);

            DdDetectionApply ddDetectionApply = new DdDetectionApply();
            ddDetectionApply.setApplyType(data.getString("categoryId"));
            ddDetectionApply.setStatus(DdDetectionApplyConstants.STATUS_CREATED);
            ddDetectionApply.setResourceId(data.getString("articleId"));
            ddDetectionApply.setResourceName(data.getString("articleName"));
            ddDetectionApply.setCompanyName(data.getString("companyName"));
            ddDetectionApply.setContact(data.getString("contact"));
            ddDetectionApply.setContactName(data.getString("contactName"));
            ddDetectionApply.setComment(data.getString("comment"));
            ddDetectionApply.setApplierUserId(LoginUtils.getCurrentLogin().getUserId());
            ddDetectionApply.setApplierName(LoginUtils.getCurrentLogin().getName());
            ddDetectionApply.setCreateTime(new Date());
            ddDetectionApplyMapper.insertSelective(ddDetectionApply);

        } catch (JSONException e) {
            LOGGER.error("Failed to parse park join apply json.", e);
            throw new ServiceException("InvalidJsonData", "解析数据失败");
        }
    }

}
