package cn.thinkjoy.hsll.bean.adminBean;

import cn.thinkjoy.hsll.bean.Member;

import java.util.List;

/**
 * Created by wpliu on 17/8/19.
 */
public class MemberResPonse {
    private int pageNo;
    private int listCount;
    private List<Member> memberList;

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

    public List<Member> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<Member> memberList) {
        this.memberList = memberList;
    }
}
