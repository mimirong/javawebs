package cn.com.hugedata.spark.management.features.GaMessagePost;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.hugedata.spark.commons.storage.entity.GaMessagePost;
import cn.com.hugedata.spark.commons.web.features.FeatureControllerImpl;


@Controller
@RequestMapping("/features/GaMessagePost")
public class GaMessagePostController
        extends FeatureControllerImpl<GaMessagePost, Integer, GaMessagePostService, GaMessagePostModel> {
	
	
	
	@RequestMapping("/detailData")
	@ResponseBody
	public GaMessagePost detailData(
			HttpServletRequest request, 
			HttpServletResponse response,
			@RequestParam("serviceId") int serviceId,
			Locale locale) {
	    
		return this.service.selectById(serviceId);
	}
}
