package cn.thinkjoy.hsll.service.impl;

import cn.thinkjoy.hsll.bean.Order;
import cn.thinkjoy.hsll.dao.OrderDao;
import cn.thinkjoy.hsll.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<Order> getListByMemberId(long id,int offset, int pageSize) {
        return orderDao.getListByMemberId(id,offset,pageSize);
    }

    @Override
    public List<Order> getListByStatus(int i, int pageSize, int status) {
        return orderDao.getListByStatus(i, pageSize, status);
    }

    @Override
    public int getListCountByStatus(int status) {
        return orderDao.getListCountByStatus(status);
    }

    @Override
    public void sendByOrderNo(String orderNo,int status) {
        orderDao.sendByOrderNo(orderNo,status);
    }

    @Override
    public List<Order> getListBySearch(int i, int pageSize, String searchName) {
        return orderDao.getListBySearch(i,pageSize,searchName);
    }

    @Override
    public int getListCountBySearchName(String searchName) {
        return orderDao.getListCountBySearchName(searchName);
    }
}
