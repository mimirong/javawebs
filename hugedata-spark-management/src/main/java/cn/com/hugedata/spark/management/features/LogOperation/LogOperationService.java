package cn.com.hugedata.spark.management.features.LogOperation;

import org.springframework.stereotype.Service;

import cn.com.hugedata.spark.commons.storage.entity.LogOperation;
import cn.com.hugedata.spark.commons.storage.mapper.LogOperationMapper;
import cn.com.hugedata.spark.commons.web.features.FeatureServiceImpl;
import cn.com.hugedata.spark.commons.web.features.NullModel;

@Service
public class LogOperationService extends FeatureServiceImpl<LogOperation, Integer, NullModel, LogOperationMapper> {

}
