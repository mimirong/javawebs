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

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.PageEntity;
import cn.com.hugedata.spark.commons.service.preview.ImagePreviewInfo;
import cn.com.hugedata.spark.commons.service.preview.ImagePreviewService;
import cn.com.hugedata.spark.commons.service.preview.ImagePreviewTypes;
import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.entity.SpProjectImage;
import cn.com.hugedata.spark.commons.storage.entity.SpServiceProject;
import cn.com.hugedata.spark.commons.storage.entity.SpSpecification;
import cn.com.hugedata.spark.commons.storage.mapper.SpProjectImageMapper;
import cn.com.hugedata.spark.commons.storage.mapper.SpServiceProjectMapper;
import cn.com.hugedata.spark.commons.storage.mapper.SpSpecificationMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.commons.web.login.LoginUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Service
public class SpServiceProjectService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpServiceProjectService.class);

    @Autowired
    private SpServiceProjectMapper spServiceProjectMapper;

    @Autowired
    private SpSpecificationMapper spSpecificationMapper;

    @Autowired
    private SpProjectImageMapper spProjectImageMapper;

    @Autowired
    private ImagePreviewService imagePreviewService;

    /**
     * 查询服务项目列表.
     * @param supplyType    供应类型
     * @param serviceField  服务领域
     * @param sortType      排序方式
     * @param searchWord    搜索关键字
     * @param start         分页开始记录
     * @param length        每页记录数
     * @return
     */
    public PageEntity<SpServiceProject> listData(String supplyType, String serviceField, String sortType, String searchWord,
                                                 Integer start, Integer length) {
        Map<String, Object> query = MyBatisUtils.buildParameterMap(
                SpServiceProject.Fields.SUPPLY_TYPE, supplyType
        );
        if (!StringUtils.isEmpty(serviceField)) {
            query.put(SpServiceProject.Fields.SERVICE_FIELD, serviceField);
        }
        if (SpServiceProject.QUERY_SORT_VISITOR_COUNT.equals(sortType)) {
            query.put(OrderItem.ORDER_KEY, Arrays.asList(new OrderItem(SpServiceProject.Fields.VISITOR_COUNT, OrderItem.DESC)));
        } else if (SpServiceProject.QUERY_SORT_LATEST_PUBLISH.equals(sortType)) {
            query.put(OrderItem.ORDER_KEY, Arrays.asList(new OrderItem(SpServiceProject.Fields.CREATE_TIME, OrderItem.DESC)));
        }
        if (!StringUtils.isEmpty(searchWord)) {
            query.put(SpServiceProject.Query.SEARCH_KEY__LIKE, searchWord);
        }
        List<SpServiceProject> data = spServiceProjectMapper.selectByMap(query, new RowBounds(start, length));
        int count = spServiceProjectMapper.countByMap(query);
        return new PageEntity<>(data, start, length, count);
    }

    /**
     * 提交服务项目
     * @param dataJson
     */
    public void submitServiceProject(String dataJson) throws ServiceException {
        try {
            JSONObject data = JSON.parseObject(dataJson);

            SpServiceProject spServiceProject = new SpServiceProject();
            spServiceProject.setServiceField(data.getString("serviceField"));
            spServiceProject.setSupplyType(data.getString("supplyType"));
            spServiceProject.setSupplyTheme(data.getString("supplyTheme"));
            spServiceProject.setSupplyBrief(data.getString("supplyBrief"));
            // Update coverFileId and coverFileName in saveImage().
            spServiceProject.setDetailDesc(data.getString("detailDesc"));
            spServiceProject.setSearchKey(data.getString("searchKey"));
            spServiceProject.setAddressProvince(data.getString("addressProvince"));
            spServiceProject.setAddressDetail(data.getString("addressDetail"));
            spServiceProject.setContact(data.getString("contact"));
            spServiceProject.setContactMobile(data.getString("contactMobile"));
            spServiceProject.setEmail(data.getString("email"));
            spServiceProject.setVisitorCount(0);
            spServiceProject.setPrice(getServiceProjectPrice(data.getJSONArray("specData")));
            spServiceProject.setPublisherUserId(LoginUtils.getCurrentLogin().getUserId());
            spServiceProject.setPublisherName(LoginUtils.getCurrentLogin().getName());
            spServiceProject.setCompanyId(LoginUtils.getCurrentLogin().getCompanyId());
            spServiceProject.setCompanyName(LoginUtils.getCurrentLogin().getCompanyName());
            spServiceProject.setCreateTime(new Date());
            spServiceProjectMapper.insertSelective(spServiceProject);

            saveSpecifications(data.getJSONArray("specData"), spServiceProject.getProjectId());
            saveProjectImage(data.getJSONArray("uploadImageData"), spServiceProject.getProjectId());

        } catch (Exception e) {
            LOGGER.error("Failed to parse service project json.", e);
            throw new ServiceException("InvalidJsonData", "解析数据失败");
        }
    }

    /**
     * 修改服务项目
     * @param dataJson
     */
    public void modifyServiceProject(String dataJson) throws ServiceException {
        try {
            JSONObject data = JSON.parseObject(dataJson);

            SpServiceProject spServiceProject = new SpServiceProject();
            spServiceProject.setProjectId(data.getInteger("projectId"));
            spServiceProject.setServiceField(data.getString("serviceField"));
            spServiceProject.setSupplyType(data.getString("supplyType"));
            spServiceProject.setSupplyTheme(data.getString("supplyTheme"));
            spServiceProject.setSupplyBrief(data.getString("supplyBrief"));
            // Update coverFileId and coverFileName in saveImage().
            spServiceProject.setDetailDesc(data.getString("detailDesc"));
            spServiceProject.setSearchKey(data.getString("searchKey"));
            spServiceProject.setAddressProvince(data.getString("addressProvince"));
            spServiceProject.setAddressDetail(data.getString("addressDetail"));
            spServiceProject.setContact(data.getString("contact"));
            spServiceProject.setContactMobile(data.getString("contactMobile"));
            spServiceProject.setEmail(data.getString("email"));
            spServiceProject.setPrice(getServiceProjectPrice(data.getJSONArray("specData")));
            spServiceProject.setPublisherUserId(LoginUtils.getCurrentLogin().getUserId());
            spServiceProject.setPublisherName(LoginUtils.getCurrentLogin().getName());
            spServiceProject.setCompanyId(LoginUtils.getCurrentLogin().getCompanyId());
            spServiceProject.setCompanyName(LoginUtils.getCurrentLogin().getCompanyName());
            spServiceProject.setUpdateTime(new Date());
            spServiceProjectMapper.updateSelectiveById(spServiceProject);

            saveSpecifications(data.getJSONArray("specData"), spServiceProject.getProjectId());
            saveProjectImage(data.getJSONArray("uploadImageData"), spServiceProject.getProjectId());

        } catch (Exception e) {
            LOGGER.error("Failed to parse service project json.", e);
            throw new ServiceException("InvalidJsonData", "解析数据失败");
        }
    }

    /**
     * 保存图片、生成缩略图以及生成封面.
     * @param imageArray   图片数据
     * @param projectId         服务项目ID
     */
    private void saveProjectImage(JSONArray imageArray, Integer projectId) throws ServiceException {
        // -1- 删除该项目所有图片
        spProjectImageMapper.deleteByMap(MyBatisUtils.buildParameterMap(
                SpProjectImage.Fields.PROJECT_ID, projectId
        ));
        // -2- 如果没有图片需要删除封面
        if (imageArray == null || imageArray.size() == 0) {
            SpServiceProject spServiceProject = new SpServiceProject();
            spServiceProject.setProjectId(projectId);
            spServiceProject.setCoverFileId("");
            spServiceProject.setCoverFileName("");
            spServiceProject.setUpdateTime(new Date());
            spServiceProjectMapper.updateSelectiveById(spServiceProject);
        }
        // -3- 有图片时生成封面并保存
        else {
            for (int i = 0; i < imageArray.size(); i++) {
                // 生成缩略图
                String fileId = imageArray.getJSONObject(i).getString("fileId");
                ImagePreviewInfo original = imagePreviewService.createPreview(ImagePreviewTypes.OS_SERVICE_PROJECT_ORIGINAL, fileId);
                ImagePreviewInfo prev = imagePreviewService.createPreview(ImagePreviewTypes.OS_SERVICE_PROJECT_PREVIEW, fileId);

                SpProjectImage spProjectImage = new SpProjectImage();
                spProjectImage.setProjectId(String.valueOf(projectId));
                spProjectImage.setFileId(original.getPreviewFileId());
                spProjectImage.setFileName(original.getPreviewFileName());
                spProjectImage.setPreviewFileId(prev.getPreviewFileId());
                spProjectImage.setPreviewFileName(prev.getPreviewFileName());
                spProjectImage.setSortIndex(i);
                spProjectImage.setCreateTime(new Date());
                spProjectImageMapper.insertSelective(spProjectImage);

                // 第一张图设置为封面
                if (i == 0) {
                    ImagePreviewInfo cover = imagePreviewService.createPreview(ImagePreviewTypes.OS_SERVICE_PROJECT_COVER, fileId);
                    SpServiceProject spServiceProject = new SpServiceProject();
                    spServiceProject.setProjectId(projectId);
                    spServiceProject.setCoverFileId(cover.getPreviewFileId());
                    spServiceProject.setCoverFileName(cover.getPreviewFileName());
                    spServiceProject.setUpdateTime(new Date());
                    spServiceProjectMapper.updateSelectiveById(spServiceProject);
                }
            }
        }

    }

    /**
     * 保存规格信息.
     * @param specArray      规格信息数组
     * @param projectId     服务项目ID
     */
    private void saveSpecifications(JSONArray specArray, Integer projectId) throws ServiceException {
        // -1- 删除该服务项目已有规格
        spSpecificationMapper.deleteByMap(MyBatisUtils.buildParameterMap(SpSpecification.Fields.PROJECT_ID,projectId));
        // -2- 重新添加
        if (specArray != null || specArray.size() > 0) {
//            List<SpSpecification> specificationList = new ArrayList<SpSpecification>();
            for (int i = 0, size = specArray.size(); i < size; i++) {
                SpSpecification spSpecification = new SpSpecification();
                spSpecification.setSpecName(specArray.getJSONObject(i).getString("specName"));
                spSpecification.setProjectId(String.valueOf(projectId));
                int isNegotiablePrice = specArray.getJSONObject(i).getInteger("isNegotiablePrice");
                if (isNegotiablePrice == 1) {
                    spSpecification.setIsNegotiablePrice(true);
                } else {
                    spSpecification.setIsNegotiablePrice(false);
                    spSpecification.setReferPrice(specArray.getJSONObject(i).getFloat("referPrice"));
                    spSpecification.setMeasureUnit(specArray.getJSONObject(i).getString("measureUnit"));
                    spSpecification.setPriceUnit(specArray.getJSONObject(i).getString("priceUnit"));
                }
                spSpecification.setCreateTime(new Date());
//                specificationList.add(spSpecification);
                spSpecificationMapper.insertSelective(spSpecification);
            }
        }
    }

    /**
     * 获取服务项目价格.
     * @param specArray  规格数组
     * @return
     */
    private String getServiceProjectPrice(JSONArray specArray) throws ServiceException {
        String priceStr = "价格面议";
        if (specArray != null || specArray.size() > 0) {
            float minPrice = 0f;
            float maxPrice = 0f;
            String minPriceStr = "0元";
            String maxPriceStr = "0元";
            for (int i = 0, size = specArray.size(); i < size; i++) {
                int isNegotiablePrice = specArray.getJSONObject(i).getInteger("isNegotiablePrice");
                if (isNegotiablePrice == 1) {
                    continue;
                }
                float referPrice = specArray.getJSONObject(i).getFloat("referPrice");
                String priceUnit = specArray.getJSONObject(i).getString("priceUnit");

                float curSpecPrice = referPrice;
                String curPriceUnitStr = "元";
                switch (priceUnit) {
                    //万元
                    case SpSpecification.PRICE_UNIT_TEN_THOUSAND_YUAN:
                        curSpecPrice = referPrice * 10000;
                        curPriceUnitStr = "万元";
                        break;
                    // 百万元
                    case SpSpecification.PRICE_UNIT_MILLION_YUAN:
                        curSpecPrice = referPrice * 1000000;
                        curPriceUnitStr = "百万元";
                        break;
                    case SpSpecification.PRICE_UNIT_YUAN:
                    default:
                        curSpecPrice = referPrice;
                        curPriceUnitStr = "元";
                        break;
                }
                if (i == 0) {
                    minPrice = curSpecPrice;
                    maxPrice = curSpecPrice;
                    minPriceStr = referPrice + curPriceUnitStr;
                    maxPriceStr = referPrice + curPriceUnitStr;
                } else {
                    if (curSpecPrice < minPrice) {
                        minPrice = curSpecPrice;
                        minPriceStr = referPrice + curPriceUnitStr;
                    }
                    if (curSpecPrice > maxPrice) {
                        maxPrice = curSpecPrice;
                        maxPriceStr = referPrice + curPriceUnitStr;
                    }
                }
                priceStr = minPriceStr + "~" + maxPriceStr;
            }
        }
        return priceStr;
    }

    /**
     * 查询我的发布.
     * @param start         分页开始记录
     * @param length        每页记录数
     * @return
     */
    public PageEntity<SpServiceProject> listMyRelease(Integer start, Integer length) {
        Map<String, Object> query = MyBatisUtils.buildParameterMap(
                SpServiceProject.Query.SUPPLY_TYPE__IN, Arrays.asList(
                        SpServiceProject.SP_SUPPLY_TYPE_INSPECT_DETEC,
                        SpServiceProject.SP_SUPPLY_TYPE_TECH_SERVICE),
                SpServiceProject.Query.SERVICE_FIELD__IN, Arrays.asList(
                        SpServiceProject.SP_SERVICE_FIELD_OTHER,
                        SpServiceProject.SP_SERVICE_FIELD_CHEMISTRY,
                        SpServiceProject.SP_SERVICE_FIELD_CONSTRUCTION ,
                        SpServiceProject.SP_SERVICE_FIELD_NDT,
                        SpServiceProject.SP_SERVICE_FIELD_CONSUMER_GOODS,
                        SpServiceProject.SP_SERVICE_FIELD_ENVIRON_HEALTH,
                        SpServiceProject.SP_SERVICE_FIELD_BIOLOGY_EQUIPMENT,
                        SpServiceProject.SP_SERVICE_FIELD_ELECTRONICS_MECHANICAL,
                        SpServiceProject.SP_SERVICE_FIELD_EMC,
                        SpServiceProject.SP_SERVICE_FIELD_SPIN_FIBER ,
                        SpServiceProject.SP_SERVICE_FIELD_SOFTWARE_INFO,
                        SpServiceProject.SP_SERVICE_FIELD_METAL_MATERIAL,
                        SpServiceProject.SP_SERVICE_FIELD_FOOD_FARM_PRODUCTS
                ),
                OrderItem.ORDER_KEY, Arrays.asList(new OrderItem(SpServiceProject.Fields.CREATE_TIME, OrderItem.DESC),
                        new OrderItem(SpServiceProject.Fields.VISITOR_COUNT, OrderItem.DESC)),
                SpServiceProject.Fields.PUBLISHER_USER_ID, LoginUtils.getCurrentLogin().getUserId()
        );

        List<SpServiceProject> data = spServiceProjectMapper.selectByMap(query, new RowBounds(start, length));
        int count = spServiceProjectMapper.countByMap(query);
        return new PageEntity<>(data, start, length, count);
    }

    /**
     * 删除我的发布.
     * @param projectId 服务项目ID
     */
    public void deleteMyRelease(Integer projectId) throws ServiceException {
        // 删除服务项目
        Map<String, Object> deleteServiceProject = MyBatisUtils.buildParameterMap(
                SpServiceProject.Fields.PROJECT_ID, projectId,
                SpServiceProject.Fields.PUBLISHER_USER_ID, LoginUtils.getCurrentLogin().getUserId()
        );
        spServiceProjectMapper.deleteByMap(deleteServiceProject);

        // 删除服务项目规格
        Map<String, Object> deleteSpecification = MyBatisUtils.buildParameterMap(
                SpSpecification.Fields.PROJECT_ID, projectId
        );
        spSpecificationMapper.deleteByMap(deleteSpecification);

        // 删除服务项目图片
        Map<String, Object> deleteProjectImage = MyBatisUtils.buildParameterMap(
                SpProjectImage.Fields.PROJECT_ID, projectId
        );
        spProjectImageMapper.deleteByMap(deleteProjectImage);
    }

    /**
     * 根据服务项目ID查询服务项目详情.
     * @param projectId     服务项目ID
     * @return
     */
    public JSONObject queryServiceProjectDetail(Integer projectId) throws ServiceException {
        JSONObject object = new JSONObject();
        SpServiceProject spServiceProject = spServiceProjectMapper.selectById(projectId);
        if (spServiceProject == null) {
            throw new ServiceException("InvalidJsonData", "解析数据失败");
        }
        object.put("serviceProject", spServiceProject);

        List<SpSpecification> spSpecificationList = spSpecificationMapper.selectByMap(MyBatisUtils.buildParameterMap(
                SpSpecification.Fields.PROJECT_ID, projectId
        ));
        object.put("specList", spSpecificationList);

        List<SpProjectImage> spProjectImageList = getServiceProjectImages(projectId);
        object.put("imageList", spProjectImageList);

        return object;
    }

    /**
    * @Title: getServiceProjectImages
    * @Description: TODO(这里用一句话描述这个方法的作用)
    * @author QianQingzhao
    * @param projectId
    * @return
    * @throws
    */
    public List<SpProjectImage> getServiceProjectImages(Integer projectId)
    {
        List<SpProjectImage> spProjectImageList = spProjectImageMapper.selectByMap(MyBatisUtils.buildParameterMap(
                SpProjectImage.Fields.PROJECT_ID, projectId
        ));
        return spProjectImageList;
    }

    /**
     * 点击详情页，浏览量自增.
     * @param projectId     服务项目ID
     */
    public void viewServiceProjectDetail(Integer projectId) {
        try {
            SpServiceProject spServiceProject = spServiceProjectMapper.selectById(projectId);
            if (spServiceProject == null) {
                LOGGER.error("SpServiceProject[projectId: " + projectId + "] does not exist.");
                throw new ServiceException("NullPointerException", "SpServiceProject[projectId: " + projectId + "] does not exist.");
            }
            SpServiceProject newSpServiceProject = new SpServiceProject();
            newSpServiceProject.setProjectId(projectId);
            newSpServiceProject.setVisitorCount(spServiceProject.getVisitorCount() + 1);
            newSpServiceProject.setUpdateTime(new Date());
            spServiceProjectMapper.updateSelectiveById(newSpServiceProject);
        } catch (Exception e) {
            LOGGER.error("Fail to update the visitor count service project.", e.getMessage());
        }
    }

    /**
    * @Title: listSupply
    * @Description: TODO(这里用一句话描述这个方法的作用)
    * @author QianQingzhao
    * @return
    * @throws
    */
    public  List<SpServiceProject> listSupply1()
    {
        Map<String, Object> query = MyBatisUtils.buildParameterMap(
                SpServiceProject.Fields.SUPPLY_TYPE, 
                        SpServiceProject.SP_SUPPLY_TYPE_INSPECT_DETEC,
                        
                SpServiceProject.Query.SERVICE_FIELD__IN, Arrays.asList(
                        SpServiceProject.SP_SERVICE_FIELD_OTHER,
                        SpServiceProject.SP_SERVICE_FIELD_CHEMISTRY,
                        SpServiceProject.SP_SERVICE_FIELD_CONSTRUCTION ,
                        SpServiceProject.SP_SERVICE_FIELD_NDT,
                        SpServiceProject.SP_SERVICE_FIELD_CONSUMER_GOODS,
                        SpServiceProject.SP_SERVICE_FIELD_ENVIRON_HEALTH,
                        SpServiceProject.SP_SERVICE_FIELD_BIOLOGY_EQUIPMENT,
                        SpServiceProject.SP_SERVICE_FIELD_ELECTRONICS_MECHANICAL,
                        SpServiceProject.SP_SERVICE_FIELD_EMC,
                        SpServiceProject.SP_SERVICE_FIELD_SPIN_FIBER ,
                        SpServiceProject.SP_SERVICE_FIELD_SOFTWARE_INFO,
                        SpServiceProject.SP_SERVICE_FIELD_METAL_MATERIAL,
                        SpServiceProject.SP_SERVICE_FIELD_FOOD_FARM_PRODUCTS
                ),
                OrderItem.ORDER_KEY, Arrays.asList(new OrderItem(SpServiceProject.Fields.CREATE_TIME, OrderItem.DESC),
                        new OrderItem(SpServiceProject.Fields.VISITOR_COUNT, OrderItem.DESC))
        );

        return spServiceProjectMapper.selectByMap(query, new RowBounds(0, 6));
    }
    
    public  List<SpServiceProject> listSupply2()
    {
        Map<String, Object> query = MyBatisUtils.buildParameterMap(
                SpServiceProject.Fields.SUPPLY_TYPE, 
                        
                        SpServiceProject.SP_SUPPLY_TYPE_TECH_SERVICE ,
                SpServiceProject.Query.SERVICE_FIELD__IN, Arrays.asList(
                        SpServiceProject.SP_SERVICE_FIELD_OTHER,
                        SpServiceProject.SP_SERVICE_FIELD_CHEMISTRY,
                        SpServiceProject.SP_SERVICE_FIELD_CONSTRUCTION ,
                        SpServiceProject.SP_SERVICE_FIELD_NDT,
                        SpServiceProject.SP_SERVICE_FIELD_CONSUMER_GOODS,
                        SpServiceProject.SP_SERVICE_FIELD_ENVIRON_HEALTH,
                        SpServiceProject.SP_SERVICE_FIELD_BIOLOGY_EQUIPMENT,
                        SpServiceProject.SP_SERVICE_FIELD_ELECTRONICS_MECHANICAL,
                        SpServiceProject.SP_SERVICE_FIELD_EMC,
                        SpServiceProject.SP_SERVICE_FIELD_SPIN_FIBER ,
                        SpServiceProject.SP_SERVICE_FIELD_SOFTWARE_INFO,
                        SpServiceProject.SP_SERVICE_FIELD_METAL_MATERIAL,
                        SpServiceProject.SP_SERVICE_FIELD_FOOD_FARM_PRODUCTS
                ),
                OrderItem.ORDER_KEY, Arrays.asList(new OrderItem(SpServiceProject.Fields.CREATE_TIME, OrderItem.DESC),
                        new OrderItem(SpServiceProject.Fields.VISITOR_COUNT, OrderItem.DESC))
        );

        return spServiceProjectMapper.selectByMap(query, new RowBounds(0, 6));
    }
    
}
