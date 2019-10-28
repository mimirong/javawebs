package cn.com.hugedata.spark.govaffairs.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.PageEntity;
import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.entity.ItSurvey;
import cn.com.hugedata.spark.commons.storage.entity.ItSurveyOption;
import cn.com.hugedata.spark.commons.storage.entity.ItSurveyQuestion;
import cn.com.hugedata.spark.commons.storage.entity.ItSurveyQuestionResult;
import cn.com.hugedata.spark.commons.storage.entity.ItSurveyResult;
import cn.com.hugedata.spark.commons.storage.entity.UcUserInfo;
import cn.com.hugedata.spark.commons.storage.mapper.ItSurveyMapper;
import cn.com.hugedata.spark.commons.storage.mapper.ItSurveyOptionMapper;
import cn.com.hugedata.spark.commons.storage.mapper.ItSurveyQuestionMapper;
import cn.com.hugedata.spark.commons.storage.mapper.ItSurveyQuestionResultMapper;
import cn.com.hugedata.spark.commons.storage.mapper.ItSurveyResultMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.commons.web.login.LoginUtils;
import cn.com.hugedata.spark.govaffairs.model.SurveyModel;
import cn.com.hugedata.spark.govaffairs.model.SurveyQuestionModel;

/**
 * 办事指南查询服务.
 */
@Service
public class SurveyService {

	@Autowired
	private ItSurveyMapper itSurveyMapper;
	@Autowired
	private ItSurveyQuestionMapper itSurveyQuestionMapper;
	@Autowired
	private ItSurveyOptionMapper itSurveyOptionMapper;
	@Autowired
	private ItSurveyResultMapper itSurveyResultMapper;
	@Autowired
	private ItSurveyQuestionResultMapper itSurveyQuestionResultMapper;

	/**
	 * 查询调查征集问卷列表
	 * 
	 * @param
	 * @return
	 */
	public PageEntity<SurveyModel> list(int start, int length) {
		Map<String, Object> query = MyBatisUtils.buildParameterMap(OrderItem.ORDER_KEY,
				Arrays.asList(new OrderItem(ItSurvey.Fields.CREATE_TIME, OrderItem.DESC)));
		query.put(ItSurvey.Fields.IS_PUBLISHED, true);
		List<ItSurvey> data = itSurveyMapper.selectByMap(query, new RowBounds(start, length));
		List<SurveyModel> surveyModelList = new ArrayList<SurveyModel>();
		UcUserInfo user = LoginUtils.getCurrentLogin();
		for (ItSurvey itSurvey:data){
			SurveyModel surveyModel = new SurveyModel();
			surveyModel.setItSurvey(itSurvey);
			surveyModel.setIsExpired(false);
			//判断用户是否登录，如登录需要查询用户对于的每个问题是否调查过
			if(user!=null){
				Map<String,Object> quertMap = new HashMap<String,Object>();
				quertMap.put(ItSurveyResult.Fields.SURVEY_ID, itSurvey.getSurveyId());
				quertMap.put(ItSurveyResult.Fields.SUBMITTER_USER_ID, user.getUserId());
				Integer submitCount = itSurveyResultMapper.countByMap(quertMap);
				if(submitCount>0){
					surveyModel.setIsExpired(true);
				}
			}
			surveyModelList.add(surveyModel);
		}
		
		int count = itSurveyMapper.countByMap(query);
		return new PageEntity<SurveyModel>(surveyModelList, start, length, count);
	}

	/**
	 * 查询调查征集明细
	 * 
	 * @param
	 * @return 查询结果
	 */
	public ItSurvey surveyDeatil(Integer surveyId) {
		return itSurveyMapper.selectById(surveyId);
	}
	/**
	 * 查询问卷的问题列表
	 * 
	 * @param surveyId
	 * @return 查询结果
	 */
	public List<SurveyQuestionModel> questionlist(Integer surveyId) {
		Map<String, Object> query = MyBatisUtils.buildParameterMap(OrderItem.ORDER_KEY,
				Arrays.asList(new OrderItem(ItSurveyQuestion.Fields.SORT_INDEX, OrderItem.ASC)));
		query.put(ItSurveyQuestion.Fields.SURVEY_ID, surveyId);
		List<ItSurveyQuestion> data = itSurveyQuestionMapper.selectByMap(query);
		List<SurveyQuestionModel> surveyQuestionModelList = new ArrayList<SurveyQuestionModel>();
		for (ItSurveyQuestion itSurveyQuestion : data) {
			SurveyQuestionModel surveyQuestionModel = new SurveyQuestionModel();
			surveyQuestionModel.setSurveyId(itSurveyQuestion.getSurveyId());
			surveyQuestionModel.setQuestionId(itSurveyQuestion.getQuestionId());
			surveyQuestionModel.setQuestionText(itSurveyQuestion.getQuestionText());
			surveyQuestionModel.setQuestionType(itSurveyQuestion.getQuestionType());
			surveyQuestionModel.setIsRequired(itSurveyQuestion.getIsRequired());
			surveyQuestionModel.setSortIndex(itSurveyQuestion.getSortIndex());
			if ("0".equals(itSurveyQuestion.getQuestionType()) || "2".equals(itSurveyQuestion.getQuestionType())) {
				Map<String, Object> paramQuery = MyBatisUtils.buildParameterMap(OrderItem.ORDER_KEY,
						Arrays.asList(new OrderItem(ItSurveyOption.Fields.OPTION_CODE, OrderItem.ASC)));
				paramQuery.put(ItSurveyOption.Fields.QUESTION_ID, itSurveyQuestion.getQuestionId());
				List<ItSurveyOption> optionList = itSurveyOptionMapper.selectByMap(paramQuery);
				surveyQuestionModel.setItSurveyOptionList(optionList);
			}
			surveyQuestionModelList.add(surveyQuestionModel);
		}
		return surveyQuestionModelList;
	}

	/**
	 * 根据调查ID查询一个调查的信息，不包括问题列表. 如果不存在，抛出异常
	 * 
	 * @param surveyId
	 *            调查ID
	 * @return 调查信息
	 * @throws ServiceException
	 *             调查不存在
	 */
	private ItSurvey querySurvey(int surveyId) throws ServiceException {
		ItSurvey survey = itSurveyMapper.selectById(surveyId);
		if (survey == null) {
			throw new ServiceException("SurveySubmitService-SurveyNotFound", "调查信息不存在");
		}
		return survey;
	}

	/**
	 * 查询一个调查的问题列表.
	 * 
	 * @param surveyId
	 *            调查ID
	 * @return 问题列表信息
	 * @throws ServiceException
	 *             查询失败，异常包含错误消息
	 */
	private List<ItSurveyQuestion> queryQuestionList(int surveyId) throws ServiceException {
		List<ItSurveyQuestion> list = itSurveyQuestionMapper.selectByMap(
				MyBatisUtils.buildParameterMap(ItSurveyQuestion.Fields.SURVEY_ID, surveyId, OrderItem.ORDER_KEY,
						Arrays.asList(new OrderItem(ItSurveyQuestion.Fields.SORT_INDEX, OrderItem.ASC))));
		if (list == null || list.isEmpty()) {
			throw new ServiceException("SurveySubmitService-NoQuestionsForSurvey", "调查配置不正确");
		}
		return list;
	}
	
    /**
     * 更新提交计数.
     * @param surveyId 需要更新的调查ID
     */
    private void updateSurveySubmitCount(int surveyId) {
        int submitCount = itSurveyResultMapper.countByMap(MyBatisUtils.buildParameterMap(
                ItSurveyResult.Fields.SURVEY_ID, surveyId
        ));
        ItSurvey surveyUpdate = new ItSurvey();
        surveyUpdate.setSurveyId(surveyId);
        surveyUpdate.setSubmitCount(submitCount);
        itSurveyMapper.updateSelectiveById(surveyUpdate);
    }
    
    /**
     * 保存调查结果
     * @param surveyId
     */
    @Transactional(rollbackFor = Exception.class)
	public void submit(int surveyId, JSONObject data) throws ServiceException {
		querySurvey(surveyId);
		List<ItSurveyQuestion> questionList = queryQuestionList(surveyId);

		// 保存调查信息
		ItSurveyResult surveyResult = new ItSurveyResult();
		surveyResult.setSurveyId(surveyId);
		surveyResult.setSubmitterUserId(LoginUtils.getCurrentLogin().getUserId());
		surveyResult.setSubmitterName(LoginUtils.getCurrentLogin().getName());
		surveyResult.setCreateTime(new Date());
		itSurveyResultMapper.insertSelective(surveyResult);

		// 保存题目答案
		for (ItSurveyQuestion question : questionList) {
			// 获取值
			String value = data.getString("" + question.getQuestionId());

			// 根据类型处理
			List<String> resultValues = new ArrayList<>();
			List<String> resultTexts = new ArrayList<>();
			switch (question.getQuestionType()) {
    			case "1":
    			    resultValues.add("");
    			    resultTexts.add(value);
    				break;
                case "0":
                    resultValues.add(value.split("@@")[0]);
                    resultTexts.add(value.split("@@")[1]);
                    break;
                case "2":
                    {
                        JSONArray values = JSON.parseArray(value);
                        for (int i = 0; i < values.size(); i++) {
                            JSONObject choice = values.getJSONObject(i);
                            resultValues.add(choice.getString("value"));
                            resultTexts.add(choice.getString("text"));
                        }
                    }
                    break;
			}

			// 保存到数据库
			for (int i = 0; i < resultValues.size(); i++) {                
        		ItSurveyQuestionResult result = new ItSurveyQuestionResult();
        		result.setSurveyResultId(surveyResult.getSurveyResultId());
        		result.setQuestionId(question.getQuestionId());
        		result.setSurveyId(surveyId);
        		result.setResult(resultValues.get(i));
        		result.setResultText(resultTexts.get(i));
        		result.setCreateTime(new Date());
        		itSurveyQuestionResultMapper.insertSelective(result);
			}
		}

		// 更新提交计数
		 updateSurveySubmitCount(surveyId);
	}
}
