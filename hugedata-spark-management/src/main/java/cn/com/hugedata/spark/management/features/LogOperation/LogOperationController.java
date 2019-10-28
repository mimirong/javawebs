package cn.com.hugedata.spark.management.features.LogOperation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.hugedata.spark.commons.storage.entity.LogOperation;

import cn.com.hugedata.spark.commons.web.features.FeatureControllerImpl;
import cn.com.hugedata.spark.commons.web.features.NullModel;

@Controller
@RequestMapping("/features/LogOperation")
public class LogOperationController extends FeatureControllerImpl<LogOperation, Integer, LogOperationService, NullModel> {

}
