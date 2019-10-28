package cn.com.hugedata.spark.connect.handlers.article;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.UrlManager;
import cn.com.hugedata.spark.commons.service.file.FileUrlHelperService;
import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.entity.PtArticle;
import cn.com.hugedata.spark.commons.storage.mapper.PtArticleMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.connect.BaseHandler;
import cn.com.hugedata.spark.connect.InterfaceResponse;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 文章列表接口.
 */
public class ArticleListHandler extends BaseHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleListHandler.class);

    private List<String> categoryIdArray;
    private Integer isNeedPicture;// 是否必须需要图片，1为必须，0为非必须

    Integer start;
    Integer length;

    @Autowired
    private PtArticleMapper ptArticleMapper;

    @Autowired
    private FileUrlHelperService fileUrlHelperService;

    @Autowired
    private UrlManager urlManager;

    @Override
    protected boolean isRequireLogin() {
        return false;
    }

    @Override
    public InterfaceResponse execute(JSONObject requestData) throws ServiceException {

        parseParameters(requestData);
        checkParameters();
        List<JSONObject> ptArticleJsons = new ArrayList<JSONObject>();
        Map<String, Object> query = MyBatisUtils.buildParameterMap(
                PtArticle.Query.CATEGORY_ID__IN, categoryIdArray,
                PtArticle.Fields.IS_VISIBLE, true,
                PtArticle.Query.PUBLISH_TIME__END, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),
                OrderItem.ORDER_KEY, Arrays.asList(new OrderItem(PtArticle.Fields.PUBLISH_TIME, OrderItem.DESC),
                        new OrderItem(PtArticle.Fields.CREATE_TIME, OrderItem.DESC)));
        if(1 == isNeedPicture){
            query.put(PtArticle.Query.COVER_FILE_ID__NE, null);
            query.put(PtArticle.Query.COVER_FILE_ID__NE, "");
        }
        List<PtArticle> ptArticleList = ptArticleMapper.selectByMap(query, new RowBounds(start, length));

        JSONObject resp = new JSONObject();
        if (null == ptArticleList) {
            resp.put("ptArticles", null);
            resp.put("count", 0);
            return InterfaceResponse.createSuccessResponse(resp);
        }

        for (PtArticle ptArticle : ptArticleList) {
            String fileUrl = fileUrlHelperService.fixDownloadUrl(ptArticle.getCoverFileId(), ptArticle.getCoverFileName());
            JSONObject ptArticleJson = ptArticle.toJSON();
            ptArticleJson.put("detailUrl", urlManager.getConnectUrl() + "/article/detail/" + ptArticle.getArticleId());
            ptArticleJson.put("coverFileUrl", fileUrl);
            removeKey(ptArticleJson);
            ptArticleJsons.add(ptArticleJson);
        }
        resp.put("ptArticles", ptArticleJsons);
        resp.put("count", ptArticleJsons.size());
        return InterfaceResponse.createSuccessResponse(resp);
    }

    private void parseParameters(JSONObject req) throws ServiceException {
        try {
            categoryIdArray = JSONArray.parseArray(req.getString("categoryIdArray"), String.class);
        } catch (Exception e) {
            LOGGER.error("CategoryIdArray is not found.");
            throw new ServiceException(this.getClass().getSimpleName() + "-categoryIdArray", "categoryIdArray参数异常");
        }
        start = req.getInteger("start");
        length = req.getInteger("length");
        isNeedPicture = req.getInteger("isNeedPicture");
    }

    private void checkParameters() throws ServiceException {
        if (categoryIdArray == null) {
            LOGGER.error("Category id list is null.");
            throw new ServiceException(this.getClass().getSimpleName() + "-categoryIdArray", "categoryIdArray不能为空");
        }
        if (null == start || 0 > start) {
            start = 0;
        }
        if (null == isNeedPicture || 0 > isNeedPicture) {
            isNeedPicture = 0;
        }
        if (null == length || 0 > length) {
            length = 10;
        }

    }

    private void removeKey(JSONObject object) {
        object.remove("idValue");
        object.remove("idPropertyName");
        object.remove("brief");
        object.remove("content");
        object.remove("isVisible");
        object.remove("createTime");
        object.remove("createTimeStr");
        object.remove("updateTime");
        object.remove("updateTimeStr");
        object.remove("extraData");
    }
}
