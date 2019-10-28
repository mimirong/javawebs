package cn.com.hugedata.spark.management.features.PmProject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.hugedata.spark.commons.storage.entity.PmDocType;
import cn.com.hugedata.spark.commons.web.features.FeatureControllerImpl;


@Controller
@RequestMapping("/features/PmDocType")
public class PmDocTypeController
        extends FeatureControllerImpl<PmDocType, Integer, PmDocTypeService, PmDocTypeModel> {
    
}
