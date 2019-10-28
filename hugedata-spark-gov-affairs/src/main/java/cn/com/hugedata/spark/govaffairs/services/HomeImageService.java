package cn.com.hugedata.spark.govaffairs.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.entity.PtHomeImage;
import cn.com.hugedata.spark.commons.storage.mapper.PtHomeImageMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;

@Service
public class HomeImageService {

    @Autowired
    private PtHomeImageMapper ptHomeImageMapper;
    
    public List<PtHomeImage> queryHomeImages() {
        return ptHomeImageMapper.selectByMap(MyBatisUtils.params(
                PtHomeImage.Fields.IS_VISIBLE, true,
                new OrderItem(PtHomeImage.Fields.SORT_INDEX, OrderItem.ASC),
                new OrderItem(PtHomeImage.Fields.IMAGE_ID, OrderItem.DESC)
        ));
    }
    
}
