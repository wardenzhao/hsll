package cn.thinkjoy.hsll.dao;

import cn.thinkjoy.hsll.bean.Batch;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by warden on 17/7/22.
 */
public interface BatchDao {

    List<Batch> getNewBatch(@Param("count") int count);

    void updateStatusById(@Param("status") int status,@Param("id")long id);

    Batch getBatchByGoodsCode(@Param("goodsCode")String goodsCode);
}
