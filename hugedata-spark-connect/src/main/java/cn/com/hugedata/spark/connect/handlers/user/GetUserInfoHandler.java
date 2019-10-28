package cn.com.hugedata.spark.connect.handlers.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.file.FileUrlHelperService;
import cn.com.hugedata.spark.commons.storage.entity.UcCompanyInfo;
import cn.com.hugedata.spark.commons.storage.entity.UcUserInfo;
import cn.com.hugedata.spark.commons.storage.mapper.UcCompanyInfoMapper;
import cn.com.hugedata.spark.commons.storage.mapper.UcUserInfoMapper;
import cn.com.hugedata.spark.connect.BaseHandler;
import cn.com.hugedata.spark.connect.InterfaceResponse;

public class GetUserInfoHandler extends BaseHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetUserInfoHandler.class);

    @Autowired
    private UcUserInfoMapper ucUserInfoMapper;

    @Autowired
    private UcCompanyInfoMapper ucCompanyInfoMapper;
    
    @Autowired
    private FileUrlHelperService fileUrlHelperService;
    
    @Override
    public InterfaceResponse execute(JSONObject req) throws ServiceException {
        Integer userId = req.getInteger("userId");
        if (userId == null) {
            LOGGER.error("User id is empty.");
            throw new ServiceException("UserIdEmpty", "用户ID不能为空");
        }
        
        // 查询用户信息
        UcUserInfo user = ucUserInfoMapper.selectById(userId);
        if (user == null) {
            LOGGER.error("User not found: {}", userId);
            throw new ServiceException("UserNotFound", "用户不存在");
        }
        
        // 查询企业信息
        UcCompanyInfo companyInfo = null;
        if (UcUserInfo.USER_TYPE_COMPANY.equals(user.getUserType()) || user.getCompanyId() != null) {
            companyInfo = ucCompanyInfoMapper.selectById(user.getCompanyId());
        }

        // 返回
        JSONObject userObj = (JSONObject) JSON.toJSON(user);
        userObj.remove("password");
        userObj.remove("passwordHash");
        userObj.remove("passwordKey");
        userObj.remove("loginNameUpper");
        userObj.remove("emailUpper");
        userObj.remove("idPropertyName");
        userObj.remove("idValue");
        userObj.remove("loginNameUpper");
        userObj.remove("emailUpper");
        userObj.put("headImageUrl", fileUrlHelperService.fixDownloadUrl(user.getHeadImageId()));
        userObj.put("headImagePreviewUrl", fileUrlHelperService.fixDownloadUrl(user.getHeadImagePreviewId()));
        
        JSONObject companyObj = null;
        if (companyInfo != null) {
            companyObj = (JSONObject) JSON.toJSON(companyInfo);
            companyObj.remove("idPropertyName");
            companyObj.remove("idValue");
        }
        
        JSONObject resp = new JSONObject();
        resp.put("user", userObj);
        resp.put("company", companyObj);
        return InterfaceResponse.createSuccessResponse(resp);
    }

}
