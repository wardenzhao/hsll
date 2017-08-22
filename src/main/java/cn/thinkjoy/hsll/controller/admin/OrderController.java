package cn.thinkjoy.hsll.controller.admin;

import cn.thinkjoy.hsll.bean.Batch;
import cn.thinkjoy.hsll.bean.Goods;
import cn.thinkjoy.hsll.bean.Member;
import cn.thinkjoy.hsll.bean.Order;
import cn.thinkjoy.hsll.bean.adminBean.OrderInfo;
import cn.thinkjoy.hsll.bean.adminBean.OrderResponse;
import cn.thinkjoy.hsll.bean.adminBean.ResultResponse;
import cn.thinkjoy.hsll.service.BatchService;
import cn.thinkjoy.hsll.service.GoodsService;
import cn.thinkjoy.hsll.service.MemberService;
import cn.thinkjoy.hsll.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wpliu on 17/8/21.
 */
@Scope("prototype")
@Controller("orderController")
@RequestMapping(value = "/admin/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private BatchService batchService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private GoodsService goodsService;

    /**
     *
     * @param request
     * @param response
     * @param pageNo  分页起始页
     * @param pageSize  页面长度
     * @param status -1:全部 0：提货卡未发货 1:提货卡已发货  2：未发货 3：已发货
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public OrderResponse list(HttpServletRequest request,HttpServletResponse response,int pageNo,int pageSize,int status){
        OrderResponse orderResponse=new OrderResponse();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Order>  orders=orderService.getListByStatus(pageNo*pageSize,pageSize,status);
        List<OrderInfo> orderInfos=new ArrayList<>();
        for(int i=0;i<orders.size();i++){
            Order order=orders.get(i);
            OrderInfo orderInfo=new OrderInfo();
            orderInfo.setAddress(order.getAddress());
            Batch batch= batchService.getBatchById(Long.valueOf(order.getBatchId()));
            String batchInfo= batch.getBatchName()+"第"+batch.getBatchNo()+"盒";
            orderInfo.setBatchInfo(batchInfo);
            Member member=memberService.getMemberById(order.getBuyMemberId());
            orderInfo.setBuyUser(member.getName());
            orderInfo.setGoodCode(order.getGoodsCode());
            Goods goods=goodsService.getGoodsById(order.getGoodsId());
            orderInfo.setGoodName(goods.getName());
            orderInfo.setOrderDate(format.format(order.getCreatedTime()));
            orderInfo.setTaxInfo(order.getTaxInfo());
            orderInfo.setUserReply(order.getBuyerMessage());
            orderInfo.setOrderNo(order.getOrderNo());
            orderInfo.setOrderStatus(order.getStatus()+"");
            orderInfo.setOrderType(order.getType()+"");
            orderInfos.add(orderInfo);
        }
        int count=orderService.getListCountByStatus(status);
        orderResponse.setOrderInfos(orderInfos);
        orderResponse.setPageNo(pageNo);
        orderResponse.setListCount(count);
        return  orderResponse;
    }

    /**
     *订单详情
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/detail",method = RequestMethod.GET)
    @ResponseBody
    public OrderInfo list(HttpServletRequest request,HttpServletResponse response,String orderNo){
        OrderResponse orderResponse=new OrderResponse();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Order order=orderService.getByOrderNo(orderNo);
            OrderInfo orderInfo=new OrderInfo();
            orderInfo.setAddress(order.getAddress());
            Batch batch= batchService.getBatchById(Long.valueOf(order.getBatchId()));
            String batchInfo= batch.getBatchName()+"第"+batch.getBatchNo()+"盒";
            orderInfo.setBatchInfo(batchInfo);
            Member member=memberService.getMemberById(order.getBuyMemberId());
            orderInfo.setBuyUser(member.getName());
            orderInfo.setGoodCode(order.getGoodsCode());
            Goods goods=goodsService.getGoodsById(order.getGoodsId());
            orderInfo.setGoodName(goods.getName());
            orderInfo.setOrderDate(format.format(order.getCreatedTime()));
            orderInfo.setTaxInfo(order.getTaxInfo());
            orderInfo.setUserReply(order.getBuyerMessage());
            orderInfo.setOrderNo(order.getOrderNo());
            orderInfo.setOrderStatus(order.getStatus()+"");
            orderInfo.setOrderType(order.getType()+"");

        return  orderInfo;
    }

    /**
     * 发货
     * @param request
     * @param response
     * @param orderNo
     * @param status
     * @return
     */
    @RequestMapping(value = "/send",method = RequestMethod.GET)
    @ResponseBody
    public ResultResponse send(HttpServletRequest request,HttpServletResponse response,String orderNo,String status){
        ResultResponse result=new ResultResponse();
        orderService.sendByOrderNo(orderNo,1);
        result.setRet(0);
        result.setMsg("success");
        return result;
    }

}
