package cn.thinkjoy.hsll.dao;

import cn.thinkjoy.hsll.bean.Goods;
import cn.thinkjoy.hsll.bean.adminBean.ProductRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by warden on 17/7/22.
 */
public interface GoodsDao {


    List<Goods> getGoodsList();

    Goods getGoodsById(@Param("id")long id);

    void updateGoode(@Param("productRequest")ProductRequest productRequest);

    long addGood(@Param("productRequest")ProductRequest productRequest);

    void delGood(@Param("goodId")long goodId);
}
