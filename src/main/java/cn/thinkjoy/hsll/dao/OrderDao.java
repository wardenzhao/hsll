package cn.thinkjoy.hsll.dao;

import cn.thinkjoy.hsll.bean.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by warden on 17/7/22.
 */
public interface OrderDao {


    void insertData(@Param("order") Order order);

    Order getByOrderNo(@Param("orderNo")String orderNo);

    void updateData(@Param("order") Order order);

    Order getByGoodsCode(@Param("goodsCode")String goodsCode);

    List<Order> getListByMemberId(@Param("memberId")long memberId,@Param("offset") int offset, @Param("pageSize") int pageSize);
}
