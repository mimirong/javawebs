package cn.com.hugedata.spark.management.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.hugedata.spark.commons.storage.entity.UcUserInfo;
import cn.com.hugedata.spark.commons.storage.mapper.UcUserInfoMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;

/**
 * 用于查询公司列表的服务.
 */
@Service
public class CompanyQueryService {

    @Autowired
    private UcUserInfoMapper ucUserInfoMapper;
    
    /**
     * 模糊查询公司名称.
     * @param like 查询关键字
     * @return     公司名称列表
     */
    public List<String> queryCompanyNamesLike(String like) {
        List<UcUserInfo> list = ucUserInfoMapper.selectByMap(MyBatisUtils.buildParameterMap(
                UcUserInfo.Fields.USER_TYPE, UcUserInfo.USER_TYPE_COMPANY,
                UcUserInfo.Query.COMPANY_NAME__NE, "",
                UcUserInfo.Query.COMPANY_NAME__LIKE, like
        ));
        List<String> retList = new ArrayList<>();
        for (UcUserInfo user : list) {
            retList.add(user.getCompanyName());
        }
        return retList;
    }
    
}
