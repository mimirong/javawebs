package cn.com.hugedata.spark.management.utils;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.hugedata.spark.commons.storage.entity.LogOperation;
import cn.com.hugedata.spark.commons.storage.mapper.LogOperationMapper;
import cn.com.hugedata.spark.commons.utils.SpringUtils;
import cn.com.hugedata.spark.commons.web.login.LoginUtils;

/**
 * 用于记录系统日志的工具类.
 * @author gaopeng
 */
public class ManagerOperationLogUtils {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ManagerOperationLogUtils.class);

    private static LogOperationMapper logOperationMapper;
    
    /**
     * 记录系统日志.
     * @param target     操作目标
     * @param targetId   操作目标ID(可以为空)
     * @param targetName 操作目标名称(可以为空)
     * @param operation  操作
     * @param data       数据
     */
    public static void save(String target, Object targetId, String targetName, String operation, String data) {
        if (logOperationMapper == null) {
            logOperationMapper = SpringUtils.getApplicationContext().getBean(LogOperationMapper.class);
        }
        
        LOGGER.debug("Saving operation log: {}, {}, {}, {}", operation, target, targetId, targetName);
        
        // 构造操作目标targetId
        String targetInfo = "";
        if (targetId != null) {
            targetInfo += targetId;
        }
        if (targetName != null) {
            if (targetId != null) {
                targetInfo += "/";
            }
            targetInfo += targetName;
        }
        targetInfo = targetInfo.trim();
        if (targetInfo.length() > 40) {
            targetInfo = targetInfo.substring(0, 37) + "...";
        }
        
        LogOperation log = new LogOperation();
        if (LoginUtils.getCurrentLogin() != null) {
            log.setUserId(LoginUtils.getCurrentLogin().getUserId());
            log.setUsername(LoginUtils.getCurrentLogin().getName() + "/" 
                    + LoginUtils.getCurrentLogin().getLoginName());
        }
        log.setOperationType(LogOperation.OPERATION_TYPE_MANAGEMENT);
        log.setTarget(target);
        log.setTargetInfo(targetInfo);
        log.setOperation(operation);
        log.setCreateTime(new Date());
        log.setData(data);
        logOperationMapper.insertSelective(log);
    }

    /**
     * 记录系统日志.
     * @param target    操作目标
     * @param targetId   操作目标ID(可以为空)
     * @param targetName 操作目标名称(可以为空)
     * @param operation 操作
     */
    public static void save(String target, Object targetId, String targetName, String operation) {
        save(target, targetId, targetName, operation, null);
    }
    
}
