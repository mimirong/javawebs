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

public class CreateComputingApplyHandler extends BaseHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateComputingApplyHandler.class);
    
    @Autowired
    private OsTechServiceApplyMapper osTechServiceApplyMapper;

    @Override
    public InterfaceResponse execute(JSONObject req) throws ServiceException {
        // 获取参数
        String specCpu = req.getString("specCpu");
        String specMemory = req.getString("specMemory");
        String specDisk = req.getString("specDisk");
        String specBandwidth = req.getString("specBandwidth");
        String specId = req.getString("specId");
        String specName = req.getString("specName");
        Integer totalPrice = req.getInteger("totalPrice");
        Integer serviceDuration = req.getInteger("serviceDuration");
        Integer amount = req.getInteger("amount");
        
        // 检查参数
        if (StringUtils.isEmpty(specCpu)) {
            LOGGER.error("Parameter specCpu is empty.");
            throw new ServiceException("EmptyParameter", "参数specCpu不能为空");
        }
        if (StringUtils.isEmpty(specMemory)) {
            LOGGER.error("Parameter specMemory is empty.");
            throw new ServiceException("EmptyParameter", "参数specMemory不能为空");
        }
        if (StringUtils.isEmpty(specDisk)) {
            LOGGER.error("Parameter specDisk is empty.");
            throw new ServiceException("EmptyParameter", "参数specDisk不能为空");
        }
        if (StringUtils.isEmpty(specBandwidth)) {
            LOGGER.error("Parameter specBandwidth is empty.");
            throw new ServiceException("EmptyParameter", "参数specBandwidth不能为空");
        }
        if (StringUtils.isEmpty(specName)) {
            LOGGER.error("Parameter specName is empty.");
            throw new ServiceException("EmptyParameter", "参数specName不能为空");
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
        
        // 创建申请
        JSONObject specsJson = new JSONObject();
        specsJson.put("cpu", specCpu);
        specsJson.put("memory", specMemory);
        specsJson.put("disk", specDisk);
        specsJson.put("bandwidth", specBandwidth);
        
        OsTechServiceApply apply = new OsTechServiceApply();
        apply.setApplyType(OsTechServiceApply.APPLY_TYPE_COMPUTING);
        apply.setSpecId(specId);
        apply.setSpecName(specName);
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
