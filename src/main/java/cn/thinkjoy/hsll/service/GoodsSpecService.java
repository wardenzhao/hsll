package cn.thinkjoy.hsll.service;

import cn.thinkjoy.hsll.bean.GoodsSpec;

import java.util.List;

/**
 * Created by warden on 17/7/22.
 */
public interface GoodsSpecService {

    GoodsSpec getGoodsSpecByGoodsId(long goodsId);

    List<GoodsSpec> getSpecLsBygoodId(long id);
}
