package cn.com.hugedata.spark.govaffairs.services;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.hugedata.spark.commons.service.PageEntity;
import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.entity.ItMessage;
import cn.com.hugedata.spark.commons.storage.mapper.ItMessageMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;

/**
 * 办事指南查询服务.
 */
@Service
public class InteractiveService {

	@Autowired
	private ItMessageMapper itMessageMapper;

	/**
	 * 查询信箱列表
	 * 
	 * @param
	 * @return 查询结果
	 */
	public PageEntity<ItMessage> list(String password, int start, int length) {
		Map<String, Object> query = MyBatisUtils.buildParameterMap(
				OrderItem.ORDER_KEY,Arrays.asList(new OrderItem(ItMessage.Fields.SUBMIT_TIME, OrderItem.DESC)));
		query.put(ItMessage.Fields.IS_DELETED, false);
		Boolean passwordFlag = StringUtils.isNotEmpty(password);
		if(passwordFlag){
			query.put(ItMessage.Fields.PASSWORD, password);
		}
		List<ItMessage> data = itMessageMapper.selectByMap(query, new RowBounds(start, length));
		if(passwordFlag){
			//借用isDeleted字段用来存储查询是否输入密码，如输入可以点击查看详细
			for(ItMessage itMessage:data){
				itMessage.setIsDeleted(passwordFlag);
			}
		}
		int count = itMessageMapper.countByMap(query);
		return new PageEntity<ItMessage>(data, start, length, count);
	}
	
	public Integer writeLetterData(ItMessage itMessage) {
		return itMessageMapper.insertSelective(itMessage);
	}
	
	
	public ItMessage letterDetailData(Integer messageId) {
		return itMessageMapper.selectById(messageId);
	}
}
