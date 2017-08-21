package cn.thinkjoy.hsll.service;

import cn.thinkjoy.hsll.bean.GoodsSpec;
import cn.thinkjoy.hsll.bean.adminBean.ProductSpec;

import java.util.List;

/**
 * Created by warden on 17/7/22.
 */
public interface GoodsSpecService {

    GoodsSpec getGoodsSpecByGoodsId(long goodsId);

    List<GoodsSpec> getSpecLsBygoodId(long id);

    void update(ProductSpec productSpec);

    void add(ProductSpec productSpec);

    GoodsSpec getSpecById(Long aLong);
}
