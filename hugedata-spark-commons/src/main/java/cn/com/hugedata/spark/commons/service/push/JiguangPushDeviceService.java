package cn.com.hugedata.spark.commons.service.push;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import cn.com.hugedata.spark.commons.storage.entity.UcPushDevice;
import cn.com.hugedata.spark.commons.storage.mapper.UcPushDeviceMapper;

/**
 * 用于管理极光推送的推送设备信息.
 */
@Service
public class JiguangPushDeviceService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(JiguangPushDeviceService.class);

    @Autowired
    private UcPushDeviceMapper ucPushDeviceMapper;
    
    /**
     * 保存或更新推送设备信息.
     * @param registrationId 极光推送的registration_id
     * @param userId         用户id
     * @param platformCode   所属平台
     * @param loginToken     登录Token
     */
    public void updateDevice(String registrationId, Integer userId, String platformCode, String loginToken) {
        UcPushDevice pd = ucPushDeviceMapper.selectById(registrationId);
        if (pd == null) {
            saveDevice(registrationId, userId, platformCode);
        } else {
            LOGGER.info("Updating push device: {}, {}", registrationId, userId);
            pd.setUserId(userId);
            pd.setPlatformCode(platformCode);
            pd.setUpdateTime(new Date());
            pd.setLoginToken(loginToken);
            ucPushDeviceMapper.updateSelectiveById(pd);
        }
    }
    
    /**
     * 保存设备信息.
     * @param registrationId 极光推送的registration_id
     * @param userId         用户id
     * @param platformCode   所属平台
     */
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.SERIALIZABLE)
    private void saveDevice(String registrationId, Integer userId, String platformCode) {
        LOGGER.info("Saving push device: {}, {}", registrationId, userId);
        UcPushDevice pd = ucPushDeviceMapper.selectById(registrationId);
        if (pd == null) {
            pd = new UcPushDevice();
            pd.setRegistrationId(registrationId);
            pd.setUserId(userId);
            pd.setPlatformCode(platformCode);
            pd.setUpdateTime(new Date());
            ucPushDeviceMapper.insertSelective(pd);
        } else {
            LOGGER.warn("Push device failed to save: {}, {}", registrationId, userId);
        }
    }
    
    /**
     * 从数据库删除推送设备登记.
     * @param registrationId 极光推送的registration_id
     */
    public void removeDevice(String registrationId) {
        ucPushDeviceMapper.deleteById(registrationId);
    }
}
