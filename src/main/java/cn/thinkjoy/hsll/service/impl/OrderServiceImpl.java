package cn.thinkjoy.hsll.service.impl;

import cn.thinkjoy.hsll.bean.Order;
import cn.thinkjoy.hsll.dao.OrderDao;
import cn.thinkjoy.hsll.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by warden on 17/7/22.
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public void insertData(Order order) {
        orderDao.insertData(order);
    }

    @Override
    public Order getByOrderNo(String orderNo) {
        return orderDao.getByOrderNo(orderNo);
    }

    @Override
    public void updateData(Order order) {
        orderDao.updateData(order);
    }

    @Override
    public Order getByGoodsCode(String goodsCode) {
        return orderDao.getByGoodsCode("%"+goodsCode+"%");
    }
}
