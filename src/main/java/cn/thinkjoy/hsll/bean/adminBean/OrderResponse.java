package cn.thinkjoy.hsll.bean.adminBean;

import java.util.List;

/**
 * Created by wpliu on 17/8/21.
 */
public class OrderResponse {
    private  int pageNo;
    private int listCount;
    private List<OrderInfo> orderInfos;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getListCount() {
        return listCount;
    }

    public void setListCount(int listCount) {
        this.listCount = listCount;
    }

    public List<OrderInfo> getOrderInfos() {
        return orderInfos;
    }

    public void setOrderInfos(List<OrderInfo> orderInfos) {
        this.orderInfos = orderInfos;
    }
}
