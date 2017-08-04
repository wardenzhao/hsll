package cn.thinkjoy.hsll.dao;

import cn.thinkjoy.hsll.bean.GoodsSpec;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by warden on 17/7/22.
 */
public interface GoodsSpecDao {


    List<GoodsSpec> getGoodsSpecByGoodsId(@Param("goodsId") long goodsId);
}
