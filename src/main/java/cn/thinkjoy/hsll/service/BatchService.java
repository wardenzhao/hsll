package cn.thinkjoy.hsll.service;

import cn.thinkjoy.hsll.bean.Batch;

import java.util.List;

/**
 * Created by warden on 17/7/22.
 */
public interface BatchService {


    List<Batch> getNewBatch(int count);

    void updateStatusById(int status, long id);

    Batch getBatchByGoodsCode(String takeCode);
}
