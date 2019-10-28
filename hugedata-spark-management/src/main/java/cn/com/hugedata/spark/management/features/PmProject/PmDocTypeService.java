package cn.com.hugedata.spark.management.features.PmProject;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.hugedata.spark.commons.storage.entity.LogOperation;
import cn.com.hugedata.spark.commons.storage.entity.PmDocType;
import cn.com.hugedata.spark.commons.storage.mapper.PmDocTypeMapper;
import cn.com.hugedata.spark.management.features.base.ManagementFeatureServiceImpl;
@Service
public class PmDocTypeService
        extends ManagementFeatureServiceImpl<PmDocType, Integer, PmDocTypeModel, PmDocTypeMapper> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PmDocTypeService.class);
    
   
    @Override
    protected String getLogTargetName() {
        return LogOperation.TARGET_PM_PROJECT;
    }
}
