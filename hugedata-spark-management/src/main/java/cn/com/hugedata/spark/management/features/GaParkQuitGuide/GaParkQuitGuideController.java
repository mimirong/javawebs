package cn.com.hugedata.spark.management.features.GaParkQuitGuide;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.hugedata.spark.commons.storage.entity.GaParkQuitGuide;
import cn.com.hugedata.spark.commons.web.features.FeatureControllerImpl;

@Controller
@RequestMapping("/features/GaParkQuitGuide")
public class GaParkQuitGuideController 
    extends FeatureControllerImpl<GaParkQuitGuide, Integer, GaParkQuitGuideService, GaParkQuitGuideModel> {

}
