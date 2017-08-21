package cn.thinkjoy.hsll.service.impl;

import cn.thinkjoy.hsll.bean.GoodsSpec;
import cn.thinkjoy.hsll.bean.adminBean.ProductSpec;
import cn.thinkjoy.hsll.dao.GoodsSpecDao;
import cn.thinkjoy.hsll.service.GoodsSpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by warden on 17/7/22.
 */
@Service("goodsSpecService")
public class GoodsSpecServiceImpl implements GoodsSpecService{

    @Autowired
    private GoodsSpecDao goodsSpecDao;


    @Override
    public GoodsSpec getGoodsSpecByGoodsId(long goodsId) {
        List<GoodsSpec> list = goodsSpecDao.getGoodsSpecByGoodsId(goodsId);
        if(list.size() >0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<GoodsSpec> getSpecLsBygoodId(long id) {
        List<GoodsSpec> list = goodsSpecDao.getGoodsSpecByGoodsId(id);
        return list;
    }

    @Override
    public void update(ProductSpec productSpec) {
        goodsSpecDao.update(productSpec);
    }

    @Override
    public void add(ProductSpec productSpec) {
        goodsSpecDao.add(productSpec);
    }

    @Override
    public GoodsSpec getSpecById(Long aLong) {
        return goodsSpecDao.getSpecById(aLong);
    }
}
