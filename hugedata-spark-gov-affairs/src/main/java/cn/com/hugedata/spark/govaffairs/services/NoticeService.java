package cn.com.hugedata.spark.govaffairs.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.entity.PtNoticeBanner;
import cn.com.hugedata.spark.commons.storage.mapper.PtNoticeBannerMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;

@Service
public class NoticeService {

    @Autowired
    private PtNoticeBannerMapper ptNoticeBannerMapper;
    
    public List<PtNoticeBanner> queryBannerImages() {
        return ptNoticeBannerMapper.selectByMap(MyBatisUtils.params(
                PtNoticeBanner.Fields.IS_VISIBLE, true,
                new OrderItem(PtNoticeBanner.Fields.SORT_INDEX, OrderItem.ASC),
                new OrderItem(PtNoticeBanner.Fields.IMAGE_ID, OrderItem.DESC)
        ));
    }
}
