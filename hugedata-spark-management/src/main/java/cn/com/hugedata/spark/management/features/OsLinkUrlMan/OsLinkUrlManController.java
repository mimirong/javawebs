package cn.com.hugedata.spark.management.features.OsLinkUrlMan;

import cn.com.hugedata.spark.commons.storage.entity.PtTechImage;
import cn.com.hugedata.spark.commons.web.features.FeatureControllerImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/features/OsLinkUrlMan")
public class OsLinkUrlManController
        extends FeatureControllerImpl<PtTechImage, Integer, OsLinkUrlManService, OsLinkUrlManModel> {


}
