package cn.com.hugedata.spark.management.features.Interactive;

import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.hugedata.spark.commons.storage.entity.ItMessage;
import cn.com.hugedata.spark.commons.web.AjaxResponse;
import cn.com.hugedata.spark.commons.web.features.FeatureControllerImpl;


@Controller
@RequestMapping("/features/Interactive")
public class InteractiveController
        extends FeatureControllerImpl<ItMessage, Integer, InteractiveService, InteractiveModel> {
	@RequestMapping("/fakeDel")
	@ResponseBody
	public AjaxResponse openAdd(
			HttpServletRequest request, 
			HttpServletResponse response,
			@RequestParam("messageIdList[]") ArrayList<Integer> messageIdList,
			@RequestParam(value="isDeleted",defaultValue="true") Boolean isDeleted,
			Locale locale) {
		return AjaxResponse.createSuccessResponse(service.fakeDel(messageIdList,isDeleted));
	}
	
	@RequestMapping("/toReply")
	@ResponseBody
	public AjaxResponse toReply(
			HttpServletRequest request, 
			HttpServletResponse response,
			@RequestParam("messageId")int messageId,
			Locale locale) {
		
		return AjaxResponse.createSuccessResponse(service.toReply(messageId));
	}

}
