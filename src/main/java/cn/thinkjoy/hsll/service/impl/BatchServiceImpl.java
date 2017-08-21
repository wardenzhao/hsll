package cn.thinkjoy.hsll.service.impl;

import cn.thinkjoy.hsll.bean.Batch;
import cn.thinkjoy.hsll.bean.adminBean.BatchInfo;
import cn.thinkjoy.hsll.dao.BatchDao;
import cn.thinkjoy.hsll.service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by warden on 17/7/22.
 */
@Service("batchService")
public class BatchServiceImpl implements BatchService{

    @Autowired
    private BatchDao batchDao;

    @Override
    public List<Batch> getNewBatch(int count) {
        return batchDao.getNewBatch(count);
    }

    @Override
    public void updateStatusById(int status, long id) {
        batchDao.updateStatusById(status, id);
    }

    @Override
    public Batch getBatchByGoodsCode(String goodsCode) {
        return batchDao.getBatchByGoodsCode(goodsCode);
    }

    @Override
    public List<BatchInfo> getBatchList(int i, int pageSize) {
        return batchDao.getBatchList(i,pageSize);
    }

    @Override
    public int getBatchCount() {
        return batchDao.getBatchCount();
    }

    @Override
    public void add(Batch batch) {
        batchDao.add(batch);
    }

    @Override
    public int getUseNumByBatchCode(String batchCode) {
        return batchDao.getUseNumByBatchCode(batchCode);
    }

    @Override
    public List<BatchInfo> getGoodCodeList(String batchCode, int i, int pageSize) {
        return batchDao.getGoodCodeList(batchCode, i, pageSize);
    }

    @Override
    public int getUnUseNumByBatchCode(String batchCode) {
        return batchDao.getUnUseNumByBatchCode(batchCode);
    }
}
