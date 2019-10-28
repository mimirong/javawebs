package cn.com.hugedata.spark.connect.handlers.outsourcing;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.file.FileStoreService;
import cn.com.hugedata.spark.commons.service.file.FileUrlHelperService;
import cn.com.hugedata.spark.commons.storage.entity.SpProjectImage;
import cn.com.hugedata.spark.commons.storage.entity.SpServiceProject;
import cn.com.hugedata.spark.commons.storage.entity.SpSpecification;
import cn.com.hugedata.spark.commons.storage.mapper.SpProjectImageMapper;
import cn.com.hugedata.spark.commons.storage.mapper.SpServiceProjectMapper;
import cn.com.hugedata.spark.commons.storage.mapper.SpSpecificationMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.connect.BaseHandler;
import cn.com.hugedata.spark.connect.InterfaceResponse;

import com.alibaba.fastjson.JSONObject;

public class SupplyDetailHandler extends BaseHandler {


    private Integer projectId;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(SupplyDetailHandler.class);

    @Autowired
    private SpServiceProjectMapper spServiceProjectMapper;
    
    @Autowired
    private SpProjectImageMapper spProjectImageMapper;
    
    @Autowired
    private SpSpecificationMapper spSpecificationMapper;
    
    
    @Autowired
    private FileStoreService fileStoreService;
    
    
    @Autowired
    private FileUrlHelperService fileUrlHelperService;

    @Override
    protected boolean isRequireLogin() {
        return false;
    }

    @Override
    public InterfaceResponse execute(JSONObject requestData)
            throws ServiceException
    {
        parseParameters(requestData);
        checkParameters();
        SpServiceProject sp = spServiceProjectMapper.selectById(projectId);
        if (sp == null) {
            return InterfaceResponse.createFailResponse("服务项目不存在");
        }
        DetailData data = new DetailData();
        data.serviceProject = sp;
        List<SpProjectImage> spProjectImageList = spProjectImageMapper.selectByMap(MyBatisUtils.buildParameterMap(
                SpProjectImage.Fields.PROJECT_ID, projectId
        ));
        data.imageList = spProjectImageList;
        
        
        List<SpSpecification> spSpecificationList = spSpecificationMapper.selectByMap(MyBatisUtils.buildParameterMap(
                SpSpecification.Fields.PROJECT_ID, projectId
        ));
        
        data.specList = spSpecificationList;
        
        JSONObject resp = new JSONObject();
        resp.put("supplyDetail", data);
        
        return InterfaceResponse.createSuccessResponse(resp);
        
        
        
        
    }

    private void parseParameters(JSONObject req) {
        projectId = req.getInteger("projectId");
    }

    private void checkParameters() throws ServiceException {        
        if (projectId == null ) {
            LOGGER.error("Parameter [projectId] is empty.");
            throw new ServiceException(this.getClass().getSimpleName() + "-projectId", "projectId不能为空");
        }
    }

   
    public static class DetailData {

        private SpServiceProject serviceProject;
        
        private List<SpProjectImage> imageList;
        
        private List<SpSpecification> specList;

        public SpServiceProject getServiceProject()
        {
            return serviceProject;
        }

        public void setServiceProject(SpServiceProject serviceProject)
        {
            this.serviceProject = serviceProject;
        }

        public List<SpProjectImage> getImageList()
        {
            return imageList;
        }

        public void setImageList(List<SpProjectImage> imageList)
        {
            this.imageList = imageList;
        }

        public List<SpSpecification> getSpecList()
        {
            return specList;
        }

        public void setSpecList(List<SpSpecification> specList)
        {
            this.specList = specList;
        }


    }


}
