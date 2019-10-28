package cn.com.hugedata.spark.management.features.GaServiceGuide;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.hugedata.spark.commons.storage.entity.GaServiceGuide;
import cn.com.hugedata.spark.commons.web.features.FeatureControllerImpl;

@Controller
@RequestMapping("/features/GaServiceGuide")
public class GaServiceGuideController
        extends FeatureControllerImpl<GaServiceGuide, Integer, GaServiceGuideService, GaServiceGuideModel> {

}
