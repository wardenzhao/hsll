package cn.thinkjoy.hsll.controller;

/**
 * Created by wpliu on 16/3/2.
 */

import cn.thinkjoy.hsll.bean.Member;
import cn.thinkjoy.hsll.bean.MemberAddress;
import cn.thinkjoy.hsll.bean.SnsapiUserinfo;
import cn.thinkjoy.hsll.req.BaseReq;
import cn.thinkjoy.hsll.req.EditAddressReq;
import cn.thinkjoy.hsll.resp.*;
import cn.thinkjoy.hsll.service.MemberAddressService;
import cn.thinkjoy.hsll.service.MemberService;
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
import java.util.ArrayList;
import java.util.List;

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

    @RequestMapping(value = "/check/isuser",method = RequestMethod.GET)
    public String index(HttpServletRequest request){
        BaseResp reObject = new BaseResp();
        SnsapiUserinfo snsapiUserinfo = (SnsapiUserinfo)request.getSession().getAttribute(KeyUtil.SESSION_KEY);
        if(snsapiUserinfo == null){
            String INIT_URL = "http://172.16.222.194:8080/hsll/auth/init";
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
        }

        Member member = memberService.getMemberByOpenId(req.getOpenId());
        if(member != null && member.getId()>0){
            MemberResp memberResp = new MemberResp();
            memberResp.setId(member.getId());
            memberResp.setOpenId(member.getOpenId());
            memberResp.setUserName(member.getName());
            memberResp.setAddress(member.getAddress());
            memberResp.setInviteCode(member.getInviteCode());
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
            }

            Member member = memberService.getMemberByOpenId(req.getOpenId());
            if(member != null && member.getId()>0){
                List<MemberAddress> addressList = memberAddressService.getMemberByMemberId(member.getId());

                List<MemberAddressResp> respList = new ArrayList<MemberAddressResp>();
                for(MemberAddress info:addressList){
                    MemberAddressResp memberAddressResp = new MemberAddressResp();
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
}