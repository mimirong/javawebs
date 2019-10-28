package cn.com.hugedata.spark.govaffairs.services;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.hugedata.spark.commons.service.PageEntity;
import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.entity.GaServiceGuide;
import cn.com.hugedata.spark.commons.storage.entity.GaServiceGuideAttConfig;
import cn.com.hugedata.spark.commons.storage.entity.GaServiceGuideAttachment;
import cn.com.hugedata.spark.commons.storage.mapper.GaServiceGuideAttConfigMapper;
import cn.com.hugedata.spark.commons.storage.mapper.GaServiceGuideAttachmentMapper;
import cn.com.hugedata.spark.commons.storage.mapper.GaServiceGuideMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;

/**
 * 办事指南查询服务.
 */
@Service
public class ServiceGuideQueryService {

    @Autowired
    private GaServiceGuideMapper gaServiceGuideMapper;

    @Autowired
    private GaServiceGuideAttachmentMapper gaServiceGuideAttachmentMapper;
    
    @Autowired
    private GaServiceGuideAttConfigMapper gaServiceGuideAttConfigMapper;
    
    /**
     * 查询办事指南列表.
     * @param deptCode 部门
     * @param start    分页开始记录
     * @param length   每页记录数
     * @return         分页查询结果
     */
    public PageEntity<GaServiceGuide> list(String deptCode, int start, int length) {
        Map<String, Object> query = MyBatisUtils.buildParameterMap(
                GaServiceGuide.Fields.DEPT_CODE, deptCode,
                GaServiceGuide.Fields.IS_INTERNAL, false,
                OrderItem.ORDER_KEY, Arrays.asList(new OrderItem(GaServiceGuide.Fields.GUIDE_ID, OrderItem.DESC))
        );
        List<GaServiceGuide> data = gaServiceGuideMapper.selectByMap(query, new RowBounds(start, length));
        int count = gaServiceGuideMapper.countByMap(query);
        return new PageEntity<>(data, start, length, count);
    }
    
    /**
     * 查询一个办事指南的详细信息.
     * @param guideId 
     * @return
     */
    public ServiceGuideData queryServiceGuideData(int guideId) {
        // 查询办事指南信息
        GaServiceGuide guide = gaServiceGuideMapper.selectById(guideId);
        if (guide == null) {
            return null;
        }

        // 查询附件信息
        List<GaServiceGuideAttachment> attachmentList = gaServiceGuideAttachmentMapper.selectByMap(MyBatisUtils.params(
                GaServiceGuideAttachment.Fields.GUIDE_ID, guideId,
                new OrderItem(GaServiceGuideAttachment.Fields.SORT_INDEX, OrderItem.ASC)
        ));
        
        // 查询办理材料信息
        List<GaServiceGuideAttConfig> configs = gaServiceGuideAttConfigMapper.selectByMap(MyBatisUtils.params(
                GaServiceGuideAttConfig.Fields.GUIDE_ID, guideId,
                new OrderItem(GaServiceGuideAttConfig.Fields.ATT_CONFIG_ID, OrderItem.ASC)
        ));
        
        // 办理材料信息
        guide.setMaterial(createMaterials(configs));
        
        // 返回
        ServiceGuideData data = new ServiceGuideData();
        data.guide = guide;
        data.attachments = attachmentList;
        data.attConfigs = configs;
        return data;
    }
    
    /**
     * 通过附件配置生成办理材料信息.
     */
    private String createMaterials(List<GaServiceGuideAttConfig> attConfigs) {
        if (attConfigs.isEmpty()) {
            return "";
        }
        
        String first = attConfigs.get(0).getAttConfigName();
        boolean hasNumPrefix = first.startsWith("1、") || first.startsWith("一、") || first.startsWith("（一）"); 
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < attConfigs.size(); i++) {
            GaServiceGuideAttConfig ac = attConfigs.get(i);
            if (!hasNumPrefix) {
                sb.append(i + 1);
                sb.append("、");
            }
            sb.append(ac.getAttConfigName());
            sb.append("\n");
        }
        return sb.toString();
    }
    
    /**
     * 表示一个办事指南的信息.
     */
    public static class ServiceGuideData {

        private GaServiceGuide guide;
        
        private List<GaServiceGuideAttachment> attachments;
        
        private List<GaServiceGuideAttConfig> attConfigs;

        public GaServiceGuide getGuide() {
            return guide;
        }

        public void setGuide(GaServiceGuide guide) {
            this.guide = guide;
        }

        public List<GaServiceGuideAttachment> getAttachments() {
            return attachments;
        }

        public void setAttachments(List<GaServiceGuideAttachment> attachments) {
            this.attachments = attachments;
        }

        public List<GaServiceGuideAttConfig> getAttConfigs() {
            return attConfigs;
        }

        public void setAttConfigs(List<GaServiceGuideAttConfig> attConfigs) {
            this.attConfigs = attConfigs;
        }
        
    }
}
