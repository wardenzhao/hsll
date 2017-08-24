package cn.thinkjoy.hsll.controller.admin;

import cn.thinkjoy.hsll.bean.GoodsSpec;
import cn.thinkjoy.hsll.bean.Member;
import cn.thinkjoy.hsll.bean.MemberAddress;
import cn.thinkjoy.hsll.bean.Order;
import cn.thinkjoy.hsll.bean.adminBean.BuyInfoResponse;
import cn.thinkjoy.hsll.bean.adminBean.MemberResPonse;
import cn.thinkjoy.hsll.bean.adminBean.ResultResponse;
import cn.thinkjoy.hsll.service.GoodsSpecService;
import cn.thinkjoy.hsll.service.MemberAddressService;
import cn.thinkjoy.hsll.service.MemberService;
import cn.thinkjoy.hsll.service.OrderService;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wpliu on 17/8/19.
 */
@Scope("prototype")
@Controller("memberController")
@RequestMapping(value = "/admin/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private OrderService orderService;
    @Autowired
    private GoodsSpecService goodsSpecService;
    @Autowired
    private MemberAddressService memberAddressService;

    /**
     * 会员列表
     * @param request
     * @param response
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public MemberResPonse list(HttpServletRequest request,HttpServletResponse response,int pageNo,int pageSize){

        List<Member> members= memberService.getMemberList(pageNo,pageSize);
        for (int i=0;i<members.size();i++){
            Member member=members.get(i);
           List<MemberAddress>  memberAddresses= memberAddressService.getMemberByMemberId(member.getId());
            member.setAddress(JSONArray.toJSONString(memberAddresses,false));
        }
        int count=memberService.getMemberCount();
        MemberResPonse memberResPonse=new MemberResPonse();
        memberResPonse.setPageNo(pageNo);
        memberResPonse.setListCount(count);
        memberResPonse.setMemberList(members);
        return memberResPonse;
    }

    /**
     * 添加会员
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse add(HttpServletRequest request,HttpServletResponse response,@RequestBody Member member){
        ResultResponse result=new ResultResponse();
        try{
            memberService.add(member);
        }catch (Exception e){
            e.printStackTrace();
        }
        result.setRet(0);
        result.setMsg("success");
        return result;
    }

    /**
     * 会员详情
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping(value = "/detail",method = RequestMethod.POST)
    @ResponseBody
    public Member getMemberDetail(HttpServletRequest request,HttpServletResponse response,long id){
        Member member=new Member();
         member=memberService.getMemberById(id);
      List<MemberAddress> memberAddresses=  memberAddressService.getMemberByMemberId(id);
        member.setAddress(JSONArray.toJSONString(memberAddresses,false));
        return  member;
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse update(HttpServletRequest request,HttpServletResponse response,@RequestBody Member member){
        ResultResponse result=new ResultResponse();
        try{
            memberService.update(member);
        }catch (Exception e){
            e.printStackTrace();
        }
        result.setRet(0);
        result.setMsg("success");
        return result;
    }


    @RequestMapping(value = "/del",method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse del(HttpServletRequest request,HttpServletResponse response,long id){
        ResultResponse result=new ResultResponse();
        try{
            memberService.delete(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        result.setRet(0);
        result.setMsg("success");
        return result;
    }

    /**
     * 会员购买记录
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping(value = "/buyInfo",method = RequestMethod.GET)
    @ResponseBody
    public List<BuyInfoResponse> buyInfo(HttpServletRequest request,HttpServletResponse response,long id){
        List<BuyInfoResponse> buyInfoResponses=new ArrayList<>();
        try{
           List<Order> orders=orderService.getListByMemberId(id,1,1000);
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Order order=null;
            for(int i=0;i<orders.size();i++){
                BuyInfoResponse buyInfoResponse=new BuyInfoResponse();
                order=orders.get(i);
                buyInfoResponse.setNum(order.getGoodsNum()+"");
                buyInfoResponse.setOrderNo(order.getOrderNo());
                buyInfoResponse.setOrderTime(format.format(order.getCreatedTime()));
                buyInfoResponse.setRecAddress(order.getAddress());
                buyInfoResponse.setRecPerson(order.getPerson());
                buyInfoResponse.setRecPhone(order.getPhone());
                GoodsSpec goodsSpec= goodsSpecService.getSpecById(Long.valueOf(order.getGoodsSpecId()));
                buyInfoResponse.setSpecName(goodsSpec.getSpecName());
                buyInfoResponse.setStatus(order.getStatus());
                buyInfoResponses.add(buyInfoResponse);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return buyInfoResponses;
    }



}
