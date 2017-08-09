package cn.thinkjoy.hsll.service.impl;

import cn.thinkjoy.hsll.bean.Batch;
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
    public void updateStatusById(long id) {
        batchDao.updateStatusById(id);
    }

    @Override
    public Batch getBatchByGoodsCode(String goodsCode) {
        return batchDao.getBatchByGoodsCode(goodsCode);
    }
}
