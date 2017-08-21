package cn.thinkjoy.hsll.dao;

import cn.thinkjoy.hsll.bean.Batch;
import cn.thinkjoy.hsll.bean.adminBean.BatchInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by warden on 17/7/22.
 */
public interface BatchDao {

    List<Batch> getNewBatch(@Param("count") int count);

    void updateStatusById(@Param("status") int status,@Param("id")long id);

    Batch getBatchByGoodsCode(@Param("goodsCode")String goodsCode);

    List<BatchInfo> getBatchList(@Param("startPage")int i,@Param("pageSize") int pageSize);

    int getBatchCount();

    void add(@Param("batch")Batch batch);

    int getUseNumByBatchCode(@Param("batchCode")String batchCode);

    List<BatchInfo> getGoodCodeList(@Param("batchCode")String batchCode, @Param("pageNo")int i, @Param("pageSize")int pageSize);

    int getUnUseNumByBatchCode(@Param("batchCode")String batchCode);
}
