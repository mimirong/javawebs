package cn.com.hugedata.spark.management.features.Survey;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import cn.com.hugedata.spark.commons.storage.mapper.ItSurveyMapper;
import cn.com.hugedata.spark.commons.storage.mapper.ItSurveyOptionMapper;
import cn.com.hugedata.spark.commons.storage.mapper.ItSurveyQuestionMapper;
import cn.com.hugedata.spark.commons.storage.mapper.ItSurveyQuestionResultMapper;
import cn.com.hugedata.spark.commons.storage.queryentity.SurveySummaryRadioQuestions;
import cn.com.hugedata.spark.commons.storage.querymapper.SurveySummaryQueryMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.commons.web.login.LoginUtils;
import cn.com.hugedata.spark.management.features.SurveyQuestion.SurveyQuestionModel;
import cn.com.hugedata.spark.management.features.base.ManagementFeatureServiceImpl;

/**
 * 后台"调查征集"管理.
 */
@Service
public class SurveyService extends ManagementFeatureServiceImpl<ItSurvey, Integer, SurveyModel, ItSurveyMapper> {

	private static final Logger LOGGER = LoggerFactory.getLogger(SurveyService.class);

	@Autowired
	private ItSurveyMapper itSurveyMapper;

	@Autowired
	private ItSurveyQuestionMapper itSurveyQuestionMapper;

	@Autowired
	private ItSurveyOptionMapper itSurveyOptionMapper;

	@Autowired
	private SurveySummaryQueryMapper surveySummaryQueryMapper;

	@Autowired
	private ItSurveyQuestionResultMapper itSurveyQuestionResultMapper;

	@Override
	protected String getLogTargetName() {
		return null;
	}

	@Override
	public PageEntity<ItSurvey> list(Map<String, Object> query, int pageStart, int pageSize, List<OrderItem> orders) {
		orders = new ArrayList<OrderItem>();
		orders.add(new OrderItem(ItSurvey.Fields.CREATE_TIME, OrderItem.DESC));

		PageEntity<ItSurvey> page = super.list(query, pageStart, pageSize, orders);

		return page;
	}

	@Override
	public ItSurvey add(SurveyModel model) throws ServiceException {
		parseModelDates(model);

		model.setSubmitCount(0);
		model.setCreateTime(new Date());
		model.setUpdateTime(new Date());
		model.setCreatorName(LoginUtils.getCurrentLogin().getName());
		model.setCreatorUserId(LoginUtils.getCurrentLogin().getUserId());
		model.setIsPublished(false);
		return super.add(model);
	}

	@Override
	public SurveyModel queryForModify(Integer id) throws ServiceException {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

		SurveyModel model = super.queryForModify(id);
		model.setStartTimeStr(fmt.format(model.getStartTime()));
		model.setEndTimeStr(fmt.format(model.getEndTime()));
		return model;
	}

	@Override
	public void modify(SurveyModel model) throws ServiceException {
		parseModelDates(model);
		model.setUpdateTime(new Date());
		super.modify(model);
	}

	/**
	 * 根据问卷ID查询问卷包含问题数
	 * 
	 * @param surveyId
	 */
	public Integer getQuestionCount(Integer surveyId){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put(ItSurvey.Fields.SURVEY_ID, surveyId);
		return itSurveyQuestionMapper.countByMap(paramMap);
	}

	/**
	 * 处理日期，将日期字符串转为Date.
	 * 
	 * @param model
	 *            需要处理的Model
	 * @throws ServiceException
	 *             日期格式不正确
	 */
	private void parseModelDates(SurveyModel model) throws ServiceException {
		try {
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (StringUtils.isNotEmpty(model.getStartTimeStr())) {
				model.setStartTime(fmt.parse(model.getStartTimeStr() + " 00:00:00"));
			}
			if (StringUtils.isNotEmpty(model.getEndTimeStr())) {
				model.setEndTime(fmt.parse(model.getEndTimeStr() + " 23:59:59"));
			}
		} catch (ParseException e) {
			LOGGER.error("Invalid date format: {}, {}", model.getStartTimeStr(), model.getEndTimeStr());
			throw new ServiceException("SurveyService-InvalidDateFormat", "日期格式不正确");
		}
	}

	/**
	 * 设置调查的发布状态.
	 * 
	 * @param surveyId
	 *            调查ID
	 * @param isPublished
	 *            是否发布
	 * @throws ServiceException
	 *             设置失败，异常包含错误消息
	 */
	public void setSurveyPublishStatus(int surveyId, boolean isPublished) throws ServiceException {
		querySurvey(surveyId);

		ItSurvey update = new ItSurvey();
		update.setSurveyId(surveyId);
		update.setIsPublished(isPublished);
		itSurveyMapper.updateSelectiveById(update);
	}

	/**
	 * 根据调查ID查询一个调查，如果不存在，抛出异常.
	 * 
	 * @param surveyId
	 *            调查ID
	 * @return 调查信息
	 * @throws ServiceException
	 *             调查信息不存在
	 */
	public ItSurvey querySurvey(int surveyId) throws ServiceException {
		ItSurvey survey = itSurveyMapper.selectById(surveyId);
		if (survey == null) {
			LOGGER.error("Survey id not found: {}", surveyId);
			throw new ServiceException("SurveyService-SurveyIdNotFound", "调查信息不存在");
		}
		return survey;
	}

	/**
	 * 根据调查ID查询问题列表.
	 * 
	 * @param surveyId
	 *            调查ID
	 * @return 汇总数据
	 * @throws ServiceException
	 *             查询失败
	 */
	public List<ItSurveyQuestion> queryQuestionList(int surveyId) throws ServiceException {
		List<ItSurveyQuestion> questionList = itSurveyQuestionMapper.selectByMap(
				MyBatisUtils.buildParameterMap(ItSurveyQuestion.Fields.SURVEY_ID, surveyId, OrderItem.ORDER_KEY,
						Arrays.asList(new OrderItem(ItSurveyQuestion.Fields.SORT_INDEX, OrderItem.ASC))));
		for (ItSurveyQuestion question : questionList) {
			StringBuffer str = new StringBuffer();
			str.append("问题：").append(question.getQuestionText()).append("<br>");
			if ("0".equals(question.getQuestionType()) || "2".equals(question.getQuestionType())) {
				Integer questionId = question.getQuestionId();
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put(ItSurveyOption.Fields.QUESTION_ID, questionId);
				List<ItSurveyOption> itSurveyOptionList = itSurveyOptionMapper.selectByMap(paramMap);
				for (int i = 0; i < itSurveyOptionList.size(); i++) {
					str.append("选项").append(i + 1).append("：")
					        .append(itSurveyOptionList.get(i).getOptionText())
							.append("<br>");
				}
				question.setConfigData(str.toString());
			} else {
				question.setConfigData(str.toString());
				continue;
			}
		}
		return questionList;
	}

	/**
	 * 根据调查ID查询一个调查的汇总数据.
	 * 
	 * @param surveyId
	 *            调查ID
	 * @return 汇总数据
	 * @throws ServiceException
	 *             查询失败
	 */
	public JSONArray querySummaryData(int surveyId) throws ServiceException {
		List<ItSurveyQuestion> questionList = itSurveyQuestionMapper.selectByMap(
				MyBatisUtils.buildParameterMap(ItSurveyQuestion.Fields.SURVEY_ID, surveyId, OrderItem.ORDER_KEY,
						Arrays.asList(new OrderItem(ItSurveyQuestion.Fields.SORT_INDEX, OrderItem.ASC))));
		JSONArray arr = new JSONArray();
		for (ItSurveyQuestion question : questionList) {
			SurveyQuestionModel surveyQuestionModel = new SurveyQuestionModel();
			surveyQuestionModel.setQuestionId(question.getQuestionId());
			surveyQuestionModel.setQuestionText(question.getQuestionText());
			surveyQuestionModel.setQuestionType(question.getQuestionType());
			surveyQuestionModel.setSortIndex(question.getSortIndex());
			surveyQuestionModel.setIsRequired(question.getIsRequired());
			if ("0".equals(question.getQuestionType()) || "2".equals(question.getQuestionType())) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put(ItSurveyOption.Fields.QUESTION_ID, question.getQuestionId());
				List<ItSurveyOption> itSurveyOptionList = itSurveyOptionMapper.selectByMap(paramMap);
				surveyQuestionModel.setItSurveyOptionList(itSurveyOptionList);
			}
			JSONObject obj = (JSONObject) JSON.toJSON(surveyQuestionModel);
			switch (question.getQuestionType()) {
    			case "0":
    				obj.put("summary", queryRadioOptionsSummary(question));
    				break;
    			case "2":
    			    obj.put("summary", queryCheckOptionsSummary(question));
                    break;
			}
			arr.add(obj);
		}
		return arr;
	}

	/**
	 * 查询RADIO类型问题的汇总统计.
	 * 
	 * @param question
	 *            问题信息
	 * @return 统计结果
	 */
	private JSONArray queryRadioOptionsSummary(ItSurveyQuestion question) {
		List<SurveySummaryRadioQuestions> results = surveySummaryQueryMapper
				.queryRadioSummary(question.getQuestionId());

		return (JSONArray) JSON.toJSON(results);
	}

    /**
     * 查询多选类型问题的汇总统计.
     * @param question 问题信息
     * @return 统计结果
     */
    private JSONArray queryCheckOptionsSummary(ItSurveyQuestion question) {
        List<SurveySummaryRadioQuestions> results = surveySummaryQueryMapper
                .queryRadioSummary(question.getQuestionId());
        return (JSONArray) JSON.toJSON(results);
    }

	/**
	 * 查询问题的结果列表
	 * 
	 * @param questionId
	 *            问题信息
	 * @return 统计结果
	 */
	public PageEntity<ItSurveyQuestionResult> getResultList(Integer questionId, int start, int length) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put(ItSurveyQuestionResult.Fields.QUESTION_ID, questionId);
		List<ItSurveyQuestionResult> data = itSurveyQuestionResultMapper.selectByMap(queryMap,
				new RowBounds(start, length));
		int count = itSurveyQuestionResultMapper.countByMap(queryMap);

		return new PageEntity<ItSurveyQuestionResult>(data, start, length, count);
	}

	/**
	 * 删除调查
	 * 
	 * @param surveyIdList
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(List<Integer> surveyIdList) throws ServiceException {
		if (surveyIdList == null || surveyIdList.isEmpty()) {
			throw new ServiceException("surveyId list for deletion is empty", "manager.features.failed_delete");
		}
		// 删除调查
		itSurveyMapper.deleteByIds(surveyIdList);
		// 删除调查对应的问题
		Map<String, Object> deleteMap = new HashMap<String, Object>();
		deleteMap.put(ItSurveyOption.Query.SURVEY_ID__IN, surveyIdList);
		itSurveyQuestionMapper.deleteByMap(deleteMap);
		// 删除问题对应的选项
		itSurveyOptionMapper.deleteByMap(deleteMap);
	}
}
