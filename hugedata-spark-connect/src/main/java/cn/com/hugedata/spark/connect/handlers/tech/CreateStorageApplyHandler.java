package cn.com.hugedata.spark.connect.handlers.tech;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.entity.OsTechServiceApply;
import cn.com.hugedata.spark.commons.storage.mapper.OsTechServiceApplyMapper;
import cn.com.hugedata.spark.connect.BaseHandler;
import cn.com.hugedata.spark.connect.InterfaceResponse;

public class CreateStorageApplyHandler extends BaseHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateStorageApplyHandler.class);
    
    @Autowired
    private OsTechServiceApplyMapper osTechServiceApplyMapper;

    @Override
    public InterfaceResponse execute(JSONObject req) throws ServiceException {
        // 获取参数
        String size = req.getString("size");
        Integer totalPrice = req.getInteger("totalPrice");
        Integer serviceDuration = req.getInteger("serviceDuration");
        Integer amount = req.getInteger("amount");

        // 检查参数
        if (StringUtils.isEmpty(size)) {
            LOGGER.error("Parameter size is empty.");
            throw new ServiceException("EmptyParameter", "参数size不能为空");
        }
        if (totalPrice == null) {
            LOGGER.error("Parameter totalPrice is empty.");
            throw new ServiceException("EmptyParameter", "参数totalPrice不能为空");
        }
        if (serviceDuration == null) {
            LOGGER.error("Parameter serviceDuration is empty.");
            throw new ServiceException("EmptyParameter", "参数serviceDuration不能为空");
        }
        if (amount == null) {
            LOGGER.error("Parameter amount is empty.");
            throw new ServiceException("EmptyParameter", "参数amount不能为空");
        }

        // 创建
        JSONObject specsJson = new JSONObject();
        specsJson.put("size", size);
        
        OsTechServiceApply apply = new OsTechServiceApply();
        apply.setApplyType(OsTechServiceApply.APPLY_TYPE_STORAGE);
        apply.setSpecId(size);
        apply.setSpecName(size);
        apply.setRemark(null);
        apply.setServiceDuration(serviceDuration);
        apply.setServiceStart(null);
        apply.setServiceEnd(null);
        apply.setAmount(amount);
        apply.setPrice(totalPrice);
        apply.setStatus(OsTechServiceApply.STATUS_CREATED);
        apply.setCompanyName(getLogin().getCompanyName());
        apply.setContactName(getLogin().getName());
        apply.setContactMobile(getLogin().getMobile());
        apply.setApplierId(getLogin().getUserId());
        apply.setApplierName(getLogin().getName());
        apply.setExtraData(specsJson.toJSONString());
        apply.setCreateTime(new Date());
        apply.setUpdateTime(new Date());;
        osTechServiceApplyMapper.insertSelective(apply);

        JSONObject resp = new JSONObject();
        resp.put("applyId", apply.getApplyId());
        return InterfaceResponse.createSuccessResponse(resp);
    }

}
