package cn.com.hugedata.spark.govaffairs.services;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.hugedata.spark.commons.service.PageEntity;
import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.entity.OsRequireAtt;
import cn.com.hugedata.spark.commons.storage.entity.OsRequireInfo;
import cn.com.hugedata.spark.commons.storage.mapper.OsRequireAttMapper;
import cn.com.hugedata.spark.commons.storage.mapper.OsRequireInfoMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.commons.web.login.LoginUtils;

/**
 * 检验检测Service
 */
@Service
public class OsRequireService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OsRequireService.class);

    @Autowired
    private OsRequireInfoMapper osRequireMapper;
    
    @Autowired
    private OsRequireAttMapper osRequireAttMapper;

    /**
    * @Title: addRequire
    * @Description: TODO(这里用一句话描述这个方法的作用)
    * @author QianQingzhao
    * @param r
    * @throws
    */
    public void addRequire(OsRequireInfo r)
    {
        osRequireMapper.insertSelective(r);
    }
    
    public void updateRequire(OsRequireInfo r)
    {
        osRequireMapper.updateSelectiveById(r);
    }
    
    public OsRequireInfo getRequireInfoDetail(Integer requireId){
        return osRequireMapper.selectById(requireId);
    }
    
    public List<OsRequireAtt> getRequireAtts(Integer requireId){
        return osRequireAttMapper.selectByMap(MyBatisUtils.buildParameterMap(OsRequireAtt.Fields.REQUIRE_ID,requireId));
    }
    
    
    public PageEntity<OsRequireInfo> listRequires(Integer start, Integer length, String searchWord, String requireArea, String orderType) {
        Date now = new Date();
        Map<String, Object> query = MyBatisUtils.buildParameterMap(
                OsRequireInfo.Fields.DELETED,"0",
                OsRequireInfo.Query.DEAD_DATE__BEGIN,now,
                OrderItem.ORDER_KEY, Arrays.asList(new OrderItem(OsRequireInfo.Fields.PUBLISH_TIME, OrderItem.DESC))
        );
        
        if("按浏览量".equals(orderType)){
            query.put(OrderItem.ORDER_KEY, Arrays.asList(new OrderItem(OsRequireInfo.Fields.READ_NUM, OrderItem.DESC),new OrderItem(OsRequireInfo.Fields.PUBLISH_TIME, OrderItem.DESC)));
        }else{
            query.put(OrderItem.ORDER_KEY, Arrays.asList(new OrderItem(OsRequireInfo.Fields.PUBLISH_TIME, OrderItem.DESC),new OrderItem(OsRequireInfo.Fields.READ_NUM, OrderItem.DESC)));
        }

        if(StringUtils.isNotEmpty(searchWord)) {
            query.put(OsRequireInfo.Query.KEY_WORD__LIKE, searchWord);
        }
        
        if(StringUtils.isNotEmpty(requireArea)) {
            query.put(OsRequireInfo.Fields.REQUIRE_AREA, requireArea);
        }
        

        List<OsRequireInfo> data = osRequireMapper.selectByMap(query, new RowBounds(start, length));
        int count = osRequireMapper.countByMap(query);

        return new PageEntity<>(data, start, length, count);
    }
    
    
    public PageEntity<OsRequireInfo> listMyRequires(Integer start, Integer length, String searchWord) {
        Integer companyId = LoginUtils.getCurrentLogin().getCompanyId();
        if(companyId != null){
            Date now = new Date();
            Map<String, Object> query = MyBatisUtils.buildParameterMap(
                    OsRequireInfo.Fields.DELETED,"0",
                    OsRequireInfo.Fields.COMPANY_ID,companyId,
                    OsRequireInfo.Query.DEAD_DATE__BEGIN,now,
                    OrderItem.ORDER_KEY, Arrays.asList(new OrderItem(OsRequireInfo.Fields.PUBLISH_TIME, OrderItem.DESC))
            );

            if(StringUtils.isNotEmpty(searchWord)) {
                query.put(OsRequireInfo.Query.KEY_WORD__LIKE, searchWord);
            }

            List<OsRequireInfo> data = osRequireMapper.selectByMap(query, new RowBounds(start, length));
            int count = osRequireMapper.countByMap(query);

            return new PageEntity<>(data, start, length, count);
        }else{
            return new PageEntity<>(null, start, length, 0);
        }
        
    }


    /**
    * @Title: deleteRequire
    * @Description: TODO(这里用一句话描述这个方法的作用)
    * @author QianQingzhao
    * @param requireId
    * @throws
    */
    public void deleteRequire(Integer requireId)
    {
        OsRequireInfo r = osRequireMapper.selectById(requireId);
        r.setDeleted("1");
        osRequireMapper.updateSelectiveById(r);
    }

    public List<OsRequireInfo> listRequire() {
        Date now = new Date();
        Map<String, Object> query = MyBatisUtils.buildParameterMap(
                OsRequireInfo.Fields.DELETED,"0",
                OsRequireInfo.Query.DEAD_DATE__BEGIN,now,
                OrderItem.ORDER_KEY, Arrays.asList(new OrderItem(OsRequireInfo.Fields.PUBLISH_TIME, OrderItem.DESC))
        );

       return osRequireMapper.selectByMap(query, new RowBounds(0, 8));
       
    }

}
