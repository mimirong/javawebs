package cn.com.hugedata.spark.govaffairs.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.commons.exception.HttpBadRequestException;
import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.PageEntity;
import cn.com.hugedata.spark.commons.storage.entity.ItSurvey;
import cn.com.hugedata.spark.commons.web.AjaxResponse;
import cn.com.hugedata.spark.commons.web.login.Login;
import cn.com.hugedata.spark.govaffairs.model.SurveyModel;
import cn.com.hugedata.spark.govaffairs.model.SurveyQuestionModel;
import cn.com.hugedata.spark.govaffairs.services.SurveyService;

/**
 * 办事指南.
 */
@Controller
@RequestMapping("/survey")
public class SurveyController {

	@Autowired
	private SurveyService surveyService;

	@RequestMapping("/list")
	@Login(required = false)
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("/spark/survey/list");
		mv.addObject("menuId", "702");
		return mv;
	}

	@RequestMapping("/listData")
	@ResponseBody
	@Login(required = false)
	public PageEntity<SurveyModel> listData(
			@RequestParam("start") Integer start,
			@RequestParam("length") Integer length) {
		return surveyService.list(start, length);
	}

	@RequestMapping("/toQuestion")
	@Login(required = true)
	public ModelAndView toQuestion(@RequestParam("surveyId") Integer surveyId) {
		ModelAndView mv = new ModelAndView("/spark/survey/questionDetail");
		mv.addObject("surveyId", surveyId);
        mv.addObject("menuId", "702");
		return mv;
	}
	
	@RequestMapping("/questionDetail")
	@ResponseBody
	@Login(required = true)
	public AjaxResponse questionDetail(@RequestParam("surveyId") Integer surveyId) {
		ItSurvey itSurvey = surveyService.surveyDeatil(surveyId);
		List<SurveyQuestionModel> questionList =surveyService.questionlist(surveyId);
		Map<String,Object> retrunMap = new HashMap<String,Object>();
		retrunMap.put("itSurvey", itSurvey);
		retrunMap.put("questionList", questionList);
		return AjaxResponse.createSuccessResponse(retrunMap);
	}
	
	 /**
     * 调查征集提交.
     * @param surveyId       调查征集ID
     * @param submitDataJSON 提交数据JSON
     * @return               处理结果
     */
    @RequestMapping("/resultSubmit")
    @ResponseBody
    public AjaxResponse submit(
            @RequestParam("surveyId") Integer surveyId,
            @RequestParam("data") String submitDataJSON) {
        try {
            JSONObject submitData = JSON.parseObject(submitDataJSON);
            surveyService.submit(surveyId, submitData);
            return AjaxResponse.createSuccessResponse();
        } catch (ServiceException e) {
            return AjaxResponse.createFailResponse(e.getMessage());
        } catch (JSONException e) {
            throw new HttpBadRequestException();
        }
    }
}
