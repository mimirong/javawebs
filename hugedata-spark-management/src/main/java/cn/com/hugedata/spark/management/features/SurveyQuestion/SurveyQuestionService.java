package cn.com.hugedata.spark.management.features.SurveyQuestion;



import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.PageEntity;
import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.entity.ItSurveyOption;
import cn.com.hugedata.spark.commons.storage.entity.ItSurveyQuestion;
import cn.com.hugedata.spark.commons.storage.mapper.ItSurveyOptionMapper;
import cn.com.hugedata.spark.commons.storage.mapper.ItSurveyQuestionMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.commons.web.features.FeatureServiceImpl;


/**
 * 管理调查题目的Service.
 */
@Service
public class SurveyQuestionService 
        extends FeatureServiceImpl<ItSurveyQuestion, Integer, SurveyQuestionModel, ItSurveyQuestionMapper> {

    @Autowired
    private ItSurveyQuestionMapper itSurveyQuestionMapper;
    
    @Autowired
    private ItSurveyOptionMapper itSurveyOptionMapper;
    

    @Override
    public PageEntity<ItSurveyQuestion> list(Map<String, Object> query, int pageStart, int pageSize,
            List<OrderItem> orders) {
        orders = Arrays.asList(new OrderItem(ItSurveyQuestion.Fields.SORT_INDEX, OrderItem.ASC));
        return super.list(query, pageStart, pageSize, orders);
    }
    /**
	 * 新增问题
	 * 
	 * @param 
	 * @return 统计结果
	 */
    @Transactional(rollbackFor = Exception.class)
    public void addQuestion(SurveyQuestionModel model,String itSurveyOptionListStr) throws ServiceException {
        if (model.getIsRequired() == null) {
            model.setIsRequired(false);
        }
        
        // 获取最大的一个sortIndex
        List<ItSurveyQuestion> max = itSurveyQuestionMapper.selectByMap(MyBatisUtils.buildParameterMap(
                ItSurveyQuestion.Fields.SURVEY_ID, model.getSurveyId(),
                OrderItem.ORDER_KEY, Arrays.asList(new OrderItem(ItSurveyQuestion.Fields.SORT_INDEX, OrderItem.DESC))
        ));
        if (max != null && !max.isEmpty()) {
            model.setSortIndex(max.get(0).getSortIndex() + 10);
        } else {
            model.setSortIndex(10);
        }
        ItSurveyQuestion itSurveyQuestion =  super.add(model);
         if ("0".equals(model.getQuestionType()) || "2".equals(model.getQuestionType())) {
 	    	List<ItSurveyOption>  itSurveyOptionList =  JSONObject.parseArray(itSurveyOptionListStr, ItSurveyOption.class); 
 	    	this.addItSurveyOption(itSurveyOptionList,itSurveyQuestion.getQuestionId());
     	}
    }
    
    
    /**
	 * 修改问题
	 * 
	 * @param 
	 * @return 统计结果
	 */
    @Transactional(rollbackFor = Exception.class)
    public void modifyQuestion(SurveyQuestionModel model,String itSurveyOptionListStr) throws ServiceException {
        if (model.getIsRequired() == null) {
            model.setIsRequired(false);
        }
        super.modify(model);
        if("0".equals(model.getQuestionType()) || "2".equals(model.getQuestionType())){
    		this.deleteItSurveyOption(model.getQuestionId());
	    	List<ItSurveyOption>  itSurveyOptionList =  JSONObject.parseArray(itSurveyOptionListStr, ItSurveyOption.class); 
	    	this.addItSurveyOption(itSurveyOptionList,model.getQuestionId());
    	}
    }

    /**
     * 将题目的排序信息更新到数据库.
     * @param idlist 排序的ID列表
     */
    public void updateQuestionOrder(List<Integer> idlist) {
        for (int i = 0; i < idlist.size(); i++) {
            ItSurveyQuestion update = new ItSurveyQuestion();
            update.setQuestionId(idlist.get(i));
            update.setSortIndex(i * 10);
            itSurveyQuestionMapper.updateSelectiveById(update);
        }
    }
    /**
     * 将题目选项更新到数据库.
     * @param itSurveyOptionList
     */
    public void addItSurveyOption(List<ItSurveyOption> itSurveyOptionList,Integer questionId){
    	for(ItSurveyOption itSurveyOption:itSurveyOptionList){
    		itSurveyOption.setQuestionId(questionId);
    		itSurveyOptionMapper.insertSelective(itSurveyOption);
    	}
    }
    /**
     * 删除题目选项.
     * @param itSurveyOptionList
     */
    public void deleteItSurveyOption(Integer questionId){
    	Map<String,Object> paramMap = new HashMap<String,Object>();
    	paramMap.put(ItSurveyOption.Fields.QUESTION_ID, questionId);
    	itSurveyOptionMapper.deleteByMap(paramMap);
    }
    
    
    /**
     * 查询题目明细
     * @param questionId
     */
    public SurveyQuestionModel selectById(Integer questionId){
    	ItSurveyQuestion itSurveyQuestion = itSurveyQuestionMapper.selectById(questionId);
    	
    	SurveyQuestionModel surveyQuestionModel = new SurveyQuestionModel();
    	surveyQuestionModel.setQuestionId(itSurveyQuestion.getQuestionId());
    	surveyQuestionModel.setQuestionText(itSurveyQuestion.getQuestionText());
    	surveyQuestionModel.setQuestionType(itSurveyQuestion.getQuestionType());
    	surveyQuestionModel.setIsRequired(itSurveyQuestion.getIsRequired());
    	if ("0".equals(itSurveyQuestion.getQuestionType()) || "2".equals(itSurveyQuestion.getQuestionType())){
	    	Map<String, Object> paramMap = new HashMap<String, Object>();
	    	paramMap.put(ItSurveyOption.Fields.QUESTION_ID, questionId);
	    	List<ItSurveyOption> itSurveyOptionList =  itSurveyOptionMapper.selectByMap(paramMap);
	    	surveyQuestionModel.setItSurveyOptionList(itSurveyOptionList);
    	}
    	return surveyQuestionModel;
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Integer> questionIdList) throws ServiceException {
        if (questionIdList == null || questionIdList.isEmpty()) {
            throw new ServiceException("questionId list for deletion is empty", "manager.features.failed_delete");
        }
        //删除问题
        itSurveyQuestionMapper.deleteByIds(questionIdList);
        //删除问题对应的选项
        Map<String,Object> deleteMap  = new HashMap<String,Object>();
        deleteMap.put(ItSurveyOption.Query.QUESTION_ID__IN, questionIdList);
        itSurveyOptionMapper.deleteByMap(deleteMap);
    }
}
