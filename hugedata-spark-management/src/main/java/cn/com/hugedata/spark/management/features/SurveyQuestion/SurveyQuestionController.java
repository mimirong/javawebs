package cn.com.hugedata.spark.management.features.SurveyQuestion;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.PageEntity;
import cn.com.hugedata.spark.commons.storage.entity.ItSurvey;
import cn.com.hugedata.spark.commons.storage.entity.ItSurveyQuestion;
import cn.com.hugedata.spark.commons.web.AjaxResponse;
import cn.com.hugedata.spark.commons.web.ErrorPageBuilder;
import cn.com.hugedata.spark.commons.web.features.FeatureControllerImpl;
import cn.com.hugedata.spark.management.features.Survey.SurveyService;


/**
 * 调查题目后台管理.
 */
@Controller
@RequestMapping("/features/SurveyQuestion")
public class SurveyQuestionController
        extends FeatureControllerImpl<ItSurveyQuestion, Integer, SurveyQuestionService, SurveyQuestionModel> {

    @Autowired 
    private SurveyService surveyService;
    
    @Override
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        try {
            ItSurvey survey = ensureSurveyIdParameter(request);
            String title =request.getParameter("title");
            
            ModelAndView mv = super.list(request, response, locale);
            mv.addObject("survey", survey);
            mv.addObject("title", title);
            return mv;
        } catch (ServiceException e) {
            return new ErrorPageBuilder().setErrorTitleText(e.getMessage())
                    .setErrorMessageText(e.getMessage()).create();
        }
    }
    
    /**
     * 检查surveyId参数是否已经存在.
     * @param request           Http请求
     * @throws ServiceException 参数不存在
     */
    private ItSurvey ensureSurveyIdParameter(HttpServletRequest request) throws ServiceException {
        try {
            Integer surveyId = Integer.parseInt(request.getParameter("surveyId"));
            return surveyService.querySurvey(surveyId);
        } catch (NumberFormatException e) {
            throw new ServiceException("SurveyQuestionController-ParameterSurveyIdNotFound", "调查ID不存在");
        }
    }
    
    /**
     * 保存题目排序.
     * @param idList 排序的ID列表
     * @return       处理结果
     */
    @RequestMapping("/setOrder")
    @ResponseBody
    public AjaxResponse setOrder(@RequestParam("idlist[]") List<Integer> idList) {
        service.updateQuestionOrder(idList);
        return AjaxResponse.createSuccessResponse();
    }
    
    /**
     * 保存题目.
     * @param 
     * @return     
     * @throws ServiceException 
     */
    @RequestMapping("/saveQuestion")
    @ResponseBody
    public AjaxResponse saveQuestion(HttpServletRequest request,HttpServletResponse response) throws ServiceException {
    	Integer surveyId = Integer.parseInt(request.getParameter("surveyId"));
    	String questionText = request.getParameter("questionText");
    	String questionType = request.getParameter("questionType");
    	String isRequired = request.getParameter("isRequired");
    	SurveyQuestionModel surveyQuestionModel = new SurveyQuestionModel();
    	surveyQuestionModel.setSurveyId(surveyId);
    	surveyQuestionModel.setQuestionType(questionType);
    	surveyQuestionModel.setQuestionText(questionText);
    	surveyQuestionModel.setIsRequired(new Boolean(isRequired));
    	String itSurveyOptionListStr = request.getParameter("itSurveyOptionList");
    	service.addQuestion(surveyQuestionModel,itSurveyOptionListStr);
    	return AjaxResponse.createSuccessResponse();
        
    }
    /**
     * 修改题目.
     * @param 
     * @return     
     * @throws ServiceException 
     */
    @RequestMapping("/updateQuestion")
    @ResponseBody
    public AjaxResponse updateQuestion(HttpServletRequest request,HttpServletResponse response) throws ServiceException {
    	Integer surveyId = Integer.parseInt(request.getParameter("surveyId"));
    	Integer questionId = Integer.parseInt(request.getParameter("questionId"));
    	String questionText = request.getParameter("questionText");
    	String questionType = request.getParameter("questionType");
    	String isRequired = request.getParameter("isRequired");
    	SurveyQuestionModel surveyQuestionModel = new SurveyQuestionModel();
    	surveyQuestionModel.setSurveyId(surveyId);
    	surveyQuestionModel.setQuestionId(questionId);
    	surveyQuestionModel.setQuestionType(questionType);
    	surveyQuestionModel.setQuestionText(questionText);
    	surveyQuestionModel.setIsRequired(new Boolean(isRequired));
    	String itSurveyOptionListStr = request.getParameter("itSurveyOptionList");
    	service.modifyQuestion(surveyQuestionModel,itSurveyOptionListStr);
    	return AjaxResponse.createSuccessResponse();
        
    }
    @Override
    public  PageEntity<ItSurveyQuestion> listData (
            HttpServletRequest request, 
            HttpServletResponse response,
            @RequestParam("start") int start,
            @RequestParam("length") int length,
            @RequestParam("surveyId") String surveyId,
            Locale locale){
    	    List<ItSurveyQuestion> itSurveyQuestionList = new ArrayList<ItSurveyQuestion>();
    		try {
    			itSurveyQuestionList = surveyService.queryQuestionList(Integer.parseInt(surveyId));
			} catch (NumberFormatException  e) {
				e.printStackTrace();
			}
    		catch (ServiceException  e) {
				e.printStackTrace();
			}
			return new PageEntity<>(itSurveyQuestionList,0,100000,100000);
    	
    }
    
    /**
     * 查询题目.
     * @param 
     * @return     
     * @throws ServiceException 
     */
    @RequestMapping("/queryQuestion")
    @ResponseBody
    public AjaxResponse queryQuestion(HttpServletRequest request,HttpServletResponse response) throws ServiceException {
    	Integer questionId = Integer.parseInt(request.getParameter("questionId"));
    	SurveyQuestionModel surveyQuestionModel = service.selectById(questionId);
    	return AjaxResponse.createSuccessResponse(surveyQuestionModel);
        
    }
    
}
