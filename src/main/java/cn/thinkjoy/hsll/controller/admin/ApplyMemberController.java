package cn.thinkjoy.hsll.controller.admin;

import cn.thinkjoy.hsll.bean.Member;
import cn.thinkjoy.hsll.bean.MemberApply;
import cn.thinkjoy.hsll.bean.adminBean.MemberResPonse;
import cn.thinkjoy.hsll.bean.adminBean.ResultResponse;
import cn.thinkjoy.hsll.service.MemberApplyService;
import cn.thinkjoy.hsll.service.MemberService;
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
 * Created by wpliu on 17/8/20.
 */
@Scope("prototype")
@Controller("applyMemberController")
@RequestMapping(value = "/admin/apply")
public class ApplyMemberController {

    @Autowired
    private MemberApplyService memberApplyService;
    @Autowired
    private MemberService memberService;
    /**
     * 会员申请列表
     * @param request
     * @param response
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public MemberResPonse list(HttpServletRequest request,HttpServletResponse response,int pageNo,int pageSize){
        MemberResPonse memberResPonse=new MemberResPonse();
            List<MemberApply> memberApplies=memberApplyService.getMemberApplys(pageNo*pageSize,pageNo);
            int count=memberApplyService.getMemberApplysCount();
            for(int i=0;i<memberApplies.size();i++){
                MemberApply apply=new MemberApply();
                 Member member=  memberService.getMemberById(apply.getInviteMemberId());
                apply.setInviteMemberName(member.getName());
            }
        memberResPonse.setPageNo(pageNo);
        memberResPonse.setMemberList(memberApplies);
        memberResPonse.setListCount(count);
        return memberResPonse;
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
    public MemberApply applyDetail(HttpServletRequest request,HttpServletResponse response,long id){
       MemberApply memberApply= memberApplyService.getMemberApplyDetail(id);
        Member member=  memberService.getMemberById(memberApply.getInviteMemberId());
        memberApply.setInviteMemberName(member.getName());
        return memberApply;
    }



    /**
     * 会员审核
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping(value = "/check",method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse check(HttpServletRequest request,HttpServletResponse response,long id,int status){
        ResultResponse result=new ResultResponse();
        memberApplyService.updateApplyStatus(id,status);
        result.setRet(0);
        result.setMsg("success");
        return result;
    }



}
