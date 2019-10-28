package cn.com.hugedata.spark.connect.handlers.outsourcing;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.constant.DdDetectionApplyConstants;
import cn.com.hugedata.spark.commons.storage.constant.PtCategoryConstants;
import cn.com.hugedata.spark.commons.storage.entity.DdDetectionApply;
import cn.com.hugedata.spark.commons.storage.entity.PtArticle;
import cn.com.hugedata.spark.commons.storage.mapper.DdDetectionApplyMapper;
import cn.com.hugedata.spark.commons.storage.mapper.PtArticleMapper;
import cn.com.hugedata.spark.connect.BaseHandler;
import cn.com.hugedata.spark.connect.InterfaceResponse;

/**
 * 服务外包 - 检验检测服务 - 提交申请接口.
 */
public class OsDdSubmitApplyHandler extends BaseHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(OsDdSubmitApplyHandler.class);

    private String categoryId;// 栏目ID，必填项
    private String articleId;// 文章ID，必填项
    private String companyName;// 公司名称，必填项
    private String contact;// 手机号码，必填项
    private String contactName;// 联系人， 必填项
    private String comment;// 说明，非必填项

    @Autowired
    private DdDetectionApplyMapper ddDetectionApplyMapper;
    
    @Autowired
    private PtArticleMapper ptArticleMapper;

    @Override
    protected boolean isRequireLogin() {
        return true;
    }

    @Override
    public InterfaceResponse execute(JSONObject requestData) throws ServiceException {
        parseParameters(requestData);
        checkParameters();
        
        // 查询文章信息
        PtArticle article = ptArticleMapper.selectById(Integer.parseInt(articleId));
        if (article == null) {
            LOGGER.error("Article id not found: {}", articleId);
            throw new ServiceException("ArticleIdNotFound", "文章ID不存在");
        }

        DdDetectionApply ddDetectionApply = new DdDetectionApply();
        ddDetectionApply.setApplyType(categoryId);
        ddDetectionApply.setStatus(DdDetectionApplyConstants.STATUS_CREATED);
        ddDetectionApply.setResourceId(articleId);
        ddDetectionApply.setResourceName(article.getTitle());
        ddDetectionApply.setCompanyName(companyName);
        ddDetectionApply.setContact(contact);
        ddDetectionApply.setContactName(contactName);
        ddDetectionApply.setComment(comment);
        ddDetectionApply.setApplierUserId(getLogin().getUserId());
        ddDetectionApply.setApplierName(getLogin().getName());
        ddDetectionApply.setCreateTime(new Date());
        ddDetectionApplyMapper.insertSelective(ddDetectionApply);

        return InterfaceResponse.createSuccessResponse();
    }

    private void parseParameters(JSONObject req) {
        categoryId = req.getString("categoryId");
        articleId = req.getString("articleId");
        companyName = req.getString("companyName");
        contact = req.getString("contact");
        contactName = req.getString("contactName");
        comment = req.getString("comment");
    }

    private void checkParameters() throws ServiceException {
        if (StringUtils.isEmpty(categoryId)) {
            LOGGER.error("Parameter [categoryId] is empty.");
            throw new ServiceException(this.getClass().getSimpleName() + "-categoryId", "categoryId不能为空");
        } else if (!PtCategoryConstants.OS_DETEC_RESOURCE_PLATFORM.equals(categoryId) &&
                !PtCategoryConstants.OS_DETEC_TECH_TRANSFER.equals(categoryId)) {
            LOGGER.error("Parameter [categoryId] is illegal.");
            throw new ServiceException(this.getClass().getSimpleName() + "-categoryId", "该类型服务不可进行申请");
        }

        if (StringUtils.isEmpty(articleId)) {
            LOGGER.error("Parameter [articleId] is empty.");
            throw new ServiceException(this.getClass().getSimpleName() + "-articleId", "articleId不能为空");
        }

        if (StringUtils.isEmpty(companyName)) {
            LOGGER.error("Parameter [companyName] is empty.");
            throw new ServiceException(this.getClass().getSimpleName() + "-companyName", "companyName不能为空");
        }

        if (StringUtils.isEmpty(contact)) {
            LOGGER.error("Parameter [contact] is empty.");
            throw new ServiceException(this.getClass().getSimpleName() + "-contact", "contact不能为空");
        }

        if (StringUtils.isEmpty(contactName)) {
            LOGGER.error("Parameter [contactName] is empty.");
            throw new ServiceException(this.getClass().getSimpleName() + "-contactName", "contactName不能为空");
        }
    }
}
