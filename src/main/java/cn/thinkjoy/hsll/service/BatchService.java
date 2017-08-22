package cn.thinkjoy.hsll.service;

import cn.thinkjoy.hsll.bean.Batch;
import cn.thinkjoy.hsll.bean.adminBean.BatchInfo;

import java.util.List;

/**
 * Created by warden on 17/7/22.
 */
public interface BatchService {


    List<Batch> getNewBatch(int count);

    void updateStatusById(int status, long id);

    Batch getBatchByGoodsCode(String takeCode);

    List<BatchInfo> getBatchList(int i, int pageSize);

    int getBatchCount();

    void add(Batch batch);

    int getUseNumByBatchCode(String batchCode);

    List<BatchInfo> getGoodCodeList(String batchCode, int i, int pageSize);

    int getUnUseNumByBatchCode(String batchCode);

    Batch getBatchById(Long aLong);
}
