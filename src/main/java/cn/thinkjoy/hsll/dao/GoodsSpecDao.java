package cn.thinkjoy.hsll.dao;

import cn.thinkjoy.hsll.bean.GoodsSpec;
import cn.thinkjoy.hsll.bean.adminBean.ProductSpec;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by warden on 17/7/22.
 */
public interface GoodsSpecDao {


    List<GoodsSpec> getGoodsSpecByGoodsId(@Param("goodsId") long goodsId);

    void update(@Param("productSpec")ProductSpec productSpec);

    void add(@Param("productSpec")ProductSpec productSpec);

    GoodsSpec getSpecById(@Param("id")Long aLong);
}
