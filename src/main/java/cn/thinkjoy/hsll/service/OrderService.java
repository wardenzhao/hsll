package cn.thinkjoy.hsll.service;

import cn.thinkjoy.hsll.bean.Order;

import java.util.List;

/**
 * Created by warden on 17/7/22.
 */
public interface OrderService {

    void insertData(Order order);

    Order getByOrderNo(String orderNo);

    void updateData(Order order);

    Order getByGoodsCode(String goodsCode);

    List<Order> getListByMemberId(long id,int offset, int pageSize);
}
