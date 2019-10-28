package cn.com.hugedata.spark.management.features.GaServiceGuide;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.PageEntity;
import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.entity.GaServiceGuide;
import cn.com.hugedata.spark.commons.storage.entity.GaServiceGuideAttConfig;
import cn.com.hugedata.spark.commons.storage.entity.GaServiceGuideAttachment;
import cn.com.hugedata.spark.commons.storage.mapper.GaServiceGuideAttConfigMapper;
import cn.com.hugedata.spark.commons.storage.mapper.GaServiceGuideAttachmentMapper;
import cn.com.hugedata.spark.commons.storage.mapper.GaServiceGuideMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.commons.web.features.FeatureServiceImpl;

@Service
public class GaServiceGuideService
        extends FeatureServiceImpl<GaServiceGuide, Integer, GaServiceGuideModel, GaServiceGuideMapper> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GaServiceGuideService.class);
    
    @Autowired
    private GaServiceGuideAttachmentMapper gaServiceGuideAttachmentMapper;

    @Autowired
    private GaServiceGuideAttConfigMapper gaServiceGuideAttConfigMapper;
    
    @Override
    public PageEntity<GaServiceGuide> list(Map<String, Object> query, int pageStart, int pageSize,
            List<OrderItem> orders) {
        query.put(GaServiceGuide.Fields.IS_INTERNAL, false);
        
        orders = new ArrayList<OrderItem>();
        orders.add(new OrderItem(GaServiceGuide.Fields.GUIDE_ID, OrderItem.DESC));
        
        return super.list(query, pageStart, pageSize, orders);
    }
    
    @Override
    public GaServiceGuide add(GaServiceGuideModel model) throws ServiceException {
        model.setCreateTime(new Date());
        model.setUpdateTime(new Date());
        GaServiceGuide guide = super.add(model);
        saveAttachmentData(guide.getGuideId(), model.getAttachmentData());
        saveAttConfigs(guide.getGuideId(), model.getAttConfigJson());
        return guide;
    }

    @Override
    public void modify(GaServiceGuideModel model) throws ServiceException {
        model.setUpdateTime(new Date());
        super.modify(model);
        saveAttachmentData(model.getGuideId(), model.getAttachmentData());
        saveAttConfigs(model.getGuideId(), model.getAttConfigJson());
    }

    @Override
    public GaServiceGuideModel queryForModify(Integer id) throws ServiceException {
        GaServiceGuideModel model = super.queryForModify(id);
        model.setAttachmentData(loadAttachmentData(id));
        model.setAttConfigJson(loadAttConfigs(id));
        return model;
    }

    private void saveAttachmentData(int guideId, String attachmentDataJson) throws ServiceException {
        gaServiceGuideAttachmentMapper.deleteByMap(MyBatisUtils.buildParameterMap(
                GaServiceGuideAttachment.Fields.GUIDE_ID, guideId
        ));
        
        JSONArray array = JSON.parseArray(attachmentDataJson);
        for (int i = 0; i < array.size(); i++) {
            JSONObject item = array.getJSONObject(i);

            GaServiceGuideAttachment att = new GaServiceGuideAttachment();
            att.setAttachmentType(item.getString("type"));
            att.setGuideId(guideId);
            att.setFileId(item.getString("fileId"));
            att.setFileName(item.getString("fileName"));
            att.setSortIndex(i);
            att.setCreateTime(new Date());
            gaServiceGuideAttachmentMapper.insertSelective(att);
        }
    }
    
    private String loadAttachmentData(int guideId) {
        List<GaServiceGuideAttachment> list = gaServiceGuideAttachmentMapper.selectByMap(
                MyBatisUtils.buildParameterMap(
                        GaServiceGuideAttachment.Fields.GUIDE_ID, guideId,
                        OrderItem.ORDER_KEY, Arrays.asList(new OrderItem(GaServiceGuideAttachment.Fields.SORT_INDEX, OrderItem.ASC))
                )
        );
        JSONArray array = new JSONArray();
        for (GaServiceGuideAttachment att : list) {
            JSONObject obj = new JSONObject();
            obj.put("type", att.getAttachmentType());
            obj.put("fileId",  att.getFileId());
            obj.put("fileName", att.getFileName());
            array.add(obj);
        }
        return array.toJSONString();
    }
    
    /**
     * 保存申请材料配置信息.
     */
    private void saveAttConfigs(int guideId, String json) throws ServiceException {
        try {
            // 解析JSON
            JSONArray arr = JSON.parseArray(json);
            
            // 查询原有
            List<GaServiceGuideAttConfig> oldConfigList = gaServiceGuideAttConfigMapper.selectByMap(MyBatisUtils.params(
                    GaServiceGuideAttConfig.Fields.GUIDE_ID, guideId
            ));
            
            Map<Integer, GaServiceGuideAttConfig> oldConfigMap = new TreeMap<>();
            Set<Integer> configToDelete = new TreeSet<>();                          //更新后需要删除的
            
            for (GaServiceGuideAttConfig ac : oldConfigList) {
                oldConfigMap.put(ac.getAttConfigId(), ac);
                configToDelete.add(ac.getAttConfigId());
            }
            
            // 更新和插入
            for (int i = 0; i < arr.size(); i++) {
                JSONObject item = arr.getJSONObject(i);
                Integer attConfigId = item.getInteger("id");
                
                if (attConfigId != null && oldConfigMap.containsKey(attConfigId)) {
                    // 更新
                    GaServiceGuideAttConfig ac = new GaServiceGuideAttConfig();
                    ac.setAttConfigId(attConfigId);
                    ac.setAttConfigName(item.getString("name"));
                    ac.setIsRequired(true);
                    gaServiceGuideAttConfigMapper.updateSelectiveById(ac);
                    
                    configToDelete.remove(attConfigId);
                } else {
                    // 插入
                    GaServiceGuideAttConfig ac = new GaServiceGuideAttConfig();
                    ac.setAttConfigName(item.getString("name"));
                    ac.setGuideId(guideId);
                    ac.setIsRequired(true);
                    ac.setCreateTime(new Date());
                    gaServiceGuideAttConfigMapper.insertSelective(ac);
                }
            }
            
            // 删除操作
            if (!configToDelete.isEmpty()) {
                List<Integer> configToDeleteList = new ArrayList<>();
                configToDeleteList.addAll(configToDelete);
                gaServiceGuideAttConfigMapper.deleteByIds(configToDeleteList);
            }
            
        } catch (JSONException e) {
            LOGGER.error("Failed to parse att config json", e);
            throw new ServiceException("FailedParseAttConfigJson", "解析申请材料信息失败");
        }
    }

    /**
     * 加载申请材料配置信息.
     */
    private String loadAttConfigs(int guideId) {
        List<GaServiceGuideAttConfig> list = gaServiceGuideAttConfigMapper.selectByMap(MyBatisUtils.params(
                GaServiceGuideAttConfig.Fields.GUIDE_ID, guideId,
                new OrderItem(GaServiceGuideAttConfig.Fields.ATT_CONFIG_ID, OrderItem.ASC)
        ));
        JSONArray arr = new JSONArray();
        for (GaServiceGuideAttConfig ac : list) {
            JSONObject obj = new JSONObject();
            obj.put("id", "" + ac.getAttConfigId());
            obj.put("name", ac.getAttConfigName());
            arr.add(obj);
        }
        return arr.toJSONString();
    }
}
