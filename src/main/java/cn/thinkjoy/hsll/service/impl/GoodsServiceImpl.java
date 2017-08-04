package cn.thinkjoy.hsll.service.impl;

import cn.thinkjoy.hsll.bean.Goods;
import cn.thinkjoy.hsll.dao.GoodsDao;
import cn.thinkjoy.hsll.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by warden on 17/7/22.
 */
@Service("goodsService")
public class GoodsServiceImpl implements GoodsService{

    @Autowired
    private GoodsDao goodsDao;

    @Override
    public List<Goods> getGoodsList() {
        return goodsDao.getGoodsList();
    }
}
