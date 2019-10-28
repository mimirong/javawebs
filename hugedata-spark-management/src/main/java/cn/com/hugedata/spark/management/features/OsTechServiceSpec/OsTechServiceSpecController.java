package cn.com.hugedata.spark.management.features.OsTechServiceSpec;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.hugedata.spark.commons.storage.entity.OsTechServiceSpec;
import cn.com.hugedata.spark.commons.web.features.FeatureControllerImpl;

@Controller
@RequestMapping("/features/OsTechServiceSpec")
public class OsTechServiceSpecController
        extends FeatureControllerImpl<OsTechServiceSpec, Integer, OsTechServiceSpecService, OsTechServiceSpecModel> {

}
