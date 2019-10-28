package cn.com.hugedata.spark.management.features.Survey;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.PageEntity;
import cn.com.hugedata.spark.commons.storage.entity.ItSurvey;
import cn.com.hugedata.spark.commons.storage.entity.ItSurveyQuestionResult;
import cn.com.hugedata.spark.commons.web.AjaxResponse;
import cn.com.hugedata.spark.commons.web.ErrorPageBuilder;
import cn.com.hugedata.spark.commons.web.features.FeatureControllerImpl;

/**
 * 后台"调查征集"管理.
 */
@Controller
@RequestMapping("/features/Survey")
public class SurveyController extends FeatureControllerImpl<ItSurvey, Integer, SurveyService, SurveyModel> {

	/**
	 * 设置调查的发布状态.
	 * 
	 * @param surveyId
	 *            调查ID
	 * @param isPublished
	 *            是否发布
	 * @return 处理结果
	 */
	@RequestMapping("/setPublished")
	@ResponseBody
	public AjaxResponse setPublished(@RequestParam("surveyId") Integer surveyId,
			@RequestParam("isPublished") Boolean isPublished) {
		try {
			int count = service.getQuestionCount(surveyId);
			if(count==0 && isPublished){
				return AjaxResponse.createSuccessResponse("请先配置问题");
			}
			else{
				service.setSurveyPublishStatus(surveyId, isPublished);
				return AjaxResponse.createSuccessResponse();
			}
		} catch (ServiceException e) {
			return AjaxResponse.createFailResponse(e.getMessage());
		}
	}
	

	/**
	 * 查看调查征集结果报表.
	 * 
	 * @param surveyId
	 *            调查ID
	 * @return ModelAndView
	 */
	@RequestMapping("/summary")
	public ModelAndView summary(@RequestParam("surveyId") Integer surveyId, @RequestParam("title") String title) {
		try {
			ModelAndView mv = new ModelAndView("/features/Survey/Survey_Summary");
			mv.addObject("surveyId", surveyId);
			mv.addObject("survey", service.querySurvey(surveyId));
			mv.addObject("title", title);
			return mv;
		} catch (ServiceException e) {
			return new ErrorPageBuilder().setErrorTitleText(e.getMessage()).setErrorMessageText(e.getMessage())
					.create();
		}
	}

	/**
	 * 查看调查征集结果报表.
	 * 
	 * @param surveyId
	 *            调查ID
	 * @return AjaxResponse
	 */
	@RequestMapping("/toUpdate")
	@ResponseBody
	public AjaxResponse toUpdate(@RequestParam("surveyId") Integer surveyId) {
		try {
			ItSurvey itSurvey = service.querySurvey(surveyId);
			return AjaxResponse.createSuccessResponse(itSurvey);
		} catch (ServiceException e) {
			return AjaxResponse.createFailResponse(e.getMessage());
		}
	}

	/**
	 * 获取调查征集结果数据.
	 * 
	 * @param surveyId
	 *            调查ID
	 * @return AjaxResponse
	 */
	@RequestMapping("/summaryData")
	@ResponseBody
	public AjaxResponse summaryData(@RequestParam("surveyId") Integer surveyId) {
		try {
			JSONArray data = service.querySummaryData(surveyId);
			return AjaxResponse.createSuccessResponse(data);
		} catch (ServiceException e) {
			return AjaxResponse.createFailResponse(e.getMessage());
		}
	}

	/**
	 * 获取问题编码查询结果
	 * 
	 * @param questionId
	 *            调查ID
	 * @return 
	 */
	@RequestMapping("/resultData")
	@ResponseBody
	public PageEntity<ItSurveyQuestionResult> resultData(@RequestParam(value = "searchParams", defaultValue = "{}") String searchParams, int start, int length) {
		  Map<String, Integer> map = new TreeMap<String, Integer>();
	        JSONObject obj = JSON.parseObject(searchParams);
	        for (Object key : obj.keySet()) {
	            Object value = obj.get(key.toString());
	            if (value instanceof String) {
	                if ((Integer) value !=null) {
	                    map.put(key.toString(), (Integer)value);
	                }   
	            } else {
	                map.put(key.toString(),(Integer) value);
	            }
	        }
	    Integer questionId =  map.get("questionId");
	    if(questionId==null){
	    	return new PageEntity<ItSurveyQuestionResult>(new ArrayList<ItSurveyQuestionResult>(), 0, 10, 0);
	    }
	    else{   
	    	return service.getResultList(map.get("questionId"), start, length);
	    }
	}
}
