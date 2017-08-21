package cn.thinkjoy.hsll.controller.admin;

import cn.thinkjoy.hsll.bean.adminBean.ProductResponse;
import cn.thinkjoy.hsll.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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


    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public List<ProductResponse> list(HttpServletRequest request,HttpServletResponse response){

        return  null;
    }
}
