package cn.com.hugedata.spark.commons.storage.querymapper;


import java.util.List;

import cn.com.hugedata.spark.commons.storage.queryentity.SurveySummaryRadioQuestions;

public interface SurveySummaryQueryMapper {

    /** 
     * 查询RADIO类型问题的选择汇总.
     * @param questionId 问题ID
     * @return           汇总结果
     */
    public List<SurveySummaryRadioQuestions> queryRadioSummary(int questionId);
    
}
