package cn.thinkjoy.hsll.service;

import cn.thinkjoy.hsll.bean.Goods;

import java.util.List;

/**
 * Created by warden on 17/7/22.
 */
public interface GoodsService {


    List<Goods> getGoodsList();

    Goods getGoodsById(long goodsId);

//    void updateGood(ProductRequest productRequest);
}
