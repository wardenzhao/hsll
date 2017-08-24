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

    List<Order> getListByStatus(@Param("startPage")int i, @Param("pageSize")int pageSize, @Param("status")int status);

    int getListCountByStatus(@Param("status")int status);

    void sendByOrderNo(@Param("orderNo")String orderNo,@Param("status")int status);

    List<Order> getListBySearch(@Param("pageNo")int i,@Param("pageSize") int pageSize, @Param("searchName")String searchName);

    int getListCountBySearchName(@Param("searchName")String searchName);
}
