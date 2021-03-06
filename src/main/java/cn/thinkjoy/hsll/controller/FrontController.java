package cn.thinkjoy.hsll.controller;

/**
 * Created by wpliu on 16/3/2.
 */

import cn.thinkjoy.hsll.bean.*;
import cn.thinkjoy.hsll.req.*;
import cn.thinkjoy.hsll.resp.*;
import cn.thinkjoy.hsll.service.*;
import cn.thinkjoy.hsll.util.CodeUtil;
import cn.thinkjoy.hsll.util.JsonUtil;
import cn.thinkjoy.hsll.util.KeyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 主页控制类
 */
@Scope("prototype")
@Controller("frontController")
@RequestMapping(value = "/hsll")
public class FrontController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(FrontController.class);

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberAddressService memberAddressService;

    @Autowired
    private MemberApplyService memberApplyService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GoodsSpecService goodsSpecService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private BatchService batchService;

    @Autowired
    private JieqiService jieqiService;

    private String baseUrl = "http://172.16.222.194:8080";
//    private String baseUrl = "http://172.16.222.157:8282";

    @RequestMapping(value = "/check/isuser",method = RequestMethod.GET)
    public String index(HttpServletRequest request){
        BaseResp reObject = new BaseResp();
        SnsapiUserinfo snsapiUserinfo = (SnsapiUserinfo)request.getSession().getAttribute(KeyUtil.SESSION_KEY);
        if(snsapiUserinfo == null){
            String INIT_URL = baseUrl + "/hsll/auth/init";
            return "redirect:/hsll/auth?redirect_uri"+INIT_URL;
        }
        Member member = memberService.getMemberByOpenId(snsapiUserinfo.getOpenid());
        if(member != null && member.getId()>0){
            reObject.setCode("1");
            reObject.setMsg("");
            reObject.getOpedId(snsapiUserinfo.getOpenid());
            //return reObject.toString();
        }else{
            //如果不是会员跳转到是不会会员提示页面
            return "not_member";
        }
        return "goods_list";
    }

    @RequestMapping(value = "/goodsList",method = RequestMethod.GET)
    public String goodsList(HttpServletRequest request){
        return "redirect:"+baseUrl + "/baiguoshu-h5/dist/index.html#/buy-goods?1231231";
    }

    @RequestMapping(value = "/userInfo",method = RequestMethod.GET)
    public String userInfo(HttpServletRequest request){
        return "redirect:"+baseUrl + "/baiguoshu-h5/dist/index.html#/members";
    }


    @RequestMapping(value = "/notMember",method = RequestMethod.GET)
    public String notMember(HttpServletRequest request){
        return "redirect:"+baseUrl + "/baiguoshu-h5/dist/index.html#/no-members";
    }

    /**
     * 获取用户信息
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getUserMessage")
    public String getUserMessage(HttpServletRequest request){
        String json = this.getJsonString(request);
        ObjectDataResp resp = new ObjectDataResp();
        BaseReq req = null;
        if(json != null){
            req = JsonUtil.tranjsonStrToObject(json,BaseReq.class);
        }else{
            resp.setCode("-1");
            resp.setMsg("请求参数错误");
            resp.setOpedId("");
            return JsonUtil.tranObjectToJsonStr(resp);
        }

        Member member = memberService.getMemberByOpenId(req.getOpenId());
        if(member != null && member.getId()>0){
            MemberResp memberResp = new MemberResp();
            memberResp.setId(member.getId());
            memberResp.setPhone(member.getPhone()+"");
            memberResp.setOpenId(member.getOpenId()+"");
            memberResp.setUserName(member.getName()+"");
            memberResp.setAddress(member.getAddress()+"");
            memberResp.setInviteCode(member.getInviteCode()+"");
            if(member.getSex() == 0){
                memberResp.setUserSex("女");
            }else{
                memberResp.setUserSex("男");
            }
            resp.setData(memberResp);
            resp.setCode("1");
            resp.setMsg("success");
            resp.setOpedId(req.getOpenId());
        }else{
            resp.setCode("0");
            resp.setMsg("未找到该会员信息");
            resp.setOpedId(req.getOpenId());
        }
        return JsonUtil.tranObjectToJsonStr(resp);
    }

    /**
     * 修改收货地址
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/edit/address")
    public String editAddress(HttpServletRequest request){
        String json = this.getJsonString(request);
        BaseResp resp = new BaseResp();
        EditAddressReq req = null;
        try{
            if(json != null){
                req = JsonUtil.tranjsonStrToObject(json,EditAddressReq.class);
            }else{
                resp.setCode("-1");
                resp.setMsg("请求参数错误");
                resp.setOpedId("");
                return JsonUtil.tranObjectToJsonStr(resp);
            }

            Member member = memberService.getMemberByOpenId(req.getOpenId());
            if(member != null && member.getId()>0){
                MemberAddress memberAddress =  new MemberAddress();
                //如果ID大于0表示修改地址
                if(req.getId() > 0){
                    memberAddress = memberAddressService.getMemberById(req.getId());
                    memberAddress.setAddress(req.getAddress());
                    memberAddress.setPerson(req.getPerson());
                    memberAddress.setPhone(req.getPhone());
                    memberAddressService.updateData(memberAddress);
                }else{
                    memberAddress.setMemberId(member.getId());
                    memberAddress.setAddress(req.getAddress());
                    memberAddress.setPerson(req.getPerson());
                    memberAddress.setPhone(req.getPhone());
                    memberAddressService.insertData(memberAddress);
                }
                resp.setCode("1");
                resp.setMsg("success");
                resp.setOpedId(req.getOpenId());
            }else{
                resp.setCode("0");
                resp.setMsg("未找到该会员信息");
                resp.setOpedId(req.getOpenId());
            }
        }catch (Exception e){
            e.printStackTrace();
            resp.setCode("-1");
            resp.setMsg("系统错误");
            resp.setOpedId(req.getOpenId());
        }

        return JsonUtil.tranObjectToJsonStr(resp);
    }


    /**
     * 获取收货地址
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "get/address")
    public String getAddress(HttpServletRequest request){
        String json = this.getJsonString(request);
        ListDataResp resp = new ListDataResp();
        BaseReq req = null;
        try{
            if(json != null){
                req = JsonUtil.tranjsonStrToObject(json,BaseReq.class);
            }else{
                resp.setCode("-1");
                resp.setMsg("请求参数错误");
                resp.setOpedId("");
                return JsonUtil.tranObjectToJsonStr(resp);
            }

            Member member = memberService.getMemberByOpenId(req.getOpenId());
            if(member != null && member.getId()>0){
                List<MemberAddress> addressList = memberAddressService.getMemberByMemberId(member.getId());

                List<MemberAddressResp> respList = new ArrayList<MemberAddressResp>();
                for(MemberAddress info:addressList){
                    MemberAddressResp memberAddressResp = new MemberAddressResp();
                    memberAddressResp.setId(info.getId());
                    memberAddressResp.setPhone(info.getPhone());
                    memberAddressResp.setAddress(info.getAddress());
                    memberAddressResp.setPerson(info.getPerson());
                    memberAddressResp.setMemberId(info.getMemberId());
                    respList.add(memberAddressResp);
                }
                resp.setData(respList);
                resp.setCode("1");
                resp.setMsg("success");
                resp.setOpedId(req.getOpenId());
            }else{
                resp.setCode("0");
                resp.setMsg("未找到该会员信息");
                resp.setOpedId(req.getOpenId());
            }
        }catch (Exception e){
            e.printStackTrace();
            resp.setCode("-1");
            resp.setMsg("系统错误");
            resp.setOpedId(req.getOpenId());
        }

        return JsonUtil.tranObjectToJsonStr(resp);
    }

    /**
     * 校验会员邀请码
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "check/inviteCode")
    public String checkInviteCode(HttpServletRequest request){
        String json = this.getJsonString(request);
        CheckInviteCodeResp resp = new CheckInviteCodeResp();
        CheckInviteCodeReq req = null;
        try{
            if(json != null){
                req = JsonUtil.tranjsonStrToObject(json,CheckInviteCodeReq.class);
            }else{
                resp.setCode("-1");
                resp.setMsg("请求参数错误");
                resp.setOpedId("");
                return JsonUtil.tranObjectToJsonStr(resp);
            }

            Member member = memberService.getMemberByInviteCode(req.getInviteCode());
            if(member != null && member.getId()>0){
                Member inviteMember = new Member();
                inviteMember.setOpenId(req.getOpenId());
                inviteMember.setName(req.getName());
                inviteMember.setSex(0);
                inviteMember.setPhone(req.getPhone()+"");
                inviteMember.setAddress("");
                inviteMember.setInviteCode(CodeUtil.getRandomString(8));

                memberService.insertData(inviteMember);


                resp.setResult("恭喜你通过" + member.getName() + "的邀请成为会员");
                resp.setCode("1");
                resp.setMsg("success");
                resp.setOpedId(req.getOpenId());
            }else{
                resp.setCode("0");
                resp.setMsg("您输入的会员邀请码不正确，请重新输入");
                resp.setOpedId(req.getOpenId());
            }
        }catch (Exception e){
            e.printStackTrace();
            resp.setCode("-1");
            resp.setMsg("系统错误");
            resp.setOpedId(req.getOpenId());
        }

        return JsonUtil.tranObjectToJsonStr(resp);
    }

    /**
     * 用户注册
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "user/register")
    public String userRegister(HttpServletRequest request){
        String json = this.getJsonString(request);
        BaseResp resp = new BaseResp();
        RegisterReq req = null;
        try{
            if(json != null){
                req = JsonUtil.tranjsonStrToObject(json,RegisterReq.class);
            }else{
                resp.setCode("-1");
                resp.setMsg("请求参数错误");
                resp.setOpedId("");
                return JsonUtil.tranObjectToJsonStr(resp);
            }
            if(req.getInviteName() != null && !"".equals(req.getInviteName())){
                Member member = memberService.getMemberByInviteName(req.getInviteName());
                if(member != null && member.getId()>0){
                    MemberApply memberApply = new MemberApply();
                    memberApply.setPhone(req.getPhone());
                    memberApply.setName(req.getUserName());
                    memberApply.setAddress(req.getAddress());
                    memberApply.setInviteMemberId(member.getId());
                    memberApply.setSex(req.getSex());
                    memberApply.setOpenId(req.getOpenId());
                    memberApply.setStatus(0);
                    memberApplyService.insertData(memberApply);

                    resp.setCode("1");
                    resp.setMsg("success");
                    resp.setOpedId(req.getOpenId());
                }else{
                    resp.setCode("0");
                    resp.setMsg("您输入的推荐会员姓名不正确，请重新输入");
                    resp.setOpedId(req.getOpenId());
                }
            }else{
                MemberApply memberApply = new MemberApply();
                memberApply.setPhone(req.getPhone());
                memberApply.setName(req.getUserName());
                memberApply.setAddress(req.getAddress());
                memberApply.setInviteMemberId(0);
                memberApply.setSex(req.getSex());
                memberApply.setOpenId(req.getOpenId());
                memberApply.setStatus(0);
                memberApplyService.insertData(memberApply);

                resp.setCode("1");
                resp.setMsg("success");
                resp.setOpedId(req.getOpenId());
            }

        }catch (Exception e){
            e.printStackTrace();
            resp.setCode("-1");
            resp.setMsg("系统错误");
            resp.setOpedId(req.getOpenId());
        }

        return JsonUtil.tranObjectToJsonStr(resp);
    }

    /**
     * 获取商品列表
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getGoods")
    public String getGoods(HttpServletRequest request){
        String json = this.getJsonString(request);
        ListDataResp resp = new ListDataResp();
        BaseReq req = null;
        try{
            if(json != null){
                req = JsonUtil.tranjsonStrToObject(json,BaseReq.class);
            }else{
                resp.setCode("-1");
                resp.setMsg("请求参数错误");
                resp.setOpedId(req.getOpenId());
                return JsonUtil.tranObjectToJsonStr(resp);
            }

            List<Goods> list = goodsService.getGoodsList();
            List<GoodResp> respList = new ArrayList<>();
            for(Goods good:list){
                GoodResp goodResp=new GoodResp();
                goodResp.setGoodId(good.getId());
                goodResp.setGoodName(good.getName());
                goodResp.setGoodTitle(good.getTitle());
                goodResp.setGoodPrice(good.getPrice());
                goodResp.setAdvImg(good.getImage());
                goodResp.setGoodSubTitle(good.getSubTitle());
                goodResp.setGoodDes(good.getDescribe());
                goodResp.setThumb(good.getThumb());

                List<GoodsSpec> specList = goodsSpecService.getSpecLsBygoodId(good.getId());
                if(specList != null && specList.size() > 0){
                    List<GoodSpecResp> goodSpecRespList = new ArrayList<>();
                    for(GoodsSpec spec:specList){
                        GoodSpecResp goodSpecResp = new GoodSpecResp();
                        goodSpecResp.setId(spec.getId());
                        goodSpecResp.setName(spec.getSpecName());
                        goodSpecResp.setPrice(spec.getPrice());
                        goodResp.setGoodAddress(spec.getAddress());
                        goodSpecRespList.add(goodSpecResp);
                    }

                    goodResp.setGoodSpec(goodSpecRespList);
                }

                respList.add(goodResp);
            }
            resp.setData(respList);
            resp.setCode("1");
            resp.setMsg("success");
            resp.setOpedId(req.getOpenId());
        }catch (Exception e){
            e.printStackTrace();
            resp.setCode("-1");
            resp.setMsg("系统错误");
            resp.setOpedId(req.getOpenId());
        }
        return JsonUtil.tranObjectToJsonStr(resp);
    }

    /**
     * 购买商品
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "buy/goods")
    public String buyGoods(HttpServletRequest request){
        String json = this.getJsonString(request);
        ObjectDataResp resp = new ObjectDataResp();
        BuyGoods req = null;
        try{
            if(json != null){
                req = JsonUtil.tranjsonStrToObject(json,BuyGoods.class);
            }else{
                resp.setCode("-1");
                resp.setMsg("请求参数错误");
                resp.setOpedId(req.getOpenId());
                return JsonUtil.tranObjectToJsonStr(resp);
            }

            Member member = memberService.getMemberByOpenId(req.getOpenId());
            if(member != null && member.getId()>0){
                Order order = new Order();
                order.setBuyMemberId(member.getId());
                order.setGoodsId(req.getGoodId());
                order.setGoodsNum(req.getGoodNum());
                order.setAddressId(req.getAddressId());
                order.setBuyerMessage(req.getBuyerMessage());
                order.setOrderNo((new Date()).getTime() + "");
                order.setGoodsSpecId(req.getGoodSpecId());
                order.setAddress(req.getAddress());
                order.setPerson(req.getPerson());
                order.setPhone(req.getPhone());
                order.setTotalPrice(req.getGoodPrice());

                TaxInfo taxInfo = new TaxInfo();
                taxInfo.setReceiptNo(req.getReceiptNo());
                taxInfo.setReceiptTitle(req.getReceiptTitle());
                taxInfo.setReceiptType(req.getReceiptType());
                order.setTaxInfo(JsonUtil.tranObjectToJsonStr(taxInfo));
                order.setStatus(-1);
                //购买订单
                order.setType(0);

                order.setCreatedTime(new Date());
                order.setBatchId("");
                order.setGoodsCode("");

                orderService.insertData(order);

                resp.setData(order.getOrderNo());
                resp.setCode("1");
                resp.setMsg("success");
                resp.setOpedId(req.getOpenId());
            }else{
                resp.setCode("0");
                resp.setMsg("未找到该会员信息");
                resp.setOpedId(req.getOpenId());
                return JsonUtil.tranObjectToJsonStr(resp);
            }

            resp.setCode("1");
            resp.setMsg("success");
            resp.setOpedId(req.getOpenId());
        }catch (Exception e){
            e.printStackTrace();
            resp.setCode("-1");
            resp.setMsg("系统错误");
            resp.setOpedId(req.getOpenId());
        }
        return JsonUtil.tranObjectToJsonStr(resp);
    }


    @ResponseBody
    @RequestMapping(value = "pay/goods/success")
    public String paySuccess(HttpServletRequest request){
        String json = this.getJsonString(request);
        ObjectDataResp resp = new ObjectDataResp();
        PaySussessReq req = null;
        String batchMessage = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            if(json != null){
                req = JsonUtil.tranjsonStrToObject(json,PaySussessReq.class);
            }else{
                resp.setCode("-1");
                resp.setMsg("请求参数错误");
                resp.setOpedId(req.getOpenId());
                return JsonUtil.tranObjectToJsonStr(resp);
            }
            String orderNo = req.getOrderNo();
//            orderNo = "1502276362910";
            Order order = orderService.getByOrderNo(orderNo);
            String batchId = "";
            String goodsCode = "";
            //获取最新的批次号码
            List<Batch> batchList = batchService.getNewBatch(order.getGoodsNum());
            for(int i=0;i<batchList.size();i++){
                if(i==0){
                    batchMessage += batchList.get(i).getBatchName() +"第"+batchList.get(i).getBatchNo();
                }else{
                    batchMessage += ","+batchList.get(i).getBatchNo();
                }
                if(i==(batchList.size()-1)){
                    batchId += batchList.get(i).getId();
                    goodsCode += batchList.get(i).getGoodsCode();
                }else{
                    batchId += batchList.get(i).getId()+",";
                    goodsCode += batchList.get(i).getGoodsCode() + ",";
                }
                batchService.updateStatusById(1,batchList.get(i).getId());
            }
            batchMessage += "盒";
            order.setBatchId(batchId);
            order.setGoodsCode(goodsCode);
            orderService.updateData(order);

            PaySuccessResp paySuccess = new PaySuccessResp();
            paySuccess.setBatch(batchMessage);
            paySuccess.setReceiver("陕西百果园");
            paySuccess.setPayTime(dateFormat.format(order.getCreatedTime()));
            paySuccess.setOrderPrice(order.getTotalPrice() + "");
            paySuccess.setPerson(order.getPerson());
            paySuccess.setPhone(order.getPhone());
            paySuccess.setAddress(order.getAddress());

            resp.setData(paySuccess);
            resp.setCode("1");
            resp.setMsg("请求成功");
        }catch (Exception e){
            e.printStackTrace();
            resp.setCode("-1");
            resp.setMsg("系统错误");
        }
        return JsonUtil.tranObjectToJsonStr(resp);
    }

    /**
     * 校验提货码
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "take/good")
    public String takeGood(HttpServletRequest request) {
        String json = this.getJsonString(request);
        ObjectDataResp resp = new ObjectDataResp();
        TakeCodeReq req = null;
        try {
            if (json != null) {
                req = JsonUtil.tranjsonStrToObject(json, TakeCodeReq.class);
            } else {
                resp.setCode("-1");
                resp.setMsg("请求参数错误");
                resp.setOpedId(req.getOpenId());
                return JsonUtil.tranObjectToJsonStr(resp);
            }
            Batch batch = batchService.getBatchByGoodsCode(req.getTakeCode());
            if(batch == null){
                resp.setCode("0");
                resp.setMsg("提货码有误，请重新输入");
                resp.setOpedId(req.getOpenId());
                return JsonUtil.tranObjectToJsonStr(resp);
            }else{
                TakeCodeResp takeCodeResp = new TakeCodeResp();
                Order order = orderService.getByGoodsCode(req.getTakeCode());
                if(order == null ){
                    resp.setCode("0");
                    resp.setMsg("提货码有误，请重新输入");
                    resp.setOpedId(req.getOpenId());
                }else{
                    Member member = memberService.getMemberById(order.getBuyMemberId());
                    Goods goods = goodsService.getGoodsById(order.getGoodsId());
                    String batchString = "此盒柿饼是好柿连连柿园"+batch.getBatchName()+"第"+batch.getBatchNo()+"盒流星吊柿";
                    takeCodeResp.setGoodBatch(batchString);
                    takeCodeResp.setGoodId(goods.getId());
                    takeCodeResp.setGoodName(goods.getName());
                    takeCodeResp.setGoodImg(goods.getImage().split(",")[0]);
                    takeCodeResp.setReserveMember(member.getName());
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Calendar calendar   =   new GregorianCalendar();
                    calendar.setTime(new Date());
                    calendar.add(calendar.DATE, 2);//把日期往后增加一天.整数往后推,负数往前移动
                    calendar.getTime();   //这个时间就是日期往后推一天的结果

                    takeCodeResp.setSendTime(dateFormat.format(calendar.getTime()));

                    resp.setCode("1");
                    resp.setMsg("请求成功");
                    resp.setOpedId(req.getOpenId());
                    resp.setData(takeCodeResp);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.setCode("-1");
            resp.setMsg("系统错误");
            resp.setOpedId(req.getOpenId());
        }
        return JsonUtil.tranObjectToJsonStr(resp);
    }


    /**
     * 确认提货
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "confirm/takeGood")
    public String confirmTakeGood(HttpServletRequest request){
        String json = this.getJsonString(request);
        ConfirmTakeResp resp = new ConfirmTakeResp();
        ConfirmTakeGood req = null;
        try{
            if(json != null){
                req = JsonUtil.tranjsonStrToObject(json,ConfirmTakeGood.class);
            }else{
                resp.setCode("-1");
                resp.setMsg("请求参数错误");
                resp.setOpedId(req.getOpenId());
                return JsonUtil.tranObjectToJsonStr(resp);
            }

            Batch batch = batchService.getBatchByGoodsCode(req.getTakeCode());
            if(batch == null){
                resp.setCode("0");
                resp.setMsg("提货码有误，请重新输入");
                resp.setOpedId(req.getOpenId());
                return JsonUtil.tranObjectToJsonStr(resp);
            }

            Member member = memberService.getMemberByOpenId(req.getOpenId());
            if(member != null && member.getId()>0){
                Order order = new Order();
                order.setBuyMemberId(member.getId());
                order.setGoodsId(req.getGoodId());
                order.setGoodsNum(1);
                order.setGoodsCode(req.getTakeCode());
                order.setOrderNo((new Date()).getTime() + "");
                order.setAddress(req.getAddress());
                order.setPerson(req.getPerson());
                order.setPhone(req.getPhone());

                order.setStatus(1);
                //提货订单
                order.setType(1);

                order.setCreatedTime(new Date());
                order.setBatchId(batch.getId()+"");

                orderService.insertData(order);

                //重置提货验证码
                batchService.updateStatusById(2, batch.getId());

                resp.setResult(req.getReserveMember() + "已邀请您成为好柿连连会员，去柿园看看");
                resp.setCode("1");
                resp.setMsg("success");
                resp.setOpedId(req.getOpenId());
            }else{
                Order orderBuy = orderService.getByGoodsCode(req.getTakeCode());
                Member memberBuy = memberService.getMemberById(orderBuy.getBuyMemberId());

                Member memberNew = new Member();
                memberNew.setOpenId(req.getOpenId());
                memberNew.setName(req.getPerson());
                memberNew.setInviteCode(CodeUtil.getRandomString(8));
                memberService.insertData(memberNew);

                Order order = new Order();
                order.setBuyMemberId(memberService.getMemberByOpenId(req.getOpenId()).getId());
                order.setGoodsId(req.getGoodId());
                order.setGoodsNum(1);
                order.setGoodsCode(req.getTakeCode());
                order.setOrderNo((new Date()).getTime() + "");
                order.setAddress(req.getAddress());
                order.setPerson(req.getPerson());
                order.setPhone(req.getPhone());

                order.setStatus(1);
                //提货订单
                order.setType(1);

                order.setCreatedTime(new Date());
                order.setBatchId(batch.getId()+"");

                orderService.insertData(order);

                //重置提货验证码
                batchService.updateStatusById(2, batch.getId());

                resp.setResult(req.getReserveMember()+"已邀请您成为好柿连连会员，去柿园看看");
                resp.setCode("1");
                resp.setMsg("success");
                resp.setOpedId(req.getOpenId());
            }

        }catch (Exception e){
            e.printStackTrace();
            resp.setCode("-1");
            resp.setMsg("系统错误");
            resp.setOpedId(req.getOpenId());
        }
        return JsonUtil.tranObjectToJsonStr(resp);
    }

    /**
     * 我的订单
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "myorder")
    public String myorder(HttpServletRequest request) {
        String json = this.getJsonString(request);
        ListDataResp resp = new ListDataResp();
        MyOrderReq req = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int pageSize = 20;
        try {
            if (json != null) {
                req = JsonUtil.tranjsonStrToObject(json, MyOrderReq.class);
            } else {
                resp.setCode("-1");
                resp.setMsg("请求参数错误");
                resp.setOpedId(req.getOpenId());
                return JsonUtil.tranObjectToJsonStr(resp);
            }

            Member member = memberService.getMemberByOpenId(req.getOpenId());
            if(member != null && member.getId()>0){
                List<Order> orderList = orderService.getListByMemberId(member.getId(),(req.getPage()-1)*pageSize,pageSize);

                List<MyOrderResp> respList = new ArrayList<>();
                for(Order order :orderList){
                    MyOrderResp myOrderResp = new MyOrderResp();
                    myOrderResp.setOrderNo(order.getOrderNo());
                    myOrderResp.setOrderTime(dateFormat.format(order.getCreatedTime()));
                    myOrderResp.setAddress(order.getAddress());
                    myOrderResp.setPerson(order.getPerson());
                    myOrderResp.setPhone(order.getPhone());
                    myOrderResp.setGoodNum(order.getGoodsNum());

                    if(order.getStatus() == -1){
                        myOrderResp.setOrderState("未支付");
                    }else if(order.getStatus() == 1){
                        myOrderResp.setOrderState("等待发货");
                    }else if(order.getStatus() == 2){
                        myOrderResp.setOrderState("已发货");
                    }else if(order.getStatus() == 3){
                        myOrderResp.setOrderState("已经确认收货");
                    }

                    Goods goods = goodsService.getGoodsById(order.getGoodsId());
                    myOrderResp.setGoodName(goods.getName());
                    myOrderResp.setGoodIcon(goods.getThumb());
                    myOrderResp.setOrderPrice(order.getTotalPrice() + "");
                    if (order.getSendTime() != null){
                        myOrderResp.setSendTime(dateFormat.format(order.getSendTime()));
                    }else{

                        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
                        Calendar calendar   =   new GregorianCalendar();
                        calendar.setTime(new Date());
                        calendar.add(calendar.DATE, 2);//把日期往后增加一天.整数往后推,负数往前移动
                        calendar.getTime();   //这个时间就是日期往后推一天的结果

                        myOrderResp.setSendTime(dateFormat1.format(calendar.getTime()));
                    }

                    respList.add(myOrderResp);
                }

                resp.setData(respList);
                resp.setCode("1");
                resp.setMsg("success");
                resp.setOpedId(req.getOpenId());
            }else{
                resp.setCode("0");
                resp.setMsg("未找到该会员信息");
                resp.setOpedId(req.getOpenId());
                return JsonUtil.tranObjectToJsonStr(resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.setCode("-1");
            resp.setMsg("系统错误");
            resp.setOpedId(req.getOpenId());
        }
        return JsonUtil.tranObjectToJsonStr(resp);
    }

    /**
     * 修改收货地址
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "changeOrderAddress")
    public String changeOrderAddress(HttpServletRequest request) {
        String json = this.getJsonString(request);
        BaseResp resp = new BaseResp();
        ChangeOrderAddressReq req = null;

        try {
            if (json != null) {
                req = JsonUtil.tranjsonStrToObject(json, ChangeOrderAddressReq.class);
            } else {
                resp.setCode("-1");
                resp.setMsg("请求参数错误");
                resp.setOpedId(req.getOpenId());
                return JsonUtil.tranObjectToJsonStr(resp);
            }

            Order order = orderService.getByOrderNo(req.getOrderNo());
            order.setAddress(req.getAddress());
            order.setPhone(req.getPhone());
            order.setPerson(req.getPerson());

            orderService.updateData(order);

            resp.setCode("1");
            resp.setMsg("success");
            resp.setOpedId(req.getOpenId());
        } catch (Exception e) {
            e.printStackTrace();
            resp.setCode("-1");
            resp.setMsg("系统错误");
            resp.setOpedId(req.getOpenId());
        }
        return JsonUtil.tranObjectToJsonStr(resp);
    }


    @ResponseBody
    @RequestMapping(value = "getJq")
    public String getJq(HttpServletRequest request) {
        String json = this.getJsonString(request);
        ListDataResp resp = new ListDataResp();

        try {
            List<Jieqi> dataList = jieqiService.getJieqi();

            resp.setData(dataList);
            resp.setCode("1");
            resp.setMsg("success");
            resp.setOpedId("");
        } catch (Exception e) {
            e.printStackTrace();
            resp.setCode("-1");
            resp.setMsg("系统错误");
            resp.setOpedId("");
        }
        return JsonUtil.tranObjectToJsonStr(resp);
    }

}
