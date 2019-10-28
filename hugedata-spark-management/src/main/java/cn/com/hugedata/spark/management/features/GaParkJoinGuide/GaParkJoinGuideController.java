package cn.com.hugedata.spark.management.features.GaParkJoinGuide;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.hugedata.spark.commons.storage.entity.GaParkJoinGuide;
import cn.com.hugedata.spark.commons.web.features.FeatureControllerImpl;

@Controller
@RequestMapping("/features/GaParkJoinGuide")
public class GaParkJoinGuideController 
        extends FeatureControllerImpl<GaParkJoinGuide, Integer, GaParkJoinGuideService, GaParkJoinGuideModel> {

}
